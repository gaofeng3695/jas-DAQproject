package cn.jasgroup.jasframework.acquisitiondata.station.detetion.infiltration.query;

import cn.jasgroup.jasframework.acquisitiondata.station.detetion.infiltration.query.bo.DaqStationDetectionInfiltrationBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @description 渗透检测(站场)
 * @author cuixianing
 * @date 2018年7月12日上午10:19:04
 * @version V1.0
 * @since JDK 1.80
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

    private String projectOid;
    private String pipelineOid;
    private String tendersOid;
    private String pipeSegmentOrCrossOid;
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
                " doma1.code_name evaluation_grade_name " +
                "from daq_station_detection_infiltration wc " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid " +
                "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wc.tenders_oid " +
                "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wc.pipeline_oid " +
                "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid=wc.pipe_station_oid " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wc.supervision_unit " +
                "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wc.detection_unit " +
                "LEFT JOIN (select oid,weld_code from daq_station_process_weld where active=1 and approve_status=2) dspw on dspw.oid = wc.weld_oid " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'detection_type_domain' and active=1 ) doma ON doma.code_id = wc.detection_type " +
                "LEFT JOIN (SELECT code_id,code_name,active FROM sys_domain where domain_name = 'evaluation_grade_domain' and active=1 ) doma1 ON doma1.code_id = wc.evaluation_grade where 1=1 ");

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
            if (StringUtils.isNotBlank(pipeSegmentOrCrossOid)) {
                conditionSql += " and wc.pipe_oid = :pipeOid";
            }
            if (StringUtils.isNotBlank(weldOid)) {
                conditionSql += " and wc.weldOid = :weldOid";
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

    public String getPipeSegmentOrCrossOid() {
        return pipeSegmentOrCrossOid;
    }

    public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
        this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
    }

    public String getWeldOid() {
        return weldOid;
    }

    public void setWeldOid(String weldOid) {
        this.weldOid = weldOid;
    }
}
