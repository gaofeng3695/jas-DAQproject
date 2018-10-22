package cn.jasgroup.jasframework.acquisitiondata.cathodic.sacrifice.dao.entity;

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
  *<p>类描述：牺牲阳极实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月18日 上午11:00:46。</p>
 */
@CommonSaveConfig(
	scene = "/cathodicSacrificeAnode/save"
)
@CommonUpdateConfig(
	scene = "/cathodicSacrificeAnode/update"
)
@CommonDeleteConfig(
	scene = "/cathodicSacrificeAnode/delete"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"pipeSegmentOid","anodeCode"},name="同一线路段下阳极编号不能重复"),
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
@JdbcEntity(name="daq_cathodic_sacrifice_anode")
public class CathodicSacrificeAnode extends CommonJdbcEntity {

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
	 * 阳极编号 
	 */
	private String anodeCode; 

	/**
	 * 保护对象 
	 */
	private String protectObject; 

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
	 * 是否临时 
	 */
	private Integer isTemporary; 

	/**
	 * 设计寿命(年) 
	 */
	private Double designLife; 

	/**
	 * 牺牲阳极材料 
	 */
	private String anodeMaterial; 

	/**
	 * 牺牲阳极型号 
	 */
	private String anodeSpecification; 

	/**
	 * 牺牲阳极数量(支) 
	 */
	private Double andoeQuantity; 

	/**
	 * 牺牲阳极单重(kg) 
	 */
	private Double andoeWeight; 

	/**
	 * 牺牲阳极总重(kg) 
	 */
	private Double totalWeight; 

	/**
	 * 阳极间距(m) 
	 */
	private Double anodeDistance; 

	/**
	 * 与管道距离(m) 
	 */
	private Double pipeDistance; 

	/**
	 * 制造商 
	 */
	private String manufacture; 

	/**
	 * 埋设日期 
	 */
	private Date burialDate; 

	/**
	 * 阳极埋深(m) 
	 */
	private Double anodeBuriedDepth; 

	/**
	 * 铺设长度(m) 
	 */
	private Double layLength; 

	/**
	 * 填包料重量(kg) 
	 */
	private Double fillWeight; 

	/**
	 * 各接点质量及绝缘情况 
	 */
	private String insulationQuality; 

	/**
	 * 检查片埋设情况 
	 */
	private Integer tabletsCondition; 

	/**
	 * 安装位置描述 
	 */
	private String installLocationDescription; 

	/**
	 * 结论 
	 */
	private Integer conclusion; 

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

	public String getAnodeCode() {
		return anodeCode; 
	}

	public void setAnodeCode(String anodeCode) {
		this.anodeCode = anodeCode; 
		super.setField("anodeCode");
	}

	public String getProtectObject() {
		return protectObject; 
	}

	public void setProtectObject(String protectObject) {
		this.protectObject = protectObject; 
		super.setField("protectObject");
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

	public Integer getIsTemporary() {
		return isTemporary; 
	}

	public void setIsTemporary(Integer isTemporary) {
		this.isTemporary = isTemporary; 
		super.setField("isTemporary");
	}

	public Double getDesignLife() {
		return designLife; 
	}

	public void setDesignLife(Double designLife) {
		this.designLife = designLife; 
		super.setField("designLife");
	}

	public String getAnodeMaterial() {
		return anodeMaterial; 
	}

	public void setAnodeMaterial(String anodeMaterial) {
		this.anodeMaterial = anodeMaterial; 
		super.setField("anodeMaterial");
	}

	public String getAnodeSpecification() {
		return anodeSpecification; 
	}

	public void setAnodeSpecification(String anodeSpecification) {
		this.anodeSpecification = anodeSpecification; 
		super.setField("anodeSpecification");
	}

	public Double getAndoeQuantity() {
		return andoeQuantity; 
	}

	public void setAndoeQuantity(Double andoeQuantity) {
		this.andoeQuantity = andoeQuantity; 
		super.setField("andoeQuantity");
	}

	public Double getAndoeWeight() {
		return andoeWeight; 
	}

	public void setAndoeWeight(Double andoeWeight) {
		this.andoeWeight = andoeWeight; 
		super.setField("andoeWeight");
	}

	public Double getTotalWeight() {
		return totalWeight; 
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight; 
		super.setField("totalWeight");
	}

	public Double getAnodeDistance() {
		return anodeDistance; 
	}

	public void setAnodeDistance(Double anodeDistance) {
		this.anodeDistance = anodeDistance; 
		super.setField("anodeDistance");
	}

	public Double getPipeDistance() {
		return pipeDistance; 
	}

	public void setPipeDistance(Double pipeDistance) {
		this.pipeDistance = pipeDistance; 
		super.setField("pipeDistance");
	}

	public String getManufacture() {
		return manufacture; 
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture; 
		super.setField("manufacture");
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

	public Double getAnodeBuriedDepth() {
		return anodeBuriedDepth; 
	}

	public void setAnodeBuriedDepth(Double anodeBuriedDepth) {
		this.anodeBuriedDepth = anodeBuriedDepth; 
		super.setField("anodeBuriedDepth");
	}

	public Double getLayLength() {
		return layLength; 
	}

	public void setLayLength(Double layLength) {
		this.layLength = layLength; 
		super.setField("layLength");
	}

	public Double getFillWeight() {
		return fillWeight; 
	}

	public void setFillWeight(Double fillWeight) {
		this.fillWeight = fillWeight; 
		super.setField("fillWeight");
	}

	public String getInsulationQuality() {
		return insulationQuality; 
	}

	public void setInsulationQuality(String insulationQuality) {
		this.insulationQuality = insulationQuality; 
		super.setField("insulationQuality");
	}

	public Integer getTabletsCondition() {
		return tabletsCondition; 
	}

	public void setTabletsCondition(Integer tabletsCondition) {
		this.tabletsCondition = tabletsCondition; 
		super.setField("tabletsCondition");
	}

	public String getInstallLocationDescription() {
		return installLocationDescription; 
	}

	public void setInstallLocationDescription(String installLocationDescription) {
		this.installLocationDescription = installLocationDescription; 
		super.setField("installLocationDescription");
	}

	public Integer getConclusion() {
		return conclusion; 
	}

	public void setConclusion(Integer conclusion) {
		this.conclusion = conclusion; 
		super.setField("conclusion");
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