package cn.jasgroup.jasframework.acquisitiondata.station.spatial.icdetectionsys.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @version V1.0
 * @description 内腐蚀检测系统Bo
 * @Author: 陈祥思
 * @Date: 2019/1/15 13:45
 * @since JDK 1.80
 */

public class DaqStationIcDetectionSysBo extends CommonBaseBo {
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
     * 起始桩号
     */
    private String startMedianStakeCode;

    /**
     * 相对起始桩里程(m)
     */
    private Double startRelativeMileage;

    /**
     * 结束桩号
     */
    private String endMedianStakeOid;

    /**
     * 结束桩号
     */
    private String endMedianStakeCode;

    /**
     * 相对结束桩里程(m)
     */
    private Double endRelativeMileage;

    /**
     * 系统类型,域值：system_type_domain
     */
    private String systemType;

    /**
     * 系统类型,域值：system_type_domain
     */
    private String systemTypeName;

    /**
     * 系统原理，域值：system_principle_domain
     */
    private String systemPrinciple;

    /**
     * 系统原理，域值：system_principle_domain
     */
    private String systemPrincipleName;

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

    public String getStartMedianStakeOid() {
        return startMedianStakeOid;
    }

    public void setStartMedianStakeOid(String startMedianStakeOid) {
        this.startMedianStakeOid = startMedianStakeOid;
    }

    public String getStartMedianStakeCode() {
        return startMedianStakeCode;
    }

    public void setStartMedianStakeCode(String startMedianStakeCode) {
        this.startMedianStakeCode = startMedianStakeCode;
    }

    public Double getStartRelativeMileage() {
        return startRelativeMileage;
    }

    public void setStartRelativeMileage(Double startRelativeMileage) {
        this.startRelativeMileage = startRelativeMileage;
    }

    public String getEndMedianStakeOid() {
        return endMedianStakeOid;
    }

    public void setEndMedianStakeOid(String endMedianStakeOid) {
        this.endMedianStakeOid = endMedianStakeOid;
    }

    public String getEndMedianStakeCode() {
        return endMedianStakeCode;
    }

    public void setEndMedianStakeCode(String endMedianStakeCode) {
        this.endMedianStakeCode = endMedianStakeCode;
    }

    public Double getEndRelativeMileage() {
        return endRelativeMileage;
    }

    public void setEndRelativeMileage(Double endRelativeMileage) {
        this.endRelativeMileage = endRelativeMileage;
    }

    public String getSystemType() {
        return systemType;
    }

    public void setSystemType(String systemType) {
        this.systemType = systemType;
    }

    public String getSystemTypeName() {
        return systemTypeName;
    }

    public void setSystemTypeName(String systemTypeName) {
        this.systemTypeName = systemTypeName;
    }

    public String getSystemPrinciple() {
        return systemPrinciple;
    }

    public void setSystemPrinciple(String systemPrinciple) {
        this.systemPrinciple = systemPrinciple;
    }

    public String getSystemPrincipleName() {
        return systemPrincipleName;
    }

    public void setSystemPrincipleName(String systemPrincipleName) {
        this.systemPrincipleName = systemPrincipleName;
    }

    public String getDevelopmentUnit() {
        return developmentUnit;
    }

    public void setDevelopmentUnit(String developmentUnit) {
        this.developmentUnit = developmentUnit;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getPutIntoDate() {
        return putIntoDate;
    }

    public void setPutIntoDate(Date putIntoDate) {
        this.putIntoDate = putIntoDate;
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
