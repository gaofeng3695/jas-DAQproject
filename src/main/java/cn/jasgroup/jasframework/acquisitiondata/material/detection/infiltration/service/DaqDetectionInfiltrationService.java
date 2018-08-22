package cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.DaqDetectionInfiltrationDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.DaqDetectionInfiltrationSubDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.entity.DaqDetectionInfiltration;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.entity.DaqDetectionInfiltrationSub;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query.bo.DaqDetectionInfiltrationBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

/**
 * @description 渗透检测service
 * @author zhangyi
 * @date 2018年7月11日下午4:41:56
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqDetectionInfiltrationService extends CommonDataJdbcService{

	@Autowired
	private DaqDetectionInfiltrationDao infiltrationDao;
	
	@Autowired
	private DaqDetectionInfiltrationSubDao infiltrationSubDao;
	
	/**
	 * <p>功能描述：审核。</p>
	 * <p>张毅 </p>	
	 * @param paramMap
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午4:46:09。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap){
		return this.infiltrationDao.approve(paramMap);
	}
	
	/**
	 * <p>功能描述：查询详情。</p>
	 * <p>张毅 </p>	
	 * @param oid	数据id
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午5:59:13。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public DaqDetectionInfiltrationBo getDetail(String oid){
		DaqDetectionInfiltrationBo infiltrationBo = this.infiltrationDao.get(oid);
		if(null != infiltrationBo){
			List<DaqDetectionInfiltrationSub> infiltrationSubList = this.infiltrationSubDao.getList(oid);
			infiltrationBo.setInfiltrationSubList(infiltrationSubList);
		}
		return infiltrationBo;
	}
	
	/**
	 * <p>功能描述：保存后改变焊口的状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:08:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChanageWledStatus(DaqDetectionInfiltration daqDetectionInfiltration){
		this.infiltrationDao.chanageWledStatus(daqDetectionInfiltration.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：修改前将原实体快照放入当前线程中。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionFaUltrasonic
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:19:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatusBeforeAdvice(DaqDetectionInfiltration daqDetectionInfiltration){
		DaqDetectionInfiltration oldDaqDetectionInfiltration = this.infiltrationDao.find(daqDetectionInfiltration.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldDaqDetectionInfiltration);
	}
	
	/**
	 * <p>功能描述：修改后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:24:38。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatus(DaqDetectionInfiltration daqDetectionInfiltration){
		//从当前线程获取原实体快照
		DaqDetectionInfiltration oldDaqDetectionInfiltration = (DaqDetectionInfiltration)BaseEntityThreadLocalHolder.getEntitySnap();
		//将原实体焊口状态改为未渗透检测
		this.infiltrationDao.chanageWledStatus(oldDaqDetectionInfiltration.getWeldOid(), 0);
		this.infiltrationDao.chanageWledStatus(daqDetectionInfiltration.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：删除后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionFaUltrasonic
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:28:14。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChanageWledStatus(DaqDetectionInfiltration daqDetectionInfiltration){
		DaqDetectionInfiltration olDaqDetectionInfiltration = infiltrationDao.find(daqDetectionInfiltration.getOid());
		this.infiltrationDao.chanageWledStatus(olDaqDetectionInfiltration.getWeldOid(), 0);
	}
}
