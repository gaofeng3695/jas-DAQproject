package cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.dao.PipeFittingDao;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.WeldDao;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

@Service
@Transactional
public class PipeFittingService extends CommonDataHibernateService{
	
	@Resource
	private PipeFittingDao pipeFittingDao;
	
	@Resource
	private WeldDao weldDao;
	
	/***
	  * <p>功能描述：根据管件类型获取相应的管件列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeTypeCode
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月13日 上午11:33:55。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPipeFittingList(String projectOid,String pipeSegmentOrCrossOid,String pipeTypeCode){
		return this.pipeFittingDao.getPipeFittingList(projectOid,pipeSegmentOrCrossOid,pipeTypeCode);
	}
	
	/***
	  * <p>功能描述：焊口新增时更新管件信息。</p>
	  * <p> 雷凯。</p>	
	  * @param constructionWeld
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月13日 下午2:47:35。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChanagePipeFittingUseState(ConstructionWeld constructionWeld){
		String tendersOid = constructionWeld.getTendersOid();
		String pipelineOid = constructionWeld.getPipelineOid();
		String frontPipeOid = constructionWeld.getFrontPipeOid();
		String frontPipeTypeCode = constructionWeld.getFrontPipeType();
		this.pipeFittingDao.updateFrontPipeFitting(tendersOid, pipelineOid, frontPipeOid, frontPipeTypeCode,1);
		String backPipeOid = constructionWeld.getBackPipeOid();
		String backPipeTypeCode = constructionWeld.getBackPipeType();
		this.pipeFittingDao.updateBackPipeFitting(tendersOid, pipelineOid, backPipeOid, backPipeTypeCode,1);
	}
	
	/**
	  * <p>功能描述：修改焊口信息时更新管件信息。</p>
	  * <p> 雷凯。</p>	
	  * @param constructionWeld
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月13日 下午3:18:46。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanagePipeFittingUseState(ConstructionWeld constructionWeld){
		ConstructionWeld oldConstructionWeld = (ConstructionWeld) BaseEntityThreadLocalHolder.getEntitySnap();
		String oldFrontPipeOid = oldConstructionWeld.getFrontPipeOid();
		String oldFrontPipeTypeCode = oldConstructionWeld.getFrontPipeType();
		this.pipeFittingDao.updateFrontPipeFitting("", "", oldFrontPipeOid, oldFrontPipeTypeCode,0);
		String oldBackPipeOid = oldConstructionWeld.getBackPipeOid();
		String oldBackPipeTypeCode = oldConstructionWeld.getBackPipeType();
		this.pipeFittingDao.updateBackPipeFitting( "", "", oldBackPipeOid, oldBackPipeTypeCode,0);
		
		
		String tendersOid = constructionWeld.getTendersOid();
		String pipelineOid = constructionWeld.getPipelineOid();
		String frontPipeOid = constructionWeld.getFrontPipeOid();
		String frontPipeTypeCode = constructionWeld.getFrontPipeType();
		this.pipeFittingDao.updateFrontPipeFitting(tendersOid, pipelineOid, frontPipeOid, frontPipeTypeCode,1);
		String backPipeOid = constructionWeld.getBackPipeOid();
		String backPipeTypeCode = constructionWeld.getBackPipeType();
		this.pipeFittingDao.updateBackPipeFitting(tendersOid, pipelineOid, backPipeOid, backPipeTypeCode,1);
		
	}
	public void updateChanageBeforeAdvice(ConstructionWeld constructionWeld){
		ConstructionWeld oldConstructionWeld = (ConstructionWeld) weldDao.find(constructionWeld.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldConstructionWeld);
	}
	/***
	  * <p>功能描述：删除焊口信息时更新管件信息。</p>
	  * <p> 雷凯。</p>	
	  * @param constructionWeld
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月13日 下午3:22:00。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChanagePipeFittingUseState(ConstructionWeld constructionWeld){
		ConstructionWeld oldConstructionWeld = (ConstructionWeld) weldDao.find(constructionWeld.getOid());
		if(oldConstructionWeld!=null){
			String oldFrontPipeOid = oldConstructionWeld.getFrontPipeOid();
			String oldFrontPipeTypeCode = oldConstructionWeld.getFrontPipeType();
			this.pipeFittingDao.updateFrontPipeFitting("", "", oldFrontPipeOid, oldFrontPipeTypeCode,0);
			String oldBackPipeOid = oldConstructionWeld.getBackPipeOid();
			String oldBackPipeTypeCode = oldConstructionWeld.getBackPipeType();
			this.pipeFittingDao.updateBackPipeFitting("", "", oldBackPipeOid, oldBackPipeTypeCode,0);
		}
	}

	/**
	 * <p>功能描述：根据项目查询未使用且未进行中线测量的弯管列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午3:20:31。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getBendingList(String projectOid) {
		return pipeFittingDao.getBendingList(projectOid);
	}
}
