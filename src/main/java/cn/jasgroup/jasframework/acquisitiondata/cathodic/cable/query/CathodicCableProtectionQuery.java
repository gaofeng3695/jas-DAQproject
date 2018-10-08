package cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.cathodic.cable.query.bo.CathodicCableProtectionBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：阴保电缆分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月18日 上午10:28:42。</p>
 */
@QueryConfig(scene = "/cathodicCableProtection/getPage", 
			 resultClass = CathodicCableProtectionBo.class, 
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService", method = "injectDataAuthoritySql(dataAuthoritySql)") 
			 }
)
public class CathodicCableProtectionQuery extends BaseJavaQuery{
	
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
	 * 阴保电缆编号 
	 */
	private String cableCode;
	
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
					+ "d.code_name as cable_struction_name,	dm.code_name as cable_laying_method_name,dom.code_name as cable_use_name, "
					+ "cts.test_stake_code, csa.anode_code, csb.ground_bed,"
					+ " u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name,"
					+ " ccp.* FROM daq_cathodic_cable_protection ccp "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = ccp.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = ccp.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = ccp.tenders_oid "
					+ "LEFT JOIN (select oid, pipe_segment_name, active from daq_pipe_segment where active=1) ps ON ps.oid = ccp.pipe_segment_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = ccp.median_stake_oid "
					+ "LEFT JOIN (select code_id, code_name, active from sys_domain where active=1) d on d.code_id = ccp.cable_struction "
					+ "LEFT JOIN (select code_id, code_name, active from sys_domain where active=1) dm on dm.code_id = ccp.cable_laying_method "
					+ "LEFT JOIN (select code_id, code_name, active from sys_domain where active=1) dom on dom.code_id = ccp.cable_use "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = ccp.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = ccp.construct_unit "
					+ "LEFT JOIN (select oid, test_stake_code,active from daq_cathodic_test_stake where active=1) cts ON cts.oid = ccp.test_stake_oid "
					+ "LEFT JOIN (select oid, anode_code,active from daq_cathodic_sacrifice_anode where active=1) csa on csa.oid = ccp.sacrifice_anode_oid "
					+ "LEFT JOIN (select oid, ground_bed,active from daq_cathodic_anode_bed where active=1) csb on csb.oid = ccp.auxiliary_anode_bed_oid "
					+ "WHERE ccp.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and ccp.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and ccp.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and ccp.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and ccp.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOid)) {
				conditionSql += " and ccp.pipe_segment_oid = :pipeSegmentOid";
			}
			if (StringUtils.isNotBlank(cableCode)) {
				conditionSql += " and ccp.cable_code like :cableCode";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and ccp.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by ccp.create_datetime desc";
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

	public String getCableCode() {
		if (StringUtils.isNotBlank(cableCode)) {
			return "%"+cableCode+"%";
		}
		return null;
	}

	public void setCableCode(String cableCode) {
		this.cableCode = cableCode;
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
