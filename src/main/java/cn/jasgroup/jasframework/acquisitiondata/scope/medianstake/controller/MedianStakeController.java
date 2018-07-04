package cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.exception.BusinessException;
import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.dao.entity.MedianStake;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.MedianStakeService;
import cn.jasgroup.jasframework.acquisitiondata.scope.medianstake.service.bo.MedianStakeQueryBo;
import cn.jasgroup.jasframework.base.controller.BaseController;
import cn.jasgroup.jasframework.unique.UniqueHibernateService;
import cn.jasgroup.jasframework.unique.UniqueResult;

@RestController
@RequestMapping("daq/medianStake")
public class MedianStakeController extends BaseController{

	@Resource(name="medianStakeService")
	private MedianStakeService medianStakeService;
	
	@Autowired
	private UniqueHibernateService uniqueService;
	/**
	 * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param medianStake
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午6:57:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public Object save(HttpServletRequest request,@RequestBody MedianStake medianStake){
		SimpleResult<String> result = null;
		try {
			List<UniqueResult> uniqueResultList = uniqueService.checkSaveUnique(medianStake);
			if(uniqueResultList.size() > 0){
				ListResult<UniqueResult> listResult = new ListResult<UniqueResult>(-1,"400",uniqueResultList.get(0).getName());
				listResult.setRows(uniqueResultList);
				return listResult;
			}
			String oid = this.medianStakeService.save(medianStake);
			result = new SimpleResult<String>(1, "200", "ok",oid);
		} catch (Exception e) {
			e.printStackTrace();
			result = new SimpleResult<String>(-1, "400", "error");
		}
		return result;
	}
	/**
	 * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param medianStake
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午6:57:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Object update(HttpServletRequest request,@RequestBody MedianStake medianStake){
		BaseResult result = null;
		try {
			List<UniqueResult> uniqueResultList = uniqueService.checkUpdateUnique(medianStake);
			if(uniqueResultList.size() > 0){
				ListResult<UniqueResult> listResult = new ListResult<UniqueResult>(-1,"400",uniqueResultList.get(0).getName());
				listResult.setRows(uniqueResultList);
				return listResult;
			}
			this.medianStakeService.update(medianStake);
			result = new BaseResult(1, "200", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BaseResult(-1, "400", "error");
		}
		return result;
	}
	/***
	  * <p>功能描述：获取中线桩详情。</p>
	  * <p> 雷凯。</p>	
	  * @param request
	  * @param oid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午7:01:26。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="get",method = RequestMethod.GET)
	@ResponseBody
	public Object get(HttpServletRequest request,String oid){
		SimpleResult<MedianStakeQueryBo> result = null;
		try {
			MedianStakeQueryBo medianStake = this.medianStakeService.get(oid);
			result = new SimpleResult<MedianStakeQueryBo>(1, "200", "ok", medianStake);
		} catch (Exception e) {
			e.printStackTrace();
			result = new SimpleResult<MedianStakeQueryBo>(-1, "400", "ok");
		}
		return result;
	}
	/***
	 * <p>功能描述：删除中线桩。</p>
	 * <p> 雷凯。</p>	
	 * @param request
	 * @param oid
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年6月13日 下午7:01:26。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="delete",method = RequestMethod.POST)
	@ResponseBody
	public Object delete(HttpServletRequest request,@RequestBody MedianStake medianStake){
		BaseResult result = null;
		try {
			this.medianStakeService.delete(medianStake);
			result = new BaseResult(1, "200", "ok");
		} catch (Exception e) {
			e.printStackTrace();
			result = new BaseResult(-1, "400", "ok");
		}
		return result;
	}
	/***
	  * <p>功能描述：获取中线桩列表，用于下拉选选。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月14日 上午11:09:44。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping(value="getList",method = RequestMethod.POST)
	@ResponseBody
	public Object getList(HttpServletRequest request,@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result = null;
		String projectOid = param.get("projectOid");
		String pipelineOid = param.get("pipelineOid");
		if(StringUtils.isBlank(projectOid) && StringUtils.isBlank(pipelineOid)){
			result = new ListResult<>(-1, "400", "param is null");
			throw new BusinessException("param is null", "400");
		}else{
			try {
				List<Map<String, Object>> rows = this.medianStakeService.getList(projectOid, pipelineOid);
				result = new ListResult<>(1, "200", "ok", rows);
			} catch (Exception e) {
				result = new ListResult<>(-1, "400", "error");
				e.printStackTrace();
			}
		}
		return result;
	}
}
