package cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query.bo.DaqDetectionUltrasonicBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 超声波主表查询类
 * @author zhangyi
 * @date 2018年7月11日上午9:57:24
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene="/detectionUltrasonic/getPage",
	resultClass=DaqDetectionUltrasonicBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqDetectionUltrasonicQuery extends BaseJavaQuery{

	private List<String> oids;
	
	private String projectOid;
	private String pipelineOid;
	private String tendersOid;
	private String pipeSegmentOrCrossOid;	
	private String weldOid;
	private String approveStatus;
	
	/**
	 * 检测单位oid
	 */
	private String constructUnit;
	
	@Override
	public String getQuerySql() {
		String sql = "select t.*,d1.code_name as detection_type_name,"
				+ " d2.code_name as evaluation_grade_name,"
				+ "	p.project_name,"
				+ "	l.pipeline_name,"
				+ "	dt.tenders_name,"
				+ "	v.name as pipe_segment_or_cross_name,"
				+ "	u1.unit_name as detection_unit_name,"
				+ "	u2.unit_name as supervision_unit_name,"
				+ " vdwi.weld_code "				
				+ " from (select *,detection_unit as construct_unit from daq_detection_ultrasonic) t "
				+ " left join (select code_id,code_name from sys_domain) d1 on d1.code_id = t.detection_type"
				+ " left join (select code_id,code_name from sys_domain) d2 on d2.code_id = t.evaluation_grade"
				+ " left join (select oid,project_name from daq_project) p on p.oid=t.project_oid "
				+ " left join (select oid,pipeline_name from daq_pipeline) l on l.oid=t.pipeline_oid "
				+ " left join (select oid,tenders_name from daq_tenders) dt on dt.oid=t.tenders_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.pipe_segment_or_cross_oid "
				+ " left join (select oid,unit_name from pri_unit) u1 on u1.oid=t.detection_unit "
				+ " left join (select oid,unit_name from pri_unit) u2 on u2.oid=t.supervision_unit "	
				+ " left join (select oid,weld_code from v_daq_weld_info) vdwi on vdwi.oid=t.weld_oid "				
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
		if(StringUtils.isNotBlank(weldOid)){
			sql += " and t.weld_oid = :weldOid ";
		}
		if (null != oids && oids.size() > 0) {
			sql += " and t.oid in (:oids) ";
		}
		if (StringUtils.isNotBlank(approveStatus)) {
			sql += " and t.approve_status in ("+ approveStatus +")";
		}	
		if (StringUtils.isNotBlank(constructUnit)) {
			sql += " and t.detection_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
		}
		sql += this.dataAuthoritySql;
		sql +=" order by t.create_datetime desc";
		return sql;
	}

	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
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

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
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

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}
	
}
