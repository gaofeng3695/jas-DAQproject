package cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.entity;

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
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.Merge;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.assist.MergeType;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>类描述：相控阵超声波检测主表。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月12日 上午10:56:31。</p>
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.service.DaqDetectionPaUltrasonicService #saveChanageWledStatus}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.service.DaqDetectionPaUltrasonicService #updateChanageWledStatusBeforeAdvice}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.service.DaqDetectionPaUltrasonicService #updateChanageWledStatus}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.service.DaqDetectionPaUltrasonicService #deleteChanageWledStatus}
 */

@CommonSaveConfig(
	scene = "/detectionPaUltrasonic/save",
	afterAdvice = {
		@Process(service = "daqDetectionPaUltrasonicService", method = "saveChanageWledStatus()")
	}
)
@CommonUpdateConfig(
	scene = "/detectionPaUltrasonic/update",
	beforeAdvice={
		@Process(service = "daqDetectionPaUltrasonicService", method = "updateChanageWledStatusBeforeAdvice()")
	},
	afterAdvice={
		@Process(service = "daqDetectionPaUltrasonicService", method = "updateChanageWledStatus()")
	}
)
@CommonDeleteConfig(
	scene = "/detectionPaUltrasonic/delete",
	afterAdvice = {
		@Process(service = "daqDetectionPaUltrasonicService", method = "deleteChanageWledStatus()")
	}
)
@CommonDeleteBatchConfig(scene = "/detectionPaUltrasonic/deleteBatch")
@JdbcEntity(name="daq_detection_pa_ultrasonic")
public class DaqDetectionPaUltrasonic extends CommonJdbcEntity {

	/**
	 * 项目oid 
	 */
	private String projectOid; 

	/**
	 * 管线oid 
	 */
	private String pipelineOid; 

	/**
	 * 标段oid 
	 */
	private String tendersOid; 

	/**
	 * 线路段/穿跨越 
	 */
	private String pipeSegmentOrCrossOid; 

	/**
	 * 焊口oid
	 */
	private String weldOid;

	/**
	 * 检测报告编号 
	 */
	private String detectionReportNum; 

	/**
	 * 检测文件编号 
	 */
	private String detectionFileNum; 

	/**
	 * 检测日期 
	 */
	private Date detectionDeta; 

	/**
	 * 检测类型 
	 */
	private String detectionType; 

	/**
	 * 检测处置 
	 */
	private String detectionDispose; 

	/**
	 * 评定结果 
	 */
	private Integer evaluationResult; 

	/**
	 * 检测单位 
	 */
	private String detectionUnit; 

	/**
	 * 审核人员 
	 */
	private String auditor; 

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
	 * 子表集合
	 */
	private List<DaqDetectionPaUltrasonicSub> paUltrasonicSubList = new ArrayList<DaqDetectionPaUltrasonicSub>();

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

	public String getDetectionFileNum() {
		return detectionFileNum; 
	}

	public void setDetectionFileNum(String detectionFileNum) {
		this.detectionFileNum = detectionFileNum; 
		super.setField("detectionFileNum");
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

	public String getDetectionDispose() {
		return detectionDispose; 
	}

	public void setDetectionDispose(String detectionDispose) {
		this.detectionDispose = detectionDispose; 
		super.setField("detectionDispose");
	}

	public Integer getEvaluationResult() {
		return evaluationResult; 
	}

	public void setEvaluationResult(Integer evaluationResult) {
		this.evaluationResult = evaluationResult; 
		super.setField("evaluationResult");
	}

	public String getDetectionUnit() {
		return detectionUnit; 
	}

	public void setDetectionUnit(String detectionUnit) {
		this.detectionUnit = detectionUnit; 
		super.setField("detectionUnit");
	}

	public String getAuditor() {
		return auditor; 
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor; 
		super.setField("auditor");
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
	public List<DaqDetectionPaUltrasonicSub> getPaUltrasonicSubList() {
		return paUltrasonicSubList;
	}

	public void setPaUltrasonicSubList(List<DaqDetectionPaUltrasonicSub> paUltrasonicSubList) {
		this.paUltrasonicSubList = paUltrasonicSubList;
	}

}