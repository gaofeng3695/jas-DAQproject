package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.dao.entity;

import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Point;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * <p>类描述：节点信息实体</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月24日 13:56。</p>
 */

@CommonSaveConfig(
        scene = "/daqMvPipeNode/save"
)
@CommonUpdateConfig(
        scene = "/daqMvPipeNode/update"
)
@CommonDeleteConfig(
        scene = "/daqMvPipeNode/delete"
)
@Point(
        x = "pointx",
        y = "pointy",
        z = "pointz",
        geometryColumnName = "geom",
        calculateType = CalculateType.Coordinates
)
@UniqueConstraints(
        strategys = {
                @UniqueConstraintStrategy(
                        columnNames = {"projectOid","pipeNodeCode"},
                        name = "同一工程下节点编号不能重复"
                )
        }
)
@JdbcEntity(name="daq_mv_pipe_node")
public class DaqMvPipeNode extends CommonJdbcEntity {

    /**
     * 工程oid
     */
    private String projectOid;

    /**
     * 节点编号
     */
    private String pipeNodeCode;

    /**
     * 点类型,域值：pipe_node_type_domain
     */
    private String pipeNodeType;

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
     * 电子标签类型，域值：electronic_label_type_domain
     */
    private String electronicLabelType;

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

    public String getPipeNodeCode() {
        return pipeNodeCode;
    }

    public void setPipeNodeCode(String pipeNodeCode) {
        this.pipeNodeCode = pipeNodeCode;
        super.setField("pipeNodeCode");
    }

    public String getPipeNodeType() {
        return pipeNodeType;
    }

    public void setPipeNodeType(String pipeNodeType) {
        this.pipeNodeType = pipeNodeType;
        super.setField("pipeNodeType");
    }

    public String getPipeNodeSpec() {
        return pipeNodeSpec;
    }

    public void setPipeNodeSpec(String pipeNodeSpec) {
        this.pipeNodeSpec = pipeNodeSpec;
        super.setField("pipeNodeSpec");
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        super.setField("manufacturer");
    }

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
        super.setField("factoryNum");
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

    public Double getBuriedDepth() {
        return buriedDepth;
    }

    public void setBuriedDepth(Double buriedDepth) {
        this.buriedDepth = buriedDepth;
        super.setField("buriedDepth");
    }

    public String getUserBuilding() {
        return userBuilding;
    }

    public void setUserBuilding(String userBuilding) {
        this.userBuilding = userBuilding;
        super.setField("userBuilding");
    }

    public Integer getIsElectronicLabel() {
        return isElectronicLabel;
    }

    public void setIsElectronicLabel(Integer isElectronicLabel) {
        this.isElectronicLabel = isElectronicLabel;
        super.setField("isElectronicLabel");
    }

    public String getElectronicLabelType() {
        return electronicLabelType;
    }

    public void setElectronicLabelType(String electronicLabelType) {
        this.electronicLabelType = electronicLabelType;
        super.setField("electronicLabelType");
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
