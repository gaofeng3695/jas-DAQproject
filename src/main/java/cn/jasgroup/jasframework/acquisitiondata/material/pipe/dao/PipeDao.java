package cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class PipeDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	//查询未使用且长度大于1的钢管
	public List<Map<String, Object>> getPipeList() {
		String sql ="select pipe_code as key,pipe_code as value from daq_material_pipe where active=1 and is_use=0 and pipe_length >= 1";
		return baseJdbcDao.queryForList(sql, null);
	}

	//删除类似编号且oid不相等的钢管
	public void deleteSegmentPipe(String pipeCode, String oid) {
		String sql = "delete from daq_material_pipe where oid != ? and pipe_code like ?";
		Object[] values = new Object[2];
		values[0] = oid;
		values[1] = pipeCode+"%";
		baseJdbcDao.delete(sql, values);
	}
	
}
