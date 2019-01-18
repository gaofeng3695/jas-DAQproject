package cn.jasgroup.jasframework.acquisitiondata.station.spatial.sweeping.dao.entity;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
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

@CommonSaveConfig(
        scene = "/daqStationPipeSweeping/save"
)
@CommonUpdateConfig(
        scene = "/daqStationPipeSweeping/update"
)
@CommonDeleteConfig(
        scene = "/daqStationPipeSweeping/delete"
)
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
@JdbcEntity(name="daq_station_pipe_sweeping")
public class DaqStationPipeSweeping extends CommonJdbcEntity {

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
     * 试压起始桩号
     */
    private String startMedianStakeOid;

    /**
     * 相对起始桩位置(m)
     */
    private Double startRelativeMileage;

    /**
     * 试压结束桩号
     */
    private String endMedianStakeOid;

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

    public Double getPipeLength() {
        return pipeLength;
    }

    public void setPipeLength(Double pipeLength) {
        this.pipeLength = pipeLength;
        super.setField("pipeLength");
    }

    public String getPipeSpecification() {
        return pipeSpecification;
    }

    public void setPipeSpecification(String pipeSpecification) {
        this.pipeSpecification = pipeSpecification;
        super.setField("pipeSpecification");
    }

    public Double getMaximumHeightDifference() {
        return maximumHeightDifference;
    }

    public void setMaximumHeightDifference(Double maximumHeightDifference) {
        this.maximumHeightDifference = maximumHeightDifference;
        super.setField("maximumHeightDifference");
    }

    public Double getMaxHeightDistance() {
        return maxHeightDistance;
    }

    public void setMaxHeightDistance(Double maxHeightDistance) {
        this.maxHeightDistance = maxHeightDistance;
        super.setField("maxHeightDistance");
    }

    public Integer getSweepingMedium() {
        return sweepingMedium;
    }

    public void setSweepingMedium(Integer sweepingMedium) {
        this.sweepingMedium = sweepingMedium;
        super.setField("sweepingMedium");
    }

    public String getAirCompressorModel() {
        return airCompressorModel;
    }

    public void setAirCompressorModel(String airCompressorModel) {
        this.airCompressorModel = airCompressorModel;
        super.setField("airCompressorModel");
    }

    public Double getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Double runningTime) {
        this.runningTime = runningTime;
        super.setField("runningTime");
    }

    public String getProcessDescription() {
        return processDescription;
    }

    public void setProcessDescription(String processDescription) {
        this.processDescription = processDescription;
        super.setField("processDescription");
    }

    public String getPressureTestResult() {
        return pressureTestResult;
    }

    public void setPressureTestResult(String pressureTestResult) {
        this.pressureTestResult = pressureTestResult;
        super.setField("pressureTestResult");
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        super.setField("remarks");
    }

}
