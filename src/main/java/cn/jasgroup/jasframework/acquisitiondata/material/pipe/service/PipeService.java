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
	
	
}
