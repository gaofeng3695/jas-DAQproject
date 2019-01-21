package cn.jasgroup.jasframework.acquisitiondata.material.pipe.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.material.pipe.service.PipeService;
import cn.jasgroup.jasframework.acquisitiondata.privilege.service.DaqPrivilegeService;
import cn.jasgroup.jasframework.base.service.RedisService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@RestController
@RequestMapping("daq/materialPipe")
public class PipeController {

	@Autowired
	private PipeService pipeService;
	
	@Resource(name="daqPrivilegeService")
	private DaqPrivilegeService daqPrivilegeService;
	
	@Autowired
	private RedisService redisService;
	
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
			String projectOid = param.get("projectOid");
			if(StringUtils.isBlank(type)){
				type = "1";
			}
			List<Map<String, Object>> rows = this.pipeService.getPipeList(type,projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/**
	  * <p>功能描述：获取钢管编号。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月15日 下午7:06:56。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getPipeList", method = RequestMethod.POST)
	@ResponseBody
	public Object getPipeList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String, Object>> result= null;
		try{
			String projectOid = param.get("projectOid");
			String type = param.get("type")==null?request.getParameter("type"):param.get("type");
			if(StringUtils.isBlank(type)){
				type = "1";
			}
			List<Map<String,Object>> pipeRows = this.pipeService.getMaterialPipeList(projectOid,type);
			result = new ListResult<>(1,"200","ok",pipeRows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：热煨弯管。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月15日 下午7:07:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getHotBendsList", method = RequestMethod.POST)
	@ResponseBody
	public Object getHotBendsList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String, Object>> result= null;
		try{
			String projectOid = param.get("projectOid");
			String type = param.get("type")==null?request.getParameter("type"):param.get("type");
			if(StringUtils.isBlank(type)){
				type = "1";
			}
			List<Map<String,Object>> hotBendsRows = this.pipeService.getMaterialHotBendsList(projectOid,type);
			result = new ListResult<>(1,"200","ok",hotBendsRows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	 * <p>功能描述：三通。</p>
	 * <p> 雷凯。</p>	
	 * @param request
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年9月15日 下午7:07:47。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getTeeList", method = RequestMethod.POST)
	@ResponseBody
	public Object getTeeList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String, Object>> result= null;
		try{
			String projectOid = param.get("projectOid");
			List<Map<String,Object>> teeRows = this.pipeService.getMaterialTeeList(projectOid);
			result = new ListResult<>(1,"200","ok",teeRows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：绝缘接头。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月15日 下午7:09:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getJnsulatedJointList", method = RequestMethod.POST)
	@ResponseBody
	public Object getJnsulatedJointList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String, Object>> result= null;
		try{
			String projectOid = param.get("projectOid");
			List<Map<String,Object>> jnsulatedJointRows = this.pipeService.getMaterialJnsulatedJointList(projectOid);
			result = new ListResult<>(1,"200","ok",jnsulatedJointRows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：大小头。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月15日 下午7:10:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getReducerList", method = RequestMethod.POST)
	@ResponseBody
	public Object getReducerList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String, Object>> result= null;
		try{
			String projectOid = param.get("projectOid");
			List<Map<String,Object>> reducerRows = this.pipeService.getMaterialReducerList(projectOid);
			result = new ListResult<>(1,"200","ok",reducerRows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：封堵物。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月15日 下午7:10:36。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getClosureList", method = RequestMethod.POST)
	@ResponseBody
	public Object getClosureList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String, Object>> result= null;
		try{
			String projectOid = param.get("projectOid");
			List<Map<String,Object>> closureRows = this.pipeService.getMaterialClosureList(projectOid);
			result = new ListResult<>(1,"200","ok",closureRows);
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
			result = new SimpleResult<Map<String,Object>>(1, "200", "ok", dataMap);
		} catch (Exception e) {
			result = new SimpleResult<Map<String,Object>>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
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
	
	/***
	  * <p>功能描述：二维码生成及下载。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param response
	  * @param oids
	  * @since JDK1.8。
	  * <p>创建日期:2018年11月22日 下午3:35:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/produceScanner",method=RequestMethod.POST)
	@ResponseBody
	public Object produceScanner(HttpServletRequest request,@RequestBody Map<String,Object> params){
		SimpleResult<String> result;
		try {
			List<String> oids = (List<String>) params.get("oids");
			String functionCode = params.get("functionCode").toString();
			String fileOid = this.pipeService.produceScanner(request,functionCode,oids);
			if(fileOid!=null){
				result = new SimpleResult<String>(1, "200", "ok", fileOid);
			}else{
				result = new SimpleResult<String>(-1, "400", "error");
			}
		} catch (Exception e) {
			result = new SimpleResult<String>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	@RequestMapping(value="/downloadScanner")
	@ResponseBody
	public void downloadScanner(HttpServletResponse response,@RequestParam("fileOid") String fileOid) {
		String filePath=null;
		try {
			Object obj = redisService.getValue(fileOid);
			filePath = obj != null ? obj.toString() : "";
			if(StringUtils.isNotBlank(filePath)){
				InputStream inputstream = new FileInputStream(new File(filePath));
				String fileNameMid = "";
				fileNameMid = URLEncoder.encode(new File(filePath).getName(), "utf-8");
				response.setHeader("Content-Disposition", "attachment;filename=" + fileNameMid);
				OutputStream utputStream = response.getOutputStream();
				byte[] filedata = new byte[1024];
				int byteread = 0;
				while ((byteread = inputstream.read(filedata)) != -1) {
					utputStream.write(filedata, 0, byteread);
				}
				inputstream.close();
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(StringUtils.isNotBlank(filePath)){
				redisService.removeKey(fileOid);
				deleteDir(new File(filePath).getParentFile());
			}
		}
	}

	private static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list(); // 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}
}
