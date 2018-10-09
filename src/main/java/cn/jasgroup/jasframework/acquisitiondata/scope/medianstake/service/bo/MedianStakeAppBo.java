package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo;

import cn.jasgroup.jasframework.base.data.BaseBo;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

public class MedianStakeAppBo extends BaseBo{
	/**
	 * 
	 */
	private String oid;

	/** 项目oid */
	private String projectOid; 
	
	/** 管线oid */
	private String pipelineOid;
	
	/** 中线桩编号 */
	private String medianStakeCode; 
	
	/** 里程(km) */
	private Double mileage; 

	/** X坐标 */
	private Double pointx; 

	/** X坐标 */
	private Double pointy; 

	/** 高程 */
	private Double pointz; 
	
	private String userId=ThreadLocalHolder.getCurrentUserId();

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

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getMedianStakeCode() {
		return medianStakeCode;
	}

	public void setMedianStakeCode(String medianStakeCode) {
		this.medianStakeCode = medianStakeCode;
	}

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public Double getPointx() {
		return pointx;
	}

	public void setPointx(Double pointx) {
		this.pointx = pointx;
	}

	public Double getPointy() {
		return pointy;
	}

	public void setPointy(Double pointy) {
		this.pointy = pointy;
	}

	public Double getPointz() {
		return pointz;
	}

	public void setPointz(Double pointz) {
		this.pointz = pointz;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
