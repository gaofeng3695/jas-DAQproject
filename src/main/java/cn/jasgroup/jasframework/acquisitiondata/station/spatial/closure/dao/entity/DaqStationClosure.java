package cn.jasgroup.jasframework.acquisitiondata.station.spatial.closure.dao.entity;

import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
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
@CommonSaveConfig(
        scene = "/daqStationClosure/save"
)
@CommonUpdateConfig(
        scene = "/daqStationClosure/update"
)
@CommonDeleteConfig(
        scene = "/daqStationClosure/delete"
)
@Point(
        x = "pointx",
        y = "pointy",
        z = "pointz",
        geometryColumnName = "geom",
        calculateType = CalculateType.Coordinates
)
@UniqueConstraints(
        strategys ={
                @UniqueConstraintStrategy(columnNames={"pipeStationOid","deviceCode"},name="同一线站场/阀室下编号不能重复")
        }
)
@JdbcEntity(name="daq_station_closure")
public class DaqStationClosure extends CommonJdbcEntity {

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
     * 封堵物尺寸
     */
    private String closureSize;

    /**
     * 封堵物类型，域值：closure_mold_domain
     */
    private String closureMold;

    /**
     * 封堵物材料{1未知;2:钢材;3:塑料;4:其他}
     */
    private Integer closureMaterial;

    /**
     * 连接方式,域值：closure_connection_methods_domain
     */
    private String closureConnectionMethods;

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

    public String getClosureSize() {
        return closureSize;
    }

    public void setClosureSize(String closureSize) {
        this.closureSize = closureSize;
        super.setField("closureSize");
    }

    public String getClosureMold() {
        return closureMold;
    }

    public void setClosureMold(String closureMold) {
        this.closureMold = closureMold;
        super.setField("closureMold");
    }

    public Integer getClosureMaterial() {
        return closureMaterial;
    }

    public void setClosureMaterial(Integer closureMaterial) {
        this.closureMaterial = closureMaterial;
        super.setField("closureMaterial");
    }

    public String getClosureConnectionMethods() {
        return closureConnectionMethods;
    }

    public void setClosureConnectionMethods(String closureConnectionMethods) {
        this.closureConnectionMethods = closureConnectionMethods;
        super.setField("closureConnectionMethods");
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        super.setField("address");
    }

    public String getTubeMakingMethods() {
        return tubeMakingMethods;
    }

    public void setTubeMakingMethods(String tubeMakingMethods) {
        this.tubeMakingMethods = tubeMakingMethods;
        super.setField("tubeMakingMethods");
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