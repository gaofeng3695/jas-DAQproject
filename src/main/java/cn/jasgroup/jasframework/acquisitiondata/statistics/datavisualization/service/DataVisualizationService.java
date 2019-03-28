package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm.MaterialBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm.ScopeManagementBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.dao.DataVisualizationDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo.*;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsProcessEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsUtils;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.dao.OverallStatisticsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.domain.utils.DomainUtil;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
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
    private OverallStatisticsDao overallStatisticsDao;

    public List<Map<String, Object>> getProjectInfoByUserId() {
        // id, name, pipe_network_type_code
        List<Map<String, Object>> projectLists = this.daqPrivilegeService.getProject();
        
        // 过滤项目，只查询“管网类型”为“高压管网”的项目	add by gejian at 2019-03-28 14:16:30
        projectLists = projectLists.stream().filter((Map<String, Object> map) -> {
            if("pipe_network_code_001".equals(map.get("pipe_network_type_code")))
            {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        
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
            } else {
                returnList.add(new StatsResultBo(date, 0));
            }
        });

        return returnList;
    }


    public List<StatsResultBo> statsTypeOfPersonnel(List<String> projectIds) {
        List<StatsResultBo> returnList = this.dataVisualizationDao.countWeldUnitAndPerson(projectIds);
        List<Map<String, Integer>> constructorAndSupervisor = this.dataVisualizationDao.countConstructorAndSupervisor(projectIds);
        Map<String, Integer> map = constructorAndSupervisor.get(0);
        map.keySet().stream().map(key -> new StatsResultBo(key, map.get(key))).forEach(returnList::add);
        return returnList;
    }


    public List<StatsResultWithNameBo> statsProcessCompletion(List<String> projectIds) {
        // 统计管材, 焊口, 补口
        StatsResultBo pipeStatsResult = this.overallStatisticsDao.statsPipeLength(projectIds);
        List<StatsResultBo> weldAndPatchStatsResult = this.overallStatisticsDao.statsWeldAndPatchLengthByDate(projectIds);

        // 统计测量放线, 管沟回填, 地貌恢复
        List<StatsResultBo> otherStatsResultBos = this.overallStatisticsDao.statsOtherLength(projectIds);

        List<StatsResultBo> statsResultBoList = Lists.newArrayList(pipeStatsResult);
        statsResultBoList.addAll(weldAndPatchStatsResult);
        statsResultBoList.addAll(otherStatsResultBos);

        // null转0
        statsResultBoList.stream().filter(bo -> bo.getStatsResult() == null).forEach(bo -> bo.setStatsResult(0));

        List<StatsResultWithNameBo> returnList = Lists.newArrayList();
        Map<String, StatsResultBo> typeToBo = statsResultBoList.stream().collect(Collectors.toMap(StatsResultBo::getStatsType, bo -> bo, (a, b) -> b));
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


    public List<PersonFillBo> statsPersonFill(List<String> projectIds, Integer topNum) {

        List<PersonFillBo> resultList = Lists.newArrayList();
        List<StatsResultBo> statsResultBos = this.dataVisualizationDao.countPersonFillTopNum(projectIds, topNum);
        List<String> userIdCollect = statsResultBos.stream().map(StatsResultBo::getStatsType).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(userIdCollect)) {
            return resultList;
        }
        List<PersonFillBo> userAndUnitMapList = this.dataVisualizationDao.queryUserAndUnit(userIdCollect);
        Map<String, PersonFillBo> userIdToName = userAndUnitMapList.stream().collect(Collectors.toMap(PersonFillBo::getUserId, bo -> bo, (a, b) -> b));


        for (int i = 0; i < statsResultBos.size(); i++) {
            StatsResultBo bo = statsResultBos.get(i);
            PersonFillBo personFillBo = new PersonFillBo();
            personFillBo.setNo(i+1);
            personFillBo.setEntryCount(Integer.valueOf(bo.getStatsResult().toString()));
            String userId = bo.getStatsType();
            personFillBo.setUserName(userIdToName.get(userId).getUserName());
            personFillBo.setUnitName(userIdToName.get(userId).getUnitName());
            resultList.add(personFillBo);
        }
        return resultList;
    }
}
