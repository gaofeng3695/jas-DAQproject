package cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao.entity.ColdBendingPipe;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class ColdBendingPipeDao extends BaseJdbcDao{
	
	public void chanageOriginalPipeUseState(int isColdeBend,String projectOid,String pipelineOid, String tendersOid, String pipeOid){
//		String sql = "update daq_material_pipe set is_cold_bend=1 where oid=?";
		String sql = "update daq_material_pipe set is_cold_bend=?, project_oid=?,pipeline_oid=?,tenders_oid=? where oid=?";
		this.update(sql, new Object[]{isColdeBend,projectOid,pipelineOid,tendersOid,pipeOid});
	}
	
	/***
	  * <p>功能描述：获取冷弯管下拉选列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月20日 下午3:12:41。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getList(String tendersOid){
		String sql = "select t.oid as key,t.pipe_cold_bending_code as value,t.tenders_oid,t.front_is_use,back_is_use,t.approve_status,t.pipe_segment_or_cross_oid "
				+ "from daq_material_pipe_cold_bending t "
				+ "where t.active=1 and is_check=0";
		if(StringUtils.isNotBlank(tendersOid)){
			sql += " and t.tenders_oid='"+tendersOid+"'";
		}
		return this.queryForList(sql, new Object[]{});
	}
	
	public ColdBendingPipe find(String oid){
		String sql = "select * from daq_material_pipe_cold_bending t where t.oid=?";
		List<ColdBendingPipe> list = this.queryForList(sql, new Object[]{oid}, ColdBendingPipe.class);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
