package cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;

/**
 * 
  *<p>类描述：中低压统计dao。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年3月6日 下午5:18:01。</p>
 */
@Repository
public class MesolowStatsDao {

	 @Autowired
	 private CommonDataJdbcDao commonDataJdbcDao;

	 /**
	  * <p>功能描述：获取每月新增列表。</p>
	   * <p> 葛建。</p>	
	   * @param projectOid
	   * @param year
	   * @return
	   * @since JDK1.8。
	   * <p>创建日期:2019年3月6日 下午5:59:02。</p>
	   * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	  */
	public List<Map<String, Object>> getMonthlyGrowth(List<String> projectOids, String year) {
		String sql = "select t.month,COALESCE (sum(t.sum_per_month), 0) as sum_per_month  from  "
						+"(select to_char(collection_date,'yyyy-MM') as month,COALESCE(sum(pipe_section_length),0) as sum_per_month from daq_mv_pipe_section ps where active=1 and approve_status=2 and project_oid in (:projectOids) and to_char(collection_date,'yyyy')=:year GROUP BY to_char(collection_date,'yyyy-MM') "
						+"union ALL "
						+"select to_char(collection_date,'yyyy-MM') as month,COALESCE(sum(pipe_section_length),0) as sum_per_month from daq_mv_across_info ps where active=1 and approve_status=2 and project_oid in (:projectOids) and to_char(collection_date,'yyyy')=:year GROUP BY to_char(collection_date,'yyyy-MM') "
						+"union ALL "
						+"select to_char(collection_date,'yyyy-MM') as month,COALESCE(sum(pipe_section_length),0) as sum_per_month from daq_mv_stride_across_info ps where active=1 and approve_status=2 and project_oid in (:projectOids) and to_char(collection_date,'yyyy')=:year GROUP BY to_char(collection_date,'yyyy-MM')) t "
						+"GROUP BY t.month ";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(projectOids!=null && projectOids.size()>0){
			list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "year", year));
		}
		return list;
	}

	/**
	 * <p>功能描述：获取截止今年的累计长度。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param year
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月6日 下午6:34:14。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getTotalBefore(List<String> projectOids, String year) {
		String sql = "select COALESCE(sum(t.pipe_section_length),0) as total from ( "+
						"select pipe_section_length from daq_mv_pipe_section where active=1 and approve_status=2 and project_oid in (:projectOids) and to_char(collection_date, 'yyyy')<:year  "
						+"union ALL "
						+"select pipe_section_length from daq_mv_across_info where active=1 and approve_status=2 and project_oid in (:projectOids) and to_char(collection_date, 'yyyy')<:year "
						+"union ALL "
						+"select pipe_section_length from daq_mv_stride_across_info where active=1 and approve_status=2 and project_oid in (:projectOids) and to_char(collection_date, 'yyyy')<:year "
						+") t";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(projectOids!=null && projectOids.size()>0){
			list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "year", year));
		}
		return list;
	}

	/**
	 * <p>功能描述：查询各施工单位当月新增。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月7日 下午2:27:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getUnitAndMonthlyGrowth(String projectOid, String month) {
		String sql = "select u.unit_name,COALESCE(sum(t.pipe_section_length),0) as sum_per_unit from (select pipe_section_length,construct_unit from daq_mv_pipe_section where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy-MM')=:month "
						+ "UNION ALL "
						+ "select pipe_section_length,construct_unit from daq_mv_across_info where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy-MM')=:month "
						+ "union ALL "
						+ "select pipe_section_length,construct_unit from daq_mv_stride_across_info where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy-MM')=:month) t "
						+ "LEFT JOIN (select oid,unit_name from pri_unit where active=1) u on u.oid=t.construct_unit  "
						+ "GROUP BY u.unit_name";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid, "month", month));
		return list;
	}

	/**
	 * <p>功能描述：根据项目和年份查询各施工单位的月新增。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param year
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月7日 下午4:20:39。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMonthAndUnitAndMonthlyGrowth(String projectOid, String year) {
		String sql = "select m.oid,m.unit_name,'['||m.data||','''||coalesce(round(y.year_num,2),0)||''']' as result_data from ( " +
				"   select u.oid,u.unit_name,array_to_string (array_agg(''''||coalesce(round(v.num,2),0)||''''),',') as data from ( " +
				"         select * from ( " +
				"            select oid,unit_name,month,d.hierarchy from ( " +
				"            select distinct t.oid ,t.unit_name,t.hierarchy from pri_unit t left JOIN daq_implement_scope_ref i on t.oid=i.unit_oid where t.active=1 and t.hierarchy like 'Unit.0001.0005%' and i.project_oid=:projectOid ORDER BY t.hierarchy) d  " +
				"            left join ( select to_char(tt.day, 'yyyy-mm') as month from (select generate_series(to_date(:year||'-01','yyyy-MM'),to_date(:year||'-12','yyyy-MM'), '1 month') as day) as tt order by month ) pp on 1=1 " +
				"         ) vv order by vv.hierarchy,vv.month " +
				"      ) u  " +
				"      left join ( " +
				"         select construct_unit,sum(pipe_section_length) as num,to_char(d.collection_date,'yyyy-mm') as month from ( " +
				"            select pipe_section_length,construct_unit,collection_date from daq_mv_pipe_section where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy')=:year  " +
				"            UNION ALL  " +
				"            select pipe_section_length,construct_unit,collection_date from daq_mv_across_info where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy')=:year  " +
				"            union ALL  " +
				"            select pipe_section_length,construct_unit,collection_date from daq_mv_stride_across_info where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy')=:year " +
				"         ) d group by d.construct_unit,to_char(d.collection_date,'yyyy-mm') " +
				"      ) v on u.oid=construct_unit and v.month=u.month " +
				"      group by oid,unit_name,u.hierarchy order by u.hierarchy " +
				") m " +
				"left join ( " +
				"   select construct_unit,sum(pipe_section_length) as year_num from ( " +
				"      select pipe_section_length,construct_unit,collection_date from daq_mv_pipe_section where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy')=:year  " +
				"      UNION ALL  " +
				"      select pipe_section_length,construct_unit,collection_date from daq_mv_across_info where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy')=:year  " +
				"      union ALL  " +
				"      select pipe_section_length,construct_unit,collection_date from daq_mv_stride_across_info where active=1 and approve_status=2 and project_oid=:projectOid and to_char(collection_date, 'yyyy')=:year " +
				"   ) d group by d.construct_unit,to_char(d.collection_date,'yyyy') " +
				") y on m.oid=y.construct_unit ";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid, "year", year));
		return list;
	}

}
