package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipesection.dao.entity;
import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.dao.entity.DaqMvPipeNode;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @version V1.0
 * @description 管段信息实体
 * @Author: 陈祥思
 * @Date: 2019/1/24 16:49
 * @since JDK 1.80
 */

@CommonSaveConfig(scene = "/mvPipeSection/save",
        afterAdvice = {
                @Process(service = "openGisService", method = "updateGeom()")
        })
@CommonUpdateConfig(scene = "/mvPipeSection/update",
        afterAdvice = {
                @Process(service = "openGisService", method = "updateGeom()")
        })
@CommonDeleteConfig(scene = "/mvPipeSection/delete")
@CommonDeleteBatchConfig(scene = "/mvPipeSection/deleteBatch")
@CommonGetConfig(scene = "/mvPipeSection/get")

@UniqueConstraints(
    strategys ={
        @UniqueConstraintStrategy(
            columnNames={"projectOid","pipeSectionCode"},
            name="同一项目下管段编号不能重复"
        )
    }
)
@JdbcEntity(name="daq_mv_pipe_section")
public class DaqMvPipeSection extends CommonJdbcEntity {
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
     * 管径(mm)
     */
    private Double pipeDiameter;

    /**
     * 壁厚(mm)
     */
    private Double wallThickness;

    /**
     * 材质
     */
    private String pipeSectionMaterial;

    /**
     * 规格
     */
    private String pipeSectionSpec;

    /**
     * 管道设计年限(年){30/50}
     */
    private Integer designLife;

    /**
     * 管道外防腐{1:3pe;2:环氧粉末}
     */
    private Integer pipeOuterAnticorrosive;

    /**
     * 防腐等级{1:普通级;2:加强级}
     */
    private String outerAnticorrosiveGrade;

    /**
     * 阴极保护方式{1牺牲阳极;2:外加电流}
     */
    private Integer cathodicProtectionMethod;

    /**
     * 埋设方式{1:埋地管;2:明管}
     */
    private Integer burialMethod;

    /**
     * 管段类别{1:市政管;2:庭院管}
     */
    private Integer pipeSectionCategory;

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

    public Double getPipeDiameter() {
        return pipeDiameter;
    }

    public void setPipeDiameter(Double pipeDiameter) {
        this.pipeDiameter = pipeDiameter;
        super.setField("pipeDiameter");
    }

    public Double getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(Double wallThickness) {
        this.wallThickness = wallThickness;
        super.setField("wallThickness");
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

    public Integer getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Integer designLife) {
        this.designLife = designLife;
        super.setField("designLife");
    }

    public Integer getPipeOuterAnticorrosive() {
        return pipeOuterAnticorrosive;
    }

    public void setPipeOuterAnticorrosive(Integer pipeOuterAnticorrosive) {
        this.pipeOuterAnticorrosive = pipeOuterAnticorrosive;
        super.setField("pipeOuterAnticorrosive");
    }

    public String getOuterAnticorrosiveGrade() {
        return outerAnticorrosiveGrade;
    }

    public void setOuterAnticorrosiveGrade(String outerAnticorrosiveGrade) {
        this.outerAnticorrosiveGrade = outerAnticorrosiveGrade;
        super.setField("outerAnticorrosiveGrade");
    }

    public Integer getCathodicProtectionMethod() {
        return cathodicProtectionMethod;
    }

    public void setCathodicProtectionMethod(Integer cathodicProtectionMethod) {
        this.cathodicProtectionMethod = cathodicProtectionMethod;
        super.setField("cathodicProtectionMethod");
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
