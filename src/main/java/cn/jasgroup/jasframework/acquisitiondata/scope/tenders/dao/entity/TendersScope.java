package cn.jasgroup.jasframework.acquisitiondata.scope.tenders.dao.entity;

import javax.persistence.Entity;

import cn.jasgroup.jasframework.engine.hibernate.entity.CommonHibernateEntity;

@Entity(name="daq_tenders_scope_ref")
public class TendersScope extends CommonHibernateEntity{
	/** 管线oid */
	private String pipelineOid;
	
	/** 标段oid */
	private String tendersOid; 

	/** 范围oid（即线路段oid或者站场oid等） */
	private String scopeOid; 

	/** 范围类型（1：线路段，2：创跨越，3：站场/阀室，4：伴行道路，5：外供电线路） */
	private String scopeType;

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
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

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}
	
}
