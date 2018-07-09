package cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CheckPipeColdBendingBo {

	/**
	 * oid
	 */
	private String oid ;

	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 监工单位oid
	 */
	private String constructionUnit;

	/**
	 * 监工单位名称
	 */
	private String unitName;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段名称
	 */
	private String tendersName;

	/**
	 * 冷弯管编号
	 */
	private String pipeColdBendingCode; 

	/**
	 *  合格证编号
	 */
	private String certificateNum; 

	/** 
	 * 弯管长度(m)
	 */
	private Double pipeLength; 

	/**
	 * 管径(mm)
	 */
	private Double pipeDiameter; 

	/** 
	 * 壁厚(mm）
	 */
	private Double wallThickness; 

	/**
	 * 弯制单位
	 */
	private String productionUnit;
	
	/**
	 *  弯制角度(°)
	 */
	private Double bendAngle; 

	/**
	 *  纵焊缝位置 
	 */
	private String weldPosition; 

	/** 
	 * 椭圆度<0.6%D 
	 */
	private String ovality; 

	/** 
	 * 坡口检查
	 */
	private String grooveCheck; 

	/**
	 *  防腐层内外表面质量
	 */
	private String coatingIoFaceCheck; 

	/**
	 *  防腐层端部内外涂层
	 */
	private String coatingIoEndsCheck; 

	/** 
	 * 检查人
	 */
	private String checkedBy; 

	/**
	 *  检查日期
	 */
	private Date checkedDate; 

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

	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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

	public String getPipeColdBendingCode() {
		return pipeColdBendingCode;
	}

	public void setPipeColdBendingCode(String pipeColdBendingCode) {
		this.pipeColdBendingCode = pipeColdBendingCode;
	}

	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}

	public Double getPipeLength() {
		return pipeLength;
	}

	public void setPipeLength(Double pipeLength) {
		this.pipeLength = pipeLength;
	}

	public Double getPipeDiameter() {
		return pipeDiameter;
	}

	public void setPipeDiameter(Double pipeDiameter) {
		this.pipeDiameter = pipeDiameter;
	}

	public Double getWallThickness() {
		return wallThickness;
	}

	public void setWallThickness(Double wallThickness) {
		this.wallThickness = wallThickness;
	}

	public String getProductionUnit() {
		return productionUnit;
	}

	public void setProductionUnit(String productionUnit) {
		this.productionUnit = productionUnit;
	}

	public Double getBendAngle() {
		return bendAngle;
	}

	public void setBendAngle(Double bendAngle) {
		this.bendAngle = bendAngle;
	}

	public String getWeldPosition() {
		return weldPosition;
	}

	public void setWeldPosition(String weldPosition) {
		this.weldPosition = weldPosition;
	}

	public String getOvality() {
		return ovality;
	}

	public void setOvality(String ovality) {
		this.ovality = ovality;
	}

	public String getGrooveCheck() {
		return grooveCheck;
	}

	public void setGrooveCheck(String grooveCheck) {
		this.grooveCheck = grooveCheck;
	}

	public String getCoatingIoFaceCheck() {
		return coatingIoFaceCheck;
	}

	public void setCoatingIoFaceCheck(String coatingIoFaceCheck) {
		this.coatingIoFaceCheck = coatingIoFaceCheck;
	}

	public String getCoatingIoEndsCheck() {
		return coatingIoEndsCheck;
	}

	public void setCoatingIoEndsCheck(String coatingIoEndsCheck) {
		this.coatingIoEndsCheck = coatingIoEndsCheck;
	}

	public String getCheckedBy() {
		return checkedBy;
	}

	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCheckedDate() {
		return checkedDate;
	}

	public void setCheckedDate(Date checkedDate) {
		this.checkedDate = checkedDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
