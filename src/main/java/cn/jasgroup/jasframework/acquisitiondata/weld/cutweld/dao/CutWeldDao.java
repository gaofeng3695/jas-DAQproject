package cn.jasgroup.jasframework.acquisitiondata.weld.cutweld.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class CutWeldDao {

	@Resource
	private BaseJdbcDao baseJdbcDao;
	

	public String getOidByCode(String cellValue, String tableName, String code) {
		String sql = "select oid from "+tableName+" where active=1 and "+code+"='"+cellValue+"'";
		List<Map<String, Object>> list = baseJdbcDao.queryForList(sql, null);
		String oid = (String)list.get(0).get("oid");
		return oid;
	}


	public List<Map<String, Object>> queryPipeCodeList() {
		String sql = "select mp.pipe_code, pro.project_code from daq_cut_pipe cp LEFT JOIN (select oid, pipe_code from daq_material_pipe where active = 1) mp ON mp.oid = cp.pipe_oid LEFT JOIN (select oid,project_code from daq_project where active = 1) pro ON pro.oid = cp.project_oid where cp.active =1 ";
		List<Map<String, Object>> list = baseJdbcDao.queryForList(sql, null);
		return list;
	}


	public List<Map<String, Object>> getPipeInfo(String pipeOid) {
		String sql = "select pipe_length,pipe_diameter,wall_thickness from daq_material_pipe where active=1 and oid='"+pipeOid+"'";
		List<Map<String, Object>> list = baseJdbcDao.queryForList(sql, null);
		return list;
	}

}
