package cn.jasgroup.jasframework.acquisitiondata.scope.scopeManager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.scope.scopeManager.dao.TenderScopeDAO;
import cn.jasgroup.jasframework.acquisitiondata.scope.scopeManager.dao.entity.TenderScopeBo;

@Service
@Transactional
public class TenderScopeService {

	@Autowired
	private TenderScopeDAO TenderScopeDAO;
	
	/**
	 * 通过标段id获取项目id
	 * @param tenderId
	 * @return
	 */
	public String getProjectByTenderId(String tenderId) {
		return TenderScopeDAO.getProjectByTenderId(tenderId);
	}

	/**
	 * 通过项目id获取标段范围BoList
	 * @param projectId
	 * @return
	 */
	public List<TenderScopeBo> getBoByProjectId(String projectId) {
		return TenderScopeDAO.getBoByProjectId(projectId);
	}
	
	
}
