package cn.jasgroup.jasframework.acquisitiondata.station.spatial.cppowersupply.query;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.cppowersupply.query.bo.DaqStationCpPowerSupplyBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @version V1.0
 * @description 阴保电源Query
 * @Author: 陈祥思
 * @Date: 2019/1/15 9:33
 * @since JDK 1.80
 */
@QueryConfig(
    scene ="/stationCpPowerSupply/getPage",
    resultClass= DaqStationCpPowerSupplyBo.class,
    queryBeforeProcess = {
            @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
    }
)
public class DaqStationCpPowerSupplyQuery extends BaseJavaQuery {

    /**
     * oid
     */
    private String oid;

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
     * 设备编号
     */
    private String deviceCode;

    /**
     * 设备名称
     */
    private String deviceName;

    @Override
    public String getQuerySql() {
        String sql = "SELECT dscps.oid, dscps.project_oid, pro.project_name, dscps.tenders_oid, te.tenders_name, dscps.pipeline_oid, pi.pipeline_name, " +
                "dscps.pipe_station_oid, ps.pipe_station_name, dscps.device_code, dscps.device_name, dscps.manufacture_number, dscps.protect_object, doma.code_name as protect_object_name, " +
                "dscps.pointx, dscps.pointy, dscps.pointz, dscps.rated_current, dscps.rated_voltage, dscps.output_current, dscps.output_voltage, dscps.bed_num, dscps.anode_num, " +
                "dscps.power_supply_type, doma.code_name as power_supply_type_name, dscps.manufacturer_name, to_char(dscps.put_into_date, 'YYYY-MM-DD') as put_into_date, dscps.design_life, " +
                "dscps.electrified_point, dscps.given_voltage, dscps.reference_voltage, to_char(dscps.construct_date, 'YYYY-MM-DD') as construct_date, " +
                "dscps.construct_unit, u.unit_name as construct_unit_name, dscps.supervision_unit, pu.unit_name as supervision_unit_name, dscps.supervision_engineer, " +
                "dscps.collection_person, to_char(dscps.collection_date, 'YYYY-MM-DD') as collection_date, dscps.geom, dscps.geo_state, dscps.approve_status, " +
                "case when dscps.approve_status = -1 then '驳回' when dscps.approve_status = 1 then '待审核' when dscps.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name, " +
                "dscps.remarks, dscps.create_user_id, dscps.create_user_name, dscps.create_datetime, dscps.modify_user_id, dscps.modify_user_name, dscps.modify_datetime, dscps.active " +
                "FROM daq_station_cp_power_supply dscps " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = dscps.project_oid " +
                "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = dscps.pipeline_oid " +
                "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = dscps.tenders_oid " +
                "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid = dscps.pipe_station_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = dscps.supervision_unit " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = dscps.construct_unit " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'protect_object_domain' and active=1 ) doma ON doma.code_id = dscps.protect_object " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'power_supply_type_domain' and active=1 ) doma1 ON doma1.code_id = dscps.power_supply_type " +
                "WHERE 1 = 1 AND dscps.active = 1";
        sql += conditionSql();
        return sql;
    }

    private String conditionSql() {
        String conditionSql= "";
        if (StringUtils.isNotBlank(oid)) {
            conditionSql += " and dscps.oid=:oid";
        }else{
            if (StringUtils.isNotBlank(projectOid)) {
                conditionSql += " and dscps.project_oid = :projectOid";
            }
            if (StringUtils.isNotBlank(tendersOid)) {
                conditionSql += " and dscps.tenders_oid = :tendersOid";
            }
            if (StringUtils.isNotBlank(pipelineOid)) {
                conditionSql += " and dscps.pipeline_oid = :pipelineOid";
            }
            if (StringUtils.isNotBlank(pipeStationOid)) {
                conditionSql += " and dscps.pipe_station_oid = :pipeStationOid";
            }
            if (StringUtils.isNotBlank(deviceCode)) {
                conditionSql += " and dscps.device_code like :deviceCode";
            }
            if (StringUtils.isNotBlank(deviceName)) {
                conditionSql += " and dscps.device_name like :deviceName";
            }
            conditionSql +=  this.dataAuthoritySql;
        }
        conditionSql += " order by dscps.create_datetime desc";
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
        if(StringUtils.isNotBlank(deviceCode)){
            return "%"+deviceCode+"%";
        }
        return null;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceName() {
        if(StringUtils.isNotBlank(deviceName)){
            return "%"+deviceName+"%";
        }
        return null;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
