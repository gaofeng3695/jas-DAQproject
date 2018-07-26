package cn.jasgroup.jasframework.acquisitiondata.material.detection.faultrasonic.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.faultrasonic.dao.DaqDetectionFaUltrasonicDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.faultrasonic.dao.DaqDetectionFaUltrasonicSubDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.faultrasonic.dao.entity.DaqDetectionFaUltrasonicSub;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.faultrasonic.query.bo.DaqDetectionFaUltrasonicBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * @description 全自动超声波检测主表service
 * @author zhangyi
 * @date 2018年7月12日上午10:35:57
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqDetectionFaUltrasonicService extends CommonDataJdbcService {

	@Autowired
	private DaqDetectionFaUltrasonicDao faUltrasonicDao;
	
	@Autowired
	private DaqDetectionFaUltrasonicSubDao faUltrasonicSubDao;
	
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
		return this.faUltrasonicDao.approve(paramMap);
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
	public DaqDetectionFaUltrasonicBo get(String oid){
		DaqDetectionFaUltrasonicBo faUltrasonicBo = this.faUltrasonicDao.get(oid);
		if(null != faUltrasonicBo){
			List<DaqDetectionFaUltrasonicSub> faUltrasonicSubList = this.faUltrasonicSubDao.getList(oid);
			faUltrasonicBo.setFaUltrasonicSubList(faUltrasonicSubList);
		}
		return faUltrasonicBo;
	}
}
