package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p>类描述：节点信息实体Bo</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月24日 14:04。</p>
 */
public class DaqMvPipeNodeBo extends CommonBaseBo {

    /**
     * oid
     */
    private String oid;
    /**
     * 工程oid
     */
    private String projectOid;
    /**
     * 工程名称
     */
    private String projectName;
    /**
     * 节点编号
     */
    private String pipeNodeCode;

    /**
     * 点类型,域值：pipe_node_type_domain
     */
    private String pipeNodeType;
    /**
     * 点类型,域值：pipe_node_type_domain
     */
    private String pipeNodeTypeName;

    /**
     * 规格
     */
    private String pipeNodeSpec;

    /**
     * 生产厂家
     */
    private String manufacturer;

    /**
     * 出厂编号
     */
    private String factoryNum;

    /**
     * x坐标
     */
    private Double pointx;

    /**
     * y坐标
     */
    private Double pointy;

    /**
     * 管顶高程(m)
     */
    private Double pointz;

    /**
     * 埋深(m)
     */
    private Double buriedDepth;

    /**
     * 用户楼宇
     */
    private String userBuilding;

    /**
     * 是否设置电子标签
     */
    private Integer isElectronicLabel;

    /**
     * 是否设置电子标签
     */
    private String isElectronicLabelName;

    /**
     * 电子标签类型，域值：electronic_label_type_domain
     */
    private String electronicLabelType;

    /**
     * 电子标签类型，域值：electronic_label_type_domain
     */
    private String electronicLabelTypeName;

    /**
     * 采集单位
     */
    private String constructUnit;

    /**
     * 采集单位名称
     */
    private String constructUnitName;

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
     * 监理单位名称
     */
    private String supervisionUnitName;

    /**
     * 监理工程师
     */
    private String supervisionEngineer;

    /**
     * 审核状态
     */
    private Integer approveStatus;

    /**
     * 审核状态名称
     */
    private String approveStatusName;

    /**
     * 备注
     */
    private String remarks;

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

    public String getPipeNodeCode() {
        return pipeNodeCode;
    }

    public void setPipeNodeCode(String pipeNodeCode) {
        this.pipeNodeCode = pipeNodeCode;
    }

    public String getPipeNodeType() {
        return pipeNodeType;
    }

    public void setPipeNodeType(String pipeNodeType) {
        this.pipeNodeType = pipeNodeType;
    }

    public String getPipeNodeTypeName() {
        return pipeNodeTypeName;
    }

    public void setPipeNodeTypeName(String pipeNodeTypeName) {
        this.pipeNodeTypeName = pipeNodeTypeName;
    }

    public String getPipeNodeSpec() {
        return pipeNodeSpec;
    }

    public void setPipeNodeSpec(String pipeNodeSpec) {
        this.pipeNodeSpec = pipeNodeSpec;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
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

    public Double getBuriedDepth() {
        return buriedDepth;
    }

    public void setBuriedDepth(Double buriedDepth) {
        this.buriedDepth = buriedDepth;
    }

    public String getUserBuilding() {
        return userBuilding;
    }

    public void setUserBuilding(String userBuilding) {
        this.userBuilding = userBuilding;
    }

    public Integer getIsElectronicLabel() {
        return isElectronicLabel;
    }

    public void setIsElectronicLabel(Integer isElectronicLabel) {
        this.isElectronicLabel = isElectronicLabel;
    }

    public String getElectronicLabelType() {
        return electronicLabelType;
    }

    public void setElectronicLabelType(String electronicLabelType) {
        this.electronicLabelType = electronicLabelType;
    }

    public String getElectronicLabelTypeName() {
        return electronicLabelTypeName;
    }

    public void setElectronicLabelTypeName(String electronicLabelTypeName) {
        this.electronicLabelTypeName = electronicLabelTypeName;
    }

    public String getConstructUnit() {
        return constructUnit;
    }

    public void setConstructUnit(String constructUnit) {
        this.constructUnit = constructUnit;
    }

    public String getConstructUnitName() {
        return constructUnitName;
    }

    public void setConstructUnitName(String constructUnitName) {
        this.constructUnitName = constructUnitName;
    }

    public String getCollectionPerson() {
        return collectionPerson;
    }

    public void setCollectionPerson(String collectionPerson) {
        this.collectionPerson = collectionPerson;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    public String getSupervisionUnit() {
        return supervisionUnit;
    }

    public void setSupervisionUnit(String supervisionUnit) {
        this.supervisionUnit = supervisionUnit;
    }

    public String getSupervisionUnitName() {
        return supervisionUnitName;
    }

    public void setSupervisionUnitName(String supervisionUnitName) {
        this.supervisionUnitName = supervisionUnitName;
    }

    public String getSupervisionEngineer() {
        return supervisionEngineer;
    }

    public void setSupervisionEngineer(String supervisionEngineer) {
        this.supervisionEngineer = supervisionEngineer;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIsElectronicLabelName() {
        return isElectronicLabelName;
    }

    public void setIsElectronicLabelName(String isElectronicLabelName) {
        this.isElectronicLabelName = isElectronicLabelName;
    }

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName;
    }
}
