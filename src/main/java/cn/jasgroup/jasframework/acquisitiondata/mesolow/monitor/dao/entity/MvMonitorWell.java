package cn.jasgroup.jasframework.acquisitiondata.mesolow.monitor.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.domain.utils.DomainUtil;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：中低压监测井信息entity。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月25日 下午1:54:04。</p>
 */
@CommonSaveConfig(
        scene = "/mvMonitorWell/save"
)
@CommonUpdateConfig(
        scene = "/mvMonitorWell/update"
)
@CommonDeleteConfig(
        scene = "/mvMonitorWell/delete"
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"projectOid","monitorWellCode"},
            name="同一项目下编号不能重复"
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
@JdbcEntity(name="daq_mv_monitor_well")
public class MvMonitorWell extends CommonJdbcEntity {

	/**
	 * 工程oid 
	 */
	private String projectOid; 

	/**
	 * 工程oid 
	 */
	private String projectName; 

	/**
	 * 编号 
	 */
	private String monitorWellCode; 

	/**
	 * 用途,域值：monitor_well_purpose_domain 
	 */
	private String monitorWellPurpose; 

	/**
	 * 用途 
	 */
	private String monitorWellPurposeName; 

	/**
	 * 坐标x 
	 */
	private Double pointx; 

	/**
	 * 坐标y 
	 */
	private Double pointy; 

	/**
	 * 管顶高程 
	 */
	private Double pointz; 

	/**
	 * 阴保监测井材料,域值:monitor_well_material_domain 
	 */
	private String monitorWellMaterial; 

	/**
	 * 阴保监测井材料 
	 */
	private String monitorWellMaterialName; 

	/**
	 * 投用日期 
	 */
	private Date investmentDate; 

	/**
	 * 采集单位 
	 */
	private String constructUnit; 

	/**
	 * 采集单位 
	 */
	private String constructUnitName; 

	/**
	 * 采集人员 
	 */
	private String collectionPerson; 

	/**
	 * 采集日期 
	 */
	private Date collectionDate; 

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
	 * 空间数据状态 
	 */
	private String geoState; 

	/**
	 * 审核状态 
	 */
	private Integer approveStatus=0; 

	/**
	 * 备注 
	 */
	private String remarks; 

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
		super.setField("projectOid");
	}

	public String getMonitorWellCode() {
		return monitorWellCode; 
	}

	public void setMonitorWellCode(String monitorWellCode) {
		this.monitorWellCode = monitorWellCode; 
		super.setField("monitorWellCode");
	}

	public String getMonitorWellPurpose() {
		return monitorWellPurpose; 
	}

	public void setMonitorWellPurpose(String monitorWellPurpose) {
		this.monitorWellPurpose = monitorWellPurpose; 
		super.setField("monitorWellPurpose");
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

	public String getMonitorWellMaterial() {
		return monitorWellMaterial; 
	}

	public void setMonitorWellMaterial(String monitorWellMaterial) {
		this.monitorWellMaterial = monitorWellMaterial; 
		super.setField("monitorWellMaterial");
	}

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
	public Date getInvestmentDate() {
		return investmentDate; 
	}

	public void setInvestmentDate(Date investmentDate) {
		this.investmentDate = investmentDate; 
		super.setField("investmentDate");
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
		super.setField("constructUnit");
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

	public String getGeoState() {
		return geoState; 
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState; 
		super.setField("geoState");
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

	@Transient
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Transient
	public String getMonitorWellPurposeName() {
		return DomainUtil.getValue("monitor_well_purpose_domain", monitorWellPurpose);
	}

	public void setMonitorWellPurposeName(String monitorWellPurposeName) {
	}

	@Transient
	public String getMonitorWellMaterialName() {
		return DomainUtil.getValue("monitor_well_material_domain", monitorWellMaterial);
	}

	public void setMonitorWellMaterialName(String monitorWellMaterialName) {
		this.monitorWellMaterialName = monitorWellMaterialName;
	}

	@Transient
	public String getConstructUnitName() {
		return constructUnitName;
	}

	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}

	@Transient
	public String getSupervisionUnitName() {
		return supervisionUnitName;
	}

	public void setSupervisionUnitName(String supervisionUnitName) {
		this.supervisionUnitName = supervisionUnitName;
	}

}