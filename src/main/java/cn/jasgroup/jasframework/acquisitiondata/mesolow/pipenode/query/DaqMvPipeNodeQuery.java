package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.query;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.query.bo.DaqMvPipeNodeBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * <p>类描述：</p>。
 *
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月24日 14:10。</p>
 */

@QueryConfig(
        scene ="/daqMvPipeNode/getPage",
        resultClass= DaqMvPipeNodeBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        }
)
public class DaqMvPipeNodeQuery extends BaseJavaQuery {

    /**
     * 工程oid
     */
    private String projectOid;

    /**
     * 节点编号
     */
    private String pipeNodeCode;

    /**
     * 点类型,域值：pipe_node_type_domain
     */
    private String pipeNodeType;

    @Override
    public String getQuerySql() {
        StringBuffer bufferSql = new StringBuffer();
        bufferSql.append("select wc.*,pro.project_name,doma.code_name pipe_node_type_name,doma1.code_name electronic_label_type_name,pu.unit_name as supervision_unit_name,u.unit_name as construct_unit_name," +
                "case when wc.approve_status = -1 then '驳回' when wc.approve_status = 1 then '待审核' when wc.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name," +
                "case when wc.is_electronic_label=1 then '是' when wc.is_electronic_label=0 then '否' end as is_electronic_label_name " +
                "from daq_mv_pipe_node wc " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'pipe_node_type_domain' and active=1 ) doma ON doma.code_id = wc.pipe_node_type " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'electronic_label_type_domain' and active=1 ) doma1 ON doma1.code_id = wc.electronic_label_type " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit  " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.construct_unit where wc.active=1 ");
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
            if (StringUtils.isNotBlank(pipeNodeCode)) {
                conditionSql.append(" and wc.pipe_node_code like :pipeNodeCode ");
            }
            if (StringUtils.isNotBlank(pipeNodeType)) {
                conditionSql.append(" and wc.pipe_node_type = :pipeNodeType ");
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

    public String getPipeNodeCode() {
        if (StringUtils.isNotBlank(pipeNodeCode)) {
            return "%"+pipeNodeCode+"%";
        }
        return null;
    }

    public void setPipeNodeCode(String pipeNodeCode) {
        this.pipeNodeCode = pipeNodeCode;
    }

    public String getPipeNodeType() {
        return pipeNodeType;
    }

    public void setPipeNodeType(String pipeNodeType) {
        this.pipeNodeType = pipeNodeType;
    }
}
