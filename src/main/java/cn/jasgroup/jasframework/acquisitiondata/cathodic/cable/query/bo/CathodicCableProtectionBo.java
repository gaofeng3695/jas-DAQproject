package cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
  *<p>类描述：阴保电缆Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月18日 上午10:26:54。</p>
 */
public class CathodicCableProtectionBo extends CommonBaseBo{

	/**
	 * oid
	 */
	private String oid;
	
	/**
	 * 项目oid 
	 */
	private String projectOid; 
	
	/**
	 * 项目名称
	 */
	private String projectName; 

	/**
	 * 管线oid 
	 */
	private String pipelineOid; 

	/**
	 * 管线名称
	 */
	private String pipelineName; 

	/**
	 * 标段oid 
	 */
	private String tendersOid; 

	/**
	 * 标段名称
	 */
	private String tendersName; 

	/**
	 * 线路段
	 */
	private String pipeSegmentOid; 

	/**
	 * 线路段名称
	 */
	private String pipeSegmentName;

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
	 * 电缆保护结构 
	 */
	private String cableStructionName; 

	/**
	 * 电缆敷设方式 
	 */
	private String cableLayingMethod; 

	/**
	 * 电缆敷设方式名称 
	 */
	private String cableLayingMethodName; 

	/**
	 * 电缆长度(m) 
	 */
	private Double cableLength; 

	/**
	 * 电缆用途 
	 */
	private String cableUse; 

	/**
	 * 电缆用途名称 
	 */
	private String cableUseName; 

	/**
	 * 测试桩号 
	 */
	private String testStakeOid; 

	/**
	 * 测试桩号 
	 */
	private String testStakeCode; 

	/**
	 * 阴保电源编号 
	 */
	private String cableProtectionCode; 

	/**
	 * 辅助阳极地床编号 
	 */
	private String auxiliaryAnodeBedOid; 

	/**
	 * 辅助阳极地床编号 
	 */
	private String groundBed; 

	/**
	 * 牺牲阳极编号 
	 */
	private String sacrificeAnodeOid; 

	/**
	 * 牺牲阳极编号 
	 */
	private String anodeCode; 

	/**
	 * 电缆敷设图编号 
	 */
	private String cableLayoutCode; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 桩号编码
	 */
	private String medianStakeCode; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 

	/**
	 * 施工单位名称 
	 */
	private String constructUnitName; 

	/**
	 * 施工日期 
	 */
	private Date constructDate; 

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

	public String getPipeSegmentOid() {
		return pipeSegmentOid;
	}

	public void setPipeSegmentOid(String pipeSegmentOid) {
		this.pipeSegmentOid = pipeSegmentOid;
	}

	public String getPipeSegmentName() {
		return pipeSegmentName;
	}

	public void setPipeSegmentName(String pipeSegmentName) {
		this.pipeSegmentName = pipeSegmentName;
	}

	public String getCableCode() {
		return cableCode;
	}

	public void setCableCode(String cableCode) {
		this.cableCode = cableCode;
	}

	public String getCableSpecification() {
		return cableSpecification;
	}

	public void setCableSpecification(String cableSpecification) {
		this.cableSpecification = cableSpecification;
	}

	public String getCableTrayNum() {
		return cableTrayNum;
	}

	public void setCableTrayNum(String cableTrayNum) {
		this.cableTrayNum = cableTrayNum;
	}

	public String getCableBatche() {
		return cableBatche;
	}

	public void setCableBatche(String cableBatche) {
		this.cableBatche = cableBatche;
	}

	public String getTestStakeCode() {
		return testStakeCode;
	}

	public void setTestStakeCode(String testStakeCode) {
		this.testStakeCode = testStakeCode;
	}

	public String getGroundBed() {
		return groundBed;
	}

	public void setGroundBed(String groundBed) {
		this.groundBed = groundBed;
	}

	public String getAnodeCode() {
		return anodeCode;
	}

	public void setAnodeCode(String anodeCode) {
		this.anodeCode = anodeCode;
	}

	public String getCableStruction() {
		return cableStruction;
	}

	public void setCableStruction(String cableStruction) {
		this.cableStruction = cableStruction;
	}

	public String getCableStructionName() {
		return cableStructionName;
	}

	public void setCableStructionName(String cableStructionName) {
		this.cableStructionName = cableStructionName;
	}

	public String getCableLayingMethod() {
		return cableLayingMethod;
	}

	public void setCableLayingMethod(String cableLayingMethod) {
		this.cableLayingMethod = cableLayingMethod;
	}

	public String getCableLayingMethodName() {
		return cableLayingMethodName;
	}

	public void setCableLayingMethodName(String cableLayingMethodName) {
		this.cableLayingMethodName = cableLayingMethodName;
	}

	public Double getCableLength() {
		return cableLength;
	}

	public void setCableLength(Double cableLength) {
		this.cableLength = cableLength;
	}

	public String getCableUse() {
		return cableUse;
	}

	public void setCableUse(String cableUse) {
		this.cableUse = cableUse;
	}

	public String getCableUseName() {
		return cableUseName;
	}

	public void setCableUseName(String cableUseName) {
		this.cableUseName = cableUseName;
	}

	public String getTestStakeOid() {
		return testStakeOid;
	}

	public void setTestStakeOid(String testStakeOid) {
		this.testStakeOid = testStakeOid;
	}

	public String getCableProtectionCode() {
		return cableProtectionCode;
	}

	public void setCableProtectionCode(String cableProtectionCode) {
		this.cableProtectionCode = cableProtectionCode;
	}

	public String getAuxiliaryAnodeBedOid() {
		return auxiliaryAnodeBedOid;
	}

	public void setAuxiliaryAnodeBedOid(String auxiliaryAnodeBedOid) {
		this.auxiliaryAnodeBedOid = auxiliaryAnodeBedOid;
	}

	public String getSacrificeAnodeOid() {
		return sacrificeAnodeOid;
	}

	public void setSacrificeAnodeOid(String sacrificeAnodeOid) {
		this.sacrificeAnodeOid = sacrificeAnodeOid;
	}

	public String getCableLayoutCode() {
		return cableLayoutCode;
	}

	public void setCableLayoutCode(String cableLayoutCode) {
		this.cableLayoutCode = cableLayoutCode;
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

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getConstructDate() {
		return constructDate;
	}

	public void setConstructDate(Date constructDate) {
		this.constructDate = constructDate;
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
