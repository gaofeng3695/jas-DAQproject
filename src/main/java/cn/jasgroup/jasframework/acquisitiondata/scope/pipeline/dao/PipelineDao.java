package cn.jasgroup.jasframework.acquisitiondata.scope.pipeline.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class PipelineDao extends BaseJdbcDao{
	
	@Resource
	private BaseJdbcDao baseJdbcDao;
	
	public List<Map<String,Object>> getPipeLineList(String projectOid){
		String sql = "select t.oid as key,t.pipeline_name as value from daq_pipeline t where t.active=1 and t.project_oid=?";
		List<Map<String, Object>> result = baseJdbcDao.queryForList(sql, new Object[]{projectOid});
		return result;
	}
}
