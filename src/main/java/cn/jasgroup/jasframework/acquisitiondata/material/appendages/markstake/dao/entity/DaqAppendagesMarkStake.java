package cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.dao.entity;
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
 * <p>类描述：标志桩。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月18日 上午11:24:51。</p>
 */

@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"markStakeOid"},name="标志桩编号"),
    }
)
@CommonSaveConfig(
	scene = "/appendagesMarkStake/save"
)
@CommonUpdateConfig(
	scene = "/appendagesMarkStake/update"
)
@CommonDeleteConfig(
	scene = "/appendagesMarkStake/delete"
)
@CommonDeleteBatchConfig(
	scene = "/appendagesMarkStake/deleteBatch"
)
@Point(
	x = "pointx",
	y = "pointy",
	geometryColumnName = "geom",
	calculateType = CalculateType.SingleAnchorAndDeviation,
	anchorClass = MedianStake.class,
	scopeFieldName = "pipelineOid",
	anchorOid = "medianStakeOid",
	deviation = "relativeMileage"
	
)
@JdbcEntity(name="daq_appendages_mark_stake")
public class DaqAppendagesMarkStake extends CommonJdbcEntity {

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
	 * 标志桩编号 
	 */
	private String markStakeOid; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * 东坐标 
	 */
	private Double pointx; 

	/**
	 * 北坐标 
	 */
	private Double pointy; 

	/**
	 * 桩体结构 
	 */
	private String stakeStructure; 

	/**
	 * 埋设日期 
	 */
	private Date burialDate; 

	/**
	 * 桩功能 
	 */
	private String stakeFunction; 

	/**
	 * 埋深(m) 
	 */
	private Double burialDepth; 

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

	private String geom; 

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

	public String getMarkStakeOid() {
		return markStakeOid; 
	}

	public void setMarkStakeOid(String markStakeOid) {
		this.markStakeOid = markStakeOid; 
		super.setField("markStakeOid");
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

	public String getStakeStructure() {
		return stakeStructure; 
	}

	public void setStakeStructure(String stakeStructure) {
		this.stakeStructure = stakeStructure; 
		super.setField("stakeStructure");
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

	public String getStakeFunction() {
		return stakeFunction; 
	}

	public void setStakeFunction(String stakeFunction) {
		this.stakeFunction = stakeFunction; 
		super.setField("stakeFunction");
	}

	public Double getBurialDepth() {
		return burialDepth; 
	}

	public void setBurialDepth(Double burialDepth) {
		this.burialDepth = burialDepth; 
		super.setField("burialDepth");
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

	public String getGeom() {
		return geom; 
	}

	public void setGeom(String geom) {
		this.geom = geom; 
		super.setField("geom");
	}

}