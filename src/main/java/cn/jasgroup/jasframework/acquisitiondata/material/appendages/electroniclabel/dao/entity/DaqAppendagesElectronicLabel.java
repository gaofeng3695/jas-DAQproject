package cn.jasgroup.jasframework.acquisitiondata.material.appendages.electroniclabel.dao.entity;
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
 * <p>类描述：电子标签。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月21日 上午11:19:45。</p>
 */

@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"electronicLabelCode"},name="电子标签编号"),
    }
)
@CommonSaveConfig(
	scene = "/appendagesElectronicLabel/save"
)
@CommonUpdateConfig(
	scene = "/appendagesElectronicLabel/update"
)
@CommonDeleteConfig(
	scene = "/appendagesElectronicLabel/delete"
)
@CommonDeleteBatchConfig(
	scene = "/appendagesElectronicLabel/deleteBatch"
)
@Point(
	x = "pointx",
	y = "pointy",
	z = "pointz",
	geometryColumnName = "geom",
	calculateType = CalculateType.Coordinates,
	anchorClass = MedianStake.class,
	anchorOid = "medianStakeOid"
)
@JdbcEntity(name="daq_appendages_electronic_label")
public class DaqAppendagesElectronicLabel extends CommonJdbcEntity {

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
	 * 电子标签编号 
	 */
	private String electronicLabelCode; 

	/**
	 * 产品编号 
	 */
	private String productNum; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 坐标x 
	 */
	private Double pointx; 

	/**
	 * 坐标Y 
	 */
	private Double pointy; 

	/**
	 * 高程 
	 */
	private Double pointz; 

	/**
	 * 埋深(m) 
	 */
	private Double burialDepth; 

	/**
	 * 特征点类型 
	 */
	private String featurePointType; 

	/**
	 * 埋设日期 
	 */
	private Date burialDate; 

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

	public String getElectronicLabelCode() {
		return electronicLabelCode; 
	}

	public void setElectronicLabelCode(String electronicLabelCode) {
		this.electronicLabelCode = electronicLabelCode; 
		super.setField("electronicLabelCode");
	}

	public String getProductNum() {
		return productNum; 
	}

	public void setProductNum(String productNum) {
		this.productNum = productNum; 
		super.setField("productNum");
	}

	public String getMedianStakeOid() {
		return medianStakeOid; 
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid; 
		super.setField("medianStakeOid");
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

	public Double getBurialDepth() {
		return burialDepth; 
	}

	public void setBurialDepth(Double burialDepth) {
		this.burialDepth = burialDepth; 
		super.setField("burialDepth");
	}

	public String getFeaturePointType() {
		return featurePointType; 
	}

	public void setFeaturePointType(String featurePointType) {
		this.featurePointType = featurePointType; 
		super.setField("featurePointType");
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getBurialDate() {
		return burialDate; 
	}

	public void setBurialDate(Date burialDate) {
		this.burialDate = burialDate; 
		super.setField("burialDate");
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