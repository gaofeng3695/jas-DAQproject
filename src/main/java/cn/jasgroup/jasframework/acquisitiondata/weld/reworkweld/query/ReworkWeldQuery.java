package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query.bo.ReworkWeldBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：焊口返修分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月10日 上午8:56:41。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 */
@QueryConfig(scene = "/reworkWeld/getPage", 
			 resultClass = ReworkWeldBo.class,
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			 }
)
public class ReworkWeldQuery extends BaseJavaQuery {
	
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
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid;
	
	/**
	 * 返修口编号
	 */
	private String weldOid;
	
	/**
	 * 审核状态
	 */
	private String approveStatus;
	
	/**
	 * 施工单位
	 */
	private String constructUnit;

	@Override
	public String getQuerySql() {
		String sql ="SELECT wrw.oid,wrw.project_oid,pro.project_name, wrw.pipeline_oid,pi.pipeline_name,wrw.tenders_oid,te.tenders_name,"
					+ "wrw.pipe_segment_or_cross_oid,vpsc.name as pipe_segment_or_cross_name,wrw.weld_oid, cw.weld_code, wrw.rework_weld_code, "
					+ "wrw.weld_rod_batch_num,wrw.weld_wire_batch_num, wrw.weld_produce, wps.weld_produce_code, wrw.cover_oid, wp.personnel_name as cover_name, "
					+ "wrw.padder_oid, wpe.personnel_name as padder_name,wrw.render_oid, wper.personnel_name as render_name, wrw.weld_date,"
					+ "wrw.construct_unit, u.unit_name as construct_unit_name,wrw.work_unit_oid, wu.work_unit_name, wrw.supervision_unit,"
					+ "pu.unit_name as supervision_unit_name, wrw.supervision_engineer,wrw.collection_person,wrw.collection_date, wrw.approve_status, wrw.remarks,"
					+ "wrw.create_user_id,wrw.create_user_name,wrw.create_datetime,wrw.modify_user_id,wrw.modify_user_name,	wrw.modify_datetime,wrw.active"
					+ " FROM daq_weld_rework_weld wrw "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wrw.project_oid  "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wrw.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wrw.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wrw.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, weld_code, active from daq_construction_weld where active=1) cw ON cw.oid = wrw.weld_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wrw.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wrw.construct_unit "
					+ "LEFT JOIN (select oid, work_unit_name, active from daq_work_unit where active=1) wu ON wu.oid = wrw.work_unit_oid "
					+ "LEFT JOIN (SELECT oid, weld_produce_code, active FROM daq_weld_produce_specification where active=1) wps ON wps.oid = wrw.weld_produce "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wp ON wp.oid = wrw.cover_oid "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wpe ON wpe.oid = wrw.padder_oid "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wper ON wper.oid = wrw.render_oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_weld_rework_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.cover_oid like '%'||wpe.oid||'%' group by t.oid) wp ON wp.oid = wrw.oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_weld_rework_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.padder_oid like '%'||wpe.oid||'%' group by t.oid) wpe ON wpe.oid = wrw.oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_weld_rework_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.render_oid like '%'||wpe.oid||'%' group by t.oid) wper ON wper.oid = wrw.oid "
					+ "WHERE wrw.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and wrw.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and wrw.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and wrw.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and wrw.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOrCrossOid)) {
				conditionSql += " and wrw.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid";
			}
			if (StringUtils.isNotBlank(weldOid)) {
				conditionSql += " and wrw.weld_oid = :weldOid";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and wrw.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql +=  this.dataAuthoritySql;
		}
		conditionSql += " order by wrw.create_datetime desc";
		return conditionSql;
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

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}

}
