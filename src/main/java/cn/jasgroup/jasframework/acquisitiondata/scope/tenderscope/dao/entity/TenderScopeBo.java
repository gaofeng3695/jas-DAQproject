package cn.jasgroup.jasframework.acquisitiondata.scope.tenderscope.dao.entity;

public class TenderScopeBo {
	private String oid;
	private String projectOid;
	private String parentOid;
	private String name;
	private String type;
	private String typeName;
	private String province;
	private String provinceName;

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

	public String getParentOid() {
		return parentOid;
	}

	public void setParentOid(String parentOid) {
		this.parentOid = parentOid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	@Override
	public String toString() {
		return "[oid:" + oid + ", projectOid:" + projectOid + ", parentOid:" + parentOid + ", name:"
				+ name + ", type:" + type + ", typeName:" + typeName + ", province:" + province + ", provinceName:"
				+ provinceName + "]";
	}

}
