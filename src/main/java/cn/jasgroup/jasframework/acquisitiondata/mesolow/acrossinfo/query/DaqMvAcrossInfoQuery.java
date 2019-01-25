package cn.jasgroup.jasframework.acquisitiondata.mesolow.acrossinfo.query;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.acrossinfo.query.bo.DaqMvAcrossInfoBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * <p>类描述：穿越信息实体query</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月25日 9:30。</p>
 */
@QueryConfig(
        scene ="/daqMvAcrossInfo/getPage",
        resultClass= DaqMvAcrossInfoBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        }
)
public class DaqMvAcrossInfoQuery extends BaseJavaQuery {

    /**
     * 工程oid
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
        StringBuffer bufferSql = new StringBuffer();
        bufferSql.append("select wc.* ,pro.project_name,pu.unit_name as supervision_unit_name,u.unit_name as construct_unit_name,mpn.pipe_node_code start_pipe_node_name,mpn1.pipe_node_code end_pipe_node_name, " +
                "doma.code_name across_method_name,doma1.code_name across_object_name, " +
                "case when wc.burial_method=1 then '埋地管' when wc.burial_method=2 then '明管' end as burial_method_name, " +
                "case when wc.pipe_section_category=1 then '市政管' when wc.pipe_section_category=2 then '庭院管' end as pipe_section_category_name, " +
                "case when wc.design_life=1 then '30' when wc.design_life=2 then '50' end as design_life_name, " +
                "case when wc.approve_status = -1 then '驳回' when wc.approve_status = 1 then '待审核' when wc.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name," +
                "doma2.code_name pipe_section_material_name,doma3.code_name pipe_section_spec_name " +
                "from daq_mv_across_info wc " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit  " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.construct_unit  " +
                "LEFT JOIN (select oid,pipe_node_code from daq_mv_pipe_node where active=1) mpn on mpn.oid=wc.start_pipe_node_oid " +
                "LEFT JOIN (select oid,pipe_node_code from daq_mv_pipe_node where active=1) mpn1 on mpn1.oid=wc.end_pipe_node_oid  " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'across_method_domain' and active=1 ) doma ON doma.code_id = wc.across_method " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'across_object_domain' and active=1 ) doma1 ON doma1.code_id = wc.across_object " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'pipe_section_material_domain' and active=1 ) doma2 ON doma2.code_id = wc.pipe_section_material " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'pipe_section_spec_domain' and active=1 ) doma3 ON doma3.code_id = wc.pipe_section_spec " +
                "where wc.active=1  ");
        bufferSql.append(conditionSql());
        return bufferSql.toString();
    }

    private String conditionSql() {
        StringBuffer conditionSql = new StringBuffer();
        if (StringUtils.isNotBlank(oid)) {
            conditionSql.append(" and wc.oid=:oid ");
        }else{
            if (StringUtils.isNotBlank(projectOid)) {
                conditionSql.append(" and wc.project_oid = :projectOid ");
            }
            if (StringUtils.isNotBlank(pipeSectionCode)) {
                conditionSql.append(" and wc.pipe_section_code like :pipeSectionCode ");
            }
            if (StringUtils.isNotBlank(startPipeNodeOid)) {
                conditionSql.append(" and wc.start_pipe_node_oid = :startPipeNodeOid ");
            }
            if (StringUtils.isNotBlank(endPipeNodeOid)) {
                conditionSql.append(" and wc.end_pipe_node_oid = :endPipeNodeOid ");
            }
            conditionSql.append(this.dataAuthoritySql);
        }
        conditionSql.append(" order by wc.create_datetime desc");
        return conditionSql.toString();
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
    }

    public String getPipeSectionCode() {
        if(StringUtils.isNotBlank(pipeSectionCode)){
            return "%" + pipeSectionCode + "%";
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
