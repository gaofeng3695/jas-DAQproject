package cn.jasgroup.jasframework.acquisitiondata.datavisualization.dao;

import cn.jasgroup.jasframework.acquisitiondata.datavisualization.comm.MaterialBlock;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.comm.ScopeManagementBlock;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.service.bo.MaterialStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.service.bo.ScopeStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatsResultBo;
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



    public List<ScopeStatsResultBo> countScopeManagement(String projectId) {
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

        return commonDataJdbcDao.queryForList(sql.toString() , ImmutableMap.of("projectId", projectId), ScopeStatsResultBo.class);
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


    public void countAndSumPipeUsage(List<String> projectIds) {

    }
}
