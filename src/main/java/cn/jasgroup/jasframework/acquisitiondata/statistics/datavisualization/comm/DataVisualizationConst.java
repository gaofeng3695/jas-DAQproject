package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm;

import com.google.common.collect.ImmutableList;

import java.util.List;

public interface DataVisualizationConst {

    /**
     * 施工机组中焊接机组类型阈值: 半自动焊接机组, 全自动焊接机组, 手工焊机组
     */
    List<String> WELD_UNIT_TYPE_CODE = ImmutableList.of("work_unit_type_code_001", "work_unit_type_code_010", "work_unit_type_code_012");

    /**
     * 机组人员中的焊工类型阈值: 电焊工, 气焊工
     */
    List<String> WELDER_TYPE_CODE = ImmutableList.of("personnel_type_code_005", "personnel_type_code_006\n");
}
