package cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.query.bo;

import java.util.Date;

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
	private String projectCode;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 管线编号
	 */
	private String pipelineCode;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段编号
	 */
	private String tendersCode;

	/**
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid;

	/**
	 * 线路段/穿跨越名称
	 */
	private String pipeSegmentOrCrossName;

	/**
	 * 焊口oid
	 */
	private String weldOid;

	/**
	 * 焊口编号
	 */
	private String weldCode;

	/**
	 * 桩号
	 */
	private String medianStakeOid;

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

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getPipelineCode() {
		return pipelineCode;
	}

	public void setPipelineCode(String pipelineCode) {
		this.pipelineCode = pipelineCode;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getTendersCode() {
		return tendersCode;
	}

	public void setTendersCode(String tendersCode) {
		this.tendersCode = tendersCode;
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
