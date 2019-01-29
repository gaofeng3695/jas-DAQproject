package cn.jasgroup.jasframework.acquisitiondata.mesolow.strenchprotect.dao.entity;

import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.dao.entity.DaqMvPipeNode;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * <p>类描述：管沟信息entity</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月25日 14:59。</p>
 */
@CommonSaveConfig(
        scene = "/daqMvPipeTrenchProtect/save",
        afterAdvice = {
                @Process(service = "openGisService", method = "updateGeom()")
        }
)
@CommonUpdateConfig(
        scene = "/daqMvPipeTrenchProtect/update",
        afterAdvice = {
                @Process(service = "openGisService", method = "updateGeom()")
        }
)
@CommonDeleteConfig(
        scene = "/daqMvPipeTrenchProtect/delete"
)
@JdbcEntity(name="daq_mv_pipe_trench_protect")
public class DaqMvPipeTrenchProtect extends CommonJdbcEntity {

    /**
     * 工程oid
     */
    private String projectOid;

    /**
     * 工程oid
     */
    private String projectName;

    /**
     * 管沟长度(m)
     */
    private Double pipeTrenchLength;

    /**
     * 管沟宽度(m)
     */
    private Double pipeTrenchWidth;

    /**
     * 管沟高度(m)
     */
    private Double pipeTrenchHeight;

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

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
        super.setField("projectOid");
    }

    public Double getPipeTrenchLength() {
        return pipeTrenchLength;
    }

    public void setPipeTrenchLength(Double pipeTrenchLength) {
        this.pipeTrenchLength = pipeTrenchLength;
        super.setField("pipeTrenchLength");
    }

    public Double getPipeTrenchWidth() {
        return pipeTrenchWidth;
    }

    public void setPipeTrenchWidth(Double pipeTrenchWidth) {
        this.pipeTrenchWidth = pipeTrenchWidth;
        super.setField("pipeTrenchWidth");
    }

    public Double getPipeTrenchHeight() {
        return pipeTrenchHeight;
    }

    public void setPipeTrenchHeight(Double pipeTrenchHeight) {
        this.pipeTrenchHeight = pipeTrenchHeight;
        super.setField("pipeTrenchHeight");
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

    @Transient
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
