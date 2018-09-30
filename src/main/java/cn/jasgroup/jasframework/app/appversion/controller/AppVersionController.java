package cn.jasgroup.jasframework.app.appversion.controller;

import cn.jasgroup.framework.automation.base.utils.CollectionUtil;
import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.jasframework.acquisitiondata.utils.ResultVOUtil;
import cn.jasgroup.jasframework.app.appversion.service.AppVersionBo;
import cn.jasgroup.jasframework.app.appversion.service.AppVersionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        if (StringUtils.isEmpty(productId) || StringUtils.isEmpty(clientType)) {
            throw new BusinessException("403", "参数错误");
        }

        List<AppVersionBo> versionBos = this.appVersionService.getActiveVersion(productId, clientType);
        if (CollectionUtils.isEmpty(versionBos)) {
            throw new BusinessException("404", "resource not found!");
        }

        return ResultVOUtil.ofSuccess(versionBos.get(0));
    }
}
