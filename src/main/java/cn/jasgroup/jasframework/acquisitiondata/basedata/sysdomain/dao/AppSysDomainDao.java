package cn.jasgroup.jasframework.acquisitiondata.basedata.sysdomain.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
  *<p>类描述：域值查询dao。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月11日 下午2:46:48。</p>
 */
@Repository
public class AppSysDomainDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	public List<Map<String, Object>> getListData(String domainName) {
		return baseJdbcDao.queryForList("select code_id,code_name,domain_name from sys_domain where active=1 and domain_name in ("+domainName+")", new Object[]{});
	}
	
	
}
