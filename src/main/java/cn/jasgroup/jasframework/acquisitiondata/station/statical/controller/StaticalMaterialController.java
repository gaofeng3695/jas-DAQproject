package cn.jasgroup.jasframework.acquisitiondata.station.statical.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.station.statical.service.StaticalMaterialService;

/**
 * 
  *<p>类描述：静设备物资controller。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年1月4日 上午10:33:26。</p>
 */
@RestController
@RequestMapping("daq/staticalMaterial")
public class StaticalMaterialController {

	@Autowired
	private StaticalMaterialService staticalMaterialService;

	/**
	 * <p>功能描述：根据项目查询清管器收发球装置物资列表。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月4日 上午10:32:21。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getMaterialLauncherList")
	public Object getMaterialLauncherList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		if (StringUtils.isBlank(projectOid)) {
			return new ListResult<>(-1,"400","项目Oid不能为空");
		}
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.staticalMaterialService.getMaterialLauncherList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
}
