package cn.jasgroup.jasframework.acquisitiondata;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.PictureRenderData;

public class SegmentData {
	@Name("project_name")
	private String projectName;
	
	@Name("pipe_code")
	private String pipeCode;

	private String diameter;

	@Name("pipe_length")
	private String pipeLength;

	@Name("pipe_weight")
	private String pipeWeight;

	@Name("scaner_pic")
	private PictureRenderData picture;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPipeCode() {
		return pipeCode;
	}

	public void setPipeCode(String pipeCode) {
		this.pipeCode = pipeCode;
	}

	public String getDiameter() {
		return diameter;
	}

	public void setDiameter(String diameter) {
		this.diameter = diameter;
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

	public PictureRenderData getPicture() {
		return picture;
	}

	public void setPicture(PictureRenderData picture) {
		this.picture = picture;
	}
	
	
	
}
