package cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.dao;

import java.util.List;
import java.util.Map;

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
	public List<Map<String, Object>> getPipeFittingList(String pipeSegmentOrCrossOid,String pipeTypeCode){
		String sql= null;
		switch (pipeTypeCode) {
		case "pipe_type_code_001"://直钢管
			sql = "select t.pipe_code as value,t.pipe_code as key from daq_material_pipe t where t.active=1 and t.is_cut=1 and t.is_use=0 and t.is_cold_bend=0";
			break;
		case "pipe_type_code_002"://热煨弯管
			sql = "select t.hot_bends_code as key,t.hot_bends_code as value from daq_material_hot_bends  t where t.active=1 and t.is_use=0";
			break;
		case "pipe_type_code_003"://三通
			sql = "select t.tee_code as key,t.tee_code as value from daq_material_tee  t where t.active=1 and t.is_use=0";
			break;
		case "pipe_type_code_004"://阀门
			sql = "";
			break;
		case "pipe_type_code_005"://绝缘接头
			sql = "select t.manufacturer_code as key,t.manufacturer_code as value from daq_material_insulated_joint  t where t.active=1 and t.is_use=0";
			break;
		case "pipe_type_code_006"://大小头
			sql = "select t.reducer_code as key,t.reducer_code as value from daq_material_reducer  t where t.active=1 and t.is_use=0";
			break;
		case "pipe_type_code_007"://封堵物
			sql = "select t.closure_code as key,t.closure_code as value from daq_material_closure  t where t.active=1 and t.is_use=0";
			break;
		case "pipe_type_code_008"://冷弯管
			sql = "select t.pipe_cold_bending_code as key,t.pipe_cold_bending_code as value from daq_material_pipe_cold_bending  t where t.active=1 and t.is_use=0 and t.pipe_segment_or_cross_oid='"+pipeSegmentOrCrossOid+"'";
			break;
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
	public void updatePipeFitting(String projectOid,String tendersOid,String pipelineOid,String pipeCode,String pipeTypeCode,Integer isUse){
		String sql= null;
		if(null == isUse){
			isUse = 0;
		}
		switch (pipeTypeCode) {
		case "pipe_type_code_001"://直钢管
			sql = "update daq_material_pipe t set t.project_oid='"+projectOid+"',t.tenders_oid='"+tendersOid+"',t.pipeline_oid='"+pipelineOid+"',t.is_use="+isUse+" where t.pipe_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_002"://热煨弯管
			sql = "update daq_material_hot_bends t set t.project_oid='"+projectOid+"',t.tenders_oid='"+tendersOid+"',t.pipeline_oid='"+pipelineOid+"',t.is_use="+isUse+" where t.hot_bends_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_003"://三通
			sql = "update daq_material_tee t set t.project_oid='"+projectOid+"',t.tenders_oid='"+tendersOid+"',t.pipeline_oid='"+pipelineOid+"',t.is_use="+isUse+" where t.tee_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_004"://阀门
			sql = "";
			break;
		case "pipe_type_code_005"://绝缘接头
			sql = "update daq_material_insulated_joint t set t.project_oid='"+projectOid+"',t.tenders_oid='"+tendersOid+"',t.pipeline_oid='"+pipelineOid+"',t.is_use="+isUse+" where t.manufacturer_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_006"://大小头
			sql = "update daq_material_reducer t set t.project_oid='"+projectOid+"',t.tenders_oid='"+tendersOid+"',t.pipeline_oid='"+pipelineOid+"',t.is_use="+isUse+" where t.reducer_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_007"://封堵物
			sql = "update daq_material_closure t set t.project_oid='"+projectOid+"',t.tenders_oid='"+tendersOid+"',t.pipeline_oid='"+pipelineOid+"',t.is_use="+isUse+" where t.closure_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_008"://冷弯管
			sql = "update daq_material_pipe_cold_bending t set t.is_use="+isUse+" where t.pipe_cold_bending_code='"+pipeCode+"'";
			break;
		}
		this.baseJdbcTemplate.update(sql, null);
	}
}
