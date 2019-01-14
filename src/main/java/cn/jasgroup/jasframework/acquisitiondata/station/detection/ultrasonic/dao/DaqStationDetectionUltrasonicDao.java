package cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.dao;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.query.bo.DaqStationDetectionUltrasonicSubBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.jasgroup.jasframework.dataaccess.base.BaseNamedParameterJdbcDao;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 超声波检测(站场)
 * @author 陈祥思
 * @date 2019-01-14 10:00:31
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class DaqStationDetectionUltrasonicDao  extends BaseNamedParameterJdbcDao{
    @Resource
    private BaseJdbcDao baseJdbcDao;

    @Autowired
    private BaseJdbcTemplate baseJdbcTemplate;

    /**
     * @description 查询子表
     * @author 陈祥思
     * @date 2019-01-14 10:00:31
     * @version V1.0
     * @since JDK 1.80
     */

    public List<DaqStationDetectionUltrasonicSubBo> queryStationDetectionUltrasonicSubList(String parentOid) {
        String sql = "SELECT sta.oid,sta.parent_oid,sta.weld_oid,spw.weld_code,sta.defect_position,sta.defect_properties,sta.defect_length,sta.defect_depth,"
                + "sta.create_user_id,sta.create_user_name,sta.create_datetime,sta.modify_user_id,sta.modify_user_name,sta.modify_datetime,"
                + "sta.active, d.code_name defect_properties_name FROM daq_station_detection_ultrasonic_sub sta "
                + "left join (select oid,weld_code from daq_station_process_weld where active=1 and approve_status=2) spw on spw.oid=sta.weld_oid "
                + "left join (select code_id,code_name from sys_domain where domain_name = 'defect_properties_domain' and active=1) d on d.code_id=sta.defect_properties "
                + "WHERE 1 = 1 AND sta.active = 1 and parent_oid = '"+parentOid+"'";
        return baseJdbcDao.queryForList(sql, null, DaqStationDetectionUltrasonicSubBo.class);
    }
}