package cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.dao.entity;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：焊口测量成果信息实体类。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService #changeMeasuredFieldAfterSave()}
  * {@link cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService #changeMeasuredFieldBeforeUpdate()}
  * {@link cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService #changeMeasuredFieldAfterUpdate()}
  * {@link cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService #changeMeasuredFieldAfterDelete()}
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月11日 下午3:28:20。</p>
 */
@CommonSaveConfig(
	scene = "/weldMeasuredResult/save",
	afterAdvice={
		@Process(service = "weldService", method = "changeMeasuredFieldAfterSave()")
	}
)
@CommonUpdateConfig(
	scene = "/weldMeasuredResult/update",
	beforeAdvice={
		@Process(service = "weldService", method = "changeMeasuredFieldBeforeUpdate()")
	},
	afterAdvice={
		@Process(service = "weldService", method = "changeMeasuredFieldAfterUpdate()")
	}
)
@CommonDeleteConfig(
	scene = "/weldMeasuredResult/delete",
	afterAdvice={
		@Process(service = "weldService", method = "changeMeasuredFieldAfterDelete()")
	}
)
@CommonDeleteBatchConfig(
	scene = "/weldMeasuredResult/deleteBatch"
)
@Point(
		x = "pointx",
		y = "pointy",
		z = "surfaceeLevation",
		geometryColumnName = "geom",
		calculateType = CalculateType.Coordinates
)
@UniqueConstraints(
		strategys ={
			@UniqueConstraintStrategy(columnNames={"pipeSegmentOrCrossOid","measureControlPointCode"},name="同一线路段/穿跨越下测量控制点编号不能重复")
		}
	)
@JdbcEntity(name="daq_weld_measured_result")
public class WeldMeasuredResult extends CommonJdbcEntity {

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
	 * 测量控制点类型 
	 */
	private String measureControlPointType; 

	/**
	 * 测量控制点编号 
	 */
	private String measureControlPointCode; 
	
	/**
	 * 焊口编号 
	 */
	private String weldOid; 

	/**
	 * 弯管编号 
	 */
	private String bendingOid; 

	/**
	 * 桩号 
	 */
	private String medianStakeCode; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * 坐标X(m) 
	 */
	private Double pointx; 

	/**
	 * 坐标Y(m) 
	 */
	private Double pointy; 

	/**
	 * 地表高程(m) 
	 */
	private Double surfaceeLevation; 

	/**
	 * 管顶高程(m) 
	 */
	private Double pipeTopElevation; 

	/**
	 * 埋深(m) 
	 */
	private Double buriedDepth; 

	/**
	 * 测量人 
	 */
	private String surveyCrew; 

	/**
	 * 测量日期 
	 */
	private Date surveyDate; 
	
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
	 * 数据采集人 
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

	public String getMeasureControlPointType() {
		return measureControlPointType; 
	}

	public void setMeasureControlPointType(String measureControlPointType) {
		this.measureControlPointType = measureControlPointType; 
		super.setField("measureControlPointType");
	}

	public String getMeasureControlPointCode() {
		return measureControlPointCode; 
	}

	public void setMeasureControlPointCode(String measureControlPointCode) {
		this.measureControlPointCode = measureControlPointCode; 
		super.setField("measureControlPointCode");
	}

	public String getWeldOid() {
		return weldOid; 
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid; 
		super.setField("weldOid");
	}

	public String getBendingOid() {
		return bendingOid; 
	}

	public void setBendingOid(String bendingOid) {
		this.bendingOid = bendingOid; 
		super.setField("bendingOid");
	}

	public String getMedianStakeCode() {
		return medianStakeCode;
	}

	public void setMedianStakeCode(String medianStakeCode) {
		this.medianStakeCode = medianStakeCode;
		super.setField("medianStakeCode");
	}

	public Double getRelativeMileage() {
		return relativeMileage; 
	}

	public void setRelativeMileage(Double relativeMileage) {
		this.relativeMileage = relativeMileage; 
		super.setField("relativeMileage");
	}

	public Double getPointx() {
		return pointx; 
	}

	public void setPointx(Double pointx) {
		this.pointx = pointx; 
		super.setField("pointx");
	}

	public Double getPointy() {
		return pointy; 
	}

	public void setPointy(Double pointy) {
		this.pointy = pointy; 
		super.setField("pointy");
	}

	public Double getSurfaceeLevation() {
		return surfaceeLevation; 
	}

	public void setSurfaceeLevation(Double surfaceeLevation) {
		this.surfaceeLevation = surfaceeLevation; 
		super.setField("surfaceeLevation");
	}

	public Double getPipeTopElevation() {
		return pipeTopElevation; 
	}

	public void setPipeTopElevation(Double pipeTopElevation) {
		this.pipeTopElevation = pipeTopElevation; 
		super.setField("pipeTopElevation");
	}

	public Double getBuriedDepth() {
		return buriedDepth; 
	}

	public void setBuriedDepth(Double buriedDepth) {
		this.buriedDepth = buriedDepth; 
		super.setField("buriedDepth");
	}

	public String getSurveyCrew() {
		return surveyCrew; 
	}

	public void setSurveyCrew(String surveyCrew) {
		this.surveyCrew = surveyCrew; 
		super.setField("surveyCrew");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSurveyDate() {
		return surveyDate; 
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate; 
		super.setField("surveyDate");
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