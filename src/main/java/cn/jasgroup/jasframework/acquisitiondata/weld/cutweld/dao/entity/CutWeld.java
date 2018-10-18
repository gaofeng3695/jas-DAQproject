package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.dao.entity;

import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：钢管切管实体。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月6日 下午5:35:47。</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.service.CutWeldService #savePipeAfterCut()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.service.CutWeldService #updatePipeAfterCut()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.service.CutWeldService #deletePipeAfterCut()}
 */
@CommonSaveConfig(
	scene = "/cutWeld/save",
	afterAdvice = {
			//将切管后的钢管信息保存到钢管表
			@Process(service = "cutWeldService", method = "savePipeAfterCut()")
	}
)
@CommonUpdateConfig(
	scene = "/cutWeld/update",
	afterAdvice = {
			//将切管后的钢管信息保存到钢管表
			@Process(service = "cutWeldService", method = "updatePipeAfterCut()")
	}
)
@CommonDeleteConfig(
	scene = "/cutWeld/delete",
	beforeAdvice = {
			//将切管后的钢管信息保存到钢管表
			@Process(service = "cutWeldService", method = "deletePipeAfterCut()")
	}
)
@JdbcEntity(name="daq_cut_pipe")
public class CutWeld extends CommonJdbcEntity{
	
	/**
	 *  项目oid 
	 */
	private String projectOid; 

	/**
	 * 管线oid
	 */
	private String pipelineOid; 

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 钢管编号
	 */
	private String pipeOid; 

	/**
	 * 管径(mm)
	 */
	private Double pipeDiameter; 

	/**
	 * 壁厚(mm）
	 */
	private Double wallThickness; 

	/**
	 * 分割段数（段）
	 */
	private Integer segmentsNum; 

	/**
	 * 第一段长度(m)
	 */
	private Double firstParagraphLength; 

	/**
	 * 第二段长度(m)
	 */
	private Double secondParagraphLength; 

	/**
	 * 第三段长度(m)
	 */
	private Double thirdParagraphLength; 

	/**
	 * 第四段长度(m)
	 */
	private Double fourthParagraphLength; 

	/**
	 * 第五段长度(m)
	 */
	private Double fifthParagraphLength; 

	/**
	 * 审核状态
	 */
	private Integer approveStatus = 0;
	
	/**
	 * 备注 
	 */
	private String remarks; 
	
	/**
	 * 施工单位 
	 */
	private String constructUnit; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnit; 

	/**
	 * 监理工程师 
	 */
	private String supervisionEngineer; 

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

	public String getPipeOid() {
		return pipeOid; 
	}

	public void setPipeOid(String pipeOid) {
		this.pipeOid = pipeOid; 
		super.setField("pipeOid");
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

	public Integer getSegmentsNum() {
		return segmentsNum; 
	}

	public void setSegmentsNum(Integer segmentsNum) {
		this.segmentsNum = segmentsNum; 
		super.setField("segmentsNum");
	}

	public Double getFirstParagraphLength() {
		return firstParagraphLength; 
	}

	public void setFirstParagraphLength(Double firstParagraphLength) {
		this.firstParagraphLength = firstParagraphLength; 
		super.setField("firstParagraphLength");
	}

	public Double getSecondParagraphLength() {
		return secondParagraphLength; 
	}

	public void setSecondParagraphLength(Double secondParagraphLength) {
		this.secondParagraphLength = secondParagraphLength; 
		super.setField("secondParagraphLength");
	}

	public Double getThirdParagraphLength() {
		return thirdParagraphLength; 
	}

	public void setThirdParagraphLength(Double thirdParagraphLength) {
		this.thirdParagraphLength = thirdParagraphLength; 
		super.setField("thirdParagraphLength");
	}

	public Double getFourthParagraphLength() {
		return fourthParagraphLength; 
	}

	public void setFourthParagraphLength(Double fourthParagraphLength) {
		this.fourthParagraphLength = fourthParagraphLength; 
		super.setField("fourthParagraphLength");
	}

	public Double getFifthParagraphLength() {
		return fifthParagraphLength; 
	}

	public void setFifthParagraphLength(Double fifthParagraphLength) {
		this.fifthParagraphLength = fifthParagraphLength; 
		super.setField("fifthParagraphLength");
	}
	
	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
		super.setField("approveStatus");
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

	public String getSupervisionUnit() {
		return supervisionUnit; 
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit; 
		super.setField("supervisionUnit");
	}

	public String getSupervisionEngineer() {
		return supervisionEngineer; 
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer; 
		super.setField("supervisionEngineer");
	}

}
