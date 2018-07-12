/*
 *施工数据采集产品数据库表 
 */
/**********范围管理数据表begin***************/
CREATE TABLE daq_project (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_name VARCHAR (50) NOT NULL,
	project_code VARCHAR (50) NOT NULL,
	pipe_network_type_code VARCHAR (50),
	medium_type_code VARCHAR (50),
	construct VARCHAR (50),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_project is '项目表';
comment on column daq_project.oid is '主键';
comment on column daq_project.project_name is '项目名称';
comment on column daq_project.project_code is '项目编号';
comment on column daq_project.pipe_network_type_code is '管网类型编码';
comment on column daq_project.medium_type_code is '介质类型编号';
comment on column daq_project.construct is '建设单位编号';
comment on column daq_project.remarks is '备注';

create index INDEX_DAQ_PROJECT_PROJECT_NAME_5 ON daq_project ( project_name );
create index INDEX_DAQ_PROJECT_PROJECT_CODE_6 ON daq_project ( project_code );

CREATE TABLE daq_pipeline (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	pipeline_name VARCHAR (50) NOT NULL,
	pipeline_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36),
	pipeline_type VARCHAR (30),
	pipeline_length NUMERIC (6, 2),
	pipeline_grade VARCHAR (30),
	design_pressure VARCHAR (25),
	start_point VARCHAR (25),
	end_point VARCHAR (25),
	design_flowrate NUMERIC (3, 0),
	pipeline_diameter NUMERIC (5, 1),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_pipeline is '管线表';
comment on column daq_pipeline.oid is '主键';
comment on column daq_pipeline.pipeline_name is '管线名称';
comment on column daq_pipeline.pipeline_code is '管线编号';
comment on column daq_pipeline.project_oid is '项目oid';
comment on column daq_pipeline.pipeline_type is '管线类型';
comment on column daq_pipeline.pipeline_length is '管线长度(km)';
comment on column daq_pipeline.pipeline_grade is '钢级';
comment on column daq_pipeline.design_pressure is '设计压力(mpa)';
comment on column daq_pipeline.start_point is '起点';
comment on column daq_pipeline.end_point is '终点';
comment on column daq_pipeline.design_flowrate is '设计流量设计输量(108m3/a)';
comment on column daq_pipeline.pipeline_diameter is '管径(mm)';
comment on column daq_pipeline.remarks is '备注';

create index INDEX_DAQ_PIPELINE_PIPELINE_NAME_5 ON daq_pipeline ( pipeline_name );
create index INDEX_DAQ_PIPELINE_PIPELINE_CODE_6 ON daq_pipeline ( pipeline_code );

CREATE TABLE daq_pipe_segment (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	pipe_segment_name VARCHAR (50) NOT NULL,
	pipe_segment_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	pipeline_oid VARCHAR (36) NOT NULL,
	start_stake_oid VARCHAR (36),
	end_stake_oid VARCHAR (36),
	province VARCHAR (60),
	city VARCHAR (60),
	county VARCHAR (60),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_pipe_segment is '线路段表';
comment on column daq_pipe_segment.oid is '主键';
comment on column daq_pipe_segment.pipe_segment_name is '线路段名称';
comment on column daq_pipe_segment.pipe_segment_code is '线路段编号';
comment on column daq_pipe_segment.project_oid is '项目oid';
comment on column daq_pipe_segment.pipeline_oid is '管线oid';
comment on column daq_pipe_segment.start_stake_oid is '起始桩oid';
comment on column daq_pipe_segment.end_stake_oid is '终点桩oid';
comment on column daq_pipe_segment.province is '所属省';
comment on column daq_pipe_segment.city is '市';
comment on column daq_pipe_segment.county is '区/县';
comment on column daq_pipe_segment.remarks is '备注';
create index INDEX_DAQ_PIPE_SEGMENT_PIPE_SEGMENT_NAME_5 ON daq_pipe_segment ( pipe_segment_name );
create index INDEX_DAQ_PIPE_SEGMENT_PIPE_SEGMENT_CODE_6 ON daq_pipe_segment ( pipe_segment_code );

CREATE TABLE daq_cross (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	cross_name VARCHAR (50) NOT NULL,
	cross_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	pipeline_oid VARCHAR (36) NOT NULL,
	cross_type_code VARCHAR (36),
	cross_way_code VARCHAR (36),
	cross_length NUMERIC (8, 1),
	start_stake_oid VARCHAR (36),
	end_stake_oid VARCHAR (36),
	province VARCHAR (60),
	city VARCHAR (60),
	county VARCHAR (60),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_cross is '穿跨越表';
comment on column daq_cross.oid is '主键';
comment on column daq_cross.cross_name is '穿跨越名称';
comment on column daq_cross.cross_code is '穿跨越编号';
comment on column daq_cross.project_oid is '项目oid';
comment on column daq_cross.pipeline_oid is '管线oid';
comment on column daq_cross.cross_type_code is '穿跨越类型编号';
comment on column daq_cross.cross_way_code is '穿跨越方式编号';
comment on column daq_cross.cross_length is '穿跨越长度';
comment on column daq_cross.start_stake_oid is '起始桩oid';
comment on column daq_cross.end_stake_oid is '终点桩oid';
comment on column daq_cross.province is '所属省';
comment on column daq_cross.city is '市';
comment on column daq_cross.county is '区/县';
comment on column daq_cross.remarks is '备注';
create index INDEX_DAQ_CROSS_CROSS_NAME_5 ON daq_cross ( cross_name );
create index INDEX_DAQ_CROSS_CROSS_CODE_6 ON daq_cross ( cross_code );

CREATE TABLE daq_pipe_station (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	pipe_station_name VARCHAR (50) NOT NULL,
	pipe_station_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	pipeline_oid VARCHAR (36) NOT NULL,
	pipe_station_classification VARCHAR (36),
	pipe_station_type VARCHAR (36),
	province VARCHAR (60),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_pipe_station is '站场/阀室表';
comment on column daq_pipe_station.oid is '主键';
comment on column daq_pipe_station.pipe_station_name is '站场/阀室名称';
comment on column daq_pipe_station.pipe_station_code is '站场/阀室编号';
comment on column daq_pipe_station.project_oid is '项目oid';
comment on column daq_pipe_station.pipeline_oid is '管线oid';
comment on column daq_pipe_station.pipe_station_classification is '分类';
comment on column daq_pipe_station.pipe_station_type is '类型';
comment on column daq_pipe_station.province is '所属省';
comment on column daq_pipe_station.remarks is '备注';
create index INDEX_DAQ_PIPE_STATION_PIPE_STATION_NAME_5 ON daq_pipe_station ( pipe_station_name );
create index INDEX_DAQ_PIPE_STATION_PIPE_STATION_CODE_6 ON daq_pipe_station ( pipe_station_code );

CREATE TABLE daq_maintenance_road (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	road_name VARCHAR (50) NOT NULL,
	road_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	pipeline_oid VARCHAR (36) NOT NULL,
	road_type VARCHAR (30),
	road_grade VARCHAR (30),
	road_length NUMERIC (4, 1),
	land_area NUMERIC (10, 2),
	province VARCHAR (60),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_maintenance_road is '伴行道路管理表';
comment on column daq_maintenance_road.oid is '主键';
comment on column daq_maintenance_road.road_name is '伴行道路名称';
comment on column daq_maintenance_road.road_code is '伴行道路编号';
comment on column daq_maintenance_road.project_oid is '项目oid';
comment on column daq_maintenance_road.pipeline_oid is '管线oid';
comment on column daq_maintenance_road.road_type is '道路类型';
comment on column daq_maintenance_road.road_grade is '道路等级';
comment on column daq_maintenance_road.road_length is '长度(km)';
comment on column daq_maintenance_road.land_area is '用地面积(㎡)';
comment on column daq_maintenance_road.province is '所属省';
comment on column daq_maintenance_road.remarks is '备注';
create index INDEX_DAQ_MAINTENANCE_ROAD_ROAD_NAME_5 ON daq_maintenance_road ( road_name );
create index INDEX_DAQ_MAINTENANCE_ROAD_ROAD_CODE_6 ON daq_maintenance_road ( road_code );

CREATE TABLE daq_power_line (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	power_line_name VARCHAR (50) NOT NULL,
	power_line_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	pipeline_oid VARCHAR (36) NOT NULL,
	power_supply_name VARCHAR (50),
	line_length NUMERIC (6, 0),
	tower_type VARCHAR (50),
	tower_total NUMERIC (6, 0),
	province VARCHAR(36),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_power_line is '外供电线路表';
comment on column daq_power_line.oid is '主键';
comment on column daq_power_line.power_line_name is '外供电线路名称';
comment on column daq_power_line.power_line_code is '外供电线路编号';
comment on column daq_power_line.project_oid is '项目oid';
comment on column daq_power_line.pipeline_oid is '管线oid';
comment on column daq_power_line.power_supply_name is '电源点名称';
comment on column daq_power_line.line_length is '线路长度(m)';
comment on column daq_power_line.tower_type is '杆塔类型';
comment on column daq_power_line.tower_total is '杆塔总基数';
comment on column daq_power_line.province is '所属省';
comment on column daq_power_line.remarks is '备注';
create index INDEX_DAQ_POWER_LINE_POWER_LINE_NAME_5 ON daq_power_line ( power_line_name );
create index INDEX_DAQ_POWER_LINE_POWER_LINE_CODE_6 ON daq_power_line ( power_line_code );

CREATE TABLE daq_tenders (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	tenders_name VARCHAR (50) NOT NULL,
	tenders_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	manage_model VARCHAR (30),
	start_stake_oid VARCHAR (36),
	end_stake_oid VARCHAR (36),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_tenders is '标段表';
comment on column daq_tenders.oid is '主键';
comment on column daq_tenders.tenders_name is '标段名称';
comment on column daq_tenders.tenders_code is '标段编号';
comment on column daq_tenders.project_oid is '项目oid';
comment on column daq_tenders.manage_model is '建设管理模式';
comment on column daq_tenders.start_stake_oid is '起始桩号';
comment on column daq_tenders.end_stake_oid is '终点桩号';
comment on column daq_tenders.remarks is '备注';
create index INDEX_DAQ_TENDERS_TENDERS_NAME_5 ON daq_tenders ( tenders_name );
create index INDEX_DAQ_TENDERS_TENDERS_CODE_6 ON daq_tenders ( tenders_code );

CREATE TABLE daq_median_stake (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	median_stake_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	pipeline_oid VARCHAR (36) NOT NULL,
	mileage NUMERIC (10, 3),
	mark_stone_type VARCHAR (38),
	mark_stone_location VARCHAR (150),
	pointx NUMERIC (17, 9),
	pointy NUMERIC (17, 9),
	pointz NUMERIC (7, 3),
	geo_state VARCHAR (10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_median_stake is '中线桩表';
comment on column daq_median_stake.oid is '主键';
comment on column daq_median_stake.median_stake_code is '中线桩编号';
comment on column daq_median_stake.project_oid is '项目oid';
comment on column daq_median_stake.pipeline_oid is '管线oid';
comment on column daq_median_stake.mileage is '里程(km)';
comment on column daq_median_stake.mark_stone_type is '标石类型';
comment on column daq_median_stake.mark_stone_location is '标石概略位置';
comment on column daq_median_stake.pointx is 'X坐标';
comment on column daq_median_stake.pointy is 'X坐标';
comment on column daq_median_stake.pointz is '高程';
comment on column daq_median_stake.remarks is '备注';
comment on column daq_median_stake.geo_state is '空间数据状态';
create index INDEX_DAQ_MEDIAN_STAKE_MEDIAN_STAKE_CODE_5 ON daq_median_stake ( median_stake_code );

select dropgeometrycolumn('public', 'daq_median_stake', 'geom');
select AddGeometryColumn('public', 'daq_median_stake', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_median_stake_geom_idx ON public.daq_median_stake USING gist (geom);

/***标段范围管理视图***/
create or replace view v_daq_scope as select tt.*,a.name as province_name from (
	select t.oid,t.oid as project_oid,null as parent_oid,t.project_name as name,1 as ordernum,-1 as type,'项目' as type_name,null as province from daq_project t where t.active=1
	union all
	select t.oid,t.project_oid,t.project_oid as parent_oid,t.pipeline_name as name,2 as ordernum,0 as type,'管线' as type_name, null province from daq_pipeline t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.pipe_segment_name as name,3 as ordernum,1 as type,'线路段' as type_name,t.province from daq_pipe_segment t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.cross_name as name,4 as ordernum,2 as type,'穿跨越' as type_name,t.province from daq_cross t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.pipe_station_name as name,5 as ordernum,3 as type,'站场/阀室' as type_name,t.province from daq_pipe_station  t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.road_name as name,6 as ordernum,4 as type,'伴行道路' as type_name,t.province from daq_maintenance_road t where t.active=1
	union all
	select t.oid,t.project_oid,t.pipeline_oid as parent_oid,t.power_line_name as name,7 as ordernum,5 as type,'外供电线路' as type_name,t.province from daq_power_line t where t.active=1
	) tt left join area a on tt.province=a.oid order by tt.ordernum

CREATE TABLE daq_tenders_scope_ref (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	scope_oid VARCHAR (36),
	scope_type VARCHAR (10),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_tenders_scope_ref is '标段范围关联表';
comment on column daq_tenders_scope_ref.oid is '主键';
comment on column daq_tenders_scope_ref.pipeline_oid is '管线oid';
comment on column daq_tenders_scope_ref.tenders_oid is '标段oid';
comment on column daq_tenders_scope_ref.scope_oid is '范围oid（即线路段oid或者站场oid等）';
comment on column daq_tenders_scope_ref.scope_type is '范围类型（1：线路段，2：创跨越，3：站场/阀室，4：伴行道路，5：外供电线路）';
comment on column daq_tenders_scope_ref.create_user_id is '创建人id';
comment on column daq_tenders_scope_ref.create_user_name is '创建人名称';
comment on column daq_tenders_scope_ref.create_datetime is '创建时间';
comment on column daq_tenders_scope_ref.modify_user_id is '修改人id';
comment on column daq_tenders_scope_ref.modify_user_name is '修改人名称';
comment on column daq_tenders_scope_ref.modify_datetime is '修改时间';
comment on column daq_tenders_scope_ref.active is '有效标志';
create index INDEX_DAQ_TENDERS_SCOPE_REF_TENDERS_OID_5 ON daq_tenders_scope_ref ( tenders_oid );
create index INDEX_DAQ_TENDERS_SCOPE_REF_SCOPE_OID_6 ON daq_tenders_scope_ref ( scope_oid );

/**********范围管理数据表end***************/
/**********权限管理数据表begin***************/
/**实施范围视图**/
create or replace view v_daq_implement_scope as 
	select tt.*,a.name as province_name from (select t.oid,null as parent_oid,t.project_name as name,-2 as type,'项目' as type_name,t.oid as project_oid,null as province from daq_project t where t.active=1
	union all
	select distinct t.oid,t.project_oid as parent_oid,t.tenders_name as name,-1 as type,'标段' as type_name,t.project_oid,null as province from daq_tenders_scope_ref r join daq_tenders t on r.tenders_oid=t.oid where t.active=1
	union all
	select distinct t.oid,r.tenders_oid as parent_oid,t.pipeline_name as name,0 as type,'管线' as type_name,t.project_oid,null as province from daq_pipeline t join daq_tenders_scope_ref r on t.oid=r.pipeline_oid and t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.pipe_segment_name as name,1 as type,'线路段' as type_name,t.project_oid,t.province from daq_pipe_segment t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.cross_name as name,2 as type,'穿跨越' as type_name,t.project_oid,t.province from daq_cross t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.pipe_station_name as name,3 as type,'站场/阀室' as type_name,t.project_oid,t.province from daq_pipe_station t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.road_name as name,4 as type,'伴行道路' as type_name,t.project_oid,t.province from daq_maintenance_road t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	union all
	select t.oid,t.pipeline_oid as parent_oid,t.power_line_name as name,5 as type,'外供电线路' as type_name,t.project_oid,t.province from daq_power_line t join daq_tenders_scope_ref r on t.oid=r.scope_oid where t.active=1
	) tt left join area a on tt.province=a.oid order by tt.type
	
CREATE TABLE daq_implement_scope_ref (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	unit_oid VARCHAR (36),
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	scope_oid VARCHAR (36),
	scope_type VARCHAR (10),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);

comment on table daq_implement_scope_ref is '实施范围关联表';
comment on column daq_implement_scope_ref.oid is '主键';
comment on column daq_implement_scope_ref.unit_oid is '部门oid';
comment on column daq_implement_scope_ref.project_oid is '项目oid';
comment on column daq_implement_scope_ref.pipeline_oid is '管线oid';
comment on column daq_implement_scope_ref.scope_oid is '实体oid（即线路段oid或者站场oid等）';
comment on column daq_implement_scope_ref.scope_type is '实体类型（1：线路段，2：创跨越，3：站场/阀室，4：伴行道路，5：外供电线路）';
comment on column daq_implement_scope_ref.create_user_id is '创建人id';
comment on column daq_implement_scope_ref.create_user_name is '创建人名称';
comment on column daq_implement_scope_ref.create_datetime is '创建时间';
comment on column daq_implement_scope_ref.modify_user_id is '修改人id';
comment on column daq_implement_scope_ref.modify_user_name is '修改人名称';
comment on column daq_implement_scope_ref.modify_datetime is '修改时间';
comment on column daq_implement_scope_ref.active is '有效标志';
create index INDEX_DAQ_IMPLEMENT_SCOPE_REF_UNIT_OID_5 ON daq_implement_scope_ref ( unit_oid );
create index INDEX_DAQ_IMPLEMENT_SCOPE_REF_PROJECT_OID_6 ON daq_implement_scope_ref ( project_oid );

/**********权限管理数据表end***************/

/**********基础数据维护begin***************/
CREATE TABLE daq_weld_produce_specification (
	oid VARCHAR (36) NOT NULL,
	weld_produce_code VARCHAR (50) NOT NULL,
	project_oid VARCHAR (36),
	applicable_type VARCHAR (50),
	welding_method_temp VARCHAR (50),
	pipe_diameter NUMERIC (5, 1),
	pipe_ply NUMERIC (5, 1),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_weld_produce_specification IS '焊接工艺规程表';
comment on column daq_weld_produce_specification.oid IS '主键';
comment on column daq_weld_produce_specification.weld_produce_code IS '焊接工艺规程编号';
comment on column daq_weld_produce_specification.project_oid IS '项目oid';
comment on column daq_weld_produce_specification.applicable_type IS '适用类型';
comment on column daq_weld_produce_specification.welding_method_temp IS '焊接方法';
comment on column daq_weld_produce_specification.pipe_diameter IS '管材管径（mm）';
comment on column daq_weld_produce_specification.pipe_ply IS '管壁厚度（mm）';
comment on column daq_weld_produce_specification.remarks IS '备注';
comment on column daq_weld_produce_specification.create_user_id IS '创建人id';
comment on column daq_weld_produce_specification.create_user_name IS '创建人名称';
comment on column daq_weld_produce_specification.create_datetime IS '创建时间';
comment on column daq_weld_produce_specification.modify_user_id IS '修改人id';
comment on column daq_weld_produce_specification.modify_user_name IS '修改人名称';
comment on column daq_weld_produce_specification.modify_datetime IS '修改时间';
comment on column daq_weld_produce_specification.active IS '有效标志';

CREATE TABLE daq_work_personnel (
	oid VARCHAR (36) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	work_unit_oid VARCHAR (36),
	personnel_name VARCHAR (50),
	personnel_code VARCHAR (50),
	personnel_type VARCHAR (50),
	id_card_no VARCHAR (30),
	certificate_number VARCHAR (50),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_work_personnel IS '机组人员表';
comment on column daq_work_personnel.oid IS '主键';
comment on column daq_work_personnel.project_oid IS '项目oid';
comment on column daq_work_personnel.work_unit_oid IS '机组oid';
comment on column daq_work_personnel.personnel_name IS '人员名称';
comment on column daq_work_personnel.personnel_code IS '人员编号';
comment on column daq_work_personnel.personnel_type IS '人员类别';
comment on column daq_work_personnel.id_card_no IS '身份证号';
comment on column daq_work_personnel.certificate_number IS '证书编号';
comment on column daq_work_personnel.remarks IS '备注';
comment on column daq_work_personnel.create_user_id IS '创建人id';
comment on column daq_work_personnel.create_user_name IS '创建人名称';
comment on column daq_work_personnel.create_datetime IS '创建时间';
comment on column daq_work_personnel.modify_user_id IS '修改人id';
comment on column daq_work_personnel.modify_user_name IS '修改人名称';
comment on column daq_work_personnel.modify_datetime IS '修改时间';
comment on column daq_work_personnel.active IS '有效标志';

CREATE TABLE daq_work_unit (
	oid VARCHAR (36) NOT NULL,
	project_oid VARCHAR (36) NOT NULL,
	work_unit_name VARCHAR (50),
	work_unit_code VARCHAR (50),
	work_unit_type VARCHAR (50),
	construct_unit VARCHAR (50),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_work_unit IS '施工机组表';
comment on column daq_work_unit.oid IS '主键';
comment on column daq_work_unit.project_oid IS '项目oid';
comment on column daq_work_unit.work_unit_name IS '机组名称';
comment on column daq_work_unit.work_unit_code IS '机组编号';
comment on column daq_work_unit.work_unit_type IS '机组类型';
comment on column daq_work_unit.construct_unit IS '施工单位';
comment on column daq_work_unit.remarks IS '备注';
comment on column daq_work_unit.create_user_id IS '创建人id';
comment on column daq_work_unit.create_user_name IS '创建人名称';
comment on column daq_work_unit.create_datetime IS '创建时间';
comment on column daq_work_unit.modify_user_id IS '修改人id';
comment on column daq_work_unit.modify_user_name IS '修改人名称';
comment on column daq_work_unit.modify_datetime IS '修改时间';
CREATE INDEX index_daq_weld_produce_specification_weld_produce_code_5 ON daq_weld_produce_specification USING btree (weld_produce_code);
CREATE INDEX index_daq_weld_produce_specification_project_oid_6 ON daq_weld_produce_specification USING btree (project_oid);
ALTER TABLE daq_weld_produce_specification ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_work_personnel_project_oid_5 ON daq_work_personnel USING btree (project_oid);
CREATE INDEX index_daq_work_personnel_work_unit_oid_6 ON daq_work_personnel USING btree (work_unit_oid);
CREATE INDEX index_daq_work_personnel_personnel_name_7 ON daq_work_personnel USING btree (personnel_name);
ALTER TABLE daq_work_personnel ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_work_unit_project_oid_5 ON daq_work_unit USING btree (project_oid);
CREATE INDEX index_daq_work_unit_work_unit_name_6 ON daq_work_unit USING btree (work_unit_name);
CREATE INDEX index_daq_work_unit_work_unit_code_7 ON daq_work_unit USING btree (work_unit_code);
ALTER TABLE daq_work_unit ADD PRIMARY KEY (oid);
/**********基础数据维护end***************/
/**********线路物资基本信息begin***************/

DROP TABLE IF EXISTS daq_material_closure;
CREATE TABLE daq_material_closure (
	oid varchar(36) NOT NULL,
	closure_code varchar(36),
	closure_type varchar(60),
	material varchar(50),
	steel_grade varchar(50),
	outside_diameter numeric(9,3),
	wall_thickness numeric(9,3),
	connection_methods varchar(50),
	coating_methods varchar(50),
	manufacturer varchar(50),
	manufacturer_date timestamp(6),
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	is_use SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_material_closure IS '封堵物信息表';
comment on column daq_material_closure.oid IS '主键';
comment on column daq_material_closure.closure_code IS '封堵物编号';
comment on column daq_material_closure.closure_type IS '封堵物类型';
comment on column daq_material_closure.material IS '材质';
comment on column daq_material_closure.steel_grade IS '钢材等级';
comment on column daq_material_closure.outside_diameter IS '管道外壁直径(mm)';
comment on column daq_material_closure.wall_thickness IS '管道壁厚(mm)';
comment on column daq_material_closure.connection_methods IS '连接方式';
comment on column daq_material_closure.coating_methods IS '防腐方式';
comment on column daq_material_closure.manufacturer IS '生产厂家';
comment on column daq_material_closure.manufacturer_date IS '生产日期';
comment on column daq_material_closure.project_oid IS '项目oid';
comment on column daq_material_closure.pipeline_oid IS '管线oid';
comment on column daq_material_closure.tenders_oid IS '标段oid';
comment on column daq_material_closure.is_use IS '是否使用';
comment on column daq_material_closure.remarks IS '备注';
comment on column daq_material_closure.create_user_id IS '创建人id';
comment on column daq_material_closure.create_user_name IS '创建人名称';
comment on column daq_material_closure.create_datetime IS '创建时间';
comment on column daq_material_closure.modify_user_id IS '修改人id';
comment on column daq_material_closure.modify_user_name IS '修改人名称';
comment on column daq_material_closure.modify_datetime IS '修改时间';
comment on column daq_material_closure.active IS '有效标志';

CREATE TABLE daq_material_flange (
	oid varchar(36) NOT NULL,
	flange_code varchar(36),
	flange_type varchar(60),
	material varchar(50),
	pressure_grade varchar(50),
	specification varchar(30),
	criterion varchar(30),
	connection_methods varchar(50),
	coating_methods varchar(50),
	manufacturer varchar(50),
	manufacturer_date timestamp(6),
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	is_use SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_material_flange IS '法兰信息表';
comment on column daq_material_flange.oid IS '主键';
comment on column daq_material_flange.flange_code IS '法兰编号';
comment on column daq_material_flange.flange_type IS '法兰类型';
comment on column daq_material_flange.material IS '材质';
comment on column daq_material_flange.pressure_grade IS '压力等级';
comment on column daq_material_flange.specification IS '规格型号';
comment on column daq_material_flange.criterion IS '标准';
comment on column daq_material_flange.connection_methods IS '连接方式';
comment on column daq_material_flange.coating_methods IS '防腐方式';
comment on column daq_material_flange.manufacturer IS '生产厂家';
comment on column daq_material_flange.manufacturer_date IS '生产日期';
comment on column daq_material_flange.project_oid IS '项目oid';
comment on column daq_material_flange.pipeline_oid IS '管线oid';
comment on column daq_material_flange.tenders_oid IS '标段oid';
comment on column daq_material_flange.is_use IS '是否使用';
comment on column daq_material_flange.remarks IS '备注';
comment on column daq_material_flange.create_user_id IS '创建人id';
comment on column daq_material_flange.create_user_name IS '创建人名称';
comment on column daq_material_flange.create_datetime IS '创建时间';
comment on column daq_material_flange.modify_user_id IS '修改人id';
comment on column daq_material_flange.modify_user_name IS '修改人名称';
comment on column daq_material_flange.modify_datetime IS '修改时间';
comment on column daq_material_flange.active IS '有效标志';

DROP TABLE IF EXISTS daq_material_hot_bends;
CREATE TABLE daq_material_hot_bends (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	hot_bends_code varchar(50),
	technology varchar(50),
	specification varchar(50),
	diameter numeric(9,3),
	wall_thickness numeric(9,3),
	pipe_length numeric(12,3),
	weight numeric(12,3),
	angle_bending numeric(9,3),
	straight_length numeric(9,3),
	manufacture_date timestamp(6),
	manufacture_factory varchar(50),
	quality_certificate_num varchar(60),
	steel_pipe_code varchar(50),
	material varchar(50),
	pipe_grade varchar(50),
	stove_serial_num varchar(50),
	pipe_forming_method varchar(50),
	external_coating_type varchar(50),
	external_coating_grade varchar(50),
	internal_coating_material varchar(50),
	internal_coating_thinkness numeric(9,3),
	pipe_manufacture_date timestamp(6),
	steel_plate_num varchar(60),
	steel_plate_quality_certificate_num varchar(60),
	coating_num varchar(50),
	coating_date timestamp(6),
	coating_certificate_num varchar(60),
	coating_factory varchar(70),
	is_use SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_material_hot_bends IS '热煨弯管表';
comment on column daq_material_hot_bends.oid IS '主键';
comment on column daq_material_hot_bends.project_oid IS '项目oid';
comment on column daq_material_hot_bends.pipeline_oid IS '管线oid';
comment on column daq_material_hot_bends.tenders_oid IS '标段oid';
comment on column daq_material_hot_bends.hot_bends_code IS '弯管编号';
comment on column daq_material_hot_bends.technology IS '制造工艺';
comment on column daq_material_hot_bends.specification IS '规格';
comment on column daq_material_hot_bends.diameter IS '管径(mm)';
comment on column daq_material_hot_bends.wall_thickness IS '壁厚(mm）';
comment on column daq_material_hot_bends.pipe_length IS '长度(m)';
comment on column daq_material_hot_bends.weight IS '重量(吨)';
comment on column daq_material_hot_bends.angle_bending IS '弯制角度(°)';
comment on column daq_material_hot_bends.straight_length IS '直管段长度(m)';
comment on column daq_material_hot_bends.manufacture_date IS '弯管生产日期';
comment on column daq_material_hot_bends.manufacture_factory IS '弯管生产厂家';
comment on column daq_material_hot_bends.quality_certificate_num IS '弯管合格证编号';
comment on column daq_material_hot_bends.steel_pipe_code IS '钢管编号';
comment on column daq_material_hot_bends.material IS '材质';
comment on column daq_material_hot_bends.pipe_grade IS '管材等级';
comment on column daq_material_hot_bends.stove_serial_num IS '炉批号';
comment on column daq_material_hot_bends.pipe_forming_method IS '钢管成型方式';
comment on column daq_material_hot_bends.external_coating_type IS '外防腐类型';
comment on column daq_material_hot_bends.external_coating_grade IS '外防腐等级';
comment on column daq_material_hot_bends.internal_coating_material IS '内防腐材料';
comment on column daq_material_hot_bends.internal_coating_thinkness IS '内涂层厚度（mm）';
comment on column daq_material_hot_bends.pipe_manufacture_date IS '钢管生产日期';
comment on column daq_material_hot_bends.steel_plate_num IS '钢板号';
comment on column daq_material_hot_bends.steel_plate_quality_certificate_num IS '钢板质量证明书编号';
comment on column daq_material_hot_bends.coating_num IS '防腐号';
comment on column daq_material_hot_bends.coating_date IS '防腐日期';
comment on column daq_material_hot_bends.coating_certificate_num IS '防腐证书编号';
comment on column daq_material_hot_bends.coating_factory IS '防腐加工厂家';
comment on column daq_material_hot_bends.is_use IS '是否使用';
comment on column daq_material_hot_bends.remarks IS '备注';
comment on column daq_material_hot_bends.create_user_id IS '创建人id';
comment on column daq_material_hot_bends.create_user_name IS '创建人名称';
comment on column daq_material_hot_bends.create_datetime IS '创建时间';
comment on column daq_material_hot_bends.modify_user_id IS '修改人id';
comment on column daq_material_hot_bends.modify_user_name IS '修改人名称';
comment on column daq_material_hot_bends.modify_datetime IS '修改时间';
comment on column daq_material_hot_bends.active IS '有效标志';

CREATE TABLE daq_material_insulated_joint (
	oid varchar(36) NOT NULL,
	manufacturer_code varchar(36),
	certification_num varchar(60),
	diameter numeric(9,3),
	pressure numeric(9,3),
	insulated_resistance numeric(9,3),
	manufacturer varchar(50),
	manufacturer_date timestamp(6),
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	is_use SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_material_insulated_joint IS '绝缘接头信息表';
comment on column daq_material_insulated_joint.oid IS '主键';
comment on column daq_material_insulated_joint.manufacturer_code IS '出厂编号';
comment on column daq_material_insulated_joint.certification_num IS '合格证编号';
comment on column daq_material_insulated_joint.diameter IS '公称直径(mm)';
comment on column daq_material_insulated_joint.pressure IS '公称压力(MPa)';
comment on column daq_material_insulated_joint.insulated_resistance IS '绝缘电阻(MΩ)';
comment on column daq_material_insulated_joint.manufacturer IS '生产厂家';
comment on column daq_material_insulated_joint.manufacturer_date IS '生产日期';
comment on column daq_material_insulated_joint.project_oid IS '项目oid';
comment on column daq_material_insulated_joint.pipeline_oid IS '管线oid';
comment on column daq_material_insulated_joint.tenders_oid IS '标段oid';
comment on column daq_material_insulated_joint.is_use IS '是否使用';
comment on column daq_material_insulated_joint.remarks IS '备注';
comment on column daq_material_insulated_joint.create_user_id IS '创建人id';
comment on column daq_material_insulated_joint.create_user_name IS '创建人名称';
comment on column daq_material_insulated_joint.create_datetime IS '创建时间';
comment on column daq_material_insulated_joint.modify_user_id IS '修改人id';
comment on column daq_material_insulated_joint.modify_user_name IS '修改人名称';
comment on column daq_material_insulated_joint.modify_datetime IS '修改时间';
comment on column daq_material_insulated_joint.active IS '有效标志';

CREATE TABLE daq_material_pipe (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	pipe_code varchar(50),
	material varchar(50),
	pipe_technology varchar(50),
	pipe_diameter numeric(6,2),
	wall_thickness numeric(6,2),
	stove_serial_num varchar(50),
	pipe_forming_method varchar(50),
	pipe_length numeric(10,3),
	pipe_weight numeric(10,3),
	grade varchar(50),
	manufacture_date timestamp(6),
	manufacture_factory varchar(50),
	pipe_quality_certificate_num varchar(60),
	steel_plate_num varchar(60),
	steel_plate_quality_certificate_num varchar(60),
	external_coating_type varchar(50),
	external_coating_grade varchar(50),
	internal_coating_material varchar(50),
	internal_coating_thinkness numeric(6,3),
	coating_num varchar(50),
	coating_date timestamp(6),
	coating_certificate_num varchar(60),
	coating_factory varchar(70),
	is_cut SMALLINT DEFAULT 0,
	is_use SMALLINT DEFAULT 0,
	is_hot_bend SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_material_pipe IS '钢管表';
comment on column daq_material_pipe.oid IS '主键';
comment on column daq_material_pipe.project_oid IS '项目oid';
comment on column daq_material_pipe.pipeline_oid IS '管线oid';
comment on column daq_material_pipe.tenders_oid IS '标段oid';
comment on column daq_material_pipe.pipe_code IS '钢管编号';
comment on column daq_material_pipe.material IS '材质';
comment on column daq_material_pipe.pipe_technology IS '制造工艺';
comment on column daq_material_pipe.pipe_diameter IS '管径(mm)';
comment on column daq_material_pipe.wall_thickness IS '壁厚(mm）';
comment on column daq_material_pipe.stove_serial_num IS '炉批号';
comment on column daq_material_pipe.pipe_forming_method IS '钢管成型方式';
comment on column daq_material_pipe.pipe_length IS '长度(m)';
comment on column daq_material_pipe.pipe_weight IS '重量(吨)';
comment on column daq_material_pipe.grade IS '管材等级';
comment on column daq_material_pipe.manufacture_date IS '钢管生产日期';
comment on column daq_material_pipe.manufacture_factory IS '钢管生产厂家';
comment on column daq_material_pipe.pipe_quality_certificate_num IS '成品管质量证明书编号';
comment on column daq_material_pipe.steel_plate_num IS '钢板号';
comment on column daq_material_pipe.steel_plate_quality_certificate_num IS '钢板质量证明书编号';
comment on column daq_material_pipe.external_coating_type IS '外防腐类型';
comment on column daq_material_pipe.external_coating_grade IS '外防腐等级';
comment on column daq_material_pipe.internal_coating_material IS '内防腐材料';
comment on column daq_material_pipe.internal_coating_thinkness IS '内涂层厚度（mm）';
comment on column daq_material_pipe.coating_num IS '防腐号';
comment on column daq_material_pipe.coating_date IS '防腐日期';
comment on column daq_material_pipe.coating_certificate_num IS '防腐证书编号';
comment on column daq_material_pipe.coating_factory IS '防腐加工厂家';
comment on column daq_material_pipe.is_cut IS '是否切管';
comment on column daq_material_pipe.is_use IS '是否使用';
comment on column daq_material_pipe.is_hot_bend IS '是否热弯';
comment on column daq_material_pipe.remarks IS '备注';
comment on column daq_material_pipe.create_user_id IS '创建人id';
comment on column daq_material_pipe.create_user_name IS '创建人名称';
comment on column daq_material_pipe.create_datetime IS '创建时间';
comment on column daq_material_pipe.modify_user_id IS '修改人id';
comment on column daq_material_pipe.modify_user_name IS '修改人名称';
comment on column daq_material_pipe.modify_datetime IS '修改时间';
comment on column daq_material_pipe.active IS '有效标志';

CREATE TABLE daq_material_pipe_cold_bending (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	tenders_oid varchar(36),
	pipeline_oid varchar(36),
	pipe_segment_oid varchar(36),
	pipe_code varchar(50),
	pipe_cold_bending_code varchar(53),
	certificate_num varchar(60),
	pipe_bending_standards varchar(60),
	bending_radius numeric(9,3),
	bending_angle numeric(9,3),
	curve_length numeric(9,3),
	straight_pipe_length numeric(9,3),
	pipe_length numeric(9,3),
	ellipticity numeric(9,3),
	wall_thickness_redurate numeric(9,3),
	pipe_diameter numeric(9,2),
	wall_thickness numeric(9,2),
	produce_date timestamp(6),
	construct_unit varchar(50),
	supervision_unit varchar(50),
	supervision_engineer varchar(50),
	collection_person varchar(30),
	collection_date timestamp(6),
	is_use SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_material_pipe_cold_bending IS '冷弯管表';
comment on column daq_material_pipe_cold_bending.oid IS '主键';
comment on column daq_material_pipe_cold_bending.project_oid IS '项目oid';
comment on column daq_material_pipe_cold_bending.tenders_oid IS '标段oid';
comment on column daq_material_pipe_cold_bending.pipeline_oid IS '管线oid';
comment on column daq_material_pipe_cold_bending.pipe_segment_oid IS '线路段oid';
comment on column daq_material_pipe_cold_bending.pipe_code IS '原材料钢管编号';
comment on column daq_material_pipe_cold_bending.pipe_cold_bending_code IS '冷弯管编号';
comment on column daq_material_pipe_cold_bending.certificate_num IS '合格证编号';
comment on column daq_material_pipe_cold_bending.pipe_bending_standards IS '管子弯曲设计标准';
comment on column daq_material_pipe_cold_bending.bending_radius IS '曲率半径（mm)';
comment on column daq_material_pipe_cold_bending.bending_angle IS '弯曲角度(°)';
comment on column daq_material_pipe_cold_bending.curve_length IS '曲线长度(m)';
comment on column daq_material_pipe_cold_bending.straight_pipe_length IS '直管段最小长度(m)';
comment on column daq_material_pipe_cold_bending.pipe_length IS '长度(m)';
comment on column daq_material_pipe_cold_bending.ellipticity IS '椭圆率(%)';
comment on column daq_material_pipe_cold_bending.wall_thickness_redurate IS '壁厚减薄率(%)';
comment on column daq_material_pipe_cold_bending.pipe_diameter IS '管径(mm)';
comment on column daq_material_pipe_cold_bending.wall_thickness IS '壁厚(mm)';
comment on column daq_material_pipe_cold_bending.produce_date IS '制作时间';
comment on column daq_material_pipe_cold_bending.construct_unit IS '施工单位';
comment on column daq_material_pipe_cold_bending.supervision_unit IS '监理单位';
comment on column daq_material_pipe_cold_bending.supervision_engineer IS '监理工程师';
comment on column daq_material_pipe_cold_bending.collection_person IS '采集人员';
comment on column daq_material_pipe_cold_bending.collection_date IS '采集时间';
comment on column daq_material_pipe_cold_bending.is_use IS '是否使用';
comment on column daq_material_pipe_cold_bending.remarks IS '备注';
comment on column daq_material_pipe_cold_bending.create_user_id IS '创建人id';
comment on column daq_material_pipe_cold_bending.create_user_name IS '创建人名称';
comment on column daq_material_pipe_cold_bending.create_datetime IS '创建时间';
comment on column daq_material_pipe_cold_bending.modify_user_id IS '修改人id';
comment on column daq_material_pipe_cold_bending.modify_user_name IS '修改人名称';
comment on column daq_material_pipe_cold_bending.modify_datetime IS '修改时间';
comment on column daq_material_pipe_cold_bending.active IS '有效标志';

CREATE TABLE daq_material_reducer (
	oid varchar(36) NOT NULL,
	reducer_code varchar(36),
	certification_num varchar(60),
	coating_type varchar(50),
	inlet_diameter numeric(9,3),
	inlet_wall_thickness numeric(9,3),
	outlet_diameter numeric(9,3),
	outlet_wall_thickness numeric(9,3),
	material varchar(50),
	steel_grade varchar(50),
	axis_location varchar(10),
	manufacturer varchar(50),
	manufacturer_date timestamp(6),
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	is_use SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_material_reducer IS '大小头信息表';
comment on column daq_material_reducer.oid IS '主键';
comment on column daq_material_reducer.reducer_code IS '大小头编号';
comment on column daq_material_reducer.certification_num IS '合格证编号';
comment on column daq_material_reducer.coating_type IS '防腐类型';
comment on column daq_material_reducer.inlet_diameter IS '大头管径(mm)';
comment on column daq_material_reducer.inlet_wall_thickness IS '大头壁厚(mm)';
comment on column daq_material_reducer.outlet_diameter IS '小头管径(mm)';
comment on column daq_material_reducer.outlet_wall_thickness IS '小头壁厚(mm)';
comment on column daq_material_reducer.material IS '材质';
comment on column daq_material_reducer.steel_grade IS '钢材等级';
comment on column daq_material_reducer.axis_location IS '轴心位置类型';
comment on column daq_material_reducer.manufacturer IS '生产厂家';
comment on column daq_material_reducer.manufacturer_date IS '生产日期';
comment on column daq_material_reducer.project_oid IS '项目oid';
comment on column daq_material_reducer.pipeline_oid IS '管线oid';
comment on column daq_material_reducer.tenders_oid IS '标段oid';
comment on column daq_material_reducer.is_use IS '是否使用';
comment on column daq_material_reducer.remarks IS '备注';
comment on column daq_material_reducer.create_user_id IS '创建人id';
comment on column daq_material_reducer.create_user_name IS '创建人名称';
comment on column daq_material_reducer.create_datetime IS '创建时间';
comment on column daq_material_reducer.modify_user_id IS '修改人id';
comment on column daq_material_reducer.modify_user_name IS '修改人名称';
comment on column daq_material_reducer.modify_datetime IS '修改时间';
comment on column daq_material_reducer.active IS '有效标志';

CREATE TABLE daq_material_tee (
	oid varchar(36) NOT NULL,
	tee_code varchar(36),
	certification_num varchar(60),
	specification varchar(50),
	material varchar(50),
	steel_grade varchar(50),
	is_stop varchar(5),
	oven_num varchar(50),
	batch_num varchar(50),
	coating_type varchar(36),
	pipe_wall_thickness numeric(9,3),
	pipe_diameter numeric(9,3),
	branch_wall_thickness numeric(9,3),
	branch_diameter numeric(9,3),
	manufacturer varchar(50),
	manufacturer_date timestamp(6),
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	is_use SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_material_tee IS '三通信息表';
comment on column daq_material_tee.oid IS '主键';
comment on column daq_material_tee.tee_code IS '三通编号';
comment on column daq_material_tee.certification_num IS '合格证编号';
comment on column daq_material_tee.specification IS '规格';
comment on column daq_material_tee.material IS '材质';
comment on column daq_material_tee.steel_grade IS '钢材等级';
comment on column daq_material_tee.is_stop IS '是否有档条';
comment on column daq_material_tee.oven_num IS '炉号';
comment on column daq_material_tee.batch_num IS '批号';
comment on column daq_material_tee.coating_type IS '防腐类型';
comment on column daq_material_tee.pipe_wall_thickness IS '管端壁厚(mm)';
comment on column daq_material_tee.pipe_diameter IS '管端管径（mm）';
comment on column daq_material_tee.branch_wall_thickness IS '拔制端壁厚（mm）';
comment on column daq_material_tee.branch_diameter IS '拔制端管径（mm）';
comment on column daq_material_tee.manufacturer IS '制造单位';
comment on column daq_material_tee.manufacturer_date IS '生产日期';
comment on column daq_material_tee.project_oid IS '项目oid';
comment on column daq_material_tee.pipeline_oid IS '管线oid';
comment on column daq_material_tee.tenders_oid IS '标段oid';
comment on column daq_material_tee.is_use IS '是否使用';
comment on column daq_material_tee.remarks IS '备注';
comment on column daq_material_tee.create_user_id IS '创建人id';
comment on column daq_material_tee.create_user_name IS '创建人名称';
comment on column daq_material_tee.create_datetime IS '创建时间';
comment on column daq_material_tee.modify_user_id IS '修改人id';
comment on column daq_material_tee.modify_user_name IS '修改人名称';
comment on column daq_material_tee.modify_datetime IS '修改时间';
comment on column daq_material_tee.active IS '有效标志';

CREATE INDEX index_daq_material_closure_closure_code_5 ON daq_material_closure USING btree (closure_code);
ALTER TABLE daq_material_closure ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_flange_flange_code_5 ON daq_material_flange USING btree (flange_code);
ALTER TABLE daq_material_flange ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_hot_bends_hot_bends_code_8 ON daq_material_hot_bends USING btree (hot_bends_code);
ALTER TABLE daq_material_hot_bends ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_insulated_joint_manufacturer_code_5 ON daq_material_insulated_joint USING btree (manufacturer_code);
ALTER TABLE daq_material_insulated_joint ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_pipe_pipe_code_8 ON daq_material_pipe USING btree (pipe_code);
ALTER TABLE daq_material_pipe ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_pipe_cold_bending_pipe_code_9 ON daq_material_pipe_cold_bending USING btree (pipe_code);
ALTER TABLE daq_material_pipe_cold_bending ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_reducer_reducer_code_5 ON daq_material_reducer USING btree (reducer_code);
ALTER TABLE daq_material_reducer ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_tee_tee_code_5 ON daq_material_tee USING btree (tee_code);
ALTER TABLE daq_material_tee ADD PRIMARY KEY (oid);

create or replace view v_daq_pipe_segment_cross as  select s.oid,s.pipe_segment_name as name,s.start_stake_oid,s.end_stake_oid,1 as type from daq_pipe_segment s where s.active=1
union all
select s.oid,s.cross_name as name,s.start_stake_oid,s.end_stake_oid, 2 as type from daq_cross s where s.active=1
/**********线路物资基本信息end***************/
/**********线路物资检查信息begin***************/
CREATE TABLE daq_check_coating_pipe (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construction_unit varchar(36),
	pipe_code varchar(50),
	groove_check varchar(5),
	pipe_end_proring_check varchar(5),
	coating_io_face_check varchar(5),
	diameter_check varchar(5),
	coating_io_ends_check varchar(5),
	excess_weld_metal varchar(5),
	ovality varchar(5),
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_check_coating_pipe IS '防腐管检查及信息记录表';
comment on column daq_check_coating_pipe.oid IS '主键';
comment on column daq_check_coating_pipe.project_oid IS '项目oid';
comment on column daq_check_coating_pipe.tenders_oid IS '标段oid';
comment on column daq_check_coating_pipe.construction_unit IS '施工单位';
comment on column daq_check_coating_pipe.pipe_code IS '钢管编号';
comment on column daq_check_coating_pipe.groove_check IS '坡口检查';
comment on column daq_check_coating_pipe.pipe_end_proring_check IS '管端保护圈';
comment on column daq_check_coating_pipe.coating_io_face_check IS '防腐层内外表面质量';
comment on column daq_check_coating_pipe.diameter_check IS '管径偏差+0.2mm至-0.5mm';
comment on column daq_check_coating_pipe.coating_io_ends_check IS '防腐层端部内外涂层';
comment on column daq_check_coating_pipe.excess_weld_metal IS '管端焊缝余高（0mm）';
comment on column daq_check_coating_pipe.ovality IS '椭圆度<0.6%D';
comment on column daq_check_coating_pipe.checked_by IS '检查人';
comment on column daq_check_coating_pipe.checked_date IS '检查日期';
comment on column daq_check_coating_pipe.remarks IS '备注';
comment on column daq_check_coating_pipe.create_user_id IS '创建人id';
comment on column daq_check_coating_pipe.create_user_name IS '创建人名称';
comment on column daq_check_coating_pipe.create_datetime IS '创建时间';
comment on column daq_check_coating_pipe.modify_user_id IS '修改人id';
comment on column daq_check_coating_pipe.modify_user_name IS '修改人名称';
comment on column daq_check_coating_pipe.modify_datetime IS '修改时间';
comment on column daq_check_coating_pipe.active IS '有效标志';

CREATE TABLE daq_check_hot_bends (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construction_unit varchar(36),
	hot_bends_code varchar(50),
	weld_position varchar(5),
	pipe_length varchar(5),
	ovality varchar(5),
	groove_check varchar(5),
	coating_io_face_check varchar(5),
	coating_io_ends_check varchar(5),
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_check_hot_bends IS '热煨弯管检查信息记录表';
comment on column daq_check_hot_bends.oid IS '主键';
comment on column daq_check_hot_bends.project_oid IS '项目oid';
comment on column daq_check_hot_bends.tenders_oid IS '标段oid';
comment on column daq_check_hot_bends.construction_unit IS '施工单位';
comment on column daq_check_hot_bends.hot_bends_code IS '弯管编号';
comment on column daq_check_hot_bends.weld_position IS '纵焊缝位置';
comment on column daq_check_hot_bends.pipe_length IS '直管段长度';
comment on column daq_check_hot_bends.ovality IS '椭圆度<0.6%D';
comment on column daq_check_hot_bends.groove_check IS '坡口检查';
comment on column daq_check_hot_bends.coating_io_face_check IS '防腐层内外表面质量';
comment on column daq_check_hot_bends.coating_io_ends_check IS '防腐层端部内外涂层';
comment on column daq_check_hot_bends.checked_by IS '检查人';
comment on column daq_check_hot_bends.checked_date IS '检查日期';
comment on column daq_check_hot_bends.remarks IS '备注';
comment on column daq_check_hot_bends.create_user_id IS '创建人id';
comment on column daq_check_hot_bends.create_user_name IS '创建人名称';
comment on column daq_check_hot_bends.create_datetime IS '创建时间';
comment on column daq_check_hot_bends.modify_user_id IS '修改人id';
comment on column daq_check_hot_bends.modify_user_name IS '修改人名称';
comment on column daq_check_hot_bends.modify_datetime IS '修改时间';
comment on column daq_check_hot_bends.active IS '有效标志';

CREATE TABLE daq_check_insulated_joint (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construction_unit varchar(36),
	manufacturer_code varchar(36),
	certification_num varchar(60),
	diameter numeric(9,3),
	pressure numeric(9,3),
	manufacturer varchar(50),
	test_equipment varchar(50),
	specand_model varchar(30),
	resistance_val varchar(20),
	check_results varchar(200),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_check_insulated_joint IS '绝缘接头检查及信息记录表';
comment on column daq_check_insulated_joint.oid IS '主键';
comment on column daq_check_insulated_joint.project_oid IS '项目oid';
comment on column daq_check_insulated_joint.tenders_oid IS '标段oid';
comment on column daq_check_insulated_joint.construction_unit IS '施工单位';
comment on column daq_check_insulated_joint.manufacturer_code IS '出厂编号';
comment on column daq_check_insulated_joint.certification_num IS '合格证编号';
comment on column daq_check_insulated_joint.diameter IS '公称直径(mm)';
comment on column daq_check_insulated_joint.pressure IS '公称压力(MPa)';
comment on column daq_check_insulated_joint.manufacturer IS '生产厂家';
comment on column daq_check_insulated_joint.test_equipment IS '测试仪器';
comment on column daq_check_insulated_joint.specand_model IS '仪器规格型号';
comment on column daq_check_insulated_joint.resistance_val IS '实测绝缘电阻值(MΩ)';
comment on column daq_check_insulated_joint.check_results IS '验收结论';
comment on column daq_check_insulated_joint.remarks IS '备注';
comment on column daq_check_insulated_joint.create_user_id IS '创建人id';
comment on column daq_check_insulated_joint.create_user_name IS '创建人名称';
comment on column daq_check_insulated_joint.create_datetime IS '创建时间';
comment on column daq_check_insulated_joint.modify_user_id IS '修改人id';
comment on column daq_check_insulated_joint.modify_user_name IS '修改人名称';
comment on column daq_check_insulated_joint.modify_datetime IS '修改时间';
comment on column daq_check_insulated_joint.active IS '有效标志';

CREATE TABLE daq_check_pipe_cold_bending (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construction_unit varchar(36),
	pipe_cold_bending_code varchar(50),
	certificate_num varchar(60),
	pipe_length numeric(9,3),
	pipe_diameter numeric(9,3),
	wall_thickness numeric(9,3),
	production_unit varchar(60),
	bend_angle numeric(4),
	weld_position varchar(5),
	ovality varchar(5),
	groove_check varchar(5),
	coating_io_face_check varchar(5),
	coating_io_ends_check varchar(5),
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_check_pipe_cold_bending IS '冷弯管检查及信息记录表';
comment on column daq_check_pipe_cold_bending.oid IS '主键';
comment on column daq_check_pipe_cold_bending.project_oid IS '项目oid';
comment on column daq_check_pipe_cold_bending.tenders_oid IS '标段oid';
comment on column daq_check_pipe_cold_bending.construction_unit IS '施工单位';
comment on column daq_check_pipe_cold_bending.pipe_cold_bending_code IS '冷弯管编号';
comment on column daq_check_pipe_cold_bending.certificate_num IS '合格证编号';
comment on column daq_check_pipe_cold_bending.pipe_length IS '弯管长度(m)';
comment on column daq_check_pipe_cold_bending.pipe_diameter IS '管径(mm)';
comment on column daq_check_pipe_cold_bending.wall_thickness IS '壁厚(mm）';
comment on column daq_check_pipe_cold_bending.production_unit IS '弯制单位';
comment on column daq_check_pipe_cold_bending.bend_angle IS '弯制角度(°）';
comment on column daq_check_pipe_cold_bending.weld_position IS '纵焊缝位置';
comment on column daq_check_pipe_cold_bending.ovality IS '椭圆度<0.6%D';
comment on column daq_check_pipe_cold_bending.groove_check IS '坡口检查';
comment on column daq_check_pipe_cold_bending.coating_io_face_check IS '防腐层内外表面质量';
comment on column daq_check_pipe_cold_bending.coating_io_ends_check IS '防腐层端部内外涂层';
comment on column daq_check_pipe_cold_bending.checked_by IS '检查人';
comment on column daq_check_pipe_cold_bending.checked_date IS '检查日期';
comment on column daq_check_pipe_cold_bending.remarks IS '备注';
comment on column daq_check_pipe_cold_bending.create_user_id IS '创建人id';
comment on column daq_check_pipe_cold_bending.create_user_name IS '创建人名称';
comment on column daq_check_pipe_cold_bending.create_datetime IS '创建时间';
comment on column daq_check_pipe_cold_bending.modify_user_id IS '修改人id';
comment on column daq_check_pipe_cold_bending.modify_user_name IS '修改人名称';
comment on column daq_check_pipe_cold_bending.modify_datetime IS '修改时间';
comment on column daq_check_pipe_cold_bending.active IS '有效标志';

CREATE TABLE daq_check_reducer (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construction_unit varchar(36),
	reducer_code varchar(36),
	ovality varchar(5),
	groove_check varchar(5),
	coating_io_face_check varchar(5),
	coating_io_ends_check varchar(5),
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_check_reducer IS '大小头检查及信息记录表';
comment on column daq_check_reducer.oid IS '主键';
comment on column daq_check_reducer.project_oid IS '项目oid';
comment on column daq_check_reducer.tenders_oid IS '标段oid';
comment on column daq_check_reducer.construction_unit IS '施工单位';
comment on column daq_check_reducer.reducer_code IS '大小头编号';
comment on column daq_check_reducer.ovality IS '椭圆度<0.6%D';
comment on column daq_check_reducer.groove_check IS '坡口检查';
comment on column daq_check_reducer.coating_io_face_check IS '防腐层内外表面质量';
comment on column daq_check_reducer.coating_io_ends_check IS '防腐层端部内外涂层';
comment on column daq_check_reducer.checked_by IS '检查人';
comment on column daq_check_reducer.checked_date IS '检查日期';
comment on column daq_check_reducer.remarks IS '备注';
comment on column daq_check_reducer.create_user_id IS '创建人id';
comment on column daq_check_reducer.create_user_name IS '创建人名称';
comment on column daq_check_reducer.create_datetime IS '创建时间';
comment on column daq_check_reducer.modify_user_id IS '修改人id';
comment on column daq_check_reducer.modify_user_name IS '修改人名称';
comment on column daq_check_reducer.modify_datetime IS '修改时间';
comment on column daq_check_reducer.active IS '有效标志';

CREATE TABLE daq_check_tee (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construction_unit varchar(36),
	tee_code varchar(36),
	pipe_wall_thickness numeric(9,3),
	branch_wall_thickness numeric(9,3),
	ovality varchar(5),
	groove_check varchar(5),
	coating_io_face_check varchar(5),
	coating_io_ends_check varchar(5),
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_check_tee IS '三通检查及信息记录表';
comment on column daq_check_tee.oid IS '主键';
comment on column daq_check_tee.project_oid IS '项目oid';
comment on column daq_check_tee.tenders_oid IS '标段oid';
comment on column daq_check_tee.construction_unit IS '施工单位';
comment on column daq_check_tee.tee_code IS '三通编号';
comment on column daq_check_tee.pipe_wall_thickness IS '管端壁厚(mm)';
comment on column daq_check_tee.branch_wall_thickness IS '拔制端壁厚（mm）';
comment on column daq_check_tee.ovality IS '椭圆度<0.6%D';
comment on column daq_check_tee.groove_check IS '坡口检查';
comment on column daq_check_tee.coating_io_face_check IS '防腐层内外表面质量';
comment on column daq_check_tee.coating_io_ends_check IS '防腐层端部内外涂层';
comment on column daq_check_tee.checked_by IS '检查人';
comment on column daq_check_tee.checked_date IS '检查日期';
comment on column daq_check_tee.remarks IS '备注';
comment on column daq_check_tee.create_user_id IS '创建人id';
comment on column daq_check_tee.create_user_name IS '创建人名称';
comment on column daq_check_tee.create_datetime IS '创建时间';
comment on column daq_check_tee.modify_user_id IS '修改人id';
comment on column daq_check_tee.modify_user_name IS '修改人名称';
comment on column daq_check_tee.modify_datetime IS '修改时间';
comment on column daq_check_tee.active IS '有效标志';

CREATE INDEX index_daq_check_coating_pipe_pipe_code_5 ON daq_check_coating_pipe USING btree (pipe_code);
ALTER TABLE daq_check_coating_pipe ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_hot_bends_hot_bends_code_5 ON daq_check_hot_bends USING btree (hot_bends_code);
ALTER TABLE daq_check_hot_bends ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_insulated_joint_manufacturer_code_5 ON daq_check_insulated_joint USING btree (manufacturer_code);
ALTER TABLE daq_check_insulated_joint ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_pipe_cold_bending_pipe_cold_bending_code_5 ON daq_check_pipe_cold_bending USING btree (pipe_cold_bending_code);
ALTER TABLE daq_check_pipe_cold_bending ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_reducer_reducer_code_5 ON daq_check_reducer USING btree (reducer_code);
ALTER TABLE daq_check_reducer ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_tee_tee_code_5 ON daq_check_tee USING btree (tee_code);
ALTER TABLE daq_check_tee ADD PRIMARY KEY (oid);
/**********线路物资检查信息end***************/
/**********管道焊接信息begin***************/
CREATE TABLE daq_construction_weld (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	pipe_segment_or_cross_oid varchar(36),
	weld_code varchar(50),
	weld_type varchar(50),
	weld_method varchar(50),
	median_stake_oid varchar(50),
	relative_mileage numeric(9,3),
	front_pipe_type varchar(50),
	front_pipe_code varchar(36),
	back_pipe_type varchar(50),
	back_pipe_code varchar(36),
	weld_rod_batch_num varchar(60),
	weld_wire_batch_num varchar(60),
	weld_produce varchar(36),
	surface_check SMALLINT,
	construct_unit varchar(36),
	work_unit_oid varchar(36),
	cover_oid varchar(36),
	padder_oid varchar(36),
	render_oid varchar(36),
	is_golde_joint SMALLINT,
	is_pipe_head SMALLINT,
	construct_date timestamp(6),
	supervision_unit varchar(38),
	supervision_engineer varchar(50),
	collection_person varchar(30),
	collection_date timestamp(6),
	approve_status SMALLINT default 0,
	geo_state varchar(10),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT default 0 NOT NULL
);
comment on table daq_construction_weld IS '焊口信息表';
comment on column daq_construction_weld.oid IS '主键';
comment on column daq_construction_weld.project_oid IS '项目oid';
comment on column daq_construction_weld.pipeline_oid IS '管线oid';
comment on column daq_construction_weld.tenders_oid IS '标段oid';
comment on column daq_construction_weld.pipe_segment_or_cross_oid IS '线路段/穿跨越';
comment on column daq_construction_weld.weld_code IS '焊口编号';
comment on column daq_construction_weld.weld_type IS '焊口类型';
comment on column daq_construction_weld.weld_method IS '焊接方式';
comment on column daq_construction_weld.median_stake_oid IS '桩号';
comment on column daq_construction_weld.relative_mileage IS '相对桩位置(m)';
comment on column daq_construction_weld.front_pipe_type IS '前管件类型';
comment on column daq_construction_weld.front_pipe_code IS '前管件编号';
comment on column daq_construction_weld.back_pipe_type IS '后管件类型';
comment on column daq_construction_weld.back_pipe_code IS '后管件编号';
comment on column daq_construction_weld.weld_rod_batch_num IS '焊条批号';
comment on column daq_construction_weld.weld_wire_batch_num IS '焊丝批号';
comment on column daq_construction_weld.weld_produce IS '焊接工艺规程';
comment on column daq_construction_weld.surface_check IS '外观质量检查';
comment on column daq_construction_weld.construct_unit IS '施工单位';
comment on column daq_construction_weld.work_unit_oid IS '施工机组代号';
comment on column daq_construction_weld.cover_oid IS '盖面人员';
comment on column daq_construction_weld.padder_oid IS '填充人员';
comment on column daq_construction_weld.render_oid IS '打底人员';
comment on column daq_construction_weld.is_golde_joint IS '是否金口';
comment on column daq_construction_weld.is_pipe_head IS '是否连头口';
comment on column daq_construction_weld.construct_date IS '施工日期';
comment on column daq_construction_weld.supervision_unit IS '监理单位';
comment on column daq_construction_weld.supervision_engineer IS '监理工程师';
comment on column daq_construction_weld.collection_person IS '采集人员';
comment on column daq_construction_weld.collection_date IS '采集日期';
comment on column daq_construction_weld.approve_status IS '审核状态';
comment on column daq_construction_weld.geo_state IS '空间数据状态';
comment on column daq_construction_weld.remarks IS '备注';
comment on column daq_construction_weld.create_user_id IS '创建人id';
comment on column daq_construction_weld.create_user_name IS '创建人名称';
comment on column daq_construction_weld.create_datetime IS '创建时间';
comment on column daq_construction_weld.modify_user_id IS '修改人id';
comment on column daq_construction_weld.modify_user_name IS '修改人名称';
comment on column daq_construction_weld.modify_datetime IS '修改时间';
comment on column daq_construction_weld.active IS '有效标志';
CREATE INDEX index_daq_construction_weld_weld_code_9 ON daq_construction_weld USING btree (weld_code);
ALTER TABLE daq_construction_weld ADD PRIMARY KEY (oid);
select dropgeometrycolumn('public', 'daq_construction_weld', 'geom');
select AddGeometryColumn('public', 'daq_construction_weld', 'geom', 4490, 'POINT', 4);
CREATE INDEX point_test_geom_idx ON public.daq_construction_weld USING gist (geom);


CREATE TABLE daq_weld_anticorrosion_check (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	pipe_segment_or_cross_oid varchar(36),
	weld_oid varchar(36),
	buckle_date timestamp(6),
	buckle_anticorrosive_type varchar(50),
	buckle_anticorrosive_grade varchar(50),
	buckle_material_batch_num varchar(60),
	derusting_grade varchar(50),
	pipe_mouth_clean SMALLINT,
	sandblasting_and_derusting SMALLINT,
	pipe_mouth_preheat SMALLINT,
	epoxy_primer SMALLINT,
	baking_check SMALLINT,
	overlap_check SMALLINT,
	appearance_check SMALLINT,
	electric_spark_leak_detection SMALLINT,
	buckle_conclusion varchar(500),
	anticorrosion varchar(20),
	construct_unit varchar(36),
	supervision_unit varchar(38),
	supervision_engineer varchar(50),
	collection_person varchar(30),
	collection_date timestamp(6),
	approve_status SMALLINT default 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_weld_anticorrosion_check IS '防腐补口检查信息表';
comment on column daq_weld_anticorrosion_check.oid IS '主键';
comment on column daq_weld_anticorrosion_check.project_oid IS '项目oid';
comment on column daq_weld_anticorrosion_check.pipeline_oid IS '管线oid';
comment on column daq_weld_anticorrosion_check.tenders_oid IS '标段oid';
comment on column daq_weld_anticorrosion_check.pipe_segment_or_cross_oid IS '线路段/穿跨越';
comment on column daq_weld_anticorrosion_check.weld_oid IS '焊口编号';
comment on column daq_weld_anticorrosion_check.buckle_date IS '补口日期';
comment on column daq_weld_anticorrosion_check.buckle_anticorrosive_type IS '补口防腐类型';
comment on column daq_weld_anticorrosion_check.buckle_anticorrosive_grade IS '防腐等级';
comment on column daq_weld_anticorrosion_check.buckle_material_batch_num IS '补口材料批号';
comment on column daq_weld_anticorrosion_check.derusting_grade IS '除锈等级';
comment on column daq_weld_anticorrosion_check.pipe_mouth_clean IS '管口清理';
comment on column daq_weld_anticorrosion_check.sandblasting_and_derusting IS '喷砂除绣';
comment on column daq_weld_anticorrosion_check.pipe_mouth_preheat IS '管口预热';
comment on column daq_weld_anticorrosion_check.epoxy_primer IS '环氧底漆';
comment on column daq_weld_anticorrosion_check.baking_check IS '喷烤';
comment on column daq_weld_anticorrosion_check.overlap_check IS '轴向搭接';
comment on column daq_weld_anticorrosion_check.appearance_check IS '外观检查';
comment on column daq_weld_anticorrosion_check.electric_spark_leak_detection IS '电火花检漏';
comment on column daq_weld_anticorrosion_check.buckle_conclusion IS '补口结论';
comment on column daq_weld_anticorrosion_check.anticorrosion IS '防腐工';
comment on column daq_weld_anticorrosion_check.construct_unit IS '施工单位';
comment on column daq_weld_anticorrosion_check.supervision_unit IS '监理单位';
comment on column daq_weld_anticorrosion_check.supervision_engineer IS '监理工程师';
comment on column daq_weld_anticorrosion_check.collection_person IS '数据采集人';
comment on column daq_weld_anticorrosion_check.collection_date IS '采集日期';
comment on column daq_weld_anticorrosion_check.approve_status IS '审核状态';
comment on column daq_weld_anticorrosion_check.remarks IS '备注';
comment on column daq_weld_anticorrosion_check.create_user_id IS '创建人id';
comment on column daq_weld_anticorrosion_check.create_user_name IS '创建人名称';
comment on column daq_weld_anticorrosion_check.create_datetime IS '创建时间';
comment on column daq_weld_anticorrosion_check.modify_user_id IS '修改人id';
comment on column daq_weld_anticorrosion_check.modify_user_name IS '修改人名称';
comment on column daq_weld_anticorrosion_check.modify_datetime IS '修改时间';
comment on column daq_weld_anticorrosion_check.active IS '有效标志';

CREATE TABLE daq_weld_anticorrosion_repair (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	pipe_segment_or_cross_oid varchar(36),
	weld_oid varchar(36),
	relative_mileage numeric(9,3),
	anticorrosion_position varchar(30),
	anticorrosion_describe varchar(50),
	anticorrosion_method varchar(50),
	detection_voltage numeric(8,3),
	construction_date timestamp(6),
	construction_person varchar(20),
	construct_unit varchar(36),
	supervision_unit varchar(38),
	supervision_engineer varchar(50),
	collection_person varchar(30),
	collection_date timestamp(6),
	approve_status SMALLINT default 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_weld_anticorrosion_repair IS '防腐补伤信息表';
comment on column daq_weld_anticorrosion_repair.oid IS '主键';
comment on column daq_weld_anticorrosion_repair.project_oid IS '项目oid';
comment on column daq_weld_anticorrosion_repair.pipeline_oid IS '管线oid';
comment on column daq_weld_anticorrosion_repair.tenders_oid IS '标段oid';
comment on column daq_weld_anticorrosion_repair.pipe_segment_or_cross_oid IS '线路段/穿跨越';
comment on column daq_weld_anticorrosion_repair.weld_oid IS '焊口编号';
comment on column daq_weld_anticorrosion_repair.relative_mileage IS '相对焊口位置(m)';
comment on column daq_weld_anticorrosion_repair.anticorrosion_position IS '补伤部位';
comment on column daq_weld_anticorrosion_repair.anticorrosion_describe IS '补伤材料描述';
comment on column daq_weld_anticorrosion_repair.anticorrosion_method IS '补伤方式';
comment on column daq_weld_anticorrosion_repair.detection_voltage IS '检测电压(KV)';
comment on column daq_weld_anticorrosion_repair.construction_date IS '施工日期';
comment on column daq_weld_anticorrosion_repair.construction_person IS '补伤人';
comment on column daq_weld_anticorrosion_repair.construct_unit IS '施工单位';
comment on column daq_weld_anticorrosion_repair.supervision_unit IS '监理单位';
comment on column daq_weld_anticorrosion_repair.supervision_engineer IS '监理工程师';
comment on column daq_weld_anticorrosion_repair.collection_person IS '数据采集人';
comment on column daq_weld_anticorrosion_repair.collection_date IS '采集日期';
comment on column daq_weld_anticorrosion_repair.approve_status IS '审核状态';
comment on column daq_weld_anticorrosion_repair.remarks IS '备注';
comment on column daq_weld_anticorrosion_repair.create_user_id IS '创建人id';
comment on column daq_weld_anticorrosion_repair.create_user_name IS '创建人名称';
comment on column daq_weld_anticorrosion_repair.create_datetime IS '创建时间';
comment on column daq_weld_anticorrosion_repair.modify_user_id IS '修改人id';
comment on column daq_weld_anticorrosion_repair.modify_user_name IS '修改人名称';
comment on column daq_weld_anticorrosion_repair.modify_datetime IS '修改时间';
comment on column daq_weld_anticorrosion_repair.active IS '有效标志';

CREATE TABLE daq_weld_anticorrosion_test (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	pipe_segment_or_cross_oid varchar(36),
	weld_oid varchar(36),
	relative_mileage numeric(9,3),
	separate_method varchar(30),
	pe_strength numeric(9,3),
	light_peel_strength numeric(9,3),
	peel_welds_strength numeric(9,3),
	test_result varchar(300),
	test_date timestamp(6),
	construct_unit varchar(36),
	supervision_unit varchar(38),
	supervision_engineer varchar(50),
	collection_person varchar(30),
	collection_date timestamp(6),
	approve_status SMALLINT default 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_weld_anticorrosion_test IS '防腐补口剥离强度试验信息表';
comment on column daq_weld_anticorrosion_test.oid IS '主键';
comment on column daq_weld_anticorrosion_test.project_oid IS '项目oid';
comment on column daq_weld_anticorrosion_test.pipeline_oid IS '管线oid';
comment on column daq_weld_anticorrosion_test.tenders_oid IS '标段oid';
comment on column daq_weld_anticorrosion_test.pipe_segment_or_cross_oid IS '线路段/穿跨越';
comment on column daq_weld_anticorrosion_test.weld_oid IS '焊口编号';
comment on column daq_weld_anticorrosion_test.relative_mileage IS '相对焊口里程(m)';
comment on column daq_weld_anticorrosion_test.separate_method IS '剥离方法';
comment on column daq_weld_anticorrosion_test.pe_strength IS '对PE剥离强度(N/cm)';
comment on column daq_weld_anticorrosion_test.light_peel_strength IS '对光管剥离强度(N/cm)';
comment on column daq_weld_anticorrosion_test.peel_welds_strength IS '对焊道剥离强度(N/cm)';
comment on column daq_weld_anticorrosion_test.test_result IS '实验结果';
comment on column daq_weld_anticorrosion_test.test_date IS '实验日期';
comment on column daq_weld_anticorrosion_test.construct_unit IS '施工单位';
comment on column daq_weld_anticorrosion_test.supervision_unit IS '监理单位';
comment on column daq_weld_anticorrosion_test.supervision_engineer IS '监理工程师';
comment on column daq_weld_anticorrosion_test.collection_person IS '数据采集人';
comment on column daq_weld_anticorrosion_test.collection_date IS '采集日期';
comment on column daq_weld_anticorrosion_test.approve_status IS '审核状态';
comment on column daq_weld_anticorrosion_test.remarks IS '备注';
comment on column daq_weld_anticorrosion_test.create_user_id IS '创建人id';
comment on column daq_weld_anticorrosion_test.create_user_name IS '创建人名称';
comment on column daq_weld_anticorrosion_test.create_datetime IS '创建时间';
comment on column daq_weld_anticorrosion_test.modify_user_id IS '修改人id';
comment on column daq_weld_anticorrosion_test.modify_user_name IS '修改人名称';
comment on column daq_weld_anticorrosion_test.modify_datetime IS '修改时间';
comment on column daq_weld_anticorrosion_test.active IS '有效标志';

CREATE TABLE daq_weld_cut (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	pipe_segment_or_cross_oid varchar(36),
	cut_weld_code varchar(50),
	front_weld_oid varchar(36),
	back_weld_oid varchar(36),
	cut_weld_date timestamp(6),
	construct_unit varchar(36),
	work_unit_oid varchar(36),
	supervision_unit varchar(38),
	supervision_engineer varchar(50),
	collection_person varchar(30),
	collection_date timestamp(6),
	approve_status SMALLINT default 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_weld_cut IS '焊口割口信息表';
comment on column daq_weld_cut.oid IS '主键';
comment on column daq_weld_cut.project_oid IS '项目oid';
comment on column daq_weld_cut.pipeline_oid IS '管线oid';
comment on column daq_weld_cut.tenders_oid IS '标段oid';
comment on column daq_weld_cut.pipe_segment_or_cross_oid IS '线路段/穿跨越';
comment on column daq_weld_cut.cut_weld_code IS '割口编号';
comment on column daq_weld_cut.front_weld_oid IS '新焊口编号1';
comment on column daq_weld_cut.back_weld_oid IS '新焊口编号2';
comment on column daq_weld_cut.cut_weld_date IS '割口日期';
comment on column daq_weld_cut.construct_unit IS '施工单位';
comment on column daq_weld_cut.work_unit_oid IS '施工机组代号';
comment on column daq_weld_cut.supervision_unit IS '监理单位';
comment on column daq_weld_cut.supervision_engineer IS '监理工程师';
comment on column daq_weld_cut.collection_person IS '数据采集人';
comment on column daq_weld_cut.collection_date IS '采集日期';
comment on column daq_weld_cut.approve_status IS '审核状态';
comment on column daq_weld_cut.remarks IS '备注';
comment on column daq_weld_cut.create_user_id IS '创建人id';
comment on column daq_weld_cut.create_user_name IS '创建人名称';
comment on column daq_weld_cut.create_datetime IS '创建时间';
comment on column daq_weld_cut.modify_user_id IS '修改人id';
comment on column daq_weld_cut.modify_user_name IS '修改人名称';
comment on column daq_weld_cut.modify_datetime IS '修改时间';
comment on column daq_weld_cut.active IS '有效标志';

CREATE TABLE daq_weld_rework_weld (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	pipe_segment_or_cross_oid varchar(36),
	weld_oid varchar(36),
	rework_weld_code varchar(53),
	weld_rod_batch_num varchar(60),
	weld_wire_batch_num varchar(60),
	weld_produce varchar(36),
	cover_oid varchar(36),
	padder_oid varchar(36),
	render_oid varchar(36),
	weld_date timestamp(6),
	construct_unit varchar(36),
	work_unit_oid varchar(36),
	supervision_unit varchar(38),
	supervision_engineer varchar(50),
	collection_person varchar(30),
	collection_date timestamp(6),
	approve_status SMALLINT default 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_weld_rework_weld IS '焊口返修信息表';
comment on column daq_weld_rework_weld.oid IS '主键';
comment on column daq_weld_rework_weld.project_oid IS '项目oid';
comment on column daq_weld_rework_weld.pipeline_oid IS '管线oid';
comment on column daq_weld_rework_weld.tenders_oid IS '标段oid';
comment on column daq_weld_rework_weld.pipe_segment_or_cross_oid IS '线路段/穿跨越';
comment on column daq_weld_rework_weld.weld_oid IS '返修口编号';
comment on column daq_weld_rework_weld.rework_weld_code IS '返修后焊口编号';
comment on column daq_weld_rework_weld.weld_rod_batch_num IS '焊条批号';
comment on column daq_weld_rework_weld.weld_wire_batch_num IS '焊丝批号';
comment on column daq_weld_rework_weld.weld_produce IS '焊接工艺规程';
comment on column daq_weld_rework_weld.cover_oid IS '填充人员';
comment on column daq_weld_rework_weld.padder_oid IS '打底人员';
comment on column daq_weld_rework_weld.render_oid IS '盖面人员';
comment on column daq_weld_rework_weld.weld_date IS '焊接日期';
comment on column daq_weld_rework_weld.construct_unit IS '施工单位';
comment on column daq_weld_rework_weld.work_unit_oid IS '施工机组代号';
comment on column daq_weld_rework_weld.supervision_unit IS '监理单位';
comment on column daq_weld_rework_weld.supervision_engineer IS '监理工程师';
comment on column daq_weld_rework_weld.collection_person IS '采集人员';
comment on column daq_weld_rework_weld.collection_date IS '采集日期';
comment on column daq_weld_rework_weld.approve_status IS '审核状态';
comment on column daq_weld_rework_weld.remarks IS '备注';
comment on column daq_weld_rework_weld.create_user_id IS '创建人id';
comment on column daq_weld_rework_weld.create_user_name IS '创建人名称';
comment on column daq_weld_rework_weld.create_datetime IS '创建时间';
comment on column daq_weld_rework_weld.modify_user_id IS '修改人id';
comment on column daq_weld_rework_weld.modify_user_name IS '修改人名称';
comment on column daq_weld_rework_weld.modify_datetime IS '修改时间';
comment on column daq_weld_rework_weld.active IS '有效标志';

CREATE INDEX index_daq_weld_anticorrosion_check_weld_oid_9 ON daq_weld_anticorrosion_check USING btree (weld_oid);
ALTER TABLE daq_weld_anticorrosion_check ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_weld_anticorrosion_repair_weld_oid_9 ON daq_weld_anticorrosion_repair USING btree (weld_oid);
ALTER TABLE daq_weld_anticorrosion_repair ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_weld_anticorrosion_test_weld_oid_9 ON daq_weld_anticorrosion_test USING btree (weld_oid);
ALTER TABLE daq_weld_anticorrosion_test ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_weld_cut_cut_weld_code_9 ON daq_weld_cut USING btree (cut_weld_code);
CREATE INDEX index_daq_weld_cut_front_weld_oid_10 ON daq_weld_cut USING btree (front_weld_oid);
CREATE INDEX index_daq_weld_cut_back_weld_oid_11 ON daq_weld_cut USING btree (back_weld_oid);
ALTER TABLE daq_weld_cut ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_weld_rework_weld_weld_oid_9 ON daq_weld_rework_weld USING btree (weld_oid);
CREATE INDEX index_daq_weld_rework_weld_rework_weld_code_10 ON daq_weld_rework_weld USING btree (rework_weld_code);
ALTER TABLE daq_weld_rework_weld ADD PRIMARY KEY (oid);

CREATE TABLE daq_cut_pipe (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	pipeline_oid varchar(36),
	tenders_oid varchar(36),
	pipe_oid varchar(50),
	pipe_diameter numeric(9,3),
	wall_thickness numeric(9,3),
	segments_num SMALLINT,
	first_paragraph_length numeric(9,3),
	second_paragraph_length numeric(9,3),
	third_paragraph_length numeric(9,3),
	fourth_paragraph_length numeric(9,3),
	fifth_paragraph_length numeric(9,3),
	approve_status SMALLINT default 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active SMALLINT NOT NULL
);
comment on table daq_cut_pipe IS '钢管切管信息表';
comment on column daq_cut_pipe.oid IS '主键';
comment on column daq_cut_pipe.project_oid IS '项目oid';
comment on column daq_cut_pipe.pipeline_oid IS '管线oid';
comment on column daq_cut_pipe.tenders_oid IS '标段oid';
comment on column daq_cut_pipe.pipe_oid IS '钢管编号';
comment on column daq_cut_pipe.pipe_diameter IS '管径(mm)';
comment on column daq_cut_pipe.wall_thickness IS '壁厚(mm）';
comment on column daq_cut_pipe.segments_num IS '分割段数（段）';
comment on column daq_cut_pipe.first_paragraph_length IS '第一段长度(m)';
comment on column daq_cut_pipe.second_paragraph_length IS '第二段长度(m)';
comment on column daq_cut_pipe.third_paragraph_length IS '第三段长度(m)';
comment on column daq_cut_pipe.fourth_paragraph_length IS '第四段长度(m)';
comment on column daq_cut_pipe.fifth_paragraph_length IS '第五段长度(m)';
comment on column daq_cut_pipe.approve_status IS '审核状态';
comment on column daq_cut_pipe.remarks IS '备注';
comment on column daq_cut_pipe.create_user_id IS '创建人id';
comment on column daq_cut_pipe.create_user_name IS '创建人名称';
comment on column daq_cut_pipe.create_datetime IS '创建时间';
comment on column daq_cut_pipe.modify_user_id IS '修改人id';
comment on column daq_cut_pipe.modify_user_name IS '修改人名称';
comment on column daq_cut_pipe.modify_datetime IS '修改时间';
comment on column daq_cut_pipe.active IS '有效标志';
CREATE INDEX index_daq_cut_pipe_pipe_oid_8 ON daq_cut_pipe USING btree (pipe_oid);
ALTER TABLE daq_cut_pipe ADD PRIMARY KEY (oid);

CREATE TABLE daq_weld_measured_result (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_oid VARCHAR (36),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (9, 3),
	pointx NUMERIC (10, 3),
	pointy NUMERIC (11, 3),
	surfacee_levation NUMERIC (9, 2),
	pipe_top_elevation NUMERIC (9, 2),
	buried_depth NUMERIC (9, 2),
	survey_crew VARCHAR (20),
	survey_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state varchar(10),
	approve_status SMALLINT,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_weld_measured_result is '焊口测量成果信息表';
comment on column daq_weld_measured_result.oid is '主键';
comment on column daq_weld_measured_result.project_oid is '项目oid';
comment on column daq_weld_measured_result.pipeline_oid is '管线oid';
comment on column daq_weld_measured_result.tenders_oid is '标段oid';
comment on column daq_weld_measured_result.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_weld_measured_result.weld_oid is '焊口编号';
comment on column daq_weld_measured_result.median_stake_oid is '桩号';
comment on column daq_weld_measured_result.relative_mileage is '相对桩位置(m)';
comment on column daq_weld_measured_result.pointx is '坐标X(m)';
comment on column daq_weld_measured_result.pointy is '坐标Y(m)';
comment on column daq_weld_measured_result.surfacee_levation is '地表高程(m)';
comment on column daq_weld_measured_result.pipe_top_elevation is '管顶高程(m)';
comment on column daq_weld_measured_result.buried_depth is '埋深(m)';
comment on column daq_weld_measured_result.survey_crew is '测量人';
comment on column daq_weld_measured_result.survey_date is '测量日期';
comment on column daq_weld_measured_result.construct_unit is '施工单位';
comment on column daq_weld_measured_result.supervision_unit is '监理单位';
comment on column daq_weld_measured_result.supervision_engineer is '监理工程师';
comment on column daq_weld_measured_result.collection_person is '数据采集人';
comment on column daq_weld_measured_result.collection_date is '采集日期';
comment on column daq_weld_measured_result.geo_state is '空间数据状态';
comment on column daq_weld_measured_result.approve_status is '审核状态';
comment on column daq_weld_measured_result.remarks is '备注';
comment on column daq_weld_measured_result.create_user_id is '创建人id';
comment on column daq_weld_measured_result.create_user_name is '创建人名称';
comment on column daq_weld_measured_result.create_datetime is '创建时间';
comment on column daq_weld_measured_result.modify_user_id is '修改人id';
comment on column daq_weld_measured_result.modify_user_name is '修改人名称';
comment on column daq_weld_measured_result.modify_datetime is '修改时间';
comment on column daq_weld_measured_result.active is '有效标志';
create index INDEX_DAQ_WELD_MEASURED_RESULT_WELD_OID_9 ON daq_weld_measured_result ( weld_oid );
select AddGeometryColumn('public', 'daq_weld_measured_result', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_weld_measured_result_geom_idx ON public.daq_weld_measured_result USING gist (geom);

/**********管道焊接信息end***************/
/**********管道检测信息begin***************/
CREATE TABLE daq_detection_ray (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_code VARCHAR (36),
	detection_report_num VARCHAR (60),
	detection_deta TIMESTAMP (6),
	detection_type VARCHAR (50),
	evaluation_grade VARCHAR (50),
	evaluation_result SMALLINT,
	detection_dispose VARCHAR (50),
	detection_unit VARCHAR (50),
	detection_person VARCHAR (25),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_detection_ray is '射线检测';
comment on column daq_detection_ray.oid is '主键';
comment on column daq_detection_ray.project_oid is '项目oid';
comment on column daq_detection_ray.pipeline_oid is '管线oid';
comment on column daq_detection_ray.tenders_oid is '标段oid';
comment on column daq_detection_ray.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_ray.weld_code is '焊口编号';
comment on column daq_detection_ray.detection_report_num is '检测报告编号';
comment on column daq_detection_ray.detection_deta is '检测日期';
comment on column daq_detection_ray.detection_type is '检测类型';
comment on column daq_detection_ray.evaluation_grade is '评定等级';
comment on column daq_detection_ray.evaluation_result is '评定结果';
comment on column daq_detection_ray.detection_dispose is '检测处置';
comment on column daq_detection_ray.detection_unit is '检测单位';
comment on column daq_detection_ray.detection_person is '检测人员';
comment on column daq_detection_ray.supervision_unit is '监理单位';
comment on column daq_detection_ray.supervision_engineer is '监理工程师';
comment on column daq_detection_ray.collection_date is '采集日期';
comment on column daq_detection_ray.approve_status is '审核状态';
comment on column daq_detection_ray.remarks is '备注';
comment on column daq_detection_ray.create_user_id is '创建人id';
comment on column daq_detection_ray.create_user_name is '创建人名称';
comment on column daq_detection_ray.create_datetime is '创建时间';
comment on column daq_detection_ray.modify_user_id is '修改人id';
comment on column daq_detection_ray.modify_user_name is '修改人名称';
comment on column daq_detection_ray.modify_datetime is '修改时间';
comment on column daq_detection_ray.active is '有效标志';
create index INDEX_DAQ_DETECTION_RAY_WELD_CODE_9 ON daq_detection_ray ( weld_code );
create index INDEX_DAQ_DETECTION_RAY_DETECTION_REPORT_NUM_10 ON daq_detection_ray ( detection_report_num );

CREATE TABLE daq_detection_ray_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_code VARCHAR (36),
	defect_position VARCHAR (60),
	defect_properties VARCHAR (50),
	defect_size NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_detection_ray_sub is '射线检测子表';
comment on column daq_detection_ray_sub.oid is '主键';
comment on column daq_detection_ray_sub.parent_oid is '主表oid';
comment on column daq_detection_ray_sub.weld_code is '焊口编号';
comment on column daq_detection_ray_sub.defect_position is '缺陷位置';
comment on column daq_detection_ray_sub.defect_properties is '缺陷性质';
comment on column daq_detection_ray_sub.defect_size is '缺陷尺寸(mm/mm²/点)';
comment on column daq_detection_ray_sub.create_user_id is '创建人id';
comment on column daq_detection_ray_sub.create_user_name is '创建人名称';
comment on column daq_detection_ray_sub.create_datetime is '创建时间';
comment on column daq_detection_ray_sub.modify_user_id is '修改人id';
comment on column daq_detection_ray_sub.modify_user_name is '修改人名称';
comment on column daq_detection_ray_sub.modify_datetime is '修改时间';
comment on column daq_detection_ray_sub.active is '有效标志';
create index INDEX_DAQ_DETECTION_RAY_SUB_WELD_CODE_6 ON daq_detection_ray_sub ( weld_code );

CREATE TABLE daq_detection_ultrasonic (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_code VARCHAR (36),
	detection_report_num VARCHAR (60),
	detection_deta TIMESTAMP (6),
	detection_type VARCHAR (50),
	evaluation_grade VARCHAR (50),
	evaluation_result SMALLINT,
	detection_dispose VARCHAR (50),
	detection_length NUMERIC (9, 3),
	detection_unit VARCHAR (50),
	detection_person VARCHAR (25),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_detection_ultrasonic is '超声波检测表';
comment on column daq_detection_ultrasonic.oid is '主键';
comment on column daq_detection_ultrasonic.project_oid is '项目oid';
comment on column daq_detection_ultrasonic.pipeline_oid is '管线oid';
comment on column daq_detection_ultrasonic.tenders_oid is '标段oid';
comment on column daq_detection_ultrasonic.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_ultrasonic.weld_code is '焊口编号';
comment on column daq_detection_ultrasonic.detection_report_num is '检测报告编号';
comment on column daq_detection_ultrasonic.detection_deta is '检测日期';
comment on column daq_detection_ultrasonic.detection_type is '检测类型';
comment on column daq_detection_ultrasonic.evaluation_grade is '评定等级';
comment on column daq_detection_ultrasonic.evaluation_result is '评定结果';
comment on column daq_detection_ultrasonic.detection_dispose is '检测处置';
comment on column daq_detection_ultrasonic.detection_length is '检测长度(mm)';
comment on column daq_detection_ultrasonic.detection_unit is '检测单位';
comment on column daq_detection_ultrasonic.detection_person is '检测人员';
comment on column daq_detection_ultrasonic.supervision_unit is '监理单位';
comment on column daq_detection_ultrasonic.supervision_engineer is '监理工程师';
comment on column daq_detection_ultrasonic.collection_date is '采集日期';
comment on column daq_detection_ultrasonic.approve_status is '审核状态';
comment on column daq_detection_ultrasonic.remarks is '备注';
comment on column daq_detection_ultrasonic.create_user_id is '创建人id';
comment on column daq_detection_ultrasonic.create_user_name is '创建人名称';
comment on column daq_detection_ultrasonic.create_datetime is '创建时间';
comment on column daq_detection_ultrasonic.modify_user_id is '修改人id';
comment on column daq_detection_ultrasonic.modify_user_name is '修改人名称';
comment on column daq_detection_ultrasonic.modify_datetime is '修改时间';
comment on column daq_detection_ultrasonic.active is '有效标志';
create index INDEX_DAQ_DETECTION_ULTRASONIC_WELD_CODE_9 ON daq_detection_ultrasonic ( weld_code );
create index INDEX_DAQ_DETECTION_ULTRASONIC_DETECTION_REPORT_NUM_10 ON daq_detection_ultrasonic ( detection_report_num );

CREATE TABLE daq_detection_ultrasonic_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_code VARCHAR (36),
	defect_position VARCHAR (60),
	defect_properties VARCHAR (50),
	defect_size NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_detection_ultrasonic_sub is '超声波检测子表';
comment on column daq_detection_ultrasonic_sub.oid is '主键';
comment on column daq_detection_ultrasonic_sub.parent_oid is '主表oid';
comment on column daq_detection_ultrasonic_sub.weld_code is '焊口编号';
comment on column daq_detection_ultrasonic_sub.defect_position is '缺陷位置';
comment on column daq_detection_ultrasonic_sub.defect_properties is '缺陷性质';
comment on column daq_detection_ultrasonic_sub.defect_size is '缺陷尺寸(mm/mm²/点)';
comment on column daq_detection_ultrasonic_sub.create_user_id is '创建人id';
comment on column daq_detection_ultrasonic_sub.create_user_name is '创建人名称';
comment on column daq_detection_ultrasonic_sub.create_datetime is '创建时间';
comment on column daq_detection_ultrasonic_sub.modify_user_id is '修改人id';
comment on column daq_detection_ultrasonic_sub.modify_user_name is '修改人名称';
comment on column daq_detection_ultrasonic_sub.modify_datetime is '修改时间';
comment on column daq_detection_ultrasonic_sub.active is '有效标志';
create index INDEX_daq_detection_ultrasonic_sub_WELD_CODE_6 ON daq_detection_ultrasonic_sub ( weld_code );

CREATE TABLE daq_detection_infiltration (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_code VARCHAR (36),
	detection_report_num VARCHAR (60),
	detection_deta TIMESTAMP (6),
	evaluation_result SMALLINT,
	detection_dispose VARCHAR (50),
	detection_length NUMERIC (9, 3),
	detection_unit VARCHAR (50),
	detection_person VARCHAR (25),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_detection_infiltration is '渗透检测';
comment on column daq_detection_infiltration.oid is '主键';
comment on column daq_detection_infiltration.project_oid is '项目oid';
comment on column daq_detection_infiltration.pipeline_oid is '管线oid';
comment on column daq_detection_infiltration.tenders_oid is '标段oid';
comment on column daq_detection_infiltration.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_infiltration.weld_code is '焊口编号';
comment on column daq_detection_infiltration.detection_report_num is '检测报告编号';
comment on column daq_detection_infiltration.detection_deta is '检测日期';
comment on column daq_detection_infiltration.evaluation_result is '评定结果';
comment on column daq_detection_infiltration.detection_dispose is '检测处置';
comment on column daq_detection_infiltration.detection_length is '检测长度(mm)';
comment on column daq_detection_infiltration.detection_unit is '检测单位';
comment on column daq_detection_infiltration.detection_person is '检测人员';
comment on column daq_detection_infiltration.supervision_unit is '监理单位';
comment on column daq_detection_infiltration.supervision_engineer is '监理工程师';
comment on column daq_detection_infiltration.collection_date is '采集日期';
comment on column daq_detection_infiltration.approve_status is '审核状态';
comment on column daq_detection_infiltration.remarks is '备注';
comment on column daq_detection_infiltration.create_user_id is '创建人id';
comment on column daq_detection_infiltration.create_user_name is '创建人名称';
comment on column daq_detection_infiltration.create_datetime is '创建时间';
comment on column daq_detection_infiltration.modify_user_id is '修改人id';
comment on column daq_detection_infiltration.modify_user_name is '修改人名称';
comment on column daq_detection_infiltration.modify_datetime is '修改时间';
comment on column daq_detection_infiltration.active is '有效标志';
create index INDEX_DAQ_DETECTION_INFILTRATION_WELD_CODE_9 ON daq_detection_infiltration ( weld_code );
create index INDEX_DAQ_DETECTION_INFILTRATION_DETECTION_REPORT_NUM_10 ON daq_detection_infiltration ( detection_report_num );

CREATE TABLE daq_detection_infiltration_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_code VARCHAR (36),
	defect_position VARCHAR (60),
	defect_properties VARCHAR (50),
	defect_size NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_detection_infiltration_sub is '渗透检测子表';
comment on column daq_detection_infiltration_sub.oid is '主键';
comment on column daq_detection_infiltration_sub.parent_oid is '主表oid';
comment on column daq_detection_infiltration_sub.weld_code is '焊口编号';
comment on column daq_detection_infiltration_sub.defect_position is '缺陷位置';
comment on column daq_detection_infiltration_sub.defect_properties is '缺陷性质';
comment on column daq_detection_infiltration_sub.defect_size is '缺陷尺寸(mm/mm²/点)';
comment on column daq_detection_infiltration_sub.create_user_id is '创建人id';
comment on column daq_detection_infiltration_sub.create_user_name is '创建人名称';
comment on column daq_detection_infiltration_sub.create_datetime is '创建时间';
comment on column daq_detection_infiltration_sub.modify_user_id is '修改人id';
comment on column daq_detection_infiltration_sub.modify_user_name is '修改人名称';
comment on column daq_detection_infiltration_sub.modify_datetime is '修改时间';
comment on column daq_detection_infiltration_sub.active is '有效标志';
create index INDEX_daq_detection_infiltration_sub_WELD_CODE_6 ON daq_detection_infiltration_sub ( weld_code );

CREATE TABLE daq_detection_magnetic_powder (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_code VARCHAR (36),
	detection_report_num VARCHAR (60),
	detection_deta TIMESTAMP (6),
	evaluation_result SMALLINT,
	detection_dispose VARCHAR (50),
	detection_length NUMERIC (9, 3),
	detection_unit VARCHAR (50),
	detection_person VARCHAR (25),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_detection_magnetic_powder is '磁粉检测表';
comment on column daq_detection_magnetic_powder.oid is '主键';
comment on column daq_detection_magnetic_powder.project_oid is '项目oid';
comment on column daq_detection_magnetic_powder.pipeline_oid is '管线oid';
comment on column daq_detection_magnetic_powder.tenders_oid is '标段oid';
comment on column daq_detection_magnetic_powder.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_magnetic_powder.weld_code is '焊口编号';
comment on column daq_detection_magnetic_powder.detection_report_num is '检测报告编号';
comment on column daq_detection_magnetic_powder.detection_deta is '检测日期';
comment on column daq_detection_magnetic_powder.evaluation_result is '评定结果';
comment on column daq_detection_magnetic_powder.detection_dispose is '检测处置';
comment on column daq_detection_magnetic_powder.detection_length is '检测长度(mm)';
comment on column daq_detection_magnetic_powder.detection_unit is '检测单位';
comment on column daq_detection_magnetic_powder.detection_person is '检测人员';
comment on column daq_detection_magnetic_powder.supervision_unit is '监理单位';
comment on column daq_detection_magnetic_powder.supervision_engineer is '监理工程师';
comment on column daq_detection_magnetic_powder.collection_date is '采集日期';
comment on column daq_detection_magnetic_powder.approve_status is '审核状态';
comment on column daq_detection_magnetic_powder.remarks is '备注';
comment on column daq_detection_magnetic_powder.create_user_id is '创建人id';
comment on column daq_detection_magnetic_powder.create_user_name is '创建人名称';
comment on column daq_detection_magnetic_powder.create_datetime is '创建时间';
comment on column daq_detection_magnetic_powder.modify_user_id is '修改人id';
comment on column daq_detection_magnetic_powder.modify_user_name is '修改人名称';
comment on column daq_detection_magnetic_powder.modify_datetime is '修改时间';
comment on column daq_detection_magnetic_powder.active is '有效标志';
create index INDEX_DAQ_DETECTION_MAGNETIC_POWDER_WELD_CODE_9 ON daq_detection_magnetic_powder ( weld_code );
create index INDEX_DAQ_DETECTION_MAGNETIC_POWDER_DETECTION_REPORT_NUM_10 ON daq_detection_magnetic_powder ( detection_report_num );

CREATE TABLE daq_detection_magnetic_powder_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_code VARCHAR (36),
	defect_position VARCHAR (60),
	defect_properties VARCHAR (50),
	defect_size NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_detection_magnetic_powder_sub is '磁粉检测子表';
comment on column daq_detection_magnetic_powder_sub.oid is '主键';
comment on column daq_detection_magnetic_powder_sub.parent_oid is '主表oid';
comment on column daq_detection_magnetic_powder_sub.weld_code is '焊口编号';
comment on column daq_detection_magnetic_powder_sub.defect_position is '缺陷位置';
comment on column daq_detection_magnetic_powder_sub.defect_properties is '缺陷性质';
comment on column daq_detection_magnetic_powder_sub.defect_size is '缺陷尺寸(mm/mm²/点)';
comment on column daq_detection_magnetic_powder_sub.create_user_id is '创建人id';
comment on column daq_detection_magnetic_powder_sub.create_user_name is '创建人名称';
comment on column daq_detection_magnetic_powder_sub.create_datetime is '创建时间';
comment on column daq_detection_magnetic_powder_sub.modify_user_id is '修改人id';
comment on column daq_detection_magnetic_powder_sub.modify_user_name is '修改人名称';
comment on column daq_detection_magnetic_powder_sub.modify_datetime is '修改时间';
comment on column daq_detection_magnetic_powder_sub.active is '有效标志';
create index INDEX_daq_detection_magnetic_powder_sub_WELD_CODE_6 ON daq_detection_magnetic_powder_sub ( weld_code );

CREATE TABLE daq_detection_fa_ultrasonic (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_code VARCHAR (36),
	detection_report_num VARCHAR (60),
	detection_deta TIMESTAMP (6),
	detection_type VARCHAR (50),
	detection_dispose VARCHAR (50),
	evaluation_result SMALLINT,
	detection_unit VARCHAR (50),
	detection_person VARCHAR (25),
	auditor VARCHAR (20),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_detection_fa_ultrasonic is '全自动超声波检测表';
comment on column daq_detection_fa_ultrasonic.oid is '主键';
comment on column daq_detection_fa_ultrasonic.project_oid is '项目oid';
comment on column daq_detection_fa_ultrasonic.pipeline_oid is '管线oid';
comment on column daq_detection_fa_ultrasonic.tenders_oid is '标段oid';
comment on column daq_detection_fa_ultrasonic.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_fa_ultrasonic.weld_code is '焊口编号';
comment on column daq_detection_fa_ultrasonic.detection_report_num is '检测报告编号';
comment on column daq_detection_fa_ultrasonic.detection_deta is '检测日期';
comment on column daq_detection_fa_ultrasonic.detection_type is '检测类型';
comment on column daq_detection_fa_ultrasonic.detection_dispose is '检测处置';
comment on column daq_detection_fa_ultrasonic.evaluation_result is '评定结果';
comment on column daq_detection_fa_ultrasonic.detection_unit is '检测单位';
comment on column daq_detection_fa_ultrasonic.detection_person is '检测人员';
comment on column daq_detection_fa_ultrasonic.auditor is '审核人员';
comment on column daq_detection_fa_ultrasonic.supervision_unit is '监理单位';
comment on column daq_detection_fa_ultrasonic.supervision_engineer is '监理工程师';
comment on column daq_detection_fa_ultrasonic.collection_date is '采集日期';
comment on column daq_detection_fa_ultrasonic.approve_status is '审核状态';
comment on column daq_detection_fa_ultrasonic.remarks is '备注';
comment on column daq_detection_fa_ultrasonic.create_user_id is '创建人id';
comment on column daq_detection_fa_ultrasonic.create_user_name is '创建人名称';
comment on column daq_detection_fa_ultrasonic.create_datetime is '创建时间';
comment on column daq_detection_fa_ultrasonic.modify_user_id is '修改人id';
comment on column daq_detection_fa_ultrasonic.modify_user_name is '修改人名称';
comment on column daq_detection_fa_ultrasonic.modify_datetime is '修改时间';
comment on column daq_detection_fa_ultrasonic.active is '有效标志';
create index INDEX_DAQ_DETECTION_FA_ULTRASONIC_WELD_CODE_9 ON daq_detection_fa_ultrasonic ( weld_code );
create index INDEX_DAQ_DETECTION_FA_ULTRASONIC_DETECTION_REPORT_NUM_10 ON daq_detection_fa_ultrasonic ( detection_report_num );

CREATE TABLE daq_detection_fa_ultrasonic_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_code VARCHAR (36),
	defect_position VARCHAR (60),
	amplitude_region VARCHAR (60),
	defect_length NUMERIC (9, 3),
	defect_depth NUMERIC (9, 3),
	height NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_detection_fa_ultrasonic_sub is '全自动超声波检测子表';
comment on column daq_detection_fa_ultrasonic_sub.oid is '主键';
comment on column daq_detection_fa_ultrasonic_sub.parent_oid is '主表oid';
comment on column daq_detection_fa_ultrasonic_sub.weld_code is '焊口编号';
comment on column daq_detection_fa_ultrasonic_sub.defect_position is '缺陷位置';
comment on column daq_detection_fa_ultrasonic_sub.amplitude_region is '振幅区域';
comment on column daq_detection_fa_ultrasonic_sub.defect_length is '缺陷长度(mm)';
comment on column daq_detection_fa_ultrasonic_sub.defect_depth is '缺陷深度(mm)';
comment on column daq_detection_fa_ultrasonic_sub.height is '自身高度(mm)';
comment on column daq_detection_fa_ultrasonic_sub.create_user_id is '创建人id';
comment on column daq_detection_fa_ultrasonic_sub.create_user_name is '创建人名称';
comment on column daq_detection_fa_ultrasonic_sub.create_datetime is '创建时间';
comment on column daq_detection_fa_ultrasonic_sub.modify_user_id is '修改人id';
comment on column daq_detection_fa_ultrasonic_sub.modify_user_name is '修改人名称';
comment on column daq_detection_fa_ultrasonic_sub.modify_datetime is '修改时间';
comment on column daq_detection_fa_ultrasonic_sub.active is '有效标志';
create index INDEX_daq_detection_fa_ultrasonic_sub_WELD_CODE_6 ON daq_detection_fa_ultrasonic_sub ( weld_code );

CREATE TABLE daq_detection_pa_ultrasonic (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_code VARCHAR (36),
	detection_report_num VARCHAR (60),
	detection_file_num VARCHAR (60),
	detection_deta TIMESTAMP (6),
	detection_type VARCHAR (50),
	detection_dispose VARCHAR (50),
	evaluation_result SMALLINT,
	detection_unit VARCHAR (50),
	auditor VARCHAR (20),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_detection_pa_ultrasonic is '相控阵超声波检测表';
comment on column daq_detection_pa_ultrasonic.oid is '主键';
comment on column daq_detection_pa_ultrasonic.project_oid is '项目oid';
comment on column daq_detection_pa_ultrasonic.pipeline_oid is '管线oid';
comment on column daq_detection_pa_ultrasonic.tenders_oid is '标段oid';
comment on column daq_detection_pa_ultrasonic.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_pa_ultrasonic.weld_code is '焊口编号';
comment on column daq_detection_pa_ultrasonic.detection_report_num is '检测报告编号';
comment on column daq_detection_pa_ultrasonic.detection_file_num is '检测文件编号';
comment on column daq_detection_pa_ultrasonic.detection_deta is '检测日期';
comment on column daq_detection_pa_ultrasonic.detection_type is '检测类型';
comment on column daq_detection_pa_ultrasonic.detection_dispose is '检测处置';
comment on column daq_detection_pa_ultrasonic.evaluation_result is '评定结果';
comment on column daq_detection_pa_ultrasonic.detection_unit is '检测单位';
comment on column daq_detection_pa_ultrasonic.auditor is '审核人员';
comment on column daq_detection_pa_ultrasonic.supervision_unit is '监理单位';
comment on column daq_detection_pa_ultrasonic.supervision_engineer is '监理工程师';
comment on column daq_detection_pa_ultrasonic.collection_date is '采集日期';
comment on column daq_detection_pa_ultrasonic.approve_status is '审核状态';
comment on column daq_detection_pa_ultrasonic.remarks is '备注';
comment on column daq_detection_pa_ultrasonic.create_user_id is '创建人id';
comment on column daq_detection_pa_ultrasonic.create_user_name is '创建人名称';
comment on column daq_detection_pa_ultrasonic.create_datetime is '创建时间';
comment on column daq_detection_pa_ultrasonic.modify_user_id is '修改人id';
comment on column daq_detection_pa_ultrasonic.modify_user_name is '修改人名称';
comment on column daq_detection_pa_ultrasonic.modify_datetime is '修改时间';
comment on column daq_detection_pa_ultrasonic.active is '有效标志';
create index INDEX_DAQ_DETECTION_PA_ULTRASONIC_WELD_CODE_9 ON daq_detection_pa_ultrasonic ( weld_code );
create index INDEX_DAQ_DETECTION_PA_ULTRASONIC_DETECTION_REPORT_NUM_10 ON daq_detection_pa_ultrasonic ( detection_report_num );

CREATE TABLE daq_detection_pa_ultrasonic_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_code VARCHAR (36),
	defect_position VARCHAR (60),
	amplitude_region VARCHAR (60),
	defect_length NUMERIC (9, 3),
	defect_depth NUMERIC (9, 3),
	height NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_detection_pa_ultrasonic_sub is '相控阵超声波检测子表';
comment on column daq_detection_pa_ultrasonic_sub.oid is '主键';
comment on column daq_detection_pa_ultrasonic_sub.parent_oid is '主表oid';
comment on column daq_detection_pa_ultrasonic_sub.weld_code is '焊口编号';
comment on column daq_detection_pa_ultrasonic_sub.defect_position is '缺陷位置';
comment on column daq_detection_pa_ultrasonic_sub.amplitude_region is '振幅区域';
comment on column daq_detection_pa_ultrasonic_sub.defect_length is '缺陷长度(mm)';
comment on column daq_detection_pa_ultrasonic_sub.defect_depth is '缺陷深度(mm)';
comment on column daq_detection_pa_ultrasonic_sub.height is '自身高度(mm)';
comment on column daq_detection_pa_ultrasonic_sub.create_user_id is '创建人id';
comment on column daq_detection_pa_ultrasonic_sub.create_user_name is '创建人名称';
comment on column daq_detection_pa_ultrasonic_sub.create_datetime is '创建时间';
comment on column daq_detection_pa_ultrasonic_sub.modify_user_id is '修改人id';
comment on column daq_detection_pa_ultrasonic_sub.modify_user_name is '修改人名称';
comment on column daq_detection_pa_ultrasonic_sub.modify_datetime is '修改时间';
comment on column daq_detection_pa_ultrasonic_sub.active is '有效标志';
create index INDEX_daq_detection_pa_ultrasonic_sub_WELD_CODE_6 ON daq_detection_pa_ultrasonic_sub ( weld_code );
/**********管道检测信息end***************/
/**********管道敷设信息begin***************/
CREATE TABLE daq_lay_surveying (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (9, 3),
	measured_angle NUMERIC (9, 3),
	drawing_angle NUMERIC (9, 3),
	left_width NUMERIC (8, 3),
	right_width NUMERIC (8, 3),
	construct_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);

comment on table daq_lay_surveying is '测量放线表';
comment on column daq_lay_surveying.oid is '主键';
comment on column daq_lay_surveying.project_oid is '项目oid';
comment on column daq_lay_surveying.pipeline_oid is '管线oid';
comment on column daq_lay_surveying.tenders_oid is '标段oid';
comment on column daq_lay_surveying.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_lay_surveying.median_stake_oid is '桩号';
comment on column daq_lay_surveying.relative_mileage is '里程(m)';
comment on column daq_lay_surveying.measured_angle is '实测转角(°)';
comment on column daq_lay_surveying.drawing_angle is '图纸转角(°)';
comment on column daq_lay_surveying.left_width is '左侧放线宽度(m)';
comment on column daq_lay_surveying.right_width is '右侧放线宽度(m)';
comment on column daq_lay_surveying.construct_date is '施工日期';
comment on column daq_lay_surveying.construct_unit is '施工单位';
comment on column daq_lay_surveying.supervision_unit is '监理单位';
comment on column daq_lay_surveying.supervision_engineer is '监理工程师';
comment on column daq_lay_surveying.collection_person is '采集人员';
comment on column daq_lay_surveying.collection_date is '采集日期';
comment on column daq_lay_surveying.approve_status is '审核状态';
comment on column daq_lay_surveying.geo_state is '空间数据状态';
comment on column daq_lay_surveying.remarks is '备注';
comment on column daq_lay_surveying.create_user_id is '创建人id';
comment on column daq_lay_surveying.create_user_name is '创建人名称';
comment on column daq_lay_surveying.create_datetime is '创建时间';
comment on column daq_lay_surveying.modify_user_id is '修改人id';
comment on column daq_lay_surveying.modify_user_name is '修改人名称';
comment on column daq_lay_surveying.modify_datetime is '修改时间';
comment on column daq_lay_surveying.active is '有效标志';
select AddGeometryColumn('public', 'daq_lay_surveying', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_lay_surveying_geom_idx ON public.daq_lay_surveying USING gist (geom);

CREATE TABLE daq_lay_pipe_trench_excavation (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (9, 3),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (9, 3),
	soil_type VARCHAR (45),
	pipe_trench_depth NUMERIC (8, 3),
	groove_width NUMERIC (8, 3),
	slope_angle NUMERIC (8, 3),
	groove_height NUMERIC (8, 3),
	design_corner_angle NUMERIC (9, 3),
	actual_corner_angle NUMERIC (9, 3),
	conclusion SMALLINT,
	acceptance_opinion VARCHAR (200),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);

comment on table daq_lay_pipe_trench_excavation is '管沟开挖表';
comment on column daq_lay_pipe_trench_excavation.oid is '主键';
comment on column daq_lay_pipe_trench_excavation.project_oid is '项目oid';
comment on column daq_lay_pipe_trench_excavation.pipeline_oid is '管线oid';
comment on column daq_lay_pipe_trench_excavation.tenders_oid is '标段oid';
comment on column daq_lay_pipe_trench_excavation.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_lay_pipe_trench_excavation.start_median_stake_oid is '起始桩号';
comment on column daq_lay_pipe_trench_excavation.start_relative_mileage is '相对起始桩里程(m)';
comment on column daq_lay_pipe_trench_excavation.end_median_stake_oid is '结束桩号';
comment on column daq_lay_pipe_trench_excavation.end_relative_mileage is '相对结束桩里程(m)';
comment on column daq_lay_pipe_trench_excavation.soil_type is '土壤类别';
comment on column daq_lay_pipe_trench_excavation.pipe_trench_depth is '管沟深度(m)';
comment on column daq_lay_pipe_trench_excavation.groove_width is '沟底宽度(m)';
comment on column daq_lay_pipe_trench_excavation.slope_angle is '放坡角度(°)';
comment on column daq_lay_pipe_trench_excavation.groove_height is '沟底长度(m)';
comment on column daq_lay_pipe_trench_excavation.design_corner_angle is '设计转角(°)';
comment on column daq_lay_pipe_trench_excavation.actual_corner_angle is '实际转角(°)';
comment on column daq_lay_pipe_trench_excavation.conclusion is '结论';
comment on column daq_lay_pipe_trench_excavation.acceptance_opinion is '检查验收意见';
comment on column daq_lay_pipe_trench_excavation.construct_unit is '施工单位';
comment on column daq_lay_pipe_trench_excavation.construct_date is '施工日期';
comment on column daq_lay_pipe_trench_excavation.supervision_unit is '监理单位';
comment on column daq_lay_pipe_trench_excavation.supervision_engineer is '监理工程师';
comment on column daq_lay_pipe_trench_excavation.collection_person is '采集人员';
comment on column daq_lay_pipe_trench_excavation.collection_date is '采集日期';
comment on column daq_lay_pipe_trench_excavation.approve_status is '审核状态';
comment on column daq_lay_pipe_trench_excavation.geo_state is '空间数据状态';
comment on column daq_lay_pipe_trench_excavation.remarks is '备注';
comment on column daq_lay_pipe_trench_excavation.create_user_id is '创建人id';
comment on column daq_lay_pipe_trench_excavation.create_user_name is '创建人名称';
comment on column daq_lay_pipe_trench_excavation.create_datetime is '创建时间';
comment on column daq_lay_pipe_trench_excavation.modify_user_id is '修改人id';
comment on column daq_lay_pipe_trench_excavation.modify_user_name is '修改人名称';
comment on column daq_lay_pipe_trench_excavation.modify_datetime is '修改时间';
comment on column daq_lay_pipe_trench_excavation.active is '有效标志';
select AddGeometryColumn('public', 'daq_lay_pipe_trench_excavation', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_lay_pipe_trench_excavation_geom_idx ON public.daq_lay_pipe_trench_excavation USING gist (geom);

CREATE TABLE daq_lay_pipe_trench_backfill (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (9, 3),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (9, 3),
	backfill_length NUMERIC (8, 3),
	backfill_thickness NUMERIC (8, 3),
	one_backfill_thickness NUMERIC (8, 3),
	two_backfill_thickness NUMERIC (8, 3),
	sign_type VARCHAR (50),
	sign_length NUMERIC (8, 3),
	sign_depth NUMERIC (8, 3),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_lay_pipe_trench_backfill is '回填表';
comment on column daq_lay_pipe_trench_backfill.oid is '主键';
comment on column daq_lay_pipe_trench_backfill.project_oid is '项目oid';
comment on column daq_lay_pipe_trench_backfill.pipeline_oid is '管线oid';
comment on column daq_lay_pipe_trench_backfill.tenders_oid is '标段oid';
comment on column daq_lay_pipe_trench_backfill.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_lay_pipe_trench_backfill.start_median_stake_oid is '起始桩号';
comment on column daq_lay_pipe_trench_backfill.start_relative_mileage is '相对起始桩里程(m)';
comment on column daq_lay_pipe_trench_backfill.end_median_stake_oid is '结束桩号';
comment on column daq_lay_pipe_trench_backfill.end_relative_mileage is '相对结束桩里程(m)';
comment on column daq_lay_pipe_trench_backfill.backfill_length is '回填长度(m)';
comment on column daq_lay_pipe_trench_backfill.backfill_thickness is '细土回填厚度(mm)';
comment on column daq_lay_pipe_trench_backfill.one_backfill_thickness is '一次回填厚度(mm)';
comment on column daq_lay_pipe_trench_backfill.two_backfill_thickness is '二次回填厚度(mm)';
comment on column daq_lay_pipe_trench_backfill.sign_type is '埋地标识类型';
comment on column daq_lay_pipe_trench_backfill.sign_length is '标识长度(m)';
comment on column daq_lay_pipe_trench_backfill.sign_depth is '标识埋深(m)';
comment on column daq_lay_pipe_trench_backfill.construct_unit is '施工单位';
comment on column daq_lay_pipe_trench_backfill.construct_date is '施工日期';
comment on column daq_lay_pipe_trench_backfill.supervision_unit is '监理单位';
comment on column daq_lay_pipe_trench_backfill.supervision_engineer is '监理工程师';
comment on column daq_lay_pipe_trench_backfill.collection_person is '采集人员';
comment on column daq_lay_pipe_trench_backfill.collection_date is '采集日期';
comment on column daq_lay_pipe_trench_backfill.approve_status is '审核状态';
comment on column daq_lay_pipe_trench_backfill.geo_state is '空间数据状态';
comment on column daq_lay_pipe_trench_backfill.remarks is '备注';
comment on column daq_lay_pipe_trench_backfill.create_user_id is '创建人id';
comment on column daq_lay_pipe_trench_backfill.create_user_name is '创建人名称';
comment on column daq_lay_pipe_trench_backfill.create_datetime is '创建时间';
comment on column daq_lay_pipe_trench_backfill.modify_user_id is '修改人id';
comment on column daq_lay_pipe_trench_backfill.modify_user_name is '修改人名称';
comment on column daq_lay_pipe_trench_backfill.modify_datetime is '修改时间';
comment on column daq_lay_pipe_trench_backfill.active is '有效标志';
select AddGeometryColumn('public', 'daq_lay_pipe_trench_backfill', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_lay_pipe_trench_backfill_geom_idx ON public.daq_lay_pipe_trench_backfill USING gist (geom);

CREATE TABLE daq_lay_land_restoration (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (9, 3),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (9, 3),
	certificate_num VARCHAR (60),
	LENGTH NUMERIC (9, 3),
	region VARCHAR (60),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);

comment on table daq_lay_land_restoration is '地貌恢复表';
comment on column daq_lay_land_restoration.oid is '主键';
comment on column daq_lay_land_restoration.project_oid is '项目oid';
comment on column daq_lay_land_restoration.pipeline_oid is '管线oid';
comment on column daq_lay_land_restoration.tenders_oid is '标段oid';
comment on column daq_lay_land_restoration.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_lay_land_restoration.start_median_stake_oid is '起始桩号';
comment on column daq_lay_land_restoration.start_relative_mileage is '相对起始桩里程(m)';
comment on column daq_lay_land_restoration.end_median_stake_oid is '结束桩号';
comment on column daq_lay_land_restoration.end_relative_mileage is '相对结束桩里程(m)';
comment on column daq_lay_land_restoration.certificate_num is '证书编号';
comment on column daq_lay_land_restoration.length is '长度(m)';
comment on column daq_lay_land_restoration.region is '经过地区';
comment on column daq_lay_land_restoration.construct_unit is '施工单位';
comment on column daq_lay_land_restoration.construct_date is '施工日期';
comment on column daq_lay_land_restoration.supervision_unit is '监理单位';
comment on column daq_lay_land_restoration.supervision_engineer is '监理工程师';
comment on column daq_lay_land_restoration.collection_person is '采集人员';
comment on column daq_lay_land_restoration.collection_date is '采集日期';
comment on column daq_lay_land_restoration.approve_status is '审核状态';
comment on column daq_lay_land_restoration.geo_state is '空间数据状态';
comment on column daq_lay_land_restoration.remarks is '备注';
comment on column daq_lay_land_restoration.create_user_id is '创建人id';
comment on column daq_lay_land_restoration.create_user_name is '创建人名称';
comment on column daq_lay_land_restoration.create_datetime is '创建时间';
comment on column daq_lay_land_restoration.modify_user_id is '修改人id';
comment on column daq_lay_land_restoration.modify_user_name is '修改人名称';
comment on column daq_lay_land_restoration.modify_datetime is '修改时间';
comment on column daq_lay_land_restoration.active is '有效标志';
select AddGeometryColumn('public', 'daq_lay_land_restoration', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_lay_land_restoration_geom_idx ON public.daq_lay_land_restoration USING gist (geom);

CREATE TABLE daq_lay_thermal_insulation (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (9, 3),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (9, 3),
	insulation_material VARCHAR (50),
	insulation_thickness NUMERIC (9, 3),
	protective_tape VARCHAR (50),
	protective_thickness NUMERIC (8, 3),
	manufacturer VARCHAR (50),
	manufacturer_address VARCHAR (75),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	approve_status SMALLINT,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL default 0
);
comment on table daq_lay_thermal_insulation is '保温表';
comment on column daq_lay_thermal_insulation.oid is '主键';
comment on column daq_lay_thermal_insulation.project_oid is '项目oid';
comment on column daq_lay_thermal_insulation.pipeline_oid is '管线oid';
comment on column daq_lay_thermal_insulation.tenders_oid is '标段oid';
comment on column daq_lay_thermal_insulation.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_lay_thermal_insulation.start_median_stake_oid is '起始桩号';
comment on column daq_lay_thermal_insulation.start_relative_mileage is '相对起始桩里程(m)';
comment on column daq_lay_thermal_insulation.end_median_stake_oid is '结束桩号';
comment on column daq_lay_thermal_insulation.end_relative_mileage is '相对结束桩里程(m)';
comment on column daq_lay_thermal_insulation.insulation_material is '保温材料';
comment on column daq_lay_thermal_insulation.insulation_thickness is '保温层厚度(mm)';
comment on column daq_lay_thermal_insulation.protective_tape is '防护层结构';
comment on column daq_lay_thermal_insulation.protective_thickness is '防护层厚度(mm)';
comment on column daq_lay_thermal_insulation.manufacturer is '保温层生产厂家';
comment on column daq_lay_thermal_insulation.manufacturer_address is '具体地址';
comment on column daq_lay_thermal_insulation.construct_unit is '施工单位';
comment on column daq_lay_thermal_insulation.construct_date is '施工日期';
comment on column daq_lay_thermal_insulation.supervision_unit is '监理单位';
comment on column daq_lay_thermal_insulation.supervision_engineer is '监理工程师';
comment on column daq_lay_thermal_insulation.collection_person is '采集人员';
comment on column daq_lay_thermal_insulation.collection_date is '采集日期';
comment on column daq_lay_thermal_insulation.approve_status is '审核状态';
comment on column daq_lay_thermal_insulation.geo_state is '空间数据状态';
comment on column daq_lay_thermal_insulation.remarks is '备注';
comment on column daq_lay_thermal_insulation.create_user_id is '创建人id';
comment on column daq_lay_thermal_insulation.create_user_name is '创建人名称';
comment on column daq_lay_thermal_insulation.create_datetime is '创建时间';
comment on column daq_lay_thermal_insulation.modify_user_id is '修改人id';
comment on column daq_lay_thermal_insulation.modify_user_name is '修改人名称';
comment on column daq_lay_thermal_insulation.modify_datetime is '修改时间';
comment on column daq_lay_thermal_insulation.active is '有效标志';
select AddGeometryColumn('public', 'daq_lay_thermal_insulation', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_lay_thermal_insulation_geom_idx ON public.daq_lay_thermal_insulation USING gist (geom);
/**********管道敷设信息end***************/
/**********管道穿跨越信息begin***************/
CREATE TABLE daq_cross_excavation (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	cross_oid VARCHAR (36),
	crossing_department VARCHAR (50),
	cross_length NUMERIC (9, 2),
	cross_max_length NUMERIC (10, 2),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (8, 0),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (8, 0),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (9, 3),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (9, 3),
	steady_tube_measures VARCHAR (200),
	commencement_date TIMESTAMP (6),
	completion_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_cross_excavation is '开挖穿越表';
comment on column daq_cross_excavation.oid is '主键';
comment on column daq_cross_excavation.project_oid is '项目oid';
comment on column daq_cross_excavation.tenders_oid is '标段oid';
comment on column daq_cross_excavation.pipeline_oid is '管线oid';
comment on column daq_cross_excavation.cross_oid is '穿跨越oid';
comment on column daq_cross_excavation.crossing_department is '穿越物管理单位';
comment on column daq_cross_excavation.cross_length is '穿越长度(m)';
comment on column daq_cross_excavation.cross_max_length is '穿越最大深度(m)';
comment on column daq_cross_excavation.start_median_stake_oid is '起始桩号';
comment on column daq_cross_excavation.start_relative_mileage is '相对起始桩位置(m)';
comment on column daq_cross_excavation.end_median_stake_oid is '结束桩号';
comment on column daq_cross_excavation.end_relative_mileage is '相对结束桩位置(m)';
comment on column daq_cross_excavation.start_pointx is '起始点X坐标';
comment on column daq_cross_excavation.start_pointy is '起始点Y坐标';
comment on column daq_cross_excavation.start_pointz is '起始点高程(m)';
comment on column daq_cross_excavation.end_pointx is '结束点X坐标';
comment on column daq_cross_excavation.end_pointy is '结束点Y坐标';
comment on column daq_cross_excavation.end_pointz is '结束点高程(m)';
comment on column daq_cross_excavation.steady_tube_measures is '稳管措施';
comment on column daq_cross_excavation.commencement_date is '开工日期';
comment on column daq_cross_excavation.completion_date is '完工日期';
comment on column daq_cross_excavation.construct_unit is '施工单位';
comment on column daq_cross_excavation.supervision_unit is '监理单位';
comment on column daq_cross_excavation.supervision_engineer is '监理工程师';
comment on column daq_cross_excavation.collection_person is '采集人员';
comment on column daq_cross_excavation.collection_date is '采集日期';
comment on column daq_cross_excavation.geo_state is '空间数据状态';
comment on column daq_cross_excavation.approve_status is '审核状态';
comment on column daq_cross_excavation.remarks is '备注';
comment on column daq_cross_excavation.create_user_id is '创建人id';
comment on column daq_cross_excavation.create_user_name is '创建人名称';
comment on column daq_cross_excavation.create_datetime is '创建时间';
comment on column daq_cross_excavation.modify_user_id is '修改人id';
comment on column daq_cross_excavation.modify_user_name is '修改人名称';
comment on column daq_cross_excavation.modify_datetime is '修改时间';
comment on column daq_cross_excavation.active is '有效标志';
create index INDEX_DAQ_CROSS_EXCAVATION_CROSS_OID_8 ON daq_cross_excavation ( cross_oid );
select AddGeometryColumn('public', 'daq_cross_excavation', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_excavation_geom_idx ON public.daq_cross_excavation USING gist (geom);

CREATE TABLE daq_cross_pipe_jacking (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	cross_oid VARCHAR (36),
	crossing_department VARCHAR (50),
	cross_length NUMERIC (9, 2),
	cross_max_length NUMERIC (10, 2),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (8, 0),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (8, 0),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (9, 3),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (9, 3),
	commencement_date TIMESTAMP (6),
	completion_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_cross_pipe_jacking is '顶管穿越表';
comment on column daq_cross_pipe_jacking.oid is '主键';
comment on column daq_cross_pipe_jacking.project_oid is '项目oid';
comment on column daq_cross_pipe_jacking.tenders_oid is '标段oid';
comment on column daq_cross_pipe_jacking.pipeline_oid is '管线oid';
comment on column daq_cross_pipe_jacking.cross_oid is '穿跨越oid';
comment on column daq_cross_pipe_jacking.crossing_department is '穿越物管理单位';
comment on column daq_cross_pipe_jacking.cross_length is '穿越长度(m)';
comment on column daq_cross_pipe_jacking.cross_max_length is '穿越最大深度(m)';
comment on column daq_cross_pipe_jacking.start_median_stake_oid is '起始桩号';
comment on column daq_cross_pipe_jacking.start_relative_mileage is '相对起始桩位置(m)';
comment on column daq_cross_pipe_jacking.end_median_stake_oid is '结束桩号';
comment on column daq_cross_pipe_jacking.end_relative_mileage is '相对结束桩位置(m)';
comment on column daq_cross_pipe_jacking.start_pointx is '起始点X坐标';
comment on column daq_cross_pipe_jacking.start_pointy is '起始点Y坐标';
comment on column daq_cross_pipe_jacking.start_pointz is '起始点高程(m)';
comment on column daq_cross_pipe_jacking.end_pointx is '结束点X坐标';
comment on column daq_cross_pipe_jacking.end_pointy is '结束点Y坐标';
comment on column daq_cross_pipe_jacking.end_pointz is '结束点高程(m)';
comment on column daq_cross_pipe_jacking.commencement_date is '开工日期';
comment on column daq_cross_pipe_jacking.completion_date is '完工日期';
comment on column daq_cross_pipe_jacking.construct_unit is '施工单位';
comment on column daq_cross_pipe_jacking.supervision_unit is '监理单位';
comment on column daq_cross_pipe_jacking.supervision_engineer is '监理工程师';
comment on column daq_cross_pipe_jacking.collection_person is '采集人员';
comment on column daq_cross_pipe_jacking.collection_date is '采集日期';
comment on column daq_cross_pipe_jacking.geo_state is '空间数据状态';
comment on column daq_cross_pipe_jacking.approve_status is '审核状态';
comment on column daq_cross_pipe_jacking.remarks is '备注';
comment on column daq_cross_pipe_jacking.create_user_id is '创建人id';
comment on column daq_cross_pipe_jacking.create_user_name is '创建人名称';
comment on column daq_cross_pipe_jacking.create_datetime is '创建时间';
comment on column daq_cross_pipe_jacking.modify_user_id is '修改人id';
comment on column daq_cross_pipe_jacking.modify_user_name is '修改人名称';
comment on column daq_cross_pipe_jacking.modify_datetime is '修改时间';
comment on column daq_cross_pipe_jacking.active is '有效标志';
create index INDEX_daq_cross_pipe_jacking_CROSS_OID_8 ON daq_cross_pipe_jacking ( cross_oid );
select AddGeometryColumn('public', 'daq_cross_pipe_jacking', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_pipe_jacking_geom_idx ON public.daq_cross_pipe_jacking USING gist (geom);

CREATE TABLE daq_cross_box_culvert (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	cross_oid VARCHAR (36),
	crossing_department VARCHAR (50),
	cross_length NUMERIC (9, 2),
	cross_max_length NUMERIC (10, 2),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (8, 0),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (8, 0),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (9, 3),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (9, 3),
	commencement_date TIMESTAMP (6),
	completion_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_cross_box_culvert is '箱涵穿越表';
comment on column daq_cross_box_culvert.oid is '主键';
comment on column daq_cross_box_culvert.project_oid is '项目oid';
comment on column daq_cross_box_culvert.tenders_oid is '标段oid';
comment on column daq_cross_box_culvert.pipeline_oid is '管线oid';
comment on column daq_cross_box_culvert.cross_oid is '穿跨越oid';
comment on column daq_cross_box_culvert.crossing_department is '穿越物管理单位';
comment on column daq_cross_box_culvert.cross_length is '穿越长度(m)';
comment on column daq_cross_box_culvert.cross_max_length is '穿越最大深度(m)';
comment on column daq_cross_box_culvert.start_median_stake_oid is '起始桩号';
comment on column daq_cross_box_culvert.start_relative_mileage is '相对起始桩位置(m)';
comment on column daq_cross_box_culvert.end_median_stake_oid is '结束桩号';
comment on column daq_cross_box_culvert.end_relative_mileage is '相对结束桩位置(m)';
comment on column daq_cross_box_culvert.start_pointx is '起始点X坐标';
comment on column daq_cross_box_culvert.start_pointy is '起始点Y坐标';
comment on column daq_cross_box_culvert.start_pointz is '起始点高程(m)';
comment on column daq_cross_box_culvert.end_pointx is '结束点X坐标';
comment on column daq_cross_box_culvert.end_pointy is '结束点Y坐标';
comment on column daq_cross_box_culvert.end_pointz is '结束点高程(m)';
comment on column daq_cross_box_culvert.commencement_date is '开工日期';
comment on column daq_cross_box_culvert.completion_date is '完工日期';
comment on column daq_cross_box_culvert.construct_unit is '施工单位';
comment on column daq_cross_box_culvert.supervision_unit is '监理单位';
comment on column daq_cross_box_culvert.supervision_engineer is '监理工程师';
comment on column daq_cross_box_culvert.collection_person is '采集人员';
comment on column daq_cross_box_culvert.collection_date is '采集日期';
comment on column daq_cross_box_culvert.geo_state is '空间数据状态';
comment on column daq_cross_box_culvert.approve_status is '审核状态';
comment on column daq_cross_box_culvert.remarks is '备注';
comment on column daq_cross_box_culvert.create_user_id is '创建人id';
comment on column daq_cross_box_culvert.create_user_name is '创建人名称';
comment on column daq_cross_box_culvert.create_datetime is '创建时间';
comment on column daq_cross_box_culvert.modify_user_id is '修改人id';
comment on column daq_cross_box_culvert.modify_user_name is '修改人名称';
comment on column daq_cross_box_culvert.modify_datetime is '修改时间';
comment on column daq_cross_box_culvert.active is '有效标志';
create index INDEX_daq_cross_box_culvert_CROSS_OID_8 ON daq_cross_box_culvert ( cross_oid );
select AddGeometryColumn('public', 'daq_cross_box_culvert', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_box_culvert_geom_idx ON public.daq_cross_box_culvert USING gist (geom);

CREATE TABLE daq_cross_drilling (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	cross_oid VARCHAR (36),
	crossing_department VARCHAR (50),
	cross_length NUMERIC (9, 2),
	cross_max_length NUMERIC (10, 2),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (8, 0),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (8, 0),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (9, 3),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (9, 3),
	exit_angle NUMERIC (12, 6),
	enter_angle NUMERIC (12, 6),
	commencement_date TIMESTAMP (6),
	completion_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_cross_drilling is '定向钻穿越表';
comment on column daq_cross_drilling.oid is '主键';
comment on column daq_cross_drilling.project_oid is '项目oid';
comment on column daq_cross_drilling.tenders_oid is '标段oid';
comment on column daq_cross_drilling.pipeline_oid is '管线oid';
comment on column daq_cross_drilling.cross_oid is '穿跨越oid';
comment on column daq_cross_drilling.crossing_department is '穿越物管理单位';
comment on column daq_cross_drilling.cross_length is '穿越长度(m)';
comment on column daq_cross_drilling.cross_max_length is '穿越最大深度(m)';
comment on column daq_cross_drilling.start_median_stake_oid is '起始桩号';
comment on column daq_cross_drilling.start_relative_mileage is '相对起始桩位置(m)';
comment on column daq_cross_drilling.end_median_stake_oid is '结束桩号';
comment on column daq_cross_drilling.end_relative_mileage is '相对结束桩位置(m)';
comment on column daq_cross_drilling.start_pointx is '入土点X坐标';
comment on column daq_cross_drilling.start_pointy is '入土点Y坐标';
comment on column daq_cross_drilling.start_pointz is '入土点高程(m)';
comment on column daq_cross_drilling.end_pointx is '出土点X坐标';
comment on column daq_cross_drilling.end_pointy is '出土点Y坐标';
comment on column daq_cross_drilling.end_pointz is '出土点高程(m)';
comment on column daq_cross_drilling.exit_angle is '入土角';
comment on column daq_cross_drilling.enter_angle is '出土角';
comment on column daq_cross_drilling.commencement_date is '开工日期';
comment on column daq_cross_drilling.completion_date is '完工日期';
comment on column daq_cross_drilling.construct_unit is '施工单位';
comment on column daq_cross_drilling.supervision_unit is '监理单位';
comment on column daq_cross_drilling.supervision_engineer is '监理工程师';
comment on column daq_cross_drilling.collection_person is '采集人员';
comment on column daq_cross_drilling.collection_date is '采集日期';
comment on column daq_cross_drilling.geo_state is '空间数据状态';
comment on column daq_cross_drilling.approve_status is '审核状态';
comment on column daq_cross_drilling.remarks is '备注';
comment on column daq_cross_drilling.create_user_id is '创建人id';
comment on column daq_cross_drilling.create_user_name is '创建人名称';
comment on column daq_cross_drilling.create_datetime is '创建时间';
comment on column daq_cross_drilling.modify_user_id is '修改人id';
comment on column daq_cross_drilling.modify_user_name is '修改人名称';
comment on column daq_cross_drilling.modify_datetime is '修改时间';
comment on column daq_cross_drilling.active is '有效标志';
create index INDEX_daq_cross_drilling_CROSS_OID_8 ON daq_cross_drilling ( cross_oid );
select AddGeometryColumn('public', 'daq_cross_drilling', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_drilling_geom_idx ON public.daq_cross_drilling USING gist (geom);

CREATE TABLE daq_cross_shield (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	cross_oid VARCHAR (36),
	crossing_department VARCHAR (50),
	cross_length NUMERIC (9, 2),
	cross_max_length NUMERIC (10, 2),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (8, 0),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (8, 0),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (9, 3),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (9, 3),
	commencement_date TIMESTAMP (6),
	completion_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_cross_shield is '盾构隧道穿越表';
comment on column daq_cross_shield.oid is '主键';
comment on column daq_cross_shield.project_oid is '项目oid';
comment on column daq_cross_shield.tenders_oid is '标段oid';
comment on column daq_cross_shield.pipeline_oid is '管线oid';
comment on column daq_cross_shield.cross_oid is '穿跨越oid';
comment on column daq_cross_shield.crossing_department is '穿越物管理单位';
comment on column daq_cross_shield.cross_length is '穿越长度(m)';
comment on column daq_cross_shield.cross_max_length is '穿越最大深度(m)';
comment on column daq_cross_shield.start_median_stake_oid is '起始桩号';
comment on column daq_cross_shield.start_relative_mileage is '相对起始桩位置(m)';
comment on column daq_cross_shield.end_median_stake_oid is '结束桩号';
comment on column daq_cross_shield.end_relative_mileage is '相对结束桩位置(m)';
comment on column daq_cross_shield.start_pointx is '起始点X坐标';
comment on column daq_cross_shield.start_pointy is '起始点Y坐标';
comment on column daq_cross_shield.start_pointz is '起始点高程(m)';
comment on column daq_cross_shield.end_pointx is '结束点X坐标';
comment on column daq_cross_shield.end_pointy is '结束点Y坐标';
comment on column daq_cross_shield.end_pointz is '结束点高程(m)';
comment on column daq_cross_shield.commencement_date is '开工日期';
comment on column daq_cross_shield.completion_date is '完工日期';
comment on column daq_cross_shield.construct_unit is '施工单位';
comment on column daq_cross_shield.supervision_unit is '监理单位';
comment on column daq_cross_shield.supervision_engineer is '监理工程师';
comment on column daq_cross_shield.collection_person is '采集人员';
comment on column daq_cross_shield.collection_date is '采集日期';
comment on column daq_cross_shield.geo_state is '空间数据状态';
comment on column daq_cross_shield.approve_status is '审核状态';
comment on column daq_cross_shield.remarks is '备注';
comment on column daq_cross_shield.create_user_id is '创建人id';
comment on column daq_cross_shield.create_user_name is '创建人名称';
comment on column daq_cross_shield.create_datetime is '创建时间';
comment on column daq_cross_shield.modify_user_id is '修改人id';
comment on column daq_cross_shield.modify_user_name is '修改人名称';
comment on column daq_cross_shield.modify_datetime is '修改时间';
comment on column daq_cross_shield.active is '有效标志';
create index INDEX_daq_cross_shield_CROSS_OID_8 ON daq_cross_shield ( cross_oid );
select AddGeometryColumn('public', 'daq_cross_shield', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_shield_geom_idx ON public.daq_cross_shield USING gist (geom);

CREATE TABLE daq_cross_drilling_blasting (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	cross_oid VARCHAR (36),
	crossing_department VARCHAR (50),
	cross_length NUMERIC (9, 2),
	cross_max_length NUMERIC (10, 2),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (8, 0),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (8, 0),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (9, 3),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (9, 3),
	steady_tube_measures VARCHAR(200),
	cofferdam_grade VARCHAR(50),
	lining_type VARCHAR(50),
	commencement_date TIMESTAMP (6),
	completion_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_cross_drilling_blasting is '钻爆隧道穿越表';
comment on column daq_cross_drilling_blasting.oid is '主键';
comment on column daq_cross_drilling_blasting.project_oid is '项目oid';
comment on column daq_cross_drilling_blasting.tenders_oid is '标段oid';
comment on column daq_cross_drilling_blasting.pipeline_oid is '管线oid';
comment on column daq_cross_drilling_blasting.cross_oid is '穿跨越oid';
comment on column daq_cross_drilling_blasting.crossing_department is '穿越物管理单位';
comment on column daq_cross_drilling_blasting.cross_length is '穿越长度(m)';
comment on column daq_cross_drilling_blasting.cross_max_length is '穿越最大深度(m)';
comment on column daq_cross_drilling_blasting.start_median_stake_oid is '起始桩号';
comment on column daq_cross_drilling_blasting.start_relative_mileage is '相对起始桩位置(m)';
comment on column daq_cross_drilling_blasting.end_median_stake_oid is '结束桩号';
comment on column daq_cross_drilling_blasting.end_relative_mileage is '相对结束桩位置(m)';
comment on column daq_cross_drilling_blasting.start_pointx is '起始点X坐标';
comment on column daq_cross_drilling_blasting.start_pointy is '起始点Y坐标';
comment on column daq_cross_drilling_blasting.start_pointz is '起始点高程(m)';
comment on column daq_cross_drilling_blasting.end_pointx is '结束点X坐标';
comment on column daq_cross_drilling_blasting.end_pointy is '结束点Y坐标';
comment on column daq_cross_drilling_blasting.end_pointz is '结束点高程(m)';
comment on column daq_cross_drilling_blasting.steady_tube_measures is '稳管措施';
comment on column daq_cross_drilling_blasting.cofferdam_grade is '钻爆围堰等级';
comment on column daq_cross_drilling_blasting.lining_type is '衬砌形式';
comment on column daq_cross_drilling_blasting.commencement_date is '开工日期';
comment on column daq_cross_drilling_blasting.completion_date is '完工日期';
comment on column daq_cross_drilling_blasting.construct_unit is '施工单位';
comment on column daq_cross_drilling_blasting.supervision_unit is '监理单位';
comment on column daq_cross_drilling_blasting.supervision_engineer is '监理工程师';
comment on column daq_cross_drilling_blasting.collection_person is '采集人员';
comment on column daq_cross_drilling_blasting.collection_date is '采集日期';
comment on column daq_cross_drilling_blasting.geo_state is '空间数据状态';
comment on column daq_cross_drilling_blasting.approve_status is '审核状态';
comment on column daq_cross_drilling_blasting.remarks is '备注';
comment on column daq_cross_drilling_blasting.create_user_id is '创建人id';
comment on column daq_cross_drilling_blasting.create_user_name is '创建人名称';
comment on column daq_cross_drilling_blasting.create_datetime is '创建时间';
comment on column daq_cross_drilling_blasting.modify_user_id is '修改人id';
comment on column daq_cross_drilling_blasting.modify_user_name is '修改人名称';
comment on column daq_cross_drilling_blasting.modify_datetime is '修改时间';
comment on column daq_cross_drilling_blasting.active is '有效标志';
create index INDEX_daq_cross_drilling_blasting_CROSS_OID_8 ON daq_cross_drilling_blasting ( cross_oid );
select AddGeometryColumn('public', 'daq_cross_drilling_blasting', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_drilling_blasting_geom_idx ON public.daq_cross_drilling_blasting USING gist (geom);

CREATE TABLE daq_cross_across (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	cross_oid VARCHAR (36),
	crossing_department VARCHAR (50),
	across_type VARCHAR (50),
	cross_length NUMERIC (9, 2),
	cross_max_length NUMERIC (10, 2),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (8, 0),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (8, 0),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (9, 3),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (9, 3),
	commencement_date TIMESTAMP (6),
	completion_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active SMALLINT NOT NULL
);
comment on table daq_cross_across is '跨越表';
comment on column daq_cross_across.oid is '主键';
comment on column daq_cross_across.project_oid is '项目oid';
comment on column daq_cross_across.tenders_oid is '标段oid';
comment on column daq_cross_across.pipeline_oid is '管线oid';
comment on column daq_cross_across.cross_oid is '穿跨越oid';
comment on column daq_cross_across.crossing_department is '穿越物管理单位';
comment on column daq_cross_across.cross_length is '穿越长度(m)';
comment on column daq_cross_across.across_type is '跨越类型';
comment on column daq_cross_across.cross_max_length is '穿越最大深度(m)';
comment on column daq_cross_across.start_median_stake_oid is '起始桩号';
comment on column daq_cross_across.start_relative_mileage is '相对起始桩位置(m)';
comment on column daq_cross_across.end_median_stake_oid is '结束桩号';
comment on column daq_cross_across.end_relative_mileage is '相对结束桩位置(m)';
comment on column daq_cross_across.start_pointx is '起始点X坐标';
comment on column daq_cross_across.start_pointy is '起始点Y坐标';
comment on column daq_cross_across.start_pointz is '起始点高程(m)';
comment on column daq_cross_across.end_pointx is '结束点X坐标';
comment on column daq_cross_across.end_pointy is '结束点Y坐标';
comment on column daq_cross_across.end_pointz is '结束点高程(m)';
comment on column daq_cross_across.commencement_date is '开工日期';
comment on column daq_cross_across.completion_date is '完工日期';
comment on column daq_cross_across.construct_unit is '施工单位';
comment on column daq_cross_across.supervision_unit is '监理单位';
comment on column daq_cross_across.supervision_engineer is '监理工程师';
comment on column daq_cross_across.collection_person is '采集人员';
comment on column daq_cross_across.collection_date is '采集日期';
comment on column daq_cross_across.geo_state is '空间数据状态';
comment on column daq_cross_across.approve_status is '审核状态';
comment on column daq_cross_across.remarks is '备注';
comment on column daq_cross_across.create_user_id is '创建人id';
comment on column daq_cross_across.create_user_name is '创建人名称';
comment on column daq_cross_across.create_datetime is '创建时间';
comment on column daq_cross_across.modify_user_id is '修改人id';
comment on column daq_cross_across.modify_user_name is '修改人名称';
comment on column daq_cross_across.modify_datetime is '修改时间';
comment on column daq_cross_across.active is '有效标志';
create index INDEX_daq_cross_across_CROSS_OID_8 ON daq_cross_across ( cross_oid );
select AddGeometryColumn('public', 'daq_cross_across', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_across_geom_idx ON public.daq_cross_across USING gist (geom);
/**********管道穿跨越信息end***************/