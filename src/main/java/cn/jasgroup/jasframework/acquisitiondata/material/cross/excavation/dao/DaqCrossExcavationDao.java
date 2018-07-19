package cn.jasgroup.jasframework.acquisitiondata.material.cross.excavation.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.jasgroup.jasframework.acquisitiondata.material.cross.excavation.query.bo.DaqCrossExcavationBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess.base.BaseNamedParameterJdbcDao;

/**
 * @description 开挖传跨域dao
 * @author zhangyi
 * @date 2018年7月16日下午3:07:33
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class DaqCrossExcavationDao extends BaseNamedParameterJdbcDao{

	@Resource
	private BaseJdbcDao baseJdbcDao;
	
	/**
	 * <p>功能描述：数据审核。</p>
	 * <p>张毅 </p>	
	 * @param paramMap
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:05:51。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap) {
		Boolean b = false;
		String sql = "update daq_cross_excavation set approve_status = :approveStatus where active=1"
				+ " and oid in(:idList)";
		int count = super.update(sql, paramMap);
		if(count > 0){
			b = true;
		}
		return b;
	}
	
	/**
	 * <p>功能描述：获取单条数据详情。</p>
	 * <p>张毅 </p>	
	 * @param oid
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午2:48:43。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public DaqCrossExcavationBo get(String oid){
		
		String sql = "select t.*, "
				+ " p.project_name,"
				+ " l.pipeline_name,"
				+ " dt.tenders_name,"
				+ "	v.name as crossName,"
				+ "	ms.median_stake_code as startMedianStakeCode,"
				+ "	me.median_stake_code as endMedianStakeCode,"				
				+ "	u1.unit_name as constructUnitName,"
				+ "	u2.unit_name as supervisionUnitName"
				+ " from daq_cross_excavation t "
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ " left join daq_tenders dt on dt.oid=t.tenders_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.cross_oid "
				+ " left join (select m.oid,m.median_stake_code from daq_median_stake m where active=1) ms"
				+ " on ms.oid=t.start_median_stake_oid "
				+ " left join (select m.oid,m.median_stake_code from daq_median_stake m where active=1) me"
				+ " on me.oid=t.end_median_stake_oid "				
				+ " left join pri_unit u1 on u1.oid=t.construct_unit "
				+ " left join pri_unit u2 on u2.oid=t.supervision_unit "
				+ " where t.active = 1 and t.oid=?";
		return (DaqCrossExcavationBo) this.baseJdbcDao.queryForObject(sql, new Object[]{oid}, DaqCrossExcavationBo.class);
	}
}
