package cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.dao.WeldProductDao;

@Service
@Transactional
public class WeldProductService {

	@Autowired
	private WeldProductDao weldProductDao;
	
	/***
	  * <p>功能描述：根据projectOid。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:29:09。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByCondition(String projectOid) {
		if(StringUtils.isNotBlank(projectOid)){
			return weldProductDao.getListByProjectOid(projectOid);
		}else{
			return weldProductDao.getListByProjectOid();
		}
	}	
}
