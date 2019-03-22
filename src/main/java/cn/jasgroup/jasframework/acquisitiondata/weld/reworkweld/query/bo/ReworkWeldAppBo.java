package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.BaseBo;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

/**
 * 
  *<p>类描述：焊口返修Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月10日 上午8:57:12。</p>
 */
public class ReworkWeldAppBo extends BaseBo {

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
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid; 
	
	/**
	 * 线路段/穿跨越名称
	 */
	private String pipeSegmentOrCrossName; 

	/**
	 *  返修后焊口编号
	 */
	private String reworkWeldCode;
	
	/**
	 * 焊口编号
	 */
	private String weldOid;
	
	private String userId = ThreadLocalHolder.getCurrentUserId();
	/** 
	 * 施工单位名称
	 */
	private String constructUnitName; 
	/**
	 *  施工机组代号
	 */
	private String workUnitCode; 
	
	/**
	 * 监理单位名称
	 */
	private String supervisionUnitName; 
	
	/** 
	 * 采集日期
	 */
	private Date collectionDate; 
	
	/**
	 *  焊接日期
	 */
	private Date weldDate; 
	
	
	/***
     * 是否测量
     */
    private Integer isMeasure;
    /***
     * 审核状态
     */
    private Integer approveStatus;
    
    /***
     * 是否割口
     */
    private Integer isCut;

	
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

	public String getReworkWeldCode() {
		return reworkWeldCode;
	}

	public void setReworkWeldCode(String reworkWeldCode) {
		this.reworkWeldCode = reworkWeldCode;
	}

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getConstructUnitName() {
		return constructUnitName;
	}

	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}

	public String getWorkUnitCode() {
		return workUnitCode;
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode;
	}

	public String getSupervisionUnitName() {
		return supervisionUnitName;
	}

	public void setSupervisionUnitName(String supervisionUnitName) {
		this.supervisionUnitName = supervisionUnitName;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getWeldDate() {
		return weldDate;
	}

	public void setWeldDate(Date weldDate) {
		this.weldDate = weldDate;
	}

	public Integer getIsMeasure() {
		return isMeasure;
	}

	public void setIsMeasure(Integer isMeasure) {
		this.isMeasure = isMeasure;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Integer getIsCut() {
		return isCut;
	}

	public void setIsCut(Integer isCut) {
		this.isCut = isCut;
	} 
	
	
}
