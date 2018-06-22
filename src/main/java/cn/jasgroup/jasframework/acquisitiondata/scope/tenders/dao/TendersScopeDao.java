package cn.jasgroup.jasframework.acquisitiondata.scope.tenders.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

@Repository
public class TendersScopeDao extends BaseJdbcDao{
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Autowired
	private BaseJdbcTemplate jdbcTemplate;
	
	/***
	  * <p>功能描述：保存标段与范围的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @param dataList
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月19日 下午4:24:18。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public boolean saveRef(String tendersOid,List<List<Object>> dataList){
		try {
			String sql = "insert into daq_tenders_scope_ref(oid,pipeline_oid,tenders_oid,scope_oid,scope_type,create_user_id,create_user_name,create_datetime,modify_user_id,modify_user_name,modify_datetime,active) values (?,?,?,?,?,?,?,?,?,?,?,?)";
			return this.jdbcTemplate.importExecuteBatch(sql, dataList);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	  * <p>功能描述：根据标段oid删除范围的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月19日 下午4:24:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteByTendersOid(String tendersOid){
//		String sql = "update daq_tenders_scope_ref set active=0 where tenders_oid=?";
//		this.baseJdbcDao.update(sql, new Object[]{tendersOid});
		String sql = "delete from daq_tenders_scope_ref where tenders_oid=?";
		this.baseJdbcDao.delete(sql, new Object[]{tendersOid});
	}
	/***
	  * <p>功能描述：根据标段oid获取范围树。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月19日 下午4:42:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getScopeDataTree(String tendersOid){
		String sql = "select v.* from daq_tenders t left join v_daq_scope v on v.project_oid=t.project_oid where t.oid = ?";
		return this.baseJdbcDao.queryForList(sql, new Object[]{tendersOid});
	}
	/***
	  * <p>功能描述：根据标段获取范围的关联列表。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月20日 上午10:57:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getTendersScopeRef(String tendersOid){
		String sql = "select scope_oid from daq_tenders_scope_ref where active=1 and tenders_oid=?";
		return this.baseJdbcDao.queryForList(sql, new Object[]{tendersOid});
	}
}
