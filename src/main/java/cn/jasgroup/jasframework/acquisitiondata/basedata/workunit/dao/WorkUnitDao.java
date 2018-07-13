package cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class WorkUnitDao {
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	public List<Map<String, Object>> getListByCondition(String projectOid, String types) {
		String sql = "select oid as key, work_unit_name as value from daq_work_unit where project_oid='"+projectOid+"' and work_unit_type in ("+types+");";
		return baseJdbcDao.queryForList(sql, null);
	}
	
	
}
