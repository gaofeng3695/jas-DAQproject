package cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.dao.entity;

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
import cn.jasgroup.jasframework.base.annotation.assist.MergeType;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * @description 超声波主表
 * @author zhangyi
 * @date 2018年7月11日上午9:35:57
 * @version V1.0
 * @since JDK 1.80
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.service.DaqDetectionUltrasonicService #saveChanageWledStatus}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.service.DaqDetectionUltrasonicService #updateChanageWledStatusBeforeAdvice}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.service.DaqDetectionUltrasonicService #updateChanageWledStatus}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.service.DaqDetectionUltrasonicService #deleteChanageWledStatus}
 */

@CommonSaveConfig(
	scene = "/detectionUltrasonic/save",
	afterAdvice = {
		@Process(service = "daqDetectionUltrasonicService", method = "saveChanageWledStatus()")
	}
)
@CommonUpdateConfig(
	scene = "/detectionUltrasonic/update",
	beforeAdvice={
		@Process(service = "daqDetectionUltrasonicService", method = "updateChanageWledStatusBeforeAdvice()")
	},
	afterAdvice={
		@Process(service = "daqDetectionUltrasonicService", method = "updateChanageWledStatus()")
	}
)
@CommonDeleteConfig(
	scene = "/detectionUltrasonic/delete",
	afterAdvice = {
		@Process(service = "daqDetectionUltrasonicService", method = "deleteChanageWledStatus()")
	}
)
@CommonDeleteBatchConfig(scene = "/detectionUltrasonic/deleteBatch")
@CommonGetConfig(scene = "/detectionUltrasonic/get")
@JdbcEntity(name="daq_detection_ultrasonic")
public class DaqDetectionUltrasonic extends CommonJdbcEntity{

	/** 项目oid */
	private String projectOid; 

	/** 管线oid */
	private String pipelineOid; 

	/** 标段oid */
	private String tendersOid; 

	/** 线路段/穿跨越 */
	private String pipeSegmentOrCrossOid; 

	/**
	 * 焊口oid
	 */
	private String weldOid;

	/** 检测报告编号 */
	private String detectionReportNum; 

	/** 检测日期 */
	private Date detectionDeta; 

	/** 检测类型 */
	private String detectionType; 

	/** 评定等级 */
	private String evaluationGrade; 

	/** 评定结果 */
	private Integer evaluationResult; 

	/** 检测处置 */
	private String detectionDispose; 

	/** 检测长度(mm) */
	private Double detectionLength; 

	/** 检测单位 */
	private String detectionUnit; 

	/** 检测人员 */
	private String detectionPerson; 

	/** 监理单位 */
	private String supervisionUnit; 

	/** 监理工程师 */
	private String supervisionEngineer; 

	/** 采集日期 */
	private Date collectionDate; 

	/** 备注 */
	private String remarks;
	
	/** 子表集合 */
	private List<DaqDetectionUltrasonicSub> ultrasonicSubList = new ArrayList<DaqDetectionUltrasonicSub>();

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
		super.setField("projectOid");
	}

	public String getPipelineOid() {
		return pipelineOid; 
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
		super.setField("pipelineOid");
	}

	public String getTendersOid() {
		return tendersOid; 
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
		super.setField("tendersOid");
	}

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid; 
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
		super.setField("pipeSegmentOrCrossOid");
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
	public Date getDetectionDeta() {
		return detectionDeta; 
	}

	public void setDetectionDeta(Date detectionDeta) {
		this.detectionDeta = detectionDeta;
		super.setField("detectionDeta");
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

	public String getDetectionDispose() {
		return detectionDispose; 
	}

	public void setDetectionDispose(String detectionDispose) {
		this.detectionDispose = detectionDispose;
		super.setField("detectionDispose");
	}

	public Double getDetectionLength() {
		return detectionLength; 
	}

	public void setDetectionLength(Double detectionLength) {
		this.detectionLength = detectionLength; 
		super.setField("detectionLength");
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
	public List<DaqDetectionUltrasonicSub> getUltrasonicSubList() {
		return ultrasonicSubList;
	}

	public void setUltrasonicSubList(List<DaqDetectionUltrasonicSub> ultrasonicSubList) {
		this.ultrasonicSubList = ultrasonicSubList;
	}
	
}
