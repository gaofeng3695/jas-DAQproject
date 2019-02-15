package cn.jasgroup.jasframework.acquisitiondata.dataapprove.dao.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.engine.hibernate.entity.CommonHibernateEntity;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/***
 * 
  *<p>类描述：。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月21日 下午4:22:30。</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.dataapprove.service.DataApproveService #changeBusinessApproveStatus()}
 */
@CommonSaveConfig(
		beforeAdvice={
				@Process(service = "dataApproveService", method = "changeBusinessApproveStatus()")
			}
	)
@Entity(name="daq_data_approve")
public class DataApprove extends CommonHibernateEntity {

	/**
	 * 业务oid 
	 */
	private String businessOid; 

	/**
	 * 审批意见 
	 */
	private String approveOpinion; 

	/**
	 * 审核状态 
	 */
	private Integer approveStatus; 

	/***
	 * 模块oid,自定义表单时使用 
	 */
	private String functionCode;
	
	/***
	 * 实体类路径
	 */
	private String className;
	
	private String privilegeCode;
	
	@Column(name="business_oid")
	public String getBusinessOid() {
		return businessOid; 
	}

	public void setBusinessOid(String businessOid) {
		this.businessOid = businessOid; 
		super.setField("businessOid");
	}

	@Column(name="approve_opinion")
	public String getApproveOpinion() {
		return approveOpinion; 
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion; 
		super.setField("approveOpinion");
	}

	@Column(name="approve_status")
	public Integer getApproveStatus() {
		return approveStatus; 
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus; 
		super.setField("approveStatus");
	}

	@Transient
	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	@Transient
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Transient
	public String getPrivilegeCode() {
		return privilegeCode;
	}

	public void setPrivilegeCode(String privilegeCode) {
		this.privilegeCode = privilegeCode;
	}
	
	
}