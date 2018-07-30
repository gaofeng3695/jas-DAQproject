package cn.jasgroup.jasframework.acquisitiondata.variate;

public enum UnitHierarchyEnum {
	supervision_unit("Unit.0001.0004"),//监理单位
	construct_unit("Unit.0001.0005"),//施工单位
	detection_unit("Unit.0001.0006"),//检查单位
	project_unit("Unit.0001.0001"),//建设单位
	supplier("Unit.0001.0007");//厂商
	
	private final String hierarchy;

	private UnitHierarchyEnum(String hierarchy)
    {
        this.hierarchy = hierarchy;
    }
	
	public String getHierarchy() {
		return hierarchy;
	}
	
}
