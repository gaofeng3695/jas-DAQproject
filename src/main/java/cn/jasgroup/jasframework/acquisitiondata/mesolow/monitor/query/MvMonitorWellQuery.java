package cn.jasgroup.jasframework.acquisitiondata.mesolow.monitor.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.monitor.dao.entity.MvMonitorWell;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：中低压监测井信息query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月25日 下午2:00:31。</p>
 */
@QueryConfig(
	scene ="/mvMonitorWell/getPage",
	resultClass= MvMonitorWell.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class MvMonitorWellQuery extends BaseJavaQuery {
	
	/**
	 * oid
	 */
	private String oid;

	/**
	 * 项目oid 
	 */
	private String projectOid; 

	/**
	 * 编号 
	 */
	private String monitorWellCode;

	/**
	 * oids
	 */
	private List<String> oids;
	
	@Override
	public String getQuerySql() {
		String sql = "SELECT mv.oid,mv.project_oid,mv.monitor_well_code,mv.monitor_well_purpose,mv.pointx,mv.pointy,mv.pointz,"
					+ "mv.monitor_well_material,mv.investment_date,mv.construct_unit,mv.collection_person,mv.collection_date,mv.supervision_unit,"
					+ "mv.supervision_engineer,mv.geo_state,mv.approve_status,mv.remarks,mv.create_user_id,mv.create_user_name,mv.create_datetime,"
					+ "mv.modify_user_id,mv.modify_user_name,mv.modify_datetime,mv.active,mv.geom,pu.unit_name as supervision_unit_name,"
					+ "case when mv.approve_status = -1 then '驳回' when mv.approve_status = 1 then '待审核' when mv.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name,"
					+ "u.unit_name as construct_unit_name,pro.project_name "
					+ "FROM daq_mv_monitor_well mv LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = mv.project_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = mv.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = mv.construct_unit "
					+ "WHERE 1 = 1 AND mv.active = 1";
		sql += conditionSql();
		return sql;
	}
	
	private String conditionSql() {
		String conditionSql= "";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and mv.oid=:oid";
		}else{
			if (oids != null && oids.size() > 0) {
				conditionSql += "mvel.oid in (:oids)";
			}
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and mv.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(monitorWellCode)) {
				conditionSql += " and mv.monitor_well_code like :monitorWellCode";
			}
			conditionSql +=  this.dataAuthoritySql;
		}
		conditionSql += " order by mv.create_datetime desc";
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

	public String getMonitorWellCode() {
		if (StringUtils.isNotBlank(monitorWellCode)) {
			return "%"+monitorWellCode+"%";
		}
		return null;
	}

	public void setMonitorWellCode(String monitorWellCode) {
		this.monitorWellCode = monitorWellCode;
	}

	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
	}
	
}
