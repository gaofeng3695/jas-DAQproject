package cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jasgroup.jasframework.acquisitiondata.utils.VariateInjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.material.pipe.service.PipeScannerBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess3.core.BaseNamedParameterJdbcTemplate;

@Repository
public class PipeDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Autowired
	private BaseNamedParameterJdbcTemplate baseNamedParameterJdbcTemplate;
	
	//查询未使用且长度大于1的钢管
	public List<Map<String, Object>> getPipeList(String type,String projectOid) {
		String sql = null;
		if(type.equals("1")){
			sql ="select oid as key,pipe_code as value,pipe_length as length,pipe_diameter,wall_thickness from daq_material_pipe where active=1 and is_use=0 and pipe_length >= 1 and is_cut=0 and is_check=1 and project_oid='"+projectOid+"'";
		}else{
			sql ="select oid as key,pipe_code as value,pipe_length as length,pipe_diameter,wall_thickness from daq_material_pipe where active=1 and is_use=0 and is_cold_bend=0 and pipe_length >= 1 and is_cut=0 and is_check=1 and project_oid='"+projectOid+"'";
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
	public List<Map<String,Object>> getMaterialPipeList(String projectOid,String type){
		String sql = null;
		if(type.equals("0")){
			sql = "select oid as key,pipe_code as value,back_is_use,front_is_use,is_cold_bend,project_oid from daq_material_pipe where active=1 and is_cut=0 and is_cold_bend=0";
		}else{
			sql = "select oid as key,pipe_code as value,back_is_use,front_is_use,is_cold_bend,project_oid from daq_material_pipe where active=1 and is_cut=0 and is_cold_bend=0 and is_check=0";
		}
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
		String sql = "select oid as key,pipe_code as value,back_is_use,front_is_use,is_cold_bend,is_check,project_oid from daq_material_pipe where active=1 and is_cut=0 and is_cold_bend=0";
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
	public List<Map<String,Object>> getMaterialHotBendsList(String projectOid,String type){
		String sql = null;
		if(type.equals("0")){
			sql = "select oid as key,hot_bends_code as value,back_is_use,front_is_use,project_oid from daq_material_hot_bends where active=1 and  @privilege_strategy_sql";
		}else{
			sql = "select oid as key,hot_bends_code as value,back_is_use,front_is_use,project_oid from daq_material_hot_bends where active=1 and @privilege_strategy_sql and is_check=0";
		}
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and project_oid='"+projectOid+"'";
		}
		sql = VariateInjectUtils.invoke(sql);
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
		String sql = "select oid as key,hot_bends_code as value,back_is_use,front_is_use,project_oid,is_check,is_measure from daq_material_hot_bends where active=1";
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
		String sql = "select oid as key,tee_code as value,is_use,project_oid from daq_material_tee t where active=1";
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
		String sql = "select oid as key,tee_code as value,is_use,project_oid from daq_material_tee t where active=1";
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
		String sql = "select oid as key,manufacturer_code as value,is_use,project_oid from daq_material_insulated_joint t where active=1";
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
		String sql = "select oid as key,manufacturer_code as value,is_use,project_oid from daq_material_insulated_joint t where active=1";
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
		String sql = "select oid as key,reducer_code as value,is_use,project_oid from daq_material_reducer t where active=1";
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
		String sql = "select oid as key,reducer_code as value,is_use,project_oid from daq_material_reducer t where active=1";
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
		String sql = "select oid as key,closure_code as value,is_use,project_oid from daq_material_closure t where active=1";
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
		String sql = "select oid as key,closure_code as value,is_use,project_oid from daq_material_closure t where active=1";
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
		String sql = "select t.oid as key,t.pipe_cold_bending_code as value,t.tenders_oid,t.front_is_use,back_is_use,t.approve_status,t.pipe_segment_or_cross_oid,is_check,project_oid,is_measure "
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
		String sql = "select oid as key,valve_name as value,project_oid from daq_material_valve where active=1";
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
		String sql = "select t.oid as key, t.valve_name as value, t.is_use,project_oid from daq_material_valve t where t.active=1";
		Map<String,Object> param = new HashMap<String,Object>();
		if(projectOids!=null && projectOids.size()>0){
			sql += " and t.project_oid in (:projectOids)";
			param.put("projectOids", projectOids);
		}
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	
	/***
	  * <p>功能描述：直管二维码生成信息查询。</p>
	  * <p> 雷凯。</p>	
	  * @param oids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年11月22日 下午5:21:21。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<PipeScannerBo> queryPipeScannerInfo(List<String> oids){
		Map<String,Object> param = new HashMap<String,Object>();
		String sql = "select concat_ws(';','Z',p.project_code,pipe_code,pipe_diameter||'*'||wall_thickness,tt.code_name,ttt.code_name,pipe_length,pipe_weight,stove_serial_num,tttt.code_name,manufacture_factory,coating_factory,to_char(production_date,'yyyy-MM-dd')) as scanner_context,"
				+ "p.project_code,pipe_code,pipe_length,pipe_weight,stove_serial_num,tttt.code_name as external_coating_type,manufacture_factory,coating_factory,to_char(production_date,'yyyy-MM-dd') as production_date,"
				+ "concat_ws(',',pipe_diameter||'*'||wall_thickness,tt.code_name,ttt.code_name) as pipe_info "
				+ "from daq_material_pipe t "
				+ "left join (select code_id,code_name from sys_domain t where t.domain_name='pipe_forming_method_domain' and t.active=1) tt on tt.code_id=t.pipe_forming_method "
				+ "left join (select code_id,code_name from sys_domain t where t.domain_name='grade_domain' and t.active=1) ttt on ttt.code_id=t.grade "
				+ "left join (select code_id,code_name from sys_domain t where t.domain_name='coating_type_domain' and t.active=1) tttt on tttt.code_id=t.external_coating_type "
				+ "left join (select oid,project_code from daq_project) p on p.oid=t.project_oid "
				+ "where t.active=1 and t.is_cut=0 ";
		if(oids!=null && oids.size()>0){
			sql += "and t.oid in (:oids) ";
			param.put("oids", oids);
		}
		sql += " order by t.pipe_code";
		return this.baseNamedParameterJdbcTemplate.queryForList(sql, param,PipeScannerBo.class);
	}
	/***
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param oids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年11月23日 下午4:47:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<PipeScannerBo> queryHotPipeScannerInfo(List<String> oids){
		Map<String,Object> param = new HashMap<String,Object>();
		String sql = "select concat_ws(';','RW',p.project_code,t.hot_bends_code,t.diameter||'*'||t.wall_thickness,pf.code_name,tt.code_name,t.pipe_length,t.curve_length,t.angle_bending,ct.code_name,t.manufacture_factory,to_char(t.production_date,'yyyy-MM-dd')) as scanner_context,"
					+ "p.project_code,t.hot_bends_code as pipe_code,"
					+"concat_ws(',',t.diameter||'*'||t.wall_thickness,pf.code_name,tt.code_name)as pipe_info,"
					+"t.pipe_length,t.curve_length,t.angle_bending,ct.code_name as external_coating_type,t.manufacture_factory,to_char(production_date,'yyyy-MM-dd') as production_date"
					+" from daq_material_hot_bends t " 
					+"left join (select oid,project_code from daq_project where active=1) p on p.oid=t.project_oid "
					+"left join (select code_id,code_name from sys_domain t where t.domain_name='pipe_forming_method_domain' and t.active=1) pf on pf.code_id=t.pipe_forming_method "
					+"left join (select code_id,code_name from sys_domain t where t.domain_name='grade_domain' and t.active=1) tt on tt.code_id=t.pipe_grade "
					+"left join (select code_id,code_name from sys_domain t where t.domain_name='coating_type_domain' and t.active=1) ct on ct.code_id=t.external_coating_type "
					+"where t.active=1";
		if(oids!=null && oids.size()>0){
			sql += "and t.oid in (:oids) ";
			param.put("oids", oids);
		}
		sql += " order by t.hot_bends_code";
		return this.baseNamedParameterJdbcTemplate.queryForList(sql, param,PipeScannerBo.class);
	}
	
	/**
	 * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param functionCode
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年11月23日 下午4:39:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public String getTableNameByFunctionCode(String functionCode){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("functionCode",functionCode);
		String tableName=null;
		String selectSql = "select t.table_name from custom_fun_function t where t.function_code=:functionCode";
		List<Map<String, Object>> dataList = baseNamedParameterJdbcTemplate.queryForListHump(selectSql, param);
		if(dataList.size()>0){
			Object tableNameObj = dataList.get(0).get("tableName");
			tableName = tableNameObj !=null ? tableNameObj.toString() : "";
		}
		return tableName;
	}
}
