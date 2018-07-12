package cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.dao.DaqDetectionUltrasonicDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.ultrasonic.query.bo.DaqDetectionUltrasonicBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

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
		return this.ultrasonicDao.approve(oid, approveStatus);
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
		return this.ultrasonicDao.get(oid);
	}
	
}
