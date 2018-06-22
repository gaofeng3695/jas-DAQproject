package cn.jasgroup.jasframework.acquisitiondata.scope.tenders.scopeenum;

public enum ScopeEnum {
	segment_scope("线路工程"),
	cross_scope("穿跨越工程"),
	pipe_station_scope("站场/阀室工程"),
	maintenance_road_scope("伴行道路工程"),
	power_line_scope("外电工程");
    
    private final String name;
    
    private ScopeEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
