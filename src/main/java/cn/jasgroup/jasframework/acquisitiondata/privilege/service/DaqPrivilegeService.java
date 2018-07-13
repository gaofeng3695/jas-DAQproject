package cn.jasgroup.jasframework.acquisitiondata.privilege.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.privilege.dao.DaqPrivilegeDao;
import cn.jasgroup.jasframework.base.service.BaseService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Service
@Transactional
public class DaqPrivilegeService extends BaseService{
	
	@Resource(name="daqPrivilegeDao")
	private DaqPrivilegeDao daqPrivilegeDao;
	
	/***
	  * <p>功能描述：获取当前用户所在部门下的项目列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午2:06:56。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getProject(){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		return this.daqPrivilegeDao.getProjectList(unitOid);
	}
	/***
	  * <p>功能描述：获取当前用户所在部门下的标段列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午2:08:16。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getTendersList(String projectOid){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		return this.daqPrivilegeDao.getTendersList(unitOid,projectOid);
	}
	/***
	  * <p>功能描述：根据项根据标段oid获取部门及部门一下的管线列表。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午2:45:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getPipelineList(String tendersOid){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		return this.daqPrivilegeDao.getPipelineList(tendersOid, unitOid);
	}
	/***
	  * <p>功能描述：根据管线oid获取当前部门及一下部门的线路段和穿跨越列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipelineOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午3:01:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getPipeSegmentOrCrossList(String pipelineOid){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		return this.daqPrivilegeDao.getPipeSegmentOrCrossList(pipelineOid, unitOid);
	}
	/***
	  * <p>功能描述：根据标段oid获取该标段下监理单位。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午2:09:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getSupervisionUnitByTendersOid(String tendersOid){
		return this.daqPrivilegeDao.getSupervisionUnitByTendersOid(tendersOid);
	}
	/***
	  * <p>功能描述：根据标段oid获取该标段下施工单位。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月9日 上午11:13:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getConstructionUnitByTendersOid(String tendersOid){
		return this.daqPrivilegeDao.getConstructionUnitByTendersOid(tendersOid);
	}
	/***
	  * <p>功能描述：根据线路段oid或者穿跨越oid获取中线桩列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 下午2:21:42。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getMedianStakeList(String pipeSegmentOrCrossOid){
		return this.daqPrivilegeDao.getMedianStakeList(pipeSegmentOrCrossOid);
	}
	/**
	  * <p>功能描述：根据管线oid和穿越类型获取当前用户所在部门及下级部门下的穿越列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipelineOid
	  * @param crossWay
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 下午6:17:31。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getCrossList(String pipelineOid,String crossWay){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		return this.daqPrivilegeDao.getCrossList(pipelineOid, crossWay, unitOid);
	}
	/***
	  * <p>功能描述：根据管线oid获取当前用户所在部门及下级部门下的线路段列表。</p>
	  * <p> 雷凯。</p>	
	  * @param pipelineOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月11日 下午3:23:11。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getPipeSegmentList(String pipelineOid){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		return this.daqPrivilegeDao.getPipeSegmentList(pipelineOid, unitOid);
	}
}
