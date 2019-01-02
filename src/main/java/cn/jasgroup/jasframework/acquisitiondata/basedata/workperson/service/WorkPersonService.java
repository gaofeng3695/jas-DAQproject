package cn.jasgroup.jasframework.acquisitiondata.basedata.workperson.service;

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
	/**
	  * <p>功能描述：根据施工机组oid和人员类型获取机组人员。</p>
	  * <p> 雷凯。</p>	
	  * @param workUnitOid
	  * @param types
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:33:23。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByCondition(String workUnitOid, String types) {
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
	/**
	  * <p>功能描述：获取机组人员列表。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年9月10日 下午3:34:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getListByCondition() {
		return workPersonDao.getPersonByWorkUnit();
	}
	
	/**
	 * <p>功能描述：查询机组下的焊工列表。</p>
	  * <p> 葛建。</p>	
	  * @param workUnitOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年1月2日 上午11:37:49。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getWeldersList(String workUnitOid) {
		return workPersonDao.getWeldersList(workUnitOid);
	}
}
