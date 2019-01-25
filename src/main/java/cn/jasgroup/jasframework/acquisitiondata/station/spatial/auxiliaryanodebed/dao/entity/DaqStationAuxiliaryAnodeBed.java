package cn.jasgroup.jasframework.acquisitiondata.station.spatial.auxiliaryanodebed.dao.entity;
import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @version V1.0
 * @description 辅助阳极地床实体
 * @Author: 陈祥思
 * @Date: 2019/1/15 13:49
 * @since JDK 1.80
 */

@CommonSaveConfig(scene = "/stationAuxiliaryAnodeBed/save")
@CommonUpdateConfig(scene = "/stationAuxiliaryAnodeBed/update")
@CommonDeleteConfig(scene = "/stationAuxiliaryAnodeBed/delete")
@CommonDeleteBatchConfig(scene = "/stationAuxiliaryAnodeBed/deleteBatch")
@CommonGetConfig(scene = "/stationAuxiliaryAnodeBed/get")
//单点坐标
@Point(
        x = "pointx",
        y = "pointy",
        z = "pointz",
        scopeFieldName="pipelineOid",
        scopeType=ScopeType.CURRENT,
        geometryColumnName = "geom",
        calculateType=CalculateType.SingleAnchorAndDeviation,
        anchorClass=MedianStake.class,
    	anchorOid="medianStakeOid",
    	deviation="relativeMileage"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"pipeStationOid","deviceCode"},
            name="同一线站场/阀室下地床编号不能重复"
        )
    }
)
@JdbcEntity(name="daq_station_auxiliary_anode_bed")
public class DaqStationAuxiliaryAnodeBed extends CommonJdbcEntity {

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
     * 站场/阀室编号
     */
    private String pipeStationOid;

    /**
     * 地床编号
     */
    private String deviceCode;

    /**
     * 桩号
     */
    private String medianStakeOid;

    /**
     * 相对里程（m）
     */
    private Double relativeMileage;

    /**
     * 安装位置描述
     */
    private String installLocationDes;

    /**
     * 埋深(m)
     */
    private Double buryingDepth;

    /**
     * 是否临时{0:否;1:是}
     */
    private Integer isTemporary;

    /**
     * 设计寿命(年)
     */
    private Double designLife;

    /**
     * 回填材料,域值：backfill_material_domain
     */
    private String backfillMaterial;

    /**
     * 辅助阳极数量(个)
     */
    private Double auxiliaryAnodeNum;

    /**
     * 埋设,域值：burial_way_domain
     */
    private String burialWay;

    /**
     * 总重(kg)
     */
    private Double grossWeight;

    /**
     * 东坐标(m)
     */
    private Double pointx;

    /**
     * 北坐标(m)
     */
    private Double pointy;

    /**
     * 高程(m)
     */
    private Double pointz;

    /**
     * 电缆长度(m)
     */
    private Double cableLength;

    /**
     * 保护长度(m)
     */
    private Double protectLength;

    /**
     * 阳极材料类型,域值：anode_specification_domain
     */
    private String anodeMaterial;

    /**
     * 阳极材料规格
     */
    private String anodeSpecification;

    /**
     * 阳极电阻(ω)
     */
    private Double anodeResistance;

    /**
     * 是否有注水系统{0:否;1:是}
     */
    private Integer hasWaterFloodingSys;

    /**
     * 是否有排气孔{0:否;1:是}
     */
    private Integer hasVentHole;

    /**
     * 地床连接阴保电源
     */
    private String anodeBedConnectionPower;

    /**
     * 埋设日期
     */
    private Date embedmentDate;

    /**
     * 施工日期
     */
    private Date constructDate;

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
     * 审核状态
     */
    private Integer approveStatus;

    /**
     * 备注
     */
    private String remarks;

	/**
	 * 空间数据状态 
	 */
	private String geoState; 
    
	public String getGeoState() {
		return geoState; 
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState; 
		super.setField("geoState");
	}


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

    public String getPipeStationOid() {
        return pipeStationOid;
    }

    public void setPipeStationOid(String pipeStationOid) {
        this.pipeStationOid = pipeStationOid;
        super.setField("pipeStationOid");
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
        super.setField("deviceCode");
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

    public String getInstallLocationDes() {
        return installLocationDes;
    }

    public void setInstallLocationDes(String installLocationDes) {
        this.installLocationDes = installLocationDes;
        super.setField("installLocationDes");
    }

    public Double getBuryingDepth() {
        return buryingDepth;
    }

    public void setBuryingDepth(Double buryingDepth) {
        this.buryingDepth = buryingDepth;
        super.setField("buryingDepth");
    }

    public Integer getIsTemporary() {
        return isTemporary;
    }

    public void setIsTemporary(Integer isTemporary) {
        this.isTemporary = isTemporary;
        super.setField("isTemporary");
    }

    public Double getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Double designLife) {
        this.designLife = designLife;
        super.setField("designLife");
    }

    public String getBackfillMaterial() {
        return backfillMaterial;
    }

    public void setBackfillMaterial(String backfillMaterial) {
        this.backfillMaterial = backfillMaterial;
        super.setField("backfillMaterial");
    }

    public Double getAuxiliaryAnodeNum() {
        return auxiliaryAnodeNum;
    }

    public void setAuxiliaryAnodeNum(Double auxiliaryAnodeNum) {
        this.auxiliaryAnodeNum = auxiliaryAnodeNum;
        super.setField("auxiliaryAnodeNum");
    }

    public String getBurialWay() {
        return burialWay;
    }

    public void setBurialWay(String burialWay) {
        this.burialWay = burialWay;
        super.setField("burialWay");
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
        super.setField("grossWeight");
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

    public Double getCableLength() {
        return cableLength;
    }

    public void setCableLength(Double cableLength) {
        this.cableLength = cableLength;
        super.setField("cableLength");
    }

    public Double getProtectLength() {
        return protectLength;
    }

    public void setProtectLength(Double protectLength) {
        this.protectLength = protectLength;
        super.setField("protectLength");
    }

    public String getAnodeMaterial() {
        return anodeMaterial;
    }

    public void setAnodeMaterial(String anodeMaterial) {
        this.anodeMaterial = anodeMaterial;
        super.setField("anodeMaterial");
    }

    public String getAnodeSpecification() {
        return anodeSpecification;
    }

    public void setAnodeSpecification(String anodeSpecification) {
        this.anodeSpecification = anodeSpecification;
        super.setField("anodeSpecification");
    }

    public Double getAnodeResistance() {
        return anodeResistance;
    }

    public void setAnodeResistance(Double anodeResistance) {
        this.anodeResistance = anodeResistance;
        super.setField("anodeResistance");
    }

    public Integer getHasWaterFloodingSys() {
        return hasWaterFloodingSys;
    }

    public void setHasWaterFloodingSys(Integer hasWaterFloodingSys) {
        this.hasWaterFloodingSys = hasWaterFloodingSys;
        super.setField("hasWaterFloodingSys");
    }

    public Integer getHasVentHole() {
        return hasVentHole;
    }

    public void setHasVentHole(Integer hasVentHole) {
        this.hasVentHole = hasVentHole;
        super.setField("hasVentHole");
    }

    public String getAnodeBedConnectionPower() {
        return anodeBedConnectionPower;
    }

    public void setAnodeBedConnectionPower(String anodeBedConnectionPower) {
        this.anodeBedConnectionPower = anodeBedConnectionPower;
        super.setField("anodeBedConnectionPower");
    }

    public Date getEmbedmentDate() {
        return embedmentDate;
    }

    public void setEmbedmentDate(Date embedmentDate) {
        this.embedmentDate = embedmentDate;
        super.setField("embedmentDate");
    }

    public Date getConstructDate() {
        return constructDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public void setConstructDate(Date constructDate) {
        this.constructDate = constructDate;
        super.setField("constructDate");
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

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        super.setField("remarks");
    }


}
