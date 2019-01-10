package cn.jasgroup.jasframework.acquisitiondata.station.material.process.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class ProcessWeldDao {
	
	@Autowired
	private BaseJdbcDao baseJdbcDao;

	/**
	 * <p>功能描述：通过站场阀室编号查询工艺管道焊口。</p>
	  * <p> 葛建。</p>	
	  * @param pipeStationOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月3日 上午8:59:28。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getProcessWeldList(String pipeStationOid) {
		String sql = "select oid as key, weld_code as value from daq_station_process_weld "
					+ "where active=1 and approve_status=2 and pipe_station_oid='"+pipeStationOid+"'";
		return baseJdbcDao.queryForList(sql, null);
	}

}
