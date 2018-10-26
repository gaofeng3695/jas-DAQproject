package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * description: 数据录入相关统计不可变集合常量Block;
 *
 * @author xiefayang
 * 2018/8/28 14:17
 */
public class EntryStatisticsBlock {

    /** 数据录入统计: 没有审核操作的 */
    private static final Map<String, String> PIPE_CHECKED_BLOCK;

    /** 数据录入统计: 有审核操作的 */
    private static final Map<String, String> WELD_APPROVE_BLOCK;

    static {

        PIPE_CHECKED_BLOCK = ImmutableMap.<String, String>builder()
                .put("coating_pipe", "daq_check_coating_pipe") // 防腐管检查
                .put("hot_bends", "daq_check_hot_bends") // 热煨弯管检查
                .put("pipe_cold_bending", "daq_check_pipe_cold_bending") // 冷弯管检查
                .build();

        WELD_APPROVE_BLOCK = ImmutableMap.<String, String>builder()
                .put("construction_weld", "daq_construction_weld") // 焊口记录
                .put("rework_weld", "daq_weld_rework_weld") // 焊口返修
                .put("measured_result", "daq_weld_measured_result") // 焊口测量
                .put("anticorrosion_check", "daq_weld_anticorrosion_check") // 补口记录
                .build();
    }


    public static Map<String, String> getPipeCheckedBlock() {
        return PIPE_CHECKED_BLOCK;
    }

    public static Map<String, String> getWeldApproveBlock() {
        return WELD_APPROVE_BLOCK;
    }

}
