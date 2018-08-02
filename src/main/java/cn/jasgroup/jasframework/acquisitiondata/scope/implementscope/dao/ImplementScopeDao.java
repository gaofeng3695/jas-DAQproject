package cn.jasgroup.jasframework.acquisitiondata.scope.implementscope.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

@Repository
public class ImplementScopeDao extends BaseJdbcDao{
	
	@Autowired
	private BaseJdbcTemplate jdbcTemplate;
	
	/***
	  * <p>功能描述：获取实施范围数据列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午10:27:03。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getImplementScopeTreeData(){
		String sql = "select * from v_daq_implement_scope";
		return super.queryForList(sql, null);
	}
	
	/***
	  * <p>功能描述：保存实施范围的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param dataList
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午10:27:35。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public boolean saveRef(List<List<Object>> dataList){
		try {
			String sql = "insert into daq_implement_scope_ref (oid,unit_oid,project_oid,pipeline_oid,tenders_oid,scope_oid,scope_type,create_user_id,create_user_name,create_datetime,modify_user_id,modify_user_name,modify_datetime,active) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			this.jdbcTemplate.importExecuteBatch(sql, dataList);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/***
	  * <p>功能描述：根据unitOid删除实施范围关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param unitOid
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午10:38:23。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteRef(String unitOid){
//		String sql = "update daq_implement_scope_ref set avtice = 0 where unit_oid=?";
//		this.update(sql, new Object[]{unitOid});
		String sql = "delete from daq_implement_scope_ref where unit_oid=?";
		this.delete(sql, new Object[]{unitOid});
	}
	
	/***
	  * <p>功能描述：根据unitOid获取已存在的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param unitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午11:27:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getImplemntScopeRef(String unitOid){
		String sql = "select scope_oid,tenders_oid,pipeline_oid,project_oid from daq_implement_scope_ref where unit_oid=? and active=1";
		return this.queryForList(sql, new Object[]{unitOid});
	}
}
