package cn.jasgroup.jasframework.acquisitiondata.story;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.DocxRenderData;

public class StoryData { 
	@Name("title_name")
	private String titleName;
	private DocxRenderData segment;
	private DocxRenderData segment2;
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public DocxRenderData getSegment() {
		return segment;
	}
	public void setSegment(DocxRenderData segment) {
		this.segment = segment;
	}
	public DocxRenderData getSegment2() {
		return segment2;
	}
	public void setSegment2(DocxRenderData segment2) {
		this.segment2 = segment2;
	}
	
}
