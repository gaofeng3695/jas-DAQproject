package cn.jasgroup.jasframework.acquisitiondata.material.pipe.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.annotation.CommonGetConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：钢管实体。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月10日 上午9:11:18。</p>
 */
@CommonGetConfig(
	scene = "/steelPipe/get"
)
@JdbcEntity(name="daq_material_pipe")
public class SteelPipe extends CommonJdbcEntity{

	/**
	 *  项目oid
	 */
	private String projectOid; 

	/** 
	 * 管线oid
	 */
	private String pipelineOid; 

	/** 
	 * 标段oid
	 */
	private String tendersOid; 

	/**
	 *  钢管编号 
	 */
	private String pipeCode; 

	/**
	 *  材质
	 */
	private String material; 

	/** 
	 * 制造工艺
	 */
	private String pipeTechnology; 

	/** 
	 * 管径(mm) 
	 */
	private Double pipeDiameter; 

	/**
	 *  壁厚(mm）
	 */
	private Double wallThickness; 

	/** 
	 * 炉批号
	 */
	private String stoveSerialNum; 

	/**
	 *  钢管成型方式
	 */
	private String pipeFormingMethod; 

	/** 
	 * 长度(m)
	 */
	private Double pipeLength; 

	/**
	 *  重量(吨)
	 */
	private Double pipeWeight; 

	/**
	 * 管材等级
	 */
	private String grade; 

	/** 
	 * 钢管生产日期 
	 */
	private Date manufactureDate; 

	/**
	 *  钢管生产厂家
	 */
	private String manufactureFactory; 

	/** 
	 * 成品管质量证明书编号 
	 */
	private String pipeQualityCertificateNum; 

	/**
	 *  钢板号 
	 */
	private String steelPlateNum; 

	/**
	 *  钢板质量证明书编号
	 */
	private String steelPlateQualityCertificateNum; 

	/** 
	 * 外防腐类型 
	 */
	private String externalCoatingType; 

	/** 
	 * 外防腐等级
	 */
	private String externalCoatingGrade; 

	/**
	 *  内防腐材料
	 */
	private String internalCoatingMaterial; 

	/**
	 *  内涂层厚度（mm）
	 */
	private Double internalCoatingThinkness; 

	/**
	 *  防腐号
	 */
	private String coatingNum; 

	/** 
	 * 防腐日期
	 */
	private Date coatingDate; 

	/** 
	 * 防腐证书编号
	 */
	private String coatingCertificateNum; 

	/**
	 *  防腐加工厂家
	 */
	private String coatingFactory; 

	/** 
	 * 是否切管
	 */
	private Integer isCut; 

	/** 
	 * 是否使用 
	 */
	private Integer isUse; 

	/** 
	 * 是否热弯 
	 */
	private Integer isColdBend; 

	/** 
	 * 是否热检查
	 */
	private Integer isCheck; 

	/** 
	 * 是否是子管
	 */
	private Integer isChild; 
	
	/** 
	 * 前端是否使用
	 */
	private Integer frontIsUse; 
	
	/** 
	 * 后端是否使用
	 */
	private Integer backIsUse; 

	/** 
	 * 物料编码
	 */
	private String materielCode; 
	
	/** 
	 * 出厂日期
	 */
	private Date productionDate; 

	/** 
	 * 备注
	 */
	private String remarks; 
	/**
	 * 施工单位 
	 */
	private String constructUnit; 
	
	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
		super.setField("projectOid");
	}

	public String getPipelineOid() {
		return pipelineOid; 
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid; 
		super.setField("pipelineOid");
	}

	public String getTendersOid() {
		return tendersOid; 
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid; 
		super.setField("tendersOid");
	}

	public String getPipeCode() {
		return pipeCode; 
	}

	public void setPipeCode(String pipeCode) {
		this.pipeCode = pipeCode; 
		super.setField("pipeCode");
	}

	public String getMaterial() {
		return material; 
	}

	public void setMaterial(String material) {
		this.material = material; 
		super.setField("material");
	}

	public String getPipeTechnology() {
		return pipeTechnology; 
	}

	public void setPipeTechnology(String pipeTechnology) {
		this.pipeTechnology = pipeTechnology; 
		super.setField("pipeTechnology");
	}

	public Double getPipeDiameter() {
		return pipeDiameter; 
	}

	public void setPipeDiameter(Double pipeDiameter) {
		this.pipeDiameter = pipeDiameter; 
		super.setField("pipeDiameter");
	}

	public Double getWallThickness() {
		return wallThickness; 
	}

	public void setWallThickness(Double wallThickness) {
		this.wallThickness = wallThickness; 
		super.setField("wallThickness");
	}

	public String getStoveSerialNum() {
		return stoveSerialNum; 
	}

	public void setStoveSerialNum(String stoveSerialNum) {
		this.stoveSerialNum = stoveSerialNum; ; 
		super.setField("stoveSerialNum");
	}

	public String getPipeFormingMethod() {
		return pipeFormingMethod; 
	}

	public void setPipeFormingMethod(String pipeFormingMethod) {
		this.pipeFormingMethod = pipeFormingMethod; 
		super.setField("pipeFormingMethod");
	}

	public Double getPipeLength() {
		return pipeLength; 
	}

	public void setPipeLength(Double pipeLength) {
		this.pipeLength = pipeLength; 
		super.setField("pipeLength");
	}

	public Double getPipeWeight() {
		return pipeWeight; 
	}

	public void setPipeWeight(Double pipeWeight) {
		this.pipeWeight = pipeWeight; 
		super.setField("pipeWeight");
	}

	public String getGrade() {
		return grade; 
	}

	public void setGrade(String grade) {
		this.grade = grade; 
		super.setField("grade");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getManufactureDate() {
		return manufactureDate; 
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate; 
		super.setField("manufactureDate");
	}

	public String getManufactureFactory() {
		return manufactureFactory; 
	}

	public void setManufactureFactory(String manufactureFactory) {
		this.manufactureFactory = manufactureFactory; 
		super.setField("manufactureFactory");
	}

	public String getPipeQualityCertificateNum() {
		return pipeQualityCertificateNum; 
	}

	public void setPipeQualityCertificateNum(String pipeQualityCertificateNum) {
		this.pipeQualityCertificateNum = pipeQualityCertificateNum; 
		super.setField("pipeQualityCertificateNum");
	}

	public String getSteelPlateNum() {
		return steelPlateNum; 
	}

	public void setSteelPlateNum(String steelPlateNum) {
		this.steelPlateNum = steelPlateNum; 
		super.setField("steelPlateNum");
	}

	public String getSteelPlateQualityCertificateNum() {
		return steelPlateQualityCertificateNum; 
	}

	public void setSteelPlateQualityCertificateNum(String steelPlateQualityCertificateNum) {
		this.steelPlateQualityCertificateNum = steelPlateQualityCertificateNum; 
		super.setField("steelPlateQualityCertificateNum");
	}

	public String getExternalCoatingType() {
		return externalCoatingType; 
	}

	public void setExternalCoatingType(String externalCoatingType) {
		this.externalCoatingType = externalCoatingType; 
		super.setField("externalCoatingType");
	}

	public String getExternalCoatingGrade() {
		return externalCoatingGrade; 
	}

	public void setExternalCoatingGrade(String externalCoatingGrade) {
		this.externalCoatingGrade = externalCoatingGrade; 
		super.setField("externalCoatingGrade");
	}

	public String getInternalCoatingMaterial() {
		return internalCoatingMaterial; 
	}

	public void setInternalCoatingMaterial(String internalCoatingMaterial) {
		this.internalCoatingMaterial = internalCoatingMaterial; 
		super.setField("internalCoatingMaterial");
	}

	public Double getInternalCoatingThinkness() {
		return internalCoatingThinkness; 
	}

	public void setInternalCoatingThinkness(Double internalCoatingThinkness) {
		this.internalCoatingThinkness = internalCoatingThinkness; 
		super.setField("internalCoatingThinkness");
	}

	public String getCoatingNum() {
		return coatingNum; 
	}

	public void setCoatingNum(String coatingNum) {
		this.coatingNum = coatingNum; 
		super.setField("coatingNum");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCoatingDate() {
		return coatingDate; 
	}

	public void setCoatingDate(Date coatingDate) {
		this.coatingDate = coatingDate; 
		super.setField("coatingDate");
	}

	public String getCoatingCertificateNum() {
		return coatingCertificateNum; 
	}

	public void setCoatingCertificateNum(String coatingCertificateNum) {
		this.coatingCertificateNum = coatingCertificateNum; 
		super.setField("coatingCertificateNum");
	}

	public String getCoatingFactory() {
		return coatingFactory; 
	}

	public void setCoatingFactory(String coatingFactory) {
		this.coatingFactory = coatingFactory; 
		super.setField("coatingFactory");
	}

	public Integer getIsCut() {
		return isCut; 
	}

	public void setIsCut(Integer isCut) {
		this.isCut = isCut; 
		super.setField("isCut");
	}

	public Integer getIsUse() {
		return isUse; 
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse; 
		super.setField("isUse");
	}

	public Integer getIsColdBend() {
		return isColdBend;
	}

	public void setIsColdBend(Integer isColdBend) {
		this.isColdBend = isColdBend;
		super.setField("isColdBend");
	}
	
	public Integer getIsChild() {
		return isChild;
	}
	
	public void setIsChild(Integer isChild) {
		this.isChild = isChild;
		super.setField("isChild");
	}

	public Integer getFrontIsUse() {
		return frontIsUse;
	}

	public void setFrontIsUse(Integer frontIsUse) {
		this.frontIsUse = frontIsUse;
		super.setField("frontIsUse");
	}

	public Integer getBackIsUse() {
		return backIsUse;
	}

	public void setBackIsUse(Integer backIsUse) {
		this.backIsUse = backIsUse;
		super.setField("backIsUse");
	}

	public String getMaterielCode() {
		return materielCode;
	}

	public void setMaterielCode(String materielCode) {
		this.materielCode = materielCode;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
		super.setField("productionDate");
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}
	
	public Integer getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
		super.setField("isCheck");
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
		super.setField("constructUnit");
	}

}
