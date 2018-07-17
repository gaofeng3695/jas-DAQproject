package cn.jasgroup.jasframework.acquisitiondata.lay.Insulation.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
  *<p>类描述：保温Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月17日 上午10:36:32。</p>
 */
public class LayThermalInsulationBo extends CommonBaseBo{

	/**
	 * oid
	 */
	private String oid;
	
	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 管线名称
	 */
	private String pipelineName;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段名称
	 */
	private String tendersName;

	/** 
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid; 
	
	/**
	 * 线路段/穿跨越名称
	 */
	private String pipeSegmentOrCrossName; 

	/**
	 * 起始桩号 
	 */
	private String startMedianStakeOid; 
	
	/**
	 * 起始桩编号
	 */
	private String startMedianStakeName;

	/**
	 * 相对起始桩里程(m) 
	 */
	private Double startRelativeMileage; 

	/**
	 * 结束桩号 
	 */
	private String endMedianStakeOid; 
	
	/**
	 * 终点桩编号
	 */
	private String endMedianStakeName;

	/**
	 * 相对结束桩里程(m) 
	 */
	private Double endRelativeMileage; 

	/**
	 * 保温材料 
	 */
	private String insulationMaterial; 

	/**
	 * 保温材料名称
	 */
	private String insulationMaterialName; 

	/**
	 * 保温层厚度(mm) 
	 */
	private Double insulationThickness; 

	/**
	 * 防护层结构 
	 */
	private String protectiveTape; 

	/**
	 * 防护层结构名称 
	 */
	private String protectiveTapeName; 

	/**
	 * 防护层厚度(mm) 
	 */
	private Double protectiveThickness; 

	/**
	 * 保温层生产厂家 
	 */
	private String manufacturer; 

	/**
	 * 具体地址 
	 */
	private String manufacturerAddress; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 

	/** 
	 * 施工单位名称
	 */
	private String constructUnitName; 

	/**
	 * 施工日期 
	 */
	private Date constructDate; 

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
	private Integer approveStatus = 0; 

	/**
	 * 空间数据状态 
	 */
	private String geoState; 

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

	public String getPipelineName() {
		return pipelineName;
	}

	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
	}

	public String getTendersName() {
		return tendersName;
	}

	public void setTendersName(String tendersName) {
		this.tendersName = tendersName;
	}

	public String getPipeSegmentOrCrossName() {
		return pipeSegmentOrCrossName;
	}

	public void setPipeSegmentOrCrossName(String pipeSegmentOrCrossName) {
		this.pipeSegmentOrCrossName = pipeSegmentOrCrossName;
	}

	public String getStartMedianStakeName() {
		return startMedianStakeName;
	}

	public void setStartMedianStakeName(String startMedianStakeName) {
		this.startMedianStakeName = startMedianStakeName;
	}

	public String getEndMedianStakeName() {
		return endMedianStakeName;
	}

	public void setEndMedianStakeName(String endMedianStakeName) {
		this.endMedianStakeName = endMedianStakeName;
	}

	public String getInsulationMaterialName() {
		return insulationMaterialName;
	}

	public void setInsulationMaterialName(String insulationMaterialName) {
		this.insulationMaterialName = insulationMaterialName;
	}

	public String getProtectiveTapeName() {
		return protectiveTapeName;
	}

	public void setProtectiveTapeName(String protectiveTapeName) {
		this.protectiveTapeName = protectiveTapeName;
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

	public String getPipelineOid() {
		return pipelineOid; 
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid; 
	}

	public String getTendersOid() {
		return tendersOid; 
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid; 
	}

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid; 
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid; 
	}

	public String getStartMedianStakeOid() {
		return startMedianStakeOid; 
	}

	public void setStartMedianStakeOid(String startMedianStakeOid) {
		this.startMedianStakeOid = startMedianStakeOid; 
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

	public Double getEndRelativeMileage() {
		return endRelativeMileage; 
	}

	public void setEndRelativeMileage(Double endRelativeMileage) {
		this.endRelativeMileage = endRelativeMileage; 
	}

	public String getInsulationMaterial() {
		return insulationMaterial; 
	}

	public void setInsulationMaterial(String insulationMaterial) {
		this.insulationMaterial = insulationMaterial; 
	}

	public Double getInsulationThickness() {
		return insulationThickness; 
	}

	public void setInsulationThickness(Double insulationThickness) {
		this.insulationThickness = insulationThickness; 
	}

	public String getProtectiveTape() {
		return protectiveTape; 
	}

	public void setProtectiveTape(String protectiveTape) {
		this.protectiveTape = protectiveTape; 
	}

	public Double getProtectiveThickness() {
		return protectiveThickness; 
	}

	public void setProtectiveThickness(Double protectiveThickness) {
		this.protectiveThickness = protectiveThickness; 
	}

	public String getManufacturer() {
		return manufacturer; 
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer; 
	}

	public String getManufacturerAddress() {
		return manufacturerAddress; 
	}

	public void setManufacturerAddress(String manufacturerAddress) {
		this.manufacturerAddress = manufacturerAddress; 
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getConstructDate() {
		return constructDate; 
	}

	public void setConstructDate(Date constructDate) {
		this.constructDate = constructDate; 
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

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
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

	public String getGeoState() {
		return geoState; 
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState; 
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
	}

}
