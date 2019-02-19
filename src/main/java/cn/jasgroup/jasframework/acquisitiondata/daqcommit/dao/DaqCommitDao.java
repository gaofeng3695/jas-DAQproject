package cn.jasgroup.jasframework.acquisitiondata.daqcommit.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

@Repository
public class DaqCommitDao {
	
	@Autowired
	private BaseJdbcTemplate baseJdbcTemplate;

	/**
	 * <p>功能描述：修改functionCode或者className中对应oids的提交状态。</p>
	  * <p> 葛建。</p>	
	  * @param object
	  * @param functionCode
	  * @param businessOids
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月18日 下午5:24:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeBussinessCommitStatus(String tableName, String functionCode, String oids) {
		// 若表名为空，则为自定义表单那操作
		if (StringUtils.isBlank(tableName)) {
			// 根据functionCode查出自定义表单对应的表名
			String selectSql = "select t.table_name from custom_fun_function t where t.function_code=?";
			List<Map<String, Object>> dataList = baseJdbcTemplate.queryForListHump(selectSql, new Object[]{functionCode});
			if(dataList.size()>0){
				Object tableNameObj = dataList.get(0).get("tableName");
				tableName = tableNameObj !=null ? tableNameObj.toString() : "";
			}
		}
		String updateSql = "update "+tableName+" set commit_status=1 where oid in ("+ oids +")";
		baseJdbcTemplate.update(updateSql, new Object[]{});
	}

}

