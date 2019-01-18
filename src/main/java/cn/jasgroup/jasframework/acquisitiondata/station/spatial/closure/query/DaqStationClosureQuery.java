package cn.jasgroup.jasframework.acquisitiondata.station.spatial.closure.query;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.closure.query.bo.DaqStationClosureBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * <p>封堵物getPage</p>
 * @author cuixianing。
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-15 15:18:32。</p>
 */
@QueryConfig(
        scene ="/daqStationClosure/getPage",
        resultClass= DaqStationClosureBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        }
)
public class DaqStationClosureQuery extends BaseJavaQuery {

    /**
     * 项目oid
     */
    private String projectOid;

    /**
     * 标段oid
     */
    private String tendersOid;

    /**
     * 管线oid
     */
    private String pipelineOid;

    /**
     * 站场/阀室编号
     */
    private String pipeStationOid;

    /**
     * 编号
     */
    private String deviceCode;

    /**
     * 名称
     */
    private String deviceName;

    @Override
    public String getQuerySql() {
        StringBuffer bufferSql = new StringBuffer();
        bufferSql.append("SELECT " +
                "wc.*, pro.project_name, " +
                "te.tenders_name, " +
                "pi.pipeline_name, " +
                "ps.pipe_station_name, " +
                "pu.unit_name AS supervision_unit_name, " +
                "u.unit_name AS construct_unit_name, " +
                "doma.code_name closure_mold_name, " +
                "case when wc.closure_material=1 then '未知' when wc.closure_material=2 then '钢材' when wc.closure_material=3 then '塑料'  " +
                "when wc.closure_material=4 then '其他' end as closure_material_name, " +
                "doma1.code_name closure_connection_methods_name, " +
                "doma2.code_name tube_making_methods_name, " +
                "case when wc.approve_status = -1 then '驳回' when wc.approve_status = 1 then '待审核' when wc.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name " +
                "from  " +
                "daq_station_closure wc " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid  " +
                "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wc.tenders_oid  " +
                "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wc.pipeline_oid  " +
                "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid=wc.pipe_station_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit  " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.construct_unit  " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'closure_mold_domain' and active=1 ) doma ON doma.code_id = wc.closure_mold " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'closure_connection_methods_domain' and active=1 ) doma1 ON doma1.code_id = wc.closure_connection_methods " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'tube_making_methods_domain' and active=1 ) doma2 ON doma2.code_id = wc.tube_making_methods " +
                "WHERE wc.active = 1 ");
        bufferSql.append(conditionSql());
        return bufferSql.toString();

    }

    private String conditionSql() {
        String conditionSql= "";
        if (StringUtils.isNotBlank(oid)) {
            conditionSql += " and wc.oid=:oid";
        }else{
            if (StringUtils.isNotBlank(projectOid)) {
                conditionSql += " and wc.project_oid = :projectOid";
            }
            if (StringUtils.isNotBlank(tendersOid)) {
                conditionSql += " and wc.tenders_oid = :tendersOid";
            }
            if (StringUtils.isNotBlank(pipelineOid)) {
                conditionSql += " and wc.pipeline_oid = :pipelineOid";
            }
            if (StringUtils.isNotBlank(pipeStationOid)) {
                conditionSql += " and wc.pipe_station_oid = :pipeStationOid";
            }
            if (StringUtils.isNotBlank(deviceCode)) {
                conditionSql += " and wc.device_code like :deviceCode";
            }
            if (StringUtils.isNotBlank(deviceName)) {
                conditionSql += " and wc.device_name  like :deviceName";
            }
            conditionSql +=  this.dataAuthoritySql;
        }
        conditionSql += " order by wc.create_datetime desc";
        return conditionSql;
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
    }

    public String getTendersOid() {
        return tendersOid;
    }

    public void setTendersOid(String tendersOid) {
        this.tendersOid = tendersOid;
    }

    public String getPipelineOid() {
        return pipelineOid;
    }

    public void setPipelineOid(String pipelineOid) {
        this.pipelineOid = pipelineOid;
    }

    public String getPipeStationOid() {
        return pipeStationOid;
    }

    public void setPipeStationOid(String pipeStationOid) {
        this.pipeStationOid = pipeStationOid;
    }

    public String getDeviceCode() {
        if (StringUtils.isNotBlank(deviceCode)) {
            return "%"+deviceCode+"%";
        }
        return null;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        if (StringUtils.isNotBlank(deviceName)) {
            return "%"+deviceName+"%";
        }
        return null;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
