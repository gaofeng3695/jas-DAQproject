package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class MaterialStatisticsBlock {
	/** 统计code */
    private String code;

    /** 统计表的中文名称 */
    private String cnName;

    /** 表名, 拼接sql用 */
    private String tableName;

    /** 分类code */
    private String categoryCode;
    
    /** 数据审核统计: 线路物资 */
    private static final Map<String, MaterialStatisticsBlock> MATERIAL_BLOCK;
    /** 数据审核统计: 线路物资检查 */
    private static final Map<String, MaterialStatisticsBlock> MATERIAL_CHECK_BLOCK;
    /** all */
    public static final Map<String, MaterialStatisticsBlock> ALL;
    static{
    	MATERIAL_BLOCK = ImmutableMap.<String, MaterialStatisticsBlock>builder()
                .put("material_pipe", new MaterialStatisticsBlock("material_pipe", "钢管信息", "daq_material_pipe", "pipe_weld"))
                .put("material_hot_bends", new MaterialStatisticsBlock("material_hot_bends", "热煨弯管", "daq_material_hot_bends", "pipe_weld"))
                .put("material_tee", new MaterialStatisticsBlock("material_tee", "三通", "daq_material_tee", "pipe_weld"))
                .put("material_insulated_joint", new MaterialStatisticsBlock("material_insulated_joint", "绝缘接头信息", "daq_material_insulated_joint", "pipe_weld"))
                .put("material_reducer", new MaterialStatisticsBlock("material_reducer", "大小头信息", "daq_material_reducer", "pipe_weld"))
                .put("material_closure", new MaterialStatisticsBlock("material_closure", "封堵物信息记录", "daq_material_closure", "pipe_weld"))
                .put("material_flange", new MaterialStatisticsBlock("material_flange", "法兰信息", "daq_material_flange", "pipe_weld"))
                .build();
    	MATERIAL_CHECK_BLOCK = ImmutableMap.<String, MaterialStatisticsBlock>builder()
                .put("check_coating_pipe", new MaterialStatisticsBlock("check_coating_pipe", "防腐管检查信息", "daq_check_coating_pipe", "pipe_weld"))
                .put("check_hot_bends", new MaterialStatisticsBlock("check_hot_bends", "热煨弯管检查记录", "daq_check_hot_bends", "pipe_weld"))
                .put("check_tee", new MaterialStatisticsBlock("check_tee", "三通信息检查", "daq_check_tee", "pipe_weld"))
                .put("check_reducer", new MaterialStatisticsBlock("check_reducer", "大小头信息检查", "daq_check_reducer", "pipe_weld"))
                .put("check_insulated_joint", new MaterialStatisticsBlock("check_insulated_joint", "绝缘接头检查", "daq_check_insulated_joint", "pipe_weld"))
                .build();
    	ALL = ImmutableMap.copyOf(ImmutableSet.<Map.Entry<String, MaterialStatisticsBlock>>builder()
                .addAll(MATERIAL_BLOCK.entrySet())
                .addAll(MATERIAL_CHECK_BLOCK.entrySet())
                .build());
    }
    
    public MaterialStatisticsBlock() {
    }

    public MaterialStatisticsBlock(String code, String cnName, String tableName, String categoryCode) {
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
