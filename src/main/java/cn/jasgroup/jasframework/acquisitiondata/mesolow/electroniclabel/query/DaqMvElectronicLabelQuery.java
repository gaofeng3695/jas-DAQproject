package cn.jasgroup.jasframework.acquisitiondata.mesolow.electroniclabel.query;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.electroniclabel.dao.entity.DaqMvElectronicLabel;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * <p>功能描述：电子标签信息Query</p>
 * @author 陈祥思
 * @param
 * @return
 * @since JDK1.8
 * <p>创建日期:  2019/1/25 13:52</p>
 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
 **/

@QueryConfig(
	scene ="/mvElectronicLabel/getPage",
	resultClass= DaqMvElectronicLabel.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqMvElectronicLabelQuery extends BaseJavaQuery {
	
	/**
	 * oid
	 */
	private String oid;

	/**
	 * 项目oid 
	 */
	private String projectOid; 
	
	/**
	 * oids
	 */
	private List<String> oids;

	@Override
	public String getQuerySql() {
		String sql = "SELECT mvel.*,pro.project_name,pu.unit_name as supervision_unit_name, u.unit_name as construct_unit_name, " +
				     "case when mvel.approve_status = -1 then '驳回' when mvel.approve_status = 1 then '待审核' when mvel.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name " +
					 "FROM daq_mv_electronic_label mvel " +
					 "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = mvel.project_oid " +
				  	 "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = mvel.supervision_unit " +
					 "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = mvel.construct_unit " +
					 "WHERE 1 = 1 AND mvel.active = 1";
		sql += conditionSql();
		return sql;
	}

	private String conditionSql() {
		String conditionSql= "";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and mvel.oid=:oid";
		}else{
			if (oids != null && oids.size() > 0) {
				conditionSql += " and mvel.oid in (:oids)";
			}
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and mvel.project_oid = :projectOid";
			}
			conditionSql +=  this.dataAuthoritySql;
		}
		conditionSql += " order by mvel.create_datetime desc";
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

	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
	}

}
