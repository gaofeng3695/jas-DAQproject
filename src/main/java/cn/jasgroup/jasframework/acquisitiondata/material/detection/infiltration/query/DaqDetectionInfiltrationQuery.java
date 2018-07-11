package cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query.bo.DaqDetectionUltrasonicBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 渗透检测主表查询
 * @author zhangyi
 * @date 2018年7月11日上午11:16:02
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene="/detectionInfiltration/getPage",
	resultClass=DaqDetectionUltrasonicBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}	
)
public class DaqDetectionInfiltrationQuery extends BaseJavaQuery{

	private List<String> oids;
	
	private String projectOid;
	private String pipelineOid;
	private String weldCode;
	
	@Override
	public String getSql() {
		String sql = "select t.*,"
				+ "	p.project_name,"
				+ "	l.pipeline_name"
				+ " from daq_detection_infiltration t "
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ " where t.active = 1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and t.project_oid = :projectOid ";
		}
		if(StringUtils.isNotBlank(pipelineOid)){
			sql += " and t.pipeline_oid = :pipelineOid ";
		}
		if(StringUtils.isNotBlank(weldCode)){
			sql += " and t.weld_code like :weldCode ";
		}
		if (null != oids && oids.size() > 0) {
			sql += " and oid in (:oids) ";
		}
		sql +=" order by t.create_datetime desc";
		return sql;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getWeldCode() {
		if(StringUtils.isNotBlank(weldCode)){
			return "%"+weldCode+"%";
		}
		return null;
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
	}

	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
	}
	
}