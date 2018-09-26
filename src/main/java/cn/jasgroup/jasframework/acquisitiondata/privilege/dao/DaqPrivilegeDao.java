package cn.jasgroup.jasframework.acquisitiondata.privilege.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
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
				+ "select distinct tt.oid as key,tt.tenders_name as value,tt.create_datetime,tt.project_oid from daq_implement_scope_ref t left join (select oid,tenders_name,project_oid,create_datetime from daq_tenders where active=1) tt on t.tenders_oid=tt.oid where t.unit_oid in (select oid from pri_unit_temp)";
				if(StringUtils.isNotBlank(projectOid)){
					sql += " and tt.project_oid='"+projectOid+"'";
				}
				sql += " order by tt.create_datetime asc";
		return this.queryForList(sql, new Object[]{unitOid});
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
				+ "select distinct t.oid as key,t.pipeline_name as value,t.create_datetime,s.tenders_oid from daq_implement_scope_ref s left join (select oid,pipeline_name,create_datetime from daq_pipeline where active=1) t on t.oid=s.pipeline_oid where s.unit_oid in (select oid from pri_unit_temp)";
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
				+ "select distinct v.oid as key,v.name as value,v.type,v.create_datetime,t.pipeline_oid from v_daq_pipe_segment_cross v left join daq_implement_scope_ref t on t.scope_oid=v.oid where t.unit_oid in (select oid from pri_unit_temp)";
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
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getSupervisionUnitByTendersOid(String tendersOid){
		String sql = "select distinct t.oid as key,t.unit_name as value,t.create_datetime,i.tenders_oid "
				+ "from pri_unit t "
				+ "left join daq_implement_scope_ref i on t.oid = i.unit_oid "
				+ "where t.hierarchy like 'Unit.0001.0004%' and t.active=1 ";
		if(StringUtils.isNotBlank(tendersOid)){
			sql += " and i.tenders_oid='"+tendersOid+"'";
		}
		sql += " order by t.create_datetime asc";
		return this.queryForList(sql, new Object[]{});
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
		String sql = "select distinct t.oid as key,t.unit_name as value,t.create_datetime,i.tenders_oid "
				+ "from pri_unit t "
				+ "left join daq_implement_scope_ref i on t.oid = i.unit_oid "
				+ "where t.hierarchy like 'Unit.0001.0005%' and t.active=1 ";
		if(StringUtils.isNotBlank(tendersOid)){
			sql +=" and i.tenders_oid='"+tendersOid+"'";
		}
		sql += "order by t.create_datetime asc";
		return this.queryForList(sql, new Object[]{});
	}
	/**
	  * <p>功能描述：根据项目oid获取施工单位列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午9:24:33。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getConstructionUnitByProjectOid(String projectOid){
		String sql = "select distinct t.oid as key,t.unit_name as value,t.create_datetime "
				+ "from pri_unit t "
				+ "left join daq_implement_scope_ref i on t.oid = i.unit_oid "
				+ "where t.hierarchy like 'Unit.0001.0005%' and t.active=1 ";
		if(StringUtils.isNotBlank(projectOid)){
			sql +=" and i.project_oid='"+projectOid+"'";
		}
		sql += "order by t.create_datetime asc";
		return this.queryForList(sql, new Object[]{});
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
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getMedianStakeList(String pipeSegmentOrCrossOid){
//		String sql ="select t.oid as key,t.median_stake_code as value,vv.oid as pipe_segment_or_cross_oid,vv.start_median_stake_code,vv.end_median_stake_code from ("
//				+ "select v.*,n.median_stake_code as start_median_stake_code,m.median_stake_code as end_median_stake_code "
//				+ "from v_daq_pipe_segment_cross v "
//				+ "left join (select oid,median_stake_code from daq_median_stake where active=1) n on n.oid=v.start_stake_oid "
//				+ "left join (select oid,median_stake_code from daq_median_stake where active=1) m on m.oid=v.end_stake_oid) vv "
//				+ "left join daq_median_stake t on t.median_stake_code>=vv.start_median_stake_code and t.median_stake_code<=vv.end_median_stake_code "
//				+ "where vv.active=1";
//				if(StringUtil.isNotBlank(pipeSegmentOrCrossOid)){
//					sql +=" and vv.oid ='"+pipeSegmentOrCrossOid+"'";
//				}
//				sql += " order by t.create_datetime asc";
		String sql = "select st.oid as key,st.median_stake_code as value,vt.start_stake_code,vt.end_stake_code "
				+ "from daq_median_stake st "
				+ "inner join ( "
					+ "select t.median_stake_code as start_stake_code,tt.median_stake_code as end_stake_code,t.pipeline_oid "
					+ "from v_daq_pipe_segment_cross v "
					+ "left join (select oid,pipeline_oid,median_stake_code,mileage from daq_median_stake where active=1) t on t.oid=v.start_stake_oid "
					+ "left join(select oid,median_stake_code,mileage from daq_median_stake where active=1) tt on tt.oid=v.end_stake_oid "
					+ "where v.oid='"+pipeSegmentOrCrossOid+"') vt "
					+ "on st.pipeline_oid=vt.pipeline_oid where st.active=1 order by st.mileage";
		return this.queryForList(sql, new Object[]{});
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
	
	/***
	  * <p>功能描述：根据监理单位获取对应标段下的施工单位和检测单位。</p>
	  * <p> 雷凯。</p>	
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午4:36:43。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getConstructAndDetectionUnitList(String unitOid,String projectOid){
		String sql = "select distinct tt.unit_oid as key,pp.unit_name as value,pp.type from daq_implement_scope_ref tt "
				+ "left join( "
					+ "select distinct t.tenders_oid from daq_implement_scope_ref t "
					+ "left join("
						+ "select p.oid from pri_unit p "
						+ "inner join (select hierarchy from pri_unit where oid='"+unitOid+"') pp on p.hierarchy like pp.hierarchy||'%') pu on pu.oid = t.unit_oid) uu on uu.tenders_oid = tt.tenders_oid "
				+ "inner join (select oid,unit_name,hierarchy,1 as type from pri_unit where hierarchy like 'Unit.0001.0005%' union all select oid,unit_name,hierarchy,2 as type from pri_unit where hierarchy like 'Unit.0001.0006%') pp on pp.oid=tt.unit_oid ";
			if(StringUtils.isNotBlank(projectOid)){
				sql +=" where tt.project_oid='"+projectOid+"' "; 
			}
			sql += "order by pp.unit_name";
		return this.queryForList(sql, null);
	}
	/**
	  * <p>功能描述：获取施工单位所有用户。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月11日 上午9:47:40。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getConstructUnitAllUser(){
		String sql = "select t.oid,t.user_name,t.login_name,t.password,t.unit_id,u.unit_name from pri_user t inner join (select oid,unit_name from pri_unit where hierarchy like 'Unit.0001.0005%' and active=1) u on u.oid=t.unit_id and t.active=1";
		return this.queryForList(sql, null);
	}

	/**
	 * <p>功能描述：根据管线oid获取当前用户所在部门及下级部门的站场/阀室列表。</p>
	  * <p> 葛建。</p>	
	  * @param pipelineOid
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午11:25:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPipeStationList(String pipelineOid, String unitOid) {
		String sql = "with recursive pri_unit_temp(oid,parent_id) as ("
				+ "select t.oid,t.parent_id from pri_unit t where t.oid='"+unitOid+"' and t.active=1 "
				+ "union all "
				+ "select t.oid,t.parent_id from pri_unit t inner join pri_unit_temp b on t.parent_id=b.oid and t.active=1 "
				+ ")"
				+ "select distinct t.oid as key,t.pipe_station_name as value,t.create_datetime from daq_implement_scope_ref s "
				+ "left join (select oid,pipe_station_name,create_datetime from daq_pipe_station where active=1) t on t.oid=s.scope_oid where s.unit_oid in (select oid from pri_unit_temp)";
		if (StringUtils.isNotBlank(pipelineOid)) {
			sql += " and s.pipeline_oid='"+pipelineOid+"'";
		}
		sql += " order by t.create_datetime asc";
		return this.queryForList(sql, null);
	}

}
