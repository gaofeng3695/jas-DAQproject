package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.dao;

import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.ApproveStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.EntryStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsProcessForAppEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.*;
import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
     * @param statsTypeList 统计类型来源
     * @return List
     */
    public List<StatsResultBo> listDataEntry(List<String> statsTypeList, String projectOid) {
        StringBuilder sql = new StringBuilder();
        if (CollectionUtils.isEmpty(statsTypeList)) {
            return Lists.newArrayList();
        }

        Map<String, Object> variables = Maps.newHashMap();
        variables.put("createUserId", ThreadLocalHolder.getCurrentUserId());

        Map<String, String> pipeCheckedBlock = EntryStatisticsBlock.getPipeCheckedBlock();
        Map<String, String> weldApproveBlock = EntryStatisticsBlock.getWeldApproveBlock();

        for (int i = 0; i < statsTypeList.size(); i++) {
            String statType = statsTypeList.get(i);

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

            sql.append(i<(statsTypeList.size()-1) ? " UNION ALL ":"");
        }

        return commonDataJdbcDao.queryForList(sql.toString(), variables, StatsResultBo.class);
    }


    /**
     * 数据审核统计 
     * @param unitIds 施工单位\检测单位的ID集合
     * @return List
     */
    public List<DataApproveSubBo> listDataAuditing(String projectOid, List<String> supervisionUnits, List<String> unitIds, String unitType) {

        List<String> codeList;

        if (UnitHierarchyEnum.detection_unit.getHierarchy().equals(unitType)) { // 检测单位
            codeList = new ArrayList<>(ApproveStatisticsBlock.PIPE_INSPECTION_BLOCK.keySet());
        } else if (UnitHierarchyEnum.construct_unit.getHierarchy().equals(unitType)) { // 检测单位
            codeList = new ArrayList<>(ApproveStatisticsBlock.NON_DETECTION.keySet());
        } else { // 全部单位
            codeList = new ArrayList<>(ApproveStatisticsBlock.ALL.keySet());
        }

        StringBuilder sql = new StringBuilder();
        String sqlTemplate = " " +
                " select '%s' as code, '%s' as category_code, count(*) as total, " +
                " sum(case when (approve_status=1) then 1 else 0 end) as unaudited from %s " +
                " where active = 1 and project_oid = :projectOid and approve_status!=0 and supervision_unit in (:supervisionUnits) ";

        // 拼接统计SQL
        for (int i = 0; i < codeList.size(); i++) {
            String code = codeList.get(i);
            ApproveStatisticsBlock statsBlock = ApproveStatisticsBlock.ALL.get(code);
            String tableName = statsBlock.getTableName();
            String categoryCode = statsBlock.getCategoryCode();
            sql.append(String.format(sqlTemplate, code, categoryCode, tableName));

            // 如果是管道检测分类下的: 统计的字段是检测单位, 其他分类则是施工单位
            if (ApproveStatisticsBlock.PIPE_INSPECTION_BLOCK.containsKey(code)) {
                sql.append(" and detection_unit in (:unitIds) ");
            } else {
                sql.append(" and construct_unit in (:unitIds) ");
            }

            sql.append(i<(codeList.size()-1) ? " UNION ALL ":"");
        }

        Map<String, Object> variables = Maps.newHashMap();
        variables.put("unitIds", unitIds);
        variables.put("supervisionUnits", supervisionUnits);
        variables.put("projectOid", projectOid);
        return commonDataJdbcDao.queryForList(sql.toString(), variables, DataApproveSubBo.class);
    }


    /**
     * 查询该单位及该单位下级的所有单位ID集合
     * @param hierarchy 单位层级
     * @return List
     */
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
    public StatsResultBo statsPipeLengthByDate(String projectId, String date) {

        String conditionSql = " and to_char(t.checked_date , 'yyyy-MM-dd') = '"+date+"' ";
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
                ImmutableMap.of("projectId", projectId), StatsResultBo.class);
        if (CollectionUtils.isEmpty(resultList)) {
            return new StatsResultBo(StatsProcessForAppEnum.PIPE.getType(), 0);
        }
        return (StatsResultBo) resultList.get(0);
    }


    public StatsResultBo statsWeldLengthByDate(String projectId, String date) {
        String sql = "" +
                " select 'weld' as stats_type, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result \n" +
                " from daq_construction_weld w \n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1 \n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1 \n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1 \n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid = :projectId and to_char(w.construct_date, 'yyyy-MM-dd') = '"+date+"' ";
        List<StatsResultBo> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsResultBo.class);
        if (CollectionUtils.isEmpty(list)) {
            return new StatsResultBo(StatsProcessForAppEnum.WELD.getType(), 0);
        }
        return list.get(0);
    }


    public StatsResultBo statsPatchLengthByDate(String projectId, String date) {
        String sql = "" +
                " select 'patch' as stats_type, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_weld_anticorrosion_check patch\n" +
                "   left join daq_construction_weld w on patch.weld_oid = w.oid and w.active = 1 and w.approve_status=2\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where patch.active=1 and patch.approve_status=2 and patch.project_oid =:projectId and to_char(patch.buckle_date, 'yyyy-MM-dd') = '"+date+"'";
        List<StatsResultBo> list = commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsResultBo.class);
        if (CollectionUtils.isEmpty(list)) {
            return new StatsResultBo(StatsProcessForAppEnum.PATCH.getType(), 0);
        }
        return list.get(0);
    }

    /**
     * 统计管沟回填长度
     */
    public StatsResultBo statsBackFillLengthByDate(String projectId, String date) {
        String sql = "" +
                " select 'lay_pipe_trench_backfill' as stats_type, coalesce(sum(backfill_length), 0) as stats_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 " +
                " and project_oid = :projectId and to_char(construct_date, 'yyyy-MM-dd') = '"+date+"' ";
        List<StatsResultBo> resultList = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsResultBo.class);
        if (CollectionUtils.isEmpty(resultList)) {
            return new StatsResultBo(StatsProcessForAppEnum.LAY_PIPE_TRENCH_BACKFILL.getType(), 0);
        }
        return resultList.get(0);
    }


    public List<StatsProcessResultBo> statsPipeLengthGroupByConstruct(String projectId, String date) {

        // 项目, 单位, 日期过滤
        String conditionSql = " and t.project_oid = :projectId and to_char(t.checked_date , 'yyyy-MM-dd') = '"+date+"' ";
        String sql = "" +
                " select stats_type, COALESCE(sum(result), 0) as stats_result from ( " +
                "  select t.construct_unit as stats_type, COALESCE(sum(p.pipe_length), 0) as result from daq_check_coating_pipe t " +
                "  left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 ".concat(conditionSql) +
                "  group by t.construct_unit " +
                "  union all " +
                "  select t.construct_unit as stats_type, COALESCE(sum(p.pipe_length), 0) as result from daq_check_hot_bends t " +
                "  left join daq_material_hot_bends p on t.hot_bends_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 ".concat(conditionSql) +
                "  group by t.construct_unit " +
                ") as ss " +
                " group by stats_type";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsProcessResultBo.class);
    }


    /**
     * 统计管沟回填长度: 根据施工单位分组
     */
    public List<StatsProcessResultBo> sumBackFillLengthGroupByConstruct(String projectId, String date) {
        String sql = "" +
                " select construct_unit as stats_type, COALESCE(sum(backfill_length), 0) as stats_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 and project_oid = :projectId " +
                " and to_char(construct_date, 'yyyy-MM-dd') = '"+date+"'" +
                " group by construct_unit ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsProcessResultBo.class);
    }


    public List<StatsProcessResultBo> statsWeldLengthGroupByConstruct(String projectId, String date) {
        String sql = "" +
                " select w.construct_unit as stats_type, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                "  from daq_construction_weld w\n" +
                "    left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "    left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "    left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid = :projectId and to_char(w.construct_date, 'yyyy-MM-dd') = '"+date+"'\n" +
                " group by w.construct_unit";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsProcessResultBo.class);
    }


    public List<StatsProcessResultBo> statsPatchLengthGroupByConstruct(String projectId, String date) {
        String sql = "" +
                " select patch.construct_unit as stats_type, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_weld_anticorrosion_check patch\n" +
                "    left join daq_construction_weld w on patch.weld_oid = w.oid and w.active = 1 and w.approve_status=2\n" +
                "    left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "    left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "    left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where patch.active=1 and patch.approve_status=2 and patch.project_oid = :projectId and to_char(patch.buckle_date , 'yyyy-MM-dd') = '"+date+"'\n" +
                " group by patch.construct_unit";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsProcessResultBo.class);
    }


    public List<DateStatsResultBo> statsPipeLengthGroupByConstructAndDate(String projectId, Collection<String> unitIds, String startDate, String endDate) {

        // 项目, 单位, 日期过滤
        String conditionSql = " and t.project_oid = :projectId and t.construct_unit in (:unitIds) and to_char(t.checked_date , 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"' ";
        String sql = "" +
                " select stats_type, date as stats_date, COALESCE(sum(result), 0) as stats_result from ( " +
                "  select t.construct_unit as stats_type, to_char(t.checked_date , 'yyyy-MM-dd') as date, sum(p.pipe_length) as result from daq_check_coating_pipe t " +
                "  left join daq_material_pipe p on t.pipe_oid = p.oid " +
                "  where t.active = 1 and p.active = 1 " + conditionSql +
                "  group by t.construct_unit, to_char(t.checked_date , 'yyyy-MM-dd') " +
                "  union all " +
                "  select t.construct_unit as stats_type, to_char(t.checked_date , 'yyyy-MM-dd') as date, sum(p.pipe_length) as result from daq_check_hot_bends t " +
                "  left join daq_material_hot_bends p on t.hot_bends_oid = p.oid   " +
                "  where t.active = 1 and p.active = 1 " + conditionSql +
                "  group by t.construct_unit, to_char(t.checked_date , 'yyyy-MM-dd') " +
                " ) as ss " +
                " group by stats_type, date";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "unitIds", unitIds), DateStatsResultBo.class);
    }


    public List<StatsResultBo> statsProcessLengthByDate(String projectId, String date) {
        String sql = "" +
                " select 'pipe' as stats_type, COALESCE(sum(stats_result), 0) as stats_result from (\n" +
                "   select sum(p.pipe_length) as stats_result\n" +
                "   from daq_check_coating_pipe t left join daq_material_pipe p on t.pipe_oid = p.oid\n" +
                "   where t.active = 1 and p.active = 1 and t.project_oid = :projectId\n" +
                "   and t.checked_date < '"+date+"' \n" +
                "   union all\n" +
                "   select sum(p.pipe_length) as stats_result\n" +
                "   from daq_check_hot_bends t left join daq_material_hot_bends p on t.hot_bends_oid = p.oid\n" +
                "   where t.active = 1 and p.active = 1 and t.project_oid = :projectId\n" +
                "   and t.checked_date < '"+date+"'\n" +
                " ) as s\n" +
                " union all\n" +
                " select 'weld' as stats_type, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_construction_weld w\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid = :projectId and w.construct_date < '"+date+"'\n" +
                " union all\n" +
                " select 'lay_pipe_trench_backfill' as stats_type, coalesce(sum(backfill_length), 0) as stats_result from daq_lay_pipe_trench_backfill\n" +
                " where active = 1 and approve_status = 2 and project_oid = :projectId and construct_date < '"+date+"'\n" +
                " union all\n" +
                " select 'patch' as stats_type, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_weld_anticorrosion_check patch\n" +
                "   left join daq_construction_weld w on patch.weld_oid = w.oid and w.active = 1 and w.approve_status=2\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where patch.active=1 and patch.approve_status=2 and patch.project_oid = :projectId and patch.buckle_date < '"+date+"'";
        return commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), DateStatsResultBo.class);
    }


    public List<StatsProcessResultBo> statsProcessLengthGroupByUnit(String projectId, Collection<String> unitIds, String date) {
        String sql = "" +
                " select 'pipe' as stats_type, construct_unit as construct_id, COALESCE(sum(stats_result), 0) as stats_result from (\n" +
                "   select sum(p.pipe_length) as stats_result, t.construct_unit\n" +
                "   from daq_check_coating_pipe t left join daq_material_pipe p on t.pipe_oid = p.oid\n" +
                "   where t.active = 1 and p.active = 1 and t.project_oid = :projectId and t.construct_unit in (:unitIds)\n" +
                "   and t.checked_date < '"+date+"' group by t.construct_unit\n" +
                "   union all\n" +
                "   select sum(p.pipe_length) as stats_result, t.construct_unit\n" +
                "   from daq_check_hot_bends t left join daq_material_hot_bends p on t.hot_bends_oid = p.oid\n" +
                "   where t.active = 1 and p.active = 1 and t.project_oid = :projectId and t.construct_unit in (:unitIds)\n" +
                "   and t.checked_date < '"+date+"' group by t.construct_unit\n" +
                " ) as s\n" +
                " group by construct_unit\n" +
                " union all\n" +
                " select 'weld' as stats_type, w.construct_unit as construct_id, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_construction_weld w\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid = :projectId and w.construct_unit in (:unitIds) and w.construct_date < '"+date+"'\n" +
                " group by w.construct_unit\n" +
                " union all\n" +
                " select 'lay_pipe_trench_backfill' as stats_type, construct_unit as construct_id, coalesce(sum(backfill_length), 0) as stats_result from daq_lay_pipe_trench_backfill\n" +
                " where active = 1 and approve_status = 2 and project_oid = :projectId and construct_unit in (:unitIds) and construct_date < '"+date+"'\n" +
                " group by construct_unit\n" +
                " union all\n" +
                " select 'patch' as stats_type, patch.construct_unit as construct_id, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_weld_anticorrosion_check patch\n" +
                "   left join daq_construction_weld w on patch.weld_oid=w.oid and w.active=1 and w.approve_status=2\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2\n" +
                " and patch.active=1 and patch.approve_status=2 and patch.project_oid = :projectId and patch.construct_unit in (:unitIds) and patch.buckle_date < '"+date+"' \n" +
                " group by patch.construct_unit\n";
        return commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "unitIds", unitIds), StatsProcessResultBo.class);
    }


    public List<DateStatsResultBo> statsPipeLengthGroupByDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                "select 'pipe' as stats_type, stats_date as stats_date, COALESCE(sum(stats_result), 0) as stats_result from (  " +
                "  select 'check_coating_pipe' as stats_type, to_char(t.checked_date, 'yyyy-MM-dd') as stats_date, sum(p.pipe_length) as stats_result  " +
                "  from daq_check_coating_pipe t left join daq_material_pipe p on t.pipe_oid = p.oid  " +
                "  where t.active = 1 and p.active = 1 and t.project_oid = :projectId  " +
                "  and to_char(t.checked_date, 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"' " +
                "  group by to_char(t.checked_date, 'yyyy-MM-dd')  " +
                "  union all  " +
                "  select 'check_hot_bends' as stats_type, to_char(t.checked_date, 'yyyy-MM-dd') as stats_date, sum(p.pipe_length) as stats_result  " +
                "  from daq_check_hot_bends t left join daq_material_hot_bends p on t.hot_bends_oid = p.oid  " +
                "  where t.active = 1 and p.active = 1 and t.project_oid = :projectId  " +
                "  and to_char(t.checked_date, 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"'  " +
                "  group by to_char(t.checked_date, 'yyyy-MM-dd')  " +
                ") as ss group by stats_date ";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), DateStatsResultBo.class);
    }


    /**
     * 统计管沟回填长度: 根据日期分组
     */
    public List<DateStatsResultBo> statsBackFillLengthGroupByDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                " select 'lay_pipe_trench_backfill' as stats_type, to_char(construct_date, 'yyyy-MM-dd') as stats_date, sum(backfill_length) as stats_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 and project_oid = :projectId " +
                " and to_char(construct_date, 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"' " +
                " group by to_char(construct_date, 'yyyy-MM-dd') ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), DateStatsResultBo.class);
    }


    /**
     * 统计管沟回填长度: 根据施工单位和日期分组
     */
    public List<DateStatsResultBo> statsBackFillLengthGroupByConstructAndDate(String projectId, Collection<String> unitIds, String startDate, String endDate) {
        String sql = "" +
                " select construct_unit as stats_type, to_char(construct_date , 'yyyy-MM-dd') as stats_date, sum(backfill_length) as stats_result from daq_lay_pipe_trench_backfill " +
                " where active = 1 and approve_status = 2 and project_oid = :projectId and to_char(construct_date, 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"' " +
                " and construct_unit in (:unitIds) " +
                " group by construct_unit, to_char(construct_date , 'yyyy-MM-dd') ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "unitIds", unitIds), DateStatsResultBo.class);
    }


    public Integer countWeldByDate(String projectId, String date) {
        String sql = "" +
                " select count(*) from daq_construction_weld where active = 1 and approve_status = 2 " +
                " and project_oid = :projectId and to_char(construct_date, 'yyyy-MM-dd') = '"+date+"' ";
        return this.commonDataJdbcDao.queryForInt(ImmutableMap.of("projectId", projectId), sql);
    }

    public Integer countPatchByDate(String projectId, String date) {
        String sql = "" +
                " select count(*) from daq_weld_anticorrosion_check where active = 1 and approve_status = 2 " +
                " and project_oid = :projectId and to_char(buckle_date, 'yyyy-MM-dd') = '"+date+"'";
        return this.commonDataJdbcDao.queryForInt(ImmutableMap.of("projectId", projectId), sql);
    }

    public List<StatsProcessResultBo> countWeldGroupByUnitId(String projectId, String date) {
        String sql = "" +
                " select construct_unit as construct_id, count(*) as stats_count from daq_construction_weld where active=1 \n" +
                " and approve_status=2 and project_oid = :projectId and to_char(construct_date, 'yyyy-MM-dd') = '"+date+"'\n" +
                " group by construct_unit";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsProcessResultBo.class);
    }

    public List<StatsProcessResultBo> countPatchGroupByUnitId(String projectId, String date) {
        String sql = "" +
                " select construct_unit as construct_id, count(*) as stats_count from daq_weld_anticorrosion_check where active=1 \n" +
                " and approve_status=2 and project_oid = :projectId and to_char(buckle_date, 'yyyy-MM-dd') = '"+date+"'\n" +
                " group by construct_unit";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), StatsProcessResultBo.class);
    }


    public List<DateApproveStatsForApp> statsDataEntryApproveGroupByDay(String projectId, String startDate, String endDate) {
        String sqlFormat = "" +
                " select to_char(create_datetime, 'yyyy-MM-dd') as stats_date, " +
                " count(*) as total_count, sum(case when (approve_status=2) then 1 else 0 end) as audited_count " +
                " from %s where active = 1 and project_oid = :projectId" +
                " group by to_char(create_datetime, 'yyyy-MM-dd') ";
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


    public List<DateStatsResultBo> statsWeldLengthGroupByDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                " select 'weld' as stats_type, to_char(w.construct_date , 'yyyy-MM-dd') as stats_date, COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_construction_weld w\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid = :projectId \n" +
                " and to_char(w.construct_date , 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"'\n" +
                " group by to_char(w.construct_date , 'yyyy-MM-dd')";
        return commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "startDate", startDate, "endDate", endDate), DateStatsResultBo.class);
    }


    public List<DateStatsResultBo> statsWeldLengthGroupByConstructAndDate(String projectId, Collection<String> unitIds, String startDate, String endDate) {
        String sql = "" +
                " select w.construct_unit as stats_type, to_char(w.construct_date , 'yyyy-MM-dd') as stats_date,\n" +
                "   COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_construction_weld w\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid = :projectId \n" +
                " and to_char(w.construct_date, 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"'\n" +
                " and w.construct_unit in (:unitIds) " +
                " group by w.construct_unit, to_char(w.construct_date , 'yyyy-MM-dd')";
        return commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "unitIds", unitIds), DateStatsResultBo.class);
    }


    public List<DateStatsResultBo> statsPatchLengthGroupByDate(String projectId, String startDate, String endDate) {
        String sql = "" +
                " select 'patch' as stats_type, to_char(patch.buckle_date , 'yyyy-MM-dd') as stats_date,\n" +
                "   COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                " from daq_construction_weld w\n" +
                " left join daq_weld_anticorrosion_check patch on patch.weld_oid = w.oid\n" +
                " left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                " left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                " left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid = :projectId\n" +
                " and patch.active=1 and patch.approve_status=2 and patch.project_oid = :projectId \n" +
                " and to_char(patch.buckle_date , 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"'\n" +
                " group by to_char(patch.buckle_date , 'yyyy-MM-dd')";
        return commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId), DateStatsResultBo.class);
    }


    public List<DateStatsResultBo> statsPatchLengthGroupByConstructAndDate(String projectId, Collection<String> unitIds, String startDate, String endDate) {
        String sql = "" +
                " select w.construct_unit as stats_type, to_char(patch.buckle_date , 'yyyy-MM-dd') as stats_date,\n" +
                "   COALESCE(sum(pipe.pipe_length), 0) + COALESCE(sum(hot.pipe_length), 0) + COALESCE(sum(cold.pipe_length), 0) as stats_result\n" +
                "   from daq_construction_weld w\n" +
                "   left join daq_weld_anticorrosion_check patch on patch.weld_oid = w.oid\n" +
                "   left join daq_material_pipe pipe on pipe.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_001' and pipe.active=1\n" +
                "   left join daq_material_hot_bends hot on hot.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_002' and hot.active=1\n" +
                "   left join daq_material_pipe_cold_bending cold on cold.oid = w.front_pipe_oid and w.front_pipe_type = 'pipe_type_code_008' and cold.active=1\n" +
                " where w.active=1 and w.approve_status=2 and w.project_oid = :projectId\n" +
                " and patch.active=1 and patch.approve_status=2 and patch.project_oid = :projectId\n" +
                " and to_char(patch.buckle_date , 'yyyy-MM-dd') between '"+startDate+"' and '"+endDate+"'\n" +
                " and patch.construct_unit in (:unitIds) \n" +
                " group by w.construct_unit, to_char(patch.buckle_date , 'yyyy-MM-dd')";
        return commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectId", projectId, "unitIds", unitIds), DateStatsResultBo.class);
    }
}
