package cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.query;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.query.bo.DaqStationDetectionUltrasonicBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;
/**
 * <p>Table description 超声波检测(站场)Bo</p>
 * @author 陈祥思
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-14 13:40:31。</p>
 */
@QueryConfig(
        scene="/stationDetectionUltrasonic/getPage",
        resultClass= DaqStationDetectionUltrasonicBo.class,
        queryBeforeProcess = {
                @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
        },
        resultAfterProcess = {
                @Process(service = "daqStationDetectionUltrasonicService" , method = "injectStationDetectionUltrasonicSubList()")
        }
)
public class DaqStationDetectionUltrasonicQuery extends BaseJavaQuery {


    private String oid;
    private String projectOid;
    private String pipelineOid;
    private String tendersOid;
    private String pipeStationOid;
    private String weldOid;
    private String constructUnit;
    /**
     * <p>Table description 查询列表或详情</p>
     * @author 陈祥思
     * @version v1.0.0.1。
     * @since JDK1.8.0_181。
     * <p>创建日期：2019-01-14 13:40:31。</p>
     */
    @Override
    public String getQuerySql() {
        String sql = "SELECT dsdu.oid,dsdu.project_oid,dsdu.tenders_oid,dsdu.pipeline_oid,dsdu.pipe_station_oid,dsdu.weld_oid,dsdu.detection_report_num,"
                + " to_char(dsdu.detection_date, 'YYYY-MM-DD') as detection_date,dsdu.detection_type,dsdu.evaluation_grade,dsdu.evaluation_result,"
                + " case when dsdu.evaluation_result = 0 then '不合格' when dsdu.evaluation_result = 1 then '合格' else '' end as evaluation_result_name,"
                + " dsdu.detection_unit,dsdu.detection_person,dsdu.supervision_unit,dsdu.supervision_engineer,to_char(dsdu.collection_date, 'YYYY-MM-DD') as collection_date,dsdu.approve_status,"
                + " case when dsdu.approve_status = -1 then '驳回' when dsdu.approve_status = 1 then '待审核' when dsdu.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name,"
                + " dsdu.remarks,dsdu.create_user_id,dsdu.create_user_name,dsdu.create_datetime,dsdu.modify_user_id,dsdu.modify_user_name,dsdu.modify_datetime,dsdu.active,"
                + " d1.code_name as detection_type_name, d2.code_name as evaluation_grade_name,"
                + "	p.project_name, l.pipeline_name,	dt.tenders_name,	dps.pipe_station_name pipe_station_name,"
                + "	u1.unit_name as detection_unit_name,	u2.unit_name as supervision_unit_name,"
                + " dspw.weld_code "
                + " from daq_station_detection_ultrasonic dsdu "
                + " left join (select code_id,code_name from sys_domain where active=1) d1 on d1.code_id = dsdu.detection_type"
                + " left join (select code_id,code_name from sys_domain where active=1) d2 on d2.code_id = dsdu.evaluation_grade"
                + " left join (select oid,project_name from daq_project where active=1) p on p.oid=dsdu.project_oid "
                + " left join (select oid,pipeline_name from daq_pipeline where active=1) l on l.oid=dsdu.pipeline_oid "
                + " left join (select oid,tenders_name from daq_tenders where active=1) dt on dt.oid=dsdu.tenders_oid "
                + " left join (select oid,pipe_station_name from daq_pipe_station where active=1) dps on dps.oid=dsdu.pipe_station_oid "
                + " left join (select oid,unit_name from pri_unit where active=1) u1 on u1.oid=dsdu.detection_unit "
                + " left join (select oid,unit_name from pri_unit where active=1) u2 on u2.oid=dsdu.supervision_unit "
                + " left join (select oid,weld_code from daq_station_process_weld where active=1 and approve_status=2) dspw on dspw.oid=dsdu.weld_oid "
                + " where 1=1 and dsdu.active = 1";
        if (StringUtils.isNotBlank(oid)) {
            sql += " and dsdu.oid=:oid ";
        }else {

            if (StringUtils.isNotBlank(projectOid)) {
                sql += " and dsdu.project_oid = :projectOid ";
            }
            if (StringUtils.isNotBlank(pipelineOid)) {
                sql += " and dsdu.pipeline_oid = :pipelineOid ";
            }
            if (StringUtils.isNotBlank(tendersOid)) {
                sql += " and dsdu.tenders_oid = :tendersOid ";
            }
            if (StringUtils.isNotBlank(pipeStationOid)) {
                sql += " and dsdu.pipe_station_oid = :pipeStationOid ";
            }
            if (StringUtils.isNotBlank(weldOid)) {
                sql += " and dsdu.weld_oid = :weldOid ";
            }
            if (StringUtils.isNotBlank(constructUnit)) {
                sql += " and dsdu.detection_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
            }
        }
        sql += this.dataAuthoritySql;
        sql +=" order by dsdu.create_datetime desc";
        return sql;
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

    public String getConstructUnit() {
        return constructUnit;
    }

    public void setConstructUnit(String constructUnit) {
        this.constructUnit = constructUnit;
    }
}
