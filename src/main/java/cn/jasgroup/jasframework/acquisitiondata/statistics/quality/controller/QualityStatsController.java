package cn.jasgroup.jasframework.acquisitiondata.statistics.quality.controller;

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
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.statistics.quality.service.QualityStatsService;

@RestController
@RequestMapping("daq/qualityStats")
public class QualityStatsController {

	@Resource(name = "qualityStatsService")
	private QualityStatsService qualityStatsService;
	
	/**
	 * <p>功能描述：项目/单位分月合格率对比。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月13日 下午2:52:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMonthlyQualiifiedRate")
	@ResponseBody
	public Object getMonthlyQualiifiedRateByProjectAndUnit(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			List<String> projectOids = (List<String>)params.get("projectOids");
			//单位
			List<String> unitOids = (List<String>)params.get("unitOids");
			//年份
			String year = (String)params.get("year");
			if (StringUtils.isBlank(year)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			Map<String, Object> rows = qualityStatsService.getMonthlyQualiifiedRateByProjectAndUnit(projectOids, unitOids, year);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：项目缺陷性质分类统计-根据单位分组的count。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午1:47:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getKindsOfDefectCountByProjects")
	@ResponseBody
	public Object getKindsOfDefectCountByProjects(@RequestBody Map<String, Object> params){
		ListResult<Map<String, Object>> result= null;
		try{
			//项目
			List<String> projectOids = (List<String>)params.get("projectOids");
			//单位
			List<String> unitOids = (List<String>)params.get("unitOids");
			//月份
			String month = (String)params.get("month");
			if (StringUtils.isBlank(month)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			List<Map<String, Object>> rows = qualityStatsService.getKindsOfDefectCountByProjects(projectOids, unitOids, month);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：项目缺陷性质分类统计-占比。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午4:22:49。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getKindsOfDefectRateByProjects")
	@ResponseBody
	public Object getKindsOfDefectRateByProjects(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			List<String> projectOids = (List<String>)params.get("projectOids");
			//单位
			List<String> unitOids = (List<String>)params.get("unitOids");
			//月份
			String month = (String)params.get("month");
			if (StringUtils.isBlank(month)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			Map<String, Object> rows = qualityStatsService.getKindsOfDefectRateByProjects(projectOids, unitOids, month);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：项目各标段焊接一次合格率对比。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 上午9:56:36。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachTendersQualifiedRateByProjects")
	@ResponseBody
	public Object getEachTendersQualifiedRateByProjects(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			String projectOid = (String)params.get("projectOid");
			//月份
			String date = (String)params.get("date");
			if (StringUtils.isBlank(date)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			//根据项目、日期查询每个标段的检测口数和一次合格率
			Map<String, Object> rows = qualityStatsService.getEachTendersQualifiedRateByProjects(projectOid, date);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：项目各标段焊接不合格口数对比。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 上午11:28:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachTendersUnQualifiedRateByProjects")
	@ResponseBody
	public Object getEachTendersUnQualifiedRateByProjects(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			String projectOid = (String)params.get("projectOid");
			//月份
			String date = (String)params.get("date");
			if (StringUtils.isBlank(date)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			//根据项目、日期查询每个标段的检测口数和一次合格率
			Map<String, Object> rows = qualityStatsService.getEachTendersUnQualifiedRateByProjects(projectOid, date);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：项目各单位焊接一次合格率对比。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午1:40:23。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachUnitQualifiedRateByProjects")
	@ResponseBody
	public Object getEachUnitQualifiedRateByProjects(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			String projectOid = (String)params.get("projectOid");
			//月份
			String date = (String)params.get("date");
			if (StringUtils.isBlank(date)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			//根据项目、日期查询每个单位的检测口数和一次合格率
			Map<String, Object> rows = qualityStatsService.getEachUnitQualifiedRateByProjects(projectOid, date);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：项目各单位焊接不合格口数对比。</p>
	 * <p> 葛建。</p>	
	 * @param params
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年12月17日 上午11:28:13。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getEachUnitUnQualifiedRateByProjects")
	@ResponseBody
	public Object getEachUnitUnQualifiedRateByProjects(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			String projectOid = (String)params.get("projectOid");
			//月份
			String date = (String)params.get("date");
			if (StringUtils.isBlank(date)) {
				return new ListResult<>(-1,"400","请选择日期");
			}
			//根据项目、日期查询每个单位的检测口数和一次合格率
			Map<String, Object> rows = qualityStatsService.getEachUnitUnQualifiedRateByProjects(projectOid, date);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
