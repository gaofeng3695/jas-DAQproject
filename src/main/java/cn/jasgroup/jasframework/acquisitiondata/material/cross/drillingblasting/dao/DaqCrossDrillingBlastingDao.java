package cn.jasgroup.jasframework.acquisitiondata.material.cross.drillingblasting.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.jasgroup.jasframework.acquisitiondata.material.cross.drillingblasting.query.bo.DaqCrossDrillingBlastingBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
 * @description 钻爆隧道穿越
 * @author zhangyi
 * @date 2018年7月17日上午10:45:00
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class DaqCrossDrillingBlastingDao extends BaseJdbcDao {
	
	@Resource
	private BaseJdbcDao baseJdbcDao;
	
	/**
	 * <p>功能描述：数据审核。</p>
	 * <p>张毅 </p>	
	 * @param oid
	 * @param approveStatus
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:05:51。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(String oid, Integer approveStatus) {
		Boolean b = false;
		String sql = "update daq_cross_drilling_blasting set approve_status = ? where active=1"
				+ " and oid =?";
		
		int count = this.baseJdbcDao.update(sql, new Object[]{approveStatus, oid});
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
	public DaqCrossDrillingBlastingBo get(String oid){
		
		String sql = "select t.*, "
				+ " p.project_name,"
				+ " l.pipeline_name,"
				+ " dt.tenders_name,"
				+ "	v.name as crossName,"
				+ "	ms.median_stake_code as startMedianStakeCode,"
				+ "	me.median_stake_code as endMedianStakeCode,"				
				+ "	u1.unit_name as constructUnitName,"
				+ "	u2.unit_name as supervisionUnitName"
				+ " from daq_cross_drilling_blasting t "
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
		return (DaqCrossDrillingBlastingBo) this.baseJdbcDao.queryForObject(sql, new Object[]{oid}, DaqCrossDrillingBlastingBo.class);
	}	
	
}