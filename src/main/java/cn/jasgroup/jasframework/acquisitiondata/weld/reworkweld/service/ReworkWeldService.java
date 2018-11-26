package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao.ReworkWeldDao;
import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao.entity.ReworkWeld;
import cn.jasgroup.jasframework.base.data.BaseEntity;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

@Service
@Transactional
public class ReworkWeldService extends CommonDataJdbcService {

	@Resource
	private ReworkWeldDao reworkWeldDao;

	public void changeGeomColumn(ReworkWeld reworkWeld) {
		reworkWeldDao.changeGeomColumn(reworkWeld.getOid(), reworkWeld.getWeldOid());
	}
	/**
	 * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param reworkWeld
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月20日 上午9:47:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChangeWeldStatus(ReworkWeld reworkWeld) {
		reworkWeldDao.changeWeldStatus(reworkWeld.getWeldOid(), 1);
		reworkWeldDao.changeGeomColumn(reworkWeld.getOid(), reworkWeld.getWeldOid());
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param reworkWeld
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月20日 上午9:47:29。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChangeWeldStatus(ReworkWeld reworkWeld) {
		ReworkWeld oldReworkWeld = (ReworkWeld)BaseEntityThreadLocalHolder.getEntitySnap();
		reworkWeldDao.changeWeldStatus(oldReworkWeld.getWeldOid(), 0);
		reworkWeldDao.changeWeldStatus(reworkWeld.getWeldOid(), 1);
		reworkWeldDao.changeGeomColumn(reworkWeld.getOid(), reworkWeld.getWeldOid());
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param reworkWeld
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月20日 上午9:47:33。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChangeWeldStatusBeforeAdvice(ReworkWeld reworkWeld) {
		ReworkWeld oldReworkWeld = reworkWeldDao.find(reworkWeld.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldReworkWeld);
	}
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param reworkWeld
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月20日 上午9:47:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChangeWeldStatus(ReworkWeld reworkWeld) {
		reworkWeld = (ReworkWeld)this.get(ReworkWeld.class, reworkWeld.getOid());
		reworkWeldDao.changeWeldStatus(reworkWeld.getWeldOid(), 0);
	}
}
