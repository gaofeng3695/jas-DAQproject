package cn.jasgroup.jasframework.acquisitiondata.lay.Insulation.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.lay.Insulation.query.bo.LayThermalInsulationBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：保温分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月17日 上午10:42:27。</p>
 */
@QueryConfig(scene = "/layThermalInsulation/getPage", 
			 resultClass = LayThermalInsulationBo.class,
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			 }
)
public class LayThermalInsulationQuery extends BaseJavaQuery{

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
	 * 审核状态
	 */
	private Integer approveStatus;

	@Override
	public String getQuerySql() {
		String sql = "SELECT pro.project_name, pi.pipeline_name, te.tenders_name, vpsc.name as pipe_segment_or_cross_name,"
					+ "ms.median_stake_code AS start_median_stake_name,dms.median_stake_code as end_median_stake_name,"
					+ "u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, d.code_name as insulation_material_name, "
					+ "dm.code_name as protective_tape_name, lti.* FROM daq_lay_thermal_insulation lti "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = lti.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = lti.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = lti.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = lti.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = lti.start_median_stake_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) dms ON dms.oid = lti.end_median_stake_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = lti.construct_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = lti.supervision_unit "
					+ "LEFT JOIN (select code_id, code_name, active from sys_domain where active=1) d on d.code_id = lti.insulation_material "
					+ "LEFT JOIN (select code_id, code_name, active from sys_domain where active=1) dm on dm.code_id = lti.protective_tape "
					+ "WHERE lti.active = 1"; 
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and lti.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and lti.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and lti.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and lti.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOrCrossOid)) {
				conditionSql += " and lti.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid";
			}
			if (approveStatus != null) {
				conditionSql += " and lti.approve_status = :approveStatus";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by lti.create_datetime desc";
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

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	
}
