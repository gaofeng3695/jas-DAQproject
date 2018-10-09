package cn.jasgroup.jasframework.acquisitiondata.material.appendages.casingpipe.dao.entity;
import java.util.Date;

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
 * <p>类描述：套管表。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月21日 下午2:07:03。</p>
 */

@CommonSaveConfig(
	scene = "/appendagesCasingPipe/save"
)
@CommonUpdateConfig(
	scene = "/appendagesCasingPipe/update"
)
@CommonDeleteConfig(
	scene = "/appendagesCasingPipe/delete"
)
@CommonDeleteBatchConfig(
	scene = "/appendagesCasingPipe/deleteBatch"
)
@Line(
	geometryColumnName = "geom",
	geometryState="geoState",
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
@JdbcEntity(name="daq_appendages_casing_pipe")
public class DaqAppendagesCasingPipe extends CommonJdbcEntity {

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
	 * 线路段/穿跨越oid 
	 */
	private String pipeSegmentOrCrossOid; 

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
	 * 起点坐标X 
	 */
	private Double startPointx; 

	/**
	 * 起点坐标Y 
	 */
	private Double startPointy; 

	/**
	 * 终点坐标X 
	 */
	private Double endPointx; 

	/**
	 * 终点坐标Y 
	 */
	private Double endPointy; 

	/**
	 * 套管类型 
	 */
	private String casingPipeType; 

	/**
	 * 套管长度(m) 
	 */
	private Double casingPipeLength; 

	/**
	 * 套管规格 
	 */
	private String casingPipeSpecifications; 

	/**
	 * 施工日期 
	 */
	private Date constructDate; 

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

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid; 
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid; 
		super.setField("pipeSegmentOrCrossOid");
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

	public String getCasingPipeType() {
		return casingPipeType; 
	}

	public void setCasingPipeType(String casingPipeType) {
		this.casingPipeType = casingPipeType; 
		super.setField("casingPipeType");
	}

	public Double getCasingPipeLength() {
		return casingPipeLength; 
	}

	public void setCasingPipeLength(Double casingPipeLength) {
		this.casingPipeLength = casingPipeLength; 
		super.setField("casingPipeLength");
	}

	public String getCasingPipeSpecifications() {
		return casingPipeSpecifications; 
	}

	public void setCasingPipeSpecifications(String casingPipeSpecifications) {
		this.casingPipeSpecifications = casingPipeSpecifications; 
		super.setField("casingPipeSpecifications");
	}

	public Date getConstructDate() {
		return constructDate; 
	}

	public void setConstructDate(Date constructDate) {
		this.constructDate = constructDate; 
		super.setField("constructDate");
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