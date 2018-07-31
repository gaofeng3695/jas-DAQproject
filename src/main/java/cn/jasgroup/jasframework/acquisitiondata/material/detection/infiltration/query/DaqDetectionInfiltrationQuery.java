package cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query.bo.DaqDetectionInfiltrationBo;
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
	resultClass=DaqDetectionInfiltrationBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}	
)
public class DaqDetectionInfiltrationQuery extends BaseJavaQuery{

	private List<String> oids;
	
	private String projectOid;
	private String pipelineOid;
	private String tendersOid;
	private String pipeSegmentOrCrossOid;
	private String weldCode;
	
	@Override
	public String getSql() {
		String sql = "select t.*,"
				+ "	p.project_name,"
				+ "	l.pipeline_name,"
				+ "	dt.tenders_name,"
				+ "	v.name as pipeSegmentOrCrossName,"
				+ "	u1.unit_name as detectionUnitName,"
				+ "	u2.unit_name as supervisionUnitName"				
				+ " from (select *,detection_unit as construct_unit from daq_detection_infiltration) t "
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ " left join daq_tenders dt on dt.oid=t.tenders_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.pipe_segment_or_cross_oid "
				+ " left join pri_unit u1 on u1.oid=t.detection_unit "
				+ " left join pri_unit u2 on u2.oid=t.supervision_unit "				
				+ " where t.active = 1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and t.project_oid = :projectOid ";
		}
		if(StringUtils.isNotBlank(pipelineOid)){
			sql += " and t.pipeline_oid = :pipelineOid ";
		}
		if(StringUtils.isNotBlank(tendersOid)){
			sql += " and t.tenders_oid = :tendersOid ";
		}
		if(StringUtils.isNotBlank(pipeSegmentOrCrossOid)){
			sql += " and t.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid ";
		}
		if(StringUtils.isNotBlank(weldCode)){
			sql += " and t.weld_code like :weldCode ";
		}
		if (null != oids && oids.size() > 0) {
			sql += " and oid in (:oids) ";
		}
		sql += this.dataAuthoritySql;
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

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}
	
}
