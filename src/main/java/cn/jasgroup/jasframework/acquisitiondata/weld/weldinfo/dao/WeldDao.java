package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class WeldDao extends BaseJdbcDao{
	
	public List<Map<String,Object>> getWeldList(String pipeSegmentOrCrossOid){
//		String sql = "select t.oid as key,t.weld_code as value from daq_construction_weld t where t.active=1 and t.pipe_segment_or_cross_oid=? and approve_status=2";
		String sql = "select oid as key,weld_code as value from daq_construction_weld t where t.oid not in("
							+ "select w.oid from daq_construction_weld w inner join ("
								+ "select weld_oid from daq_weld_rework_weld where active=1"
							+ ") wr on wr.weld_oid=w.oid where w.active=1 and w.pipe_segment_or_cross_oid=?) "
							+ "and t.approve_status=2 and t.pipe_segment_or_cross_oid=? "
					+ "union all "
					+ "select oid as key,rework_weld_code as value from daq_weld_rework_weld where active=1 and approve_status=2 and pipe_segment_or_cross_oid=?";
		return this.queryForList(sql, new Object[]{pipeSegmentOrCrossOid,pipeSegmentOrCrossOid,pipeSegmentOrCrossOid});
	}
	
	@SuppressWarnings("unchecked")
	public ConstructionWeld find(String oid){
		String sql = "select * from daq_construction_weld t where t.oid=?";
		List<ConstructionWeld> list = this.queryForList(sql, new Object[]{oid}, ConstructionWeld.class);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
