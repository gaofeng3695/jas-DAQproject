package cn.jasgroup.jasframework.acquisitiondata.cathodic.decoupler.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.cathodic.decoupler.query.bo.CathodicSolidDecouplerBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：固态去耦合器分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月19日 下午5:36:53。</p>
 */
@QueryConfig(scene = "/cathodicSolidDecouplert/getPage", 
			 resultClass = CathodicSolidDecouplerBo.class, 
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService", method = "injectDataAuthoritySql(dataAuthoritySql)") 
			 }
)
public class CathodicSolidDecouplerQuery extends BaseJavaQuery{
	
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
					+ "u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, csd.* FROM daq_cathodic_solid_decoupler csd "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = csd.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = csd.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = csd.tenders_oid "
					+ "LEFT JOIN (select oid, pipe_segment_name, active from daq_pipe_segment where active=1) ps ON ps.oid = csd.pipe_segment_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = csd.median_stake_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = csd.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = csd.construct_unit "
					+ "WHERE csd.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and csd.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and csd.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and csd.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and csd.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOid)) {
				conditionSql += " and csd.pipe_segment_oid = :pipeSegmentOid";
			}
			if (StringUtils.isNotBlank(equipmentCode)) {
				conditionSql += " and csd.equipment_code like :equipmentCode";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and csd.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by csd.create_datetime desc";
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
