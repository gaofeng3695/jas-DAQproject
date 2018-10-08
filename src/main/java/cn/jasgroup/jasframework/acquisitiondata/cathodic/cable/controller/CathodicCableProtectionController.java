package cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.service.CathodicCableProtectionService;

@RestController
@RequestMapping("daq/cathodicCableProtection")
public class CathodicCableProtectionController {
	
	@Autowired
	private CathodicCableProtectionService cathodicCableProtectionService;

	@RequestMapping("queryForSelect")
	public Object queryForSelect(@RequestBody Map<String,String> param){
		ListResult<Map<String,Object>> result=null;
		String codeId=param.get("codeId");
		String pipeSegmentOid=param.get("pipeSegmentOid");
		if (StringUtils.isNotBlank(codeId)) {
			try {
				List<Map<String,Object>> rows = this.cathodicCableProtectionService.queryForSelect(codeId, pipeSegmentOid);
				result = new ListResult<>(1, "200", "ok", rows);
			} catch (Exception e) {
				result = new ListResult<>(-1, "400", "error");
				e.printStackTrace();
			}
		}else{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			result = new ListResult<>(1, "200", "ok", list);
		}
		return result;
	}
}
