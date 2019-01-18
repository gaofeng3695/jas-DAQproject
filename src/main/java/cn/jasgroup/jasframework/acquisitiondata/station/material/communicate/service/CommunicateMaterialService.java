package cn.jasgroup.jasframework.acquisitiondata.station.material.communicate.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.station.material.communicate.dao.CommunicateMaterialDao;

@Service
@Transactional
public class CommunicateMaterialService {

	@Autowired
	private CommunicateMaterialDao communicateMaterialDao;

	/**
	 * <p>功能描述：根据项目查询话音交换设备物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月10日 上午11:37:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialScsList(String projectOid) {
		return communicateMaterialDao.getMaterialScsList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询工业电视监控系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月10日 上午11:39:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialCctvSystemList(String projectOid) {
		return communicateMaterialDao.getMaterialCctvSystemList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询会议电视设备物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月10日 上午11:41:18。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialVideoConferenceList(String projectOid) {
		return communicateMaterialDao.getMaterialVideoConferenceList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询高频开关电源设备物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月11日 上午11:07:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialHighFrequencySwitchList(String projectOid) {
		return communicateMaterialDao.getMaterialHighFrequencySwitchList(projectOid);
	}
	
}
