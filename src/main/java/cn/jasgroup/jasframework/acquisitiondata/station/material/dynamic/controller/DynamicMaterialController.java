package cn.jasgroup.jasframework.acquisitiondata.station.material.dynamic.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.station.material.dynamic.service.DynamicMaterialService;

@RestController
@RequestMapping("daq/dynamicMaterial")
public class DynamicMaterialController {

	@Autowired
	private DynamicMaterialService dynamicMaterialService;

	/**
	 * <p>功能描述：查询项目下的空气冷却器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:25:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialAirCoolerList")
	public Object getMaterialAirCoolerList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.dynamicMaterialService.getMaterialAirCoolerList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：查询项目下的压缩机燃气机物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:29:16。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialGasEngine")
	public Object getMaterialGasEngine(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.dynamicMaterialService.getMaterialGasEngine(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：查询项目下的仪表风压缩机橇物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:36:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialInstrumentCompressor")
	public Object getMaterialInstrumentCompressor(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.dynamicMaterialService.getMaterialInstrumentCompressor(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <p>功能描述：查询项目下的电加热器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:39:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialElectricHeater")
	public Object getMaterialElectricHeater(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.dynamicMaterialService.getMaterialElectricHeater(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * <p>功能描述：查询项目下的消气器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午9:42:00。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialAirEliminater")
	public Object getMaterialAirEliminater(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.dynamicMaterialService.getMaterialAirEliminater(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
}
