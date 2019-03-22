package cn.jasgroup.jasframework.acquisitiondata.mesolow.electroniclabel.dao.entity;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.domain.utils.DomainUtil;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.util.Date;

/**
 * <p>功能描述：电子标签信息实体</p>
 * @author 陈祥思
 * @param
 * @return
 * @since JDK1.8
 * <p>创建日期:  2019/1/25 13:52</p>
 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
 **/

@CommonSaveConfig(scene = "/mvElectronicLabel/save")
@CommonUpdateConfig(scene = "/mvElectronicLabel/update")
@CommonDeleteConfig(scene = "/mvElectronicLabel/delete")
@CommonDeleteBatchConfig(scene = "/mvElectronicLabel/deleteBatch")
@CommonGetConfig(scene = "/mvElectronicLabel/get")
//单点坐标
@Point(
		x = "pointx",
		y = "pointy",
		z = "pointz",
		geometryColumnName = "geom",
		calculateType = CalculateType.Coordinates
)

@JdbcEntity(name="daq_mv_electronic_label")
public class DaqMvElectronicLabel extends CommonJdbcEntity {
	/**
	 * 工程oid
	 */
	private String projectOid;

	/**
	 * 电子标签类型，域值：electronic_label_type_domain
	 */
	private String electronicLabelType;

	/**
	 * 电子标签特征点类型
	 */
	private String electronicLabelFeatures;

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
	 * 埋深(m)
	 */
	private Double burialDepth;

	/**
	 * 采集单位
	 */
	private String constructUnit;

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

	/**
	 * 工程名称
	 */
	private String projectName;

	/**
	 * 电子标签类型，域值：electronic_label_type_domain
	 */
	private String electronicLabelTypeName;

	/**
	 * 电子标签特征点类型
	 */
	private String electronicLabelFeaturesName;

	/**
	 * 采集单位
	 */
	private String constructUnitName;

	/**
	 * 监理单位
	 */
	private String supervisionUnitName;

	/**
	 * 审核状态
	 */
	private String approveStatusName;


	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
		super.setField("projectOid");
	}

	public String getElectronicLabelType() {
		return electronicLabelType;
	}

	public void setElectronicLabelType(String electronicLabelType) {
		this.electronicLabelType = electronicLabelType;
		super.setField("electronicLabelType");
	}

	public String getElectronicLabelFeatures() {
		return electronicLabelFeatures;
	}

	public void setElectronicLabelFeatures(String electronicLabelFeatures) {
		this.electronicLabelFeatures = electronicLabelFeatures;
		super.setField("electronicLabelFeatures");
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

	public Double getBurialDepth() {
		return burialDepth;
	}

	public void setBurialDepth(Double burialDepth) {
		this.burialDepth = burialDepth;
		super.setField("burialDepth");
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
	public String getElectronicLabelTypeName() {
		return DomainUtil.getValue("electronic_label_type_domain", electronicLabelType);
	}

	public void setElectronicLabelTypeName(String electronicLabelTypeName) {
		this.electronicLabelTypeName = electronicLabelTypeName;
	}

	@Transient
	public String getElectronicLabelFeaturesName() {
		return DomainUtil.getValue("electronic_label_features_domain", electronicLabelFeatures);
	}

	public void setElectronicLabelFeaturesName(String electronicLabelFeaturesName) {
		this.electronicLabelFeaturesName = electronicLabelFeaturesName;
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

	@Transient
	public String getApproveStatusName() {
		return approveStatusName;
	}

	public void setApproveStatusName(String approveStatusName) {
		this.approveStatusName = approveStatusName;
	}


}
