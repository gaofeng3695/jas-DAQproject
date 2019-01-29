package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipesection.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @version V1.0
 * @description 管段信息Bo
 * @Author: 陈祥思
 * @Date: 2019/1/24 15:49
 * @since JDK 1.80
 */
public class DaqMvPipeSectionBo extends CommonBaseBo {
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
     * 管段编号
     */
    private String pipeSectionCode;

    /**
     * 起始节点oid
     */
    private String startPipeNodeOid;

    /**
     * 起始节点编号
     */
    private String startPipeNodeCode;

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
     * 终止节点编号
     */
    private String endPipeNodeCode;

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
     * 材质
     */
    private String pipeSectionMaterialName;

    /**
     * 规格
     */
    private String pipeSectionSpec;

    /**
     * 规格
     */
    private String pipeSectionSpecName;

    /**
     * 管道设计年限(年){30/50}
     */
    private Integer designLife;

    /**
     * 管道设计年限(年){30/50}
     */
    private String designLifeName;

    /**
     * 管道外防腐{1:3pe;2:环氧粉末}
     */
    private Integer pipeOuterAnticorrosive;

    /**
     * 管道外防腐{1:3pe;2:环氧粉末}
     */
    private String pipeOuterAnticorrosiveName;

    /**
     * 防腐等级{1:普通级;2:加强级}
     */
    private String outerAnticorrosiveGrade;

    /**
     * 防腐等级{1:普通级;2:加强级}
     */
    private String outerAnticorrosiveGradeName;

    /**
     * 阴极保护方式{1牺牲阳极;2:外加电流}
     */
    private Integer cathodicProtectionMethod;

    /**
     * 阴极保护方式{1牺牲阳极;2:外加电流}
     */
    private String cathodicProtectionMethodName;

    /**
     * 埋设方式{1:埋地管;2:明管}
     */
    private Integer burialMethod;

    /**
     * 埋设方式{1:埋地管;2:明管}
     */
    private String burialMethodName;

    /**
     * 管段类别{1:市政管;2:庭院管}
     */
    private Integer pipeSectionCategory;

    /**
     * 管段类别{1:市政管;2:庭院管}
     */
    private String pipeSectionCategoryName;

    /**
     * 采集单位
     */
    private String constructUnit;

    /**
     * 施工单位
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

    public String getPipeSectionCode() {
        return pipeSectionCode;
    }

    public void setPipeSectionCode(String pipeSectionCode) {
        this.pipeSectionCode = pipeSectionCode;
    }

    public String getStartPipeNodeOid() {
        return startPipeNodeOid;
    }

    public void setStartPipeNodeOid(String startPipeNodeOid) {
        this.startPipeNodeOid = startPipeNodeOid;
    }

    public String getStartPipeNodeCode() {
        return startPipeNodeCode;
    }

    public void setStartPipeNodeCode(String startPipeNodeCode) {
        this.startPipeNodeCode = startPipeNodeCode;
    }

    public Double getStartPointx() {
        return startPointx;
    }

    public void setStartPointx(Double startPointx) {
        this.startPointx = startPointx;
    }

    public Double getStartPointy() {
        return startPointy;
    }

    public void setStartPointy(Double startPointy) {
        this.startPointy = startPointy;
    }

    public Double getStartPointz() {
        return startPointz;
    }

    public void setStartPointz(Double startPointz) {
        this.startPointz = startPointz;
    }

    public String getEndPipeNodeOid() {
        return endPipeNodeOid;
    }

    public void setEndPipeNodeOid(String endPipeNodeOid) {
        this.endPipeNodeOid = endPipeNodeOid;
    }

    public String getEndPipeNodeCode() {
        return endPipeNodeCode;
    }

    public void setEndPipeNodeCode(String endPipeNodeCode) {
        this.endPipeNodeCode = endPipeNodeCode;
    }

    public Double getEndPointx() {
        return endPointx;
    }

    public void setEndPointx(Double endPointx) {
        this.endPointx = endPointx;
    }

    public Double getEndPointy() {
        return endPointy;
    }

    public void setEndPointy(Double endPointy) {
        this.endPointy = endPointy;
    }

    public Double getEndPointz() {
        return endPointz;
    }

    public void setEndPointz(Double endPointz) {
        this.endPointz = endPointz;
    }

    public Double getPipeSectionLength() {
        return pipeSectionLength;
    }

    public void setPipeSectionLength(Double pipeSectionLength) {
        this.pipeSectionLength = pipeSectionLength;
    }

    public Double getPipeDiameter() {
        return pipeDiameter;
    }

    public void setPipeDiameter(Double pipeDiameter) {
        this.pipeDiameter = pipeDiameter;
    }

    public Double getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(Double wallThickness) {
        this.wallThickness = wallThickness;
    }

    public String getPipeSectionMaterial() {
        return pipeSectionMaterial;
    }

    public void setPipeSectionMaterial(String pipeSectionMaterial) {
        this.pipeSectionMaterial = pipeSectionMaterial;
    }

    public String getPipeSectionMaterialName() {
        return pipeSectionMaterialName;
    }

    public void setPipeSectionMaterialName(String pipeSectionMaterialName) {
        this.pipeSectionMaterialName = pipeSectionMaterialName;
    }

    public String getPipeSectionSpec() {
        return pipeSectionSpec;
    }

    public void setPipeSectionSpec(String pipeSectionSpec) {
        this.pipeSectionSpec = pipeSectionSpec;
    }

    public String getPipeSectionSpecName() {
        return pipeSectionSpecName;
    }

    public void setPipeSectionSpecName(String pipeSectionSpecName) {
        this.pipeSectionSpecName = pipeSectionSpecName;
    }

    public Integer getDesignLife() {
        return designLife;
    }

    public void setDesignLife(Integer designLife) {
        this.designLife = designLife;
    }

    public String getDesignLifeName() {
        return designLifeName;
    }

    public void setDesignLifeName(String designLifeName) {
        this.designLifeName = designLifeName;
    }

    public Integer getPipeOuterAnticorrosive() {
        return pipeOuterAnticorrosive;
    }

    public void setPipeOuterAnticorrosive(Integer pipeOuterAnticorrosive) {
        this.pipeOuterAnticorrosive = pipeOuterAnticorrosive;
    }

    public String getPipeOuterAnticorrosiveName() {
        return pipeOuterAnticorrosiveName;
    }

    public void setPipeOuterAnticorrosiveName(String pipeOuterAnticorrosiveName) {
        this.pipeOuterAnticorrosiveName = pipeOuterAnticorrosiveName;
    }

    public String getOuterAnticorrosiveGrade() {
        return outerAnticorrosiveGrade;
    }

    public void setOuterAnticorrosiveGrade(String outerAnticorrosiveGrade) {
        this.outerAnticorrosiveGrade = outerAnticorrosiveGrade;
    }

    public String getOuterAnticorrosiveGradeName() {
        return outerAnticorrosiveGradeName;
    }

    public void setOuterAnticorrosiveGradeName(String outerAnticorrosiveGradeName) {
        this.outerAnticorrosiveGradeName = outerAnticorrosiveGradeName;
    }

    public Integer getCathodicProtectionMethod() {
        return cathodicProtectionMethod;
    }

    public void setCathodicProtectionMethod(Integer cathodicProtectionMethod) {
        this.cathodicProtectionMethod = cathodicProtectionMethod;
    }

    public String getCathodicProtectionMethodName() {
        return cathodicProtectionMethodName;
    }

    public void setCathodicProtectionMethodName(String cathodicProtectionMethodName) {
        this.cathodicProtectionMethodName = cathodicProtectionMethodName;
    }

    public Integer getBurialMethod() {
        return burialMethod;
    }

    public void setBurialMethod(Integer burialMethod) {
        this.burialMethod = burialMethod;
    }

    public String getBurialMethodName() {
        return burialMethodName;
    }

    public void setBurialMethodName(String burialMethodName) {
        this.burialMethodName = burialMethodName;
    }

    public Integer getPipeSectionCategory() {
        return pipeSectionCategory;
    }

    public void setPipeSectionCategory(Integer pipeSectionCategory) {
        this.pipeSectionCategory = pipeSectionCategory;
    }

    public String getPipeSectionCategoryName() {
        return pipeSectionCategoryName;
    }

    public void setPipeSectionCategoryName(String pipeSectionCategoryName) {
        this.pipeSectionCategoryName = pipeSectionCategoryName;
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

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @Temporal(TemporalType.DATE)
    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

}
