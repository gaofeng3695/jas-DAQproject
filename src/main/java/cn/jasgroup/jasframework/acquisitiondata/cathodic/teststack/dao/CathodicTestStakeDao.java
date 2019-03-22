package cn.jasgroup.jasframework.acquisitiondata.cathodic.teststack.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.jasgroup.jasframework.acquisitiondata.utils.VariateInjectUtils;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

@Repository
public class CathodicTestStakeDao {

	@Resource
	private BaseJdbcTemplate baseJdbcTemplate;
	
	/***
	  * <p>功能描述：根据标段oid获取测试桩列表。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月31日 下午1:58:20。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getTestStakeList(String tendersOid){
		String sql = "select oid as key,test_stake_code as value from daq_cathodic_test_stake where active=1 and @privilege_strategy_sql and tenders_oid=?";
		sql = VariateInjectUtils.invoke(sql);
		return this.baseJdbcTemplate.queryForListHump(sql, new Object[]{tendersOid});
	}
}
