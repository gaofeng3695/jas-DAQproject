package cn.jasgroup.jasframework.acquisitiondata.station.material.statical.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.station.material.statical.dao.StaticalMaterialDao;

/**
  *<p>类描述：静设备物资service。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月4日 上午10:39:39。</p>
 */
@Service
@Transactional
public class StaticalMaterialService {

	@Autowired
	private StaticalMaterialDao staticalMaterialDao;

	/**
	 * <p>功能描述：根据项目查询清管器收发球装置物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月4日 上午10:33:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialLauncherList(String projectOid) {
		return staticalMaterialDao.getMaterialLauncherList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询放空火炬物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月4日 下午4:25:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialFlareList(String projectOid) {
		return staticalMaterialDao.getMaterialFlareList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询过滤器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月7日 下午2:19:38。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialFilterList(String projectOid) {
		return staticalMaterialDao.getMaterialFilterList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询管壳式热交换器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月7日 下午2:39:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialExchangerList(String projectOid) {
		return staticalMaterialDao.getMaterialExchangerList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询加热炉物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月7日 下午4:47:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialHeaterList(String projectOid) {
		return staticalMaterialDao.getMaterialHeaterList(projectOid);
	}

	/**
	 * <p>功能描述：根据项目查询放空立管物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月15日 上午9:35:38。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialVentStackList(String projectOid) {
		return staticalMaterialDao.getMaterialVentStackList(projectOid);
	}

}
