package cn.jasgroup.jasframework.acquisitiondata.cathodic.sacrifice.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.cathodic.sacrifice.query.bo.CathodicSacrificeAnodeBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
  *<p>类描述：牺牲阳极分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月18日 上午11:06:30。</p>
 */
@QueryConfig(scene = "/cathodicSacrificeAnode/getPage", 
			 resultClass = CathodicSacrificeAnodeBo.class, 
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService", method = "injectDataAuthoritySql(dataAuthoritySql)") 
			 }
)
public class CathodicSacrificeAnodeQuery extends BaseJavaQuery{

	/**
	 * 唯一标识
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
	 * 线路段
	 */
	private String pipeSegmentOid;

	/**
	 * 阳极编号
	 */
	private String anodeCode;
	
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
					+ "d.code_name as anode_material_name,dm.code_name as insulation_quality_name, u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, csa.* "
					+ "FROM daq_cathodic_sacrifice_anode csa "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = csa.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = csa.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = csa.tenders_oid "
					+ "LEFT JOIN (select oid, pipe_segment_name, active from daq_pipe_segment where active=1) ps ON ps.oid = csa.pipe_segment_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = csa.median_stake_oid "
					+ "LEFT JOIN (select code_id, code_name, active from sys_domain where active=1) d on d.code_id = csa.anode_material "
					+ "LEFT JOIN (select code_id, code_name, active from sys_domain where active=1) dm on dm.code_id = csa.insulation_quality "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = csa.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = csa.construct_unit "
					+ "WHERE csa.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and csa.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and csa.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and csa.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and csa.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOid)) {
				conditionSql += " and csa.pipe_segment_oid = :pipeSegmentOid";
			}
			if (StringUtils.isNotBlank(anodeCode)) {
				conditionSql += " and csa.anode_code like :anodeCode";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and csa.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by csa.create_datetime desc";
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

	public String getAnodeCode() {
		if (StringUtils.isNotBlank(anodeCode)) {
			return "%"+anodeCode+"%";
		}
		return null;
	}

	public void setAnodeCode(String anodeCode) {
		this.anodeCode = anodeCode;
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
