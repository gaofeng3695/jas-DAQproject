package cn.jasgroup.jasframework.acquisitiondata.station.spatial.auxiliaryanodebed.query.bo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @version V1.0
 * @description 辅助阳极地床Bo
 * @Author: 陈祥思
 * @Date: 2019/1/15 13:49
 * @since JDK 1.80
 */
public class DaqStationAuxiliaryAnodeBedBo {
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
     * 站场/阀室编号
     */
    private String pipeStationOid;

    /**
     * 站场/阀室编名称
     */
    private String pipeStationName;

    /**
     * 地床编号
     */
    private String deviceCode;

    /**
     * 桩号
     */
    private String medianStakeOid;

    /**
     * 桩编号
     */
    private String medianStakeCode;

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
     * 是否临时{0:否;1:是}
     */
    private String isTemporaryName;

    /**
     * 设计寿命(年)
     */
    private Double designLife;

    /**
     * 回填材料,域值：backfill_material_domain
     */
    private String backfillMaterial;

    /**
     * 回填材料,域值：backfill_material_domain
     */
    private String backfillMaterialName;

    /**
     * 辅助阳极数量(个)
     */
    private Double auxiliaryAnodeNum;

    /**
     * 埋设,域值：burial_way_domain
     */
    private String burialWay;

    /**
     * 埋设,域值：burial_way_domain
     */
    private String burialWayName;


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
     * 阳极材料类型,域值：anode_specification_domain
     */
    private String anodeMaterialName;

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
     * 是否有注水系统{0:否;1:是}
     */
    private String hasWaterFloodingSysName;

    /**
     * 是否有排气孔{0:否;1:是}
     */
    private Integer hasVentHole;

    /**
     * 是否有排气孔{0:否;1:是}
     */
    private String hasVentHoleName;

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
     * 施工单位
     */
    private String constructUnitName;

    /**
     * 监理单位
     */
    private String supervisionUnit;

    /**
     * 监理单位
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
     * 审核状态
     */
    private String approveStatusName;

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

    public String getPipeStationOid() {
        return pipeStationOid;
    }

    public void setPipeStationOid(String pipeStationOid) {
        this.pipeStationOid = pipeStationOid;
    }

    public String getPipeStationName() {
        return pipeStationName;
    }

    public void setPipeStationName(String pipeStationName) {
        this.pipeStationName = pipeStationName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
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

    public String getInstallLocationDes() {
        return installLocationDes;
    }

    public void setInstallLocationDes(String installLocationDes) {
        this.installLocationDes = installLocationDes;
    }

    public Double getBuryingDepth() {
        return buryingDepth;
    }

    public void setBuryingDepth(Double buryingDepth) {
        this.buryingDepth = buryingDepth;
    }

    public Integer getIsTemporary() {
        return isTemporary;
    }

    public void setIsTemporary(Integer isTemporary) {
        this.isTemporary = isTemporary;
    }

    public String getIsTemporaryName() {
        return isTemporaryName;
    }

    public void setIsTemporaryName(String isTemporaryName) {
        this.isTemporaryName = isTemporaryName;
    }

    public Double getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Double designLife) {
        this.designLife = designLife;
    }

    public String getBackfillMaterial() {
        return backfillMaterial;
    }

    public void setBackfillMaterial(String backfillMaterial) {
        this.backfillMaterial = backfillMaterial;
    }

    public String getBackfillMaterialName() {
        return backfillMaterialName;
    }

    public void setBackfillMaterialName(String backfillMaterialName) {
        this.backfillMaterialName = backfillMaterialName;
    }

    public Double getAuxiliaryAnodeNum() {
        return auxiliaryAnodeNum;
    }

    public void setAuxiliaryAnodeNum(Double auxiliaryAnodeNum) {
        this.auxiliaryAnodeNum = auxiliaryAnodeNum;
    }

    public String getBurialWay() {
        return burialWay;
    }

    public void setBurialWay(String burialWay) {
        this.burialWay = burialWay;
    }

    public String getBurialWayName() {
        return burialWayName;
    }

    public void setBurialWayName(String burialWayName) {
        this.burialWayName = burialWayName;
    }

    public Double getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(Double grossWeight) {
        this.grossWeight = grossWeight;
    }

    public Double getPointx() {
        return pointx;
    }

    public void setPointx(Double pointx) {
        this.pointx = pointx;
    }

    public Double getPointy() {
        return pointy;
    }

    public void setPointy(Double pointy) {
        this.pointy = pointy;
    }

    public Double getPointz() {
        return pointz;
    }

    public void setPointz(Double pointz) {
        this.pointz = pointz;
    }

    public Double getCableLength() {
        return cableLength;
    }

    public void setCableLength(Double cableLength) {
        this.cableLength = cableLength;
    }

    public Double getProtectLength() {
        return protectLength;
    }

    public void setProtectLength(Double protectLength) {
        this.protectLength = protectLength;
    }

    public String getAnodeMaterial() {
        return anodeMaterial;
    }

    public void setAnodeMaterial(String anodeMaterial) {
        this.anodeMaterial = anodeMaterial;
    }

    public String getAnodeMaterialName() {
        return anodeMaterialName;
    }

    public void setAnodeMaterialName(String anodeMaterialName) {
        this.anodeMaterialName = anodeMaterialName;
    }

    public String getAnodeSpecification() {
        return anodeSpecification;
    }

    public void setAnodeSpecification(String anodeSpecification) {
        this.anodeSpecification = anodeSpecification;
    }

    public Double getAnodeResistance() {
        return anodeResistance;
    }

    public void setAnodeResistance(Double anodeResistance) {
        this.anodeResistance = anodeResistance;
    }

    public Integer getHasWaterFloodingSys() {
        return hasWaterFloodingSys;
    }

    public void setHasWaterFloodingSys(Integer hasWaterFloodingSys) {
        this.hasWaterFloodingSys = hasWaterFloodingSys;
    }

    public String getHasWaterFloodingSysName() {
        return hasWaterFloodingSysName;
    }

    public void setHasWaterFloodingSysName(String hasWaterFloodingSysName) {
        this.hasWaterFloodingSysName = hasWaterFloodingSysName;
    }

    public Integer getHasVentHole() {
        return hasVentHole;
    }

    public void setHasVentHole(Integer hasVentHole) {
        this.hasVentHole = hasVentHole;
    }

    public String getHasVentHoleName() {
        return hasVentHoleName;
    }

    public void setHasVentHoleName(String hasVentHoleName) {
        this.hasVentHoleName = hasVentHoleName;
    }

    public String getAnodeBedConnectionPower() {
        return anodeBedConnectionPower;
    }

    public void setAnodeBedConnectionPower(String anodeBedConnectionPower) {
        this.anodeBedConnectionPower = anodeBedConnectionPower;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getEmbedmentDate() {
        return embedmentDate;
    }

    public void setEmbedmentDate(Date embedmentDate) {
        this.embedmentDate = embedmentDate;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getConstructDate() {
        return constructDate;
    }

    public void setConstructDate(Date constructDate) {
        this.constructDate = constructDate;
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
    @Temporal(TemporalType.DATE)
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

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
