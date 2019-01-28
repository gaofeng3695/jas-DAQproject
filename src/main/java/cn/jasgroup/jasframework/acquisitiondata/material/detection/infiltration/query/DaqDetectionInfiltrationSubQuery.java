package cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query.bo.DaqDetectionInfiltrationSubBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 查询渗透检测子表query
 * @author zhangyi
 * @date 2018年7月11日上午11:15:52
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene ="/detectionInfiltrationSub/getPage",
	resultClass= DaqDetectionInfiltrationSubBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}	
)
public class DaqDetectionInfiltrationSubQuery extends BaseJavaQuery{

	private List<String> oids;
	
	private String parentOid;

	@Override
	public String getQuerySql() {
		String sql = "select t.*,d.code_name as defect_properties_name,"
				+ " vdwi.weld_code "		
				+ " from daq_detection_infiltration_sub t "
				+ " left join sys_domain d on d.code_id = t.defect_properties"
				+ " left join (select oid,weld_code from v_daq_weld_info) vdwi on vdwi.oid=t.weld_oid "	
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
