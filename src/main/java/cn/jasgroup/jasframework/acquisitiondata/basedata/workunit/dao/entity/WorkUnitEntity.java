package cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.dao.entity;

import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

@CommonSaveConfig(
		scene = "/workUnit/save"
	)
@CommonUpdateConfig(
		scene = "/workUnit/update"
	)
@CommonDeleteConfig(
		scene = "/workUnit/delete"
	)
@UniqueConstraints(
	    strategys ={
	        @UniqueConstraintStrategy(columnNames={"projectOid","workUnitName"},name="机组名称已存在"),
	        @UniqueConstraintStrategy(columnNames={"projectOid","workUnitCode"},name="机组编号已存在"),
	    }
	)
@JdbcEntity(name="daq_work_unit")
public class WorkUnitEntity extends CommonJdbcEntity{
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

	/**
	 * 机组类型 
	 */
	private String workUnitType; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 

	/**
	 * 备注 
	 */
	private String remarks; 

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
		super.setField("projectOid");
	}

	public String getWorkUnitName() {
		return workUnitName; 
	}

	public void setWorkUnitName(String workUnitName) {
		this.workUnitName = workUnitName; 
		super.setField("workUnitName");
	}

	public String getWorkUnitCode() {
		return workUnitCode; 
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode; 
		super.setField("workUnitCode");
	}

	public String getWorkUnitType() {
		return workUnitType; 
	}

	public void setWorkUnitType(String workUnitType) {
		this.workUnitType = workUnitType; 
		super.setField("workUnitType");
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
		super.setField("constructUnit");
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}
}
