package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

/**
 * description: 审核状态枚举
 *
 * @author xiefayang
 * 2018/8/28 14:08
 */
public enum ApproveStatusEnum {

    REJECT(-1, "驳回"),
    UNREPORTED(0, "未上报"),
    WAIT_AUDITING(1, "待审核"),
    PASSED(2, "审核通过")
    ;

    private Integer code;

    private String message;


    ApproveStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
