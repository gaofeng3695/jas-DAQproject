package cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo;

public class CheckPipeColdBendingBo {

	/**
	 * 出厂编号
	 */
	private String manufacturerCode;

	/**
	 * 合格证编号
	 */
	private String certificationNum;

	/**
	 * 公称直径(mm)
	 */
	private Double diameter;

	/**
	 * 公称压力(MPa)
	 */
	private Double pressure;

	/**
	 * 生产厂家
	 */
	private String manufacturer;

	/**
	 * 测试仪器
	 */
	private String testEquipment;

	/**
	 * 仪器规格型号
	 */
	private String specandModel;

	/**
	 * 实测绝缘电阻值(MΩ)
	 */
	private String resistanceVal;

	/**
	 * 验收结论
	 */
	private String checkResults;

	/**
	 * 备注
	 */
	private String remarks;

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
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
