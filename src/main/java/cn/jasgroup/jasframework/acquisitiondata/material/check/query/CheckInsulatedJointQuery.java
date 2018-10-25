package cn.jasgroup.jasframework.acquisitiondata.material.check.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo.CheckInsulatedJointBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.base.annotation.Process;

/**
 * 
  *<p>类描述：绝缘接头检查分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月6日 下午5:38:47。</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 */
@QueryConfig(
		scene = "/checkInsulatedJoint/getPage", 
		resultClass = CheckInsulatedJointBo.class,
		queryBeforeProcess = {
			 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
		}
	)
public class CheckInsulatedJointQuery extends BaseJavaQuery {
	
	/**
	 * oid 集合（用于导出选中）
	 */
	private List<String> oids;
	
	/**
	 * 唯一标识
	 */
	private String oid;

	/**
	 *  项目oid 
	 */
	private String projectOid; 

	/**
	 *  监工单位oid
	 */
	private String constructUnit; 

	/**
	 *  标段oid
	 */
	private String tendersOid; 

	/**
	 * 出厂编号 
	 */
	private String manufacturerCode; 
	
	@Override
	public String getQuerySql() {
		String sql ="select cij.*,pro.project_name, pi.unit_name,te.tenders_name,mij.manufacturer_code as manufacturer_code_name from daq_check_insulated_joint cij "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cij.project_oid "
				+ "LEFT JOIN (SELECT oid, unit_name, active FROM pri_unit where active=1) pi ON pi.oid = cij.construct_unit "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cij.tenders_oid "
				+ "LEFT JOIN (select oid,manufacturer_code from daq_material_insulated_joint where active=1) mij ON mij.oid=cij.manufacturer_code "
				+ "where cij.active=1 ";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and cij.oid = :oid";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and cij.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and cij.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and cij.construct_unit = :constructUnit";
			}
			if (StringUtils.isNotBlank(manufacturerCode)) {
				conditionSql += " and cij.manufacturer_code = :manufacturerCode";
			}
			if (null != oids && oids.size() > 0) {
				conditionSql += " and cij.oid in (:oids)";
			}
		}
		conditionSql += this.dataAuthoritySql;
		conditionSql += " order by  cij.create_datetime desc";
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

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
	}

}
