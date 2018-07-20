package cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao.ColdBendingPipeDao;
import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao.entity.ColdBendingPipe;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;

@Service
@Transactional
public class ColdBendingPipeService extends CommonDataHibernateService{
	
	@Autowired
	private ColdBendingPipeDao coldBendingPipeDao;
	
	/***
	  * <p>功能描述：冷弯管保存。</p>
	  * <p> 雷凯。</p>	
	  * @param coldBendingPipe
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月29日 下午6:00:44。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public String save(ColdBendingPipe coldBendingPipe){
		super.save(coldBendingPipe);
		return coldBendingPipe.getOid();
	}
	
	/***
	  * <p>功能描述：冷弯管修改。</p>
	  * <p> 雷凯。</p>	
	  * @param coldBendingPipe
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月29日 下午6:05:58。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void update(ColdBendingPipe coldBendingPipe){
		super.update(coldBendingPipe);
	}
	
	/**
	  * <p>功能描述：冷弯管删除。</p>
	  * <p> 雷凯。</p>	
	  * @param coldBendingPipe
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月29日 下午6:06:49。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void delete(ColdBendingPipe coldBendingPipe){
		super.delete(coldBendingPipe);
	}
	
	/**
	  * <p>功能描述：根据原材料钢管编号改变它的使用状态。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeCode
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月29日 下午6:26:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void chanageOriginalPipeUseState(ColdBendingPipe clodBendingPipe){
		this.coldBendingPipeDao.chanageOriginalPipeUseState(clodBendingPipe.getPipeOid());
	}
}
