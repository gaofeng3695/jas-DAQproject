package cn.jasgroup.jasframework.acquisitiondata.scope.implementscope.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.scope.implementscope.dao.entity.ImplementScope;
import cn.jasgroup.jasframework.acquisitiondata.scope.implementscope.service.ImplementScopeService;
import cn.jasgroup.jasframework.base.controller.BaseController;
import cn.jasgroup.jasframework.security.controller.data.json.TreeItem;
import cn.jasgroup.jasframework.utils.web.JsonUtil;

@RestController
@RequestMapping("daq/implementScope")
public class ImplementScopeController extends BaseController{
	
	@Resource(name="implementScopeService")
	private ImplementScopeService implementScopeService;
	
	/***
	  * <p>功能描述：获取实施范围树形数据。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午10:29:40。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getImplementScopeTree",method = RequestMethod.POST)
	@ResponseBody
	public Object getImplementScopeTree(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<TreeItem> result = null;
		String unitOid = param.get("unitOid");
		try {
			List<TreeItem> itemList = this.implementScopeService.getImplementScopeTree(unitOid);
			result = new ListResult<>(0, "200", "ok", itemList);
		} catch (Exception e) {
			e.printStackTrace();
			result = new ListResult<>(-1, "400", "error");
		}
		return result;
	}
	/***
	  * <p>功能描述：保存实施范围的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param dataList
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午10:51:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveRef",method = RequestMethod.POST)
	@ResponseBody
	public Object saveRef(HttpServletRequest request,@RequestBody Map<String,Object> param){
		BaseResult result = null;
		String unitOid = param.get("unitOid")!=null?param.get("unitOid").toString():null;
		Object dataStr = param.get("data");
		List<ImplementScope> dataList = null;
		try {
			String jsonString = JsonUtil.getJSONString(dataStr);
			dataList = JsonUtil.getDTOList(jsonString, ImplementScope.class);
			boolean success = this.implementScopeService.saveRef(unitOid,dataList);
			if(success){
				result = new BaseResult(1, "200", "ok");
			}else{
				result = new BaseResult(-1, "400", "error");
			}
		} catch (Exception e) {
			result = new BaseResult(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
}
