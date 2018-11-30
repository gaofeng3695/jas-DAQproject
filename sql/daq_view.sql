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
	select oid,pipe_cold_bending_code as code,pipe_length as length from daq_material_pipe_cold_bending where active=1
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
 * 焊口测量成果视图
 */
create or replace view v_daq_weld_measured_result as 
	select m.*,t.weld_code from daq_weld_measured_result m left join (select oid,weld_code from daq_construction_weld) t on t.oid=m.weld_oid;
/***
 * 中线桩连线视图
 */	
create or replace view v_daq_median_stake_polyline as
	select t.project_oid,t.pipeline_oid,ST_GeomFromText(concat('LINESTRING(',string_agg(concat_ws(' ',t.pointx,t.pointy),','),')'),4490) as geom from (select project_oid,pipeline_oid,pointx,pointy from daq_median_stake where active=1 order by mileage) t  group by t.project_oid,t.pipeline_oid;	