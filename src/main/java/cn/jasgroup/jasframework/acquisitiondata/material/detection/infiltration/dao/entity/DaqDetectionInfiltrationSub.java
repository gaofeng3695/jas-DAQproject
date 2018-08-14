package cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.entity;

import javax.persistence.Transient;

import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.domain.utils.DomainUtil;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>类描述：渗透检测主表。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月11日 上午11:10:38。</p>
 */

@JdbcEntity(name="daq_detection_infiltration_sub")
public class DaqDetectionInfiltrationSub extends CommonJdbcEntity {

	/**
	 * 主表oid 
	 */
	private String parentOid; 

	/**
	 * 焊口oid
	 */
	private String weldOid; 

	/**
	 * 缺陷位置 
	 */
	private String defectPosition; 

	/**
	 * 缺陷性质 
	 */
	private String defectProperties; 

	/**
	 * 缺陷尺寸(mm/mm²/点) 
	 */
	private Double defectSize; 

	public String getParentOid() {
		return parentOid; 
	}

	public void setParentOid(String parentOid) {
		this.parentOid = parentOid; 
		super.setField("parentOid");
	}

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
		super.setField("weldOid");
	}

	public String getDefectPosition() {
		return defectPosition; 
	}

	public void setDefectPosition(String defectPosition) {
		this.defectPosition = defectPosition; 
		super.setField("defectPosition");
	}

	public String getDefectProperties() {
		return defectProperties; 
	}

	public void setDefectProperties(String defectProperties) {
		this.defectProperties = defectProperties; 
		super.setField("defectProperties");
	}

	public Double getDefectSize() {
		return defectSize; 
	}

	public void setDefectSize(Double defectSize) {
		this.defectSize = defectSize; 
		super.setField("defectSize");
	}
	
	@Transient
	public String getDefectPropertiesName() {
		return DomainUtil.getValue("defect_properties_domain", defectProperties); 
	}

	public void setDefectPropertiesName(String defectPropertiesName) {
	}

}