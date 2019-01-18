package cn.jasgroup.jasframework.acquisitiondata.station.spatial.reserved.dao.entity;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：预留甩头entity。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月15日 下午3:08:43。</p>
 */
@CommonSaveConfig(
		scene = "/reservedInterface/save"
)
@CommonUpdateConfig(
	scene = "/reservedInterface/update"
)
@CommonDeleteConfig(
	scene = "/reservedInterface/delete"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"pipeStationOid","deviceCode"},
            name="同一线站场/阀室下编号不能重复"
        )
    }
)
@Point(
	x = "pointx",
	y = "pointy",
	z = "pointz",
	geometryColumnName = "geom",
	calculateType = CalculateType.Coordinates
)
@JdbcEntity(name="daq_station_reserved_interface")
public class StationReservedInterface extends CommonJdbcEntity {

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
	 * 甩头管径(mm） 
	 */
	private Double pipeDiameter; 

	/**
	 * 甩头材质{1未知;2:钢材;3:塑料;4:其他} 
	 */
	private Integer interfaceMaterial; 

	/**
	 * 甩头壁厚(mm) 
	 */
	private Double wallThickness; 

	/**
	 * 甩头封口形式 
	 */
	private String sealingType; 

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
	 * 地址 
	 */
	private String address; 

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
	 * 施工日期 
	 */
	private Date constructDate; 

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

	public Double getPipeDiameter() {
		return pipeDiameter; 
	}

	public void setPipeDiameter(Double pipeDiameter) {
		this.pipeDiameter = pipeDiameter; 
		super.setField("pipeDiameter");
	}

	public Integer getInterfaceMaterial() {
		return interfaceMaterial; 
	}

	public void setInterfaceMaterial(Integer interfaceMaterial) {
		this.interfaceMaterial = interfaceMaterial; 
		super.setField("interfaceMaterial");
	}

	public Double getWallThickness() {
		return wallThickness; 
	}

	public void setWallThickness(Double wallThickness) {
		this.wallThickness = wallThickness; 
		super.setField("wallThickness");
	}

	public String getSealingType() {
		return sealingType; 
	}

	public void setSealingType(String sealingType) {
		this.sealingType = sealingType; 
		super.setField("sealingType");
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

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getConstructDate() {
		return constructDate;
	}

	public void setConstructDate(Date constructDate) {
		this.constructDate = constructDate;
		super.setField("constructDate");
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
		super.setField("remarks");
	}

}
