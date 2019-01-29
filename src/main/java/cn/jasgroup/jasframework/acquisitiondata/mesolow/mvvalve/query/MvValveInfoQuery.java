package cn.jasgroup.jasframework.acquisitiondata.mesolow.mvvalve.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.mvvalve.dao.entity.MvValveInfo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：中低压。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月25日 上午10:46:06。</p>
 */
@QueryConfig(
	scene ="/mvValveInfo/getPage",
	resultClass= MvValveInfo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class MvValveInfoQuery extends BaseJavaQuery {
	
	/**
	 * oid
	 */
	private String oid;

	/**
	 * 项目oid 
	 */
	private String projectOid; 

	/**
	 * 节点oid 
	 */
	private String pipeNodeOid; 

	/**
	 * 阀门编号 
	 */
	private String valveCode; 


	@Override
	public String getQuerySql() {
		String sql = "SELECT mv.oid,mv.project_oid,mv.pipe_node_oid,mv.valve_code,mv.manufacturer,mv.valve_spec,mv.valve_type,mv.valve_material,"
					+ "mv.burial_method,mv.nominal_diameter,mv.nominal_pressure,mv.pointx,mv.pointy,mv.pointz,mv.buried_depth,mv.is_electronic_label,"
					+ "mv.electronic_label_type,mv.valve_connection_method,mv.is_heavy_shaft_cover,mv.well_cover_type,mv.well_cover_material,"
					+ "mv.discharge_pipe,mv.valve_discharge_pipe_info,mv.construct_unit,mv.collection_person,mv.collection_date,mv.supervision_unit,"
					+ "mv.supervision_engineer,mv.geo_state,mv.approve_status,mv.remarks,mv.create_user_id,mv.create_user_name,mv.create_datetime,"
					+ "mv.modify_user_id,mv.modify_user_name,mv.modify_datetime,mv.active,mv.geom,pro.project_name,node.pipe_node_code,"
					+ "pu.unit_name as supervision_unit_name,u.unit_name as construct_unit_name,"
					+ "case when mv.approve_status = -1 then '驳回' when mv.approve_status = 1 then '待审核' when mv.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name,"
					+ "case when mv.valve_material = 1 then '钢管' when mv.valve_material = 2 then 'PE管' else '' end as valve_material_name,"
					+ "case when mv.burial_method = 1 then '直埋' when mv.burial_method = 2 then '阀门井' else '' end as burial_method_name,"
					+ "case when mv.is_electronic_label = 0 then '否' when mv.is_electronic_label = 1 then '是' else '' end as is_electronic_label_name,"
					+ "case when mv.is_heavy_shaft_cover = 0 then '否' when mv.is_heavy_shaft_cover = 1 then '是' else '' end as is_heavy_shaft_cover_name,"
					+ "case when mv.well_cover_type = 1 then '矩形' when mv.well_cover_type = 2 then '圆形' else '' end as well_cover_type_name "
					+ "FROM daq_mv_valve_info mv "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = mv.project_oid "
					+ "LEFT JOIN (select oid,pipe_node_code from daq_mv_pipe_node where active=1) node on node.oid=mv.pipe_node_oid "
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
		if (StringUtils.isNotBlank(projectOid)) {
			conditionSql += " and mv.project_oid = :projectOid";
		}
		if (StringUtils.isNotBlank(pipeNodeOid)) {
			conditionSql += " and mv.pipe_node_oid = :pipeNodeOid";
		}
		if (StringUtils.isNotBlank(valveCode)) {
			conditionSql += " and mv.valve_code like :valveCode";
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


	public String getPipeNodeOid() {
		return pipeNodeOid;
	}


	public void setPipeNodeOid(String pipeNodeOid) {
		this.pipeNodeOid = pipeNodeOid;
	}


	public String getValveCode() {
		if (StringUtils.isNotBlank(valveCode)) {
			return "%"+valveCode+"%";
		}
		return null;
	}


	public void setValveCode(String valveCode) {
		this.valveCode = valveCode;
	}

}
