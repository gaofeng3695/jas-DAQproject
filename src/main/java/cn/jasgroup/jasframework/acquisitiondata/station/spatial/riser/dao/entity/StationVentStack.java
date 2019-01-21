package cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.dao.entity;

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
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
/**
 * 
  *<p>类描述：放空立管实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月15日 上午9:45:09。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.service.StationVentStackService #updateMaterialAfterSaveEntity()}
  * {@link cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.service.StationVentStackService #updateMaterialBeforeUpdateEntity()}
  * {@link cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.service.StationVentStackService #updateMaterialAfterUpdateEntity()}
  * {@link cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.service.StationVentStackService #updateMaterialAfterDeleteEntity()}
 */
@CommonSaveConfig(
		scene = "/ventStack/save",
		afterAdvice = {
			@Process(service = "stationVentStackService", method = "updateMaterialAfterSaveEntity()")
		}
)
@CommonUpdateConfig(
	scene = "/ventStack/update",
	beforeAdvice = {
		@Process(service = "stationVentStackService", method = "updateMaterialBeforeUpdateEntity()")
	},
	afterAdvice= {
		@Process(service = "stationVentStackService", method = "updateMaterialAfterUpdateEntity()")
	}
)
@CommonDeleteConfig(
	scene = "/ventStack/delete",
	afterAdvice= {
		@Process(service = "stationVentStackService", method = "updateMaterialAfterDeleteEntity()")
	}
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"pipeStationOid","deviceCode"},
            name="同一线站场/阀室下编号不能重复"
        ),
        @UniqueConstraintStrategy(
        	columnNames={"projectOid","manufactureNumber"},
        	name="同一项目下出厂编号不能重复")
    }
    
)
@Point(
	x = "pointx",
	y = "pointy",
	geometryColumnName = "geom",
	calculateType = CalculateType.Coordinates
)
@JdbcEntity(name="daq_station_vent_stack")
public class StationVentStack extends CommonJdbcEntity {

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
	 * 出厂编号 
	 */
	private String manufactureNumber; 

	/**
	 * 东坐标 
	 */
	private Double pointx; 

	/**
	 * 北坐标 
	 */
	private Double pointy; 

	/**
	 * 方位，域值：position_domain 
	 */
	private String position; 

	/**
	 * 距离(m) 
	 */
	private Double distance; 

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

	public String getManufactureNumber() {
		return manufactureNumber; 
	}

	public void setManufactureNumber(String manufactureNumber) {
		this.manufactureNumber = manufactureNumber; 
		super.setField("manufactureNumber");
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

	public String getPosition() {
		return position; 
	}

	public void setPosition(String position) {
		this.position = position; 
		super.setField("position");
	}

	public Double getDistance() {
		return distance; 
	}

	public void setDistance(Double distance) {
		this.distance = distance; 
		super.setField("distance");
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
	
	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}

}
