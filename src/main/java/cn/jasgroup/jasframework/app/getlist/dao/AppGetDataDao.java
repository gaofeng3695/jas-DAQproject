package cn.jasgroup.jasframework.app.getlist.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.jasgroup.jasframework.acquisitiondata.material.check.query.bo.CheckPipeColdBendingBo;
import cn.jasgroup.jasframework.acquisitiondata.weld.measuredresult.query.bo.WeldMeasuredResultBo;
import cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query.bo.ReworkWeldBo;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query.bo.ConstructionWeldBo;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess.data.Page;
import cn.jasgroup.jasframework.dataaccess.data.PageRequest;
import cn.jasgroup.jasframework.dataaccess.springhelper.JdbcDaoHelper;
import cn.jasgroup.jasframework.dataaccess3.core.BaseNamedParameterJdbcTemplate;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Repository
public class AppGetDataDao {

	@Autowired
	private BaseJdbcDao baseJdbcDao;
	
	@Autowired
	private JdbcDaoHelper jdbcDaoHelper;

	@Autowired
	private BaseNamedParameterJdbcTemplate baseNamedParameterJdbcTemplate;

	/**
	 * <p>功能描述：APP端防腐管检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月28日 下午4:53:45。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getCoatingPipeListByPage(int page, int rows, String projectOid) {
		String userId = ThreadLocalHolder.getCurrentUserId();
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageSize(rows);
		pageRequest.setPageNo(page);
		String sql = "SELECT ccp.commit_status, ccp.oid,ccp.project_oid, ccp.tenders_oid,ccp.construct_unit, ccp.pipe_oid, ccp.groove_check,"
					+ " case when ccp.groove_check=1 then '合格' when ccp.groove_check=0 then '不合格' end as groove_check_name, ccp.pipe_end_proring_check,"
					+ "case when ccp.pipe_end_proring_check=1 then '合格' when ccp.pipe_end_proring_check=0 then '不合格' end as pipe_end_proring_check_name,"
					+ " case when ccp.coating_io_face_check=1 then '合格' when ccp.coating_io_face_check=0 then '不合格' end as coating_io_face_check_name,"
					+ "ccp.coating_io_face_check, case when ccp.diameter_check=1 then '合格' when ccp.diameter_check=0 then '不合格' end as diameter_check_name,"
					+ "ccp.diameter_check, case when ccp.coating_io_ends_check=1 then '合格' when ccp.coating_io_ends_check=0 then '不合格' end as coating_io_ends_check_name,"
					+ "ccp.coating_io_ends_check, case when ccp.excess_weld_metal=1 then '合格' when ccp.excess_weld_metal=0 then '不合格' end as excess_weld_metal_name,"
					+ " ccp.excess_weld_metal, case when ccp.ovality=1 then '合格' when ccp.ovality=0 then '不合格' end as ovality_name,ccp.ovality, ccp.checked_by, "
					+ "to_char(ccp.checked_date, 'YYYY-MM-DD') as checked_date, ccp.remarks, ccp.create_user_id, ccp.create_user_name, ccp.create_datetime, ccp.modify_user_id,"
					+ " ccp.modify_user_name, ccp.modify_datetime, ccp.active,pro.project_name, pi.unit_name as construct_unit_name, te.tenders_name,mp.pipe_code "
					+ "FROM daq_check_coating_pipe ccp "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = ccp.project_oid "
					+ "LEFT JOIN (SELECT oid, unit_name, active FROM pri_unit where active=1) pi ON pi.oid = ccp.construct_unit "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = ccp.tenders_oid "
					+ "left join (select oid,pipe_code from daq_material_pipe where active=1) mp on mp.oid=ccp.pipe_oid  "
					+ "WHERE 1 = 1  AND ccp.active = 1 and create_user_id='"+userId+"' and ccp.project_oid='"+ projectOid +"' order by ccp.create_datetime,mp.pipe_code desc";
		Page pageObj = new Page(pageRequest);
		String pageSql = jdbcDaoHelper.getPageSql(pageRequest, sql);
		List list = null;
		list = baseNamedParameterJdbcTemplate.queryForListHump(pageSql, Map.class);
		pageObj.setResult(list);
		return pageObj;
	}

	/**
	 * <p>功能描述：APP端热煨弯管检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月28日 下午4:56:43。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getHotBendsListByPage(int page, int rows, String projectOid) {
		String userId = ThreadLocalHolder.getCurrentUserId();
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageSize(rows);
		pageRequest.setPageNo(page);
		String sql = "SELECT chb.commit_status, chb.oid,chb.project_oid, chb.tenders_oid,chb.construct_unit,  chb.hot_bends_oid, case when chb.weld_position =1 then '合格' when chb.weld_position =0 then '不合格'  "
					+"       else '' end as weld_position_name ,  chb.weld_position,  case  when  chb.pipe_length = 1 then '合格'  when chb.pipe_length = 0 then '不合格' else '' end as pipe_length_name , "
					+"	   chb.pipe_length, "
					+" case  when  chb.ovality = 1 then '合格' "
					+"       when chb.ovality = 0 then '不合格'  "
					+"       else '' end as ovality_name , "
					+"	   chb.ovality, "
					+" case  when  chb.groove_check = 1 then '合格' "
					+"       when chb.groove_check = 0 then '不合格'  "
					+"       else '' end as groove_check_name , "
					+"	   chb.groove_check, "
					+" case  when  chb.coating_io_face_check = 1 then '合格' "
					+"       when chb.coating_io_face_check = 0 then '不合格'  "
					+"       else '' end as coating_io_face_check_name , "
					+"	   chb.coating_io_face_check, "
					+" case  when  chb.coating_io_ends_check = 1 then '合格' "
					+"       when chb.coating_io_ends_check = 0 then '不合格' "
					+"       else '' end as coating_io_ends_check_name ,chb.coating_io_ends_check, chb.checked_by,  to_char(chb.checked_date, 'YYYY-MM-DD') as checked_date, chb.remarks, "
					+ "chb.create_user_id, chb.create_user_name, to_char(chb.create_datetime,'YYYY-MM-DD') as create_datetime, chb.modify_user_id, chb.modify_user_name, chb.modify_datetime,"
					+ " chb.active,pro.project_name, pi.unit_name as construct_unit_name,te.tenders_name,mhb.hot_bends_code FROM daq_check_hot_bends chb "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = chb.project_oid "
					+ "LEFT JOIN (SELECT oid, unit_name, active FROM pri_unit where active=1) pi ON pi.oid = chb.construct_unit "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = chb.tenders_oid "
					+ "left join (select oid,hot_bends_code from daq_material_hot_bends where active=1) mhb on mhb.oid=chb.hot_bends_oid "
					+ "WHERE 1 = 1  AND chb.active = 1 and chb.create_user_id='"+userId+"' and chb.project_oid='"+ projectOid +"' order by chb.create_datetime,mhb.hot_bends_code desc";
		Page pageObj = new Page(pageRequest);
		String pageSql = jdbcDaoHelper.getPageSql(pageRequest, sql);
		List list = null;
		list = baseNamedParameterJdbcTemplate.queryForListHump(pageSql, Map.class);
		pageObj.setResult(list);
		return pageObj;
	}
	
	/**
	 * <p>功能描述：APP端冷弯管检查分页查询。</p>
	 * <p> 葛建。</p>	
	 * @param page
	 * @param rows
	 * @return
	 * @since JDK1.8。
	 * <p>创建日期:2019年3月28日 下午4:56:43。</p>
	 * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getColdBendsListByPage(int page, int rows, String projectOid) {
		String userId = ThreadLocalHolder.getCurrentUserId();
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageSize(rows);
		pageRequest.setPageNo(page);
		String sql = "select cpcb.commit_status, cpcb.oid, cpcb.project_oid, cpcb.tenders_oid, cpcb.construct_unit, cpcb.pipe_cold_bending_oid, cpcb.certificate_num, "
				+ "cpcb.pipe_length, cpcb.pipe_diameter, cpcb.wall_thickness, cpcb.production_unit, cpcb.bend_angle, "
				+ "case when cpcb.weld_position=1 then '合格' when cpcb.weld_position=0 then '不合格' end as weld_position_name,cpcb.weld_position, "
				+ "case when cpcb.ovality=1 then '合格' when cpcb.ovality=0 then '不合格' end as ovality_name,cpcb.ovality, "
				+ "case when cpcb.groove_check=1 then '合格' when cpcb.groove_check=0 then '不合格' end as groove_check_name,cpcb.groove_check, "
				+ "case when cpcb.coating_io_face_check=1 then '合格' when cpcb.coating_io_face_check=0 then '不合格' end as coating_io_face_check_name,cpcb.coating_io_face_check, "
				+ "case when cpcb.coating_io_ends_check=1 then '合格' when cpcb.coating_io_ends_check=0 then '不合格' end as coating_io_ends_check_name,cpcb.coating_io_ends_check, "
				+ "cpcb.checked_by, cpcb.checked_date, cpcb.remarks, cpcb.create_user_id, cpcb.create_user_name, cpcb.create_datetime, cpcb.modify_user_id, "
				+ "cpcb.modify_user_name, cpcb.modify_datetime, cpcb.active, pro.project_name, pi.unit_name,pi.unit_name as construct_unit_name,"
				+ "te.tenders_name,cp.pipe_cold_bending_code "
				+ "from daq_check_pipe_cold_bending cpcb "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = cpcb.project_oid "
				+ "LEFT JOIN (SELECT oid, unit_name, active FROM pri_unit where active=1) pi ON pi.oid = cpcb.construct_unit "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = cpcb.tenders_oid "
				+ "left join (select oid, pipe_cold_bending_code from daq_material_pipe_cold_bending ) cp on cp.oid = cpcb.pipe_cold_bending_oid "
				+ "where cpcb.active=1 and cpcb.create_user_id='"+userId+"' and cpcb.project_oid='"+ projectOid +"' order by cpcb.create_datetime,cp.pipe_cold_bending_code desc";
		Page pageObj = new Page(pageRequest);
		String pageSql = jdbcDaoHelper.getPageSql(pageRequest, sql);
		List list = null;
		list = baseNamedParameterJdbcTemplate.queryForList(pageSql, null, CheckPipeColdBendingBo.class);
		pageObj.setResult(list);
		return pageObj;
	}

	/**
	 * <p>功能描述：APP端焊口记录分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月29日 上午10:03:27。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getWeldInfoList(int page, int rows, String projectOid, String approveStatus) {
		String userId = ThreadLocalHolder.getCurrentUserId();
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageSize(rows);
		pageRequest.setPageNo(page);
		String sql = "SELECT cw.*,pro.project_name, pi.pipeline_name, te.tenders_name, vpsc.name as pipe_segment_or_cross_name, ms.median_stake_code,"
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
					+ "Left join (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.cover_oid like '%'||wpe.oid||'%' group by t.oid) wp ON wp.oid = cw.oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.padder_oid like '%'||wpe.oid||'%' group by t.oid) wpe ON wpe.oid = cw.oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_construction_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.render_oid like '%'||wpe.oid||'%' group by t.oid) wper ON wper.oid = cw.oid "
					+ "left join (select code_id, code_name from sys_domain where active=1 and domain_name='pipe_type_domain') pf on cw.front_pipe_type= pf.code_id "
					+ "left join (select code_id, code_name from sys_domain where active=1 and domain_name='back_pipe_type_domain') bp on cw.back_pipe_type=bp.code_id "
					+ "left join (select oid,code from v_daq_material) vmf on vmf.oid=cw.front_pipe_oid "
					+ "left join (select oid,code from v_daq_material) vmb on vmb.oid=cw.back_pipe_oid "
					+ "WHERE cw.active = 1  and cw.create_user_id='"+userId+"' and cw.project_oid='"+ projectOid +"' and cw.approve_status in ("+ approveStatus +") order by cw.create_datetime,cw.weld_code desc";
		Page pageObj = new Page(pageRequest);
		String pageSql = jdbcDaoHelper.getPageSql(pageRequest, sql);
		List list = null;
		list = baseNamedParameterJdbcTemplate.queryForList(pageSql, null,ConstructionWeldBo.class);
		pageObj.setResult(list);
		return pageObj;
	}

	/**
	 * <p>功能描述：APP端焊口返修分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月29日 上午10:05:30。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getWeldReworkList(int page, int rows, String projectOid, String approveStatus) {
		String userId = ThreadLocalHolder.getCurrentUserId();
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageSize(rows);
		pageRequest.setPageNo(page);
		String sql = "SELECT wrw.oid,wrw.project_oid,pro.project_name, wrw.pipeline_oid,pi.pipeline_name,wrw.tenders_oid,te.tenders_name,"
					+ "wrw.pipe_segment_or_cross_oid,vpsc.name as pipe_segment_or_cross_name,wrw.weld_oid, cw.weld_code, wrw.rework_weld_code, "
					+ "wrw.weld_rod_batch_num,wrw.weld_wire_batch_num, wrw.weld_produce, wps.weld_produce_code, wrw.cover_oid, wp.personnel_name as cover_name, "
					+ "wrw.padder_oid, wpe.personnel_name as padder_name,wrw.render_oid, wper.personnel_name as render_name, wrw.weld_date,"
					+ "wrw.construct_unit, u.unit_name as construct_unit_name,wrw.work_unit_oid, wu.work_unit_name, wrw.supervision_unit,"
					+ "pu.unit_name as supervision_unit_name, wrw.supervision_engineer,wrw.collection_person,wrw.collection_date, wrw.approve_status, wrw.remarks,"
					+ "wrw.create_user_id,wrw.create_user_name,wrw.create_datetime,wrw.modify_user_id,wrw.modify_user_name,	wrw.modify_datetime,wrw.active"
					+ " FROM daq_weld_rework_weld wrw "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wrw.project_oid  "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wrw.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wrw.tenders_oid "
					+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wrw.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, weld_code, active from daq_construction_weld where active=1) cw ON cw.oid = wrw.weld_oid "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wrw.supervision_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wrw.construct_unit "
					+ "LEFT JOIN (select oid, work_unit_name, active from daq_work_unit where active=1) wu ON wu.oid = wrw.work_unit_oid "
					+ "LEFT JOIN (SELECT oid, weld_produce_code, active FROM daq_weld_produce_specification where active=1) wps ON wps.oid = wrw.weld_produce "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_weld_rework_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.cover_oid like '%'||wpe.oid||'%' group by t.oid) wp ON wp.oid = wrw.oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_weld_rework_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.padder_oid like '%'||wpe.oid||'%' group by t.oid) wpe ON wpe.oid = wrw.oid "
					+ "LEFT JOIN (SELECT t.oid, array_to_string(array_agg(wpe.personnel_name),',') as personnel_name FROM daq_weld_rework_weld t LEFT JOIN ( SELECT oid, personnel_name, active FROM daq_work_personnel WHERE active = 1 ) wpe ON t.render_oid like '%'||wpe.oid||'%' group by t.oid) wper ON wper.oid = wrw.oid "
					+ "WHERE wrw.active = 1  and wrw.create_user_id='"+userId+"' and wrw.project_oid='"+ projectOid +"' and wrw.approve_status in ("+ approveStatus +") order by wrw.create_datetime,cw.weld_code desc";
		Page pageObj = new Page(pageRequest);
		String pageSql = jdbcDaoHelper.getPageSql(pageRequest, sql);
		List list = null;
		list = baseNamedParameterJdbcTemplate.queryForList(pageSql, null, ReworkWeldBo.class);
		pageObj.setResult(list);
		return pageObj;
	}

	/**
	 * <p>功能描述：APP端中线测量分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月29日 上午10:08:46。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getMeasuredResultList(int page, int rows, String projectOid, String approveStatus) {
		String userId = ThreadLocalHolder.getCurrentUserId();
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageSize(rows);
		pageRequest.setPageNo(page);
		String sql = "SELECT wmr.*,pro.project_name, pi.pipeline_name, te.tenders_name, vpsc.name as pipe_segment_or_cross_name, cw.weld_code, "
				+ "u.unit_name as construct_unit_name, pu.unit_name as supervision_unit_name, d.code_name as measure_control_point_type_name,bending.bending_code "
				+ "FROM daq_weld_measured_result wmr "
				+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wmr.project_oid "
				+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wmr.pipeline_oid "
				+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wmr.tenders_oid "
				+ "LEFT JOIN (select * from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wmr.pipe_segment_or_cross_oid "
				+ "LEFT JOIN (select oid, weld_code from v_daq_weld_for_measure) cw ON cw.oid = wmr.weld_oid "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wmr.supervision_unit "
				+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wmr.construct_unit "
				+ "LEFT JOIN (select code_id,code_name from sys_domain where active=1) d on d.code_id=wmr.measure_control_point_type "
				+ "LEFT JOIN (select * from v_daq_material_bending) bending on bending.oid=wmr.bending_oid "
				+ "WHERE wmr.active = 1 and wmr.create_user_id='"+userId+"' and wmr.project_oid='"+ projectOid +"' and wmr.approve_status in ("+ approveStatus +") order by wmr.create_datetime,wmr.measure_control_point_code desc";
		Page pageObj = new Page(pageRequest);
		String pageSql = jdbcDaoHelper.getPageSql(pageRequest, sql);
		List list = null;
		list = baseNamedParameterJdbcTemplate.queryForList(pageSql, null, WeldMeasuredResultBo.class);
		pageObj.setResult(list);
		return pageObj;
	}

	/**
	 * <p>功能描述：APP端防腐补口检查分页查询。</p>
	  * <p> 葛建。</p>	
	  * @param page
	  * @param rows
	  * @param projectOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月29日 上午10:13:01。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Page<Object> getAnticorrosionCheckList(int page, int rows, String projectOid, String approveStatus) {
		String userId = ThreadLocalHolder.getCurrentUserId();
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPageSize(rows);
		pageRequest.setPageNo(page);
		String sql = "SELECT wac.oid, wac.project_oid, pro.project_name, wac.pipeline_oid, pi.pipeline_name, wac.tenders_oid, te.tenders_name, wac.pipe_segment_or_cross_oid,"
					+ " vpsc.name as pipe_segment_or_cross_name, wac.weld_oid, cw.weld_code, to_char(wac.buckle_date, 'YYYY-MM-DD') as buckle_date, wac.buckle_anticorrosive_type,"
					+ " d.code_name as buckle_anticorrosive_type_name, wac.buckle_anticorrosive_grade, dm.code_name as buckle_anticorrosive_grade_name, wac.buckle_material_batch_num,"
					+ " wac.derusting_grade, dom.code_name as derusting_grade_name, wac.pipe_mouth_clean, wac.sandblasting_and_derusting, wac.pipe_mouth_preheat, wac.epoxy_primer,"
					+ " wac.baking_check, wac.overlap_check, wac.appearance_check, wac.electric_spark_leak_detection, wac.buckle_conclusion,"
					+ "case when wac.buckle_conclusion=1 then '合格' when wac.buckle_conclusion=0 then '不合格' end as buckle_conclusion_name, wac.anticorrosion, wac.construct_unit,"
					+ " u.unit_name as construct_unit_name, wac.supervision_unit, pu.unit_name as supervision_unit_name, wac.supervision_engineer, wac.collection_person, "
					+ "to_char(wac.collection_date, 'YYYY-MM-DD') as collection_date,case when wac.approve_status = -1 then '驳回' when wac.approve_status = 1 then '待审核' "
					+ "when wac.approve_status = 2 then '审核通过' else '未上报' end as approve_status_name, wac.remarks, wac.create_user_id, wac.create_user_name, "
					+ "to_char(wac.create_datetime,'YYYY-MM-DD') as create_datetime, wac.modify_user_id, wac.modify_user_name, wac.modify_datetime, wac.active,"
					+ "case when wac.pipe_mouth_clean=1 then '合格' when wac.pipe_mouth_clean=0 then '不合格' end as pipe_mouth_clean_name,"
					+ "case when wac.sandblasting_and_derusting=1 then '合格' when wac.sandblasting_and_derusting=0 then '不合格' end as sandblasting_and_derusting_name,"
					+ "case when wac.pipe_mouth_preheat=1 then '合格' when wac.pipe_mouth_preheat=0 then '不合格' end as pipe_mouth_preheat_name,"
					+ "case when wac.epoxy_primer=1 then '合格' when wac.epoxy_primer=0 then '不合格' end as epoxy_primer_name,"
					+ "case when wac.baking_check=1 then '合格' when wac.baking_check=0 then '不合格' end as baking_check_name,"
					+ "case when wac.overlap_check=1 then '合格' when wac.overlap_check=0 then '不合格' end as overlap_check_name,"
					+ "case when wac.appearance_check=1 then '合格' when wac.appearance_check=0 then '不合格' end as appearance_check_name,"
					+ "case when wac.electric_spark_leak_detection=1 then '合格' when wac.electric_spark_leak_detection=0 then '不合格' end as electric_spark_leak_detection_name,"
					+ "wac.approve_status FROM daq_weld_anticorrosion_check wac "
					+ "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wac.project_oid "
					+ "LEFT JOIN (SELECT oid, pipeline_name, active FROM daq_pipeline where active=1) pi ON pi.oid = wac.pipeline_oid "
					+ "LEFT JOIN (SELECT oid, tenders_name, active FROM daq_tenders where active=1) te ON te.oid = wac.tenders_oid "
					+ "LEFT JOIN (select oid,name from v_daq_pipe_segment_cross) vpsc on vpsc.oid = wac.pipe_segment_or_cross_oid "
					+ "LEFT JOIN (select oid, weld_code from v_daq_weld_info) cw ON cw.oid = wac.weld_oid "
					+ "LEFT JOIN (select code_id,code_name, active from sys_domain where active=1) d ON d.code_id = wac.buckle_anticorrosive_type "
					+ "LEFT JOIN (select code_id,code_name, active from sys_domain where active=1) dm ON dm.code_id = wac.buckle_anticorrosive_grade "
					+ "LEFT JOIN (select code_id,code_name, active from sys_domain where active=1) dom ON dom.code_id = wac.derusting_grade "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wac.construct_unit "
					+ "LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) pu on pu.oid = wac.supervision_unit "
					+ "WHERE wac.active = 1 and wac.create_user_id='"+userId+"' and wac.project_oid='"+ projectOid +"' and wac.approve_status in ("+ approveStatus +") order by wac.create_datetime,cw.weld_code desc";
		Page pageObj = new Page(pageRequest);
		String pageSql = jdbcDaoHelper.getPageSql(pageRequest, sql);
		List list = null;
		list = baseNamedParameterJdbcTemplate.queryForListHump(pageSql, Map.class);
		pageObj.setResult(list);
		return pageObj;
	}

}
