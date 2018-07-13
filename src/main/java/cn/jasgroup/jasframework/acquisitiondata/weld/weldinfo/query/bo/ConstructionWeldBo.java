package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * 
  *<p>类描述：焊口信息Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月11日 下午2:10:36。</p>
 */
public class ConstructionWeldBo extends CommonBaseBo {
	
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
	 * 焊口编号
	 */
	private String weldCode;

	/**
	 * 焊口类型
	 */
	private String weldType;

	/**
	 * 焊口类型名称
	 */
	private String weldTypeName;

	/**
	 * 焊接方式
	 */
	private String weldMethod;

	/**
	 * 焊接方式名称
	 */
	private String weldMethodName;

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
	 * 前管件类型
	 */
	private String frontPipeType;

	/**
	 * 前管件编号
	 */
	private String frontPipeCode;

	/**
	 * 后管件类型
	 */
	private String backPipeType;

	/**
	 * 后管件编号
	 */
	private String backPipeCode;

	/**
	 * 焊条批号
	 */
	private String weldRodBatchNum;

	/**
	 * 焊丝批号
	 */
	private String weldWireBatchNum;

	/**
	 * 焊接工艺规程
	 */
	private String weldProduce;

	/**
	 *  焊接工艺规程编号
	 */
	private String weldProduceCode; 

	/**
	 * 外观质量检查
	 */
	private Integer surfaceCheck;

	/**
	 * 施工单位
	 */
	private String constructUnit;

	/**
	 * 施工单位名称
	 */
	private String constructUnitName;

	/**
	 * 施工机组代号
	 */
	private String workUnitOid;

	/**
	 * 施工机组编号
	 */
	private String workUnitCode;

	/** 
	 * 填充人员 
	 */
	private String coverOid; 

	/** 
	 * 填充人员名称 
	 */
	private String coverName; 

	/** 
	 * 打底人员
	 */
	private String padderOid;

	/** 
	 * 打底人员名称 
	 */
	private String padderName; 

	/** 
	 * 盖面人员
	 */
	private String renderOid; 

	/** 
	 * 盖面人员名称 
	 */
	private String renderName; 

	/**
	 * 是否金口
	 */
	private Integer isGoldeJoint;

	/**
	 * 是否连头口
	 */
	private Integer isPipeHead;

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
	 * 审核状态
	 */
	private Integer approveStatus;

	/**
	 * 空间数据状态
	 */
	private String geoState;

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

	public String getWeldCode() {
		return weldCode;
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
	}

	public String getWeldType() {
		return weldType;
	}

	public void setWeldType(String weldType) {
		this.weldType = weldType;
	}

	public String getWeldTypeName() {
		return weldTypeName;
	}

	public void setWeldTypeName(String weldTypeName) {
		this.weldTypeName = weldTypeName;
	}

	public String getWeldMethod() {
		return weldMethod;
	}

	public void setWeldMethod(String weldMethod) {
		this.weldMethod = weldMethod;
	}

	public String getWeldMethodName() {
		return weldMethodName;
	}

	public void setWeldMethodName(String weldMethodName) {
		this.weldMethodName = weldMethodName;
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

	public String getFrontPipeType() {
		return frontPipeType;
	}

	public void setFrontPipeType(String frontPipeType) {
		this.frontPipeType = frontPipeType;
	}

	public String getFrontPipeCode() {
		return frontPipeCode;
	}

	public void setFrontPipeCode(String frontPipeCode) {
		this.frontPipeCode = frontPipeCode;
	}

	public String getBackPipeType() {
		return backPipeType;
	}

	public void setBackPipeType(String backPipeType) {
		this.backPipeType = backPipeType;
	}

	public String getBackPipeCode() {
		return backPipeCode;
	}

	public void setBackPipeCode(String backPipeCode) {
		this.backPipeCode = backPipeCode;
	}

	public String getWeldRodBatchNum() {
		return weldRodBatchNum;
	}

	public void setWeldRodBatchNum(String weldRodBatchNum) {
		this.weldRodBatchNum = weldRodBatchNum;
	}

	public String getWeldWireBatchNum() {
		return weldWireBatchNum;
	}

	public void setWeldWireBatchNum(String weldWireBatchNum) {
		this.weldWireBatchNum = weldWireBatchNum;
	}

	public String getWeldProduce() {
		return weldProduce;
	}

	public void setWeldProduce(String weldProduce) {
		this.weldProduce = weldProduce;
	}

	public String getWeldProduceCode() {
		return weldProduceCode;
	}

	public void setWeldProduceCode(String weldProduceCode) {
		this.weldProduceCode = weldProduceCode;
	}

	public Integer getSurfaceCheck() {
		return surfaceCheck;
	}

	public void setSurfaceCheck(Integer surfaceCheck) {
		this.surfaceCheck = surfaceCheck;
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

	public String getWorkUnitOid() {
		return workUnitOid;
	}

	public void setWorkUnitOid(String workUnitOid) {
		this.workUnitOid = workUnitOid;
	}

	public String getWorkUnitCode() {
		return workUnitCode;
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode;
	}

	public String getCoverOid() {
		return coverOid;
	}

	public void setCoverOid(String coverOid) {
		this.coverOid = coverOid;
	}

	public String getCoverName() {
		return coverName;
	}

	public void setCoverName(String coverName) {
		this.coverName = coverName;
	}

	public String getPadderName() {
		return padderName;
	}

	public void setPadderName(String padderName) {
		this.padderName = padderName;
	}

	public String getRenderName() {
		return renderName;
	}

	public void setRenderName(String renderName) {
		this.renderName = renderName;
	}

	public String getPadderOid() {
		return padderOid;
	}

	public void setPadderOid(String padderOid) {
		this.padderOid = padderOid;
	}

	public String getRenderOid() {
		return renderOid;
	}

	public void setRenderOid(String renderOid) {
		this.renderOid = renderOid;
	}

	public Integer getIsGoldeJoint() {
		return isGoldeJoint;
	}

	public void setIsGoldeJoint(Integer isGoldeJoint) {
		this.isGoldeJoint = isGoldeJoint;
	}

	public Integer getIsPipeHead() {
		return isPipeHead;
	}

	public void setIsPipeHead(Integer isPipeHead) {
		this.isPipeHead = isPipeHead;
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

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getGeoState() {
		return geoState;
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
