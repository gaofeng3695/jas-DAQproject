package cn.jasgroup.jasframework.acquisitiondata.station.spatial.pressure.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.pressure.query.bo.StationPipePressureTestBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：站场管道试压query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月15日 上午9:17:40。</p>
 */
@QueryConfig(
		scene ="/pipePressureTest/getPage",
		resultClass= StationPipePressureTestBo.class,
		queryBeforeProcess = {
			@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
		}
)
public class StationPipePressureTestQuery extends BaseJavaQuery {
	
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

	@Override
	public String getQuerySql() {
		String sql = "SELECT sta.oid,sta.start_median_stake_oid,me.median_stake_code as start_median_stake_code,sta.start_relative_mileage,"
					+ "sta.end_median_stake_oid,med.median_stake_code as end_median_stake_code,sta.end_relative_mileage,sta.pressure_test_length,"
					+ "sta.pipe_specification,sta.pressure_test_medium,case when sta.pressure_test_medium = 1 then '水' "
					+ "when sta.pressure_test_medium = 2 then '空气' when sta.pressure_test_medium = 3 then '氮气' else '' end as pressure_test_medium_name,"
					+ "sta.design_pressure,sta.process_description,sta.pressure_test_result,"
					+ "case when sta.pressure_test_result = 0 then '管道强度、严密性试验经检查不合格' when sta.pressure_test_result = 1 then '管道强度、严密性试验经检查合格' else '' end as pressure_test_result_name,"
					+ "sta.construct_unit,sta.supervision_unit,sta.supervision_engineer,sta.collection_person,sta.approve_status,sta.geo_state,"
					+ "sta.remarks,sta.create_user_id,sta.create_user_name,sta.create_datetime,sta.modify_user_id,sta.modify_user_name,"
					+ "sta.modify_datetime,sta.active,sta.geom,sta.project_oid,pro.project_name,sta.tenders_oid,te.tenders_name, "
					+ "sta.pipeline_oid,pi.pipeline_name,sta.pipe_station_oid,ps.pipe_station_name,u.unit_name as construct_unit_name,"
					+ "pu.unit_name as supervision_unit_name,to_char(sta.pressure_test_date, 'YYYY-MM-DD') as pressure_test_date,"
					+ "to_char(sta.collection_date, 'YYYY-MM-DD') as collection_date,case when sta.approve_status = -1 then '驳回' "
					+ "when sta.approve_status = 1 then '待审核' when sta.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name "
					+ "FROM daq_station_pipe_pressure_test sta "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = sta.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid =sta.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid =sta.tenders_oid "
					+ "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid=sta.pipe_station_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = sta.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = sta.construct_unit "
					+ "LEFT JOIN (select oid,median_stake_code from daq_median_stake where active=1) me ON me.oid = sta.start_median_stake_oid "
					+ "LEFT JOIN (select oid,median_stake_code from daq_median_stake where active=1) med ON med.oid = sta.end_median_stake_oid "
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
			if (StringUtils.isNotBlank(startMedianStakeOid)) {
				conditionSql += " and sta.start_median_stake_oid = :startMedianStakeOid";
			}
			if (StringUtils.isNotBlank(endMedianStakeOid)) {
				conditionSql += " and sta.end_median_stake_oid = :endMedianStakeOid";
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
