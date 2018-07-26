package cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.dao.DaqAppendagesHandHoleDao;
import cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.query.bo.DaqAppendagesHandHoleBo;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * @description 手孔表
 * @author zhangyi
 * @date 2018年7月21日下午12:37:39
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqAppendagesHandHoleService extends CommonDataJdbcService {

	@Autowired
	private DaqAppendagesHandHoleDao handHoleDao;
	
	/**
	 * <p>功能描述：审核。</p>
	 * <p>张毅 </p>	
	 * @param paramMap <idList, 数据oid集合>, <approveStatus, 数据状态>
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:04:23。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap) {
		return this.handHoleDao.approve(paramMap);
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
	public DaqAppendagesHandHoleBo get(BaseJavaQuery query){
		return this.handHoleDao.get(query);
	}
}
