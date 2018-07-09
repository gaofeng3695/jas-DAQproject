package cn.jasgroup.jasframework.acquisitiondata.variate;

public enum UnitHierarchyEnum {
	construct_unit("Unit.0001.0005"),
	detection_unit("Unit.0001.0006"),
	supervision_unit("Unit.0001.0004");
	
	private final String hierarchy;

	private UnitHierarchyEnum(String hierarchy)
    {
        this.hierarchy = hierarchy;
    }
	
	public String getHierarchy() {
		return hierarchy;
	}
	
}
