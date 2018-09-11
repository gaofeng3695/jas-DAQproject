package cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.query.bo.WeldCutBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/***
 * 
 * <p>
 * 类描述：焊口割口query。
 * </p>
 * 
 * @author 雷凯 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 *        <p>
 * 		创建日期：2018年8月21日 下午5:42:50。
 *        </p>
 */
@QueryConfig(scene = "/weldCut/getPage", resultClass = WeldCutBo.class, queryBeforeProcess = {
		@Process(service = "daqInjectService", method = "injectDataAuthoritySql(dataAuthoritySql)") })
public class WeldCutQuery extends BaseJavaQuery {
	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid;

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
		String sql = "SELECT wc.oid,wc.project_oid,pro.project_name,wc.pipeline_oid,pi.pipeline_name,wc.tenders_oid,te.tenders_name,wc.pipe_segment_or_cross_oid,vpsc.name as pipe_segment_or_cross_name, wc.weld_oid, "
				+ "wc.front_weld_oid, wc.back_weld_oid,w.weld_code as weld_code,bw.weld_code as back_weld_code,fw.weld_code as front_weld_code, wc.cut_weld_date,"
				+ "wc.construct_unit,u.unit_name as construct_unit_name,wc.work_unit_oid, wu.work_unit_code, wc.supervision_unit,pu.unit_name as supervision_unit_name, wc.supervision_engineer, wc.collection_person, "
				+ "wc.collection_date,"
				// + "case when wc.approve_status = -1 then '驳回' when
				// wc.approve_status = 1 then '待审核' when wc.approve_status = 2
				// then '审核通过' else '未上报' end as approve_status_name,"
				+ "wc.remarks,wc.create_user_id,wc.create_user_name, wc.create_datetime, wc.modify_user_id,wc.modify_user_name, wc.modify_datetime,wc.active,wc.approve_status FROM daq_weld_cut wc "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid "
				+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wc.pipeline_oid "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wc.tenders_oid "
				+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wc.pipe_segment_or_cross_oid "
				+ "LEFT JOIN (select oid, work_unit_code, active from daq_work_unit where active=1) wu ON wu.oid = wc.work_unit_oid "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.construct_unit "
				+ "left join (select oid,weld_code from daq_construction_weld where active=1 and approve_status=2 union all select oid,rework_weld_code as weld_code from daq_weld_rework_weld where active=1 and approve_status=2) w on w.oid=wc.weld_oid "
				+ "left join(select oid,weld_code from daq_construction_weld where active=1 and approve_status=2 union all select oid,rework_weld_code as weld_code from daq_weld_rework_weld where active=1 and approve_status=2) bw on bw.oid=wc.back_weld_oid left "
				+ "join(select oid,weld_code from daq_construction_weld where active=1 and approve_status=2 union all select oid,rework_weld_code as weld_code from daq_weld_rework_weld where active=1 and approve_status=2) fw on fw.oid=wc.front_weld_oid "
				+ "WHERE wc.active = 1 ";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and wc.oid = :oid ";
		} else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and wc.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and wc.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and wc.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOrCrossOid)) {
				conditionSql += " and wc.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and wc.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by wc.create_datetime desc";
		return conditionSql;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
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
