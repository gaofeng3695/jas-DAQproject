package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService;
import cn.jasgroup.jasframework.base.controller.BaseController;

@RestController
@RequestMapping("daq/weld")
public class WeldController extends BaseController{
	
	@Resource(name="weldService")
	private WeldService weldService;
	
	@RequestMapping(value="getWeldList",method = RequestMethod.POST)
	@ResponseBody
	public Object getWeldList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String pipeSegmentOrCrossOid=param.get("pipeSegmentOrCrossOid");
		try {
			List<Map<String,Object>> rows = this.weldService.getWeldList(pipeSegmentOrCrossOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	
}
