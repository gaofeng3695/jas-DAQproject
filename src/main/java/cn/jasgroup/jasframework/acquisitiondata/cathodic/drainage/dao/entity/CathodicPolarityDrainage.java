package cn.jasgroup.jasframework.acquisitiondata.cathodic.drainage.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
  *<p>类描述：极性排流器实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月19日 下午6:37:30。</p>
 */
@CommonSaveConfig(
	scene = "/cathodicPolarityDrainage/save"
)
@CommonUpdateConfig(
	scene = "/cathodicPolarityDrainage/update"
)
@CommonDeleteConfig(
	scene = "/cathodicPolarityDrainage/delete"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"pipeSegmentOid","equipmentCode"},name="同一线路段下设备编号不能重复"),
    }
)
@Point(
	x="pointx",
	y="pointy" ,
	z="pointz" ,
	scopeFieldName="pipelineOid",
	scopeType=ScopeType.CURRENT,
	geometryColumnName="geom",
	calculateType=CalculateType.SingleAnchorAndDeviation,
	anchorClass=MedianStake.class,
	anchorOid="medianStakeOid",
	deviation="relativeMileage"
)
@JdbcEntity(name="daq_cathodic_polarity_drainage")
public class CathodicPolarityDrainage extends CommonJdbcEntity {

	/**
	 * 项目oid 
	 */
	private String projectOid; 

	/**
	 * 标段oid 
	 */
	private String tendersOid; 

	/**
	 * 管线oid 
	 */
	private String pipelineOid; 

	/**
	 * 线路段oid 
	 */
	private String pipeSegmentOid; 

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
	 * 施工日期 
	 */
	private Date constructDate; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnit; 

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
	private Integer approveStatus = 0; 

	/**
	 * 备注 
	 */
	private String remarks; 

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
		super.setField("projectOid");
	}

	public String getTendersOid() {
		return tendersOid; 
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid; 
		super.setField("tendersOid");
	}

	public String getPipelineOid() {
		return pipelineOid; 
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid; 
		super.setField("pipelineOid");
	}

	public String getPipeSegmentOid() {
		return pipeSegmentOid; 
	}

	public void setPipeSegmentOid(String pipeSegmentOid) {
		this.pipeSegmentOid = pipeSegmentOid; 
		super.setField("pipeSegmentOid");
	}

	public String getEquipmentCode() {
		return equipmentCode; 
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode; 
		super.setField("equipmentCode");
	}

	public String getEquipmentName() {
		return equipmentName; 
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName; 
		super.setField("equipmentName");
	}

	public String getFactoryNum() {
		return factoryNum; 
	}

	public void setFactoryNum(String factoryNum) {
		this.factoryNum = factoryNum; 
		super.setField("factoryNum");
	}

	public String getMedianStakeOid() {
		return medianStakeOid; 
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid; 
		super.setField("medianStakeOid");
	}

	public Double getRelativeMileage() {
		return relativeMileage; 
	}

	public void setRelativeMileage(Double relativeMileage) {
		this.relativeMileage = relativeMileage; 
		super.setField("relativeMileage");
	}

	public Double getPointx() {
		return pointx; 
	}

	public void setPointx(Double pointx) {
		this.pointx = pointx; 
		super.setField("pointx");
	}

	public Double getPointy() {
		return pointy; 
	}

	public void setPointy(Double pointy) {
		this.pointy = pointy; 
		super.setField("pointy");
	}

	public String getProtectionScope() {
		return protectionScope; 
	}

	public void setProtectionScope(String protectionScope) {
		this.protectionScope = protectionScope; 
		super.setField("protectionScope");
	}

	public String getManufacturer() {
		return manufacturer; 
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer; 
		super.setField("manufacturer");
	}

	public Double getDischargeFlow() {
		return dischargeFlow; 
	}

	public void setDischargeFlow(Double dischargeFlow) {
		this.dischargeFlow = dischargeFlow; 
		super.setField("dischargeFlow");
	}

	public Double getMaxDischargeFlow() {
		return maxDischargeFlow; 
	}

	public void setMaxDischargeFlow(Double maxDischargeFlow) {
		this.maxDischargeFlow = maxDischargeFlow; 
		super.setField("maxDischargeFlow");
	}

	public Double getAvgDischargeFlow() {
		return avgDischargeFlow; 
	}

	public void setAvgDischargeFlow(Double avgDischargeFlow) {
		this.avgDischargeFlow = avgDischargeFlow; 
		super.setField("avgDischargeFlow");
	}

	public Double getMinDischargeFlow() {
		return minDischargeFlow; 
	}

	public void setMinDischargeFlow(Double minDischargeFlow) {
		this.minDischargeFlow = minDischargeFlow; 
		super.setField("minDischargeFlow");
	}

	public String getGroundBedMaterial() {
		return groundBedMaterial; 
	}

	public void setGroundBedMaterial(String groundBedMaterial) {
		this.groundBedMaterial = groundBedMaterial; 
		super.setField("groundBedMaterial");
	}

	public Double getManagePotential() {
		return managePotential; 
	}

	public void setManagePotential(Double managePotential) {
		this.managePotential = managePotential; 
		super.setField("managePotential");
	}

	public Double getGroundPotential() {
		return groundPotential; 
	}

	public void setGroundPotential(Double groundPotential) {
		this.groundPotential = groundPotential; 
		super.setField("groundPotential");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getServiceDate() {
		return serviceDate; 
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate; 
		super.setField("serviceDate");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getExpirationDate() {
		return expirationDate; 
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate; 
		super.setField("expirationDate");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getProductDate() {
		return productDate; 
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate; 
		super.setField("productDate");
	}

	public String getDrainagePurpose() {
		return drainagePurpose; 
	}

	public void setDrainagePurpose(String drainagePurpose) {
		this.drainagePurpose = drainagePurpose; 
		super.setField("drainagePurpose");
	}

	public String getDrainageType() {
		return drainageType; 
	}

	public void setDrainageType(String drainageType) {
		this.drainageType = drainageType; 
		super.setField("drainageType");
	}

	public Integer getDesignLife() {
		return designLife; 
	}

	public void setDesignLife(Integer designLife) {
		this.designLife = designLife; 
		super.setField("designLife");
	}

	public Double getAvgPotentialUndrain() {
		return avgPotentialUndrain; 
	}

	public void setAvgPotentialUndrain(Double avgPotentialUndrain) {
		this.avgPotentialUndrain = avgPotentialUndrain; 
		super.setField("avgPotentialUndrain");
	}

	public Double getAvgPotentialDrain() {
		return avgPotentialDrain; 
	}

	public void setAvgPotentialDrain(Double avgPotentialDrain) {
		this.avgPotentialDrain = avgPotentialDrain; 
		super.setField("avgPotentialDrain");
	}

	public Double getEarthingResistance() {
		return earthingResistance; 
	}

	public void setEarthingResistance(Double earthingResistance) {
		this.earthingResistance = earthingResistance; 
		super.setField("earthingResistance");
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
		super.setField("constructUnit");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getConstructDate() {
		return constructDate; 
	}

	public void setConstructDate(Date constructDate) {
		this.constructDate = constructDate; 
		super.setField("constructDate");
	}

	public String getSupervisionUnit() {
		return supervisionUnit; 
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit; 
		super.setField("supervisionUnit");
	}

	public String getSupervisionEngineer() {
		return supervisionEngineer; 
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer; 
		super.setField("supervisionEngineer");
	}

	public String getCollectionPerson() {
		return collectionPerson; 
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson; 
		super.setField("collectionPerson");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCollectionDate() {
		return collectionDate; 
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate; 
		super.setField("collectionDate");
	}

	public String getGeoState() {
		return geoState; 
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState; 
		super.setField("geoState");
	}

	public Integer getApproveStatus() {
		return approveStatus; 
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus; 
		super.setField("approveStatus");
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}
}