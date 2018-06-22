package cn.jasgroup.jasframework.acquisitiondata.scope.tenderscope.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedCaseInsensitiveMap;

import cn.jasgroup.jasframework.acquisitiondata.scope.tenderscope.dao.entity.TenderScopeBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class TenderScopeDAO {
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	public String getProjectByTenderId(String tenderId) {
		String sql = "select project_oid from daq_tenders where oid = ?";
		return (String) baseJdbcDao.queryForObject(sql, new Object[]{tenderId});
	}

	public List<TenderScopeBo> getBoByProjectId(String projectId) {
		String sql = "select * from v_daq_scope where project_oid=?";
		List<TenderScopeBo> tenderScopeBoList = (List<TenderScopeBo>)baseJdbcDao.queryForList(sql, new Object[]{projectId}, TenderScopeBo.class);
		return tenderScopeBoList;
	}

	public List<LinkedCaseInsensitiveMap> getScopeIdByTenderId(String tenderId) {
		String sql = "select scope_oid  from daq_tenders_scope_ref where active=1 and tenders_oid=?";
		List<LinkedCaseInsensitiveMap> scopeIdList = baseJdbcDao.queryForList(sql, new Object[]{tenderId});
		return scopeIdList;
	}
	
}
