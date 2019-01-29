package cn.jasgroup.jasframework.acquisitiondata.mesolow.common.service;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.common.dao.OpenGisDao;
import cn.jasgroup.jasframework.acquisitiondata.utils.OpenGISUtil;
import cn.jasgroup.jasframework.base.data.BaseEntity;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.support.ModelFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;

/**
 * <p>类描述：</p>。
 *
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月28日 16:07。</p>
 */
@Service
public class OpenGisService extends CommonDataJdbcService {

    @Autowired
    private OpenGisDao openGisDao;

    /**
     * <p>功能描述: 更新gi是相关字段数据。</p>
     * <p> cuixianing。</p>
     * @param baseEntity
     * @since JDK1.8
     * <p>创建日期:2019/1/28 16:58</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    @Transactional
    public void updateGeom(BaseEntity baseEntity){
        String className = baseEntity.getClass().getName();

        // 获取坐标信息
        String startPointx = baseEntity.getValue("startPointx").toString();
        String startPointy = baseEntity.getValue("startPointy").toString();
        String startPointz = baseEntity.getValue("startPointz").toString();
        String startBuriedDepth = null;
        try {
            startBuriedDepth = baseEntity.getValue("startBuriedDepth").toString();
        } catch (Exception e) {
            startBuriedDepth = "0";
        }

        String endPointx = baseEntity.getValue("endPointx").toString();
        String endPointy = baseEntity.getValue("endPointy").toString();
        String endPointz = baseEntity.getValue("endPointz").toString();
        String endBuriedDepth = null;
        try {
            endBuriedDepth = baseEntity.getValue("endBuriedDepth").toString();
        } catch (Exception e) {
            endBuriedDepth = "0";
        }

        // 调用gi解析函数
        double[][] path = new double[][]{{Double.parseDouble(startPointx),Double.parseDouble(startPointy),Double.parseDouble(startPointz),Double.parseDouble(startPointz)},
                {Double.parseDouble(endPointx),Double.parseDouble(endPointy),Double.parseDouble(endPointz),Double.parseDouble(endBuriedDepth)}};
        String wkt = OpenGISUtil.toLineWkt(path);
        //从缓存容器获取表名
        String tableName = ModelFacade.getTableName(className);
        String oid = baseEntity.getOid();
        openGisDao.updateGeom(wkt,oid,tableName);
    }
}
