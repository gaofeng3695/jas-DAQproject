package cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.query;

import java.util.List;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.query.bo.DaqDetectionRaySubBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 查询检测数据子表数据page
 * @author zhangyi
 * @date 2018年7月10日下午5:21:16
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
		scene ="/detectionRaySub/getPage",
		resultClass= DaqDetectionRaySubBo.class,
		queryBeforeProcess = {
			@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
		}
)
public class DaqDetectionRaySubQuery extends BaseJavaQuery{

	private List<String> oids;

	@Override
	public String getQuerySql() {
		String sql = "select t.*,d.code_name as defect_properties_name,"
				+ " vdwi.weld_code "	
				+ " from daq_detection_ray_sub t "
				+ " left join sys_domain d on d.code_id = t.defect_properties"
				+ " left join (select oid,weld_code from v_daq_weld_info) vdwi on vdwi.oid=t.weld_oid "	
				+ " where t.active = 1";
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
}
