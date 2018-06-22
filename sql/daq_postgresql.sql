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
	construct_oid VARCHAR (36),
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
comment on column daq_project.construct_oid is '建设单位编号';
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
	active SMALLINT
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