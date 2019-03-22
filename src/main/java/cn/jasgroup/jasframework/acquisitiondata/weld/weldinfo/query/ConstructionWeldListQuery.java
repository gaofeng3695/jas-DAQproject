package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query;


import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query.bo.ConstructionWeldAppBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(
	scene = "/constructionWeld/getList", 
	resultClass = ConstructionWeldAppBo.class, 
	queryBeforeProcess = {
		@Process(service = "daqInjectService", method = "injectDataAuthoritySql(dataAuthoritySql)") 
	}
)
public class ConstructionWeldListQuery extends BaseJavaQuery {
	
	private String lastDataTime;

	@Override
	public String getQuerySql() {
		String sql = "select t.oid,t.project_oid,t.tenders_oid,t.pipeline_oid,t.pipe_segment_or_cross_oid,t.weld_code,"
					+ "st_x(t.geom) as pointx,st_y(t.geom) as pointy,st_z(t.geom) as pointz,st_m(t.geom) as mileage,"
					+ "p.project_name,dt.tenders_name,dp.pipeline_name,vpsc.name as pipe_segment_or_cross_name,t.is_anticorrosion_check,t.is_rework,"
					+ "u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, wu.work_unit_code,t.construct_date,"
//					+ "(t.is_measure+t.is_backfill+t.is_land_restoration+t.has_cut_pipe+t.has_reducer+t.has_bend_pipe) as is_detection,"
					+ "t.approve_status,t.is_measure,t.is_cut,t.median_stake_oid,t.relative_mileage,t.airflow_direction,t.sequence_number_first,t.sequence_number_second,"
					+ "case when t.airflow_direction=1 then '+' when t.airflow_direction=0 then '-' end as airflow_direction_name "
				+ "from daq_construction_weld t "
				+ "left join (select project_name,oid from daq_project where active=1) p on p.oid=t.project_oid "
				+ "left join (select oid,tenders_name from daq_tenders where active=1) dt on dt.oid=t.tenders_oid "
				+ "left join (select oid,pipeline_name from daq_pipeline where active=1) dp on dp.oid=t.pipeline_oid "
				+ "left join (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = t.pipe_segment_or_cross_oid "
				+ "left join (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = t.supervision_unit "
				+ "left join (select oid, unit_name, active from pri_unit where active=1) u on u.oid = t.construct_unit "
				+ "left join (select oid, work_unit_code, active from daq_work_unit where active=1) wu ON wu.oid = t.work_unit_oid "
				+ "where t.active=1 and t.approve_status in (1,2) ";
		sql += this.dataAuthoritySql;
		sql += " order by t.create_datetime desc";
		if(lastDataTime!=null){
			
		}
		return sql;
	}

	public String getLastDataTime() {
		return lastDataTime;
	}

	public void setLastDataTime(String lastDataTime) {
		this.lastDataTime = lastDataTime;
	}
	
}
