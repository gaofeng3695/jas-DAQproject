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
	
	//查询未使用且长度大于1的钢管
	public List<Map<String, Object>> getPipeList(String type) {
		String sql = null;
		if(type.equals("1")){
			sql ="select oid as key,pipe_code as value,pipe_length as length,pipe_diameter,wall_thickness from daq_material_pipe where active=1 and is_use=0 and pipe_length >= 1";
		}else{
			sql ="select oid as key,pipe_code as value,pipe_length as length,pipe_diameter,wall_thickness from daq_material_pipe where active=1 and is_use=0 and is_cold_bend=0 and pipe_length >= 1";
		}
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
	
	/***
	  * <p>功能描述：获取所有的钢管列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午3:19:33。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialPipeList(){
		String sql = "select oid as key,pipe_code as value from daq_material_pipe where active=1";
		return baseJdbcDao.queryForList(sql, null);
	}
	/**
	  * <p>功能描述：获取所有的热煨弯管列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午3:21:33。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialHotBendsList(){
		String sql = "select oid as key,hot_bends_code as value from daq_material_hot_bends where active=1";
		return baseJdbcDao.queryForList(sql, null);
	}
}
