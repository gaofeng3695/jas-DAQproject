package cn.jasgroup.jasframework.app.qeury;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(scene = "/weldAnticorrosionTest/getPage",
	queryBeforeProcess = {
		 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class WeldAnticorrosionTestQuery extends BaseJavaQuery {
	
	private String approveStatus;

	@Override
	public String getQuerySql() {
		String sql = "SELECT wat.oid, wat.project_oid, pro.project_name, wat.pipeline_oid, pi.pipeline_name, wat.tenders_oid, te.tenders_name, "
					+ "wat.pipe_segment_or_cross_oid, vpsc.name as pipe_segment_or_cross_name, wat.weld_oid, cw.weld_code, wat.relative_mileage, "
					+ "wat.separate_method, wat.pe_strength, wat.light_peel_strength, wat.peel_welds_strength, wat.test_result, "
					+ "to_char(wat.test_date, 'YYYY-MM-DD') as test_date, wat.construct_unit, u.unit_name as construct_unit_name, "
					+ "wat.supervision_unit, pu.unit_name as supervision_unit_name, wat.supervision_engineer, wat.collection_person, "
					+ "to_char(wat.collection_date, 'YYYY-MM-DD') as collection_date,"
					+ "case when wat.approve_status = -1 then '驳回' when wat.approve_status = 1 then '待审核' when wat.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name,"
					+ "wat.approve_status as \"approveStatus\", wat.remarks, wat.create_user_id, wat.create_user_name, wat.create_datetime, wat.modify_user_id, "
					+ "wat.modify_user_name, wat.modify_datetime, wat.active "
					+ "FROM daq_weld_anticorrosion_test wat "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wat.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wat.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wat.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wat.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, weld_code from v_daq_weld_info) cw ON cw.oid = wat.weld_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wat.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wat.construct_unit "
					+ "WHERE wat.active = 1";
		sql += conditionSql();
		return sql;
	}

	private String conditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(approveStatus)) {
			conditionSql = " and wat.approve_status in ("+ approveStatus +")";
		}
		conditionSql += this.dataAuthoritySql;
		conditionSql += " order by wat.create_datetime desc";
		return conditionSql;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}
	
}
