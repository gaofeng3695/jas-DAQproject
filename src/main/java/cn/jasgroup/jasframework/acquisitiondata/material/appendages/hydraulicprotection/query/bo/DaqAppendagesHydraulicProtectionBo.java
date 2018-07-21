package cn.jasgroup.jasframework.acquisitiondata.material.appendages.hydraulicprotection.query.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 水工保护
 * @author zhangyi
 * @date 2018年7月21日下午1:33:57
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqAppendagesHydraulicProtectionBo extends CommonBaseBo {

	/**
	 * 数据oid 
	 */
	private String oid;

	/**
	 * 项目oid 
	 */
	private String projectOid;
	
	/**
	 * 项目name
	 */
	private String projectName; 

	/**
	 * 标段oid 
	 */
	private String tendersOid;
	
	/**
	 * 标段name 
	 */
	private String tendersName; 

	/**
	 * 管线oid 
	 */
	private String pipelineOid;
	
	/**
	 * 管线name 
	 */
	private String pipelineName;
	
	/**
	 * 线路段/穿跨越oid 
	 */
	private String pipeSegmentOrCrossOid;
	
	/**
	 * 线路段/穿跨越name 
	 */
	private String pipeSegmentOrCrossName;
	
	/**
	 * 水工保护编号 
	 */
	private String hydraulicProtectionCode; 

	/**
	 * 水工保护类型 
	 */
	private String hydraulicProtectionType;
	
	/**
	 * 水工保护类型阈值名称 
	 */
	private String hydraulicProtectionTypeName; 

	/**
	 * 水工保护名称 
	 */
	private String hydraulicProtectionName; 

	/**
	 * 桩号id 
	 */
	private String medianStakeOid;
	
	/**
	 * 桩号 
	 */
	private String medianStakeCode; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * 起点坐标X 
	 */
	private Double startPointx; 

	/**
	 * 起点坐标Y 
	 */
	private Double startPointy; 

	/**
	 * 终点坐标X 
	 */
	private Double endPointx; 

	/**
	 * 终点坐标Y 
	 */
	private Double endPointy; 

	/**
	 * 结构尺寸 
	 */
	private String structureSize; 

	/**
	 * 工程量(m3) 
	 */
	private Double engineerQuatity; 

	/**
	 * 水工保护材料 
	 */
	private String hydraulicProtectionMaterial;
	
	/**
	 * 水工保护材料阈值名称 
	 */
	private String hydraulicProtectionMaterialName; 

	/**
	 * 检查验收日期 
	 */
	private Date acceptDate; 

	/**
	 * 检查结论 
	 */
	private Integer inspectionFindings; 

	/**
	 * 施工机组代号 
	 */
	private String workUnitOid;
	
	/**
	 * 施工机组代号code 
	 */
	private String workUnitCode;
	
	/**
	 * 施工机组代号name 
	 */
	private String workUnitName; 

	/**
	 * 施工单位 
	 */
	private String constructUnit;
	
	/**
	 * 施工单位name 
	 */
	private String constructUnitName; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnit;
	
	/**
	 * 监理单位name 
	 */
	private String supervisionUnitName; 

	/**
	 * 监理工程师 
	 */
	private String supervisionEngineer; 

	/**
	 * 采集人员 
	 */
	private String collectionPerson; 

	/**
	 * 采集日期 
	 */
	private Date collectionDate; 

	/**
	 * 审核状态 
	 */
	private Integer approveStatus; 

	/**
	 * 备注 
	 */
	private String remarks;

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

	public String getHydraulicProtectionCode() {
		return hydraulicProtectionCode;
	}

	public void setHydraulicProtectionCode(String hydraulicProtectionCode) {
		this.hydraulicProtectionCode = hydraulicProtectionCode;
	}

	public String getHydraulicProtectionType() {
		return hydraulicProtectionType;
	}

	public void setHydraulicProtectionType(String hydraulicProtectionType) {
		this.hydraulicProtectionType = hydraulicProtectionType;
	}

	public String getHydraulicProtectionTypeName() {
		return hydraulicProtectionTypeName;
	}

	public void setHydraulicProtectionTypeName(String hydraulicProtectionTypeName) {
		this.hydraulicProtectionTypeName = hydraulicProtectionTypeName;
	}

	public String getHydraulicProtectionName() {
		return hydraulicProtectionName;
	}

	public void setHydraulicProtectionName(String hydraulicProtectionName) {
		this.hydraulicProtectionName = hydraulicProtectionName;
	}

	public String getMedianStakeOid() {
		return medianStakeOid;
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid;
	}

	public String getMedianStakeCode() {
		return medianStakeCode;
	}

	public void setMedianStakeCode(String medianStakeCode) {
		this.medianStakeCode = medianStakeCode;
	}

	public Double getRelativeMileage() {
		return relativeMileage;
	}

	public void setRelativeMileage(Double relativeMileage) {
		this.relativeMileage = relativeMileage;
	}

	public Double getStartPointx() {
		return startPointx;
	}

	public void setStartPointx(Double startPointx) {
		this.startPointx = startPointx;
	}

	public Double getStartPointy() {
		return startPointy;
	}

	public void setStartPointy(Double startPointy) {
		this.startPointy = startPointy;
	}

	public Double getEndPointx() {
		return endPointx;
	}

	public void setEndPointx(Double endPointx) {
		this.endPointx = endPointx;
	}

	public Double getEndPointy() {
		return endPointy;
	}

	public void setEndPointy(Double endPointy) {
		this.endPointy = endPointy;
	}

	public String getStructureSize() {
		return structureSize;
	}

	public void setStructureSize(String structureSize) {
		this.structureSize = structureSize;
	}

	public Double getEngineerQuatity() {
		return engineerQuatity;
	}

	public void setEngineerQuatity(Double engineerQuatity) {
		this.engineerQuatity = engineerQuatity;
	}

	public String getHydraulicProtectionMaterial() {
		return hydraulicProtectionMaterial;
	}

	public void setHydraulicProtectionMaterial(String hydraulicProtectionMaterial) {
		this.hydraulicProtectionMaterial = hydraulicProtectionMaterial;
	}

	public String getHydraulicProtectionMaterialName() {
		return hydraulicProtectionMaterialName;
	}

	public void setHydraulicProtectionMaterialName(String hydraulicProtectionMaterialName) {
		this.hydraulicProtectionMaterialName = hydraulicProtectionMaterialName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getAcceptDate() {
		return acceptDate;
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate;
	}

	public Integer getInspectionFindings() {
		return inspectionFindings;
	}

	public void setInspectionFindings(Integer inspectionFindings) {
		this.inspectionFindings = inspectionFindings;
	}

	public String getWorkUnitOid() {
		return workUnitOid;
	}

	public void setWorkUnitOid(String workUnitOid) {
		this.workUnitOid = workUnitOid;
	}

	public String getWorkUnitName() {
		return workUnitName;
	}

	public void setWorkUnitName(String workUnitName) {
		this.workUnitName = workUnitName;
	}

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}

	public String getConstructUnitName() {
		return constructUnitName;
	}

	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}

	public String getSupervisionUnit() {
		return supervisionUnit;
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit;
	}

	public String getSupervisionUnitName() {
		return supervisionUnitName;
	}

	public void setSupervisionUnitName(String supervisionUnitName) {
		this.supervisionUnitName = supervisionUnitName;
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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

	public String getWorkUnitCode() {
		return workUnitCode;
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode;
	}
	
}
