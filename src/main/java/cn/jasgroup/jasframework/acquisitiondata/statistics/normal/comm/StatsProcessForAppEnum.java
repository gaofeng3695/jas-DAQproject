package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

/**
 * description: 工序类型枚举
 *
 * @author xiefayang
 * 2018/9/14 10:29
 */
public enum StatsProcessForAppEnum {

    PIPE("pipe", "管材"),
    WELD("weld", "焊接"),
    PATCH("patch", "补口"),
    LAY_PIPE_TRENCH_BACKFILL("lay_pipe_trench_backfill", "管沟回填"),
    ;

    private String type;

    private String name;

    StatsProcessForAppEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
