package cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.controller;

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
	@PostMapping("getMonthLyGrothAndTotal")
	@ResponseBody
	public Object getMonthLyGrothAndTotal(@RequestBody Map<String, Object> params){
		SimpleResult<Map<String, Object>> result= null;
		try{
			//项目
			List<String> projectOids = (List<String>)params.get("projectOids");
			//月份
			String year = (String)params.get("year");
			if (StringUtils.isBlank(year)) {
				return new ListResult<>(-1,"400","请选择年份");
			}
			//根据项目、日期查询每个标段的检测口数和一次合格率
			Map<String, Object> rows = mesolowStatsService.getMonthLyGrowthAndTotal(projectOids, year);
			result = new SimpleResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
