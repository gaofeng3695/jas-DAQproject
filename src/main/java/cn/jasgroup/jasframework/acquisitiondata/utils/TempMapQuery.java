package cn.jasgroup.jasframework.acquisitiondata.utils;

import cn.jasgroup.jasframework.base.data.BaseMapQuery;

public class TempMapQuery extends BaseMapQuery{

	public TempMapQuery(){
		super();
		super.setValue("className", TempMapQuery.class.getName());
	}
	
	/**
	 * 
	  *<p>功能描述：获取sql的策略编码。</p>
	  * <p> 张锡梓 </p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月6日 下午2:58:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public String getSqlStrategyCode() {
		return super.getString("sqlStrategyCode");
	}


	public void setSqlStrategyCode(String sqlStrategyCode) {
		super.setValue("sqlStrategyCode", sqlStrategyCode);
	}
}