package cn.jasgroup.jasframework.acquisitiondata.statistics.service;

import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.MonthlyEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.dao.OverallStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataEntryAuditBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DateStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.WeldInfoBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import com.google.common.collect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private OverallStatisticsDao overallStatisticsDao;


    /**
     * 各工序累计完成情况
     * @param projectOids 项目IDs
     * @return ProcessCompletionBo
     */
    public List<StatsResultBo> processCumulativeCompletion(List<String> projectOids) {

        List<StatsResultBo> resultList = Lists.newArrayList();

        // 焊接关联的管件信息
        List<WeldInfoBo> weldList = this.overallStatisticsDao.listWeldInfo(projectOids);

        // 补口关联的焊口关联的管件信息
        List<WeldInfoBo> patchRelaWeldList = this.overallStatisticsDao.listWeldRelaAnticorrosionInfo(projectOids);

        // 管材
        resultList.addAll(this.overallStatisticsDao.statsPipeLength(projectOids));

        // 焊接/补口
        resultList.addAll(statsPipeLength(weldList, projectOids, "weld"));
        resultList.addAll(statsPipeLength(patchRelaWeldList, projectOids, "patch"));

        // 测量放线, 管沟回填, 地貌恢复
        resultList.addAll(this.overallStatisticsDao.statsOtherLength(projectOids));

        return resultList;
    }


    /**
     * 各工序分月完成情况对比
     */
    public void processMonthlyCompletion(List<String> projectOids) {

        List<Integer> monthlys = Lists.newArrayList(MonthlyEnum.values()).stream().map(MonthlyEnum::getMonth).collect(Collectors.toList());

        // 测量放线, 管沟回填, 地貌恢复
        List<DateStatsResultBo> otherStatsResult = this.overallStatisticsDao.statsOtherLengthMonthly(projectOids);
        Table<String, Integer, Object> table = HashBasedTable.create();
        for (DateStatsResultBo bo : otherStatsResult) {
            table.put(bo.getStatsType(), bo.getStatsMonth(), bo.getStatsResult());
        }

        for (DateStatsResultBo bo : otherStatsResult) {
            if (!monthlys.contains(bo.getStatsMonth())) {

            }
        }

        for (String statsType : table.rowKeySet()) {

        }

    }

    private static void initMonthly(List<DateStatsResultBo> otherStatsResult) {

        List<Integer> monthlyCollect = Lists.newArrayList(MonthlyEnum.values()).stream().map(MonthlyEnum::getMonth).collect(Collectors.toList());

        for (DateStatsResultBo bo : otherStatsResult) {

        }
    }

    public static void main(String[] args) {
        initMonthly(null);
    }


    private List<StatsResultBo> statsPipeLength(List<WeldInfoBo> weldList, List<String> projectOids, String statsType) {
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

        List<StatsResultBo> resultBos = this.overallStatisticsDao.statsPipeLength(statsType,
                ImmutableMap.of("projectOids", projectOids,
                        StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), straightSteelPipes,
                        StatsPipeEnum.HOT_BEND.getCode(), hotBends, StatsPipeEnum.COLD_BEND.getCode(), coldBends));
        if (CollectionUtils.isEmpty(resultBos)) {
            logger.info("[工序累计统计]类型{}统计结果为空", statsType);
            return Lists.newArrayList(new StatsResultBo(statsType, 0));
        }

        return resultBos;
    }



    /**
     * 数据录入/审核数量情况统计
     * @param projectOids 项目IDs
     * @return DataEntryAuditBo
     */
    public DataEntryAuditBo dataEntryAudit(List<String> projectOids) {
        List<DataEntryAuditBo> dataEntryAuditBos = this.overallStatisticsDao.dataEntryAudit(projectOids);
        return new DataEntryAuditBo(
                dataEntryAuditBos.stream().mapToInt(DataEntryAuditBo::getTotal).sum(),
                dataEntryAuditBos.stream().mapToInt(DataEntryAuditBo::getNeedAudit).sum(),
                dataEntryAuditBos.stream().mapToInt(DataEntryAuditBo::getAudited).sum()
        );
    }
}
