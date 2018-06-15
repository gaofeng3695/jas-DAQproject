package cn.jasgroup.jasframework.acquisitiondata.scope.project.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.scope.project.service.ProjectService;
import cn.jasgroup.jasframework.base.controller.BaseController;

/***
 * 
  *<p>类描述：。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年6月13日 下午4:23:56。</p>
 */
@RestController
@RequestMapping("daq/project")
public class ProjectController extends BaseController{

	@Resource(name="projectService")
	private ProjectService projectService;
	
	/***
	 * <p>功能描述：获取项目列表，下拉选使用。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午4:24:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getList", method = RequestMethod.POST)
	@ResponseBody
	public Object getProjectList(HttpServletRequest request){
		ListResult<Map<String, Object>> result = null;
		try {
			List<Map<String, Object>> rows = this.projectService.getProjectList();
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
}
