package cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class PipeDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	public List<Map<String, Object>> getPipeList() {
		String sql ="select pipe_code as key,pipe_code as value from daq_material_pipe where active=1 and is_cut=1 and is_use=0";
		return baseJdbcDao.queryForList(sql, null);
	}
	
}
