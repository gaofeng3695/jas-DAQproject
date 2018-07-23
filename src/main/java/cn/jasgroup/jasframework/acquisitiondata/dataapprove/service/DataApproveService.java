package cn.jasgroup.jasframework.acquisitiondata.dataapprove.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.dataapprove.dao.DataApproveDao;
import cn.jasgroup.jasframework.acquisitiondata.dataapprove.dao.entity.DataApprove;
import cn.jasgroup.jasframework.engine.hibernate.service.CommonDataHibernateService;
import cn.jasgroup.jasframework.support.ModelFacade;

@Service
@Transactional
public class DataApproveService extends CommonDataHibernateService{
	
	@Resource
	private DataApproveDao dataApproveDao;
	
	/***
	  * <p>功能描述：更改业务数据的审批状态。</p>
	  * <p> 雷凯。</p>	
	  * @param dataApprove
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月21日 下午5:23:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void changeBusinessApproveStatus(DataApprove dataApprove){
		String businessOid = dataApprove.getBusinessOid();
		Integer approveStatus = dataApprove.getApproveStatus();
		if(StringUtils.isNotBlank(dataApprove.getFunctionCode())){
			dataApproveDao.changeBusinessApproveStatus(null,dataApprove.getFunctionCode(), businessOid, approveStatus);
		}else if(StringUtils.isNotBlank(dataApprove.getClassName())){
			try {
				String tableName = ModelFacade.getTableName(Class.forName(dataApprove.getClassName()));
				dataApproveDao.changeBusinessApproveStatus(tableName, null, businessOid, approveStatus);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}
