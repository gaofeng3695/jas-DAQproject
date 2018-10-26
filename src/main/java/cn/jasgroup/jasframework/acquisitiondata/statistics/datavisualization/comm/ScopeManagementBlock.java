package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * description: 范围管理统计类型
 *
 * @author xiefayang
 * 2018/10/22 14:03
 */
public class ScopeManagementBlock {

    private String tableName;

    private String cnName;

    private static final Map<String, ScopeManagementBlock> SCOPE_MANAGEMENT_BLOCK;

    public static final Map<String, String> MAP = ImmutableMap.of("pipe_station", "station_classification_code01", "pipe_valve_room", "station_classification_code02");

    static {
        SCOPE_MANAGEMENT_BLOCK = ImmutableMap.<String, ScopeManagementBlock>builder()
                .put("pipeline", new ScopeManagementBlock("daq_pipeline", "管线"))
                .put("median_stake", new ScopeManagementBlock("daq_median_stake", "中线桩"))
                .put("pipe_segment", new ScopeManagementBlock("daq_pipe_segment", "线路段"))

                .put("cross", new ScopeManagementBlock("daq_cross", "穿跨越"))
                .put("pipe_station", new ScopeManagementBlock("daq_pipe_station", "站场"))
                .put("pipe_valve_room", new ScopeManagementBlock("daq_pipe_station", "阀室"))

                .put("maintenance_road", new ScopeManagementBlock("daq_maintenance_road", "伴行道路"))
                .put("power_line", new ScopeManagementBlock("daq_power_line", "外供电线路"))
                .put("tenders", new ScopeManagementBlock("daq_tenders", "标段"))
                .build();
    }

    public static Map<String, ScopeManagementBlock> getScopeManagementBlock() {
        return SCOPE_MANAGEMENT_BLOCK;
    }

    public ScopeManagementBlock(String tableName, String cnName) {
        this.tableName = tableName;
        this.cnName = cnName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getCnName() {
        return cnName;
    }
}
