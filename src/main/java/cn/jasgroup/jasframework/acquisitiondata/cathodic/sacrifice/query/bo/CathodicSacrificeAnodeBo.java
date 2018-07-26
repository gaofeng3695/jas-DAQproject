package cn.jasgroup.jasframework.acquisitiondata.cathodic.sacrifice.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
  *<p>类描述：牺牲阳极Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月18日 上午11:06:10。</p>
 */
public class CathodicSacrificeAnodeBo extends CommonBaseBo{
	
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
	 * 牺牲阳极材料名称
	 */
	private String anodeMaterialName; 

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
	 * 各接点质量及绝缘情况名称 
	 */
	private String insulationQualityName; 

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

	public String getPipeSegmentName() {
		return pipeSegmentName;
	}

	public void setPipeSegmentName(String pipeSegmentName) {
		this.pipeSegmentName = pipeSegmentName;
	}

	public String getMedianStakeCode() {
		return medianStakeCode;
	}

	public void setMedianStakeCode(String medianStakeCode) {
		this.medianStakeCode = medianStakeCode;
	}

	public String getAnodeMaterialName() {
		return anodeMaterialName;
	}

	public void setAnodeMaterialName(String anodeMaterialName) {
		this.anodeMaterialName = anodeMaterialName;
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

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getPipeSegmentOid() {
		return pipeSegmentOid;
	}

	public void setPipeSegmentOid(String pipeSegmentOid) {
		this.pipeSegmentOid = pipeSegmentOid;
	}

	public String getAnodeCode() {
		return anodeCode;
	}

	public void setAnodeCode(String anodeCode) {
		this.anodeCode = anodeCode;
	}

	public String getProtectObject() {
		return protectObject;
	}

	public void setProtectObject(String protectObject) {
		this.protectObject = protectObject;
	}

	public String getMedianStakeOid() {
		return medianStakeOid;
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid;
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

	public Integer getIsTemporary() {
		return isTemporary;
	}

	public void setIsTemporary(Integer isTemporary) {
		this.isTemporary = isTemporary;
	}

	public Double getDesignLife() {
		return designLife;
	}

	public void setDesignLife(Double designLife) {
		this.designLife = designLife;
	}

	public String getAnodeMaterial() {
		return anodeMaterial;
	}

	public void setAnodeMaterial(String anodeMaterial) {
		this.anodeMaterial = anodeMaterial;
	}

	public String getAnodeSpecification() {
		return anodeSpecification;
	}

	public void setAnodeSpecification(String anodeSpecification) {
		this.anodeSpecification = anodeSpecification;
	}

	public Double getAndoeQuantity() {
		return andoeQuantity;
	}

	public void setAndoeQuantity(Double andoeQuantity) {
		this.andoeQuantity = andoeQuantity;
	}

	public Double getAndoeWeight() {
		return andoeWeight;
	}

	public void setAndoeWeight(Double andoeWeight) {
		this.andoeWeight = andoeWeight;
	}

	public Double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(Double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public Double getAnodeDistance() {
		return anodeDistance;
	}

	public void setAnodeDistance(Double anodeDistance) {
		this.anodeDistance = anodeDistance;
	}

	public Double getPipeDistance() {
		return pipeDistance;
	}

	public void setPipeDistance(Double pipeDistance) {
		this.pipeDistance = pipeDistance;
	}

	public String getManufacture() {
		return manufacture;
	}

	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBurialDate() {
		return burialDate;
	}

	public void setBurialDate(Date burialDate) {
		this.burialDate = burialDate;
	}

	public Double getAnodeBuriedDepth() {
		return anodeBuriedDepth;
	}

	public void setAnodeBuriedDepth(Double anodeBuriedDepth) {
		this.anodeBuriedDepth = anodeBuriedDepth;
	}

	public Double getLayLength() {
		return layLength;
	}

	public void setLayLength(Double layLength) {
		this.layLength = layLength;
	}

	public Double getFillWeight() {
		return fillWeight;
	}

	public void setFillWeight(Double fillWeight) {
		this.fillWeight = fillWeight;
	}

	public String getInsulationQuality() {
		return insulationQuality;
	}

	public void setInsulationQuality(String insulationQuality) {
		this.insulationQuality = insulationQuality;
	}

	public String getInsulationQualityName() {
		return insulationQualityName;
	}

	public void setInsulationQualityName(String insulationQualityName) {
		this.insulationQualityName = insulationQualityName;
	}

	public Integer getTabletsCondition() {
		return tabletsCondition;
	}

	public void setTabletsCondition(Integer tabletsCondition) {
		this.tabletsCondition = tabletsCondition;
	}

	public String getInstallLocationDescription() {
		return installLocationDescription;
	}

	public void setInstallLocationDescription(String installLocationDescription) {
		this.installLocationDescription = installLocationDescription;
	}

	public Integer getConclusion() {
		return conclusion;
	}

	public void setConclusion(Integer conclusion) {
		this.conclusion = conclusion;
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
