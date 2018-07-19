package cn.jasgroup.jasframework.acquisitiondata.material.cross.boxculvert.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.cross.boxculvert.dao.DaqCrossBoxCulvertDao;
import cn.jasgroup.jasframework.acquisitiondata.material.cross.boxculvert.query.bo.DaqCrossBoxCulvertBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * @description 箱涵穿越
 * @author zhangyi
 * @date 2018年7月16日下午3:46:53
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqCrossBoxCulvertService extends CommonDataJdbcService {

	@Autowired
	private DaqCrossBoxCulvertDao boxCulvertDao;
	
	/**
	 * <p>功能描述：审核。</p>
	 * <p>张毅 </p>	
	 * @param paramMap	数据状态
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:04:23。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap) {
		return this.boxCulvertDao.approve(paramMap);
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
	public DaqCrossBoxCulvertBo get(String oid){
		return this.boxCulvertDao.get(oid);
	}
}
