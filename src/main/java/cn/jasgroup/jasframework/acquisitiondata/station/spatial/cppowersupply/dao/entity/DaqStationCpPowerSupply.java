package cn.jasgroup.jasframework.acquisitiondata.station.spatial.cppowersupply.dao.entity;
import java.util.Date;


import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @version V1.0
 * @description 阴保电源实体
 * @Author: 陈祥思
 * @Date: 2019/1/15 9:20
 * @since JDK 1.80
 */
@CommonSaveConfig(scene = "/stationCpPowerSupply/save")
@CommonUpdateConfig(scene = "/stationCpPowerSupply/update")
@CommonDeleteConfig(scene = "/stationCpPowerSupply/delete")
@CommonDeleteBatchConfig(scene = "/stationCpPowerSupply/deleteBatch")
@CommonGetConfig(scene = "/stationCpPowerSupply/get")
//单点坐标
@Point(
        x = "pointx",
        y = "pointy",
        z = "pointz",
        geometryColumnName = "geom",
        calculateType = CalculateType.Coordinates
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"pipeStationOid","deviceCode"},
            name="同一线站场/阀室下设备编号不能重复"
        )
    }
)

@JdbcEntity(name="daq_station_cp_power_supply")
public class DaqStationCpPowerSupply extends CommonJdbcEntity {

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

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
        super.setField("deviceName");
    }

    public String getManufactureNumber() {
        return manufactureNumber;
    }

    public void setManufactureNumber(String manufactureNumber) {
        this.manufactureNumber = manufactureNumber;
        super.setField("manufactureNumber");
    }

    public String getProtectObject() {
        return protectObject;
    }

    public void setProtectObject(String protectObject) {
        this.protectObject = protectObject;
        super.setField("protectObject");
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

    public Double getRatedCurrent() {
        return ratedCurrent;
    }

    public void setRatedCurrent(Double ratedCurrent) {
        this.ratedCurrent = ratedCurrent;
        super.setField("ratedCurrent");
    }

    public Double getRatedVoltage() {
        return ratedVoltage;
    }

    public void setRatedVoltage(Double ratedVoltage) {
        this.ratedVoltage = ratedVoltage;
        super.setField("ratedVoltage");
    }

    public Double getOutputCurrent() {
        return outputCurrent;
    }

    public void setOutputCurrent(Double outputCurrent) {
        this.outputCurrent = outputCurrent;
        super.setField("outputCurrent");
    }

    public Double getOutputVoltage() {
        return outputVoltage;
    }

    public void setOutputVoltage(Double outputVoltage) {
        this.outputVoltage = outputVoltage;
        super.setField("outputVoltage");
    }

    public Double getBedNum() {
        return bedNum;
    }

    public void setBedNum(Double bedNum) {
        this.bedNum = bedNum;
        super.setField("bedNum");
    }

    public Double getAnodeNum() {
        return anodeNum;
    }

    public void setAnodeNum(Double anodeNum) {
        this.anodeNum = anodeNum;
        super.setField("anodeNum");
    }

    public String getPowerSupplyType() {
        return powerSupplyType;
    }

    public void setPowerSupplyType(String powerSupplyType) {
        this.powerSupplyType = powerSupplyType;
        super.setField("powerSupplyType");
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
        super.setField("manufacturerName");
    }
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPutIntoDate() {
        return putIntoDate;
    }

    public void setPutIntoDate(Date putIntoDate) {
        this.putIntoDate = putIntoDate;
        super.setField("putIntoDate");
    }

    public Double getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Double designLife) {
        this.designLife = designLife;
        super.setField("designLife");
    }

    public String getElectrifiedPoint() {
        return electrifiedPoint;
    }

    public void setElectrifiedPoint(String electrifiedPoint) {
        this.electrifiedPoint = electrifiedPoint;
        super.setField("electrifiedPoint");
    }

    public Double getGivenVoltage() {
        return givenVoltage;
    }

    public void setGivenVoltage(Double givenVoltage) {
        this.givenVoltage = givenVoltage;
        super.setField("givenVoltage");
    }

    public Double getReferenceVoltage() {
        return referenceVoltage;
    }

    public void setReferenceVoltage(Double referenceVoltage) {
        this.referenceVoltage = referenceVoltage;
        super.setField("referenceVoltage");
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        super.setField("remarks");
    }

}