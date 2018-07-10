package cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.dao.entity;

import javax.persistence.Column;

import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonGetConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>类描述：射线检测子表。</p>
 * @author 张毅
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2018年7月10日 上午9:55:56。</p>
 */

@CommonSaveConfig(scene = "/detectionRaySub/save")
@CommonUpdateConfig(scene = "/detectionRaySub/update")
@CommonDeleteConfig(scene = "/detectionRaySub/delete")
@CommonDeleteBatchConfig(scene = "/detectionRaySub/deleteBatch")
@CommonGetConfig(scene = "/detectionRaySub/get")
@JdbcEntity(name = "daq_detection_ray_sub")
public class DaqDetectionRaySub extends CommonJdbcEntity {

	/** 主表oid */
	private String parentOid;

	/** 焊口编号 */
	private String weldCode;

	/** 缺陷位置 */
	private String defectPosition;

	/** 缺陷性质 */
	private String defectProperties;

	/** 缺陷尺寸(mm/mm²/点) */
	private Double defectSize;

	@Column(name = "parent_oid", length = 36)
	public String getParentOid() {
		return parentOid;
	}

	public void setParentOid(String parentOid) {
		this.parentOid = parentOid;
		super.setField("parentOid");
	}

	@Column(name = "weld_code", length = 36)
	public String getWeldCode() {
		return weldCode;
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
		super.setField("weldCode");
	}

	@Column(name = "defect_position", length = 60)
	public String getDefectPosition() {
		return defectPosition;
	}

	public void setDefectPosition(String defectPosition) {
		this.defectPosition = defectPosition;
		super.setField("defectPosition");
	}

	@Column(name = "defect_properties", length = 50)
	public String getDefectProperties() {
		return defectProperties;
	}

	public void setDefectProperties(String defectProperties) {
		this.defectProperties = defectProperties;
		super.setField("defectProperties");
	}

	@Column(name = "defect_size", precision = 9, scale = 3)
	public Double getDefectSize() {
		return defectSize;
	}

	public void setDefectSize(Double defectSize) {
		this.defectSize = defectSize;
		super.setField("defectSize");
	}

}