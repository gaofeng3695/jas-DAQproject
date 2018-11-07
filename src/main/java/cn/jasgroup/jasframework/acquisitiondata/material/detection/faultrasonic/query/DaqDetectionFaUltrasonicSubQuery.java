package cn.jasgroup.jasframework.acquisitiondata.material.detection.faultrasonic.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.faultrasonic.query.bo.DaqDetectionFaUltrasonicSubBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 全自动超声波检测子表
 * @author zhangyi
 * @date 2018年7月12日上午10:21:29
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene = "/detectionFaUltrasonicSub/getPage", 
	resultClass = DaqDetectionFaUltrasonicSubBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqDetectionFaUltrasonicSubQuery extends BaseJavaQuery {

	private List<String> oids;
	
	private String parentOid;

	@Override
	public String getQuerySql() {
		String sql = "select t.*,"
				+ " vdwi.weld_code "
				+ " from daq_detection_fa_ultrasonic_sub t "
				+ " left join (select oid,weld_code from v_daq_weld_info) vdwi on vdwi.oid=t.weld_oid "
				+ " where t.active = 1";
		if(StringUtils.isNotBlank(parentOid)){
			sql += " and t.parent_oid = :parentOid ";
		}
		if (null != oids && oids.size() > 0) {
			sql += " and t.oid in (:oids) ";
		}
		sql +=" order by t.create_datetime desc";
		return sql;
	}

	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
	}

	public String getParentOid() {
		return parentOid;
	}

	public void setParentOid(String parentOid) {
		this.parentOid = parentOid;
	}
}
