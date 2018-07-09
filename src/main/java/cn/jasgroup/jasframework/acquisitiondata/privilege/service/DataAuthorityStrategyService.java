package cn.jasgroup.jasframework.acquisitiondata.privilege.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.framework.data.authority.service.AbstractDataAuthorityStrategyService;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

@Service
@Transactional
public class DataAuthorityStrategyService extends AbstractDataAuthorityStrategyService{

	/**
	 * 
	  * <p>功能描述:获取数据权限过滤规则。</p>	
	  * @param query
	  * @return
	  * <p> 张锡梓</p>
	  * @since JDK1.8。
	  * <p>创建日期2018年6月14日 下午1:40:28。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@Override
	public List<String> getDataFilterRegulation(BaseJavaQuery query) {
		List<String> dataFilterRegulationList = new ArrayList<String>();
		dataFilterRegulationList.add("org");
		return dataFilterRegulationList;
	}
}
