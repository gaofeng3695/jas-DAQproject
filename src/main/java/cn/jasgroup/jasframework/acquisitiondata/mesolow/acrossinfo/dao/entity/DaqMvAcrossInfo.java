package cn.jasgroup.jasframework.acquisitiondata.mesolow.acrossinfo.dao.entity;


import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.dao.entity.DaqMvPipeNode;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>类描述：穿越信息实体</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月24日 17:37。</p>
 */

@CommonSaveConfig(
        scene = "/daqMvAcrossInfo/save",
        afterAdvice = {
                @Process(service = "openGisService", method = "updateGeom()")
        }
)
@CommonUpdateConfig(
        scene = "/daqMvAcrossInfo/update",
        afterAdvice = {
                @Process(service = "openGisService", method = "updateGeom()")
        }
)
@CommonDeleteConfig(
        scene = "/daqMvAcrossInfo/delete"
)

@UniqueConstraints(
        strategys = {
                @UniqueConstraintStrategy(
                        columnNames = {"projectOid","pipeSectionCode"},
                        name = "同一工程下管段编号不能重复"
                )
        }
)
@JdbcEntity(name="daq_mv_across_info")
public class DaqMvAcrossInfo extends CommonJdbcEntity {

    /**
     * 工程oid
     */
    private String projectOid;

    /**
     * 管段编号
     */
    private String pipeSectionCode;

    /**
     * 起始节点oid
     */
    private String startPipeNodeOid;

    /**
     * 起始点x坐标
     */
    private Double startPointx;

    /**
     * 起始点y坐标
     */
    private Double startPointy;

    /**
     * 起始点管顶高程(m)
     */
    private Double startPointz;

    /**
     * 终止节点oid
     */
    private String endPipeNodeOid;

    /**
     * 终止点x坐标
     */
    private Double endPointx;

    /**
     * 终止点y坐标
     */
    private Double endPointy;

    /**
     * 终止点管顶高程(m)
     */
    private Double endPointz;

    /**
     * 管段长度(m)
     */
    private Double pipeSectionLength;

    /**
     * 穿越方式，域值:across_method_domain
     */
    private String acrossMethod;

    /**
     * 穿越对象类型，域值：across_object_domain
     */
    private String acrossObject;

    /**
     * 埋设方式{1:埋地管;2:明管}
     */
    private Integer burialMethod;

    /**
     * 管段类别{1:市政管;2:庭院管}
     */
    private Integer pipeSectionCategory;

    /**
     * 材质
     */
    private String pipeSectionMaterial;

    /**
     * 规格
     */
    private String pipeSectionSpec;

    /**
     * 外径
     */
    private Double outerDiameter;

    /**
     * 壁厚
     */
    private Double wallThickness;

    /**
     * 管道设计年限{30/50}
     */
    private Integer designLife;

    /**
     * 陀螺仪测量单位 
     */
    private String measureUnit;

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

    public String getPipeSectionCode() {
        return pipeSectionCode;
    }

    public void setPipeSectionCode(String pipeSectionCode) {
        this.pipeSectionCode = pipeSectionCode;
        super.setField("pipeSectionCode");
    }

    public String getStartPipeNodeOid() {
        return startPipeNodeOid;
    }

    public void setStartPipeNodeOid(String startPipeNodeOid) {
        this.startPipeNodeOid = startPipeNodeOid;
        super.setField("startPipeNodeOid");
    }

    public Double getStartPointx() {
        return startPointx;
    }

    public void setStartPointx(Double startPointx) {
        this.startPointx = startPointx;
        super.setField("startPointx");
    }

    public Double getStartPointy() {
        return startPointy;
    }

    public void setStartPointy(Double startPointy) {
        this.startPointy = startPointy;
        super.setField("startPointy");
    }

    public Double getStartPointz() {
        return startPointz;
    }

    public void setStartPointz(Double startPointz) {
        this.startPointz = startPointz;
        super.setField("startPointz");
    }

    public String getEndPipeNodeOid() {
        return endPipeNodeOid;
    }

    public void setEndPipeNodeOid(String endPipeNodeOid) {
        this.endPipeNodeOid = endPipeNodeOid;
        super.setField("endPipeNodeOid");
    }

    public Double getEndPointx() {
        return endPointx;
    }

    public void setEndPointx(Double endPointx) {
        this.endPointx = endPointx;
        super.setField("endPointx");
    }

    public Double getEndPointy() {
        return endPointy;
    }

    public void setEndPointy(Double endPointy) {
        this.endPointy = endPointy;
        super.setField("endPointy");
    }

    public Double getEndPointz() {
        return endPointz;
    }

    public void setEndPointz(Double endPointz) {
        this.endPointz = endPointz;
        super.setField("endPointz");
    }

    public Double getPipeSectionLength() {
        return pipeSectionLength;
    }

    public void setPipeSectionLength(Double pipeSectionLength) {
        this.pipeSectionLength = pipeSectionLength;
        super.setField("pipeSectionLength");
    }

    public String getAcrossMethod() {
        return acrossMethod;
    }

    public void setAcrossMethod(String acrossMethod) {
        this.acrossMethod = acrossMethod;
        super.setField("acrossMethod");
    }

    public String getAcrossObject() {
        return acrossObject;
    }

    public void setAcrossObject(String acrossObject) {
        this.acrossObject = acrossObject;
        super.setField("acrossObject");
    }

    public Integer getBurialMethod() {
        return burialMethod;
    }

    public void setBurialMethod(Integer burialMethod) {
        this.burialMethod = burialMethod;
        super.setField("burialMethod");
    }

    public Integer getPipeSectionCategory() {
        return pipeSectionCategory;
    }

    public void setPipeSectionCategory(Integer pipeSectionCategory) {
        this.pipeSectionCategory = pipeSectionCategory;
        super.setField("pipeSectionCategory");
    }

    public String getPipeSectionMaterial() {
        return pipeSectionMaterial;
    }

    public void setPipeSectionMaterial(String pipeSectionMaterial) {
        this.pipeSectionMaterial = pipeSectionMaterial;
        super.setField("pipeSectionMaterial");
    }

    public String getPipeSectionSpec() {
        return pipeSectionSpec;
    }

    public void setPipeSectionSpec(String pipeSectionSpec) {
        this.pipeSectionSpec = pipeSectionSpec;
        super.setField("pipeSectionSpec");
    }

    public Double getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(Double outerDiameter) {
        this.outerDiameter = outerDiameter;
        super.setField("outerDiameter");
    }

    public Double getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(Double wallThickness) {
        this.wallThickness = wallThickness;
        super.setField("wallThickness");
    }

    public Integer getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Integer designLife) {
        this.designLife = designLife;
        super.setField("designLife");
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
        super.setField("measureUnit");
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
