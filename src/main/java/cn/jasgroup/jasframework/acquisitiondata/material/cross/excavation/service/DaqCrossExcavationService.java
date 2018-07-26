package cn.jasgroup.jasframework.acquisitiondata.material.cross.excavation.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.cross.excavation.dao.DaqCrossExcavationDao;
import cn.jasgroup.jasframework.acquisitiondata.material.cross.excavation.query.bo.DaqCrossExcavationBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * @description 开挖穿跨越
 * @author zhangyi
 * @date 2018年7月16日下午3:12:17
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqCrossExcavationService extends CommonDataJdbcService {

	@Autowired
	private DaqCrossExcavationDao excavationDao;
	
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
		
		return this.excavationDao.approve(paramMap);
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
	public DaqCrossExcavationBo get(String oid){
		return this.excavationDao.get(oid);
	}
}
