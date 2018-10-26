package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * description: 统计结果BO
 *
 * @author xiefayang
 * 2018/8/28 15:34
 */
public class StatsProcessResultBo extends StatsResultBo {

    private String constructId;

    private String constructName;

    /** 统计数量 */
    private Integer statsCount;

    public StatsProcessResultBo() {
    }

    public StatsProcessResultBo(String statsType, Integer statsCount, Object statsResult) {
        super(statsType, statsResult);
        this.statsCount = statsCount;
    }

    public String getConstructId() {
        return constructId;
    }

    public void setConstructId(String constructId) {
        this.constructId = constructId;
    }

    public String getConstructName() {
        return constructName;
    }

    public void setConstructName(String constructName) {
        this.constructName = constructName;
    }

    public Integer getStatsCount() {
        return statsCount;
    }

    public void setStatsCount(Integer statsCount) {
        this.statsCount = statsCount;
    }
}
