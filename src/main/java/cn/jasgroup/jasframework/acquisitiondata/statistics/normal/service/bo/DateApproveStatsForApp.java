package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

public class DateApproveStatsForApp {

    private String statsDate;

    private Integer totalCount;

    private Integer auditedCount;

    public DateApproveStatsForApp() {
    }

    public DateApproveStatsForApp(String date, Integer totalCount, Integer auditedCount) {
        this.statsDate = date;
        this.totalCount = totalCount;
        this.auditedCount = auditedCount;
    }

    public String getStatsDate() {
        return statsDate;
    }

    public void setStatsDate(String statsDate) {
        this.statsDate = statsDate;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAuditedCount() {
        return auditedCount;
    }

    public void setAuditedCount(Integer auditedCount) {
        this.auditedCount = auditedCount;
    }
}
