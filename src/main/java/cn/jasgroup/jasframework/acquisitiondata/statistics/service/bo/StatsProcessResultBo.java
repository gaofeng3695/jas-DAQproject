package cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo;

/**
 * description: 统计结果BO
 *
 * @author xiefayang
 * 2018/8/28 15:34
 */
public class StatsProcessResultBo {

    /** 统计类型 */
    private String statsType;

    private String constructName;

    /** 统计数量 */
    private Integer statsCount;

    /** 统计结果(长度) */
    private Object statsLength;

    public StatsProcessResultBo() {
    }

    public StatsProcessResultBo(String statsType, Integer statsCount, Object statsLength) {
        this.statsType = statsType;
        this.statsCount = statsCount;
        this.statsLength = statsLength;
    }

    public String getConstructName() {
        return constructName;
    }

    public void setConstructName(String constructName) {
        this.constructName = constructName;
    }

    public String getStatsType() {
        return statsType;
    }

    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }

    public Integer getStatsCount() {
        return statsCount;
    }

    public void setStatsCount(Integer statsCount) {
        this.statsCount = statsCount;
    }

    public Object getStatsLength() {
        return statsLength;
    }

    public void setStatsLength(Object statsLength) {
        this.statsLength = statsLength;
    }
}
