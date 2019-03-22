package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query;

import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query.bo.ReworkWeldAppBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@QueryConfig(scene = "/reworkWeld/getList", 
resultClass = ReworkWeldAppBo.class,
queryBeforeProcess = {
	 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
}
)
public class ReworkWeldListQuery extends BaseJavaQuery {
	
	@Override
	public String getQuerySql() {
		String sql = "select t.oid,t.project_oid,t.tenders_oid,t.pipeline_oid,t.pipe_segment_or_cross_oid,t.rework_weld_code,"
				+ "p.project_name,dt.tenders_name,dp.pipeline_name,vc.name as pipeSegmentOrCrossName,t.weld_oid,"
				+ "u.unit_name as construct_unit_name,pu.unit_name as supervision_unit_name,t.collection_date,t.weld_date,wu.work_unit_code,"
				+ "t.approve_status,t.is_measure,t.is_cut "
				+ "from daq_weld_rework_weld t "
				+ "left join(select oid,project_name from daq_project where active=1) p on p.oid=t.project_oid "
				+ "left join(select oid,tenders_name from daq_tenders where active=1) dt on dt.oid = t.tenders_oid "
				+ "left join(select oid,pipeline_name from daq_pipeline where active=1) dp on dp.oid=t.pipeline_oid "
				+ "left join(select oid,name from v_daq_pipe_segment_cross) vc on vc.oid=t.pipe_segment_or_cross_oid "
				+ "left join (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = t.supervision_unit "
				+ "left join (select oid, unit_name, active from pri_unit where active=1) u on u.oid = t.construct_unit "
				+ "left join (select oid, work_unit_code, active from daq_work_unit where active=1) wu ON wu.oid = t.work_unit_oid "
				+ "where t.active=1 and t.approve_status in (1,2)";
		sql += this.dataAuthoritySql;
		return sql;
	}
}
