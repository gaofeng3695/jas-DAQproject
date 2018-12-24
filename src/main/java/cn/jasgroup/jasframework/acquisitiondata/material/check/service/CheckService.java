package cn.jasgroup.jasframework.acquisitiondata.material.check.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.check.dao.CheckDao;
import cn.jasgroup.jasframework.acquisitiondata.material.check.dao.entity.CheckPipeColdBending;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.support.BaseEntityThreadLocalHolder;

@Service
@Transactional
public class CheckService extends CommonDataHibernateService{
	
	@Autowired
	private CheckDao checkDao;
	
	/**
	 * <p>功能描述：获取绝缘接头的出厂编号。</p>
	  * <p> 葛建。</p>	
	 * @param projectOid 
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年10月17日 下午3:29:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String,Object>> getManufacturerCode(String projectOid){
		return checkDao.getManufacturerCode(projectOid);
	}
	/**
	  * <p>功能描述：保存时修改冷弯管检查状态。</p>
	  * <p> 雷凯。</p>	
	  * @param checkPipeColdBending
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午6:30:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void saveChanagePipeColdBendingChecked(CheckPipeColdBending checkPipeColdBending){
		this.checkDao.chanagePipeColdBendingChecked(checkPipeColdBending.getPipeColdBendingOid(), 1);
	}
	/**
	  * <p>功能描述：修改时修改冷弯管检查状态。</p>
	  * <p> 雷凯。</p>	
	  * @param checkPipeColdBending
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午6:30:43。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanagePipeColdBendingChecked(CheckPipeColdBending checkPipeColdBending){
		CheckPipeColdBending oldCheckPipeColdBending = (CheckPipeColdBending)BaseEntityThreadLocalHolder.getEntitySnap();
		this.checkDao.chanagePipeColdBendingChecked(oldCheckPipeColdBending.getPipeColdBendingOid(), 0);
		this.checkDao.chanagePipeColdBendingChecked(checkPipeColdBending.getPipeColdBendingOid(), 1);
	}
	/**
	  * <p>功能描述：删除时修改冷弯管检查状态。</p>
	  * <p> 雷凯。</p>	
	  * @param checkPipeColdBending
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午6:30:54。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void deleteChanagePipeColdBendingChecked(CheckPipeColdBending checkPipeColdBending){
		CheckPipeColdBending oldCheckPipeColdBending = (CheckPipeColdBending)BaseEntityThreadLocalHolder.getEntitySnap();
		this.checkDao.chanagePipeColdBendingChecked(oldCheckPipeColdBending.getPipeColdBendingOid(), 0);
	}
	/***
	  * <p>功能描述：修改钱信息获取。</p>
	  * <p> 雷凯。</p>	
	  * @param checkPipeColdBending
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午6:31:05。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void updateChanageBeforeAdvice(CheckPipeColdBending checkPipeColdBending){
		CheckPipeColdBending oldCheckPipeColdBending = this.checkDao.find(checkPipeColdBending.getOid());
		BaseEntityThreadLocalHolder.setEntitySnap(oldCheckPipeColdBending);
	}
}
