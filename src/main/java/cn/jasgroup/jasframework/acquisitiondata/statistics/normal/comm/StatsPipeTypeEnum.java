package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description: 管材长度统计中管材的类型
 * - 各工序累计完成情况
 * - 各工序分月累计完成情况对比
 *
 * @author xiefayang
 * 2018/9/12 9:52
 */
public enum StatsPipeTypeEnum {



    STRAIGHT_STEEL_PIPE("pipe_type_code_001", "直钢管"),
    HOT_BEND("pipe_type_code_002", "热煨弯管"),
    COLD_BEND("pipe_type_code_008", "冷弯管"),
    ;

    private String code;

    private String name;

    public static final List<String> TYPE_LIST = Arrays.stream(StatsPipeTypeEnum.values()).map(StatsPipeTypeEnum::getCode).collect(Collectors.toList());

    StatsPipeTypeEnum(String code, String name) {
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
