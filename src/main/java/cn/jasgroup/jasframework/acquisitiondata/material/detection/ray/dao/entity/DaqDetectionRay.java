package cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.dao.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
 * <p>类描述：射线检测主表。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月10日 上午10:06:13。</p>
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.service.DaqDetectionRayService #saveChanageWledStatus}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.service.DaqDetectionRayService #updateChanageWledStatusBeforeAdvice}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.service.DaqDetectionRayService #updateChanageWledStatus}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.service.DaqDetectionRayService #deleteChanageWledStatus}
 */

@CommonSaveConfig(
	scene = "/detectionRay/save",
	afterAdvice = {
		@Process(service = "daqDetectionRayService", method = "saveChanageWledStatus()")
	}
)
@CommonUpdateConfig(
	scene = "/detectionRay/update",
	beforeAdvice={
		@Process(service = "daqDetectionRayService", method = "updateChanageWledStatusBeforeAdvice()")
	},
	afterAdvice={
		@Process(service = "daqDetectionRayService", method = "updateChanageWledStatus()")
	}
)
@CommonDeleteConfig(
	scene = "/detectionRay/delete",
	afterAdvice = {
		@Process(service = "daqDetectionRayService", method = "deleteChanageWledStatus()")
	}
)
@CommonDeleteBatchConfig(scene = "/detectionRay/deleteBatch")
@CommonGetConfig(scene = "/detectionRay/get")
@JdbcEntity(name = "daq_detection_ray")
public class DaqDetectionRay extends CommonJdbcEntity {

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

	/** 射线检测子表集合 */
	private List<DaqDetectionRaySub> raySubList = new ArrayList<DaqDetectionRaySub>();

	@Column(name = "project_oid", length = 36)
	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
		super.setField("projectOid");
	}

	@Column(name = "pipeline_oid", length = 36)
	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
		super.setField("pipelineOid");
	}

	@Column(name = "tenders_oid", length = 36)
	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
		super.setField("tendersOid");
	}

	@Column(name = "pipe_segment_or_cross_oid", length = 36)
	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
		super.setField("pipeSegmentOrCrossOid");
	}

	@Column(name = "weld_oid", length = 36)
	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
		super.setField("weldOid");
	}
	
	@Column(name = "detection_report_num", length = 60)
	public String getDetectionReportNum() {
		return detectionReportNum;
	}

	public void setDetectionReportNum(String detectionReportNum) {
		this.detectionReportNum = detectionReportNum;
		super.setField("detectionReportNum");
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	@Column(name = "detection_deta", length = 6)
	public Date getDetectionDeta() {
		return detectionDeta;
	}

	public void setDetectionDeta(Date detectionDeta) {
		this.detectionDeta = detectionDeta;
		super.setField("detectionDeta");
	}

	@Column(name = "detection_type", length = 50)
	public String getDetectionType() {
		return detectionType;
	}

	public void setDetectionType(String detectionType) {
		this.detectionType = detectionType;
		super.setField("detectionType");
	}

	@Column(name = "evaluation_grade", length = 50)
	public String getEvaluationGrade() {
		return evaluationGrade;
	}

	public void setEvaluationGrade(String evaluationGrade) {
		this.evaluationGrade = evaluationGrade;
		super.setField("evaluationGrade");
	}

	@Column(name = "evaluation_result", precision = 1, scale = 0)
	public Integer getEvaluationResult() {
		return evaluationResult;
	}

	public void setEvaluationResult(Integer evaluationResult) {
		this.evaluationResult = evaluationResult;
		super.setField("evaluationResult");
	}

	@Column(name = "detection_dispose", length = 50)
	public String getDetectionDispose() {
		return detectionDispose;
	}

	public void setDetectionDispose(String detectionDispose) {
		this.detectionDispose = detectionDispose;
		super.setField("detectionDispose");
	}

	@Column(name = "detection_unit", length = 50)
	public String getDetectionUnit() {
		return detectionUnit;
	}

	public void setDetectionUnit(String detectionUnit) {
		this.detectionUnit = detectionUnit;
		super.setField("detectionUnit");
	}

	@Column(name = "detection_person", length = 25)
	public String getDetectionPerson() {
		return detectionPerson;
	}

	public void setDetectionPerson(String detectionPerson) {
		this.detectionPerson = detectionPerson;
		super.setField("detectionPerson");
	}

	@Column(name = "supervision_unit", length = 38)
	public String getSupervisionUnit() {
		return supervisionUnit;
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit;
		super.setField("supervisionUnit");
	}

	@Column(name = "supervision_engineer", length = 50)
	public String getSupervisionEngineer() {
		return supervisionEngineer;
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer;
		super.setField("supervisionEngineer");
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	@Column(name = "collection_date", length = 6)
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
		super.setField("collectionDate");
	}

	@Column(name = "remarks", length = 200)
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
	public List<DaqDetectionRaySub> getRaySubList() {
		return raySubList;
	}

	public void setRaySubList(List<DaqDetectionRaySub> raySubList) {
		this.raySubList = raySubList;
	}

}