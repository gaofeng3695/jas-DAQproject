package cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class ColdBendingPipeDao extends BaseJdbcDao{
	
	public void chanageOriginalPipeUseState(String pipeCode){
		String sql = "update daq_material_pipe set is_cold_bend=1 where oid=?";
		this.update(sql, new Object[]{pipeCode});
	}
}
