package cn.jasgroup.jasframework.acquisitiondata.material.pipe.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.PipeDao;

@Service
@Transactional
public class PipeService {

	@Autowired
	private PipeDao pipeDao;

	/**
	 * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param type
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午3:22:51。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPipeList(String type,String projectOid) {
		return pipeDao.getPipeList(type,projectOid);
	}
	/**
	  * <p>功能描述：根据项目获取所有的钢管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:18:46。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialPipeList(String projectOid) {
		return pipeDao.getMaterialPipeList(projectOid);
	}
	/***
	  * <p>功能描述：根据项目获取所有的钢管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午9:32:58。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialPipeList(List<String> projectOids) {
		return pipeDao.getMaterialPipeList(projectOids);
	}
	/**
	  * <p>功能描述：根据项目获取所有的热煨弯管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:19:09。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialHotBendsList(String projectOid) {
		return pipeDao.getMaterialHotBendsList(projectOid);
	}
	/***
	  * <p>功能描述：根据项目获取所有的热煨弯管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:09:21。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialHotBendsList(List<String> projectOids) {
		return pipeDao.getMaterialHotBendsList(projectOids);
	}
	/**
	  * <p>功能描述：根据项目获取所有的三通列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:13:20。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTeeList(String projectOid) {
		return pipeDao.getMaterialTeeList(projectOid);
	}
	/**
	  * <p>功能描述：根据项目获取所有的三通列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:11:32。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTeeList(List<String> projectOids) {
		return pipeDao.getMaterialTeeList(projectOids);
	}
	/**
	  * <p>功能描述：根据项目获取绝缘接头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:13:32。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialJnsulatedJointList(String projectOid) {
		return pipeDao.getMaterialJnsulatedJointList(projectOid);
	}
	/**
	 	* <p>功能描述：根据项目获取绝缘接头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:11:40。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialJnsulatedJointList(List<String> projectOids) {
		return pipeDao.getMaterialJnsulatedJointList(projectOids);
	}
	/**
	  * <p>功能描述：根据项目获取所有的大小头列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月5日 下午4:17:15。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialReducerList(String projectOid) {
		return pipeDao.getMaterialReducerList(projectOid);
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:11:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialReducerList(List<String> projectOids) {
		return pipeDao.getMaterialReducerList(projectOids);
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月5日 下午4:17:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialClosureList(String projectOid) {
		return pipeDao.getMaterialClosureList(projectOid);
	}
	/**
	  * <p>功能描述：根据项目获取所有的大小头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:11:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialClosureList(List<String> projectOids) {
		return pipeDao.getMaterialClosureList(projectOids);
	}
	
	/**
	 * <p>功能描述：获取阀门列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午11:28:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getValveList(String projectOid) {
		return pipeDao.getValveList(projectOid);
	}
	
	/**
	 * <p>功能描述：根据项目获取所有的阀门列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午11:29:12。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getValveList(List<String> projectOids) {
		return pipeDao.getValveList(projectOids);
	}
	
	/**
	  * <p>功能描述：根据项目获取冷弯管下拉选列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:07:35。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getPipeColdBendingList(List<String> projectOids){
		return pipeDao.getPipeColdBendingList(projectOids);
	}
	
}
