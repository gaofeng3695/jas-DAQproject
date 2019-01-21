package cn.jasgroup.jasframework.acquisitiondata.station.spatial.pressure.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.framework.spatial.support.enumeration.ScopeType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：管道试压实体类。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月14日 下午6:11:02。</p>
 */
@CommonSaveConfig(
		scene = "/pipePressureTest/save"
)
@CommonUpdateConfig(
	scene = "/pipePressureTest/update"
)
@CommonDeleteConfig(
	scene = "/pipePressureTest/delete"
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
@JdbcEntity(name="daq_station_pipe_pressure_test")
public class StationPipePressureTest extends CommonJdbcEntity {
	
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
	 * 试压段长度(m) 
	 */
	private Double pressureTestLength; 

	/**
	 * 管道规格 
	 */
	private String pipeSpecification; 

	/**
	 * 试压介质
	 */
	private Integer pressureTestMedium; 

	/**
	 * 设计压力(mpa) 
	 */
	private Double designPressure; 

	/**
	 * 试压日期 
	 */
	private Date pressureTestDate; 

	/**
	 * 试验过程描述 
	 */
	private String processDescription; 

	/**
	 * 结论
	 */
	private Integer pressureTestResult; 

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

	public Double getPressureTestLength() {
		return pressureTestLength; 
	}

	public void setPressureTestLength(Double pressureTestLength) {
		this.pressureTestLength = pressureTestLength; 
		super.setField("pressureTestLength");
	}

	public String getPipeSpecification() {
		return pipeSpecification; 
	}

	public void setPipeSpecification(String pipeSpecification) {
		this.pipeSpecification = pipeSpecification; 
		super.setField("pipeSpecification");
	}

	public Integer getPressureTestMedium() {
		return pressureTestMedium; 
	}

	public void setPressureTestMedium(Integer pressureTestMedium) {
		this.pressureTestMedium = pressureTestMedium; 
		super.setField("pressureTestMedium");
	}

	public Double getDesignPressure() {
		return designPressure; 
	}

	public void setDesignPressure(Double designPressure) {
		this.designPressure = designPressure; 
		super.setField("designPressure");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getPressureTestDate() {
		return pressureTestDate; 
	}

	public void setPressureTestDate(Date pressureTestDate) {
		this.pressureTestDate = pressureTestDate; 
		super.setField("pressureTestDate");
	}

	public String getProcessDescription() {
		return processDescription; 
	}

	public void setProcessDescription(String processDescription) {
		this.processDescription = processDescription; 
		super.setField("processDescription");
	}

	public Integer getPressureTestResult() {
		return pressureTestResult; 
	}

	public void setPressureTestResult(Integer pressureTestResult) {
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
