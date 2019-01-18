package cn.jasgroup.jasframework.acquisitiondata.station.detection.infiltration.dao;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.infiltration.query.bo.DaqStationDetectionInfiltrationSubBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class DaqStationDetectionInfiltrationSubDao {

    @Resource
    private BaseJdbcDao baseJdbcDao;

    /**
     * <p>功能描述:根据parentoid查询渗透检测子表(站场)</p>
     * <p> cuixianing</p>
     * @param parentOid
     * @return java.util.List<DaqStationDetectionInfiltrationSub>
     * @since JDK1.8
     * <p>创建日期:2019/1/14 14:22</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public List<DaqStationDetectionInfiltrationSubBo> getDaqStationDetectionInfiltrationSubListByParentOid(String parentOid){
        String sql = "select wc.*, dspw.weld_code,doma.code_name defect_properties_name " +
                "from daq_station_detection_infiltration_sub wc " +
                "LEFT JOIN (select oid,weld_code from daq_station_process_weld where active=1 and approve_status=2) dspw on dspw.oid = wc.weld_oid " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'defect_properties_domain' and active=1 ) doma ON doma.code_id = wc.defect_properties " +
                "where wc.parent_oid=?";
        return baseJdbcDao.queryForList(sql,new Object[]{parentOid},DaqStationDetectionInfiltrationSubBo.class);
    }
}
