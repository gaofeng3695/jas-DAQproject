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

	/**
	  * <p>功能描述：获取施工单位列表。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param types
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月2日 上午11:29:41。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
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
	/***
	  * <p>功能描述：获取施工机组列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:37:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByCondition() {
		return workUnitDao.getListByCondition();
	}
}
