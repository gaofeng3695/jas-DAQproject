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
	private String projectName;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 管线编号
	 */
	private String pipelineName;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段编号
	 */
	private String tendersName;

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
	 * 前管件类型名称
	 */
	private String frontPipeTypeName;

	/**
	 * 前管件编号
	 */
	private String frontPipeCode;
	/***
	 * 前管件oid
	 */
	private String frontPipeOid;

	/**
	 * 后管件类型
	 */
	private String backPipeType;
	
	/**
	 * 后管件类型名称
	 */
	private String backPipeTypeName;

	/**
	 * 后管件编号
	 */
	private String backPipeCode;
	/**
	 * 后管件oid
	 */
	private String backPipeOid;

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

	// add by cuixianing 2019-2-20
	/**
	 *是否检测
	 */
	private Integer isDetection;

	private String fOid;
	private String fWeldCode;
	private String fFrontPipeOid;
	private String fFrontPipeType;
	private String fFrontPipeCode;
	
	private String bOid;
	private String bWeldCode;
	private String bBackPipeOid;
	private String bBackPipeCode;
	private String bBackPipeType;
	
	private String frontPipeLength;
	private String backPipeLength;
	
	private String fFrontPipeLength;
	private String bBackPipeLength;

	/**
	 * 是否割口
	 */
	private Integer isCut;

	/**
	 * 是否射线检测
	 */
	private Integer isRay;

	/**
	 * 是否超声波检查
	 */
	private Integer isUltrasonic;

	/**
	 * 是否渗透检测
	 */
	private Integer isInfiltration;

	/**
	 * 是否磁粉检测
	 */
	private Integer isMagneticPowder;

	/**
	 * 是否全自动检测
	 */
	private Integer isFaUltrasonic;

	/**
	 * 相控阵超声波检测
	 */
	private Integer isPaUltrasonic;

	/**
	 * 是否补扣
	 */
	private Integer isAnticorrosionCheck;

	/**
	 * 是否返修
	 */
	private Integer isRework;

	/**
	 * 是否测量
	 */
	private Integer isMeasure;

	/**
	 * 是否回填
	 */
	private Integer isBackfill;

	/**
	 * 是否地貌恢复
	 */
	private Integer isLandRestoration;

	/**
	 * 是否存在切管
	 */
	private Integer hasCutPipe;

	/**
	 * 是否变径
	 */
	private Integer hasReducer;

	/**
	 * 是否有弯管
	 */
	private Integer hasBendPipe;

	// add by cuixianing 2019-3-5
	/**
	 * 气流方向
	 */
	private Integer airflowDirection;

    /**
     * 气流方向名称
     */
    private String airflowDirectionName;

	/**
	 * 焊口顺序号1
	 */
	private Double sequenceNumberFirst;

	/**
	 * 焊口顺序号2
	 */
	private Double sequenceNumberSecond;

    public String getAirflowDirectionName() {
        return airflowDirectionName;
    }

    public void setAirflowDirectionName(String airflowDirectionName) {
        this.airflowDirectionName = airflowDirectionName;
    }

    public Integer getAirflowDirection() {
        return airflowDirection;
    }

    public void setAirflowDirection(Integer airflowDirection) {
        this.airflowDirection = airflowDirection;
    }

    public Double getSequenceNumberFirst() {
        return sequenceNumberFirst;
    }

    public void setSequenceNumberFirst(Double sequenceNumberFirst) {
        this.sequenceNumberFirst = sequenceNumberFirst;
    }

    public Double getSequenceNumberSecond() {
        return sequenceNumberSecond;
    }

    public void setSequenceNumberSecond(Double sequenceNumberSecond) {
        this.sequenceNumberSecond = sequenceNumberSecond;
    }

    public Integer getIsCut() {
        return isCut;
    }

    public void setIsCut(Integer isCut) {
        this.isCut = isCut;
    }

    public Integer getIsRay() {
        return isRay;
    }

    public void setIsRay(Integer isRay) {
        this.isRay = isRay;
    }

    public Integer getIsUltrasonic() {
        return isUltrasonic;
    }

    public void setIsUltrasonic(Integer isUltrasonic) {
        this.isUltrasonic = isUltrasonic;
    }

    public Integer getIsInfiltration() {
        return isInfiltration;
    }

    public void setIsInfiltration(Integer isInfiltration) {
        this.isInfiltration = isInfiltration;
    }

    public Integer getIsMagneticPowder() {
        return isMagneticPowder;
    }

    public void setIsMagneticPowder(Integer isMagneticPowder) {
        this.isMagneticPowder = isMagneticPowder;
    }

    public Integer getIsFaUltrasonic() {
        return isFaUltrasonic;
    }

    public void setIsFaUltrasonic(Integer isFaUltrasonic) {
        this.isFaUltrasonic = isFaUltrasonic;
    }

    public Integer getIsPaUltrasonic() {
        return isPaUltrasonic;
    }

    public void setIsPaUltrasonic(Integer isPaUltrasonic) {
        this.isPaUltrasonic = isPaUltrasonic;
    }

    public Integer getIsAnticorrosionCheck() {
        return isAnticorrosionCheck;
    }

    public void setIsAnticorrosionCheck(Integer isAnticorrosionCheck) {
        this.isAnticorrosionCheck = isAnticorrosionCheck;
    }

    public Integer getIsRework() {
        return isRework;
    }

    public void setIsRework(Integer isRework) {
        this.isRework = isRework;
    }

    public Integer getIsMeasure() {
        return isMeasure;
    }

    public void setIsMeasure(Integer isMeasure) {
        this.isMeasure = isMeasure;
    }

    public Integer getIsBackfill() {
        return isBackfill;
    }

    public void setIsBackfill(Integer isBackfill) {
        this.isBackfill = isBackfill;
    }

    public Integer getIsLandRestoration() {
        return isLandRestoration;
    }

    public void setIsLandRestoration(Integer isLandRestoration) {
        this.isLandRestoration = isLandRestoration;
    }

    public Integer getHasCutPipe() {
        return hasCutPipe;
    }

    public void setHasCutPipe(Integer hasCutPipe) {
        this.hasCutPipe = hasCutPipe;
    }

    public Integer getHasReducer() {
        return hasReducer;
    }

    public void setHasReducer(Integer hasReducer) {
        this.hasReducer = hasReducer;
    }

    public Integer getHasBendPipe() {
        return hasBendPipe;
    }

    public void setHasBendPipe(Integer hasBendPipe) {
        this.hasBendPipe = hasBendPipe;
    }

    public Integer getIsDetection() {
        return isDetection;
    }

    public void setIsDetection(Integer isDetection) {
        this.isDetection = isDetection;
    }

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

	public String getPipelineName() {
		return pipelineName;
	}

	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
	}

	public String getTendersName() {
		return tendersName;
	}

	public void setTendersName(String tendersName) {
		this.tendersName = tendersName;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
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

	public String getFrontPipeTypeName() {
		return frontPipeTypeName;
	}

	public void setFrontPipeTypeName(String frontPipeTypeName) {
		this.frontPipeTypeName = frontPipeTypeName;
	}

	public String getBackPipeTypeName() {
		return backPipeTypeName;
	}

	public void setBackPipeTypeName(String backPipeTypeName) {
		this.backPipeTypeName = backPipeTypeName;
	}

	public String getFrontPipeOid() {
		return frontPipeOid;
	}

	public void setFrontPipeOid(String frontPipeOid) {
		this.frontPipeOid = frontPipeOid;
	}

	public String getBackPipeOid() {
		return backPipeOid;
	}

	public void setBackPipeOid(String backPipeOid) {
		this.backPipeOid = backPipeOid;
	}

	public String getFOid() {
		return fOid;
	}

	public void setFOid(String fOid) {
		this.fOid = fOid;
	}

	public String getFWeldCode() {
		return fWeldCode;
	}

	public void setFWeldCode(String fWeldCode) {
		this.fWeldCode = fWeldCode;
	}

	public String getFFrontPipeOid() {
		return fFrontPipeOid;
	}

	public void setFFrontPipeOid(String fFrontPipeOid) {
		this.fFrontPipeOid = fFrontPipeOid;
	}

	public String getFFrontPipeType() {
		return fFrontPipeType;
	}

	public void setFFrontPipeType(String fFrontPipeType) {
		this.fFrontPipeType = fFrontPipeType;
	}

	public String getFFrontPipeCode() {
		return fFrontPipeCode;
	}

	public void setFFrontPipeCode(String fFrontPipeCode) {
		this.fFrontPipeCode = fFrontPipeCode;
	}

	public String getBOid() {
		return bOid;
	}

	public void setBOid(String bOid) {
		this.bOid = bOid;
	}

	public String getBWeldCode() {
		return bWeldCode;
	}

	public void setBWeldCode(String bWeldCode) {
		this.bWeldCode = bWeldCode;
	}

	public String getBBackPipeOid() {
		return bBackPipeOid;
	}

	public void setBBackPipeOid(String bBackPipeOid) {
		this.bBackPipeOid = bBackPipeOid;
	}

	public String getBBackPipeCode() {
		return bBackPipeCode;
	}

	public void setBBackPipeCode(String bBackPipeCode) {
		this.bBackPipeCode = bBackPipeCode;
	}

	public String getBBackPipeType() {
		return bBackPipeType;
	}

	public void setBBackPipeType(String bBackPipeType) {
		this.bBackPipeType = bBackPipeType;
	}

	public String getFrontPipeLength() {
		return frontPipeLength;
	}

	public void setFrontPipeLength(String frontPipeLength) {
		this.frontPipeLength = frontPipeLength;
	}

	public String getBackPipeLength() {
		return backPipeLength;
	}

	public void setBackPipeLength(String backPipeLength) {
		this.backPipeLength = backPipeLength;
	}

	public String getFFrontPipeLength() {
		return fFrontPipeLength;
	}

	public void setFFrontPipeLength(String fFrontPipeLength) {
		this.fFrontPipeLength = fFrontPipeLength;
	}

	public String getBBackPipeLength() {
		return bBackPipeLength;
	}

	public void setBBackPipeLength(String bBackPipeLength) {
		this.bBackPipeLength = bBackPipeLength;
	}
	
	
}
