package cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.dao.entity.DaqWeldCut;
import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

/***
  * 
  *<p>类描述：焊口割口Dao。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年8月21日 下午5:43:36。</p>
 */
@Repository
public class WeldCutDao {

	@Resource
	private BaseJdbcTemplate baseJdbcTemplate;
	
	public DaqWeldCut find (String oid){
		String sql = "select * from daq_weld_cut t where t.oid=?";
		List<DaqWeldCut> data = this.baseJdbcTemplate.queryForList(sql, new Object[]{oid}, DaqWeldCut.class);
		if(data!=null && data.size()>0){
			return data.get(0);
		}else{
			return null;
		}
	}
	
	/***
	  * <p>功能描述：改变焊口的割口状态。</p>
	  * <p> 雷凯。</p>	
	  * @param weldOid
	  * @param status
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午3:18:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void chanageWledStatus(String weldOid,int status){
		String sql = "update daq_construction_weld set is_cut="+status+" where oid='"+weldOid+"';"
				+ "update daq_weld_rework_weld set is_cut="+status+" where oid='"+weldOid+"';";
		baseJdbcTemplate.batchExecute(sql);
	}
}
