package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm.MaterialBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm.ScopeManagementBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.dao.DataVisualizationDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo.DataEntryAndAuditBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo.MaterialStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo.StatsResultWithNameBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo.StatsPipeCuttingBo;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsProcessEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsUtils;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.dao.OverallStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.OverallStatisticsService;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.WeldInfoBo;
import cn.jasgroup.jasframework.domain.utils.DomainUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/10/22 10:06
 */
@Service
public class DataVisualizationService {

    @Autowired
    private DaqPrivilegeService daqPrivilegeService;

    @Autowired
    private DataVisualizationDao dataVisualizationDao;

    @Autowired
    private OverallStatisticsService overallStatisticsService;
    @Autowired
    private OverallStatisticsDao overallStatisticsDao;

    public List<Map<String, Object>> getProjectInfoByUserId() {
        // id, name, pipe_network_type_code
        List<Map<String, Object>> projectLists = this.daqPrivilegeService.getProject();

        List<String> projectIds = projectLists.stream().map(map -> String.valueOf(map.get("oid"))).collect(Collectors.toList());

        List<StatsResultBo> resultBos = this.dataVisualizationDao.sumPipelineLengthGroupByProjectId(projectIds);
        Map<String, Object> statsMap = resultBos.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, StatsResultBo::getStatsResult, (a, b) -> b));

        projectLists.forEach(projectInfo -> {
            projectInfo.put("pipelineLength", statsMap.get(String.valueOf(projectInfo.get("oid"))));
            String domainValue = DomainUtil.getValue("pipe_network_type_domain", String.valueOf(projectInfo.get("pipe_network_type_code")));
            projectInfo.put("pipeNetworkTypeName", domainValue);
        });

        return projectLists;
    }


    /**
     * 统计在某一个项目下的管线、中线桩、线路段、穿跨越、站场、阀室、伴行路、外供电线路、标段的数量。
     * @param projectId 项目ID
     */
    public List<StatsResultWithNameBo> statsScopeManagement(String projectId) {
        List<StatsResultWithNameBo> returnList = this.dataVisualizationDao.countScopeManagement(projectId);
        Map<String, ScopeManagementBlock> block = ScopeManagementBlock.getScopeManagementBlock();
        returnList.forEach(bo -> bo.setCnName(block.get(bo.getStatsType()).getCnName()));
        return returnList;
    }



    public List<MaterialStatsResultBo> statsQuantityOfMaterial(String projectId) {
        List<MaterialStatsResultBo> returnList = this.dataVisualizationDao.countMaterial(projectId);
        Map<String, MaterialBlock> block = MaterialBlock.getMaterialInfo();
        returnList.forEach(bo -> bo.setCnName(block.get(bo.getStatsType()).getCnName()));
        return returnList;
    }


    public List steelPipeUsage(List<String> projectIds) {
        return this.dataVisualizationDao.countAndSumPipeUsage(projectIds);
    }


    public StatsPipeCuttingBo statsPipeCutting(List<String> projectIds) {
        return this.dataVisualizationDao.statsPipeCutting(projectIds);
    }


    public Map<String, Integer> statsWeldOnceQualifiedRate(List<String> projectIds) {
        List<Map<String, Integer>> returnList = this.dataVisualizationDao.countWeldOnceQualified(projectIds);
        if (CollectionUtils.isEmpty(returnList)) {
            throw new BusinessException("recourse not found", "404");
        }
        return returnList.get(0);
    }


    public List<StatsResultBo> statsWeldRework(List<String> projectIds, Integer statsDays) {
        LocalDate nowDate = LocalDate.now();
        List<String> continuityDates = StatsUtils.genContinuityDayStr(nowDate.minusDays(statsDays).toString(), nowDate.toString(), "yyyy-MM-dd");
        List<StatsResultBo> resultBos = this.dataVisualizationDao.countWeldReworkGroupByWeldDate(projectIds);
        Map<String, StatsResultBo> dateToResult = resultBos.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, resultBo -> resultBo, (a, b) -> b));

        List<StatsResultBo> returnList = Lists.newArrayList();
        continuityDates.forEach(date -> {
            if (dateToResult.containsKey(date)) {
                returnList.add(dateToResult.get(date));
            }
            returnList.add(new StatsResultBo(date, 0));
        });

        return returnList;
    }


    public List<StatsResultBo> statsTypeOfPersonnel(List<String> projectIds) {
        return this.dataVisualizationDao.countWeldUnitAndPerson(projectIds);
    }

    public List<StatsResultWithNameBo> statsProcessCompletion(List<String> projectIds) {

        // 查询管件信息集合: 焊接关联的
        List<WeldInfoBo> weldList = this.overallStatisticsDao.listWeldInfo(projectIds);

        // 查询管件信息集合: 补口关联的焊口关联的
        List<WeldInfoBo> patchRelationWeldList = this.overallStatisticsDao.listPatchRelationWeldInfo(projectIds);

        // 统计管材, 焊口, 补口
        StatsResultBo pipeStatsResult = this.overallStatisticsDao.statsPipeLengthByType(projectIds, null);
        StatsResultBo weldStatsResult = overallStatisticsService.statsPipeLengthByType(weldList, projectIds, StatsProcessEnum.WELD.getType());
        StatsResultBo patchStatsResult = overallStatisticsService.statsPipeLengthByType(patchRelationWeldList, projectIds, StatsProcessEnum.PATCH.getType());

        // 统计测量放线, 管沟回填, 地貌恢复
        List<StatsResultBo> otherStatsResultBos = this.overallStatisticsDao.statsOtherLength(projectIds);
        otherStatsResultBos.stream().filter(bo -> bo.getStatsResult() == null).forEach(bo -> bo.setStatsResult(0));

        List<StatsResultBo> resultList = Lists.newArrayList(pipeStatsResult, weldStatsResult, patchStatsResult);
        resultList.addAll(otherStatsResultBos);


        List<StatsResultWithNameBo> returnList = Lists.newArrayList();
        Map<String, StatsResultBo> typeToBo = resultList.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, bo -> bo, (a, b) -> b));
        for (StatsProcessEnum anEnum : StatsProcessEnum.values()) {
            StatsResultWithNameBo resultWithNameBo = new StatsResultWithNameBo();
            BeanUtils.copyProperties(typeToBo.get(anEnum.getType()), resultWithNameBo);
            resultWithNameBo.setCnName(anEnum.getName());
            returnList.add(resultWithNameBo);
        }

        return returnList;
    }


    public List<DataEntryAndAuditBo> statsDataAcquisitionAndAudit(List<String> projectIds) {
        return this.dataVisualizationDao.countDataEntryAndAudit(projectIds);
    }


    public List statsPersonFill(List<String> projectIds, Integer topNum) {
        return null;
    }
}
