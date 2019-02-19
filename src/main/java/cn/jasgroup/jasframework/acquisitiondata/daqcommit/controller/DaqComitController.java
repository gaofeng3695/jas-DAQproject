package cn.jasgroup.jasframework.acquisitiondata.daqcommit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.daqcommit.service.DaqCommitService;

@RestController
@RequestMapping("daq/daqcommit")
public class DaqComitController {

	@Autowired
	private DaqCommitService daqCommitService;
	
	/**
	 * <p>功能描述： 修改functionCode或者className中对应oids的提交状态。</p>
	  * <p> 葛建。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月18日 下午5:08:10。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="changeCommitStatus", method = RequestMethod.POST)
	@ResponseBody
	public Object changeCommitStatus(HttpServletRequest request,@RequestBody Map<String,Object> param){
		SimpleResult<String> result = null;
		try {
			// 有提交动作的数据oid
			List<String> businessOids = (List<String>)param.get("businessOid");
			if(businessOids==null || businessOids.size()==0){
				return new SimpleResult<>(-1, "400", "businessOid not is null");
			}
			//数据对应的自定义表单的functionCode
			String functionCode = param.get("functionCode") != null ? param.get("functionCode").toString() : null;
			//数据对应的后台类的className
			String className = param.get("className") != null ? param.get("className").toString() : null;
			this.daqCommitService.changeBussinessCommitStatus(businessOids, functionCode, className);
			result = new SimpleResult<>(1, "200", "ok");
		} catch (Exception e) {
			result = new SimpleResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	} 
	
}
