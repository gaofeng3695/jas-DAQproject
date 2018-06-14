package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

public class MedianStakeQueryBo extends CommonBaseBo{
	private String oid;
	/** 中线桩编号 */
	private String medianStakeCode; 

	/** 项目oid */
	private String projectOid; 

	/** 项目名称 **/
	private String projectName;
	
	/** 管线oid */
	private String pipelineOid;
	
	/** 管线名称 **/
	private String pipelineName;
	
	/** 里程(km) */
	private Double mileage; 

	/** 标石类型 */
	private String markStoneType; 
	
	/** 标石类型名 **/
	private String markStoneTypeName;
	
	/** 标石概略位置 */
	private String markStoneLocation; 

	/** X坐标 */
	private Double pointx; 

	/** X坐标 */
	private Double pointy; 

	/** 高程 */
	private Double pointz; 

	/** 备注 */
	private String remarks;

	public String getMedianStakeCode() {
		return medianStakeCode;
	}

	public void setMedianStakeCode(String medianStakeCode) {
		this.medianStakeCode = medianStakeCode;
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

	public String getMarkStoneType() {
		return markStoneType;
	}

	public void setMarkStoneType(String markStoneType) {
		this.markStoneType = markStoneType;
	}

	public String getMarkStoneTypeName() {
		return markStoneTypeName;
	}

	public void setMarkStoneTypeName(String markStoneTypeName) {
		this.markStoneTypeName = markStoneTypeName;
	}

	public String getMarkStoneLocation() {
		return markStoneLocation;
	}

	public void setMarkStoneLocation(String markStoneLocation) {
		this.markStoneLocation = markStoneLocation;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Double getPointx() {
		return pointx;
	}

	public void setPointx(Double pointx) {
		this.pointx = pointx;
	}

	public Double getPointy() {
		return pointy;
	}

	public void setPointy(Double pointy) {
		this.pointy = pointy;
	}

	public Double getPointz() {
		return pointz;
	}

	public void setPointz(Double pointz) {
		this.pointz = pointz;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
	
}
