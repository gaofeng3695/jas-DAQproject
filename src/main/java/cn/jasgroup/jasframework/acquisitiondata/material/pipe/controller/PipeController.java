package cn.jasgroup.jasframework.acquisitiondata.material.pipe.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.material.pipe.service.PipeService;

@RestController
@RequestMapping("daq/materialPipe")
public class PipeController {

	@Autowired
	private PipeService pipeService;
	
	@RequestMapping("/getCutAndNotUse")
	public Object getNotUseAndHasCut(){
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.pipeService.getPipeList();
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
