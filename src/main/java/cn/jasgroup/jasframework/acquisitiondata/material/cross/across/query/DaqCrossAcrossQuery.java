package cn.jasgroup.jasframework.acquisitiondata.material.cross.across.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.cross.across.query.bo.DaqCrossAcrossBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 跨越表
 * @author zhangyi
 * @date 2018年7月17日上午11:07:51
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene = "/crossAcross/getPage",
	resultClass = DaqCrossAcrossBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqCrossAcrossQuery extends BaseJavaQuery {

	private List<String> oids;
	private String projectOid;
	private String pipelineOid;
	private String tendersOid;
	private String crossOid;
	private String startMedianStakeOid;
	private String endMedianStakeOid;
	
	@Override
	public String getSql() {
		String sql = "select t.*, "
				+ " p.project_name,"
				+ " l.pipeline_name,"
				+ " dt.tenders_name,"
				+ "	v.name as crossName,"
				+ "	sd.code_name as acrossTypeName,"
				+ "	ms.median_stake_code as startMedianStakeCode,"
				+ "	me.median_stake_code as endMedianStakeCode,"				
				+ "	u1.unit_name as constructUnitName,"
				+ "	u2.unit_name as supervisionUnitName"
				+ " from daq_cross_across t "
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ " left join daq_tenders dt on dt.oid=t.tenders_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.cross_oid "
				+ " left join (select m.oid,m.median_stake_code from daq_median_stake m where active=1) ms"
				+ " on ms.oid=t.start_median_stake_oid "
				+ " left join (select m.oid,m.median_stake_code from daq_median_stake m where active=1) me"
				+ " on me.oid=t.end_median_stake_oid "				
				+ " left join pri_unit u1 on u1.oid=t.construct_unit "
				+ " left join pri_unit u2 on u2.oid=t.supervision_unit "
				+ " left join sys_domain sd on sd.code_id=t.across_type "
				+ " where t.active = 1 ";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and t.project_oid = :projectOid ";
		}
		if(StringUtils.isNotBlank(pipelineOid)){
			sql += " and t.pipeline_oid = :pipelineOid ";
		}
		if(StringUtils.isNotBlank(tendersOid)){
			sql += " and t.tenders_oid = :tendersOid ";
		}
		if(StringUtils.isNotBlank(crossOid)){
			sql += " and t.cross_oid = :crossOid ";
		}
		if(StringUtils.isNotBlank(startMedianStakeOid)){
			sql += " and t.start_median_stake_oid = :startMedianStakeOid ";
		}
		if(StringUtils.isNotBlank(endMedianStakeOid)){
			sql += " and t.end_median_stake_oid = :endMedianStakeOid ";
		}
		if(null != oids && oids.size()>0){
			sql += " and oids in (:oids) ";
		}
		return sql;
	}

	public List<String> getOids() {
		return oids;
	}

	public void setOids(List<String> oids) {
		this.oids = oids;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getCrossOid() {
		return crossOid;
	}

	public void setCrossOid(String crossOid) {
		this.crossOid = crossOid;
	}

	public String getStartMedianStakeOid() {
		return startMedianStakeOid;
	}

	public void setStartMedianStakeOid(String startMedianStakeOid) {
		this.startMedianStakeOid = startMedianStakeOid;
	}

	public String getEndMedianStakeOid() {
		return endMedianStakeOid;
	}

	public void setEndMedianStakeOid(String endMedianStakeOid) {
		this.endMedianStakeOid = endMedianStakeOid;
	}
	
}
