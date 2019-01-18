package cn.jasgroup.jasframework.acquisitiondata.station.detection.infiltration.query.bo;

import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>渗透检测(站场)子表bo</p>
 * @author cuixianing
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-14 10:22:29。</p>
 */
public class DaqStationDetectionInfiltrationSubBo extends CommonBaseBo {

    /**
     * oid
     */
    private String oid;
    /**
     * 主表oid
     */
    private String parentOid;

    /**
     * 焊口oid
     */
    private String weldOid;

    /**
     * 焊口编号
     */
    private String weldCode;

    /**
     * 缺陷位置
     */
    private String defectPosition;

    /**
     * 缺陷性质，域值：defect_properties_domain
     */
    private String defectProperties;

    /**
     * 缺陷性质名称
     */
    private String defectPropertiesName;

    /**
     * 缺陷长度(mm)
     */
    private Double defectLength;

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

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}