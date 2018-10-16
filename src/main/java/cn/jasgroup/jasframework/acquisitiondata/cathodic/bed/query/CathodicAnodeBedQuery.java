package cn.jasgroup.jasframework.acquisitiondata.cathodic.bed.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.cathodic.bed.query.bo.CathodicAnodeBedBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 *<p>类描述：辅助阳极地床分页查询。</p>
 * @author 葛建 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 *<p>创建日期：2018年7月19日 下午7:09:00。</p>
*/
@QueryConfig(scene = "/cathodicAnodeBed/getPage", 
			 resultClass = CathodicAnodeBedBo.class, 
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService", method = "injectDataAuthoritySql(dataAuthoritySql)") 
			 }
)
public class CathodicAnodeBedQuery extends BaseJavaQuery{
	
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
	 * 地床编号 
	 */
	private String groundBed;
	
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
				+ "u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, d.code_name as backfill_material_name,"
				+ "dm.code_name as burial_way_name,cab.* FROM daq_cathodic_anode_bed cab "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cab.project_oid "
				+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = cab.pipeline_oid "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cab.tenders_oid "
				+ "LEFT JOIN (select oid, pipe_segment_name, active from daq_pipe_segment where active=1) ps ON ps.oid = cab.pipe_segment_oid "
				+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = cab.median_stake_oid "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = cab.supervision_unit "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = cab.construct_unit "
				+ "left JOIN (select code_id,code_name,active from sys_domain where active=1) d ON d.code_id = cab.backfill_material "
				+ "left JOIN (select code_id,code_name,active from sys_domain where active=1) dm ON dm.code_id = cab.burial_way "
				+ "WHERE cab.active = 1";
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and cab.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and cab.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and cab.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and cab.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOid)) {
				conditionSql += " and cab.pipe_segment_oid = :pipeSegmentOid";
			}
			if (StringUtils.isNotBlank(groundBed)) {
				conditionSql += " and cab.ground_bed like :groundBed";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and cab.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by cab.create_datetime desc";
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

	public String getGroundBed() {
		if (StringUtils.isNotBlank(groundBed)) {
			return "%"+groundBed+"%";
		}
		return null;
	}

	public void setGroundBed(String groundBed) {
		this.groundBed = groundBed;
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
