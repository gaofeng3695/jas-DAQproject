package cn.jasgroup.jasframework.acquisitiondata.station.material.communicate.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.station.material.communicate.service.CommunicateMaterialService;

@RestController
@RequestMapping("daq/communicateMaterial")
public class CommunicateMaterialController {

	@Autowired
	private CommunicateMaterialService communicateMaterialService;
	
	/**
	 * <p>功能描述：根据项目查询话音交换设备物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月10日 上午11:37:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialTelephoneExchangeList")
	public Object getMaterialTelephoneExchangeList(@RequestBody Map<String, Object> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.communicateMaterialService.getMaterialScsList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询工业电视监控系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月10日 上午11:39:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialCctvSystemList")
	public Object getMaterialCctvSystemList(@RequestBody Map<String, Object> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.communicateMaterialService.getMaterialCctvSystemList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询会议电视设备物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月10日 上午11:41:18。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialVideoConferenceList")
	public Object getMaterialVideoConferenceList(@RequestBody Map<String, Object> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.communicateMaterialService.getMaterialVideoConferenceList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
			
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询高频开关电源设备物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月11日 上午11:07:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialHighFrequencySwitchList")
	public Object getMaterialHighFrequencySwitchList(@RequestBody Map<String, Object> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.communicateMaterialService.getMaterialHighFrequencySwitchList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
			
		}
		return result;
	}
	
	
}
