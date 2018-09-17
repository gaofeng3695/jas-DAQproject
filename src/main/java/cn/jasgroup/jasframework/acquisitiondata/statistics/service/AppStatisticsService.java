package cn.jasgroup.jasframework.acquisitiondata.statistics.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatusEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.EntryStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.dao.StatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataApproveStatsBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataApproveSubBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataEntryStatsBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
    private StatisticsDao statisticsDao;


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

        List<StatsResultBo> resultList = statisticsDao.listDataEntry(statsTypes, projectOid);

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


    public static void main(String[] args) {
        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 4, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("a", 1, "d", 4, "c", 3);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        System.out.println(diff.entriesInCommon());
        System.out.println(diff.entriesOnlyOnLeft());
        System.out.println(diff.entriesOnlyOnRight());
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

            constructUnits = this.statisticsDao.queryConstructUnitByHierarchy(constructUnitHierarchy);
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

        List<String> supervisionUnits = this.statisticsDao.queryConstructUnitByHierarchy(currentUnitHierarchy);

        if (CollectionUtils.isEmpty(supervisionUnits)) {
            throw new BusinessException("currentUserUnits Not Found", "404");
        }

        List<DataApproveSubBo> dataApproveSubBos = this.statisticsDao.listDataAuditing(projectOid, supervisionUnits, constructUnits);

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
}
