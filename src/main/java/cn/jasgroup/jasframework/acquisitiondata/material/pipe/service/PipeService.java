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
	public List<Map<String, Object>> getPipeList(String type) {
		return pipeDao.getPipeList(type);
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午3:22:56。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialPipeList() {
		return pipeDao.getMaterialPipeList();
	}
	/***
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午3:22:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialHotBendsList() {
		return pipeDao.getMaterialHotBendsList();
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月5日 下午4:16:49。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialTeeList() {
		return pipeDao.getMaterialTeeList();
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月5日 下午4:17:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialJnsulatedJointList() {
		return pipeDao.getMaterialJnsulatedJointList();
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月5日 下午4:17:15。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialReducerList() {
		return pipeDao.getMaterialReducerList();
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月5日 下午4:17:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialClosureList() {
		return pipeDao.getMaterialClosureList();
	}
	
	
}
