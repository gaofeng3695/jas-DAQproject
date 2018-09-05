package cn.jasgroup.jasframework.acquisitiondata.cathodic.protector.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.cathodic.protector.query.bo.CathodicInsulatedJointBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：绝缘接头保护器分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月19日 下午5:09:36。</p>
 */
@QueryConfig(scene = "/cathodicInsulatedJoint/getPage", 
			 resultClass = CathodicInsulatedJointBo.class, 
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService", method = "injectDataAuthoritySql(dataAuthoritySql)") 
			 }
)
public class CathodicInsulatedJointQuery extends BaseJavaQuery{
	
	/**
	 * oid
	 */
	private String oid;

	/**
	 * 项目oid 
	 */
	private String projectOid; 

	/**
	 * 标段oid 
	 */
	private String tendersOid; 

	/**
	 * 管线oid 
	 */
	private String pipelineOid; 

	/**
	 * 线路段oid 
	 */
	private String pipeSegmentOid; 

	/**
	 * 设备编号 
	 */
	private String equipmentCode;
	
	/**
	 * 审核状态
	 */
	private String approveStatus;
	
	/**
	 * 施工单位
	 */
	private String constructUnit;

	@Override
	public String getQuerySql() {
		String sql = "SELECT pro.project_name, pi.pipeline_name, te.tenders_name,ps.pipe_segment_name, ms.median_stake_code,"
					+ "u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, cij.* FROM daq_cathodic_insulated_joint cij "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cij.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = cij.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cij.tenders_oid "
					+ "LEFT JOIN (select oid, pipe_segment_name, active from daq_pipe_segment where active=1) ps ON ps.oid = cij.pipe_segment_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = cij.median_stake_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = cij.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = cij.construct_unit "
					+ "WHERE cij.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and cij.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and cij.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and cij.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and cij.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOid)) {
				conditionSql += " and cij.pipe_segment_oid = :pipeSegmentOid";
			}
			if (StringUtils.isNotBlank(equipmentCode)) {
				conditionSql += " and cij.equipment_code like :equipmentCode";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and cij.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by cij.create_datetime desc";
		return conditionSql;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getPipeSegmentOid() {
		return pipeSegmentOid;
	}

	public void setPipeSegmentOid(String pipeSegmentOid) {
		this.pipeSegmentOid = pipeSegmentOid;
	}

	public String getEquipmentCode() {
		if (StringUtils.isNotBlank(equipmentCode)) {
			return "%"+equipmentCode+"%";
		}
		return null;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
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
