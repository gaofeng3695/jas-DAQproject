package cn.jasgroup.jasframework.acquisitiondata.station.material.autocontrol.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
 * 
  *<p>类描述：自动控制设备物资dao。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月8日 上午9:37:50。</p>
 */
@Repository
public class AutocontrolMaterialDao {
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	 * <p>功能描述：根据项目查询SCADA系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:45:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialScadaList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_scada_system where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询站控系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:56:29。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialScsList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_scs_system where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询计量系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:58:23。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialMeteringList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_metering_system where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询调压系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午10:00:22。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialPressureList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_pressure_control_system where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询分析系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午10:57:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMateriAlanalysisList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_analysis_system where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询标定设备物资列表。。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:10:53。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMateriProverList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_prover_system where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询自用气系统物资列表。。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:25:56。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMateriSgsList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_sgs_system where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询压力变送器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:28:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTransmitterList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_pressure_transmitter where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询压力物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:31:29。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialGaugeList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_pressure_gauge where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询差压变送器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:35:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialDifferenceTransmitterList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_pressure_difference_transmitter where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询差压表物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午1:21:55。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialDifferenceGaugeList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_pressure_difference_gauge where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询温度变送器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午1:25:30。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTemperatureTransmitterList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_temperature_transmitter where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询双金属温度计物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午1:28:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialBimetallicThermometerList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_bimetallic_thermometer where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询铂电阻物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午1:30:55。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTemperatureElementList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_temperature_element where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询平均温度计物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午1:33:55。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialAgeTemperatureList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_age_temperature_transmitter where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询液位变送器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午1:36:22。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialLitLevelList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_lit_level_transmitter where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询液位计物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午1:38:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialLevelGaugeList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_level_gauge where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询调节阀物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午1:41:38。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialControlValveList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_control_valve where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询清管球通过指示器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 下午3:23:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialYsPigDetectorList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_ys_pig_detector where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据项目查询分析设备物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月10日 上午9:24:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialAnalysisTransmitter(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_analysis_transmitter where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

	
}
