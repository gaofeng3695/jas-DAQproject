package cn.jasgroup.jasframework.acquisitiondata.datavisualization.controller;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.service.DataVisualizationService;
import cn.jasgroup.jasframework.acquisitiondata.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Deprecated
    @GetMapping("statsMaterialCheck")
    public BaseResult statsMaterialPresentCheck(@RequestParam String projectId) {
        return ResultVOUtil.ofSuccess();
    }

    @Deprecated
    @GetMapping("statsMaterialUsage")
    public BaseResult statsMaterialUsage(@RequestParam String projectId) {
        return ResultVOUtil.ofSuccess();
    }

    @Deprecated
    @GetMapping("statsMaterialCheckedUnchecked")
    public BaseResult statsMaterialCheckedUnchecked(@RequestParam String projectId) {
        return ResultVOUtil.ofSuccess();
    }
}
