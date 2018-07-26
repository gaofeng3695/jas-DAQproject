package cn.jasgroup.jasframework.acquisitiondata.dataapprove.service.bo;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

public class DataApproveBo extends CommonBaseBo{
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
	/**
	 * 审核状态
	 */
	private String  approveStatusName;
	public String getBusinessOid() {
		return businessOid;
	}
	public void setBusinessOid(String businessOid) {
		this.businessOid = businessOid;
	}
	public String getApproveOpinion() {
		return approveOpinion;
	}
	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}
	public Integer getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getApproveStatusName() {
		return approveStatusName;
	}
	public void setApproveStatusName(String approveStatusName) {
		this.approveStatusName = approveStatusName;
	}
	
}
