package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * description: 数据录入统计的BO
 *
 * @author xiefayang
 * 2018/8/28 14:43
 */
public class DataEntryStatsBo {

    /** 统计类型 */
    private String statisType;

    /** 已录入 */
    private Long enteredCount;

    /** 待提交 */
    private Long toSubmitCount;

    /** 打回 */
    private Long repulseCount;

    public DataEntryStatsBo() {
    }

    public DataEntryStatsBo(String statisType, Long enteredCount) {
        this.statisType = statisType;
        this.enteredCount = enteredCount;
    }

    public DataEntryStatsBo(String statisType, Long enteredCount, Long toSubmitCount, Long repulseCount) {
        this.statisType = statisType;
        this.enteredCount = enteredCount;
        this.toSubmitCount = toSubmitCount;
        this.repulseCount = repulseCount;
    }

    public String getStatisType() {
        return statisType;
    }

    public void setStatisType(String statisType) {
        this.statisType = statisType;
    }

    public Long getEnteredCount() {
        return enteredCount;
    }

    public void setEnteredCount(Long enteredCount) {
        this.enteredCount = enteredCount;
    }

    public Long getToSubmitCount() {
        return toSubmitCount;
    }

    public void setToSubmitCount(Long toSubmitCount) {
        this.toSubmitCount = toSubmitCount;
    }

    public Long getRepulseCount() {
        return repulseCount;
    }

    public void setRepulseCount(Long repulseCount) {
        this.repulseCount = repulseCount;
    }
}
