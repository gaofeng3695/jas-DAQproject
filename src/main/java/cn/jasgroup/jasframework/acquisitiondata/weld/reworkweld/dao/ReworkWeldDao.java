package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

@Repository
public class ReworkWeldDao {
	
	@Resource
	private BaseJdbcTemplate baseJdbcTemplate;
	
	public void changeGeomColumn(String oid,String weldOid){
		String sql = "update daq_weld_rework_weld set geom = (select geom from daq_construction_weld where oid=?) where oid=?";
		this.baseJdbcTemplate.batchExecute(sql,new Object[]{weldOid,oid});
	}
}
