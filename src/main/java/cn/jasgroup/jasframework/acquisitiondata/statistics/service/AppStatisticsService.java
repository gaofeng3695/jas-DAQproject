package cn.jasgroup.jasframework.acquisitiondata.statistics.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.*;
import cn.jasgroup.jasframework.acquisitiondata.statistics.dao.OverallStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.dao.AppStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.*;
import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import com.google.common.collect.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/8/27 10:37
 */
@Service
public class AppStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(AppStatisticsService.class);

    @Autowired
    private AppStatisticsDao appStatisticsDao;

    @Autowired
    private OverallStatisticsService overallStatisticsService;

    @Autowired
    private DaqPrivilegeService daqPrivilegeService;

    @Autowired
    private UnitService unitService;

    /**
     * 数据录入统计
     * @param statsTypes 统计类型来源(默认统计7个)
     * - {@link EntryStatisticsBlock#PIPE_CHECKED_BLOCK}
     * - {@link EntryStatisticsBlock#WELD_APPROVE_BLOCK}
     * @return list
     */
    public List<DataEntryStatsBo> dataEntry(List<String> statsTypes, String projectOid) {

        List<DataEntryStatsBo> returnList = Lists.newArrayList();
        Map<String, String> pipeCheckedBlock = EntryStatisticsBlock.getPipeCheckedBlock();
        Map<String, String> weldApproveBlock = EntryStatisticsBlock.getWeldApproveBlock();

        if (CollectionUtils.isEmpty(statsTypes)) {
            statsTypes = Lists.newArrayList();
            statsTypes.addAll(EntryStatisticsBlock.getPipeCheckedBlock().keySet());
            statsTypes.addAll(EntryStatisticsBlock.getWeldApproveBlock().keySet());
        } else {
            statsTypes.removeIf(s -> !pipeCheckedBlock.containsKey(s) && !weldApproveBlock.containsKey(s));
        }

        List<StatsResultBo> resultList = appStatisticsDao.listDataEntry(statsTypes, projectOid);

        // pipeCheckedBlock: 没有审核操作的: 只统计录入数
        for (String statsType : pipeCheckedBlock.keySet()) {
            if (statsTypes.contains(statsType)) {
                Optional<StatsResultBo> optional = resultList.stream().filter(resultBo -> statsType.equals(resultBo.getStatsType())).findAny();
                optional.ifPresent(statsResultBo -> returnList.add(new DataEntryStatsBo(statsType, Long.valueOf(String.valueOf(statsResultBo.getStatsResult())))));
            }
        }

        // weldApproveBlock: 有审核操作的: 统计录入数, 待提交数, 打回数
        for (String statsType : weldApproveBlock.keySet()) {
            if (statsTypes.contains(statsType)) {
                long enteredCount = resultList.stream().filter(resultBo -> statsType.equals(resultBo.getStatsType())).count();
                long toSubmitCount = resultList.stream().filter(resultBo -> statsType.equals(resultBo.getStatsType()) && Objects.equals(ApproveStatusEnum.UNREPORTED.getCode(), Integer.valueOf(String.valueOf(resultBo.getStatsResult())))).count();
                long repulseCount = resultList.stream().filter(resultBo -> statsType.equals(resultBo.getStatsType()) && Objects.equals(ApproveStatusEnum.REJECT.getCode(), Integer.valueOf(String.valueOf(resultBo.getStatsResult())))).count();
                returnList.add(new DataEntryStatsBo(statsType, enteredCount, toSubmitCount, repulseCount));
            }
        }

        return returnList;
    }



    /**
     * 数据审核统计, 默认统计
     * 统计范围:
     *   - 包含6个分类及各分类下的数据统计 {@link ApproveStatisticsBlock#APPROVE_CATEGORY}
     *   - 各分类下的数据统计: {@link ApproveStatisticsBlock#ALL}
     * 过滤条件:
     *  - 根据参数施工单位过滤 (该部门及部门以下的)
     *  - 根据监理单位当前用户过滤 (部门及部门以下的)
     *  - TODO: 根据登录用户的项目ID
     */
    public List<DataApproveStatsBo> dataAuditing(String projectOid, String constructUnitId) {

        List<DataApproveStatsBo> returnList = new ArrayList<>();

        List<String> constructUnits = null;

        // 根据参数施工单位过滤 (范围: 该部门及部门以下的)
        if (!StringUtils.isEmpty(constructUnitId)) {
            PriUnit constructUnit = (PriUnit) unitService.get(PriUnit.class, constructUnitId);
            if (null == constructUnit) {
                throw new BusinessException("constructUnit Not Found", "404");
            }
            String constructUnitHierarchy = constructUnit.getHierarchy();

            if (!constructUnitHierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy()) &&
                    !constructUnitHierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())) {
                logger.error("施工/检测单位层级错误, hierarchy={}", constructUnitHierarchy);
                throw new BusinessException("施工/检测单位层级错误", "403");
            }

            constructUnits = this.appStatisticsDao.queryConstructUnitByHierarchy(constructUnitHierarchy);
            if (CollectionUtils.isEmpty(constructUnits)) {
                throw new BusinessException("ConstructUnits Not Found", "404");
            }
        }


        // 根据监理单位当前用户过滤, (范围: 部门及部门以下的)
        PriUnit currentUserUnit = (PriUnit) unitService.get(PriUnit.class, ThreadLocalHolder.getCurrentUser().getUnitId());
        String currentUnitHierarchy = currentUserUnit.getHierarchy();
        if (!currentUnitHierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())) {
            if (!UnitHierarchyEnum.supervision_unit.getHierarchy().contains(currentUnitHierarchy)) {
                logger.error("当前用户unit:{}权限错误", currentUnitHierarchy);
                throw new BusinessException("当前用户unit权限错误", "403");
            }
        }

        List<String> supervisionUnits = this.appStatisticsDao.queryConstructUnitByHierarchy(currentUnitHierarchy);

        if (CollectionUtils.isEmpty(supervisionUnits)) {
            throw new BusinessException("currentUserUnits Not Found", "404");
        }

        List<DataApproveSubBo> dataApproveSubBos = this.appStatisticsDao.listDataAuditing(projectOid, supervisionUnits, constructUnits);

        // 包装统计数据的中文名
        dataApproveSubBos.forEach(dataApproveSubBo -> dataApproveSubBo.setCnName(ApproveStatisticsBlock.ALL.get(dataApproveSubBo.getCode()).getCnName()));


        for (String categoryCode : ApproveStatisticsBlock.APPROVE_CATEGORY.keySet()) {
            List<DataApproveSubBo> subCollect = dataApproveSubBos.stream().filter(bo -> bo.getCategoryCode().equals(categoryCode)).collect(Collectors.toList());

            // 统计该分类下的总树木之和
            int totalSum = subCollect.stream().mapToInt(DataApproveSubBo::getTotal).sum();

            // 统计该分类下的未审核树木之和
            int unauditedSum = subCollect.stream().mapToInt(DataApproveSubBo::getUnaudited).sum();

            returnList.add(
                    new DataApproveStatsBo(categoryCode, ApproveStatisticsBlock.APPROVE_CATEGORY.get(categoryCode),
                            totalSum, unauditedSum, subCollect)
            );
        }

        return returnList;
    }


    public List<StatsProcessResultBo> statsYesterdayProcess(String projectId) {
        LocalDate now = LocalDate.now();
        String yesterday = now.minusDays(1).toString();
        StatsResultBo pipeResultBo = appStatisticsDao.statsPipeLengthByDate(projectId, yesterday, yesterday);
        StatsResultBo backFillResultBo = this.appStatisticsDao.statsBackFillLength(projectId, yesterday, yesterday);

        // 焊接(km/口)
        List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listWeldInfoByDate(projectId, yesterday, yesterday);
        Integer weldCount = this.appStatisticsDao.countWeldInfoByDate(projectId, yesterday, null);
        StatsResultBo weldResultBo = overallStatisticsService.statsPipeLengthByType(weldInfoBos, Lists.newArrayList(projectId), StatsProcessEnum.WELD.getType());

        // 补口(km/口)
        List<WeldInfoBo> patchWeldInBos = this.appStatisticsDao.listPatchRelationWeldInfoByDate(projectId, yesterday, yesterday);
        Integer patchRelationWeldCount = this.appStatisticsDao.countPatchRelationWeldInfoByDate(projectId, yesterday, null);
        StatsResultBo patchRelationWeldResultBo = overallStatisticsService.statsPipeLengthByType(patchWeldInBos, Lists.newArrayList(projectId), StatsProcessEnum.PATCH.getType());


        return Lists.newArrayList(
                new StatsProcessResultBo(pipeResultBo.getStatsType(), null, pipeResultBo.getStatsResult()==null?0:pipeResultBo.getStatsResult()),
                new StatsProcessResultBo(weldResultBo.getStatsType(), weldCount, weldResultBo.getStatsResult()==null?0:weldResultBo.getStatsResult()),
                new StatsProcessResultBo(patchRelationWeldResultBo.getStatsType(), patchRelationWeldCount, patchRelationWeldResultBo.getStatsResult()==null?0:patchRelationWeldResultBo.getStatsResult()),
                new StatsProcessResultBo(backFillResultBo.getStatsType(), null, backFillResultBo.getStatsResult()==null?0:backFillResultBo.getStatsResult())
        );
    }


    public List<StatsProcessResultBo> statsYesterdayProcessDetail(String projectId, String statsType) {
        LocalDate now = LocalDate.now();
        String yesterday = now.minusDays(1).toString();

        // 获取该项目下的所有施工单位(idToUnitNameMap)
        List<Map<String, Object>> list = this.daqPrivilegeService.getConstructionUnitByProjectOid(projectId);
        Map<String, String> idToUnitNameMap = list.stream().collect(Collectors.toMap(construct -> String.valueOf(construct.get("key")), construct -> String.valueOf(construct.get("value")), (a, b) -> b));

        if (StatsProcessForAppEnum.PIPE.getType().equals(statsType)) {
            List<StatsProcessResultBo> pipeResultBo = appStatisticsDao.statsPipeLengthGroupByConstruct(projectId, yesterday, yesterday);
            pipeResultBo.forEach(bo -> bo.setConstructName(idToUnitNameMap.get(bo.getStatsType())));
            return pipeResultBo;
        }

        if (StatsProcessForAppEnum.WELD.getType().equals(statsType)) {
            List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listWeldInfoByDate(projectId, yesterday, yesterday);
            Map<String, Map<String, Double>> pipeLengthMap = this.overallStatisticsService.getPipeLengthMap(weldInfoBos);
            //  根据施工单位分组计算
            Map<String, List<WeldInfoBo>> dateToWeldInfoList = weldInfoBos.stream().collect(Collectors.groupingBy(WeldInfoBo::getConstructUnit, Collectors.toList()));
            List<StatsProcessResultBo> resultBoList = Lists.newArrayList();
            for (String constructId : dateToWeldInfoList.keySet()) {
                Double length = this.overallStatisticsService.statsPipeLength(pipeLengthMap, dateToWeldInfoList.get(constructId));
                Integer count = this.appStatisticsDao.countWeldInfoByDate(projectId, yesterday, constructId);
                resultBoList.add(new StatsProcessResultBo(constructId, idToUnitNameMap.get(constructId), count, length));
            }

            return resultBoList;
        }


        if (StatsProcessForAppEnum.PATCH.getType().equals(statsType)) {
            List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listPatchRelationWeldInfoByDate(projectId, yesterday, yesterday);
            Map<String, Map<String, Double>> pipeLengthMap = this.overallStatisticsService.getPipeLengthMap(weldInfoBos);

            Map<String, List<WeldInfoBo>> dateToWeldInfoList = weldInfoBos.stream().collect(Collectors.groupingBy(WeldInfoBo::getConstructUnit, Collectors.toList()));
            List<StatsProcessResultBo> resultBoList = Lists.newArrayList();
            for (String constructId : dateToWeldInfoList.keySet()) {
                Double length = this.overallStatisticsService.statsPipeLength(pipeLengthMap, dateToWeldInfoList.get(constructId));
                Integer count = this.appStatisticsDao.countPatchRelationWeldInfoByDate(projectId, yesterday, constructId);
                resultBoList.add(new StatsProcessResultBo(constructId, idToUnitNameMap.get(constructId), count, length));
            }

            return resultBoList;
        }

        if (StatsProcessForAppEnum.LAY_PIPE_TRENCH_BACKFILL.getType().equals(statsType)) {
            List<StatsProcessResultBo> resultBos = appStatisticsDao.statsBackFillLengthGroupByConstruct(projectId, yesterday, yesterday);
            resultBos.forEach(bo -> bo.setConstructName(idToUnitNameMap.get(bo.getStatsType())));
            return resultBos;
        }


        return null;
    }



    public Table<String, String, Object> statsLatestWeekCumulativeProcess(String projectId) {
        LocalDate now = LocalDate.now();
        String startDate = StatsUtils.getStartDayOfWeek(now.toString(), "yyyy-MM-dd");
        String endDate = StatsUtils.getEndDayOfWeek(now.toString(), "yyyy-MM-dd");

        List<String> dayList = StatsUtils.genContinuityDayStr(startDate, endDate, "yyyy-MM-dd");

        // TODO: 管材
        List<DateStatsResultBo> pipeStatsResult = this.appStatisticsDao.statsPipeLengthGroupDate(projectId, startDate, endDate);

        // TODO: 焊接
        List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listWeldInfoByDate(projectId, startDate, endDate);

        // TODO: 补口
        List<WeldInfoBo> patchRelationWeldInfoBos = this.appStatisticsDao.listPatchRelationWeldInfoByDate(projectId, startDate, endDate);

        List<WeldInfoBo> statsWeldList = Lists.newArrayList(weldInfoBos);
        statsWeldList.addAll(patchRelationWeldInfoBos);
        Map<String, Map<String, Double>> pipeLengthMap = this.overallStatisticsService.getPipeLengthMap(statsWeldList);
        List<DateStatsResultBo> weldStatsResult = this.overallStatisticsService.getDateStatsResult(StatsProcessEnum.WELD.getType(), weldInfoBos, pipeLengthMap);
        List<DateStatsResultBo> patchStatsResult = this.overallStatisticsService.getDateStatsResult(StatsProcessEnum.PATCH.getType(), patchRelationWeldInfoBos, pipeLengthMap);

        //TODO: 回填
        List<DateStatsResultBo> backFillStatsResult = this.appStatisticsDao.statsBackFillLengthGroupDate(projectId, startDate, endDate);

        List<DateStatsResultBo> resultBos = Lists.newArrayList();
        resultBos.addAll(pipeStatsResult);
        resultBos.addAll(weldStatsResult);
        resultBos.addAll(patchStatsResult);
        resultBos.addAll(backFillStatsResult);

        // 初始化table生成连续的月份
        Table<String, String, Object> table = TreeBasedTable.create();
        this.initTable(dayList, table);

        // Table赋值
        resultBos.forEach(bo -> table.put(bo.getStatsType(), bo.getStatsDate(), bo.getStatsResult()==null?0:bo.getStatsResult()));

        // 计算累积结果: 每个统计类型下的年月统计值=之前月份累计只和
        Table<String, String, Object> resultTable = HashBasedTable.create();
        for (String statsType : table.rowKeySet()) {
            for (String date : dayList) {
                resultTable.put(statsType, date, overallStatisticsService.getCumulativeCount(table, dayList, statsType, date));
            }
        }

        return resultTable;
    }


    public Table<String, String, Integer> statsDateEntryAndAuditing(String projectId) {
        LocalDate now = LocalDate.now();
        String startDate = StatsUtils.getStartDayOfWeek(now.toString(), "yyyy-MM-dd");
        String endDate = StatsUtils.getEndDayOfWeek(now.toString(), "yyyy-MM-dd");
        List<DateApproveStatsForApp> statsResultList = this.appStatisticsDao.statsDataEntryApproveGroupByDay(projectId, startDate, endDate);

        List<String> dayList = StatsUtils.genContinuityDayStr(startDate, endDate, "yyyy-MM-dd");
        Map<String, DateApproveStatsForApp> dateToCountMap = statsResultList.stream().collect(Collectors.toMap(DateApproveStatsForApp::getStatsDate, app -> app, (a, b) -> b));
        Table<String, String, Integer> table = TreeBasedTable.create();
        for (String date : dayList) {
            Integer totalCount = 0;
            Integer auditedCount = 0;
            if (dateToCountMap.containsKey(date)) {
                totalCount = dateToCountMap.get(date).getTotalCount();
                auditedCount = dateToCountMap.get(date).getAuditedCount();
            }
            table.put(date, "totalCount", totalCount);
            table.put(date, "auditedCount", auditedCount);
        }

        return table;
    }


    public static void main(String[] args) {

    }

    private void initTable(List<String> dateList, Table<String, String, Object> table) {
        for (StatsProcessForAppEnum processEnum : StatsProcessForAppEnum.values()) {
            for (String date : dateList) {
                table.put(processEnum.getType(), date, 0);
            }
        }
    }

}
