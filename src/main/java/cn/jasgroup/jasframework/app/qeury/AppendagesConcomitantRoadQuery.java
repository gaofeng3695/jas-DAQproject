package cn.jasgroup.jasframework.app.qeury;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：伴行道路query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月3日 下午2:31:23。</p>
 */
@QueryConfig(scene = "/appendagesConcomitantRoad/getPage",
	queryBeforeProcess = {
		 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class AppendagesConcomitantRoadQuery extends BaseJavaQuery {
	
	private String approveStatus;
	private String constructUnit;

	@Override
	public String getQuerySql() {
		String sql = "SELECT t.oid,t.project_oid,t.tenders_oid,t.pipeline_oid,t.concomitant_road_code,t.concomitant_road_name,"
				+ "t.concomitant_road_length,t.pavement_type,t.road_width,to_char(t.commence_date,'YYYY-MM-DD') as commence_date,"
				+ "to_char(t.completion_date,'YYYY-MM-DD') as completion_date,t.construct_unit,t.supervision_unit,t.supervision_engineer,"
				+ "t.collection_person,to_char(t.collection_date,'YYYY-MM-DD') as collection_date,t.geo_state,t.approve_status as \"approveStatus\","
				+ "t.remarks, t.create_user_id,t.create_user_name,to_char(t.create_datetime, 'YYYY-MM-DD') as create_datetime,t.modify_user_id,"
				+ "t.modify_user_name,to_char(t.modify_datetime, 'YYYY-MM-DD') as modify_datetime,t.active,"
				+ "sd.code_name as pavement_type_name,dp.project_name,dt.tenders_name,dl.pipeline_name,cu.unit_name as construct_unit_name,"
				+ "su.unit_name as supervision_unit_name,"
				+ "case when t.approve_status = -1 then '驳回' when t.approve_status = 1 then '待审核' when t.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name "
				+ "FROM daq_appendages_concomitant_road t "
				+ "left join (select code_id,code_name from sys_domain) sd on sd.code_id=t.pavement_type "
				+ "left join (select oid,project_name from daq_project) dp on dp.oid = t.project_oid "
				+ "left join (select oid,tenders_name from daq_tenders) dt on dt.oid=t.tenders_oid "
				+ "left join (select oid,pipeline_name from daq_pipeline) dl on dl.oid=t.pipeline_oid "
				+ "left join (select oid,unit_name from pri_unit) cu on cu.oid=t.construct_unit "
				+ "left join (select oid,unit_name from pri_unit) su on su.oid=t.supervision_unit "
				+ "WHERE 1 = 1  AND t.active = 1  ";
		sql += conditionSql();
		return sql;
	}

	private String conditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(approveStatus)) {
			conditionSql = " and t.approve_status in ("+ approveStatus +")";
		}
//		if (StringUtils.isNotBlank(constructUnit)) {
//			conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
//		}
		conditionSql += this.dataAuthoritySql;
		conditionSql += "  order by t.create_datetime desc";
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
