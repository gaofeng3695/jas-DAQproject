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
	
	
	@SuppressWarnings("unchecked")
	public double getMaterialgrandTotal(String year){
		String sql = "select sum(pipe_length)/1000 as pipe_length from ("
				+ "select COALESCE(sum(tt.pipe_length),0) as pipe_length from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where t.active=1 and to_char(t.checked_date,'yyyy')<>:year "
				+ " union all "
				+ "select coalesce(sum(tt.pipe_length),0) as pipe_length from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where t.active=1 and to_char(t.checked_date,'yyyy')<>:year) t";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("year", year);
		Map<String,Object> result =this.baseNamedParameterJdbcTemplate.queryForMapHump(sql, param);
		if(result!=null && result.containsKey("pipeLength")){
			return Double.parseDouble(result.get("pipeLength").toString());
		}
		return 0D;
	}
	
	public List<Map<String,Object>> monthlyStatistics(String year){
		String sql = "select to_char(t.checked_date,'yyyy-MM') as date_time,sum(tt.pipe_length)/1000 as pipe_length,'pipe' as type from daq_check_coating_pipe t left join (select oid,pipe_length from daq_material_pipe where active=1) tt on t.pipe_oid=tt.oid where to_char(t.checked_date,'yyyy')=:year group by date_time "
				+ "union all "
				+ "select to_char(t.checked_date,'yyyy-MM') as date_time,sum(tt.pipe_length)/1000 as pipe_length,'hot_pipe' as type from daq_check_hot_bends t left join (select oid,pipe_length from daq_material_hot_bends where active=1) tt on t.hot_bends_oid=tt.oid where to_char(t.checked_date,'yyyy')=:year group by date_time";
		
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("year", year);
		return this.baseNamedParameterJdbcTemplate.queryForListHump(sql, param);
	}
}
