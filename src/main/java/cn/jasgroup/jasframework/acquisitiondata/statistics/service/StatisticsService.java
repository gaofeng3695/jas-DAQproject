package cn.jasgroup.jasframework.acquisitiondata.statistics.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatusEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.EntryStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.dao.StatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataApproveStatisticsBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataApproveSubBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataEntryStatisticsBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatisticsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import com.google.common.collect.Lists;
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
public class StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsService.class);

    @Autowired
    private StatisticsDao statisticsDao;


    @Autowired
    private UnitService unitService;

    /**
     * 数据录入统计
     * @param statisTypes 统计类型来源(默认统计7个)
     * - {@link EntryStatisticsBlock#PIPE_CHECKED_BLOCK}
     * - {@link EntryStatisticsBlock#WELD_APPROVE_BLOCK}
     * @return list
     */
    public List<DataEntryStatisticsBo> dataEntry(List<String> statisTypes) {

        List<DataEntryStatisticsBo> returnList = Lists.newArrayList();
        Map<String, String> pipeCheckedBlock = EntryStatisticsBlock.getPipeCheckedBlock();
        Map<String, String> weldApproveBlock = EntryStatisticsBlock.getWeldApproveBlock();

        if (CollectionUtils.isEmpty(statisTypes)) {
            statisTypes = Lists.newArrayList();
            statisTypes.addAll(EntryStatisticsBlock.getPipeCheckedBlock().keySet());
            statisTypes.addAll(EntryStatisticsBlock.getWeldApproveBlock().keySet());
        } else {
            statisTypes.removeIf(s -> !pipeCheckedBlock.containsKey(s) && !weldApproveBlock.containsKey(s));
        }

        List<StatisticsResultBo> resultList = statisticsDao.listDataEntry(statisTypes);

        // pipeCheckedBlock: 没有审核操作的: 只统计录入数
        for (String statisType : pipeCheckedBlock.keySet()) {
            if (statisTypes.contains(statisType)) {
                Optional<StatisticsResultBo> optional = resultList.stream().filter(resultBo -> statisType.equals(resultBo.getStatisType())).findAny();
                optional.ifPresent(statisticsResultBo -> returnList.add(new DataEntryStatisticsBo(statisType, statisticsResultBo.getStatisResult())));
            }
        }

        // weldApproveBlock: 有审核操作的: 统计录入数, 待提交数, 打回数
        for (String statisType : weldApproveBlock.keySet()) {
            if (statisTypes.contains(statisType)) {
                long enteredCount = resultList.stream().filter(resultBo -> statisType.equals(resultBo.getStatisType())).count();
                long toSubmitCount = resultList.stream().filter(resultBo -> statisType.equals(resultBo.getStatisType()) && ApproveStatusEnum.UNREPORTED.getCode() == resultBo.getStatisResult().intValue()).count();
                long repulseCount = resultList.stream().filter(resultBo -> statisType.equals(resultBo.getStatisType()) && ApproveStatusEnum.REJECT.getCode() == resultBo.getStatisResult().intValue()).count();
                returnList.add(new DataEntryStatisticsBo(statisType, enteredCount, toSubmitCount, repulseCount));
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
    public List<DataApproveStatisticsBo> dataAuditing(String projectOid, String constructUnitId) {

        List<DataApproveStatisticsBo> returnList = new ArrayList<>();

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
                    new DataApproveStatisticsBo(categoryCode, ApproveStatisticsBlock.APPROVE_CATEGORY.get(categoryCode),
                            totalSum, unauditedSum, subCollect)
            );
        }

        return returnList;
    }
}
