package cn.jasgroup.jasframework.acquisitiondata.station.material.storage.controller;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.station.material.storage.service.StorageTankService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("daq/storagetank")
public class StorageTankController {

	@Autowired
	private StorageTankService storageTankService;

	/**
	 * <p>功能描述:查询项目下的储罐列表。</p>
	 * <p> cuixianing</p>
	 * @param
	 * @return java.lang.Object
	 * @since JDK1.8
	 * <p>创建日期:2019/1/4 14:07</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
	 */
	@RequestMapping("getStorageTankList")
	public Object getStorageTankList(@RequestBody Map<String,String> param){
		String projectOid = (String)param.get("projectOid");
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.storageTankService.getStorageTankList(projectOid);
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
	
}
