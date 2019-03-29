package cn.jasgroup.jasframework.app.getlist.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.PageResult;
import cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo.CheckPipeColdBendingBo;
import cn.jasgroup.jasframework.app.getlist.service.AppGetDataService;
import cn.jasgroup.jasframework.dataaccess.data.Page;

@RestController
@RequestMapping("appdata")
public class AppGetDataController {
	
	@Autowired
	private AppGetDataService appGetDataService;
	
	/**
	 * <p>功能描述：APP端防腐管检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月28日 下午4:50:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("coatingPipeCheck")
	public Object getCoatingPipeList(@RequestBody Map<String, Object> param){
		ListResult<Map<String, Object>> result= null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try{
			int page = Integer.parseInt(param.get("page").toString());
			int rows = Integer.parseInt(param.get("rows").toString());
			String projectOid = param.get("projectOid").toString();
			
			Page<Object> pageObj = 
					(Page<Object>) appGetDataService.getCoatingPipeListByPage(page,rows,projectOid);
			PageResult pageResult = new PageResult(pageObj.getResult(), (long)pageObj.getTotalLength());
			return pageResult;
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <p>功能描述：APP端热煨弯管检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月28日 下午4:54:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("hotBendsCheck")
	public Object getHotBendsList(@RequestBody Map<String, Object> param){
		ListResult<Map<String, Object>> result= null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try{
			int page = Integer.parseInt(param.get("page").toString());
			int rows = Integer.parseInt(param.get("rows").toString());
			String projectOid = param.get("projectOid").toString();
			
			Page<Object> pageObj = 
					(Page<Object>) appGetDataService.getHotBendsListByPage(page,rows,projectOid);
			PageResult pageResult = new PageResult(pageObj.getResult(), (long)pageObj.getTotalLength());
			return pageResult;
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：APP端冷弯管检查分页查询。</p>
	 * <p> 葛建。</p>	
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2019年3月28日 下午4:54:04。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("coldBendCheck")
	public Object getColdBendsList(@RequestBody Map<String, Object> param){
		ListResult<Map<String, Object>> result= null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try{
			int page = Integer.parseInt(param.get("pageNo").toString());
			int rows = Integer.parseInt(param.get("pageSize").toString());
			String projectOid = param.get("projectOid").toString();
			
			Page<Object> pageObj = 
					(Page<Object>) appGetDataService.getColdBendsListByPage(page, rows, projectOid);
			PageResult pageResult = new PageResult(pageObj.getResult(), (long)pageObj.getTotalLength());
			return pageResult;
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：APP端焊口记录分页查询。</p>
	 * <p> 葛建。</p>	
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2019年3月28日 下午4:54:04。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("weldInfo")
	public Object getWeldInfoList(@RequestBody Map<String, Object> param){
		ListResult<Map<String, Object>> result= null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try{
			int page = Integer.parseInt(param.get("pageNo").toString());
			int rows = Integer.parseInt(param.get("pageSize").toString());
			String projectOid = param.get("projectOid").toString();
			String approveStatus = param.get("approveStatus") != null ? param.get("approveStatus").toString() : null;
			
			Page<Object> pageObj = 
					(Page<Object>) appGetDataService.getWeldInfoList(page, rows, projectOid,approveStatus);
			PageResult pageResult = new PageResult(pageObj.getResult(), (long)pageObj.getTotalLength());
			return pageResult;
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：APP端焊口返修分页查询。</p>
	 * <p> 葛建。</p>	
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2019年3月28日 下午4:54:04。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("weldRework")
	public Object getWeldReworkList(@RequestBody Map<String, Object> param){
		ListResult<Map<String, Object>> result= null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try{
			int page = Integer.parseInt(param.get("pageNo").toString());
			int rows = Integer.parseInt(param.get("pageSize").toString());
			String projectOid = param.get("projectOid") != null ? param.get("projectOid").toString() : null;
			String approveStatus = param.get("approveStatus") != null ? param.get("approveStatus").toString() : null;
			
			Page<Object> pageObj = 
					(Page<Object>) appGetDataService.getWeldReworkList(page, rows, projectOid,approveStatus);
			PageResult pageResult = new PageResult(pageObj.getResult(), (long)pageObj.getTotalLength());
			return pageResult;
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：APP端中线测量分页查询。</p>
	 * <p> 葛建。</p>	
	 * @param param
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2019年3月28日 下午4:54:04。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("measuredResult")
	public Object getMeasuredResultList(@RequestBody Map<String, Object> param){
		ListResult<Map<String, Object>> result= null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try{
			int page = Integer.parseInt(param.get("pageNo").toString());
			int rows = Integer.parseInt(param.get("pageSize").toString());
			String projectOid = param.get("projectOid").toString();
			String approveStatus = param.get("approveStatus") != null ? param.get("approveStatus").toString() : null;
			
			Page<Object> pageObj = 
					(Page<Object>) appGetDataService.getMeasuredResultList(page, rows, projectOid,approveStatus);
			PageResult pageResult = new PageResult(pageObj.getResult(), (long)pageObj.getTotalLength());
			return pageResult;
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：APP端防腐补口检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月28日 下午4:50:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("anticorrosionCheck")
	public Object getAnticorrosionCheckList(@RequestBody Map<String, Object> param){
		ListResult<Map<String, Object>> result= null;
		Map<String,Object> dataMap = new HashMap<String,Object>();
		try{
			int page = Integer.parseInt(param.get("page").toString());
			int rows = Integer.parseInt(param.get("rows").toString());
			String projectOid = param.get("projectOid").toString();
			String approveStatus = param.get("approveStatus") != null ? param.get("approveStatus").toString() : null;
			
			Page<Object> pageObj = 
					(Page<Object>) appGetDataService.getAnticorrosionCheckList(page,rows,projectOid,approveStatus);
			PageResult pageResult = new PageResult(pageObj.getResult(), (long)pageObj.getTotalLength());
			return pageResult;
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
