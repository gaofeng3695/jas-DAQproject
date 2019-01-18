package cn.jasgroup.jasframework.acquisitiondata.station.spatial.pressure.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * 
  *<p>类描述：管道试压Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月14日 下午6:11:36。</p>
 */
public class StationPipePressureTestBo extends CommonBaseBo{
	
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
	 * 站场/阀室编号 
	 */
	private String pipeStationOid; 

	/**
	 * 站场/阀室编名称
	 */
	private String pipeStationName; 
	
	/**
	 * 试压起始桩号 
	 */
	private String startMedianStakeOid; 
	
	/**
	 * 试压起始桩号 
	 */
	private String startMedianStakeCode; 

	/**
	 * 相对起始桩位置(m) 
	 */
	private Double startRelativeMileage; 

	/**
	 * 试压结束桩号 
	 */
	private String endMedianStakeOid; 

	/**
	 * 试压结束桩号 
	 */
	private String endMedianStakeCode; 

	/**
	 * 相对结束桩位置(m) 
	 */
	private Double endRelativeMileage; 

	/**
	 * 试压段长度(m) 
	 */
	private Double pressureTestLength; 

	/**
	 * 管道规格 
	 */
	private String pipeSpecification; 

	/**
	 * 试压介质
	 */
	private Integer pressureTestMedium; 

	/**
	 * 试压介质
	 */
	private String pressureTestMediumName; 

	/**
	 * 设计压力(mpa) 
	 */
	private Double designPressure; 

	/**
	 * 试压日期 
	 */
	private Date pressureTestDate; 

	/**
	 * 试验过程描述 
	 */
	private String processDescription; 

	/**
	 * 结论
	 */
	private Integer pressureTestResult; 

	/**
	 * 结论
	 */
	private String pressureTestResultName; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 
	
	/**
	 * 施工单位 
	 */
	private String constructUnitName; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnit; 
	
	/**
	 * 监理单位 
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
	 * 审核状态 
	 */
	private Integer approveStatus; 
	
	/**
	 * 审核状态 
	 */
	private String approveStatusName; 

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

	public String getPipeStationOid() {
		return pipeStationOid;
	}

	public void setPipeStationOid(String pipeStationOid) {
		this.pipeStationOid = pipeStationOid;
	}

	public String getPipeStationName() {
		return pipeStationName;
	}

	public void setPipeStationName(String pipeStationName) {
		this.pipeStationName = pipeStationName;
	}

	public String getStartMedianStakeOid() {
		return startMedianStakeOid;
	}

	public void setStartMedianStakeOid(String startMedianStakeOid) {
		this.startMedianStakeOid = startMedianStakeOid;
	}

	public String getStartMedianStakeCode() {
		return startMedianStakeCode;
	}

	public void setStartMedianStakeCode(String startMedianStakeCode) {
		this.startMedianStakeCode = startMedianStakeCode;
	}

	public Double getStartRelativeMileage() {
		return startRelativeMileage;
	}

	public void setStartRelativeMileage(Double startRelativeMileage) {
		this.startRelativeMileage = startRelativeMileage;
	}

	public String getEndMedianStakeOid() {
		return endMedianStakeOid;
	}

	public void setEndMedianStakeOid(String endMedianStakeOid) {
		this.endMedianStakeOid = endMedianStakeOid;
	}

	public String getEndMedianStakeCode() {
		return endMedianStakeCode;
	}

	public void setEndMedianStakeCode(String endMedianStakeCode) {
		this.endMedianStakeCode = endMedianStakeCode;
	}

	public Double getEndRelativeMileage() {
		return endRelativeMileage;
	}

	public void setEndRelativeMileage(Double endRelativeMileage) {
		this.endRelativeMileage = endRelativeMileage;
	}

	public Double getPressureTestLength() {
		return pressureTestLength;
	}

	public void setPressureTestLength(Double pressureTestLength) {
		this.pressureTestLength = pressureTestLength;
	}

	public String getPipeSpecification() {
		return pipeSpecification;
	}

	public void setPipeSpecification(String pipeSpecification) {
		this.pipeSpecification = pipeSpecification;
	}

	public Integer getPressureTestMedium() {
		return pressureTestMedium;
	}

	public void setPressureTestMedium(Integer pressureTestMedium) {
		this.pressureTestMedium = pressureTestMedium;
	}

	public void setApproveStatusName(String approveStatusName) {
		this.approveStatusName = approveStatusName;
	}

	public Double getDesignPressure() {
		return designPressure;
	}

	public void setDesignPressure(Double designPressure) {
		this.designPressure = designPressure;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getPressureTestDate() {
		return pressureTestDate;
	}

	public void setPressureTestDate(Date pressureTestDate) {
		this.pressureTestDate = pressureTestDate;
	}

	public String getProcessDescription() {
		return processDescription;
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription;
	}

	public Integer getPressureTestResult() {
		return pressureTestResult;
	}

	public void setPressureTestResult(Integer pressureTestResult) {
		this.pressureTestResult = pressureTestResult;
	}

	public String getPressureTestResultName() {
		return pressureTestResultName;
	}

	public void setPressureTestResultName(String pressureTestResultName) {
		this.pressureTestResultName = pressureTestResultName;
	}

	public String getPressureTestMediumName() {
		return pressureTestMediumName;
	}

	public void setPressureTestMediumName(String pressureTestMediumName) {
		this.pressureTestMediumName = pressureTestMediumName;
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

	public String getCollectionPerson() {
		return collectionPerson;
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveStatusName() {
		return approveStatusName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	} 

}
