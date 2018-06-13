package cn.jasgroup.jasframework.acquisitiondata.scope.project.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.scope.project.dao.ProjectDao;
import cn.jasgroup.jasframework.base.service.BaseService;

@Service
@Transactional
public class ProjectService extends BaseService{
	
	@Resource
	private ProjectDao projectDao;
	
	/**
	 * <p>功能描述：获取项目列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午4:24:33。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getProjectList(){
		return this.projectDao.getProjectList();
	}
}
