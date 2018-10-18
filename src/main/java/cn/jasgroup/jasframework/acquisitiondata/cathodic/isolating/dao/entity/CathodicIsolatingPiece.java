package cn.jasgroup.jasframework.acquisitiondata.cathodic.isolating.dao.entity;

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
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
  *<p>类描述：绝缘件实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月18日 上午8:55:32。</p>
 */
@CommonSaveConfig(
	scene = "/cathodicIsolatingPiece/save"
)
@CommonUpdateConfig(
	scene = "/cathodicIsolatingPiece/update"
)
@CommonDeleteConfig(
	scene = "/cathodicIsolatingPiece/delete"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"pipeSegmentOid","isolatingPieceCode"},name="同一线路段下绝缘件编号不能重复"),
    }
)
@Point(
	x="pointx",
	y="pointy" ,
	z="pointz" ,
	scopeFieldName="pipelineOid",
	scopeType=ScopeType.CURRENT,
	geometryColumnName="geom",
	calculateType=CalculateType.SingleAnchorAndDeviation,
	anchorClass=MedianStake.class,
	anchorOid="medianStakeOid",
	deviation="relativeMileage"
)
@JdbcEntity(name="daq_cathodic_isolating_piece")
public class CathodicIsolatingPiece extends CommonJdbcEntity {

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
	 * 线路段oid 
	 */
	private String pipeSegmentOid; 

	/**
	 * 绝缘件编号 
	 */
	private String isolatingPieceCode; 

	/**
	 * 名称 
	 */
	private String isolatingPieceName; 

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
	 * 高程(m) 
	 */
	private Double pointz; 

	/**
	 * 前管件编号 
	 */
	private String startPipeFittingNum; 

	/**
	 * 前管件类型 
	 */
	private String startPipeFittingType; 

	/**
	 * 后管件编号 
	 */
	private String endPipeFittingNum; 

	/**
	 * 后管件类型 
	 */
	private String endPipeFittingType; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 

	/**
	 * 施工日期 
	 */
	private Date constructDate; 

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

	public String getPipeSegmentOid() {
		return pipeSegmentOid; 
	}

	public void setPipeSegmentOid(String pipeSegmentOid) {
		this.pipeSegmentOid = pipeSegmentOid; 
		super.setField("pipeSegmentOid");
	}

	public String getIsolatingPieceCode() {
		return isolatingPieceCode; 
	}

	public void setIsolatingPieceCode(String isolatingPieceCode) {
		this.isolatingPieceCode = isolatingPieceCode; 
		super.setField("isolatingPieceCode");
	}

	public String getIsolatingPieceName() {
		return isolatingPieceName; 
	}

	public void setIsolatingPieceName(String isolatingPieceName) {
		this.isolatingPieceName = isolatingPieceName; 
		super.setField("isolatingPieceName");
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

	public String getStartPipeFittingNum() {
		return startPipeFittingNum; 
	}

	public void setStartPipeFittingNum(String startPipeFittingNum) {
		this.startPipeFittingNum = startPipeFittingNum; 
		super.setField("startPipeFittingNum");
	}

	public String getStartPipeFittingType() {
		return startPipeFittingType; 
	}

	public void setStartPipeFittingType(String startPipeFittingType) {
		this.startPipeFittingType = startPipeFittingType; 
		super.setField("startPipeFittingType");
	}

	public String getEndPipeFittingNum() {
		return endPipeFittingNum; 
	}

	public void setEndPipeFittingNum(String endPipeFittingNum) {
		this.endPipeFittingNum = endPipeFittingNum; 
		super.setField("endPipeFittingNum");
	}

	public String getEndPipeFittingType() {
		return endPipeFittingType; 
	}

	public void setEndPipeFittingType(String endPipeFittingType) {
		this.endPipeFittingType = endPipeFittingType; 
		super.setField("endPipeFittingType");
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
		super.setField("constructUnit");
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
