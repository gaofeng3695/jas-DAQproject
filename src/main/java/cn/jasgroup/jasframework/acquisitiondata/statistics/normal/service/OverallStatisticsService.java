package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsProcessEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsUtils;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.dao.OverallStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.DataEntryAuditBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.DateStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.StatsResultBo;
import com.google.common.collect.ArrayTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * description: 总体统计业务逻辑
 *
 * @author xiefayang
 * 2018/9/11 11:21
 */
@Service
public class OverallStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(OverallStatisticsService.class);

    private static final String YYYY_MM = "yyyy-MM";

    private static final String STRAIGHT_STEEL_PIPE = "straight_steel_pipe";
    private static final String HOT_BEND = "hot_bend";
    private static final String COLD_BEND = "cold_bend";

    @Autowired
    private OverallStatisticsDao overallStatisticsDao;


    /**
     * 各工序累计完成情况
     * @param projectIds 项目IDs
     * @return ProcessCompletionBo
     */
    public List<StatsResultBo> processCumulativeCompletion(List<String> projectIds) {
        // 统计管材, 焊口, 补口
        StatsResultBo pipeStatsResult = this.overallStatisticsDao.statsPipeLength(projectIds);
        List<StatsResultBo> weldAndPatchStatsResult = this.overallStatisticsDao.statsWeldAndPatchLengthByDate(projectIds);

        // 统计测量放线, 管沟回填, 地貌恢复
        List<StatsResultBo> otherStatsResultBos = this.overallStatisticsDao.statsOtherLength(projectIds);

        List<StatsResultBo> resultList = Lists.newArrayList(pipeStatsResult);
        resultList.addAll(weldAndPatchStatsResult);
        resultList.addAll(otherStatsResultBos);

        // null转0 && 矫正顺序
        resultList.stream().filter(bo -> bo.getStatsResult() == null).forEach(bo -> bo.setStatsResult(0));
        Map<String, Object> typeToBo = resultList.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, StatsResultBo::getStatsResult, (a, b) -> b));
        return Arrays.stream(StatsProcessEnum.values()).map(anEnum -> new StatsResultBo(anEnum.getType(), typeToBo.get(anEnum.getType()))).collect(Collectors.toList());
    }


    /**
     * 各工序分月累计完成情况(自开工以来)
     * @param projectIds 项目IDs
     * @return Table<String, String, Object>: rowKey:统计类型, columnKey:月份, value: 长度
     */
    public Table<String, String, Object> processMonthlyCompletion(List<String> projectIds) {

        // 按年月分组统计: 管材
        List<DateStatsResultBo> pipeStatsResult = this.overallStatisticsDao.statsPipeLengthGroupByYearMonth(projectIds);

        // 按年月分组统计: 测量放线, 管够回填, 地貌回复
        List<DateStatsResultBo> otherStatsResult = this.overallStatisticsDao.statsOtherLengthGroupByYearMonth(projectIds);
        List<DateStatsResultBo> weldStatsResult = this.overallStatisticsDao.statsWeldLengthGroupByYearMonth(projectIds);
        List<DateStatsResultBo> patchStatsResult = this.overallStatisticsDao.statsPatchLengthGroupByYearMonth(projectIds);

        List<DateStatsResultBo> resultBos = Lists.newArrayList();
        resultBos.addAll(pipeStatsResult);
        resultBos.addAll(weldStatsResult);
        resultBos.addAll(patchStatsResult);
        resultBos.addAll(otherStatsResult);

        if (CollectionUtils.isEmpty(resultBos)) {
            return null;
        }

        // 统计从开工年月开始: 找出最早的年月做统计开始日期
        OptionalLong minTimestamp = resultBos.stream().mapToLong(value -> StatsUtils.strToDateLong(value.getStatsDate(), YYYY_MM)).min();

        // 生成连续的年月集合: 根据统计开始日期和结束日期
        List<String> monthlyList = StatsUtils.genContinuityYearMonthStr(new Date(minTimestamp.getAsLong()), new Date(), YYYY_MM);

        // 初始化table生成连续的月份(为了保证table row key的顺序, 这里用ArrayTable先初始化一下)
        List<String> typeList = Arrays.stream(StatsProcessEnum.values()).map(StatsProcessEnum::getType).collect(Collectors.toList());
        Table<String, String, Object> table = ArrayTable.create(typeList, monthlyList);
        initTable(monthlyList, table);

        // Table赋值
        resultBos.forEach(bo -> table.put(bo.getStatsType(), bo.getStatsDate(), bo.getStatsResult()==null?0:bo.getStatsResult()));
        // 计算累积结果: 每个统计类型下的年月统计值=之前月份累计只和
        Table<String, String, Object> resultTable = ArrayTable.create(typeList, monthlyList);
        for (String statsType : table.rowKeySet()) {
            for (String yearMonth : monthlyList) {
                resultTable.put(statsType, yearMonth, getCumulativeCount(table, monthlyList, statsType, yearMonth));
            }
        }
        return resultTable;
    }



    /**
     * 初始化统计Table
     * @param dateList 连续日期(横轴: column key)
     * @param table Table
     */
    public void initTable(List<String> dateList, Table<String, String, Object> table) {
        for (StatsProcessEnum processEnum : StatsProcessEnum.values()) {
            dateList.forEach(date -> table.put(processEnum.getType(), date, 0));
        }
    }


    /**
     * 计算累计值: 计算统计日期之前所有日期统计值之和
     * @param table 统计Table
     * @param dateList 连续年月日期(必须有序)
     * @param rowKey 统计类型
     * @param columnKey 统计日期
     * @return Double
     */
    public Object getCumulativeCount(Table<String, String, Object> table, List<String> dateList, String rowKey, String columnKey) {
        if (!table.contains(rowKey, columnKey)) {
            return 0d;
        }
        int index = dateList.indexOf(columnKey);
        if (0 == index) {
            return table.get(rowKey, columnKey);
        }
        Double count = 0d;
        List<String> beforeYearMonths = Lists.partition(dateList, index).get(0);
        for (String beforeYearMonth : beforeYearMonths) {
            count = Double.sum(count, Double.parseDouble(table.get(rowKey, beforeYearMonth).toString()));
        }

        return Double.sum(count, Double.parseDouble(table.get(rowKey, columnKey).toString()));
    }


    /**
     * 统计射线检测一次性合格率
     * @param projectIds 项目IDs
     * @return Map
     */
    public Map<String, Integer> statsDetectionRayPassCount(List<String> projectIds) {
        List<Map<String, Integer>> returnList = this.overallStatisticsDao.statsDetectionRayPassCount(projectIds);
        if (CollectionUtils.isEmpty(returnList)) {
            throw new BusinessException("recourse not found", "404");
        }

        Map<String, Integer> result = returnList.get(0);
        for (String key : result.keySet()) {
            result.putIfAbsent(key, 0);
        }
        return result;
    }


    /**
     * 数据录入/审核数量情况统计
     * @param projectIds 项目IDs
     * @return DataEntryAuditBo
     */
    public DataEntryAuditBo dataEntryAudit(List<String> projectIds) {
        List<DataEntryAuditBo> dataEntryAuditBos = this.overallStatisticsDao.dataEntryAudit(projectIds);
        return new DataEntryAuditBo(
                dataEntryAuditBos.stream().mapToInt(DataEntryAuditBo::getTotal).sum(),
                dataEntryAuditBos.stream().mapToInt(DataEntryAuditBo::getNeedAudit).sum(),
                dataEntryAuditBos.stream().mapToInt(DataEntryAuditBo::getAudited).sum()
        );
    }
}
