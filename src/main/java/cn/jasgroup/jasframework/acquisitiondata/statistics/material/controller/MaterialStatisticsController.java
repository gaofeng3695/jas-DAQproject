package cn.jasgroup.jasframework.acquisitiondata.statistics.material.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.statistics.material.service.MaterialStatisticsService;
import cn.jasgroup.jasframework.base.controller.BaseController;

@RestController
@RequestMapping("daq/materialStatistics")
public class MaterialStatisticsController extends BaseController{
	
	@Resource(name="materialStatisticsService")
	private MaterialStatisticsService materialStatisticsService;
	
	
	
	@RequestMapping(value="/getMonthlyStatistics",method = RequestMethod.POST)
	@ResponseBody
	public Object getMonthlyStatistics(HttpServletRequest request,@RequestBody Map<String,String> param){
		SimpleResult<Map<String,Object>> result = null;
		String year = param.get("year");
		try {
			if(StringUtils.isNotBlank(year)){
				Map<String,Object> data = this.materialStatisticsService.getMonthlyStatistics(year);
				result = new SimpleResult<Map<String,Object>>(0, "200","ok", data);
			}else{
				result = new SimpleResult<>(0, "400", "year can not is null");
			}
		} catch (Exception e) {
			result = new SimpleResult<>(0, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
