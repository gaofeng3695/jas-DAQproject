package cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * @description 超声波子表
 * @author zhangyi
 * @date 2018年7月11日上午10:10:38
 * @version V1.0
 * @since JDK 1.80
 */

public class DaqDetectionUltrasonicSubBo extends CommonBaseBo{
	
	/** 数据oid */
	private String oid;

	/** 主表oid */
	private String parentOid;

	/**
	 * 焊口oid
	 */
	private String weldOid;

	/** 焊口编号 */
	private String weldCode;

	/** 缺陷位置 */
	private String defectPosition;

	/** 缺陷性质 */
	private String defectProperties;
	
	/** 缺陷性质阈值名称 */
	private String defectPropertiesName;

	/** 缺陷尺寸(mm/mm²/点) */
	private Double defectSize;

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

	public String getDefectProperties() {
		return defectProperties;
	}

	public void setDefectProperties(String defectProperties) {
		this.defectProperties = defectProperties;
	}

	public String getDefectPropertiesName() {
		return defectPropertiesName;
	}

	public void setDefectPropertiesName(String defectPropertiesName) {
		this.defectPropertiesName = defectPropertiesName;
	}

	public Double getDefectSize() {
		return defectSize;
	}

	public void setDefectSize(Double defectSize) {
		this.defectSize = defectSize;
	}
}
