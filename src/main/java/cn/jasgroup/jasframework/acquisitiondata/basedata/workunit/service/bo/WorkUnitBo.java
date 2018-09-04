package cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.service.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

public class WorkUnitBo extends CommonBaseBo{
	/**
	 * oid
	 */
	private String oid;
	/**
	 * 项目oid 
	 */
	private String projectOid; 
	
	/**
	 * 项目名称
	 */
	private String projectName; 

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
	 * 机组类型名称
	 */
	private String workUnitTypeName; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 
	
	/***
	 * 施工单位名称
	 */
	private String constructUnitName;
	/**
	 * 备注 
	 */
	private String remarks; 

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
	}

	public String getWorkUnitName() {
		return workUnitName; 
	}

	public void setWorkUnitName(String workUnitName) {
		this.workUnitName = workUnitName; 
	}

	public String getWorkUnitCode() {
		return workUnitCode; 
	}

	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode; 
	}

	public String getWorkUnitType() {
		return workUnitType; 
	}

	public void setWorkUnitType(String workUnitType) {
		this.workUnitType = workUnitType; 
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getWorkUnitTypeName() {
		return workUnitTypeName;
	}

	public void setWorkUnitTypeName(String workUnitTypeName) {
		this.workUnitTypeName = workUnitTypeName;
	}

	public String getConstructUnitName() {
		return constructUnitName;
	}

	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}
}
