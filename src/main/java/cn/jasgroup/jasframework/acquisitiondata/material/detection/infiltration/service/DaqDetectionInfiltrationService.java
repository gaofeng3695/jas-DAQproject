package cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.DaqDetectionInfiltrationDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.DaqDetectionInfiltrationSubDao;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao.entity.DaqDetectionInfiltrationSub;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query.bo.DaqDetectionInfiltrationBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

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
}
