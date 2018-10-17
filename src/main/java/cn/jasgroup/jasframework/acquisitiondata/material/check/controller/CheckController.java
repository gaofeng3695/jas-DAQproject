package cn.jasgroup.jasframework.acquisitiondata.material.check.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	@RequestMapping(value="getManufacturerCode",method = RequestMethod.POST)
	@ResponseBody
	public Object getAllWeldList(HttpServletRequest request){
		ListResult<Map<String,Object>> result=null;
		try{
			List<Map<String, Object>> rows = this.checkService.getManufacturerCode();
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
}
