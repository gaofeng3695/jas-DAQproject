package cn.jasgroup.jasframework.acquisitiondata.material.appendages.obstacle.query.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 地下障碍物
 * @author zhangyi
 * @date 2018年7月21日下午12:47:18
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqAppendagesObstacleBo extends CommonBaseBo {

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
	 * 障碍物编号 
	 */
	private String obstacleCode; 

	/**
	 * 障碍物名称 
	 */
	private String obstacleName; 

	/**
	 * 障碍物类型 
	 */
	private String obstacleType;
	
	/**
	 * 障碍物类型阈值名称 
	 */
	private String obstacleTypeName; 

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
	 * 高程(m) 
	 */
	private Double pointz; 

	/**
	 * 所属单位 
	 */
	private String subordinateUnit; 

	/**
	 * 地址 
	 */
	private String address; 

	/**
	 * 联系人 
	 */
	private String contacts; 

	/**
	 * 电话 
	 */
	private String telephone; 

	/**
	 * 最小间距(m) 
	 */
	private Double minDistance; 

	/**
	 * 是否与管道交叉 
	 */
	private Integer isThroughPipeline; 

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

	public String getObstacleCode() {
		return obstacleCode;
	}

	public void setObstacleCode(String obstacleCode) {
		this.obstacleCode = obstacleCode;
	}

	public String getObstacleName() {
		return obstacleName;
	}

	public void setObstacleName(String obstacleName) {
		this.obstacleName = obstacleName;
	}

	public String getObstacleType() {
		return obstacleType;
	}

	public void setObstacleType(String obstacleType) {
		this.obstacleType = obstacleType;
	}

	public String getObstacleTypeName() {
		return obstacleTypeName;
	}

	public void setObstacleTypeName(String obstacleTypeName) {
		this.obstacleTypeName = obstacleTypeName;
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

	public String getSubordinateUnit() {
		return subordinateUnit;
	}

	public void setSubordinateUnit(String subordinateUnit) {
		this.subordinateUnit = subordinateUnit;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(Double minDistance) {
		this.minDistance = minDistance;
	}

	public Integer getIsThroughPipeline() {
		return isThroughPipeline;
	}

	public void setIsThroughPipeline(Integer isThroughPipeline) {
		this.isThroughPipeline = isThroughPipeline;
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
