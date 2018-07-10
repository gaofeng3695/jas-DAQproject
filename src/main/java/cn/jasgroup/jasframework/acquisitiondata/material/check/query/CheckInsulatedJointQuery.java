package cn.jasgroup.jasframework.acquisitiondata.material.check.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo.CheckInsulatedJointBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：绝缘接头检查分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月6日 下午5:38:47。</p>
 */
@QueryConfig(scene = "/checkInsulatedJoint/getPage", resultClass = CheckInsulatedJointBo.class)
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
