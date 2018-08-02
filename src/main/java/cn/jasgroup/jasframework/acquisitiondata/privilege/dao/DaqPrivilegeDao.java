package cn.jasgroup.jasframework.acquisitiondata.privilege.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class DaqPrivilegeDao extends BaseJdbcDao{
	
	/***
	  * <p>功能描述：根据部门oid获取该部门及部门一下的项目列表。</p>
	  * <p> 雷凯。</p>	
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午1:59:46。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getProjectList(String unitOid,String pipeNetworkTypeCode){
		String sql = "with recursive pri_unit_temp(oid,parent_id) as ("
				+ "select t.oid,t.parent_id from pri_unit t where t.oid=? and t.active=1 "
				+ "union all "
				+ "select t.oid,t.parent_id from pri_unit t inner join pri_unit_temp b on t.parent_id=b.oid and t.active=1 "
				+ ")"
				+ "select distinct p.oid as key,p.project_name as value,p.create_datetime from daq_implement_scope_ref s left join (select oid,project_name,pipe_network_type_code,create_datetime from daq_project where active=1) p on s.project_oid=p.oid where s.unit_oid in (select oid from pri_unit_temp) "
				+ "and p.pipe_network_type_code=? order by p.create_datetime asc";
		return this.queryForList(sql, new Object[]{unitOid,pipeNetworkTypeCode});
	}
	
	/***
	  * <p>功能描述：根据部门oid获取干部们及一下部门的标段列表。</p>
	  * <p> 雷凯。</p>	
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午2:01:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getTendersList(String unitOid,String projectOid){
		String sql = "with recursive pri_unit_temp(oid,parent_id) as ("
				+ "select t.oid,t.parent_id from pri_unit t where t.oid=? and t.active=1 "
				+ "union all "
				+ "select t.oid,t.parent_id from pri_unit t inner join pri_unit_temp b on t.parent_id=b.oid and t.active=1 "
				+ ")"
				+ "select distinct tt.oid as key,tt.tenders_name as value,tt.create_datetime from daq_implement_scope_ref t left join (select oid,tenders_name,project_oid,create_datetime from daq_tenders where active=1) tt on t.tenders_oid=tt.oid where t.unit_oid in (select oid from pri_unit_temp) and tt.project_oid=?"
				+ " order by tt.create_datetime asc";
		return this.queryForList(sql, new Object[]{unitOid,projectOid});
	}
	/***
	  * <p>功能描述：根据项根据标段oid获取部门及部门一下的管线列表。</p>
	  * <p> 雷凯。</p>	
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午2:36:41。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getPipelineList(String tendersOid,String unitOid){
		String sql = "with recursive pri_unit_temp(oid,parent_id) as ("
				+ "select t.oid,t.parent_id from pri_unit t where t.oid='"+unitOid+"' and t.active=1 "
				+ "union all "
				+ "select t.oid,t.parent_id from pri_unit t inner join pri_unit_temp b on t.parent_id=b.oid and t.active=1 "
				+ ")"
				+ "select distinct t.oid as key,t.pipeline_name as value,t.create_datetime from daq_implement_scope_ref s left join (select oid,pipeline_name,create_datetime from daq_pipeline where active=1) t on t.oid=s.pipeline_oid where s.unit_oid in (select oid from pri_unit_temp)";
		if(StringUtils.isNotBlank(tendersOid)){
			sql += " and s.tenders_oid='"+tendersOid+"'";
		}
		 sql += " order by t.create_datetime asc";
		return this.queryForList(sql, null);
	}
	/***
	  * <p>功能描述：根据管线oid获取该部门及一下部门的线路段和穿跨越列表。</p>
	  * <p> 雷凯。</p>	
	  * @param PipelineOid
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午2:59:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getPipeSegmentOrCrossList(String pipelineOid,String unitOid){
		String sql = "with recursive pri_unit_temp(oid,parent_id) as ("
				+ "select t.oid,t.parent_id from pri_unit t where t.oid='"+unitOid+"' and t.active=1 "
				+ "union all "
				+ "select t.oid,t.parent_id from pri_unit t inner join pri_unit_temp b on t.parent_id=b.oid and t.active=1 "
				+ ")"
				+ "select distinct v.oid as key,v.name as value,v.type,v.create_datetime from v_daq_pipe_segment_cross v left join daq_implement_scope_ref t on t.scope_oid=v.oid where t.unit_oid in (select oid from pri_unit_temp)";
		if(StringUtils.isNotBlank(pipelineOid)){
			sql += " and t.pipeline_oid='"+pipelineOid+"'";
		}
		sql += "order by v.create_datetime asc";
		return this.queryForList(sql, null);
	}
	/***
	  * <p>功能描述：根据标段oid获取该标段下监理单位。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午2:03:55。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getSupervisionUnitByTendersOid(String tendersOid){
		String sql = "select distinct t.oid as key,t.unit_name as value,t.create_datetime from pri_unit t left join daq_implement_scope_ref i on t.oid = i.unit_oid where i.tenders_oid=? and t.hierarchy like 'Unit.0001.0004%' and t.active=1 order by t.create_datetime asc";
		return this.queryForList(sql, new Object[]{tendersOid});
	}
	/***
	  * <p>功能描述：根据标段oid获取该标段下施工单位。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月9日 上午11:13:18。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getConstructionUnitByTendersOid(String tendersOid){
		String sql = "select distinct t.oid as key,t.unit_name as value,t.create_datetime from pri_unit t left join daq_implement_scope_ref i on t.oid = i.unit_oid where i.tenders_oid=? and t.hierarchy like 'Unit.0001.0005%' and t.active=1 order by t.create_datetime asc";
		return this.queryForList(sql, new Object[]{tendersOid});
	}
	
	/***
	  * <p>功能描述：根据线路段oid或者穿跨越oid获取中线桩列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 上午11:18:33。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMedianStakeList(String pipeSegmentOrCrossOid){
		String sql ="select t.oid as key,t.median_stake_code as value from (select v.*,n.median_stake_code as start_median_stake_code,m.median_stake_code as end_median_stake_code from v_daq_pipe_segment_cross v left join (select oid,median_stake_code from daq_median_stake where active=1) n on n.oid=v.start_stake_oid left join (select oid,median_stake_code from daq_median_stake where active=1) m on m.oid=v.end_stake_oid) vv left join daq_median_stake t on t.median_stake_code>=vv.start_median_stake_code and t.median_stake_code<=vv.end_median_stake_code where vv.oid =? order by t.create_datetime asc";
		return this.queryForList(sql, new Object[]{pipeSegmentOrCrossOid});
	}
	
	/***
	  * <p>功能描述：根据管线oid和穿越类型获取当前用户所在部门及下级部门下的穿越列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipelineOid
	  * @param crossWay 穿越方式
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 下午6:15:06。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getCrossList(String pipelineOid,String crossWay,String unitOid){
		String sql = "with recursive pri_unit_temp(oid,parent_id) as ("
				+ "select t.oid,t.parent_id from pri_unit t where t.oid='"+unitOid+"' and t.active=1 "
				+ "union all "
				+ "select t.oid,t.parent_id from pri_unit t inner join pri_unit_temp b on t.parent_id=b.oid and t.active=1 "
				+ ")"
				+ "select distinct c.oid as key,c.cross_name as value,c.cross_length as length,c.create_datetime from daq_cross c left join daq_implement_scope_ref t on t.scope_oid=c.oid where t.unit_oid in (select oid from pri_unit_temp) ";
			sql += " and t.pipeline_oid='"+pipelineOid+"'";
			sql += "  and c.cross_way_code='"+crossWay+"'";
			sql += " order by c.create_datetime asc";
		return this.queryForList(sql, null);
	}
	/***
	  * <p>功能描述：根据管线oid获取当前用户所在部门及下级部门下的线路段列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipelineOid
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月11日 下午3:22:00。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getPipeSegmentList(String pipelineOid,String unitOid){
		String sql = "with recursive pri_unit_temp(oid,parent_id) as ("
				+ "select t.oid,t.parent_id from pri_unit t where t.oid='"+unitOid+"' and t.active=1 "
				+ "union all "
				+ "select t.oid,t.parent_id from pri_unit t inner join pri_unit_temp b on t.parent_id=b.oid and t.active=1 "
				+ ")"
				+ "select distinct s.oid as key,s.pipe_segment_code as value,s.create_datetime from daq_pipe_segment s left join daq_implement_scope_ref t on t.scope_oid=s.oid where t.unit_oid in (select oid from pri_unit_temp)";
			sql += " and t.pipeline_oid='"+pipelineOid+"'";
			sql += " order by s.create_datetime asc";
			return this.queryForList(sql, null);
	}
}
