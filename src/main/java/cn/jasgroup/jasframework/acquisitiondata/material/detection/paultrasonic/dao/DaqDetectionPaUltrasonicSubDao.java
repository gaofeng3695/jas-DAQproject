package cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.entity.DaqDetectionPaUltrasonicSub;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
 * @description 相控阵超声波检测dao
 * @author zhangyi
 * @date 2018年7月13日上午11:08:02
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class DaqDetectionPaUltrasonicSubDao extends BaseJdbcDao{

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
	public List<DaqDetectionPaUltrasonicSub> getList(String parentOid){
		String sql = "select t.*,"
				+ " vdwi.weld_code "
				+ " from daq_detection_pa_ultrasonic_sub t "
				+ " left join (select oid,weld_code from v_daq_weld_info) vdwi on vdwi.oid=t.weld_oid "	
				+ " where t.active = 1 and t.parent_oid = ?";
		List<DaqDetectionPaUltrasonicSub> paUltrasonicSubList = 
				this.baseJdbcDao.queryForList(sql, new String[]{parentOid}, DaqDetectionPaUltrasonicSub.class);
		return paUltrasonicSubList;
	}
}
