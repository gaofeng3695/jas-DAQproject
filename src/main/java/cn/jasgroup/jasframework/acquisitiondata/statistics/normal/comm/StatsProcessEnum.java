package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

/**
 * description: 工序类型枚举
 *
 * @author xiefayang
 * 2018/9/14 10:29
 */
public enum StatsProcessEnum {

    PIPE("pipe", "管材"),
    LAY_SURVEYING("lay_surveying", "测量放线"),
    WELD("weld", "焊接"),
    PATCH("patch", "补口"),
    LAY_PIPE_TRENCH_BACKFILL("lay_pipe_trench_backfill", "管沟回填"),
    LAY_LAND_RESTORATION("lay_land_restoration", "地貌恢复"),
    ;

    private String type;

    private String name;

    StatsProcessEnum(String type, String name) {
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
