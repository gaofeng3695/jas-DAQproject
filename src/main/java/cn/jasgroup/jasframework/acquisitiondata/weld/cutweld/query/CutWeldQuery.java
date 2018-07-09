package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.query.bo.CutWeldBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(scene = "/cutWeld/getPage", resultClass = CutWeldBo.class)
public class CutWeldQuery extends BaseJavaQuery{
	
	/**
	 * oid
	 */
	private String oid; 

	/**
	 *  项目oid 
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
	 * 钢管编号
	 */
	private String pipeOid;

	@Override
	public String getQuerySql() {
		String sql = "SELECT cp.*,	pro.project_name,	pi.pipeline_name,	te.tenders_name FROM daq_cut_pipe cp "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cp.project_oid "
				+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = cp.pipeline_oid "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cp.tenders_oid  "
				+ "WHERE cp.active = 1 ";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql = "";
		if(StringUtils.isNotBlank(oid)){
			conditionSql += " and cp.oid = :oid";
		}else{
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and cp.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and cp.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and cp.pipeline_oid = :pipelineOid";
			}		
			if (StringUtils.isNotBlank(pipeOid)) {
				conditionSql += " and cp.pipe_oid = :pipeOid";
			}
			conditionSql += " order by cp.create_datetime desc";
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

	public String getPipeOid() {
		return pipeOid;
	}

	public void setPipeOid(String pipeOid) {
		this.pipeOid = pipeOid;
	} 
	
}