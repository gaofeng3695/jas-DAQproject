package cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.query.bo.StationVentStackBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：放空立管query。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月15日 上午11:11:33。</p>
 */
@QueryConfig(
		scene ="/ventStack/getPage",
		resultClass= StationVentStackBo.class,
		queryBeforeProcess = {
			@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
		}
)
public class StationVentStackQuery extends BaseJavaQuery {
	
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


	@Override
	public String getQuerySql() {
		String sql = "SELECT sta.oid,sta.device_code,sta.manufacture_number,mac.manufacture_number_name,mac.material_device_name,sta.pointx,sta.pointy,"
					+ "sta.POSITION,d.code_name as position_name, sta.distance,sta.construct_unit,sta.supervision_unit,sta.supervision_engineer,"
					+ "sta.collection_person,sta.approve_status,sta.geo_state,sta.remarks,sta.create_user_id,sta.create_user_name,sta.create_datetime,"
					+ "sta.modify_user_id,sta.modify_user_name,sta.modify_datetime,sta.active,sta.geom,sta.project_oid,pro.project_name,sta.tenders_oid,"
					+ "te.tenders_name, sta.pipeline_oid,pi.pipeline_name,sta.pipe_station_oid,ps.pipe_station_name,u.unit_name as construct_unit_name,"
					+ "pu.unit_name as supervision_unit_name,to_char(sta.construct_date, 'YYYY-MM-DD') as construct_date,"
					+ "to_char(sta.collection_date, 'YYYY-MM-DD') as collection_date,case when sta.approve_status = -1 then '驳回' "
					+ "when sta.approve_status = 1 then '待审核' when sta.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name "
					+ "FROM daq_station_vent_stack sta "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = sta.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid =sta.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid =sta.tenders_oid "
					+ "LEFT JOIN (select oid,pipe_station_name, active from daq_pipe_station where active=1) ps ON ps.oid=sta.pipe_station_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = sta.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = sta.construct_unit "
					+ "LEFT JOIN (select oid, manufacture_number as manufacture_number_name,device_name as material_device_name, active from daq_s_material_vent_stack where active=1) mac on mac.oid = sta.manufacture_number "
					+ "left join (select code_id,code_name from sys_domain where active=1) d on d.code_id=sta.POSITION "
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
			if (StringUtils.isNotBlank(deviceCode)) {
				conditionSql += " and sta.device_code like :deviceCode";
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

	public String getDeviceCode() {
		if (StringUtils.isNotBlank(deviceCode)) {
			return "%"+deviceCode+"%";
		}
		return null;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
}
