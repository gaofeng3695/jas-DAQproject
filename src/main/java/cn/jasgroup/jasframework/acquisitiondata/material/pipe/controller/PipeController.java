package cn.jasgroup.jasframework.acquisitiondata.material.pipe.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.material.pipe.service.PipeService;

@RestController
@RequestMapping("daq/materialPipe")
public class PipeController {

	@Autowired
	private PipeService pipeService;
	
	/**
	 * <p>功能描述：查询未使用的钢管。</p>
	  * <p> 葛建。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月10日 上午9:57:29。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@RequestMapping("/getCutAndNotUse")
	public Object getNotUseAndHasCut(){
		ListResult<Map<String, Object>> result= null;
		try{
			List<Map<String, Object>> rows = this.pipeService.getPipeList();
			result = new ListResult<>(1,"200","ok",rows);
		}catch(Exception e){
			result = new ListResult<>(-1,"400","error");
			e.printStackTrace();
		}
		return result;
	}
}
