package cn.jasgroup.jasframework.acquisitiondata.statistics.dao;

import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.ApproveStatusEnum;
import cn.jasgroup.jasframework.acquisitiondata.statistics.comm.EntryStatisticsBlock;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.DataApproveSubBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.service.bo.StatisticsResultBo;
import cn.jasgroup.jasframework.dataaccess3.core.BaseNamedParameterJdbcTemplate;
import cn.jasgroup.jasframework.engine.jdbc.dao.CommonDataJdbcDao;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
public class StatisticsDao {

    @Autowired
    private BaseNamedParameterJdbcTemplate baseNamedParameterJdbcTemplate;

    @Autowired
    private CommonDataJdbcDao commonDataJdbcDao;

    /**
     * 数据录入统计
     * @param statisTypeList 统计类型来源
     * @return List
     */
    public List<StatisticsResultBo> listDataEntry(List<String> statisTypeList) {
        StringBuilder sql = new StringBuilder();
        if (CollectionUtils.isEmpty(statisTypeList)) {
            return Lists.newArrayList();
        }

        Map<String, String> pipeCheckedBlock = EntryStatisticsBlock.getPipeCheckedBlock();
        Map<String, String> weldApproveBlock = EntryStatisticsBlock.getWeldApproveBlock();

        for (int i = 0; i < statisTypeList.size(); i++) {
            String statType = statisTypeList.get(i);

            if (pipeCheckedBlock.containsKey(statType)) {
                String tableName = pipeCheckedBlock.get(statType);
                sql.append(String.format(" select '%s' as statis_type, count(*) as statis_result from %s where active = 1 and create_user_id =:createUserId ", statType, tableName));
            } else if (weldApproveBlock.containsKey(statType)) {
                String tableName = weldApproveBlock.get(statType);
                sql.append(String.format(" select '%s' as statis_type, approve_status as statis_result from %s where active = 1  and create_user_id =:createUserId ", statType, tableName));
            }

            sql.append(i<(statisTypeList.size()-1) ? " UNION ALL ":"");
        }

        return commonDataJdbcDao.queryForList(sql.toString(), ImmutableMap.of("createUserId", ThreadLocalHolder.getCurrentUserId()), StatisticsResultBo.class);
    }


    /**
     * 数据审核统计
     * @param constructUnitIds 施工单位ID集合
     * @return List
     */
    public List<DataApproveSubBo> listDataAuditing(List<String> supervisionUnits, List<String> constructUnitIds) {
        List<String> codeList = new ArrayList<>(ApproveStatisticsBlock.ALL.keySet());
        StringBuilder sql = new StringBuilder();
        String sqlTemplate = " select '%s' as code, '%s' as category_code, count(*) as total, " +
                " sum(case when (approve_status=" + ApproveStatusEnum.WAIT_AUDITING.getCode() + ") then 1 else 0 end) as unaudited " +
                " from %s where active = 1 and approve_status!=0 and supervision_unit in (:supervisionUnits) ";

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

        return commonDataJdbcDao.queryForList(sql.toString(),
                ImmutableMap.of("constructUnitIds", constructUnitIds==null?new ArrayList<>():constructUnitIds, "supervisionUnits", supervisionUnits),
                DataApproveSubBo.class);
    }

    public List<String> queryConstructUnitByHierarchy(String hierarchy) {
        String sql = " select oid from pri_unit where active = 1 and hierarchy like :hierarchy ";
        List<Map<String, String>> list = this.commonDataJdbcDao.queryForList(sql, ImmutableMap.of("hierarchy", hierarchy + "%"));
        return list.stream().map(s -> s.get("oid")).collect(Collectors.toList());
    }
}
