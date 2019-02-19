package cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

public class CheckPipeColdBendingBo extends CommonBaseBo{

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
	private String constructUnit;

	/**
	 * 监工单位名称
	 */
	private String unitName;
	private String constructUnitName;

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
	
	/***
	 * 冷弯管oid
	 */
	private String pipeColdBendingOid; 

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
	private Integer weldPosition;

	/**
	 *  纵焊缝位置 
	 */
	private String weldPositionName; 

	/** 
	 * 椭圆度<0.6%D 
	 */
	private String ovalityName; 

	/** 
	 * 椭圆度<0.6%D 
	 */
	private Integer ovality; 

	/** 
	 * 坡口检查
	 */
	private Integer grooveCheck; 

	/** 
	 * 坡口检查
	 */
	private String grooveCheckName; 

	/**
	 *  防腐层内外表面质量
	 */
	private Integer coatingIoFaceCheck; 

	/**
	 *  防腐层内外表面质量
	 */
	private String coatingIoFaceCheckName; 

	/**
	 *  防腐层端部内外涂层
	 */
	private Integer coatingIoEndsCheck; 

	/**
	 *  防腐层端部内外涂层
	 */
	private String coatingIoEndsCheckName; 

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

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
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

	public Integer getWeldPosition() {
		return weldPosition;
	}

	public void setWeldPosition(Integer weldPosition) {
		this.weldPosition = weldPosition;
	}

	public String getWeldPositionName() {
		return weldPositionName;
	}

	public void setWeldPositionName(String weldPositionName) {
		this.weldPositionName = weldPositionName;
	}

	public String getOvalityName() {
		return ovalityName;
	}

	public void setOvalityName(String ovalityName) {
		this.ovalityName = ovalityName;
	}

	public Integer getOvality() {
		return ovality;
	}

	public void setOvality(Integer ovality) {
		this.ovality = ovality;
	}

	public Integer getGrooveCheck() {
		return grooveCheck;
	}

	public void setGrooveCheck(Integer grooveCheck) {
		this.grooveCheck = grooveCheck;
	}

	public String getGrooveCheckName() {
		return grooveCheckName;
	}

	public void setGrooveCheckName(String grooveCheckName) {
		this.grooveCheckName = grooveCheckName;
	}

	public Integer getCoatingIoFaceCheck() {
		return coatingIoFaceCheck;
	}

	public void setCoatingIoFaceCheck(Integer coatingIoFaceCheck) {
		this.coatingIoFaceCheck = coatingIoFaceCheck;
	}

	public String getCoatingIoFaceCheckName() {
		return coatingIoFaceCheckName;
	}

	public void setCoatingIoFaceCheckName(String coatingIoFaceCheckName) {
		this.coatingIoFaceCheckName = coatingIoFaceCheckName;
	}

	public Integer getCoatingIoEndsCheck() {
		return coatingIoEndsCheck;
	}

	public void setCoatingIoEndsCheck(Integer coatingIoEndsCheck) {
		this.coatingIoEndsCheck = coatingIoEndsCheck;
	}

	public String getCoatingIoEndsCheckName() {
		return coatingIoEndsCheckName;
	}

	public void setCoatingIoEndsCheckName(String coatingIoEndsCheckName) {
		this.coatingIoEndsCheckName = coatingIoEndsCheckName;
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

	public String getPipeColdBendingOid() {
		return pipeColdBendingOid;
	}

	public void setPipeColdBendingOid(String pipeColdBendingOid) {
		this.pipeColdBendingOid = pipeColdBendingOid;
	}

	public String getConstructUnitName() {
		return constructUnitName;
	}

	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}
	
}
