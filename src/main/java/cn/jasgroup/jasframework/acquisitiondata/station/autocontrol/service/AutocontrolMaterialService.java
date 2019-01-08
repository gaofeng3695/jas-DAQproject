package cn.jasgroup.jasframework.acquisitiondata.station.autocontrol.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.station.autocontrol.dao.AutocontrolMaterialDao;

/**
 * 
  *<p>类描述：自动控制设备物资service。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月8日 上午9:38:58。</p>
 */
@Service
@Transactional
public class AutocontrolMaterialService {

	@Autowired
	private AutocontrolMaterialDao autocontrolMaterialDao;

	/**
	 * <p>功能描述：根据项目查询SCADA系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:44:58。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialScadaList(String projectOid) {
		return autocontrolMaterialDao.getMaterialScadaList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询站控系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:56:10。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialScsList(String projectOid) {
		return autocontrolMaterialDao.getMaterialScsList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询计量系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:57:57。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialMeteringList(String projectOid) {
		return autocontrolMaterialDao.getMaterialMeteringList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询调压系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:59:57。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialPressureList(String projectOid) {
		return autocontrolMaterialDao.getMaterialPressureList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询分析系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午10:56:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMateriAlanalysisList(String projectOid) {
		return autocontrolMaterialDao.getMateriAlanalysisList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询标定设备物资列表。。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:10:29。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMateriProverList(String projectOid) {
		return autocontrolMaterialDao.getMateriProverList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询自用气系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:25:30。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMateriSgsList(String projectOid) {
		return autocontrolMaterialDao.getMateriSgsList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询压力变送器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:28:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTransmitterList(String projectOid) {
		return autocontrolMaterialDao.getMaterialTransmitterList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询压力物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:31:10。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialGaugeList(String projectOid) {
		return autocontrolMaterialDao.getMaterialGaugeList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询差压变送器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:34:49。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialDifferenceTransmitterList(String projectOid) {
		return autocontrolMaterialDao.getMaterialDifferenceTransmitterList(projectOid);
	}

}
