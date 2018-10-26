package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

/**
 * description: 月份
 *
 * @author xiefayang
 * 2018/9/12 9:52
 */
public enum MonthlyEnum {

    JAN(1, "一月"),
    FEB(2, "二月"),
    MAR(3, "三月"),
    APR(4, "四月"),
    MAY(5, "五月"),
    JUNE(6, "六月"),

    JULY(7, "七月"),
    AUG(8, "八月"),
    SEPT(9, "九月"),
    OCT(10, "十月"),
    NOV(11, "十一月"),
    DEC(12, "十二月"),
    ;

    private Integer month;

    private String cnName;

    MonthlyEnum(Integer month, String cnName) {
        this.month = month;
        this.cnName = cnName;
    }

    public Integer getMonth() {
        return month;
    }

    public String getCnName() {
        return cnName;
    }
}
