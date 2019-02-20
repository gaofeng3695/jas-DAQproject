alter table daq_construction_weld add is_cut smallint default 0;
comment on column daq_construction_weld.is_cut is '是否割口';
alter table daq_construction_weld add is_ray smallint default 0;
comment on column daq_construction_weld.is_ray is '是否射线检测';
alter table daq_construction_weld add is_ultrasonic smallint default 0;
comment on column daq_construction_weld.is_ultrasonic is '是否超声波检查';
alter table daq_construction_weld add is_infiltration smallint default 0;
comment on column daq_construction_weld.is_infiltration is '是否渗透检测';
alter table daq_construction_weld add is_magnetic_powder smallint default 0;
comment on column daq_construction_weld.is_magnetic_powder is '是否磁粉检测';
alter table daq_construction_weld add is_fa_ultrasonic smallint default 0;
comment on column daq_construction_weld.is_fa_ultrasonic is '是否全自动检测';
alter table daq_construction_weld add is_pa_ultrasonic smallint default 0;
comment on column daq_construction_weld.is_pa_ultrasonic is '相控阵超声波检测';


alter table daq_weld_rework_weld add is_cut smallint default 0;
comment on column daq_weld_rework_weld.is_cut is '是否割口';

alter table daq_weld_rework_weld add is_ray smallint default 0;
comment on column daq_weld_rework_weld.is_ray is '是否射线检测';

alter table daq_weld_rework_weld add is_ultrasonic smallint default 0;
comment on column daq_weld_rework_weld.is_ultrasonic is '是否超声波检查';

alter table daq_weld_rework_weld add is_infiltration smallint default 0;
comment on column daq_weld_rework_weld.is_infiltration is '是否渗透检测';

alter table daq_weld_rework_weld add is_magnetic_powder smallint default 0;
comment on column daq_weld_rework_weld.is_magnetic_powder is '是否磁粉检测';

alter table daq_weld_rework_weld add is_fa_ultrasonic smallint default 0;
comment on column daq_weld_rework_weld.is_fa_ultrasonic is '是否全自动检测';

alter table daq_weld_rework_weld add is_pa_ultrasonic smallint default 0;
comment on column daq_weld_rework_weld.is_pa_ultrasonic is '相控阵超声波检测';

alter table daq_material_pipe_cold_bending add column front_is_use smallint default 0;
alter table daq_material_pipe_cold_bending add column back_is_use smallint default 0;

comment on column daq_material_pipe_cold_bending.front_is_use is '前端已焊';
comment on column daq_material_pipe_cold_bending.back_is_use is '后端已焊';

alter table daq_material_hot_bends add column materiel_code varchar(100);
alter table daq_material_hot_bends add column production_date timestamp(6);
alter table daq_material_hot_bends add column front_is_use smallint default 0;
alter table daq_material_hot_bends add column back_is_use smallint default 0;
alter table daq_material_hot_bends add column curve_length numeric(9,3);

comment on column daq_material_hot_bends.materiel_code is '物料编码';
comment on column daq_material_hot_bends.production_date is '出厂日期';
comment on column daq_material_hot_bends.front_is_use is '前端已焊';
comment on column daq_material_hot_bends.back_is_use is '后端已焊';
comment on column daq_material_hot_bends.curve_length is '曲线长度';

alter table daq_material_pipe add column materiel_code varchar(100);
alter table daq_material_pipe add column production_date timestamp(6);
alter table daq_material_pipe add column front_is_use smallint default 0;
alter table daq_material_pipe add column back_is_use smallint default 0;

comment on column daq_material_pipe.materiel_code is '物料编码';
comment on column daq_material_pipe.production_date is '出厂日期';
comment on column daq_material_pipe.front_is_use is '前端已焊';
comment on column daq_material_pipe.back_is_use is '后端已焊';

alter table daq_construction_weld add column is_anticorrosion_check smallint default 0;
alter table daq_construction_weld add column is_rework smallint default 0;
comment on column daq_construction_weld.is_anticorrosion_check is '是否补扣';
comment on column daq_construction_weld.is_rework is '是否返修';


alter table daq_material_pipe add is_check smallint not null default 0;
comment on column daq_material_pipe.is_check is '是否检查';

alter table daq_material_hot_bends add is_check smallint not null default 0;
comment on column daq_material_hot_bends.is_check is '是否检查';

alter table daq_material_pipe_cold_bending add is_check smallint not null default 0;
comment on column daq_material_pipe_cold_bending.is_check is '是否检查';

alter table pri_role add data_filter_code varchar(45);
comment on column pri_role.data_filter_code is '数据权限过滤规则';

alter table daq_weld_produce_specification add construct_unit varchar(36);

alter table custom_fun_fields add column precision int2;

comment on column custom_fun_fields.precision is 'xiao shu dian bao liu ji wei';

alter table custom_fun_fields alter column child_field type varchar(100) ;


/**********物资基本信息和检查信息添加提交状态字段start**********/
alter table daq_material_pipe add column commit_status int2 default 0;
comment on column daq_material_pipe.commit_status is '提交状态';
alter table daq_material_hot_bends add column commit_status int2 default 0;
comment on column daq_material_hot_bends.commit_status is '提交状态';
alter table daq_material_tee add column commit_status int2 default 0;
comment on column daq_material_tee.commit_status is '提交状态';
alter table daq_material_insulated_joint add column commit_status int2 default 0;
comment on column daq_material_insulated_joint.commit_status is '提交状态';
alter table daq_material_closure add column commit_status int2 default 0;
comment on column daq_material_closure.commit_status is '提交状态';
alter table daq_material_reducer add column commit_status int2 default 0;
comment on column daq_material_reducer.commit_status is '提交状态';
alter table daq_material_flange add column commit_status int2 default 0;
comment on column daq_material_flange.commit_status is '提交状态';

alter table daq_check_coating_pipe add column commit_status int2 default 0;
comment on column daq_check_coating_pipe.commit_status is '提交状态';
alter table daq_check_hot_bends add column commit_status int2 default 0;
comment on column daq_check_hot_bends.commit_status is '提交状态';
alter table daq_check_pipe_cold_bending add column commit_status int2 default 0;
comment on column daq_check_pipe_cold_bending.commit_status is '提交状态';
alter table daq_check_tee add column commit_status int2 default 0;
comment on column daq_check_tee.commit_status is '提交状态';
alter table daq_check_insulated_joint add column commit_status int2 default 0;
comment on column daq_check_insulated_joint.commit_status is '提交状态';
alter table daq_check_reducer add column commit_status int2 default 0;
comment on column daq_check_reducer.commit_status is '提交状态';
/**********物资基本信息和检查信息添加提交状态字段end**********/

/*******焊口测量成果改为中线测量成果start***********/
ALTER TABLE daq_weld_measured_result DROP COLUMN IF EXISTS work_unit_oid;

alter table daq_weld_measured_result add column measure_control_point_type varchar(36) ;
comment on column daq_weld_measured_result.measure_control_point_type is '测量控制点类型';
alter table daq_weld_measured_result add column measure_control_point_code varchar(50) ;
comment on column daq_weld_measured_result.measure_control_point_code is '测量控制点编号';
alter table daq_weld_measured_result add column bending_oid varchar(36) ;
comment on column daq_weld_measured_result.bending_oid is '弯管编号';
/*******焊口测量成果改为中线测量成果end***********/