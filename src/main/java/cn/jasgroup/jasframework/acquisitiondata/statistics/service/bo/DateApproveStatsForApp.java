package cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo;

public class DateApproveStatsForApp {

    private String date;

    private Integer totalCount;

    private Integer auditedCount;

    public DateApproveStatsForApp() {
    }

    public DateApproveStatsForApp(String date, Integer totalCount, Integer auditedCount) {
        this.date = date;
        this.totalCount = totalCount;
        this.auditedCount = auditedCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
