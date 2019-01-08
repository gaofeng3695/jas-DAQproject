package cn.jasgroup.jasframework.acquisitiondata.station.autocontrol.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.station.autocontrol.service.AutocontrolMaterialService;

/**
 * 
  *<p>类描述：自动控制设备物资controller。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月8日 上午9:40:11。</p>
 */
@RestController
@RequestMapping("daq/autocontrolMaterial")
public class AutocontrolMaterialController {

	@Autowired
	private AutocontrolMaterialService autocontrolMaterialService;

	/**
	 * <p>功能描述：根据项目查询SCADA系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:41:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialScadaList")
	public Object getMaterialScadaList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMaterialScadaList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询站控系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:55:10。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialScsList")
	public Object getMaterialScsList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMaterialScsList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询计量系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:56:57。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialMeteringList")
	public Object getMaterialMeteringList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMaterialMeteringList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询调压系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午9:58:48。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialPressureList")
	public Object getMaterialPressureList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMaterialPressureList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询分析系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午10:55:43。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMateriAlanalysisList")
	public Object getMateriAlanalysisList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMateriAlanalysisList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询标定设备物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:09:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMateriProverList")
	public Object getMateriProverList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMateriProverList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询自用气系统物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:21:36。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMateriSgsList")
	public Object getMateriSgsList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMateriSgsList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询压力变送器物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:27:23。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialTransmitterList")
	public Object getMaterialTransmitterList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMaterialTransmitterList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询压力物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:30:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialGaugeList")
	public Object getMaterialGaugeList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMaterialGaugeList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * <p>功能描述：根据项目查询差压变送器物资列表daq_s_material_pressure_difference_transmitter。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月8日 上午11:32:08。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialDifferenceTransmitterList")
	public Object getMaterialDifferenceTransmitterList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.autocontrolMaterialService.getMaterialDifferenceTransmitterList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
	
}
