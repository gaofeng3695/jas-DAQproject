package cn.jasgroup.jasframework.acquisitiondata.material.cross.pipejacking.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>顶管（前后点加偏移）</p>
 * @author admin 。
 * @version v1.0.0.1。
 * @since JDK1.8.0_51。
 * <p>创建日期：2018-07-16 10:12:06。</p>
 */

@CommonSaveConfig(
	scene = "/crossPipeJacking/save"
)
@CommonUpdateConfig(
	scene = "/crossPipeJacking/update"
)
@CommonDeleteConfig(
	scene = "/crossPipeJacking/delete"
)
@CommonDeleteBatchConfig(
	scene = "/crossPipeJacking/deleteBatch"
)
@Line(
	geometryColumnName = "geom",
	calculateType = CalculateType.DoubleAnchorAndDeviation,
	anchorClass = MedianStake.class,
	scopeFieldName = "pipelineOid",	
	startAnchorOid = "startMedianStakeOid",
	startDeviation = "startRelativeMileage",
	endAnchorOid = "endMedianStakeOid",
	endDeviation = "endRelativeMileage",
	startX = "startPointx",
	startY = "startPointy",
	startZ = "startPointz",
	endX = "endPointx",
	endY = "endPointy",
	endZ = "endPointz"
)
@JdbcEntity(name="daq_cross_pipe_jacking")
public class DaqCrossPipeJacking extends CommonJdbcEntity {

	/**
	 * 项目oid 
	 */
	private String projectOid; 

	/**
	 * 标段oid 
	 */
	private String tendersOid; 

	/**
	 * 管线oid 
	 */
	private String pipelineOid; 

	/**
	 * 穿跨越oid 
	 */
	private String crossOid; 

	/**
	 * 穿越物管理单位 
	 */
	private String crossingDepartment; 

	/**
	 * 穿越长度(m) 
	 */
	private Double crossLength; 

	/**
	 * 穿越最大深度(m) 
	 */
	private Double crossMaxLength; 

	/**
	 * 起始桩号 
	 */
	private String startMedianStakeOid; 

	/**
	 * 相对起始桩位置(m) 
	 */
	private Double startRelativeMileage; 

	/**
	 * 结束桩号 
	 */
	private String endMedianStakeOid; 

	/**
	 * 相对结束桩位置(m) 
	 */
	private Double endRelativeMileage; 

	/**
	 * 起始点X坐标 
	 */
	private Double startPointx; 

	/**
	 * 起始点Y坐标 
	 */
	private Double startPointy; 

	/**
	 * 起始点高程(m) 
	 */
	private Double startPointz; 

	/**
	 * 结束点X坐标 
	 */
	private Double endPointx; 

	/**
	 * 结束点Y坐标 
	 */
	private Double endPointy; 

	/**
	 * 结束点高程(m) 
	 */
	private Double endPointz; 

	/**
	 * 开工日期 
	 */
	private Date commencementDate; 

	/**
	 * 完工日期 
	 */
	private Date completionDate; 

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
	private Integer approveStatus = 0; 

	/**
	 * 备注 
	 */
	private String remarks; 

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
		super.setField("projectOid");
	}

	public String getTendersOid() {
		return tendersOid; 
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid; 
		super.setField("tendersOid");
	}

	public String getPipelineOid() {
		return pipelineOid; 
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid; 
		super.setField("pipelineOid");
	}

	public String getCrossOid() {
		return crossOid; 
	}

	public void setCrossOid(String crossOid) {
		this.crossOid = crossOid; 
		super.setField("crossOid");
	}

	public String getCrossingDepartment() {
		return crossingDepartment; 
	}

	public void setCrossingDepartment(String crossingDepartment) {
		this.crossingDepartment = crossingDepartment; 
		super.setField("crossingDepartment");
	}

	public Double getCrossLength() {
		return crossLength; 
	}

	public void setCrossLength(Double crossLength) {
		this.crossLength = crossLength; 
		super.setField("crossLength");
	}

	public Double getCrossMaxLength() {
		return crossMaxLength; 
	}

	public void setCrossMaxLength(Double crossMaxLength) {
		this.crossMaxLength = crossMaxLength; 
		super.setField("crossMaxLength");
	}

	public String getStartMedianStakeOid() {
		return startMedianStakeOid; 
	}

	public void setStartMedianStakeOid(String startMedianStakeOid) {
		this.startMedianStakeOid = startMedianStakeOid; 
		super.setField("startMedianStakeOid");
	}

	public Double getStartRelativeMileage() {
		return startRelativeMileage; 
	}

	public void setStartRelativeMileage(Double startRelativeMileage) {
		this.startRelativeMileage = startRelativeMileage; 
		super.setField("startRelativeMileage");
	}

	public String getEndMedianStakeOid() {
		return endMedianStakeOid; 
	}

	public void setEndMedianStakeOid(String endMedianStakeOid) {
		this.endMedianStakeOid = endMedianStakeOid; 
		super.setField("endMedianStakeOid");
	}

	public Double getEndRelativeMileage() {
		return endRelativeMileage; 
	}

	public void setEndRelativeMileage(Double endRelativeMileage) {
		this.endRelativeMileage = endRelativeMileage; 
		super.setField("endRelativeMileage");
	}

	public Double getStartPointx() {
		return startPointx; 
	}

	public void setStartPointx(Double startPointx) {
		this.startPointx = startPointx; 
		super.setField("startPointx");
	}

	public Double getStartPointy() {
		return startPointy; 
	}

	public void setStartPointy(Double startPointy) {
		this.startPointy = startPointy; 
		super.setField("startPointy");
	}

	public Double getStartPointz() {
		return startPointz; 
	}

	public void setStartPointz(Double startPointz) {
		this.startPointz = startPointz; 
		super.setField("startPointz");
	}

	public Double getEndPointx() {
		return endPointx; 
	}

	public void setEndPointx(Double endPointx) {
		this.endPointx = endPointx; 
		super.setField("endPointx");
	}

	public Double getEndPointy() {
		return endPointy; 
	}

	public void setEndPointy(Double endPointy) {
		this.endPointy = endPointy; 
		super.setField("endPointy");
	}

	public Double getEndPointz() {
		return endPointz; 
	}

	public void setEndPointz(Double endPointz) {
		this.endPointz = endPointz; 
		super.setField("endPointz");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getCommencementDate() {
		return commencementDate; 
	}

	public void setCommencementDate(Date commencementDate) {
		this.commencementDate = commencementDate; 
		super.setField("commencementDate");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getCompletionDate() {
		return completionDate; 
	}

	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate; 
		super.setField("completionDate");
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

	public String getCollectionPerson() {
		return collectionPerson; 
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson; 
		super.setField("collectionPerson");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getCollectionDate() {
		return collectionDate; 
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate; 
		super.setField("collectionDate");
	}

	public String getGeoState() {
		return geoState; 
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState; 
		super.setField("geoState");
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

}