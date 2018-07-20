package cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class WeldProductDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;


	public List<Map<String, Object>> getListByProjectOid(String projectOid) {
		String sql = "select oid as key, weld_produce_code as value from daq_weld_produce_specification where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

}
