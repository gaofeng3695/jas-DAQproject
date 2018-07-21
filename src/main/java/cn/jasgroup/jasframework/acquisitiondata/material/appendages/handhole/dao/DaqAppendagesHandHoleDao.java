package cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.query.bo.DaqAppendagesHandHoleBo;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess.base.BaseNamedParameterJdbcDao;

/**
 * @description 手孔表
 * @author zhangyi
 * @date 2018年7月21日下午12:35:33
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class DaqAppendagesHandHoleDao extends BaseNamedParameterJdbcDao {

	@Resource
	private BaseJdbcDao baseJdbcDao;
	
	/**
	 * <p>功能描述：数据审核。</p>
	 * <p>张毅 </p>	
	 * @param paramMap <idList, 数据oid集合>, <approveStatus, 数据状态>
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:05:51。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap) {
		Boolean b = false;
		String sql = "update daq_appendages_hand_hole set approve_status = :approveStatus  where active=1"
				+ " and oid in (:idList)";
		int count = super.update(sql, paramMap);
		if(count > 0){
			b = true;
		}
		return b;
	}

	/**
	 * <p>功能描述：获取单条数据详情。</p>
	 * <p>张毅 </p>	
	 * @param query
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午2:48:43。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public DaqAppendagesHandHoleBo get(BaseJavaQuery query){
		String sql = "select t.* from (" +  query.getSql() + ") t where t.oid = ? ";
		return (DaqAppendagesHandHoleBo) this.baseJdbcDao.queryForObject(sql, 
				new Object[]{query.getOid()}, DaqAppendagesHandHoleBo.class);
	}
}
