package cn.jasgroup.jasframework.acquisitiondata.statistics.material.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess3.core.BaseNamedParameterJdbcTemplate;


/**
  * 
  *<p>类描述：物资统计dao。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年12月10日 下午5:51:29。</p>
 */
@Repository
public class MaterialStatisticsDao {
	
	@Resource
	private BaseNamedParameterJdbcTemplate baseNamedParameterJdbcTemplate;
	
	
	/***
	  * <p>功能描述：获取当前年之前的累计量。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param year
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 上午11:16:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public double getYearGrandTotal(List<Object> projectOids,String year){
		Map<String,Object> param = new HashMap<String,Object>();
		String sql = "select sum(pipe_length)/1000 as pipe_length from ("
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where t.active=1 and t.checked_date<to_date(:year,'yyyy') and t.project_oid in (:projectOid) "
				+ " union all "
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where t.active=1 and t.checked_date<to_date(:year,'yyyy') and t.project_oid in (:projectOid)) t";
		param.put("year", year);
		param.put("projectOid", projectOids);
		Map<String,Object> result =this.baseNamedParameterJdbcTemplate.queryForMapHump(sql, param);
		if(result!=null && result.containsKey("pipeLength")){
			return Double.parseDouble(result.get("pipeLength").toString());
		}
		return 0D;
	}
	
	/**
	  * <p>功能描述：按月统计。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param year
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 上午11:17:58。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMonthlyStatistics(List<Object> projectOids,String year){
		String sql = "select to_char(t.checked_date,'yyyy-MM') as date_time,coalesce(sum(tt.pipe_length),0)/1000 as pipe_length,'pipe' as type from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where to_char(t.checked_date,'yyyy')=:year and t.project_oid in (:projectOid) group by date_time "
				+ " union all "
				+ "select to_char(t.checked_date,'yyyy-MM') as date_time,coalesce(sum(tt.pipe_length),0)/1000 as pipe_length,'hot_pipe' as type from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where to_char(t.checked_date,'yyyy')=:year and t.project_oid in (:projectOid) group by date_time";
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("year", year);
		param.put("projectOid", projectOids);
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	
	/***
	  * <p>功能描述：获取当前月之前的累计量。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午2:26:51。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public double getMonthGrandTotal(List<Object> projectOids,String month){
		Map<String,Object> param = new HashMap<String,Object>();
		String sql = "select sum(pipe_length)/1000 as pipe_length from ("
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where t.active=1 and t.checked_date<to_date(:month,'yyyy-MM') and t.project_oid in (:projectOid) "
				+ " union all "
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where t.active=1 and t.checked_date<to_date(:month,'yyyy-MM') and t.project_oid in (:projectOid)) t";
		param.put("month", month);
		param.put("projectOid", projectOids);
		Map<String,Object> result =this.baseNamedParameterJdbcTemplate.queryForMapHump(sql, param);
		if(result!=null && result.containsKey("pipeLength")){
			return Double.parseDouble(result.get("pipeLength").toString());
		}
		return 0D;
	}
	
	/**
	  * <p>功能描述：按日统计。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午2:29:03。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getDailyStatistics(List<Object> projectOids,String month){
		String sql = "select to_char(t.checked_date,'yyyy-MM-dd') as date_time,coalesce(sum(tt.pipe_length),0)/1000 as pipe_length,'pipe' as type from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where to_char(t.checked_date,'yyyy-MM')=:month and t.project_oid in (:projectOid) group by date_time "
				+ " union all "
				+ "select to_char(t.checked_date,'yyyy-MM-dd') as date_time,coalesce(sum(tt.pipe_length),0)/1000 as pipe_length,'hot_pipe' as type from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where to_char(t.checked_date,'yyyy-MM')=:month and t.project_oid in (:projectOid) group by date_time";
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("month", month);
		param.put("projectOid", projectOids);
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	
	/***
	  * <p>功能描述：按标段统计。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOids
	  * @param dataTime
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午4:04:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getTendersStatistics(String projectOid,String dataTime){
		String sql = "select dt.tenders_name,coalesce(sum(tt.pipe_length),0)/1000 as pipe_length,'pipe' as type,dt.tenders_code from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid and t.checked_date<=to_date(:dataTime, 'yyyy-MM-dd') and t.project_oid=:projectOid right join (select oid,tenders_name,tenders_code from daq_tenders where active=1 and project_oid=:projectOid) dt on dt.oid=t.tenders_oid group by dt.oid,dt.tenders_code,dt.tenders_name"
				+ " union all "
				+ "select dt.tenders_name,coalesce(sum(tt.pipe_length),0)/1000 as pipe_length,'hot_pipe' as type,dt.tenders_code from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid and t.checked_date<=to_date(:dataTime, 'yyyy-MM-dd') and t.project_oid=:projectOid right join (select oid,tenders_name,tenders_code from daq_tenders where active=1 and project_oid=:projectOid) dt on dt.oid=t.tenders_oid group by dt.oid,dt.tenders_code,dt.tenders_name order by tenders_code";
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("dataTime", dataTime);
		param.put("projectOid", projectOid);
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
	
	/**
	  * <p>功能描述：物资使用情况统计。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param minDateTime
	  * @param maxDateTime
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午4:34:10。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialUseStatustics(String projectOid,String minDateTime,String maxDateTime){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("minDateTime", minDateTime);
		param.put("maxDateTime", maxDateTime);
		param.put("projectOid", projectOid);
		StringBuffer sql = new StringBuffer();
		
		sql.append("select dt.oid,dt.tenders_name,v.specifications,coalesce(sum(v.pipe_length),0)/1000 as pipe_length,'pipe' as pipe_type,'pipe_total_use' as data_type from daq_tenders dt left join ( select t.tenders_oid,mp.pipe_length,mp.specifications from daq_construction_weld t left join (select new_oid as oid,pipe_length,specifications from (select case when t.is_cold_bend=0 then t.oid else c.oid end as new_oid,t.pipe_length,rtrim(rtrim(cast(t.pipe_diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_pipe t  left join (select oid,pipe_oid from daq_material_pipe_cold_bending where active=1) c on c.pipe_oid=t.oid left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.grade left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1 ) v) mp on mp.oid=t.front_pipe_oid   where  t.collection_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and t.active=1 and t.project_oid=:projectOid and t.approve_status=2 ) v on dt.oid=v.tenders_oid where dt.project_oid=:projectOid group by dt.oid,dt.tenders_name,v.specifications");
		sql.append(" union all ");
		sql.append("select dt.oid,dt.tenders_name,v.specifications,coalesce(sum(v.pipe_length),0)/1000 as pipe_length,'pipe' as pipe_type,'pipe_total_receive' as data_type from daq_tenders dt left join ( select t.tenders_oid,t.checked_date,mp.pipe_length,mp.specifications from daq_check_coating_pipe t  left join (select t.oid,t.pipe_length,rtrim(rtrim(cast(t.pipe_diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_pipe t left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.grade left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1) mp on mp.oid=t.pipe_oid  where  t.checked_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and t.active=1 and t.project_oid=:projectOid ) v on dt.oid=v.tenders_oid where dt.project_oid=:projectOid group by dt.oid,dt.tenders_name,v.specifications");
		sql.append(" union all ");
		sql.append("select dt.oid,dt.tenders_name,v.specifications,coalesce(sum(v.pipe_length),0)/1000 as pipe_length,'pipe' as pipe_type,'pipe_month_use' as data_type from daq_tenders dt left join ( select t.tenders_oid,mp.pipe_length,mp.specifications from daq_construction_weld t left join ( select new_oid as oid,pipe_length,specifications from (select case when t.is_cold_bend=0 then t.oid else c.oid end as new_oid,t.pipe_length,rtrim(rtrim(cast(t.pipe_diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_pipe t  left join (select oid,pipe_oid from daq_material_pipe_cold_bending where active=1) c on c.pipe_oid=t.oid left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.grade left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1 ) v ) mp on mp.oid=t.front_pipe_oid   where  t.collection_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and t.collection_date>=to_date(:minDateTime, 'yyyy-MM-dd') and t.active=1 and t.project_oid=:projectOid and t.approve_status=2 ) v on dt.oid=v.tenders_oid where dt.project_oid=:projectOid group by dt.oid,dt.tenders_name,v.specifications");
		sql.append(" union all ");
		sql.append("select dt.oid,dt.tenders_name,v.specifications,coalesce(sum(v.pipe_length),0)/1000 as pipe_length,'pipe' as pipe_type,'pipe_month_receive' as data_type from daq_tenders dt  left join ( select t.tenders_oid,t.checked_date,mp.pipe_length,mp.specifications from daq_check_coating_pipe t left join (select t.oid,t.pipe_length,rtrim(rtrim(cast(t.pipe_diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_pipe t left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.grade left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1) mp on mp.oid=t.pipe_oid   where  t.checked_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and t.checked_date>=to_date(:minDateTime, 'yyyy-MM-dd') and t.active=1 and t.project_oid=:projectOid ) v on dt.oid=v.tenders_oid where dt.project_oid=:projectOid group by dt.oid,dt.tenders_name,v.specifications");
		sql.append(" union all ");
		
		sql.append("select dt.oid,dt.tenders_name,v.specifications,count(v.specifications) as pipe_num,'hot_pipe' as pipe_type,'h_pipe_total_receive' as data_type from daq_tenders dt left join( select ch.tenders_oid,ch.checked_date,h.specifications from daq_check_hot_bends ch  left join ( select t.oid,rtrim(rtrim(cast(t.diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',btrim(cast(t.angle_bending as varchar),'.0'),s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_hot_bends t   left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method  left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.pipe_grade  left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1  ) h on h.oid=ch.hot_bends_oid where ch.checked_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and ch.active=1 and ch.project_oid=:projectOid ) v on v.tenders_oid=dt.oid where dt.project_oid=:projectOid group by dt.oid,dt.tenders_name,v.specifications");
		sql.append(" union all ");
		sql.append("select dt.oid,dt.tenders_name,v.specifications,count(v.specifications) as pipe_num,'hot_pipe' as pipe_type,'h_pipe_month_receive' as data_type from daq_tenders dt left join( select ch.tenders_oid,ch.checked_date,h.specifications from daq_check_hot_bends ch  left join ( select t.oid,rtrim(rtrim(cast(t.diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',btrim(cast(t.angle_bending as varchar),'.0'),s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_hot_bends t   left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method  left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.pipe_grade  left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1  ) h on h.oid=ch.hot_bends_oid where ch.checked_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and ch.checked_date>=to_date(:minDateTime, 'yyyy-MM-dd') and ch.active=1 and ch.project_oid=:projectOid ) v on v.tenders_oid=dt.oid where dt.project_oid=:projectOid group by dt.oid,dt.tenders_name,v.specifications");
		sql.append(" union all ");
		sql.append("select dt.oid,dt.tenders_name,v.specifications,count(v.specifications) as pipe_num,'hot_pipe' as pipe_type,'h_pipe_total_use' as data_type from daq_tenders dt left join( select ch.tenders_oid,ch.collection_date,h.specifications from daq_construction_weld ch  left join ( select t.oid,rtrim(rtrim(cast(t.diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',btrim(cast(t.angle_bending as varchar),'.0'),s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_hot_bends t   left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method  left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.pipe_grade  left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1  ) h on h.oid=ch.front_pipe_oid where ch.collection_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and ch.active=1 and ch.project_oid=:projectOid ) v on v.tenders_oid=dt.oid where dt.project_oid=:projectOid group by dt.oid,dt.tenders_name,v.specifications");
		sql.append(" union all ");
		sql.append("select dt.oid,dt.tenders_name,v.specifications,count(v.specifications) as pipe_num,'hot_pipe' as pipe_type,'h_pipe_month_use' as data_type from daq_tenders dt left join( select ch.tenders_oid,ch.collection_date,h.specifications from daq_construction_weld ch  left join ( select t.oid,rtrim(rtrim(cast(t.diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',btrim(cast(t.angle_bending as varchar),'.0'),s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_hot_bends t   left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method  left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.pipe_grade  left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1  ) h on h.oid=ch.front_pipe_oid where ch.collection_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and ch.collection_date>=to_date(:minDateTime, 'yyyy-MM-dd')and ch.active=1 and ch.project_oid=:projectOid ) v on v.tenders_oid=dt.oid where dt.project_oid=:projectOid group by dt.oid,dt.tenders_name,v.specifications");
		
		
		sql.append(" order by tenders_name,specifications");
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql.toString(), param);
	}
	/**
	  * <p>功能描述：物资使用情况总合计统计。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param minDateTime
	  * @param maxDateTime
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 上午9:40:26。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getMaterialUseTotalStatustics(String projectOid,String minDateTime,String maxDateTime){
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("minDateTime", minDateTime);
		param.put("maxDateTime", maxDateTime);
		param.put("projectOid", projectOid);
		StringBuffer sql = new StringBuffer();
		
		sql.append("select mp.specifications,coalesce(sum(mp.pipe_length),0)/1000 as pipe_length,'pipe' as pipe_type,'pipe_total_use' as data_type from daq_construction_weld t left join (select new_oid as oid,pipe_length,specifications from (select case when t.is_cold_bend=0 then t.oid else c.oid end as new_oid,t.pipe_length,rtrim(rtrim(cast(t.pipe_diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_pipe t  left join (select oid,pipe_oid from daq_material_pipe_cold_bending where active=1) c on c.pipe_oid=t.oid left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.grade left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1 ) v) mp on mp.oid=t.front_pipe_oid   where  t.collection_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and t.active=1 and t.project_oid=:projectOid and t.approve_status=2 group by mp.specifications");
		sql.append(" union all ");
		sql.append("select mp.specifications,coalesce(sum(mp.pipe_length),0)/1000 as pipe_length,'pipe' as pipe_type,'pipe_total_receive' as data_type from daq_check_coating_pipe t  left join (select t.oid,t.pipe_length,rtrim(rtrim(cast(t.pipe_diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_pipe t left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.grade left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1) mp on mp.oid=t.pipe_oid  where  t.checked_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and t.active=1 and t.project_oid=:projectOid  group by mp.specifications");
		sql.append(" union all ");
		sql.append("select mp.specifications,coalesce(sum(mp.pipe_length),0)/1000 as pipe_length,'pipe' as pipe_type,'pipe_month_use' as data_type from daq_construction_weld t left join ( select new_oid as oid,pipe_length,specifications from (select case when t.is_cold_bend=0 then t.oid else c.oid end as new_oid,t.pipe_length,rtrim(rtrim(cast(t.pipe_diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_pipe t  left join (select oid,pipe_oid from daq_material_pipe_cold_bending where active=1) c on c.pipe_oid=t.oid left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.grade left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1 ) v ) mp on mp.oid=t.front_pipe_oid   where  t.collection_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and t.collection_date>=to_date(:minDateTime, 'yyyy-MM-dd') and t.active=1 and t.project_oid=:projectOid and t.approve_status=2 group by mp.specifications");
		sql.append(" union all ");
		sql.append("select mp.specifications,coalesce(sum(mp.pipe_length),0)/1000 as pipe_length,'pipe' as pipe_type,'pipe_month_receive' as data_type from daq_check_coating_pipe t left join (select t.oid,t.pipe_length,rtrim(rtrim(cast(t.pipe_diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_pipe t left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.grade left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1) mp on mp.oid=t.pipe_oid   where  t.checked_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and t.checked_date>=to_date(:minDateTime, 'yyyy-MM-dd') and t.active=1 and t.project_oid=:projectOid  group by mp.specifications");
		sql.append(" union all ");
		
		sql.append("select h.specifications,count(h.specifications) as pipe_num,'hot_pipe' as pipe_type,'h_pipe_total_receive' as data_type from daq_check_hot_bends ch  left join ( select t.oid,rtrim(rtrim(cast(t.diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',btrim(cast(t.angle_bending as varchar),'.0'),s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_hot_bends t   left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method  left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.pipe_grade  left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1  ) h on h.oid=ch.hot_bends_oid where ch.checked_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and ch.active=1 and ch.project_oid=:projectOid group by h.specifications");
		sql.append(" union all ");
		sql.append("select h.specifications,count(h.specifications) as pipe_num,'hot_pipe' as pipe_type,'h_pipe_month_receive' as data_type from daq_check_hot_bends ch  left join ( select t.oid,rtrim(rtrim(cast(t.diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',btrim(cast(t.angle_bending as varchar),'.0'),s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_hot_bends t   left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method  left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.pipe_grade  left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1  ) h on h.oid=ch.hot_bends_oid where ch.checked_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and ch.checked_date>=to_date(:minDateTime, 'yyyy-MM-dd') and ch.active=1 and ch.project_oid=:projectOid group by h.specifications");
		sql.append(" union all ");
		sql.append("select h.specifications,count(h.specifications) as pipe_num,'hot_pipe' as pipe_type,'h_pipe_total_use' as data_type from daq_construction_weld ch  left join ( select t.oid,rtrim(rtrim(cast(t.diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',btrim(cast(t.angle_bending as varchar),'.0'),s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_hot_bends t   left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method  left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.pipe_grade  left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1  ) h on h.oid=ch.front_pipe_oid where ch.collection_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and ch.active=1 and ch.project_oid=:projectOid group by h.specifications");
		sql.append(" union all ");
		sql.append("select h.specifications,count(h.specifications) as pipe_num,'hot_pipe' as pipe_type,'h_pipe_month_use' as data_type from daq_construction_weld ch  left join ( select t.oid,rtrim(rtrim(cast(t.diameter as varchar),'0'),'.')||'x'||rtrim(rtrim(cast(t.wall_thickness as varchar),'0'),'.')||'(mm) '||concat_ws(' ',btrim(cast(t.angle_bending as varchar),'.0'),s.code_name,sd.code_name,ss.code_name) as specifications from daq_material_hot_bends t   left join (select code_id,code_name from sys_domain where domain_name='pipe_forming_method_domain') s on s.code_id=t.pipe_forming_method  left join (select code_id,code_name from sys_domain where domain_name='grade_domain' and active=1) sd on sd.code_id=t.pipe_grade  left join (select code_id,code_name from sys_domain where domain_name='coating_type_domain' and active=1) ss on ss.code_id=t.external_coating_type where t.active=1  ) h on h.oid=ch.front_pipe_oid where ch.collection_date<=to_date(:maxDateTime, 'yyyy-MM-dd') and ch.collection_date>=to_date(:minDateTime, 'yyyy-MM-dd')and ch.active=1 and ch.project_oid=:projectOid group by h.specifications");
		
		sql.append(" order by specifications");
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql.toString(), param);
	}
}
