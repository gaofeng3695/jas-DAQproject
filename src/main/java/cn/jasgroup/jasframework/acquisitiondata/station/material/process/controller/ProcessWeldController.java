package cn.jasgroup.jasframework.acquisitiondata.station.material.process.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.station.material.process.service.ProcessWeldService;

@RestController
@RequestMapping("daq/processWeld")
public class ProcessWeldController {

	@Autowired
	private ProcessWeldService processWeldService;
	
	/**
	 * <p>功能描述：通过站场阀室编号查询工艺管道焊口。</p>
	  * <p> 葛建。</p>	
	  * @param param
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午8:58:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("getProcessWeldList")
	public Object getProcessWeldList(@RequestBody Map<String,String> param){
		String pipeStationOid = (String)param.get("pipeStationOid");
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.processWeldService.getProcessWeldList(pipeStationOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
}
