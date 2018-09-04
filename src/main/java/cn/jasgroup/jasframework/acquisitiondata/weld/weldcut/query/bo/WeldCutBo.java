package cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
  * 
  *<p>类描述：焊口割口Bo。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年8月21日 下午5:43:12。</p>
 */
public class WeldCutBo extends CommonBaseBo {
	/**
	 * oid
	 */
	private String oid;

	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 项目编号
	 */
	private String projectName;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 管线编号
	 */
	private String pipelineName;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段编号
	 */
	private String tendersName;

	/**
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid;

	/**
	 * 线路段/穿跨越名称
	 */
	private String pipeSegmentOrCrossName;

	/**
	 * 割口编号 
	 */
	private String weldOid; 

	/**
	 * 焊口编号
	 */
	private String weldCode;
	
	/**
	 * 前焊口编号oid 
	 */
	private String frontWeldOid;
	
	/**
	 * 前焊口编号
	 */
	private String frontWeldCode; 

	/**
	 * 后焊口编号Oid
	 */
	private String backWeldOid; 
	/**
	 * 后焊口编号
	 */
	private String backWeldCode; 

	/**
	 * 割口日期 
	 */
	private Date cutWeldDate; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 
	
	/**
	 * 施工单位名称
	 */
	private String constructUnitName;

	/**
	 * 施工机组代号 
	 */
	private String workUnitOid;
	
	/**
	 * 施工机组编号
	 */
	private String workUnitCode;

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
	 * 数据采集人 
	 */
	private String collectionPerson; 

	/**
	 * 采集日期 
	 */
	private Date collectionDate; 

	/**
	 * 备注 
	 */
	private String remarks;
	
	/**
	 * 审核状态 
	 */
	private Integer approveStatus = 0;

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

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}

	public String getPipeSegmentOrCrossName() {
		return pipeSegmentOrCrossName;
	}

	public void setPipeSegmentOrCrossName(String pipeSegmentOrCrossName) {
		this.pipeSegmentOrCrossName = pipeSegmentOrCrossName;
	}

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
	}

	public String getWeldCode() {
		return weldCode;
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
	}

	public String getFrontWeldOid() {
		return frontWeldOid;
	}

	public void setFrontWeldOid(String frontWeldOid) {
		this.frontWeldOid = frontWeldOid;
	}

	public String getFrontWeldCode() {
		return frontWeldCode;
	}

	public void setFrontWeldCode(String frontWeldCode) {
		this.frontWeldCode = frontWeldCode;
	}

	public String getBackWeldOid() {
		return backWeldOid;
	}

	public void setBackWeldOid(String backWeldOid) {
		this.backWeldOid = backWeldOid;
	}

	
	public String getBackWeldCode() {
		return backWeldCode;
	}

	public void setBackWeldCode(String backWeldCode) {
		this.backWeldCode = backWeldCode;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCutWeldDate() {
		return cutWeldDate;
	}

	public void setCutWeldDate(Date cutWeldDate) {
		this.cutWeldDate = cutWeldDate;
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

	public String getWorkUnitOid() {
		return workUnitOid;
	}

	public void setWorkUnitOid(String workUnitOid) {
		this.workUnitOid = workUnitOid;
	}

	public String getWorkUnitCode() {
		return workUnitCode;
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	
	
}
