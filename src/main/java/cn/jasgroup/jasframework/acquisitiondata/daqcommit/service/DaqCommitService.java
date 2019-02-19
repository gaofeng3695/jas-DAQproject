package cn.jasgroup.jasframework.acquisitiondata.daqcommit.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.daqcommit.dao.DaqCommitDao;
import cn.jasgroup.jasframework.support.ModelFacade;

@Service
@Transactional
public class DaqCommitService {

	@Autowired
	private DaqCommitDao daqCommitDao;

	/**
	 * <p>功能描述：保存业务流程提交业务。</p>
	  * <p> 葛建。</p>	
	  * @param businessOids
	  * @param functionCode
	  * @param className
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月18日 下午5:11:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeBussinessCommitStatus(List<String> businessOids, String functionCode, String className) {
		// 拼接oids
		String oids = "";
		for (int i = 0; i < businessOids.size(); i++) {
			if (i == businessOids.size() - 1) {
				oids += "'" + businessOids.get(i) + "'";
			}else {
				oids += "'" + businessOids.get(i) + "',";
			}
		}
		// 若functionCode不为空，则为自定义表单类
		if (StringUtils.isNotBlank(functionCode)) {
			daqCommitDao.changeBussinessCommitStatus(null, functionCode, oids);
		}else if (StringUtils.isNotBlank(className)) {
			try {
				// 根据className获取对应的表名
				String tableName = ModelFacade.getTableName(className);
				daqCommitDao.changeBussinessCommitStatus(tableName, null, oids);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
