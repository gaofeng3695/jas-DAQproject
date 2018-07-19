package cn.jasgroup.jasframework.acquisitiondata.material.cross.drillingblasting.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.framework.data.result.BaseResult;
import cn.jasgroup.framework.data.result.SimpleResult;
import cn.jasgroup.jasframework.acquisitiondata.material.cross.drillingblasting.query.bo.DaqCrossDrillingBlastingBo;
import cn.jasgroup.jasframework.acquisitiondata.material.cross.drillingblasting.service.DaqCrossDrillingBlastingService;
import cn.jasgroup.jasframework.base.controller.BaseController;

/**
 * @description 钻爆隧道穿越
 * @author zhangyi
 * @date 2018年7月17日上午10:50:25
 * @version V1.0
 * @since JDK 1.80
 */

@RestController
@RequestMapping("/daq/crossDrillingBlasting")
public class DaqCrossDrillingBlastingController extends BaseController{

	@Autowired
	private DaqCrossDrillingBlastingService drillingBlastingService;
	
	/**
	 * <p>功能描述：数据审核。</p>
	 * <p>张毅 </p>	
	 * @param request
	 * @param paramMap
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午4:56:20。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@PostMapping("/approve")
	public Object approve(HttpServletRequest request , @RequestBody Map<String, Object> paramMap){
		BaseResult result = null;
		try {
			String oid = (String) paramMap.get("oid");
			Integer approveStatus = (Integer) paramMap.get("approveStatus");
			if(StringUtils.isBlank(oid) || null == approveStatus){
				result = new SimpleResult<Boolean>(-1, "400", "error");
			}
			Boolean b = this.drillingBlastingService.approve(paramMap);
			if(b){
				result = new SimpleResult<Boolean>(b);
			}else{
				result = new SimpleResult<Boolean>(-1, "400", "error");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new SimpleResult<Boolean>(-1, "500", "error");
		}
		return result;
	}
	
	/**
	 * <p>功能描述：获取详情信息。</p>
	 * <p>张毅 </p>	
	 * @param request
	 * @param oid
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:01:41。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@GetMapping("/get")
	public Object get(HttpServletRequest request, @RequestParam String oid){
		BaseResult result = null;
		try {
			if(StringUtils.isBlank(oid)){
				result = new SimpleResult<Boolean>(-1, "400", "error");
			}
			DaqCrossDrillingBlastingBo bo = this.drillingBlastingService.get(oid);
			result = new SimpleResult<DaqCrossDrillingBlastingBo>(bo);
		} catch (Exception e) {
			e.printStackTrace();
			result = new SimpleResult<String>(-1, "500", "error");
		}
		return result;
	}
}
