package cn.jasgroup.jasframework.acquisitiondata.scope.tenders.scopeenum;

/***
  * 
  *<p>类描述：工程分类枚举类。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年6月22日 下午3:24:03。</p>
 */
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
