package cn.jasgroup.jasframework.acquisitiondata.material.check.dao.entity;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

/**
 * 
  *<p>类描述：绝缘接头检查及信息记录实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月5日 上午10:14:38。</p>
 */
@CommonSaveConfig(
	scene = "/checkInsulatedJoint/save"
)
@CommonUpdateConfig(
	scene = "/checkInsulatedJoint/update"
)
@CommonDeleteConfig(
	scene = "/checkInsulatedJoint/delete"
)
@JdbcEntity(name="daq_check_insulated_joint")
public class CheckInsulatedJoint extends CommonJdbcEntity{

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
	 *  出厂编号 
	 */
	private String manufacturerCode; 

	/**
	 *  合格证编号 
	 */
	private String certificationNum; 

	/**
	 *  公称直径(mm)
	 */
	private Double diameter; 

	/**
	 *  公称压力(MPa)
	 */
	private Double pressure; 

	/**
	 *  生产厂家
	 */
	private String manufacturer; 

	/**
	 *  测试仪器
	 */
	private String testEquipment; 

	/**
	 *  仪器规格型号
	 */
	private String specandModel; 

	/**
	 *  实测绝缘电阻值(MΩ)
	 */
	private String resistanceVal; 

	/** 
	 * 验收结论
	 */
	private String checkResults; 
	
	/**
	 *  备注
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

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
		super.setField("manufacturerCode");
	}

	public String getCertificationNum() {
		return certificationNum;
	}

	public void setCertificationNum(String certificationNum) {
		this.certificationNum = certificationNum;
		super.setField("certificationNum");
	}

	public Double getDiameter() {
		return diameter;
	}

	public void setDiameter(Double diameter) {
		this.diameter = diameter;
		super.setField("diameter");
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
		super.setField("pressure");
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
		super.setField("manufacturer");
	}

	public String getTestEquipment() {
		return testEquipment;
	}

	public void setTestEquipment(String testEquipment) {
		this.testEquipment = testEquipment;
		super.setField("testEquipment");
	}

	public String getSpecandModel() {
		return specandModel;
	}

	public void setSpecandModel(String specandModel) {
		this.specandModel = specandModel;
		super.setField("specandModel");
	}

	public String getResistanceVal() {
		return resistanceVal;
	}

	public void setResistanceVal(String resistanceVal) {
		this.resistanceVal = resistanceVal;
		super.setField("resistanceVal");
	}

	public String getCheckResults() {
		return checkResults;
	}

	public void setCheckResults(String checkResults) {
		this.checkResults = checkResults;
		super.setField("checkResults");
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