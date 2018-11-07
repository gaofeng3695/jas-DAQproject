package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * 日期类型统计BO
 * @author xiefayang
 * 2018/9/11 9:30
 */
public class DateStatsResultBo extends StatsResultBo {

    private Integer statsYear;

    private Integer statsMonth;

    private String statsDate;

    public DateStatsResultBo() {
    }

    public DateStatsResultBo(String statsType, String statsDate, Object statsResult) {
        super(statsType, statsResult);
        this.statsDate = statsDate;
    }

    public String getStatsDate() {
        return statsDate;
    }

    public void setStatsDate(String statsDate) {
        this.statsDate = statsDate;
    }

    public Integer getStatsMonth() {
        return statsMonth==null ? 0:statsMonth;
    }

    public void setStatsMonth(Integer statsMonth) {
        this.statsMonth = statsMonth;
    }

    public Integer getStatsYear() {
        return statsYear;
    }

    public void setStatsYear(Integer statsYear) {
        this.statsYear = statsYear;
    }
}
