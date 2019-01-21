package cn.jasgroup.jasframework.acquisitiondata.station.spatial.icdetectionsys.dao.entity;
import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Line;
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
 * @description 内腐蚀检测系统实体
 * @Author: 陈祥思
 * @Date: 2019/1/15 13:45
 * @since JDK 1.80
 */
@CommonSaveConfig(scene = "/stationIcDetectionSys/save")
@CommonUpdateConfig(scene = "/stationIcDetectionSys/update")
@CommonDeleteConfig(scene = "/stationIcDetectionSys/delete")
@CommonDeleteBatchConfig(scene = "/stationIcDetectionSys/deleteBatch")
@CommonGetConfig(scene = "/stationIcDetectionSys/get")
//前后桩交偏移
@Line(
        scopeFieldName="pipelineOid",
        scopeType=ScopeType.CURRENT,
        geometryColumnName="geom",
        calculateType=CalculateType.DoubleAnchorAndDeviation,
        anchorClass=MedianStake.class,
        startAnchorOid="startMedianStakeOid",
        startDeviation="startRelativeMileage",
        endAnchorOid="endMedianStakeOid",
        endDeviation="endRelativeMileage"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"pipeStationOid","deviceCode"},
            name="同一线站场/阀室下设备编号不能重复"
        )
    }
)

@JdbcEntity(name="daq_station_ic_detection_sys")
public class DaqStationIcDetectionSys extends CommonJdbcEntity {

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
     * 起始桩号
     */
    private String startMedianStakeOid;

    /**
     * 相对起始桩里程(m)
     */
    private Double startRelativeMileage;

    /**
     * 结束桩号
     */
    private String endMedianStakeOid;

    /**
     * 相对结束桩里程(m)
     */
    private Double endRelativeMileage;

    /**
     * 系统类型,域值：system_type_domain
     */
    private String systemType;

    /**
     * 系统原理，域值：system_principle_domain
     */
    private String systemPrinciple;

    /**
     * 研发单位
     */
    private String developmentUnit;

    /**
     * 投用日期
     */
    private Date putIntoDate;

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

    public String getStartMedianStakeOid() {
        return startMedianStakeOid;
    }

    public void setStartMedianStakeOid(String startMedianStakeOid) {
        this.startMedianStakeOid = startMedianStakeOid;
        super.setField("startMedianStakeOid");
    }

    public Double getStartRelativeMileage() {
        return startRelativeMileage;
    }

    public void setStartRelativeMileage(Double startRelativeMileage) {
        this.startRelativeMileage = startRelativeMileage;
        super.setField("startRelativeMileage");
    }

    public String getEndMedianStakeOid() {
        return endMedianStakeOid;
    }

    public void setEndMedianStakeOid(String endMedianStakeOid) {
        this.endMedianStakeOid = endMedianStakeOid;
        super.setField("endMedianStakeOid");
    }

    public Double getEndRelativeMileage() {
        return endRelativeMileage;
    }

    public void setEndRelativeMileage(Double endRelativeMileage) {
        this.endRelativeMileage = endRelativeMileage;
        super.setField("endRelativeMileage");
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
        super.setField("systemType");
    }

    public String getSystemPrinciple() {
        return systemPrinciple;
    }

    public void setSystemPrinciple(String systemPrinciple) {
        this.systemPrinciple = systemPrinciple;
        super.setField("systemPrinciple");
    }

    public String getDevelopmentUnit() {
        return developmentUnit;
    }

    public void setDevelopmentUnit(String developmentUnit) {
        this.developmentUnit = developmentUnit;
        super.setField("developmentUnit");
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getPutIntoDate() {
        return putIntoDate;
    }

    public void setPutIntoDate(Date putIntoDate) {
        this.putIntoDate = putIntoDate;
        super.setField("putIntoDate");
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
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
