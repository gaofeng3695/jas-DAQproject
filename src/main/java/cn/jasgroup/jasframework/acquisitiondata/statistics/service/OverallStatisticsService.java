package cn.jasgroup.jasframework.acquisitiondata.statistics.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsProcessEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsUtils;
import cn.jasgroup.jasframework.acquisitiondata.statistics.dao.OverallStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataEntryAuditBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DateStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.WeldInfoBo;
import com.google.common.collect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum.*;

/**
 * description: 总体统计业务逻辑
 *
 * @author xiefayang
 * 2018/9/11 11:21
 */
@Service
public class OverallStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(OverallStatisticsService.class);

    @Autowired
    private OverallStatisticsDao overallStatisticsDao;


    /**
     * 各工序累计完成情况
     * @param projectIds 项目IDs
     * @return ProcessCompletionBo
     */
    public List<StatsResultBo> processCumulativeCompletion(List<String> projectIds) {

        // 查询管件信息集合: 焊接关联的
        List<WeldInfoBo> weldList = this.overallStatisticsDao.listWeldInfo(projectIds);

        // 查询管件信息集合: 补口关联的焊口关联的
        List<WeldInfoBo> patchRelationWeldList = this.overallStatisticsDao.listPatchRelationWeldInfo(projectIds);

        // 统计管材, 焊口, 补口
        StatsResultBo pipeStatsResult = this.overallStatisticsDao.statsPipeLengthByType(projectIds, null);
        StatsResultBo weldStatsResult = this.statsPipeLengthByType(weldList, projectIds, StatsProcessEnum.WELD.getType());
        StatsResultBo patchStatsResult = this.statsPipeLengthByType(patchRelationWeldList, projectIds, StatsProcessEnum.PATCH.getType());
        List<StatsResultBo> resultList = Lists.newArrayList(pipeStatsResult, weldStatsResult, patchStatsResult);

        // 统计测量放线, 管沟回填, 地貌恢复
        List<StatsResultBo> otherStatsResultBos = this.overallStatisticsDao.statsOtherLength(projectIds);
        otherStatsResultBos.stream().filter(bo -> bo.getStatsResult() == null).forEach(bo -> bo.setStatsResult(0));
        resultList.addAll(otherStatsResultBos);
        return resultList;
    }



    /**
     * 各工序分月累计完成情况(自开工以来)
     * @param projectIds 项目IDs
     * @return Table<String, String, Object>: rowKey:统计类型, columnKey:月份, value: 长度
     */
    public Table<String, String, Object> processMonthlyCompletion(List<String> projectIds) {

        // 按年月统计: 管材
        List<DateStatsResultBo> pipeStatsResult = this.overallStatisticsDao.statsPipeLengthMonthly(projectIds);

        // 按年月统计: 测量放线, 管够回填, 地貌回复
        List<DateStatsResultBo> otherStatsResult = this.overallStatisticsDao.statsOtherLengthMonthly(projectIds);


        // 查询管件信息集合: 焊口信息的
        List<WeldInfoBo> weldInfoBos = this.overallStatisticsDao.listWeldInfoMonthly(projectIds);

        // 查询管件信息集合: 补口关联的焊口信息的
        List<WeldInfoBo> patchRelationWelds = this.overallStatisticsDao.listPatchRelationWeldInfoByYear(projectIds);

        List<WeldInfoBo> totalWeldList = Lists.newArrayList(weldInfoBos);
        totalWeldList.addAll(patchRelationWelds);

        // 查询要统计的管件长度Map
        Map<String, Map<String, Double>> pipeLengthMap = this.getPipeLengthMap(totalWeldList);

        List<DateStatsResultBo> weldStatsResult = getDateStatsResult(StatsProcessEnum.WELD.getType(), weldInfoBos, pipeLengthMap);
        List<DateStatsResultBo> patchStatsResult = getDateStatsResult(StatsProcessEnum.PATCH.getType(), patchRelationWelds, pipeLengthMap);

        List<DateStatsResultBo> resultBos = Lists.newArrayList();
        resultBos.addAll(pipeStatsResult);
        resultBos.addAll(weldStatsResult);
        resultBos.addAll(patchStatsResult);
        resultBos.addAll(otherStatsResult);

        // 统计从开工年月开始: 找出最早的年月做统计开始日期
        OptionalLong minTimestamp = resultBos.stream().mapToLong(value -> StatsUtils.strToDateLong(value.getStatsDate(), "yyyy-MM")).min();

        // 生成连续的年月集合: 根据统计开始日期和结束日期
        List<String> monthlyList = StatsUtils.genContinuityYearMonthStr(new Date(minTimestamp.getAsLong()), new Date());

        // 初始化table生成连续的月份
        Table<String, String, Object> table = TreeBasedTable.create();
        initTable(monthlyList, table);

        // Table赋值
        resultBos.forEach(bo -> table.put(bo.getStatsType(), bo.getStatsDate(), bo.getStatsResult()==null?0:bo.getStatsResult()));

        // 计算累积结果: 每个统计类型下的年月统计值=之前月份累计只和
        Table<String, String, Object> resultTable = HashBasedTable.create();
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
            for (String date : dateList) {
                table.put(processEnum.getType(), date, 0);
            }
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
     * 获取按年月分类的统计结果
     * @param statsType 统计类型
     * @param weldInfoBos 焊口信息集合
     * @param pipeLengthMap 管材长度
     * @return List
     */
    public List<DateStatsResultBo> getDateStatsResult(String statsType, List<WeldInfoBo> weldInfoBos, Map<String, Map<String, Double>> pipeLengthMap) {
        // WeldInfoList根据年月字段分组(key: 日期, value: 焊口信息集合)
        Map<String, List<WeldInfoBo>> dateToWeldInfoList = weldInfoBos.stream().collect(Collectors.groupingBy(WeldInfoBo::getStatsDate, Collectors.toList()));
        List<DateStatsResultBo> weldStatsResult = Lists.newArrayList();
        for (String date : dateToWeldInfoList.keySet()) {
            Double sumResult = this.statsPipeLength(pipeLengthMap, dateToWeldInfoList.get(date));
            weldStatsResult.add(new DateStatsResultBo(statsType, date, sumResult));
        }
        return weldStatsResult;
    }

    public Map<String, Map<String, Double>> getPipeLengthMap( List<WeldInfoBo> weldInfoBos) {
        Set<String> straightPipeIds = getWeldPipeIdsByType(weldInfoBos, STRAIGHT_STEEL_PIPE);
        Set<String> hotBendIds = getWeldPipeIdsByType(weldInfoBos, HOT_BEND);
        Set<String> coldBendIds = getWeldPipeIdsByType(weldInfoBos, COLD_BEND);

        List<StatsResultBo> straightPipeLengthList = Lists.newArrayList();
        List<StatsResultBo> hotBendLengthList = Lists.newArrayList();
        List<StatsResultBo> coldBendLengthList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(straightPipeIds)) {
            straightPipeLengthList = this.overallStatisticsDao.queryStraightPipeLength(straightPipeIds);
            straightPipeLengthList.stream().filter(statsResultBo -> statsResultBo.getStatsResult() == null).forEach(statsResultBo -> statsResultBo.setStatsResult(0));
        }

        if (!CollectionUtils.isEmpty(hotBendIds)) {
            hotBendLengthList = this.overallStatisticsDao.queryHotBendLength(hotBendIds);
            hotBendLengthList.stream().filter(statsResultBo -> statsResultBo.getStatsResult() == null).forEach(statsResultBo -> statsResultBo.setStatsResult(0));
        }

        if (!CollectionUtils.isEmpty(coldBendIds)) {
            coldBendLengthList = this.overallStatisticsDao.queryColdBendLength(coldBendIds);
            coldBendLengthList.stream().filter(statsResultBo -> statsResultBo.getStatsResult() == null).forEach(statsResultBo -> statsResultBo.setStatsResult(0));
        }

        Map<String, Double> straightLengthMap = straightPipeLengthList.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, statsResultBo -> Double.parseDouble(statsResultBo.getStatsResult().toString()), (a, b) -> b));
        Map<String, Double> hotBendLengthMap = hotBendLengthList.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, statsResultBo -> Double.parseDouble(statsResultBo.getStatsResult().toString()) , (a, b) -> b));
        Map<String, Double> coldBendLengthMap = coldBendLengthList.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, statsResultBo -> Double.parseDouble(statsResultBo.getStatsResult().toString()), (a, b) -> b));

        Map<String, Map<String, Double>> returnMap = Maps.newHashMap();
        returnMap.put(STRAIGHT_STEEL_PIPE.getCode(), straightLengthMap);
        returnMap.put(HOT_BEND.getCode(), hotBendLengthMap);
        returnMap.put(COLD_BEND.getCode(), coldBendLengthMap);
        return returnMap;
    }


    /**
     * 统计管件长度
     * @param pipeLengthMap 管件长度Maps
     * @param weldInfoBos 焊口信息集合
     * @return Double
     */
    public Double statsPipeLength(Map<String, Map<String, Double>> pipeLengthMap, List<WeldInfoBo> weldInfoBos) {
        Set<String> straightPipeIds = getWeldPipeIdsByType(weldInfoBos, STRAIGHT_STEEL_PIPE);
        Set<String> hotBends = getWeldPipeIdsByType(weldInfoBos, HOT_BEND);
        Set<String> coldBends = getWeldPipeIdsByType(weldInfoBos, COLD_BEND);

        Double straightPipeLength = sumPipeLength(pipeLengthMap.get(STRAIGHT_STEEL_PIPE.getCode()), straightPipeIds);
        Double hotBendLength = sumPipeLength(pipeLengthMap.get(HOT_BEND.getCode()), hotBends);
        Double coldBendLength = sumPipeLength(pipeLengthMap.get(COLD_BEND.getCode()), coldBends);
        return StatsUtils.sumExact(straightPipeLength, hotBendLength, coldBendLength);
    }



    /**
     * 计算管子的长度之和
     * @param idToLengthMap id与length的映射Map
     * @param pipeIds 管子ID集合
     * @return Double
     */
    private Double sumPipeLength(Map<String, Double> idToLengthMap, Set<String> pipeIds) {
        List<Double> sumList = pipeIds.stream().map(idToLengthMap::get).collect(Collectors.toList());
        return StatsUtils.sumExact(sumList);
    }

    private Set<String> getWeldPipeIdsByType(List<WeldInfoBo> weldInfoBos, StatsPipeEnum statsPipeEnum) {
        Set<String> pipeIds = new HashSet<>();
        for (WeldInfoBo weldInfoBo : weldInfoBos) {
            if (statsPipeEnum.getCode().equals(weldInfoBo.getFrontPipeType())) {
                pipeIds.add(weldInfoBo.getFrontPipeOid());
            }

            if (statsPipeEnum.getCode().equals(weldInfoBo.getBackPipeType())) {
                pipeIds.add(weldInfoBo.getBackPipeOid());
            }
        }
        return pipeIds;
    }


    /**
     * 统计焊口信息中的前后管件长度
     * 只计算{@link StatsPipeEnum} 这几种管子的长度
     * @param weldList 焊口信息集合
     * @param projectIds 项目ID
     * @param statsType 统计类型的标识(焊接和补口可以共用)
     * @return List
     */
    public StatsResultBo statsPipeLengthByType(List<WeldInfoBo> weldList, List<String> projectIds, String statsType) {
        if (CollectionUtils.isEmpty(weldList)) {
            return new StatsResultBo(statsType, 0);
        }
        List<String> straightSteelPipes = Lists.newArrayList(), hotBends = Lists.newArrayList(), coldBends = Lists.newArrayList();
        weldList.forEach(bo -> {
            // 前管件
            if (StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode().equals(bo.getFrontPipeType())) {
                straightSteelPipes.add(bo.getFrontPipeOid());
            } else if (StatsPipeEnum.HOT_BEND.getCode().equals(bo.getFrontPipeType())) {
                hotBends.add(bo.getFrontPipeOid());
            } else if (StatsPipeEnum.COLD_BEND.getCode().equals(bo.getFrontPipeType())) {
                coldBends.add(bo.getFrontPipeOid());
            }

            // 后管件
            if (StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode().equals(bo.getBackPipeType())) {
                straightSteelPipes.add(bo.getBackPipeOid());
            } else if (StatsPipeEnum.HOT_BEND.getCode().equals(bo.getBackPipeType())) {
                hotBends.add(bo.getBackPipeOid());
            } else if (StatsPipeEnum.COLD_BEND.getCode().equals(bo.getBackPipeType())) {
                coldBends.add(bo.getBackPipeOid());
            }
        });

        return this.overallStatisticsDao.statsPipeLengthByType(statsType,
                ImmutableMap.of("projectOids", projectIds,
                        StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), straightSteelPipes,
                        StatsPipeEnum.HOT_BEND.getCode(), hotBends, StatsPipeEnum.COLD_BEND.getCode(), coldBends));

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
