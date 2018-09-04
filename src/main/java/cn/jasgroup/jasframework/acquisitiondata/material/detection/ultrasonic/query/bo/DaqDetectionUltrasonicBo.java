package cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.dao.entity.DaqDetectionUltrasonicSub;
import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 超声波检测Bo
 * @author zhangyi
 * @date 2018年7月11日上午9:58:17
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqDetectionUltrasonicBo extends CommonBaseBo{
	
	/** 数据oid */
	private String oid;

	/** 项目oid */
	private String projectOid;
	
	/** 项目名称 */
	private String projectName;

	/** 管线oid */
	private String pipelineOid;
	
	/** 管线名称 */
	private String pipelineName;

	/** 标段oid */
	private String tendersOid;
	
	/** 标段名称 */
	private String tendersName; 

	/** 线路段/穿跨越 */
	private String pipeSegmentOrCrossOid;
	
	/** 线路段/穿跨越 名称*/
	private String pipeSegmentOrCrossName;

	/**
	 * 焊口oid
	 */
	private String weldOid;
	
	/** 焊口编号 */
	private String weldCode; 

	/** 检测报告编号 */
	private String detectionReportNum; 

	/** 检测日期 */
	private Date detectionDeta; 

	/** 检测类型 */
	private String detectionType;
	
	/** 检测类型 阈值名称*/
	private String detectionTypeName;

	/** 评定等级 */
	private String evaluationGrade;
	
	/** 评定等级阈值名称 */
	private String evaluationGradeName;

	/** 评定结果 */
	private Integer evaluationResult; 

	/** 检测处置 */
	private String detectionDispose; 

	/** 检测长度(mm) */
	private Double detectionLength; 

	/** 检测单位 */
	private String detectionUnit;
	
	/** 检测单位名称 */
	private String detectionUnitName; 

	/** 检测人员 */
	private String detectionPerson; 

	/** 监理单位 */
	private String supervisionUnit;
	
	/** 监理单位名称 */
	private String supervisionUnitName; 

	/** 监理工程师 */
	private String supervisionEngineer; 

	/** 采集日期 */
	private Date collectionDate; 

	/** 审核状态 */
	private Integer approveStatus; 

	/** 备注 */
	private String remarks;
	
	/** 子表集合 */
	private List<DaqDetectionUltrasonicSub> ultrasonicSubList = new ArrayList<DaqDetectionUltrasonicSub>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getPipelineName() {
		return pipelineName;
	}

	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getTendersName() {
		return tendersName;
	}

	public void setTendersName(String tendersName) {
		this.tendersName = tendersName;
	}

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
	}

	public String getWeldCode() {
		return weldCode;
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
	}

	public String getDetectionReportNum() {
		return detectionReportNum;
	}

	public void setDetectionReportNum(String detectionReportNum) {
		this.detectionReportNum = detectionReportNum;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getDetectionDeta() {
		return detectionDeta;
	}

	public void setDetectionDeta(Date detectionDeta) {
		this.detectionDeta = detectionDeta;
	}

	public String getDetectionType() {
		return detectionType;
	}

	public void setDetectionType(String detectionType) {
		this.detectionType = detectionType;
	}

	public String getDetectionTypeName() {
		return detectionTypeName;
	}

	public void setDetectionTypeName(String detectionTypeName) {
		this.detectionTypeName = detectionTypeName;
	}

	public String getEvaluationGrade() {
		return evaluationGrade;
	}

	public void setEvaluationGrade(String evaluationGrade) {
		this.evaluationGrade = evaluationGrade;
	}

	public String getEvaluationGradeName() {
		return evaluationGradeName;
	}

	public void setEvaluationGradeName(String evaluationGradeName) {
		this.evaluationGradeName = evaluationGradeName;
	}

	public Integer getEvaluationResult() {
		return evaluationResult;
	}

	public void setEvaluationResult(Integer evaluationResult) {
		this.evaluationResult = evaluationResult;
	}

	public String getDetectionDispose() {
		return detectionDispose;
	}

	public void setDetectionDispose(String detectionDispose) {
		this.detectionDispose = detectionDispose;
	}

	public Double getDetectionLength() {
		return detectionLength;
	}

	public void setDetectionLength(Double detectionLength) {
		this.detectionLength = detectionLength;
	}

	public String getDetectionUnit() {
		return detectionUnit;
	}

	public void setDetectionUnit(String detectionUnit) {
		this.detectionUnit = detectionUnit;
	}

	public String getDetectionPerson() {
		return detectionPerson;
	}

	public void setDetectionPerson(String detectionPerson) {
		this.detectionPerson = detectionPerson;
	}

	public String getSupervisionUnit() {
		return supervisionUnit;
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit;
	}

	public String getSupervisionEngineer() {
		return supervisionEngineer;
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPipeSegmentOrCrossName() {
		return pipeSegmentOrCrossName;
	}

	public void setPipeSegmentOrCrossName(String pipeSegmentOrCrossName) {
		this.pipeSegmentOrCrossName = pipeSegmentOrCrossName;
	}

	public List<DaqDetectionUltrasonicSub> getUltrasonicSubList() {
		return ultrasonicSubList;
	}

	public void setUltrasonicSubList(List<DaqDetectionUltrasonicSub> ultrasonicSubList) {
		this.ultrasonicSubList = ultrasonicSubList;
	}

	public String getDetectionUnitName() {
		return detectionUnitName;
	}

	public void setDetectionUnitName(String detectionUnitName) {
		this.detectionUnitName = detectionUnitName;
	}

	public String getSupervisionUnitName() {
		return supervisionUnitName;
	}

	public void setSupervisionUnitName(String supervisionUnitName) {
		this.supervisionUnitName = supervisionUnitName;
	}
	
}
