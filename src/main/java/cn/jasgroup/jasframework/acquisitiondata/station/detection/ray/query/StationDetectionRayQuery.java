package cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.query.bo.StationDetectionRayBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：站场射线检测query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月14日 下午1:51:18。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.station.detection.ray.service.StationDetectionRayService #injectStationDetectionRaySubList()}
 */
@QueryConfig(
		scene ="/stationDetectionRay/getPage",
		resultClass= StationDetectionRayBo.class,
		queryBeforeProcess = {
			@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
		},
		resultAfterProcess = {
			@Process(service = "stationDetectionRayService" , method = "injectStationDetectionRaySubList()")
		}
)
public class StationDetectionRayQuery extends BaseJavaQuery {
	
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
	 * 焊口编号 
	 */
	private String weldOid;

	@Override
	public String getQuerySql() {
		String sql = "SELECT sta.oid,sta.weld_oid,spw.weld_code,sta.detection_report_num,sta.detection_type,d.code_name as detection_type_name,"
					+ "sta.evaluation_grade,dom.code_name as evaluation_grade_name,sta.evaluation_result,sta.negative_num,sta.detection_unit,"
					+ "sta.detection_person,sta.supervision_unit,sta.supervision_engineer,sta.approve_status,sta.remarks,"
					+ "sta.create_user_id,sta.create_user_name,sta.create_datetime,sta.modify_user_id,sta.modify_user_name,sta.modify_datetime,"
					+ "sta.active,sta.project_oid,pro.project_name,sta.tenders_oid,te.tenders_name, sta.pipeline_oid,pi.pipeline_name,"
					+ "sta.pipe_station_oid,ps.pipe_station_name,u.unit_name as detection_unit_name,pu.unit_name as supervision_unit_name,"
					+ "to_char(sta.detection_date, 'YYYY-MM-DD') as detection_date,to_char(sta.collection_date, 'YYYY-MM-DD') as collection_date,"
					+ "case when sta.evaluation_result = 0 then '不合格' when sta.evaluation_result = 1 then '合格' else '' end as evaluation_result_name, "
					+ "case when sta.approve_status = -1 then '驳回' when sta.approve_status = 1 then '待审核' when sta.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name "
					+ "FROM daq_station_detection_ray sta "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = sta.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid =sta.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid =sta.tenders_oid "
					+ "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid=sta.pipe_station_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = sta.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = sta.detection_unit "
					+ "LEFT JOIN (select oid,weld_code from daq_station_process_weld where active=1 and approve_status=2) spw on spw.oid=sta.weld_oid "
					+ "left join (select code_id,code_name from sys_domain where active=1) d on d.code_id=sta.detection_type "
					+ "left join (select code_id,code_name from sys_domain where active=1) dom on dom.code_id=sta.evaluation_grade "
					+ "WHERE 1 = 1 AND sta.active = 1";
		sql += conditionSql();
		return sql;
	}

	private String conditionSql() {
		String conditionSql= "";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and sta.oid=:oid";
		}else{
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and sta.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and sta.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and sta.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeStationOid)) {
				conditionSql += " and sta.pipe_station_oid = :pipeStationOid";
			}
			if (StringUtils.isNotBlank(weldOid)) {
				conditionSql += " and sta.weld_oid = :weldOid";
			}
			conditionSql +=  this.dataAuthoritySql;
		}
		conditionSql += " order by sta.create_datetime desc";
		return conditionSql;
	}

	public String getOid() {
		return oid;
	}

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

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
	} 
	
	

}
