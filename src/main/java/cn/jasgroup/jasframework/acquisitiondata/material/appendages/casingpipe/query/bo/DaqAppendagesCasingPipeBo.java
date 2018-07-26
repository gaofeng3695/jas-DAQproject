package cn.jasgroup.jasframework.acquisitiondata.material.appendages.casingpipe.query.bo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 套管
 * @author zhangyi
 * @date 2018年7月21日下午2:09:30
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqAppendagesCasingPipeBo extends CommonBaseBo {

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
	 * 套管类型 
	 */
	private String casingPipeType;
	
	/**
	 * 套管类型阈值名称 
	 */
	private String casingPipeTypeName; 

	/**
	 * 套管长度(m) 
	 */
	private Double casingPipeLength; 

	/**
	 * 套管规格 
	 */
	private String casingPipeSpecifications; 

	/**
	 * 施工日期 
	 */
	private Date constructDate; 

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

	public String getCasingPipeType() {
		return casingPipeType;
	}

	public void setCasingPipeType(String casingPipeType) {
		this.casingPipeType = casingPipeType;
	}

	public String getCasingPipeTypeName() {
		return casingPipeTypeName;
	}

	public void setCasingPipeTypeName(String casingPipeTypeName) {
		this.casingPipeTypeName = casingPipeTypeName;
	}

	public Double getCasingPipeLength() {
		return casingPipeLength;
	}

	public void setCasingPipeLength(Double casingPipeLength) {
		this.casingPipeLength = casingPipeLength;
	}

	public String getCasingPipeSpecifications() {
		return casingPipeSpecifications;
	}

	public void setCasingPipeSpecifications(String casingPipeSpecifications) {
		this.casingPipeSpecifications = casingPipeSpecifications;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getConstructDate() {
		return constructDate;
	}

	public void setConstructDate(Date constructDate) {
		this.constructDate = constructDate;
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
