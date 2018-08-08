package cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

public class ColdBendingPipeBo extends CommonBaseBo{
	private String oid;
	/** 项目oid */
	private String projectOid;
	
	/** 项目名称**/
	private String projectName;

	/** 标段oid */
	private String tendersOid;
	
	/** 标段名称 */
	private String tendersName;

	/** 管线oid */
	private String pipelineOid; 
	
	/** 管线名称 */
	private String pipelineName; 

	/** 线路段/穿跨越oid */
	private String pipeSegmentOrCrossOid;
	
	/** 线路段/穿跨越名称 */
	private String pipeSegmentOrCrossName; 

	/** 原材料钢管Oid */
	private String pipeOid;
	
	/** 原材料钢管编号 */
	private String pipeCode; 

	/** 冷弯管编号 */
	private String pipeColdBendingCode; 

	/** 合格证编号 */
	private String certificateNum; 

	/** 管子弯曲设计标准 */
	private String pipeBendingStandards; 

	/** 曲率半径（mm) */
	private Double bendingRadius; 

	/** 弯曲角度(°) */
	private Double bendingAngle; 

	/** 曲线长度(m) */
	private Double curveLength; 

	/** 直管段最小长度(m) */
	private Double straightPipeLength; 

	/** 长度(m) */
	private Double pipeLength; 

	/** 椭圆率(%) */
	private Double ellipticity; 

	/** 壁厚减薄率(%) */
	private Double wallThicknessRedurate; 

	/** 管径(mm) */
	private Double pipeDiameter; 

	/** 壁厚(mm) */
	private Double wallThickness; 

	/** 制作时间 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date produceDate; 

	/** 施工单位 */
	private String constructUnit;
	
	/** 施工单位名称 */
	private String constructUnitName; 

	/** 监理单位 */
	private String supervisionUnit;
	
	/** 监理单位 */
	private String supervisionUnitName; 

	/** 监理工程师 */
	private String supervisionEngineer; 

	/** 采集人员 */
	private String collectionPerson; 

	/** 采集时间 */
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date collectionDate; 

	/** 是否使用 */
	private String isUse;
	
	/***
	 * 审核状态
	 */
	private Integer approveStatus; 

	/** 备注 */
	private String remarks;

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

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}

	public String getPipeSegmentOrCrossName() {
		return pipeSegmentOrCrossName;
	}

	public void setPipeSegmentOrCrossName(String pipeSegmentOrCrossName) {
		this.pipeSegmentOrCrossName = pipeSegmentOrCrossName;
	}

	public String getPipeCode() {
		return pipeCode;
	}

	public void setPipeCode(String pipeCode) {
		this.pipeCode = pipeCode;
	}

	public String getPipeColdBendingCode() {
		return pipeColdBendingCode;
	}

	public void setPipeColdBendingCode(String pipeColdBendingCode) {
		this.pipeColdBendingCode = pipeColdBendingCode;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public String getPipeBendingStandards() {
		return pipeBendingStandards;
	}

	public void setPipeBendingStandards(String pipeBendingStandards) {
		this.pipeBendingStandards = pipeBendingStandards;
	}

	public Double getBendingRadius() {
		return bendingRadius;
	}

	public void setBendingRadius(Double bendingRadius) {
		this.bendingRadius = bendingRadius;
	}

	public Double getBendingAngle() {
		return bendingAngle;
	}

	public void setBendingAngle(Double bendingAngle) {
		this.bendingAngle = bendingAngle;
	}

	public Double getCurveLength() {
		return curveLength;
	}

	public void setCurveLength(Double curveLength) {
		this.curveLength = curveLength;
	}

	public Double getStraightPipeLength() {
		return straightPipeLength;
	}

	public void setStraightPipeLength(Double straightPipeLength) {
		this.straightPipeLength = straightPipeLength;
	}

	public Double getPipeLength() {
		return pipeLength;
	}

	public void setPipeLength(Double pipeLength) {
		this.pipeLength = pipeLength;
	}

	public Double getEllipticity() {
		return ellipticity;
	}

	public void setEllipticity(Double ellipticity) {
		this.ellipticity = ellipticity;
	}

	public Double getWallThicknessRedurate() {
		return wallThicknessRedurate;
	}

	public void setWallThicknessRedurate(Double wallThicknessRedurate) {
		this.wallThicknessRedurate = wallThicknessRedurate;
	}

	public Double getPipeDiameter() {
		return pipeDiameter;
	}

	public void setPipeDiameter(Double pipeDiameter) {
		this.pipeDiameter = pipeDiameter;
	}

	public Double getWallThickness() {
		return wallThickness;
	}

	public void setWallThickness(Double wallThickness) {
		this.wallThickness = wallThickness;
	}

	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
	}

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
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

	public String getCollectionPerson() {
		return collectionPerson;
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson;
	}

	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getIsUse() {
		return isUse;
	}

	public void setIsUse(String isUse) {
		this.isUse = isUse;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getConstructUnitName() {
		return constructUnitName;
	}

	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}

	public String getSupervisionUnitName() {
		return supervisionUnitName;
	}

	public void setSupervisionUnitName(String supervisionUnitName) {
		this.supervisionUnitName = supervisionUnitName;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getPipeOid() {
		return pipeOid;
	}

	public void setPipeOid(String pipeOid) {
		this.pipeOid = pipeOid;
	}
	
}
