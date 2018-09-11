package cn.jasgroup.jasframework.app.qeury;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：牺牲阳极电参数测试信息记录query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月3日 下午2:31:23。</p>
 */
@QueryConfig(scene = "/cathodicElectricalParameterTest/getPage",
	queryBeforeProcess = {
		 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class CathodicElectricalParameterTestQuery extends BaseJavaQuery {
	
	private String approveStatus;
	private String constructUnit;

	@Override
	public String getQuerySql() {
		String sql = "SELECT cept.oid, cept.test_stake_oid, cts.test_stake_code, cept.natural_potential, cept.open_circuit_potential_one, "
					+ "cept.open_circuit_potential_two, cept.protective_potential, cept.group_output_current, cept.output_current_one, "
					+ "cept.output_current_two, cept.earthing_resistance_one, cept.earthing_resistance_two, cept.test_person, "
					+ "to_char(cept.test_date, 'YYYY-MM-DD') as test_date,"
					+ "case when cept.approve_status = -1 then '驳回' when cept.approve_status = 1 then '待审核' when cept.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name,"
					+ "cept.approve_status as \"approveStatus\", cept.remarks, cept.create_user_id, cept.create_user_name, to_char(cept.create_datetime, 'YYYY-MM-DD') as create_datetime, "
					+ "cept.modify_user_id, cept.modify_user_name, to_char(cept.modify_datetime, 'YYYY-MM-DD') as modify_datetime, cept.active,pu.unit_name as construct_unit_name,cept.construct_unit,"
					+ "su.unit_name as supervision_unit_name,cept.supervision_engineer,cept.supervision_unit,cept.project_oid,cept.tenders_oid,"
					+ "dp.project_name,dt.tenders_name "
					+ "FROM daq_cathodic_electrical_parameter_test cept "
					+ "LEFT JOIN (select oid, test_stake_code, active from daq_cathodic_test_stake where active=1) cts ON cts.oid = cept.test_stake_oid "
					+ "left join (select oid,unit_name from pri_unit) pu on pu.oid=cept.construct_unit "
					+ "left join (select oid,unit_name from pri_unit) su on su.oid=cept.supervision_unit "
					+ "left join (select oid,project_name from daq_project where active=1) dp on dp.oid=cept.project_oid "
					+ "left join (select oid,tenders_name from daq_tenders where active=1) dt on dt.oid=cept.tenders_oid "
					+ "WHERE cept.active = 1 ";
		sql += conditionSql();
		return sql;
	}

	private String conditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(approveStatus)) {
			conditionSql = " and cept.approve_status in ("+ approveStatus +")";
		}
		if (StringUtils.isNotBlank(constructUnit)) {
			conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
		}
		conditionSql += this.dataAuthoritySql;
		conditionSql += "  order by cept.create_datetime desc";
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
