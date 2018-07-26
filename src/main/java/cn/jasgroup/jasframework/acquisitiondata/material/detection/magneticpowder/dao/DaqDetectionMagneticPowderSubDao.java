package cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.magneticpowder.dao.entity.DaqDetectionMagneticPowderSub;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
 * @description 磁粉检测子表dao
 * @author zhangyi
 * @date 2018年7月13日上午10:58:36
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class DaqDetectionMagneticPowderSubDao extends BaseJdbcDao{

	@Resource
	private BaseJdbcDao baseJdbcDao;
	
	/**
	 * <p>功能描述：根据主表oid查询子表集合。</p>
	 * <p>张毅 </p>	
	 * @param oid	数据oid
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午5:21:11。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@SuppressWarnings("unchecked")
	public List<DaqDetectionMagneticPowderSub> getList(String parentOid){
		String sql = "select t.*"
				+ " from daq_detection_magnetic_powder_sub t "
				+ " where t.active = 1 and t.parent_oid = ?";
		List<DaqDetectionMagneticPowderSub> powderSubList = 
				this.baseJdbcDao.queryForList(sql, new String[]{parentOid}, DaqDetectionMagneticPowderSub.class);
		return powderSubList;
	}
}
