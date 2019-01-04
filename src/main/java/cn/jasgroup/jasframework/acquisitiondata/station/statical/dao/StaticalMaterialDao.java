package cn.jasgroup.jasframework.acquisitiondata.station.statical.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
 * 
  *<p>类描述：静设备物资dao。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月4日 上午10:39:50。</p>
 */
@Repository
public class StaticalMaterialDao {
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	 * <p>功能描述：根据项目查询清管器收发球装置物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月4日 上午10:33:08。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialLauncherList(String projectOid) {
		String sql = "select oid as key,manufacture_number as value from daq_s_material_pig_receiver_launcher where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}


}
