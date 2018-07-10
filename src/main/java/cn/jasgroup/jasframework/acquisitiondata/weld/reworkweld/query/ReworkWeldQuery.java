package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query.bo.ReworkWeldBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：焊口返修分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月10日 上午8:56:41。</p>
 */
@QueryConfig(scene = "/reworkWeld/getPage", resultClass = ReworkWeldBo.class)
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

	@Override
	public String getQuerySql() {
		String sql ="SELECT wrw.oid,wrw.project_oid,pro.project_name, wrw.pipeline_oid,pi.pipeline_name,wrw.tenders_oid,te.tenders_name,"
					+ "wrw.pipe_segment_or_cross_oid,vpsc.name,wrw.weld_oid,wrw.rework_weld_code, wrw.weld_rod_batch_num,wrw.weld_wire_batch_num,"
					+ "wrw.weld_produce,wrw.cover_oid,wrw.padder_oid,wrw.render_oid,wrw.weld_date,wrw.construct_unit,wrw.work_unit_oid,wrw.supervision_unit,"
					+ "wrw.supervision_engineer,wrw.collection_person,wrw.collection_date,wrw.remarks,"
					+ "wrw.create_user_id,wrw.create_user_name,wrw.create_datetime,wrw.modify_user_id,wrw.modify_user_name,	wrw.modify_datetime,wrw.active"
					+ " FROM daq_weld_rework_weld wrw "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wrw.project_oid  "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wrw.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wrw.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wrw.pipe_segment_or_cross_oid "
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
				conditionSql += " and wrw.weldOid = :weldOid";
			}
			conditionSql += " order by wrw.create_datetime desc";
		}
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

}
