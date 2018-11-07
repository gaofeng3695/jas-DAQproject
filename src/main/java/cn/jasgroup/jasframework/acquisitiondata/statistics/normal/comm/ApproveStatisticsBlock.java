package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;

/**
 * description: 数据审核相关统计不可变集合常量Block;
 *
 * @author xiefayang
 * 2018/8/29 14:54
 */
public class ApproveStatisticsBlock {

    /** 统计code */
    private String code;

    /** 统计表的中文名称 */
    private String cnName;

    /** 表名, 拼接sql用 */
    private String tableName;

    /** 分类code */
    private String categoryCode;

    /** 数据审核统计分类code与中文名 */
    public static final Map<String, String> APPROVE_CATEGORY;

    public static final Map<String, Map<String, ApproveStatisticsBlock>> APPROVE_CATEGORY_NON_DETECTION;

    public static final Map<String, Map<String, ApproveStatisticsBlock>> APPROVE_CATEGORY_DETECTION;

    /** 数据审核统计: 管道焊接/补口 */
    private static final Map<String, ApproveStatisticsBlock> PIPE_WELD_BLOCK;

    /** 数据审核统计: 管道检测 */
    public static final Map<String, ApproveStatisticsBlock> PIPE_INSPECTION_BLOCK;

    /** 数据审核统计: 管道敷设 */
    private static final Map<String, ApproveStatisticsBlock> PIPE_LAY_BLOCK;

    /** 数据审核统计: 管道穿跨越 */
    private static final Map<String, ApproveStatisticsBlock> PIPE_CROSS_BLOCK;

    /** 数据审核统计: 管道阴保 */
    private static final Map<String, ApproveStatisticsBlock> PIPE_CATHODE_PROTECTION_BLOCK;

    /** 数据审核统计: 附属物 */
    private static final Map<String, ApproveStatisticsBlock> APPENDAGE_BLOCK;

    /** all */
    public static final Map<String, ApproveStatisticsBlock> ALL;

    /** 非检测类的统计 */
    public static final Map<String, ApproveStatisticsBlock> NON_DETECTION;


    static {
        APPROVE_CATEGORY = ImmutableMap.<String, String>builder()
                .put("pipe_weld", "管道焊接/补口").put("pipe_inspection", "管道检测")
                .put("pipe_lay", "管道敷设").put("pipe_cross", "管道穿跨越")
                .put("pipe_cathode_protection", "管道阴保").put("appendage", "附属物")
                .build();




        PIPE_WELD_BLOCK = ImmutableMap.<String, ApproveStatisticsBlock>builder()
                .put("material_pipe_cold_bending", new ApproveStatisticsBlock("material_pipe_cold_bending", "冷弯信息", "daq_material_pipe_cold_bending", "pipe_weld"))
                .put("construction_weld", new ApproveStatisticsBlock("construction_weld", "焊口信息记录", "daq_construction_weld", "pipe_weld"))
                .put("weld_rework_weld", new ApproveStatisticsBlock("weld_rework_weld", "焊口返修信息记录", "daq_weld_rework_weld", "pipe_weld"))
                .put("weld_cut", new ApproveStatisticsBlock("weld_cut", "焊口割口信息记录", "daq_weld_cut", "pipe_weld"))
                .put("cut_pipe", new ApproveStatisticsBlock("cut_pipe", "钢管切管信息记录", "daq_cut_pipe", "pipe_weld"))
                .put("weld_anticorrosion_check", new ApproveStatisticsBlock("weld_anticorrosion_check", "防腐补口检查信息记录", "daq_weld_anticorrosion_check", "pipe_weld"))
                .put("weld_anticorrosion_test", new ApproveStatisticsBlock("weld_anticorrosion_test", "防腐补口剥离强度试验", "daq_weld_anticorrosion_test", "pipe_weld"))
                .put("weld_anticorrosion_repair", new ApproveStatisticsBlock("weld_anticorrosion_repair", "防腐补伤信息记录", "daq_weld_anticorrosion_repair", "pipe_weld"))
                .put("weld_measured_result", new ApproveStatisticsBlock("weld_measured_result", "焊口测量成果信息记录", "daq_weld_measured_result", "pipe_weld"))
                .build();

        PIPE_INSPECTION_BLOCK = ImmutableMap.<String, ApproveStatisticsBlock>builder()
                .put("detection_ray", new ApproveStatisticsBlock("detection_ray", "射线检测", "daq_detection_ray", "pipe_inspection"))
                .put("detection_ultrasonic", new ApproveStatisticsBlock("detection_ultrasonic", "超声波检测", "daq_detection_ultrasonic", "pipe_inspection"))
                .put("detection_magnetic_powder", new ApproveStatisticsBlock("detection_magnetic_powder", "磁粉检测", "daq_detection_magnetic_powder", "pipe_inspection"))
                .put("detection_infiltration", new ApproveStatisticsBlock("detection_infiltration", "渗透检测", "daq_detection_infiltration", "pipe_inspection"))
                .put("detection_pa_ultrasonic", new ApproveStatisticsBlock("detection_pa_ultrasonic", "相控阵超声波检测", "daq_detection_pa_ultrasonic", "pipe_inspection"))
                .put("detection_fa_ultrasonic", new ApproveStatisticsBlock("detection_fa_ultrasonic", "全自动超声波检测", "daq_detection_fa_ultrasonic", "pipe_inspection"))
                .build();

        PIPE_LAY_BLOCK = ImmutableMap.<String, ApproveStatisticsBlock>builder()
                .put("lay_surveying", new ApproveStatisticsBlock("lay_surveying", "测量放线", "daq_lay_surveying", "pipe_lay"))
                .put("lay_pipe_trench_excavation", new ApproveStatisticsBlock("lay_pipe_trench_excavation", "管沟开挖", "daq_lay_pipe_trench_excavation", "pipe_lay"))
                .put("lay_pipe_trench_backfill", new ApproveStatisticsBlock("lay_pipe_trench_backfill", "管沟回填", "daq_lay_pipe_trench_backfill", "pipe_lay"))
                .put("lay_land_restoration", new ApproveStatisticsBlock("lay_land_restoration", "地貌恢复验收", "daq_lay_land_restoration", "pipe_lay"))
                .put("lay_thermal_insulation", new ApproveStatisticsBlock("lay_thermal_insulation", "保温", "daq_lay_thermal_insulation", "pipe_lay"))
                .build();

        PIPE_CROSS_BLOCK = ImmutableMap.<String, ApproveStatisticsBlock>builder()
                .put("cross_excavation", new ApproveStatisticsBlock("cross_excavation", "开挖穿越", "daq_cross_excavation", "pipe_cross"))
                .put("cross_pipe_jacking", new ApproveStatisticsBlock("cross_pipe_jacking", "顶管穿越", "daq_cross_pipe_jacking", "pipe_cross"))
                .put("cross_box_culvert", new ApproveStatisticsBlock("cross_box_culvert", "箱涵穿越", "daq_cross_box_culvert", "pipe_cross"))
                .put("cross_drilling", new ApproveStatisticsBlock("cross_drilling", "定向钻穿越", "daq_cross_drilling", "pipe_cross"))
                .put("cross_drilling_blasting", new ApproveStatisticsBlock("cross_drilling_blasting", "钻爆隧道穿越", "daq_cross_drilling_blasting", "pipe_cross"))

                .put("cross_across", new ApproveStatisticsBlock("cross_across", "跨越", "daq_cross_across", "pipe_cross"))
                .put("cross_shield", new ApproveStatisticsBlock("cross_shield", "盾构隧道穿越", "daq_cross_shield", "pipe_cross"))
                .build();

        PIPE_CATHODE_PROTECTION_BLOCK = ImmutableMap.<String, ApproveStatisticsBlock>builder()
                .put("cathodic_isolating_piece", new ApproveStatisticsBlock("cathodic_isolating_piece", "绝缘件", "daq_cathodic_isolating_piece", "pipe_cathode_protection"))
                .put("cathodic_cable_protection", new ApproveStatisticsBlock("cathodic_cable_protection", "阴极保护电缆", "daq_cathodic_cable_protection", "pipe_cathode_protection"))
                .put("cathodic_sacrifice_anode", new ApproveStatisticsBlock("cathodic_sacrifice_anode", "牺牲阳极", "daq_cathodic_sacrifice_anode", "pipe_cathode_protection"))
                .put("cathodic_insulated_joint", new ApproveStatisticsBlock("cathodic_insulated_joint", "绝缘接头保护器", "daq_cathodic_insulated_joint", "pipe_cathode_protection"))
                .put("cathodic_solid_decoupler", new ApproveStatisticsBlock("cathodic_solid_decoupler", "固态去耦合器", "daq_cathodic_solid_decoupler", "pipe_cathode_protection"))

                .put("cathodic_test_stake", new ApproveStatisticsBlock("cathodic_test_stake", "阴极保护测试桩", "daq_cathodic_test_stake", "pipe_cathode_protection"))
                .put("cathodic_polarity_drainage", new ApproveStatisticsBlock("cathodic_polarity_drainage", "极性排流器", "daq_cathodic_polarity_drainage", "pipe_cathode_protection"))
                .put("cathodic_anode_bed", new ApproveStatisticsBlock("cathodic_anode_bed", "辅助阳极地床", "daq_cathodic_anode_bed", "pipe_cathode_protection"))
                .put("cathodic_electrical_parameter_test", new ApproveStatisticsBlock("cathodic_electrical_parameter_test", "牺牲阳极电参数测试信息记录", "daq_cathodic_electrical_parameter_test", "pipe_cathode_protection"))
                .put("cathodic_impressed_current_test", new ApproveStatisticsBlock("cathodic_impressed_current_test", "外加电流电参数测试信息记录", "daq_cathodic_impressed_current_test", "pipe_cathode_protection"))
                .build();


        APPENDAGE_BLOCK = ImmutableMap.<String, ApproveStatisticsBlock>builder()
                .put("appendages_mark_stake", new ApproveStatisticsBlock("appendages_mark_stake", "标志桩安装检查信息记录", "daq_appendages_mark_stake", "appendage"))
                .put("appendages_electronic_label", new ApproveStatisticsBlock("appendages_electronic_label", "电子标签信息记录", "daq_appendages_electronic_label", "appendage"))
                .put("appendages_hand_hole", new ApproveStatisticsBlock("appendages_hand_hole", "通信光缆人（手）孔位置信息记录", "daq_appendages_hand_hole", "appendage"))
                .put("appendages_obstacle", new ApproveStatisticsBlock("appendages_obstacle", "管道沿线地下障碍物信息记录", "daq_appendages_obstacle", "appendage"))
                .put("appendages_hydraulic_protection", new ApproveStatisticsBlock("appendages_hydraulic_protection", "管道沿线水工保护信息记录", "daq_appendages_hydraulic_protection", "appendage"))

                .put("appendages_concomitant_road", new ApproveStatisticsBlock("appendages_concomitant_road", "伴行路", "daq_appendages_concomitant_road", "appendage"))
                .put("appendages_casing_pipe", new ApproveStatisticsBlock("appendages_casing_pipe", "套管", "daq_appendages_casing_pipe", "appendage"))
                .build();

        ALL = ImmutableMap.copyOf(ImmutableSet.<Map.Entry<String, ApproveStatisticsBlock>>builder()
                .addAll(PIPE_WELD_BLOCK.entrySet())
                .addAll(PIPE_INSPECTION_BLOCK.entrySet())
                .addAll(PIPE_LAY_BLOCK.entrySet())
                .addAll(PIPE_CROSS_BLOCK.entrySet())
                .addAll(PIPE_CATHODE_PROTECTION_BLOCK.entrySet())
                .addAll(APPENDAGE_BLOCK.entrySet())
                .build());

        NON_DETECTION = ImmutableMap.copyOf(ImmutableSet.<Map.Entry<String, ApproveStatisticsBlock>>builder()
                .addAll(PIPE_WELD_BLOCK.entrySet())
                .addAll(PIPE_LAY_BLOCK.entrySet())
                .addAll(PIPE_CROSS_BLOCK.entrySet())
                .addAll(PIPE_CATHODE_PROTECTION_BLOCK.entrySet())
                .addAll(APPENDAGE_BLOCK.entrySet())
                .build());

        APPROVE_CATEGORY_NON_DETECTION = ImmutableMap.<String, Map<String, ApproveStatisticsBlock>>builder()
                .put("pipe_weld", PIPE_WELD_BLOCK).put("pipe_lay", PIPE_LAY_BLOCK).put("pipe_cross", PIPE_CROSS_BLOCK)
                .put("pipe_cathode_protection", PIPE_CATHODE_PROTECTION_BLOCK).put("appendage", APPENDAGE_BLOCK)
                .build();

        APPROVE_CATEGORY_DETECTION = ImmutableMap.of("pipe_inspection", PIPE_INSPECTION_BLOCK);

    }


    public ApproveStatisticsBlock() {
    }

    public ApproveStatisticsBlock(String code, String cnName, String tableName, String categoryCode) {
        this.code = code;
        this.cnName = cnName;
        this.tableName = tableName;
        this.categoryCode = categoryCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

}
