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

/**
 * 
  *<p>类描述：站场射线检测service。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月14日 下午2:14:42。</p>
 */
@Service
@Transactional
public class StationDetectionRayService extends CommonDataJdbcService {

	@Autowired
	private StationDetectionRayDao stationDetectionRayDao;
	
	/**
	 * <p>功能描述：分页查询时根据parentOid查询射线检测子级集合。</p>
	  * <p> 葛建。</p>	
	  * @param stationDetectionRayBo
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月14日 下午2:15:14。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void injectStationDetectionRaySubList(List<StationDetectionRayBo> stationDetectionRayBoList){
		if(stationDetectionRayBoList.size()==1){
			StationDetectionRayBo stationDetectionRayBo = stationDetectionRayBoList.get(0);
			List<StationDetectionRaySubBo> subBoList = stationDetectionRayDao.queryStationDetectionRaySubList(stationDetectionRayBo.getOid());
			stationDetectionRayBo.setFaUltrasonicSubList(subBoList);
		}
		System.err.println(stationDetectionRayBoList.size());
	}
	
}
