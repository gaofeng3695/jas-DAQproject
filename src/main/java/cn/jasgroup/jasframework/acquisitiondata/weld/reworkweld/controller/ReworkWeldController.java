package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.service.ReworkWeldService;

@RestController
@RequestMapping("daq/reworkWeld")
public class ReworkWeldController {

	@Autowired
	private ReworkWeldService reworkWeldService;
	
	@RequestMapping("getPersonByWorkUnit")
	public Object getPersonByWorkUnit(@RequestBody Map<String, String> param){
		String workUnitOid = param.get("workUnitOid");
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.reworkWeldService.getPersonByWorkUnit(workUnitOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
