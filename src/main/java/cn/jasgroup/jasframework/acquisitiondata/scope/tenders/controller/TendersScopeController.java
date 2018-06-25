package cn.jasgroup.jasframework.acquisitiondata.scope.tenders.controller;

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
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.scope.tenders.dao.entity.TendersScope;
import cn.jasgroup.jasframework.acquisitiondata.scope.tenders.service.TendersScopeService;
import cn.jasgroup.jasframework.base.controller.BaseController;
import cn.jasgroup.jasframework.security.controller.data.json.TreeItem;
import cn.jasgroup.jasframework.utils.web.JsonUtil;

@RestController
@RequestMapping("daq/tendersScope")
public class TendersScopeController extends BaseController{
	
	@Resource(name="tendersScopeService")
	private TendersScopeService tendersScopeService;
	
	/***
	  * <p>功能描述：保存标段与范围的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月19日 下午4:25:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="saveRef",method = RequestMethod.POST)
	@ResponseBody
	public Object saveRef(HttpServletRequest request,@RequestBody Map<String,Object> param){
		BaseResult result = null;
		String tendersOid = param.get("tendersOid")!=null?param.get("tendersOid").toString():null;
		Object dataObj = param.get("data");
		List<TendersScope> dataList = null;
		try {
			String jsonString = JsonUtil.getJSONString(dataObj);
			dataList = JsonUtil.getDTOList(jsonString, TendersScope.class);
			boolean resultFlag = this.tendersScopeService.saveRef(tendersOid,dataList);
			if(resultFlag){
				result = new BaseResult(1,"200","ok");
			}else{
				result = new BaseResult(-1,"400","error");
			}
		} catch (Exception e) {
			result = new BaseResult(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * <p>功能描述：标段范围查询接口。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 下午3:23:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getScopeDataTree",method = RequestMethod.POST)
	@ResponseBody
	public Object getScopeDataTree(HttpServletRequest request,@RequestBody Map<String,String> param){
		SimpleResult<TreeItem> result = null;
		String tendersOid = param.get("tendersOid");
		try {
			TreeItem item = this.tendersScopeService.getScopeDataTree(tendersOid);
			result = new SimpleResult<TreeItem>(1,"200","ok",item);
		} catch (Exception e) {
			result = new SimpleResult<TreeItem>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
