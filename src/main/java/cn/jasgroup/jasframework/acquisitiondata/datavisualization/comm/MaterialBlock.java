package cn.jasgroup.jasframework.acquisitiondata.datavisualization.comm;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 物资
 */
public class MaterialBlock {

    private String foreignKey;

    private String tableName;

    private String checkTableName;

    /** 钢管、热煨弯管、三通、绝缘接头、封堵物、大小头、法兰 */
    private static final Map<String, MaterialBlock> MATERIAL_INFO;

    static {
        MATERIAL_INFO = ImmutableMap.<String, MaterialBlock>builder()
                .put("material_pipe", new MaterialBlock("daq_material_pipe", "daq_check_coating_pipe", "pipe_oid"))
                .put("material_hot_bends", new MaterialBlock("daq_material_hot_bends", "daq_check_hot_bends", "hot_bends_oid"))
                .put("material_tee", new MaterialBlock("daq_material_tee", "daq_check_tee", "tee_code_oid")) //TODO: 确认fk

                // TODO: 绝缘物检查的表是哪个, 外键是哪个
                .put("material_insulated_joint", new MaterialBlock("daq_material_insulated_joint", "daq_check_insulated_joint", "manufacturer_code"))
                .put("material_closure", new MaterialBlock("daq_material_closure", null, null))
                .put("material_reducer", new MaterialBlock("daq_material_reducer", "daq_check_reducer", "reducer_code_oid"))

                .put("material_flange", new MaterialBlock("daq_material_flange", null, null))
                .build();
    }

    private MaterialBlock(String tableName, String checkTableName, String foreignKey) {
        this.tableName = tableName;
        this.checkTableName = checkTableName;
        this.foreignKey = foreignKey;
    }

    public String getForeignKey() {
        return foreignKey;
    }

    public String getTableName() {
        return tableName;
    }

    public String getCheckTableName() {
        return checkTableName;
    }

    public static Map<String, MaterialBlock> getMaterialInfo() {
        return MATERIAL_INFO;
    }
}
