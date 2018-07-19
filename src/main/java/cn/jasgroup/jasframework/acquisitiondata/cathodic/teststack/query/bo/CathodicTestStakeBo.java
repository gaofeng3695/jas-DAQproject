package cn.jasgroup.jasframework.acquisitiondata.cathodic.teststack.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
  *<p>类描述：测试桩Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月19日 下午5:58:04。</p>
 */
public class CathodicTestStakeBo extends CommonBaseBo{
	
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
	 * 线路段
	 */
	private String pipeSegmentOid; 

	/**
	 * 线路段名称
	 */
	private String pipeSegmentName; 

	/**
	 * 测试桩编号 
	 */
	private String testStakeCode; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 桩号编码
	 */
	private String medianStakeCode; 


	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * X坐标 
	 */
	private Double pointx; 

	/**
	 * Y坐标 
	 */
	private Double pointy; 

	/**
	 * 高程(m) 
	 */
	private Double pointz; 

	/**
	 * 桩体结构 
	 */
	private String stakeStructure; 

	/**
	 * 桩体结构名称 
	 */
	private String stakeStructureName; 

	/**
	 * 安装位置描述 
	 */
	private String installLocationDescription; 

	/**
	 * 埋设日期 
	 */
	private Date burialDate; 

	/**
	 * 桩功能 
	 */
	private String stakeFunction; 

	/**
	 * 桩功能名称 
	 */
	private String stakeFunctionName; 

	/**
	 * 桩顶高度(m) 
	 */
	private Double stakeTopHeight; 

	/**
	 * 桩体形状 
	 */
	private String stakeShape; 

	/**
	 * 施工单位 
	 */
	private String constructUnit;  

	/**
	 * 施工单位名称 
	 */
	private String constructUnitName; 

	/**
	 * 施工日期 
	 */
	private Date constructDate; 

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

	/**
	 * 采集人员 
	 */
	private String collectionPerson; 

	/**
	 * 采集日期 
	 */
	private Date collectionDate; 

	/**
	 * 空间数据状态 
	 */
	private String geoState; 

	/**
	 * 审核状态 
	 */
	private Integer approveStatus; 

	/**
	 * 备注 
	 */
	private String remarks;

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

	public String getPipeSegmentOid() {
		return pipeSegmentOid;
	}

	public void setPipeSegmentOid(String pipeSegmentOid) {
		this.pipeSegmentOid = pipeSegmentOid;
	}

	public String getPipeSegmentName() {
		return pipeSegmentName;
	}

	public void setPipeSegmentName(String pipeSegmentName) {
		this.pipeSegmentName = pipeSegmentName;
	}

	public String getTestStakeCode() {
		return testStakeCode;
	}

	public void setTestStakeCode(String testStakeCode) {
		this.testStakeCode = testStakeCode;
	}

	public String getMedianStakeOid() {
		return medianStakeOid;
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid;
	}

	public String getMedianStakeCode() {
		return medianStakeCode;
	}

	public void setMedianStakeCode(String medianStakeCode) {
		this.medianStakeCode = medianStakeCode;
	}

	public Double getRelativeMileage() {
		return relativeMileage;
	}

	public void setRelativeMileage(Double relativeMileage) {
		this.relativeMileage = relativeMileage;
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

	public String getStakeStructure() {
		return stakeStructure;
	}

	public void setStakeStructure(String stakeStructure) {
		this.stakeStructure = stakeStructure;
	}

	public String getStakeStructureName() {
		return stakeStructureName;
	}

	public void setStakeStructureName(String stakeStructureName) {
		this.stakeStructureName = stakeStructureName;
	}

	public String getInstallLocationDescription() {
		return installLocationDescription;
	}

	public void setInstallLocationDescription(String installLocationDescription) {
		this.installLocationDescription = installLocationDescription;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBurialDate() {
		return burialDate;
	}

	public void setBurialDate(Date burialDate) {
		this.burialDate = burialDate;
	}

	public String getStakeFunction() {
		return stakeFunction;
	}

	public void setStakeFunction(String stakeFunction) {
		this.stakeFunction = stakeFunction;
	}

	public String getStakeFunctionName() {
		return stakeFunctionName;
	}

	public void setStakeFunctionName(String stakeFunctionName) {
		this.stakeFunctionName = stakeFunctionName;
	}

	public Double getStakeTopHeight() {
		return stakeTopHeight;
	}

	public void setStakeTopHeight(Double stakeTopHeight) {
		this.stakeTopHeight = stakeTopHeight;
	}

	public String getStakeShape() {
		return stakeShape;
	}

	public void setStakeShape(String stakeShape) {
		this.stakeShape = stakeShape;
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

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getConstructDate() {
		return constructDate;
	}

	public void setConstructDate(Date constructDate) {
		this.constructDate = constructDate;
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

	public String getCollectionPerson() {
		return collectionPerson;
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getGeoState() {
		return geoState;
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState;
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

}
