package cn.jasgroup.jasframework.acquisitiondata.scope.area.serivce;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.scope.area.dao.AreaDao;
import cn.jasgroup.jasframework.base.service.BaseService;

@Service
@Transactional
public class AreaService extends BaseService{
	
	@Resource
	private AreaDao areaDao;
	/**
	 * <p>功能描述：获取省。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午4:09:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getProvinceList(){
		return this.areaDao.getProvinceList();
	}
	/***
	 * <p>功能描述：根据省获取市。</p>
	  * <p> 雷凯。</p>	
	  * @param province
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午4:10:06。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getCityList(String province){
		return this.areaDao.getCityList(province);
	}
	/***
	 * <p>功能描述：根据市获取区/县。</p>
	  * <p> 雷凯。</p>	
	  * @param province
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午4:10:30。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getCountyList(String province){
		return this.areaDao.getCountyList(province);
	}
}
