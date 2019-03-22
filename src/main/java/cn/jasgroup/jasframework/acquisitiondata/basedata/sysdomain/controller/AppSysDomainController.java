package cn.jasgroup.jasframework.acquisitiondata.basedata.sysdomain.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.basedata.sysdomain.service.AppSysDomainService;

/**
  *<p>类描述：域值查询controller。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月11日 下午2:46:29。</p>
 */
@Controller
@RequestMapping("daq/sysDomain")
public class AppSysDomainController {

	@Autowired
	private AppSysDomainService sysdomainService;
	
	@RequestMapping("/getOfflineSysdomainData")
	@ResponseBody
	public Object getOfflineSysdomainData(HttpServletRequest reques){
		ListResult<Map<String, Object>> result= null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		String domainNames = "measure_control_point_type_domain,welding_method_temp_domain,weld_type_domain,pipe_type_domain,pipe_type_domain,buckle_anticorrosive_type_domain,anticorrosive_grade_domain,derusting_grade_domain,back_pipe_type_domain";
		try{
			List<Map<String, Object>> sysDomainData = this.sysdomainService.getListData(domainNames);
			result = new ListResult<>(1,"200","ok",sysDomainData);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
