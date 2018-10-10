package cn.jasgroup.jasframework.acquisitiondata.material.base.closure.dao.entity;
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
import cn.jasgroup.jasframework.base.annotation.UpdateDeleteSet;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

/**
 * 
  *<p>类描述：封堵物实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月4日 下午3:32:00。</p>
 */
@CommonSaveConfig(
	scene = "/closure/save"
)
@CommonUpdateConfig(
	scene = "/closure/update"
)
@CommonDeleteConfig(
	scene = "/closure/delete"
)
@UniqueConstraints(
	strategys ={
		@UniqueConstraintStrategy(columnNames={"closureCode"},name="封堵物编号")
	}
)
@JdbcEntity(name="daq_material_closure")
public class Closure extends CommonJdbcEntity{

	/**
	 * 封堵物编号
	 */
	private String closureCode; 

	/**
	 * 封堵物类型
	 */
	private String closureType; 

	/**
	 *  材质
	 */
	private String material; 

	/**
	 * 钢材等级
	 */
	private String steelGrade; 

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
	 *  防腐方式
	 */
	private String coatingMethods; 

	/**
	 *  生产厂家
	 */
	private String manufacturer; 

	/**
	 *  生产日期
	 */
	private Date manufacturerDate; 

	/**
	 *  项目oid 
	 */
	private String projectOid; 

	/**
	 *  管线oid
	 */
	private String pipelineOid; 

	/**
	 *  标段oid
	 */
	private String tendersOid; 

	/**
	 *  是否使用
	 */
	private Integer isUse = 0; 

	/**
	 *  备注
	 */
	private String remarks; 
	/**
	 * 施工单位
	 */
	private String constructUnit = ThreadLocalHolder.getCurrentUser().getUnitId(); 

	public String getClosureCode() {
		return closureCode; 
	}

	public void setClosureCode(String closureCode) {
		this.closureCode = closureCode; 
		super.setField("closureCode");
	}

	public String getClosureType() {
		return closureType; 
	}

	public void setClosureType(String closureType) {
		this.closureType = closureType; 
		super.setField("closureType");
	}

	public String getMaterial() {
		return material; 
	}

	public void setMaterial(String material) {
		this.material = material; 
		super.setField("material");
	}

	public String getSteelGrade() {
		return steelGrade; 
	}

	public void setSteelGrade(String steelGrade) {
		this.steelGrade = steelGrade; 
		super.setField("steelGrade");
	}

	public Double getOutsideDiameter() {
		return outsideDiameter; 
	}

	public void setOutsideDiameter(Double outsideDiameter) {
		this.outsideDiameter = outsideDiameter; 
		super.setField("outsideDiameter");
	}

	public Double getWallThickness() {
		return wallThickness; 
	}

	public void setWallThickness(Double wallThickness) {
		this.wallThickness = wallThickness; 
		super.setField("wallThickness");
	}

	public String getConnectionMethods() {
		return connectionMethods; 
	}

	public void setConnectionMethods(String connectionMethods) {
		this.connectionMethods = connectionMethods; 
		super.setField("connectionMethods");
	}

	public String getCoatingMethods() {
		return coatingMethods; 
	}

	public void setCoatingMethods(String coatingMethods) {
		this.coatingMethods = coatingMethods; 
		super.setField("coatingMethods");
	}

	public String getManufacturer() {
		return manufacturer; 
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer; 
		super.setField("manufacturer");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getManufacturerDate() {
		return manufacturerDate; 
	}

	public void setManufacturerDate(Date manufacturerDate) {
		this.manufacturerDate = manufacturerDate; 
		super.setField("manufacturerDate");
	}

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

	public Integer getIsUse() {
		return isUse; 
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse; 
		super.setField("isUse");
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
	}
	
}