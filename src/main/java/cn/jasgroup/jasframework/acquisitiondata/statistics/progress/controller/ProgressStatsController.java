package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsQueryBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.service.ProgressStatsService;

@RestController
@RequestMapping("daq/progressStats")
public class ProgressStatsController {
	
	@Resource(name="progressStatsService")
	private ProgressStatsService progressStatsService;
	
	
	/**
	 * <p>功能描述：项目-各工序分项目对比统计（km）。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月10日 下午4:49:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachItemLengthStats")
	@ResponseBody
	public Object getMaterialStatsByProjectsAndYear(@RequestBody Map<String, Object> params){
		ListResult<Map<String, Object>> result= null;
		try{
			List<String> projectOids = (List<String>)params.get("projectOids");
			String date = (String)params.get("date");
			if (projectOids.size() == 0 || StringUtils.isBlank(date)) {
				result = new ListResult<>(-1,"400","请选择对应的项目或日期");
			}
			List<Map<String, Object>> rows = progressStatsService.getEachItemLengthStats(projectOids,date);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
