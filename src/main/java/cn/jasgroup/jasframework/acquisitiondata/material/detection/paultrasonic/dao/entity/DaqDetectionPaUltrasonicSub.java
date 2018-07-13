package cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.entity;

import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>类描述：相控阵超声波检测子表。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月12日 上午11:01:28。</p>
 */

@JdbcEntity(name="daq_detection_pa_ultrasonic_sub")
public class DaqDetectionPaUltrasonicSub extends CommonJdbcEntity {

	/**
	 * 主表oid 
	 */
	private String parentOid; 

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

	public String getParentOid() {
		return parentOid; 
	}

	public void setParentOid(String parentOid) {
		this.parentOid = parentOid; 
		super.setField("parentOid");
	}

	public String getWeldCode() {
		return weldCode; 
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode; 
		super.setField("weldCode");
	}

	public String getDefectPosition() {
		return defectPosition; 
	}

	public void setDefectPosition(String defectPosition) {
		this.defectPosition = defectPosition; 
		super.setField("defectPosition");
	}

	public String getAmplitudeRegion() {
		return amplitudeRegion; 
	}

	public void setAmplitudeRegion(String amplitudeRegion) {
		this.amplitudeRegion = amplitudeRegion; 
		super.setField("amplitudeRegion");
	}

	public Double getDefectLength() {
		return defectLength; 
	}

	public void setDefectLength(Double defectLength) {
		this.defectLength = defectLength; 
		super.setField("defectLength");
	}

	public Double getDefectDepth() {
		return defectDepth; 
	}

	public void setDefectDepth(Double defectDepth) {
		this.defectDepth = defectDepth; 
		super.setField("defectDepth");
	}

	public Double getHeight() {
		return height; 
	}

	public void setHeight(Double height) {
		this.height = height; 
		super.setField("height");
	}

}