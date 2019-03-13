package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao;

import java.util.List;
import java.util.Map;

import cn.jasgroup.jasframework.acquisitiondata.utils.VariateInjectUtils;
import cn.jasgroup.jasframework.dataaccess3.core.BaseNamedParameterJdbcTemplate;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.ImmutableMap;

import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao.entity.ReworkWeld;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

import javax.annotation.Resource;

@Repository
public class WeldDao extends BaseJdbcDao{

    @Resource
    BaseNamedParameterJdbcTemplate baseNamedParameterJdbcTemplate;
	
	/***
	  * <p>功能描述：获取焊口列表（焊口+返修-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:00:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getWeldList(String pipeSegmentOrCrossOid){
		String sql = "select oid as key,weld_code as value from (select *from daq_construction_weld where @privilege_strategy_sql) t where t.is_rework=0 and t.approve_status=2 and t.pipe_segment_or_cross_oid=? and t.is_cut=0 "
				+ "union all "
				+ "select oid as key,rework_weld_code as value from daq_weld_rework_weld where active=1 and @privilege_strategy_sql and approve_status=2 and pipe_segment_or_cross_oid=? and is_cut=0";
		sql = VariateInjectUtils.invoke(sql);
		return this.queryForList(sql, new Object[]{pipeSegmentOrCrossOid,pipeSegmentOrCrossOid});
	}
	/***
	  * <p>功能描述：获取焊口列表（焊口-返修-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:01:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getOnlyWeldList(String pipeSegmentOrCrossOid){
		String sql = "select oid as key,weld_code as value from (select *from daq_construction_weld where @privilege_strategy_sql) t where t.is_rework=0 and t.approve_status=2 and t.pipe_segment_or_cross_oid=? and t.is_cut=0";
		sql = VariateInjectUtils.invoke(sql);
		return this.queryForList(sql, new Object[]{pipeSegmentOrCrossOid});
	}
	/***
	  * <p>功能描述：获取焊口列表（焊口+返修前焊口+返修后焊口-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid 线路段/穿跨越oid
	  * @param detectionType 检查类型
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:02:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getAllWeldList(String pipeSegmentOrCrossOid,String detectionType){
		String filterColumn = "";
		switch (detectionType) {
		case "1"://是否射线检测
			filterColumn = " and is_ray=0 ";
			break;
		case "2"://是否超声波检查
			filterColumn = " and is_ultrasonic=0 ";
			break;
		case "3"://是否渗透检测
			filterColumn = " and is_infiltration=0 ";
			break;
		case "4"://是否磁粉检测
			filterColumn = " and is_magnetic_powder=0 ";
			break;
		case "5"://是否全自动检测
			filterColumn = " and is_fa_ultrasonic=0 ";
			break;
		case "6"://相控阵超声波检测
			filterColumn = " and is_pa_ultrasonic=0 ";
			break;
		}
		String sql = "select t.oid as key,t.weld_code as value from daq_construction_weld t where t.active=1 and t.is_cut=0 and t.pipe_segment_or_cross_oid=? and approve_status=2"+filterColumn
			+ "union all "
			+ "select t.oid as key,t.rework_weld_code as value from daq_weld_rework_weld t where t.active=1 and t.is_cut=0 and t.pipe_segment_or_cross_oid=? and approve_status=2"+filterColumn;
		return this.queryForList(sql, new Object[]{pipeSegmentOrCrossOid,pipeSegmentOrCrossOid});
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public ConstructionWeld find(String oid){
		String sql = "select * from daq_construction_weld t where t.oid=?";
		List<ConstructionWeld> list = this.queryForList(sql, new Object[]{oid}, ConstructionWeld.class);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * <p>功能描述：根据焊口/返修oid查对应编号。</p>
	  * <p> 葛建。</p>	
	  * @param weldOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年10月18日 上午10:52:20。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public String getWeldCodeByOid(String weldOid) {
		String sql = "select weld_code from daq_construction_weld where oid='"+weldOid+"'";
		List weldList = this.queryForList(sql, null, ConstructionWeld.class);
		if (weldList.size() > 0) {
			ConstructionWeld weld = (ConstructionWeld)weldList.get(0);
			return weld.getWeldCode();
		}else {
			String sql1 = "select rework_weld_code from daq_weld_rework_weld where oid='"+weldOid+"'";
			List reworkList = this.queryForList(sql1, null, ReworkWeld.class);
			ReworkWeld reworkWeld = (ReworkWeld)reworkList.get(0);
			return reworkWeld.getReworkWeldCode();
		}
	}
	
	public List<Map<String,Object>> getDetectionInfoByWeldOid(String weldOid){
		String sql = "select t.oid,t.evaluation_result,t.type,t.code from v_daq_weld_detection t where t.weld_oid=?";
		return this.queryForList(sql, new Object[]{weldOid});
	}

	/**
	 * <p>功能描述: 根据回填的oid 查询出焊口的oid列表。</p>
	 * <p> cuixianing。</p>
	 * @param oid
	 * @since JDK1.8
	 * <p>创建日期:2019/2/20 15:45</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
	 */
    public List<Map<String,String>> queryOids(String oid,String tableName){
        String sql = "select oid from daq_construction_weld w  " +
                "inner join(select geom from "+tableName +" where oid= ? ) b on ST_Intersects( ST_Buffer(b.geom,0.0000001),w.geom) " +
                "where w.active=1";
        return this.queryForList(sql,new Object[]{oid});
    }

    /**
     * <p>功能描述: 更新焊口信息表daq_construction_weld的is_backfill的字段状态。</p>
     * <p> cuixianing。</p>
     * @param oid
     * @since JDK1.8
     * <p>创建日期:2019/2/20 16:06</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public Integer updateFieldState(List<Map<String,String>> oid,String updateField){
        String sql = "update daq_construction_weld set "+updateField+" = 1 where oid in (:oid) ";
        int[] len = baseNamedParameterJdbcTemplate.batchUpdate(sql, oid);
        return len.length;
    }

	/**
	 * <p>功能描述：根据线路段查询审核状态为1和2的焊口列表(焊口表中未返修未割口且未测量的数据，返修表中未割口且未测量的数据)。</p>
	  * <p> 葛建。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 上午11:03:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getWeldByCondition(String pipeSegmentOrCrossOid) {
		String sql = "select total.key,total.value,total.median_stake_oid,stake.median_stake_code,total.relative_mileage from "
					+ "((select weld.oid as key, weld.weld_code as value, weld.median_stake_oid, weld.relative_mileage from (select *from daq_construction_weld where @privilege_strategy_sql) weld "
					+ "where weld.active=1 and weld.pipe_segment_or_cross_oid=? and weld.approve_status in (1,2) and weld.is_rework=0 and weld.is_cut=0 and weld.is_measure=0) "
					+ "union all "
					+ "(select rework.oid as key, rework.rework_weld_code  as value, weld.median_stake_oid, weld.relative_mileage from (select *from daq_weld_rework_weld where @privilege_strategy_sql) rework "
					+ "LEFT JOIN (select oid,median_stake_oid,relative_mileage from daq_construction_weld where active=1 and @privilege_strategy_sql) weld on weld.oid=rework.weld_oid "
					+ "where rework.active=1 and rework.pipe_segment_or_cross_oid=? and rework.approve_status in (1,2) and rework.is_cut=0 and rework.is_measure=0)"
					+ ") total "
					+ "LEFT JOIN (select oid,median_stake_code from daq_median_stake where active=1) stake on total.median_stake_oid=stake.oid";
		sql = VariateInjectUtils.invoke(sql);
		return this.queryForList(sql, new Object[]{pipeSegmentOrCrossOid, pipeSegmentOrCrossOid});
	}
	
	/**
	 * <p>功能描述：修改焊口或返修的is_measure字段。</p>
	  * <p> 葛建。</p>	
	  * @param weldOid
	  * @param value
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月22日 上午10:36:21。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeIsMeasuredField(String weldOid, String bendingOid, int value) {
		if (StringUtils.isNotBlank(weldOid)) {
			String sql = "update daq_construction_weld set is_measure=:value where oid=:oid;"
						+ "update daq_weld_rework_weld set is_measure=:value where oid=:oid;"
						+ "update daq_construction_weld set is_measure=:value where oid in (select weld_oid from daq_weld_rework_weld where oid=:oid)";
			this.baseNamedParameterJdbcTemplate.batchExecute(sql, ImmutableMap.of("value", value,"oid",weldOid));
		}
		if (StringUtils.isNotBlank(bendingOid)) {
			String sql = "update daq_material_hot_bends set is_measure=:value where oid=:oid;"
					+ "update daq_material_pipe_cold_bending set is_measure=:value where oid=:oid;";
			this.baseNamedParameterJdbcTemplate.batchExecute(sql, ImmutableMap.of("value", value,"oid",bendingOid));
		}
	}
}
