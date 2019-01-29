package cn.jasgroup.jasframework.acquisitiondata.mesolow.bushinginfo.dao.entity;


import java.util.Date;

import cn.jasgroup.framework.spatial.annotation.Line;
import cn.jasgroup.framework.spatial.support.enumeration.CalculateType;
import cn.jasgroup.jasframework.acquisitiondata.mesolow.acrossinfo.dao.entity.DaqMvAcrossInfo;
import cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.dao.entity.DaqMvPipeNode;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>类描述：套管信息entity</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月25日 13:51。</p>
 */
@CommonSaveConfig(
        scene = "/daqMvBushingInfo/save",
        afterAdvice = {
                @Process(service = "openGisService", method = "updateGeom()")
        }
)
@CommonUpdateConfig(
        scene = "/daqMvBushingInfo/update",
        afterAdvice = {
                @Process(service = "openGisService", method = "updateGeom()")
        }
)
@CommonDeleteConfig(
        scene = "/daqMvBushingInfo/delete"
)
@JdbcEntity(name="daq_mv_bushing_info")
public class DaqMvBushingInfo extends CommonJdbcEntity {

    /**
     * 工程oid
     */
    private String projectOid;

    /**
     * 材质
     */
    private String bushingMaterial;

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

    public String getBushingMaterial() {
        return bushingMaterial;
    }

    public void setBushingMaterial(String bushingMaterial) {
        this.bushingMaterial = bushingMaterial;
        super.setField("bushingMaterial");
    }

    public String getBushingSpec() {
        return bushingSpec;
    }

    public void setBushingSpec(String bushingSpec) {
        this.bushingSpec = bushingSpec;
        super.setField("bushingSpec");
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

    public Double getStartBuriedDepth() {
        return startBuriedDepth;
    }

    public void setStartBuriedDepth(Double startBuriedDepth) {
        this.startBuriedDepth = startBuriedDepth;
        super.setField("startBuriedDepth");
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

    public Double getEndBuriedDepth() {
        return endBuriedDepth;
    }

    public void setEndBuriedDepth(Double endBuriedDepth) {
        this.endBuriedDepth = endBuriedDepth;
        super.setField("endBuriedDepth");
    }

    public Integer getHasCheckLeakDevice() {
        return hasCheckLeakDevice;
    }

    public void setHasCheckLeakDevice(Integer hasCheckLeakDevice) {
        this.hasCheckLeakDevice = hasCheckLeakDevice;
        super.setField("hasCheckLeakDevice");
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
