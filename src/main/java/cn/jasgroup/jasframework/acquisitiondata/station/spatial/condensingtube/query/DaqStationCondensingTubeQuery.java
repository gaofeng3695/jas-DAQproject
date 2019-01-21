package cn.jasgroup.jasframework.acquisitiondata.station.spatial.condensingtube.query;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.condensingtube.query.bo.DaqStationCondensingTubeBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * <p>排凝管getPage</p>
 * @author cuixianing
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-15 10:43:37。</p>
 */
@QueryConfig(
        scene ="/daqStationCondensingTube/getPage",
        resultClass= DaqStationCondensingTubeBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        }
)
public class DaqStationCondensingTubeQuery extends BaseJavaQuery {

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

    /**
     * 桩号
     */
    private String medianStakeOid;

    @Override
    public String getQuerySql() {
        StringBuffer bufferSql = new StringBuffer();
        bufferSql.append("SELECT " +
                " wc.*, pro.project_name, " +
                " te.tenders_name, " +
                " pi.pipeline_name, " +
                " ps.pipe_station_name, " +
                " pu.unit_name AS supervision_unit_name, " +
                " u.unit_name AS construct_unit_name, " +
                " case when wc.condensing_tube_material=1 then '未知' when wc.condensing_tube_material=2 then '钢材' when wc.condensing_tube_material=3 then '塑料'  " +
                " when wc.condensing_tube_material=4 then '其他' end as condensing_tube_material_name, " +
                " case when wc.approve_status = -1 then '驳回' when wc.approve_status = 1 then '待审核' when wc.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name," +
                " med.median_stake_code  median_stake_code " +
                "from  " +
                "daq_station_condensing_tube wc " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid  " +
                "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wc.tenders_oid  " +
                "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wc.pipeline_oid  " +
                "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid=wc.pipe_station_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit  " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.construct_unit " +
                "LEFT JOIN (select oid,median_stake_code from daq_median_stake where active=1) med ON med.oid = wc.median_stake_oid  " +
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
            if (StringUtils.isNotBlank(medianStakeOid)) {
                conditionSql += " and wc.median_stake_oid  = :medianStakeOid";
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

    public String getMedianStakeOid() {
        return medianStakeOid;
    }

    public void setMedianStakeOid(String medianStakeOid) {
        this.medianStakeOid = medianStakeOid;
    }
}
