package cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.basedata.weldproduct.service.WeldProductService;
import cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.service.WorkPersonService;
import cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.service.WorkUnitService;
import cn.jasgroup.jasframework.security.controller.UnitController;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.security.service.bo.UnitBo;

@RestController
@RequestMapping("daq/workUnit")
public class WorkUnitController {

	@Autowired
	private WorkUnitService workUnitService;
	
	@Autowired
	private WorkPersonService workPersonService;
	
	@Autowired
	private WeldProductService weldProductService;
	
	@Autowired
	private UnitController unitController;
	
	@Resource(name="unitService")
	private UnitService unitService;
	
	/**
	  * <p>功能描述：获取施工单位列表。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月2日 上午11:29:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getWorkUnitList")
	@ResponseBody
	public Object getWorkUnitList(HttpServletRequest request,@RequestBody  Map<String, String> param){
		String projectOid = param.get("projectOid");
		String types = param.get("types");
		if(StringUtils.isBlank(types)){
			types = request.getParameter("types");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.workUnitService.getListByCondition(projectOid, types);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：根据组织机构oid获取组织机构树。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月2日 上午11:28:53。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getUnitListById")
	@ResponseBody
	public Object getUnitListById(HttpServletRequest request,@RequestBody  Map<String, String> param){
		ListResult<Map<String, Object>> result= null;
		try {
			String unitOid = param.get("unitOid");
			UnitBo unit = unitService.getUnitBoByOid(unitOid);
			PriUnit priUnit = new PriUnit();
			priUnit.setOid(unitOid);
			List<PriUnit> unitBoList = this.unitService.queryList(unit.getHierarchy());
			List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", unit.getOid());
			item.put("text", unit.getUnitName());
			Map<String, Object> attribute = new HashMap<String, Object>();
			if (unit.getParentId() == null) {
				attribute.put("type", "0");
				attribute.put("parentId","");
			} else{
				attribute.put("type", unit.getUnitType());
				attribute.put("parentId",unit.getParentId());
			} 
			// 默认列表关闭
			item.put("attributes", attribute);
			item.put("children", this.unitController.getChildren(priUnit, unitBoList, unitOid));
			rows.add(item);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：获取基础数据离线数据。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:44:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getOfflineWorkUnitData")
	@ResponseBody
	public Object getOfflineWorkUnitData(HttpServletRequest request){
		SimpleResult<Map<String,Object>> result= null;
		Map<String,Object> data = new HashMap<String,Object>();
		try{
			List<Map<String, Object>> workUnitRows = this.workUnitService.getListByCondition();
			data.put("workUnitData", workUnitRows);
			List<Map<String, Object>> workPersonRows = this.workPersonService.getListByCondition();
			data.put("workPersonData", workPersonRows);
			List<Map<String,Object>> weldProductRows = this.weldProductService.getListByCondition(null);
			data.put("weldProductData", weldProductRows);
			result = new SimpleResult<>(1,"200","ok",data);
		}catch(Exception e){
			result = new SimpleResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
