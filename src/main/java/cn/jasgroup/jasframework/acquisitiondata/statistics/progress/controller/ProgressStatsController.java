package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.controller;

import java.util.Date;
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
import cn.jasgroup.jasframework.utils.DateTimeUtil;

@RestController
@RequestMapping("daq/progressStats")
public class ProgressStatsController {
	
	@Resource(name="progressStatsService")
	private ProgressStatsService progressStatsService;
	
	
	/**
	 * <p>功能描述：项目-各工序分项目对比统计（km）。
	 * 注意：返回单位为：m，单位转换在前端处理	
	 * </p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月10日 下午4:49:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachItemLengthStatsByProject")
	@ResponseBody
	public Object getEachItemLengthStatsByProject(@RequestBody Map<String, Object> params){
		ListResult<Map<String, Object>> result= null;
		try{
			List<String> projectOids = (List<String>)params.get("projectOids");
			String date = (String)params.get("date");
			if (StringUtils.isBlank(date)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			List<Map<String, Object>> rows = progressStatsService.getEachItemLengthStatsByProject(projectOids,date);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <p>功能描述：项目-各工序分项目对比统计（口数）。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午3:52:32。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachItemCountStatsByProject")
	@ResponseBody
	public Object getEachItemCountStatsByProjectsAndDate(@RequestBody Map<String, Object> params){
		ListResult<Map<String, Object>> result= null;
		try{
			List<String> projectOids = (List<String>)params.get("projectOids");
			String date = (String)params.get("date");
			if (StringUtils.isBlank(date)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			List<Map<String, Object>> rows = progressStatsService.getEachItemCountStatsByProject(projectOids,date);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：标段-工序分标段分月累计完成统计（km）。
	 * 注意：返回单位为：m，单位转换在前端处理
	 * </p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午4:18:02。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getSingleItemCumulateStats")
	@ResponseBody
	public Object getCumulateStatsByProjectAndSingleItem(@RequestBody Map<String, Object> params){
		ListResult<Map<String, Object>> result= null;
		try{
			//项目
			String projectOid = (String)params.get("projectOid");
			//工序
			String procedure = (String)params.get("procedure");
			//起止日期
			String beginMonth = (String)params.get("beginMonth");
			String endMonth = (String)params.get("endMonth");
			Date beginDate = DateTimeUtil.getDateFromDateString(beginMonth, "yyyy-MM");
			Date endDate = DateTimeUtil.getDateFromDateString(endMonth, "yyyy-MM");
			beginMonth = DateTimeUtil.getFormatDate(beginDate, "yyyy-MM");
			endMonth = DateTimeUtil.getFormatDate(endDate, "yyyy-MM");
			if (StringUtils.isBlank(procedure) || StringUtils.isBlank(beginMonth) || StringUtils.isBlank(endMonth)) {
				return new ListResult<>(-1,"400","请选择工序或起止日期");
			}
			List<Map<String, Object>> rows = progressStatsService.getCumulateStatsByProjectAndSingleItem(projectOid, procedure, beginMonth, endMonth);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：标段-各工序分标段累计情况统计（km）。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午4:37:56。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachItemLengthStatsByTenders")
	@ResponseBody
	public Object getEachItemLengthStatsByTendersAndDate(@RequestBody Map<String, Object> params){
		ListResult<Map<String, Object>> result= null;
		try{
			String projectOid = (String)params.get("projectOid");
			String date = (String)params.get("date");
			if (StringUtils.isBlank(date)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			List<Map<String, Object>> rows = progressStatsService.getEachItemLengthStatsByTenders(projectOid,date);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <p>功能描述：标段-各工序分标段累计情况统计（口）。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午5:58:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachItemCountStatsByTenders")
	@ResponseBody
	public Object getEachItemCountStatsByTendersAndDate(@RequestBody Map<String, Object> params){
		ListResult<Map<String, Object>> result= null;
		try{
			String projectOid = (String)params.get("projectOid");
			String date = (String)params.get("date");
			if (StringUtils.isBlank(date)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			List<Map<String, Object>> rows = progressStatsService.getEachItemCountStatsByTendersAndDate(projectOid,date);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
}
