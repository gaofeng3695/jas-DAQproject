package cn.jasgroup.jasframework.acquisitiondata.statistics.quality.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;

@Repository
public class QualityStatsDao {

	 @Autowired
	 private CommonDataJdbcDao commonDataJdbcDao;

	public List<Map<String, Object>> getMonthlyQualiifiedRateByProjectAndUnit(List<String> projectOids, List<String> unitOids, String year) {
		String sql = "select total_table.month,total_table.month_count,"
					+ "COALESCE (case when total_table.month = qualified_table.month then (qualified_table.qualified_count::NUMERIC)/(total_table.month_count::NUMERIC)*100 end, 0) as qualified_rate "
					+ "from (select to_char(ray.detection_deta,'MM') as month,count(weld.weld_code) as month_count "
					+ "from daq_detection_ray ray INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid "
					+ "where weld.active=1 and weld.approve_status=2 and ray.active=1 and ray.approve_status=2 and weld.project_oid in (:projectOids) "
					+ "and weld.construct_unit in (:unitOids) and to_char(ray.detection_deta,'yyyy') = :year "
					+ "GROUP BY to_char(ray.detection_deta,'MM')) as total_table "
					+ "LEFT JOIN (select to_char(ray.detection_deta,'MM') as month,count(weld.weld_code) as qualified_count from daq_detection_ray ray "
					+ "INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid where weld.active=1 and weld.approve_status=2 and ray.active=1 "
					+ "and ray.approve_status=2 and weld.project_oid in (:projectOids) and weld.construct_unit in (:unitOids) and to_char(ray.detection_deta,'yyyy') = :year "
					+ "and ray.detection_type='detection_type_code_001' and ray.evaluation_result=1 GROUP BY to_char(ray.detection_deta,'MM')) as qualified_table on qualified_table.month=total_table.month ";
		List list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"unitOids", unitOids,"year",year));
		return list;
	}

}
