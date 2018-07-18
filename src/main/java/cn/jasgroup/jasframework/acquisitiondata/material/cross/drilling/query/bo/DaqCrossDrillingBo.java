package cn.jasgroup.jasframework.acquisitiondata.material.cross.drilling.query.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 定向钻穿越bo
 * @author zhangyi
 * @date 2018年7月16日下午3:59:34
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqCrossDrillingBo extends CommonBaseBo{

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
	 * 穿跨越oid 
	 */
	private String crossOid;
	
	/**
	 * 穿跨越Name
	 */
	private String crossName;
	
	/**
	 * 穿越物管理单位 
	 */
	private String crossingDepartment; 

	/**
	 * 穿越长度(m) 
	 */
	private Double crossLength; 

	/**
	 * 穿越最大深度(m) 
	 */
	private Double crossMaxLength; 

	/**
	 * 起始桩号id 
	 */
	private String startMedianStakeOid;
	
	/**
	 * 起始桩号 
	 */
	private String startMedianStakeCode; 

	/**
	 * 相对起始桩位置(m) 
	 */
	private Double startRelativeMileage; 

	/**
	 * 结束桩号id 
	 */
	private String endMedianStakeOid;
	
	/**
	 * 结束桩号 
	 */
	private String endMedianStakeCode; 

	/**
	 * 相对结束桩位置(m) 
	 */
	private Double endRelativeMileage; 

	/**
	 * 入土点X坐标 
	 */
	private Double startPointx; 

	/**
	 * 入土点Y坐标 
	 */
	private Double startPointy; 

	/**
	 * 入土点高程(m) 
	 */
	private Double startPointz; 

	/**
	 * 出土点X坐标 
	 */
	private Double endPointx; 

	/**
	 * 出土点Y坐标 
	 */
	private Double endPointy; 

	/**
	 * 出土点高程(m) 
	 */
	private Double endPointz; 

	/**
	 * 入土角 
	 */
	private Double exitAngle; 

	/**
	 * 出土角 
	 */
	private Double enterAngle; 

	/**
	 * 开工日期 
	 */
	private Date commencementDate; 

	/**
	 * 完工日期 
	 */
	private Date completionDate; 

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

	public String getCrossOid() {
		return crossOid;
	}

	public void setCrossOid(String crossOid) {
		this.crossOid = crossOid;
	}

	public String getCrossName() {
		return crossName;
	}

	public void setCrossName(String crossName) {
		this.crossName = crossName;
	}

	public String getCrossingDepartment() {
		return crossingDepartment;
	}

	public void setCrossingDepartment(String crossingDepartment) {
		this.crossingDepartment = crossingDepartment;
	}

	public Double getCrossLength() {
		return crossLength;
	}

	public void setCrossLength(Double crossLength) {
		this.crossLength = crossLength;
	}

	public Double getCrossMaxLength() {
		return crossMaxLength;
	}

	public void setCrossMaxLength(Double crossMaxLength) {
		this.crossMaxLength = crossMaxLength;
	}

	public String getStartMedianStakeOid() {
		return startMedianStakeOid;
	}

	public void setStartMedianStakeOid(String startMedianStakeOid) {
		this.startMedianStakeOid = startMedianStakeOid;
	}

	public String getStartMedianStakeCode() {
		return startMedianStakeCode;
	}

	public void setStartMedianStakeCode(String startMedianStakeCode) {
		this.startMedianStakeCode = startMedianStakeCode;
	}

	public Double getStartRelativeMileage() {
		return startRelativeMileage;
	}

	public void setStartRelativeMileage(Double startRelativeMileage) {
		this.startRelativeMileage = startRelativeMileage;
	}

	public String getEndMedianStakeOid() {
		return endMedianStakeOid;
	}

	public void setEndMedianStakeOid(String endMedianStakeOid) {
		this.endMedianStakeOid = endMedianStakeOid;
	}

	public String getEndMedianStakeCode() {
		return endMedianStakeCode;
	}

	public void setEndMedianStakeCode(String endMedianStakeCode) {
		this.endMedianStakeCode = endMedianStakeCode;
	}

	public Double getEndRelativeMileage() {
		return endRelativeMileage;
	}

	public void setEndRelativeMileage(Double endRelativeMileage) {
		this.endRelativeMileage = endRelativeMileage;
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

	public Double getStartPointz() {
		return startPointz;
	}

	public void setStartPointz(Double startPointz) {
		this.startPointz = startPointz;
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

	public Double getEndPointz() {
		return endPointz;
	}

	public void setEndPointz(Double endPointz) {
		this.endPointz = endPointz;
	}

	public Double getExitAngle() {
		return exitAngle;
	}

	public void setExitAngle(Double exitAngle) {
		this.exitAngle = exitAngle;
	}

	public Double getEnterAngle() {
		return enterAngle;
	}

	public void setEnterAngle(Double enterAngle) {
		this.enterAngle = enterAngle;
	}


	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getCommencementDate() {
		return commencementDate;
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
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
