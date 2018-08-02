package cn.jasgroup.jasframework.acquisitiondata.material.check.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo.CheckPipeColdBendingBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：冷弯管检查分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月6日 下午5:39:07。</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 */
@QueryConfig(
		scene = "/checkPipeColdBending/getPage", 
		resultClass = CheckPipeColdBendingBo.class,
		queryBeforeProcess = {
				 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			}
	)
public class CheckPipeColdBendingQuery extends BaseJavaQuery {
	
	/**
	 * 唯一标识
	 */
	private String oid;

	/**
	 *  项目oid 
	 */
	private String projectOid; 

	/**
	 *  管线oid
	 */
	private String constructUnit; 

	/**
	 *  标段oid
	 */
	private String tendersOid; 

	/**
	 * 冷弯管oid
	 */
	private String pipeColdBendingOid;
	
	@Override
	public String getQuerySql() {
		String sql ="select cpcb.*, pro.project_name, pi.unit_name,te.tenders_name,cp.pipe_cold_bending_code from daq_check_pipe_cold_bending cpcb "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cpcb.project_oid "
				+ "LEFT JOIN (SELECT oid, unit_name, active FROM pri_unit where active=1) pi ON pi.oid = cpcb.construct_unit "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cpcb.tenders_oid "
				+ "left join (select oid, pipe_cold_bending_code from daq_material_pipe_cold_bending ) cp on cp.oid = cpcb.pipe_cold_bending_oid "
				+ "where cpcb.active=1 ";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and cpcb.oid = :oid";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and cpcb.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and cpcb.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and cpcb.construct_unit = :constructUnit";
			}
			if (StringUtils.isNotBlank(pipeColdBendingOid)) {
				conditionSql += " and pipe_cold_bending_oid = :pipeColdBendingOid";
			}
		}
		conditionSql += this.dataAuthoritySql;
		conditionSql += " order by  cpcb.create_datetime desc";
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

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getPipeColdBendingOid() {
		return pipeColdBendingOid;
	}

	public void setPipeColdBendingOid(String pipeColdBendingOid) {
		this.pipeColdBendingOid = pipeColdBendingOid;
	}

}
