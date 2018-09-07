package cn.jasgroup.jasframework.acquisitiondata.lay.excavation.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.lay.excavation.query.bo.LayPipeTrenchExcavationBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：管沟开挖分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月17日 上午10:43:09。</p>
 */
@QueryConfig(scene = "/layPipeTrenchExcavation/getPage", 
			 resultClass = LayPipeTrenchExcavationBo.class,
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			 }
)
public class LayPipeTrenchExcavationQuery extends BaseJavaQuery{

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
	private String approveStatus;
		
	/**
	* 施工单位
	*/		
	private String constructUnit;

	@Override
	public String getQuerySql() {
		String sql = "SELECT pro.project_name, pi.pipeline_name, te.tenders_name, vpsc.name as pipe_segment_or_cross_name,"
					+ "ms.median_stake_code AS start_median_stake_name,dms.median_stake_code as end_median_stake_name,"
					+ "u.unit_name as construct_unit_name,lpte.*, pu.unit_name as supervision_unit_name "
					+ "FROM daq_lay_pipe_trench_excavation lpte "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = lpte.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = lpte.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = lpte.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = lpte.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = lpte.start_median_stake_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) dms ON dms.oid = lpte.end_median_stake_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = lpte.construct_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = lpte.supervision_unit "
					+ "WHERE lpte.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and lpte.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and lpte.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and lpte.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and lpte.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOrCrossOid)) {
				conditionSql += " and lpte.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and lpte.approve_status in ("+ approveStatus +")";
			}	
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by lpte.create_datetime desc";
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
