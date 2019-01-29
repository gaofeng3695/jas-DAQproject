package cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.appendages.handhole.query.bo.DaqAppendagesHandHoleBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 手孔表
 * @author zhangyi
 * @date 2018年7月21日下午12:26:44
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene = "/appendagesHandHole/getPage",
	resultClass = DaqAppendagesHandHoleBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqAppendagesHandHoleQuery extends BaseJavaQuery {

	private List<String> oids;
	private String projectOid;
	private String pipelineOid;
	private String tendersOid;
	private String pipeSegmentOrCrossOid;
	private String handHoleCode;
	private String handHoleName;
	private String handHoleType;
	private String medianStakeOid;
	private String approveStatus;
	private String constructUnit;
	
	@Override
	public String getQuerySql() {
		String sql =  "select t.*,"
				+ " d1.code_name as hand_hole_name_code_name,"
				+ " d2.code_name as hand_hole_type_name,"
				+ "	p.project_name,"
				+ "	l.pipeline_name,"
				+ "	dt.tenders_name,"
				+ "	v.name as pipe_segment_or_cross_name,"
				+ "	ms.median_stake_code, "
				+ "	u1.unit_name as construct_unit_name,"
				+ "	u2.unit_name as supervision_unit_name"				
				+ " from daq_appendages_hand_hole t "
				+ " left join (select code_id,code_name from sys_domain) d1 on d1.code_id = t.hand_hole_name"				
				+ " left join (select code_id,code_name from sys_domain) d2 on d2.code_id = t.hand_hole_type"				
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
		if(StringUtils.isNotBlank(handHoleCode)){
			sql += " and t.hand_hole_code like :handHoleCode ";
		}
		if(StringUtils.isNotBlank(handHoleName)){
			sql += " and t.hand_hole_name = :handHoleName ";
		}
		if(StringUtils.isNotBlank(handHoleType)){
			sql += " and t.hand_hole_type = :handHoleType ";
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

	public String getHandHoleCode() {
		if(StringUtils.isNotBlank(handHoleCode)){
			return "%"+handHoleCode+"%";
		}
		return handHoleCode;
	}

	public void setHandHoleCode(String handHoleCode) {
		this.handHoleCode = handHoleCode;
	}

	public String getHandHoleName() {
		return handHoleName;
	}

	public void setHandHoleName(String handHoleName) {
		this.handHoleName = handHoleName;
	}

	public String getHandHoleType() {
		return handHoleType;
	}

	public void setHandHoleType(String handHoleType) {
		this.handHoleType = handHoleType;
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
