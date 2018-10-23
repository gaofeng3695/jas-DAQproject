package cn.jasgroup.jasframework.acquisitiondata.datavisualization.dao;

import cn.jasgroup.jasframework.acquisitiondata.datavisualization.comm.MaterialBlock;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.comm.ScopeManagementBlock;
import cn.jasgroup.jasframework.acquisitiondata.datavisualization.service.bo.MaterialStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatsResultBo;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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



    public List<StatsResultBo> countScopeManagement(String projectId) {
        String sqlFormat = " select '%s' as stats_type, count(*) as stats_result from %s where active = 1 and project_oid = :projectId ";

        StringBuilder sql = new StringBuilder();
        Map<String, String> scopeManagementBlock = ScopeManagementBlock.getScopeManagementBlock();
        List<String> statsTypes = new ArrayList<>(scopeManagementBlock.keySet());

        for (int i = 0; i < statsTypes.size(); i++) {
            String statsType = statsTypes.get(i);
            sql.append(String.format(sqlFormat, statsType, scopeManagementBlock.get(statsType)));
            sql.append(i<(statsTypes.size()-1) ? " UNION ALL ":"");
        }

        return commonDataJdbcDao.queryForList(sql.toString() , ImmutableMap.of("projectId", projectId), StatsResultBo.class);
    }


    /**
     * 统计各类物资: 已录入数量, 检查数量, 使用数量, 已检查未使用数量
     *  - 封堵物和法兰没有'已检查'和'已检查未使用'数量
     * @param projectId 项目ID
     * @return List
     */
    public List<MaterialStatsResultBo> countMaterial(String projectId) {
        Map<String, MaterialBlock> materialInfo = MaterialBlock.getMaterialInfo();

        String checkSqlFormat = "" +
                " select '%s' as stats_type, count(*) as entry_count, " +
                " (select count(distinct %s) from %s where active = 1 and project_oid = :projectId) as checked_count, \n" +
                " COALESCE(sum(case when (is_use=1) then 1 else 0 end), 0) as used_count, \n" +
                " (select count(*) from %s where active =1 and project_oid = :projectId and is_use = 0 and oid in ( \n" +
                "   select distinct %s from %s where active =1 and project_oid = :projectId)) as checked_unused_count \n" +
                " from %s where active = 1 and project_oid = :projectId \n";

        String uncheckSqlFormat = "" +
                " select '%s' as stats_type, count(*) as entry_count, 0 as checked_count, COALESCE(sum(case when (is_use=1) then 1 else 0 end), 0) as used_count, \n" +
                " 0 as checked_unused_count from %s where active = 1 and project_oid = :projectId ";

        StringBuilder sql = new StringBuilder();
        List<String> statsTypes = Lists.newArrayList(materialInfo.keySet());
        for (int i = 0; i < statsTypes.size(); i++) {
            MaterialBlock block = materialInfo.get(statsTypes.get(i));

            if (null==block.getForeignKey() || null==block.getCheckTableName()) {
                sql.append(String.format(uncheckSqlFormat, statsTypes.get(i), block.getTableName()));
            } else {
                sql.append(String.format(checkSqlFormat,
                        statsTypes.get(i),
                        block.getForeignKey(), block.getCheckTableName(), block.getTableName(),
                        block.getForeignKey(), block.getCheckTableName(), block.getTableName()
                ));
            }

            sql.append(i<(statsTypes.size()-1) ? " UNION ALL ":"");
        }

        return commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("projectId", projectId), MaterialStatsResultBo.class);
    }

}
