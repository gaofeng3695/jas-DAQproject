package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.controller;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.OverallStatisticsService;
import cn.jasgroup.jasframework.acquisitiondata.utils.ResultVOUtil;
import com.google.common.collect.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * description: 总体统计
 *
 * @author xiefayang
 * 2018/9/11 9:30
 */
@RestController
@RequestMapping("/overallStatistics")
public class OverallStatisticsController {

    @Autowired
    private OverallStatisticsService overallStatisticsService;


    /**
     * 各工序累计完成情况
     * @param params projectOids
     * @return {@link BaseResult}
     */
    @PostMapping("processCumulativeCompletion")
    public BaseResult processCumulativeCompletion(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectOids");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }
        return ResultVOUtil.ofSuccess(this.overallStatisticsService.processCumulativeCompletion(projectIds));
    }


    /**
     * 各工序自开工以来分月累计完成情况
     * @param params projectOids
     * @return {@link BaseResult}
     */
    @PostMapping("processMonthlyCompletion")
    public BaseResult processMonthlyCompletion(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectOids");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }

        Table<String, String, Object> resultTable = this.overallStatisticsService.processMonthlyCompletion(projectIds);
        if(resultTable==null){
        	return new SimpleResult<>();
        }
        return ResultVOUtil.ofSuccess(resultTable.rowMap());
    }


    /**
     * 一次性合格率统计(射线检测)
     * @param params projectOids
     * @return {@link BaseResult}
     */
    @PostMapping("statsDetectionRayPassCount")
    public BaseResult statsDetectionRayPassCount(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectOids");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }

        return ResultVOUtil.ofSuccess(this.overallStatisticsService.statsDetectionRayPassCount(projectIds));
    }


    /**
     * 数据录入/审核数量情况统计
     * {@link cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.ApproveStatusEnum}
     * - 已录入: all
     * - 需要审核: 1,-1
     * - 已审核: 2
     * @param params projectOids
     * @return {@link BaseResult}
     */
    @PostMapping("dataEntryAudit")
    public BaseResult dataEntryAudit(@RequestBody Map<String, Object> params) {
        List<String> projectIds = (List<String>) params.get("projectOids");
        if (CollectionUtils.isEmpty(projectIds)) {
            return ResultVOUtil.ofStatus(HttpStatus.BAD_REQUEST);
        }

        return ResultVOUtil.ofSuccess(overallStatisticsService.dataEntryAudit((projectIds)));
    }
}
