package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipesection.query;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.pipesection.query.bo.DaqMvPipeSectionBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @version V1.0
 * @description 管段信息Query
 * @Author: 陈祥思
 * @Date: 2019/1/24 16:49
 * @since JDK 1.80
 */
@QueryConfig(
    scene ="/mvPipeSection/getPage",
    resultClass= DaqMvPipeSectionBo.class,
    queryBeforeProcess = {
            @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
    }
)
public class DaqMvPipeSectionQuery extends BaseJavaQuery {

    /**
     * oid
     */
    private String oid;

    /**
     * 项目oid
     */
    private String projectOid;

    /**
     * 管段编号
     */
    private String pipeSectionCode;

    /**
     * 起始节点oid
     */
    private String startPipeNodeOid;

    /**
     * 终止节点oid
     */
    private String endPipeNodeOid;


    @Override
    public String getQuerySql() {
        String sql = "SELECT  mvps.*,pro.project_name,pns.pipe_node_code as start_pipe_node_code,pne.pipe_node_code as end_pipe_node_code," +
                "  doma.code_name as pipe_section_material_name,doma1.code_name as pipe_section_spec_name,pu.unit_name as supervision_unit_name, u.unit_name as construct_unit_name," +
                "  case when mvps.design_life = 1 then '30' when mvps.design_life = 2 then '50' end as design_life_name," +
                "  case when mvps.pipe_outer_anticorrosive = 1 then '3PE' when mvps.pipe_outer_anticorrosive = 2 then '环氧粉末' end as pipe_outer_anticorrosive_name," +
                "  case when mvps.outer_anticorrosive_grade = '1' then '普通级' when mvps.outer_anticorrosive_grade = '2' then '加强级' end as outer_anticorrosive_grade_name," +
                "  case when mvps.cathodic_protection_method = 1 then '牺牲阳极' when mvps.cathodic_protection_method = 2 then '外加电流' end as cathodic_protection_method_name," +
                "  case when mvps.burial_method = 1 then '埋地管' when mvps.burial_method = 2 then '明管' end as burial_method_name," +
                "  case when mvps.pipe_section_category = 1 then '市政管' when mvps.pipe_section_category = 2 then '庭院管' end as pipe_section_category_name," +
                "  case when mvps.approve_status = -1 then '驳回' when mvps.approve_status = 1 then '待审核' when mvps.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name " +
                " FROM daq_mv_pipe_section mvps " +
                " LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = mvps.project_oid " +
                " LEFT JOIN (select oid, pipe_node_code, active from daq_mv_pipe_node where active=1) pns on pns.oid = mvps.start_pipe_node_oid " +
                " LEFT JOIN (select oid, pipe_node_code, active from daq_mv_pipe_node where active=1) pne on pne.oid = mvps.end_pipe_node_oid " +
                " LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'pipe_section_material_domain' and active=1 ) doma ON doma.code_id = mvps.pipe_section_material " +
                " LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'pipe_section_spec_domain' and active=1 ) doma1 ON doma1.code_id = mvps.pipe_section_spec " +
                " LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = mvps.supervision_unit " +
                " LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = mvps.construct_unit" +
                " WHERE 1 = 1 AND mvps.active = 1";
        sql += conditionSql();
        return sql;
    }

    private String conditionSql() {
        String conditionSql = "";
        if (StringUtils.isNotBlank(oid)) {
            conditionSql += " and mvps.oid=:oid";
        } else {
            if (StringUtils.isNotBlank(projectOid)) {
                conditionSql += " and mvps.project_oid = :projectOid";
            }
            if (StringUtils.isNotBlank(pipeSectionCode)) {
                conditionSql += " and mvps.pipe_section_code like :pipeSectionCode";
            }
            if (StringUtils.isNotBlank(startPipeNodeOid)) {
                conditionSql += " and mvps.start_pipe_node_oid = :startPipeNodeOid";
            }
            if (StringUtils.isNotBlank(endPipeNodeOid)) {
                conditionSql += " and mvps.end_pipe_node_oid = :endPipeNodeOid";
            }
            conditionSql += this.dataAuthoritySql;
        }
        conditionSql += " order by mvps.create_datetime desc";
        return conditionSql;
    }

    @Override
    public String getOid() {
        return oid;
    }

    @Override
    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
    }

    public String getPipeSectionCode() {
        if(StringUtils.isNotBlank(pipeSectionCode)){
            return "%"+pipeSectionCode+"%";
        }
        return null;
    }
    public void setPipeSectionCode(String pipeSectionCode) {
        this.pipeSectionCode = pipeSectionCode;
    }

    public String getStartPipeNodeOid() {
        return startPipeNodeOid;
    }

    public void setStartPipeNodeOid(String startPipeNodeOid) {
        this.startPipeNodeOid = startPipeNodeOid;
    }

    public String getEndPipeNodeOid() {
        return endPipeNodeOid;
    }

    public void setEndPipeNodeOid(String endPipeNodeOid) {
        this.endPipeNodeOid = endPipeNodeOid;
    }
}