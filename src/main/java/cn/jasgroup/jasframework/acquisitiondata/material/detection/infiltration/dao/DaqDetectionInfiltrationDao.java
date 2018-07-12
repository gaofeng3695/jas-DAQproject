package cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.dao;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.jasgroup.jasframework.acquisitiondata.material.detection.infiltration.query.bo.DaqDetectionInfiltrationBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;

/**
 * @description 渗透检测dao
 * @author zhangyi
 * @date 2018年7月11日下午3:57:56
 * @version V1.0
 * @since JDK 1.80
 */

@Component
public class DaqDetectionInfiltrationDao extends BaseJdbcDao{

	@Resource
	private BaseJdbcDao baseJdbcDao;
	
	/**
	 * <p>功能描述：更改审核状态。</p>
	 * <p>张毅 </p>	
	 * @param oid
	 * @param approveStatus	审核状态
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2018年7月11日 下午4:39:08。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Boolean approve(String oid, Integer approveStatus){
		Boolean b = false;
		String sql = "update daq_detection_infiltration set approve_status = ? where active=1"
				+ " and oid =?";
		
		int count = this.baseJdbcDao.update(sql, new Object[]{approveStatus, oid});
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
	public DaqDetectionInfiltrationBo get(String oid){
		String sql ="select t.*,"
				+ "	p.project_name,"
				+ "	l.pipeline_name,"
				+ "	dt.tenders_name,"
				+ "	v.name as pipeSegmentOrCrossName"
				+ " from daq_detection_infiltration t "
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ " left join daq_tenders dt on dt.oid=t.tenders_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.pipe_segment_or_cross_oid "				
				+ " where t.active = 1 and t.oid =?";
		DaqDetectionInfiltrationBo infiltrationBo = 
				(DaqDetectionInfiltrationBo) this.baseJdbcDao.queryForObject(sql, new String[]{oid}, DaqDetectionInfiltrationBo.class);
		return infiltrationBo;
	}
}
