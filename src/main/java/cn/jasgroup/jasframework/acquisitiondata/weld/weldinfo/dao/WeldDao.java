package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao;

import java.util.List;
import java.util.Map;

import cn.jasgroup.jasframework.dataaccess3.core.BaseNamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

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
		String sql = "select oid as key,weld_code as value from daq_construction_weld t where t.is_rework=0 and t.approve_status=2 and t.pipe_segment_or_cross_oid=? and t.is_cut=0 "
				+ "union all "
				+ "select oid as key,rework_weld_code as value from daq_weld_rework_weld where active=1 and approve_status=2 and pipe_segment_or_cross_oid=? and is_cut=0";
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
		String sql = "select oid as key,weld_code as value from daq_construction_weld t where t.is_rework=0 and t.approve_status=2 and t.pipe_segment_or_cross_oid=? and t.is_cut=0";
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
}
