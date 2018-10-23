package cn.jasgroup.jasframework.acquisitiondata.material.check.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.check.dao.CheckDao;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;

@Service
@Transactional
public class CheckService extends CommonDataHibernateService{
	
	@Autowired
	private CheckDao checkDao;
	
	/**
	 * <p>功能描述：获取绝缘接头的出厂编号。</p>
	  * <p> 葛建。</p>	
	 * @param projectOid 
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年10月17日 下午3:29:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getManufacturerCode(String projectOid){
		return checkDao.getManufacturerCode(projectOid);
	}

}
