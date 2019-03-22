package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>类描述：焊口实体类
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.service.PipeFittingService #saveChanagePipeFittingUseState()}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.service.PipeFittingService #updateChanagePipeFittingUseState()}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.service.PipeFittingService #deleteChanagePipeFittingUseState()}
 * {@link cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.service.PipeFittingService #updateChanageBeforeAdvice()}
 * {@link cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService #updateConstructionWeldFiled()}</p>
 * @author admin 。
 * @version v1.0.0.1。
 * @since JDK1.8.0_101。
 * <p>创建日期：2018-07-11 10:43:23。</p>
 */
@CommonSaveConfig(
		scene = "/constructionWeld/save",
		beforeAdvice={
			@Process(service = "weldService", method = "updateConstructionWeldFiled()")
		},
		afterAdvice={
			@Process(service = "pipeFittingService", method = "saveChanagePipeFittingUseState()")
		}
)
@CommonUpdateConfig(
	scene = "/constructionWeld/update",
	beforeAdvice={
			@Process(service = "pipeFittingService", method = "updateChanageBeforeAdvice()"),
            @Process(service = "weldService", method = "updateConstructionWeldFiled()")
		},
	afterAdvice={
			@Process(service = "pipeFittingService", method = "updateChanagePipeFittingUseState()")
		}
)
@CommonDeleteConfig(
	scene = "/constructionWeld/delete",
	afterAdvice={
			@Process(service = "pipeFittingService", method = "deleteChanagePipeFittingUseState()")
		}
)
@CommonDeleteBatchConfig(
		scene = "/constructionWeld/deleteBatch"
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
@UniqueConstraints(
	strategys ={
		@UniqueConstraintStrategy(
			columnNames={"pipeSegmentOrCrossOid","weldCode"},
			name="同一线路段/穿跨越下焊口编号不能重复"
		)
	}
)
@JdbcEntity(name="daq_construction_weld")
public class ConstructionWeld extends CommonJdbcEntity {

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
	 * 焊口编号 
	 */
	private String weldCode; 

	/**
	 * 焊口类型 
	 */
	private String weldType; 

	/**
	 * 焊接方式 
	 */
	private String weldMethod; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

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
	private String frontPipeOid; 

	/**
	 * 后管件类型 
	 */
	private String backPipeType; 

	/**
	 * 后管件编号 
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
	 * 外观质量检查 
	 */
	private Integer surfaceCheck; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 

	/**
	 * 施工机组代号 
	 */
	private String workUnitOid; 

	/**
	 * 盖面人员 
	 */
	private String coverOid; 

	/**
	 * 填充人员 
	 */
	private String padderOid; 

	/**
	 * 打底人员 
	 */
	private String renderOid; 

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

    // add by cuixianing 2019-2-20
    /**
     * 是否变径
     */
    private Integer hasReducer;

    /**
     * 是否有弯管
     */
    private Integer hasBendPipe;

    /**
     * 是否存在切管
     */
    private Integer hasCutPipe;


    // add by cuixianing 2019-3-5
    /**
     * 气流方向
     */
    private Integer airflowDirection;

    /**
     * 焊口顺序号1
     */
    private Double sequenceNumberFirst;

    /**
     * 焊口顺序号2
     */
    private Double sequenceNumberSecond;

    public Integer getAirflowDirection() {
        return airflowDirection;
    }

    public void setAirflowDirection(Integer airflowDirection) {
        this.airflowDirection = airflowDirection;
        super.setField("airflowDirection");
    }

    public Double getSequenceNumberFirst() {
        return sequenceNumberFirst;
    }

    public void setSequenceNumberFirst(Double sequenceNumberFirst) {
        this.sequenceNumberFirst = sequenceNumberFirst;
        super.setField("sequenceNumberFirst");
    }

    public Double getSequenceNumberSecond() {
        return sequenceNumberSecond;
    }

    public void setSequenceNumberSecond(Double sequenceNumberSecond) {
        this.sequenceNumberSecond = sequenceNumberSecond;
        super.setField("sequenceNumberSecond");
    }

    public Integer getHasCutPipe() {
        return hasCutPipe;
    }

    public void setHasCutPipe(Integer hasCutPipe) {
        this.hasCutPipe = hasCutPipe;
        super.setField("hasCutPipe");
    }

    public Integer getHasReducer() {
        return hasReducer;
    }

    public void setHasReducer(Integer hasReducer) {
        this.hasReducer = hasReducer;
        super.setField("hasReducer");
    }

    public Integer getHasBendPipe() {
        return hasBendPipe;
    }

    public void setHasBendPipe(Integer hasBendPipe) {
        this.hasBendPipe = hasBendPipe;
        super.setField("hasBendPipe");
    }


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

	public String getWeldCode() {
		return weldCode; 
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode; 
		super.setField("weldCode");
	}

	public String getWeldType() {
		return weldType; 
	}

	public void setWeldType(String weldType) {
		this.weldType = weldType; 
		super.setField("weldType");
	}

	public String getWeldMethod() {
		return weldMethod; 
	}

	public void setWeldMethod(String weldMethod) {
		this.weldMethod = weldMethod; 
		super.setField("weldMethod");
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

	public String getFrontPipeType() {
		return frontPipeType; 
	}

	public void setFrontPipeType(String frontPipeType) {
		this.frontPipeType = frontPipeType; 
		super.setField("frontPipeType");
	}


	public String getBackPipeType() {
		return backPipeType; 
	}

	public void setBackPipeType(String backPipeType) {
		this.backPipeType = backPipeType; 
		super.setField("backPipeType");
	}
	
	public String getFrontPipeOid() {
		return frontPipeOid;
	}

	public void setFrontPipeOid(String frontPipeOid) {
		this.frontPipeOid = frontPipeOid;
		super.setField("frontPipeOid");
	}

	public String getBackPipeOid() {
		return backPipeOid;
	}

	public void setBackPipeOid(String backPipeOid) {
		this.backPipeOid = backPipeOid;
		super.setField("backPipeOid");
	}

	public String getWeldRodBatchNum() {
		return weldRodBatchNum; 
	}

	public void setWeldRodBatchNum(String weldRodBatchNum) {
		this.weldRodBatchNum = weldRodBatchNum; 
		super.setField("weldRodBatchNum");
	}

	public String getWeldWireBatchNum() {
		return weldWireBatchNum; 
	}

	public void setWeldWireBatchNum(String weldWireBatchNum) {
		this.weldWireBatchNum = weldWireBatchNum; 
		super.setField("weldWireBatchNum");
	}

	public String getWeldProduce() {
		return weldProduce; 
	}

	public void setWeldProduce(String weldProduce) {
		this.weldProduce = weldProduce; 
		super.setField("weldProduce");
	}

	public Integer getSurfaceCheck() {
		return surfaceCheck; 
	}

	public void setSurfaceCheck(Integer surfaceCheck) {
		this.surfaceCheck = surfaceCheck; 
		super.setField("surfaceCheck");
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
		super.setField("constructUnit");
	}

	public String getWorkUnitOid() {
		return workUnitOid; 
	}

	public void setWorkUnitOid(String workUnitOid) {
		this.workUnitOid = workUnitOid; 
		super.setField("workUnitOid");
	}

	public String getCoverOid() {
		return coverOid; 
	}

	public void setCoverOid(String coverOid) {
		this.coverOid = coverOid; 
		super.setField("coverOid");
	}

	public String getPadderOid() {
		return padderOid; 
	}

	public void setPadderOid(String padderOid) {
		this.padderOid = padderOid; 
		super.setField("padderOid");
	}

	public String getRenderOid() {
		return renderOid; 
	}

	public void setRenderOid(String renderOid) {
		this.renderOid = renderOid; 
		super.setField("renderOid");
	}

	public Integer getIsGoldeJoint() {
		return isGoldeJoint; 
	}

	public void setIsGoldeJoint(Integer isGoldeJoint) {
		this.isGoldeJoint = isGoldeJoint; 
		super.setField("isGoldeJoint");
	}

	public Integer getIsPipeHead() {
		return isPipeHead; 
	}

	public void setIsPipeHead(Integer isPipeHead) {
		this.isPipeHead = isPipeHead; 
		super.setField("isPipeHead");
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

}