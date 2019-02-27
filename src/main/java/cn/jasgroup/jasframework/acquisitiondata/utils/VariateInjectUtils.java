package cn.jasgroup.jasframework.acquisitiondata.utils;

import java.lang.reflect.Method;

import cn.jasgroup.jasframework.base.data.MapQuery;
import cn.jasgroup.jasframework.utils.InvokeSupportUtils;

public class VariateInjectUtils {
	
	public static String invoke(String sql){
		MapQuery paramArray = new MapQuery();
		paramArray.setSql(sql);
		try {
			Class<?> clz = Class.forName("cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService");
			Object object = clz.newInstance();
			Method method = clz.getMethod("privilegeStrategySql", MapQuery.class);
			InvokeSupportUtils.invokMethod(method, object, paramArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paramArray.getSql();
	}
}
