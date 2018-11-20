package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.query;



import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo.MedianStakeAppBo;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@QueryConfig(
	scene="/medianStake/getList",
	resultClass=MedianStakeAppBo.class
)
public class MedianStakeQueryList extends BaseJavaQuery{
	
	@Override
	public String getQuerySql() {
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		String sql = "select t.oid,t.project_oid,t.pipeline_oid,t.median_stake_code,t.mileage,st_x(t.geom) as pointx,st_y(t.geom) as pointy,st_z(t.geom) as pointz "
				+ "from daq_median_stake t "
				+ "inner join "
				+ "(select distinct i.project_oid "
					+ "from daq_implement_scope_ref i "
					+ "inner join "
						+ "(select oid from pri_unit p "
						+ "inner join "
						+ "(select hierarchy from pri_unit where oid='"+unitOid+"') pp "
						+ "on p.hierarchy like pp.hierarchy||'%' where p.active=1) "
					+ "pu on pu.oid = i.unit_oid) up "
				+ "on up.project_oid=t.project_oid where t.active=1 order by t.mileage";
		
		return sql;
	}
	
}
