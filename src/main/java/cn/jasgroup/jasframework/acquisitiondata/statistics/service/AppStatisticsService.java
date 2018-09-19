package cn.jasgroup.jasframework.acquisitiondata.statistics.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.*;
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

import java.text.NumberFormat;
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
        String yesterday = LocalDate.now().minusDays(1).toString();
        Map<String, String> unitMap = this.getUnitMap(projectId);

        List<StatsProcessResultBo> returnList = Lists.newArrayList();

        if (StatsProcessForAppEnum.PIPE.getType().equals(statsType)) {
            returnList = appStatisticsDao.statsPipeLengthGroupByConstruct(projectId, yesterday, yesterday);
        }

        if (StatsProcessForAppEnum.LAY_PIPE_TRENCH_BACKFILL.getType().equals(statsType)) {
            returnList = appStatisticsDao.statsBackFillLengthGroupByConstruct(projectId, yesterday, yesterday);
        }

        if (StatsProcessForAppEnum.WELD.getType().equals(statsType)) {
            List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listWeldInfoByDate(projectId, yesterday, yesterday);
            Map<String, Map<String, Double>> pipeLengthMap = this.overallStatisticsService.getPipeLengthMap(weldInfoBos);
            //  根据施工单位分组计算
            Map<String, List<WeldInfoBo>> dateToWeldInfoList = weldInfoBos.stream().collect(Collectors.groupingBy(WeldInfoBo::getConstructUnit, Collectors.toList()));
            for (String constructId : dateToWeldInfoList.keySet()) {
                List<WeldInfoBo> weldInfoList = dateToWeldInfoList.get(constructId);
                Double length = this.overallStatisticsService.statsPipeLength(pipeLengthMap, weldInfoList);
                Integer count = this.appStatisticsDao.countWeldInfoByDate(projectId, yesterday, constructId);
                returnList.add(new StatsProcessResultBo(constructId, count, length));
            }
        }

        if (StatsProcessForAppEnum.PATCH.getType().equals(statsType)) {
            List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listPatchRelationWeldInfoByDate(projectId, yesterday, yesterday);
            Map<String, Map<String, Double>> pipeLengthMap = this.overallStatisticsService.getPipeLengthMap(weldInfoBos);
            //  根据施工单位分组计算
            Map<String, List<WeldInfoBo>> dateToWeldInfoList = weldInfoBos.stream().collect(Collectors.groupingBy(WeldInfoBo::getConstructUnit, Collectors.toList()));
            for (String constructId : dateToWeldInfoList.keySet()) {
                List<WeldInfoBo> weldList = dateToWeldInfoList.get(constructId);
                Double length = this.overallStatisticsService.statsPipeLength(pipeLengthMap, weldList);
                Integer count = this.appStatisticsDao.countPatchRelationWeldInfoByDate(projectId, yesterday, constructId);
                returnList.add(new StatsProcessResultBo(constructId, count, length));
            }
        }

        // 包装该项目下所有施工单位的统计(统计数据中没有的)
        Set<String> statsUnitIds = returnList.stream().map(StatsProcessResultBo::getStatsType).collect(Collectors.toSet());
        for (String unitId : unitMap.keySet()) {
            if (!statsUnitIds.contains(unitId)) {
                returnList.add(new StatsProcessResultBo(unitId, 0, 0));
            }
        }

        // 包装单位中文名称
        returnList.forEach(bo -> bo.setConstructName(unitMap.get(bo.getStatsType())));
        return returnList;
    }



    public Table<String, String, Object> statsLatestWeekCumulativeProcess(String projectId) {
        LocalDate now = LocalDate.now();
        String startDate = StatsUtils.getStartDayOfWeek(now.toString(), "yyyy-MM-dd");
        String endDate = StatsUtils.getEndDayOfWeek(now.toString(), "yyyy-MM-dd");

        List<String> dayList = StatsUtils.genContinuityDayStr(startDate, endDate, "yyyy-MM-dd");

        // 管材
        List<DateStatsResultBo> pipeStatsResult = this.appStatisticsDao.statsPipeLengthGroupDate(projectId, startDate, endDate);

        // 焊接
        List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listWeldInfoByDate(projectId, startDate, endDate);

        // 补口
        List<WeldInfoBo> patchRelationWeldInfoBos = this.appStatisticsDao.listPatchRelationWeldInfoByDate(projectId, startDate, endDate);

        List<WeldInfoBo> statsWeldList = Lists.newArrayList(weldInfoBos);
        statsWeldList.addAll(patchRelationWeldInfoBos);
        Map<String, Map<String, Double>> pipeLengthMap = this.overallStatisticsService.getPipeLengthMap(statsWeldList);

        List<DateStatsResultBo> weldStatsResult = this.overallStatisticsService.getDateStatsResult(StatsProcessEnum.WELD.getType(), weldInfoBos, pipeLengthMap);
        List<DateStatsResultBo> patchStatsResult = this.overallStatisticsService.getDateStatsResult(StatsProcessEnum.PATCH.getType(), patchRelationWeldInfoBos, pipeLengthMap);

        // 回填
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

        // 计算累积结果: 每个统计类型下的日期统计值=之前月份累计之和
        Table<String, String, Object> resultTable = HashBasedTable.create();
        for (String statsType : table.rowKeySet()) {
            for (String date : dayList) {
                resultTable.put(statsType, date, overallStatisticsService.getCumulativeCount(table, dayList, statsType, date));
            }
        }

        return resultTable;
    }


    public Table<String, String, Object> statsLatestWeekCumulativeProcessDetail(String projectId) {
        LocalDate now = LocalDate.now();
        String startDate = StatsUtils.getStartDayOfWeek(now.toString(), "yyyy-MM-dd");
        String endDate = StatsUtils.getEndDayOfWeek(now.toString(), "yyyy-MM-dd");
        List<String> dayList = StatsUtils.genContinuityDayStr(startDate, endDate, "yyyy-MM-dd");

        Map<String, String> unitMap = this.getUnitMap(projectId);

        // TODO: 管材

        // TODO: 管沟回填

        // TODO: 焊接

        // TODO: 补口

        // TODO: 包装施工单位中文名 & 填充项目下的单位统计
        return null;
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



    private void initTable(List<String> dateList, Table<String, String, Object> table) {
        for (StatsProcessForAppEnum processEnum : StatsProcessForAppEnum.values()) {
            for (String date : dateList) {
                table.put(processEnum.getType(), date, 0);
            }
        }
    }



    public Map<String, Object> statsWeldCheck(String projectId) {

        // 审核通过的焊口数量
        Integer passedWeldCount = this.appStatisticsDao.countWeldInfoByApproveStatus(projectId, ApproveStatusEnum.PASSED.getCode());

        // 审核通过的射线检测数量
        Integer passedRayCount = this.appStatisticsDao.countRayCheckByResult(projectId, null);

        // 审核通过且检验合格的射线检测数量
        Integer passedAndOkCount = this.appStatisticsDao.countRayCheckByResult(projectId, 1);

        Map<String, Object> returnMap = Maps.newHashMap();
        returnMap.put("passedWeldCount", passedWeldCount);
        returnMap.put("passedRayCount", passedRayCount);
        returnMap.put("passedAndOkCount", passedAndOkCount);
        return returnMap;
    }


    public List<WeldCheckInfoBo> statsWeldCheckDetail(String projectId) {
        Map<String, String> unitMap = this.getUnitMap(projectId);

        List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listWeldInfo(projectId);
        Map<String, List<WeldInfoBo>> unitToWeldList = weldInfoBos.stream().collect(Collectors.groupingBy(WeldInfoBo::getConstructUnit, Collectors.toList()));

        List<String> weldIdList = weldInfoBos.stream().map(WeldInfoBo::getOid).collect(Collectors.toList());

        List<DetectionRayBo> detectionRayBos = this.appStatisticsDao.listDetectionRayWeldIn(projectId, weldIdList);

        Map<String, DetectionRayBo> weldToDetectionRayMap = detectionRayBos.stream().collect(Collectors.toMap(DetectionRayBo::getWeldOid, bo -> bo, (a, b) -> b));

        List<WeldCheckInfoBo> resultList = Lists.newArrayList();
        for (String unitId : unitToWeldList.keySet()) {
            Set<String> collect = unitToWeldList.get(unitId).stream().map(WeldInfoBo::getOid).collect(Collectors.toSet());
            WeldCheckInfoBo weldCheckInfoBo = new WeldCheckInfoBo();
            weldCheckInfoBo.setUnitId(unitId);
            weldCheckInfoBo.setUnitName(unitMap.getOrDefault(unitId, "-"));
            this.wrapperStatsCount(weldCheckInfoBo, collect, weldToDetectionRayMap);
            resultList.add(weldCheckInfoBo);
        }

        Sets.SetView<String> diff = Sets.difference(unitMap.keySet(), unitToWeldList.keySet());
        for (String unitId : diff) {
            resultList.add(new WeldCheckInfoBo(unitId, unitMap.getOrDefault(unitId, "-"), 0, 0, 0, 0, "-"));
        }

        return resultList;
    }

    private void wrapperStatsCount(WeldCheckInfoBo weldCheckInfoBo, Set<String> collect, Map<String, DetectionRayBo> weldToDetectionRay) {

        int weldCount = collect.size();
        int checkedCount = 0;
        int qualifiedCount = 0;
        int onceQualifiedCount = 0;

        for (String weldId : collect) {
            DetectionRayBo rayBo = weldToDetectionRay.get(weldId);
            if (weldToDetectionRay.containsKey(weldId)) {
                checkedCount ++;
                if (null!=rayBo && Objects.equals(1, rayBo.getEvaluationResult())) {
                    qualifiedCount ++;
                    if ("detection_type_code_001".equals(rayBo.getDetectionType())) {
                        onceQualifiedCount ++;
                    }
                }
            }
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String onceQualifiedRate = 0 == checkedCount ? "∞%":numberFormat.format((float)onceQualifiedCount / (float)checkedCount * 100).concat("%");

        weldCheckInfoBo.setWeldCount(weldCount);
        weldCheckInfoBo.setCheckedCount(checkedCount);
        weldCheckInfoBo.setUncheckedCount(weldCount - checkedCount);
        weldCheckInfoBo.setQualifiedCount(qualifiedCount);
        weldCheckInfoBo.setOnceQualifiedRate(onceQualifiedRate);
    }

    public static void main(String[] args) {

        Set<String> s1 = Sets.newHashSet("f4b587f5-4f84-4e51-9ae7-529a7f16b2f6" , "6f45fb5e-f2c7-4e40-bbe1-a2e643cc6a48");
        Set<String> s2 = Sets.newHashSet("17005abc-6a3c-456e-afa2-97ac9dc8dc13",
                 "df5332c7-961f-4df0-9cf6-a41b588dbe1b", "b700743e-9f06-4e72-a207-c05f279f6994",
                "6f45fb5e-f2c7-4e40-bbe1-a2e643cc6a48", "689b0c7c-69f2-4ec4-b0ef-6fa5f490d84f");

        Sets.SetView<String> diff = Sets.difference(s2, s1);
        for (String unitId : diff) {
            System.out.println(unitId);
        }
    }


    /**
     * 获取该项目下的所有施工单位
     * @param projectId 项目ID
     * @return dToUnitNameMap: key-施工单位ID, value-施工单位名称
     */
    private Map<String, String> getUnitMap(String projectId) {
        List<Map<String, Object>> list = this.daqPrivilegeService.getConstructionUnitByProjectOid(projectId);
        return list.stream().collect(Collectors.toMap(construct -> String.valueOf(construct.get("key")), construct -> String.valueOf(construct.get("value")), (a, b) -> b));
    }

}
