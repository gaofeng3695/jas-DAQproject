package cn.jasgroup.jasframework.acquisitiondata.utils;

import java.lang.reflect.Method;
import java.util.List;

import cn.jasgroup.jasframework.base.data.MapQuery;
import cn.jasgroup.jasframework.query.ListQueryContainer;
import cn.jasgroup.jasframework.support.process.CommonProcess;
import cn.jasgroup.jasframework.utils.InvokeSupportUtils;

public class VariateInjectUtils {
	
	public static String invoke(String sql){
		MapQuery paramArray = new MapQuery();
		paramArray.setSql(sql);
		try {
			List<CommonProcess> CommonProcessList = ListQueryContainer.getQueryBeforeProcessList("F000024");
			for(CommonProcess commonProcess:CommonProcessList){
				Object precessService = commonProcess.getServiceObject();
				Method method = commonProcess.getMethodObject();
				InvokeSupportUtils.invokMethod(method, precessService, paramArray);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paramArray.getSql();
	}
}
