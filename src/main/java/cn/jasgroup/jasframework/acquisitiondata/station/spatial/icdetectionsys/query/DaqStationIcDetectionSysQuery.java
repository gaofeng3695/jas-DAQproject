package cn.jasgroup.jasframework.acquisitiondata.station.spatial.icdetectionsys.query;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.icdetectionsys.query.bo.DaqStationIcDetectionSysBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @version V1.0
 * @description 内腐蚀检测系统Query
 * @Author: 陈祥思
 * @Date: 2019/1/15 13:46
 * @since JDK 1.80
 */
@QueryConfig(
        scene ="/stationIcDetectionSys/getPage",
        resultClass= DaqStationIcDetectionSysBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        }
)
public class DaqStationIcDetectionSysQuery extends BaseJavaQuery {
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
     * 起始桩
     */
    private String startMedianStakeOid;

    /**
     * 终点桩
     */
    private String endMedianStakeOid;

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
        String sql = "SELECT dsids.oid, dsids.project_oid, pro.project_name, dsids.tenders_oid, te.tenders_name, dsids.pipeline_oid, pi.pipeline_name, dsids.pipe_station_oid, ps.pipe_station_name, " +
                "dsids.device_code, dsids.device_name, dsids.manufacture_number, dsids.start_median_stake_oid, ms.median_stake_code as start_median_stake_code, dsids.start_relative_mileage, " +
                "dsids.end_median_stake_oid, dms.median_stake_code as end_median_stake_code, dsids.end_relative_mileage, dsids.system_type, doma.code_name as system_type_name, " +
                "dsids.system_principle, doma1.code_name as system_principle_name, dsids.development_unit, to_char(dsids.put_into_date, 'YYYY-MM-DD') as put_into_date, " +
                "to_char(dsids.construct_date, 'YYYY-MM-DD') as construct_date, dsids.construct_unit, u.unit_name as construct_unit_name, " +
                "dsids.supervision_unit, pu.unit_name as supervision_unit_name, dsids.supervision_engineer, dsids.collection_person, to_char(dsids.collection_date, 'YYYY-MM-DD') as collection_date, " +
                "dsids.approve_status, case when dsids.approve_status = -1 then '驳回' when dsids.approve_status = 1 then '待审核' when dsids.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name, " +
                "dsids.geo_state, dsids.geom, dsids.remarks, dsids.create_user_id, dsids.create_user_name, dsids.create_datetime, dsids.modify_user_id, dsids.modify_user_name, dsids.modify_datetime, dsids.active " +
                "FROM daq_station_ic_detection_sys dsids " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = dsids.project_oid " +
                "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = dsids.pipeline_oid " +
                "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = dsids.tenders_oid " +
                "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid = dsids.pipe_station_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = dsids.supervision_unit " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = dsids.construct_unit " +
                "LEFT JOIN (select oid,median_stake_code from daq_median_stake where active=1) ms ON ms.oid = dsids.start_median_stake_oid " +
                "LEFT JOIN (select oid,median_stake_code from daq_median_stake where active=1) dms ON dms.oid = dsids.end_median_stake_oid " +
                "LEFT JOIN (SELECT code_id, code_name,active FROM sys_domain where domain_name = 'system_type_domain' and active=1 ) doma ON doma.code_id = dsids.system_type " +
                "LEFT JOIN (SELECT code_id, code_name,active FROM sys_domain where domain_name = 'system_principle_domain' and active=1 ) doma1 ON doma1.code_id = dsids.system_principle "
                + "WHERE 1 = 1 AND dsids.active = 1";
        sql += conditionSql();
        return sql;
    }

    private String conditionSql() {
        String conditionSql= "";
        if (StringUtils.isNotBlank(oid)) {
            conditionSql += " and dsids.oid=:oid";
        }else{
            if (StringUtils.isNotBlank(projectOid)) {
                conditionSql += " and dsids.project_oid = :projectOid";
            }
            if (StringUtils.isNotBlank(tendersOid)) {
                conditionSql += " and dsids.tenders_oid = :tendersOid";
            }
            if (StringUtils.isNotBlank(pipelineOid)) {
                conditionSql += " and dsids.pipeline_oid = :pipelineOid";
            }
            if (StringUtils.isNotBlank(pipeStationOid)) {
                conditionSql += " and dsids.pipe_station_oid = :pipeStationOid";
            }
            if (StringUtils.isNotBlank(startMedianStakeOid)) {
                conditionSql += " and dsids.start_median_stake_oid = :startMedianStakeOid";
            }
            if (StringUtils.isNotBlank(endMedianStakeOid)) {
                conditionSql += " and dsids.end_median_stake_oid = :endMedianStakeOid";
            }
            if (StringUtils.isNotBlank(deviceCode)) {
                conditionSql += " and dsids.device_code like :deviceCode";
            }
            if (StringUtils.isNotBlank(deviceName)) {
                conditionSql += " and dscps.device_name like :deviceName";
            }
            conditionSql +=  this.dataAuthoritySql;
        }
        conditionSql += " order by dsids.create_datetime desc";
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

    public String getStartMedianStakeOid() {
        return startMedianStakeOid;
    }

    public void setStartMedianStakeOid(String startMedianStakeOid) {
        this.startMedianStakeOid = startMedianStakeOid;
    }

    public String getEndMedianStakeOid() {
        return endMedianStakeOid;
    }

    public void setEndMedianStakeOid(String endMedianStakeOid) {
        this.endMedianStakeOid = endMedianStakeOid;
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
