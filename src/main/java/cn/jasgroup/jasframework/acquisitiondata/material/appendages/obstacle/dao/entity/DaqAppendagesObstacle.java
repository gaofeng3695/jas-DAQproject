package cn.jasgroup.jasframework.acquisitiondata.material.appendages.obstacle.dao.entity;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>类描述：地下障碍物表。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月21日 下午12:45:30。</p>
 */
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"obstacleCode"},name="地下障碍物编号"),
    }
)
@CommonSaveConfig(
	scene = "/appendagesObstacle/save"
)
@CommonUpdateConfig(
	scene = "/appendagesObstacle/update"
)
@CommonDeleteConfig(
	scene = "/appendagesObstacle/delete"
)
@CommonDeleteBatchConfig(
	scene = "/appendagesObstacle/deleteBatch"
)
@Point(
	x = "pointx",
	y = "pointy",
	z = "pointz",
	geometryColumnName = "geom",
	calculateType = CalculateType.SingleAnchorAndDeviation,
	anchorClass = MedianStake.class,
	scopeFieldName = "pipelineOid",
	anchorOid = "medianStakeOid",
	deviation = "relativeMileage"
) 
@JdbcEntity(name="daq_appendages_obstacle")
public class DaqAppendagesObstacle extends CommonJdbcEntity {

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
	 * 线路段/穿跨越oid 
	 */
	private String pipeSegmentOrCrossOid; 

	/**
	 * 障碍物编号 
	 */
	private String obstacleCode; 

	/**
	 * 障碍物名称 
	 */
	private String obstacleName; 

	/**
	 * 障碍物类型 
	 */
	private String obstacleType; 

	/**
	 * 桩号 
	 */
	private String medianStakeOid; 

	/**
	 * 相对桩位置(m) 
	 */
	private Double relativeMileage; 

	/**
	 * X坐标 
	 */
	private Double pointx; 

	/**
	 * Y坐标 
	 */
	private Double pointy; 

	/**
	 * 高程(m) 
	 */
	private Double pointz; 

	/**
	 * 所属单位 
	 */
	private String subordinateUnit; 

	/**
	 * 地址 
	 */
	private String address; 

	/**
	 * 联系人 
	 */
	private String contacts; 

	/**
	 * 电话 
	 */
	private String telephone; 

	/**
	 * 最小间距(m) 
	 */
	private Double minDistance; 

	/**
	 * 是否与管道交叉 
	 */
	private Integer isThroughPipeline; 

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
	 * 空间数据状态 
	 */
	private String geoState; 

	/**
	 * 审核状态 
	 */
	private Integer approveStatus = 0; 

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

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid; 
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid; 
		super.setField("pipeSegmentOrCrossOid");
	}

	public String getObstacleCode() {
		return obstacleCode; 
	}

	public void setObstacleCode(String obstacleCode) {
		this.obstacleCode = obstacleCode; 
		super.setField("obstacleCode");
	}

	public String getObstacleName() {
		return obstacleName; 
	}

	public void setObstacleName(String obstacleName) {
		this.obstacleName = obstacleName; 
		super.setField("obstacleName");
	}

	public String getObstacleType() {
		return obstacleType; 
	}

	public void setObstacleType(String obstacleType) {
		this.obstacleType = obstacleType; 
		super.setField("obstacleType");
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

	public String getSubordinateUnit() {
		return subordinateUnit; 
	}

	public void setSubordinateUnit(String subordinateUnit) {
		this.subordinateUnit = subordinateUnit; 
		super.setField("subordinateUnit");
	}

	public String getAddress() {
		return address; 
	}

	public void setAddress(String address) {
		this.address = address; 
		super.setField("address");
	}

	public String getContacts() {
		return contacts; 
	}

	public void setContacts(String contacts) {
		this.contacts = contacts; 
		super.setField("contacts");
	}

	public String getTelephone() {
		return telephone; 
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone; 
		super.setField("telephone");
	}

	public Double getMinDistance() {
		return minDistance; 
	}

	public void setMinDistance(Double minDistance) {
		this.minDistance = minDistance; 
		super.setField("minDistance");
	}

	public Integer getIsThroughPipeline() {
		return isThroughPipeline; 
	}

	public void setIsThroughPipeline(Integer isThroughPipeline) {
		this.isThroughPipeline = isThroughPipeline; 
		super.setField("isThroughPipeline");
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


	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@Temporal(TemporalType.DATE)
	public Date getCollectionDate() {
		return collectionDate; 
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate; 
		super.setField("collectionDate");
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

}