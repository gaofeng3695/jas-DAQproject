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
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where t.active=1 and t.checked_date<to_date(:year,'yyyy') and t.project_oid=:projectOid "
				+ " union all "
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where t.active=1 and t.checked_date<to_date(:year,'yyyy') and t.project_oid=:projectOid) t";
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
		String sql = "select to_char(t.checked_date,'yyyy-MM') as date_time,sum(tt.pipe_length)/1000 as pipe_length,'pipe' as type from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where to_char(t.checked_date,'yyyy')=:year and t.project_oid=:projectOid group by date_time "
				+ " union all "
				+ "select to_char(t.checked_date,'yyyy-MM') as date_time,sum(tt.pipe_length)/1000 as pipe_length,'hot_pipe' as type from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where to_char(t.checked_date,'yyyy')=:year and t.project_oid=:projectOid group by date_time";
		
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
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where t.active=1 and t.checked_date<to_date(:month,'yyyy-MM') and t.project_oid=:projectOid "
				+ " union all "
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where t.active=1 and t.checked_date<to_date(:month,'yyyy-MM') and t.project_oid=:projectOid) t";
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
		String sql = "select to_char(t.checked_date,'yyyy-MM-dd') as date_time,sum(tt.pipe_length)/1000 as pipe_length,'pipe' as type from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where to_char(t.checked_date,'yyyy-MM')=:month and t.project_oid=:projectOid group by date_time "
				+ " union all "
				+ "select to_char(t.checked_date,'yyyy-MM-dd') as date_time,sum(tt.pipe_length)/1000 as pipe_length,'hot_pipe' as type from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where to_char(t.checked_date,'yyyy-MM')=:month and t.project_oid=:projectOid group by date_time";
		
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
}
