package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.dao;

import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm.DataAuditStatsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm.DataVisualizationConst;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm.MaterialBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.comm.ScopeManagementBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo.*;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/10/22 10:42
 * @
 */
@Repository
public class DataVisualizationDao {

    @Autowired
    private CommonDataJdbcDao commonDataJdbcDao;

    public List<StatsResultBo> sumPipelineLengthGroupByProjectId(Collection<String> projectIds) {
        String sql = " select project_oid as stats_type, sum(pipeline_length) as stats_result from daq_pipeline where active = 1 ";

        Map<String, Object> params = Maps.newHashMap();
        if (!CollectionUtils.isEmpty(projectIds)) {
            sql = sql.concat(" and project_oid in (:projectIds) ");
            params.put("projectIds", projectIds);
        }
        sql += "group by project_oid";
        return commonDataJdbcDao.queryForList(sql, params, StatsResultBo.class);
    }



    public List<StatsResultWithNameBo> countScopeManagement(String projectId) {
        String sqlFormat = " select '%s' as stats_type, count(*) as stats_result from %s where active = 1 and project_oid = :projectId ";
        String stationSqlFormat = " select '%s' as stats_type, count(*) as stats_result from %s where active = 1 and project_oid = :projectId and pipe_station_classification = '%s' ";
        StringBuilder sql = new StringBuilder();
        Map<String, ScopeManagementBlock> block = ScopeManagementBlock.getScopeManagementBlock();
        List<String> statsTypes = new ArrayList<>(block.keySet());

        for (int i = 0; i < statsTypes.size(); i++) {
            String statsType = statsTypes.get(i);

            // 站场/阀室 同表分类型统计
            if (ScopeManagementBlock.MAP.containsKey(statsType)) {
                sql.append(String.format(stationSqlFormat, statsType, block.get(statsType).getTableName(), ScopeManagementBlock.MAP.get(statsType)));
            } else {
                sql.append(String.format(sqlFormat, statsType, block.get(statsType).getTableName()));
            }
            sql.append(i<(statsTypes.size()-1) ? " UNION ALL ":"");

        }

        return commonDataJdbcDao.queryForList(sql.toString() , ImmutableMap.of("projectId", projectId), StatsResultWithNameBo.class);
    }


    /**
     * 统计各类物资: 已录入数量, 检查数量, 使用数量, 已检查未使用数量
     *  - 封堵物和法兰没有'已检查'和'已检查未使用'数量
     * @param projectId 项目ID
     * @return List
     */
    public List<MaterialStatsResultBo> countMaterial(String projectId) {
        Map<String, MaterialBlock> materialInfo = MaterialBlock.getMaterialInfo();

        String pipelineSqlFormat = "" +
                " select 'material_pipe' as stats_type, (select sum(pipe_length) from daq_material_pipe where active = 1 and project_oid = :projectId) as entry_count_or_length,\n" +
                "   sum(pipe_length) as checked_count_or_length,\n" +
                "   (select sum(pipe_length) from daq_material_pipe where active = 1 and project_oid = :projectId and is_use=1) as used_count_or_length,\n" +
                "   sum(case when (is_use=1) then pipe_length else 0 end ) as checked_unused_count_or_length,\n" +
                "   (select sum(pipe_length) from daq_material_pipe where active = 1 and project_oid = :projectId\n" +
                "    and oid not in (select distinct pipe_oid from daq_check_coating_pipe where active=1 and project_oid = :projectId)\n" +
                "    and oid not in (select pipe_oid from daq_material_pipe_cold_bending where active=1 and project_oid = :projectId and oid in (\n" +
                "      select pipe_cold_bending_oid from daq_check_pipe_cold_bending where project_oid = :projectId\n" +
                "    ))) as unchecked_used_count_or_length\n" +
                " from daq_material_pipe where active = 1 and project_oid = :projectId and\n" +
                " (oid in (select distinct pipe_oid from daq_check_coating_pipe where active=1 and project_oid = :projectId)) or\n" +
                " (oid in (select pipe_oid from daq_material_pipe_cold_bending where active=1 and project_oid = :projectId and oid in (\n" +
                "   select pipe_cold_bending_oid from daq_check_pipe_cold_bending where active=1 and project_oid = :projectId))) ";

        String checkSqlFormat = "" +
                " select '%s' as stats_type, count(*) as entry_count_or_length, " +
                " (select count(distinct %s) from %s where active = 1 and project_oid = :projectId) as checked_count_or_length, \n" +
                " COALESCE(sum(case when (is_use=1) then 1 else 0 end), 0) as used_count_or_length, \n" +
                " (select count(*) from %s where active =1 and project_oid = :projectId and is_use = 0 and oid in ( \n" +
                "   select distinct %s from %s where active =1 and project_oid = :projectId)) as checked_unused_count_or_length, \n" +
                " (select count(*) from %s where active =1 and project_oid = :projectId and is_use = 1 and oid not in ( \n" +
                "   select distinct %s from %s where active =1 and project_oid = :projectId)) as unchecked_used_count_or_length \n" +
                " from %s where active = 1 and project_oid = :projectId \n";

        String uncheckSqlFormat = "" +
                " select '%s' as stats_type, count(*) as entry_count_or_length, 0 as checked_count_or_length, " +
                " COALESCE(sum(case when (is_use=1) then 1 else 0 end), 0) as used_count_or_length, \n" +
                " 0 as checked_unused_count_or_length, 0 as unchecked_used_count_or_length from %s where active = 1 and project_oid = :projectId ";

        StringBuilder sql = new StringBuilder();
        List<String> statsTypes = Lists.newArrayList(materialInfo.keySet());
        for (int i = 0; i < statsTypes.size(); i++) {
            MaterialBlock block = materialInfo.get(statsTypes.get(i));

            if (null==block.getForeignKey() || null==block.getCheckTableName()) {
                sql.append(String.format(uncheckSqlFormat, statsTypes.get(i), block.getTableName()));
            } else if("material_pipe".equals(statsTypes.get(i))) {
                sql.append(pipelineSqlFormat);
            } else {
                sql.append(String.format(checkSqlFormat,
                        statsTypes.get(i),
                        block.getForeignKey(), block.getCheckTableName(), block.getTableName(),
                        block.getForeignKey(), block.getCheckTableName(), block.getTableName(),
                        block.getForeignKey(), block.getCheckTableName(), block.getTableName()
                ));
            }

            sql.append(i<(statsTypes.size()-1) ? " \nUNION ALL\n ":"");
        }

        return commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("projectId", projectId), MaterialStatsResultBo.class);
    }


    public List<SteelPipeUsageBo> countAndSumPipeUsage(List<String> projectIds) {
        String sql = "" +
                "select 'coating_pipe' as stats_type, '防腐管' as cn_name, count(*) as total_count,  sum(case when (is_use=1) then 1 else 0 end ) as used_count,\n" +
                "sum(case when (is_use=1) then pipe_length else 0 end ) as used_length from daq_material_pipe where active = 1 and is_cut = 0 and project_oid in (:projectIds)\n" +
                "union all\n" +
                "select 'straight_pipe' as stats_type, '直管' as cn_name, count(*) as total_count,  sum(case when (is_use=1) then 1 else 0 end ) as used_count,\n" +
                "sum(case when (is_use=1) then pipe_length else 0 end ) as  used_length from daq_material_pipe where active = 1 and is_cut = 0 and project_oid in (:projectIds)\n" +
                "and oid not in (select pipe_oid from daq_material_pipe_cold_bending where active = 1 and project_oid in (:projectIds))\n" +
                "union all\n" +
                "select 'cold_bending_pipe' as stats_type, '冷弯管' as cn_name, count(*) as total_count,  sum(case when (is_use=1) then 1 else 0 end ) as used_count,\n" +
                "sum(case when (is_use=1) then pipe_length else 0 end ) as  used_length from daq_material_pipe where active = 1 and is_cut = 0 and project_oid in (:projectIds)\n" +
                "and oid in (select pipe_oid from daq_material_pipe_cold_bending where active = 1 and project_oid in (:projectIds))";
        return commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), SteelPipeUsageBo.class);
    }



    public StatsPipeCuttingBo statsPipeCutting(List<String> projectIds) {
        String sql = "" +
                " select sum(case when (is_cut=1) then 1 else 0 end ) as cut_count,\n" +
                "   sum(case when (is_cut=0) then pipe_length else 0 end) as total_length,\n" +
                "   sum(case when (is_cut=0 and is_child=1) then pipe_length else 0 end) as cut_length,\n" +
                "   sum(case when (is_cut=0 and is_child=1 and is_use=1) then pipe_length else 0 end ) used_length,\n" +
                "   sum(case when (is_cut=0 and is_child=1 and is_use=0) then pipe_length else 0 end ) un_used_length\n" +
                " from daq_material_pipe where active = 1 and project_oid in (:projectIds) ";
        return (StatsPipeCuttingBo) commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), StatsPipeCuttingBo.class).get(0);
    }


    public List<Map<String, Integer>> countWeldOnceQualified(List<String> projectIds) {
        String sql = "select count(*) as count, coalesce(sum(case when (detection_type='detection_type_code_001' and evaluation_result=1) then 1 else 0 end), 0) as pass_count " +
                " from daq_detection_ray where active = 1 and approve_status = 2 and project_oid in (:projectIds) ";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds));
    }


    public List<StatsResultBo> countWeldReworkGroupByWeldDate(List<String> projectIds) {
        String sql = "" +
                " select to_char(weld_date, 'yyyy-MM-dd') as stats_type, count(*) as stats_result from daq_weld_rework_weld\n" +
                " where active =1 and project_oid in (:projectIds) and weld_date is not null group by to_char(weld_date, 'yyyy-MM-dd')";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), StatsResultBo.class);
    }


    public List<StatsResultBo> countWeldUnitAndPerson(List<String> projectIds) {
        String sql = "" +
                " select 'weld_work_unit' as stats_type, count(*) as stats_result from daq_work_unit where active = 1 \n" +
                " and project_oid in (:projectIds) and work_unit_type in (:weldUnitTypeCode)\n" +
                " union all\n" +
                " select 'welder' as stats_type, count(*) as stats_result from daq_work_personnel where active = 1 \n" +
                " and project_oid in (:projectIds) and personnel_type in (:welderTypeCode)\n";
        List<String> weldUnitTypeCode = DataVisualizationConst.WELD_UNIT_TYPE_CODE;
        List<String> welderTypeCode = DataVisualizationConst.WELDER_TYPE_CODE;
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds,
                "weldUnitTypeCode", weldUnitTypeCode, "welderTypeCode", welderTypeCode),
                StatsResultBo.class);
    }


    public List<Map<String, Integer>> countConstructorAndSupervisor(Collection<String> projectIds) {
        String sql = "" +
                " select coalesce(sum(case when hierarchy like 'Unit.0001.0005%' then 1 else 0 end), 0) as constructor,\n" +
                "   coalesce(sum(case when hierarchy like 'Unit.0001.0004%' then 1 else 0 end), 0) as supervisor from pri_unit unit\n" +
                " left join pri_user u on unit.oid = u.unit_id\n" +
                " where unit.active = 1 and u.active=1 and unit.oid in (\n" +
                "     select distinct unit_oid from daq_implement_scope_ref where active = 1 and project_oid in(:projectIds)\n" +
                " ) and (hierarchy like 'Unit.0001.0005%' or hierarchy like 'Unit.0001.0004%')";

        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("projectIds", projectIds), Map.class);
    }


    public List<DataEntryAndAuditBo> countDataEntryAndAudit(List<String> projectIds) {
        String sqlFormat = "" +
                " select '%s' as stats_type, '%s' as cn_name, count(*) as total_count,\n " +
                " COALESCE(sum(case when (approve_status=2) then 1 else 0 end), 0) as audit_passed_count,\n " +
                " COALESCE(sum(case when (approve_status=1) then 1 else 0 end), 0) as wait_audit_count\n " +
                " from %s where active = 1 and project_oid in (:projectIds) and approve_status!=0\n ";

        StringBuilder sql = new StringBuilder();
        Map<String, DataAuditStatsBlock> blockMap = DataAuditStatsBlock.getStatsBlock();
        List<String> statsTypes = new ArrayList<>(blockMap.keySet());
        for (int i = 0; i < statsTypes.size(); i++) {
            DataAuditStatsBlock block = blockMap.get(statsTypes.get(i));
            sql.append(String.format(sqlFormat, block.getCode(), block.getCnName(), block.getTableName()));
            sql.append(i<(statsTypes.size()-1) ? " UNION ALL\n ":"");
        }
        return this.commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("projectIds", projectIds), DataEntryAndAuditBo.class);
    }

    public List<StatsResultBo> countPersonFillTopNum(List<String> projectIds, Integer topNum) {
        String sqlFormat = "" +
                " select create_user_id, count(*) as result from %s where active = 1 " +
                " and project_oid in (:projectIds) group by create_user_id\n ";

        StringBuilder sql = new StringBuilder();
        Map<String, DataAuditStatsBlock> statsBlock = DataAuditStatsBlock.getStatsBlock();
        List<String> statsTypes = new ArrayList<>(statsBlock.keySet());
        for (int i = 0; i < statsTypes.size(); i++) {
            DataAuditStatsBlock block = statsBlock.get(statsTypes.get(i));
            sql.append(String.format(sqlFormat, block.getTableName()));
            sql.append(i<(statsTypes.size()-1) ? " UNION ALL\n ":"");
        }

        sql.insert(0, " select t.create_user_id as stats_type, sum(result) as stats_result from (\n")
                .append(" ) as t group by stats_type order by stats_result desc limit :topNum ");

        return this.commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("projectIds", projectIds, "topNum", topNum), StatsResultBo.class);
    }


    public List<PersonFillBo> queryUserAndUnit(List<String> userIds) {
        String sql = "" +
                " select u.oid as user_id, u.user_name as user_name, unit.unit_name as unit_name from pri_user u\n" +
                " left join pri_unit unit on u.unit_id = unit.oid\n" +
                " where u.active = 1 and unit.active = 1 and u.oid in (:userIds)";
        return this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("userIds", userIds), PersonFillBo.class);
    }
}
