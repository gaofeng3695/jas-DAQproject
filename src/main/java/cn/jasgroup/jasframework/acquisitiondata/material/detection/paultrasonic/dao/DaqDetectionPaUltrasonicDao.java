package cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.dao.entity.DaqDetectionPaUltrasonic;
import cn.jasgroup.jasframework.acquisitiondata.material.detection.paultrasonic.query.bo.DaqDetectionPaUltrasonicBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess.base.BaseNamedParameterJdbcDao;
import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;

/**
 * @description 相控阵超声波检测
 * @author zhangyi
 * @date 2018年7月12日上午10:54:28
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class DaqDetectionPaUltrasonicDao extends BaseNamedParameterJdbcDao{

	@Resource
	private BaseJdbcDao baseJdbcDao;
	
	@Autowired
	private BaseJdbcTemplate baseJdbcTemplate;
	
	/**
	 * <p>功能描述：更改审核状态。</p>
	 * <p>张毅 </p>	
	 * @param paramMap
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午4:39:08。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(Map<String, Object> paramMap){
		Boolean b = false;
		String sql = "update daq_detection_pa_ultrasonic set approve_status = :approveStatus where active=1"
				+ " and oid in (:idList)";
		
		int count = super.update(sql, paramMap);
		if(count > 0){
			b = true;
		}
		return b;
	}
	
	/**
	 * <p>功能描述：查看详情。</p>
	 * <p>张毅 </p>	
	 * @param oid	数据oid
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午5:21:11。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public DaqDetectionPaUltrasonicBo get(String oid){
		String sql = "select t.*,"
				+ " d1.code_name as detectionTypeName,"
				+ "	p.project_name,"
				+ "	l.pipeline_name,"
				+ "	dt.tenders_name,"
				+ "	v.name as pipeSegmentOrCrossName,"
				+ "	u1.unit_name as detectionUnitName,"
				+ "	u2.unit_name as supervisionUnitName,"
				+ " vdwi.weld_code "				
				+ " from daq_detection_pa_ultrasonic t "
				+ " left join sys_domain d1 on d1.code_id = t.detection_type"				
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ " left join daq_tenders dt on dt.oid=t.tenders_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.pipe_segment_or_cross_oid "
				+ " left join pri_unit u1 on u1.oid=t.detection_unit "
				+ " left join pri_unit u2 on u2.oid=t.supervision_unit "
				+ " left join (select oid,weld_code from v_daq_weld_info) vdwi on vdwi.oid=t.weld_oid "					
				+ " where t.active = 1 and t.oid =?";
		DaqDetectionPaUltrasonicBo paUltrasonicBo = 
				(DaqDetectionPaUltrasonicBo) this.baseJdbcDao.queryForObject(sql, new String[]{oid}, DaqDetectionPaUltrasonicBo.class);
		return paUltrasonicBo;
	}
	
	/**
	 * <p>功能描述：查询单个对象。</p>
	  * <p> 葛建。</p>	
	  * @param oid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:34:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public DaqDetectionPaUltrasonic find(String oid) {
		String sql = "select * from daq_detection_pa_ultrasonic t where t.oid=?";
		List<DaqDetectionPaUltrasonic> data = baseJdbcTemplate.queryForList(sql, new Object[]{oid}, DaqDetectionPaUltrasonic.class);
		if(data!=null && data.size()>0){
			return data.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * <p>功能描述：改变焊口检查状态。</p>
	  * <p> 葛建。</p>	
	  * @param weldOid
	  * @param status
	  * @since JDK1.8。
	  * <p>创建日期:2018年8月21日 下午4:31:12。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void chanageWledStatus(String weldOid, int status) {
		String sql = "update daq_construction_weld set is_pa_ultrasonic="+status+" where oid='"+weldOid+"';"
				+ "update daq_weld_rework_weld set is_pa_ultrasonic="+status+" where oid='"+weldOid+"';";
		baseJdbcTemplate.batchExecute(sql);
	}
}
