package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 管材长度统计中管材的类型(后管件)
 * - 各工序累计完成情况
 * - 各工序分月累计完成情况对比
 *
 * @author xiefayang
 * 2018/9/12 9:52
 */
public enum StatsBackPipeTypeEnum {

    STRAIGHT_STEEL_PIPE("pipe_type_code_0011", "直钢管"),
    HOT_BEND("pipe_type_code_0021", "热煨弯管"),
    COLD_BEND("pipe_type_code_0081", "冷弯管"),
    ;

    public static final List<String> TYPE_LIST = Arrays.stream(StatsBackPipeTypeEnum.values()).map(StatsBackPipeTypeEnum::getCode).collect(Collectors.toList());

    private String code;

    private String name;

    StatsBackPipeTypeEnum(String code, String name) {
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
