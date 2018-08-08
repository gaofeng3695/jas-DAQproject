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
	
	@RequestMapping(value="getWeldList",method = RequestMethod.POST)
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
	@RequestMapping(value="getWeldListByWeldOid",method = RequestMethod.POST)
	@ResponseBody
	public Object getWeldListByWeldOid(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String weldOid=param.get("weldOid");
		String token = request.getParameter("token");
		try {
			List<Map<String,Object>> rows= (List<Map<String, Object>>) redisService.getValue(token+"_get_weld_list_local");
			if(rows.size()>0){
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
	
}
