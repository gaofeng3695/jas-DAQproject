package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.dao;

import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.ApproveStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.MaterialStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsProcessEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.DataEntryAuditBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.DateStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/11 11:25
 */
@Repository
public class OverallStatisticsDao {

    @Autowired
    private CommonDataJdbcDao commonDataJdbcDao;


    /**
     * 管材长度统计
     * @param projectIds 项目ID集合
     * @return List
     */
    public StatsResultBo statsPipeLength(List<String> projectIds) {

        String sql =
                "select 'pipe' as stats_type, COALESCE(sum(result), 0) as stats_result from ( " +
                "  select COALESCE(sum(p.pipe_length), 0) as result from daq_check_coating_pipe t " +
                "  left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectIds) " +
                "  union all " +
                "  select COALESCE(sum(p.pipe_length), 0) as result from daq_check_hot_bends t " +
                "  left join daq_material_hot_bends p on t.hot_bends_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectIds) " +
                ") as ss";
        List resultList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), StatsResultBo.class);
        if (CollectionUtils.isEmpty(resultList)) {
            return new StatsResultBo(StatsProcessEnum.PIPE.getType(), 0);
        }

        return (StatsResultBo) resultList.get(0);
    }


    public List<StatsResultBo> statsWeldAndPatchLengthByDate(List<String> projectIds) {
        String sql = "" +
                " select 'weld' as stats_type, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_construction_weld w\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid in (:projectIds) \n" +
                " union all\n" +
                " select 'patch' as stats_type, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_weld_anticorrosion_check patch\n" +
                "   left join daq_construction_weld w on patch.weld_oid = w.oid and w.active = 1 and w.approve_status=2\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where patch.active=1 and patch.approve_status=2 and patch.project_oid in (:projectIds)\n";
        List<StatsResultBo> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), StatsResultBo.class);
        return list;
    }



    /**
     * 统计长度(测量放线, 管够回填, 地貌回复)
     * @param projectIds 项目ID集合
     * @return List
     */
    public List<StatsResultBo> statsOtherLength(List<String> projectIds) {
        String sql = "" +
                " select 'lay_surveying' as stats_type, COALESCE(sum(relative_mileage), 0) as stats_result from daq_lay_surveying " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " union all " +
                " select 'lay_land_restoration' as stats_type, COALESCE(sum(length), 0) as stats_result from daq_lay_land_restoration " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " union all " +
                " select 'lay_pipe_trench_backfill' as stats_type, COALESCE(sum(backfill_length), 0) as stats_result from daq_lay_pipe_trench_backfill " +
                "where active = 1 and approve_status = 2 and project_oid in (:projectIds) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), StatsResultBo.class);
    }



    /**
     * 根据年月分组统计管材长度
     * @param projectIds 项目ID
     * @return List
     */
    public List<DateStatsResultBo> statsPipeLengthGroupByYearMonth(List<String> projectIds) {
        String sql = "" +
                "select 'pipe' as stats_type, stats_date as stats_date, sum(stats_result) as stats_result from ( " +
                "  select to_char(t.checked_date, 'yyyy-MM') as stats_date, COALESCE(sum(p.pipe_length), 0) as stats_result " +
                "  from daq_check_coating_pipe t left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectIds) " +
                "  group by to_char(t.checked_date, 'yyyy-MM') " +
                "  union all " +
                "  select to_char(t.checked_date, 'yyyy-MM') as stats_date, COALESCE(sum(p.pipe_length), 0) as stats_result " +
                "  from daq_check_hot_bends t left join daq_material_hot_bends p on t.hot_bends_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectIds) " +
                "  group by to_char(t.checked_date, 'yyyy-MM') " +
                ") as ss group by stats_date";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), DateStatsResultBo.class);
    }


    /**
     * 查询当前年分类按月统计长度(测量放线, 管够回填, 地貌回复)
     * @param projectIds 项目ID
     * @return List
     */
    public List<DateStatsResultBo> statsOtherLengthGroupByYearMonth(List<String> projectIds) {
        String sql = "" +
                " select 'lay_surveying' as stats_type, to_char(construct_date, 'yyyy-MM') as stats_date, COALESCE(sum(relative_mileage), 0) as stats_result " +
                " from daq_lay_surveying where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " group by to_char(construct_date, 'yyyy-MM') " +
                " union all " +
                " select 'lay_land_restoration' as stats_type, to_char(construct_date, 'yyyy-MM') as stats_date, COALESCE(sum(length), 0) as stats_result " +
                " from daq_lay_land_restoration where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " group by to_char(construct_date, 'yyyy-MM') " +
                " union all " +
                " select 'lay_pipe_trench_backfill' as stats_type, to_char(construct_date, 'yyyy-MM') as stats_date, COALESCE(sum(backfill_length), 0) as stats_result " +
                " from daq_lay_pipe_trench_backfill where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " group by to_char(construct_date, 'yyyy-MM') ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), DateStatsResultBo.class);
    }


    public List<DateStatsResultBo> statsWeldLengthGroupByYearMonth(List<String> projectIds) {
        String sql = "" +
                " select 'weld' as stats_type, to_char(w.construct_date, 'yyyy-MM') as stats_date, \n" +
                " COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_construction_weld w\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid in (:projectIds)\n" +
                " group by to_char(w.construct_date, 'yyyy-MM')";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), DateStatsResultBo.class);
    }


    public List<DateStatsResultBo> statsPatchLengthGroupByYearMonth(List<String> projectIds) {
        String sql = "" +
                " select 'patch' as stats_type, to_char(patch.buckle_date, 'yyyy-MM') as stats_date,\n" +
                "   COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_weld_anticorrosion_check patch\n" +
                "   left join daq_construction_weld w on patch.weld_oid = w.oid and w.active = 1 and w.approve_status=2\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where patch.active=1 and patch.approve_status=2 and patch.project_oid in (:projectIds)\n" +
                " group by to_char(patch.buckle_date, 'yyyy-MM')";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), DateStatsResultBo.class);
    }


    public List<DataEntryAuditBo> dataEntryAudit(List<String> projectIds) {

        /*String sqlFormat = "" +
                " select count(*) as total, " +
                " sum(case when (approve_status=1 or approve_status=-1) then 1 else 0 end) as need_audit, " +
                " sum(case when (approve_status=2) then 1 else 0 end) as audited" +
                " from %s where active = 1 ";

        if (!CollectionUtils.isEmpty(projectIds)) {
            sqlFormat = sqlFormat.concat(" and project_oid in (:projectIds) ");
        }

        List<String> codeList = new ArrayList<>(ApproveStatisticsBlock.ALL.keySet());

        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < codeList.size(); i++) {
            sql.append(String.format(sqlFormat, ApproveStatisticsBlock.ALL.get(codeList.get(i)).getTableName()));
            sql.append(i<(codeList.size()-1) ? " UNION ALL ":"");
        }*/
        String sqlFormat = "" +
        		" select count(*) as total, " +
        		" count(*) as need_audit, " +
        		" sum(case when (approve_status=2) then 1 else 0 end) as audited" +
        		" from %s where active = 1 ";
        String sqlFormatMaterial = "" +
        		" select count(*) as total, " +
        		" 0 as need_audit, " +
        		" 0 as audited" +
        		" from %s where active = 1 ";
        if (!CollectionUtils.isEmpty(projectIds)) {
        	sqlFormat = sqlFormat.concat(" and project_oid in (:projectIds) ");
        	sqlFormatMaterial = sqlFormatMaterial.concat(" and project_oid in (:projectIds) ");
        }
        
        List<String> codeList = new ArrayList<>(ApproveStatisticsBlock.ALL.keySet());
        
        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < codeList.size(); i++) {
        	sql.append(String.format(sqlFormat, ApproveStatisticsBlock.ALL.get(codeList.get(i)).getTableName()));
        	sql.append(i<(codeList.size()-1) ? " UNION ALL ":"");
        }
        
        List<String> materialCodeList = new ArrayList<>(MaterialStatisticsBlock.ALL.keySet());
        sql.append(" UNION ALL ");
        for (int j = 0; j < materialCodeList.size(); j++) {
        	sql.append(String.format(sqlFormatMaterial, MaterialStatisticsBlock.ALL.get(materialCodeList.get(j)).getTableName()));
        	sql.append(j<(materialCodeList.size()-1) ? " UNION ALL ":"");
        }
        
        return commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("projectIds", projectIds), DataEntryAuditBo.class);
    }


    public List<Map<String, Integer>> statsDetectionRayPassCount(List<String> projectIds) {
        String sql = "" +
                " select count(*) as count, coalesce(sum(case when (detection_type='detection_type_code_001' and evaluation_result=1) then 1 else 0 end), 0) as pass_count " +
                " from daq_detection_ray where active = 1 and approve_status = 2 and project_oid in (:projectIds) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds));
    }
}
