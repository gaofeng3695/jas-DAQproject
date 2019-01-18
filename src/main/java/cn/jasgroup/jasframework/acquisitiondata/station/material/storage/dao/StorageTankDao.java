package cn.jasgroup.jasframework.acquisitiondata.station.material.storage.dao;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StorageTankDao {
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	 * <p>功能描述:查询项目下的储罐列表</p>
	 * <p> cuixianing</p>
	 * @param projectOid
	 * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
	 * @since JDK1.8
	 * <p>创建日期:2019/1/4 14:19</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
	 */
	public List<Map<String, Object>> getStorageTankList(String projectOid) {
		String sql = "select oid as key, device_name as value from daq_station_storage_tank " +
				"where active=1 and approve_status=2 and project_oid=?";
		return baseJdbcDao.queryForList(sql,new Object[]{projectOid});
	}

}
