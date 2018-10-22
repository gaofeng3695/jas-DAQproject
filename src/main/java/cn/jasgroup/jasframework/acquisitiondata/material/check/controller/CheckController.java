package cn.jasgroup.jasframework.acquisitiondata.material.check.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.material.check.service.CheckService;
import cn.jasgroup.jasframework.base.controller.BaseController;

@RestController
@RequestMapping("daq/check")
public class CheckController extends BaseController{
	
	@Autowired
	private CheckService checkService;
	
	/**
	 * <p>功能描述：获取绝缘接头的出厂编号。</p>
	  * <p> 葛建。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年10月17日 下午3:28:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getManufacturerCode",method = RequestMethod.POST)
	@ResponseBody
	public Object getAllWeldList(HttpServletRequest request, @RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String projectOid = param.get("projectOid");
		try{
			List<Map<String, Object>> rows = this.checkService.getManufacturerCode(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
}
