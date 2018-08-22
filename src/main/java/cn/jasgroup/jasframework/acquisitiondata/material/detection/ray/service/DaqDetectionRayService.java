package cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.entity.DaqDetectionPaUltrasonic;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.dao.DaqDetectionRayDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.dao.DaqDetectionRaySubDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.dao.entity.DaqDetectionRay;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.dao.entity.DaqDetectionRaySub;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.query.bo.DaqDetectionRayBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

/**
 * @description 射线检测service
 * @author zhangyi
 * @date 2018年7月11日下午6:43:02
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqDetectionRayService extends CommonDataJdbcService{

	@Autowired
	private DaqDetectionRayDao rayDao;
	
	@Autowired
	private DaqDetectionRaySubDao raySubDao;
	
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
		return this.rayDao.approve(paramMap);
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
	public DaqDetectionRayBo get(String oid){
		DaqDetectionRayBo rayBo = this.rayDao.get(oid);
		if(null != rayBo){
			List<DaqDetectionRaySub> raySubList = raySubDao.getList(oid);
			rayBo.setRaySubList(raySubList);
		}
		return rayBo;
	}
	
	/**
	 * <p>功能描述：保存后改变焊口的状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:32:59。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChanageWledStatus(DaqDetectionRay daqDetectionRay){
		this.rayDao.chanageWledStatus(daqDetectionRay.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：修改前将原实体快照放入当前线程中。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:13。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageWledStatusBeforeAdvice(DaqDetectionRay daqDetectionRay){
		DaqDetectionRay oldDaqDetectionRay = this.rayDao.find(daqDetectionRay.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldDaqDetectionRay);
	}
	
	/**
	 * <p>功能描述：修改后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:25。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。< /p>
	 */
	public void updateChanageWledStatus(DaqDetectionRay daqDetectionRay){
		//从当前线程获取原实体快照
		DaqDetectionRay oldDaqDetectionRay = (DaqDetectionRay)BaseEntityThreadLocalHolder.getEntitySnap();
		//将原实体焊口状态改为未渗透检测
		this.rayDao.chanageWledStatus(oldDaqDetectionRay.getWeldOid(), 0);
		this.rayDao.chanageWledStatus(daqDetectionRay.getWeldOid(), 1);
	}
	
	/**
	 * <p>功能描述：删除后改变焊口状态。</p>
	  * <p> 葛建。</p>	
	  * @param daqDetectionInfiltration
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:33:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChanageWledStatus(DaqDetectionRay daqDetectionRay){
		DaqDetectionRay olDaqDetectionRay = rayDao.find(daqDetectionRay.getOid());
		this.rayDao.chanageWledStatus(olDaqDetectionRay.getWeldOid(), 0);
	}
}
