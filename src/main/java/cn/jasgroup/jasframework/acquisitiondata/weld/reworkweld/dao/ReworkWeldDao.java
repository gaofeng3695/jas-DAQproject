package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao.entity.ReworkWeld;
import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

@Repository
public class ReworkWeldDao {
	
	@Resource
	private BaseJdbcTemplate baseJdbcTemplate;
	
	public void changeGeomColumn(String oid,String weldOid){
		String sql = "update daq_weld_rework_weld set geom = (select geom from daq_construction_weld where oid=?),geo_state=(select geo_state from daq_construction_weld where oid=?) where oid=?";
		this.baseJdbcTemplate.batchExecute(sql,new Object[]{weldOid,weldOid,oid});
	}
	
	public void changeWeldStatus(String weldOid,int status){
		String sql = "update daq_construction_weld set is_rework="+status+" where oid='"+weldOid+"'";
		this.baseJdbcTemplate.batchExecute(sql);
	}
	
	public ReworkWeld find(String oid){
		String sql = "select * from daq_weld_rework_weld where oid='"+oid+"'";
		List<ReworkWeld> list = this.baseJdbcTemplate.queryForList(sql, null, ReworkWeld.class);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
