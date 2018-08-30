package cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/8/28 15:34
 */
public class StatisticsResultBo {

    private String oid;

    private String statisType;

    private Long statisResult;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getStatisType() {
        return statisType;
    }

    public void setStatisType(String statisType) {
        this.statisType = statisType;
    }

    public Long getStatisResult() {
        return statisResult;
    }

    public void setStatisResult(Long statisResult) {
        this.statisResult = statisResult;
    }
}
