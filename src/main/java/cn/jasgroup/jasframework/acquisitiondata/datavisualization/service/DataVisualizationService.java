package cn.jasgroup.jasframework.acquisitiondata.datavisualization.service;

import cn.jasgroup.jasframework.acquisitiondata.datavisualization.comm.MaterialBlock;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.comm.ScopeManagementBlock;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.dao.DataVisualizationDao;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.service.bo.MaterialStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.service.bo.ScopeStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.domain.utils.DomainUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<ScopeStatsResultBo> statsScopeManagement(String projectId) {
        List<ScopeStatsResultBo> returnList = this.dataVisualizationDao.countScopeManagement(projectId);
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
}
