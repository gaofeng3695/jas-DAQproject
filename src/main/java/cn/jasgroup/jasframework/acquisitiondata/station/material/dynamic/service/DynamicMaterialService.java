package cn.jasgroup.jasframework.acquisitiondata.station.material.dynamic.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.station.material.dynamic.dao.DynamicMaterialDao;

@Service
@Transactional
public class DynamicMaterialService {

	@Autowired
	private DynamicMaterialDao dynamicMaterialDao;

	/**
	 * <p>功能描述：查询项目下的空气冷却器列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:25:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialAirCoolerList(String projectOid) {
		return dynamicMaterialDao.getMaterialAirCoolerList(projectOid);
	}

	/**
	 * <p>功能描述：查询项目下的压缩机燃气机物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:35:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialGasEngine(String projectOid) {
		return dynamicMaterialDao.getMaterialGasEngine(projectOid);
	}

	/**
	 * <p>功能描述：查询项目下的仪表风压缩机橇物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:37:29。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialInstrumentCompressor(String projectOid) {
		return dynamicMaterialDao.getMaterialInstrumentCompressor(projectOid);
	}

	/**
	 * <p>功能描述：查询项目下的电加热器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:41:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialElectricHeater(String projectOid) {
		return dynamicMaterialDao.getMaterialElectricHeater(projectOid);
	}

	/**
	 * <p>功能描述：查询项目下的消气器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:43:21。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialAirEliminater(String projectOid) {
		return dynamicMaterialDao.getMaterialAirEliminater(projectOid);
	}

	
}
