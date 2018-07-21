package cn.jasgroup.jasframework.acquisitiondata.material.appendages.electroniclabel.service;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.material.appendages.electroniclabel.dao.DaqAppendagesElectronicLabelDao;
import cn.jasgroup.jasframework.acquisitiondata.material.appendages.electroniclabel.query.bo.DaqAppendagesElectronicLabelBo;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;

/**
 * @description 电子标签
 * @author zhangyi
 * @date 2018年7月21日上午11:47:35
 * @version V1.0
 * @since JDK 1.80
 */

@Service
@Transactional
public class DaqAppendagesElectronicLabelService extends CommonDataJdbcService {

	@Autowired
	private DaqAppendagesElectronicLabelDao electronicLabelDao;
	
	/**
	 * <p>功能描述：审核。</p>
	 * <p>张毅 </p>	
	 * @param paramMap <idList, 数据oid集合>, <approveStatus, 数据状态>
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月16日 下午3:04:23。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap) {
		return this.electronicLabelDao.approve(paramMap);
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
	public DaqAppendagesElectronicLabelBo get(BaseJavaQuery query){
		return this.electronicLabelDao.get(query);
	}
}
