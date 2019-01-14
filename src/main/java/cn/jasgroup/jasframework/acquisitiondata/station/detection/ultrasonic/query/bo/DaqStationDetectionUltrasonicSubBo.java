package cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.query.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import cn.jasgroup.jasframework.domain.utils.DomainUtil;

import javax.persistence.Transient;

/**
 * <p>Table description 超声波检测子表(站场)Bo</p>
 * @author 陈祥思
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-14 11:48:13。</p>
 */
public class DaqStationDetectionUltrasonicSubBo extends CommonBaseBo {
    /**
     * oid
     */
    private String oid;
    /**
     * 主表oid
     */
    private String parentOid;

    /**
     * 焊口编号
     */
    private String weldOid;

    /** 焊口编号 */
    private String weldCode;

    /**
     * 缺陷位置
     */
    private String defectPosition;

    /**
     * 缺陷性质，域值：defect_properties_domain
     */
    private String defectProperties;


    /** 缺陷性质阈值名称 */
    private String defectPropertiesName;

    /**
     * 缺陷长度(mm)
     */
    private Double defectLength;

    /**
     * 缺陷深度(mm)
     */
    private Double defectDepth;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getParentOid() {
        return parentOid;
    }

    public void setParentOid(String parentOid) {
        this.parentOid = parentOid;
    }

    public String getWeldOid() {
        return weldOid;
    }

    public void setWeldOid(String weldOid) {
        this.weldOid = weldOid;
    }

    public String getWeldCode() {
        return weldCode;
    }

    public void setWeldCode(String weldCode) {
        this.weldCode = weldCode;
    }

    public String getDefectPosition() {
        return defectPosition;
    }

    public void setDefectPosition(String defectPosition) {
        this.defectPosition = defectPosition;
    }

    public String getDefectProperties() {
        return defectProperties;
    }

    public void setDefectProperties(String defectProperties) {
        this.defectProperties = defectProperties;
    }

    public String getDefectPropertiesName() {
        return defectPropertiesName;
    }

    public void setDefectPropertiesName(String defectPropertiesName) {
        this.defectPropertiesName = defectPropertiesName;
    }

    public Double getDefectLength() {
        return defectLength;
    }

    public void setDefectLength(Double defectLength) {
        this.defectLength = defectLength;
    }

    public Double getDefectDepth() {
        return defectDepth;
    }

    public void setDefectDepth(Double defectDepth) {
        this.defectDepth = defectDepth;
    }
}
