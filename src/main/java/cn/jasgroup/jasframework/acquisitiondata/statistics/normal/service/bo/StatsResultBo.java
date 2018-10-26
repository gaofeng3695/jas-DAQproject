package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * description: 统计结果BO
 *
 * @author xiefayang
 * 2018/8/28 15:34
 */
public class StatsResultBo {

    private String statsType;

    private Object statsResult;

    public StatsResultBo() {
    }

    public StatsResultBo(String statsType, Object statsResult) {
        this.statsType = statsType;
        this.statsResult = statsResult;
    }

    public String getStatsType() {
        return statsType;
    }

    public void setStatsType(String statsType) {
        this.statsType = statsType;
    }

    public Object getStatsResult() {
        return statsResult;
    }

    public void setStatsResult(Object statsResult) {
        this.statsResult = statsResult;
    }
}
