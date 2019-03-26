package cn.jasgroup.jasframework.acquisitiondata.station.spatial.auxiliaryanodebed.query;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.auxiliaryanodebed.query.bo.DaqStationAuxiliaryAnodeBedBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @version V1.0
 * @description 辅助阳极地床Query
 * @Author: 陈祥思
 * @Date: 2019/1/15 13:49
 * @since JDK 1.80
 */
@QueryConfig(
    scene ="/stationAuxiliaryAnodeBed/getPage",
    resultClass= DaqStationAuxiliaryAnodeBedBo.class,
    queryBeforeProcess = {
            @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
    }
)
public class DaqStationAuxiliaryAnodeBedQuery extends BaseJavaQuery {

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
     * 地床编号
     */
    private String deviceCode;

    /**
     * 桩号
     */
    private String medianStakeOid;

    @Override
    public String getQuerySql() {
        String sql = "SELECT dsaab.oid, dsaab.project_oid, pro.project_name, dsaab.tenders_oid, te.tenders_name, dsaab.pipeline_oid, pi.pipeline_name, dsaab.pipe_station_oid, ps.pipe_station_name, " +
                "dsaab.device_code, dsaab.median_stake_oid, me.median_stake_code, dsaab.relative_mileage, dsaab.install_location_des, dsaab.burying_depth, " +
                "dsaab.is_temporary, case when dsaab.is_temporary=1 then '是' when dsaab.is_temporary=0 then '否'  else '' end as is_temporary_name, " +
                "dsaab.design_life, dsaab.backfill_material, doma.code_name as backfill_material_name, dsaab.auxiliary_anode_num, dsaab.burial_way, doma1.code_name as burial_way_name, dsaab.gross_weight, " +
                "dsaab.pointx, dsaab.pointy, dsaab.pointz, dsaab.cable_length, dsaab.protect_length, dsaab.anode_material, doma2.code_name as anode_material_name, dsaab.anode_specification, dsaab.anode_resistance, " +
                "dsaab.has_water_flooding_sys, case when dsaab.has_water_flooding_sys=1 then '是' when dsaab.has_water_flooding_sys=0 then '否' else '' end as has_water_flooding_sys_name, " +
                "dsaab.has_vent_hole,  case when dsaab.has_vent_hole=1 then '是' when dsaab.has_vent_hole=0 then '否'  else '' end as has_vent_hole_name, " +
                "dsaab.anode_bed_connection_power, to_char(dsaab.embedment_date, 'YYYY-MM-DD') as embedment_date, to_char(dsaab.construct_date, 'YYYY-MM-DD') as construct_date, " +
                "dsaab.construct_unit, u.unit_name as construct_unit_name, dsaab.supervision_unit, pu.unit_name as supervision_unit_name, dsaab.supervision_engineer, dsaab.collection_person, " +
                "to_char(dsaab.collection_date, 'YYYY-MM-DD') as collection_date, dsaab.geom, dsaab.geo_state, dsaab.approve_status, " +
                "case when dsaab.approve_status = -1 then '驳回' when dsaab.approve_status = 1 then '待审核' when dsaab.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name, " +
                "dsaab.remarks, dsaab.create_user_id, dsaab.create_user_name, dsaab.create_datetime, dsaab.modify_user_id, dsaab.modify_user_name, dsaab.modify_datetime, dsaab.active " +
                "FROM daq_station_auxiliary_anode_bed dsaab " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = dsaab.project_oid " +
                "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = dsaab.pipeline_oid " +
                "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = dsaab.tenders_oid " +
                "LEFT JOIN (select oid, pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid = dsaab.pipe_station_oid " +
                "LEFT JOIN (select oid, median_stake_code from daq_median_stake where active=1) me ON me.oid = dsaab.median_stake_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = dsaab.supervision_unit " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = dsaab.construct_unit " +
                "LEFT JOIN (SELECT code_id, code_name,active FROM sys_domain where domain_name = 'backfill_material_domain' and active=1 ) doma ON doma.code_id = dsaab.backfill_material " +
                "LEFT JOIN (SELECT code_id, code_name,active FROM sys_domain where domain_name = 'burial_way_domain' and active=1 ) doma1 ON doma1.code_id = dsaab.burial_way " +
                "LEFT JOIN (SELECT code_id, code_name,active FROM sys_domain where domain_name = 'anode_specification_domain' and active=1 ) doma2 ON doma2.code_id = dsaab.anode_material " +
                "WHERE 1 = 1 AND dsaab.active = 1";
        sql += conditionSql();
        return sql;
    }


    private String conditionSql() {
        String conditionSql = "";
        if (StringUtils.isNotBlank(oid)) {
            conditionSql += " and dsaab.oid=:oid";
        } else {
            if (StringUtils.isNotBlank(projectOid)) {
                conditionSql += " and dsaab.project_oid = :projectOid";
            }
            if (StringUtils.isNotBlank(tendersOid)) {
                conditionSql += " and dsaab.tenders_oid = :tendersOid";
            }
            if (StringUtils.isNotBlank(pipelineOid)) {
                conditionSql += " and dsaab.pipeline_oid = :pipelineOid";
            }
            if (StringUtils.isNotBlank(pipeStationOid)) {
                conditionSql += " and dsaab.pipe_station_oid = :pipeStationOid";
            }
            if (StringUtils.isNotBlank(deviceCode)) {
                conditionSql += " and dsaab.device_code like :deviceCode";
            }
            if (StringUtils.isNotBlank(medianStakeOid)) {
                conditionSql += " and dsaab.median_stake_oid = :medianStakeOid";
            }
            conditionSql += this.dataAuthoritySql;
        }
        conditionSql += " order by dsaab.create_datetime desc";
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

    public String getMedianStakeOid() {
        return medianStakeOid;
    }

    public void setMedianStakeOid(String medianStakeOid) {
        this.medianStakeOid = medianStakeOid;
    }
}