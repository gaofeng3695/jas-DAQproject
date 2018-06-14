package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.MedianStakeDao;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo.MedianStakeQueryBo;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;

@Service
@Transactional
public class MedianStakeService extends CommonDataHibernateService{
	
	@Resource
	private MedianStakeDao medianStakeDao;
	
	/**
	  * <p>功能描述：新增中线桩。</p>
	  * <p> 雷凯。</p>	
	  * @param medianStake
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午6:30:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public String save(MedianStake medianStake){
		super.save(medianStake);
		return medianStake.getOid();
	}
	/***
	 * <p>功能描述：更新中线桩。</p>
	  * <p> 雷凯。</p>	
	  * @param medianStake
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午6:33:08。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void update(MedianStake medianStake){
		super.update(medianStake);
	}
	/***
	 * <p>功能描述：删除中线桩。</p>
	  * <p> 雷凯。</p>	
	  * @param medianStake
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午6:35:31。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void delete(MedianStake medianStake){
		super.delete(medianStake);
	}
	/**
	 * <p>功能描述：获取中线桩详情。</p>
	  * <p> 雷凯。</p>	
	  * @param oid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午6:38:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public MedianStakeQueryBo get(String oid){
		return medianStakeDao.get(oid);
	}
	/**
	 * <p>功能描述：获取中线桩列表，用于下拉选选。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param pipelineOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月14日 上午11:25:08。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getList(String projectOid,String pipelineOid){
		return this.medianStakeDao.getList(projectOid, pipelineOid);
	}
}
