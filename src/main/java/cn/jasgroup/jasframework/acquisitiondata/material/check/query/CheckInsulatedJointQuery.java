package cn.jasgroup.jasframework.acquisitiondata.material.check.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.check.dao.entity.CheckInsulatedJoint;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(scene = "/checkInsulatedJoint/getPage", resultClass = CheckInsulatedJoint.class)
public class CheckInsulatedJointQuery extends BaseJavaQuery {
	/**
	 * 唯一标识
	 */
	private String oid;
	/**
	 * 出厂编号 
	 */
	private String manufacturerCode; 
	
	@Override
	public String getQuerySql() {
		String sql ="select * from daq_check_insulated_joint cij where cij.active=1 ";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and oid = :oid";
		}else {
			if (StringUtils.isNotBlank(manufacturerCode)) {
				conditionSql += " and manufacturer_code = :manufacturerCode";
			}
			conditionSql += " order by  cij.create_datetime desc";
		}
		return conditionSql;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

}
