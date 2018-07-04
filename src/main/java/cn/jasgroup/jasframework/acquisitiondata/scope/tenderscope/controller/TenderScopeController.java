package cn.jasgroup.jasframework.acquisitiondata.scope.tenderscope.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.jasframework.acquisitiondata.scope.tenderscope.service.TenderScopeService;
import cn.jasgroup.jasframework.base.controller.BaseController;

/**
 * <p>功能描述：获取标段范围树。</p>
  * <p> 葛建。</p>	
  * @return
  * @since JDK1.8。
  * <p>创建日期:2018年6月20日 下午3:42:47。</p>
  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
 */
@RestController
@RequestMapping("daq/tendersScope")
public class TenderScopeController extends BaseController {

	@Autowired
	private TenderScopeService tenderScopeService;
	
	/**
	 * 获取标段范围树
	 * 
	 * @param request
	 * @param response
	 * @return itemsList
	 */
	@RequestMapping("getTenderScopeTree")
	public Object getScopeTree(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String, String> param) {
		String tenderId = param.get("tenderId");
		List<Map<String, Object>> itemsList = new ArrayList<Map<String, Object>>();
		try {
			itemsList = tenderScopeService.getTenderScopeTree(tenderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return itemsList;
	}

}