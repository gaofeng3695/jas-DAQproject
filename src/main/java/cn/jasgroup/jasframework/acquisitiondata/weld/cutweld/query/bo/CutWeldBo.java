package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * 
  *<p>类描述：钢管切管Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月10日 上午8:58:02。</p>
 */
public class CutWeldBo extends CommonBaseBo{
	
	/**
	 * oid
	 */
	private String oid;
	
	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 管线名称
	 */
	private String pipelineName;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段名称
	 */
	private String tendersName;

	/**
	 * 钢管编号
	 */
	private String pipeOid; 

	/**
	 * 钢管编号
	 */
	private String pipeCode; 

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
	private Integer approveStatus;
	
	/**
	 * 备注 
	 */
	private String remarks;
	
	/**
	 * 施工单位
	 */
	private String constructUnit;

	/**
	 * 施工单位名称
	 */
	private String constructUnitName;

	/**
	 * 监理单位
	 */
	private String supervisionUnit;

	/**
	 * 监理单位名称
	 */
	private String supervisionUnitName;

	/**
	 * 监理工程师
	 */
	private String supervisionEngineer;

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

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getPipelineName() {
		return pipelineName;
	}

	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
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

	public String getPipeOid() {
		return pipeOid;
	}

	public String getPipeCode() {
		return pipeCode;
	}

	public void setPipeCode(String pipeCode) {
		this.pipeCode = pipeCode;
	}

	public void setPipeOid(String pipeOid) {
		this.pipeOid = pipeOid;
	}

	public Double getPipeDiameter() {
		return pipeDiameter;
	}

	public void setPipeDiameter(Double pipeDiameter) {
		this.pipeDiameter = pipeDiameter;
	}

	public Double getWallThickness() {
		return wallThickness;
	}

	public void setWallThickness(Double wallThickness) {
		this.wallThickness = wallThickness;
	}

	public Integer getSegmentsNum() {
		return segmentsNum;
	}

	public void setSegmentsNum(Integer segmentsNum) {
		this.segmentsNum = segmentsNum;
	}

	public Double getFirstParagraphLength() {
		return firstParagraphLength;
	}

	public void setFirstParagraphLength(Double firstParagraphLength) {
		this.firstParagraphLength = firstParagraphLength;
	}

	public Double getSecondParagraphLength() {
		return secondParagraphLength;
	}

	public void setSecondParagraphLength(Double secondParagraphLength) {
		this.secondParagraphLength = secondParagraphLength;
	}

	public Double getThirdParagraphLength() {
		return thirdParagraphLength;
	}

	public void setThirdParagraphLength(Double thirdParagraphLength) {
		this.thirdParagraphLength = thirdParagraphLength;
	}

	public Double getFourthParagraphLength() {
		return fourthParagraphLength;
	}

	public void setFourthParagraphLength(Double fourthParagraphLength) {
		this.fourthParagraphLength = fourthParagraphLength;
	}

	public Double getFifthParagraphLength() {
		return fifthParagraphLength;
	}

	public void setFifthParagraphLength(Double fifthParagraphLength) {
		this.fifthParagraphLength = fifthParagraphLength;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}

	public String getConstructUnitName() {
		return constructUnitName;
	}

	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}

	public String getSupervisionUnit() {
		return supervisionUnit;
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit;
	}

	public String getSupervisionUnitName() {
		return supervisionUnitName;
	}

	public void setSupervisionUnitName(String supervisionUnitName) {
		this.supervisionUnitName = supervisionUnitName;
	}

	public String getSupervisionEngineer() {
		return supervisionEngineer;
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer;
	} 
	
}
