package cn.jasgroup.jasframework.acquisitiondata.material.cross.pipejacking.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.cross.pipejacking.dao.DaqCrossPipeJackingDao;
import cn.jasgroup.jasframework.acquisitiondata.material.cross.pipejacking.query.bo.DaqCrossPipeJackingBo;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * @description 顶管穿跨越service
 * @author zhangyi
 * @date 2018年7月16日下午2:49:41
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqCrossPipeJackingService extends CommonDataJdbcService {
	
	@Autowired
	private DaqCrossPipeJackingDao pipeJackingDao;
	
	/**
	 * <p>功能描述：审核。</p>
	 * <p>张毅 </p>	
	 * @param oid	数据oid
	 * @param approveStatus	数据状态
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:04:23。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap) {
		
		return this.pipeJackingDao.approve(paramMap);
	}
	
	/**
	 * <p>功能描述：获取详情。</p>
	 * <p>张毅 </p>	
	 * @param oid
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午2:55:14。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public DaqCrossPipeJackingBo get(String oid){
		return this.pipeJackingDao.get(oid);
	}

}
