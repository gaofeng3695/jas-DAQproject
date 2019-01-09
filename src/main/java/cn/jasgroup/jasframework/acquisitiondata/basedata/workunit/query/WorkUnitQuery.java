package cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.service.bo.WorkUnitBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/***
  *<p>类描述：施工机组query。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年8月2日 上午10:10:38。</p>
 */
@QueryConfig(scene = "/workUnit/getPage", 
	resultClass = WorkUnitBo.class,
	queryBeforeProcess = {
			 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class WorkUnitQuery extends BaseJavaQuery{
	
	/**
	 * oid
	 */
	private String oid;

	/**
	 * 项目oid 
	 */
	private String projectOid;
	
	/**
	 * 机组名称 
	 */
	private String workUnitName;
	
	/**
	 * 机组编号 
	 */
	private String workUnitCode;
	
	@Override
	public String getQuerySql() {
		String sql = "select t.*,p.project_name,u.unit_name as construct_unit_name,s.code_name as work_unit_type_name "
				+ "from daq_work_unit t "
				+ "left join (select oid,project_name from daq_project) p on p.oid=t.project_oid "
				+ "left join (select oid,unit_name from pri_unit) u on u.oid=t.construct_unit "
				+ "left join (select code_id,code_name from sys_domain) s on s.code_id=t.work_unit_type "
				+ "where t.active=1 ";
			if(StringUtils.isNotBlank(oid)){
				sql += " and t.oid=:oid";
			}else{
				if(StringUtils.isNotBlank(projectOid)){
					sql += "and t.project_oid=:projectOid";
				}
				if(StringUtils.isNotBlank(workUnitName)){
					sql +=" and t.work_unit_name like :workUnitName";
				}
				if(StringUtils.isNotBlank(workUnitCode)){
					sql +=" and t.work_unit_code like :workUnitCode";
				}
				sql +=  this.dataAuthoritySql;
				sql +=" order by t.create_datetime desc";
			}
		return sql;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getWorkUnitName() {
		if(StringUtils.isNotBlank(workUnitName)){
			return "%"+workUnitName+"%";
		}
		return null;
	}

	public void setWorkUnitName(String workUnitName) {
		this.workUnitName = workUnitName;
	}

	public String getWorkUnitCode() {
		if(StringUtils.isNotBlank(workUnitCode)){
			return "%"+workUnitCode+"%";
		}
		return null;
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode;
	}
	
}
