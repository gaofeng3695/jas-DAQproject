package cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.dao.StationVentStackDao;
import cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.dao.entity.StationVentStack;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * 
  *<p>类描述：放空立管service。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月15日 上午10:06:13。</p>
 */
@Service
@Transactional
public class StationVentStackService extends CommonDataJdbcService {

	@Autowired 
	private StationVentStackDao stationVentStackDao;
	
	@Autowired
	private CommonDataJdbcService commonDataJdbcService;
	
	/**
	 * <p>功能描述：放空立管保存后更新对应物资的数据。</p>
	  * <p> 葛建。</p>	
	  * @param stationVentStack
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月15日 上午10:46:02。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateMaterialAfterSaveEntity(StationVentStack stationVentStack){
		String materialOid = stationVentStack.getManufactureNumber();
		if (StringUtils.isNotBlank(materialOid)) {
			stationVentStackDao.updateMaterialData(stationVentStack.getTendersOid(), stationVentStack.getPipelineOid(), stationVentStack.getPipeStationOid(), materialOid);
		}
	}
	
	/**
	 * <p>功能描述：放空立管修改前更新对应物资的数据。</p>
	  * <p> 葛建。</p>	
	  * @param stationVentStack
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月15日 上午10:54:39。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateMaterialBeforeUpdateEntity(StationVentStack stationVentStack){
		StationVentStack oldStationVentStack = (StationVentStack)commonDataJdbcService.get(StationVentStack.class, stationVentStack.getOid());
		String materialOid = oldStationVentStack.getManufactureNumber();
		if (StringUtils.isNotBlank(materialOid)) {
			stationVentStackDao.updateMaterialData("", "", "", materialOid);
		}
	}
	
	/**
	 * <p>功能描述：放空立管修改后更新对应物资的数据。</p>
	  * <p> 葛建。</p>	
	  * @param stationVentStack
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月15日 上午10:56:44。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateMaterialAfterUpdateEntity(StationVentStack stationVentStack){
		String materialOid = stationVentStack.getManufactureNumber();
		if (StringUtils.isNotBlank(materialOid)) {
			stationVentStackDao.updateMaterialData(stationVentStack.getTendersOid(), stationVentStack.getPipelineOid(), stationVentStack.getPipeStationOid(), materialOid);
		}
	}
	
	/**
	 * <p>功能描述：放空立管删除后更新对应物资的数据。</p>
	  * <p> 葛建。</p>	
	  * @param stationVentStack
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月15日 上午10:57:31。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateMaterialAfterDeleteEntity(StationVentStack stationVentStack){
		stationVentStack = stationVentStackDao.find(stationVentStack.getOid());
		if (stationVentStack != null) {
			String materialOid = stationVentStack.getManufactureNumber();
			if (StringUtils.isNotBlank(materialOid)) {
				stationVentStackDao.updateMaterialData(stationVentStack.getTendersOid(), stationVentStack.getPipelineOid(), stationVentStack.getPipeStationOid(), materialOid);
			}
		}
	}
	
	
	
}
