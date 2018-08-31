package cn.jasgroup.jasframework.acquisitiondata.statistics.controller;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.StatisticsService;
import cn.jasgroup.jasframework.acquisitiondata.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: 统计相关接口
 *
 * @author xiefayang
 * 2018/8/27 10:30
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;


    /**
     * 数据录入统计(app)
     * @param stasticTypes 统计类型来源:
     *                     - 支持传入单个, 多个进行统计
     *                     - 不传默认统计默认的7个
     * @return {@link BaseResult}
     */
    @PostMapping("dataEntry")
    public BaseResult dataEntry(@RequestBody(required = false) List<String> stasticTypes) {
        return ResultVOUtil.ofSuccess(statisticsService.dataEntry(stasticTypes));
    }


    /**
     * 数据审核统计(app)
     * @return {@link BaseResult}
     */
    @GetMapping("dataAuditing")
    public BaseResult dataAuditing(@RequestParam String constructUnit) {
        return ResultVOUtil.ofSuccess(this.statisticsService.dataAuditing(constructUnit));
    }
}
