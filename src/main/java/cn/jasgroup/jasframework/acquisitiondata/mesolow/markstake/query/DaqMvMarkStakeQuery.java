package cn.jasgroup.jasframework.acquisitiondata.mesolow.markstake.query;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.markstake.query.bo.DaqMvMarkStakeBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @version V1.0
 * @description 标志桩信息Query
 * @Author: 陈祥思
 * @Date: 2019/1/25 10:49
 * @since JDK 1.80
 */
@QueryConfig(
    scene ="/mvMarkStake/getPage",
    resultClass= DaqMvMarkStakeBo.class,
    queryBeforeProcess = {
            @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
    }
)
public class DaqMvMarkStakeQuery extends BaseJavaQuery {

    /**
     * oid
     */
    private String oid;

    /**
     * 项目oid
     */
    private String projectOid;

    /**
     * 编号
     */
    private String markStakeCode;

    @Override
    public String getQuerySql() {
        String sql = "SELECT mvms.*,pro.project_name," +
                " doma.code_name as mark_stake_type_name,doma1.code_name as mark_stake_material_name," +
                " pu.unit_name as supervision_unit_name, u.unit_name as construct_unit_name, " +
                " case when mvms.approve_status = -1 then '驳回' when mvms.approve_status = 1 then '待审核' when mvms.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name " +
                " FROM daq_mv_mark_stake mvms " +
                " LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = mvms.project_oid " +
                " LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'mark_stake_type_domain' and active=1 ) doma ON doma.code_id = mvms.mark_stake_type " +
                " LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'mark_stake_material_domain' and active=1 ) doma1 ON doma1.code_id = mvms.mark_stake_material " +
                " LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = mvms.supervision_unit  " +
                " LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = mvms.construct_unit " +
                " WHERE 1 = 1 AND mvms.active = 1";
        sql += conditionSql();
        return sql;
    }

    private String conditionSql() {
        String conditionSql = "";
        if (StringUtils.isNotBlank(oid)) {
            conditionSql += " and mvms.oid=:oid";
        } else {
            if (StringUtils.isNotBlank(projectOid)) {
                conditionSql += " and mvms.project_oid = :projectOid";
            }
            if (StringUtils.isNotBlank(markStakeCode)) {
                conditionSql += " and mvms.mark_stake_code like :markStakeCode";
            }
            conditionSql += this.dataAuthoritySql;
        }
        conditionSql += " order by mvms.create_datetime desc";
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

    public String getMarkStakeCode() {
        if(StringUtils.isNotBlank(markStakeCode)){
            return "%"+markStakeCode+"%";
        }
        return null;
    }
    public void setMarkStakeCode(String markStakeCode) {
        this.markStakeCode = markStakeCode;
    }
}