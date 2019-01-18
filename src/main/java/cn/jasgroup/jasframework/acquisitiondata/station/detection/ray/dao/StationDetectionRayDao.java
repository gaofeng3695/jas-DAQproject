package cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.query.bo.StationDetectionRaySubBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
 * 
  *<p>类描述：站场射线检测dao。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月14日 下午3:13:57。</p>
 */
@Repository
public class StationDetectionRayDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	 * <p>功能描述：根据parentOid查询射线检测子级集合。</p>
	  * <p> 葛建。</p>	
	  * @param oid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月14日 下午2:15:46。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<StationDetectionRaySubBo> queryStationDetectionRaySubList(String parentOid) {
		String sql = "SELECT sta.oid,sta.parent_oid,sta.weld_oid,spw.weld_code,sta.defect_position,sta.defect_properties,d.code_name as defect_properties_name,sta.defect_size,"
					+ "sta.create_user_id,sta.create_user_name,sta.create_datetime,sta.modify_user_id,sta.modify_user_name,sta.modify_datetime,"
					+ "sta.active FROM daq_station_detection_ray_sub sta "
					+ "LEFT JOIN (select oid,weld_code from daq_station_process_weld where active=1 and approve_status=2) spw on spw.oid=sta.weld_oid "
					+ "left join (select code_id,code_name from sys_domain where active=1) d on d.code_id=sta.defect_properties "
					+ "WHERE 1 = 1 AND sta.active = 1 and parent_oid = '"+parentOid+"'"; 
		return baseJdbcDao.queryForList(sql, null, StationDetectionRaySubBo.class);
	}
	
	
}
