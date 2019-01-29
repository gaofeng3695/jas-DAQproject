package cn.jasgroup.jasframework.acquisitiondata.mesolow.markstake.dao.entity;
import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @version V1.0
 * @description 标志桩信息实体
 * @Author: 陈祥思
 * @Date: 2019/1/25 10:49
 * @since JDK 1.80
 */

@CommonSaveConfig(scene = "/mvMarkStake/save")
@CommonUpdateConfig(scene = "/mvMarkStake/update")
@CommonDeleteConfig(scene = "/mvMarkStake/delete")
@CommonDeleteBatchConfig(scene = "/mvMarkStake/deleteBatch")
@CommonGetConfig(scene = "/mvMarkStake/get")
//单点坐标
@Point(
        x = "pointx",
        y = "pointy",
        z = "pointz",
        geometryColumnName = "geom",
        calculateType = CalculateType.Coordinates
)
@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"projectOid","markStakeCode"},
            name="同一项目下编号不能重复"
        )
    }
)
@JdbcEntity(name="daq_mv_mark_stake")
public class DaqMvMarkStake extends CommonJdbcEntity {

    /**
     * 工程oid
     */
    private String projectOid;

    /**
     * 编号
     */
    private String markStakeCode;

    /**
     * 标志桩类型，域值:mark_stake_type_domain
     */
    private String markStakeType;

    /**
     * 材质,域值:mark_stake_material_domain
     */
    private String markStakeMaterial;

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
     * 备注
     */
    private String remarks;


    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
        super.setField("projectOid");
    }

    public String getMarkStakeCode() {
        return markStakeCode;
    }

    public void setMarkStakeCode(String markStakeCode) {
        this.markStakeCode = markStakeCode;
        super.setField("markStakeCode");
    }

    public String getMarkStakeType() {
        return markStakeType;
    }

    public void setMarkStakeType(String markStakeType) {
        this.markStakeType = markStakeType;
        super.setField("markStakeType");
    }

    public String getMarkStakeMaterial() {
        return markStakeMaterial;
    }

    public void setMarkStakeMaterial(String markStakeMaterial) {
        this.markStakeMaterial = markStakeMaterial;
        super.setField("markStakeMaterial");
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

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getBurialDate() {
        return burialDate;
    }

    public void setBurialDate(Date burialDate) {
        this.burialDate = burialDate;
        super.setField("burialDate");
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        super.setField("remarks");
    }


}
