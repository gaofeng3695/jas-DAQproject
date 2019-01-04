package cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.service.WorkPersonService;

@RestController
@RequestMapping("daq/workPerson")
public class WorkPersonController {

	@Autowired
	private WorkPersonService workPersonService;
	
	@RequestMapping("getWorkPersonList")
	public Object getWorkPersonList(@RequestBody  Map<String, String> param){
		String workUnitOid = param.get("workUnitOid");
		String types = param.get("types");
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.workPersonService.getListByCondition(workUnitOid, types);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：查询机组下的焊工列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月2日 上午11:35:48。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getWeldersListByWorkUnit")
	public Object getWeldersList(@RequestBody  Map<String, String> param){
		String workUnitOid = param.get("workUnitOid");
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.workPersonService.getWeldersList(workUnitOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
