package cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.query.bo.WeldMeasuredResultBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：焊口测量成果信息分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月11日 下午4:44:18。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 */
@QueryConfig(scene = "/weldMeasuredResult/getPage", 
			 resultClass = WeldMeasuredResultBo.class,
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			 }
)
public class WeldMeasuredResultQuery extends BaseJavaQuery{
	
	/**
	 * oid
	 */
	private String oid;

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
	 * 焊口编号 
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
		String sql ="SELECT wmr.*,pro.project_name, pi.pipeline_name, te.tenders_name, vpsc.name as pipe_segment_or_cross_name, cw.weld_code, "
					+ "ms.median_stake_code, u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, wu.work_unit_name "
					+ "FROM daq_weld_measured_result wmr "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wmr.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wmr.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wmr.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wmr.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, weld_code, active from daq_construction_weld where active=1) cw ON cw.oid = wmr.weld_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = wmr.median_stake_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wmr.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wmr.construct_unit "
					+ "LEFT JOIN (select oid, work_unit_name, active from daq_work_unit where active=1) wu ON wu.oid = wmr.work_unit_oid "
					+ "WHERE wmr.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and wmr.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and wmr.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and wmr.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and wmr.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOrCrossOid)) {
				conditionSql += " and wmr.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid";
			}
			if (StringUtils.isNotBlank(weldOid)) {
				conditionSql += " and wmr.weld_oid = :weldOid";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and wmr.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by wmr.create_datetime desc";
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
