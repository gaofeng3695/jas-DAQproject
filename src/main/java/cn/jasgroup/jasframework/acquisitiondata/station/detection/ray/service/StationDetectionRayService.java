package cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.service;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.dao.StationDetectionRayDao;
import cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.query.bo.StationDetectionRayBo;
import cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.query.bo.StationDetectionRaySubBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

@Service
@Transactional
public class StationDetectionRayService extends CommonDataJdbcService {

	@Autowired
	private StationDetectionRayDao stationDetectionRayDao;
	
	public void injectStationDetectionRaySubList(StationDetectionRayBo stationDetectionRayBo){
		if (StringUtils.isNotBlank(stationDetectionRayBo.getOid())) {
			List<StationDetectionRaySubBo> subBoList = stationDetectionRayDao.queryStationDetectionRaySubList(stationDetectionRayBo.getOid());
			stationDetectionRayBo.setFaUltrasonicSubList(subBoList);
		}
	}
	
}
