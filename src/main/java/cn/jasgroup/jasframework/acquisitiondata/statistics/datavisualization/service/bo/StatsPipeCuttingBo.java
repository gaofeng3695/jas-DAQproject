package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo;

/**
 * description: 统计切管情况BO
 *
 * @author xiefayang
 * 2018/10/25 15:57
 */
public class StatsPipeCuttingBo {

    /** 被切次数 */
    private Integer cutCount;

    private Double totalLength;

    /** 被切长度 */
    private Double cutLength;

    /** 已用长度 */
    private Double usedLength;

    /** 未用长度 */
    private Double unUsedLength;

    public Double getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(Double totalLength) {
        this.totalLength = totalLength;
    }

    public Integer getCutCount() {
        return cutCount;
    }

    public void setCutCount(Integer cutCount) {
        this.cutCount = cutCount;
    }

    public Double getCutLength() {
        return cutLength;
    }

    public void setCutLength(Double cutLength) {
        this.cutLength = cutLength;
    }

    public Double getUsedLength() {
        return usedLength;
    }

    public void setUsedLength(Double usedLength) {
        this.usedLength = usedLength;
    }

    public Double getUnUsedLength() {
        return unUsedLength;
    }

    public void setUnUsedLength(Double unUsedLength) {
        this.unUsedLength = unUsedLength;
    }
}
