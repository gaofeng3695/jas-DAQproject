package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class WeldDao extends BaseJdbcDao{
	
	public List<Map<String,Object>> getWeldList(String pipeSegmentOrCrossOid){
		String sql = "select t.oid as key,t.weld_code as value from daq_construction_weld t where t.active=1 and t.pipe_segment_or_cross_oid=?";
		return this.queryForList(sql, new Object[]{pipeSegmentOrCrossOid});
	}
	
}
