package cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.dao.WeldCutDao;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.dao.entity.DaqWeldCut;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

@Service
@Transactional
public class WeldCutService extends CommonDataHibernateService{
	
	@Resource
	private WeldCutDao weldCutDao;
	
	/**
	  * <p>功能描述：保存之后更改状态。</p>
	  * <p> 雷凯。</p>	
	  * @param daqWeldCut
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午5:35:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChanageWledStatus(DaqWeldCut daqWeldCut){
		this.weldCutDao.chanageWledStatus(daqWeldCut.getWeldOid(), 1);
	}
	/**
	  * <p>功能描述：修改前。</p>
	  * <p> 雷凯。</p>	
	  * @param daqWeldCut
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午5:35:52。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatusBeforeAdvice(DaqWeldCut daqWeldCut){
		DaqWeldCut oldDaqWeldCut = this.weldCutDao.find(daqWeldCut.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldDaqWeldCut);
	}
	/**
	  * <p>功能描述：修改后。</p>
	  * <p> 雷凯。</p>	
	  * @param daqWeldCut
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午5:36:06。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatus(DaqWeldCut daqWeldCut){
		DaqWeldCut oldDaqWeldCut = (DaqWeldCut)BaseEntityThreadLocalHolder.getEntitySnap();
		this.weldCutDao.chanageWledStatus(oldDaqWeldCut.getWeldOid(), 0);
		this.weldCutDao.chanageWledStatus(daqWeldCut.getWeldOid(), 1);
	}
	/***
	  * <p>功能描述：删除之后。</p>
	  * <p> 雷凯。</p>	
	  * @param daqWeldCut
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午5:36:21。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChanageWledStatus(DaqWeldCut daqWeldCut){
		DaqWeldCut oldDaqWeldCut = this.weldCutDao.find(daqWeldCut.getOid());
		this.weldCutDao.chanageWledStatus(oldDaqWeldCut.getWeldOid(), 0);
	}
	
}
