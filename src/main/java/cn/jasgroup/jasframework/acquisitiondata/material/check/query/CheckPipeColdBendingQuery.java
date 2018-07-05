package cn.jasgroup.jasframework.acquisitiondata.material.check.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.check.dao.entity.CheckPipeColdBending;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(scene = "/checkPipeColdBending/getPage", resultClass = CheckPipeColdBending.class)
public class CheckPipeColdBendingQuery extends BaseJavaQuery {
	/**
	 * 唯一标识
	 */
	private String oid;
	/**
	 * 冷弯管编号
	 */
	private String pipeColdBendingCode;
	
	@Override
	public String getQuerySql() {
		String sql ="select * from daq_check_pipe_cold_bending cpcb where cpcb.active=1 ";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql = "";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and oid = :oid";
		}else {
			if (StringUtils.isNotBlank(pipeColdBendingCode)) {
				conditionSql += " and pipe_cold_bending_code = :pipeColdBendingCode";
			}
			conditionSql += " order by  cpcb.create_datetime desc";
		}
		return conditionSql;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPipeColdBendingCode() {
		return pipeColdBendingCode;
	}

	public void setPipeColdBendingCode(String pipeColdBendingCode) {
		this.pipeColdBendingCode = pipeColdBendingCode;
	}

}
