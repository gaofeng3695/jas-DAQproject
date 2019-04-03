package cn.jasgroup.jasframework.acquisitiondata.privilege.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.entity.DaqWeldCodeRegular;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.entity.DaqWeldCodeRegular;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.service.DaqWeldCodeRegularService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.acquisitiondata.variate.UnitHierarchyEnum;
import cn.jasgroup.jasframework.base.controller.BaseController;
import cn.jasgroup.jasframework.base.service.RedisService;
import cn.jasgroup.jasframework.i18n.service.I18nService;
import cn.jasgroup.jasframework.log.client.support.LogTemplateFactory;
import cn.jasgroup.jasframework.security.AuthUser;
import cn.jasgroup.jasframework.security.controller.LoginController;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.dao.entity.SysLoginLog;
import cn.jasgroup.jasframework.security.service.LoginLogService;
import cn.jasgroup.jasframework.security.service.RoleService;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.security.service.bo.RoleBo;
import cn.jasgroup.jasframework.security.service.bo.UnitBo;
import cn.jasgroup.jasframework.security.service.bo.UserBo;
import cn.jasgroup.jasframework.security.support.Constants;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import cn.jasgroup.jasframework.utils.ReadConfigUtil;
import cn.jasgroup.jasframework.utils.StringUtil;

@RestController
@RequestMapping(value="daq/privilege")
public class DaqPrivilegeController extends BaseController{

	@Resource(name="daqPrivilegeService")
	private DaqPrivilegeService daqPrivilegeService;

	@Autowired
	private UnitService unitService;

	@Autowired
	private LoginController loginController;

	@Resource(name="i18nService")
	private I18nService m_I18nService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RedisService redisService;

	@Resource(name="loginLogService")
	private LoginLogService loginLogService;

	@Resource
	private DaqWeldCodeRegularService daqWeldCodeRegularService;

	/**
	 * token过期的时间量（默认5）
	 */
	private Long expireTime = 5L;

	/**
	 * token过期的时间单位（默认小时）  token过期的默认时间是5小时
	 */
	private TimeUnit expireTimeUnit = TimeUnit.HOURS;

	@PostConstruct
	public void init(){
		String expireTimeStr = ReadConfigUtil.getPlatformConfig("redis.token.expireTime");

		String expireTimeUnitStr = ReadConfigUtil.getPlatformConfig("redis.token.expireTimeUnit");

		if(StringUtils.isNotBlank(expireTimeStr)){
			expireTime = Long.parseLong(expireTimeStr);
		}
		if(StringUtils.isNotBlank(expireTimeUnitStr)){
			expireTimeUnit =  TimeUnit.valueOf(expireTimeUnitStr);
		}
	}

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
			List<Map<String,Object>> rows = null;
			String pipelineOid = param.get("pipelineOid");
			if(StringUtils.isNotBlank(pipelineOid)){
				rows = this.daqPrivilegeService.getMedianStakeListByPipelineOid(pipelineOid);
			}else{
				rows = this.daqPrivilegeService.getMedianStakeList(pipeSegmentOrCrossOid);
			}
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
			/*List<Map<String,Object>> rows = new ArrayList<Map<String,Object>>();
			AuthUser currentUser = ThreadLocalHolder.getCurrentUser();
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("key", currentUser.getUnitId());
			data.put("value", currentUser.getUnitName());
			rows.add(data);*/
			List<Map<String,Object>> rows = this.daqPrivilegeService.getCurrentUnitId();
			if(rows!=null){
				result = new ListResult<>(1, "200", "ok", rows);
			}else{
				result = new ListResult<>(-1, "400", "error");
			}
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
	/***
	 * <p>功能描述：APP端登录。</p>
	 * <p> 雷凯。</p>
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年10月11日 下午3:38:53。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/appLogin",method = RequestMethod.POST)
	@ResponseBody
	public Object appLogin(HttpServletRequest request,@RequestBody Map<String,Object> paramMap){
		Object resultData = loginController.login(request, paramMap);
		try {
			Map<String, Object> result = (Map<String, Object>)resultData;
			UserBo userBo = (UserBo)result.get("user");
			String unitOid = userBo.getUnitId();
			PriUnit unitEntity = (PriUnit)unitService.get(PriUnit.class,unitOid);
			if(unitEntity==null){
				result.put("unitType", -1);
				return result;
			}
			String hierarchy = unitEntity.getHierarchy();
			if(hierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())){//施工单位
				result.put("unitType", 1);
			}else if(hierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())){//监理单位
				result.put("unitType", 2);
			}else if(hierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())){//检测单位
				result.put("unitType", 3);
			}else if(hierarchy.startsWith(UnitHierarchyEnum.project_unit.getHierarchy())){//建设单位
				result.put("unitType", 4);
			}else if(hierarchy.startsWith(UnitHierarchyEnum.supplier.getHierarchy())){//厂商
				result.put("unitType", 5);
			}else{
				result.put("unitType", 0);
			}
			result.put("unitCode",unitEntity.getUnitCode());

			// 查询焊口规则列表
			List<Map<String,Object>> daqWeldCodeRegularList =
					daqWeldCodeRegularService.getDaqWeldCodeRegularList("");
			result.put("daqWeldCodeRegularList",daqWeldCodeRegularList);

			String loginName = paramMap.get("loginNum").toString();
			String base64Image = this.daqPrivilegeService.getFaceInfo(loginName);
			if(StringUtils.isNotBlank(base64Image)){
				result.put("isFaceInfo", 1);
			}else{
				result.put("isFaceInfo", 0);
			}
			return result;
		} catch (Exception e) {
			return resultData;
		}
	}
	/***
	 * <p>功能描述：添加人脸信息。</p>
	 * <p> 雷凯。</p>
	 * @param request
	 * @param paramMap
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年10月29日 上午11:28:02。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/addFaceInfo",method = RequestMethod.POST)
	@ResponseBody
	public Object addFaceInfo(HttpServletRequest request,@RequestBody Map<String,String> paramMap){
		BaseResult result = null;
		try {
			String loginName = ThreadLocalHolder.getCurrentUserLoginName();
			String base64Image = paramMap.get("base64Image");
			boolean info = this.daqPrivilegeService.addFaceInfo(loginName, base64Image);
			if(info){
				result = new BaseResult(0, "200", "ok");
			}else{
				result = new BaseResult(-1, "400", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new BaseResult(-1, "400", e.getMessage());
		}
		return result;
	}
	/**
	 * <p>功能描述：获取人脸BASE64图片。</p>
	 * <p> 雷凯。</p>
	 * @param request
	 * @param paramMap
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年10月29日 上午11:30:51。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getFaceInfo",method = RequestMethod.POST)
	@ResponseBody
	public Object getFaceInfo(HttpServletRequest request,@RequestBody Map<String,String> paramMap){
		try {
			String loginName = paramMap.get("loginNum");
			String base64Image = this.daqPrivilegeService.getFaceInfo(loginName);
			paramMap.put("base64Image", base64Image);
			Object resultData = this.faceLogin(request, paramMap);

			try {
				Map<String, Object> result = (Map<String, Object>)resultData;
				result.put("base64Image", base64Image);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				return resultData;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new BaseResult(0, "400", e.getMessage());
		}
	}
	@RequestMapping(value="/faceLogin",method = RequestMethod.POST)
	@ResponseBody
	public Object faceLogin(HttpServletRequest request,@RequestBody Map<String,String> paramMap){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			String loginName = paramMap.get("loginNum");
			String base64Image = paramMap.get("base64Image");

			String i18n = (String) paramMap.get("i18n");// 获取登录语言
			if(!StringUtil.hasText(i18n)){
				i18n = "zh_CN";
			}
			Map<String,Object> userMap = this.daqPrivilegeService.getFaceInfoByLoginName(loginName);
			if(userMap==null){
				return new BaseResult(-1, "PU03012", m_I18nService.getI18NByModuleid(Constants.I18N_USER_PROPERTIES, "user.usernamenotexist", i18n));
			}

			if(!userMap.containsKey("base64Image") || userMap.get("base64Image")==null){
				return new BaseResult(-1,"PU06000","用户未设置人脸识别");
			}
			if(StringUtils.isBlank(base64Image)){
				return new BaseResult(-1,"PU06000","用户未设置人脸识别");
			}
			if(!userMap.get("base64Image").toString().equals(base64Image)){
				return new BaseResult(-1,"PU06001","服务器人脸识别失败");
			}
			Map<String,Object> attributes =new HashMap<String, Object>();
			attributes.put("superadmin", false);  //是否超级管理员
			//1.设置用户角色
			List<RoleBo> userRoleList = roleService.queryPriRole(userMap.get("oid").toString());
			Map<String,Object> userRoleMap = new HashMap<String,Object>();
			userRoleMap.put("roleIds", "");
			userRoleMap.put("roleNames", "");
			for(RoleBo role : userRoleList){
				if(!StringUtil.hasText(userRoleMap.get("roleIds").toString())){
					userRoleMap.put("roleIds",role.getOid());
					userRoleMap.put("roleNames",role.getRoleName());
					continue;
				}
				if((Boolean)attributes.get("superadmin")==false){
					if("superadmin".equals(role.getRoleName())){
						attributes.put("superadmin", true);
					}
				}
				userRoleMap.put("roleIds",userRoleMap.get("roleIds")+","+role.getOid());
				userRoleMap.put("roleNames",userRoleMap.get("roleNames")+","+role.getRoleName());
			}
			userMap.put("roleIds", userRoleMap.get("roleIds"));
			userMap.put("roleNames", userRoleMap.get("roleNames"));
			AuthUser authUser = new AuthUser();
			authUser.setUid(userMap.get("oid").toString());
			authUser.setLoginName(loginName);
			authUser.setUname(userMap.get("userName").toString());
			authUser.setUnitId(userMap.get("unitId").toString());

			//2.设置用户部门
			List<String> userUnitList = getUserDeptInfo(userMap.get("unitId").toString());
			String userUnitInfo = "";
			if (userUnitList.size()>0) {
				for (int i = userUnitList.size()-1; i >0; i--) {
					userUnitInfo += userUnitList.get(i) + "-->";
				}
				authUser.setUnitName(userUnitList.get(0));
			}
			
			userUnitInfo += userUnitList.get(0);
			userMap.put("unitName", userUnitInfo);
			authUser.setUnitNameFullpath(userUnitInfo);

			//3.设置用户其他信息
			String token = UUID.randomUUID().toString();
			attributes.put("token", token);
			attributes.put("password", userMap.get("password").toString());
			attributes.put(Constants.LOGIN_I18N, i18n);  //国际化语言
			attributes.put(Constants.LOGIN_ROLES, userRoleMap); //角色
			authUser.setAttributes(attributes);

			//4.将用户信息存储到redis中
			redisService.putValue(token, authUser);
			redisService.expirse(token, expireTime, expireTimeUnit);

			//5.将线程参数存储到redis中
			Map<String, Object> threadRedisParamMap = new HashMap<String, Object>();
			threadRedisParamMap.put("IP", request.getServerName());
			redisService.putValue(token+"_threadRedisParamMap", threadRedisParamMap);
			redisService.expirse(token+"_threadRedisParamMap", expireTime, expireTimeUnit);

			// 查询焊口规则列表
			List<Map<String,Object>> daqWeldCodeRegularList =
					daqWeldCodeRegularService.getDaqWeldCodeRegularList("");
			result.put("daqWeldCodeRegularList",daqWeldCodeRegularList);
			

			//7.构造返回结果
			userMap.put("password", null);
			userMap.remove("base64Image");
			result.put("status", 1);
			result.put("user", userMap);
			result.put("token", token);
			//6.记录登录日志
			try{
				SysLoginLog loginLog = new SysLoginLog();
				loginLog.setUserId(userMap.get("oid").toString()); // 记录用户ID
				loginLog.setUserName(userMap.get("userName").toString());
				loginLog.setLoginName(loginName);
				loginLog.setUnitId(userMap.get("unitId").toString());
				loginLog.setUnitName(authUser.getUnitName());
				loginLog.setUnitNameFullpath(authUser.getUnitNameFullpath());
				loginLog.setLoginDatetime(new Date()); // 记录登陆时间
				loginLog.setClientIp(request.getRemoteAddr()); // 记录登陆Ip
				loginLog.setServerIp(request.getLocalAddr());
				loginLog.setToken(token);
				loginLog.setAppId((String)attributes.get("login_appid"));
				loginLog.setAppName((String)attributes.get("login_appname"));
				loginLogService.saveLoginLog(loginLog);
			}catch(Exception e){
				log.error("Logging Login Log Error: token--"+token+e.getMessage());
			}
			//7.记录操作日志
			LogTemplateFactory.getOptLogger().log(userMap.get("oid").toString(), "用户", "pri_user", "登录系统", authUser.getUid(), authUser.getUname(), loginName, "",
					new String[]{(String)attributes.get("login_appid"),(String)attributes.get("login_appname")});

			PriUnit unitEntity = (PriUnit)unitService.get(PriUnit.class,userMap.get("unitId").toString());
			if(unitEntity==null){
				result.put("unitType", -1);
				return result;
			}
			result.put("unitCode",unitEntity.getUnitCode());
			String hierarchy = unitEntity.getHierarchy();
			if(hierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())){//施工单位
				result.put("unitType", 1);
			}else if(hierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())){//监理单位
				result.put("unitType", 2);
			}else if(hierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())){//检测单位
				result.put("unitType", 3);
			}else if(hierarchy.startsWith(UnitHierarchyEnum.project_unit.getHierarchy())){//建设单位
				result.put("unitType", 4);
			}else if(hierarchy.startsWith(UnitHierarchyEnum.supplier.getHierarchy())){//厂商
				result.put("unitType", 5);
			}else{
				result.put("unitType", 0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private List<String> getUserDeptInfo(String unitId) {
		List<String> unitList = new ArrayList<String>();
		UnitBo unitBo = unitService.queryById(unitId);
		unitList.add(unitBo.getUnitName());
		String parentId = unitBo.getParentId();
		if (parentId!=null) {
			unitList.addAll(getUserDeptInfo(parentId));
		}
		return unitList;
	}

	/**
	 * <p>功能描述：根据项目oids查询所有施工单位。</p>
	 * <p> 葛建。</p>
	 * @param request
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年12月18日 上午9:46:37。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getConstructUnitList",method = RequestMethod.POST)
	@ResponseBody
	public Object getConstructUnitList(HttpServletRequest request,@RequestBody Map<String, Object> param){
		ListResult<Map<String,Object>> result=null;
		//项目
		List<String> projectOids = (List<String>)param.get("projectOids");
		try {
			List<Map<String,Object>> rows = this.daqPrivilegeService.getConstructUnitList(projectOids);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <p>功能描述：根据项目oid获取监理单位。</p>
	 * <p> 雷凯。</p>
	 * @param request
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2019年1月24日 下午2:46:54。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getSupervisionUnitByProjectOid",method = RequestMethod.POST)
	@ResponseBody
	public Object getSupervisionUnitByProjectOid(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String projectOid = param.get("projectOid");
			List<Map<String,Object>> rows = this.daqPrivilegeService.getSupervisionUnitByProjectOid(projectOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：保存附件与项目的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月18日 下午4:15:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/saveProjectAndFileRef",method = RequestMethod.POST)
	@ResponseBody
	public Object saveProjectAndFileRef(HttpServletRequest request,@RequestBody Map<String,Object> param){
		SimpleResult<String> result = null;
		try {
			String projectOid = param.get("projectOid").toString();
			List<String> docFileOidList = (List<String>)param.get("docFileOidList");
			this.daqPrivilegeService.saveProjectAndFileRef(projectOid, docFileOidList);
			result = new SimpleResult<>(1, "200", "ok");
		} catch (Exception e) {
			result = new SimpleResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * <p>功能描述：删除附件与项目的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param oids
	  * @param isShiftDelFile
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月19日 下午6:02:42。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value = "/deleteProjectAndFileRef",method=RequestMethod.GET)
	@ResponseBody
	public Object deleteProjectAndFileRef(@RequestParam("oids") String oids,
			@RequestParam(value = "isShiftDelFile", defaultValue = "false") String isShiftDelFile){
		SimpleResult<String> result = null;
		try {
			boolean isShiftDeleteAttachementFile = Boolean.valueOf(isShiftDelFile);
			daqPrivilegeService.deleteAttachementById(oids, isShiftDeleteAttachementFile);
			result = new SimpleResult<>(1, "200", "ok");
		} catch (Exception e) {
			result = new SimpleResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
