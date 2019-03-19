/***标段范围管理视图***/
create or replace view v_daq_scope as 
	select tt.oid,tt.project_oid,tt.parent_oid,tt.name,tt.ordernum,tt.type,tt.type_name,tt.province,a.name as province_name from (
	select t.oid,t.oid as project_oid,null as parent_oid,t.project_name as name,1 as ordernum,-1 as type,'项目' as type_name,null as province,t.create_datetime from daq_project t where t.active=1
	union all
	select t.oid,t.project_oid,t.project_oid as parent_oid,t.pipeline_name as name,2 as ordernum,0 as type,'管线' as type_name, null province,t.create_datetime from daq_pipeline t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.pipe_segment_name as name,3 as ordernum,1 as type,'线路段' as type_name,t.province,t.create_datetime from daq_pipe_segment t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.cross_name as name,4 as ordernum,2 as type,'穿跨越' as type_name,t.province,t.create_datetime from daq_cross t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.pipe_station_name as name,5 as ordernum,3 as type,'站场/阀室' as type_name,t.province,t.create_datetime from daq_pipe_station  t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.road_name as name,6 as ordernum,4 as type,'伴行道路' as type_name,t.province,t.create_datetime from daq_maintenance_road t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.power_line_name as name,7 as ordernum,5 as type,'外供电线路' as type_name,t.province,t.create_datetime from daq_power_line t where t.active=1
	) tt left join area a on tt.province=a.oid order by tt.ordernum,tt.create_datetime;
	
	/**实施范围视图**/
drop view v_daq_implement_scope;
create or replace view v_daq_implement_scope as
	select tt.oid,tt.parent_oid,tt.name,tt.type,tt.type_name,tt.project_oid,tt.tenders_oid,tt.province,a.name as province_name from (
	select t.oid,null as parent_oid,t.project_name as name,-2 as type,'项目' as type_name,t.oid as project_oid,null as province,null as tenders_oid,t.create_datetime from daq_project t where t.active=1 and t.pipe_network_type_code='pipe_network_code_001'
	union all
	select distinct t.oid,t.project_oid as parent_oid,t.tenders_name as name,-1 as type,'标段' as type_name,t.project_oid,null as province,t.oid as tenders_oid,t.create_datetime from daq_tenders_scope_ref r join daq_tenders t on r.tenders_oid=t.oid where t.active=1
	union all
	select distinct t.oid,r.tenders_oid as parent_oid,t.pipeline_name as name,0 as type,'管线' as type_name,t.project_oid,null as province,r.tenders_oid,t.create_datetime from daq_pipeline t join daq_tenders_scope_ref r on t.oid=r.pipeline_oid and t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.pipe_segment_name as name,1 as type,'线路段' as type_name,t.project_oid,t.province,r.tenders_oid,t.create_datetime from daq_pipe_segment t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.cross_name as name,2 as type,'穿跨越' as type_name,t.project_oid,t.province,r.tenders_oid,t.create_datetime from daq_cross t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.pipe_station_name as name,3 as type,'站场/阀室' as type_name,t.project_oid,t.province,r.tenders_oid,t.create_datetime from daq_pipe_station t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.road_name as name,4 as type,'伴行道路' as type_name,t.project_oid,t.province,r.tenders_oid,t.create_datetime from daq_maintenance_road t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.power_line_name as name,5 as type,'外供电线路' as type_name,t.project_oid,t.province,r.tenders_oid,t.create_datetime from daq_power_line t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all 
	select t.oid,null as parent_oid,t.project_name as name,6 as type,'中低压项目' as type_name,t.oid as project_oid,null as province,null as tenders_oid,t.create_datetime from daq_project t where t.active=1 and t.pipe_network_type_code in('pipe_network_code_002','pipe_network_code_003')
	) tt left join area a on tt.province=a.oid order by tt.type,tt.create_datetime;
	
create or replace view v_daq_pipe_segment_cross as 
	select s.oid,s.pipe_segment_name as name,s.start_stake_oid,s.end_stake_oid,1 as type,s.pipe_segment_code AS code,s.create_datetime,s.pipeline_oid from daq_pipe_segment s where s.active=1
	union all
	select s.oid,s.cross_name as name,s.start_stake_oid,s.end_stake_oid, 2 as type,s.cross_code AS code,s.create_datetime,s.pipeline_oid from daq_cross s where s.active=1;

create or replace view v_daq_weld_info as
	select oid,weld_code,pipe_segment_or_cross_oid from daq_construction_weld where active=1 and approve_status=2 
	union all 
	select oid,rework_weld_code,pipe_segment_or_cross_oid as weld_code from daq_weld_rework_weld where active=1 and approve_status=2;

create or replace view v_daq_material as
	select oid,pipe_code as code,pipe_length as length from daq_material_pipe where active=1
	union all
	select oid,hot_bends_code as code,pipe_length as length from daq_material_hot_bends where active=1
	union all 
	select oid,tee_code as code,null as length from daq_material_tee  t where active=1
	union all 
	select oid,manufacturer_code as code,null as length from daq_material_insulated_joint where active=1
	union all
	select oid,reducer_code as code,null as length from daq_material_reducer where active=1
	union all
	select oid,closure_code as code,null as length from daq_material_closure where active=1
	union all
	select t.oid,t.pipe_cold_bending_code as code,tt.pipe_length as length from daq_material_pipe_cold_bending t left join (select oid,pipe_length from daq_material_pipe) tt on t.pipe_oid=tt.oid where active=1
	union all
	select t.oid,t.valve_name,null as length from daq_material_valve t where t.active=1;
	
/***
 *焊口检测视图
 */
create or replace view v_daq_weld_detection as
	select t.oid,t.detection_report_num as code,case when t.evaluation_result=1 then '合格' else '不合格' end as evaluation_result,t.weld_oid, 1 as type from daq_detection_ray t where t.active=1 
	union all 
	select t.oid,t.detection_report_num as code,case when t.evaluation_result=1 then '合格' else '不合格' end as evaluation_result,t.weld_oid, 2 as type from daq_detection_ultrasonic t where t.active=1
	union all 
	select t.oid,t.detection_report_num as code,case when t.evaluation_result=1 then '合格' else '不合格' end as evaluation_result,t.weld_oid, 3 as type from daq_detection_infiltration t where t.active=1
	union all 
	select t.oid,t.detection_report_num as code,case when t.evaluation_result=1 then '合格' else '不合格' end as evaluation_result,t.weld_oid, 4 as type from daq_detection_magnetic_powder t where t.active=1
	union all 
	select t.oid,t.detection_report_num as code,case when t.evaluation_result=1 then '合格' else '不合格' end as evaluation_result,t.weld_oid, 5 as type from daq_detection_fa_ultrasonic t where t.active=1
	union all 
	select t.oid,t.detection_report_num as code,case when t.evaluation_result=1 then '合格' else '不合格' end as evaluation_result,t.weld_oid, 6 as type from daq_detection_pa_ultrasonic t where t.active=1
	union all 
	select t.oid,to_char(t.buckle_date,'yyyy-MM-dd') as code,s.code_name,t.weld_oid,7 as type from daq_weld_anticorrosion_check t left join (select code_id,code_name from sys_domain where domain_name='buckle_anticorrosive_type_domain') s on s.code_id=t.buckle_anticorrosive_type;

/**
 * 中线桩视图
 */
create or replace view v_daq_construction_weld as 
	select t.oid,t.weld_code,u.unit_name as construct_unit_name,to_char(t.collection_date,'yyyy-mm-dd'),wu.work_unit_code,t.geom from daq_construction_weld t 
	left join (select oid, unit_name, active from pri_unit where active=1) u on u.oid = t.construct_unit 
	left join (select oid, work_unit_code, active from daq_work_unit where active=1) wu on wu.oid = t.work_unit_oid
	where t.active=1;

/**
 * 待审核数据与权限菜单关系视图
 */
drop view if exists v_daq_approve_tip;
create or replace view v_daq_approve_tip as 
	select p.oid,p.parent_id,p.privilege_code,p.privilege_name,p.hierarchy,t.project_oid,t.supervision_unit from pri_func_privilege p 
	inner join(select project_oid,supervision_unit,privilege_code from daq_approve_tip where active=1 ) t
	on t.privilege_code=p.privilege_code where p.active=1;
	
/**
 * 创建弯管物资视图
 */
CREATE OR REPLACE VIEW v_daq_material_bending AS 
select oid,hot_bends_code as bending_code from daq_material_hot_bends where active=1 and is_use=1
union all
select oid,pipe_cold_bending_code as bending_code from daq_material_pipe_cold_bending where active=1 and is_use=1;


/**
 * 创建用于中线测量的焊口视图
 */
CREATE OR REPLACE VIEW v_daq_weld_for_measure AS 
SELECT weld.oid, weld.weld_code, weld.pipe_segment_or_cross_oid FROM daq_construction_weld weld WHERE weld.active = 1 and weld.is_rework=0 and weld.is_cut=0 
UNION ALL 
SELECT rework.oid,rework.rework_weld_code AS weld_code,rework.pipe_segment_or_cross_oid FROM daq_weld_rework_weld rework WHERE rework.active=1 and rework.is_cut=0;

/**
 * 中线测量视图
 */
/*控制点类型为焊口*/
CREATE OR REPLACE VIEW v_daq_weld_measure_result_weld AS 
select wmr.oid, pro.project_name, d.code_name as measure_control_point_type_name,wmr.measure_control_point_code,wmr.pointx, wmr.pointy,
	wmr.surfacee_levation, to_char(wmr.survey_date, 'yyyy-MM-dd'::text) AS survey_date, u.unit_name as construct_unit, wmr.geom 
from daq_weld_measured_result wmr
LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wmr.project_oid 
LEFT JOIN (select code_id,code_name from sys_domain where active=1) d on d.code_id=wmr.measure_control_point_type 
LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wmr.construct_unit 
where wmr.active=1 and wmr.measure_control_point_type='measure_control_point_type_code_001';
/*控制点类型为弯管*/
CREATE OR REPLACE VIEW v_daq_weld_measure_result_bending AS 
select wmr.oid, pro.project_name, d.code_name as measure_control_point_type_name,wmr.measure_control_point_code,wmr.pointx, wmr.pointy,
	wmr.surfacee_levation, to_char(wmr.survey_date, 'yyyy-MM-dd'::text) AS survey_date, u.unit_name as construct_unit, wmr.geom 
from daq_weld_measured_result wmr
LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wmr.project_oid 
LEFT JOIN (select code_id,code_name from sys_domain where active=1) d on d.code_id=wmr.measure_control_point_type 
LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wmr.construct_unit 
where wmr.active=1 and wmr.measure_control_point_type='measure_control_point_type_code_002';
/*控制点类型为穿越点*/
CREATE OR REPLACE VIEW v_daq_weld_measure_result_cross AS 
select wmr.oid, pro.project_name, d.code_name as measure_control_point_type_name,wmr.measure_control_point_code,wmr.pointx, wmr.pointy,
	wmr.surfacee_levation, to_char(wmr.survey_date, 'yyyy-MM-dd'::text) AS survey_date, u.unit_name as construct_unit, wmr.geom 
from daq_weld_measured_result wmr
LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wmr.project_oid 
LEFT JOIN (select code_id,code_name from sys_domain where active=1) d on d.code_id=wmr.measure_control_point_type 
LEFT JOIN (select oid, unit_name, active from pri_unit where active=1) u on u.oid = wmr.construct_unit 
where wmr.active=1 and wmr.measure_control_point_type='measure_control_point_type_code_003';

/**
 * 
 */
create or replace view v_daq_project_jasdoc_ref as 
	select j.id ||','|| project_oid as id,j.parentid||','||project_oid as parentid,j.foldername,j.foldertype,j.folderlocation,j.createtime,j.createuser,j.sort,j.hierarchy,j.deleteflag,j.isdefaultfavorite,j.deleteuser,j.updateuser,j.updatetime,j.ext_field1,j.ext_field2,j.ext_field3,j.ext_field4,j.ext_field5,j.description,j.createusername,j.updateusername,project_oid,project_name from (select oid as project_oid,project_name from daq_project where active=1) p left join(select * from jasdoc_folder where deleteflag='0' and foldertype=2 and parentid<>'0') j on 1=1
	union all 
	select j.id ||','|| project_oid as id,j.id as parentid,p.project_name as foldername,j.foldertype,j.folderlocation,j.createtime,j.createuser,j.sort,j.hierarchy,j.deleteflag,j.isdefaultfavorite,j.deleteuser,j.updateuser,j.updatetime,j.ext_field1,j.ext_field2,j.ext_field3,j.ext_field4,j.ext_field5,j.description,j.createusername,j.updateusername,p.project_oid,p.project_name from (select oid as project_oid,project_name from daq_project where active=1) p left join (select * from jasdoc_folder where deleteflag='0' and foldertype=2 and parentid='0') j on 1=1