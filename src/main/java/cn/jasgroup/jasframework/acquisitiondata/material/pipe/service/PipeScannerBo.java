package cn.jasgroup.jasframework.acquisitiondata.material.pipe.service;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.PictureRenderData;

public class PipeScannerBo {
	/**
	 * 项目编号
	 */
	@Name("project_code")
	private String projectCode;
	/**
	 * 钢管编号
	 */
	@Name("pipe_code")
	private String pipeCode;
	
	@Name("pipe_info")
	private String pipeInfo;
	/**
	 * 钢管长度
	 */
	@Name("pipe_length")
	private String pipeLength;
	/**
	 * 重量
	 */
	@Name("pipe_weight")
	private String pipeWeight;
	/**
	 * 炉批号
	 */
	@Name("stove_serial_num")
	private String stoveSerialNum;
	/**
	 * 外防腐类型
	 */
	@Name("external_coating")
	private String externalCoatingType;
	/***
	 * 钢管生产厂家
	 */
	@Name("manufa_factory")
	private String manufactureFactory;
	/**
	 * 防腐加工厂家
	 */
	@Name("coating_factory")
	private String coatingFactory;
	/**
	 * 出厂日期
	 */
	@Name("production_date")
	private String productionDate;
	
	private String scannerContext;
	
	@Name("scaner_pic")
	private PictureRenderData picture;
	
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getPipeCode() {
		return pipeCode;
	}
	public void setPipeCode(String pipeCode) {
		this.pipeCode = pipeCode;
	}
	public String getPipeLength() {
		return pipeLength;
	}
	public void setPipeLength(String pipeLength) {
		this.pipeLength = pipeLength;
	}
	public String getPipeWeight() {
		return pipeWeight;
	}
	public void setPipeWeight(String pipeWeight) {
		this.pipeWeight = pipeWeight;
	}
	public String getStoveSerialNum() {
		return stoveSerialNum;
	}
	public void setStoveSerialNum(String stoveSerialNum) {
		this.stoveSerialNum = stoveSerialNum;
	}
	public String getExternalCoatingType() {
		return externalCoatingType;
	}
	public void setExternalCoatingType(String externalCoatingType) {
		this.externalCoatingType = externalCoatingType;
	}
	public String getManufactureFactory() {
		return manufactureFactory;
	}
	public void setManufactureFactory(String manufactureFactory) {
		this.manufactureFactory = manufactureFactory;
	}
	public String getCoatingFactory() {
		return coatingFactory;
	}
	public void setCoatingFactory(String coatingFactory) {
		this.coatingFactory = coatingFactory;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public String getScannerContext() {
		return scannerContext;
	}
	public void setScannerContext(String scannerContext) {
		this.scannerContext = scannerContext;
	}
	public String getPipeInfo() {
		return pipeInfo;
	}
	public void setPipeInfo(String pipeInfo) {
		this.pipeInfo = pipeInfo;
	}
	public PictureRenderData getPicture() {
		return picture;
	}
	public void setPicture(PictureRenderData picture) {
		this.picture = picture;
	}
	
}
