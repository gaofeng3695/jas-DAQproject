package cn.jasgroup.jasframework.acquisitiondata.station.spatial.condensingtube.dao.entity;

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
 * <p>排凝管</p>
 * @author cuixianing
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-15 14:05:15。</p>
 */
@CommonSaveConfig(
        scene = "/daqStationCondensingTube/save"
)
@CommonUpdateConfig(
        scene = "/daqStationCondensingTube/update"
)
@CommonDeleteConfig(
        scene = "/daqStationCondensingTube/delete"
)
@Point(
        x="pointx",
        y="pointy" ,
        z="pointz" ,
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
                @UniqueConstraintStrategy(columnNames={"pipeStationOid","deviceCode"},name="同一线站场/阀室下编号不能重复")
        }
)
@JdbcEntity(name="daq_station_condensing_tube")
public class DaqStationCondensingTube extends CommonJdbcEntity {

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
     * 编号
     */
    private String deviceCode;

    /**
     * 名称
     */
    private String deviceName;

    /**
     * 排放介质
     */
    private String emissionMedium;

    /**
     * 排凝管管径（mm）
     */
    private Double condensingTubeDiameter;

    /**
     * 排凝管材质{1未知;2:钢材;3:塑料;4:其他}
     */
    private Integer condensingTubeMaterial;

    /**
     * 压力等级（mpa)
     */
    private Double pressureGrade;

    /**
     * 桩号
     */
    private String medianStakeOid;

    /**
     * 相对里程（m）
     */
    private Double relativeMileage;

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
     * 执行机构
     */
    private String executingAgency;

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
     * 备注
     */
    private String remarks;

	/**
	 * 空间数据状态 
	 */
	private String geoState;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getConstructDate() {
        return constructDate;
    }

    public void setConstructDate(Date constructDate) {
        this.constructDate = constructDate;
        super.setField("constructDate");
    }

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

    public String getEmissionMedium() {
        return emissionMedium;
    }

    public void setEmissionMedium(String emissionMedium) {
        this.emissionMedium = emissionMedium;
        super.setField("emissionMedium");
    }

    public Double getCondensingTubeDiameter() {
        return condensingTubeDiameter;
    }

    public void setCondensingTubeDiameter(Double condensingTubeDiameter) {
        this.condensingTubeDiameter = condensingTubeDiameter;
        super.setField("condensingTubeDiameter");
    }

    public Integer getCondensingTubeMaterial() {
        return condensingTubeMaterial;
    }

    public void setCondensingTubeMaterial(Integer condensingTubeMaterial) {
        this.condensingTubeMaterial = condensingTubeMaterial;
        super.setField("condensingTubeMaterial");
    }

    public Double getPressureGrade() {
        return pressureGrade;
    }

    public void setPressureGrade(Double pressureGrade) {
        this.pressureGrade = pressureGrade;
        super.setField("pressureGrade");
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

    public String getExecutingAgency() {
        return executingAgency;
    }

    public void setExecutingAgency(String executingAgency) {
        this.executingAgency = executingAgency;
        super.setField("executingAgency");
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