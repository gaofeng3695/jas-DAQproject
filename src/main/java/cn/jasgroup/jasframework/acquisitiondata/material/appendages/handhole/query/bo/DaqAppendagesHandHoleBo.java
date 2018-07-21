package cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.query.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 手孔表
 * @author zhangyi
 * @date 2018年7月21日下午12:02:05
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqAppendagesHandHoleBo extends CommonBaseBo {

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
	 * 孔编号 
	 */
	private String handHoleCode; 

	/**
	 * 名称 
	 */
	private String handHoleName;
	
	/**
	 * 名称阈值名称 
	 */
	private String handHoleNameCodeName; 

	/**
	 * 类型 
	 */
	private String handHoleType;
	
	/**
	 * 类型阈值名称 
	 */
	private String handHoleTypeName; 

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
	 * X坐标 
	 */
	private Double pointx; 

	/**
	 * Y坐标 
	 */
	private Double pointy; 

	/**
	 * 地表高程(m) 
	 */
	private Double pointz; 

	/**
	 * 规格型号 
	 */
	private String handHoleSpecifications; 

	/**
	 * 基础制造及安装情况 
	 */
	private String baseInstallSituation; 

	/**
	 * 口圈及安装情况 
	 */
	private String circleInstallSituation; 

	/**
	 * 材料类型 
	 */
	private String materialType;
	
	/**
	 * 光缆盘留长度(m) 
	 */
	private Double stayLong; 

	/**
	 * 是否放置电子标识 
	 */
	private Integer isElectronicMark; 

	/**
	 * 检查验收结果 
	 */
	private Integer acceptanceResults; 

	/**
	 * 施工单位 
	 */
	private String constructUnit;
	
	/**
	 * 施工单位名称 
	 */
	private String constructUnitName; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnit;
	
	/**
	 * 监理单位名称 
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

	public String getHandHoleCode() {
		return handHoleCode;
	}

	public void setHandHoleCode(String handHoleCode) {
		this.handHoleCode = handHoleCode;
	}

	public String getHandHoleName() {
		return handHoleName;
	}

	public void setHandHoleName(String handHoleName) {
		this.handHoleName = handHoleName;
	}

	public String getHandHoleNameCodeName() {
		return handHoleNameCodeName;
	}

	public void setHandHoleNameCodeName(String handHoleNameCodeName) {
		this.handHoleNameCodeName = handHoleNameCodeName;
	}

	public String getHandHoleType() {
		return handHoleType;
	}

	public void setHandHoleType(String handHoleType) {
		this.handHoleType = handHoleType;
	}

	public String getHandHoleTypeName() {
		return handHoleTypeName;
	}

	public void setHandHoleTypeName(String handHoleTypeName) {
		this.handHoleTypeName = handHoleTypeName;
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

	public Double getPointz() {
		return pointz;
	}

	public void setPointz(Double pointz) {
		this.pointz = pointz;
	}

	public String getHandHoleSpecifications() {
		return handHoleSpecifications;
	}

	public void setHandHoleSpecifications(String handHoleSpecifications) {
		this.handHoleSpecifications = handHoleSpecifications;
	}

	public String getBaseInstallSituation() {
		return baseInstallSituation;
	}

	public void setBaseInstallSituation(String baseInstallSituation) {
		this.baseInstallSituation = baseInstallSituation;
	}

	public String getCircleInstallSituation() {
		return circleInstallSituation;
	}

	public void setCircleInstallSituation(String circleInstallSituation) {
		this.circleInstallSituation = circleInstallSituation;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public Double getStayLong() {
		return stayLong;
	}

	public void setStayLong(Double stayLong) {
		this.stayLong = stayLong;
	}

	public Integer getIsElectronicMark() {
		return isElectronicMark;
	}

	public void setIsElectronicMark(Integer isElectronicMark) {
		this.isElectronicMark = isElectronicMark;
	}

	public Integer getAcceptanceResults() {
		return acceptanceResults;
	}

	public void setAcceptanceResults(Integer acceptanceResults) {
		this.acceptanceResults = acceptanceResults;
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

}
