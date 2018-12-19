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

	 /**
	  * <p>功能描述：根据条件查询每月射线检测的焊口口数及对应的一次合格率。</p>
	   * <p> 葛建。</p>	
	   * @param projectOids
	   * @param unitOids
	   * @param year
	   * @return
	   * @since JDK1.8。
	   * <p>创建日期:2018年12月14日 下午2:04:03。</p>
	   * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	  */
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

	/**
	 * <p>功能描述：根据unitOids查询部门oid和对应的名称列表。</p>
	  * <p> 葛建。</p>	
	  * @param unitOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午1:58:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, String>> getUnitList(List<String> unitOids) {
		String sql = "select oid,unit_name from pri_unit where active=1 and oid in (:unitOids)";
		List list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("unitOids", unitOids));
		return list;
	}

	/**
	 * <p>功能描述：根据项目、unit和月份查询对应的单位下不同缺陷性质的个数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param unitOids
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午2:03:55。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getKindsOfDefectCountByProjects(List<String> projectOids, List<String> unitOids, String month) {
		String sql = "select tt.*,u.unit_name from "
					+ "(select weld.construct_unit,sub.defect_properties,count(sub.parent_oid) as count from daq_detection_ray_sub sub "
					+ "INNER JOIN (select oid, weld_oid, detection_deta, project_oid,detection_unit,active,approve_status from daq_detection_ray where active=1 and approve_status=2) ray on ray.oid=sub.parent_oid "
					+ "INNER JOIN (select oid,active,construct_unit from daq_construction_weld where active=1 and approve_status=2) weld on weld.oid=ray.weld_oid "
					+ "where ray.project_oid in (:projectOids) and weld.construct_unit in (:unitOids) and to_char(ray.detection_deta, 'yyyy-MM') <= :month "
					+ "GROUP BY weld.construct_unit,sub.defect_properties"
					+ ") tt LEFT JOIN (select * from pri_unit where active=1) u on u.oid=tt.construct_unit";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "unitOids", unitOids, "month", month));
		return list;
	}

	/**
	 * <p>功能描述：查询缺陷性质列表。</p>
	  * <p> 葛建。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午4:08:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, String>> getDefectList() {
		String sql = "select code_id as key,code_name as value from sys_domain where active=1 and domain_name = 'defect_properties_domain' ORDER BY code_id";
		return commonDataJdbcDao.queryForList(sql,null);
	}

	/**
	 * <p>功能描述：根据项目、unit和月份查询不同缺陷性质的占比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param unitOids
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 上午11:38:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getKindsOfDefectRateByProjects(List<String> projectOids, List<String> unitOids,
			String month) {
		String sql = "select tt.defect_properties,tt.count "
					+ "from (SELECT sub.defect_properties,count(sub.parent_oid) FROM	daq_detection_ray_sub sub INNER JOIN "
					+ "(SELECT oid,weld_oid,detection_deta,project_oid,detection_unit,active,approve_status FROM	daq_detection_ray WHERE	active = 1 AND approve_status = 2) ray ON ray.oid = sub.parent_oid "
					+ "INNER JOIN (select oid,active,construct_unit from daq_construction_weld where active=1 and approve_status=2) weld on weld.oid=ray.weld_oid "
					+ "WHERE ray.project_oid IN (:projectOids) AND weld.construct_unit IN (:unitOids) AND to_char(ray.detection_deta,'yyyy-MM') <= :month GROUP BY sub.defect_properties) tt";
		return commonDataJdbcDao.queryForList(sql,ImmutableMap.of("projectOids", projectOids, "unitOids", unitOids, "month", month));
	}

	/**
	 * <p>功能描述：根据项目、日期查询每个标段的检测口数和一次合格率。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 上午11:39:51。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getEachTendersQualifiedRateByProjects(String projectOid, String date) {
		String sql = "select total_table.tenders_oid,total_table.total_count,COALESCE (case when total_table.tenders_oid = qualified_table.tenders_oid then (qualified_table.qualified_count::NUMERIC)/(total_table.total_count::NUMERIC)*100 end, 0) as qualified_rate " 
					+ "from (select ray.tenders_oid,count(ray.weld_oid) as total_count from daq_detection_ray ray "
					+ "INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid where weld.active=1 and weld.approve_status=2 and ray.active=1 and ray.approve_status=2 "
					+ "and weld.project_oid = :projectOid and to_char(ray.detection_deta,'yyyy-MM-dd') <= :date GROUP BY ray.tenders_oid) total_table "
					+ "LEFT JOIN (select ray.tenders_oid,count(weld.weld_code) as qualified_count from daq_detection_ray ray INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid "
					+ "where weld.active=1 and weld.approve_status=2 and ray.active=1 and ray.approve_status=2 and weld.project_oid = :projectOid "
					+ "and to_char(ray.detection_deta,'yyyy-MM-dd') <= :date and ray.detection_type='detection_type_code_001' and ray.evaluation_result=1 "
					+ "GROUP BY ray.tenders_oid) qualified_table on qualified_table.tenders_oid=total_table.tenders_oid ";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid, "date", date));
		return list;
	}

	/**
	 * <p>功能描述：根据项目和日期查询各标段不合格口数占比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 上午11:40:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getEachTendersUnQualifiedRateByProjects(String projectOid, String date) {
		String sql = "select ray.tenders_oid,count(ray.weld_oid) as count "
					+ "from daq_detection_ray ray INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid where weld.active=1 and weld.approve_status=2 "
					+ "and ray.active=1 and ray.approve_status=2 and weld.project_oid = :projectOid and to_char(ray.detection_deta,'yyyy-MM-dd') <= :date "
					+ "and ray.evaluation_result=0 GROUP by ray.tenders_oid";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid, "date", date));
		return list;
	}

	/**
	 * <p>功能描述：根据项目和日期查询标段下施工单位的检测口数和一次合格率。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午2:20:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getEachUnitQualifiedRateByProjects(String projectOid, String date) {
		String sql = "select total_table.construct_unit,total_table.total_count,COALESCE (case when total_table.construct_unit = qualified_table.construct_unit "
					+ "then (qualified_table.qualified_count::NUMERIC)/(total_table.total_count::NUMERIC)*100 end, 0) as qualified_rate  "
					+ "from (select weld.construct_unit,count(weld.weld_code) as total_count from daq_detection_ray ray INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid "
					+ "where weld.active=1 and weld.approve_status=2 and ray.active=1 and ray.approve_status=2 and weld.project_oid = :projectOid "
					+ "and to_char(ray.detection_deta,'yyyy-MM-dd') <= :date GROUP BY weld.construct_unit ) total_table "
					+ "LEFT JOIN (select weld.construct_unit,count(weld.weld_code) as qualified_count from daq_detection_ray ray INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid "
					+ "where weld.active=1 and weld.approve_status=2 and ray.active=1 and ray.approve_status=2 and weld.project_oid = :projectOid "
					+ "and to_char(ray.detection_deta,'yyyy-MM-dd') <= :date and ray.detection_type='detection_type_code_001' and ray.evaluation_result=1 GROUP BY weld.construct_unit ) qualified_table "
					+ "on qualified_table.construct_unit=total_table.construct_unit "
					+ "LEFT JOIN (select oid,unit_name,active from pri_unit where active=1) u on u.oid=total_table.construct_unit";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid, "date", date));
		return list;
	}

	/**
	 * <p>功能描述：根据项目和日期查询各单位不合格口数占比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午2:56:39。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getEachUnitUnQualifiedRateByProjects(String projectOid, String date) {
		String sql = "SELECT tt.*,u.unit_name from (select weld.construct_unit,count(ray.weld_oid) as count ,count(ray.weld_oid)::NUMERIC/(select count(ray.weld_oid) from daq_detection_ray ray "
					+ "INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid where weld.active=1 and weld.approve_status=2 and ray.active=1 and ray.approve_status=2 "
					+ "and weld.project_oid = :projectOid and to_char(ray.detection_deta,'yyyy-MM-dd') <= :date and ray.evaluation_result=0)::NUMERIC*100 as un_qualified_rate "
					+ "from daq_detection_ray ray INNER JOIN daq_construction_weld weld on weld.oid=ray.weld_oid where weld.active=1 and weld.approve_status=2 and ray.active=1 "
					+ "and ray.approve_status=2 and weld.project_oid = :projectOid and to_char(ray.detection_deta,'yyyy-MM-dd') <= :date and ray.evaluation_result=0 "
					+ "GROUP by weld.construct_unit) tt LEFT JOIN (select oid,unit_name,active from pri_unit where active=1) u on u.oid=tt.construct_unit";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid, "date", date));
		return list;
	}

	/**
	 * <p>功能描述：根据项目查询项目下所有的施工单位。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午4:39:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getConstructUnitByProjectOid(String projectOid) {
		String sql = "select distinct t.oid as key,t.unit_name as value,t.hierarchy from pri_unit t left JOIN daq_implement_scope_ref i on t.oid=i.unit_oid "
					+ "where t.active=1 and t.hierarchy like 'Unit.0001.0005%' and i.project_oid=:projectOid ORDER BY t.hierarchy";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid));
		return list;
	}

	/**
	 * <p>功能描述：根据项目查询项目下的所有的建设单位。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月18日 下午4:38:08。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getAllProjectUnitByProjectOid(String projectOid) {
		String sql = "select DISTINCT t.oid as key,t.unit_name as value,t.hierarchy from pri_unit t left JOIN daq_implement_scope_ref i on t.oid=i.unit_oid "
					+ "where t.active=1 and t.hierarchy like 'Unit.0001.0001%' and i.project_oid=:projectOid ORDER BY t.hierarchy";
		List<Map<String, Object>> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid));
		return list;
	}

}
