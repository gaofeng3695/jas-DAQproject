package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsQueryBo;
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
	public List<ProgressStatsQueryBo> getWeldLengthStats(List<String> projectOids, String date) {
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
	public List<ProgressStatsQueryBo> getPetchLengthStats(List<String> projectOids, String date) {
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
	public List<ProgressStatsQueryBo> getBackFillLengthStats(List<String> projectOids, String date) {
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
	public List<ProgressStatsQueryBo> getLandRestorationLengthStats(List<String> projectOids, String date) {
		String sql = "select tt.*,p.project_name from "
				+ "(select 'lay_land_restoration' as stats_type,llr.project_oid, COALESCE(sum(llr.length), 0) as stats_result "
				+ "from daq_lay_land_restoration  llr "
				+ "where llr.active = 1 and llr.approve_status = 2 and llr.project_oid in (:projectOids) "
				+ "and to_char(llr.construct_date, 'yyyy-MM-dd') <= :date GROUP BY llr.project_oid) tt "
				+ "LEFT JOIN (select oid,project_name,active from daq_project where active=1) p ON p.oid=tt.project_oid";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids,"date",date), ProgressStatsQueryBo.class);
		return queryForList;
	}

	public List<Map<String, String>> getProjectList(List<String> projectOids) {
		String sql = "select oid, project_name from daq_project where active=1 and oid in (:projectOids)";
		List queryForList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids));
		return queryForList;
	}
	

}
