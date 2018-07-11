package cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query.bo.DaqDetectionUltrasonicSubBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 超声波子表
 * @author zhangyi
 * @date 2018年7月11日上午10:11:57
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene ="/detectionUltrasonicSub/getPage",
	resultClass= DaqDetectionUltrasonicSubBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}	
)
public class DaqDetectionUltrasonicSubQuery extends BaseJavaQuery{

	private List<String> oids;
	
	private String parentOid;

	@Override
	public String getSql() {
		String sql = "select t.*,d.code_name as defectPropertiesName"
				+ " from daq_detection_ray_sub t "
				+ " left join sys_domain d on d.code_id = t.defect_properties"
				+ " where t.active = 1";
		if(StringUtils.isNotBlank(parentOid)){
			sql += " and parent_oid = :parentOid ";
		}
		if (null != oids && oids.size() > 0) {
			sql += " and oid in (:oids) ";
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
