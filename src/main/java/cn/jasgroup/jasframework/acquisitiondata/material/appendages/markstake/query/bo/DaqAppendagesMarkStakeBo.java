package cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.query.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 标志桩附属物
 * @author zhangyi
 * @date 2018年7月19日下午6:06:59
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqAppendagesMarkStakeBo extends CommonBaseBo {

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
	 * 标志桩编号 
	 */
	private String markStakeCode; 

	/**
	 * 桩号id 
	 */
	private String medianStakeOid;
	
	/**
	 * 中线桩号 
	 */
	private String medianStakeCode; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * 东坐标 
	 */
	private Double pointx; 

	/**
	 * 北坐标 
	 */
	private Double pointy; 

	/**
	 * 桩体结构 
	 */
	private String stakeStructure;
	
	/**
	 * 桩体结构阈值名称 
	 */
	private String stakeStructureName; 

	/**
	 * 埋设日期 
	 */
	private Date burialDate; 

	/**
	 * 桩功能 
	 */
	private String stakeFunction;
	
	/**
	 * 桩功能阈值名称 
	 */
	private String stakeFunctionName; 

	/**
	 * 埋深(m) 
	 */
	private Double burialDepth; 

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
	private Integer approveStatus = 0; 

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

	public String getMarkStakeCode() {
		return markStakeCode;
	}

	public void setMarkStakeCode(String markStakeCode) {
		this.markStakeCode = markStakeCode;
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

	public Double getPointx() {
		return pointx;
	}

	public void setPointx(Double pointx) {
		this.pointx = pointx;
	}

	public Double getPointy() {
		return pointy;
	}

	public void setPointy(Double pointy) {
		this.pointy = pointy;
	}

	public String getStakeStructure() {
		return stakeStructure;
	}

	public void setStakeStructure(String stakeStructure) {
		this.stakeStructure = stakeStructure;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getBurialDate() {
		return burialDate;
	}

	public void setBurialDate(Date burialDate) {
		this.burialDate = burialDate;
	}

	public String getStakeFunction() {
		return stakeFunction;
	}

	public void setStakeFunction(String stakeFunction) {
		this.stakeFunction = stakeFunction;
	}

	public Double getBurialDepth() {
		return burialDepth;
	}

	public void setBurialDepth(Double burialDepth) {
		this.burialDepth = burialDepth;
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

	public String getStakeStructureName() {
		return stakeStructureName;
	}

	public void setStakeStructureName(String stakeStructureName) {
		this.stakeStructureName = stakeStructureName;
	}

	public String getStakeFunctionName() {
		return stakeFunctionName;
	}

	public void setStakeFunctionName(String stakeFunctionName) {
		this.stakeFunctionName = stakeFunctionName;
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
	
}
