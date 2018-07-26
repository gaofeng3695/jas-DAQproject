package cn.jasgroup.jasframework.acquisitiondata.cathodic.bed.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
  *<p>类描述：辅助阳极地床Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月19日 下午7:09:00。</p>
 */
public class CathodicAnodeBedBo extends CommonBaseBo{
	
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
	 * 地床编号 
	 */
	private String groundBed; 

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
	 * 高程(m) 
	 */
	private Double pointz; 

	/**
	 * 安装位置描述 
	 */
	private String installLocationDescription; 

	/**
	 * 埋深(m) 
	 */
	private Double buriedDepth; 

	/**
	 * 是否临时 
	 */
	private Integer isTemporary; 

	/**
	 * 设计寿命(年) 
	 */
	private Integer designLife; 

	/**
	 * 回填材料 
	 */
	private String backfillMaterial; 

	/**
	 * 回填材料名称 
	 */
	private String backfillMaterialName; 

	/**
	 * 辅助阳极数量(个) 
	 */
	private Integer quantity; 

	/**
	 * 埋设方式 
	 */
	private String burialWay; 

	/**
	 * 埋设方式名称 
	 */
	private String burialWayName; 

	/**
	 * 总重(kg) 
	 */
	private Double totalWeight; 

	/**
	 * 电缆长度(m) 
	 */
	private Double cableLength; 

	/**
	 * 保护长度(m) 
	 */
	private Double protectLength; 

	/**
	 * 阳极材料类型 
	 */
	private String anodeMaterialType; 

	/**
	 * 阳极材料规格 
	 */
	private String anodeMaterialSpec; 

	/**
	 * 阳极电阻(Ω) 
	 */
	private Double anodeResistance; 

	/**
	 * 是否有注水系统 
	 */
	private Integer isWaterInjection; 

	/**
	 * 是否有排气孔 
	 */
	private Integer isAirbleed; 

	/**
	 * 埋设日期 
	 */
	private Date burialDate; 

	/**
	 * 地床连接阴保电源 
	 */
	private String connectionPower; 

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

	public String getGroundBed() {
		return groundBed;
	}

	public void setGroundBed(String groundBed) {
		this.groundBed = groundBed;
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

	public String getInstallLocationDescription() {
		return installLocationDescription;
	}

	public void setInstallLocationDescription(String installLocationDescription) {
		this.installLocationDescription = installLocationDescription;
	}

	public Double getBuriedDepth() {
		return buriedDepth;
	}

	public void setBuriedDepth(Double buriedDepth) {
		this.buriedDepth = buriedDepth;
	}

	public Integer getIsTemporary() {
		return isTemporary;
	}

	public void setIsTemporary(Integer isTemporary) {
		this.isTemporary = isTemporary;
	}

	public Integer getDesignLife() {
		return designLife;
	}

	public void setDesignLife(Integer designLife) {
		this.designLife = designLife;
	}

	public String getBackfillMaterial() {
		return backfillMaterial;
	}

	public void setBackfillMaterial(String backfillMaterial) {
		this.backfillMaterial = backfillMaterial;
	}

	public String getBackfillMaterialName() {
		return backfillMaterialName;
	}

	public void setBackfillMaterialName(String backfillMaterialName) {
		this.backfillMaterialName = backfillMaterialName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBurialWay() {
		return burialWay;
	}

	public void setBurialWay(String burialWay) {
		this.burialWay = burialWay;
	}

	public String getBurialWayName() {
		return burialWayName;
	}

	public void setBurialWayName(String burialWayName) {
		this.burialWayName = burialWayName;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Double getCableLength() {
		return cableLength;
	}

	public void setCableLength(Double cableLength) {
		this.cableLength = cableLength;
	}

	public Double getProtectLength() {
		return protectLength;
	}

	public void setProtectLength(Double protectLength) {
		this.protectLength = protectLength;
	}

	public String getAnodeMaterialType() {
		return anodeMaterialType;
	}

	public void setAnodeMaterialType(String anodeMaterialType) {
		this.anodeMaterialType = anodeMaterialType;
	}

	public String getAnodeMaterialSpec() {
		return anodeMaterialSpec;
	}

	public void setAnodeMaterialSpec(String anodeMaterialSpec) {
		this.anodeMaterialSpec = anodeMaterialSpec;
	}

	public Double getAnodeResistance() {
		return anodeResistance;
	}

	public void setAnodeResistance(Double anodeResistance) {
		this.anodeResistance = anodeResistance;
	}

	public Integer getIsWaterInjection() {
		return isWaterInjection;
	}

	public void setIsWaterInjection(Integer isWaterInjection) {
		this.isWaterInjection = isWaterInjection;
	}

	public Integer getIsAirbleed() {
		return isAirbleed;
	}

	public void setIsAirbleed(Integer isAirbleed) {
		this.isAirbleed = isAirbleed;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBurialDate() {
		return burialDate;
	}

	public void setBurialDate(Date burialDate) {
		this.burialDate = burialDate;
	}

	public String getConnectionPower() {
		return connectionPower;
	}

	public void setConnectionPower(String connectionPower) {
		this.connectionPower = connectionPower;
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
