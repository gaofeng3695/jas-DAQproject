package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * description: 数据录入/审核情况统计
 *
 * @author xiefayang
 * 2018/9/11 11:26
 */
public class DataEntryAuditBo {

    private Integer total;

    private Integer needAudit;

    private Integer audited;

    public DataEntryAuditBo() {
    }

    public DataEntryAuditBo(Integer total, Integer needAudit, Integer audited) {
        this.total = total;
        this.needAudit = needAudit;
        this.audited = audited;
    }

    public Integer getTotal() {
        return total==null ? 0:total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getNeedAudit() {
        return needAudit==null ? 0:needAudit;
    }

    public void setNeedAudit(Integer needAudit) {
        this.needAudit = needAudit;
    }

    public Integer getAudited() {
        return audited==null ? 0:audited;
    }

    public void setAudited(Integer audited) {
        this.audited = audited;
    }
}
