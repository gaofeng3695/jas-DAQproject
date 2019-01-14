package cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.annotation.Cascade;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonGetConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.Merge;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.base.annotation.assist.MergeType;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：站场射线检测主表。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月14日 上午10:13:33。</p>
 */
@CommonSaveConfig(scene = "/stationDetectionRay/save")
@CommonUpdateConfig(scene = "/stationDetectionRay/update")
@CommonDeleteConfig(scene = "/stationDetectionRay/delete")
@JdbcEntity(name="daq_station_detection_ray")
@UniqueConstraints(
strategys ={
		@UniqueConstraintStrategy(
			columnNames={"pipeStationOid","weldOid"},
			name="同一线站场/阀室下焊口编号不能重复"
		)
	}
)
public class StationDetectionRay extends CommonJdbcEntity {

	/**
	 * 项目oid 
	 */
	private String projectOid; 

	/**
	 * 标段oid 
	 */
	private String tendersOid; 

	/**
	 * 管线oid 
	 */
	private String pipelineOid; 

	/**
	 * 站场/阀室编号 
	 */
	private String pipeStationOid; 

	/**
	 * 焊口编号 
	 */
	private String weldOid; 

	/**
	 * 检测报告编号 
	 */
	private String detectionReportNum; 

	/**
	 * 检测日期 
	 */
	private Date detectionDate; 

	/**
	 * 检测类型
	 */
	private String detectionType; 

	/**
	 * 评定等级
	 */
	private String evaluationGrade; 

	/**
	 * 评定结果{0:不合格;1:合格} 
	 */
	private Integer evaluationResult; 

	/**
	 * 底片编号 
	 */
	private String negativeNum; 

	/**
	 * 检测单位 
	 */
	private String detectionUnit; 

	/**
	 * 检测人员 
	 */
	private String detectionPerson; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnit; 

	/**
	 * 监理工程师 
	 */
	private String supervisionEngineer; 

	/**
	 * 采集日期 
	 */
	private Date collectionDate; 

	/**
	 * 备注 
	 */
	private String remarks; 
	
	/**
	 * 射线检测子表集合
	 */
	private List<StationDetectionRaySub> faUltrasonicSubList = new ArrayList<StationDetectionRaySub>();

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
		super.setField("projectOid");
	}

	public String getTendersOid() {
		return tendersOid; 
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid; 
		super.setField("tendersOid");
	}

	public String getPipelineOid() {
		return pipelineOid; 
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid; 
		super.setField("pipelineOid");
	}

	public String getPipeStationOid() {
		return pipeStationOid; 
	}

	public void setPipeStationOid(String pipeStationOid) {
		this.pipeStationOid = pipeStationOid; 
		super.setField("pipeStationOid");
	}

	public String getWeldOid() {
		return weldOid; 
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid; 
		super.setField("weldOid");
	}

	public String getDetectionReportNum() {
		return detectionReportNum; 
	}

	public void setDetectionReportNum(String detectionReportNum) {
		this.detectionReportNum = detectionReportNum; 
		super.setField("detectionReportNum");
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getDetectionDate() {
		return detectionDate; 
	}

	public void setDetectionDate(Date detectionDate) {
		this.detectionDate = detectionDate; 
		super.setField("detectionDate");
	}

	public String getDetectionType() {
		return detectionType; 
	}

	public void setDetectionType(String detectionType) {
		this.detectionType = detectionType; 
		super.setField("detectionType");
	}

	public String getEvaluationGrade() {
		return evaluationGrade; 
	}

	public void setEvaluationGrade(String evaluationGrade) {
		this.evaluationGrade = evaluationGrade; 
		super.setField("evaluationGrade");
	}

	public Integer getEvaluationResult() {
		return evaluationResult; 
	}

	public void setEvaluationResult(Integer evaluationResult) {
		this.evaluationResult = evaluationResult; 
		super.setField("evaluationResult");
	}

	public String getNegativeNum() {
		return negativeNum; 
	}

	public void setNegativeNum(String negativeNum) {
		this.negativeNum = negativeNum; 
		super.setField("negativeNum");
	}

	public String getDetectionUnit() {
		return detectionUnit; 
	}

	public void setDetectionUnit(String detectionUnit) {
		this.detectionUnit = detectionUnit; 
		super.setField("detectionUnit");
	}

	public String getDetectionPerson() {
		return detectionPerson; 
	}

	public void setDetectionPerson(String detectionPerson) {
		this.detectionPerson = detectionPerson; 
		super.setField("detectionPerson");
	}

	public String getSupervisionUnit() {
		return supervisionUnit; 
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit; 
		super.setField("supervisionUnit");
	}

	public String getSupervisionEngineer() {
		return supervisionEngineer; 
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer; 
		super.setField("supervisionEngineer");
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getCollectionDate() {
		return collectionDate; 
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate; 
		super.setField("collectionDate");
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}
	
	@OneToMany(mappedBy = "parentOid")
	@Cascade(value = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
	@Merge(mergeType = MergeType.OPERATION_FLAG)
	public List<StationDetectionRaySub> getFaUltrasonicSubList() {
		return faUltrasonicSubList;
	}

	public void setFaUltrasonicSubList(List<StationDetectionRaySub> faUltrasonicSubList) {
		this.faUltrasonicSubList = faUltrasonicSubList;
	}

}
