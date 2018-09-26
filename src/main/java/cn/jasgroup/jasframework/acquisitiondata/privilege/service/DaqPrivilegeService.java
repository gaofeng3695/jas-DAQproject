package cn.jasgroup.jasframework.acquisitiondata.privilege.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
	public List<Map<String,Object>>getProject(String pipeNetworkTypeCode){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		return this.daqPrivilegeDao.getProjectList(unitOid,pipeNetworkTypeCode);
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
	/**
	  * <p>功能描述：根据项目oid获取施工单位列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午9:24:33。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getConstructionUnitByProjectOid(String projectOid){
		return this.daqPrivilegeDao.getConstructionUnitByProjectOid(projectOid);
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
	/***
	  * <p>功能描述：根据监理单位获取对应标段下的施工单位和检测单位。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午4:38:15。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getConstructAndDetectionUnitList(){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		List<Map<String, Object>> resultList = this.daqPrivilegeDao.getConstructAndDetectionUnitList(unitOid);

		List<String> unitIds = resultList.stream().map(map -> String.valueOf(map.get("key"))).collect(Collectors.toList());
        List<Map<String, String>> unitList = this.daqPrivilegeDao.getUnitInIds(unitIds);
        Map<String, String> idToHierarchy = unitList.stream().collect(Collectors.toMap(mapObj -> mapObj.get("oid"), mapObj -> mapObj.get("hierarchy"), (a, b) -> b));
        resultList.forEach(resultMap -> resultMap.put("hierarchy", idToHierarchy.get(resultMap.get("key"))));

        return resultList;
	}



	/**
	  * <p>功能描述：获取施工单位所有用户。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月11日 上午9:47:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>>getConstructUnitAllUser(){
		return this.daqPrivilegeDao.getConstructUnitAllUser();
	}
	
	/**
	 * <p>功能描述：根据管线oid获取当前用户所在部门及下级部门的站场/阀室列表。</p>
	  * <p> 葛建。</p>	
	  * @param pipelineOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午11:22:49。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPipeStationList(String pipelineOid) {
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		return this.daqPrivilegeDao.getPipeStationList(pipelineOid,unitOid);
	}
}
