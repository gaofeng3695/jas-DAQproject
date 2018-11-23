package cn.jasgroup.jasframework.acquisitiondata.story;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.PictureRenderData;

public class PipeSegmentData {
	@Name("project_code")
	private String projectCode;
	
	@Name("pipe_code")
	private String pipeCode;

	@Name("pipe_info")
	private String pipeInfo;

	@Name("pipe_length")
	private String pipeLength;

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
	@Name("external_coating_type")
	private String externalCoatingType;
	/***
	 * 钢管生产厂家
	 */
	@Name("manufacture_factory")
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

	public String getPipeInfo() {
		return pipeInfo;
	}

	public void setPipeInfo(String pipeInfo) {
		this.pipeInfo = pipeInfo;
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

	public PictureRenderData getPicture() {
		return picture;
	}

	public void setPicture(PictureRenderData picture) {
		this.picture = picture;
	}
}
