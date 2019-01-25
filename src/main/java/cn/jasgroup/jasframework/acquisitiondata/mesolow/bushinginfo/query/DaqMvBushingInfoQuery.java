package cn.jasgroup.jasframework.acquisitiondata.mesolow.bushinginfo.query;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.bushinginfo.query.bo.DaqMvBushingInfoBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * <p>类描述：套管信息query</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月25日 14:03。</p>
 */
@QueryConfig(
        scene ="/daqMvBushingInfo/getPage",
        resultClass= DaqMvBushingInfoBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        }
)
public class DaqMvBushingInfoQuery extends BaseJavaQuery {

    /**
     * 工程oid
     */
    private String projectOid;

    /**
     * 材质
     */
    private String bushingMaterial;

    /**
     * 规格
     */
    private String bushingSpec;

    @Override
    public String getQuerySql() {
        StringBuffer bufferSql = new StringBuffer();
        bufferSql.append("select wc.*,pro.project_name,pu.unit_name as supervision_unit_name,u.unit_name as construct_unit_name,doma.code_name bushing_material_name, " +
                "case when wc.has_check_leak_device=1 then '是' when wc.has_check_leak_device=0 then '否' end as has_check_leak_device_name, " +
                "case when wc.approve_status = -1 then '驳回' when wc.approve_status = 1 then '待审核' when wc.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name " +
                "from daq_mv_bushing_info wc " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit  " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.construct_unit  " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'bushing_material_domain' and active=1 ) doma ON doma.code_id = wc.bushing_material " +
                "where wc.active=1 ");
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
            if (StringUtils.isNotBlank(bushingMaterial)) {
                conditionSql.append(" and wc.bushing_material  = :bushingMaterial ");
            }
            if (StringUtils.isNotBlank(bushingSpec)) {
                conditionSql.append(" and wc.bushing_spec  like :bushingSpec ");
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

    public String getBushingMaterial() {
        return bushingMaterial;
    }

    public void setBushingMaterial(String bushingMaterial) {
        this.bushingMaterial = bushingMaterial;
    }

    public String getBushingSpec() {
        if(StringUtils.isNotBlank(bushingSpec)){
            return "%" +bushingSpec + "%";
        }
        return null;
    }

    public void setBushingSpec(String bushingSpec) {
        this.bushingSpec = bushingSpec;
    }
}
