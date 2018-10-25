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
    private Double entryCountOrLength;

    /** 已检查数量 */
    private Double checkedCountOrLength;

    /** 已使用数量 */
    private Double usedCountOrLength;

    /** 已检查未使用数量 */
    private Double checkedUnusedCountOrLength;

    /** 未检查已使用数量 */
    private Double uncheckedUsedCountOrLength;

    public String getStatsType() {
        return statsType;
    }

    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Double getEntryCountOrLength() {
        return entryCountOrLength;
    }

    public void setEntryCountOrLength(Double entryCountOrLength) {
        this.entryCountOrLength = entryCountOrLength;
    }

    public Double getCheckedCountOrLength() {
        return checkedCountOrLength;
    }

    public void setCheckedCountOrLength(Double checkedCountOrLength) {
        this.checkedCountOrLength = checkedCountOrLength;
    }

    public Double getUsedCountOrLength() {
        return usedCountOrLength;
    }

    public void setUsedCountOrLength(Double usedCountOrLength) {
        this.usedCountOrLength = usedCountOrLength;
    }

    public Double getCheckedUnusedCountOrLength() {
        return checkedUnusedCountOrLength;
    }

    public void setCheckedUnusedCountOrLength(Double checkedUnusedCountOrLength) {
        this.checkedUnusedCountOrLength = checkedUnusedCountOrLength;
    }

    public Double getUncheckedUsedCountOrLength() {
        return uncheckedUsedCountOrLength;
    }

    public void setUncheckedUsedCountOrLength(Double uncheckedUsedCountOrLength) {
        this.uncheckedUsedCountOrLength = uncheckedUsedCountOrLength;
    }
}
