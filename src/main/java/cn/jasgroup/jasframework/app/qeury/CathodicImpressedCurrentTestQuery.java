package cn.jasgroup.jasframework.app.qeury;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：外加电流电参数测试信息记录query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月3日 下午2:31:23。</p>
 */
@QueryConfig(scene = "/cathodicImpressedCurrentTest/getPage",
	queryBeforeProcess = {
		 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class CathodicImpressedCurrentTestQuery extends BaseJavaQuery {
	
	private String approveStatus;
	private String constructUnit;

	@Override
	public String getQuerySql() {
		String sql = "SELECT cict.project_oid,cict.tenders_oid,dq.project_name,dt.tenders_name,cict.oid, cict.test_stake_oid, cts.test_stake_code,"
				+ "cict.test_region , to_char(cict.test_date, 'YYYY-MM-DD') as test_date, to_char(cict.currency_date, 'YYYY-MM-DD') as currency_date,  "
				+ "to_char(cict.polarization_date, 'YYYY-MM-DD') as polarization_date,  cict.natural_potential, cict.earthing_resistance, "
				+ "cict.direct_current_voltage, cict.standard_voltage, cict.measured_voltage, cict.standard_current, cict.measured_current,"
				+ "case when cict.approve_status = -1 then '驳回' when cict.approve_status = 1 then '待审核' when cict.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name,"
				+ "cict.approve_status as \"approveStatus\", cict.remarks, cict.create_user_id, cict.create_user_name, to_char(cict.create_datetime, 'YYYY-MM-DD') as create_datetime, "
				+ "cict.modify_user_id, cict.modify_user_name, to_char(cict.modify_datetime, 'YYYY-MM-DD') as modify_datetime, cict.active, cict.construct_unit, pu.unit_name,"
				+ "su.unit_name as supervision_unit_name,cict.supervision_engineer "
				+ "FROM daq_cathodic_impressed_current_test cict "
				+ "LEFT JOIN (select oid, test_stake_code, active from daq_cathodic_test_stake where active=1) cts ON cts.oid = cict.test_stake_oid "
				+ "left join (select oid,unit_name from pri_unit) pu on pu.oid=cict.construct_unit "
				+ "left join (select oid,unit_name from pri_unit) su on su.oid=cict.supervision_unit "
				+ "left join (select oid,project_name from daq_project) dq on dq.oid=cict.project_oid "
				+ "left join (select oid,tenders_name from daq_tenders) dt on dt.oid=cict.tenders_oid "
				+ "WHERE cict.active = 1 ";
		sql += conditionSql();
		return sql;
	}

	private String conditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(approveStatus)) {
			conditionSql = " and cict.approve_status in ("+ approveStatus +")";
		}
		if (StringUtils.isNotBlank(constructUnit)) {
			conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
		}
		conditionSql += this.dataAuthoritySql;
		conditionSql += " order by cict.create_datetime desc";
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
