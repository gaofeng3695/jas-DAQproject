package cn.jasgroup.jasframework.acquisitiondata.station.spatial.sweeping.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p>管道扫水</p>
 * @author cuixianing
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-15 10:43:37。</p>
 */

public class DaqStationPipeSweepingBo extends CommonBaseBo {

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
     * 试压起始桩号
     */
    private String startMedianStakeOid;

    /**
     * 试压起始桩号
     */
    private String startMedianStakeCode;

    /**
     * 相对起始桩位置(m)
     */
    private Double startRelativeMileage;

    /**
     * 试压结束桩号
     */
    private String endMedianStakeOid;

    /**
     * 试压结束桩号
     */
    private String endMedianStakeCode;

    /**
     * 相对结束桩位置(m)
     */
    private Double endRelativeMileage;

    /**
     * 管道长度
     */
    private Double pipeLength;

    /**
     * 管道规格
     */
    private String pipeSpecification;

    /**
     * 管段最大高差(m)
     */
    private Double maximumHeightDifference;

    /**
     * 管段最高点距清管器发送端距离(km)
     */
    private Double maxHeightDistance;

    /**
     * 吹扫介质{1:水;2:空气;3:氮气}
     */
    private Integer sweepingMedium;

    /**
     * 吹扫介质{1:水;2:空气;3:氮气}名称
     */
    private String sweepingMediumName;

    /**
     * 空气压缩机型号及规格
     */
    private String airCompressorModel;

    /**
     * 运行时间(h)
     */
    private Double runningTime;

    /**
     * 扫水情况描述
     */
    private String processDescription;

    /**
     * 结论
     */
    private String pressureTestResult;

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
     * 审核状态名称
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

    public String getStartMedianStakeOid() {
        return startMedianStakeOid;
    }

    public void setStartMedianStakeOid(String startMedianStakeOid) {
        this.startMedianStakeOid = startMedianStakeOid;
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

    public Double getEndRelativeMileage() {
        return endRelativeMileage;
    }

    public void setEndRelativeMileage(Double endRelativeMileage) {
        this.endRelativeMileage = endRelativeMileage;
    }

    public Double getPipeLength() {
        return pipeLength;
    }

    public void setPipeLength(Double pipeLength) {
        this.pipeLength = pipeLength;
    }

    public String getPipeSpecification() {
        return pipeSpecification;
    }

    public void setPipeSpecification(String pipeSpecification) {
        this.pipeSpecification = pipeSpecification;
    }

    public Double getMaximumHeightDifference() {
        return maximumHeightDifference;
    }

    public void setMaximumHeightDifference(Double maximumHeightDifference) {
        this.maximumHeightDifference = maximumHeightDifference;
    }

    public Double getMaxHeightDistance() {
        return maxHeightDistance;
    }

    public void setMaxHeightDistance(Double maxHeightDistance) {
        this.maxHeightDistance = maxHeightDistance;
    }

    public Integer getSweepingMedium() {
        return sweepingMedium;
    }

    public void setSweepingMedium(Integer sweepingMedium) {
        this.sweepingMedium = sweepingMedium;
    }

    public String getSweepingMediumName() {
        return sweepingMediumName;
    }

    public void setSweepingMediumName(String sweepingMediumName) {
        this.sweepingMediumName = sweepingMediumName;
    }

    public String getAirCompressorModel() {
        return airCompressorModel;
    }

    public void setAirCompressorModel(String airCompressorModel) {
        this.airCompressorModel = airCompressorModel;
    }

    public Double getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Double runningTime) {
        this.runningTime = runningTime;
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
    }

    public String getPressureTestResult() {
        return pressureTestResult;
    }

    public void setPressureTestResult(String pressureTestResult) {
        this.pressureTestResult = pressureTestResult;
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

    public String getStartMedianStakeCode() {
        return startMedianStakeCode;
    }

    public void setStartMedianStakeCode(String startMedianStakeCode) {
        this.startMedianStakeCode = startMedianStakeCode;
    }

    public String getEndMedianStakeCode() {
        return endMedianStakeCode;
    }

    public void setEndMedianStakeCode(String endMedianStakeCode) {
        this.endMedianStakeCode = endMedianStakeCode;
    }
}
