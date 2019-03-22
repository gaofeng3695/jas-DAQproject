package cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.dao;

import java.util.List;
import java.util.Map;

import cn.jasgroup.jasframework.acquisitiondata.utils.VariateInjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Repository
public class WorkUnitDao {
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	  * <p>功能描述：根据项目oid 和 机组类型类型获取施工机组。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param types
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月2日 上午11:30:00。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByCondition(String projectOid, String types) {
		String constructUnit= ThreadLocalHolder.getCurrentUnitId();
		String sql = "select oid as key, work_unit_code as value from daq_work_unit where active=1 and @privilege_strategy_sql and project_oid='"+projectOid+"' and work_unit_type in ("+types+") and construct_unit='"+constructUnit+"';";
		sql = VariateInjectUtils.invoke(sql);
		return baseJdbcDao.queryForList(sql, null);
	}
	/***
	  * <p>功能描述：获取施工机组人员列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:35:23。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByCondition() {
		String sql = "select oid as key, work_unit_code as value,project_oid,work_unit_type from daq_work_unit where active=1";
		return baseJdbcDao.queryForList(sql, null);
	}
	
}
