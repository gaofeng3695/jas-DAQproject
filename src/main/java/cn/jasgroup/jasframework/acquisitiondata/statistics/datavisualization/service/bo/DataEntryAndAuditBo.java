package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo;

/**
 * 数据录入和审核DTO
 */
public class DataEntryAndAuditBo {

    private String statsType;

    private String cnName;

    private Integer totalCount;

    /** 审核通过数量 */
    private Integer auditPassedCount;

    /** 待审核数量 */
    private Integer waitAuditCount;

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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getAuditPassedCount() {
        return auditPassedCount;
    }

    public void setAuditPassedCount(Integer auditPassedCount) {
        this.auditPassedCount = auditPassedCount;
    }

    public Integer getWaitAuditCount() {
        return waitAuditCount;
    }

    public void setWaitAuditCount(Integer waitAuditCount) {
        this.waitAuditCount = waitAuditCount;
    }
}
