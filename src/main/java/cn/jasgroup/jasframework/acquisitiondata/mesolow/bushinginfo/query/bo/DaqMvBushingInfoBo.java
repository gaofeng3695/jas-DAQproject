package cn.jasgroup.jasframework.acquisitiondata.mesolow.bushinginfo.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * <p>类描述：套管信息bo</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月25日 13:54。</p>
 */
public class DaqMvBushingInfoBo extends CommonBaseBo {

    /**
     * oid
     */
    private String oid;

    /**
     * 工程oid
     */
    private String projectOid;

    /**
     * 工程oid
     */
    private String projectName;

    /**
     * 材质
     */
    private String bushingMaterial;

    /**
     * 材质
     */
    private String bushingMaterialName;

    /**
     * 规格
     */
    private String bushingSpec;

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
     * 起始点埋深(m)
     */
    private Double startBuriedDepth;

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
     * 终止点埋深(m)
     */
    private Double endBuriedDepth;

    /**
     * 是否设置检漏装置{0:否;1:是}
     */
    private Integer hasCheckLeakDevice;

    /**
     * 是否设置检漏装置{0:否;1:是}
     */
    private String hasCheckLeakDeviceName;

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

    public String getBushingMaterial() {
        return bushingMaterial;
    }

    public void setBushingMaterial(String bushingMaterial) {
        this.bushingMaterial = bushingMaterial;
    }

    public String getBushingMaterialName() {
        return bushingMaterialName;
    }

    public void setBushingMaterialName(String bushingMaterialName) {
        this.bushingMaterialName = bushingMaterialName;
    }

    public String getBushingSpec() {
        return bushingSpec;
    }

    public void setBushingSpec(String bushingSpec) {
        this.bushingSpec = bushingSpec;
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

    public Double getStartBuriedDepth() {
        return startBuriedDepth;
    }

    public void setStartBuriedDepth(Double startBuriedDepth) {
        this.startBuriedDepth = startBuriedDepth;
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

    public Double getEndBuriedDepth() {
        return endBuriedDepth;
    }

    public void setEndBuriedDepth(Double endBuriedDepth) {
        this.endBuriedDepth = endBuriedDepth;
    }

    public Integer getHasCheckLeakDevice() {
        return hasCheckLeakDevice;
    }

    public void setHasCheckLeakDevice(Integer hasCheckLeakDevice) {
        this.hasCheckLeakDevice = hasCheckLeakDevice;
    }

    public String getHasCheckLeakDeviceName() {
        return hasCheckLeakDeviceName;
    }

    public void setHasCheckLeakDeviceName(String hasCheckLeakDeviceName) {
        this.hasCheckLeakDeviceName = hasCheckLeakDeviceName;
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
}
