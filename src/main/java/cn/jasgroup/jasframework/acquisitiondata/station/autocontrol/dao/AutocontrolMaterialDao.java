package cn.jasgroup.jasframework.acquisitiondata.station.autocontrol.dao;

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

	
}
