package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.query.bo.CutWeldBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：钢管切管分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月10日 上午8:58:19。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 */
@QueryConfig(scene = "/cutWeld/getPage", 
			 resultClass = CutWeldBo.class,
			 queryBeforeProcess = {
			 	 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			 }
)
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
		String sql = "SELECT cp.*,	pro.project_name,	pi.pipeline_name,	te.tenders_name, mp.pipe_code"
					+ ",cu.unit_name as construct_unit_name, su.unit_name as supervision_unit_name "
					+ "FROM daq_cut_pipe cp "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cp.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = cp.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cp.tenders_oid "
					+ "LEFT JOIN (select oid, pipe_code, active from daq_material_pipe where active=1) mp on mp.oid = cp.pipe_oid "
					+ "left join (select oid, unit_name, active from pri_unit where active=1) cu on cu.oid=cp.construct_unit "
					+ "left join (select oid, unit_name, active from pri_unit where active=1) su on su.oid = cp.supervision_unit "
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
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and cp.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by cp.create_datetime desc";
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
