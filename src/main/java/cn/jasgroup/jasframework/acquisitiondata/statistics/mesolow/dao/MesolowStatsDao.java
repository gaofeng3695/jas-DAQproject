package cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.dao;

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
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "year", year));
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
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "year", year));
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

}
