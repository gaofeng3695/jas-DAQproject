package cn.jasgroup.jasframework.acquisitiondata.mesolow.acrossinfo.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p>类描述：穿越信息实体Bo</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月25日 9:15。</p>
 */
public class DaqMvAcrossInfoBo extends CommonBaseBo {

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
     * 起始节点名称
     */
    private String startPipeNodeName;

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
     * 终止节点名称
     */
    private String endPipeNodeName;

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
     * 穿越方式，域值:across_method_domain
     */
    private String acrossMethodName;

    /**
     * 穿越对象类型，域值：across_object_domain
     */
    private String acrossObject;

    /**
     * 穿越对象类型，域值：across_object_domain
     */
    private String acrossObjectName;

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
     * 管道设计年限{30/50}
     */
    private String designLifeName;

    /**
     * 陀螺仪测量单位 
     */
    private String measureUnit;

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

    public String getStartPipeNodeName() {
        return startPipeNodeName;
    }

    public void setStartPipeNodeName(String startPipeNodeName) {
        this.startPipeNodeName = startPipeNodeName;
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

    public String getEndPipeNodeName() {
        return endPipeNodeName;
    }

    public void setEndPipeNodeName(String endPipeNodeName) {
        this.endPipeNodeName = endPipeNodeName;
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

    public String getAcrossMethod() {
        return acrossMethod;
    }

    public void setAcrossMethod(String acrossMethod) {
        this.acrossMethod = acrossMethod;
    }

    public String getAcrossMethodName() {
        return acrossMethodName;
    }

    public void setAcrossMethodName(String acrossMethodName) {
        this.acrossMethodName = acrossMethodName;
    }

    public String getAcrossObject() {
        return acrossObject;
    }

    public void setAcrossObject(String acrossObject) {
        this.acrossObject = acrossObject;
    }

    public String getAcrossObjectName() {
        return acrossObjectName;
    }

    public void setAcrossObjectName(String acrossObjectName) {
        this.acrossObjectName = acrossObjectName;
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

    public String getPipeSectionMaterial() {
        return pipeSectionMaterial;
    }

    public void setPipeSectionMaterial(String pipeSectionMaterial) {
        this.pipeSectionMaterial = pipeSectionMaterial;
    }

    public String getPipeSectionSpec() {
        return pipeSectionSpec;
    }

    public void setPipeSectionSpec(String pipeSectionSpec) {
        this.pipeSectionSpec = pipeSectionSpec;
    }

    public Double getOuterDiameter() {
        return outerDiameter;
    }

    public void setOuterDiameter(Double outerDiameter) {
        this.outerDiameter = outerDiameter;
    }

    public Double getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(Double wallThickness) {
        this.wallThickness = wallThickness;
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

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
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

    public String getPipeSectionMaterialName() {
        return pipeSectionMaterialName;
    }

    public void setPipeSectionMaterialName(String pipeSectionMaterialName) {
        this.pipeSectionMaterialName = pipeSectionMaterialName;
    }

    public String getPipeSectionSpecName() {
        return pipeSectionSpecName;
    }

    public void setPipeSectionSpecName(String pipeSectionSpecName) {
        this.pipeSectionSpecName = pipeSectionSpecName;
    }
}
