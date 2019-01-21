package cn.jasgroup.jasframework.acquisitiondata.material.check.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

/**
  *<p>类描述：冷弯管检查及信息记录实体类。</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.material.check.service.CheckService #saveChanagePipeColdBendingChecked()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.material.check.service.CheckService #updateChanagePipeColdBendingChecked()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.material.check.service.CheckService #deleteChanagePipeColdBendingChecked()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.material.check.service.CheckService #updateChanageBeforeAdvice()}
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月5日 上午9:17:52。</p>
 */
@CommonSaveConfig(
	scene = "/checkPipeColdBending/save",
	afterAdvice = {
		@Process(service = "checkService", method = "saveChanagePipeColdBendingChecked()")
	}
)
@CommonUpdateConfig(
	scene = "/checkPipeColdBending/update",
	afterAdvice = {
		@Process(service = "checkService", method = "updateChanagePipeColdBendingChecked()")
	},
	beforeAdvice = {
		@Process(service = "checkService", method = "updateChanageBeforeAdvice()")
	}
)
@CommonDeleteConfig(
	scene = "/checkPipeColdBending/delete",
	beforeAdvice = {
			@Process(service = "checkService", method = "updateChanageBeforeAdvice()")
	},
	afterAdvice = {
			@Process(service = "checkService", method = "deleteChanagePipeColdBendingChecked()")
	}
)
@CommonDeleteBatchConfig(
		scene = "/checkPipeColdBending/deleteBatch"
)
@JdbcEntity(name="daq_check_pipe_cold_bending")
public class CheckPipeColdBending extends CommonJdbcEntity{

	/**
	 *  项目oid 
	 */
	private String projectOid; 

	/**
	 * 施工单位
	 */
	private String constructUnit;

	/**
	 *  标段oid
	 */
	private String tendersOid; 

	/**
	 * 冷弯管编号
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
	 * 椭圆度<0.6%D 
	 */
	private Integer ovality; 

	/** 
	 * 坡口检查
	 */
	private Integer grooveCheck; 

	/**
	 *  防腐层内外表面质量
	 */
	private Integer coatingIoFaceCheck; 

	/**
	 *  防腐层端部内外涂层
	 */
	private Integer coatingIoEndsCheck; 

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

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
		super.setField("tendersOid");
	}

	public String getPipeColdBendingOid() {
		return pipeColdBendingOid;
	}

	public void setPipeColdBendingOid(String pipeColdBendingOid) {
		this.pipeColdBendingOid = pipeColdBendingOid;
		super.setField("pipeColdBendingOid");
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

	public Integer getWeldPosition() {
		return weldPosition; 
	}

	public void setWeldPosition(Integer weldPosition) {
		this.weldPosition = weldPosition; 
		super.setField("weldPosition");
	}

	public Integer getOvality() {
		return ovality; 
	}

	public void setOvality(Integer ovality) {
		this.ovality = ovality; 
		super.setField("ovality");
	}

	public Integer getGrooveCheck() {
		return grooveCheck; 
	}

	public void setGrooveCheck(Integer grooveCheck) {
		this.grooveCheck = grooveCheck; 
		super.setField("grooveCheck");
	}

	public Integer getCoatingIoFaceCheck() {
		return coatingIoFaceCheck; 
	}

	public void setCoatingIoFaceCheck(Integer coatingIoFaceCheck) {
		this.coatingIoFaceCheck = coatingIoFaceCheck; 
		super.setField("coatingIoFaceCheck");
	}

	public Integer getCoatingIoEndsCheck() {
		return coatingIoEndsCheck; 
	}

	public void setCoatingIoEndsCheck(Integer coatingIoEndsCheck) {
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
	
	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
		super.setField("constructUnit");
	}

}
