package cn.jasgroup.jasframework.app.qeury;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：防腐补口检查信息记录query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月3日 下午2:31:23。</p>
 */
@QueryConfig(scene = "/weldAnticorrosionCheck/getPage",
	queryBeforeProcess = {
		 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class WeldAnticorrosionCheckQuery extends BaseJavaQuery {
	
	private String approveStatus;
	private String constructUnit;

	@Override
	public String getQuerySql() {
		String sql = "SELECT wac.oid, wac.project_oid, pro.project_name, wac.pipeline_oid, pi.pipeline_name, wac.tenders_oid, te.tenders_name, "
				+ "wac.pipe_segment_or_cross_oid, vpsc.name as pipe_segment_or_cross_name, wac.weld_oid, cw.weld_code, "
				+ "to_char(wac.buckle_date, 'YYYY-MM-DD') as buckle_date, wac.buckle_anticorrosive_type, d.code_name as buckle_anticorrosive_type_name, "
				+ "wac.buckle_anticorrosive_grade, dm.code_name as buckle_anticorrosive_grade_name, wac.buckle_material_batch_num, "
				+ "wac.derusting_grade, dom.code_name as derusting_grade_name, wac.pipe_mouth_clean, wac.sandblasting_and_derusting, "
				+ "wac.pipe_mouth_preheat, wac.epoxy_primer, wac.baking_check, wac.overlap_check, wac.appearance_check, wac.electric_spark_leak_detection, "
				+ "wac.buckle_conclusion,case when wac.buckle_conclusion=1 then '合格' when wac.buckle_conclusion=0 then '不合格' end as buckle_conclusion_name, "
				+ "wac.anticorrosion, wac.construct_unit, u.unit_name as construct_unit_name, wac.supervision_unit, pu.unit_name as supervision_unit_name, "
				+ "wac.supervision_engineer, wac.collection_person, to_char(wac.collection_date, 'YYYY-MM-DD') as collection_date,"
				+ "case when wac.approve_status = -1 then '驳回' when wac.approve_status = 1 then '待审核' when wac.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name, "
				+ "wac.remarks, wac.create_user_id, wac.create_user_name, to_char(wac.create_datetime, 'YYYY-MM-DD') as create_datetime, wac.modify_user_id, wac.modify_user_name, "
				+ "to_char(wac.modify_datetime, 'YYYY-MM-DD') as modify_datetime, "
				+ "wac.active,case when wac.pipe_mouth_clean=1 then '合格' when wac.pipe_mouth_clean=0 then '不合格' end as pipe_mouth_clean_name,"
				+ "case when wac.sandblasting_and_derusting=1 then '合格' when wac.sandblasting_and_derusting=0 then '不合格' end as sandblasting_and_derusting_name,"
				+ "case when wac.pipe_mouth_preheat=1 then '合格' when wac.pipe_mouth_preheat=0 then '不合格' end as pipe_mouth_preheat_name,"
				+ "case when wac.epoxy_primer=1 then '合格' when wac.epoxy_primer=0 then '不合格' end as epoxy_primer_name,"
				+ "case when wac.baking_check=1 then '合格' when wac.baking_check=0 then '不合格' end as baking_check_name,"
				+ "case when wac.overlap_check=1 then '合格' when wac.overlap_check=0 then '不合格' end as overlap_check_name,"
				+ "case when wac.appearance_check=1 then '合格' when wac.appearance_check=0 then '不合格' end as appearance_check_name,"
				+ "case when wac.electric_spark_leak_detection=1 then '合格' when wac.electric_spark_leak_detection=0 then '不合格' end as electric_spark_leak_detection_name,"
				+ "wac.approve_status as \"approveStatus\" "
				+ "FROM daq_weld_anticorrosion_check wac "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wac.project_oid "
				+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wac.pipeline_oid "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wac.tenders_oid "
				+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wac.pipe_segment_or_cross_oid "
				+ "LEFT JOIN (select oid, weld_code from v_daq_weld_info) cw ON cw.oid = wac.weld_oid "
				+ "LEFT JOIN (select code_id,code_name, active from sys_domain where active=1) d ON d.code_id = wac.buckle_anticorrosive_type "
				+ "LEFT JOIN (select code_id,code_name, active from sys_domain where active=1) dm ON dm.code_id = wac.buckle_anticorrosive_grade "
				+ "LEFT JOIN (select code_id,code_name, active from sys_domain where active=1) dom ON dom.code_id = wac.derusting_grade "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wac.construct_unit "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wac.supervision_unit "
				+ "WHERE wac.active = 1 ";
		sql += conditionSql();
		return sql;
	}

	private String conditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(approveStatus)) {
			conditionSql = " and wac.approve_status in ("+ approveStatus +")";
		}
		if (StringUtils.isNotBlank(constructUnit)) {
			conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
		}
		conditionSql += this.dataAuthoritySql;
		conditionSql += "  order by wac.create_datetime desc ";
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
