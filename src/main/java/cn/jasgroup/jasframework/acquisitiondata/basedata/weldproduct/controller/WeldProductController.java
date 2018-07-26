package cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.service.WeldProductService;

@RestController
@RequestMapping("/daq/weldProduct")
public class WeldProductController {

	@Autowired
	private WeldProductService weldProductService;
	
	@RequestMapping("getListByCondition")
	public Object getListByCondition(@RequestBody Map<String, String> param){
		String projectOid = param.get("projectOid");
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.weldProductService.getListByCondition(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
