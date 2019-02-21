package cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * 
  *<p>类描述：焊口测量成果信息Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月11日 下午4:44:42。</p>
 */
public class WeldMeasuredResultBo extends CommonBaseBo {

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
	 * 测量控制点类型 
	 */
	private String measureControlPointType; 

	/**
	 * 测量控制点类型 
	 */
	private String measureControlPointTypeName; 

	/**
	 * 测量控制点编号 
	 */
	private String measureControlPointCode; 
	
	/**
	 * 焊口编号 
	 */
	private String weldOid; 

	/**
	 * 焊口编号
	 */
	private String weldCode;

	/**
	 * 弯管编号 
	 */
	private String bendingOid; 

	/**
	 * 弯管编号 
	 */
	private String bendingCode; 

	/**
	 * 中线桩编号
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
	 * 施工单位名称
	 */
	private String constructUnitName;

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

	public String getMeasureControlPointType() {
		return measureControlPointType;
	}

	public void setMeasureControlPointType(String measureControlPointType) {
		this.measureControlPointType = measureControlPointType;
	}

	public String getMeasureControlPointTypeName() {
		return measureControlPointTypeName;
	}

	public void setMeasureControlPointTypeName(String measureControlPointTypeName) {
		this.measureControlPointTypeName = measureControlPointTypeName;
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

	public String getMeasureControlPointCode() {
		return measureControlPointCode;
	}

	public void setMeasureControlPointCode(String measureControlPointCode) {
		this.measureControlPointCode = measureControlPointCode;
	}

	public String getBendingOid() {
		return bendingOid;
	}

	public void setBendingOid(String bendingOid) {
		this.bendingOid = bendingOid;
	}

	public String getBendingCode() {
		return bendingCode;
	}

	public void setBendingCode(String bendingCode) {
		this.bendingCode = bendingCode;
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

	public Double getSurfaceeLevation() {
		return surfaceeLevation;
	}

	public void setSurfaceeLevation(Double surfaceeLevation) {
		this.surfaceeLevation = surfaceeLevation;
	}

	public Double getPipeTopElevation() {
		return pipeTopElevation;
	}

	public void setPipeTopElevation(Double pipeTopElevation) {
		this.pipeTopElevation = pipeTopElevation;
	}

	public Double getBuriedDepth() {
		return buriedDepth;
	}

	public void setBuriedDepth(Double buriedDepth) {
		this.buriedDepth = buriedDepth;
	}

	public String getSurveyCrew() {
		return surveyCrew;
	}

	public void setSurveyCrew(String surveyCrew) {
		this.surveyCrew = surveyCrew;
	}


	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getSurveyDate() {
		return surveyDate;
	}

	public void setSurveyDate(Date surveyDate) {
		this.surveyDate = surveyDate;
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
