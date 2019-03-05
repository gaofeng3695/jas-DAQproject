package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query;

import org.apache.commons.lang.StringUtils;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query.bo.ConstructionWeldBo;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;

/**
 * 
  *<p>类描述：焊口信息分页查询。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月11日 下午4:44:59。</p>
  * {@link cn.jasgroup.jasframework.acquisitiondata.variate.DaqInjectService #injectDataAuthoritySql()}
 */
@QueryConfig(scene = "/constructionWeld/getPage",
			 resultClass = ConstructionWeldBo.class,
			 queryBeforeProcess = {
				 @Process(service = "daqInjectService" , method = "injectDataAuthoritySql(dataAuthoritySql)")
			 }
)
public class ConstructionWeldQuery extends BaseJavaQuery{
	
	/**
	 * oid
	 */
	private String oid;

	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid;

	/**
	 * 焊口编号
	 */
	private String weldCode;

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
		String sql = "";
		if(StringUtils.isNotBlank(oid)){
			sql = "SELECT cw.*,pro.project_name, pi.pipeline_name, te.tenders_name, vpsc.name as pipe_segment_or_cross_name, ms.median_stake_code,"
					+ " u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, wu.work_unit_code, d.code_name as weld_type_name,wps.weld_produce_code, "
					+ "dm.code_name as weld_method_name,wp.personnel_name as cover_name, wpe.personnel_name as padder_name, wper.personnel_name as render_name,"
					+ "pf.code_name as front_pipe_type_name,bp.code_name as back_pipe_type_name,vmf.code as front_pipe_code,vmb.code as back_pipe_code,f.*,b.*,vmf.length as front_pipe_length,vmb.length as back_pipe_length,"
					+ "(cw.is_ray+cw.is_ultrasonic+cw.is_infiltration+cw.is_magnetic_powder+cw.is_fa_ultrasonic+cw.is_pa_ultrasonic) as is_detection,"
					+ "case when cw.airflow_direction=1 then '+' when cw.airflow_direction=0 then '-' end as airflow_direction_name  "
					+ "FROM daq_construction_weld cw "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cw.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = cw.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cw.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = cw.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = cw.median_stake_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = cw.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = cw.construct_unit "
					+ "LEFT JOIN (select oid, work_unit_code, active from daq_work_unit where active=1) wu ON wu.oid = cw.work_unit_oid "
					+ "LEFT JOIN (select code_id, code_name,active from sys_domain where active=1) d ON d.code_id = cw.weld_type "
					+ "LEFT JOIN (select code_id, code_name,active from sys_domain where active=1) dm ON dm.code_id = cw.weld_method "
					+ "LEFT JOIN (SELECT oid, weld_produce_code, active FROM daq_weld_produce_specification where active=1) wps ON wps.oid = cw.weld_produce "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wp ON wp.oid = cw.cover_oid "
					+ "Left join (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.cover_oid like '%'||wpe.oid||'%' group by t.oid) wp ON wp.oid = cw.oid "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wpe ON wpe.oid = cw.padder_oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.padder_oid like '%'||wpe.oid||'%' group by t.oid) wpe ON wpe.oid = cw.oid "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wper ON wper.oid = cw.render_oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.render_oid like '%'||wpe.oid||'%' group by t.oid) wper ON wper.oid = cw.oid "
					+ "left join (select code_id, code_name from sys_domain where active=1 and domain_name='pipe_type_domain') pf on cw.front_pipe_type= pf.code_id "
					+ "left join (select code_id, code_name from sys_domain where active=1 and domain_name='back_pipe_type_domain') bp on cw.back_pipe_type=bp.code_id "
					+ "left join (select oid,code,length from v_daq_material) vmf on vmf.oid=cw.front_pipe_oid "
					+ "left join (select oid,code,length from v_daq_material) vmb on vmb.oid=cw.back_pipe_oid "
					+ "left join (select tt.oid as f_oid,weld_code as f_weld_code,front_pipe_oid as f_front_pipe_oid,front_pipe_type as f_front_pipe_type,vf.code as f_front_pipe_code,back_pipe_oid as f_back_pipe_oid,length as f_front_pipe_length from daq_construction_weld tt left join (select oid,code,length from v_daq_material) vf on vf.oid = tt.front_pipe_oid where active=1) f on f.f_back_pipe_oid=cw.front_pipe_oid "
					+ "left join (select tt.oid as b_oid,weld_code as b_weld_code,front_pipe_oid as b_front_pipe_oid,back_pipe_type as b_back_pipe_type,back_pipe_oid as b_back_pipe_oid,bf.code as b_back_pipe_code, length as b_back_pipe_length from daq_construction_weld tt left join (select oid,code,length from v_daq_material) bf on bf.oid = tt.back_pipe_oid where tt.active=1) b on b.b_front_pipe_oid=cw.back_pipe_oid "
					+ "WHERE cw.active = 1";
		}else{
			sql = "SELECT cw.*,pro.project_name, pi.pipeline_name, te.tenders_name, vpsc.name as pipe_segment_or_cross_name, ms.median_stake_code,"
					+ " u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, wu.work_unit_code, d.code_name as weld_type_name,wps.weld_produce_code, "
					+ "dm.code_name as weld_method_name,wp.personnel_name as cover_name, wpe.personnel_name as padder_name, wper.personnel_name as render_name,"
					+ "pf.code_name as front_pipe_type_name,bp.code_name as back_pipe_type_name,vmf.code as front_pipe_code,vmb.code as back_pipe_code, "
					+ "(cw.is_ray+cw.is_ultrasonic+cw.is_infiltration+cw.is_magnetic_powder+cw.is_fa_ultrasonic+cw.is_pa_ultrasonic) as is_detection,  "
                    + "case when cw.airflow_direction=1 then '+' when cw.airflow_direction=0 then '-' end as airflow_direction_name  "
                    + "FROM daq_construction_weld cw "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cw.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = cw.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cw.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = cw.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, median_stake_code, active from daq_median_stake where active=1) ms ON ms.oid = cw.median_stake_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = cw.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = cw.construct_unit "
					+ "LEFT JOIN (select oid, work_unit_code, active from daq_work_unit where active=1) wu ON wu.oid = cw.work_unit_oid "
					+ "LEFT JOIN (select code_id, code_name,active from sys_domain where active=1) d ON d.code_id = cw.weld_type "
					+ "LEFT JOIN (select code_id, code_name,active from sys_domain where active=1) dm ON dm.code_id = cw.weld_method "
					+ "LEFT JOIN (SELECT oid, weld_produce_code, active FROM daq_weld_produce_specification where active=1) wps ON wps.oid = cw.weld_produce "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wp ON wp.oid = cw.cover_oid "
					+ "Left join (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.cover_oid like '%'||wpe.oid||'%' group by t.oid) wp ON wp.oid = cw.oid "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wpe ON wpe.oid = cw.padder_oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.padder_oid like '%'||wpe.oid||'%' group by t.oid) wpe ON wpe.oid = cw.oid "
//					+ "LEFT JOIN (SELECT oid, personnel_name, active FROM daq_work_personnel where active=1) wper ON wper.oid = cw.render_oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.render_oid like '%'||wpe.oid||'%' group by t.oid) wper ON wper.oid = cw.oid "
					+ "left join (select code_id, code_name from sys_domain where active=1 and domain_name='pipe_type_domain') pf on cw.front_pipe_type= pf.code_id "
					+ "left join (select code_id, code_name from sys_domain where active=1 and domain_name='back_pipe_type_domain') bp on cw.back_pipe_type=bp.code_id "
					+ "left join (select oid,code from v_daq_material) vmf on vmf.oid=cw.front_pipe_oid "
					+ "left join (select oid,code from v_daq_material) vmb on vmb.oid=cw.back_pipe_oid "
					+ "WHERE cw.active = 1";
		}
		sql += getConditionSql();
		return sql;
	}

	private String getConditionSql() {
		String conditionSql ="";
		if (StringUtils.isNotBlank(oid)) {
			conditionSql += " and cw.oid = :oid ";
		}else {
			if (StringUtils.isNotBlank(projectOid)) {
				conditionSql += " and cw.project_oid = :projectOid";
			}
			if (StringUtils.isNotBlank(tendersOid)) {
				conditionSql += " and cw.tenders_oid = :tendersOid";
			}
			if (StringUtils.isNotBlank(pipelineOid)) {
				conditionSql += " and cw.pipeline_oid = :pipelineOid";
			}
			if (StringUtils.isNotBlank(pipeSegmentOrCrossOid)) {
				conditionSql += " and cw.pipe_segment_or_cross_oid = :pipeSegmentOrCrossOid";
			}
			if (StringUtils.isNotBlank(weldCode)) {
				conditionSql += " and cw.weld_code like :weldCode";
			}
			if (StringUtils.isNotBlank(approveStatus)) {
				conditionSql += " and cw.approve_status in ("+ approveStatus +")";
			}
			if (StringUtils.isNotBlank(constructUnit)) {
				conditionSql += " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid=:constructUnit)";
			}
			conditionSql += this.dataAuthoritySql;
		}
		conditionSql += " order by cw.create_datetime desc";
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

	public String getWeldCode() {
		if (StringUtils.isNotBlank(weldCode)) {
			return "%"+weldCode+"%";
		}
		return null;
	}

	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
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
