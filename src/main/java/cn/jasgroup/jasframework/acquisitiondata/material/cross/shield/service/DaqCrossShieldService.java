package cn.jasgroup.jasframework.acquisitiondata.material.cross.shield.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.cross.shield.dao.DaqCrossShieldDao;
import cn.jasgroup.jasframework.acquisitiondata.material.cross.shield.query.bo.DaqCrossShieldBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * @description 盾构隧道穿越service
 * @author zhangyi
 * @date 2018年7月17日上午10:14:17
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqCrossShieldService extends CommonDataJdbcService{

	@Autowired
	private DaqCrossShieldDao shieldDao;
	
	/**
	 * <p>功能描述：审核。</p>
	 * <p>张毅 </p>	
	 * @param paramMap
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:04:23。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap) {
		
		return this.shieldDao.approve(paramMap);
	}
	
	/**
	 * <p>功能描述：获取详情。</p>
	 * <p>张毅 </p>	
	 * @param oid
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午2:55:14。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public DaqCrossShieldBo get(String oid){
		return this.shieldDao.get(oid);
	}
}
