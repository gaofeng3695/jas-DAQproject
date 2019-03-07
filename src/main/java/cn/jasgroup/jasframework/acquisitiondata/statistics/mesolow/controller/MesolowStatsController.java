package cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.service.MesolowStatsService;
import cn.jasgroup.jasframework.utils.DateTimeUtil;

/**
 * 
  *<p>类描述：中低压统计controller。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年3月6日 下午5:17:44。</p>
 */
@RestController
@RequestMapping("daq/mesolow")
public class MesolowStatsController {

	@Autowired
	private MesolowStatsService mesolowStatsService;
	
	/**
	 * <p>功能描述：项目-月新增管段长度统计。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月6日 下午5:17:28。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getMonthlyGrothAndTotal")
	@ResponseBody
	public Object getMonthlyGrothAndTotal(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			List<String> projectOids = (List<String>)params.get("projectOids");
			//月份
			String year = (String)params.get("year");
			if (StringUtils.isBlank(year)) {
				return new ListResult<>(-1,"400","请选择年份");
			}
			//根据项目和时间查询当月各施工单位月新增。
			Map<String, Object> rows = mesolowStatsService.getMonthlyGrothAndTotal(projectOids, year);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：施工单位-月新增管道长度(单月)。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月7日 上午10:31:18。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getSingleMonthlyGroth")
	@ResponseBody
	public Object getSingleMonthlyGroth(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			String projectOid = (String) params.get("projectOid");
			//月份
			String month = (String)params.get("date");
			if (StringUtils.isBlank(month)) {
				return new ListResult<>(-1,"400","请选择年月");
			}
			Date date = DateTimeUtil.getDateFromDateString(month, "yyyy-MM");
			month = DateTimeUtil.getFormatDate(date, "yyyy-MM");
			//根据项目和时间查询当月各施工单位月新增。
			Map<String, Object> rows = mesolowStatsService.getSingleMonthlyGroth(projectOid, month);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：施工单位-月新增管道长度(全年)。</p>
	  * <p> 葛建。</p>	
	  * @param params
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月7日 上午10:31:18。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("getMonthlyGrowthAllYear")
	@ResponseBody
	public Object getMonthlyGrowthAllYear(@RequestBody Map<String, Object> params){
		ListResult<Map<String, Object>> result= null;
		try{
			//项目
			String projectOid = (String) params.get("projectOid");
			//月份
			String year = (String)params.get("date");
			if (StringUtils.isBlank(year)) {
				return new ListResult<>(-1,"400","请选择年月");
			}
			Date date = DateTimeUtil.getDateFromDateString(year, "yyyy");
			year = DateTimeUtil.getFormatDate(date, "yyyy");
			//施工单位-月新增管道长度(全年)。
			List<Map<String, Object>> rows = mesolowStatsService.getMonthlyGrowthAllYear(projectOid, year);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
