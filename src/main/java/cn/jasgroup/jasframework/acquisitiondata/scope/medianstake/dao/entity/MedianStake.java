package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.hibernate.entity.CommonHibernateEntity;

@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(columnNames={"medianStakeCode"},name="中线桩编号已存在"),
    }
)
@Point(
	x="pointx",
	y="pointy",
	z="pointz",
	m="mileage",
	geometryColumnName="geom",
	calculateType=CalculateType.Coordinates
)
@Entity(name = "daq_median_stake")
public class MedianStake extends CommonHibernateEntity{
	
	/** 中线桩编号 */
	private String medianStakeCode; 

	/** 项目oid */
	private String projectOid; 

	/** 管线oid */
	private String pipelineOid; 

	/** 里程(km) */
	private Double mileage; 

	/** 标石类型 */
	private String markStoneType; 

	/** 标石概略位置 */
	private String markStoneLocation; 

	/** X坐标 */
	private Double pointx; 

	/** X坐标 */
	private Double pointy; 

	/** 高程 */
	private Double pointz; 

	/** 备注 */
	private String remarks;
	/**
     * 空间数据状态
     */
    private String geoState;
	
	@Column(name="median_stake_code", length=50)
	public String getMedianStakeCode() {
		return medianStakeCode;
	}

	public void setMedianStakeCode(String medianStakeCode) {
		this.medianStakeCode = medianStakeCode;
		super.setField("medianStakeCode");
	}

	@Column(name="project_oid",length=36)
	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
		super.setField("projectOid");
	}
	
	@Column(name="pipeline_oid",length=36)
	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
		super.setField("pipelineOid");
	}

	@Column(name="mileage")
	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
		super.setField("mileage");
	}

	@Column(name="mark_stone_type",length=38)
	public String getMarkStoneType() {
		return markStoneType;
	}

	public void setMarkStoneType(String markStoneType) {
		this.markStoneType = markStoneType;
		super.setField("markStoneType");
	}

	@Column(name="mark_stone_location",length=150)
	public String getMarkStoneLocation() {
		return markStoneLocation;
	}

	public void setMarkStoneLocation(String markStoneLocation) {
		this.markStoneLocation = markStoneLocation;
		super.setField("markStoneLocation");
	}
	
	@Column(name="pointx")
	public Double getPointx() {
		return pointx;
	}

	public void setPointx(Double pointx) {
		this.pointx = pointx;
		super.setField("pointx");
	}
	
	@Column(name="pointy")
	public Double getPointy() {
		return pointy;
	}

	public void setPointy(Double pointy) {
		this.pointy = pointy;
		super.setField("pointy");
	}

	@Column(name="pointz")
	public Double getPointz() {
		return pointz;
	}

	public void setPointz(Double pointz) {
		this.pointz = pointz;
		super.setField("pointz");
	}

	@Column(name="remarks",length=200)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
		super.setField("remarks");
	}
	@Column(name="geo_state")
	public String getGeoState() {
		return geoState;
	}

	public void setGeoState(String geoState) {
		this.geoState = geoState;
	}
}
