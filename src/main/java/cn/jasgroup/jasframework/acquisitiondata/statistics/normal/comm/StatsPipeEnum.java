package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/12 9:52
 */
public enum StatsPipeEnum {

    STRAIGHT_STEEL_PIPE("pipe_type_code_001", "直钢管"),
    HOT_BEND("pipe_type_code_002", "热煨弯管"),
    COLD_BEND("pipe_type_code_008", "冷弯管"),
    ;

    private String code;

    private String name;

    StatsPipeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
