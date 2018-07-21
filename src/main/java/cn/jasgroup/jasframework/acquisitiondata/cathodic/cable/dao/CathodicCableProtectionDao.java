package cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class CathodicCableProtectionDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;

	public List<Map<String, Object>> queryTestStack(String pipeSegmentOid) {
		String sql = "select oid as key, test_stake_code as value from daq_cathodic_test_stake where active=1 and pipe_segment_oid = ?";
		Object[] param = new Object[1];
		param[0]=pipeSegmentOid;
		return baseJdbcDao.queryForList(sql, param);
	}

	public List<Map<String, Object>> querySacrificeAnode(String pipeSegmentOid) {
		String sql = "select oid as key, anode_code as value from daq_cathodic_sacrifice_anode where active=1 and pipe_segment_oid = ?";
		Object[] param = new Object[1];
		param[0]=pipeSegmentOid;
		return baseJdbcDao.queryForList(sql, param);
	}

	public List<Map<String, Object>> queryAnodeBed(String pipeSegmentOid) {
		String sql = "select oid as key, ground_bed as value from daq_cathodic_anode_bed where active=1 and pipe_segment_oid = ?";
		Object[] param = new Object[1];
		param[0]=pipeSegmentOid;
		return baseJdbcDao.queryForList(sql, param);
	}
	
	
}
