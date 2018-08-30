package cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.entity.DaqDetectionInfiltration;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.DaqDetectionMagneticPowderDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.DaqDetectionMagneticPowderSubDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.entity.DaqDetectionMagneticPowder;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.entity.DaqDetectionMagneticPowderSub;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.query.bo.DaqDetectionMagneticPowderBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

/**
 * @description 磁粉检测service
 * @author zhangyi
 * @date 2018年7月12日上午9:50:30
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqDetectionMagneticPowderService extends CommonDataJdbcService{

	@Autowired
	private DaqDetectionMagneticPowderDao magneticPowderDao;
	
	@Autowired
	private DaqDetectionMagneticPowderSubDao magneticPowderSubDao;
	
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
		return this.magneticPowderDao.approve(paramMap);
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
	public DaqDetectionMagneticPowderBo get(String oid){
		DaqDetectionMagneticPowderBo magneticPowderBo = this.magneticPowderDao.get(oid);
		if(null != magneticPowderBo){
			List<DaqDetectionMagneticPowderSub> powderSubList = this.magneticPowderSubDao.getList(oid);
			magneticPowderBo.setPowderSubList(powderSubList);
		}
		return magneticPowderBo;
	}
	

	/**
	 * <p>功能描述：保存后改变焊口的状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:32:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChanageWledStatus(DaqDetectionMagneticPowder daqDetectionMagneticPowder){
		this.magneticPowderDao.chanageWledStatus(daqDetectionMagneticPowder.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：修改前将原实体快照放入当前线程中。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatusBeforeAdvice(DaqDetectionMagneticPowder daqDetectionMagneticPowder){
		DaqDetectionMagneticPowder oldDaqDetectionMagneticPowder = this.magneticPowderDao.find(daqDetectionMagneticPowder.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldDaqDetectionMagneticPowder);
	}
	
	/**
	 * <p>功能描述：修改后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatus(DaqDetectionMagneticPowder daqDetectionMagneticPowder){
		//从当前线程获取原实体快照
		DaqDetectionMagneticPowder oldDaqDetectionMagneticPowder = (DaqDetectionMagneticPowder)BaseEntityThreadLocalHolder.getEntitySnap();
		//将原实体焊口状态改为未渗透检测
		this.magneticPowderDao.chanageWledStatus(oldDaqDetectionMagneticPowder.getWeldOid(), 0);
		this.magneticPowderDao.chanageWledStatus(daqDetectionMagneticPowder.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：删除后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChanageWledStatus(DaqDetectionMagneticPowder daqDetectionMagneticPowder){
		DaqDetectionMagneticPowder olDaqDetectionMagneticPowder = magneticPowderDao.find(daqDetectionMagneticPowder.getOid());
		this.magneticPowderDao.chanageWledStatus(olDaqDetectionMagneticPowder.getWeldOid(), 0);
	}
}
