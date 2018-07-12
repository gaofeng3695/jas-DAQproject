package cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.DaqDetectionPaUltrasonicDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.query.bo.DaqDetectionPaUltrasonicBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

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
		return this.paUltrasonicDao.approve(oid, approveStatus);
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
		return this.paUltrasonicDao.get(oid);
	}
}
