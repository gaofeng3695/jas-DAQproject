package cn.jasgroup.jasframework.acquisitiondata.statistics.material.controller;

import java.util.List;
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
	
	
	/***
	  * <p>功能描述：物资按年分月统计。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 上午10:29:51。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getMonthlyStatistics",method = RequestMethod.POST)
	@ResponseBody
	public Object getMonthlyStatistics(HttpServletRequest request,@RequestBody Map<String,Object> param){
		SimpleResult<Map<String,Object>> result = null;
		String year = param.get("year")!=null?param.get("year").toString():null;
		List<Object> projectOid=(List<Object>)param.get("projectOid");
		try {
			if(StringUtils.isNotBlank(year) && projectOid!=null && projectOid.size()>0){
				Map<String,Object> data = this.materialStatisticsService.getMonthlyStatistics(projectOid,year);
				result = new SimpleResult<Map<String,Object>>(0, "200","ok", data);
			}else{
				result = new SimpleResult<>(0, "400", "param can not is null");
			}
		} catch (Exception e) {
			result = new SimpleResult<>(0, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午2:47:42。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getDailyStatistics",method = RequestMethod.POST)
	@ResponseBody
	public Object getDailyStatistics(HttpServletRequest request,@RequestBody Map<String,Object> param){
		SimpleResult<Map<String,Object>> result = null;
		String month = param.get("month")!=null?param.get("month").toString():null;
		List<Object> projectOid=(List<Object>)param.get("projectOid");
		try {
			if(StringUtils.isNotBlank(month) && projectOid!=null && projectOid.size()>0){
				Map<String,Object> data = this.materialStatisticsService.getDailyStatistics(projectOid,month);
				result = new SimpleResult<Map<String,Object>>(0, "200","ok", data);
			}else{
				result = new SimpleResult<>(0, "400", "param can not is null");
			}
		} catch (Exception e) {
			result = new SimpleResult<>(0, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：按标段统计。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午4:15:40。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getTendersStatistics",method = RequestMethod.POST)
	@ResponseBody
	public Object getTendersStatistics(HttpServletRequest request,@RequestBody Map<String,String> param){
		SimpleResult<Map<String,Object>> result = null;
		String dateTime = param.get("dateTime");
		String projectOid=param.get("projectOid");
		try {
			if(StringUtils.isNotBlank(dateTime) && projectOid!=null){
				Map<String,Object> data = this.materialStatisticsService.getTendersStatistics(projectOid,dateTime);
				result = new SimpleResult<Map<String,Object>>(0, "200","ok", data);
			}else{
				result = new SimpleResult<>(0, "400", "param can not is null");
			}
		} catch (Exception e) {
			result = new SimpleResult<>(0, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/***
	  * <p>功能描述：物资使用情况统计。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午4:34:39。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getMaterialUseStatustics",method = RequestMethod.POST)
	@ResponseBody
	public Object getMaterialUseStatustics(HttpServletRequest request,@RequestBody Map<String,String> param){
		SimpleResult<List<Map<String,Object>>> result = null;
		String month = param.get("month");
		String projectOid=param.get("projectOid");
		try {
			if(StringUtils.isNotBlank(month) && projectOid!=null){
				List<Map<String,Object>> data = this.materialStatisticsService.getMaterialUseStatustics(projectOid,month);
				result = new SimpleResult<List<Map<String,Object>>>(0, "200","ok", data);
			}else{
				result = new SimpleResult<>(0, "400", "param can not is null");
			}
		} catch (Exception e) {
			result = new SimpleResult<>(0, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
