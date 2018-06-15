package cn.jasgroup.jasframework.acquisitiondata.scope.area.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.scope.area.serivce.AreaService;
import cn.jasgroup.jasframework.base.controller.BaseController;

/***
  * 
  *<p>类描述：。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年6月13日 下午3:43:34。</p>
 */
@RestController
@RequestMapping("daq/area")
public class AreaController extends BaseController{
	
	@Resource(name="areaService")
	private AreaService areaService;
	
	/**
	  * <p>功能描述：获取省。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午3:43:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getProvinceList", method=RequestMethod.POST)
	@ResponseBody
	public Object getProvinceList(HttpServletRequest request){
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.areaService.getProvinceList();
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/**
	  * <p>功能描述：根据省获取市。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param province
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午3:43:52。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getCityList",method = RequestMethod.POST)
	@ResponseBody
	public Object getCityList(HttpServletRequest request,@RequestBody Map<String,String>param){
		ListResult<Map<String, Object>> result= null;
		String province = param.get("province");
		if(StringUtils.isNotBlank(province)){
			try{
				List<Map<String, Object>> rows = this.areaService.getCityList(province);
				result = new ListResult<>(1,"200","ok",rows);
			}catch(Exception e){
				result = new ListResult<>(-1,"400","error");
				e.printStackTrace();
			}
		}else{
			result = new ListResult<>(-1,"400","error");
			throw new BusinessException("error","province not is null");
		}
		return result;
	}
	/***
	  * <p>功能描述：根据市获取区/县。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param county
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午3:43:57。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getCountyList",method = RequestMethod.POST)
	@ResponseBody
	public Object getCountyList(HttpServletRequest request,@RequestBody Map<String,String>param){
		ListResult<Map<String, Object>> result= null;
		String city = param.get("city");
		if(StringUtils.isNotBlank(city)){
			try{
				List<Map<String, Object>> rows = this.areaService.getCountyList(city);
				result = new ListResult<>(1,"200","ok",rows);
			}catch(Exception e){
				result = new ListResult<>(-1,"400","error");
				e.printStackTrace();
			}
		}else{
			result = new ListResult<>(-1,"400","error");
			throw new BusinessException("error","city not is null");
		}
		return result;
	}
}
