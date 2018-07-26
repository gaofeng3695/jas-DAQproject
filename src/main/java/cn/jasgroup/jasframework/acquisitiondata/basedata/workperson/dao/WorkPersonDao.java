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

	public List<Map<String, Object>> getListByCondition(String workUnitOid, String str) {
		String sql = "select oid as key, personnel_name as value from daq_work_personnel where active=1 and work_unit_oid='"+workUnitOid+"' and personnel_type in ("+str+");";
		return baseJdbcDao.queryForList(sql, null);
	}

	public List<Map<String, Object>> getPersonByWorkUnit(String workUnitOid) {
		String sql ="select oid as key, personnel_name as value from daq_work_personnel where active=1 and work_unit_oid='"+workUnitOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}
	
	
}
