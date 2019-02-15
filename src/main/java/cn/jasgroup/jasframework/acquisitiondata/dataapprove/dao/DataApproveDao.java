package cn.jasgroup.jasframework.acquisitiondata.dataapprove.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;
import cn.jasgroup.jasframework.utils.DateTimeUtil;

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
	
	
	/***
	  * <p>功能描述：新增或者修改待审核数据。</p>
	  * <p> 雷凯。</p>	
	  * @param tableName
	  * @param functionCode
	  * @param businessOid
	  * @param approveStatus
	  * @param privilegeCode
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月15日 上午11:16:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveOrUpdateApproveTipInfo(String tableName, String functionCode, String businessOid, Integer approveStatus, String privilegeCode){
		if(StringUtils.isBlank(tableName)){
			String selectSql = "select t.table_name from custom_fun_function t where t.function_code=?";
			List<Map<String, Object>> dataList = baseJdbcTemplate.queryForListHump(selectSql, new Object[]{functionCode});
			if(dataList.size()>0){
				Object tableNameObj = dataList.get(0).get("tableName");
				tableName = tableNameObj !=null ? tableNameObj.toString() : "";
			}
		}
		String sql = null;
		if(approveStatus==1){
			String oid = UUID.randomUUID().toString();
			String userId = ThreadLocalHolder.getCurrentUserId();
			String userName = ThreadLocalHolder.getCurrentUserName();
			String dateTime = DateTimeUtil.getFormatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
			sql = "insert into daq_approve_tip(oid,project_oid,business_oid,privilege_code,supervision_unit,create_user_id,create_user_name,create_datetime) "
					+ "select '"+oid+"',project_oid,oid,'"+privilegeCode+"',supervision_unit,'"+userId+"','"+userName+"',to_timestamp('"+dateTime+"', 'yyyy-MM-dd hh24:mi:ss') from "+tableName+" where oid=?;";
		}else{
//			sql = "update daq_approve_tip set active=0 where business_oid=?;";
			sql = "delete from daq_approve_tip where business_oid=?;";
		}
		baseJdbcTemplate.batchExecute(sql, new Object[]{businessOid});
	}
	
	/***
	  * <p>功能描述：获取权限树对应的为审核的数据。</p>
	  * <p> 雷凯。</p>	
	  * @param supervisionUnitOid
	  * @param unitType
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月15日 下午5:21:46。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> queryUnauditedInfo(String supervisionUnitOid,Integer unitType){
		String sql = null;
		if(unitType==1){
			sql = "with recursive daq_approve_tip_test(oid,parent_id,privilege_code) as ("
					+ "select oid,parent_id,privilege_code from v_daq_approve_tip "
						+ "where supervision_unit ='"+supervisionUnitOid+"' "
						+ "and project_oid in (select distinct p.oid from daq_implement_scope_ref s left join (select oid from daq_project where active=1) p on s.project_oid=p.oid where s.unit_oid in(select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+supervisionUnitOid+"')) "
					+ "union all "
					+ "select t.oid,t.parent_id,t.privilege_code from pri_func_privilege t inner join daq_approve_tip_test a on t.oid=a.parent_id where t.parent_id is not null"
				+ ")"
				+ "select privilege_code,count(*) as number from daq_approve_tip_test group by privilege_code";
		}else{
			sql = "with recursive daq_approve_tip_test(oid,parent_id,privilege_code) as ("
					+ "select oid,parent_id,privilege_code from v_daq_approve_tip "
						+ "where project_oid in (select distinct p.oid from daq_implement_scope_ref s left join (select oid from daq_project where active=1) p on s.project_oid=p.oid where s.unit_oid in(select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+supervisionUnitOid+"')) "
					+ "union all "
					+ "select t.oid,t.parent_id,t.privilege_code from pri_func_privilege t inner join daq_approve_tip_test a on t.oid=a.parent_id where t.parent_id is not null"
				+ ")"
				+ "select privilege_code,count(*) as number from daq_approve_tip_test group by privilege_code";
		}
		return baseJdbcTemplate.queryForList(sql, null,Map.class);
	}
}
