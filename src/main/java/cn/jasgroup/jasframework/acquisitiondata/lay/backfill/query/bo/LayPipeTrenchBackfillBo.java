package cn.jasgroup.jasframework.acquisitiondata.lay.backfill.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
  *<p>类描述：回填Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月16日 下午5:20:07。</p>
 */
public class LayPipeTrenchBackfillBo extends CommonBaseBo{
	
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
	 * 起始桩号 
	 */
	private String startMedianStakeOid; 
	
	/**
	 * 起始桩编号
	 */
	private String startMedianStakeName;

	/**
	 * 相对起始桩里程(m) 
	 */
	private Double startRelativeMileage; 

	/**
	 * 结束桩号 
	 */
	private String endMedianStakeOid; 
	
	/**
	 * 终点桩编号
	 */
	private String endMedianStakeName;

	/**
	 * 相对结束桩里程(m) 
	 */
	private Double endRelativeMileage; 

	/**
	 * 回填长度(m) 
	 */
	private Double backfillLength; 

	/**
	 * 细土回填厚度(mm) 
	 */
	private Double backfillThickness; 

	/**
	 * 一次回填厚度(mm) 
	 */
	private Double oneBackfillThickness; 

	/**
	 * 二次回填厚度(mm) 
	 */
	private Double twoBackfillThickness; 

	/**
	 * 埋地标识类型 
	 */
	private String signType; 

	/**
	 * 埋地标识类型 
	 */
	private String signTypeName; 

	/**
	 * 标识长度(m) 
	 */
	private Double signLength; 

	/**
	 * 标识埋深(m) 
	 */
	private Double signDepth; 

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
	 * 审核状态 
	 */
	private Integer approveStatus = 0; 

	/**
	 * 空间数据状态 
	 */
	private String geoState; 

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

	public String getStartMedianStakeOid() {
		return startMedianStakeOid;
	}

	public void setStartMedianStakeOid(String startMedianStakeOid) {
		this.startMedianStakeOid = startMedianStakeOid;
	}

	public String getStartMedianStakeName() {
		return startMedianStakeName;
	}

	public void setStartMedianStakeName(String startMedianStakeName) {
		this.startMedianStakeName = startMedianStakeName;
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

	public String getEndMedianStakeName() {
		return endMedianStakeName;
	}

	public void setEndMedianStakeName(String endMedianStakeName) {
		this.endMedianStakeName = endMedianStakeName;
	}

	public Double getEndRelativeMileage() {
		return endRelativeMileage;
	}

	public void setEndRelativeMileage(Double endRelativeMileage) {
		this.endRelativeMileage = endRelativeMileage;
	}

	public Double getBackfillLength() {
		return backfillLength;
	}

	public void setBackfillLength(Double backfillLength) {
		this.backfillLength = backfillLength;
	}

	public Double getBackfillThickness() {
		return backfillThickness;
	}

	public void setBackfillThickness(Double backfillThickness) {
		this.backfillThickness = backfillThickness;
	}

	public Double getOneBackfillThickness() {
		return oneBackfillThickness;
	}

	public void setOneBackfillThickness(Double oneBackfillThickness) {
		this.oneBackfillThickness = oneBackfillThickness;
	}

	public Double getTwoBackfillThickness() {
		return twoBackfillThickness;
	}

	public void setTwoBackfillThickness(Double twoBackfillThickness) {
		this.twoBackfillThickness = twoBackfillThickness;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSignTypeName() {
		return signTypeName;
	}

	public void setSignTypeName(String signTypeName) {
		this.signTypeName = signTypeName;
	}

	public Double getSignLength() {
		return signLength;
	}

	public void setSignLength(Double signLength) {
		this.signLength = signLength;
	}

	public Double getSignDepth() {
		return signDepth;
	}

	public void setSignDepth(Double signDepth) {
		this.signDepth = signDepth;
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

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getGeoState() {
		return geoState;
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	} 

}
