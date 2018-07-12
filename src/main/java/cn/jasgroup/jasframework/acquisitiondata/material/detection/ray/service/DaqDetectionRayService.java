package cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query.bo.DaqDetectionInfiltrationBo;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.dao.DaqDetectionRayDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ray.query.bo.DaqDetectionRayBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

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
	
	/**
	 * <p>功能描述：审核。</p>
	 * <p>张毅 </p>	
	 * @param oid	数据id
	 * @param approveStatus	审核状态
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午4:46:09。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(String oid, Integer approveStatus){
		return this.rayDao.approve(oid, approveStatus);
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
		return this.rayDao.get(oid);
	}
}
