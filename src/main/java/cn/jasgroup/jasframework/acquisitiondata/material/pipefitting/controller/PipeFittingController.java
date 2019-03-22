package cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.controller;

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

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.material.pipefitting.service.PipeFittingService;
import cn.jasgroup.jasframework.base.controller.BaseController;

@RestController
@RequestMapping("daq/pipefitting")
public class PipeFittingController extends BaseController{
	
	@Resource(name="pipeFittingService")
	private PipeFittingService pipeFittingService;
	
	/**
	  * <p>功能描述：根据管件类型获取相应的管件列表。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月13日 上午11:40:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getPipeFittingList", method = RequestMethod.POST)
	@ResponseBody
	public Object getPipeFittingList(HttpServletRequest request, @RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String pipeSegmentOrCrossOid = param.get("pipeSegmentOrCrossOid");
			String projectOid = param.get("projectOid");
			String pipeTypeCode = param.get("pipeTypeCode");
			if(StringUtils.isBlank(pipeTypeCode)){
				return new ListResult<>(-1, "400", "pipeTypeCode is not null!"); 
			}
			List<Map<String, Object>> rows = this.pipeFittingService.getPipeFittingList(projectOid,pipeSegmentOrCrossOid,pipeTypeCode);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询未使用且未进行中线测量的弯管列表。</p>
	  * <p> 葛建。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午3:19:36。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getBendingList", method = RequestMethod.POST)
	@ResponseBody
	public Object getBendingList(HttpServletRequest request, @RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String projectOid = param.get("projectOid");
			List<Map<String, Object>> rows = this.pipeFittingService.getBendingList(projectOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
}
