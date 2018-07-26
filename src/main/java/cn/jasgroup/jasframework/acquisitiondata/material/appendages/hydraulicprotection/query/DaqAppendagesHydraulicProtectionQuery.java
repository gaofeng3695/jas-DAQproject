package cn.jasgroup.jasframework.acquisitiondata.material.appendages.hydraulicprotection.query;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.material.appendages.hydraulicprotection.query.bo.DaqAppendagesHydraulicProtectionBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * @description 水工保护
 * @author zhangyi
 * @date 2018年7月21日下午1:50:03
 * @version V1.0
 * @since JDK 1.80
 */

@QueryConfig(
	scene = "/appendagesHydraulicProtection/getPage",
	resultClass = DaqAppendagesHydraulicProtectionBo.class,
	queryBeforeProcess = {
		@Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
	}
)
public class DaqAppendagesHydraulicProtectionQuery extends BaseJavaQuery{

	private List<String> oids;
	private String projectOid;
	private String pipelineOid;
	private String tendersOid;
	private String pipeSegmentOrCrossOid;
	private String hydraulicProtectionCode;
	private String hydraulicProtectionName;
	private String hydraulicProtectionType;
	private String medianStakeOid;
	
	@Override
	public String getSql() {
		String sql =  "select t.*,"
				+ " d1.code_name as hydraulicProtectionTypeName,"
				+ " d2.code_name as hydraulicProtectionMaterialName,"
				+ "	p.project_name,"
				+ "	l.pipeline_name,"
				+ "	dt.tenders_name,"
				+ "	v.name as pipeSegmentOrCrossName,"
				+ "	ms.median_stake_code, "
				+ "	wu.work_unit_name as workUnitName,"
				+ "	wu.work_unit_code as workUnitCode,"
				+ "	u1.unit_name as constructUnitName,"
				+ "	u2.unit_name as supervisionUnitName"				
				+ " from daq_appendages_hydraulic_protection t "
				+ " left join sys_domain d1 on d1.code_id = t.hydraulic_protection_type"				
				+ " left join sys_domain d2 on d2.code_id = t.hydraulic_protection_material"				
				+ " left join daq_project p on p.oid=t.project_oid "
				+ " left join daq_pipeline l on l.oid=t.pipeline_oid "
				+ " left join daq_tenders dt on dt.oid=t.tenders_oid "
				+ " left join (select m.oid,m.median_stake_code from daq_median_stake m where active=1) ms"
				+ " on ms.oid=t.median_stake_oid "
				+ " left join v_daq_pipe_segment_cross v on v.oid=t.pipe_segment_or_cross_oid "
				+ " left join daq_work_unit wu on wu.oid=t.work_unit_oid "				
				+ " left join pri_unit u1 on u1.oid=t.construct_unit "
				+ " left join pri_unit u2 on u2.oid=t.supervision_unit "				
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
		if(StringUtils.isNotBlank(hydraulicProtectionCode)){
			sql += " and t.hydraulic_protection_code like :hydraulicProtectionCode ";
		}
		if(StringUtils.isNotBlank(hydraulicProtectionName)){
			sql += " and t.hydraulic_protection_name like :hydraulicProtectionName ";
		}
		if(StringUtils.isNotBlank(hydraulicProtectionType)){
			sql += " and t.hydraulic_protection_type = :hydraulicProtectionType ";
		}
		if(StringUtils.isNotBlank(medianStakeOid)){
			sql += " and t.median_stake_oid = :medianStakeOid ";
		}
		if (null != oids && oids.size() > 0) {
			sql += " and oid in (:oids) ";
		}
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

	public String getHydraulicProtectionCode() {
		if(StringUtils.isNotBlank(hydraulicProtectionCode)){
			return "%"+hydraulicProtectionCode+"%";
		}
		return hydraulicProtectionCode;
	}

	public void setHydraulicProtectionCode(String hydraulicProtectionCode) {
		this.hydraulicProtectionCode = hydraulicProtectionCode;
	}

	public String getHydraulicProtectionName() {
		if(StringUtils.isNotBlank(hydraulicProtectionName)){
			return "%"+hydraulicProtectionName+"%";
		}
		return hydraulicProtectionName;
	}

	public void setHydraulicProtectionName(String hydraulicProtectionName) {
		this.hydraulicProtectionName = hydraulicProtectionName;
	}

	public String getHydraulicProtectionType() {
		return hydraulicProtectionType;
	}

	public void setHydraulicProtectionType(String hydraulicProtectionType) {
		this.hydraulicProtectionType = hydraulicProtectionType;
	}

	public String getMedianStakeOid() {
		return medianStakeOid;
	}

	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid;
	}

}
