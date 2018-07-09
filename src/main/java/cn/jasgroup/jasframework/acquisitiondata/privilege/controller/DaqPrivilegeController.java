package cn.jasgroup.jasframework.acquisitiondata.privilege.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.base.controller.BaseController;

@RestController
@RequestMapping(value="daq/privilege")
public class DaqPrivilegeController extends BaseController{
	
	@Resource(name="daqPrivilegeService")
	private DaqPrivilegeService daqPrivilegeService;
	
	/***
	  * <p>功能描述：获取当前用户所在部门下的项目列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。`
	  * <p>创建日期:2018年7月3日 上午11:40:16。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getProjectList")
	public Object getProjectList(HttpServletRequest request){
		ListResult<Map<String,Object>> result = null;
		try {
			List<Map<String,Object>> rows = this.daqPrivilegeService.getProject();
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
			if(StringUtils.isBlank(tendersOid)){
				return new BaseResult(-1, "error", "标段id不能为空！");
			}
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
			if(StringUtils.isBlank(tendersOid)){
				return new BaseResult(-1, "error", "标段id不能为空！");
			}
			List<Map<String,Object>> rows = this.daqPrivilegeService.getConstructionUnitByTendersOid(tendersOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
}