package cn.jasgroup.jasframework.acquisitiondata.material.check.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

@Repository
public class CheckDao extends BaseJdbcDao{
	
	/**
	 * <p>功能描述：获取绝缘接头的出厂编号。</p>
	  * <p> 葛建。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年10月17日 下午3:29:35。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getManufacturerCode(){
		String sql = "SELECT oid as key,manufacturer_code as value FROM daq_material_insulated_joint where active=1";
		return this.queryForList(sql, null);
	}
}
