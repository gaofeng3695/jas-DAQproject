package cn.jasgroup.jasframework.acquisitiondata.mesolow.markstake.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @version V1.0
 * @description 标志桩信息信息Bo
 * @Author: 陈祥思
 * @Date: 2019/1/25 10:59
 * @since JDK 1.80
 */
public class DaqMvMarkStakeBo extends CommonBaseBo {
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
     * 编号
     */
    private String markStakeCode;

    /**
     * 标志桩类型，域值:mark_stake_type_domain
     */
    private String markStakeType;

    /**
     * 标志桩类型，域值:mark_stake_type_domain
     */
    private String markStakeTypeName;

    /**
     * 材质,域值:mark_stake_material_domain
     */
    private String markStakeMaterial;

    /**
     * 材质,域值:mark_stake_material_domain
     */
    private String markStakeMaterialName;

    /**
     * 坐标x
     */
    private Double pointx;

    /**
     * 坐标y
     */
    private Double pointy;

    /**
     * 地面高程
     */
    private Double pointz;

    /**
     * 埋设日期
     */
    private Date burialDate;

    /**
     * 采集单位
     */
    private String constructUnit;

    /**
     * 采集单位
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
     * 监理单位
     */
    private String supervisionUnitName;

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
    private Integer approveStatus;

    /**
     * 审核状态
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

    public String getMarkStakeCode() {
        return markStakeCode;
    }

    public void setMarkStakeCode(String markStakeCode) {
        this.markStakeCode = markStakeCode;
    }

    public String getMarkStakeType() {
        return markStakeType;
    }

    public void setMarkStakeType(String markStakeType) {
        this.markStakeType = markStakeType;
    }

    public String getMarkStakeTypeName() {
        return markStakeTypeName;
    }

    public void setMarkStakeTypeName(String markStakeTypeName) {
        this.markStakeTypeName = markStakeTypeName;
    }

    public String getMarkStakeMaterial() {
        return markStakeMaterial;
    }

    public void setMarkStakeMaterial(String markStakeMaterial) {
        this.markStakeMaterial = markStakeMaterial;
    }

    public String getMarkStakeMaterialName() {
        return markStakeMaterialName;
    }

    public void setMarkStakeMaterialName(String markStakeMaterialName) {
        this.markStakeMaterialName = markStakeMaterialName;
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

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getBurialDate() {
        return burialDate;
    }

    public void setBurialDate(Date burialDate) {
        this.burialDate = burialDate;
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

    public String getGeoState() {
        return geoState;
    }

    public void setGeoState(String geoState) {
        this.geoState = geoState;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
