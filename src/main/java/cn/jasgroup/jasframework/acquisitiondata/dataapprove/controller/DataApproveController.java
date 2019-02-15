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

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.dataapprove.service.DataApproveService;
import cn.jasgroup.jasframework.base.controller.BaseController;

@RequestMapping("daq/dataApprove")
@RestController
public class DataApproveController extends BaseController{
	
	@Resource(name="dataApproveService")
	private DataApproveService dataApproveService;
	
	/**
	  * <p>功能描述：保存业务流程审批记录。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月23日 下午1:50:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
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
			String privilegeCode = param.get("privilegeCode")!=null ? param.get("privilegeCode").toString() : null;
			this.dataApproveService.saveData(businessOids, className, functionCode, approveOpinion, approveStatus,privilegeCode);
			result = new SimpleResult<>(1, "200", "ok");
		} catch (Exception e) {
			result = new SimpleResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：获取权限树对应的为审核的数据。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月15日 下午5:27:02。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="queryUnauditedInfo", method = RequestMethod.POST)
	@ResponseBody
	public Object queryUnauditedInfo(HttpServletRequest request){
		ListResult<Map<String,Object>> result = null;
		try {
			List<Map<String, Object>> listData = dataApproveService.queryUnauditedInfo();
			result = new ListResult<>(0, "200", "ok", listData);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
