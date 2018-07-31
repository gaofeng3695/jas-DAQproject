package cn.jasgroup.jasframework.acquisitiondata.cathodic.teststack.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.cathodic.teststack.dao.CathodicTestStakeDao;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;

@Service
@Transactional
public class CathodicTestStakeService extends CommonDataHibernateService{

	@Resource
	private CathodicTestStakeDao cathodicTestStakeDao;
	
	/***
	  * <p>功能描述：根据标段oid获取测试桩列表。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月31日 下午1:58:02。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getTestStakeList(String tendersOid){
		return this.cathodicTestStakeDao.getTestStakeList(tendersOid);
	}
}
