package cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.BaseBo;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

/**
 * 
 * <p>
 * 类描述：焊口测量成果信息Bo。
 * </p>
 * 
 * @author 葛建 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 *        <p>
 * 		创建日期：2018年7月11日 下午4:44:42。
 *        </p>
 */
public class WeldMeasuredResultAppBo extends BaseBo {

	/**
	 * oid
	 */
	private String oid;

	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 项目编号
	 */
	private String projectName;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 管线编号
	 */
	private String pipelineName;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段编号
	 */
	private String tendersName;

	/**
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid;

	/**
	 * 线路段/穿跨越名称
	 */
	private String pipeSegmentOrCrossName;

//	/**
//	 * 焊口oid
//	 */
//	private String weldOid;
//
//	/**
//	 * 焊口编号
//	 */
//	private String weldCode;
	
	/**
	 * 测量控制点编号 
	 */
	private String measureControlPointCode; 

	/** X坐标 */
	private Double pointx;

	/** X坐标 */
	private Double pointy;

	/** 高程 */
	private Double pointz;
	/**
	 * 里程
	 */
	private Double mileage;
	
	private String userId = ThreadLocalHolder.getCurrentUserId();

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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getPipelineName() {
		return pipelineName;
	}

	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getTendersName() {
		return tendersName;
	}

	public void setTendersName(String tendersName) {
		this.tendersName = tendersName;
	}

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}

	public String getPipeSegmentOrCrossName() {
		return pipeSegmentOrCrossName;
	}

	public void setPipeSegmentOrCrossName(String pipeSegmentOrCrossName) {
		this.pipeSegmentOrCrossName = pipeSegmentOrCrossName;
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

	public Double getMileage() {
		return mileage;
	}

	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMeasureControlPointCode() {
		return measureControlPointCode;
	}

	public void setMeasureControlPointCode(String measureControlPointCode) {
		this.measureControlPointCode = measureControlPointCode;
	}
	
	

}
