package cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.entity.DaqDetectionMagneticPowder;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.DaqDetectionPaUltrasonicDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.DaqDetectionPaUltrasonicSubDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.entity.DaqDetectionPaUltrasonic;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.entity.DaqDetectionPaUltrasonicSub;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.query.bo.DaqDetectionPaUltrasonicBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

/**
 * @description 相控阵超声波service
 * @author zhangyi
 * @date 2018年7月12日上午11:14:23
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqDetectionPaUltrasonicService extends CommonDataJdbcService {

	@Autowired
	private DaqDetectionPaUltrasonicDao paUltrasonicDao;
	
	@Autowired
	private DaqDetectionPaUltrasonicSubDao paUltrasonicSubDao;
	
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
		return this.paUltrasonicDao.approve(paramMap);
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
	public DaqDetectionPaUltrasonicBo get(String oid){
		DaqDetectionPaUltrasonicBo paUltrasonicBo = this.paUltrasonicDao.get(oid);
		if(null != paUltrasonicBo){
			List<DaqDetectionPaUltrasonicSub> paUltrasonicSubList = this.paUltrasonicSubDao.getList(oid);
			paUltrasonicBo.setPaUltrasonicSubList(paUltrasonicSubList);
		}
		return paUltrasonicBo;
	}
	
	/**
	 * <p>功能描述：保存后改变焊口的状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:32:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChanageWledStatus(DaqDetectionPaUltrasonic daqDetectionPaUltrasonic){
		this.paUltrasonicDao.chanageWledStatus(daqDetectionPaUltrasonic.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：修改前将原实体快照放入当前线程中。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatusBeforeAdvice(DaqDetectionPaUltrasonic daqDetectionPaUltrasonic){
		DaqDetectionPaUltrasonic oldDaqDetectionPaUltrasonic = this.paUltrasonicDao.find(daqDetectionPaUltrasonic.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldDaqDetectionPaUltrasonic);
	}
	
	/**
	 * <p>功能描述：修改后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatus(DaqDetectionPaUltrasonic daqDetectionPaUltrasonic){
		//从当前线程获取原实体快照
		DaqDetectionPaUltrasonic oldDaqDetectionPaUltrasonic = (DaqDetectionPaUltrasonic)BaseEntityThreadLocalHolder.getEntitySnap();
		//将原实体焊口状态改为未渗透检测
		this.paUltrasonicDao.chanageWledStatus(oldDaqDetectionPaUltrasonic.getWeldOid(), 0);
		this.paUltrasonicDao.chanageWledStatus(daqDetectionPaUltrasonic.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：删除后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChanageWledStatus(DaqDetectionPaUltrasonic daqDetectionPaUltrasonic){
		DaqDetectionPaUltrasonic olDaqDetectionPaUltrasonic = paUltrasonicDao.find(daqDetectionPaUltrasonic.getOid());
		this.paUltrasonicDao.chanageWledStatus(olDaqDetectionPaUltrasonic.getWeldOid(), 0);
	}
}
