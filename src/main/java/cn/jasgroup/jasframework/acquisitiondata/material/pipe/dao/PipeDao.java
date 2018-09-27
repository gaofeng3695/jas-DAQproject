package cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess3.core.BaseNamedParameterJdbcTemplate;

@Repository
public class PipeDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Autowired
	private BaseNamedParameterJdbcTemplate baseNamedParameterJdbcTemplate;
	
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
	  * <p>功能描述：根据项目获取所有的钢管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:17:23。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialPipeList(String projectOid){
		String sql = "select oid as key,pipe_code as value,back_is_use,front_is_use,is_cold_bend from daq_material_pipe where active=1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and project_oid='"+projectOid+"'";
		}
		return baseJdbcDao.queryForList(sql, null);
	}
	/***
	  * <p>功能描述：根据项目获取所有的钢管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:17:42。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialPipeList(List<String> projectOids){
		String sql = "select oid as key,pipe_code as value,back_is_use,front_is_use,is_cold_bend from daq_material_pipe where active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	/**
	  * <p>功能描述：根据项目获取所有的热煨弯管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:18:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialHotBendsList(String projectOid){
		String sql = "select oid as key,hot_bends_code as value,back_is_use,front_is_use from daq_material_hot_bends where active=1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and project_oid='"+projectOid+"'";
		}
		return baseJdbcDao.queryForList(sql, null);
	}
	/**
	  * <p>功能描述：根据项目获取所有的热煨弯管列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:10:09。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialHotBendsList(List<String> projectOids){
		String sql = "select oid as key,hot_bends_code as value,back_is_use,front_is_use from daq_material_hot_bends where active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	/***
	  * <p>功能描述：根据项目获取所有的三通列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:13:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialTeeList(String projectOid){
		String sql = "select oid as key,tee_code as value,is_use from daq_material_tee  t where active=1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and project_oid='"+projectOid+"'";
		}
		return baseJdbcDao.queryForList(sql, null);
	}
	/**
	  * <p>功能描述：根据项目获取所有的三通列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:12:42。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialTeeList(List<String> projectOids){
		String sql = "select oid as key,tee_code as value,is_use from daq_material_tee  t where active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	/***
	  * <p>功能描述：根据项目获取绝缘接头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:13:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialJnsulatedJointList(String projectOid){
		String sql = "select oid as key,manufacturer_code as value,is_use from daq_material_insulated_joint t where active=1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and project_oid='"+projectOid+"'";
		}
		return baseJdbcDao.queryForList(sql, null);
	}
	/**
	  * <p>功能描述：根据项目获取绝缘接头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:14:08。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialJnsulatedJointList(List<String> projectOids){
		String sql = "select oid as key,manufacturer_code as value,is_use from daq_material_insulated_joint t where active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	/***
	  * <p>功能描述：根据项目获取所有的大小头列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:15:21。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialReducerList(String projectOid){
		String sql = "select oid as key,reducer_code as value,is_use from daq_material_reducer t where active=1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and project_oid='"+projectOid+"'";
		}
		return baseJdbcDao.queryForList(sql, null);
	}
	/***
	 * <p>功能描述：根据项目获取所有的大小头列表。</p>
	 * <p> 雷凯。</p>	
	 * @param projectOids
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年9月17日 上午10:15:21。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialReducerList(List<String> projectOids){
		String sql = "select oid as key,reducer_code as value,is_use from daq_material_reducer t where active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	/**
	  * <p>功能描述：根据项目获取所有的封堵物列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午10:16:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialClosureList(String projectOid){
		String sql = "select oid as key,closure_code as value,is_use from daq_material_closure t where active=1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and project_oid='"+projectOid+"'";
		}
		return baseJdbcDao.queryForList(sql, null);
	}
	/**
	 * <p>功能描述：根据项目获取所有的封堵物列表。</p>
	 * <p> 雷凯。</p>	
	 * @param projectOids
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年9月17日 上午10:16:07。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialClosureList(List<String> projectOids){
		String sql = "select oid as key,closure_code as value,is_use from daq_material_closure t where active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	/***
	  * <p>功能描述：根据项目获取冷弯管下拉选列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月20日 下午3:12:41。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getPipeColdBendingList(List<String> projectOids){
		String sql = "select t.oid as key,t.pipe_cold_bending_code as value,t.tenders_oid,t.front_is_use,back_is_use,t.approve_status,t.pipe_segment_or_cross_oid "
				+ "from daq_material_pipe_cold_bending t "
				+ "where t.active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and t.project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	
	/**
	 * <p>功能描述：获取阀门列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午11:28:48。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getValveList(String projectOid) {
		String sql = "select oid as key,valve_name as value from daq_material_valve where active=1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and project_oid='"+projectOid+"'";
		}
		return baseJdbcDao.queryForList(sql, null);
	}
	
	/**
	 * <p>功能描述：根据项目获取所有的阀门列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午11:29:35。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getValveList(List<String> projectOids) {
		String sql = "select t.oid as key, t.valve_name as value, t.is_use from daq_material_valve t where t.active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and t.project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
}
