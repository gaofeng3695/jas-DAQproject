package cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.base.annotation.UpdateDeleteSet;
import cn.jasgroup.jasframework.engine.hibernate.entity.CommonHibernateEntity;

/***
 * 
  *<p>类描述：冷弯管实体类。
  *{@link cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service.ColdBendingPipeService #saveChanageOriginalPipeUseState()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service.ColdBendingPipeService #updateChanageOriginalPipeUseState()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service.ColdBendingPipeService #deleteChanageOriginalPipeUseState()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service.ColdBendingPipeService #updateChanageBeforeAdvice()}</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年6月29日 下午6:16:38。</p>
 */
@CommonSaveConfig(
    afterAdvice = {
        @Process(service = "coldBendingPipeService", method = "saveChanageOriginalPipeUseState()")
    }
)
@CommonUpdateConfig(
	beforeAdvice={
			@Process(service = "coldBendingPipeService", method = "updateChanageBeforeAdvice()")
		},
	afterAdvice={
			@Process(service = "coldBendingPipeService", method = "updateChanageOriginalPipeUseState()")
	}
)
@CommonDeleteConfig(
	afterAdvice={
			@Process(service = "coldBendingPipeService", method = "deleteChanageOriginalPipeUseState()")
		}
)
@UniqueConstraints(
	strategys ={
		@UniqueConstraintStrategy(	
			columnNames={"pipeSegmentOrCrossOid","pipeColdBendingCode"},
			name="同一线路段/穿跨越下冷弯管编号不能重复"
		)
	}
)
@Entity(name="daq_material_pipe_cold_bending")
public class ColdBendingPipe extends CommonHibernateEntity{
	/** 项目oid */
	private String projectOid; 

	/** 标段oid */
	private String tendersOid; 

	/** 管线oid */
	private String pipelineOid; 

	/** 线路段oid */
	private String pipeSegmentOrCrossOid; 

	/** 原材料钢管编号 */
	private String pipeOid; 

	/** 冷弯管编号 */
	private String pipeColdBendingCode; 

	/** 合格证编号 */
	private String certificateNum; 

	/** 管子弯曲设计标准 */
	private String pipeBendingStandards; 

	/** 曲率半径（mm) */
	private Double bendingRadius; 

	/** 弯曲角度(°) */
	private Double bendingAngle; 

	/** 曲线长度(m) */
	private Double curveLength; 

	/** 直管段最小长度(m) */
	private Double straightPipeLength; 

	/** 长度(m) */
	private Double pipeLength; 

	/** 椭圆率(%) */
	private Double ellipticity; 

	/** 壁厚减薄率(%) */
	private Double wallThicknessRedurate; 

	/** 管径(mm) */
	private Double pipeDiameter; 

	/** 壁厚(mm) */
	private Double wallThickness; 

	/** 制作时间 */
	private Date produceDate; 

	/** 施工单位 */
	private String constructUnit; 

	/** 监理单位 */
	private String supervisionUnit; 

	/** 监理工程师 */
	private String supervisionEngineer; 

	/** 采集人员 */
	private String collectionPerson; 

	/** 采集时间 */
	private Date collectionDate; 

	/** 是否使用 */
	private Integer isUse = 0; 
	
	/**
	 *审核状态 
	 */
	private Integer approveStatus = 0; 

	/** 备注 */
	private String remarks;

	@Column(name="project_oid")
	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
		super.setField("projectOid");
	}
	@Column(name="tenders_oid")
	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
		super.setField("tendersOid");
	}

	@Column(name="pipeline_oid")
	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
		super.setField("pipelineOid");
	}
	
	@Column(name="pipe_segment_or_cross_oid")
	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
		super.setField("pipeSegmentOrCrossOid");
	}
	
	@Column(name="pipe_oid")
	public String getPipeOid() {
		return pipeOid;
	}

	public void setPipeOid(String pipeOid) {
		this.pipeOid = pipeOid;
		super.setField("pipeOid");
	}
	
	@Column(name="pipe_cold_bending_code")
	public String getPipeColdBendingCode() {
		return pipeColdBendingCode;
	}

	public void setPipeColdBendingCode(String pipeColdBendingCode) {
		this.pipeColdBendingCode = pipeColdBendingCode;
		super.setField("pipeColdBendingCode");
	}

	@Column(name="certificate_num")
	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
		super.setField("certificateNum");
	}

	@Column(name="pipe_bending_standards")
	public String getPipeBendingStandards() {
		return pipeBendingStandards;
	}

	public void setPipeBendingStandards(String pipeBendingStandards) {
		this.pipeBendingStandards = pipeBendingStandards;
		super.setField("pipeBendingStandards");
	}

	@Column(name="bending_radius")
	public Double getBendingRadius() {
		return bendingRadius;
	}

	public void setBendingRadius(Double bendingRadius) {
		this.bendingRadius = bendingRadius;
		super.setField("bendingRadius");
	}

	@Column(name="bending_angle")
	public Double getBendingAngle() {
		return bendingAngle;
	}

	public void setBendingAngle(Double bendingAngle) {
		this.bendingAngle = bendingAngle;
		super.setField("bendingAngle");
	}

	@Column(name="curve_length")
	public Double getCurveLength() {
		return curveLength;
	}

	public void setCurveLength(Double curveLength) {
		this.curveLength = curveLength;
		super.setField("curveLength");
	}

	@Column(name="straight_pipe_length")
	public Double getStraightPipeLength() {
		return straightPipeLength;
	}

	public void setStraightPipeLength(Double straightPipeLength) {
		this.straightPipeLength = straightPipeLength;
		super.setField("straightPipeLength");
	}

	@Column(name="pipe_length")
	public Double getPipeLength() {
		return pipeLength;
	}

	public void setPipeLength(Double pipeLength) {
		this.pipeLength = pipeLength;
		super.setField("pipeLength");
	}

	public Double getEllipticity() {
		return ellipticity;
	}

	public void setEllipticity(Double ellipticity) {
		this.ellipticity = ellipticity;
		super.setField("ellipticity");
	}

	@Column(name="wall_thickness_redurate")
	public Double getWallThicknessRedurate() {
		return wallThicknessRedurate;
	}

	public void setWallThicknessRedurate(Double wallThicknessRedurate) {
		this.wallThicknessRedurate = wallThicknessRedurate;
		super.setField("wallThicknessRedurate");
	}

	@Column(name="pipe_diameter")
	public Double getPipeDiameter() {
		return pipeDiameter;
	}

	public void setPipeDiameter(Double pipeDiameter) {
		this.pipeDiameter = pipeDiameter;
		super.setField("pipeDiameter");
	}

	@Column(name="wall_thickness")
	public Double getWallThickness() {
		return wallThickness;
	}

	public void setWallThickness(Double wallThickness) {
		this.wallThickness = wallThickness;
		super.setField("wallThickness");
	}
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="produce_date")
	public Date getProduceDate() {
		return produceDate;
	}

	public void setProduceDate(Date produceDate) {
		this.produceDate = produceDate;
		super.setField("produceDate");
	}

	@Column(name="construct_unit")
	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
		super.setField("constructUnit");
	}

	@Column(name="supervision_unit")
	public String getSupervisionUnit() {
		return supervisionUnit;
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit;
		super.setField("supervisionUnit");
	}

	@Column(name="supervision_engineer")
	public String getSupervisionEngineer() {
		return supervisionEngineer;
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer;
		super.setField("supervisionEngineer");
	}

	@Column(name="collection_person")
	public String getCollectionPerson() {
		return collectionPerson;
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson;
		super.setField("collectionPerson");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="collection_date")
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
		super.setField("collectionDate");
	}

	@Column(name="is_use")
	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
		super.setField("isUse");
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
		super.setField("remarks");
	}

	@Column(name="approve_status")
	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	
	
}
