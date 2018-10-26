package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * description: 焊口检测信息
 *
 * @author xiefayang
 * 2018/9/18 14:31
 */
public class WeldCheckInfoBo {

    /** 施工单位ID */
    private String unitId;

    /** 施工单位名称 */
    private String unitName;

    /** 焊接数量 */
    private Integer weldCount;

    /** 已检测数量 */
    private Integer checkedCount;

    /** 未检测数量 */
    private Integer uncheckedCount;

    /** 射线检测焊口数 */
    private Integer detectionRayCount;

    /** 合格数量 */
    private Integer qualifiedCount;

    private Integer onceQualifiedCount;

    /** 一次合格数 */
    private String onceQualifiedRate;

    public WeldCheckInfoBo() {
    }

    public WeldCheckInfoBo(String unitId, String unitName, Integer weldCount, Integer checkedCount, Integer uncheckedCount, Integer qualifiedCount, Integer onceQualifiedCount, String onceQualifiedRate) {
        this.unitId = unitId;
        this.unitName = unitName;
        this.weldCount = weldCount;
        this.checkedCount = checkedCount;
        this.uncheckedCount = uncheckedCount;
        this.qualifiedCount = qualifiedCount;
        this.onceQualifiedCount= onceQualifiedCount;
        this.onceQualifiedRate = onceQualifiedRate;
    }

    public Integer getOnceQualifiedCount() {
        return onceQualifiedCount;
    }

    public void setOnceQualifiedCount(Integer onceQualifiedCount) {
        this.onceQualifiedCount = onceQualifiedCount;
    }

    public Integer getDetectionRayCount() {
        return detectionRayCount;
    }

    public void setDetectionRayCount(Integer detectionRayCount) {
        this.detectionRayCount = detectionRayCount;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getWeldCount() {
        return weldCount;
    }

    public void setWeldCount(Integer weldCount) {
        this.weldCount = weldCount;
    }

    public Integer getCheckedCount() {
        return checkedCount;
    }

    public void setCheckedCount(Integer checkedCount) {
        this.checkedCount = checkedCount;
    }

    public Integer getUncheckedCount() {
        return uncheckedCount;
    }

    public void setUncheckedCount(Integer uncheckedCount) {
        this.uncheckedCount = uncheckedCount;
    }

    public Integer getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(Integer qualifiedCount) {
        this.qualifiedCount = qualifiedCount;
    }

    public String getOnceQualifiedRate() {
        return onceQualifiedRate;
    }

    public void setOnceQualifiedRate(String onceQualifiedRate) {
        this.onceQualifiedRate = onceQualifiedRate;
    }
}
