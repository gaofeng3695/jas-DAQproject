package cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service.bo.ColdBendingPipeBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@QueryConfig(
	scene="/coldBendingPipe/getPage",
	resultClass=ColdBendingPipeBo.class
)
public class ColdBendingPipeQuery extends BaseJavaQuery{
	/**
	 * 唯一标识
	 */
	private String oid;
	/**
	 * 项目oid
	 */
	private String projectOid;
	/**
	 * 标段oid
	 */
	private String tendersOid;
	/**
	 * 管线oid
	 */
	private String pipelineOid;
	/**
	 * 线路段oid
	 */
	private String pipeSegmentOid;
	
	/**
	 * 冷弯管编号
	 */
	private String pipeColdBendingCode;
	
	/***
	 * 是否使用
	 */
	private String isUse;
	
	@Override
	public String getSql() {
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		String sql = "select t.oid,p.project_name,l.pipeline_name,s.tenders_name,t.oid,t.project_oid,t.tenders_oid,t.pipeline_oid,t.pipe_segment_oid,t.pipe_code,t.certificate_num,t.pipe_cold_bending_code,t.pipe_bending_standards,t.bending_radius,t.bending_angle,t.curve_length,t.straight_pipe_length,t.pipe_length,t.ellipticity,t.wall_thickness_redurate,t.pipe_diameter,t.wall_thickness,t.produce_date,t.construct_unit,t.supervision_unit,t.supervision_engineer,t.collection_person,t.collection_date,t.remarks,case when t.is_use=1 then '是' else '否' end as is_use,v.name as pipe_segment_name"
				+ ",t.create_user_id,t.create_user_name,t.create_datetime,t.modify_user_id,t.modify_user_name,t.modify_datetime,u.unit_name as construct_unit_name,uu.unit_name as supervision_unit_name "
				+ "from daq_material_pipe_cold_bending t "
				+ "left join (select oid,project_name from daq_project) p on p.oid=t.project_oid "
				+ "left join (select oid,pipeline_name from daq_pipeline) l on l.oid=t.pipeline_oid "
				+ "left join (select oid,tenders_name from daq_tenders) s on s.oid=t.tenders_oid "
				+ "left join v_daq_pipe_segment_cross v on v.oid = t.pipe_segment_oid "
				+ "left join(select oid,unit_name from pri_unit) u on t.construct_unit = u.oid "
				+ "left join(select oid,unit_name from pri_unit) uu on t.supervision_unit = uu.oid "
				+ "where t.active=1";
		if(StringUtils.isNotBlank(oid)){
			sql += " and t.oid = :oid";
		}else{
			if(StringUtils.isNotBlank(projectOid)){
				sql += " and t.project_oid = :projectOid";
			}
			if(StringUtils.isNotBlank(pipelineOid)){
				sql +=" and t.pipeline_oid = :pipelineOid";
			}
			if(StringUtils.isNotBlank(tendersOid)){
				sql += " and t.tenders_oid = :tendersOid";
			}
			if(StringUtils.isNotBlank(pipeSegmentOid)){
				sql += " and t.pipe_segment_oid = :pipeSegmentOid";
			}
			if(StringUtils.isNotBlank(pipeColdBendingCode)){
				sql +=" and t.pipe_cold_bending_code like :pipeColdBendingCode";
			}
		}
		sql += " and t.construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		sql += " order by t.create_datetime desc";
		return sql;
	}
	
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getProjectOid() {
		return projectOid;
	}
	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}
	public String getTendersOid() {
		return tendersOid;
	}
	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}
	public String getPipelineOid() {
		return pipelineOid;
	}
	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}
	public String getPipeSegmentOid() {
		return pipeSegmentOid;
	}
	public void setPipeSegmentOid(String pipeSegmentOid) {
		this.pipeSegmentOid = pipeSegmentOid;
	}

	public String getPipeColdBendingCode() {
		if(StringUtils.isNotBlank(pipeColdBendingCode)){
			return "%"+pipeColdBendingCode+"%";
		}
		return null;
	}

	public void setPipeColdBendingCode(String pipeColdBendingCode) {
		this.pipeColdBendingCode = pipeColdBendingCode;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}
}