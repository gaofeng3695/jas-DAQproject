package cn.jasgroup.jasframework.acquisitiondata.datavisualization.service.bo;

/**
 * description: 物资统计数量DTO
 *
 * @author xiefayang
 * 2018/10/22 17:23
 */
public class MaterialStatsResultBo {

    /** 统计类型 */
    private String statsType;

    private String cnName;

    /** 录入数量 */
    private Integer entryCount;

    /** 已检查数量 */
    private Integer checkedCount;

    /** 已使用数量 */
    private Integer usedCount;

    /** 已检查未使用数量 */
    private Integer checkedUnusedCount;

    /** 未检查已使用数量 */
    private Integer uncheckedUsedCount;

    public String getStatsType() {
        return statsType;
    }

    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }

    public Integer getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Integer entryCount) {
        this.entryCount = entryCount;
    }

    public Integer getCheckedCount() {
        return checkedCount;
    }

    public void setCheckedCount(Integer checkedCount) {
        this.checkedCount = checkedCount;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }

    public Integer getCheckedUnusedCount() {
        return checkedUnusedCount;
    }

    public void setCheckedUnusedCount(Integer checkedUnusedCount) {
        this.checkedUnusedCount = checkedUnusedCount;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Integer getUncheckedUsedCount() {
        return uncheckedUsedCount;
    }

    public void setUncheckedUsedCount(Integer uncheckedUsedCount) {
        this.uncheckedUsedCount = uncheckedUsedCount;
    }
}
