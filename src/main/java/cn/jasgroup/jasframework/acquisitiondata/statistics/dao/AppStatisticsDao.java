package cn.jasgroup.jasframework.acquisitiondata.statistics.dao;

import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatusEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.EntryStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.StatsPipeEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.*;
import cn.jasgroup.jasframework.dataaccess3.core.BaseNamedParameterJdbcTemplate;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/8/27 10:39
 */
@Repository
public class AppStatisticsDao {

    @Autowired
    private CommonDataJdbcDao commonDataJdbcDao;

    /**
     * 数据录入统计
     * @param statisTypeList 统计类型来源
     * @return List
     */
    public List<StatsResultBo> listDataEntry(List<String> statisTypeList, String projectOid) {
        StringBuilder sql = new StringBuilder();
        if (CollectionUtils.isEmpty(statisTypeList)) {
            return Lists.newArrayList();
        }

        Map<String, Object> variables = Maps.newHashMap();
        variables.put("createUserId", ThreadLocalHolder.getCurrentUserId());

        Map<String, String> pipeCheckedBlock = EntryStatisticsBlock.getPipeCheckedBlock();
        Map<String, String> weldApproveBlock = EntryStatisticsBlock.getWeldApproveBlock();

        for (int i = 0; i < statisTypeList.size(); i++) {
            String statType = statisTypeList.get(i);

            if (pipeCheckedBlock.containsKey(statType)) {
                String tableName = pipeCheckedBlock.get(statType);
                sql.append(String.format(" select '%s' as stats_type, count(*) as stats_result from %s where active = 1 and create_user_id =:createUserId ", statType, tableName));
            } else if (weldApproveBlock.containsKey(statType)) {
                String tableName = weldApproveBlock.get(statType);
                sql.append(String.format(" select '%s' as stats_type, approve_status as stats_result from %s where active = 1  and create_user_id =:createUserId ", statType, tableName));
            }

            if (!StringUtils.isEmpty(projectOid)) {
                sql.append(" and project_oid = :projectOid");
                variables.put("projectOid", projectOid);
            }

            sql.append(i<(statisTypeList.size()-1) ? " UNION ALL ":"");
        }

        return commonDataJdbcDao.queryForList(sql.toString(), variables, StatsResultBo.class);
    }


    /**
     * 数据审核统计 
     * @param constructUnitIds 施工单位ID集合
     * @return List
     */
    public List<DataApproveSubBo> listDataAuditing(String projectOid, List<String> supervisionUnits, List<String> constructUnitIds) {
        List<String> codeList = new ArrayList<>(ApproveStatisticsBlock.ALL.keySet());
        StringBuilder sql = new StringBuilder();
        String sqlTemplate = " select '%s' as code, '%s' as category_code, count(*) as total, " +
                " sum(case when (approve_status=" + ApproveStatusEnum.WAIT_AUDITING.getCode() + ") then 1 else 0 end) as unaudited " +
                " from %s where active = 1 and approve_status!=0 and supervision_unit in (:supervisionUnits) ";

        if (!StringUtils.isEmpty(projectOid)) {
            sqlTemplate = sqlTemplate.concat(" and project_oid = :projectOid ");
        }

        // 拼接统计SQL
        for (int i = 0; i < codeList.size(); i++) {
            String code = codeList.get(i);
            ApproveStatisticsBlock statisticsBlock = ApproveStatisticsBlock.ALL.get(code);
            String tableName = statisticsBlock.getTableName();
            String categoryCode = statisticsBlock.getCategoryCode();
            sql.append(String.format(sqlTemplate, code, categoryCode, tableName));

            // 如果是管道检测分类下的: 统计的字段是检测单位, 其他分类则是施工单位
            if (!CollectionUtils.isEmpty(constructUnitIds)) {
                if (ApproveStatisticsBlock.PIPE_INSPECTION_BLOCK.containsKey(code)) {
                    sql.append(String.format(" and %s in (:constructUnitIds) ", ApproveStatisticsBlock.DETECTION_UNIT));
                } else {
                    sql.append(String.format(" and %s in (:constructUnitIds) ", ApproveStatisticsBlock.CONSTRUCT_UNIT));
                }
            }

            sql.append(i<(codeList.size()-1) ? " UNION ALL ":"");
        }

        Map<String, Object> variables = Maps.newHashMap();
        variables.put("constructUnitIds", constructUnitIds);
        variables.put("supervisionUnits", supervisionUnits);
        variables.put("projectOid", projectOid);
        return commonDataJdbcDao.queryForList(sql.toString(), variables, DataApproveSubBo.class);
    }



    public List<String> queryConstructUnitByHierarchy(String hierarchy) {
        String sql = " select oid from pri_unit where active = 1 and hierarchy like :hierarchy ";
        List<Map<String, String>> list = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("hierarchy", hierarchy + "%"));
        return list.stream().map(s -> s.get("oid")).collect(Collectors.toList());
    }


    /**
     * 管材长度统计
     * - 防腐管检查-钢管长度统计
     * - 热煨弯管检查-热煨弯管长度统计
     * @param projectId 项目ID集合
     * @return List
     */
    public StatsResultBo statsPipeLengthByDate(String projectId, String startDate, String endDate) {

        String conditionSql = " and to_char(t.create_datetime , 'yyyy-MM-dd') between :startDate and :endDate ";
        String sql =
                " select 'pipe' as stats_type, coalesce(sum(result), 0) as stats_result from ( " +
                "   select sum(p.pipe_length) as result from daq_check_coating_pipe t " +
                "   left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "   where t.active = 1 and p.active = 1 and t.project_oid = :projectId " + conditionSql +
                "   union all " +
                "   select sum(p.pipe_length) as result from daq_check_hot_bends t " +
                "   left join daq_material_hot_bends p on t.hot_bends_oid = p.oid " +
                "   where t.active = 1 and p.active = 1 and t.project_oid = :projectId " + conditionSql +
                " ) as ss ";
        List resultList = this.commonDataJdbcDao.queryForList(sql,
                ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), StatsResultBo.class);
        if (CollectionUtils.isEmpty(resultList)) {
            return new StatsResultBo("pipe", 0);
        }
        return (StatsResultBo) resultList.get(0);
    }


    public List<StatsProcessResultBo> statsPipeLengthGroupByConstruct(String projectId, String startDate, String endDate) {

        // 日期过滤
        String conditionSql = " and to_char(t.create_datetime , 'yyyy-MM-dd') between :startDate and :endDate ";
        String sql = "" +
                " select stats_type, sum(result) as stats_result from ( " +
                "  select t.construct_unit as stats_type, sum(p.pipe_length) as result from daq_check_coating_pipe t " +
                "  left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid = :projectId " + conditionSql +
                "  group by t.construct_unit " +
                "  union all " +
                "  select t.construct_unit as stats_type, sum(p.pipe_length) as result from daq_check_hot_bends t " +
                "  left join daq_material_hot_bends p on t.hot_bends_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 and t.project_oid = :projectId " + conditionSql +
                "  group by t.construct_unit " +
                ") as ss " +
                " group by stats_type";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), StatsProcessResultBo.class);
    }

    public List<DateStatsResultBo> statsPipeLengthGroupByConstructAndDate(String projectId, String startDate, String endDate) {

        // 日期过滤
        String conditionSql = " and to_char(t.create_datetime , 'yyyy-MM-dd') between :startDate and :endDate ";
        String sql = "" +
                " select stats_type, date as stats_date, sum(result) as stats_result from ( " +
                "  select t.construct_unit as stats_type, to_char(t.create_datetime , 'yyyy-MM-dd') as date, sum(p.pipe_length) as result from daq_check_coating_pipe t " +
                "  left join daq_material_pipe p on t.pipe_oid = p.oid   " +
                "  where t.active = 1 and p.active = 1 and t.project_oid = :projectId " + conditionSql +
                "  group by t.construct_unit, to_char(t.create_datetime , 'yyyy-MM-dd') " +
                "  union all   " +
                "  select t.construct_unit as stats_type, to_char(t.create_datetime , 'yyyy-MM-dd') as date, sum(p.pipe_length) as result from daq_check_hot_bends t " +
                "  left join daq_material_hot_bends p on t.hot_bends_oid = p.oid   " +
                "  where t.active = 1 and p.active = 1 and t.project_oid = :projectId " + conditionSql +
                "  group by t.construct_unit, to_char(t.create_datetime , 'yyyy-MM-dd') " +
                " ) as ss   " +
                " group by stats_type, date";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), DateStatsResultBo.class);
    }



    public List<DateStatsResultBo> statsPipeLengthGroupDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                "select 'pipe' as stats_type, stats_date as stats_date, sum(stats_result) as stats_result from (  " +
                "  select 'check_coating_pipe' as stats_type, to_char(t.create_datetime, 'yyyy-MM-dd') as stats_date, sum(p.pipe_length) as stats_result  " +
                "  from daq_check_coating_pipe t left join daq_material_pipe p on t.pipe_oid = p.oid  " +
                "  where t.active = 1 and p.active = 1 and t.project_oid = :projectId  " +
                "  and to_char(t.create_datetime, 'yyyy-MM-dd') between :startDate and :endDate  " +
                "  group by to_char(t.create_datetime, 'yyyy-MM-dd')  " +
                "  union all  " +
                "  select 'check_hot_bends' as stats_type, to_char(t.create_datetime, 'yyyy-MM-dd') as stats_date, sum(p.pipe_length) as stats_result  " +
                "  from daq_check_hot_bends t left join daq_material_hot_bends p on t.hot_bends_oid = p.oid  " +
                "  where t.active = 1 and p.active = 1 and t.project_oid = :projectId  " +
                "  and to_char(t.create_datetime, 'yyyy-MM-dd') between :startDate and :endDate  " +
                "  group by to_char(t.create_datetime, 'yyyy-MM-dd')  " +
                ") as ss group by stats_date ";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), DateStatsResultBo.class);
    }


    /**
     * 统计管沟回填长度
     */
    public StatsResultBo sumBackFillLengthByDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                " select 'lay_pipe_trench_backfill' as stats_type, coalesce(sum(backfill_length), 0) as stats_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 and project_oid = :projectId and to_char(create_datetime, 'yyyy-MM-dd') between :startDate and :endDate ";
        List<StatsResultBo> resultList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), StatsResultBo.class);
        if (CollectionUtils.isEmpty(resultList)) {
            return new StatsResultBo("lay_pipe_trench_backfill", 0);
        }
        return resultList.get(0);
    }


    /**
     * 统计管沟回填长度: 根据施工单位分组
     */
    public List<StatsProcessResultBo> sumBackFillLengthGroupByConstruct(String projectId, String startDate, String endDate) {
        String sql = "" +
                " select construct_unit as stats_type, sum(backfill_length) as stats_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 and project_oid = :projectId " +
                " and to_char(create_datetime, 'yyyy-MM-dd') between :startDate and :endDate " +
                " group by construct_unit ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), StatsProcessResultBo.class);
    }


    /**
     * 统计管沟回填长度: 根据日期分组
     */
    public List<DateStatsResultBo> statsBackFillLengthGroupByDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                " select 'lay_pipe_trench_backfill' as stats_type, to_char(create_datetime, 'yyyy-MM-dd') as stats_date, sum(backfill_length) as stats_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 and project_oid = :projectId " +
                " and to_char(create_datetime, 'yyyy-MM-dd') between :startDate and :endDate " +
                " group by to_char(create_datetime, 'yyyy-MM-dd') ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), DateStatsResultBo.class);
    }


    /**
     * 统计管沟回填长度: 根据施工单位和日期分组
     */
    public List<DateStatsResultBo> statsBackFillLengthGroupByConstructAndDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                " select construct_unit as stats_type, to_char(create_datetime , 'yyyy-MM-dd') as stats_date, sum(backfill_length) as stats_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 and project_oid = :projectId and to_char(create_datetime, 'yyyy-MM-dd') between :startDate and :endDate " +
                " group by construct_unit, to_char(create_datetime , 'yyyy-MM-dd') ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), DateStatsResultBo.class);
    }





    /**
     * 查询要统计长度焊口信息
     */
    public List<WeldInfoBo> listWeldInfoByDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                " select oid, construct_unit, to_char(create_datetime, 'yyyy-MM-dd') as stats_date, front_pipe_type, front_pipe_oid, back_pipe_type, back_pipe_oid from daq_construction_weld " +
                " where active = 1 and approve_status = 2 and project_oid = :projectId " +
                " and (front_pipe_type in (:statsTypes) or back_pipe_type in (:statsTypes) ) " +
                " and to_char(create_datetime, 'yyyy-MM-dd') between :startDate and :endDate ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "statsTypes", statsTypes, "startDate", startDate, "endDate", endDate), WeldInfoBo.class);
    }

    public Integer countWeldInfoByDate(String projectId, String date, String constructId) {

        Map<String, Object> params = new HashMap<>(4);
        params.put("projectId", projectId);
        params.put("date", date);

        String sql = "select count(*) from daq_construction_weld where active = 1 and approve_status = 2 and project_oid = :projectId";
        sql += " and to_char(create_datetime, 'yyyy-MM-dd') = :date";

        if (!StringUtils.isEmpty(constructId)) {
            sql += " and construct_unit = :constructId ";
            params.put("constructId", constructId);
        }
        return this.commonDataJdbcDao.queryForInt(params, sql);
    }


    /**
     * 查询补口关联焊口信息中的管件
     */
    public List<WeldInfoBo> listPatchRelationWeldInfoByDate(String projectId, String startDate, String endDate) {
        String sql = "select t.oid, to_char(c.create_datetime, 'yyyy-MM-dd') as stats_date, t.front_pipe_type, t.front_pipe_oid, t.back_pipe_type, t.back_pipe_oid from daq_construction_weld t " +
                " inner join daq_weld_anticorrosion_check c on t.oid = c.weld_oid " +
                " where t.active = 1 and t.approve_status = 2 and t.project_oid = :projectId and c.project_oid = :projectId " +
                " and c.active=1 and c.approve_status = 2 and (t.front_pipe_type in (:statsTypes) or t.back_pipe_type in (:statsTypes)) " +
                " and to_char(c.create_datetime, 'yyyy-MM-dd') between :startDate and :endDate  ";
        ImmutableList<String> statsTypes = ImmutableList.of(StatsPipeEnum.STRAIGHT_STEEL_PIPE.getCode(), StatsPipeEnum.HOT_BEND.getCode(), StatsPipeEnum.COLD_BEND.getCode());
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "statsTypes", statsTypes, "startDate", startDate, "endDate", endDate),
                WeldInfoBo.class);
    }

    public Integer countPatchRelationWeldInfoByDate(String projectId, String date, String constructId) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("projectId", projectId);
        params.put("date", date);
        String sql = "select count(*) from daq_weld_anticorrosion_check where active = 1 and approve_status = 2 and project_oid = :projectId";
        sql += " and to_char(create_datetime, 'yyyy-MM-dd') = :date";

        if (!StringUtils.isEmpty(constructId)) {
            sql += " and construct_unit = :constructId ";
            params.put("constructId", constructId);
        }
        return this.commonDataJdbcDao.queryForInt(params, sql);
    }


    public Map<String, String> queryConstructNameByIdIn(Collection<String> constructIds) {

        Map<String, Object> params = new HashMap<>(4);
        String sql = "select oid, unit_name from pri_unit where 1=1";

        if (!CollectionUtils.isEmpty(constructIds)) {
            sql += " and oid in (:constructIds) ";
            params.put("constructIds", constructIds);
        }

        Map<String, String> resultMap = Maps.newHashMap();
        List<Map<String, String>> list = this.commonDataJdbcDao.queryForList(sql, params);
        for (Map<String, String> boMap : list) {
            resultMap.put(boMap.get("oid"), boMap.get("unitName"));
        }
        return resultMap;
    }


    public List<DateApproveStatsForApp> statsDataEntryApproveGroupByDay(String projectId, String startDate, String endDate) {
        String sqlFormat = "" +
                " select to_char(create_datetime, 'yyyy-DD-mm') as stats_date, count(*) as total_count, sum(case when (approve_status=2) then 1 else 0 end) as audited_count " +
                " from %s where active = 1 and project_oid = :projectId" +
                " group by to_char(create_datetime, 'yyyy-DD-mm') ";
        List<String> codeList = new ArrayList<>(ApproveStatisticsBlock.ALL.keySet());

        StringBuilder sql = new StringBuilder();
        for (int i = 0; i < codeList.size(); i++) {
            sql.append(String.format(sqlFormat, ApproveStatisticsBlock.ALL.get(codeList.get(i)).getTableName()));
            sql.append(i<(codeList.size()-1) ? " UNION ALL ":"");
        }

        sql.insert(0, " select stats_date, sum(total_count) as total_count, sum(audited_count) as audited_count from ( ").append(" ) as ss ");
        sql.append(" where stats_date BETWEEN :startDate and :endDate group by stats_date");
        return commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), DateApproveStatsForApp.class);
    }


    public WeldCheckInfoBo countWeldDetectionInfo(String projectId) {
        String sql = "" +
                " select count(*) as weld_count, sum(case when (is_ray=1) then 1 else 0 end) as checked_count " +
                " from daq_construction_weld where active = 1 and project_oid = :projectId and approve_status = 2 ";

        List<WeldCheckInfoBo> list = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), WeldCheckInfoBo.class);

        if (CollectionUtils.isEmpty(list)) {
            WeldCheckInfoBo bo = new WeldCheckInfoBo();
            bo.setWeldCount(0);
            bo.setCheckedCount(0);
            return bo;
        }
        return list.get(0);
    }


    public List<WeldInfoBo> listWeldInfo(String projectId) {
        String sql = " select oid, construct_unit, is_ray from daq_construction_weld where active = 1 and approve_status = 2 and project_oid = :projectId ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), WeldInfoBo.class);
    }


    /**
     * 查询合格的焊口检测(根据焊口信息ID查询射线检测)
     * @param projectId 项目ID
     * @param weldIds 焊口信息ID集合
     * @return List
     */
    public List<DetectionRayBo> listQualifiedDetectionRayWeldIn(String projectId, Collection<String> weldIds) {
        String sql = "" +
                " select oid, weld_oid, detection_type, evaluation_result from daq_detection_ray where active = 1 " +
                " and project_oid = :projectId and approve_status = 2 and evaluation_result = 1 and weld_oid in (:weldIds) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "weldIds", weldIds), DetectionRayBo.class);
    }


    public WeldCheckInfoBo countRayDetection(String projectId) {
        String sql = "" +
                " select count(*) as detection_ray_count, sum(case when (evaluation_result=1) then 1 else 0 end) as qualified_count  from daq_detection_ray " +
                " where active=1 and project_oid = :projectId and approve_status = 2 ";
        List<WeldCheckInfoBo> list = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), WeldCheckInfoBo.class);

        if (CollectionUtils.isEmpty(list)) {
            WeldCheckInfoBo bo = new WeldCheckInfoBo();
            bo.setDetectionRayCount(0);
            bo.setQualifiedCount(0);
            return bo;
        }
        return list.get(0);
    }

}
