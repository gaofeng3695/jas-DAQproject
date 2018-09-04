package cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.dao.entity.DaqDetectionRay;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.dao.DaqDetectionUltrasonicDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.dao.DaqDetectionUltrasonicSubDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.dao.entity.DaqDetectionUltrasonic;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.dao.entity.DaqDetectionUltrasonicSub;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query.bo.DaqDetectionUltrasonicBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

/**
 * @description 超声波检测
 * @author zhangyi
 * @date 2018年7月11日下午7:01:55
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqDetectionUltrasonicService extends CommonDataJdbcService{
	
	@Autowired
	private DaqDetectionUltrasonicDao ultrasonicDao;
	
	@Autowired
	private DaqDetectionUltrasonicSubDao ultrasonicSubDao;
	
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
		return this.ultrasonicDao.approve(paramMap);
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
	public DaqDetectionUltrasonicBo get(String oid){
		DaqDetectionUltrasonicBo ultrasonicBo =  this.ultrasonicDao.get(oid);
		if(null != ultrasonicBo){
			List<DaqDetectionUltrasonicSub> ultrasonicSubList = this.ultrasonicSubDao.getList(oid);
			ultrasonicBo.setUltrasonicSubList(ultrasonicSubList);
		}
		return ultrasonicBo;
	}
	
	/**
	 * <p>功能描述：保存后改变焊口的状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:32:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChanageWledStatus(DaqDetectionUltrasonic daqDetectionUltrasonic){
		this.ultrasonicDao.chanageWledStatus(daqDetectionUltrasonic.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：修改前将原实体快照放入当前线程中。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatusBeforeAdvice(DaqDetectionUltrasonic daqDetectionUltrasonic){
		DaqDetectionUltrasonic oldDaqDetectionUltrasonic = this.ultrasonicDao.find(daqDetectionUltrasonic.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldDaqDetectionUltrasonic);
	}
	
	/**
	 * <p>功能描述：修改后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatus(DaqDetectionUltrasonic daqDetectionUltrasonic){
		//从当前线程获取原实体快照
		DaqDetectionUltrasonic oldDaqDetectionUltrasonic = (DaqDetectionUltrasonic)BaseEntityThreadLocalHolder.getEntitySnap();
		//将原实体焊口状态改为未渗透检测
		this.ultrasonicDao.chanageWledStatus(oldDaqDetectionUltrasonic.getWeldOid(), 0);
		this.ultrasonicDao.chanageWledStatus(daqDetectionUltrasonic.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：删除后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChanageWledStatus(DaqDetectionUltrasonic daqDetectionUltrasonic){
		DaqDetectionUltrasonic oldDaqDetectionUltrasonic = ultrasonicDao.find(daqDetectionUltrasonic.getOid());
		this.ultrasonicDao.chanageWledStatus(oldDaqDetectionUltrasonic.getWeldOid(), 0);
	}
}
