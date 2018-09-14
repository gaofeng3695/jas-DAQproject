package cn.jasgroup.jasframework.acquisitiondata.statistics.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.MonthlyEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsProcessEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.dao.OverallStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataEntryAuditBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DateStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.WeldInfoBo;
import com.google.common.collect.*;
import com.sun.star.corba.ObjectKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum.COLD_BEND;
import static cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum.HOT_BEND;
import static cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum.STRAIGHT_STEEL_PIPE;

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
        List<WeldInfoBo> patchRelaWeldList = this.overallStatisticsDao.listPatchRelaWeldInfo(projectOids);

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
     * 各工序当前年分月完成情况对比
     */
    public  Table<String, Integer, Object> processMonthlyCompletion(List<String> projectOids) {

        List<Integer> monthlys = Lists.newArrayList(MonthlyEnum.values()).stream().map(MonthlyEnum::getMonth).collect(Collectors.toList());

        // TODO: 测量放线, 管沟回填, 地貌恢复
        List<DateStatsResultBo> otherStatsResult = this.overallStatisticsDao.statsOtherLengthMonthly(projectOids);

        // TODO: 管材
        List<DateStatsResultBo> pipeStatsResult = this.overallStatisticsDao.statsPipeLengthMonthly(projectOids);

        // TODO: 焊接
        List<WeldInfoBo> weldInfoBos = this.overallStatisticsDao.listWeldInfoCurrentYear(projectOids);
        List<DateStatsResultBo> weldStatsResult = getDateStatsResults(weldInfoBos);

        // TODO: 补口
        List<WeldInfoBo> patchRelaWelInfoBos = this.overallStatisticsDao.listPatchRelaWeldInfoByYear(projectOids);
        List<DateStatsResultBo> patchStatsResult = getDateStatsResults(patchRelaWelInfoBos);

        List<DateStatsResultBo> statsResult = Lists.newArrayList();
        statsResult.addAll(pipeStatsResult);
        statsResult.addAll(weldStatsResult);
        statsResult.addAll(patchStatsResult);
        statsResult.addAll(otherStatsResult);

        // 初始化table
        Table<String, Integer, Object> table = HashBasedTable.create();
//        for (DateStatsResultBo bo : statsResult) {
//            table.put(bo.getStatsType(), bo.getStatsMonth(), bo.getStatsResult());
//        }

        // 初始化分月数据
        for (StatsProcessEnum processEnum : StatsProcessEnum.values()) {
            Arrays.stream(MonthlyEnum.values()).forEach(monthlyEnum -> table.put(processEnum.getType(), monthlyEnum.getMonth(), 0));
        }

        statsResult.forEach(bo -> table.put(bo.getStatsType(), bo.getStatsMonth(), bo.getStatsResult()==null?0:bo.getStatsResult()));

//        table.rowKeySet().forEach(statsType -> {
//            for (MonthlyEnum monthlyEnum : MonthlyEnum.values()) {
//                if (!table.contains(statsType, monthlyEnum.getMonth())) {
//                    table.put(statsType, monthlyEnum.getMonth(), 0);
//                }
//            }
//        });

        // 计算累积结果
        Table<String, Integer, Object> resultTable = HashBasedTable.create();
        for (String statsType : table.rowKeySet()) {
            for (MonthlyEnum monthlyEnum : MonthlyEnum.values()) {
                resultTable.put(statsType, monthlyEnum.getMonth(), this.getCumulativeCount(table, statsType, monthlyEnum.getMonth()));
            }
        }

        return resultTable;
    }

    private List<DateStatsResultBo> getDateStatsResults(List<WeldInfoBo> weldInfoBos) {
        List<StatsResultBo> pipeLengthResult = getPipeLength(weldInfoBos);
        Map<String, Double> idToLengthMap = new HashMap<>();
        for (StatsResultBo bo : pipeLengthResult) {
            Double pipeLength = bo.getStatsResult()==null ? 0d:Double.parseDouble(bo.getStatsResult().toString());
            idToLengthMap.put(bo.getOid(), pipeLength);
        }

        Set<Integer> monthSet = weldInfoBos.stream().map(WeldInfoBo::getStatsMonth).collect(Collectors.toSet());
        List<DateStatsResultBo> weldStatsResult = Lists.newArrayList();
        for (Integer month : monthSet) {
            Set<String> pipeOidsForMonth = this.getWeldPipeOidsByMonth(weldInfoBos, month);
            weldStatsResult.add(new DateStatsResultBo("weld", this.sumPipeLength(idToLengthMap, pipeOidsForMonth), month));
        }

        return weldStatsResult;
    }

    private List<StatsResultBo> getPipeLength(List<WeldInfoBo> weldInfoBos) {
        List<String> pipeOids = getWeldPipeOidsByType(weldInfoBos, StatsPipeEnum.STRAIGHT_STEEL_PIPE);
        List<String> hotOids = getWeldPipeOidsByType(weldInfoBos, StatsPipeEnum.HOT_BEND);
        List<String> coldOids = getWeldPipeOidsByType(weldInfoBos, StatsPipeEnum.COLD_BEND);
        return this.overallStatisticsDao.queryPipeLengthOidIn(ImmutableMap.of(
                STRAIGHT_STEEL_PIPE.getCode(), pipeOids, HOT_BEND.getCode(), hotOids, COLD_BEND.getCode(), coldOids
        ));
    }


    private Double sumPipeLength(Map<String, Double> idToLengthMap, Set<String> oids) {
        List<Double> sumList = oids.stream().map(idToLengthMap::get).collect(Collectors.toList());
        double sum = sumList.stream().mapToDouble(s -> (s == null ? 0 : s)).sum();
        return sum;
    }

    private List<String> getWeldPipeOidsByType(List<WeldInfoBo> weldInfoBos, StatsPipeEnum statsPipeEnum) {
        List<String> pipeOidList = Lists.newArrayList();
        for (WeldInfoBo weldInfoBo : weldInfoBos) {
            if (statsPipeEnum.getCode().equals(weldInfoBo.getFrontPipeType())) {
                pipeOidList.add(weldInfoBo.getFrontPipeOid());
            }

            if (statsPipeEnum.getCode().equals(weldInfoBo.getBackPipeType())) {
                pipeOidList.add(weldInfoBo.getBackPipeOid());
            }
        }
        return pipeOidList;
    }

    private Set<String> getWeldPipeOidsByMonth(List<WeldInfoBo> weldInfoBos, Integer month) {
        Set<String> pipeOidList = Sets.newHashSet();
        for (WeldInfoBo weldInfoBo : weldInfoBos) {
            if (month.equals(weldInfoBo.getStatsMonth())) {
                pipeOidList.add(weldInfoBo.getFrontPipeOid());
                pipeOidList.add(weldInfoBo.getBackPipeOid());
            }
        }
        return pipeOidList;
    }

    private Double getCumulativeCount(Table<String, Integer, Object> table, String rowKey, Integer columnKey) {
        Double count = 0d;
        for (Integer i = 0; i < columnKey; i++) {
            count = Double.sum(count, Double.parseDouble(table.get(rowKey, i+1).toString()));
        }
        return count;
    }




    /**
     * 统计焊口信息中前后管件长度(按月分类统计)
     * 只计算{@link StatsPipeEnum} 这几种管子的长度)
     * @param weldList 焊口信息集合
     * @param projectOids 项目ID
     * @param statsType 统计类型的标识(焊接和补口可以共用)
     * @return List
     */
    private List<DateStatsResultBo> statsPipeLengthMonthly(List<WeldInfoBo> weldList, List<String> projectOids, String statsType) {
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
                ImmutableMap.of("projectOids", projectOids, StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), straightSteelPipes,
                        StatsPipeEnum.HOT_BEND.getCode(), hotBends, StatsPipeEnum.COLD_BEND.getCode(), coldBends));

        return null;
    }


    /**
     * 统计焊口信息中的前后管件长度
     * 只计算{@link StatsPipeEnum} 这几种管子的长度
     * @param weldList 焊口信息集合
     * @param projectOids 项目ID
     * @param statsType 统计类型的标识(焊接和补口可以共用)
     * @return List
     */
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
                ImmutableMap.of("projectOids", projectOids, StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), straightSteelPipes,
                        StatsPipeEnum.HOT_BEND.getCode(), hotBends, StatsPipeEnum.COLD_BEND.getCode(), coldBends));
        if (CollectionUtils.isEmpty(resultBos)) {
            logger.info("[工序累计统计]类型{}统计结果为空", statsType);
            return Lists.newArrayList(new StatsResultBo(statsType, 0));
        }

        return resultBos;
    }


    public Map<String, Integer> statsDetectionRayPassCount(List<String> projectOids) {
        List<Map<String, Integer>> returnList = this.overallStatisticsDao.statsDetectionRayPassCount(projectOids);
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
