package cn.jasgroup.jasframework.app.appversion.dao;

import cn.jasgroup.jasframework.app.appversion.service.AppVersionBo;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/30 10:15
 */
@Repository
public class AppVersionDao {

    @Autowired
    private CommonDataJdbcDao commonDataJdbcDao;

    public List<AppVersionBo> getActiveVersion(String productId, String clientType) {

        String sql = " select * from app_version where product_id = :productId and client_type = :clientType and active = 1 ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("productId", productId, "clientType", clientType), AppVersionBo.class);
    }
}
