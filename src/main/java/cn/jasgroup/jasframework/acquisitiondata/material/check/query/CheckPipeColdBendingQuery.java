package cn.jasgroup.jasframework.acquisitiondata.material.check.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo.CheckPipeColdBendingBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：冷弯管检查分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月6日 下午5:39:07。</p>
 */
@QueryConfig(scene = "/checkPipeColdBending/getPage", resultClass = CheckPipeColdBendingBo.class)
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
	private String constructionUnit; 

	/**
	 *  标段oid
	 */
	private String tendersOid; 

	/**
	 * 冷弯管编号
	 */
	private String pipeColdBendingCode;
	
	@Override
	public String getQuerySql() {
		String sql ="select cpcb.*, pro.project_name, pi.unit_name,te.tenders_name from daq_check_pipe_cold_bending cpcb "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cpcb.project_oid "
				+ "LEFT JOIN (SELECT oid, unit_name, active FROM pri_unit where active=1) pi ON pi.oid = cpcb.construction_unit "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cpcb.tenders_oid "
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
			if (StringUtils.isNotBlank(constructionUnit)) {
				conditionSql += " and cpcb.construction_unit = :constructionUnit";
			}
			if (StringUtils.isNotBlank(pipeColdBendingCode)) {
				conditionSql += " and pipe_cold_bending_code like :pipeColdBendingCode";
			}
			conditionSql += " order by  cpcb.create_datetime desc";
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

	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getPipeColdBendingCode() {
		if (StringUtils.isNotBlank(pipeColdBendingCode)) {
			return "%"+pipeColdBendingCode+"%";
		}
		return null;
	}

	public void setPipeColdBendingCode(String pipeColdBendingCode) {
		this.pipeColdBendingCode = pipeColdBendingCode;
	}

}
