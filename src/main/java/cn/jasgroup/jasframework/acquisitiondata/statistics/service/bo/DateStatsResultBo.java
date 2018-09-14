package cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo;

/**
 * 日期类型统计BO
 */
public class DateStatsResultBo extends StatsResultBo {

    private Integer statsMonth;

    public DateStatsResultBo() {
    }

    public DateStatsResultBo(String statsType, Object statsResult, Integer statsMonth) {
        super(statsType, statsResult);
        this.statsMonth = statsMonth;
    }

    public Integer getStatsMonth() {
        return statsMonth;
    }

    public void setStatsMonth(Integer statsMonth) {
        this.statsMonth = statsMonth;
    }
}
