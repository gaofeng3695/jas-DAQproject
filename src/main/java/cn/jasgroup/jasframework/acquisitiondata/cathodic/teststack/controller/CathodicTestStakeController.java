package cn.jasgroup.jasframework.acquisitiondata.cathodic.teststack.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.cathodic.teststack.service.CathodicTestStakeService;
import cn.jasgroup.jasframework.base.controller.BaseController;

@RestController
@RequestMapping("daq/cathodicTestStake")
public class CathodicTestStakeController extends BaseController{
	
	@Resource(name="cathodicTestStakeService")
	private CathodicTestStakeService cathodicTestStakeService;
	
	/***
	  * <p>功能描述：根据标段oid获取测试桩列表。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月31日 下午1:57:36。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/getTestStakeList")
	@ResponseBody
	public Object getTestStakeList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String tendersOid = param.get("tendersOid");
			List<Map<String, Object>> rows = this.cathodicTestStakeService.getTestStakeList(tendersOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
}
