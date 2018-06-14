package cn.jasgroup.jasframework.acquisitiondata.scope.pipeline.controller;

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

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.scope.pipeline.service.PipelineService;
import cn.jasgroup.jasframework.base.controller.BaseController;

/***
  * 管线管理controller
  *<p>类描述：。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年6月12日 下午3:22:32。</p>
 */
@RestController
@RequestMapping("daq/pipeline")
public class PipelineController extends BaseController{
	
	@Resource(name="pipelineService")
	private PipelineService pipelineService;
	
	/***
	  * <p>功能描述：根据项目oid获取项目下的管线。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月12日 下午5:19:15。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getList",method=RequestMethod.POST)
	@ResponseBody
	public Object getPipeLineList(HttpServletRequest request, @RequestBody Map<String,String> param){
		ListResult<Map<String, Object>> result = null;
		String projectOid = param.get("projectOid");
		if(StringUtils.isNotBlank(projectOid)){
			List<Map<String,Object>> rows = pipelineService.getPipeLineList(projectOid);
			result = new ListResult<>(1, "200", "ok",rows);
		}else{
			result = new ListResult<>(-1, "400", "projectOid is null");
			throw new BusinessException("error","projectOid not is null");
		}
		return result;
	}
}
