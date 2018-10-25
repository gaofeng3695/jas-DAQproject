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
	
	/**
	 * <p>功能描述：通过表名、字段名查询对应的oid。</p>
	  * <p> 葛建。</p>	
	  * @param cellValue
	  * @param tableName
	  * @param code
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年10月24日 上午9:45:14。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public String getOidByCode(String cellValue, String tableName, String code) {
		String sql = "select oid from "+tableName+" where active=1 and "+code+"='"+cellValue+"'";
		List<Map<String, Object>> list = baseJdbcDao.queryForList(sql, null);
		if (list.size() == 0) {
			return "";
		}
		String oid = (String)list.get(0).get("oid");
		return oid;
	}

	/**
	 * <p>功能描述：查询切管表中项目code和对应的管线code。</p>
	  * <p> 葛建。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年10月24日 上午9:46:16。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> queryPipeCodeList() {
		String sql = "select mp.pipe_code, pro.project_code from daq_cut_pipe cp LEFT JOIN (select oid, pipe_code from daq_material_pipe where active = 1) mp ON mp.oid = cp.pipe_oid LEFT JOIN (select oid,project_code from daq_project where active = 1) pro ON pro.oid = cp.project_oid where cp.active =1 ";
		List<Map<String, Object>> list = baseJdbcDao.queryForList(sql, null);
		return list;
	}

	/**
	 * <p>功能描述：获取钢管信息。</p>
	  * <p> 葛建。</p>	
	  * @param pipeOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年10月24日 上午9:47:00。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getPipeInfo(String pipeOid) {
		String sql = "select pipe_length,pipe_diameter,wall_thickness from daq_material_pipe where active=1 and oid='"+pipeOid+"'";
		List<Map<String, Object>> list = baseJdbcDao.queryForList(sql, null);
		return list;
	}

}
