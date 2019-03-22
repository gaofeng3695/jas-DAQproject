package cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.query;

import cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.query.bo.WeldMeasuredResultAppBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：焊口测量成果信息分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月11日 下午4:44:18。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 */
@QueryConfig(scene = "/weldMeasuredResult/getList", 
			 resultClass = WeldMeasuredResultAppBo.class,
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			 }
)
public class WeldMeasuredResultListQuery extends BaseJavaQuery {
	
	
	@Override
	public String getQuerySql() {
//		String sql = "select t.oid,t.project_oid,t.tenders_oid,t.pipeline_oid,t.pipe_segment_or_cross_oid,vw.weld_code,t.weld_oid,"
		String sql = "select t.oid,t.project_oid,t.tenders_oid,t.pipeline_oid,t.pipe_segment_or_cross_oid,"
				+ "st_x(t.geom) as pointx,st_y(t.geom) as pointy,st_z(t.geom) as pointz,st_m(t.geom) as mileage,"
				+ "p.project_name,dt.tenders_name,dp.pipeline_name,vc.name as pipeSegmentOrCrossName,t.measure_control_point_code "
				+ "from daq_weld_measured_result t "
				+ "left join(select oid,project_name from daq_project where active=1) p on p.oid=t.project_oid "
				+ "left join(select oid,tenders_name from daq_tenders where active=1) dt on dt.oid = t.tenders_oid "
				+ "left join(select oid,pipeline_name from daq_pipeline where active=1) dp on dp.oid=t.pipeline_oid "
				+ "left join(select oid,name from v_daq_pipe_segment_cross) vc on vc.oid=t.pipe_segment_or_cross_oid "
//				+ "left join(select oid,weld_code from v_daq_weld_info) vw on vw.oid=t.weld_oid "
				+ "where t.active=1  and t.approve_status=2 and measure_control_point_type='measure_control_point_type_code_001'";
		sql += this.dataAuthoritySql;
		return sql;
	}
}
