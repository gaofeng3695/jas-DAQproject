package cn.jasgroup.jasframework.acquisitiondata.station.spatial.sweeping.query;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.sweeping.query.bo.DaqStationPipeSweepingBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * <p>管道扫水getPage</p>
 * @author cuixianing
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-15 10:43:37。</p>
 */
@QueryConfig(
        scene ="/daqStationPipeSweeping/getPage",
        resultClass= DaqStationPipeSweepingBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        }
)
public class DaqStationPipeSweepingQuery extends BaseJavaQuery {

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
     * 试压起始桩号
     */
    private String startMedianStakeOid;

    /**
     * 试压结束桩号
     */
    private String endMedianStakeOid;

    @Override
    public String getQuerySql() {
        StringBuffer bufferSql = new StringBuffer();
        bufferSql.append("SELECT " +
                "wc.*, pro.project_name," +
                "te.tenders_name," +
                "pi.pipeline_name," +
                "ps.pipe_station_name," +
                "pu.unit_name AS supervision_unit_name," +
                "u.unit_name AS construct_unit_name," +
                "case when wc.sweeping_medium=1 then '水' when wc.sweeping_medium=2 then '空气' when wc.sweeping_medium=3 then '氮气'  end as sweeping_medium_name," +
                "case when wc.approve_status = -1 then '驳回' when wc.approve_status = 1 then '待审核' when wc.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name," +
                "med.median_stake_code as end_median_stake_code,me.median_stake_code as start_median_stake_code " +
                "from " +
                "daq_station_pipe_sweeping wc " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid  " +
                "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wc.tenders_oid  " +
                "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wc.pipeline_oid  " +
                "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid=wc.pipe_station_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit  " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.construct_unit " +
                "LEFT JOIN (select oid,median_stake_code from daq_median_stake where active=1) me ON me.oid = wc.start_median_stake_oid " +
                "LEFT JOIN (select oid,median_stake_code from daq_median_stake where active=1) med ON med.oid = wc.end_median_stake_oid " +
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
            if (StringUtils.isNotBlank(startMedianStakeOid)) {
                conditionSql += " and wc.start_median_stake_oid = :startMedianStakeOid";
            }
            if (StringUtils.isNotBlank(endMedianStakeOid)) {
                conditionSql += " and wc.end_median_stake_oid = :endMedianStakeOid";
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
}
