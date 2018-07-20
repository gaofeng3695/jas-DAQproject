package cn.jasgroup.jasframework.acquisitiondata.lay.surveying.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：测量放线实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月13日 上午11:36:41。</p>
 */

@CommonSaveConfig(
	scene = "/laySurveying/save"
)
@CommonUpdateConfig(
	scene = "/laySurveying/update"
)
@CommonDeleteConfig(
	scene = "/laySurveying/delete"
)
@Point(
	scopeFieldName="pipelineOid",
	scopeType=ScopeType.CURRENT,
	geometryColumnName="geom",
	calculateType=CalculateType.SingleAnchorAndDeviation,
	anchorClass=MedianStake.class,
	anchorOid="medianStakeOid",
	deviation="relativeMileage"
)
@JdbcEntity(name="daq_lay_surveying")
public class LaySurveying extends CommonJdbcEntity {

	/**
	 * 项目oid 
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
	 * 线路段/穿跨越 
	 */
	private String pipeSegmentOrCrossOid; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 里程(m) 
	 */
	private Double relativeMileage; 

	/**
	 * 实测转角(°) 
	 */
	private Double measuredAngle; 

	/**
	 * 图纸转角(°) 
	 */
	private Double drawingAngle; 

	/**
	 * 左侧放线宽度(m) 
	 */
	private Double leftWidth; 

	/**
	 * 右侧放线宽度(m) 
	 */
	private Double rightWidth; 

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

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid; 
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid; 
		super.setField("pipeSegmentOrCrossOid");
	}

	public String getMedianStakeOid() {
		return medianStakeOid; 
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid; 
		super.setField("medianStakeOid");
	}

	public Double getRelativeMileage() {
		return relativeMileage; 
	}

	public void setRelativeMileage(Double relativeMileage) {
		this.relativeMileage = relativeMileage; 
		super.setField("relativeMileage");
	}

	public Double getMeasuredAngle() {
		return measuredAngle; 
	}

	public void setMeasuredAngle(Double measuredAngle) {
		this.measuredAngle = measuredAngle; 
		super.setField("measuredAngle");
	}

	public Double getDrawingAngle() {
		return drawingAngle; 
	}

	public void setDrawingAngle(Double drawingAngle) {
		this.drawingAngle = drawingAngle; 
		super.setField("drawingAngle");
	}

	public Double getLeftWidth() {
		return leftWidth; 
	}

	public void setLeftWidth(Double leftWidth) {
		this.leftWidth = leftWidth; 
		super.setField("leftWidth");
	}

	public Double getRightWidth() {
		return rightWidth; 
	}

	public void setRightWidth(Double rightWidth) {
		this.rightWidth = rightWidth; 
		super.setField("rightWidth");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
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

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	
	
	
	
	public Date getCollectionDate() {
		return collectionDate; 
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate; 
		super.setField("collectionDate");
	}

	public Integer getApproveStatus() {
		return approveStatus; 
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus; 
		super.setField("approveStatus");
	}

	public String getGeoState() {
		return geoState; 
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState; 
		super.setField("geoState");
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}

}