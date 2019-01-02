package cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class WorkPersonDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/***
	  * <p>功能描述：根据施工机组Oid和人员类型获取机组人员。</p>
	  * <p> 雷凯。</p>	
	  * @param workUnitOid
	  * @param str
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:31:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByCondition(String workUnitOid, String str) {
		String sql = "select oid as key, personnel_name as value from daq_work_personnel where active=1 and work_unit_oid='"+workUnitOid+"' and personnel_type in ("+str+");";
		return baseJdbcDao.queryForList(sql, null);
	}
	/**
	  * <p>功能描述：根据施工机组oid获取机组人员列表。</p>
	  * <p> 雷凯。</p>	
	  * @param workUnitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:31:26。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPersonByWorkUnit(String workUnitOid) {
		String sql ="select oid as key, personnel_name as value from daq_work_personnel where active=1 and work_unit_oid='"+workUnitOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}
	/***
	  * <p>功能描述：获取机组人员列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:31:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPersonByWorkUnit() {
		String sql ="select oid as key, personnel_name as value,work_unit_oid,personnel_type from daq_work_personnel where active=1";
		return baseJdbcDao.queryForList(sql, null);
	}
	
	/**
	 * <p>功能描述：查询机组下的焊工列表。</p>
	  * <p> 葛建。</p>	
	  * @param workUnitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月2日 上午11:38:39。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getWeldersList(String workUnitOid) {
		String sql ="select oid as key,personnel_name as value from daq_work_personnel "
				+ "where active=1 and personnel_type in ('personnel_type_code_006','personnel_type_code_005') and work_unit_oid = '"+workUnitOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}
}
