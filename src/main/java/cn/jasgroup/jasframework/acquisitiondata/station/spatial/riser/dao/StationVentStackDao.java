package cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.station.spatial.riser.dao.entity.StationVentStack;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
 * 
  *<p>类描述：放空立管dao。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月15日 上午10:05:45。</p>
 */
@Repository
public class StationVentStackDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	 * <p>功能描述：放空立管物资数据更新。</p>
	  * <p> 葛建。</p>	
	  * @param tendersOid
	  * @param pipelineOid
	  * @param pipeStationOid
	  * @param materialOid
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月15日 上午10:48:40。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateMaterialData(String tendersOid, String pipelineOid, String pipeStationOid, String materialOid) {
		String sql = "update daq_s_material_vent_stack set tenders_oid='"+tendersOid+"',pipeline_oid='"+pipelineOid+"',pipe_station_oid='"+pipeStationOid+"' where oid='"+materialOid+"'";
		baseJdbcDao.update(sql, null);
	}

	public StationVentStack find(String oid) {
		String sql = "select * from daq_station_vent_stack where oid = ?";
		List<StationVentStack> list = baseJdbcDao.queryForList(sql, new Object[]{oid}, StationVentStack.class);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
}
