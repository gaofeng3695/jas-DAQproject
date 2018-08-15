package cn.jasgroup.jasframework.acquisitiondata.material.detection.faultrasonic.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 全自动超声波检测子表Bo
 * @author zhangyi
 * @date 2018年7月12日上午10:48:03
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqDetectionFaUltrasonicSubBo extends CommonBaseBo {
	
	/**
	 * 数据oid
	 */
	private String oid;

	/**
	 * 主表oid 
	 */
	private String parentOid; 

	/**
	 * 焊口oid
	 */
	private String weldOid;

	/**
	 * 焊口编号 
	 */
	private String weldCode; 

	/**
	 * 缺陷位置 
	 */
	private String defectPosition; 

	/**
	 * 振幅区域 
	 */
	private String amplitudeRegion; 

	/**
	 * 缺陷长度(mm) 
	 */
	private Double defectLength; 

	/**
	 * 缺陷深度(mm) 
	 */
	private Double defectDepth; 

	/**
	 * 自身高度(mm) 
	 */
	private Double height;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getParentOid() {
		return parentOid;
	}

	public void setParentOid(String parentOid) {
		this.parentOid = parentOid;
	}

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
	}

	public String getWeldCode() {
		return weldCode;
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
	}

	public String getDefectPosition() {
		return defectPosition;
	}

	public void setDefectPosition(String defectPosition) {
		this.defectPosition = defectPosition;
	}

	public String getAmplitudeRegion() {
		return amplitudeRegion;
	}

	public void setAmplitudeRegion(String amplitudeRegion) {
		this.amplitudeRegion = amplitudeRegion;
	}

	public Double getDefectLength() {
		return defectLength;
	}

	public void setDefectLength(Double defectLength) {
		this.defectLength = defectLength;
	}

	public Double getDefectDepth() {
		return defectDepth;
	}

	public void setDefectDepth(Double defectDepth) {
		this.defectDepth = defectDepth;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}
	
}
