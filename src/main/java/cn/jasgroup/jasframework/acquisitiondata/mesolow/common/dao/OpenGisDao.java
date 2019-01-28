package cn.jasgroup.jasframework.acquisitiondata.mesolow.common.dao;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <p>类描述：</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月28日 15:59。</p>
 */
@Component
public class OpenGisDao extends BaseJdbcDao {

    @Resource
    private BaseJdbcDao baseJdbcDao;

    /**
     * <p>功能描述: 更新gi是相关字段数据。</p>
     * <p> cuixianing。</p>
     * @param wkt, oid, tableName
     * @since JDK1.8
     * <p>创建日期:2019/1/28 16:06</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public void updateGeom(String wkt,String oid,String tableName){
        String sql = "update "+tableName+" set geom = st_geomfromtext('"+wkt+"'),geo_state='正常' where oid = ? ";
        baseJdbcDao.update(sql,new Object[]{oid});
    }
}
