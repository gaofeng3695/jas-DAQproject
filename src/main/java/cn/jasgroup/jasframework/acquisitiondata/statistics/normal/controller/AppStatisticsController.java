package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.controller;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsProcessForAppEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.AppStatisticsService;
import cn.jasgroup.jasframework.acquisitiondata.utils.ResultVOUtil;
import com.google.common.collect.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: APP统计相关接口
 *
 * @author xiefayang
 * 2018/8/27 10:30
 */
@RestController
@RequestMapping("/statistics")
public class AppStatisticsController {

    @Autowired
    private AppStatisticsService appStatisticsService;

    /**
     * 数据录入统计(app)
     * @param params 统计类型来源:
     * - 支持传入单个, 多个进行统计
     * - 不传默认统计默认的7个
     *
     * @return {@link BaseResult}
     */
    @PostMapping("dataEntry")
    public BaseResult dataEntry(@RequestBody Map<String, Object> params) {
        @SuppressWarnings("unchecked")
        List<String> statsTypes = (List<String>) params.get("statsTypes");
        String projectId = (String) params.get("projectOid");
        return ResultVOUtil.ofSuccess(appStatisticsService.dataEntry(statsTypes, projectId));
    }


    /**
     * 数据审核统计(app)
     * @param unitId 单位ID
     * @param projectOid 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("dataAuditing")
    public BaseResult dataAuditing(@RequestParam String unitId, @RequestParam String projectOid) {
        return ResultVOUtil.ofSuccess(this.appStatisticsService.dataAuditing(projectOid, unitId));
    }


    /* ---------------------------------------------------------------------------------- */

    /**
     * 昨日进度统计
     * @param projectId 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("statsYesterdayProcess")
    public BaseResult statsYesterdayProcess(@RequestParam String projectId) {
        return ResultVOUtil.ofSuccess(this.appStatisticsService.statsYesterdayProcess(projectId));
    }


    /**
     * 昨日进度统计(分单位统计)
     * @param projectId 项目ID
     * @param statsType 统计类型 {@link StatsProcessForAppEnum}
     * @return {@link BaseResult}
     */
    @GetMapping("statsYesterdayProcessDetail")
    public BaseResult statsYesterdayProcessDetail(@RequestParam String projectId, @RequestParam String statsType) {
        List<String> statsTypes = Arrays.stream(StatsProcessForAppEnum.values()).map(StatsProcessForAppEnum::getType).collect(Collectors.toList());
        if (!statsTypes.contains(statsType)) {
            throw new BusinessException("统计类型statsType不存在", "403");
        }
        return ResultVOUtil.ofSuccess(this.appStatisticsService.statsYesterdayProcessDetail(projectId, statsType));
    }


    /**
     * 近一周累积完成情况统计
     * @param projectId 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("statsLatestWeekCumulativeProcess")
    public BaseResult statsLatestWeekCumulativeProcess(@RequestParam String projectId) {
        Table<String, String, Object> resultTable = this.appStatisticsService.statsLatestWeekCumulativeProcess(projectId);
        return ResultVOUtil.ofSuccess(resultTable.rowMap());
    }


    /**
     * 近一周累积完成情况统计详情(分单位统计)
     * @param projectId 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("statsLatestWeekCumulativeProcessDetail")
    public BaseResult statsLatestWeekCumulativeProcessDetail(@RequestParam String projectId) {
        List result = this.appStatisticsService.statsLatestWeekCumulativeProcessDetail(projectId);
        return ResultVOUtil.ofSuccess(result);
    }


    /**
     * 焊口检测情况统计
     * @param projectId 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("statsWeldCheck")
    public BaseResult statsWeldCheck(@RequestParam String projectId) {
        return ResultVOUtil.ofSuccess(this.appStatisticsService.statsWeldCheck(projectId));
    }


    /**
     * 焊口检测情况详情(分单位统计)
     * @param projectId 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("statsWeldCheckDetail")
    public BaseResult statsWeldCheckDetail(@RequestParam String projectId) {
        return ResultVOUtil.ofSuccess(this.appStatisticsService.statsWeldCheckDetail(projectId));
    }


    /**
     * 数据录入及审核情况统计(本周的)
     * @param projectId 项目ID
     * @return {@link BaseResult}
     */
    @GetMapping("statsDateEntryAndAuditing")
    public BaseResult statsDateEntryAndAuditing(@RequestParam String projectId) {
        Table<String, String, Integer> resultTable = this.appStatisticsService.statsDateEntryAndAuditing(projectId);
        return ResultVOUtil.ofSuccess(resultTable.rowMap());
    }

}
