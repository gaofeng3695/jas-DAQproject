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
			sql = "select pipe_code as value,pipe_code as key from daq_material_pipe t where active=1 and is_use=0 and is_cold_bend=0";
			break;
		case "pipe_type_code_002"://热煨弯管
			sql = "select hot_bends_code as key,hot_bends_code as value from daq_material_hot_bends  t where active=1 and is_use=0";
			break;
		case "pipe_type_code_003"://三通
			sql = "select tee_code as key,tee_code as value from daq_material_tee  t where active=1 and is_use=0";
			break;
		case "pipe_type_code_004"://阀门
			sql = "";
			break;
		case "pipe_type_code_005"://绝缘接头
			sql = "select manufacturer_code as key,manufacturer_code as value from daq_material_insulated_joint  t where active=1 and is_use=0";
			break;
		case "pipe_type_code_006"://大小头
			sql = "select reducer_code as key,reducer_code as value from daq_material_reducer  t where active=1 and is_use=0";
			break;
		case "pipe_type_code_007"://封堵物
			sql = "select closure_code as key,closure_code as value from daq_material_closure  t where active=1 and is_use=0";
			break;
		case "pipe_type_code_008"://冷弯管
			sql = "select pipe_cold_bending_code as key,pipe_cold_bending_code as value from daq_material_pipe_cold_bending  t where active=1 and is_use=0 and pipe_segment_or_cross_oid='"+pipeSegmentOrCrossOid+"'";
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
			sql = "update daq_material_pipe set project_oid='"+projectOid+"',tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where pipe_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_002"://热煨弯管
			sql = "update daq_material_hot_bends set project_oid='"+projectOid+"',tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where hot_bends_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_003"://三通
			sql = "update daq_material_tee set project_oid='"+projectOid+"',tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where tee_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_004"://阀门
			sql = "";
			break;
		case "pipe_type_code_005"://绝缘接头
			sql = "update daq_material_insulated_joint set project_oid='"+projectOid+"',tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where manufacturer_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_006"://大小头
			sql = "update daq_material_reducer set project_oid='"+projectOid+"',tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where reducer_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_007"://封堵物
			sql = "update daq_material_closure set project_oid='"+projectOid+"',tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',is_use="+isUse+" where closure_code='"+pipeCode+"'";
			break;
		case "pipe_type_code_008"://冷弯管
			sql = "update daq_material_pipe_cold_bending set is_use="+isUse+" where pipe_cold_bending_code='"+pipeCode+"'";
			break;
		}
		this.baseJdbcTemplate.update(sql, null);
	}
}
