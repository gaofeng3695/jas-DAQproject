package cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.query.bo.StationDetectionRaySubBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class StationDetectionRayDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	public List<StationDetectionRaySubBo> queryStationDetectionRaySubList(String oid) {
		String sql = "SELECT sta.oid,sta.parent_oid,sta.weld_oid,spw.weld_code,sta.defect_position,sta.defect_properties,sta.defect_size,"
					+ "sta.create_user_id,sta.create_user_name,sta.create_datetime,sta.modify_user_id,sta.modify_user_name,sta.modify_datetime,"
					+ "sta.active FROM daq_station_detection_ray_sub sta "
					+ "LEFT JOIN (select oid,weld_code from daq_station_process_weld where active=1 and approve_status=2) spw on spw.oid=sta.weld_oid "
					+ "WHERE 1 = 1 AND sta.active = 1"; 
		return baseJdbcDao.queryForList(sql, null, StationDetectionRaySubBo.class);
	}
	
	
}
