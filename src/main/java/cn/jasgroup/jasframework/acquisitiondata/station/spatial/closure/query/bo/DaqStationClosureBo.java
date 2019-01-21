package cn.jasgroup.jasframework.acquisitiondata.station.spatial.closure.query.bo;

import java.util.Date;
import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>封堵物</p>
 * @author cuixianing。
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-15 15:18:32。</p>
 */

public class DaqStationClosureBo extends CommonBaseBo {

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
     * 标段oid
     */
    private String tendersOid;

    /**
     * 标段名称
     */
    private String tendersName;

    /**
     * 管线oid
     */
    private String pipelineOid;

    /**
     * 管线名称
     */
    private String pipelineName;

    /**
     * 站场/阀室编号
     */
    private String pipeStationOid;

    /**
     * 站场/阀室编名称
     */
    private String pipeStationName;

    /**
     * 编号
     */
    private String deviceCode;

    /**
     * 名称
     */
    private String deviceName;

    /**
     * 封堵物尺寸
     */
    private String closureSize;

    /**
     * 封堵物类型，域值：closure_mold_domain
     */
    private String closureMold;

    /**
     * 封堵物类型，域值：closure_mold_domain
     */
    private String closureMoldName;

    /**
     * 封堵物材料{1未知;2:钢材;3:塑料;4:其他}
     */
    private Integer closureMaterial;

    /**
     * 封堵物材料{1未知;2:钢材;3:塑料;4:其他}
     */
    private String closureMaterialName;

    /**
     * 连接方式,域值：closure_connection_methods_domain
     */
    private String closureConnectionMethods;

    /**
     * 连接方式,域值：closure_connection_methods_domain
     */
    private String closureConnectionMethodsName;

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
     * 具体地址
     */
    private String address;

    /**
     * 制管形式，域值：tube_making_methods_domain
     */
    private String tubeMakingMethods;

    /**
     * 制管形式，域值：tube_making_methods_domain
     */
    private String tubeMakingMethodsName;

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

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getConstructDate() {
        return constructDate;
    }

    public void setConstructDate(Date constructDate) {
        this.constructDate = constructDate;
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

    public String getClosureSize() {
        return closureSize;
    }

    public void setClosureSize(String closureSize) {
        this.closureSize = closureSize;
    }

    public String getClosureMold() {
        return closureMold;
    }

    public void setClosureMold(String closureMold) {
        this.closureMold = closureMold;
    }

    public String getClosureMoldName() {
        return closureMoldName;
    }

    public void setClosureMoldName(String closureMoldName) {
        this.closureMoldName = closureMoldName;
    }

    public Integer getClosureMaterial() {
        return closureMaterial;
    }

    public void setClosureMaterial(Integer closureMaterial) {
        this.closureMaterial = closureMaterial;
    }

    public String getClosureMaterialName() {
        return closureMaterialName;
    }

    public void setClosureMaterialName(String closureMaterialName) {
        this.closureMaterialName = closureMaterialName;
    }

    public String getClosureConnectionMethods() {
        return closureConnectionMethods;
    }

    public void setClosureConnectionMethods(String closureConnectionMethods) {
        this.closureConnectionMethods = closureConnectionMethods;
    }

    public String getClosureConnectionMethodsName() {
        return closureConnectionMethodsName;
    }

    public void setClosureConnectionMethodsName(String closureConnectionMethodsName) {
        this.closureConnectionMethodsName = closureConnectionMethodsName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTubeMakingMethods() {
        return tubeMakingMethods;
    }

    public void setTubeMakingMethods(String tubeMakingMethods) {
        this.tubeMakingMethods = tubeMakingMethods;
    }

    public String getTubeMakingMethodsName() {
        return tubeMakingMethodsName;
    }

    public void setTubeMakingMethodsName(String tubeMakingMethodsName) {
        this.tubeMakingMethodsName = tubeMakingMethodsName;
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