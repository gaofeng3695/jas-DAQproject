package cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/12 16:33
 */
public class WeldInfoBo {

    private String oid;

    private String frontPipeType;

    private String frontPipeOid;

    private String backPipeType;

    private String backPipeOid;

    private Integer statsMonth;

    private String createDatetime;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getFrontPipeType() {
        return frontPipeType;
    }

    public void setFrontPipeType(String frontPipeType) {
        this.frontPipeType = frontPipeType;
    }

    public String getFrontPipeOid() {
        return frontPipeOid;
    }

    public void setFrontPipeOid(String frontPipeOid) {
        this.frontPipeOid = frontPipeOid;
    }

    public String getBackPipeType() {
        return backPipeType;
    }

    public void setBackPipeType(String backPipeType) {
        this.backPipeType = backPipeType;
    }

    public String getBackPipeOid() {
        return backPipeOid;
    }

    public void setBackPipeOid(String backPipeOid) {
        this.backPipeOid = backPipeOid;
    }

    public Integer getStatsMonth() {
        return statsMonth;
    }

    public void setStatsMonth(Integer statsMonth) {
        this.statsMonth = statsMonth;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }
}
