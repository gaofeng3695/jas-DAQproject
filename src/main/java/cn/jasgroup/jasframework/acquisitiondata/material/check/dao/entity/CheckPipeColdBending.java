package cn.jasgroup.jasframework.acquisitiondata.material.check.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：冷弯管检查及信息记录实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月5日 上午9:17:52。</p>
 */
@CommonSaveConfig(
		scene = "/checkPipeColdBending/save"
	)
	@CommonUpdateConfig(
		scene = "/checkPipeColdBending/update"
	)
	@CommonDeleteConfig(
		scene = "/checkPipeColdBending/delete"
	)
@UniqueConstraints(
		strategys ={
			@UniqueConstraintStrategy(columnNames={"pipeColdBendingCode"})
		}
	)
@JdbcEntity(name="daq_check_pipe_cold_bending")
public class CheckPipeColdBending extends CommonJdbcEntity{

	/**
	 *  项目oid 
	 */
	private String projectOid; 

	/**
	 *  监工单位oid
	 */
	private String constructionUnit; 

	/**
	 *  标段oid
	 */
	private String tendersOid; 

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

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
		super.setField("projectOid");
	}

	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
		super.setField("constructionUnit");
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
		super.setField("tendersOid");
	}

	public String getPipeColdBendingCode() {
		return pipeColdBendingCode; 
	}

	public void setPipeColdBendingCode(String pipeColdBendingCode) {
		this.pipeColdBendingCode = pipeColdBendingCode; 
		super.setField("pipeColdBendingCode");
	}

	public String getCertificateNum() {
		return certificateNum; 
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum; 
		super.setField("certificateNum");
	}

	public Double getPipeLength() {
		return pipeLength; 
	}

	public void setPipeLength(Double pipeLength) {
		this.pipeLength = pipeLength; 
		super.setField("pipeLength");
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
	
	public String getProductionUnit() {
		return productionUnit;
	}

	public void setProductionUnit(String productionUnit) {
		this.productionUnit = productionUnit;
		super.setField("productionUnit");
	}

	public Double getBendAngle() {
		return bendAngle; 
	}

	public void setBendAngle(Double bendAngle) {
		this.bendAngle = bendAngle; 
		super.setField("bendAngle");
	}

	public String getWeldPosition() {
		return weldPosition; 
	}

	public void setWeldPosition(String weldPosition) {
		this.weldPosition = weldPosition; 
		super.setField("weldPosition");
	}

	public String getOvality() {
		return ovality; 
	}

	public void setOvality(String ovality) {
		this.ovality = ovality; 
		super.setField("ovality");
	}

	public String getGrooveCheck() {
		return grooveCheck; 
	}

	public void setGrooveCheck(String grooveCheck) {
		this.grooveCheck = grooveCheck; 
		super.setField("grooveCheck");
	}

	public String getCoatingIoFaceCheck() {
		return coatingIoFaceCheck; 
	}

	public void setCoatingIoFaceCheck(String coatingIoFaceCheck) {
		this.coatingIoFaceCheck = coatingIoFaceCheck; 
		super.setField("coatingIoFaceCheck");
	}

	public String getCoatingIoEndsCheck() {
		return coatingIoEndsCheck; 
	}

	public void setCoatingIoEndsCheck(String coatingIoEndsCheck) {
		this.coatingIoEndsCheck = coatingIoEndsCheck; 
		super.setField("coatingIoEndsCheck");
	}

	public String getCheckedBy() {
		return checkedBy; 
	}

	public void setCheckedBy(String checkedBy) {
		this.checkedBy = checkedBy; 
		super.setField("checkedBy");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCheckedDate() {
		return checkedDate; 
	}

	public void setCheckedDate(Date checkedDate) {
		this.checkedDate = checkedDate; 
		super.setField("checkedDate");
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}

}
