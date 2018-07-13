package cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class WorkPersonDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	public List<Map<String, Object>> getListByCondition(String projectOid, String str) {
		String sql = "select oid as key, personnel_name as value from daq_work_personnel where project_oid='"+projectOid+"' and personnel_type in ("+str+");";
		return baseJdbcDao.queryForList(sql, null);
	}
	
	
}
