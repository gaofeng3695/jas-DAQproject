package cn.jasgroup.jasframework.acquisitiondata.material.base.closure.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.base.closure.query.bo.ClosureBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：封堵物分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月6日 下午5:38:09。</p>
 */
@QueryConfig(scene = "/closure/getPage", resultClass = ClosureBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class ClosureQuery extends BaseJavaQuery {
	
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
	 * 封堵物编号
	 */
	private String closureCode;
	
	/**
	 * 封堵物类型
	 */
	private String closureType;
	
	/***
	 * 是否使用
	 */
	private Integer isUse;

	@Override
	public String getQuerySql() {
		String sql = "SELECT mc.commit_status, mc.oid, mc.closure_code, mc.closure_type, mc.material, mc.steel_grade,d.code_name as steel_grade_name, mc.outside_diameter, "
						+ "mc.wall_thickness, mc.connection_methods,dm.code_name as connection_methods_name, mc.coating_methods,dom.code_name as coating_methods_name, "
						+ "mc.manufacturer, mc.manufacturer_date, mc.project_oid, pro.project_name, mc.pipeline_oid, pi.pipeline_name, mc.tenders_oid, te.tenders_name, "
						+ "mc.is_use, mc.remarks, "
						+ "mc.create_user_id, mc.create_user_name, mc.create_datetime, mc.modify_user_id, mc.modify_user_name, mc.modify_datetime, "
						+ "mc.active  FROM daq_material_closure mc "
						+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = mc.project_oid "
						+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = mc.pipeline_oid "
						+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = mc.tenders_oid "
						+ "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where active=1) d ON d.code_id = mc.steel_grade "
						+ "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where active=1) dm ON dm.code_id = mc.connection_methods "
						+ "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where active=1) dom ON dom.code_id = mc.coating_methods "
						+ "WHERE 1 = 1 AND mc.active = 1";
		sql += getConditionSql();
		return sql;
	}

	public String getConditionSql(){
		String conditionSql = "";
		if(StringUtils.isNotBlank(oid)){
			conditionSql += " and mc.oid = :oid";
		}else{
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and mc.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and mc.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and mc.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(closureCode)) {
				conditionSql += " and mc.closure_code like :closureCode";
			}
			if (StringUtils.isNotBlank(closureType)) {
				conditionSql += " and mc.closure_type = :closureType";
			}
			if ( isUse != null ){
				conditionSql += " and mc.is_use = :isUse";
			}
			conditionSql +=  this.dataAuthoritySql;
		}
		conditionSql += " order by mc.create_datetime desc";
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

	public String getClosureCode() {
		if (StringUtils.isNotBlank(closureCode)) {
			return "%"+closureCode+"%";
		}
		return null;
	}

	public void setClosureCode(String closureCode) {
		this.closureCode = closureCode;
	}

	public String getClosureType() {
		return closureType;
	}

	public void setClosureType(String closureType) {
		this.closureType = closureType;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
