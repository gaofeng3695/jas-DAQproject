package cn.jasgroup.jasframework.app.appversion.controller;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.jasframework.acquisitiondata.utils.ResultVOUtil;
import cn.jasgroup.jasframework.app.appversion.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/30 10:13
 */
@RestController
@RequestMapping("/appversion")
public class AppVersionController {

    @Autowired
    private AppVersionService appVersionService;

    @GetMapping("getActiveVersion")
    public BaseResult getActiveVersion(@RequestParam String productId, @RequestParam String clientType) {
        return ResultVOUtil.ofSuccess(this.appVersionService.getActiveVersion(productId, clientType));
    }
}
