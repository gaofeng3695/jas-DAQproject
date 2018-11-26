package cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao.ColdBendingPipeDao;
import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao.entity.ColdBendingPipe;
import cn.jasgroup.jasframework.base.data.BaseEntity;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

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
	public void saveChanageOriginalPipeUseState(ColdBendingPipe coldBendingPipe){
		this.coldBendingPipeDao.chanageOriginalPipeUseState(1,coldBendingPipe.getProjectOid(),coldBendingPipe.getPipelineOid(),coldBendingPipe.getTendersOid(),coldBendingPipe.getPipeOid());
	}
	public void updateChanageOriginalPipeUseState(ColdBendingPipe coldBendingPipe){
		ColdBendingPipe oldColdBendingPipe = (ColdBendingPipe)BaseEntityThreadLocalHolder.getEntitySnap();
		this.coldBendingPipeDao.chanageOriginalPipeUseState(0,oldColdBendingPipe.getProjectOid(),"","",oldColdBendingPipe.getPipeOid());
		this.coldBendingPipeDao.chanageOriginalPipeUseState(1,coldBendingPipe.getProjectOid(),coldBendingPipe.getPipelineOid(),coldBendingPipe.getTendersOid(),coldBendingPipe.getPipeOid());
	}
	public void deleteChanageOriginalPipeUseState(ColdBendingPipe coldBendingPipe){
		ColdBendingPipe oldColdBendingPipe = this.coldBendingPipeDao.find(coldBendingPipe.getOid());
		this.coldBendingPipeDao.chanageOriginalPipeUseState(0,oldColdBendingPipe.getProjectOid(),"","",oldColdBendingPipe.getPipeOid());
	}
	public void updateChanageBeforeAdvice(ColdBendingPipe coldBendingPipe){
		ColdBendingPipe oldColdBendingPipe = this.coldBendingPipeDao.find(coldBendingPipe.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldColdBendingPipe);
	}
	
	/***
	 * <p>功能描述：获取冷弯管下拉选列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月20日 下午3:13:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getListData(String tendersOid){
		return this.coldBendingPipeDao.getList(tendersOid);
	}
}
