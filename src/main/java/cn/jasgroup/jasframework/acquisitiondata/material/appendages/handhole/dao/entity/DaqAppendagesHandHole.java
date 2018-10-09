package cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.dao.entity;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>类描述：手孔表。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月21日 上午11:55:38。</p>
 */

@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"handHoleCode"},name="手孔编号"),
    }
)
@CommonSaveConfig(
	scene = "/appendagesHandHole/save"
)
@CommonUpdateConfig(
	scene = "/appendagesHandHole/update"
)
@CommonDeleteConfig(
	scene = "/appendagesHandHole/delete"
)
@CommonDeleteBatchConfig(
	scene = "/appendagesHandHole/deleteBatch"
)
@Point(
	x = "pointx",
	y = "pointy",
	z = "pointz",
	geometryColumnName = "geom",
	calculateType = CalculateType.SingleAnchorAndDeviation,
	anchorClass = MedianStake.class,
	scopeFieldName = "pipelineOid",
	anchorOid = "medianStakeOid",
	deviation = "relativeMileage"
)
@JdbcEntity(name="daq_appendages_hand_hole")
public class DaqAppendagesHandHole extends CommonJdbcEntity {

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
	 * 孔编号 
	 */
	private String handHoleCode; 

	/**
	 * 名称 
	 */
	private String handHoleName; 

	/**
	 * 类型 
	 */
	private String handHoleType; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * X坐标 
	 */
	private Double pointx; 

	/**
	 * Y坐标 
	 */
	private Double pointy; 

	/**
	 * 地表高程(m) 
	 */
	private Double pointz; 

	/**
	 * 规格型号 
	 */
	private String handHoleSpecifications; 

	/**
	 * 基础制造及安装情况 
	 */
	private String baseInstallSituation; 

	/**
	 * 口圈及安装情况 
	 */
	private String circleInstallSituation; 

	/**
	 * 材料类型 
	 */
	private String materialType; 

	/**
	 * 光缆盘留长度(m) 
	 */
	private Double stayLong; 

	/**
	 * 是否放置电子标识 
	 */
	private Integer isElectronicMark; 

	/**
	 * 检查验收结果 
	 */
	private Integer acceptanceResults; 

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

	public String getHandHoleCode() {
		return handHoleCode; 
	}

	public void setHandHoleCode(String handHoleCode) {
		this.handHoleCode = handHoleCode; 
		super.setField("handHoleCode");
	}

	public String getHandHoleName() {
		return handHoleName; 
	}

	public void setHandHoleName(String handHoleName) {
		this.handHoleName = handHoleName; 
		super.setField("handHoleName");
	}

	public String getHandHoleType() {
		return handHoleType; 
	}

	public void setHandHoleType(String handHoleType) {
		this.handHoleType = handHoleType; 
		super.setField("handHoleType");
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

	public Double getPointz() {
		return pointz; 
	}

	public void setPointz(Double pointz) {
		this.pointz = pointz; 
		super.setField("pointz");
	}

	public String getHandHoleSpecifications() {
		return handHoleSpecifications; 
	}

	public void setHandHoleSpecifications(String handHoleSpecifications) {
		this.handHoleSpecifications = handHoleSpecifications; 
		super.setField("handHoleSpecifications");
	}

	public String getBaseInstallSituation() {
		return baseInstallSituation; 
	}

	public void setBaseInstallSituation(String baseInstallSituation) {
		this.baseInstallSituation = baseInstallSituation; 
		super.setField("baseInstallSituation");
	}

	public String getCircleInstallSituation() {
		return circleInstallSituation; 
	}

	public void setCircleInstallSituation(String circleInstallSituation) {
		this.circleInstallSituation = circleInstallSituation; 
		super.setField("circleInstallSituation");
	}

	public String getMaterialType() {
		return materialType; 
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType; 
		super.setField("materialType");
	}

	public Double getStayLong() {
		return stayLong; 
	}

	public void setStayLong(Double stayLong) {
		this.stayLong = stayLong; 
		super.setField("stayLong");
	}

	public Integer getIsElectronicMark() {
		return isElectronicMark; 
	}

	public void setIsElectronicMark(Integer isElectronicMark) {
		this.isElectronicMark = isElectronicMark; 
		super.setField("isElectronicMark");
	}

	public Integer getAcceptanceResults() {
		return acceptanceResults; 
	}

	public void setAcceptanceResults(Integer acceptanceResults) {
		this.acceptanceResults = acceptanceResults; 
		super.setField("acceptanceResults");
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

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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