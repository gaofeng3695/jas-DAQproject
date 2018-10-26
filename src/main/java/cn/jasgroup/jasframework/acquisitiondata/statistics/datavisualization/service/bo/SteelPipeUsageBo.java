package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo;

/**
 * description: 钢管使用情况统计BO
 *
 * @author xiefayang
 * 2018/10/25 14:59
 */
public class SteelPipeUsageBo {

    /** 统计类型 */
    private String statsType;

    private String cnName;

    /** 使用长度 */
    private Double usedLength;

    /** 总数量 */
    private Integer totalCount;

    /** 使用数量 */
    private Integer usedCount;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getStatsType() {
        return statsType;
    }

    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }

    public Double getUsedLength() {
        return usedLength;
    }

    public void setUsedLength(Double usedLength) {
        this.usedLength = usedLength;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getUsedCount() {
        return usedCount;
    }

    public void setUsedCount(Integer usedCount) {
        this.usedCount = usedCount;
    }
}
