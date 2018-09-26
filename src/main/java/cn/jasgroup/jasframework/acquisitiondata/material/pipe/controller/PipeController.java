package cn.jasgroup.jasframework.acquisitiondata.material.pipe.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.material.pipe.service.PipeService;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;

@RestController
@RequestMapping("daq/materialPipe")
public class PipeController {

	@Autowired
	private PipeService pipeService;
	
	@Resource(name="daqPrivilegeService")
	private DaqPrivilegeService daqPrivilegeService;
	
	/**
	 * <p>功能描述：查询未使用的钢管。</p>
	  * <p> 葛建。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 上午9:57:29。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getCutAndNotUse", method = RequestMethod.POST)
	@ResponseBody
	public Object getNotUseAndHasCut(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String, Object>> result= null;
		try{
			String type = param.get("type");
			if(StringUtils.isBlank(type)){
				type = "1";
			}
			List<Map<String, Object>> rows = this.pipeService.getPipeList(type);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：获取物资离线数据。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月30日 下午3:09:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getOfflineMaterialData", method = RequestMethod.POST)
	@ResponseBody
	public Object getOfflineMaterialData(HttpServletRequest request){
		SimpleResult<Map<String,Object>> result = null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try {
			List<Map<String,Object>> projectList= daqPrivilegeService.getProject("pipe_network_code_001");
			List<String> projectOids=new ArrayList<String>();
			for(Map<String,Object> map:projectList){
				projectOids.add(map.get("key").toString());
			}
			List<Map<String, Object>> coldBendingRows = this.pipeService.getPipeColdBendingList(projectOids);
			dataMap.put("coldBendingData", coldBendingRows);//冷弯管
			List<Map<String,Object>> pipeRows = this.pipeService.getMaterialPipeList(projectOids);
			dataMap.put("materialPipeData", pipeRows);//钢管
			List<Map<String,Object>> hotBendsRows = this.pipeService.getMaterialHotBendsList(projectOids);
			dataMap.put("materialHotBendsData", hotBendsRows);//热煨弯管
			List<Map<String,Object>> teeRows = this.pipeService.getMaterialTeeList(projectOids);
			dataMap.put("materialTeeData", teeRows);//三通
			List<Map<String,Object>> jnsulatedJointRows = this.pipeService.getMaterialJnsulatedJointList(projectOids);
			dataMap.put("materialJnsulatedJointData", jnsulatedJointRows);//绝缘接头
			List<Map<String,Object>> reducerRows = this.pipeService.getMaterialReducerList(projectOids);
			dataMap.put("materialReducerData", reducerRows);//大小头
			List<Map<String,Object>> closureRows = this.pipeService.getMaterialClosureList(projectOids);
			dataMap.put("materialClosureData", closureRows);//封堵物
			List<Map<String,Object>> valveRows = this.pipeService.getValveList(projectOids);
			dataMap.put("materialValveData", valveRows);//封堵物
			result = new SimpleResult<Map<String,Object>>(1, "200", "ok", dataMap);
		} catch (Exception e) {
			result = new SimpleResult<Map<String,Object>>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：获取阀门列表。</p>
	  * <p> 葛建。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月18日 上午11:22:40。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getValveList",method = RequestMethod.POST)
	@ResponseBody
	public Object getValveList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String projectOid = param.get("projectOid");
			List<Map<String,Object>> rows = this.pipeService.getValveList(projectOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
}
