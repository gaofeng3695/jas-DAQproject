package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.controller;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.DataVisualizationService;
import cn.jasgroup.jasframework.acquisitiondata.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/10/22 9:50
 */
@RestController
@RequestMapping("/dataVisualization")
public class DataVisualizationController {

    @Autowired
    private DataVisualizationService dataVisualizationService;

    /**
     * 获取项目列表信息
     * 根据当前用户所能看到的项目，获取项目列表
     * @return 列表包含字段（项目名称、管网类型、管线长度）
     */
    @GetMapping("getProjectInfoByUserId")
    public BaseResult getProjectInfoByUserId() {
        return ResultVOUtil.ofSuccess(dataVisualizationService.getProjectInfoByUserId());
    }


    /**
     * 范围管理概况统计
     * - 统计在某一个项目下的管线、中线桩、线路段、穿跨越、站场、阀室、伴行路、外供电线路、标段的数量
     * @param projectId 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("statsScopeManagement")
    public BaseResult statsScopeManagement(@RequestParam String projectId) {
        return ResultVOUtil.ofSuccess(dataVisualizationService.statsScopeManagement(projectId));
    }


    /**
     * 统计各类物资: 已录入数量, 检查数量, 使用数量, 已检查未使用数量
     *  - 封堵物和法兰没有'已检查'和'已检查未使用'数量
     * @param projectId 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("statsQuantityOfMaterial")
    public BaseResult statsQuantityOfMaterial(@RequestParam String projectId) {
        return ResultVOUtil.ofSuccess(dataVisualizationService.statsQuantityOfMaterial(projectId));
    }


    /**
     * 统计钢管使用情况: 防腐管, 直管, 冷弯管 (统计都是未切管的: is_cut=0)
     * - 使用数量
     * - 使用长度
     * @param params projectIds
     * @return {@link BaseResult}
     */
    @PostMapping("steelPipeUsage")
    public BaseResult steelPipeUsage(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectIds");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }
        return ResultVOUtil.ofSuccess(dataVisualizationService.steelPipeUsage(projectIds));
    }


    /**
     * 统计切管情况
     * @param params projectIds
     * @return {@link BaseResult}
     */
    @PostMapping("statsPipeCutting")
    public BaseResult statsPipeCutting(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectIds");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }
        return ResultVOUtil.ofSuccess(dataVisualizationService.statsPipeCutting(projectIds));
    }


    /**
     * 统计焊口一次性合格率
     * @param params projectIds
     * @return {@link BaseResult}
     */
    @PostMapping("statsWeldOnceQualifiedRate")
    public BaseResult statsWeldOnceQualifiedRate(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectIds");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }
        return ResultVOUtil.ofSuccess(dataVisualizationService.statsWeldOnceQualifiedRate(projectIds));
    }


    /**
     * 统计焊口返修情况
     * @param params projectIds
     * @return {@link BaseResult}
     */
    @PostMapping("statsWeldRework")
    public BaseResult statsWeldRework(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectIds");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }

        Integer statsDays = Integer.parseInt(String.valueOf(params.get("statsDays")==null?30:params.get("statsDays")));
        if (statsDays < 0) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }

        return ResultVOUtil.ofSuccess(dataVisualizationService.statsWeldRework(projectIds, statsDays));
    }



    /**
     * TODO: 数据采集人员和监理人员
     * 统计机组和各类人员数量
     * @param params projectIds
     * @return {@link BaseResult}
     */
    @PostMapping("statsTypeOfPersonCount")
    public BaseResult statsTypeOfPersonnel(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectIds");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }
        return ResultVOUtil.ofSuccess(dataVisualizationService.statsTypeOfPersonnel(projectIds));
    }


    /**
     * 统计各工序完成情况
     * @param params projectIds
     * @return {@link BaseResult}
     * 与Web端总体中各工序累计完成情况相同: {@link cn.jasgroup.jasframework.acquisitiondata.statistics.normal.controller.OverallStatisticsController#processCumulativeCompletion}
     */
    @PostMapping("statsProcessCompletion")
    public BaseResult statsProcessCompletion(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectIds");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }
        return ResultVOUtil.ofSuccess(dataVisualizationService.statsProcessCompletion(projectIds));
    }


    /**
     * 统计数据采集和审核情况
     * @param params projectIds
     * @return {@link BaseResult}
     */
    @PostMapping("statsDataAcquisitionAndAudit")
    public BaseResult statsDataAcquisitionAndAudit(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectIds");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }
        return ResultVOUtil.ofSuccess(dataVisualizationService.statsDataAcquisitionAndAudit(projectIds));
    }


    /**
     * 统计人员填报情况
     * @param params projectIds, topNum(统计前几个)
     * @return {@link BaseResult}
     */
    @PostMapping("statsPersonFill")
    public BaseResult statsPersonFill(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectIds");

        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }
        Integer topNum = Integer.valueOf(String.valueOf(params.get("topNum")==null?10:params.get("topNum")));
        if (topNum < 1) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }

        return ResultVOUtil.ofSuccess(dataVisualizationService.statsPersonFill(projectIds, topNum));
    }
}
