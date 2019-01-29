package cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.appendages.markstake.query.bo.DaqAppendagesMarkStakeBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 标志桩
 * @author zhangyi
 * @date 2018年7月19日下午7:19:30
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene = "/appendagesMarkStake/getPage",
	resultClass = DaqAppendagesMarkStakeBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqAppendagesMarkStakeQuery extends BaseJavaQuery {

	private List<String> oids;
	private String projectOid;
	private String pipelineOid;
	private String tendersOid;
	private String pipeSegmentOrCrossOid;
	private String markStakeCode;
	private String medianStakeOid;
	private String approveStatus;
	private String constructUnit;
	
	@Override
	public String getQuerySql() {
		String sql =  "select t.*,"
				+ " d1.code_name as stake_structure_name,"
				+ " ss.stake_function_name,"
				+ "	p.project_name,"
				+ "	l.pipeline_name,"
				+ "	dt.tenders_name,"
				+ "	v.name as pipe_segment_or_cross_name,"
				+ "	ms.median_stake_code, "
				+ "	u1.unit_name as construct_unit_name,"
				+ "	u2.unit_name as supervision_unit_name"				
				+ " from daq_appendages_mark_stake t "
				+ " left join (select code_id,code_name from sys_domain) d1 on d1.code_id = t.stake_structure"				
				+ " left join (SELECT t.oid, array_to_string(array_agg(s.code_name),',') as stake_function_name FROM daq_appendages_mark_stake t LEFT JOIN ( select code_id, code_name from sys_domain where active=1 and domain_name='mark_stake_function_domain' ) s ON t.stake_function like '%'||s.code_id||'%' group by t.oid) As ss"
				+ " on ss.oid = t.oid"
				+ " left join (select oid,project_name from daq_project) p on p.oid=t.project_oid "
				+ " left join (select oid,pipeline_name from daq_pipeline) l on l.oid=t.pipeline_oid "
				+ " left join (select oid,tenders_name from daq_tenders) dt on dt.oid=t.tenders_oid "
				+ " left join (select m.oid,m.median_stake_code from daq_median_stake m where active=1) ms"
				+ " on ms.oid=t.median_stake_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.pipe_segment_or_cross_oid "
				+ " left join (select oid,unit_name from pri_unit) u1 on u1.oid=t.construct_unit "
				+ " left join (select oid,unit_name from pri_unit) u2 on u2.oid=t.supervision_unit "				
				+ " where t.active = 1";
		if(StringUtils.isNotBlank(projectOid)){
			sql += " and t.project_oid = :projectOid ";
		}
		if(StringUtils.isNotBlank(pipelineOid)){
			sql += " and t.pipeline_oid = :pipelineOid ";
		}
		if(StringUtils.isNotBlank(tendersOid)){
			sql += " and t.tenders_oid = :tendersOid ";
		}
		if(StringUtils.isNotBlank(pipeSegmentOrCrossOid)){
			sql += " and t.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid ";
		}
		if(StringUtils.isNotBlank(markStakeCode)){
			sql += " and t.mark_stake_code like '%"+markStakeCode+"%' ";
		}
		if(StringUtils.isNotBlank(medianStakeOid)){
			sql += " and t.median_stake_oid = :medianStakeOid ";
		}
		if (null != oids && oids.size() > 0) {
			sql += " and oid in (:oids) ";
		}
		if(StringUtils.isNotBlank(approveStatus)){
			sql += " and t.approve_status in ("+ approveStatus +")";
		}
		if (StringUtils.isNotBlank(constructUnit)) {
			sql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
		}
		sql += this.dataAuthoritySql;
		sql +=" order by t.create_datetime desc";
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

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}

	public String getMarkStakeCode() {
		if(StringUtils.isNotBlank(markStakeCode)){
			return "%"+markStakeCode+"%";
		}
		return markStakeCode;
	}

	public void setMarkStakeCode(String markStakeCode) {
		this.markStakeCode = markStakeCode;
	}

	public String getMedianStakeOid() {
		return medianStakeOid;
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}
	
}
