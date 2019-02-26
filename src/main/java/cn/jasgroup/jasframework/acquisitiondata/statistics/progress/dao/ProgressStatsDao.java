package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsQueryBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsResultBo;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;

@Repository
public class ProgressStatsDao {
	
	 @Autowired
	 private CommonDataJdbcDao commonDataJdbcDao;

	 /**
	  * <p>功能描述：根据项目分组，查询指定项目或日期下焊接长度。</p>
	   * <p> 葛建。</p>	
	   * @param projectOids
	   * @param date
	   * @return
	   * @since JDK1.8。
	   * <p>创建日期:2018年12月11日 上午11:06:11。</p>
	   * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	  */
	public List<ProgressStatsQueryBo> getWeldLengthStatsByProject(List<String> projectOids, String date) {
		String sql = "SELECT tt.*,p.project_name FROM ("
					+ "(SELECT 'weld' AS statsType,weld.project_oid,COALESCE (SUM(pipe.pipe_length), 0) + COALESCE (SUM(hot.pipe_length), 0) + COALESCE (SUM(cold.pipe_length), 0) AS stats_result "
					+ "FROM daq_construction_weld weld "
					+ "left join daq_material_pipe pipe on pipe.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_001' and pipe.active=1 "
					+ "left join daq_material_hot_bends hot on hot.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_002' and hot.active=1 "
					+ "left join daq_material_pipe_cold_bending cold on cold.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_008' and cold.active=1 "
					+ "WHERE weld.active = 1 AND weld.approve_status = 2 AND weld.project_oid IN (:projectOids) AND to_char(weld.construct_date,'yyyy-MM-dd') <= :date "
					+ "GROUP BY weld.project_oid)"
					+ ") tt LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：按项目分组，查询指定项目或时间下补口长度。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 上午11:07:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getPetchLengthStatsByProject(List<String> projectOids, String date) {
		String sql = "select tt.*,p.project_name from "
					+ "(select 'patch' as stats_type, patch.project_oid, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result"
					+ " from daq_weld_anticorrosion_check patch "
					+ "left join daq_construction_weld w on patch.weld_oid = w.oid and w.active = 1 and w.approve_status=2 "
					+ "left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1 "
					+ "left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1 "
					+ "left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1 "
					+ "where patch.active=1 and patch.approve_status=2 and patch.project_oid in (:projectOids) AND to_char(patch.buckle_date,'yyyy-MM-dd') <= :date "
					+ "GROUP BY patch.project_oid"
					+ ")tt LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：按项目分组，查询指定项目或时间下回填长度。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 上午11:07:49。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getBackFillLengthStatsByProject(List<String> projectOids, String date) {
		String sql = "select tt.*,p.project_name from "
					+ "(SELECT 'lay_pipe_trench_backfill' AS stats_type, project_oid, COALESCE (SUM(backfill_length), 0) AS stats_result "
					+ "FROM daq_lay_pipe_trench_backfill "
					+ "WHERE active = 1 AND approve_status = 2 AND project_oid IN (:projectOids) AND to_char(construct_date, 'yyyy-MM-dd') <= :date "
					+ "GROUP BY project_oid ) tt "
					+ "LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：按项目分组，查询指定项目或时间下地貌恢复长度。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 上午11:21:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getLandRestorationLengthStatsByProject(List<String> projectOids, String date) {
		String sql = "select tt.*,p.project_name from "
				+ "(select 'lay_land_restoration' as stats_type,llr.project_oid, COALESCE(sum(llr.length), 0) as stats_result "
				+ "from daq_lay_land_restoration  llr "
				+ "where llr.active = 1 and llr.approve_status = 2 and llr.project_oid in (:projectOids) "
				+ "and to_char(llr.construct_date, 'yyyy-MM-dd') <= :date GROUP BY llr.project_oid) tt "
				+ "LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据项目oids查询项目oid和对应的名称列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午2:46:28。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, String>> getProjectList(List<String> projectOids) {
		String sql = "select oid, project_name from daq_project where active=1 and oid in (:projectOids) order by project_code";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids));
		return queryForList;
	}
	
	/**
	 * <p>功能描述：根据项目分组，查询查询指定项目或时间下焊接口数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午3:06:20。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getWeldCountStatsByProject(List<String> projectOids, String date) {
		String sql = "SELECT tt.*,p.project_name FROM ("
					+ "(SELECT 'weld' AS statsType,weld.project_oid,COALESCE (count(weld.weld_code), 0) AS stats_result "
					+ "FROM daq_construction_weld weld "
					+ "WHERE weld.active = 1 AND weld.approve_status = 2 AND weld.project_oid IN (:projectOids) AND to_char(weld.construct_date,'yyyy-MM-dd') <= :date "
					+ "GROUP BY weld.project_oid)"
					+ ") tt LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据项目分组，查询查询指定项目或时间下补口口数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午3:08:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getPetchCountStatsByProject(List<String> projectOids, String date) {
		String sql = "select tt.*,p.project_name from "
					+ "(select 'patch' as stats_type, patch.project_oid, COALESCE(count(patch.weld_oid), 0) as stats_result"
					+ " from daq_weld_anticorrosion_check patch "
					+ "where patch.active=1 and patch.approve_status=2 and patch.project_oid in (:projectOids) AND to_char(patch.buckle_date,'yyyy-MM-dd') <= :date "
					+ "GROUP BY patch.project_oid"
					+ ")tt LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	
	/**
	 * <p>功能描述：根据项目分组，查询查询指定项目或时间下射线检测口数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午3:30:48。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getDetectionRayCountStatsByProject(List<String> projectOids, String date) {
		String sql = "SELECT tt.*, P .project_name FROM "
					+ "(SELECT 'detection_ray' AS stats_type, project_oid, COALESCE (count(weld_oid), 0) AS stats_result "
					+ "FROM daq_detection_ray "
					+ "WHERE active = 1 AND approve_status = 2 AND project_oid IN (:projectOids) AND to_char(detection_deta,'yyyy-MM-dd') <= :date "
					+ "GROUP BY project_oid "
					+ " ) tt LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据项目分组，查询查询指定项目或时间下焊口测量口数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午3:31:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getMeasuredResultCountStatsByProject(List<String> projectOids, String date) {
		String sql = "SELECT tt.*, P .project_name FROM "
					+ "(SELECT 'measured_result' AS stats_type, project_oid, COALESCE (count(weld_oid), 0) AS stats_result "
					+ "FROM daq_weld_measured_result "
					+ "WHERE active = 1 AND measure_control_point_type='measure_control_point_type_code_001' and approve_status = 2 AND project_oid IN (:projectOids) AND to_char(survey_date,'yyyy-MM-dd') <= :date "
					+ "GROUP BY project_oid"
					+ ") tt LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据项目oid查询项目下的标段oid及对应的名称列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午6:08:48。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, String>> getTendersList(String projectOid) {
		String sql = "select oid, tenders_name from daq_tenders where active=1 and project_oid = :projectOid order BY tenders_code";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid));
		return queryForList;
	}

	/**
	 * <p>功能描述：标段-工序分标段分月累计完成统计（km）。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param procedure
	  * @param beginMonth
	  * @param endMonth
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午6:25:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsResultBo> getCumulateStatsByProjectAndSingleItem(String projectOid, String procedure,
			String beginMonth, String endMonth) {
		String segmentSql = "";
		switch (procedure) {
		case "weld":
			segmentSql = "SELECT 'weld' AS statsType, weld.tenders_oid,to_char(weld.construct_date,'yyyy-MM') as month_of_year,"
						+ "COALESCE (SUM(pipe.pipe_length), 0) + COALESCE (SUM(hot.pipe_length), 0) + COALESCE (SUM(cold.pipe_length), 0) AS sum_per_month "
						+ "FROM daq_construction_weld weld "
						+ "left join daq_material_pipe pipe on pipe.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_001' and pipe.active=1 "
						+ "left join daq_material_hot_bends hot on hot.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_002' and hot.active=1 "
						+ "left join daq_material_pipe_cold_bending cold on cold.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_008' and cold.active=1 "
						+ "WHERE weld.active = 1 AND weld.approve_status = 2 AND weld.project_oid = :projectOid "
						+ "AND to_char(weld.construct_date,'yyyy-MM') BETWEEN :beginMonth and :endMonth "
						+ "GROUP by to_char(weld.construct_date,'yyyy-MM'),weld.tenders_oid ORDER BY MONTH_of_year";
			break;
		case "patch":
			segmentSql = "SELECT 'patch' AS statsType, patch.tenders_oid,to_char(patch.buckle_date,'yyyy-MM') as month_of_year,"
						+ "COALESCE (SUM(pipe.pipe_length), 0) + COALESCE (SUM(hot.pipe_length), 0) + COALESCE (SUM(cold.pipe_length), 0) AS sum_per_month "
						+ "FROM daq_weld_anticorrosion_check patch "
						+ "LEFT JOIN daq_construction_weld weld on weld.oid=patch.weld_oid and weld.active=1 and weld.approve_status=2 "
						+ "left join daq_material_pipe pipe on pipe.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_001' and pipe.active=1 "
						+ "left join daq_material_hot_bends hot on hot.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_002' and hot.active=1 "
						+ "left join daq_material_pipe_cold_bending cold on cold.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_008' and cold.active=1 "
						+ "WHERE patch.active = 1 AND patch.approve_status = 2 AND patch.project_oid = :projectOid "
						+ "AND to_char(patch.buckle_date,'yyyy-MM') BETWEEN :beginMonth and :endMonth "
						+ "GROUP by to_char(patch.buckle_date,'yyyy-MM'),patch.tenders_oid ORDER BY MONTH_of_year";
			break;
		case "lay_pipe_trench_backfill":
			segmentSql = "SELECT 'lay_pipe_trench_backfill' AS statsType, backfill.tenders_oid,to_char(backfill.construct_date,'yyyy-MM') as month_of_year,"
						+ "COALESCE (SUM(backfill_length), 0) AS sum_per_month "
						+ "FROM daq_lay_pipe_trench_backfill backfill "
						+ "WHERE backfill.active = 1 AND backfill.approve_status = 2 AND backfill.project_oid = :projectOid "
						+ "AND to_char(backfill.construct_date,'yyyy-MM') BETWEEN :beginMonth and :endMonth "
						+ "GROUP by to_char(backfill.construct_date,'yyyy-MM'),backfill.tenders_oid ORDER BY MONTH_of_year";
			break;
		case "lay_land_restoration":
			segmentSql = "SELECT 'lay_land_restoration' AS statsType, land.tenders_oid,to_char(land.construct_date,'yyyy-MM') as month_of_year,"
						+ "COALESCE (SUM(length), 0) AS sum_per_month "
						+ "FROM daq_lay_land_restoration land "
						+ "WHERE land.active = 1 AND land.approve_status = 2 AND land.project_oid = :projectOid "
						+ "AND to_char(land.construct_date,'yyyy-MM') BETWEEN :beginMonth and :endMonth "
						+ "GROUP by to_char(land.construct_date,'yyyy-MM'),land.tenders_oid ORDER BY MONTH_of_year";
			break;
		default:
			return null;
		}
		String sql = "SELECT tenders_oid,te.tenders_name,month_of_year,sum_per_month,"
					+ "(SELECT SUM (sum_per_month) FROM (" +segmentSql+ ") AS test_table_1 "
							+ "WHERE test_table_1.month_of_year <= test_table_2.month_of_year and test_table_1.tenders_oid=test_table_2.tenders_oid "
					+ ") AS total_by_month "
					+ "FROM (" +segmentSql+ ") AS test_table_2 "
					+ "LEFT JOIN (select oid,tenders_name from daq_tenders where active=1) te on te.oid=test_table_2.tenders_oid "
					+ "ORDER BY month_of_year;";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"beginMonth",beginMonth,"endMonth",endMonth),ProgressStatsResultBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据标段分组，查询指定项目或日期下焊接长度。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午4:45:00。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getWeldLengthStatsByTenders(String projectOid, String date) {
		String sql = "SELECT tt.*,p.tenders_name FROM ("
					+ "(SELECT 'weld' AS statsType,weld.tenders_oid,"
					+ "COALESCE(SUM(pipe.pipe_length), 0)+COALESCE(SUM(hot.pipe_length), 0)+COALESCE(SUM(cold.pipe_length), 0) AS stats_result "
					+ "FROM daq_construction_weld weld "
					+ "left join daq_material_pipe pipe on pipe.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_001' and pipe.active=1 "
					+ "left join daq_material_hot_bends hot on hot.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_002' and hot.active=1  "
					+ "left join daq_material_pipe_cold_bending cold on cold.oid = weld.front_pipe_oid and weld.front_pipe_type = 'pipe_type_code_008' and cold.active=1  "
					+ "WHERE weld.active = 1 AND weld.approve_status = 2 AND weld.project_oid = :projectOid "
					+ "AND to_char(weld.construct_date,'yyyy-MM-dd') <= :date GROUP BY weld.tenders_oid)"
					+ ") tt LEFT JOIN (select oid,tenders_name,active,create_datetime from daq_tenders where active=1) p ON p.oid=tt.tenders_oid order by p.create_datetime";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据标段分组，查询指定项目或日期下补口长度。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午5:27:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getPetchLengthStatsByTenders(String projectOid, String date) {
		String sql = "select tt.*,p.tenders_name from "
					+ "(select 'patch' as stats_type, patch.tenders_oid, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result"
					+ " from daq_weld_anticorrosion_check patch "
					+ "left join daq_construction_weld w on patch.weld_oid = w.oid and w.active = 1 and w.approve_status=2 "
					+ "left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1 "
					+ "left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1 "
					+ "left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1 "
					+ "where patch.active=1 and patch.approve_status=2 and patch.project_oid = :projectOid AND to_char(patch.buckle_date,'yyyy-MM-dd') <= :date "
					+ "GROUP BY patch.tenders_oid"
					+ ") tt LEFT JOIN (select oid,tenders_name,active,create_datetime from daq_tenders where active=1) p ON p.oid=tt.tenders_oid "
					+ "order by p.create_datetime";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据标段分组，查询指定项目或日期下管沟回填长度。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午5:36:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getBackFillLengthStatsByTenders(String projectOid, String date) {
		String sql = "select tt.*,p.tenders_name from "
					+ "(SELECT 'lay_pipe_trench_backfill' AS stats_type, tenders_oid, COALESCE (SUM(backfill_length), 0) AS stats_result "
					+ "FROM daq_lay_pipe_trench_backfill "
					+ "WHERE active = 1 AND approve_status = 2 AND project_oid = :projectOid AND to_char(construct_date, 'yyyy-MM-dd') <= :date "
					+ "GROUP BY tenders_oid  "
					+ ") tt LEFT JOIN (select oid,tenders_name,active,create_datetime from daq_tenders where active=1) p ON p.oid=tt.tenders_oid order by p.create_datetime";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据标段分组，查询指定项目或日期下地貌恢复长度。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午5:38:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getLandRestorationLengthStatsByTenders(String projectOid, String date) {
		String sql = "select tt.*,p.tenders_name from "
					+ "(select 'lay_land_restoration' as stats_type,llr.tenders_oid, COALESCE(sum(llr.length), 0) as stats_result "
					+ "from daq_lay_land_restoration  llr "
					+ "where llr.active = 1 and llr.approve_status = 2 and llr.project_oid = :projectOid "
					+ "and to_char(llr.construct_date, 'yyyy-MM-dd') <= :date GROUP BY llr.tenders_oid) tt "
					+ "LEFT JOIN (select oid,tenders_name,active,create_datetime from daq_tenders where active=1) p ON p.oid=tt.tenders_oid order by p.create_datetime";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据标段分组，查询查询指定项目或时间下焊接口数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午5:59:53。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getWeldCountStatsByTenders(String projectOid, String date) {
		String sql = "SELECT tt.*,p.tenders_name FROM ("
					+ "(SELECT 'weld' AS statsType,weld.tenders_oid,COALESCE (count(weld.weld_code), 0) AS stats_result "
					+ "FROM daq_construction_weld weld "
					+ "WHERE weld.active = 1 AND weld.approve_status = 2 AND weld.project_oid = :projectOid "
					+ "AND to_char(weld.construct_date,'yyyy-MM-dd') <= :date GROUP BY weld.tenders_oid)"
					+ ") tt LEFT JOIN (select oid,tenders_name,active,create_datetime from daq_tenders where active=1) p ON p.oid=tt.tenders_oid order by p.create_datetime";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据标段分组，查询查询指定项目或时间下补口口数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月13日 上午9:17:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getPetchCountStatsByTenders(String projectOid, String date) {
		String sql = "select tt.*,p.tenders_name from "
					+ "(select 'patch' as stats_type, patch.tenders_oid, COALESCE(count(patch.weld_oid), 0) as stats_result"
					+ " from daq_weld_anticorrosion_check patch "
					+ "where patch.active=1 and patch.approve_status=2 and patch.project_oid = :projectOid "
					+ "AND to_char(patch.buckle_date,'yyyy-MM-dd') <= :date "
					+ "GROUP BY patch.tenders_oid"
					+ ") tt LEFT JOIN (select oid,tenders_name,active,create_datetime from daq_tenders where active=1) p ON p.oid=tt.tenders_oid  order by p.create_datetime";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据标段分组，查询查询指定项目或时间下射线检测口数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月13日 上午9:20:38。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getDetectionRayCountStatsByTenders(String projectOid, String date) {
		String sql = "SELECT tt.*, P .tenders_name FROM "
					+ "(SELECT 'detection_ray' AS stats_type, tenders_oid, COALESCE (count(weld_oid), 0) AS stats_result "
					+ "FROM daq_detection_ray "
					+ "WHERE active = 1 AND approve_status = 2 AND project_oid = :projectOid AND to_char(detection_deta,'yyyy-MM-dd') <= :date "
					+ "GROUP BY tenders_oid "
					+ ") tt LEFT JOIN (select oid,tenders_name,active,create_datetime from daq_tenders where active=1) p ON p.oid=tt.tenders_oid  order by p.create_datetime";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	/**
	 * <p>功能描述：根据标段分组，查询查询指定项目或时间下焊口测量口数。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月13日 上午9:22:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<ProgressStatsQueryBo> getMeasuredResultCountStatsByTenders(String projectOid, String date) {
		String sql = "SELECT tt.*, P .tenders_name FROM "
					+ "(SELECT 'measured_result' AS stats_type, tenders_oid, COALESCE (count(weld_oid), 0) AS stats_result "
					+ "FROM daq_weld_measured_result "
					+ "WHERE active = 1 AND measure_control_point_type='measure_control_point_type_code_001' AND approve_status = 2 AND project_oid = :projectOid AND to_char(survey_date,'yyyy-MM-dd') <= :date "
					+ "GROUP BY tenders_oid"
					+ ") tt LEFT JOIN (select oid,tenders_name,active,create_datetime from daq_tenders where active=1) p ON p.oid=tt.tenders_oid  order by p.create_datetime";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOid", projectOid,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}
	

}
