package cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo;

public class CheckInsulatedJointBo {

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

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段名称
	 */
	private String tendersName;

	/**
	 *  出厂编号 
	 */
	private String manufacturerCode; 
	
	/**
	 *  出厂编号名称 
	 */
	private String manufacturerCodeName; 

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

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getManufacturerCodeName() {
		return manufacturerCodeName;
	}

	public void setManufacturerCodeName(String manufacturerCodeName) {
		this.manufacturerCodeName = manufacturerCodeName;
	}

	public String getCertificationNum() {
		return certificationNum;
	}

	public void setCertificationNum(String certificationNum) {
		this.certificationNum = certificationNum;
	}

	public Double getDiameter() {
		return diameter;
	}

	public void setDiameter(Double diameter) {
		this.diameter = diameter;
	}

	public Double getPressure() {
		return pressure;
	}

	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getTestEquipment() {
		return testEquipment;
	}

	public void setTestEquipment(String testEquipment) {
		this.testEquipment = testEquipment;
	}

	public String getSpecandModel() {
		return specandModel;
	}

	public void setSpecandModel(String specandModel) {
		this.specandModel = specandModel;
	}

	public String getResistanceVal() {
		return resistanceVal;
	}

	public void setResistanceVal(String resistanceVal) {
		this.resistanceVal = resistanceVal;
	}

	public String getCheckResults() {
		return checkResults;
	}

	public void setCheckResults(String checkResults) {
		this.checkResults = checkResults;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
