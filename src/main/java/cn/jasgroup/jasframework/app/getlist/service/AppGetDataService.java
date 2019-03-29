package cn.jasgroup.jasframework.app.getlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo.CheckPipeColdBendingBo;
import cn.jasgroup.jasframework.app.getlist.dao.AppGetDataDao;
import cn.jasgroup.jasframework.dataaccess.data.Page;

@Service
@Transactional
public class AppGetDataService {

	@Autowired
	private AppGetDataDao appGetDataDao;

	/**
	 * <p>功能描述：APP端防腐管检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月28日 下午4:53:36。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getCoatingPipeListByPage(int page, int rows, String projectOid) {
		return appGetDataDao.getCoatingPipeListByPage(page, rows, projectOid);
	}

	/**
	 * <p>功能描述：APP端热煨弯管检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月28日 下午4:56:35。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getHotBendsListByPage(int page, int rows, String projectOid) {
		return appGetDataDao.getHotBendsListByPage(page, rows, projectOid);
	}
	
	/**
	 * <p>功能描述：APP端冷弯管检查分页查询。</p>
	 * <p> 葛建。</p>	
	 * @param page
	 * @param rows
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2019年3月28日 下午4:56:35。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getColdBendsListByPage(int page, int rows, String projectOid) {
		return appGetDataDao.getColdBendsListByPage(page, rows, projectOid);
	}

	/**
	 * <p>功能描述：APP端焊口记录分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月29日 上午10:03:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getWeldInfoList(int page, int rows, String projectOid, String approveStatus) {
		return appGetDataDao.getWeldInfoList(page, rows, projectOid, approveStatus);
	}

	/**
	 * <p>功能描述：APP端焊口返修分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月29日 上午10:04:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getWeldReworkList(int page, int rows, String projectOid, String approveStatus) {
		return appGetDataDao.getWeldReworkList(page, rows, projectOid, approveStatus);
	}

	/**
	 * <p>功能描述：APP端中线测量分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月29日 上午10:08:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getMeasuredResultList(int page, int rows, String projectOid, String approveStatus) {
		return appGetDataDao.getMeasuredResultList(page, rows, projectOid, approveStatus);
	}

	/**
	 * <p>功能描述：APP端防腐补口检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月29日 上午10:11:51。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getAnticorrosionCheckList(int page, int rows, String projectOid, String approveStatus) {
		return appGetDataDao.getAnticorrosionCheckList(page, rows, projectOid, approveStatus);
	}
	
}
