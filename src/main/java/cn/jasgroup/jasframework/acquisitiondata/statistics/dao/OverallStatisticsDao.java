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

import java.util.*;

import static cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum.*;

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
    public StatsResultBo statsPipeLengthByType(List<String> projectIds, String queryDate) {

        Map<String, Object> params = new HashMap<>();
        params.put("projectIds", projectIds);

        String conditionSql = "";
        if (!StringUtils.isEmpty(queryDate)) {
            conditionSql = "and to_char(t.create_datetime , 'yyyy-MM-dd') = :queryDate";
            params.put("queryDate", queryDate);
        }
        String sql =
                "select 'pipe' as stats_type, sum(result) as stats_result from ( " +
                        // 防腐管检查-钢管长度统计
                "  select sum(p.pipe_length) as result from daq_check_coating_pipe t " +
                "  left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectIds) " + conditionSql +
                "  union all " +
                        // 热煨弯管检查-热煨弯管长度统计
                "  select sum(p.pipe_length) as result from daq_check_hot_bends t " +
                "  left join daq_material_hot_bends p on t.hot_bends_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectIds) " + conditionSql +
                ") as ss";
        List resultList = this.commonDataJdbcDao.queryForList(sql, params, StatsResultBo.class);
        if (CollectionUtils.isEmpty(resultList)) {
            return new StatsResultBo("pipe", 0);
        }

        return (StatsResultBo) resultList.get(0);
    }


    /**
     * 根据年月分组统计管材长度
     * @param projectIds 项目ID
     * @return List
     */
    public List<DateStatsResultBo> statsPipeLengthMonthly(List<String> projectIds) {
        String sql = "" +
                "select 'pipe' as stats_type, stats_date as stats_date, sum(stats_result) as stats_result from ( " +
                "  select 'check_coating_pipe' as stats_type, to_char(t.create_datetime, 'yyyy-MM') as stats_date, sum(p.pipe_length) as stats_result " +
                "  from daq_check_coating_pipe t left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectIds) " +
                "  group by to_char(t.create_datetime, 'yyyy-MM') " +
                "  union all " +
                "  select 'check_hot_bends' as stats_type, to_char(t.create_datetime, 'yyyy-MM') as stats_date, sum(p.pipe_length) as stats_result " +
                "  from daq_check_hot_bends t left join daq_material_hot_bends p on t.hot_bends_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid in (:projectIds) " +
                "  group by to_char(t.create_datetime, 'yyyy-MM') " +
                ") as ss group by stats_date";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), DateStatsResultBo.class);
    }


    public StatsResultBo statsPipeLengthByType(String statsType, Map<String, Object> variables) {

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
            return new StatsResultBo(statsType, 0);
        }

        sql.insert(0, " select '" +statsType+ "' as stats_type, sum(length) as stats_result from ( ").append(" ) as t ");
        List resultList = this.commonDataJdbcDao.queryForList(sql.toString(), variables, StatsResultBo.class);
        if (CollectionUtils.isEmpty(resultList)) {
            return new StatsResultBo(statsType, 0);
        }

        return (StatsResultBo) resultList.get(0);
    }


    public List<StatsResultBo> queryStraightPipeLength(Collection<String> idList) {
        String sql = " select oid as stats_type, coalesce(pipe_length, 0) as stats_result from daq_material_pipe where active = 1 and oid in (:idList) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("idList", idList), StatsResultBo.class);
    }

    public List<StatsResultBo> queryHotBendLength(Collection<String> idList) {
        String sql = " select oid as stats_type, coalesce(pipe_length, 0) as stats_result from daq_material_hot_bends where active = 1 and oid in (:idList) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("idList", idList), StatsResultBo.class);
    }

    public List<StatsResultBo> queryColdBendLength(Collection<String> idList) {
        String sql = " select oid as stats_type, coalesce(pipe_length, 0) as stats_result from daq_material_pipe_cold_bending where active = 1 and oid in (:idList) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("idList", idList), StatsResultBo.class);
    }



    /**
     * 查询要统计长度焊口信息
     */
    public List<WeldInfoBo> listWeldInfo(List<String> projectIds) {
        String sql = "select oid, front_pipe_type, front_pipe_oid, back_pipe_type, back_pipe_oid from daq_construction_weld " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " and (front_pipe_type in (:statsTypes) or back_pipe_type in (:statsTypes) ) ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds, "statsTypes", statsTypes), WeldInfoBo.class);
    }


    /**
     * 查询要统计长度焊口信息
     */
    public List<WeldInfoBo> listWeldInfoMonthly(List<String> projectIds) {
        String sql = "" +
                "select oid, to_char(create_datetime, 'yyyy-MM') as stats_date, front_pipe_type, front_pipe_oid, back_pipe_type, back_pipe_oid  " +
                "from daq_construction_weld " +
                "where active = 1 and project_oid in (:projectIds) and approve_status = 2  " +
                "and (front_pipe_type in (:statsTypes) or back_pipe_type in (:statsTypes) ) ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds, "statsTypes", statsTypes), WeldInfoBo.class);
    }


    /**
     * 查询补口关联焊口信息中的管件
     */
    public List<WeldInfoBo> listPatchRelationWeldInfo(List<String> projectIds) {
        String sql = "select t.oid, t.front_pipe_type, t.front_pipe_oid, t.back_pipe_type, t.back_pipe_oid from daq_construction_weld t " +
                " inner join daq_weld_anticorrosion_check c on t.oid = c.weld_oid " +
                " where t.active = 1 and t.approve_status = 2 and t.project_oid in (:projectIds) and c.project_oid in (:projectIds) " +
                " and c.active=1 and c.approve_status = 2 and (t.front_pipe_type in (:statsTypes) or t.back_pipe_type in (:statsTypes)) ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds, "statsTypes", statsTypes), WeldInfoBo.class);
    }

    public List<WeldInfoBo> listPatchRelationWeldInfoByYear(List<String> projectIds) {
        String sql = "" +
                " select t.oid, to_char(t.create_datetime, 'yyyy-MM') as stats_date, w.front_pipe_type, w.front_pipe_oid, w.back_pipe_type, w.back_pipe_oid " +
                " from daq_weld_anticorrosion_check t " +
                " left join daq_construction_weld w on t.weld_oid = w.oid " +
                " where t.active = 1 and t.approve_status = 2 and t.project_oid in (:projectIds) and w.project_oid in (:projectIds) " +
                " and w.active=1 and w.approve_status = 2 and (w.front_pipe_type in (:statsTypes) or w.back_pipe_type in (:statsTypes)) ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds, "statsTypes", statsTypes), WeldInfoBo.class);
    }


    /**
     * 统计长度(测量放线, 管够回填, 地貌回复)
     * @param projectIds 项目ID集合
     * @return List
     */
    public List<StatsResultBo> statsOtherLength(List<String> projectIds) {
        String sql = "" +
                " select 'lay_surveying' as stats_type, sum(relative_mileage) as stats_result from daq_lay_surveying " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " union all " +
                " select 'lay_land_restoration' as stats_type, sum(length) as stats_result from daq_lay_land_restoration " +
                " where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " union all " +
                " select 'lay_pipe_trench_backfill' as stats_type, sum(backfill_length) as stats_result from daq_lay_pipe_trench_backfill " +
                "where active = 1 and approve_status = 2 and project_oid in (:projectIds) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), StatsResultBo.class);
    }


    /**
     * 查询当前年分类按月统计长度(测量放线, 管够回填, 地貌回复)
     * @param projectIds 项目ID
     * @return List
     */
    public List<DateStatsResultBo> statsOtherLengthMonthly(List<String> projectIds) {
        String sql = "" +
                " select 'lay_surveying' as stats_type, to_char(create_datetime, 'yyyy-MM') as stats_date, sum(relative_mileage) as stats_result " +
                " from daq_lay_surveying where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " group by to_char(create_datetime, 'yyyy-MM') " +
                " union all " +
                " select 'lay_land_restoration' as stats_type, to_char(create_datetime, 'yyyy-MM') as stats_date, sum(length) as stats_result " +
                " from daq_lay_land_restoration where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " group by to_char(create_datetime, 'yyyy-MM') " +
                " union all " +
                " select 'lay_pipe_trench_backfill' as stats_type, to_char(create_datetime, 'yyyy-MM') as stats_date, sum(backfill_length) as stats_result " +
                " from daq_lay_pipe_trench_backfill where active = 1 and approve_status = 2 and project_oid in (:projectIds) " +
                " group by to_char(create_datetime, 'yyyy-MM') ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), DateStatsResultBo.class);
    }


    public List<DataEntryAuditBo> dataEntryAudit(List<String> projectIds) {

        String sqlFormat = "select count(*) as total, " +
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
        }

        return commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("projectIds", projectIds), DataEntryAuditBo.class);
    }


    public List<Map<String, Integer>> statsDetectionRayPassCount(List<String> projectIds) {
        String sql = "select count(*) as count, sum(case when (detection_type='detection_type_code_001') then 1 else 0 end) as pass_count " +
                " from daq_detection_ray where active = 1 and approve_status = 2 and project_oid in (:projectIds) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds));
    }
}
