package cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.service.WorkPersonService;

@RestController
@RequestMapping("daq/workPerson")
public class WorkPersonController {

	@Autowired
	private WorkPersonService workPersonService;
	
	@RequestMapping("getWorkPersonList")
	public Object getWorkPersonList(@RequestBody  Map<String, String> param){
		String projectOid = param.get("projectOid");
		String types = param.get("types");
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.workPersonService.getListByCondition(projectOid, types);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
