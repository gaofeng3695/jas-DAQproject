package cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.dao.entity;

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
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
  *<p>类描述：阴保电缆实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月18日 上午10:23:33。</p>
 */
@CommonSaveConfig(
	scene = "/cathodicCableProtection/save"
)
@CommonUpdateConfig(
	scene = "/cathodicCableProtection/update"
)
@CommonDeleteConfig(
	scene = "/cathodicCableProtection/delete"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"pipeSegmentOid","cableCode"},name="同一线路段下阴保电缆编号不能重复"),
    }
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
@JdbcEntity(name="daq_cathodic_cable_protection")
public class CathodicCableProtection extends CommonJdbcEntity {

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
	 * 阴保电缆编号 
	 */
	private String cableCode; 

	/**
	 * 电缆规格型号 
	 */
	private String cableSpecification; 

	/**
	 * 电缆盘号 
	 */
	private String cableTrayNum; 

	/**
	 * 电缆批次 
	 */
	private String cableBatche; 

	/**
	 * 电缆保护结构 
	 */
	private String cableStruction; 

	/**
	 * 电缆敷设方式 
	 */
	private String cableLayingMethod; 

	/**
	 * 电缆长度(m) 
	 */
	private Double cableLength; 

	/**
	 * 电缆用途 
	 */
	private String cableUse; 

	/**
	 * 测试桩号 
	 */
	private String testStakeOid; 

	/**
	 * 阴保电源编号 
	 */
	private String cableProtectionCode; 

	/**
	 * 辅助阳极地床编号 
	 */
	private String auxiliaryAnodeBedOid; 

	/**
	 * 牺牲阳极编号 
	 */
	private String sacrificeAnodeOid; 

	/**
	 * 电缆敷设图编号 
	 */
	private String cableLayoutCode; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

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

	public String getCableCode() {
		return cableCode; 
	}

	public void setCableCode(String cableCode) {
		this.cableCode = cableCode; 
		super.setField("cableCode");
	}

	public String getCableSpecification() {
		return cableSpecification; 
	}

	public void setCableSpecification(String cableSpecification) {
		this.cableSpecification = cableSpecification; 
		super.setField("cableSpecification");
	}

	public String getCableTrayNum() {
		return cableTrayNum; 
	}

	public void setCableTrayNum(String cableTrayNum) {
		this.cableTrayNum = cableTrayNum; 
		super.setField("cableTrayNum");
	}

	public String getCableBatche() {
		return cableBatche; 
	}

	public void setCableBatche(String cableBatche) {
		this.cableBatche = cableBatche; 
		super.setField("cableBatche");
	}

	public String getCableStruction() {
		return cableStruction; 
	}

	public void setCableStruction(String cableStruction) {
		this.cableStruction = cableStruction; 
		super.setField("cableStruction");
	}

	public String getCableLayingMethod() {
		return cableLayingMethod; 
	}

	public void setCableLayingMethod(String cableLayingMethod) {
		this.cableLayingMethod = cableLayingMethod; 
		super.setField("cableLayingMethod");
	}

	public Double getCableLength() {
		return cableLength; 
	}

	public void setCableLength(Double cableLength) {
		this.cableLength = cableLength; 
		super.setField("cableLength");
	}

	public String getCableUse() {
		return cableUse; 
	}

	public void setCableUse(String cableUse) {
		this.cableUse = cableUse; 
		super.setField("cableUse");
	}

	public String getTestStakeOid() {
		return testStakeOid; 
	}

	public void setTestStakeOid(String testStakeOid) {
		this.testStakeOid = testStakeOid; 
		super.setField("testStakeOid");
	}

	public String getCableProtectionCode() {
		return cableProtectionCode; 
	}

	public void setCableProtectionCode(String cableProtectionCode) {
		this.cableProtectionCode = cableProtectionCode; 
		super.setField("cableProtectionCode");
	}

	public String getAuxiliaryAnodeBedOid() {
		return auxiliaryAnodeBedOid; 
	}

	public void setAuxiliaryAnodeBedOid(String auxiliaryAnodeBedOid) {
		this.auxiliaryAnodeBedOid = auxiliaryAnodeBedOid; 
		super.setField("auxiliaryAnodeBedOid");
	}

	public String getSacrificeAnodeOid() {
		return sacrificeAnodeOid; 
	}

	public void setSacrificeAnodeOid(String sacrificeAnodeOid) {
		this.sacrificeAnodeOid = sacrificeAnodeOid; 
		super.setField("sacrificeAnodeOid");
	}

	public String getCableLayoutCode() {
		return cableLayoutCode; 
	}

	public void setCableLayoutCode(String cableLayoutCode) {
		this.cableLayoutCode = cableLayoutCode; 
		super.setField("cableLayoutCode");
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