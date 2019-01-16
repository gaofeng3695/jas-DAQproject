package cn.jasgroup.jasframework.acquisitiondata.station.detection.infiltration.query;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.infiltration.query.bo.DaqStationDetectionInfiltrationBo;
import cn.jasgroup.jasframework.acquisitiondata.station.detection.infiltration.service.DaqStationDetectionInfiltrationService;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * @description 渗透检测(站场)
 * @author cuixianing
 * @date 2018年7月12日上午10:19:04
 * @version V1.0
 * @since JDK 1.80
 * {@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 * {@link DaqStationDetectionInfiltrationService #injectinfiltrationSubList()}
 */

@QueryConfig(
        scene = "/daqStationDetectionInfiltration/getPage",
        resultClass = DaqStationDetectionInfiltrationBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        },
        resultAfterProcess = {
                @Process(service = "daqStationDetectionInfiltrationService" , method = "injectinfiltrationSubList()")
        }
)
public class DaqStationDetectionInfiltrationQuery extends BaseJavaQuery {

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
     * 焊口编号
     */
    private String weldOid;

    @Override
    public String getQuerySql() {
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append("SELECT" +
                " wc.*, pro.project_name," +
                " te.tenders_name," +
                " pi.pipeline_name," +
                " ps.pipe_station_name," +
                " pu.unit_name AS supervision_unit_name," +
                " u.unit_name AS detection_unit_name," +
                " dspw.weld_code," +
                " doma.code_name detection_type_name," +
                " doma1.code_name evaluation_grade_name," +
                " case when wc.evaluation_result=1 then '合格' when wc.evaluation_result=0 then '不合格' end as evaluation_result_name, " +
                " case when wc.approve_status = -1 then '驳回' when wc.approve_status = 1 then '待审核' when wc.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name " +
                "from daq_station_detection_infiltration wc " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid " +
                "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wc.tenders_oid " +
                "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wc.pipeline_oid " +
                "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid=wc.pipe_station_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.detection_unit " +
                "LEFT JOIN (select oid,weld_code from daq_station_process_weld where active=1 and approve_status=2) dspw on dspw.oid = wc.weld_oid " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'detection_type_domain' and active=1 ) doma ON doma.code_id = wc.detection_type " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'evaluation_grade_domain' and active=1 ) doma1 ON doma1.code_id = wc.evaluation_grade where wc.active = 1 ");

        sqlBuffer.append(getConditionSql());
        return sqlBuffer.toString();
    }
    private String getConditionSql() {
        String conditionSql = "";
        if(StringUtils.isNotBlank(oid)){
            conditionSql += " and wc.oid = :oid";
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
            if (StringUtils.isNotBlank(weldOid)) {
                conditionSql += " and wc.weld_oid = :weldOid";
            }
            conditionSql += this.dataAuthoritySql;
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

    public String getPipelineOid() {
        return pipelineOid;
    }

    public void setPipelineOid(String pipelineOid) {
        this.pipelineOid = pipelineOid;
    }

    public String getTendersOid() {
        return tendersOid;
    }

    public void setTendersOid(String tendersOid) {
        this.tendersOid = tendersOid;
    }

    public String getPipeStationOid() {
        return pipeStationOid;
    }

    public void setPipeStationOid(String pipeStationOid) {
        this.pipeStationOid = pipeStationOid;
    }

    public String getWeldOid() {
        return weldOid;
    }

    public void setWeldOid(String weldOid) {
        this.weldOid = weldOid;
    }
}
