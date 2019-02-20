package cn.jasgroup.jasframework.acquisitiondata.material.base.closure.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

public class ClosureBo extends CommonBaseBo {

	/**
	 * oid
	 */
	private String oid ;
	
	/**
	 * 封堵物编号
	 */
	private String closureCode;

	/**
	 * 封堵物类型
	 */
	private String closureType;

	/**
	 * 材质
	 */
	private String material;

	/**
	 * 钢材等级
	 */
	private String steelGrade;

	/**
	 * 钢材等级名称
	 */
	private String steel_grade_name;

	/**
	 * 管道外壁直径(mm)
	 */
	private Double outsideDiameter;

	/**
	 * 管道壁厚(mm)
	 */
	private Double wallThickness;

	/**
	 * 连接方式
	 */
	private String connectionMethods;

	/**
	 * 连接方式名称
	 */
	private String connectionMethodsName;

	/**
	 * 防腐方式
	 */
	private String coatingMethods;

	/**
	 * 防腐方式名称
	 */
	private String coatingMethodsName;

	/**
	 * 生产厂家
	 */
	private String manufacturer;

	/**
	 * 生产日期
	 */
	private Date manufacturerDate;

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
	 * 是否使用
	 */
	private Integer isUse;

	/**
	 * 备注
	 */
	private String remarks;
	
	/**
	 * 提交状态
	 */
	private Integer commitStatus;

	public Integer getCommitStatus() {
		return commitStatus;
	}

	public void setCommitStatus(Integer commitStatus) {
		this.commitStatus = commitStatus;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getClosureCode() {
		return closureCode;
	}

	public void setClosureCode(String closureCode) {
		this.closureCode = closureCode;
	}

	public String getClosureType() {
		return closureType;
	}

	public void setClosureType(String closureType) {
		this.closureType = closureType;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getSteelGrade() {
		return steelGrade;
	}

	public void setSteelGrade(String steelGrade) {
		this.steelGrade = steelGrade;
	}

	public String getSteel_grade_name() {
		return steel_grade_name;
	}

	public void setSteel_grade_name(String steel_grade_name) {
		this.steel_grade_name = steel_grade_name;
	}

	public Double getOutsideDiameter() {
		return outsideDiameter;
	}

	public void setOutsideDiameter(Double outsideDiameter) {
		this.outsideDiameter = outsideDiameter;
	}

	public Double getWallThickness() {
		return wallThickness;
	}

	public void setWallThickness(Double wallThickness) {
		this.wallThickness = wallThickness;
	}

	public String getConnectionMethods() {
		return connectionMethods;
	}

	public void setConnectionMethods(String connectionMethods) {
		this.connectionMethods = connectionMethods;
	}

	public String getConnectionMethodsName() {
		return connectionMethodsName;
	}

	public void setConnectionMethodsName(String connectionMethodsName) {
		this.connectionMethodsName = connectionMethodsName;
	}

	public String getCoatingMethods() {
		return coatingMethods;
	}

	public void setCoatingMethods(String coatingMethods) {
		this.coatingMethods = coatingMethods;
	}

	public String getCoatingMethodsName() {
		return coatingMethodsName;
	}

	public void setCoatingMethodsName(String coatingMethodsName) {
		this.coatingMethodsName = coatingMethodsName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getManufacturerDate() {
		return manufacturerDate;
	}

	public void setManufacturerDate(Date manufacturerDate) {
		this.manufacturerDate = manufacturerDate;
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

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
