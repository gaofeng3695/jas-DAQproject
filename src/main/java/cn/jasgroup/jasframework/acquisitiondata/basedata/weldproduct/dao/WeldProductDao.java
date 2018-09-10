package cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class WeldProductDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	  * <p>功能描述：根据projectOid获取焊接工艺。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:27:55。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByProjectOid(String projectOid) {
		String sql = "select oid as key, weld_produce_code as value from daq_weld_produce_specification where active=1 and project_oid='"+projectOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}
	/***
	  * <p>功能描述：获取焊接工艺规程。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:28:53。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByProjectOid() {
		String sql = "select oid as key, weld_produce_code as value,project_oid from daq_weld_produce_specification where active=1";
		return baseJdbcDao.queryForList(sql, null);
	}
	
}
