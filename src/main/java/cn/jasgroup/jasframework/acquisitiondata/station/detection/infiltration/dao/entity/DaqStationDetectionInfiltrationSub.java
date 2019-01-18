package cn.jasgroup.jasframework.acquisitiondata.station.detection.infiltration.dao.entity;

import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * <p>渗透检测(站场)子表</p>
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-14 10:22:29。</p>
 */
@JdbcEntity(name="daq_station_detection_infiltration_sub")
public class DaqStationDetectionInfiltrationSub extends CommonJdbcEntity {

    /**
     * 主表oid
     */
    private String parentOid;

    /**
     * 焊口编号
     */
    private String weldOid;

    /**
     * 缺陷位置
     */
    private String defectPosition;

    /**
     * 缺陷性质，域值：defect_properties_domain
     */
    private String defectProperties;

    /**
     * 缺陷长度(mm)
     */
    private Double defectLength;

    public String getParentOid() {
        return parentOid;
    }

    public void setParentOid(String parentOid) {
        this.parentOid = parentOid;
        super.setField("parentOid");
    }

    public String getWeldOid() {
        return weldOid;
    }

    public void setWeldOid(String weldOid) {
        this.weldOid = weldOid;
        super.setField("weldOid");
    }

    public String getDefectPosition() {
        return defectPosition;
    }

    public void setDefectPosition(String defectPosition) {
        this.defectPosition = defectPosition;
        super.setField("defectPosition");
    }

    public String getDefectProperties() {
        return defectProperties;
    }

    public void setDefectProperties(String defectProperties) {
        this.defectProperties = defectProperties;
        super.setField("defectProperties");
    }

    public Double getDefectLength() {
        return defectLength;
    }

    public void setDefectLength(Double defectLength) {
        this.defectLength = defectLength;
        super.setField("defectLength");
    }

}