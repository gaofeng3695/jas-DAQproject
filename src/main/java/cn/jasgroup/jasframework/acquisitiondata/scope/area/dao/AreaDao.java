package cn.jasgroup.jasframework.acquisitiondata.scope.area.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseEntityDao;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class AreaDao extends BaseEntityDao{
	
	@Resource
	private BaseJdbcDao baseJdbcDao;
	/**
	 * <p>功能描述：获取省。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午4:09:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getProvinceList(){
		String sql = "select oid as key,name as value from area where type='1' order by code ";
		return this.baseJdbcDao.queryForList(sql, null);
	}
	/***
	 * <p>功能描述：根据省获取市。</p>
	  * <p> 雷凯。</p>	
	  * @param parentOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午4:10:14。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getCityList(String parentOid){
		String sql = "select oid as key,name as value from area where type='2' and parent_oid=(select wbs from area where oid=?) order by code";
		return this.baseJdbcDao.queryForList(sql, new Object[]{parentOid});
	}
	/***
	 * <p>功能描述：根据市获取区/县。</p>
	  * <p> 雷凯。</p>	
	  * @param parentOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月13日 下午4:10:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getCountyList(String parentOid){
		String sql = "select oid as key,name as value from area where type='3' and parent_oid=? order by code ";
		return this.baseJdbcDao.queryForList(sql, new Object[]{parentOid});
	}
	
}
