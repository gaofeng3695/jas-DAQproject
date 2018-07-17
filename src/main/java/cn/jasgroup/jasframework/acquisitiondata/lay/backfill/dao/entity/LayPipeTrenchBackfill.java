package cn.jasgroup.jasframework.acquisitiondata.lay.backfill.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
  *<p>类描述：回填实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月16日 下午5:15:46。</p>
 */
@CommonSaveConfig(
	scene = "/layPipeTrenchBackfill/save"
)
@CommonUpdateConfig(
	scene = "/layPipeTrenchBackfill/update"
)
@CommonDeleteConfig(
	scene = "/layPipeTrenchBackfill/delete"
)
@Line(
	scopeFieldName="pipelineOid",
	scopeType=ScopeType.CURRENT,
	geometryColumnName="geom",
	calculateType=CalculateType.DoubleAnchorAndDeviation,
	anchorClass=MedianStake.class,
	startAnchorOid="startMedianStakeOid",
	startDeviation="startRelativeMileage",
	endAnchorOid="endMedianStakeOid",
	endDeviation="endRelativeMileage"
)
@JdbcEntity(name="daq_lay_pipe_trench_backfill")
public class LayPipeTrenchBackfill extends CommonJdbcEntity {

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
	 * 起始桩号 
	 */
	private String startMedianStakeOid; 

	/**
	 * 相对起始桩里程(m) 
	 */
	private Double startRelativeMileage; 

	/**
	 * 结束桩号 
	 */
	private String endMedianStakeOid; 

	/**
	 * 相对结束桩里程(m) 
	 */
	private Double endRelativeMileage; 

	/**
	 * 回填长度(m) 
	 */
	private Double backfillLength; 

	/**
	 * 细土回填厚度(mm) 
	 */
	private Double backfillThickness; 

	/**
	 * 一次回填厚度(mm) 
	 */
	private Double oneBackfillThickness; 

	/**
	 * 二次回填厚度(mm) 
	 */
	private Double twoBackfillThickness; 

	/**
	 * 埋地标识类型 
	 */
	private String signType; 

	/**
	 * 标识长度(m) 
	 */
	private Double signLength; 

	/**
	 * 标识埋深(m) 
	 */
	private Double signDepth; 

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

	private String geom; 

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

	public Double getBackfillLength() {
		return backfillLength; 
	}

	public void setBackfillLength(Double backfillLength) {
		this.backfillLength = backfillLength; 
		super.setField("backfillLength");
	}

	public Double getBackfillThickness() {
		return backfillThickness; 
	}

	public void setBackfillThickness(Double backfillThickness) {
		this.backfillThickness = backfillThickness; 
		super.setField("backfillThickness");
	}

	public Double getOneBackfillThickness() {
		return oneBackfillThickness; 
	}

	public void setOneBackfillThickness(Double oneBackfillThickness) {
		this.oneBackfillThickness = oneBackfillThickness; 
		super.setField("oneBackfillThickness");
	}

	public Double getTwoBackfillThickness() {
		return twoBackfillThickness; 
	}

	public void setTwoBackfillThickness(Double twoBackfillThickness) {
		this.twoBackfillThickness = twoBackfillThickness; 
		super.setField("twoBackfillThickness");
	}

	public String getSignType() {
		return signType; 
	}

	public void setSignType(String signType) {
		this.signType = signType; 
		super.setField("signType");
	}

	public Double getSignLength() {
		return signLength; 
	}

	public void setSignLength(Double signLength) {
		this.signLength = signLength; 
		super.setField("signLength");
	}

	public Double getSignDepth() {
		return signDepth; 
	}

	public void setSignDepth(Double signDepth) {
		this.signDepth = signDepth; 
		super.setField("signDepth");
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

	public String getGeom() {
		return geom; 
	}

	public void setGeom(String geom) {
		this.geom = geom; 
		super.setField("geom");
	}

}