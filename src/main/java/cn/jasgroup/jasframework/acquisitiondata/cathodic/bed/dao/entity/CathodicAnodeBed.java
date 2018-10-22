package cn.jasgroup.jasframework.acquisitiondata.cathodic.bed.dao.entity;
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
  *<p>类描述：辅助阳极地床实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月19日 下午7:06:26。</p>
 */
@CommonSaveConfig(
	scene = "/cathodicAnodeBed/save"
)
@CommonUpdateConfig(
	scene = "/cathodicAnodeBed/update"
)
@CommonDeleteConfig(
	scene = "/cathodicAnodeBed/delete"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"pipeSegmentOid","groundBed"},name="同一线路段下地床编号不能重复"),
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
@JdbcEntity(name="daq_cathodic_anode_bed")
public class CathodicAnodeBed extends CommonJdbcEntity {

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
	 * 地床编号 
	 */
	private String groundBed; 

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
	 * 辅助阳极数量(个) 
	 */
	private Integer quantity; 

	/**
	 * 埋设方式 
	 */
	private String burialWay; 

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

	public String getGroundBed() {
		return groundBed; 
	}

	public void setGroundBed(String groundBed) {
		this.groundBed = groundBed; 
		super.setField("groundBed");
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

	public Double getPointz() {
		return pointz; 
	}

	public void setPointz(Double pointz) {
		this.pointz = pointz; 
		super.setField("pointz");
	}

	public String getInstallLocationDescription() {
		return installLocationDescription; 
	}

	public void setInstallLocationDescription(String installLocationDescription) {
		this.installLocationDescription = installLocationDescription; 
		super.setField("installLocationDescription");
	}

	public Double getBuriedDepth() {
		return buriedDepth; 
	}

	public void setBuriedDepth(Double buriedDepth) {
		this.buriedDepth = buriedDepth; 
		super.setField("buriedDepth");
	}

	public Integer getIsTemporary() {
		return isTemporary; 
	}

	public void setIsTemporary(Integer isTemporary) {
		this.isTemporary = isTemporary; 
		super.setField("isTemporary");
	}

	public Integer getDesignLife() {
		return designLife; 
	}

	public void setDesignLife(Integer designLife) {
		this.designLife = designLife; 
		super.setField("designLife");
	}

	public String getBackfillMaterial() {
		return backfillMaterial; 
	}

	public void setBackfillMaterial(String backfillMaterial) {
		this.backfillMaterial = backfillMaterial; 
		super.setField("backfillMaterial");
	}

	public Integer getQuantity() {
		return quantity; 
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity; 
		super.setField("quantity");
	}

	public String getBurialWay() {
		return burialWay; 
	}

	public void setBurialWay(String burialWay) {
		this.burialWay = burialWay; 
		super.setField("burialWay");
	}

	public Double getTotalWeight() {
		return totalWeight; 
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight; 
		super.setField("totalWeight");
	}

	public Double getCableLength() {
		return cableLength; 
	}

	public void setCableLength(Double cableLength) {
		this.cableLength = cableLength; 
		super.setField("cableLength");
	}

	public Double getProtectLength() {
		return protectLength; 
	}

	public void setProtectLength(Double protectLength) {
		this.protectLength = protectLength; 
		super.setField("protectLength");
	}

	public String getAnodeMaterialType() {
		return anodeMaterialType; 
	}

	public void setAnodeMaterialType(String anodeMaterialType) {
		this.anodeMaterialType = anodeMaterialType; 
		super.setField("anodeMaterialType");
	}

	public String getAnodeMaterialSpec() {
		return anodeMaterialSpec; 
	}

	public void setAnodeMaterialSpec(String anodeMaterialSpec) {
		this.anodeMaterialSpec = anodeMaterialSpec; 
		super.setField("anodeMaterialSpec");
	}

	public Double getAnodeResistance() {
		return anodeResistance; 
	}

	public void setAnodeResistance(Double anodeResistance) {
		this.anodeResistance = anodeResistance; 
		super.setField("anodeResistance");
	}

	public Integer getIsWaterInjection() {
		return isWaterInjection; 
	}

	public void setIsWaterInjection(Integer isWaterInjection) {
		this.isWaterInjection = isWaterInjection; 
		super.setField("isWaterInjection");
	}

	public Integer getIsAirbleed() {
		return isAirbleed; 
	}

	public void setIsAirbleed(Integer isAirbleed) {
		this.isAirbleed = isAirbleed; 
		super.setField("isAirbleed");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBurialDate() {
		return burialDate; 
	}

	public void setBurialDate(Date burialDate) {
		this.burialDate = burialDate; 
		super.setField("burialDate");
	}

	public String getConnectionPower() {
		return connectionPower; 
	}

	public void setConnectionPower(String connectionPower) {
		this.connectionPower = connectionPower; 
		super.setField("connectionPower");
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