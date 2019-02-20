package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.dao.entity.WeldMeasuredResult;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.WeldDao;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.unique.UniqueResult;

@Service
@Transactional
public class WeldService extends CommonDataHibernateService{

	@Resource
	private WeldDao weldDao;
	
	@Autowired
	private CommonDataJdbcService commonDataJdbcService;
	
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
	
	public List<Map<String,Object>> getDetectionInfoByWeldOid(String weldOid){
		return this.weldDao.getDetectionInfoByWeldOid(weldOid);
	}
	
	/**
	 * <p>功能描述：根据线路段查询审核状态为1和2的焊口列表(焊口表中未返修未割口且未测量的数据，返修表中未割口且未测量的数据)。</p>
	  * <p> 葛建。</p>	
	  * @param pipeSegmentOrCrossOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 上午10:18:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getWeldByCondition(String pipeSegmentOrCrossOid) {
		return weldDao.getWeldByCondition(pipeSegmentOrCrossOid);
	}
	
	/**
	 * <p>功能描述：中线测量若测量控制点为焊口，保存后修改焊口表中对应的is_measured字段。</p>
	  * <p> 葛建。</p>	
	  * @param weldMeasuredResult
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午5:00:57。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeWeldMeasuredFieldAfterSave(WeldMeasuredResult weldMeasuredResult){
		String weldOid = weldMeasuredResult.getWeldOid();
		if (StringUtils.isNotBlank(weldOid)) {
			weldDao.changeIsMeasuredField(weldOid,1);
		}
	}

	/**
	 * <p>功能描述：中线测量若测量控制点为焊口，更新前修改焊口表中对应的is_measured字段。</p>
	  * <p> 葛建。</p>	
	  * @param weldMeasuredResult
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午5:21:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeWeldMeasuredFieldBeforeUpdate(WeldMeasuredResult weldMeasuredResult){
		WeldMeasuredResult oldWeldMeasuredResult = (WeldMeasuredResult)commonDataJdbcService.get(WeldMeasuredResult.class,weldMeasuredResult.getOid());
		String weldOid = oldWeldMeasuredResult.getWeldOid();
		if (StringUtils.isNotBlank(weldOid)) {
			weldDao.changeIsMeasuredField(weldOid,0);
		}
	}

	/**
	 * <p>功能描述：中线测量若测量控制点为焊口，更新后修改焊口表中对应的is_measured字段。</p>
	  * <p> 葛建。</p>	
	  * @param weldMeasuredResult
	  * @since JDK1.8。
	  * <p>创建日期:2019年2月20日 下午5:21:04。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeWeldMeasuredFieldAfterUpdate(WeldMeasuredResult weldMeasuredResult){
		String weldOid = weldMeasuredResult.getWeldOid();
		if (StringUtils.isNotBlank(weldOid)) {
			weldDao.changeIsMeasuredField(weldOid,1);
		}
	}
	
	/**
	 * <p>功能描述：中线测量若测量控制点为焊口，删除后修改焊口表中对应的is_measured字段。</p>
	 * <p> 葛建。</p>	
	 * @param weldMeasuredResult
	 * @since JDK1.8。
	 * <p>创建日期:2019年2月20日 下午5:21:04。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeWeldMeasuredFieldAfterDelete(WeldMeasuredResult weldMeasuredResult){
		WeldMeasuredResult newWeldMeasuredResult = (WeldMeasuredResult)commonDataJdbcService.get(WeldMeasuredResult.class,weldMeasuredResult.getOid());
		String weldOid = newWeldMeasuredResult.getWeldOid();
		if (StringUtils.isNotBlank(weldOid)) {
			weldDao.changeIsMeasuredField(weldOid,0);
		}
	}
}
