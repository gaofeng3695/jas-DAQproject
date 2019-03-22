package cn.jasgroup.jasframework.acquisitiondata.mesolow.mvvalve.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.domain.utils.DomainUtil;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：中低压阀门信息entity。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月25日 上午10:34:39。</p>
 */

@CommonSaveConfig(
        scene = "/mvValveInfo/save"
)
@CommonUpdateConfig(
        scene = "/mvValveInfo/update"
)
@CommonDeleteConfig(
        scene = "/mvValveInfo/delete"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"projectOid","valveCode"},
            name="同一项目下阀门编号不能重复"
        )
    }
)
@Point(
	x = "pointx",
	y = "pointy",
	z = "pointz",
	geometryColumnName = "geom",
	calculateType = CalculateType.Coordinates
)
@JdbcEntity(name="daq_mv_valve_info")
public class MvValveInfo extends CommonJdbcEntity {

	/**
	 * 工程oid 
	 */
	private String projectOid; 

	/**
	 * 节点oid 
	 */
	private String pipeNodeOid; 

	/**
	 * 阀门编号 
	 */
	private String valveCode; 

	/**
	 * 生产厂家 
	 */
	private String manufacturer; 

	/**
	 * 阀门规格 
	 */
	private String valveSpec; 

	/**
	 * 阀门类型，域值：valve_type_domain 
	 */
	private String valveType; 

	/**
	 * 阀门材质{1:刚;2:pe} 
	 */
	private Integer valveMaterial; 

	/**
	 * 埋设方式{1:直埋;2:阀门井} 
	 */
	private Integer burialMethod; 

	/**
	 * 阀门公称直径 
	 */
	private Double nominalDiameter; 

	/**
	 * 阀门公称压力 
	 */
	private Double nominalPressure; 

	/**
	 * 坐标x 
	 */
	private Double pointx; 

	/**
	 * 坐标y 
	 */
	private Double pointy; 

	/**
	 * 管顶高程(m) 
	 */
	private Double pointz; 

	/**
	 * 埋深(m) 
	 */
	private Double buriedDepth; 

	/**
	 * 是否设置电子标签{0:否;1:是} 
	 */
	private Integer isElectronicLabel; 

	/**
	 * 电子标签类型,域值：electronic_label_type_domai 
	 */
	private String electronicLabelType; 

	/**
	 * 阀门连接方式,域值：valve_connection_method_domain 
	 */
	private String valveConnectionMethod; 

	/**
	 * 阀门是否重型井盖{0:否;1:是} 
	 */
	private Integer isHeavyShaftCover; 

	/**
	 * 井盖类型{1:矩形;2:圆形} 
	 */
	private Integer wellCoverType; 

	/**
	 * 井盖材质,域值：well_cover_material_domain 
	 */
	private String wellCoverMaterial; 

	/**
	 * 放散管径 
	 */
	private Double dischargePipe; 

	/**
	 * 阀门放散管信息 
	 */
	private String valveDischargePipeInfo; 

	/**
	 * 采集单位 
	 */
	private String constructUnit; 

	/**
	 * 采集人员 
	 */
	private String collectionPerson; 

	/**
	 * 采集日期 
	 */
	private Date collectionDate; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnit; 

	/**
	 * 监理工程师 
	 */
	private String supervisionEngineer; 

	/**
	 * 空间数据状态 
	 */
	private String geoState; 

	/**
	 * 审核状态 
	 */
	private Integer approveStatus=0; 

	/**
	 * 备注 
	 */
	private String remarks;

	/**
	 * 工程名称 
	 */
	private String projectName; 

	/**
	 * 节点名称
	 */
	private String pipeNodeCode; 

	/**
	 * 阀门类型
	 */
	private String valveTypeName; 

	/**
	 * 阀门材质
	 */
	private String valveMaterialName; 

	/**
	 * 埋设方式 
	 */
	private String burialMethodName; 

	/**
	 * 是否设置电子标签
	 */
	private String isElectronicLabelName; 

	/**
	 * 电子标签类型 
	 */
	private String electronicLabelTypeName; 

	/**
	 * 阀门连接方式 
	 */
	private String valveConnectionMethodName; 

	/**
	 * 阀门是否重型井盖
	 */
	private String isHeavyShaftCoverName; 

	/**
	 * 井盖类型
	 */
	private String wellCoverTypeName; 

	/**
	 * 井盖材质 
	 */
	private String wellCoverMaterialName; 

	/**
	 * 采集单位 
	 */
	private String constructUnitName; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnitName; 

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
		super.setField("projectOid");
	}

	public String getPipeNodeOid() {
		return pipeNodeOid; 
	}

	public void setPipeNodeOid(String pipeNodeOid) {
		this.pipeNodeOid = pipeNodeOid; 
		super.setField("pipeNodeOid");
	}

	public String getValveCode() {
		return valveCode; 
	}

	public void setValveCode(String valveCode) {
		this.valveCode = valveCode; 
		super.setField("valveCode");
	}

	public String getManufacturer() {
		return manufacturer; 
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer; 
		super.setField("manufacturer");
	}

	public String getValveSpec() {
		return valveSpec; 
	}

	public void setValveSpec(String valveSpec) {
		this.valveSpec = valveSpec; 
		super.setField("valveSpec");
	}

	public String getValveType() {
		return valveType; 
	}

	public void setValveType(String valveType) {
		this.valveType = valveType; 
		super.setField("valveType");
	}

	public Integer getValveMaterial() {
		return valveMaterial; 
	}

	public void setValveMaterial(Integer valveMaterial) {
		this.valveMaterial = valveMaterial; 
		super.setField("valveMaterial");
	}

	public Integer getBurialMethod() {
		return burialMethod; 
	}

	public void setBurialMethod(Integer burialMethod) {
		this.burialMethod = burialMethod; 
		super.setField("burialMethod");
	}

	public Double getNominalDiameter() {
		return nominalDiameter; 
	}

	public void setNominalDiameter(Double nominalDiameter) {
		this.nominalDiameter = nominalDiameter; 
		super.setField("nominalDiameter");
	}

	public Double getNominalPressure() {
		return nominalPressure; 
	}

	public void setNominalPressure(Double nominalPressure) {
		this.nominalPressure = nominalPressure; 
		super.setField("nominalPressure");
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

	public Double getBuriedDepth() {
		return buriedDepth; 
	}

	public void setBuriedDepth(Double buriedDepth) {
		this.buriedDepth = buriedDepth; 
		super.setField("buriedDepth");
	}

	public Integer getIsElectronicLabel() {
		return isElectronicLabel; 
	}

	public void setIsElectronicLabel(Integer isElectronicLabel) {
		this.isElectronicLabel = isElectronicLabel; 
		super.setField("isElectronicLabel");
	}

	public String getElectronicLabelType() {
		return electronicLabelType; 
	}

	public void setElectronicLabelType(String electronicLabelType) {
		this.electronicLabelType = electronicLabelType; 
		super.setField("electronicLabelType");
	}

	public String getValveConnectionMethod() {
		return valveConnectionMethod; 
	}

	public void setValveConnectionMethod(String valveConnectionMethod) {
		this.valveConnectionMethod = valveConnectionMethod; 
		super.setField("valveConnectionMethod");
	}

	public Integer getIsHeavyShaftCover() {
		return isHeavyShaftCover; 
	}

	public void setIsHeavyShaftCover(Integer isHeavyShaftCover) {
		this.isHeavyShaftCover = isHeavyShaftCover; 
		super.setField("isHeavyShaftCover");
	}

	public Integer getWellCoverType() {
		return wellCoverType; 
	}

	public void setWellCoverType(Integer wellCoverType) {
		this.wellCoverType = wellCoverType; 
		super.setField("wellCoverType");
	}

	public String getWellCoverMaterial() {
		return wellCoverMaterial; 
	}

	public void setWellCoverMaterial(String wellCoverMaterial) {
		this.wellCoverMaterial = wellCoverMaterial; 
		super.setField("wellCoverMaterial");
	}

	public Double getDischargePipe() {
		return dischargePipe; 
	}

	public void setDischargePipe(Double dischargePipe) {
		this.dischargePipe = dischargePipe; 
		super.setField("dischargePipe");
	}

	public String getValveDischargePipeInfo() {
		return valveDischargePipeInfo; 
	}

	public void setValveDischargePipeInfo(String valveDischargePipeInfo) {
		this.valveDischargePipeInfo = valveDischargePipeInfo; 
		super.setField("valveDischargePipeInfo");
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
		super.setField("constructUnit");
	}

	public String getCollectionPerson() {
		return collectionPerson; 
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson; 
		super.setField("collectionPerson");
	}

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
	public Date getCollectionDate() {
		return collectionDate; 
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate; 
		super.setField("collectionDate");
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

	@Transient
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Transient
	public String getPipeNodeCode() {
		return pipeNodeCode;
	}

	public void setPipeNodeCode(String pipeNodeCode) {
		this.pipeNodeCode = pipeNodeCode;
	}

	@Transient
	public String getValveTypeName() {
		return DomainUtil.getValue("mv_valve_type_domain", valveType);
	}

	public void setValveTypeName(String valveTypeName) {
	}

	@Transient
	public String getValveMaterialName() {
		return valveMaterialName;
	}

	public void setValveMaterialName(String valveMaterialName) {
		this.valveMaterialName = valveMaterialName;
	}

	@Transient
	public String getBurialMethodName() {
		return burialMethodName;
	}

	public void setBurialMethodName(String burialMethodName) {
		this.burialMethodName = burialMethodName;
	}

	@Transient
	public String getIsElectronicLabelName() {
		return isElectronicLabelName;
	}

	public void setIsElectronicLabelName(String isElectronicLabelName) {
		this.isElectronicLabelName = isElectronicLabelName;
	}

	@Transient
	public String getElectronicLabelTypeName() {
		return DomainUtil.getValue("electronic_label_type_domain", electronicLabelType);
	}

	public void setElectronicLabelTypeName(String electronicLabelTypeName) {
	}

	@Transient
	public String getValveConnectionMethodName() {
		return DomainUtil.getValue("valve_connection_method_domain", valveConnectionMethod);
	}

	public void setValveConnectionMethodName(String valveConnectionMethodName) {
	}

	@Transient
	public String getIsHeavyShaftCoverName() {
		return isHeavyShaftCoverName;
	}

	public void setIsHeavyShaftCoverName(String isHeavyShaftCoverName) {
		this.isHeavyShaftCoverName = isHeavyShaftCoverName;
	}

	@Transient
	public String getWellCoverTypeName() {
		return wellCoverTypeName;
	}

	public void setWellCoverTypeName(String wellCoverTypeName) {
		this.wellCoverTypeName = wellCoverTypeName;
	}

	@Transient
	public String getWellCoverMaterialName() {
		return DomainUtil.getValue("well_cover_material_domain", wellCoverMaterial);
	}

	public void setWellCoverMaterialName(String wellCoverMaterialName) {
	}

	@Transient
	public String getConstructUnitName() {
		return constructUnitName;
	}

	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}

	@Transient
	public String getSupervisionUnitName() {
		return supervisionUnitName;
	}

	public void setSupervisionUnitName(String supervisionUnitName) {
		this.supervisionUnitName = supervisionUnitName;
	}
	
}
