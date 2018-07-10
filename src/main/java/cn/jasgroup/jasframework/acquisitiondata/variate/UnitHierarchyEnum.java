package cn.jasgroup.jasframework.acquisitiondata.variate;

public enum UnitHierarchyEnum {
	supervision_unit("Unit.0001.0004"),
	construct_unit("Unit.0001.0005"),
	detection_unit("Unit.0001.0006"),
	supplier("Unit.0001.0007");
	
	private final String hierarchy;

	private UnitHierarchyEnum(String hierarchy)
    {
        this.hierarchy = hierarchy;
    }
	
	public String getHierarchy() {
		return hierarchy;
	}
	
}
