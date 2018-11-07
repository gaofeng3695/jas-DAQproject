package cn.jasgroup.jasframework.acquisitiondata.dataapprove.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

@Repository
public class DataApproveDao {
	
	@Resource
	private BaseJdbcTemplate baseJdbcTemplate;
	
	/***
	  * <p>功能描述：更改业务数据的审批状态。</p>
	  * <p> 雷凯。</p>	
	  * @param functionCode 自定义表单模块code
	  * @param businessOid 业务数据oid
	  * @param approveStatus 审批状态
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月21日 下午5:23:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeBusinessApproveStatus(String tableName, String functionCode, String businessOid, Integer approveStatus){
		if(StringUtils.isBlank(tableName)){
			String selectSql = "select t.table_name from custom_fun_function t where t.function_code=?";
			List<Map<String, Object>> dataList = baseJdbcTemplate.queryForListHump(selectSql, new Object[]{functionCode});
			if(dataList.size()>0){
				Object tableNameObj = dataList.get(0).get("tableName");
				tableName = tableNameObj !=null ? tableNameObj.toString() : "";
			}
		}
		String updateSql = "update "+tableName+" set approve_status=? where oid=?";
		baseJdbcTemplate.update(updateSql, new Object[]{approveStatus,businessOid});
	}
	public void chanageOriginalPipeUseState(String tableName,String businessOid){
		String sql = "update daq_material_pipe set is_cold_bend=1,is_use=1 where oid=(select pipe_oid from "+tableName+" where oid=?)";
		baseJdbcTemplate.update(sql, new Object[]{businessOid});   
	}
}
