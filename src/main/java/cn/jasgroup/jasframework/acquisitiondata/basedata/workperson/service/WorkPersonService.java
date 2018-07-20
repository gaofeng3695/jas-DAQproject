package cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.dao.WorkPersonDao;

@Service
@Transactional
public class WorkPersonService {

	@Autowired
	private WorkPersonDao workPersonDao;

	public List<Map<String, Object>> getListByCondition(String workUnitOid, String types) {
		List<Map<String, Object>> result = new ArrayList<>();
		if (StringUtils.isNotBlank(types)) {
			String str = new String();
			String[] typeArray = types.split(",");
			if (typeArray.length == 1) {
				str = typeArray[0];
			}else{
				for (int i = 0; i < typeArray.length-1; i++) {
					str += "'"+typeArray[i]+"',";
				}
				str += "'"+typeArray[typeArray.length-1]+"'";
			}
			return workPersonDao.getListByCondition(workUnitOid, str);
		}else {
			return workPersonDao.getPersonByWorkUnit(workUnitOid);
		}
		
	}
}
