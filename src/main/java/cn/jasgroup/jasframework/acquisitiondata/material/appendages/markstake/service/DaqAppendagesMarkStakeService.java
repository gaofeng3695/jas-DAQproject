package cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.dao.DaqAppendagesMarkStakeDao;
import cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.query.bo.DaqAppendagesMarkStakeBo;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * @description 标志桩
 * @author zhangyi
 * @date 2018年7月21日上午9:28:24
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqAppendagesMarkStakeService extends CommonDataJdbcService{

	@Autowired
	private DaqAppendagesMarkStakeDao markStakeDao;
	
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
		return this.markStakeDao.approve(paramMap);
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
	public DaqAppendagesMarkStakeBo get(BaseJavaQuery query){
		return this.markStakeDao.get(query);
	}
}
