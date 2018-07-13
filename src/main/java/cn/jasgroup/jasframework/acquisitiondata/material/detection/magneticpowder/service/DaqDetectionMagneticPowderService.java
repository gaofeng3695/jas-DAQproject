package cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.DaqDetectionMagneticPowderDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.DaqDetectionMagneticPowderSubDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.entity.DaqDetectionMagneticPowderSub;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.query.bo.DaqDetectionMagneticPowderBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

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
	 * @param oid	数据id
	 * @param approveStatus	审核状态
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午4:46:09。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(String oid, Integer approveStatus){
		return this.magneticPowderDao.approve(oid, approveStatus);
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
}
