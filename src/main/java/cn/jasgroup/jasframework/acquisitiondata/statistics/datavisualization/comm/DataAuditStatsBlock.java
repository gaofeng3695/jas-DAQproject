package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 数据审核统计数据来源源 (Web数据可视化)
 * @author xiefayang
 */
public class DataAuditStatsBlock {

    /** 统计code */
    private String code;

    /** 统计表的中文名称 */
    private String cnName;

    /** 表名, 拼接sql用 */
    private String tableName;

    /** 分类code */
    private String categoryCode;


    /** 数据审核统计: 管道焊接/补口 */
    private static final Map<String, DataAuditStatsBlock> STATS_BLOCK;

    static {

        STATS_BLOCK = ImmutableMap.<String, DataAuditStatsBlock>builder()
                .put("construction_weld", new DataAuditStatsBlock("construction_weld", "焊口信息", "daq_construction_weld", "pipe_weld"))
                .put("weld_rework_weld", new DataAuditStatsBlock("weld_rework_weld", "焊口返修", "daq_weld_rework_weld", "pipe_weld"))
                .put("weld_measured_result", new DataAuditStatsBlock("weld_measured_result", "焊口测量", "daq_weld_measured_result", "pipe_weld"))
                .put("weld_anticorrosion_check", new DataAuditStatsBlock("weld_anticorrosion_check", "防腐补口检查", "daq_weld_anticorrosion_check", "pipe_weld"))

                .put("detection_ray", new DataAuditStatsBlock("detection_ray", "射线检测", "daq_detection_ray", "pipe_inspection"))
                .put("detection_ultrasonic", new DataAuditStatsBlock("detection_ultrasonic", "超声波检测", "daq_detection_ultrasonic", "pipe_inspection"))
                .put("detection_magnetic_powder", new DataAuditStatsBlock("detection_magnetic_powder", "磁粉检测", "daq_detection_magnetic_powder", "pipe_inspection"))
                .build();

    }


    public DataAuditStatsBlock() {
    }

    public DataAuditStatsBlock(String code, String cnName, String tableName, String categoryCode) {
        this.code = code;
        this.cnName = cnName;
        this.tableName = tableName;
        this.categoryCode = categoryCode;
    }

    public static Map<String, DataAuditStatsBlock> getStatsBlock() {
        return STATS_BLOCK;
    }

    public String getCode() {
        return code;
    }


    public String getCnName() {
        return cnName;
    }


    public String getTableName() {
        return tableName;
    }


    public String getCategoryCode() {
        return categoryCode;
    }


}
