package cn.jasgroup.jasframework.acquisitiondata.privilege.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.base.controller.BaseController;
import cn.jasgroup.jasframework.security.AuthUser;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.security.service.bo.UnitBo;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@RestController
@RequestMapping(value="daq/privilege")
public class DaqPrivilegeController extends BaseController{
	
	@Resource(name="daqPrivilegeService")
	private DaqPrivilegeService daqPrivilegeService;

	@Autowired
	private UnitService unitService;
	
	@RequestMapping("getUnitByCurrentUser")
	public Object login(HttpServletRequest request){
		AuthUser user = ThreadLocalHolder.getCurrentUser();
		return unitService.getUnitBoByOid(user.getUnitId());
	}
	
	/***
	  * <p>功能描述：获取当前用户所在部门下的项目列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。`
	  * <p>创建日期:2018年7月3日 上午11:40:16。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getProjectList")
	public Object getProjectList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String pipeNetworkTypeCode = param.get("pipeNetworkTypeCode");
			if(StringUtils.isBlank(pipeNetworkTypeCode)){
				pipeNetworkTypeCode = "pipe_network_code_001";
			}
			List<Map<String,Object>> rows = this.daqPrivilegeService.getProject(pipeNetworkTypeCode);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");			
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：获取当前用户所在部门下的标段列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 上午11:38:18。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getTendersList",method = RequestMethod.POST)
	@ResponseBody
	public Object getTendersList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String projectOid=param.get("projectOid");
		try {
			List<Map<String,Object>> rows = this.daqPrivilegeService.getTendersList(projectOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	 * <p>功能描述：根据标段oid获取当前用户所在部门及下级部门的管线列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 上午11:24:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getPipelineListByTendersOid",method = RequestMethod.POST)
	@ResponseBody
	public Object getPipelineListByTendersOid(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String tendersOid = param.get("tendersOid");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getPipelineList(tendersOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据管线oid获取当前用户所在部门及下级部门的站场/阀室列表。</p>
	  * <p> 葛建。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月17日 上午11:23:06。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getPipeStationListByPipelineOid",method = RequestMethod.POST)
	@ResponseBody
	public Object getPipeStationListByPipelineOid(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String pipelineOid = param.get("pipelineOid");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getPipeStationList(pipelineOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：根据管线oid获取当前用户所在部门及一下部门所有的线路段和穿跨越列表。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月3日 下午3:03:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getPipeSegmentOrCrossList",method = RequestMethod.POST)
	@ResponseBody
	public Object getPipeSegmentOrCrossList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String pipelineOid = param.get("pipelineOid");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getPipeSegmentOrCrossList(pipelineOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	 * <p>功能描述：根据标段获取监理单位列表。</p>
	 * <p> 雷凯。</p>	
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月3日 上午11:24:25。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getSupervisionUnitByTendersOid",method = RequestMethod.POST)
	@ResponseBody
	public Object getSupervisionUnitByTendersOid(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String tendersOid = param.get("tendersOid");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getSupervisionUnitByTendersOid(tendersOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：根据标段获取施工单位列表。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月9日 上午11:12:08。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getConstructionUnitByTendersOid",method = RequestMethod.POST)
	@ResponseBody
	public Object getConstructionUnitByTendersOid(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String tendersOid = param.get("tendersOid");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getConstructionUnitByTendersOid(tendersOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：根据线路段oid或者穿跨越oid获取中线桩列表。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 下午2:23:38。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getMedianStakeList",method = RequestMethod.POST)
	@ResponseBody
	public Object getMedianStakeList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String pipeSegmentOrCrossOid = param.get("pipeSegmentOrCrossOid");
			if(StringUtils.isNotBlank(param.get("pipeSegmentOrCrossOid"))){
				pipeSegmentOrCrossOid = param.get("pipeSegmentOrCrossOid");
			}else if(StringUtils.isNotBlank(param.get("crossOid"))){
				pipeSegmentOrCrossOid = param.get("crossOid");
			}else if(StringUtils.isNotBlank(param.get("pipeSegmentOid"))){
				pipeSegmentOrCrossOid = param.get("pipeSegmentOid");
			}
			List<Map<String,Object>> rows = this.daqPrivilegeService.getMedianStakeList(pipeSegmentOrCrossOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：获取当前用户所在部门。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 下午1:54:28。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getCurrentUnitId",method = RequestMethod.POST)
	@ResponseBody
	public Object getCurrentUnitId(HttpServletRequest request){
		ListResult<Map<String,Object>> result = null;
		try{
			List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
			AuthUser currentUser = ThreadLocalHolder.getCurrentUser();
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("key", currentUser.getUnitId());
			data.put("value", currentUser.getUnitName());
			rows.add(data);
			result = new ListResult<>(1, "200", "ok", rows);
		}catch(Exception e){
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：根据管线oid和穿越类型获取当前用户所在部门及下级部门下的穿越列表。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 下午6:18:12。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getCrossList",method = RequestMethod.POST)
	@ResponseBody
	public Object getCrossList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String pipelineOid = param.get("pipelineOid");
			String crossWay = param.get("crossWay");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getCrossList(pipelineOid,crossWay);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	 * <p>功能描述：根据管线oid获取当前用户所在部门及下级部门下的线路段列表。</p>
	 * <p> 雷凯。</p>	
	 * @param request
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月10日 下午6:18:12。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getPipeSegmentList",method = RequestMethod.POST)
	@ResponseBody
	public Object getPipeSegmentList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String pipelineOid = param.get("pipelineOid");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getPipeSegmentList(pipelineOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/**
	  * <p>功能描述：根据监理单位获取对应标段下的施工单位和检测单位。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午4:38:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getConstructAndDetectionUnitList",method = RequestMethod.POST)
	@ResponseBody
	public Object getConstructAndDetectionUnitList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String projectOid = param.get("projectOid");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getConstructAndDetectionUnitList(projectOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	
	/***
	  * <p>功能描述：获取离线数据。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 上午10:59:36。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getOfflineData",method = RequestMethod.POST)
	@ResponseBody
	public Object getOfflineData(HttpServletRequest request){
		SimpleResult<Map<String,Object>> result = null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {
			List<Map<String, Object>> projectRows = this.daqPrivilegeService.getProject("pipe_network_code_001");
			dataMap.put("projectData", projectRows);
			List<Map<String, Object>> tendersRows = this.daqPrivilegeService.getTendersList(null);
			dataMap.put("tendersData", tendersRows);
			List<Map<String,Object>> pipelineRows = this.daqPrivilegeService.getPipelineList(null);
			dataMap.put("pipelineData", pipelineRows);
			List<Map<String, Object>> pipeSegmentOrCrossRows = this.daqPrivilegeService.getPipeSegmentOrCrossList(null);
			dataMap.put("pipeSegmentOrCrossData", pipeSegmentOrCrossRows);
			List<Map<String,Object>> supervisionUnitRows = this.daqPrivilegeService.getSupervisionUnitByTendersOid(null);
			dataMap.put("supervisionUnitData", supervisionUnitRows);
//			List<Map<String,Object>> medianStakeRows = this.daqPrivilegeService.getMedianStakeList(null);
//			dataMap.put("medianStakeData", medianStakeRows);
			result = new SimpleResult<Map<String,Object>>(0, "200", "ok", dataMap);
		} catch (Exception e) {
			result = new SimpleResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：获取施工单位所有用户。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月11日 上午9:45:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getConstructUnitAllUser",method = RequestMethod.POST)
	@ResponseBody
	public Object getConstructUnitAllUser(HttpServletRequest request){
		ListResult<Map<String,Object>> result = null;
		try{
			List<Map<String,Object>> rows = this.daqPrivilegeService.getConstructUnitAllUser();
			result = new ListResult<>(0, "200", "ok", rows);
		}catch(Exception e){
			result = new ListResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
