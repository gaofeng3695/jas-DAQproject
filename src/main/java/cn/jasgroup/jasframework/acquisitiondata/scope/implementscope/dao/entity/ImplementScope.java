package cn.jasgroup.jasframework.acquisitiondata.scope.implementscope.dao.entity;

import javax.persistence.Entity;

import cn.jasgroup.jasframework.engine.hibernate.entity.CommonHibernateEntity;

@Entity(name="daq_implement_scope_ref")
public class ImplementScope extends CommonHibernateEntity{
	/** 部门oid */
	private String unitOid; 

	/** 项目oid */
	private String projectOid; 

	/** 管线oid */
	private String pipelineOid; 
	
	/** 标段id **/
	private String tendersOid; 

	/** 实体oid（即线路段oid或者站场oid等） */
	private String scopeOid; 

	/** 实体类型（1：线路段，2：创跨越，3：站场/阀室，4：伴行道路，5：外供电线路） */
	private String scopeType;

	public String getUnitOid() {
		return unitOid;
	}

	public void setUnitOid(String unitOid) {
		this.unitOid = unitOid;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getScopeOid() {
		return scopeOid;
	}

	public void setScopeOid(String scopeOid) {
		this.scopeOid = scopeOid;
	}

	public String getScopeType() {
		return scopeType;
	}

	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	} 
	
}
