package cn.jasgroup.jasframework.acquisitiondata.utils;

import java.lang.reflect.Method;
import java.util.List;

import cn.jasgroup.jasframework.query.ListQueryContainer;
import cn.jasgroup.jasframework.support.ConditionExpressionUtil;
import cn.jasgroup.jasframework.support.process.CommonProcess;
import cn.jasgroup.jasframework.utils.InvokeSupportUtils;

public class VariateInjectUtils {
	
	public static String invoke(String sql){
		TempMapQuery paramArray = new TempMapQuery();
		paramArray.setSql(sql);
		try {
			List<CommonProcess> CommonProcessList = ListQueryContainer.getQueryBeforeProcessList("");
			for(CommonProcess commonProcess:CommonProcessList){
				Object precessService = commonProcess.getServiceObject();
				Method method = commonProcess.getMethodObject();
				boolean needProcesse = ConditionExpressionUtil.needExecute(paramArray, commonProcess);
				if(!needProcesse){
					continue;
				}else{
					InvokeSupportUtils.invokMethod(method, precessService, paramArray);
					break;
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return paramArray.getSql();
	}
}
