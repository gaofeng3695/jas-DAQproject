package cn.jasgroup.jasframework.acquisitiondata.datavisualization.comm;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/10/22 14:03
 */
public class ScopeManagementBlock {

    private static final Map<String, String> SCOPE_MANAGEMENT_BLOCK;

    static {
        SCOPE_MANAGEMENT_BLOCK = ImmutableMap.<String, String>builder()
                .put("pipeline", "daq_pipeline") // 管线
                .put("median_stake", "daq_median_stake") // 中线桩
                .put("pipe_segment", "daq_pipe_segment") // 线路段

                .put("cross", "daq_cross") // 穿跨越
                .put("pipe_station", "daq_pipe_station") // 站场阀室
                .put("maintenance_road", "daq_maintenance_road") // 伴行道路

                .put("power_line", "daq_power_line") // 外供电线路
                .put("tenders", "daq_tenders") // 标段
                .build();
    }

    public static Map<String, String> getScopeManagementBlock() {
        return SCOPE_MANAGEMENT_BLOCK;
    }
}
