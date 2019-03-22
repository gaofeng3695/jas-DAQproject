package cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.dao;

import java.util.List;
import java.util.Map;

import cn.jasgroup.jasframework.acquisitiondata.utils.VariateInjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

@Repository
public class PipeFittingDao{
	
	@Autowired
	private BaseJdbcTemplate baseJdbcTemplate;
	
	/***
	  * <p>功能描述：根据管件类型获取相应的管件列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeTypeCode
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月13日 上午11:33:55。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPipeFittingList(String projectOid,String pipeSegmentOrCrossOid,String pipeTypeCode){
		String sql= null;
		switch (pipeTypeCode) {
		case "pipe_type_code_001"://前直钢管
			sql = "select oid as key,pipe_code as value from daq_material_pipe t where active=1 and front_is_use=0 and is_cold_bend=0 and is_cut=0 and is_check=1 and project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_0011"://后直钢管
			sql = "select oid as key,pipe_code as value from daq_material_pipe t where active=1 and back_is_use=0 and is_cold_bend=0 and is_cut=0 and is_check=1 and project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_002"://前热煨弯管
			sql = "select oid as key,hot_bends_code as value from daq_material_hot_bends  t where active=1 and front_is_use=0 and is_check=1 and project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_0021"://后热煨弯管
			sql = "select oid as key,hot_bends_code as value from daq_material_hot_bends  t where active=1 and back_is_use=0 and is_check=1 and project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_003"://三通
			sql = "select oid as key,tee_code as value from daq_material_tee  t where active=1 and project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_004"://阀门
			sql = "select t.oid as key,t.valve_name as value from daq_material_valve t where active=1 and t.project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_005"://绝缘接头
			sql = "select oid as key,manufacturer_code as value from daq_material_insulated_joint  t where active=1 and project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_006"://大小头
			sql = "select oid as key,reducer_code as value from daq_material_reducer  t where active=1 and project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_007"://封堵物
			sql = "select oid as key,closure_code as value from daq_material_closure  t where active=1 and project_oid='"+projectOid+"'";
			break;
		case "pipe_type_code_008"://前冷弯管
			sql = "select oid as key,pipe_cold_bending_code as value from daq_material_pipe_cold_bending  t where active=1 and front_is_use=0 and is_check=1 and pipe_segment_or_cross_oid='"+pipeSegmentOrCrossOid+"' and approve_status=2";
			break;
		case "pipe_type_code_0081"://后冷弯管
			sql = "select oid as key,pipe_cold_bending_code as value from daq_material_pipe_cold_bending  t where active=1 and back_is_use=0 and is_check=1 and pipe_segment_or_cross_oid='"+pipeSegmentOrCrossOid+"' and approve_status=2";
			break;
		}
		if(StringUtils.isBlank(sql)){
			return null;
		}
		return this.baseJdbcTemplate.queryForListHump(sql, null);
	}
	/***
	  * <p>功能描述：更新管线是否使用的信息。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param tendersOid
	  * @param pipelineOid
	  * @param pipeCode
	  * @param pipeTypeCode
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月13日 下午2:45:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateFrontPipeFitting(String tendersOid,String pipelineOid,String pipeOid,String pipeTypeCode,Integer isUse){
		String sql= null;
		if(null == isUse){
			isUse = 0;
		}
		switch (pipeTypeCode) {
		case "pipe_type_code_001"://直钢管
			sql = "update daq_material_pipe set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',front_is_use="+isUse+",is_use=(case when back_is_use=1 then 1 else "+isUse+" end) where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_002"://热煨弯管
			sql = "update daq_material_hot_bends set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',front_is_use="+isUse+",is_use=(case when back_is_use=1 then 1 else "+isUse+" end) where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_003"://三通
			sql = "update daq_material_tee set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_004"://阀门
			sql = "update daq_material_valve set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_005"://绝缘接头
			sql = "update daq_material_insulated_joint set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_006"://大小头
			sql = "update daq_material_reducer set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_007"://封堵物
			sql = "update daq_material_closure set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_008"://冷弯管
			sql = "update daq_material_pipe_cold_bending set front_is_use="+isUse+",is_use=(case when back_is_use=1 then 1 else "+isUse+" end) where oid='"+pipeOid+"'";
			break;
		}
		if(StringUtils.isBlank(sql)){
			return;
		}
		this.baseJdbcTemplate.update(sql, null);
	}
	
	public void updateBackPipeFitting(String tendersOid,String pipelineOid,String pipeOid,String pipeTypeCode,Integer isUse){
		String sql= null;
		if(null == isUse){
			isUse = 0;
		}
		switch (pipeTypeCode) {
		case "pipe_type_code_0011"://直钢管
			sql = "update daq_material_pipe set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',back_is_use="+isUse+",is_use=(case when front_is_use=1 then 1 else "+isUse+" end) where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_0021"://热煨弯管
			sql = "update daq_material_hot_bends set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',back_is_use="+isUse+",is_use=(case when front_is_use=1 then 1 else "+isUse+" end) where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_003"://三通
			sql = "update daq_material_tee set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_004"://阀门
			sql = "update daq_material_valve set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_005"://绝缘接头
			sql = "update daq_material_insulated_joint set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_006"://大小头
			sql = "update daq_material_reducer set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_007"://封堵物
			sql = "update daq_material_closure set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where oid='"+pipeOid+"'";
			break;
		case "pipe_type_code_0081"://冷弯管
			sql = "update daq_material_pipe_cold_bending set back_is_use="+isUse+",is_use=(case when front_is_use=1 then 1 else "+isUse+" end) where oid='"+pipeOid+"'";
			break;
		}
		if(StringUtils.isBlank(sql)){
			return;
		}
		this.baseJdbcTemplate.update(sql, null);
	}
	
	/**
	 * <p>功能描述：根据项目查询已使用且未进行中线测量的弯管列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午3:21:03。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getBendingList(String projectOid) {
		String sql = "select oid as key,hot_bends_code as value from daq_material_hot_bends where active=1 and @privilege_strategy_sql and is_use=1 and is_measure=0 and project_oid=? "
					+ "union all "
					+ "select oid as key,pipe_cold_bending_code as value from daq_material_pipe_cold_bending where active=1 and @privilege_strategy_sql and is_use=1 and is_measure=0 and project_oid=?";
		sql = VariateInjectUtils.invoke(sql);
		return this.baseJdbcTemplate.queryForListHump(sql, new Object[]{projectOid,projectOid});
	}
}
