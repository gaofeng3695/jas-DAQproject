package cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.controller;

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
import cn.jasgroup.framework.data.result.ErrorResult;
import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.framework.script.controller.CommonController;
import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.dao.entity.ColdBendingPipe;
import cn.jasgroup.jasframework.acquisitiondata.material.base.coldbending.service.ColdBendingPipeService;
import cn.jasgroup.jasframework.exception.DataflowException;

@RestController
@RequestMapping("daq/clodBendingPipe")
public class ColdBendingPipeController extends CommonController{
	
	@Resource(name="coldBendingPipeService")
	private ColdBendingPipeService coldBendingPipeService;
	
	/***
	  * <p>功能描述：冷弯管新增。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param coldBendingPipe
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月2日 下午3:01:56。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="save",method = RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request,@RequestBody ColdBendingPipe coldBendingPipe){
		SimpleResult<String> result = null;
		try{
			String oid = this.coldBendingPipeService.save(coldBendingPipe);
			result = new SimpleResult<>(1, "200", "ok",oid);
		}catch (DataflowException e) {
			ErrorResult errorResult = super.processException(e);
			return errorResult;
		}catch (Exception e) {
			e.printStackTrace();
			result = new SimpleResult<>(-1, "400", "error");
		}
		return result;
	}
	/***
	  * <p>功能描述：冷管修改。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param coldBendingPipe
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月2日 下午3:17:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="update",method = RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request,@RequestBody ColdBendingPipe coldBendingPipe){
		BaseResult result = null;
		try{
			this.coldBendingPipeService.update(coldBendingPipe);
			result = new BaseResult(1,"200","ok");
		}catch (Exception e) {
			result = new BaseResult(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：冷弯管删除。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param coldBendingPipe
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月2日 下午4:00:51。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request,@RequestBody ColdBendingPipe coldBendingPipe){
		BaseResult result = null;
		try{
			this.coldBendingPipeService.delete(coldBendingPipe);
			result = new BaseResult(1,"200","ok");
		}catch (Exception e) {
			result = new BaseResult(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
	/***
	  * <p>功能描述：获取冷弯管下拉选列表。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月20日 下午3:14:02。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getList",method = RequestMethod.POST)
	public Object getList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		try {
			String tendersOid = param.get("tendersOid");
			List<Map<String, Object>> rows = this.coldBendingPipeService.getListData(tendersOid);
			result = new ListResult<>(1, "200", "ok", rows);
		} catch (Exception e) {
			result = new ListResult<>(-1, "400", "error");
			e.printStackTrace();
		}
		return result;
	}
}
