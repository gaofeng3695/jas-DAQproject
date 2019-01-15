package cn.jasgroup.jasframework.acquisitiondata.station.spatial.cppowersupply.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @version V1.0
 * @description 阴保电源Bo
 * @Author: 陈祥思
 * @Date: 2019/1/15 9:32
 * @since JDK 1.80
 */
public class DaqStationCpPowerSupplyBo extends CommonBaseBo {

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
     * 标段oid
     */
    private String tendersOid;

    /**
     * 标段编号
     */
    private String tendersName;

    /**
     * 管线oid
     */
    private String pipelineOid;

    /**
     * 管线编号
     */
    private String pipelineName;

    /**
     * 站场/阀室Oid
     */
    private String pipeStationOid;

    /**
     * 站场/阀室编号
     */
    private String pipeStationName;

    /**
     * 设备编号
     */
    private String deviceCode;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 出厂编号
     */
    private String manufactureNumber;

    /**
     * 保护对象,域值：protect_object_domain
     */
    private String protectObject;

    /**
     * 保护对象,域值：protect_object_domain
     */
    private String protectObjectName;

    /**
     * 东坐标
     */
    private Double pointx;

    /**
     * 北坐标
     */
    private Double pointy;

    /**
     * 高程
     */
    private Double pointz;

    /**
     * 额定电流(a)
     */
    private Double ratedCurrent;

    /**
     * 额定电压(v)
     */
    private Double ratedVoltage;

    /**
     * 输出电流(a)
     */
    private Double outputCurrent;

    /**
     * 输出电压(v)
     */
    private Double outputVoltage;

    /**
     * 地床数量
     */
    private Double bedNum;

    /**
     * 阳极数量
     */
    private Double anodeNum;

    /**
     * 电源类型，域值：power_supply_type_domain
     */
    private String powerSupplyType;

    /**
     * 电源类型，域值：power_supply_type_domain
     */
    private String powerSupplyTypeName;

    /**
     * 制造商
     */
    private String manufacturerName;

    /**
     * 投用日期
     */
    private Date putIntoDate;

    /**
     * 设计寿命(年)
     */
    private Double designLife;

    /**
     * 通电点描述
     */
    private String electrifiedPoint;

    /**
     * 给定(预置)电位(v)
     */
    private Double givenVoltage;

    /**
     * 参比电位(v)
     */
    private Double referenceVoltage;

    /**
     * 施工日期
     */
    private Date constructDate;

    /**
     * 施工单位
     */
    private String constructUnit;

    /**
     * 施工单位名称
     */
    private String constructUnitName;

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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getManufactureNumber() {
        return manufactureNumber;
    }

    public void setManufactureNumber(String manufactureNumber) {
        this.manufactureNumber = manufactureNumber;
    }

    public String getProtectObject() {
        return protectObject;
    }

    public void setProtectObject(String protectObject) {
        this.protectObject = protectObject;
    }

    public String getProtectObjectName() {
        return protectObjectName;
    }

    public void setProtectObjectName(String protectObjectName) {
        this.protectObjectName = protectObjectName;
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

    public Double getRatedCurrent() {
        return ratedCurrent;
    }

    public void setRatedCurrent(Double ratedCurrent) {
        this.ratedCurrent = ratedCurrent;
    }

    public Double getRatedVoltage() {
        return ratedVoltage;
    }

    public void setRatedVoltage(Double ratedVoltage) {
        this.ratedVoltage = ratedVoltage;
    }

    public Double getOutputCurrent() {
        return outputCurrent;
    }

    public void setOutputCurrent(Double outputCurrent) {
        this.outputCurrent = outputCurrent;
    }

    public Double getOutputVoltage() {
        return outputVoltage;
    }

    public void setOutputVoltage(Double outputVoltage) {
        this.outputVoltage = outputVoltage;
    }

    public Double getBedNum() {
        return bedNum;
    }

    public void setBedNum(Double bedNum) {
        this.bedNum = bedNum;
    }

    public Double getAnodeNum() {
        return anodeNum;
    }

    public void setAnodeNum(Double anodeNum) {
        this.anodeNum = anodeNum;
    }

    public String getPowerSupplyType() {
        return powerSupplyType;
    }

    public void setPowerSupplyType(String powerSupplyType) {
        this.powerSupplyType = powerSupplyType;
    }

    public String getPowerSupplyTypeName() {
        return powerSupplyTypeName;
    }

    public void setPowerSupplyTypeName(String powerSupplyTypeName) {
        this.powerSupplyTypeName = powerSupplyTypeName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPutIntoDate() {
        return putIntoDate;
    }

    public void setPutIntoDate(Date putIntoDate) {
        this.putIntoDate = putIntoDate;
    }

    public Double getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Double designLife) {
        this.designLife = designLife;
    }

    public String getElectrifiedPoint() {
        return electrifiedPoint;
    }

    public void setElectrifiedPoint(String electrifiedPoint) {
        this.electrifiedPoint = electrifiedPoint;
    }

    public Double getGivenVoltage() {
        return givenVoltage;
    }

    public void setGivenVoltage(Double givenVoltage) {
        this.givenVoltage = givenVoltage;
    }

    public Double getReferenceVoltage() {
        return referenceVoltage;
    }

    public void setReferenceVoltage(Double referenceVoltage) {
        this.referenceVoltage = referenceVoltage;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
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
