package cn.jasgroup.jasframework.acquisitiondata.dataapprove.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.dataapprove.service.DataApproveService;
import cn.jasgroup.jasframework.base.controller.BaseController;

@RequestMapping("daq/dataApprove")
@RestController
public class DataApproveController extends BaseController{
	
	@Resource(name="dataApproveService")
	private DataApproveService dataApproveService;
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request,@RequestBody Map<String,Object> param){
		SimpleResult<String> result = null;
		try {
			List<String> businessOids = param.get("businessOid") != null ? (List<String>) param.get("businessOid") : null;
			if(businessOids==null || businessOids.size()==0){
				return new SimpleResult<>(-1, "400", "businessOid not is null");
			}
			String approveOpinion = param.get("approveOpinion") != null ? param.get("approveOpinion").toString() : null;
			String approveStatus = param.get("approveStatus") != null ? param.get("approveStatus").toString() : "0";
			String functionCode = param.get("functionCode") != null ? param.get("functionCode").toString() : null;
			String className = param.get("className") != null ? param.get("className").toString() : null;
			this.dataApproveService.saveData(businessOids, className, functionCode, approveOpinion, approveStatus);
			result = new SimpleResult<>(1, "200", "ok");
		} catch (Exception e) {
			result = new SimpleResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
