package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query.bo.ConstructionWeldBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：焊口信息分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月11日 下午4:44:59。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 */
@QueryConfig(scene = "/constructionWeld/getPage",
			 resultClass = ConstructionWeldBo.class,
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			 }
)
public class ConstructionWeldQuery extends BaseJavaQuery{

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
	private String weldCode;

	@Override
	public String getQuerySql() {
		String sql = "SELECT cw.*,pro.project_code, pi.pipeline_code, te.tenders_code, vpsc.name as pipe_segment_or_cross_name, ms.median_stake_code,"
					+ " u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, wu.work_unit_code, d.code_name as weld_type_name,wps.weld_produce_code, "
					+ "dm.code_name as weld_method_name,wp.personnel_name as cover_name, wpe.personnel_name as padder_name, wper.personnel_name as render_name "
					+ "FROM daq_construction_weld cw "
					+ "LEFT JOIN (SELECT oid, project_code, active FROM daq_project where active=1) pro ON pro.oid = cw.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_code, active FROM daq_pipeline where active=1) pi ON pi.oid = cw.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_code, active FROM daq_tenders where active=1) te ON te.oid = cw.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = cw.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = cw.median_stake_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = cw.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = cw.construct_unit "
					+ "LEFT JOIN (select oid, work_unit_code, active from daq_work_unit where active=1) wu ON wu.oid = cw.work_unit_oid "
					+ "LEFT JOIN (select code_id, code_name,active from sys_domain where active=1) d ON d.code_id = cw.weld_type "
					+ "LEFT JOIN (select code_id, code_name,active from sys_domain where active=1) dm ON dm.code_id = cw.weld_method "
					+ "LEFT JOIN (SELECT oid, weld_produce_code, active FROM daq_weld_produce_specification where active=1) wps ON wps.oid = cw.weld_produce "
					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wp ON wp.oid = cw.cover_oid "
					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wpe ON wpe.oid = cw.padder_oid "
					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wper ON wper.oid = cw.render_oid "
					+ "WHERE cw.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and cw.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and cw.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and cw.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and cw.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOrCrossOid)) {
				conditionSql += " and cw.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid";
			}
			if (StringUtils.isNotBlank(weldCode)) {
				conditionSql += " and cw.weld_code like :weldCode";
			}
			conditionSql += " order by cw.create_datetime desc";
		}
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

	public String getWeldCode() {
		if (StringUtils.isNotBlank(weldCode)) {
			return "%"+weldCode+"%";
		}
		return null;
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
	}

}
