package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.*;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.dao.AppStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.*;
import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import com.google.common.collect.*;
import com.google.common.primitives.Doubles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsProcessForAppEnum.*;

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

    private static final String YYYY_MM_DD = "yyyy-MM-dd";

    /** 射线检测类型: 一次性检测 */
    private static final String ONCE_DETECTION_QUALIFIED = "detection_type_code_001";



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
     *  - 根据参数: 单位/单位类型(施工单位, 检测单位)过滤, 范围: 该部门及部门以下的
     *  - 根据监理单位当前用户过滤 (部门及部门以下的)
     */
    public List<DataApproveStatsBo> dataAuditing(String projectOid, String unitId) {

        // 根据参数施工单位过滤 (范围: 该部门及部门以下的)
        PriUnit priUnit = (PriUnit) unitService.get(PriUnit.class, unitId);
        if (null == priUnit) {
            throw new BusinessException("unit Not Found", "404");
        }
        String unitHierarchy = priUnit.getHierarchy();
        String unitType = "";
        if (unitHierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())) {
            unitType = UnitHierarchyEnum.construct_unit.getHierarchy();
        } else if (unitHierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())) {
            unitType = UnitHierarchyEnum.detection_unit.getHierarchy();
        } else {
            logger.error("施工/检测单位层级错误, hierarchy={}", unitHierarchy);
            throw new BusinessException("施工/检测单位层级错误", "403");
        }

        List<String> unitIds = this.appStatisticsDao.queryConstructUnitByHierarchy(unitHierarchy);
        if (CollectionUtils.isEmpty(unitIds)) {
            throw new BusinessException("ConstructUnits Not Found", "404");
        }


        // 根据当前用户的监理单位过滤 (范围: 部门及部门以下的)
        PriUnit currentUserUnit = (PriUnit) unitService.get(PriUnit.class, ThreadLocalHolder.getCurrentUser().getUnitId());
        String currentUnitHierarchy = currentUserUnit.getHierarchy();
        if (!currentUnitHierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())) {
//            if (!UnitHierarchyEnum.supervision_unit.getHierarchy().contains(currentUnitHierarchy)) {
                logger.error("当前用户{}, unitId:{}, 层级{}错误", ThreadLocalHolder.getCurrentUser().getUid(), currentUserUnit.getOid(), currentUnitHierarchy);
//                throw new BusinessException("当前用户unit权限错误", "403");
//            }
        }

        List<String> supervisionUnits = this.appStatisticsDao.queryConstructUnitByHierarchy(currentUnitHierarchy);

        if (CollectionUtils.isEmpty(supervisionUnits)) {
            throw new BusinessException("currentUserUnits Not Found", "404");
        }

        List<DataApproveSubBo> dataApproveSubBos = this.appStatisticsDao.listDataAuditing(projectOid, supervisionUnits, unitIds, unitType);

        if (UnitHierarchyEnum.construct_unit.getHierarchy().equals(unitType)) {
            this.wrapperStatsInfo(dataApproveSubBos, ApproveStatisticsBlock.APPROVE_CATEGORY_DETECTION);
        } else if (UnitHierarchyEnum.detection_unit.getHierarchy().equals(unitType)) {
            this.wrapperStatsInfo(dataApproveSubBos, ApproveStatisticsBlock.APPROVE_CATEGORY_NON_DETECTION);
        }

        // 包装统计结果的中文名
        dataApproveSubBos.forEach(bo -> bo.setCnName(ApproveStatisticsBlock.ALL.get(bo.getCode()).getCnName()));

        List<DataApproveStatsBo> returnList = Lists.newArrayList();

        for (String categoryCode : ApproveStatisticsBlock.APPROVE_CATEGORY.keySet()) {
            List<DataApproveSubBo> subCollect = dataApproveSubBos.stream().filter(bo -> bo.getCategoryCode().equals(categoryCode)).collect(Collectors.toList());

            // 统计该分类下的总树木之和
            int totalSum = subCollect.stream().mapToInt(DataApproveSubBo::getTotal).sum();

            // 统计该分类下的未审核树木之和
            int unauditedSum = subCollect.stream().mapToInt(DataApproveSubBo::getUnaudited).sum();

            returnList.add(
                    new DataApproveStatsBo(categoryCode, ApproveStatisticsBlock.APPROVE_CATEGORY.get(categoryCode), totalSum, unauditedSum, subCollect)
            );
        }

        return returnList;
    }


    private void wrapperStatsInfo(List<DataApproveSubBo> dataApproveSubBos, Map<String, Map<String, ApproveStatisticsBlock>> statsBlock) {
        for (String categoryCode : statsBlock.keySet()) {
            Map<String, ApproveStatisticsBlock> subCodes = statsBlock.get(categoryCode);
            subCodes.keySet().stream().map(subCode -> new DataApproveSubBo(subCode, categoryCode, 0, 0)).forEach(dataApproveSubBos::add);
        }
    }


    /**
     * 统计昨日工序完成情况
     * @param projectId 项目ID
     * @return List
     */
    public List<StatsProcessResultBo> statsYesterdayProcess(String projectId) {
        String yesterday = LocalDate.now().minusDays(1).toString();

        // 统计长度: 管材, 管沟回填, 焊口, 补口
        StatsResultBo pipeResultBo = appStatisticsDao.statsPipeLengthByDate(projectId, yesterday);
        StatsResultBo backFillResultBo = this.appStatisticsDao.statsBackFillLengthByDate(projectId, yesterday);
        StatsResultBo weldResultBo = this.appStatisticsDao.statsWeldLengthByDate(projectId, yesterday);
        StatsResultBo patchResultBo = this.appStatisticsDao.statsPatchLengthByDate(projectId, yesterday);

        // 统计数量: 焊口, 补口
        Integer weldCount = this.appStatisticsDao.countWeldByDate(projectId, yesterday);
        Integer patchRelationWeldCount = this.appStatisticsDao.countPatchByDate(projectId, yesterday);

        return Lists.newArrayList(
                new StatsProcessResultBo(pipeResultBo.getStatsType(), null, pipeResultBo.getStatsResult()),
                new StatsProcessResultBo(weldResultBo.getStatsType(), weldCount, weldResultBo.getStatsResult()),
                new StatsProcessResultBo(patchResultBo.getStatsType(), patchRelationWeldCount, patchResultBo.getStatsResult()),
                new StatsProcessResultBo(backFillResultBo.getStatsType(), null, backFillResultBo.getStatsResult())
        );
    }


    /**
     * 统计昨日工序完成情况详情(根据施工单位分组)
     * @param projectId 项目ID
     * @param statsType 统计类型
     * @return List
     */
    public List<StatsProcessResultBo> statsYesterdayProcessDetail(String projectId, String statsType) {
        String yesterday = LocalDate.now().minusDays(1).toString();
        Map<String, String> unitMap = this.getUnitMap(projectId);

        List<StatsProcessResultBo> resultList = Lists.newArrayList();

        if (PIPE.getType().equals(statsType)) {
            resultList = appStatisticsDao.statsPipeLengthGroupByConstruct(projectId, yesterday);
        }

        if (LAY_PIPE_TRENCH_BACKFILL.getType().equals(statsType)) {
            resultList = appStatisticsDao.sumBackFillLengthGroupByConstruct(projectId, yesterday);
        }

        if (WELD.getType().equals(statsType)) {
            resultList = this.appStatisticsDao.statsWeldLengthGroupByConstruct(projectId, yesterday);
            List<StatsProcessResultBo> countList = appStatisticsDao.countWeldGroupByUnitId(projectId, yesterday);
            Map<String, Integer> unitIdToCount = countList.stream().collect(Collectors.toMap(StatsProcessResultBo::getConstructId, StatsProcessResultBo::getStatsCount, (a, b) -> b));
            resultList.forEach(bo -> bo.setStatsCount(unitIdToCount.get(bo.getStatsType())));
        }

        if (PATCH.getType().equals(statsType)) {
            resultList = this.appStatisticsDao.statsPatchLengthGroupByConstruct(projectId, yesterday);
            List<StatsProcessResultBo> countList = appStatisticsDao.countPatchGroupByUnitId(projectId, yesterday);
            Map<String, Integer> unitIdToCount = countList.stream().collect(Collectors.toMap(StatsProcessResultBo::getConstructId, StatsProcessResultBo::getStatsCount, (a, b) -> b));
            resultList.forEach(bo -> bo.setStatsCount(unitIdToCount.get(bo.getStatsType())));
        }

        // 包装该项目下所有施工单位的统计(统计数据中没有的)
        Set<String> statsUnitIds = resultList.stream().map(StatsProcessResultBo::getStatsType).collect(Collectors.toSet());
        for (String unitId : unitMap.keySet()) {
            if (!statsUnitIds.contains(unitId)) {
                resultList.add(new StatsProcessResultBo(unitId, 0, 0));
            }
        }

        // 包装单位中文名称 & 根据统计结果排序
        resultList.forEach(bo -> bo.setConstructName(unitMap.get(bo.getStatsType())));
        resultList.sort((o1, o2) -> Double.compare(Double.parseDouble(o2.getStatsResult().toString()), Double.parseDouble(o1.getStatsResult().toString())));
        return resultList;
    }


    public Table<String, String, Object> statsLatestWeekCumulativeProcess(String projectId) {
        String startDate = LocalDate.now().minusDays(6).toString();
        String endDate = LocalDate.now().toString();
        List<String> dayList = StatsUtils.genContinuityDayStr(startDate, endDate, YYYY_MM_DD);

        // 统计各工序之前的所有长度
        List<StatsResultBo> beforeStatsResults = appStatisticsDao.statsProcessLengthByDate(projectId, startDate);
        Map<String, Double> typeToLength = beforeStatsResults.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, bo -> Doubles.tryParse(StatsUtils.coalesce(bo.getStatsResult().toString())), (a, b) -> b));

        // 管材, 焊口, 补口, 管沟回填 根据日期分桶统计长度
        List<DateStatsResultBo> pipeStats = this.appStatisticsDao.statsPipeLengthGroupByDate(projectId, startDate, endDate);
        List<DateStatsResultBo> weldStats = this.appStatisticsDao.statsWeldLengthGroupByDate(projectId, startDate, endDate);
        List<DateStatsResultBo> patchStats = this.appStatisticsDao.statsPatchLengthGroupByDate(projectId, startDate, endDate);
        List<DateStatsResultBo> backFillStats = this.appStatisticsDao.statsBackFillLengthGroupByDate(projectId, startDate, endDate);

        List<DateStatsResultBo> resultBos = Lists.newArrayList();
        resultBos.addAll(pipeStats);
        resultBos.addAll(weldStats);
        resultBos.addAll(patchStats);
        resultBos.addAll(backFillStats);

        // 初始化table生成连续的月份
        Table<String, String, Object> table = TreeBasedTable.create();
        this.initTable(dayList, table);

        // Table赋值
        resultBos.forEach(bo -> table.put(bo.getStatsType(), bo.getStatsDate(), bo.getStatsResult()==null?0:bo.getStatsResult()));

        // 计算累积结果
        Table<String, String, Object> resultTable = TreeBasedTable.create();
        for (String statsType : table.rowKeySet()) {
            for (String date : dayList) {
                Object cumulativeLength = overallStatisticsService.getCumulativeCount(table, dayList, statsType, date);
                double length = Double.sum(typeToLength.get(statsType), Double.parseDouble(cumulativeLength.toString()));
                resultTable.put(statsType, date, length);
            }
        }

        return resultTable;
    }


    /**
     * 统计最近一周各工序累计完成情况
     *   - 根据施工单位分组
     *   - 根据日期过滤
     * @param projectId 项目ID
     * @return Table
     */
    public List statsLatestWeekCumulativeProcessDetail(String projectId) {
        String startDate = LocalDate.now().minusDays(6).toString();
        String endDate = LocalDate.now().toString();
        List<String> dayList = StatsUtils.genContinuityDayStr(startDate, endDate, YYYY_MM_DD);
        Map<String, String> unitMap = this.getUnitMap(projectId);
        if (unitMap.isEmpty()) {
            return null;
        }

        Set<String> unitIds = unitMap.keySet();

        // 根据日期, 单位分桶统计长度: 管材, 管沟回填, 焊口, 补口
        List<DateStatsResultBo> pipeStatsResult = this.appStatisticsDao.statsPipeLengthGroupByConstructAndDate(projectId, unitIds, startDate, endDate);
        List<DateStatsResultBo> backFillStatsResult = this.appStatisticsDao.statsBackFillLengthGroupByConstructAndDate(projectId, unitIds, startDate, endDate);
        List<DateStatsResultBo> weldStatsResult = this.appStatisticsDao.statsWeldLengthGroupByConstructAndDate(projectId, unitIds, startDate, endDate);
        List<DateStatsResultBo> patchStatsResult = this.appStatisticsDao.statsPatchLengthGroupByConstructAndDate(projectId, unitIds, startDate, endDate);

        // 统计各工序之前的所有长度
        List<StatsProcessResultBo> beforeList = appStatisticsDao.statsProcessLengthGroupByUnit(projectId, unitIds, startDate);
        Table<String, String, Object> beforeTable = HashBasedTable.create();
        beforeList.forEach(bo -> beforeTable.put(bo.getStatsType(), bo.getConstructId(), bo.getStatsResult()));

        // 包装施工单位中文名 & 填充统计结果(累积计算)
        List<Map<String, Object>> resultList = Lists.newArrayList();
        for (String unitId : unitMap.keySet()) {
            Table<String, String, Object> table = TreeBasedTable.create();
            this.initTable(dayList, table);

            pipeStatsResult.stream().filter(bo -> unitId.equals(bo.getStatsType())).forEach(dateStatsResultBo -> table.put(PIPE.getType(), dateStatsResultBo.getStatsDate(), dateStatsResultBo.getStatsResult()));
            backFillStatsResult.stream().filter(bo -> unitId.equals(bo.getStatsType())).forEach(dateStatsResultBo -> table.put(LAY_PIPE_TRENCH_BACKFILL.getType(), dateStatsResultBo.getStatsDate(), dateStatsResultBo.getStatsResult()));
            weldStatsResult.stream().filter(bo -> unitId.equals(bo.getStatsType())).forEach(bo -> table.put(WELD.getType(), bo.getStatsDate(), bo.getStatsResult()));
            patchStatsResult.stream().filter(bo -> unitId.equals(bo.getStatsType())).forEach(bo -> table.put(PATCH.getType(), bo.getStatsDate(), bo.getStatsResult()));

            // 计算累积结果: 每个统计类型下的日期统计值=之前月份累计之和
            Table<String, String, Object> resultTable = TreeBasedTable.create();
            for (String statsType : table.rowKeySet()) {
                for (String date : dayList) {
                    Double beforeResult = 0d;
                    if (beforeTable.contains(statsType, unitId)) {
                        beforeResult = Double.parseDouble(beforeTable.get(statsType, unitId).toString());
                    }
                    Object cumulativeLength = overallStatisticsService.getCumulativeCount(table, dayList, statsType, date);
                    double length = Double.sum(beforeResult, Double.parseDouble(cumulativeLength.toString()));
                    resultTable.put(statsType, date, length);
                }
            }

            Map<String, Object> resultMap = Maps.newHashMap();
            resultMap.put("constructId", unitId);
            resultMap.put("constructName", unitMap.get(unitId));
            resultMap.put("statsResult", resultTable.rowMap());
            resultList.add(resultMap);
        }
        return resultList;
    }


    public Table<String, String, Integer> statsDateEntryAndAuditing(String projectId) {
        String startDate = LocalDate.now().minusDays(6).toString();
        String endDate = LocalDate.now().toString();
        List<DateApproveStatsForApp> statsResultList = this.appStatisticsDao.statsDataEntryApproveGroupByDay(projectId, startDate, endDate);

        List<String> dayList = StatsUtils.genContinuityDayStr(startDate, endDate, YYYY_MM_DD);
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


    /**
     * 统计焊口检测
     * @param projectId 项目ID
     * @return Map
     */
    public WeldCheckInfoBo statsWeldCheck(String projectId) {

        WeldCheckInfoBo weldCheckInfo = this.appStatisticsDao.countWeldDetectionInfo(projectId);
        WeldCheckInfoBo rayDetectionInfo = this.appStatisticsDao.countRayDetection(projectId);

        WeldCheckInfoBo resultBo = new WeldCheckInfoBo();
        resultBo.setWeldCount(StatsUtils.coalesce(weldCheckInfo.getWeldCount(), 0));
        resultBo.setCheckedCount(StatsUtils.coalesce(weldCheckInfo.getCheckedCount(), 0));
        resultBo.setUncheckedCount(resultBo.getWeldCount() - resultBo.getCheckedCount());
        resultBo.setDetectionRayCount(StatsUtils.coalesce(rayDetectionInfo.getDetectionRayCount(), 0));
        resultBo.setQualifiedCount(StatsUtils.coalesce(rayDetectionInfo.getQualifiedCount(), 0));

        return resultBo;
    }


    /**
     * 统计焊口检测详情(按施工单位分组)
     * @param projectId 项目ID
     * @return List
     */
    public List<WeldCheckInfoBo> statsWeldCheckDetail(String projectId) {
        Map<String, String> unitMap = this.getUnitMap(projectId);
        List<WeldCheckInfoBo> resultBoList = Lists.newArrayList();
        List<WeldInfoBo> weldInfoBos = this.appStatisticsDao.listWeldInfo(projectId);
        Map<String, List<WeldInfoBo>> unitToWeldList = weldInfoBos.stream().collect(Collectors.groupingBy(WeldInfoBo::getConstructUnit, Collectors.toList()));
        if (CollectionUtils.isEmpty(weldInfoBos) || CollectionUtils.isEmpty(unitToWeldList)) {
            unitMap.keySet().stream().map(unitId -> new WeldCheckInfoBo(unitId, unitMap.getOrDefault(unitId, "-"), 0, 0, 0, 0, 0, "0%")).forEach(resultBoList::add);
            return resultBoList;
        }

        List<String> weldIdList = weldInfoBos.stream().map(WeldInfoBo::getOid).collect(Collectors.toList());
        List<DetectionRayBo> detectionRayBos = this.appStatisticsDao.listQualifiedDetectionRayWeldIn(projectId, weldIdList);
        Set<String> qualifiedWeldIds = detectionRayBos.stream().map(DetectionRayBo::getWeldOid).collect(Collectors.toSet());
        Set<String> onceQualifiedWeldIds = detectionRayBos.stream().filter(bo -> ONCE_DETECTION_QUALIFIED.equals(bo.getDetectionType())).map(DetectionRayBo::getWeldOid).collect(Collectors.toSet());



        // 分组统计各项: 焊接数量, 已检测, 未检测, 合格数, 一次合格率
        for (String unitId : unitToWeldList.keySet()) {
            List<WeldInfoBo> weldList = unitToWeldList.get(unitId);
            WeldCheckInfoBo resultBo = new WeldCheckInfoBo();

            int weldCount = weldList.size();
            int checkedCount = (int) weldList.stream().filter(bo -> 1==bo.getIsRay()).mapToInt(WeldInfoBo::getIsRay).count();
            int qualifiedCount = (int) weldList.stream().filter(bo -> qualifiedWeldIds.contains(bo.getOid())).count();
            int onceQualifiedCount = (int) weldList.stream().filter(bo -> onceQualifiedWeldIds.contains(bo.getOid())).count();

            String onceQualifiedRate = "";
            if (0 == checkedCount) {
                onceQualifiedRate = "0%";
            } else {
                NumberFormat numberFormat = NumberFormat.getInstance();
                numberFormat.setMaximumFractionDigits(2);
                onceQualifiedRate = numberFormat.format((float)onceQualifiedCount / (float)checkedCount * 100).concat("%");
            }

            resultBo.setUnitId(unitId);
            resultBo.setUnitName(unitMap.getOrDefault(unitId, "-"));
            resultBo.setWeldCount(weldCount);
            resultBo.setCheckedCount(checkedCount);
            resultBo.setUncheckedCount(weldCount - checkedCount);
            resultBo.setQualifiedCount(qualifiedCount);
            resultBo.setOnceQualifiedRate(onceQualifiedRate);
            resultBo.setOnceQualifiedCount(onceQualifiedCount);
            resultBoList.add(resultBo);
        }

        Sets.SetView<String> diff = Sets.difference(unitMap.keySet(), unitToWeldList.keySet());
        diff.stream().map(unitId -> new WeldCheckInfoBo(unitId, unitMap.getOrDefault(unitId, "-"), 0, 0, 0, 0, 0, "0%")).forEach(resultBoList::add);

        // 排序: 按照焊接数量desc
        resultBoList.sort((o1, o2) -> o2.getWeldCount() - o1.getCheckedCount());
        return resultBoList;
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
