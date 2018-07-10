package cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.query;

import java.util.List;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.query.bo.DaqDetectionRayBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 射线检测主表query
 * @author zhangyi
 * @date 2018年7月10日上午11:07:55
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene = "/detectionRay/getPage", 
	resultClass = DaqDetectionRayBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqDetectionRayQuery extends BaseJavaQuery {

	public List<String> oids;

	@Override
	public String getSql() {
		String sql = "select t.*,d1.code_name as detectionTypeName,"
				+ " d2.code_name as evaluationGradeName,"
				+ "	p.project_name,"
				+ "	l.pipeline_name"
				+ " from daq_detection_ray t "
				+ " left join sys_domain d1 on d1.code_id = t.detection_type"
				+ " left join sys_domain d2 on d2.code_id = t.evaluation_grade"
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
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
