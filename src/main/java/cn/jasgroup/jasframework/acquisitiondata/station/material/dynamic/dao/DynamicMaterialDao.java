package cn.jasgroup.jasframework.acquisitiondata.station.material.dynamic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class DynamicMaterialDao {
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	 * <p>功能描述：查询项目下的空气冷却器列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:24:14。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialAirCoolerList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_air_cooler where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：查询项目下的压缩机燃气机物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:35:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialGasEngine(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_compressor_gas_engine where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：查询项目下的仪表风压缩机橇物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:37:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialInstrumentCompressor(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_instrument_compressor where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：查询项目下的电加热器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:41:30。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialElectricHeater(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_electric_heater where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：查询项目下的消气器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:43:52。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialAirEliminater(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_air_eliminater"
				+ " where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

}
