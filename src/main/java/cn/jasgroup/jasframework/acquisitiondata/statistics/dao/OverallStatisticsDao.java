package cn.jasgroup.jasframework.acquisitiondata.statistics.dao;

import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataEntryAuditBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DateStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.WeldInfoBo;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum.COLD_BEND;
import static cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum.HOT_BEND;
import static cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum.STRAIGHT_STEEL_PIPE;

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
     * 管线
     * @param projectOids 项目ID
     * @return List
     */
    public List<StatsResultBo> statsPipeLength(List<String> projectOids) {
        String sql =
                "select 'pipe' as stats_type, sum(result) as stats_result from ( " +
                        // 防腐管检查-钢管长度统计
                "  select sum(p.pipe_length) as result from daq_check_coating_pipe t " +
                "  left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectOids) " +
                "  union all " +
                        // 热煨弯管检查-热煨弯管长度统计
                "  select sum(p.pipe_length) as result from daq_check_hot_bends t " +
                "  left join daq_material_hot_bends p on t.hot_bends_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectOids) " +
                ") as ss";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids), StatsResultBo.class);
    }


    /**
     * 统计本年按月分类的管线长度
     * @param projectOids 项目ID
     * @return List
     */
    public List<DateStatsResultBo> statsPipeLengthMonthly(List<String> projectOids) {
        String sql = "" +
                " select 'pipe' as stats_type, stats_month as stats_month, sum(stats_result) as stats_result from ( " +
                "  select 'check_coating_pipe' as stats_type, EXTRACT(MONTH from t.create_datetime) as stats_month, sum(p.pipe_length) as stats_result from daq_check_coating_pipe t  " +
                "  left join daq_material_pipe p on t.pipe_oid = p.oid  " +
                "  where t.active = 1 and p.active = 1 and EXTRACT(YEAR from t.create_datetime) = EXTRACT(YEAR from current_date) --and t.project_oid in (:projectOids)  " +
                "  group by EXTRACT(MONTH from t.create_datetime)  " +
                "  union all  " +
                "  select 'check_hot_bends' as stats_type, EXTRACT(MONTH from t.create_datetime) as stats_month, sum(p.pipe_length) as stats_result from daq_check_hot_bends t  " +
                "  left join daq_material_hot_bends p on t.hot_bends_oid = p.oid  " +
                "  where t.active = 1 and p.active = 1 and EXTRACT(YEAR from t.create_datetime) = EXTRACT(YEAR from current_date) --and t.project_oid in (:projectOids)  " +
                "  group by EXTRACT(MONTH from t.create_datetime)  " +
                ") as ss group by stats_month";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids), DateStatsResultBo.class);
    }


    public List<StatsResultBo> statsPipeLength(String statsType, Map<String, Object> variables) {

        StringBuilder sql = new StringBuilder();
        boolean steelPipeEmpty = CollectionUtils.isEmpty((Collection<?>) variables.get(STRAIGHT_STEEL_PIPE.getCode()));
        boolean hotBendEmpty = CollectionUtils.isEmpty((Collection<?>) variables.get(HOT_BEND.getCode()));
        boolean coldBendEmpty = CollectionUtils.isEmpty((Collection<?>) variables.get(COLD_BEND.getCode()));

        if (!steelPipeEmpty) {
            sql.append(" select sum(pipe_length) as length from daq_material_pipe where active = 1 and project_oid in (:projectOids) and oid in (:").append(STRAIGHT_STEEL_PIPE.getCode()).append(")   ");
        }

        if (!hotBendEmpty) {
            sql.append(steelPipeEmpty ? "" : " union all ");
            sql.append(" select sum(pipe_length) as length from daq_material_hot_bends where active = 1 and project_oid in (:projectOids) and oid in (:").append(HOT_BEND.getCode()).append(")   ");
        }

        if (!coldBendEmpty) {
            sql.append((!steelPipeEmpty||!hotBendEmpty) ? " union all " : "");
            sql.append("  select sum(pipe_length) as length from daq_material_pipe_cold_bending where active = 1 and project_oid in (:projectOids) and oid in (:").append(COLD_BEND.getCode()).append(")   ");
        }

        if (StringUtils.isEmpty(sql.toString())) {
            return Lists.newArrayList(new StatsResultBo(statsType, 0));
        }

        sql.insert(0, " select '" +statsType+ "' as stats_type, sum(length) as stats_result from ( ").append(" ) as t ");
        return this.commonDataJdbcDao.queryForList(sql.toString(), variables, StatsResultBo.class);
    }

    public List<Map<String, Object>> queryPipeLength() {

        return null;
    }

    public List<StatsResultBo> queryPipeLengthOidIn(Map<String, List> params) {
        String sql = "";
        boolean steelPipeEmpty = CollectionUtils.isEmpty(params.get(STRAIGHT_STEEL_PIPE.getCode()));
        boolean hotBendEmpty = CollectionUtils.isEmpty(params.get(HOT_BEND.getCode()));
        boolean coldBendEmpty = CollectionUtils.isEmpty(params.get(COLD_BEND.getCode()));

        if (!steelPipeEmpty) {
            sql += " select oid, pipe_length as stats_result from daq_material_pipe where oid in (:" +params.get(STRAIGHT_STEEL_PIPE.getCode())+ ") ";
        }

        if (!hotBendEmpty) {
            sql += steelPipeEmpty ? "" : " union all ";
            sql += " select oid, pipe_length as stats_result from daq_material_hot_bends where oid in (:"+params.get(HOT_BEND.getCode())+") ";
        }

        if (!coldBendEmpty) {
            sql += (!steelPipeEmpty||!hotBendEmpty) ? " union all " : "";
            sql += " select oid, pipe_length as stats_result from daq_material_pipe_cold_bending where oid in ('') ";
        }

        return this.commonDataJdbcDao.queryForList(sql, params, StatsResultBo.class);
    }


    /**
     * 焊口信息
     */
    public List<WeldInfoBo> listWeldInfo(List<String> projectOids) {
        String sql = "select oid, front_pipe_type, front_pipe_oid, back_pipe_type, back_pipe_oid from daq_construction_weld " +
                " where active = 1 and project_oid in (:projectOids) and approve_status = 2 " +
                " and (front_pipe_type in (:statsTypes) or back_pipe_type in (:statsTypes) ) ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "statsTypes", statsTypes), WeldInfoBo.class);
    }


    // TODO: 补口关联焊口的sqL
    public List<WeldInfoBo> listWeldInfoCurrentYear(List<String> projectOids) {
        String sql = "select oid, EXTRACT(MONTH from create_datetime) as stats_month, front_pipe_type, front_pipe_oid, back_pipe_type, back_pipe_oid from daq_construction_weld " +
                " where active = 1 and project_oid in (:projectOids) and approve_status = 2 and EXTRACT(YEAR from create_datetime) = EXTRACT(YEAR from current_date)" +
                " and (front_pipe_type in (:statsTypes) or back_pipe_type in (:statsTypes) ) ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "statsTypes", statsTypes), WeldInfoBo.class);
    }


    /**
     * 查询补口关联焊口信息中的管件
     */
    public List<WeldInfoBo> listPatchRelaWeldInfo(List<String> projectOids) {
        String sql = "select t.oid, t.front_pipe_type, t.front_pipe_oid, t.back_pipe_type, t.back_pipe_oid from daq_construction_weld t " +
                " inner join daq_weld_anticorrosion_check c on t.oid = c.weld_oid " +
                " where t.active = 1 and t.approve_status = 2 and t.project_oid in (:projectOids) and c.project_oid in (:projectOids) " +
                " and c.active=1 and c.approve_status = 2 and (t.front_pipe_type in (:statsTypes) or t.back_pipe_type in (:statsTypes)) ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "statsTypes", statsTypes), WeldInfoBo.class);
    }

    public List<WeldInfoBo> listPatchRelaWeldInfoByYear(List<String> projectOids) {
        String sql = "select t.oid, t.front_pipe_type, t.front_pipe_oid, t.back_pipe_type, t.back_pipe_oid from daq_construction_weld t " +
                " inner join daq_weld_anticorrosion_check c on t.oid = c.weld_oid " +
                " where t.active = 1 and t.approve_status = 2 and t.project_oid in (:projectOids) and c.project_oid in (:projectOids) " +
                " and c.active=1 and c.approve_status = 2 and (t.front_pipe_type in (:statsTypes) or t.back_pipe_type in (:statsTypes)) ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids, "statsTypes", statsTypes), WeldInfoBo.class);
    }


    /**
     * 统计长度(测量放线, 管够回填, 地貌回复)
     * @param projectOids 项目ID
     * @return List
     */
    public List<StatsResultBo> statsOtherLength(List<String> projectOids) {
        String sql = "" +
                " select 'lay_surveying' as stats_type, sum(relative_mileage) as stats_result from daq_lay_surveying " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectOids) " +
                " union all " +
                " select 'lay_land_restoration' as stats_type, sum(length) as stats_result from daq_lay_land_restoration " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectOids) " +
                " union all " +
                " select 'lay_pipe_trench_backfill' as stats_type, sum(backfill_length) as stats_result from daq_lay_pipe_trench_backfill " +
                "where active = 1 and approve_status = 2 and project_oid in (:projectOids) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids), StatsResultBo.class);
    }


    /**
     * 查询当前年分类按月统计长度(测量放线, 管够回填, 地貌回复)
     * @param projectOids 项目ID
     * @return List
     */
    public List<DateStatsResultBo> statsOtherLengthMonthly(List<String> projectOids) {
        String sql = "" +
                " select 'lay_surveying' as stats_type, EXTRACT(MONTH from create_datetime) as stats_month, sum(relative_mileage) as ststs_result from daq_lay_surveying " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectOids) and EXTRACT(YEAR from create_datetime) = EXTRACT(YEAR from current_date) " +
                " group by EXTRACT(MONTH from create_datetime) " +
                " union all " +
                " select 'lay_land_restoration' as stats_type, EXTRACT(MONTH from create_datetime) as stats_month, sum(length) as ststs_result from daq_lay_land_restoration " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectOids) and EXTRACT(YEAR from create_datetime) = EXTRACT(YEAR from current_date) " +
                " group by EXTRACT(MONTH from create_datetime) " +
                " union all " +
                " select 'lay_pipe_trench_backfill' as stats_type, EXTRACT(MONTH from create_datetime) as stats_month, sum(backfill_length) as ststs_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectOids) and EXTRACT(YEAR from create_datetime) = EXTRACT(YEAR from current_date) " +
                " group by EXTRACT(MONTH from create_datetime) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids), DateStatsResultBo.class);
    }


    public List<DataEntryAuditBo> dataEntryAudit(List<String> projectOids) {

        String sqlFormat = "select count(*) as total, " +
                " sum(case when (approve_status=1 or approve_status=-1) then 1 else 0 end) as need_audit, " +
                " sum(case when (approve_status=2) then 1 else 0 end) as audited" +
                " from %s where active = 1 ";

        if (!CollectionUtils.isEmpty(projectOids)) {
            sqlFormat = sqlFormat.concat(" and project_oid in (:projectOids) ");
        }

        List<String> codeList = new ArrayList<>(ApproveStatisticsBlock.ALL.keySet());

        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < codeList.size(); i++) {
            sql.append(String.format(sqlFormat, ApproveStatisticsBlock.ALL.get(codeList.get(i)).getTableName()));
            sql.append(i<(codeList.size()-1) ? " UNION ALL ":"");
        }

        return commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("projectOids", projectOids), DataEntryAuditBo.class);
    }


    public List<Map<String, Integer>> statsDetectionRayPassCount(List<String> projectOids) {
        String sql = "select count(*) as count, sum(case when (detection_type='detection_type_code_001') then 1 else 0 end) as pass_count " +
                " from daq_detection_ray where active = 1 and project_oid in (:projectOids) and approve_status = 2 ";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectOids", projectOids));
    }
}
