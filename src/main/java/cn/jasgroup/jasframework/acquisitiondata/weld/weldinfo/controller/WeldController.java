package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService;
import cn.jasgroup.jasframework.base.controller.BaseController;
import cn.jasgroup.jasframework.base.service.RedisService;

@RestController
@RequestMapping("daq/weld")
public class WeldController extends BaseController{
	
	@Resource(name="weldService")
	private WeldService weldService;
	
	@Autowired
	private RedisService redisService;
	
	/***
	  * <p>功能描述：获取焊口列表（焊口+返修-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:14:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getWeldList",method = RequestMethod.POST)
	@ResponseBody
	public Object getWeldList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String pipeSegmentOrCrossOid=param.get("pipeSegmentOrCrossOid");
		String token = request.getParameter("token");
		try {
			List<Map<String,Object>> rows = this.weldService.getWeldList(pipeSegmentOrCrossOid);
			if(rows.size()>0){
				redisService.putValue(token+"_get_weld_list_local", rows);
				redisService.expirse(token, 5, TimeUnit.HOURS);
			}
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	 * <p>功能描述：获取焊口列表（焊口-返修-割口）。</p>
	 * <p> 雷凯。</p>	
	 * @param request
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年8月21日 下午2:14:05。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getOnlyWeldList",method = RequestMethod.POST)
	@ResponseBody
	public Object getOnlyWeldList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String pipeSegmentOrCrossOid=param.get("pipeSegmentOrCrossOid");
		try {
			List<Map<String,Object>> rows = this.weldService.getOnlyWeldList(pipeSegmentOrCrossOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	
	/***
	 * <p>功能描述：获取焊口列表（焊口-返修-割口）。</p>
	 * <p> 雷凯。</p>	
	 * @param request
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年8月21日 下午2:14:05。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getAllWeldList",method = RequestMethod.POST)
	@ResponseBody
	public Object getAllWeldList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String pipeSegmentOrCrossOid=param.get("pipeSegmentOrCrossOid");
		String detectionType=param.get("detectionType");
		try {
			List<Map<String,Object>> rows = this.weldService.getAllWeldList(pipeSegmentOrCrossOid,detectionType);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:16:22。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getWeldListByWeldOid",method = RequestMethod.POST)
	@ResponseBody
	public Object getWeldListByWeldOid(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String weldOid=param.get("weldOid");
		String token = request.getParameter("token");
		try {
			List<Map<String,Object>> rows= (List<Map<String, Object>>) redisService.getValue(token+"_get_weld_list_local");
			if(rows!=null && rows.size()>0){
				if(StringUtils.isNotBlank(weldOid)){
					redisService.putValue(token+"_weld_oid_local", weldOid);
					redisService.expirse(token, 5, TimeUnit.HOURS);
					for(Map map : rows){
						if(map.get("key").equals(weldOid)){
							rows.remove(map);
							break;
						}
					}
					result = new ListResult<>(1, "200", "ok", rows);
				}else{
					weldOid = (String) redisService.getValue(token+"_weld_oid_local");
					String frontWeldOid = param.get("frontWeldOid");
					List<Map<String,Object>> rowsTemp = new ArrayList<Map<String,Object>>();
					rowsTemp.addAll(rows);
					for(Map map : rowsTemp){
						if(map.get("key").equals(weldOid)){
							rows.remove(map);
						}else if(map.get("key").equals(frontWeldOid)){
							rows.remove(map);
						}
					}
				}
			}
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value="/getDetectionInfoByWeldOid",method = RequestMethod.POST)
	@ResponseBody
	public Object getDetectionInfoByWeldOid(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result;
		try {
			String weldOid = param.get("weldOid");
			List<Map<String,Object>> list = this.weldService.getDetectionInfoByWeldOid(weldOid);
			result = new ListResult<>(1, "200", "ok", list);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "ok");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述： 中线测量成果获取焊口，根据线路段查询审核状态为1和2的焊口列表(焊口表中未返修未割口且未测量的数据，返修表中未割口且未测量的数据)。</p>
	  * <p> 葛建。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 上午10:14:00。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getWeldByCondition",method = RequestMethod.POST)
	@ResponseBody
	public Object getWeldByCondition(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String pipeSegmentOrCrossOid=param.get("pipeSegmentOrCrossOid");
		String token = request.getParameter("token");
		try {
			List<Map<String,Object>> rows = this.weldService.getWeldByCondition(pipeSegmentOrCrossOid);
//			if(rows.size()>0){
//				redisService.putValue(token+"_get_weld_list_local", rows);
//				redisService.expirse(token, 5, TimeUnit.HOURS);
//			}
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
