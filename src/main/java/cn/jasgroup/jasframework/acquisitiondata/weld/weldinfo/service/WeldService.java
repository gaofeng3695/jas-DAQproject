package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.WeldDao;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.unique.UniqueResult;

@Service
@Transactional
public class WeldService extends CommonDataHibernateService{

	@Resource
	private WeldDao weldDao;
	
	/***
	  * <p>功能描述：获取焊口列表（焊口+返修-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:12:10。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getWeldList(String pipeSegmentOrCrossOid){
		return this.weldDao.getWeldList(pipeSegmentOrCrossOid);
	}
	/***
	  * <p>功能描述：获取焊口列表（焊口-返修-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:12:17。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getOnlyWeldList(String pipeSegmentOrCrossOid){
		return this.weldDao.getOnlyWeldList(pipeSegmentOrCrossOid);
	}
	/***
	 * <p>功能描述：获取焊口列表（焊口+返修前焊口+返修后焊口-割口）。</p>
	  * <p> 雷凯。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午2:12:50。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getAllWeldList(String pipeSegmentOrCrossOid,String detectionType){
		return this.weldDao.getAllWeldList(pipeSegmentOrCrossOid,detectionType);
	}
	
	
	
  	/**
  	 * <p>功能描述：唯一性校验提示信息。</p>
  	  * <p> 葛建。</p>	
  	  * @param uniqueResultList
  	  * @return
  	  * @since JDK1.8。
  	  * <p>创建日期:2018年10月18日 上午10:52:20。</p>
  	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
  	 */
	public String formatting(List<UniqueResult> uniqueResultList){
		String msg = "";
		String name = "";
		for(UniqueResult uniqueResult : uniqueResultList){
			if(StringUtils.isNotBlank(uniqueResult.getName())){
				name = uniqueResult.getName();
			}else{
				name = uniqueResult.getFieldName();
			}
			//获取对应的oid,查出对应的code
			String weldCode = "";
			if (StringUtils.isNotBlank(uniqueResult.getValue())) {
				weldCode = weldDao.getWeldCodeByOid(uniqueResult.getValue());
			}
			msg +=name+"重复:"+weldCode+"<br>"; 		
		}	
		return msg;
	}
}
