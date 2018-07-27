package cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.basedata.workunit.dao.WorkUnitDao;

@Service
@Transactional
public class WorkUnitService {

	@Autowired
	private WorkUnitDao workUnitDao;

	public List<Map<String, Object>> getListByCondition(String projectOid, String types) {
		String str = "";
		String[] typeArray=null;
		if(types.indexOf(",")>-1){
			typeArray = types.split(",");
		}else if(types.indexOf(";")>-1){
			typeArray = types.split(";");
		}
		if(typeArray==null){
			return new ArrayList<Map<String, Object>>();
		}
		for(int i=0; i<typeArray.length; i++){
			if(i == typeArray.length-1){
				str += "'"+typeArray[i]+"'";
			}else{
				str += "'"+typeArray[i]+"',";
			}
		}
		return workUnitDao.getListByCondition(projectOid, str);
	}
}
