package cn.jasgroup.jasframework.acquisitiondata.basedata.sysdomain.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.basedata.sysdomain.dao.AppSysDomainDao;

/**
  *<p>类描述：域值查询service。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月11日 下午2:47:00。</p>
 */
@Service
@Transactional
public class AppSysDomainService {

	@Autowired
	private AppSysDomainDao sysdomainDao;

	public List<Map<String, Object>> getListData(String domainNames) {
		String domainName = "";
		String[] names = domainNames.split(",");
		for (int i = 0; i < names.length; i++) {
			if (i != names.length-1) {
				domainName += "'"+names[i]+"',";
			}else{
				domainName += "'"+names[i]+"'";
			}
		}
		return sysdomainDao.getListData(domainName);
	}
	
}
