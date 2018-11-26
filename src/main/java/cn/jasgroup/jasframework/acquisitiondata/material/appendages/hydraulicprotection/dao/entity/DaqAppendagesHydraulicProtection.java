package cn.jasgroup.jasframework.acquisitiondata.material.appendages.hydraulicprotection.dao.entity;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
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
 * <p>类描述：水工保护。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月21日 下午1:08:58。</p>
 */

@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"hydraulicProtectionCode"},name="水工保护编号"),
    }
)
@CommonSaveConfig(
	scene = "/appendagesHydraulicProtection/save"
)
@CommonUpdateConfig(
	scene = "/appendagesHydraulicProtection/update"
)
@CommonDeleteConfig(
	scene = "/appendagesHydraulicProtection/delete"
)
@CommonDeleteBatchConfig(
	scene = "/appendagesHydraulicProtection/deleteBatch"
)
@Line(
	startX = "startPointx",
	startY = "startPointy",
	endX = "endPointx",
	endY = "endPointy",
	geometryColumnName = "geom",
	anchorClass=MedianStake.class,
	scopeType=ScopeType.CURRENT,
	geometryState="geoState",
	calculateType = CalculateType.DoubleAnchor
)
@JdbcEntity(name="daq_appendages_hydraulic_protection")
public class DaqAppendagesHydraulicProtection extends CommonJdbcEntity {

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
	 * 水工保护编号 
	 */
	private String hydraulicProtectionCode; 

	/**
	 * 水工保护类型 
	 */
	private String hydraulicProtectionType; 

	/**
	 * 水工保护名称 
	 */
	private String hydraulicProtectionName; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * 起点坐标X 
	 */
	private Double startPointx; 

	/**
	 * 起点坐标Y 
	 */
	private Double startPointy; 

	/**
	 * 终点坐标X 
	 */
	private Double endPointx; 

	/**
	 * 终点坐标Y 
	 */
	private Double endPointy; 

	/**
	 * 结构尺寸 
	 */
	private String structureSize; 

	/**
	 * 工程量(m3) 
	 */
	private Double engineerQuatity; 

	/**
	 * 水工保护材料 
	 */
	private String hydraulicProtectionMaterial; 

	/**
	 * 检查验收日期 
	 */
	private Date acceptDate; 

	/**
	 * 检查结论 
	 */
	private Integer inspectionFindings; 

	/**
	 * 施工机组代号 
	 */
	private String workUnitOid; 

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

	public String getHydraulicProtectionCode() {
		return hydraulicProtectionCode; 
	}

	public void setHydraulicProtectionCode(String hydraulicProtectionCode) {
		this.hydraulicProtectionCode = hydraulicProtectionCode; 
		super.setField("hydraulicProtectionCode");
	}

	public String getHydraulicProtectionType() {
		return hydraulicProtectionType; 
	}

	public void setHydraulicProtectionType(String hydraulicProtectionType) {
		this.hydraulicProtectionType = hydraulicProtectionType; 
		super.setField("hydraulicProtectionType");
	}

	public String getHydraulicProtectionName() {
		return hydraulicProtectionName; 
	}

	public void setHydraulicProtectionName(String hydraulicProtectionName) {
		this.hydraulicProtectionName = hydraulicProtectionName; 
		super.setField("hydraulicProtectionName");
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

	public Double getStartPointx() {
		return startPointx; 
	}

	public void setStartPointx(Double startPointx) {
		this.startPointx = startPointx; 
		super.setField("startPointx");
	}

	public Double getStartPointy() {
		return startPointy; 
	}

	public void setStartPointy(Double startPointy) {
		this.startPointy = startPointy; 
		super.setField("startPointy");
	}

	public Double getEndPointx() {
		return endPointx; 
	}

	public void setEndPointx(Double endPointx) {
		this.endPointx = endPointx; 
		super.setField("endPointx");
	}

	public Double getEndPointy() {
		return endPointy; 
	}

	public void setEndPointy(Double endPointy) {
		this.endPointy = endPointy; 
		super.setField("endPointy");
	}

	public String getStructureSize() {
		return structureSize; 
	}

	public void setStructureSize(String structureSize) {
		this.structureSize = structureSize; 
		super.setField("structureSize");
	}

	public Double getEngineerQuatity() {
		return engineerQuatity; 
	}

	public void setEngineerQuatity(Double engineerQuatity) {
		this.engineerQuatity = engineerQuatity; 
		super.setField("engineerQuatity");
	}

	public String getHydraulicProtectionMaterial() {
		return hydraulicProtectionMaterial; 
	}

	public void setHydraulicProtectionMaterial(String hydraulicProtectionMaterial) {
		this.hydraulicProtectionMaterial = hydraulicProtectionMaterial; 
		super.setField("hydraulicProtectionMaterial");
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getAcceptDate() {
		return acceptDate; 
	}

	public void setAcceptDate(Date acceptDate) {
		this.acceptDate = acceptDate; 
		super.setField("acceptDate");
	}

	public Integer getInspectionFindings() {
		return inspectionFindings; 
	}

	public void setInspectionFindings(Integer inspectionFindings) {
		this.inspectionFindings = inspectionFindings; 
		super.setField("inspectionFindings");
	}

	public String getWorkUnitOid() {
		return workUnitOid; 
	}

	public void setWorkUnitOid(String workUnitOid) {
		this.workUnitOid = workUnitOid; 
		super.setField("workUnitOid");
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