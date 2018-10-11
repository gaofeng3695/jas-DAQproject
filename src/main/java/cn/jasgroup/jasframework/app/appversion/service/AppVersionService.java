package cn.jasgroup.jasframework.app.appversion.service;

import cn.jasgroup.jasframework.app.appversion.dao.AppVersionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/30 10:18
 */
@Service
public class AppVersionService {

    @Autowired
    private AppVersionDao appVersionDao;

    public List<AppVersionBo> getActiveVersion(String productId, String clientType) {
        return this.appVersionDao.getActiveVersion(productId, clientType);
    }
}
