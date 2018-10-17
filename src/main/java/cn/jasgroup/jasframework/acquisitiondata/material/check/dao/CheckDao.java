package cn.jasgroup.jasframework.acquisitiondata.material.check.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class CheckDao extends BaseJdbcDao{
	
	public List<Map<String,Object>> getManufacturerCode(){
		String sql = "SELECT oid as key,manufacturer_code as value FROM daq_material_insulated_joint where active=1";
		return this.queryForList(sql, null);
	}
}
