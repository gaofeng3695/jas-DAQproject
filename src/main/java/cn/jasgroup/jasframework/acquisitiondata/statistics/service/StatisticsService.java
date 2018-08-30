package cn.jasgroup.jasframework.acquisitiondata.statistics.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.EntryStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.dao.StatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataApproveStatisticsBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataApproveSubBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataEntryStatisticsBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatisticsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.service.UnitService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
                long toSubmitCount = resultList.stream().filter(resultBo -> statisType.equals(resultBo.getStatisType()) && 1 == resultBo.getStatisResult()).count();
                long repulseCount = resultList.stream().filter(resultBo -> statisType.equals(resultBo.getStatisType()) && 2 == resultBo.getStatisResult()).count();
                returnList.add(new DataEntryStatisticsBo(statisType, enteredCount, toSubmitCount, repulseCount));
            }
        }

        return returnList;
    }


    /**
     * 数据审核统计, 默认统计
     *   - 包含6个分类及各分类下的数据统计 {@link ApproveStatisticsBlock#APPROVE_CATEGORY}
     *   - 各分类下的数据统计: {@link ApproveStatisticsBlock#ALL}
     */
    public List<DataApproveStatisticsBo> dataAuditing(String constructUnit) {

        List<DataApproveStatisticsBo> returnList = new ArrayList<>();

        PriUnit priUnit = (PriUnit) unitService.get(PriUnit.class, constructUnit);
        if (null == priUnit) {
            throw new BusinessException("constructUnit Not Found", "404");
        }
        String hierarchy = priUnit.getHierarchy();

        if (!hierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())) {
            logger.error("施工单位层级错误, hierarchy={}", hierarchy);
            throw new BusinessException("施工单位层级错误", "403");
        }

        List<String> constructUnits = this.statisticsDao.queryConstructUnitByHierarchy(hierarchy);
        if (CollectionUtils.isEmpty(constructUnits)) {
            throw new BusinessException("ConstructUnits Not Found", "404");
        }

        List<DataApproveSubBo> dataApproveSubBos = this.statisticsDao.listDataAuditing(constructUnits);

        // 包装统计数据的中文名
        for (DataApproveSubBo dataApproveSubBo : dataApproveSubBos) {
            dataApproveSubBo.setCnName(ApproveStatisticsBlock.ALL.get(dataApproveSubBo.getCode()).getCnName());
        }

        Map<String, String> approveCategory = ApproveStatisticsBlock.APPROVE_CATEGORY;

        for (String categoryCode : approveCategory.keySet()) {
            List<DataApproveSubBo> subCollect = dataApproveSubBos.stream().filter(bo -> bo.getCategoryCode().equals(categoryCode)).collect(Collectors.toList());

            // 统计该分类下的总树木之和
            int totalSum = subCollect.stream().mapToInt(DataApproveSubBo::getTotal).sum();

            // 统计该分类下的未审核树木之和
            int unauditedSum = subCollect.stream().mapToInt(DataApproveSubBo::getUnaudited).sum();

            returnList.add(new DataApproveStatisticsBo(categoryCode, approveCategory.get(categoryCode), totalSum, unauditedSum, subCollect));
        }

        return returnList;
    }
}
