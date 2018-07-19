package cn.jasgroup.jasframework.acquisitiondata.cathodic.drainage.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
  *<p>类描述：极性排流器Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月19日 下午6:40:37。</p>
 */
public class CathodicPolarityDrainageBo extends CommonBaseBo{
	
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
	 * 线路段
	 */
	private String pipeSegmentOid; 

	/**
	 * 线路段名称
	 */
	private String pipeSegmentName; 

	/**
	 * 设备编号 
	 */
	private String equipmentCode; 

	/**
	 * 名称 
	 */
	private String equipmentName; 

	/**
	 * 出厂编号 
	 */
	private String factoryNum; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid;

	/**
	 * 桩号编码
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
	 * 保护范围 
	 */
	private String protectionScope; 

	/**
	 * 制造商 
	 */
	private String manufacturer; 

	/**
	 * 排流量(A) 
	 */
	private Double dischargeFlow; 

	/**
	 * 最大排流量(A) 
	 */
	private Double maxDischargeFlow; 

	/**
	 * 平均排流量(A) 
	 */
	private Double avgDischargeFlow; 

	/**
	 * 最小排流量(A) 
	 */
	private Double minDischargeFlow; 

	/**
	 * 地床材料 
	 */
	private String groundBedMaterial; 

	/**
	 * 地床材料名称
	 */
	private String groundBedMaterialName; 

	/**
	 * 管地电位(V) 
	 */
	private Double managePotential; 

	/**
	 * 地床接地电位(V) 
	 */
	private Double groundPotential; 

	/**
	 * 投用日期 
	 */
	private Date serviceDate; 

	/**
	 * 预计失效日期 
	 */
	private Date expirationDate; 

	/**
	 * 生产日期 
	 */
	private Date productDate; 

	/**
	 * 排流用途 
	 */
	private String drainagePurpose; 

	/**
	 * 排流用途名称 
	 */
	private String drainagePurposeName; 

	/**
	 * 排流类型 
	 */
	private String drainageType; 

	/**
	 * 设计寿命(年) 
	 */
	private Integer designLife; 

	/**
	 * 不排流时平均电位(V) 
	 */
	private Double avgPotentialUndrain; 

	/**
	 * 排流时平均电位(V) 
	 */
	private Double avgPotentialDrain; 

	/**
	 * 接地排流地床电阻(Ω) 
	 */
	private Double earthingResistance; 

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
	 * 空间数据状态 
	 */
	private String geoState; 

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

	public String getPipeSegmentOid() {
		return pipeSegmentOid;
	}

	public void setPipeSegmentOid(String pipeSegmentOid) {
		this.pipeSegmentOid = pipeSegmentOid;
	}

	public String getPipeSegmentName() {
		return pipeSegmentName;
	}

	public void setPipeSegmentName(String pipeSegmentName) {
		this.pipeSegmentName = pipeSegmentName;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getFactoryNum() {
		return factoryNum;
	}

	public void setFactoryNum(String factoryNum) {
		this.factoryNum = factoryNum;
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

	public String getProtectionScope() {
		return protectionScope;
	}

	public void setProtectionScope(String protectionScope) {
		this.protectionScope = protectionScope;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Double getDischargeFlow() {
		return dischargeFlow;
	}

	public void setDischargeFlow(Double dischargeFlow) {
		this.dischargeFlow = dischargeFlow;
	}

	public Double getMaxDischargeFlow() {
		return maxDischargeFlow;
	}

	public void setMaxDischargeFlow(Double maxDischargeFlow) {
		this.maxDischargeFlow = maxDischargeFlow;
	}

	public Double getAvgDischargeFlow() {
		return avgDischargeFlow;
	}

	public void setAvgDischargeFlow(Double avgDischargeFlow) {
		this.avgDischargeFlow = avgDischargeFlow;
	}

	public Double getMinDischargeFlow() {
		return minDischargeFlow;
	}

	public void setMinDischargeFlow(Double minDischargeFlow) {
		this.minDischargeFlow = minDischargeFlow;
	}

	public String getGroundBedMaterial() {
		return groundBedMaterial;
	}

	public void setGroundBedMaterial(String groundBedMaterial) {
		this.groundBedMaterial = groundBedMaterial;
	}

	public String getGroundBedMaterialName() {
		return groundBedMaterialName;
	}

	public void setGroundBedMaterialName(String groundBedMaterialName) {
		this.groundBedMaterialName = groundBedMaterialName;
	}

	public Double getManagePotential() {
		return managePotential;
	}

	public void setManagePotential(Double managePotential) {
		this.managePotential = managePotential;
	}

	public Double getGroundPotential() {
		return groundPotential;
	}

	public void setGroundPotential(Double groundPotential) {
		this.groundPotential = groundPotential;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public String getDrainagePurpose() {
		return drainagePurpose;
	}

	public void setDrainagePurpose(String drainagePurpose) {
		this.drainagePurpose = drainagePurpose;
	}

	public String getDrainagePurposeName() {
		return drainagePurposeName;
	}

	public void setDrainagePurposeName(String drainagePurposeName) {
		this.drainagePurposeName = drainagePurposeName;
	}

	public String getDrainageType() {
		return drainageType;
	}

	public void setDrainageType(String drainageType) {
		this.drainageType = drainageType;
	}

	public Integer getDesignLife() {
		return designLife;
	}

	public void setDesignLife(Integer designLife) {
		this.designLife = designLife;
	}

	public Double getAvgPotentialUndrain() {
		return avgPotentialUndrain;
	}

	public void setAvgPotentialUndrain(Double avgPotentialUndrain) {
		this.avgPotentialUndrain = avgPotentialUndrain;
	}

	public Double getAvgPotentialDrain() {
		return avgPotentialDrain;
	}

	public void setAvgPotentialDrain(Double avgPotentialDrain) {
		this.avgPotentialDrain = avgPotentialDrain;
	}

	public Double getEarthingResistance() {
		return earthingResistance;
	}

	public void setEarthingResistance(Double earthingResistance) {
		this.earthingResistance = earthingResistance;
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

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getGeoState() {
		return geoState;
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState;
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
