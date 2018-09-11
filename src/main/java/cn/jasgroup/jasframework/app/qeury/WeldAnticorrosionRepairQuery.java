package cn.jasgroup.jasframework.app.qeury;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：防腐补伤信息query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月3日 下午2:31:23。</p>
 */
@QueryConfig(scene = "/weldAnticorrosionRepair/getPage",
	queryBeforeProcess = {
		 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class WeldAnticorrosionRepairQuery extends BaseJavaQuery {
	
	private String approveStatus;
	private String constructUnit;

	@Override
	public String getQuerySql() {
		String sql = "SELECT war.oid, war.project_oid, pro.project_name, war.pipeline_oid, pi.pipeline_name, war.tenders_oid, te.tenders_name, "
				+ "war.pipe_segment_or_cross_oid, vpsc.name as pipe_segment_or_cross_name, war.weld_oid, cw.weld_code, war.relative_mileage, "
				+ "war.anticorrosion_position, war.anticorrosion_describe, war.anticorrosion_method,d.code_name, war.detection_voltage,"
				+ "to_char(war.construction_date, 'YYYY-MM-DD') as construction_date, war.construction_person, war.construct_unit, "
				+ "u.unit_name as construct_unit_name, war.supervision_unit, pu.unit_name as supervision_unit_name, war.supervision_engineer, "
				+ "war.collection_person,to_char(war.collection_date, 'YYYY-MM-DD') as collection_date,"
				+ "case when war.approve_status = -1 then '驳回' when war.approve_status = 1 then '待审核' when war.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name,"
				+ "war.approve_status as \"approveStatus\", war.remarks, war.create_user_id, war.create_user_name, to_char(war.create_datetime, 'YYYY-MM-DD') as create_datetime, "
				+ "war.modify_user_id, war.modify_user_name, to_char(war.modify_datetime, 'YYYY-MM-DD') as modify_datetime, war.active "
				+ "FROM daq_weld_anticorrosion_repair war "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = war.project_oid "
				+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = war.pipeline_oid "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = war.tenders_oid "
				+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = war.pipe_segment_or_cross_oid "
				+ "LEFT JOIN (select oid, weld_code, active from daq_construction_weld where active=1) cw ON cw.oid = war.weld_oid "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = war.supervision_unit "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = war.construct_unit "
				+ "LEFT JOIN (select code_id,code_name, active from sys_domain where active=1) d ON d.code_id = war.anticorrosion_method "
				+ "WHERE war.active = 1 ";
		sql += conditionSql();
		return sql;
	}

	private String conditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(approveStatus)) {
			conditionSql = " and war.approve_status in ("+ approveStatus +")";
		}
		if (StringUtils.isNotBlank(constructUnit)) {
			conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
		}
		conditionSql += this.dataAuthoritySql;
		conditionSql += " order by war.create_datetime desc";
		return conditionSql;
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
