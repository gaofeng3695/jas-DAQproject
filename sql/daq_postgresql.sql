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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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

	
CREATE TABLE daq_implement_scope_ref (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	unit_oid VARCHAR (36),
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid varchar(36) ,
	scope_oid VARCHAR (36),
	scope_type VARCHAR (10),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_implement_scope_ref is '实施范围关联表';
comment on column daq_implement_scope_ref.oid is '主键';
comment on column daq_implement_scope_ref.unit_oid is '部门oid';
comment on column daq_implement_scope_ref.project_oid is '项目oid';
comment on column daq_implement_scope_ref.pipeline_oid is '管线oid';
COMMENT ON COLUMN daq_implement_scope_ref.tenders_oid IS '标段oid';
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	construct_unit varchar(36),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
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
comment on column daq_material_closure.construct_unit IS '施工单位';
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
	construct_unit varchar(36),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
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
comment on column daq_material_flange.construct_unit IS '施工单位';
comment on column daq_material_flange.remarks IS '备注';
comment on column daq_material_flange.create_user_id IS '创建人id';
comment on column daq_material_flange.create_user_name IS '创建人名称';
comment on column daq_material_flange.create_datetime IS '创建时间';
comment on column daq_material_flange.modify_user_id IS '修改人id';
comment on column daq_material_flange.modify_user_name IS '修改人名称';
comment on column daq_material_flange.modify_datetime IS '修改时间';
comment on column daq_material_flange.active IS '有效标志';

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
	construct_unit varchar(36),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
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
comment on column daq_material_hot_bends.construct_unit IS '施工单位';
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
	construct_unit varchar(36),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
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
comment on column daq_material_insulated_joint.construct_unit IS '施工单位';
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
	is_cold_bend SMALLINT DEFAULT 0,
	is_child SMALLINT DEFAULT 0,
	construct_unit varchar(36),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
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
comment on column daq_material_pipe.is_cold_bend IS '是否冷弯';
COMMENT ON COLUMN daq_material_pipe.is_child IS '是否是子管，1为是，0为否';
comment on column daq_material_pipe.construct_unit IS '施工单位';
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
	pipe_segment_or_cross_oid varchar(36),
	pipe_oid varchar(36),
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
	approve_status SMALLINT DEFAULT 0,
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
);
comment on table daq_material_pipe_cold_bending IS '冷弯管表';
comment on column daq_material_pipe_cold_bending.oid IS '主键';
comment on column daq_material_pipe_cold_bending.project_oid IS '项目oid';
comment on column daq_material_pipe_cold_bending.tenders_oid IS '标段oid';
comment on column daq_material_pipe_cold_bending.pipeline_oid IS '管线oid';
comment on column daq_material_pipe_cold_bending.pipe_segment_or_cross_oid IS '线路段/穿跨越oid';
comment on column daq_material_pipe_cold_bending.pipe_oid IS '原材料钢管oid';
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
comment on column daq_material_pipe_cold_bending.approve_status IS '审核状态';
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
	construct_unit varchar(36),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
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
comment on column daq_material_reducer.construct_unit IS '施工单位';
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
	construct_unit varchar(36),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
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
comment on column daq_material_tee.construct_unit IS '施工单位';
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
CREATE INDEX index_daq_material_pipe_cold_bending_pipe_code_9 ON daq_material_pipe_cold_bending USING btree (pipe_oid);
ALTER TABLE daq_material_pipe_cold_bending ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_reducer_reducer_code_5 ON daq_material_reducer USING btree (reducer_code);
ALTER TABLE daq_material_reducer ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_material_tee_tee_code_5 ON daq_material_tee USING btree (tee_code);
ALTER TABLE daq_material_tee ADD PRIMARY KEY (oid);


/**********线路物资基本信息end***************/
/**********线路物资检查信息begin***************/
create table daq_check_coating_pipe( 
	oid varchar(36) not null,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construct_unit varchar(36),
	pipe_oid varchar(50),
	groove_check smallint,
	pipe_end_proring_check smallint,
	coating_io_face_check smallint,
	diameter_check smallint,
	coating_io_ends_check smallint,
	excess_weld_metal smallint,
	ovality smallint,
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
); 
comment on table daq_check_coating_pipe is '防腐管检查及信息记录表';
comment on column daq_check_coating_pipe.oid is '主键';
comment on column daq_check_coating_pipe.project_oid is '项目oid';
comment on column daq_check_coating_pipe.tenders_oid is '标段oid';
comment on column daq_check_coating_pipe.pipe_oid is '钢管编号';
comment on column daq_check_coating_pipe.groove_check is '坡口检查';
comment on column daq_check_coating_pipe.pipe_end_proring_check is '管端保护圈';
comment on column daq_check_coating_pipe.coating_io_face_check is '防腐层内外表面质量';
comment on column daq_check_coating_pipe.diameter_check is '管径偏差+0.2mm至-0.5mm';
comment on column daq_check_coating_pipe.coating_io_ends_check is '防腐层端部内外涂层';
comment on column daq_check_coating_pipe.excess_weld_metal is '管端焊缝余高（0mm）';
comment on column daq_check_coating_pipe.ovality is '椭圆度<0.6%D';
comment on column daq_check_coating_pipe.construct_unit is '施工单位oid';
comment on column daq_check_coating_pipe.checked_by is '检查人';
comment on column daq_check_coating_pipe.checked_date is '检查日期';
comment on column daq_check_coating_pipe.remarks is '备注';
comment on column daq_check_coating_pipe.create_user_id is '创建人id';
comment on column daq_check_coating_pipe.create_user_name is '创建人名称';
comment on column daq_check_coating_pipe.create_datetime is '创建时间';
comment on column daq_check_coating_pipe.modify_user_id is '修改人id';
comment on column daq_check_coating_pipe.modify_user_name is '修改人名称';
comment on column daq_check_coating_pipe.modify_datetime is '修改时间';
comment on column daq_check_coating_pipe.active is '有效标志';

create table daq_check_hot_bends( 
	oid varchar(36) not null,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construct_unit varchar(36),
	hot_bends_oid varchar(50),
	weld_position smallint,
	pipe_length smallint,
	ovality smallint,
	groove_check smallint,
	coating_io_face_check smallint,
	coating_io_ends_check smallint,
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
);
comment on table daq_check_hot_bends is '热煨弯管检查信息记录表';
comment on column daq_check_hot_bends.oid is '主键';
comment on column daq_check_hot_bends.project_oid is '项目oid';
comment on column daq_check_hot_bends.tenders_oid is '标段oid';
comment on column daq_check_hot_bends.hot_bends_oid is '弯管编号';
comment on column daq_check_hot_bends.weld_position is '纵焊缝位置';
comment on column daq_check_hot_bends.pipe_length is '直管段长度';
comment on column daq_check_hot_bends.ovality is '椭圆度<0.6%D';
comment on column daq_check_hot_bends.groove_check is '坡口检查';
comment on column daq_check_hot_bends.coating_io_face_check is '防腐层内外表面质量';
comment on column daq_check_hot_bends.coating_io_ends_check is '防腐层端部内外涂层';
comment on column daq_check_hot_bends.construct_unit is '施工单位oid';
comment on column daq_check_hot_bends.checked_by is '检查人';
comment on column daq_check_hot_bends.checked_date is '检查日期';
comment on column daq_check_hot_bends.remarks is '备注';
comment on column daq_check_hot_bends.create_user_id is '创建人id';
comment on column daq_check_hot_bends.create_user_name is '创建人名称';
comment on column daq_check_hot_bends.create_datetime is '创建时间';
comment on column daq_check_hot_bends.modify_user_id is '修改人id';
comment on column daq_check_hot_bends.modify_user_name is '修改人名称';
comment on column daq_check_hot_bends.modify_datetime is '修改时间';
comment on column daq_check_hot_bends.active is '有效标志';

CREATE TABLE daq_check_insulated_joint (
	oid varchar(36) NOT NULL,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construct_unit varchar(36),
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
	active smallint not null default 1
);
comment on table daq_check_insulated_joint IS '绝缘接头检查及信息记录表';
comment on column daq_check_insulated_joint.oid IS '主键';
comment on column daq_check_insulated_joint.project_oid IS '项目oid';
comment on column daq_check_insulated_joint.tenders_oid IS '标段oid';
comment on column daq_check_insulated_joint.manufacturer_code IS '出厂编号';
comment on column daq_check_insulated_joint.certification_num IS '合格证编号';
comment on column daq_check_insulated_joint.diameter IS '公称直径(mm)';
comment on column daq_check_insulated_joint.pressure IS '公称压力(MPa)';
comment on column daq_check_insulated_joint.manufacturer IS '生产厂家';
comment on column daq_check_insulated_joint.test_equipment IS '测试仪器';
comment on column daq_check_insulated_joint.specand_model IS '仪器规格型号';
comment on column daq_check_insulated_joint.resistance_val IS '实测绝缘电阻值(MΩ)';
comment on column daq_check_insulated_joint.check_results IS '验收结论';
comment on column daq_check_insulated_joint.construct_unit IS '施工单位';
comment on column daq_check_insulated_joint.remarks IS '备注';
comment on column daq_check_insulated_joint.create_user_id IS '创建人id';
comment on column daq_check_insulated_joint.create_user_name IS '创建人名称';
comment on column daq_check_insulated_joint.create_datetime IS '创建时间';
comment on column daq_check_insulated_joint.modify_user_id IS '修改人id';
comment on column daq_check_insulated_joint.modify_user_name IS '修改人名称';
comment on column daq_check_insulated_joint.modify_datetime IS '修改时间';
comment on column daq_check_insulated_joint.active IS '有效标志';

create table daq_check_pipe_cold_bending( 
	oid varchar(36) not null,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construct_unit varchar(36),
	pipe_cold_bending_oid varchar(50),
	certificate_num varchar(60),
	pipe_length numeric(9,3),
	pipe_diameter numeric(9,3),
	wall_thickness numeric(9,3),
	production_unit varchar(60),
	bend_angle numeric(4,0),
	weld_position smallint,
	ovality smallint,
	groove_check smallint,
	coating_io_face_check smallint,
	coating_io_ends_check smallint,
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
); 
comment on table daq_check_pipe_cold_bending is '冷弯管检查及信息记录表';
comment on column daq_check_pipe_cold_bending.oid is '主键';
comment on column daq_check_pipe_cold_bending.project_oid is '项目oid';
comment on column daq_check_pipe_cold_bending.tenders_oid is '标段oid';
comment on column daq_check_pipe_cold_bending.pipe_cold_bending_oid is '冷弯管编号';
comment on column daq_check_pipe_cold_bending.certificate_num is '合格证编号';
comment on column daq_check_pipe_cold_bending.pipe_length is '弯管长度(m)';
comment on column daq_check_pipe_cold_bending.pipe_diameter is '管径(mm)';
comment on column daq_check_pipe_cold_bending.wall_thickness is '壁厚(mm）';
comment on column daq_check_pipe_cold_bending.production_unit is '弯制单位';
comment on column daq_check_pipe_cold_bending.bend_angle is '弯制角度(°）';
comment on column daq_check_pipe_cold_bending.weld_position is '纵焊缝位置';
comment on column daq_check_pipe_cold_bending.ovality is '椭圆度<0.6%D';
comment on column daq_check_pipe_cold_bending.groove_check is '坡口检查';
comment on column daq_check_pipe_cold_bending.coating_io_face_check is '防腐层内外表面质量';
comment on column daq_check_pipe_cold_bending.coating_io_ends_check is '防腐层端部内外涂层';
comment on column daq_check_pipe_cold_bending.construct_unit is '施工单位oid';
comment on column daq_check_pipe_cold_bending.checked_by is '检查人';
comment on column daq_check_pipe_cold_bending.checked_date is '检查日期';
comment on column daq_check_pipe_cold_bending.remarks is '备注';
comment on column daq_check_pipe_cold_bending.create_user_id is '创建人id';
comment on column daq_check_pipe_cold_bending.create_user_name is '创建人名称';
comment on column daq_check_pipe_cold_bending.create_datetime is '创建时间';
comment on column daq_check_pipe_cold_bending.modify_user_id is '修改人id';
comment on column daq_check_pipe_cold_bending.modify_user_name is '修改人名称';
comment on column daq_check_pipe_cold_bending.modify_datetime is '修改时间';
comment on column daq_check_pipe_cold_bending.active is '有效标志';

create table daq_check_reducer( 
oid varchar(36) not null,
project_oid varchar(36),
tenders_oid varchar(36),
construct_unit varchar(36),
reducer_code_oid varchar(36),
ovality smallint,
groove_check smallint,
coating_io_face_check smallint,
coating_io_ends_check smallint,
checked_by varchar(20),
checked_date timestamp(6),
remarks varchar(200),
create_user_id varchar(36),
create_user_name varchar(50),
create_datetime timestamp(6),
modify_user_id varchar(36),
modify_user_name varchar(50),
modify_datetime timestamp(6),
active smallint not null default 1
); 
comment on table daq_check_reducer is '大小头检查及信息记录表';
comment on column daq_check_reducer.oid is '主键';
comment on column daq_check_reducer.project_oid is '项目oid';
comment on column daq_check_reducer.tenders_oid is '标段oid';
comment on column daq_check_reducer.reducer_code_oid is '大小头编号';
comment on column daq_check_reducer.ovality is '椭圆度<0.6%D';
comment on column daq_check_reducer.groove_check is '坡口检查';
comment on column daq_check_reducer.coating_io_face_check is '防腐层内外表面质量';
comment on column daq_check_reducer.coating_io_ends_check is '防腐层端部内外涂层';
comment on column daq_check_reducer.construct_unit is '施工单位oid';
comment on column daq_check_reducer.checked_by is '检查人';
comment on column daq_check_reducer.checked_date is '检查日期';
comment on column daq_check_reducer.remarks is '备注';
comment on column daq_check_reducer.create_user_id is '创建人id';
comment on column daq_check_reducer.create_user_name is '创建人名称';
comment on column daq_check_reducer.create_datetime is '创建时间';
comment on column daq_check_reducer.modify_user_id is '修改人id';
comment on column daq_check_reducer.modify_user_name is '修改人名称';
comment on column daq_check_reducer.modify_datetime is '修改时间';
comment on column daq_check_reducer.active is '有效标志';

create table daq_check_tee( 
	oid varchar(36) not null,
	project_oid varchar(36),
	tenders_oid varchar(36),
	construct_unit varchar(36),
	tee_code_oid varchar(36),
	pipe_wall_thickness numeric(9,3),
	branch_wall_thickness numeric(9,3),
	ovality smallint,
	groove_check smallint,
	coating_io_face_check smallint,
	coating_io_ends_check smallint,
	checked_by varchar(20),
	checked_date timestamp(6),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
); 

comment on table daq_check_tee is '三通检查及信息记录表';
comment on column daq_check_tee.oid is '主键';
comment on column daq_check_tee.project_oid is '项目oid';
comment on column daq_check_tee.tenders_oid is '标段oid';
comment on column daq_check_tee.tee_code_oid is '三通编号';
comment on column daq_check_tee.pipe_wall_thickness is '管端壁厚(mm)';
comment on column daq_check_tee.branch_wall_thickness is '拔制端壁厚（mm）';
comment on column daq_check_tee.ovality is '椭圆度<0.6%D';
comment on column daq_check_tee.groove_check is '坡口检查';
comment on column daq_check_tee.coating_io_face_check is '防腐层内外表面质量';
comment on column daq_check_tee.coating_io_ends_check is '防腐层端部内外涂层';
comment on column daq_check_tee.construct_unit is '施工单位oid';
comment on column daq_check_tee.checked_by is '检查人';
comment on column daq_check_tee.checked_date is '检查日期';
comment on column daq_check_tee.remarks is '备注';
comment on column daq_check_tee.create_user_id is '创建人id';
comment on column daq_check_tee.create_user_name is '创建人名称';
comment on column daq_check_tee.create_datetime is '创建时间';
comment on column daq_check_tee.modify_user_id is '修改人id';
comment on column daq_check_tee.modify_user_name is '修改人名称';
comment on column daq_check_tee.modify_datetime is '修改时间';
comment on column daq_check_tee.active is '有效标志';

CREATE INDEX index_daq_check_coating_pipe_pipe_code_5 ON daq_check_coating_pipe USING btree (pipe_oid);
ALTER TABLE daq_check_coating_pipe ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_hot_bends_hot_bends_code_5 ON daq_check_hot_bends USING btree (hot_bends_oid);
ALTER TABLE daq_check_hot_bends ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_insulated_joint_manufacturer_code_5 ON daq_check_insulated_joint USING btree (manufacturer_code);
ALTER TABLE daq_check_insulated_joint ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_pipe_cold_bending_pipe_cold_bending_code_5 ON daq_check_pipe_cold_bending USING btree (pipe_cold_bending_oid);
ALTER TABLE daq_check_pipe_cold_bending ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_reducer_reducer_code_5 ON daq_check_reducer USING btree (reducer_code_oid);
ALTER TABLE daq_check_reducer ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_check_tee_tee_code_5 ON daq_check_tee USING btree (tee_code_oid);
ALTER TABLE daq_check_tee ADD PRIMARY KEY (oid);
/**********线路物资检查信息end***************/
/**********站/阀/井物资基本信息begin***************/
create table daq_material_valve( 
	oid varchar(36) not null primary key,
	project_oid varchar(36)  ,
	pipeline_oid varchar(36)  ,
	tenders_oid varchar(36)  ,
	valve_name varchar(50)  ,
	valve_type varchar(50)  ,
	material_code varchar(50)  ,
	valve_model varchar(50)  ,
	valve_axial_size numeric(3,1)  ,
	valve_weight numeric(6,1)  ,
	torque numeric(6,1)  ,
	valve_body_seal_type varchar(50)  ,
	valve_body_hard_seal_material varchar(50)  ,
	valve_body_soft_seal_material varchar(50)  ,
	valve_handle_diameter numeric(6,1)  ,
	valve_handle_seal_type varchar(50)  ,
	valve_handle_seal_material varchar(50)  ,
	flange_standard varchar(50)  ,
	flange_sealing_surface_type varchar(50)  ,
	manufacture_number varchar(50)  ,
	manufacturer varchar(60)  ,
	manufacture_date timestamp(6)  ,
	guarantee_period smallint  ,
	contract_number varchar(60)  ,
	is_use smallint  default 0 ,
	construct_unit varchar(36)  ,
	remarks varchar(200)  ,
	create_user_id varchar(36)  ,
	create_user_name varchar(50)  ,
	create_datetime timestamp(6)  ,
	modify_user_id varchar(36)  ,
	modify_user_name varchar(50)  ,
	modify_datetime timestamp(6)  ,
	active smallint not null default 1  
); 
comment on table daq_material_valve is '阀门表';
comment on column daq_material_valve.oid is '主键';
comment on column daq_material_valve.project_oid is '项目oid';
comment on column daq_material_valve.pipeline_oid is '管线oid';
comment on column daq_material_valve.tenders_oid is '标段oid';
comment on column daq_material_valve.valve_name is '阀门名称';
comment on column daq_material_valve.valve_type is '阀门类型';
comment on column daq_material_valve.material_code is '物料码';
comment on column daq_material_valve.valve_model is '规格型号';
comment on column daq_material_valve.valve_axial_size is '阀门轴向尺寸(M)';
comment on column daq_material_valve.valve_weight is '阀门重量(Kg)';
comment on column daq_material_valve.torque is '扭矩(Nm)';
comment on column daq_material_valve.valve_body_seal_type is '阀体密封形式';
comment on column daq_material_valve.valve_body_hard_seal_material is '阀体硬密封材质';
comment on column daq_material_valve.valve_body_soft_seal_material is '阀体软密封材质';
comment on column daq_material_valve.valve_handle_diameter is '阀杆直径(mm)';
comment on column daq_material_valve.valve_handle_seal_type is '阀杆密封形式';
comment on column daq_material_valve.valve_handle_seal_material is '阀杆密封材质';
comment on column daq_material_valve.flange_standard is '法兰标准';
comment on column daq_material_valve.flange_sealing_surface_type is '法兰密封面型式';
comment on column daq_material_valve.manufacture_number is '出厂编号';
comment on column daq_material_valve.manufacturer is '生产厂家';
comment on column daq_material_valve.manufacture_date is '出厂时间';
comment on column daq_material_valve.guarantee_period is '质保期(月)';
comment on column daq_material_valve.contract_number is '合同号';
comment on column daq_material_valve.construct_unit is '施工单位';
comment on column daq_material_valve.is_use is '是否使用';
comment on column daq_material_valve.remarks is '备注';
comment on column daq_material_valve.create_user_id is '创建人id';
comment on column daq_material_valve.create_user_name is '创建人名称';
comment on column daq_material_valve.create_datetime is '创建时间';
comment on column daq_material_valve.modify_user_id is '修改人id';
comment on column daq_material_valve.modify_user_name is '修改人名称';
comment on column daq_material_valve.modify_datetime is '修改时间';
comment on column daq_material_valve.active is '有效标志';
/**********站/阀/井物资基本信息end***************/
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
	front_pipe_oid varchar(36),
	back_pipe_type varchar(50),
	back_pipe_oid varchar(36),
	weld_rod_batch_num varchar(60),
	weld_wire_batch_num varchar(60),
	weld_produce varchar(36),
	surface_check SMALLINT,
	construct_unit varchar(36),
	work_unit_oid varchar(36),
	cover_oid varchar(360),
	padder_oid varchar(360),
	render_oid varchar(360),
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
	active smallint not null default 1
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
comment on column daq_construction_weld.front_pipe_oid IS '前管件编号';
comment on column daq_construction_weld.back_pipe_type IS '后管件类型';
comment on column daq_construction_weld.back_pipe_oid IS '后管件编号';
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
alter table daq_construction_weld add is_cut smallint default 0;
comment on column daq_construction_weld.is_cut IS '是否割口';
alter table daq_construction_weld add is_ray smallint default 0;
comment on column daq_construction_weld.is_ray IS '是否射线检测';
alter table daq_construction_weld add is_ultrasonic smallint default 0;
comment on column daq_construction_weld.is_ultrasonic IS '是否超声波检查';
alter table daq_construction_weld add is_infiltration smallint default 0;
comment on column daq_construction_weld.is_infiltration IS '是否渗透检测';
alter table daq_construction_weld add is_magnetic_powder smallint default 0;
comment on column daq_construction_weld.is_magnetic_powder IS '是否磁粉检测';
alter table daq_construction_weld add is_fa_ultrasonic smallint default 0;
comment on column daq_construction_weld.is_fa_ultrasonic IS '是否全自动检测';
alter table daq_construction_weld add is_pa_ultrasonic smallint default 0;
comment on column daq_construction_weld.is_pa_ultrasonic IS '相控阵超声波检测';



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
	buckle_conclusion SMALLINT,
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	active smallint not null default 1
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
	weld_oid varchar(36),
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
	active smallint not null default 1
);
comment on table daq_weld_cut IS '焊口割口信息表';
comment on column daq_weld_cut.oid IS '主键';
comment on column daq_weld_cut.project_oid IS '项目oid';
comment on column daq_weld_cut.pipeline_oid IS '管线oid';
comment on column daq_weld_cut.tenders_oid IS '标段oid';
comment on column daq_weld_cut.pipe_segment_or_cross_oid IS '线路段/穿跨越';
comment on column daq_weld_cut.weld_oid IS '割口编号';
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
	cover_oid varchar(360),
	padder_oid varchar(360),
	render_oid varchar(360),
	weld_date timestamp(6),
	construct_unit varchar(36),
	work_unit_oid varchar(36),
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
	active smallint not null default 1
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
comment on column daq_weld_rework_weld.geo_state IS '空间数据状态';
comment on column daq_weld_rework_weld.remarks IS '备注';
comment on column daq_weld_rework_weld.create_user_id IS '创建人id';
comment on column daq_weld_rework_weld.create_user_name IS '创建人名称';
comment on column daq_weld_rework_weld.create_datetime IS '创建时间';
comment on column daq_weld_rework_weld.modify_user_id IS '修改人id';
comment on column daq_weld_rework_weld.modify_user_name IS '修改人名称';
comment on column daq_weld_rework_weld.modify_datetime IS '修改时间';
comment on column daq_weld_rework_weld.active IS '有效标志';

alter table daq_weld_rework_weld add is_cut smallint default 0;
comment on column daq_weld_rework_weld.is_cut IS '是否割口';

alter table daq_weld_rework_weld add is_ray smallint default 0;
comment on column daq_weld_rework_weld.is_ray IS '是否射线检测';

alter table daq_weld_rework_weld add is_ultrasonic smallint default 0;
comment on column daq_weld_rework_weld.is_ultrasonic IS '是否超声波检查';

alter table daq_weld_rework_weld add is_infiltration smallint default 0;
comment on column daq_weld_rework_weld.is_infiltration IS '是否渗透检测';

alter table daq_weld_rework_weld add is_magnetic_powder smallint default 0;
comment on column daq_weld_rework_weld.is_magnetic_powder IS '是否磁粉检测';

alter table daq_weld_rework_weld add is_fa_ultrasonic smallint default 0;
comment on column daq_weld_rework_weld.is_fa_ultrasonic IS '是否全自动检测';

alter table daq_weld_rework_weld add is_pa_ultrasonic smallint default 0;
comment on column daq_weld_rework_weld.is_pa_ultrasonic IS '相控阵超声波检测';


CREATE INDEX index_daq_weld_anticorrosion_check_weld_oid_9 ON daq_weld_anticorrosion_check USING btree (weld_oid);
ALTER TABLE daq_weld_anticorrosion_check ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_weld_anticorrosion_repair_weld_oid_9 ON daq_weld_anticorrosion_repair USING btree (weld_oid);
ALTER TABLE daq_weld_anticorrosion_repair ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_weld_anticorrosion_test_weld_oid_9 ON daq_weld_anticorrosion_test USING btree (weld_oid);
ALTER TABLE daq_weld_anticorrosion_test ADD PRIMARY KEY (oid);
CREATE INDEX index_daq_weld_cut_weld_oid_9 ON daq_weld_cut USING btree (weld_oid);
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
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	remarks varchar(200),
	create_user_id varchar(36),
	create_user_name varchar(50),
	create_datetime timestamp(6),
	modify_user_id varchar(36),
	modify_user_name varchar(50),
	modify_datetime timestamp(6),
	active smallint not null default 1
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
comment on column daq_cut_pipe.construct_unit IS '施工单位';
comment on column daq_cut_pipe.supervision_unit IS '监理单位';
comment on column daq_cut_pipe.supervision_engineer IS '监理工程师';
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
	work_unit_oid VARCHAR (36),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state varchar(10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
comment on column daq_weld_measured_result.work_unit_oid is '施工机组';
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

/**********管道焊接信息end***************/
/**********管道检测信息begin***************/
CREATE TABLE daq_detection_ray (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_oid VARCHAR (36),
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
	active smallint not null default 1
);
comment on table daq_detection_ray is '射线检测';
comment on column daq_detection_ray.oid is '主键';
comment on column daq_detection_ray.project_oid is '项目oid';
comment on column daq_detection_ray.pipeline_oid is '管线oid';
comment on column daq_detection_ray.tenders_oid is '标段oid';
comment on column daq_detection_ray.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_ray.weld_oid is '焊口编号';
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
create index INDEX_DAQ_DETECTION_RAY_weld_oid_9 ON daq_detection_ray ( weld_oid );
create index INDEX_DAQ_DETECTION_RAY_DETECTION_REPORT_NUM_10 ON daq_detection_ray ( detection_report_num );

CREATE TABLE daq_detection_ray_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_oid VARCHAR (36),
	defect_position VARCHAR (60),
	defect_properties VARCHAR (50),
	defect_size NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_detection_ray_sub is '射线检测子表';
comment on column daq_detection_ray_sub.oid is '主键';
comment on column daq_detection_ray_sub.parent_oid is '主表oid';
comment on column daq_detection_ray_sub.weld_oid is '焊口编号';
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
create index INDEX_DAQ_DETECTION_RAY_SUB_weld_oid_6 ON daq_detection_ray_sub ( weld_oid );

CREATE TABLE daq_detection_ultrasonic (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_oid VARCHAR (36),
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
	active smallint not null default 1
);
comment on table daq_detection_ultrasonic is '超声波检测表';
comment on column daq_detection_ultrasonic.oid is '主键';
comment on column daq_detection_ultrasonic.project_oid is '项目oid';
comment on column daq_detection_ultrasonic.pipeline_oid is '管线oid';
comment on column daq_detection_ultrasonic.tenders_oid is '标段oid';
comment on column daq_detection_ultrasonic.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_ultrasonic.weld_oid is '焊口编号';
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
create index INDEX_DAQ_DETECTION_ULTRASONIC_weld_oid_9 ON daq_detection_ultrasonic ( weld_oid );
create index INDEX_DAQ_DETECTION_ULTRASONIC_DETECTION_REPORT_NUM_10 ON daq_detection_ultrasonic ( detection_report_num );

CREATE TABLE daq_detection_ultrasonic_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_oid VARCHAR (36),
	defect_position VARCHAR (60),
	defect_properties VARCHAR (50),
	defect_size NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_detection_ultrasonic_sub is '超声波检测子表';
comment on column daq_detection_ultrasonic_sub.oid is '主键';
comment on column daq_detection_ultrasonic_sub.parent_oid is '主表oid';
comment on column daq_detection_ultrasonic_sub.weld_oid is '焊口编号';
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
create index INDEX_daq_detection_ultrasonic_sub_weld_oid_6 ON daq_detection_ultrasonic_sub ( weld_oid );

CREATE TABLE daq_detection_infiltration (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_oid VARCHAR (36),
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
	active smallint not null default 1
);
comment on table daq_detection_infiltration is '渗透检测';
comment on column daq_detection_infiltration.oid is '主键';
comment on column daq_detection_infiltration.project_oid is '项目oid';
comment on column daq_detection_infiltration.pipeline_oid is '管线oid';
comment on column daq_detection_infiltration.tenders_oid is '标段oid';
comment on column daq_detection_infiltration.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_infiltration.weld_oid is '焊口编号';
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
create index INDEX_DAQ_DETECTION_INFILTRATION_weld_oid_9 ON daq_detection_infiltration ( weld_oid );
create index INDEX_DAQ_DETECTION_INFILTRATION_DETECTION_REPORT_NUM_10 ON daq_detection_infiltration ( detection_report_num );

CREATE TABLE daq_detection_infiltration_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_oid VARCHAR (36),
	defect_position VARCHAR (60),
	defect_properties VARCHAR (50),
	defect_size NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_detection_infiltration_sub is '渗透检测子表';
comment on column daq_detection_infiltration_sub.oid is '主键';
comment on column daq_detection_infiltration_sub.parent_oid is '主表oid';
comment on column daq_detection_infiltration_sub.weld_oid is '焊口编号';
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
create index INDEX_daq_detection_infiltration_sub_weld_oid_6 ON daq_detection_infiltration_sub ( weld_oid );

CREATE TABLE daq_detection_magnetic_powder (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_oid VARCHAR (36),
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
	active smallint not null default 1
);
comment on table daq_detection_magnetic_powder is '磁粉检测表';
comment on column daq_detection_magnetic_powder.oid is '主键';
comment on column daq_detection_magnetic_powder.project_oid is '项目oid';
comment on column daq_detection_magnetic_powder.pipeline_oid is '管线oid';
comment on column daq_detection_magnetic_powder.tenders_oid is '标段oid';
comment on column daq_detection_magnetic_powder.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_magnetic_powder.weld_oid is '焊口编号';
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
create index INDEX_DAQ_DETECTION_MAGNETIC_POWDER_weld_oid_9 ON daq_detection_magnetic_powder ( weld_oid );
create index INDEX_DAQ_DETECTION_MAGNETIC_POWDER_DETECTION_REPORT_NUM_10 ON daq_detection_magnetic_powder ( detection_report_num );

CREATE TABLE daq_detection_magnetic_powder_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_oid VARCHAR (36),
	defect_position VARCHAR (60),
	defect_properties VARCHAR (50),
	defect_size NUMERIC (9, 3),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_detection_magnetic_powder_sub is '磁粉检测子表';
comment on column daq_detection_magnetic_powder_sub.oid is '主键';
comment on column daq_detection_magnetic_powder_sub.parent_oid is '主表oid';
comment on column daq_detection_magnetic_powder_sub.weld_oid is '焊口编号';
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
create index INDEX_daq_detection_magnetic_powder_sub_weld_oid_6 ON daq_detection_magnetic_powder_sub ( weld_oid );

CREATE TABLE daq_detection_fa_ultrasonic (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_oid VARCHAR (36),
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
	active smallint not null default 1
);
comment on table daq_detection_fa_ultrasonic is '全自动超声波检测表';
comment on column daq_detection_fa_ultrasonic.oid is '主键';
comment on column daq_detection_fa_ultrasonic.project_oid is '项目oid';
comment on column daq_detection_fa_ultrasonic.pipeline_oid is '管线oid';
comment on column daq_detection_fa_ultrasonic.tenders_oid is '标段oid';
comment on column daq_detection_fa_ultrasonic.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_fa_ultrasonic.weld_oid is '焊口编号';
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
create index INDEX_DAQ_DETECTION_FA_ULTRASONIC_weld_oid_9 ON daq_detection_fa_ultrasonic ( weld_oid );
create index INDEX_DAQ_DETECTION_FA_ULTRASONIC_DETECTION_REPORT_NUM_10 ON daq_detection_fa_ultrasonic ( detection_report_num );

CREATE TABLE daq_detection_fa_ultrasonic_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_oid VARCHAR (36),
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
	active smallint not null default 1
);
comment on table daq_detection_fa_ultrasonic_sub is '全自动超声波检测子表';
comment on column daq_detection_fa_ultrasonic_sub.oid is '主键';
comment on column daq_detection_fa_ultrasonic_sub.parent_oid is '主表oid';
comment on column daq_detection_fa_ultrasonic_sub.weld_oid is '焊口编号';
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
create index INDEX_daq_detection_fa_ultrasonic_sub_weld_oid_6 ON daq_detection_fa_ultrasonic_sub ( weld_oid );

CREATE TABLE daq_detection_pa_ultrasonic (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	weld_oid VARCHAR (36),
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
	active smallint not null default 1
);
comment on table daq_detection_pa_ultrasonic is '相控阵超声波检测表';
comment on column daq_detection_pa_ultrasonic.oid is '主键';
comment on column daq_detection_pa_ultrasonic.project_oid is '项目oid';
comment on column daq_detection_pa_ultrasonic.pipeline_oid is '管线oid';
comment on column daq_detection_pa_ultrasonic.tenders_oid is '标段oid';
comment on column daq_detection_pa_ultrasonic.pipe_segment_or_cross_oid is '线路段/穿跨越';
comment on column daq_detection_pa_ultrasonic.weld_oid is '焊口编号';
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
create index INDEX_DAQ_DETECTION_PA_ULTRASONIC_weld_oid_9 ON daq_detection_pa_ultrasonic ( weld_oid );
create index INDEX_DAQ_DETECTION_PA_ULTRASONIC_DETECTION_REPORT_NUM_10 ON daq_detection_pa_ultrasonic ( detection_report_num );

CREATE TABLE daq_detection_pa_ultrasonic_sub (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	parent_oid VARCHAR (36),
	weld_oid VARCHAR (36),
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
	active smallint not null default 1
);
comment on table daq_detection_pa_ultrasonic_sub is '相控阵超声波检测子表';
comment on column daq_detection_pa_ultrasonic_sub.oid is '主键';
comment on column daq_detection_pa_ultrasonic_sub.parent_oid is '主表oid';
comment on column daq_detection_pa_ultrasonic_sub.weld_oid is '焊口编号';
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
create index INDEX_daq_detection_pa_ultrasonic_sub_weld_oid_6 ON daq_detection_pa_ultrasonic_sub ( weld_oid );
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
	approve_status SMALLINT default 0,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	geo_state varchar(10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT  default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
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

/**********管道穿跨越信息end***************/
/**********管道阴保begin***************/
CREATE TABLE daq_cathodic_isolating_piece (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_oid VARCHAR (36),
	isolating_piece_code VARCHAR (50),
	isolating_piece_name VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (8, 0),
	pointx NUMERIC (9, 3),
	pointy NUMERIC (9, 3),
	pointz NUMERIC (9, 3),
	start_pipe_fitting_num VARCHAR (50),
	start_pipe_fitting_type VARCHAR (50),
	end_pipe_fitting_num VARCHAR (50),
	end_pipe_fitting_type VARCHAR (50),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_cathodic_isolating_piece is '绝缘件表';
comment on column daq_cathodic_isolating_piece.oid is '主键';
comment on column daq_cathodic_isolating_piece.project_oid is '项目oid';
comment on column daq_cathodic_isolating_piece.tenders_oid is '标段oid';
comment on column daq_cathodic_isolating_piece.pipeline_oid is '管线oid';
comment on column daq_cathodic_isolating_piece.pipe_segment_oid is '线路段oid';
comment on column daq_cathodic_isolating_piece.isolating_piece_code is '绝缘件编号';
comment on column daq_cathodic_isolating_piece.isolating_piece_name is '名称';
comment on column daq_cathodic_isolating_piece.median_stake_oid is '桩号';
comment on column daq_cathodic_isolating_piece.relative_mileage is '相对桩位置(m)';
comment on column daq_cathodic_isolating_piece.pointx is 'X坐标';
comment on column daq_cathodic_isolating_piece.pointy is 'Y坐标';
comment on column daq_cathodic_isolating_piece.pointz is '高程(m)';
comment on column daq_cathodic_isolating_piece.start_pipe_fitting_num is '前管件编号';
comment on column daq_cathodic_isolating_piece.start_pipe_fitting_type is '前管件类型';
comment on column daq_cathodic_isolating_piece.end_pipe_fitting_num is '后管件编号';
comment on column daq_cathodic_isolating_piece.end_pipe_fitting_type is '后管件类型';
comment on column daq_cathodic_isolating_piece.construct_unit is '施工单位';
comment on column daq_cathodic_isolating_piece.construct_date is '施工日期';
comment on column daq_cathodic_isolating_piece.supervision_unit is '监理单位';
comment on column daq_cathodic_isolating_piece.supervision_engineer is '监理工程师';
comment on column daq_cathodic_isolating_piece.collection_person is '采集人员';
comment on column daq_cathodic_isolating_piece.collection_date is '采集日期';
comment on column daq_cathodic_isolating_piece.geo_state is '空间数据状态';
comment on column daq_cathodic_isolating_piece.approve_status is '审核状态';
comment on column daq_cathodic_isolating_piece.remarks is '备注';
comment on column daq_cathodic_isolating_piece.create_user_id is '创建人id';
comment on column daq_cathodic_isolating_piece.create_user_name is '创建人名称';
comment on column daq_cathodic_isolating_piece.create_datetime is '创建时间';
comment on column daq_cathodic_isolating_piece.modify_user_id is '修改人id';
comment on column daq_cathodic_isolating_piece.modify_user_name is '修改人名称';
comment on column daq_cathodic_isolating_piece.modify_datetime is '修改时间';
comment on column daq_cathodic_isolating_piece.active is '有效标志';
create index INDEX_DAQ_CATHODIC_ISOLATING_PIECE_ISOLATING_PIECE_CODE_9 ON daq_cathodic_isolating_piece ( isolating_piece_code );
create index INDEX_DAQ_CATHODIC_ISOLATING_PIECE_ISOLATING_PIECE_NAME_10 ON daq_cathodic_isolating_piece ( isolating_piece_name );

CREATE TABLE daq_cathodic_cable_protection (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_oid VARCHAR (36),
	cable_code VARCHAR (50),
	cable_specification VARCHAR (30),
	cable_tray_num VARCHAR (35),
	cable_batche VARCHAR (50),
	cable_struction VARCHAR (50),
	cable_laying_method VARCHAR (50),
	cable_length NUMERIC (10, 3),
	cable_use VARCHAR (50),
	test_stake_oid VARCHAR (36),
	cable_protection_code VARCHAR (50),
	auxiliary_anode_bed_oid VARCHAR (50),
	sacrifice_anode_oid VARCHAR (50),
	cable_layout_code VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (8, 0),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_cathodic_cable_protection is '阴保电缆表';
comment on column daq_cathodic_cable_protection.oid is '主键';
comment on column daq_cathodic_cable_protection.project_oid is '项目oid';
comment on column daq_cathodic_cable_protection.tenders_oid is '标段oid';
comment on column daq_cathodic_cable_protection.pipeline_oid is '管线oid';
comment on column daq_cathodic_cable_protection.pipe_segment_oid is '线路段oid';
comment on column daq_cathodic_cable_protection.cable_code is '阴保电缆编号';
comment on column daq_cathodic_cable_protection.cable_specification is '电缆规格型号';
comment on column daq_cathodic_cable_protection.cable_tray_num is '电缆盘号';
comment on column daq_cathodic_cable_protection.cable_batche is '电缆批次';
comment on column daq_cathodic_cable_protection.cable_struction is '电缆保护结构';
comment on column daq_cathodic_cable_protection.cable_laying_method is '电缆敷设方式';
comment on column daq_cathodic_cable_protection.cable_length is '电缆长度(m)';
comment on column daq_cathodic_cable_protection.cable_use is '电缆用途';
comment on column daq_cathodic_cable_protection.test_stake_oid is '测试桩号';
comment on column daq_cathodic_cable_protection.cable_protection_code is '阴保电源编号';
comment on column daq_cathodic_cable_protection.auxiliary_anode_bed_oid is '辅助阳极地床编号';
comment on column daq_cathodic_cable_protection.sacrifice_anode_oid is '牺牲阳极编号';
comment on column daq_cathodic_cable_protection.cable_layout_code is '电缆敷设图编号';
comment on column daq_cathodic_cable_protection.median_stake_oid is '桩号';
comment on column daq_cathodic_cable_protection.relative_mileage is '相对桩位置(m)';
comment on column daq_cathodic_cable_protection.construct_unit is '施工单位';
comment on column daq_cathodic_cable_protection.construct_date is '施工日期';
comment on column daq_cathodic_cable_protection.supervision_unit is '监理单位';
comment on column daq_cathodic_cable_protection.supervision_engineer is '监理工程师';
comment on column daq_cathodic_cable_protection.collection_person is '采集人员';
comment on column daq_cathodic_cable_protection.collection_date is '采集日期';
comment on column daq_cathodic_cable_protection.geo_state is '空间数据状态';
comment on column daq_cathodic_cable_protection.approve_status is '审核状态';
comment on column daq_cathodic_cable_protection.remarks is '备注';
comment on column daq_cathodic_cable_protection.create_user_id is '创建人id';
comment on column daq_cathodic_cable_protection.create_user_name is '创建人名称';
comment on column daq_cathodic_cable_protection.create_datetime is '创建时间';
comment on column daq_cathodic_cable_protection.modify_user_id is '修改人id';
comment on column daq_cathodic_cable_protection.modify_user_name is '修改人名称';
comment on column daq_cathodic_cable_protection.modify_datetime is '修改时间';
comment on column daq_cathodic_cable_protection.active is '有效标志';
create index INDEX_DAQ_CATHODIC_CABLE_PROTECTION_CABLE_CODE_9 ON daq_cathodic_cable_protection ( cable_code );
create index INDEX_DAQ_CATHODIC_CABLE_PROTECTION_CABLE_SPECIFICATION_10 ON daq_cathodic_cable_protection ( cable_specification );

CREATE TABLE daq_cathodic_sacrifice_anode (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_oid VARCHAR (36),
	anode_code VARCHAR (50),
	protect_object VARCHAR (40),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (8, 0),
	pointx NUMERIC (9, 3),
	pointy NUMERIC (9, 3),
	pointz NUMERIC (9, 3),
	is_temporary SMALLINT,
	design_life NUMERIC (2, 0),
	anode_material VARCHAR (50),
	anode_specification VARCHAR (50),
	andoe_quantity NUMERIC (6, 0),
	andoe_weight NUMERIC (6, 1),
	total_weight NUMERIC (6, 1),
	anode_distance NUMERIC (6, 2),
	pipe_distance NUMERIC (6, 2),
	manufacture VARCHAR (60),
	burial_date TIMESTAMP (6),
	anode_buried_depth NUMERIC (8, 3),
	lay_length NUMERIC (8, 3),
	fill_weight NUMERIC (7, 2),
	insulation_quality VARCHAR (50),
	tablets_condition SMALLINT,
	install_location_description VARCHAR (200),
	conclusion SMALLINT,
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_cathodic_sacrifice_anode is '牺牲阳极表';
comment on column daq_cathodic_sacrifice_anode.oid is '主键';
comment on column daq_cathodic_sacrifice_anode.project_oid is '项目oid';
comment on column daq_cathodic_sacrifice_anode.tenders_oid is '标段oid';
comment on column daq_cathodic_sacrifice_anode.pipeline_oid is '管线oid';
comment on column daq_cathodic_sacrifice_anode.pipe_segment_oid is '线路段oid';
comment on column daq_cathodic_sacrifice_anode.anode_code is '阳极编号';
comment on column daq_cathodic_sacrifice_anode.protect_object is '保护对象';
comment on column daq_cathodic_sacrifice_anode.median_stake_oid is '桩号';
comment on column daq_cathodic_sacrifice_anode.relative_mileage is '相对桩位置(m)';
comment on column daq_cathodic_sacrifice_anode.pointx is 'X坐标';
comment on column daq_cathodic_sacrifice_anode.pointy is 'Y坐标';
comment on column daq_cathodic_sacrifice_anode.pointz is '高程(m)';
comment on column daq_cathodic_sacrifice_anode.is_temporary is '是否临时';
comment on column daq_cathodic_sacrifice_anode.design_life is '设计寿命(年)';
comment on column daq_cathodic_sacrifice_anode.anode_material is '牺牲阳极材料';
comment on column daq_cathodic_sacrifice_anode.anode_specification is '牺牲阳极型号';
comment on column daq_cathodic_sacrifice_anode.andoe_quantity is '牺牲阳极数量(支)';
comment on column daq_cathodic_sacrifice_anode.andoe_weight is '牺牲阳极单重(kg)';
comment on column daq_cathodic_sacrifice_anode.total_weight is '牺牲阳极总重(kg)';
comment on column daq_cathodic_sacrifice_anode.anode_distance is '阳极间距(m)';
comment on column daq_cathodic_sacrifice_anode.pipe_distance is '与管道距离(m)';
comment on column daq_cathodic_sacrifice_anode.manufacture is '制造商';
comment on column daq_cathodic_sacrifice_anode.burial_date is '埋设日期';
comment on column daq_cathodic_sacrifice_anode.anode_buried_depth is '阳极埋深(m)';
comment on column daq_cathodic_sacrifice_anode.lay_length is '铺设长度(m)';
comment on column daq_cathodic_sacrifice_anode.fill_weight is '填包料重量(kg)';
comment on column daq_cathodic_sacrifice_anode.insulation_quality is '各接点质量及绝缘情况';
comment on column daq_cathodic_sacrifice_anode.tablets_condition is '检查片埋设情况';
comment on column daq_cathodic_sacrifice_anode.install_location_description is '安装位置描述';
comment on column daq_cathodic_sacrifice_anode.conclusion is '结论';
comment on column daq_cathodic_sacrifice_anode.construct_unit is '施工单位';
comment on column daq_cathodic_sacrifice_anode.construct_date is '施工日期';
comment on column daq_cathodic_sacrifice_anode.supervision_unit is '监理单位';
comment on column daq_cathodic_sacrifice_anode.supervision_engineer is '监理工程师';
comment on column daq_cathodic_sacrifice_anode.collection_person is '采集人员';
comment on column daq_cathodic_sacrifice_anode.collection_date is '采集日期';
comment on column daq_cathodic_sacrifice_anode.geo_state is '空间数据状态';
comment on column daq_cathodic_sacrifice_anode.approve_status is '审核状态';
comment on column daq_cathodic_sacrifice_anode.remarks is '备注';
comment on column daq_cathodic_sacrifice_anode.create_user_id is '创建人id';
comment on column daq_cathodic_sacrifice_anode.create_user_name is '创建人名称';
comment on column daq_cathodic_sacrifice_anode.create_datetime is '创建时间';
comment on column daq_cathodic_sacrifice_anode.modify_user_id is '修改人id';
comment on column daq_cathodic_sacrifice_anode.modify_user_name is '修改人名称';
comment on column daq_cathodic_sacrifice_anode.modify_datetime is '修改时间';
comment on column daq_cathodic_sacrifice_anode.active is '有效标志';
create index INDEX_DAQ_CATHODIC_SACRIFICE_ANODE_ANODE_CODE_9 ON daq_cathodic_sacrifice_anode ( anode_code );


CREATE TABLE daq_cathodic_insulated_joint (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_oid VARCHAR (36),
	equipment_code VARCHAR (50),
	equipment_name VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (8, 0),
	pointx NUMERIC (9, 3),
	pointy NUMERIC (9, 3),
	pointz NUMERIC (9, 3),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_cathodic_insulated_joint is '绝缘接头保护器表';
comment on column daq_cathodic_insulated_joint.oid is '主键';
comment on column daq_cathodic_insulated_joint.project_oid is '项目oid';
comment on column daq_cathodic_insulated_joint.tenders_oid is '标段oid';
comment on column daq_cathodic_insulated_joint.pipeline_oid is '管线oid';
comment on column daq_cathodic_insulated_joint.pipe_segment_oid is '线路段oid';
comment on column daq_cathodic_insulated_joint.equipment_code is '设备编号';
comment on column daq_cathodic_insulated_joint.equipment_name is '名称';
comment on column daq_cathodic_insulated_joint.median_stake_oid is '桩号';
comment on column daq_cathodic_insulated_joint.relative_mileage is '相对桩位置(m)';
comment on column daq_cathodic_insulated_joint.pointx is 'X坐标';
comment on column daq_cathodic_insulated_joint.pointy is 'Y坐标';
comment on column daq_cathodic_insulated_joint.pointz is '高程(m)';
comment on column daq_cathodic_insulated_joint.construct_unit is '施工单位';
comment on column daq_cathodic_insulated_joint.construct_date is '施工日期';
comment on column daq_cathodic_insulated_joint.supervision_unit is '监理单位';
comment on column daq_cathodic_insulated_joint.supervision_engineer is '监理工程师';
comment on column daq_cathodic_insulated_joint.collection_person is '采集人员';
comment on column daq_cathodic_insulated_joint.collection_date is '采集日期';
comment on column daq_cathodic_insulated_joint.geo_state is '空间数据状态';
comment on column daq_cathodic_insulated_joint.approve_status is '审核状态';
comment on column daq_cathodic_insulated_joint.remarks is '备注';
comment on column daq_cathodic_insulated_joint.create_user_id is '创建人id';
comment on column daq_cathodic_insulated_joint.create_user_name is '创建人名称';
comment on column daq_cathodic_insulated_joint.create_datetime is '创建时间';
comment on column daq_cathodic_insulated_joint.modify_user_id is '修改人id';
comment on column daq_cathodic_insulated_joint.modify_user_name is '修改人名称';
comment on column daq_cathodic_insulated_joint.modify_datetime is '修改时间';
comment on column daq_cathodic_insulated_joint.active is '有效标志';
create index INDEX_DAQ_CATHODIC_INSULATED_JOINT_EQUIPMENT_CODE_9 ON daq_cathodic_insulated_joint ( equipment_code );

CREATE TABLE daq_cathodic_solid_decoupler (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_oid VARCHAR (36),
	equipment_code VARCHAR (50),
	equipment_name VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (8, 0),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_cathodic_solid_decoupler is '固态去耦合器表';
comment on column daq_cathodic_solid_decoupler.oid is '主键';
comment on column daq_cathodic_solid_decoupler.project_oid is '项目oid';
comment on column daq_cathodic_solid_decoupler.tenders_oid is '标段oid';
comment on column daq_cathodic_solid_decoupler.pipeline_oid is '管线oid';
comment on column daq_cathodic_solid_decoupler.pipe_segment_oid is '线路段oid';
comment on column daq_cathodic_solid_decoupler.equipment_code is '设备编号';
comment on column daq_cathodic_solid_decoupler.equipment_name is '名称';
comment on column daq_cathodic_solid_decoupler.median_stake_oid is '桩号';
comment on column daq_cathodic_solid_decoupler.relative_mileage is '相对桩位置(m)';
comment on column daq_cathodic_solid_decoupler.construct_unit is '施工单位';
comment on column daq_cathodic_solid_decoupler.construct_date is '施工日期';
comment on column daq_cathodic_solid_decoupler.supervision_unit is '监理单位';
comment on column daq_cathodic_solid_decoupler.supervision_engineer is '监理工程师';
comment on column daq_cathodic_solid_decoupler.collection_person is '采集人员';
comment on column daq_cathodic_solid_decoupler.collection_date is '采集日期';
comment on column daq_cathodic_solid_decoupler.geo_state is '空间数据状态';
comment on column daq_cathodic_solid_decoupler.approve_status is '审核状态';
comment on column daq_cathodic_solid_decoupler.remarks is '备注';
comment on column daq_cathodic_solid_decoupler.create_user_id is '创建人id';
comment on column daq_cathodic_solid_decoupler.create_user_name is '创建人名称';
comment on column daq_cathodic_solid_decoupler.create_datetime is '创建时间';
comment on column daq_cathodic_solid_decoupler.modify_user_id is '修改人id';
comment on column daq_cathodic_solid_decoupler.modify_user_name is '修改人名称';
comment on column daq_cathodic_solid_decoupler.modify_datetime is '修改时间';
comment on column daq_cathodic_solid_decoupler.active is '有效标志';
create index INDEX_DAQ_CATHODIC_SOLID_DECOUPLER_EQUIPMENT_CODE_9 ON daq_cathodic_solid_decoupler ( equipment_code );


CREATE TABLE daq_cathodic_test_stake (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_oid VARCHAR (36),
	test_stake_code VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (8, 0),
	pointx NUMERIC (9, 3),
	pointy NUMERIC (9, 3),
	pointz NUMERIC (9, 3),
	stake_structure VARCHAR (50),
	install_location_description VARCHAR (200),
	burial_date TIMESTAMP (6),
	stake_function VARCHAR (450),
	stake_top_height NUMERIC (9, 3),
	stake_shape VARCHAR (200),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_cathodic_test_stake is '测试桩表';
comment on column daq_cathodic_test_stake.oid is '主键';
comment on column daq_cathodic_test_stake.project_oid is '项目oid';
comment on column daq_cathodic_test_stake.tenders_oid is '标段oid';
comment on column daq_cathodic_test_stake.pipeline_oid is '管线oid';
comment on column daq_cathodic_test_stake.pipe_segment_oid is '线路段oid';
comment on column daq_cathodic_test_stake.test_stake_code is '测试桩编号';
comment on column daq_cathodic_test_stake.median_stake_oid is '桩号';
comment on column daq_cathodic_test_stake.relative_mileage is '相对桩位置(m)';
comment on column daq_cathodic_test_stake.pointx is 'X坐标';
comment on column daq_cathodic_test_stake.pointy is 'Y坐标';
comment on column daq_cathodic_test_stake.pointz is '高程(m)';
comment on column daq_cathodic_test_stake.stake_structure is '桩体结构';
comment on column daq_cathodic_test_stake.install_location_description is '安装位置描述';
comment on column daq_cathodic_test_stake.burial_date is '埋设日期';
comment on column daq_cathodic_test_stake.stake_function is '桩功能';
comment on column daq_cathodic_test_stake.stake_top_height is '桩顶高度(m)';
comment on column daq_cathodic_test_stake.stake_shape is '桩体形状';
comment on column daq_cathodic_test_stake.construct_unit is '施工单位';
comment on column daq_cathodic_test_stake.construct_date is '施工日期';
comment on column daq_cathodic_test_stake.supervision_unit is '监理单位';
comment on column daq_cathodic_test_stake.supervision_engineer is '监理工程师';
comment on column daq_cathodic_test_stake.collection_person is '采集人员';
comment on column daq_cathodic_test_stake.collection_date is '采集日期';
comment on column daq_cathodic_test_stake.geo_state is '空间数据状态';
comment on column daq_cathodic_test_stake.approve_status is '审核状态';
comment on column daq_cathodic_test_stake.remarks is '备注';
comment on column daq_cathodic_test_stake.create_user_id is '创建人id';
comment on column daq_cathodic_test_stake.create_user_name is '创建人名称';
comment on column daq_cathodic_test_stake.create_datetime is '创建时间';
comment on column daq_cathodic_test_stake.modify_user_id is '修改人id';
comment on column daq_cathodic_test_stake.modify_user_name is '修改人名称';
comment on column daq_cathodic_test_stake.modify_datetime is '修改时间';
comment on column daq_cathodic_test_stake.active is '有效标志';
create index INDEX_DAQ_CATHODIC_TEST_STAKE_TEST_STAKE_CODE_9 ON daq_cathodic_test_stake ( test_stake_code );


CREATE TABLE daq_cathodic_polarity_drainage (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_oid VARCHAR (36),
	equipment_code VARCHAR (50),
	equipment_name VARCHAR (50),
	factory_num VARCHAR (60),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (8, 0),
	pointx NUMERIC (9, 3),
	pointy NUMERIC (9, 3),
	protection_scope VARCHAR (50),
	manufacturer VARCHAR (60),
	discharge_flow NUMERIC (6, 0),
	max_discharge_flow NUMERIC (6, 0),
	avg_discharge_flow NUMERIC (6, 0),
	min_discharge_flow NUMERIC (6, 0),
	ground_bed_material VARCHAR (50),
	manage_potential NUMERIC (6, 0),
	ground_potential NUMERIC (6, 0),
	service_date TIMESTAMP (6),
	expiration_date TIMESTAMP (6),
	product_date TIMESTAMP (6),
	drainage_purpose VARCHAR (50),
	drainage_type VARCHAR (35),
	design_life SMALLINT,
	avg_potential_undrain NUMERIC (6, 0),
	avg_potential_drain NUMERIC (6, 0),
	earthing_resistance NUMERIC (6, 0),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_cathodic_polarity_drainage is '极性排流器表';
comment on column daq_cathodic_polarity_drainage.oid is '主键';
comment on column daq_cathodic_polarity_drainage.project_oid is '项目oid';
comment on column daq_cathodic_polarity_drainage.tenders_oid is '标段oid';
comment on column daq_cathodic_polarity_drainage.pipeline_oid is '管线oid';
comment on column daq_cathodic_polarity_drainage.pipe_segment_oid is '线路段oid';
comment on column daq_cathodic_polarity_drainage.equipment_code is '设备编号';
comment on column daq_cathodic_polarity_drainage.equipment_name is '名称';
comment on column daq_cathodic_polarity_drainage.factory_num is '出厂编号';
comment on column daq_cathodic_polarity_drainage.median_stake_oid is '桩号';
comment on column daq_cathodic_polarity_drainage.relative_mileage is '相对桩位置(m)';
comment on column daq_cathodic_polarity_drainage.pointx is 'X坐标';
comment on column daq_cathodic_polarity_drainage.pointy is 'Y坐标';
comment on column daq_cathodic_polarity_drainage.protection_scope is '保护范围';
comment on column daq_cathodic_polarity_drainage.manufacturer is '制造商';
comment on column daq_cathodic_polarity_drainage.discharge_flow is '排流量(A)';
comment on column daq_cathodic_polarity_drainage.max_discharge_flow is '最大排流量(A)';
comment on column daq_cathodic_polarity_drainage.avg_discharge_flow is '平均排流量(A)';
comment on column daq_cathodic_polarity_drainage.min_discharge_flow is '最小排流量(A)';
comment on column daq_cathodic_polarity_drainage.ground_bed_material is '地床材料';
comment on column daq_cathodic_polarity_drainage.manage_potential is '管地电位(V)';
comment on column daq_cathodic_polarity_drainage.ground_potential is '地床接地电位(V)';
comment on column daq_cathodic_polarity_drainage.service_date is '投用日期';
comment on column daq_cathodic_polarity_drainage.expiration_date is '预计失效日期';
comment on column daq_cathodic_polarity_drainage.product_date is '生产日期';
comment on column daq_cathodic_polarity_drainage.drainage_purpose is '排流用途';
comment on column daq_cathodic_polarity_drainage.drainage_type is '排流类型';
comment on column daq_cathodic_polarity_drainage.design_life is '设计寿命(年)';
comment on column daq_cathodic_polarity_drainage.avg_potential_undrain is '不排流时平均电位(V)';
comment on column daq_cathodic_polarity_drainage.avg_potential_drain is '排流时平均电位(V)';
comment on column daq_cathodic_polarity_drainage.earthing_resistance is '接地排流地床电阻(Ω)';
comment on column daq_cathodic_polarity_drainage.construct_unit is '施工单位';
comment on column daq_cathodic_polarity_drainage.construct_date is '施工日期';
comment on column daq_cathodic_polarity_drainage.supervision_unit is '监理单位';
comment on column daq_cathodic_polarity_drainage.supervision_engineer is '监理工程师';
comment on column daq_cathodic_polarity_drainage.collection_person is '采集人员';
comment on column daq_cathodic_polarity_drainage.collection_date is '采集日期';
comment on column daq_cathodic_polarity_drainage.geo_state is '空间数据状态';
comment on column daq_cathodic_polarity_drainage.approve_status is '审核状态';
comment on column daq_cathodic_polarity_drainage.remarks is '备注';
comment on column daq_cathodic_polarity_drainage.create_user_id is '创建人id';
comment on column daq_cathodic_polarity_drainage.create_user_name is '创建人名称';
comment on column daq_cathodic_polarity_drainage.create_datetime is '创建时间';
comment on column daq_cathodic_polarity_drainage.modify_user_id is '修改人id';
comment on column daq_cathodic_polarity_drainage.modify_user_name is '修改人名称';
comment on column daq_cathodic_polarity_drainage.modify_datetime is '修改时间';
comment on column daq_cathodic_polarity_drainage.active is '有效标志';
create index INDEX_DAQ_CATHODIC_POLARITY_DRAINAGE_EQUIPMENT_CODE_9 ON daq_cathodic_polarity_drainage ( equipment_code );


CREATE TABLE daq_cathodic_anode_bed (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_oid VARCHAR (36),
	ground_bed VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (8, 0),
	pointx NUMERIC (9, 3),
	pointy NUMERIC (9, 3),
	pointz NUMERIC (9, 3),
	install_location_description VARCHAR (200),
	buried_depth NUMERIC (8, 2),
	is_temporary SMALLINT,
	design_life SMALLINT,
	backfill_material VARCHAR (50),
	quantity SMALLINT,
	burial_way VARCHAR (50),
	total_weight NUMERIC (7, 1),
	cable_length NUMERIC (7, 1),
	protect_length NUMERIC (8, 2),
	anode_material_type VARCHAR (50),
	anode_material_spec VARCHAR (50),
	anode_resistance NUMERIC (7, 1),
	is_water_injection SMALLINT,
	is_airbleed SMALLINT,
	burial_date TIMESTAMP (6),
	connection_power VARCHAR (35),
	construct_unit VARCHAR (36),
	construct_date TIMESTAMP (6),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_cathodic_anode_bed is '辅助阳极地床表';
comment on column daq_cathodic_anode_bed.oid is '主键';
comment on column daq_cathodic_anode_bed.project_oid is '项目oid';
comment on column daq_cathodic_anode_bed.tenders_oid is '标段oid';
comment on column daq_cathodic_anode_bed.pipeline_oid is '管线oid';
comment on column daq_cathodic_anode_bed.pipe_segment_oid is '线路段oid';
comment on column daq_cathodic_anode_bed.ground_bed is '地床编号';
comment on column daq_cathodic_anode_bed.median_stake_oid is '桩号';
comment on column daq_cathodic_anode_bed.relative_mileage is '相对桩位置(m)';
comment on column daq_cathodic_anode_bed.pointx is 'X坐标';
comment on column daq_cathodic_anode_bed.pointy is 'Y坐标';
comment on column daq_cathodic_anode_bed.pointz is '高程(m)';
comment on column daq_cathodic_anode_bed.install_location_description is '安装位置描述';
comment on column daq_cathodic_anode_bed.buried_depth is '埋深(m)';
comment on column daq_cathodic_anode_bed.is_temporary is '是否临时';
comment on column daq_cathodic_anode_bed.design_life is '设计寿命(年)';
comment on column daq_cathodic_anode_bed.backfill_material is '回填材料';
comment on column daq_cathodic_anode_bed.quantity is '辅助阳极数量(个)';
comment on column daq_cathodic_anode_bed.burial_way is '埋设方式';
comment on column daq_cathodic_anode_bed.total_weight is '总重(kg)';
comment on column daq_cathodic_anode_bed.cable_length is '电缆长度(m)';
comment on column daq_cathodic_anode_bed.protect_length is '保护长度(m)';
comment on column daq_cathodic_anode_bed.anode_material_type is '阳极材料类型';
comment on column daq_cathodic_anode_bed.anode_material_spec is '阳极材料规格';
comment on column daq_cathodic_anode_bed.anode_resistance is '阳极电阻(Ω)';
comment on column daq_cathodic_anode_bed.is_water_injection is '是否有注水系统';
comment on column daq_cathodic_anode_bed.is_airbleed is '是否有排气孔';
comment on column daq_cathodic_anode_bed.burial_date is '埋设日期';
comment on column daq_cathodic_anode_bed.connection_power is '地床连接阴保电源';
comment on column daq_cathodic_anode_bed.construct_unit is '施工单位';
comment on column daq_cathodic_anode_bed.construct_date is '施工日期';
comment on column daq_cathodic_anode_bed.supervision_unit is '监理单位';
comment on column daq_cathodic_anode_bed.supervision_engineer is '监理工程师';
comment on column daq_cathodic_anode_bed.collection_person is '采集人员';
comment on column daq_cathodic_anode_bed.collection_date is '采集日期';
comment on column daq_cathodic_anode_bed.geo_state is '空间数据状态';
comment on column daq_cathodic_anode_bed.approve_status is '审核状态';
comment on column daq_cathodic_anode_bed.remarks is '备注';
comment on column daq_cathodic_anode_bed.create_user_id is '创建人id';
comment on column daq_cathodic_anode_bed.create_user_name is '创建人名称';
comment on column daq_cathodic_anode_bed.create_datetime is '创建时间';
comment on column daq_cathodic_anode_bed.modify_user_id is '修改人id';
comment on column daq_cathodic_anode_bed.modify_user_name is '修改人名称';
comment on column daq_cathodic_anode_bed.modify_datetime is '修改时间';
comment on column daq_cathodic_anode_bed.active is '有效标志';
create index INDEX_DAQ_CATHODIC_ANODE_BED_GROUND_BED_9 ON daq_cathodic_anode_bed ( ground_bed );


CREATE TABLE daq_cathodic_electrical_parameter_test (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	test_stake_oid VARCHAR (36),
	natural_potential NUMERIC (8, 2),
	open_circuit_potential_one NUMERIC (8, 2),
	open_circuit_potential_two NUMERIC (8, 2),
	protective_potential NUMERIC (8, 2),
	group_output_current NUMERIC (8, 2),
	output_current_one NUMERIC (8, 2),
	output_current_two NUMERIC (8, 2),
	earthing_resistance_one NUMERIC (8, 2),
	earthing_resistance_two NUMERIC (8, 2),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	test_person VARCHAR (30),
	test_date TIMESTAMP (6),
	approve_status SMALLINT default 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_cathodic_electrical_parameter_test is '牺牲阳极电参数测试记录表';
comment on column daq_cathodic_electrical_parameter_test.oid is '主键';
comment on column daq_cathodic_electrical_parameter_test.project_oid is '项目oid';
comment on column daq_cathodic_electrical_parameter_test.tenders_oid is '标段oid';
comment on column daq_cathodic_electrical_parameter_test.test_stake_oid is '测试桩编号';
comment on column daq_cathodic_electrical_parameter_test.natural_potential is '管道对地自然电位(V)';
comment on column daq_cathodic_electrical_parameter_test.open_circuit_potential_one is '阳极1开路电位(V)';
comment on column daq_cathodic_electrical_parameter_test.open_circuit_potential_two is '阳极2开路电位(V)';
comment on column daq_cathodic_electrical_parameter_test.protective_potential is '管道对地保护电位(V)';
comment on column daq_cathodic_electrical_parameter_test.group_output_current is '阳极组输出电流(mA)';
comment on column daq_cathodic_electrical_parameter_test.output_current_one is '阳极1输出电流(mA)';
comment on column daq_cathodic_electrical_parameter_test.output_current_two is '阳极2输出电流(mA)';
comment on column daq_cathodic_electrical_parameter_test.earthing_resistance_one is '阳极1接地电阻(Ω)';
comment on column daq_cathodic_electrical_parameter_test.earthing_resistance_two is '阳极2接地电阻(Ω)';
comment on column daq_cathodic_electrical_parameter_test.construct_unit is '施工单位';
comment on column daq_cathodic_electrical_parameter_test.supervision_unit is '监理单位';
comment on column daq_cathodic_electrical_parameter_test.supervision_engineer is '监理工程师';
comment on column daq_cathodic_electrical_parameter_test.test_person is '测试人';
comment on column daq_cathodic_electrical_parameter_test.test_date is '测试时间';
comment on column daq_cathodic_electrical_parameter_test.approve_status is '审核状态';
comment on column daq_cathodic_electrical_parameter_test.remarks is '备注';
comment on column daq_cathodic_electrical_parameter_test.create_user_id is '创建人id';
comment on column daq_cathodic_electrical_parameter_test.create_user_name is '创建人名称';
comment on column daq_cathodic_electrical_parameter_test.create_datetime is '创建时间';
comment on column daq_cathodic_electrical_parameter_test.modify_user_id is '修改人id';
comment on column daq_cathodic_electrical_parameter_test.modify_user_name is '修改人名称';
comment on column daq_cathodic_electrical_parameter_test.modify_datetime is '修改时间';
comment on column daq_cathodic_electrical_parameter_test.active is '有效标志';
create index INDEX_DAQ_CATHODIC_ELECTRICAL_PARAMETER_TEST_TEST_STAKE_OID_5 ON daq_cathodic_electrical_parameter_test ( test_stake_oid );

CREATE TABLE daq_cathodic_impressed_current_test (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	test_stake_oid VARCHAR (36),
	test_region VARCHAR (50),
	test_date TIMESTAMP (6),
	currency_date TIMESTAMP (6),
	polarization_date TIMESTAMP (6),
	natural_potential NUMERIC (8, 2),
	earthing_resistance NUMERIC (8, 2),
	direct_current_voltage NUMERIC (8, 2),
	standard_voltage NUMERIC (8, 2),
	measured_voltage NUMERIC (8, 2),
	standard_current NUMERIC (8, 2),
	measured_current NUMERIC (8, 2),
	approve_status SMALLINT default 0,
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_cathodic_impressed_current_test is '外加电流电参数测试记录表';
comment on column daq_cathodic_impressed_current_test.oid is '主键';
comment on column daq_cathodic_impressed_current_test.project_oid is '项目oid';
comment on column daq_cathodic_impressed_current_test.tenders_oid is '标段oid';
comment on column daq_cathodic_impressed_current_test.test_stake_oid is '测试桩编号';
comment on column daq_cathodic_impressed_current_test.test_region is '测试区段';
comment on column daq_cathodic_impressed_current_test.test_date is '测试日期';
comment on column daq_cathodic_impressed_current_test.currency_date is '通用日期';
comment on column daq_cathodic_impressed_current_test.polarization_date is '极化时间';
comment on column daq_cathodic_impressed_current_test.natural_potential is '投产前管道对地自然电位(V)';
comment on column daq_cathodic_impressed_current_test.earthing_resistance is '投产前阳极地床接地电阻(Ω)';
comment on column daq_cathodic_impressed_current_test.direct_current_voltage is '投产前直流电源电位/电压(V)';
comment on column daq_cathodic_impressed_current_test.standard_voltage is '投产后管道保护标准电位(V)';
comment on column daq_cathodic_impressed_current_test.measured_voltage is '投产后管道保护实测电位(V)';
comment on column daq_cathodic_impressed_current_test.standard_current is '投产后管道保护标准电流(mA)';
comment on column daq_cathodic_impressed_current_test.measured_current is '投产后管道保护实测电流(mA)';
comment on column daq_cathodic_impressed_current_test.approve_status is '审核状态';
comment on column daq_cathodic_impressed_current_test.construct_unit is '施工单位';
comment on column daq_cathodic_impressed_current_test.supervision_unit is '监理单位';
comment on column daq_cathodic_impressed_current_test.supervision_engineer is '监理工程师';
comment on column daq_cathodic_impressed_current_test.remarks is '备注';
comment on column daq_cathodic_impressed_current_test.create_user_id is '创建人id';
comment on column daq_cathodic_impressed_current_test.create_user_name is '创建人名称';
comment on column daq_cathodic_impressed_current_test.create_datetime is '创建时间';
comment on column daq_cathodic_impressed_current_test.modify_user_id is '修改人id';
comment on column daq_cathodic_impressed_current_test.modify_user_name is '修改人名称';
comment on column daq_cathodic_impressed_current_test.modify_datetime is '修改时间';
comment on column daq_cathodic_impressed_current_test.active is '有效标志';
create index INDEX_DAQ_CATHODIC_IMPRESSED_CURRENT_TEST_TEST_STAKE_OID_5 ON daq_cathodic_impressed_current_test ( test_stake_oid );
/**********管道阴保end***************/
/**********站/阀/井设备安装begin***************/
create table daq_test_valve( 
	oid varchar(36) not null primary key,
	project_oid varchar(36)  ,
	tenders_oid varchar(36)  ,
	pipeline_oid varchar(36)  ,
	pipe_station_oid varchar(36)  ,
	valve_oid varchar(36)  ,
	test_date timestamp(6)  ,
	filler varchar(50)  ,
	test_medium varchar(50)  ,
	strength_test_pressure numeric(6,3)  ,
	strength_test_time smallint  ,
	tightness_test_pressure numeric(6,3)  ,
	tightness_test_time smallint  ,
	test_condition_and_apparent_check varchar(50)  ,
	construct_date timestamp(6)  ,
	construct_unit varchar(36)  ,
	collection_person varchar(50)  ,
	collection_date timestamp(6)  ,
	supervision_unit varchar(36)  ,
	supervision_engineer varchar(50)  ,
	approve_status smallint DEFAULT 0,
	create_user_id varchar(36)  ,
	create_user_name varchar(50)  ,
	create_datetime timestamp(6)  ,
	modify_user_id varchar(36)  ,
	modify_user_name varchar(50)  ,
	modify_datetime timestamp(6)  ,
	active smallint not null default 1  
); 
comment on table daq_test_valve is '阀门试验表';
comment on column daq_test_valve.oid is '主键';
comment on column daq_test_valve.project_oid is '项目oid';
comment on column daq_test_valve.tenders_oid is '标段oid';
comment on column daq_test_valve.pipeline_oid is '管线oid';
comment on column daq_test_valve.pipe_station_oid is '站场/阀室oid';
comment on column daq_test_valve.valve_oid is '设备编号';
comment on column daq_test_valve.test_date is '试验日期';
comment on column daq_test_valve.filler is '填料';
comment on column daq_test_valve.test_medium is '试验介质';
comment on column daq_test_valve.strength_test_pressure is '强度试验压力(Mpa)';
comment on column daq_test_valve.strength_test_time is '强度试验时间(min)';
comment on column daq_test_valve.tightness_test_pressure is '严密性试验压力(Mpa)';
comment on column daq_test_valve.tightness_test_time is '严密性试验时间(min)';
comment on column daq_test_valve.test_condition_and_apparent_check is '试验情况及外观检查';
comment on column daq_test_valve.construct_date is '施工日期';
comment on column daq_test_valve.construct_unit is '施工单位';
comment on column daq_test_valve.collection_person is '数据采集人';
comment on column daq_test_valve.collection_date is '采集日期';
comment on column daq_test_valve.supervision_unit is '监理单位';
comment on column daq_test_valve.supervision_engineer is '监理工程师';
comment on column daq_test_valve.approve_status is '审核状态';
comment on column daq_test_valve.create_user_id is '创建人id';
comment on column daq_test_valve.create_user_name is '创建人名称';
comment on column daq_test_valve.create_datetime is '创建时间';
comment on column daq_test_valve.modify_user_id is '修改人id';
comment on column daq_test_valve.modify_user_name is '修改人名称';
comment on column daq_test_valve.modify_datetime is '修改时间';
comment on column daq_test_valve.active is '有效标志';
/**********站/阀/井设备安装end***************/
/**********管道附属物begin***************/
CREATE TABLE daq_appendages_mark_stake (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	mark_stake_code VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (9, 3),
	pointx NUMERIC (10, 3),
	pointy NUMERIC (11, 3),
	stake_structure VARCHAR (50),
	burial_date TIMESTAMP (6),
	stake_function VARCHAR (450),
	burial_depth NUMERIC (7, 2),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT DEFAULT 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_appendages_mark_stake is '标志桩表';
comment on column daq_appendages_mark_stake.oid is '主键';
comment on column daq_appendages_mark_stake.project_oid is '项目oid';
comment on column daq_appendages_mark_stake.tenders_oid is '标段oid';
comment on column daq_appendages_mark_stake.pipeline_oid is '管线oid';
comment on column daq_appendages_mark_stake.pipe_segment_or_cross_oid is '线路段/穿跨越oid';
comment on column daq_appendages_mark_stake.mark_stake_code is '标志桩编号';
comment on column daq_appendages_mark_stake.median_stake_oid is '桩号';
comment on column daq_appendages_mark_stake.relative_mileage is '相对桩位置(m)';
comment on column daq_appendages_mark_stake.pointx is '东坐标';
comment on column daq_appendages_mark_stake.pointy is '北坐标';
comment on column daq_appendages_mark_stake.stake_structure is '桩体结构';
comment on column daq_appendages_mark_stake.burial_date is '埋设日期';
comment on column daq_appendages_mark_stake.stake_function is '桩功能';
comment on column daq_appendages_mark_stake.burial_depth is '埋深(m)';
comment on column daq_appendages_mark_stake.construct_unit is '施工单位';
comment on column daq_appendages_mark_stake.supervision_unit is '监理单位';
comment on column daq_appendages_mark_stake.supervision_engineer is '监理工程师';
comment on column daq_appendages_mark_stake.collection_person is '采集人员';
comment on column daq_appendages_mark_stake.collection_date is '采集日期';
comment on column daq_appendages_mark_stake.geo_state is '空间数据状态';
comment on column daq_appendages_mark_stake.approve_status is '审核状态';
comment on column daq_appendages_mark_stake.remarks is '备注';
comment on column daq_appendages_mark_stake.create_user_id is '创建人id';
comment on column daq_appendages_mark_stake.create_user_name is '创建人名称';
comment on column daq_appendages_mark_stake.create_datetime is '创建时间';
comment on column daq_appendages_mark_stake.modify_user_id is '修改人id';
comment on column daq_appendages_mark_stake.modify_user_name is '修改人名称';
comment on column daq_appendages_mark_stake.modify_datetime is '修改时间';
comment on column daq_appendages_mark_stake.active is '有效标志';
create index INDEX_DAQ_APPENDAGES_MARK_STAKE_MEDIAN_STAKE_OID_10 ON daq_appendages_mark_stake ( median_stake_oid );

CREATE TABLE daq_appendages_electronic_label (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	electronic_label_code VARCHAR (50),
	product_num VARCHAR (60),
	median_stake_oid VARCHAR (36),
	pointx NUMERIC (10, 3),
	pointy NUMERIC (11, 3),
	pointz NUMERIC (7, 3),
	burial_depth NUMERIC (7, 2),
	feature_point_type VARCHAR (50),
	burial_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT DEFAULT 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_appendages_electronic_label is '电子标签表';
comment on column daq_appendages_electronic_label.oid is '主键';
comment on column daq_appendages_electronic_label.project_oid is '项目oid';
comment on column daq_appendages_electronic_label.tenders_oid is '标段oid';
comment on column daq_appendages_electronic_label.pipeline_oid is '管线oid';
comment on column daq_appendages_electronic_label.pipe_segment_or_cross_oid is '线路段/穿跨越oid';
comment on column daq_appendages_electronic_label.electronic_label_code is '电子标签编号';
comment on column daq_appendages_electronic_label.product_num is '产品编号';
comment on column daq_appendages_electronic_label.median_stake_oid is '桩号';
comment on column daq_appendages_electronic_label.pointx is '坐标x';
comment on column daq_appendages_electronic_label.pointy is '坐标Y';
comment on column daq_appendages_electronic_label.pointz is '高程';
comment on column daq_appendages_electronic_label.burial_depth is '埋深(m)';
comment on column daq_appendages_electronic_label.feature_point_type is '特征点类型';
comment on column daq_appendages_electronic_label.burial_date is '埋设日期';
comment on column daq_appendages_electronic_label.construct_unit is '施工单位';
comment on column daq_appendages_electronic_label.supervision_unit is '监理单位';
comment on column daq_appendages_electronic_label.supervision_engineer is '监理工程师';
comment on column daq_appendages_electronic_label.collection_person is '采集人员';
comment on column daq_appendages_electronic_label.collection_date is '采集日期';
comment on column daq_appendages_electronic_label.geo_state is '空间数据状态';
comment on column daq_appendages_electronic_label.approve_status is '审核状态';
comment on column daq_appendages_electronic_label.remarks is '备注';
comment on column daq_appendages_electronic_label.create_user_id is '创建人id';
comment on column daq_appendages_electronic_label.create_user_name is '创建人名称';
comment on column daq_appendages_electronic_label.create_datetime is '创建时间';
comment on column daq_appendages_electronic_label.modify_user_id is '修改人id';
comment on column daq_appendages_electronic_label.modify_user_name is '修改人名称';
comment on column daq_appendages_electronic_label.modify_datetime is '修改时间';
comment on column daq_appendages_electronic_label.active is '有效标志';
create index INDEX_DAQ_APPENDAGES_ELECTRONIC_LABEL_PRODUCT_NUM_10 ON daq_appendages_electronic_label ( product_num );


CREATE TABLE daq_appendages_hand_hole (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	hand_hole_code VARCHAR (50),
	hand_hole_name VARCHAR (35),
	hand_hole_type VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (9, 3),
	pointx NUMERIC (10, 3),
	pointy NUMERIC (11, 3),
	pointz NUMERIC (8, 3),
	hand_hole_specifications VARCHAR (50),
	base_install_situation VARCHAR (60),
	circle_install_situation VARCHAR (60),
	material_type VARCHAR (40),
	stay_long NUMERIC (7, 2),
	is_electronic_mark SMALLINT,
	acceptance_results SMALLINT,
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT DEFAULT 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_appendages_hand_hole is '手孔表';
comment on column daq_appendages_hand_hole.oid is '主键';
comment on column daq_appendages_hand_hole.project_oid is '项目oid';
comment on column daq_appendages_hand_hole.tenders_oid is '标段oid';
comment on column daq_appendages_hand_hole.pipeline_oid is '管线oid';
comment on column daq_appendages_hand_hole.pipe_segment_or_cross_oid is '线路段/穿跨越oid';
comment on column daq_appendages_hand_hole.hand_hole_code is '孔编号';
comment on column daq_appendages_hand_hole.hand_hole_name is '名称';
comment on column daq_appendages_hand_hole.hand_hole_type is '类型';
comment on column daq_appendages_hand_hole.median_stake_oid is '桩号';
comment on column daq_appendages_hand_hole.relative_mileage is '相对桩位置(m)';
comment on column daq_appendages_hand_hole.pointx is 'X坐标';
comment on column daq_appendages_hand_hole.pointy is 'Y坐标';
comment on column daq_appendages_hand_hole.pointz is '地表高程(m)';
comment on column daq_appendages_hand_hole.hand_hole_specifications is '规格型号';
comment on column daq_appendages_hand_hole.base_install_situation is '基础制造及安装情况';
comment on column daq_appendages_hand_hole.circle_install_situation is '口圈及安装情况';
comment on column daq_appendages_hand_hole.material_type is '材料类型';
comment on column daq_appendages_hand_hole.stay_long is '光缆盘留长度(m)';
comment on column daq_appendages_hand_hole.is_electronic_mark is '是否放置电子标识';
comment on column daq_appendages_hand_hole.acceptance_results is '检查验收结果';
comment on column daq_appendages_hand_hole.construct_unit is '施工单位';
comment on column daq_appendages_hand_hole.supervision_unit is '监理单位';
comment on column daq_appendages_hand_hole.supervision_engineer is '监理工程师';
comment on column daq_appendages_hand_hole.collection_person is '采集人员';
comment on column daq_appendages_hand_hole.collection_date is '采集日期';
comment on column daq_appendages_hand_hole.geo_state is '空间数据状态';
comment on column daq_appendages_hand_hole.approve_status is '审核状态';
comment on column daq_appendages_hand_hole.remarks is '备注';
comment on column daq_appendages_hand_hole.create_user_id is '创建人id';
comment on column daq_appendages_hand_hole.create_user_name is '创建人名称';
comment on column daq_appendages_hand_hole.create_datetime is '创建时间';
comment on column daq_appendages_hand_hole.modify_user_id is '修改人id';
comment on column daq_appendages_hand_hole.modify_user_name is '修改人名称';
comment on column daq_appendages_hand_hole.modify_datetime is '修改时间';
comment on column daq_appendages_hand_hole.active is '有效标志';
create index INDEX_DAQ_APPENDAGES_HAND_HOLE_HAND_HOLE_NAME_10 ON daq_appendages_hand_hole ( hand_hole_name );

CREATE TABLE daq_appendages_obstacle (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	obstacle_code VARCHAR (45),
	obstacle_name VARCHAR (45),
	obstacle_type VARCHAR (50),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (9, 3),
	pointx NUMERIC (10, 3),
	pointy NUMERIC (11, 3),
	pointz NUMERIC (8, 3),
	subordinate_unit VARCHAR (50),
	address VARCHAR (75),
	contacts VARCHAR (20),
	telephone VARCHAR (15),
	min_distance NUMERIC (7, 3),
	is_through_pipeline SMALLINT,
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT DEFAULT 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_appendages_obstacle is '地下障碍物表';
comment on column daq_appendages_obstacle.oid is '主键';
comment on column daq_appendages_obstacle.project_oid is '项目oid';
comment on column daq_appendages_obstacle.tenders_oid is '标段oid';
comment on column daq_appendages_obstacle.pipeline_oid is '管线oid';
comment on column daq_appendages_obstacle.pipe_segment_or_cross_oid is '线路段/穿跨越oid';
comment on column daq_appendages_obstacle.obstacle_code is '障碍物编号';
comment on column daq_appendages_obstacle.obstacle_name is '障碍物名称';
comment on column daq_appendages_obstacle.obstacle_type is '障碍物类型';
comment on column daq_appendages_obstacle.median_stake_oid is '桩号';
comment on column daq_appendages_obstacle.relative_mileage is '相对桩位置(m)';
comment on column daq_appendages_obstacle.pointx is 'X坐标';
comment on column daq_appendages_obstacle.pointy is 'Y坐标';
comment on column daq_appendages_obstacle.pointz is '高程(m)';
comment on column daq_appendages_obstacle.subordinate_unit is '所属单位';
comment on column daq_appendages_obstacle.address is '地址';
comment on column daq_appendages_obstacle.contacts is '联系人';
comment on column daq_appendages_obstacle.telephone is '电话';
comment on column daq_appendages_obstacle.min_distance is '最小间距(m)';
comment on column daq_appendages_obstacle.is_through_pipeline is '是否与管道交叉';
comment on column daq_appendages_obstacle.construct_unit is '施工单位';
comment on column daq_appendages_obstacle.supervision_unit is '监理单位';
comment on column daq_appendages_obstacle.supervision_engineer is '监理工程师';
comment on column daq_appendages_obstacle.collection_person is '采集人员';
comment on column daq_appendages_obstacle.collection_date is '采集日期';
comment on column daq_appendages_obstacle.geo_state is '空间数据状态';
comment on column daq_appendages_obstacle.approve_status is '审核状态';
comment on column daq_appendages_obstacle.remarks is '备注';
comment on column daq_appendages_obstacle.create_user_id is '创建人id';
comment on column daq_appendages_obstacle.create_user_name is '创建人名称';
comment on column daq_appendages_obstacle.create_datetime is '创建时间';
comment on column daq_appendages_obstacle.modify_user_id is '修改人id';
comment on column daq_appendages_obstacle.modify_user_name is '修改人名称';
comment on column daq_appendages_obstacle.modify_datetime is '修改时间';
comment on column daq_appendages_obstacle.active is '有效标志';
create index INDEX_DAQ_APPENDAGES_OBSTACLE_OBSTACLE_NAME_10 ON daq_appendages_obstacle ( obstacle_name );

CREATE TABLE daq_appendages_hydraulic_protection (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	hydraulic_protection_code VARCHAR (45),
	hydraulic_protection_type VARCHAR (45),
	hydraulic_protection_name VARCHAR (45),
	median_stake_oid VARCHAR (36),
	relative_mileage NUMERIC (9, 3),
	start_pointx NUMERIC (10, 3),
	start_pointy NUMERIC (11, 3),
	end_pointx NUMERIC (10, 3),
	end_pointy NUMERIC (11, 3),
	structure_size VARCHAR (45),
	engineer_quatity NUMERIC (8, 3),
	hydraulic_protection_material VARCHAR (50),
	accept_date TIMESTAMP (6),
	inspection_findings SMALLINT,
	work_unit_oid VARCHAR (36),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT DEFAULT 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_appendages_hydraulic_protection is '水工保护表';
comment on column daq_appendages_hydraulic_protection.oid is '主键';
comment on column daq_appendages_hydraulic_protection.project_oid is '项目oid';
comment on column daq_appendages_hydraulic_protection.tenders_oid is '标段oid';
comment on column daq_appendages_hydraulic_protection.pipeline_oid is '管线oid';
comment on column daq_appendages_hydraulic_protection.pipe_segment_or_cross_oid is '线路段/穿跨越oid';
comment on column daq_appendages_hydraulic_protection.hydraulic_protection_code is '水工保护名称';
comment on column daq_appendages_hydraulic_protection.hydraulic_protection_type is '水工保护类型';
comment on column daq_appendages_hydraulic_protection.hydraulic_protection_name is '水工保护编号';
comment on column daq_appendages_hydraulic_protection.median_stake_oid is '桩号';
comment on column daq_appendages_hydraulic_protection.relative_mileage is '相对桩位置(m)';
comment on column daq_appendages_hydraulic_protection.start_pointx is '起点坐标X';
comment on column daq_appendages_hydraulic_protection.start_pointy is '起点坐标Y';
comment on column daq_appendages_hydraulic_protection.end_pointx is '终点坐标X';
comment on column daq_appendages_hydraulic_protection.end_pointy is '终点坐标Y';
comment on column daq_appendages_hydraulic_protection.structure_size is '结构尺寸';
comment on column daq_appendages_hydraulic_protection.engineer_quatity is '工程量(m3)';
comment on column daq_appendages_hydraulic_protection.hydraulic_protection_material is '水工保护材料';
comment on column daq_appendages_hydraulic_protection.accept_date is '检查验收日期';
comment on column daq_appendages_hydraulic_protection.inspection_findings is '检查结论';
comment on column daq_appendages_hydraulic_protection.work_unit_oid is '施工机组代号';
comment on column daq_appendages_hydraulic_protection.construct_unit is '施工单位';
comment on column daq_appendages_hydraulic_protection.supervision_unit is '监理单位';
comment on column daq_appendages_hydraulic_protection.supervision_engineer is '监理工程师';
comment on column daq_appendages_hydraulic_protection.collection_person is '采集人员';
comment on column daq_appendages_hydraulic_protection.collection_date is '采集日期';
comment on column daq_appendages_hydraulic_protection.geo_state is '空间数据状态';
comment on column daq_appendages_hydraulic_protection.approve_status is '审核状态';
comment on column daq_appendages_hydraulic_protection.remarks is '备注';
comment on column daq_appendages_hydraulic_protection.create_user_id is '创建人id';
comment on column daq_appendages_hydraulic_protection.create_user_name is '创建人名称';
comment on column daq_appendages_hydraulic_protection.create_datetime is '创建时间';
comment on column daq_appendages_hydraulic_protection.modify_user_id is '修改人id';
comment on column daq_appendages_hydraulic_protection.modify_user_name is '修改人名称';
comment on column daq_appendages_hydraulic_protection.modify_datetime is '修改时间';
comment on column daq_appendages_hydraulic_protection.active is '有效标志';
create index INDEX_DAQ_APPENDAGES_HYDRAULIC_PROTECTION_CODE ON daq_appendages_hydraulic_protection ( hydraulic_protection_code );


CREATE TABLE daq_appendages_concomitant_road (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	concomitant_road_code VARCHAR (45),
	concomitant_road_name VARCHAR (45),
	concomitant_road_length NUMERIC (10, 2),
	pavement_type VARCHAR (50),
	road_width NUMERIC (7, 2),
	commence_date TIMESTAMP (6),
	completion_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT DEFAULT 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_appendages_concomitant_road is '伴行道路表';
comment on column daq_appendages_concomitant_road.oid is '主键';
comment on column daq_appendages_concomitant_road.project_oid is '项目oid';
comment on column daq_appendages_concomitant_road.tenders_oid is '标段oid';
comment on column daq_appendages_concomitant_road.pipeline_oid is '管线oid';
comment on column daq_appendages_concomitant_road.concomitant_road_code is '伴行道路编号';
comment on column daq_appendages_concomitant_road.concomitant_road_name is '伴行道路名称';
comment on column daq_appendages_concomitant_road.concomitant_road_length is '长度(m)';
comment on column daq_appendages_concomitant_road.pavement_type is '路面类型';
comment on column daq_appendages_concomitant_road.road_width is '路基宽度(mm)';
comment on column daq_appendages_concomitant_road.commence_date is '开工日期';
comment on column daq_appendages_concomitant_road.completion_date is '完工日期';
comment on column daq_appendages_concomitant_road.construct_unit is '施工单位';
comment on column daq_appendages_concomitant_road.supervision_unit is '监理单位';
comment on column daq_appendages_concomitant_road.supervision_engineer is '监理工程师';
comment on column daq_appendages_concomitant_road.collection_person is '采集人员';
comment on column daq_appendages_concomitant_road.collection_date is '采集日期';
comment on column daq_appendages_concomitant_road.geo_state is '空间数据状态';
comment on column daq_appendages_concomitant_road.approve_status is '审核状态';
comment on column daq_appendages_concomitant_road.remarks is '备注';
comment on column daq_appendages_concomitant_road.create_user_id is '创建人id';
comment on column daq_appendages_concomitant_road.create_user_name is '创建人名称';
comment on column daq_appendages_concomitant_road.create_datetime is '创建时间';
comment on column daq_appendages_concomitant_road.modify_user_id is '修改人id';
comment on column daq_appendages_concomitant_road.modify_user_name is '修改人名称';
comment on column daq_appendages_concomitant_road.modify_datetime is '修改时间';
comment on column daq_appendages_concomitant_road.active is '有效标志';
create index INDEX_DAQ_APPENDAGES_CONCOMITANT_ROAD_CONCOMITANT_ROAD_CODE_8 ON daq_appendages_concomitant_road ( concomitant_road_code );
create index INDEX_DAQ_APPENDAGES_CONCOMITANT_ROAD_CONCOMITANT_ROAD_NAME_9 ON daq_appendages_concomitant_road ( concomitant_road_name );

CREATE TABLE daq_appendages_casing_pipe (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	tenders_oid VARCHAR (36),
	pipeline_oid VARCHAR (36),
	pipe_segment_or_cross_oid VARCHAR (36),
	start_median_stake_oid VARCHAR (36),
	start_relative_mileage NUMERIC (9, 3),
	end_median_stake_oid VARCHAR (36),
	end_relative_mileage NUMERIC (9, 3),
	start_pointx NUMERIC (10, 3),
	start_pointy NUMERIC (11, 3),
	end_pointx NUMERIC (10, 3),
	end_pointy NUMERIC (11, 3),
	casing_pipe_type VARCHAR (50),
	casing_pipe_length NUMERIC (9, 2),
	casing_pipe_specifications VARCHAR (40),
	construct_date TIMESTAMP (6),
	construct_unit VARCHAR (36),
	supervision_unit VARCHAR (38),
	supervision_engineer VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	approve_status SMALLINT DEFAULT 0,
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_appendages_casing_pipe is '套管表';
comment on column daq_appendages_casing_pipe.oid is '主键';
comment on column daq_appendages_casing_pipe.project_oid is '项目oid';
comment on column daq_appendages_casing_pipe.tenders_oid is '标段oid';
comment on column daq_appendages_casing_pipe.pipeline_oid is '管线oid';
comment on column daq_appendages_casing_pipe.pipe_segment_or_cross_oid is '线路段/穿跨越oid';
comment on column daq_appendages_casing_pipe.start_median_stake_oid is '起始桩号';
comment on column daq_appendages_casing_pipe.start_relative_mileage is '相对起始桩位置(m)';
comment on column daq_appendages_casing_pipe.end_median_stake_oid is '结束桩号';
comment on column daq_appendages_casing_pipe.end_relative_mileage is '相对结束桩位置(m)';
comment on column daq_appendages_casing_pipe.start_pointx is '起点坐标X';
comment on column daq_appendages_casing_pipe.start_pointy is '起点坐标Y';
comment on column daq_appendages_casing_pipe.end_pointx is '终点坐标X';
comment on column daq_appendages_casing_pipe.end_pointy is '终点坐标Y';
comment on column daq_appendages_casing_pipe.casing_pipe_type is '套管类型';
comment on column daq_appendages_casing_pipe.casing_pipe_length is '套管长度(m)';
comment on column daq_appendages_casing_pipe.casing_pipe_specifications is '套管规格';
comment on column daq_appendages_casing_pipe.construct_date is '施工日期';
comment on column daq_appendages_casing_pipe.construct_unit is '施工单位';
comment on column daq_appendages_casing_pipe.supervision_unit is '监理单位';
comment on column daq_appendages_casing_pipe.supervision_engineer is '监理工程师';
comment on column daq_appendages_casing_pipe.collection_person is '采集人员';
comment on column daq_appendages_casing_pipe.collection_date is '采集日期';
comment on column daq_appendages_casing_pipe.geo_state is '空间数据状态';
comment on column daq_appendages_casing_pipe.approve_status is '审核状态';
comment on column daq_appendages_casing_pipe.remarks is '备注';
comment on column daq_appendages_casing_pipe.create_user_id is '创建人id';
comment on column daq_appendages_casing_pipe.create_user_name is '创建人名称';
comment on column daq_appendages_casing_pipe.create_datetime is '创建时间';
comment on column daq_appendages_casing_pipe.modify_user_id is '修改人id';
comment on column daq_appendages_casing_pipe.modify_user_name is '修改人名称';
comment on column daq_appendages_casing_pipe.modify_datetime is '修改时间';
comment on column daq_appendages_casing_pipe.active is '有效标志';

/**********管道附属物end***************/
/**********中低压begin***************/
CREATE TABLE daq_mv_pipe_node (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipe_node_code VARCHAR (50),
	pipe_node_type VARCHAR (50),
	pipe_node_spec VARCHAR (50),
	manufacturer VARCHAR (60),
	factory_num VARCHAR (60),
	pointx NUMERIC (9, 3),
	pointy NUMERIC (9, 3),
	pointz NUMERIC (7, 2),
	buried_depth NUMERIC (7, 2),
	user_building VARCHAR (60),
	is_electronic_label SMALLINT,
	electronic_label_type VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_mv_pipe_node is '节点信息表';
comment on column daq_mv_pipe_node.oid is '主键';
comment on column daq_mv_pipe_node.project_oid is '项目oid';
comment on column daq_mv_pipe_node.pipe_node_code is '节点编号';
comment on column daq_mv_pipe_node.pipe_node_type is '点类型';
comment on column daq_mv_pipe_node.pipe_node_spec is '规格';
comment on column daq_mv_pipe_node.manufacturer is '生产厂家';
comment on column daq_mv_pipe_node.factory_num is '出厂编号';
comment on column daq_mv_pipe_node.pointx is 'X坐标';
comment on column daq_mv_pipe_node.pointy is 'Y坐标';
comment on column daq_mv_pipe_node.pointz is '管顶高程(m)';
comment on column daq_mv_pipe_node.buried_depth is '埋深(m)';
comment on column daq_mv_pipe_node.user_building is '用户楼宇';
comment on column daq_mv_pipe_node.is_electronic_label is '是否设置电子标签';
comment on column daq_mv_pipe_node.electronic_label_type is '电子标签类型';
comment on column daq_mv_pipe_node.collection_person is '采集人员';
comment on column daq_mv_pipe_node.collection_date is '采集日期';
comment on column daq_mv_pipe_node.geo_state is '空间数据状态';
comment on column daq_mv_pipe_node.remarks is '备注';
comment on column daq_mv_pipe_node.create_user_id is '创建人id';
comment on column daq_mv_pipe_node.create_user_name is '创建人名称';
comment on column daq_mv_pipe_node.create_datetime is '创建时间';
comment on column daq_mv_pipe_node.modify_user_id is '修改人id';
comment on column daq_mv_pipe_node.modify_user_name is '修改人名称';
comment on column daq_mv_pipe_node.modify_datetime is '修改时间';
comment on column daq_mv_pipe_node.active is '有效标志';
create index INDEX_DAQ_MV_PIPE_NODE_PIPE_NODE_CODE_6 ON daq_mv_pipe_node ( pipe_node_code );

CREATE TABLE daq_mv_pipe_section (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipe_section_code VARCHAR (50),
	start_pipe_node_code VARCHAR (50),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (7, 2),
	end_pipe_node_code VARCHAR (50),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (7, 2),
	pipe_section_length NUMERIC (12, 3),
	pipe_diameter NUMERIC (9, 3),
	wall_thickness NUMERIC (9, 3),
	pipe_section_material VARCHAR (50),
	pipe_section_spec VARCHAR (50),
	design_life SMALLINT,
	pipe_outer_anticorrosive SMALLINT,
	outer_anticorrosive_grade VARCHAR (10),
	cathodic_protection_method SMALLINT,
	burial_method SMALLINT,
	pipe_section_category SMALLINT,
	construct_category VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_mv_pipe_section is '管段信息表';
comment on column daq_mv_pipe_section.oid is '主键';
comment on column daq_mv_pipe_section.project_oid is '项目oid';
comment on column daq_mv_pipe_section.pipe_section_code is '管段编号';
comment on column daq_mv_pipe_section.start_pipe_node_code is '起始节点编号';
comment on column daq_mv_pipe_section.start_pointx is '起始点X坐标';
comment on column daq_mv_pipe_section.start_pointy is '起始点Y坐标';
comment on column daq_mv_pipe_section.start_pointz is '起始点管顶高程(m)';
comment on column daq_mv_pipe_section.end_pipe_node_code is '终止节点编号';
comment on column daq_mv_pipe_section.end_pointx is '终止点X坐标';
comment on column daq_mv_pipe_section.end_pointy is '终止点Y坐标';
comment on column daq_mv_pipe_section.end_pointz is '终止点管顶高程(m)';
comment on column daq_mv_pipe_section.pipe_section_length is '管段长度(m)';
comment on column daq_mv_pipe_section.pipe_diameter is '管径(mm)';
comment on column daq_mv_pipe_section.wall_thickness is '壁厚(mm)';
comment on column daq_mv_pipe_section.pipe_section_material is '材质';
comment on column daq_mv_pipe_section.pipe_section_spec is '规格';
comment on column daq_mv_pipe_section.design_life is '管道设计年限(年)';
comment on column daq_mv_pipe_section.pipe_outer_anticorrosive is '管道外防腐';
comment on column daq_mv_pipe_section.outer_anticorrosive_grade is '防腐等级';
comment on column daq_mv_pipe_section.cathodic_protection_method is '阴极保护方式';
comment on column daq_mv_pipe_section.burial_method is '埋设方式';
comment on column daq_mv_pipe_section.pipe_section_category is '管段类别';
comment on column daq_mv_pipe_section.construct_category is '施工方式';
comment on column daq_mv_pipe_section.collection_person is '采集人员';
comment on column daq_mv_pipe_section.collection_date is '采集日期';
comment on column daq_mv_pipe_section.geo_state is '空间数据状态';
comment on column daq_mv_pipe_section.remarks is '备注';
comment on column daq_mv_pipe_section.create_user_id is '创建人id';
comment on column daq_mv_pipe_section.create_user_name is '创建人名称';
comment on column daq_mv_pipe_section.create_datetime is '创建时间';
comment on column daq_mv_pipe_section.modify_user_id is '修改人id';
comment on column daq_mv_pipe_section.modify_user_name is '修改人名称';
comment on column daq_mv_pipe_section.modify_datetime is '修改时间';
comment on column daq_mv_pipe_section.active is '有效标志';
create index INDEX_DAQ_MV_PIPE_SECTION_PIPE_SECTION_CODE_6 ON daq_mv_pipe_section ( pipe_section_code );

CREATE TABLE daq_mv_across_info (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipe_section_code VARCHAR (50),
	start_pipe_node_code VARCHAR (50),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (7, 2),
	end_pipe_node_code VARCHAR (50),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (7, 2),
	pipe_section_length NUMERIC (12, 3),
	across_method VARCHAR (50),
	across_object VARCHAR (50),
	burial_method SMALLINT,
	pipe_section_category SMALLINT,
	pipe_section_material VARCHAR (50),
	pipe_section_spec VARCHAR (50),
	outer_diameter NUMERIC (9, 3),
	wall_thickness NUMERIC (9, 3),
	design_life SMALLINT,
	measure_unit VARCHAR (50),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_mv_across_info is '穿越信息表';
comment on column daq_mv_across_info.oid is '主键';
comment on column daq_mv_across_info.project_oid is '项目oid';
comment on column daq_mv_across_info.pipe_section_code is '管段编号';
comment on column daq_mv_across_info.start_pipe_node_code is '起始节点编号';
comment on column daq_mv_across_info.start_pointx is '起始点X坐标';
comment on column daq_mv_across_info.start_pointy is '起始点Y坐标';
comment on column daq_mv_across_info.start_pointz is '起始点管顶高程(m)';
comment on column daq_mv_across_info.end_pipe_node_code is '终止节点编号';
comment on column daq_mv_across_info.end_pointx is '终止点X坐标';
comment on column daq_mv_across_info.end_pointy is '终止点Y坐标';
comment on column daq_mv_across_info.end_pointz is '终止点管顶高程(m)';
comment on column daq_mv_across_info.pipe_section_length is '管段长度(m)';
comment on column daq_mv_across_info.across_method is '穿越方式';
comment on column daq_mv_across_info.across_object is '穿越对象类型';
comment on column daq_mv_across_info.burial_method is '埋地方式';
comment on column daq_mv_across_info.pipe_section_category is '管段类别';
comment on column daq_mv_across_info.pipe_section_material is '材质';
comment on column daq_mv_across_info.pipe_section_spec is '规格';
comment on column daq_mv_across_info.outer_diameter is '外径';
comment on column daq_mv_across_info.wall_thickness is '壁厚';
comment on column daq_mv_across_info.design_life is '管道设计年限';
comment on column daq_mv_across_info.measure_unit is '陀螺仪测量单位 ';
comment on column daq_mv_across_info.collection_person is '采集人员';
comment on column daq_mv_across_info.collection_date is '采集日期';
comment on column daq_mv_across_info.geo_state is '空间数据状态';
comment on column daq_mv_across_info.remarks is '备注';
comment on column daq_mv_across_info.create_user_id is '创建人id';
comment on column daq_mv_across_info.create_user_name is '创建人名称';
comment on column daq_mv_across_info.create_datetime is '创建时间';
comment on column daq_mv_across_info.modify_user_id is '修改人id';
comment on column daq_mv_across_info.modify_user_name is '修改人名称';
comment on column daq_mv_across_info.modify_datetime is '修改时间';
comment on column daq_mv_across_info.active is '有效标志';
create index INDEX_DAQ_MV_ACROSS_INFO_PIPE_SECTION_CODE_6 ON daq_mv_across_info ( pipe_section_code );

CREATE TABLE daq_mv_stride_across_info (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipe_section_code VARCHAR (50),
	start_pipe_node_code VARCHAR (50),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (7, 2),
	end_pipe_node_code VARCHAR (50),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (7, 2),
	pipe_section_length NUMERIC (12, 3),
	across_method VARCHAR (50),
	across_object VARCHAR (50),
	burial_method SMALLINT,
	pipe_section_category SMALLINT,
	pipe_section_material VARCHAR (50),
	pipe_section_spec VARCHAR (50),
	outer_diameter NUMERIC (9, 3),
	wall_thickness NUMERIC (9, 3),
	design_life SMALLINT,
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_mv_stride_across_info is '跨越信息表';
comment on column daq_mv_stride_across_info.oid is '主键';
comment on column daq_mv_stride_across_info.project_oid is '项目oid';
comment on column daq_mv_stride_across_info.pipe_section_code is '管段编号';
comment on column daq_mv_stride_across_info.start_pipe_node_code is '起始节点编号';
comment on column daq_mv_stride_across_info.start_pointx is '起始点X坐标';
comment on column daq_mv_stride_across_info.start_pointy is '起始点Y坐标';
comment on column daq_mv_stride_across_info.start_pointz is '起始点管顶高程(m)';
comment on column daq_mv_stride_across_info.end_pipe_node_code is '终止节点编号';
comment on column daq_mv_stride_across_info.end_pointx is '终止点X坐标';
comment on column daq_mv_stride_across_info.end_pointy is '终止点Y坐标';
comment on column daq_mv_stride_across_info.end_pointz is '终止点管顶高程(m)';
comment on column daq_mv_stride_across_info.pipe_section_length is '管段长度(m)';
comment on column daq_mv_stride_across_info.across_method is '穿越方式';
comment on column daq_mv_stride_across_info.across_object is '穿越对象类型';
comment on column daq_mv_stride_across_info.burial_method is '埋地方式';
comment on column daq_mv_stride_across_info.pipe_section_category is '管段类别';
comment on column daq_mv_stride_across_info.pipe_section_material is '材质';
comment on column daq_mv_stride_across_info.pipe_section_spec is '规格';
comment on column daq_mv_stride_across_info.outer_diameter is '外径';
comment on column daq_mv_stride_across_info.wall_thickness is '壁厚';
comment on column daq_mv_stride_across_info.design_life is '管道设计年限';
comment on column daq_mv_stride_across_info.collection_person is '采集人员';
comment on column daq_mv_stride_across_info.collection_date is '采集日期';
comment on column daq_mv_stride_across_info.geo_state is '空间数据状态';
comment on column daq_mv_stride_across_info.remarks is '备注';
comment on column daq_mv_stride_across_info.create_user_id is '创建人id';
comment on column daq_mv_stride_across_info.create_user_name is '创建人名称';
comment on column daq_mv_stride_across_info.create_datetime is '创建时间';
comment on column daq_mv_stride_across_info.modify_user_id is '修改人id';
comment on column daq_mv_stride_across_info.modify_user_name is '修改人名称';
comment on column daq_mv_stride_across_info.modify_datetime is '修改时间';
comment on column daq_mv_stride_across_info.active is '有效标志';
create index INDEX_DAQ_MV_STRIDE_ACROSS_INFO_PIPE_SECTION_CODE_6 ON daq_mv_stride_across_info ( pipe_section_code );

CREATE TABLE daq_mv_pipe_trench_protect (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	project_oid VARCHAR (36),
	pipe_trench_length NUMERIC (9, 2),
	pipe_trench_width NUMERIC (9, 3),
	pipe_trench_height NUMERIC (9, 4),
	start_pointx NUMERIC (9, 3),
	start_pointy NUMERIC (9, 3),
	start_pointz NUMERIC (7, 2),
	end_pointx NUMERIC (9, 3),
	end_pointy NUMERIC (9, 3),
	end_pointz NUMERIC (7, 2),
	collection_person VARCHAR (30),
	collection_date TIMESTAMP (6),
	geo_state VARCHAR (10),
	remarks VARCHAR (200),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);

comment on table daq_mv_pipe_trench_protect is '管沟信息表';
comment on column daq_mv_pipe_trench_protect.oid is '主键';
comment on column daq_mv_pipe_trench_protect.project_oid is '项目oid';
comment on column daq_mv_pipe_trench_protect.pipe_trench_length is '管沟长度(m)';
comment on column daq_mv_pipe_trench_protect.pipe_trench_width is '管沟宽度(m)';
comment on column daq_mv_pipe_trench_protect.pipe_trench_height is '管沟高度(m)';
comment on column daq_mv_pipe_trench_protect.start_pointx is '起始点X坐标';
comment on column daq_mv_pipe_trench_protect.start_pointy is '起始点Y坐标';
comment on column daq_mv_pipe_trench_protect.start_pointz is '起始点管顶高程(m)';
comment on column daq_mv_pipe_trench_protect.end_pointx is '终止点X坐标';
comment on column daq_mv_pipe_trench_protect.end_pointy is '终止点Y坐标';
comment on column daq_mv_pipe_trench_protect.end_pointz is '终止点管顶高程(m)';
comment on column daq_mv_pipe_trench_protect.collection_person is '采集人员';
comment on column daq_mv_pipe_trench_protect.collection_date is '采集日期';
comment on column daq_mv_pipe_trench_protect.geo_state is '空间数据状态';
comment on column daq_mv_pipe_trench_protect.remarks is '备注';
comment on column daq_mv_pipe_trench_protect.create_user_id is '创建人id';
comment on column daq_mv_pipe_trench_protect.create_user_name is '创建人名称';
comment on column daq_mv_pipe_trench_protect.create_datetime is '创建时间';
comment on column daq_mv_pipe_trench_protect.modify_user_id is '修改人id';
comment on column daq_mv_pipe_trench_protect.modify_user_name is '修改人名称';
comment on column daq_mv_pipe_trench_protect.modify_datetime is '修改时间';
comment on column daq_mv_pipe_trench_protect.active is '有效标志';
create index INDEX_DAQ_MV_PIPE_TRENCH_PROTECT_PIPE_TRENCH_LENGTH_6 ON daq_mv_pipe_trench_protect ( pipe_trench_length );

/**********中低压end***************/
/*********数据审核记录表begin***************/
CREATE TABLE daq_data_approve (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	business_oid VARCHAR (36),
	approve_opinion VARCHAR (200),
	approve_status SMALLINT,
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime TIMESTAMP (6),
	active smallint not null default 1
);
comment on table daq_data_approve is '数据审核记录表';
comment on column daq_data_approve.oid is '主键';
comment on column daq_data_approve.business_oid is '业务oid';
comment on column daq_data_approve.approve_opinion is '审批意见';
comment on column daq_data_approve.approve_status is '审核状态';
comment on column daq_data_approve.create_user_id is '创建人id';
comment on column daq_data_approve.create_user_name is '创建人名称';
comment on column daq_data_approve.create_datetime is '创建时间';
comment on column daq_data_approve.modify_user_id is '修改人id';
comment on column daq_data_approve.modify_user_name is '修改人名称';
comment on column daq_data_approve.modify_datetime is '修改时间';
comment on column daq_data_approve.active is '有效标志';
create index INDEX_DAQ_DATA_APPROVE_BUSINESS_OID_5 ON daq_data_approve ( business_oid );
/**********数据审核记录表end***************/


/***************添加字段*****************/
alter table daq_material_pipe_cold_bending add column front_is_use smallint DEFAULT 0;
alter table daq_material_pipe_cold_bending add column back_is_use smallint DEFAULT 0;

comment on column daq_material_pipe_cold_bending.front_is_use is '前端已焊';
comment on column daq_material_pipe_cold_bending.back_is_use is '后端已焊';

alter table daq_material_hot_bends add column materiel_code varchar(100);
alter table daq_material_hot_bends add column production_date timestamp(6);
alter table daq_material_hot_bends add column front_is_use smallint DEFAULT 0;
alter table daq_material_hot_bends add column back_is_use smallint DEFAULT 0;
alter table daq_material_hot_bends add column curve_length numeric(9,3);

comment on column daq_material_hot_bends.materiel_code IS '物料编码';
comment on column daq_material_hot_bends.production_date IS '出厂日期';
comment on column daq_material_hot_bends.front_is_use is '前端已焊';
comment on column daq_material_hot_bends.back_is_use is '后端已焊';
comment on column daq_material_hot_bends.curve_length is '曲线长度';

alter table daq_material_pipe add column materiel_code varchar(100);
alter table daq_material_pipe add column production_date timestamp(6);
alter table daq_material_pipe add column front_is_use smallint DEFAULT 0;
alter table daq_material_pipe add column back_is_use smallint DEFAULT 0;

comment on column daq_material_pipe.materiel_code IS '物料编码';
comment on column daq_material_pipe.production_date IS '出厂日期';
comment on column daq_material_pipe.front_is_use is '前端已焊';
comment on column daq_material_pipe.back_is_use is '后端已焊';

alter table daq_construction_weld add column is_anticorrosion_check smallint DEFAULT 0;
alter table daq_construction_weld add column is_rework smallint DEFAULT 0;
comment on column daq_construction_weld.is_anticorrosion_check IS '是否补扣';
comment on column daq_construction_weld.is_rework IS '是否返修';

-- ----------------------------
-- T: app版本表
-- ----------------------------
DROP TABLE IF EXISTS app_version;
CREATE TABLE app_version (
	oid varchar(36) NOT NULL PRIMARY KEY,
	version_number varchar(100) NOT NULL,
	version_desc varchar(2000) ,
	version_time timestamp(6),
	appcan_version varchar(200) ,
	product_id varchar(200) ,
	product_name varchar(200) ,
	update_model SMALLINT,
	url varchar(500) ,
	client_type varchar(100) ,
	active SMALLINT
);

-- ----------------------------
-- Records of app_version
-- ----------------------------
INSERT INTO app_version VALUES ('1', 'V1.1.0', '施工数据采集测试版本&优化部分功能&修改部分Bug', '2018-09-30 10:24:05', '0.0.23', 'daq', '施工数据采集', 1, 'http://pipeline.zyax.cn:10080/巡线卫士.apk', 'Android', 1);

/**
 * 人脸信息保存表
 */
CREATE TABLE daq_user_face_info (
oid varchar(38) ,
login_name varchar(50)  NOT NULL,
base64_image text ,
CONSTRAINT daq_user_face_info_pkey PRIMARY KEY (login_name)
)
WITH (OIDS=FALSE);

ALTER TABLE daq_user_face_info OWNER TO postgres;

COMMENT ON COLUMN daq_user_face_info.oid IS '主键';

COMMENT ON COLUMN daq_user_face_info.login_name IS '登录名';

COMMENT ON COLUMN daq_user_face_info.base64_image IS 'base64图片';

/*******框架相关数据库begin********/
CREATE TABLE SYS_ATTACHMENT (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	file_name VARCHAR (200),
	file_type VARCHAR (20),
	file_size INT,
	CONTENT bytea,
	file_description VARCHAR (200),
	file_url VARCHAR (500),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_time TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_time TIMESTAMP (6),
	active smallint not null default 1
);

CREATE TABLE SYS_ATTACHMENTBUSINESSRELATION (
	oid VARCHAR (36) NOT NULL PRIMARY KEY,
	attachment_id VARCHAR (36) NOT NULL,
	business_data_id VARCHAR (36) NOT NULL,
	business_data_table VARCHAR (50),
	business_type VARCHAR (20),
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_time TIMESTAMP (6),
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_time TIMESTAMP (6),
	active SMALLINT NOT NULL
);

COMMENT ON TABLE SYS_ATTACHMENT IS '附件表，用来存储附件相关信息';

COMMENT ON COLUMN SYS_ATTACHMENT.oid IS '主键';

COMMENT ON COLUMN SYS_ATTACHMENT.file_name IS '文件名称';

COMMENT ON COLUMN SYS_ATTACHMENT.file_type IS '文件类型';

COMMENT ON COLUMN SYS_ATTACHMENT.file_size IS '文件大小';

COMMENT ON COLUMN SYS_ATTACHMENT. CONTENT IS '文件二进制流';

COMMENT ON COLUMN SYS_ATTACHMENT.file_description IS '文件描述';

COMMENT ON COLUMN SYS_ATTACHMENT.file_url IS '文件路径';

COMMENT ON COLUMN SYS_ATTACHMENT.create_user_id IS '创建人ID';

COMMENT ON COLUMN SYS_ATTACHMENT.create_user_name IS '创建人名称';

COMMENT ON COLUMN SYS_ATTACHMENT.create_time IS '创建时间';

COMMENT ON COLUMN SYS_ATTACHMENT.modify_user_id IS '修改人ID';

COMMENT ON COLUMN SYS_ATTACHMENT.modify_user_name IS '修改人名称';

COMMENT ON COLUMN SYS_ATTACHMENT.modify_time IS '修改时间';

COMMENT ON COLUMN SYS_ATTACHMENT.active IS '标识符';

COMMENT ON TABLE SYS_ATTACHMENTBUSINESSRELATION IS '附件与业务含义的关系表';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.oid IS '主键';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.attachment_id IS '附件ID';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.business_data_id IS '业务数据ID';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.business_data_table IS '业务数据表名称';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.business_type IS '业务含义';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.create_user_id IS '创建人ID';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.create_user_name IS '创建人名称';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.create_time IS '创建时间';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.modify_user_id IS '修改人ID';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.modify_user_name IS '修改人名称';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.modify_time IS '修改时间';

COMMENT ON COLUMN SYS_ATTACHMENTBUSINESSRELATION.active IS '标识符';

--为表SYS_ATTACHMENTBUSINESSRELATION添加列FILE_TYPE
ALTER TABLE SYS_ATTACHMENTBUSINESSRELATION ADD FILE_TYPE character varying(10);

/** 自定义表单-数据库-表单信息 */
create table custom_db_table(
	oid varchar(36) not null primary key,
	db_type varchar(30) not null ,
	db_source_name varchar(50) not null ,
	table_name varchar(50) not null ,
	table_name_cn varchar(100) not null ,
	table_type varchar(30) not null ,
	table_desc varchar(200)  ,
	row_index smallint not null ,
	create_user_id varchar(36) not null ,
	create_user_name varchar(50) not null ,
	create_datetime timestamp not null ,
	modify_user_id varchar(36) not null ,
	modify_user_name varchar(50) not null ,
	modify_datetime timestamp not null ,
	active smallint not null
);

create index INDEX_CUSTOM_DB_TABLE_TABLE_NAME_7 ON custom_db_table ( table_name );
comment on table custom_db_table is '自定义表单-数据库-表单信息';
comment on column custom_db_table.oid is '主键';
comment on column custom_db_table.db_type is '数据库类型';
comment on column custom_db_table.db_source_name is '数据源名称';
comment on column custom_db_table.table_name is '数据库表名称';
comment on column custom_db_table.table_type is '值范围（-32768 到 +32767）TT_10：业务属性表, TT_11：业务关系表, TT_20：点空间表, TT_21：线空间表, TT_22：面空间表（具体见预值表）';
comment on column custom_db_table.table_name_cn is '数据库表中文名称';
comment on column custom_db_table.table_desc is '数据库表描述';
comment on column custom_db_table.row_index is '排序字段';




/** 自定义表单-数据库-表字段信息 */
create table custom_db_table_fields(
	oid varchar(36) not null primary key,
	table_id varchar(36) not null ,
	field_name varchar(50) not null ,
	field_name_alias  varchar(50)  ,
	field_name_cn varchar(50)  ,
	field_type varchar(50) not null ,
	field_length varchar(50),
	if_pk varchar(20)  ,
	if_fk varchar(20)  ,
	fk_table varchar(50)  ,
	fk_table_field varchar(50)  ,
	fk_table_id varchar(36)  ,
	if_empty varchar(20)  ,
	if_index varchar(20)  ,
	field_default_value  varchar(50)  ,
	field_desc varchar(200)  ,
	row_index smallint not null ,
	create_user_id varchar(36) not null ,
	create_user_name varchar(50) not null ,
	create_datetime timestamp not null ,
	modify_user_id varchar(36) not null ,
	modify_user_name varchar(50) not null ,
	modify_datetime timestamp not null ,
	active smallint not null
);

comment on table custom_db_table_fields is '自定义表单-数据库-表字段信息';
comment on column custom_db_table_fields.oid is '主键';
comment on column custom_db_table_fields.table_id is '数据库表ID';
comment on column custom_db_table_fields.field_name is '数据库字段名称';
comment on column custom_db_table_fields.field_name_alias  is '数据库字段别名';
comment on column custom_db_table_fields.field_name_cn is '数据库字段中文名称';
comment on column custom_db_table_fields.field_type is '字段类型';
comment on column custom_db_table_fields.field_length is '字段长度/精度';
comment on column custom_db_table_fields.if_pk is '是否主键';
comment on column custom_db_table_fields.if_fk is '是否外键';
comment on column custom_db_table_fields.fk_table is '外键表名';
comment on column custom_db_table_fields.fk_table_field is '外键表字段';
comment on column custom_db_table_fields.fk_table_id is '外见表ID';
comment on column custom_db_table_fields.if_empty is '是否为空';
comment on column custom_db_table_fields.if_index is '是否索引';
comment on column custom_db_table_fields.field_default_value  is '默认值';
comment on column custom_db_table_fields.field_desc is '表字段描述';
comment on column custom_db_table_fields.row_index is '排序';


/** 自定义表单-功能配置-功能 */
create table custom_fun_function(
	oid varchar(36) not null primary key,
	function_name varchar(50) not null ,
	function_code varchar(50) not null ,
	table_name varchar(50) not null ,
	save_sql varchar(4000) not null ,
	update_sql varchar(4000) not null ,
	delete_sql varchar(4000) not null ,
	query_sql varchar(4000) not null ,
	details_sql varchar(4000) not null ,
	if_attachment varchar(20),
	create_user_id varchar(36) not null ,
	create_user_name varchar(50) not null ,
	create_datetime timestamp not null ,
	modify_user_id varchar(36) not null ,
	modify_user_name varchar(50) not null ,
	modify_datetime timestamp not null ,
	active smallint not null
);

comment on table custom_fun_function is '自定义表单-功能配置-功能';
comment on column custom_fun_function.oid is '主键';
comment on column custom_fun_function.function_name is '功能名称';
comment on column custom_fun_function.function_code is '功能编码(后台自动生成)';
comment on column custom_fun_function.table_name is '数据库表名称(数据来源)';
comment on column custom_fun_function.save_sql is '保存sql';
comment on column custom_fun_function.update_sql is '更新sql';
comment on column custom_fun_function.delete_sql is '删除sql';
comment on column custom_fun_function.query_sql is '列表查询sql';
comment on column custom_fun_function.details_sql is '详情查询sql';
comment on column custom_fun_function.if_attachment is '是否支持附件';


/** 自定义表单-功能配置-字段 */
create table custom_fun_fields(
	oid varchar(36) not null primary key,
	function_id varchar(36) not null ,
	field_name varchar(50) not null ,
	field_name_cn varchar(50)  ,
	field_source varchar(50) not null ,
	if_save varchar(20) not null ,
	if_update varchar(20) not null ,
	if_query varchar(20) not null ,
	if_list varchar(20) not null ,
	if_details varchar(20) not null ,
	if_required varchar(10) not null ,
	field_length smallint  ,
	field_type varchar(50)  ,
	ui_type varchar(50)  ,
	domain varchar(1000)  ,
	regular_expression varchar(200)  ,
	group_name varchar(100)  ,
	group_index smallint  ,
	row_index smallint  ,
	updateable varchar(10)  ,
	min int4  ,
	max int4  ,
	placeholder varchar(50)  ,
	child_field varchar(50)  ,
	request_path varchar(500)  ,
	query_index smallint ,
	list_index smallint ,
	create_user_id varchar(36) not null ,
	create_user_name varchar(50) not null ,
	create_datetime timestamp not null ,
	modify_user_id varchar(36) not null ,
	modify_user_name varchar(50) not null ,
	modify_datetime timestamp not null ,
	if_less_today varchar(20),
	less_date_scope varchar(64),
	max_date_scope varchar(64),
	active smallint not null
);

comment on table custom_fun_fields is '自定义表单-功能配置-字段';
comment on column custom_fun_fields.oid is '主键';
comment on column custom_fun_fields.function_id is '所属功能id(引用custom_fun_function的主键)';
comment on column custom_fun_fields.field_name is '数据库字段名称';
comment on column custom_fun_fields.field_name_cn is '数据库字段中文名称';
comment on column custom_fun_fields.field_source is '字段来源';
comment on column custom_fun_fields.if_save is '是否新增字段';
comment on column custom_fun_fields.if_update is '是否修改字段';
comment on column custom_fun_fields.if_query is '是否搜索字段';
comment on column custom_fun_fields.if_list is '是否列表字段';
comment on column custom_fun_fields.if_details is '是否详情字段';
comment on column custom_fun_fields.if_required is '是否是必填';
comment on column custom_fun_fields.field_length is '字段长度';
comment on column custom_fun_fields.field_type is '字段类型';
comment on column custom_fun_fields.ui_type is '控件类别（域值';
comment on column custom_fun_fields.domain is '域值';
comment on column custom_fun_fields.regular_expression is '正则表达式';
comment on column custom_fun_fields.group_name is '页面控件分组';
comment on column custom_fun_fields.group_index is '分组排序序号';
comment on column custom_fun_fields.row_index is '字段排序序号';
comment on column custom_fun_fields.updateable is '可编辑性';
comment on column custom_fun_fields.min is '最小值';
comment on column custom_fun_fields.max is '最大值';
comment on column custom_fun_fields.placeholder is '文本框默认提示';
comment on column custom_fun_fields.child_field is '子级字段';
comment on column custom_fun_fields.request_path is '请求路径';
comment on column custom_fun_fields.query_index is '查询条件排序序号';
comment on column custom_fun_fields.list_index is '列表排序序号';
comment on column custom_db_table_fields.row_index is '排序';


/** 自定义表单-数据字典 */
create table custom_dict(
	oid varchar(36) not null primary key,
	dict_type varchar(50) not null ,
	dict_type_cn varchar(50) not null ,
	dict_code varchar(50) not null ,
	dict_value varchar(50) not null ,
	dict_value_desc varchar(50)  ,
	row_index smallint not null ,
	active smallint not null
);

create index INDEX_CUSTOM_DICT_DICT_TYPE_5 ON custom_dict ( dict_type );
comment on table custom_dict is '自定义表单-数据字典';
comment on column custom_dict.oid is '主键';
comment on column custom_dict.dict_type is '类型编码';
comment on column custom_dict.dict_type_cn is '类型中文';
comment on column custom_dict.dict_code is '值编码';
comment on column custom_dict.dict_value is '值';
comment on column custom_dict.dict_value_desc is '值描述';
comment on column custom_dict.row_index is '排序';


INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('36af025c-3b7c-457f-abb4-ec0dd99acd14', 'if_type', '是否', '0', '否', NULL, '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('320bdcf5-89d2-4b9f-a66d-09310218f059', 'if_type', '是否', '1', '是', NULL, '1', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('ff8e4dc9-9e3b-4a15-b943-befb7ae2d069', 'db_type', '数据库类型', 'DT_01', 'oracle', NULL, '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('c709575b-53a6-40f4-b7b2-998bcd8894e5', 'db_type', '数据库类型', 'DT_02', 'mysql', NULL, '1', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('c1cd99b0-c8ec-4e83-80f0-4bb7cbd4bc21', 'db_type', '数据库类型', 'DT_03', 'postgresql', NULL, '2', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('2ec56b85-e8da-4939-8036-794574db85d5', 'table_type', '数据库表类型', 'TT_10', '业务属性表', NULL, '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('02b2b8b2-1411-4797-8e11-156301290367', 'table_type', '数据库表类型', 'TT_11', '业务关系表', NULL, '1', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('7bd742a8-c649-470b-8b7c-1b0430fa4c01', 'table_type', '数据库表类型', 'TT_20', '点空间表', NULL, '2', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('bf8dd52f-e8d3-431e-8eb6-db63b4bacce1', 'table_type', '数据库表类型', 'TT_21', '线空间表', NULL, '3', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('d4afecd3-5262-4f7c-bc85-a46511569680', 'table_type', '数据库表类型', 'TT_22', '面空间表', NULL, '4', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('04005f8f-2042-4913-b477-5a925755acf6', 'oracle_fields_type', 'oracle数据库字段类型', 'varchar2', 'varchar2', '字符串（不含中文，最大4KB）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('c1e503a7-e3cc-4c0c-8792-8b5b7f6aca61', 'oracle_fields_type', 'oracle数据库字段类型', 'nvarchar2', 'nvarchar2', '字符串（包含中文，最大4KB，约1333个汉字）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('b9e83ef4-89e1-4ee4-8cfa-36a8ef096955', 'oracle_fields_type', 'oracle数据库字段类型', 'integer', 'integer', '整数（不推荐使用）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('a625813e-c529-4cfe-b8bf-a9cff678dc26', 'oracle_fields_type', 'oracle数据库字段类型', 'number', 'number(P,S)', 'P为整数位，S位小数位', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('4b710f1c-bfbe-473e-bb29-9372f1f5b5b2', 'oracle_fields_type', 'oracle数据库字段类型', 'decimal', 'decimal(P,S)', 'P为整数位，S位小数位', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('efd72a02-ec0f-4767-8f0b-ac82de2bc93e', 'oracle_fields_type', 'oracle数据库字段类型', 'blob', 'blob', '二进制类型（适用于文件存储，最大4G）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('13bab619-3c73-47df-90cf-e1b5c73e1833', 'oracle_fields_type', 'oracle数据库字段类型', 'clob', 'clob', '大文本类型（最大4G）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('7a3c8216-9a02-463f-aec9-0d601c05386a', 'oracle_fields_type', 'oracle数据库字段类型', 'date', 'date', '日期时间类型', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('c38e7436-1be5-445e-9d44-65c7ad295333', 'oracle_fields_type', 'oracle数据库字段类型', 'timestamp', 'timestamp(p)', '高精度日期时间类型（p精度，范围0~9）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('a3173502-49e1-4bd5-815a-d98ec4d45cf9', 'mysql_fields_type', 'mysql数据库字段类型', 'varchar', 'varchar(n)', '字符串（包含中文，最大长度21845）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('4dc35249-d582-42c5-9808-86e0f22beab1', 'mysql_fields_type', 'mysql数据库字段类型', 'tinyint', 'tinyint', '有符号（-128~127）无符号（0~255）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('e7de0f02-6aba-4fd0-8903-8eafa580d81e', 'mysql_fields_type', 'mysql数据库字段类型', 'smallint', 'smallint', '有符号（-32768~32767)无符号（0~65535） ', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('2e60cb38-3b77-479b-84ad-ac1067b69a5a', 'mysql_fields_type', 'mysql数据库字段类型', 'mediumint', 'mediumint', '有符号（-8338608~8388607）无符号（0~16777215）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('bfe2b023-f98c-41eb-8bb8-44caf8711104', 'mysql_fields_type', 'mysql数据库字段类型', 'int', 'int', '有符号（-2147483648~2147483647）无符号（0~4294967295）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('93aa6b87-9f33-4d3f-ba0b-c6a64d080d11', 'mysql_fields_type', 'mysql数据库字段类型', 'float', 'float', '4字节', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('6f1070d1-91cb-4f2e-8275-4d42dea92bd1', 'mysql_fields_type', 'mysql数据库字段类型', 'double', 'double', '8字节', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('26a8dc32-cdb6-45e5-b573-6d6e62cf216d', 'mysql_fields_type', 'mysql数据库字段类型', 'decimal', 'decimal(m,d)', 'm为整数位，d位小数位', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('b9bb4964-a69a-4a9d-90aa-ed25aad10894', 'mysql_fields_type', 'mysql数据库字段类型', 'text', 'text', '64KB', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('73707391-1511-4375-8153-55e8dfd22f17', 'mysql_fields_type', 'mysql数据库字段类型', 'mediumtext', 'mediumtext', '16M', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('3c75fc0e-027a-4ec3-aad5-45de4c6a21b2', 'mysql_fields_type', 'mysql数据库字段类型', 'longtext', 'longtext', '4G', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('e0223d28-e52c-44bb-8aa6-709d27695b98', 'mysql_fields_type', 'mysql数据库字段类型', 'blob', 'blob', '64KB', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('045f20ff-69dc-4442-80c9-48850027bae3', 'mysql_fields_type', 'mysql数据库字段类型', 'mediumblob', 'mediumblob', '16M', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('15b0e3a5-09e0-4184-921b-23160811897d', 'mysql_fields_type', 'mysql数据库字段类型', 'longblob', 'longblob', '4G', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('0e8f192c-8b76-4436-92c1-96ff9bbc64be', 'mysql_fields_type', 'mysql数据库字段类型', 'date', 'date', 'YYYY-MM-DD', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('d527764d-607a-4a81-ae54-e40783f62412', 'mysql_fields_type', 'mysql数据库字段类型', 'time', 'time', 'HH:MM:SS', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('0c03819d-115d-46a3-a4e7-88ede0a5ec8d', 'mysql_fields_type', 'mysql数据库字段类型', 'datetime', 'datetime', 'YYYY-MM-DD HH:MM:SS', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('85a3e694-af6a-47b6-af99-9870520c4204', 'mysql_fields_type', 'mysql数据库字段类型', 'timestamp', 'timestamp', 'timestamp', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('7fd5d65f-b90a-4c0d-9b03-5c1ee5e935bf', 'pg_fields_type', 'postgreSQL数据库字段类型', 'varchar', 'varchar(size)', '字符串', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('aa311066-2dbe-4547-9631-9fe14423a733', 'pg_fields_type', 'postgreSQL数据库字段类型', 'smallint', 'smallint', '有符号（-32768~32767）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('c252c02f-87eb-42d9-8e12-d6ff93ac92fc', 'pg_fields_type', 'postgreSQL数据库字段类型', 'integer', 'integer', '有符号（-2147483648~2147483647）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('1c87448d-ef02-40bc-980a-5a2596310dcf', 'pg_fields_type', 'postgreSQL数据库字段类型', 'real', 'real', '4字节（6位数字精度）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('96a0433a-f9d1-4d41-b6ad-5681d73c408c', 'pg_fields_type', 'postgreSQL数据库字段类型', 'double', 'double', '8字节（15位数字精度）', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('f068eb6c-31cd-417d-bf35-8ad3910b0d31', 'pg_fields_type', 'postgreSQL数据库字段类型', 'numeric', 'numeric(p,s)', 'p为整数位，s位小数位', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('04429b2f-6e74-409d-a4ef-ea23ccfc97c1', 'pg_fields_type', 'postgreSQL数据库字段类型', 'decimal', 'decimal(p,s)', 'p为整数位，s位小数位', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('4538e49a-eb78-42b4-94a4-ab8fd660a4bf', 'pg_fields_type', 'postgreSQL数据库字段类型', 'text', 'text', '文本', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('f3d8b299-7b70-4085-95b5-f81a9eac824f', 'pg_fields_type', 'postgreSQL数据库字段类型', 'date', 'date', 'YYYY-MM-DD', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('b54ff40d-a456-4764-8853-6644e6e4d9e8', 'pg_fields_type', 'postgreSQL数据库字段类型', 'time', 'time', 'HH:MM:SS', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('7672c0e7-c575-4333-8d8e-314320059b41', 'pg_fields_type', 'postgreSQL数据库字段类型', 'timestamp', 'timestamp', 'timestamp', '0', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('afab443e-f0f4-4810-86b9-0649c8b1f005', 'ui_type', 'UI控件类型', 'UT_01', '文本输入框', '', '10', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('1379e709-818a-4675-8f52-dd46697d539f', 'ui_type', 'UI控件类型', 'UT_14', '数字输入框', '', '20', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('2f5b94e9-3817-4649-b7e9-b6f529a804ce', 'ui_type', 'UI控件类型', 'UT_02', '时间输入框', '', '30', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('6eecd829-c26a-4d20-af15-6d5fe162e6e3', 'ui_type', 'UI控件类型', 'UT_03', '日期输入框', '', '40', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('c7b0287d-9b3f-4ef2-9f16-4f5be790a775', 'ui_type', 'UI控件类型', 'UT_04', '时间日期输入框', '', '50', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('4b12d1e2-65cf-4bbf-87eb-50bc32d2ccaa', 'ui_type', 'UI控件类型', 'UT_05', '单选框(sql)', '', '60', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('253005b4-bc7d-4326-8a7e-3ed18eb054ec', 'ui_type', 'UI控件类型', 'UT_06', '单选框(json)', '', '70', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('1612535a-b418-4902-8ece-8132185a864e', 'ui_type', 'UI控件类型', 'UT_07', '复选框(sql)', '', '80', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('6299de0a-2821-496f-b197-f4765a55dbfd', 'ui_type', 'UI控件类型', 'UT_08', '复选框(json)', '', '90', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('52eb5091-64eb-4316-be8c-e7347ecff36e', 'ui_type', 'UI控件类型', 'UT_09', '下拉多选框(sql)', '', '100', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('68221361-24ad-49a3-862a-a0e37b2a919d', 'ui_type', 'UI控件类型', 'UT_10', '下拉多选框(json)', '', '110', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('f1e032d0-53af-4b6f-b1c4-3a4492c01524', 'ui_type', 'UI控件类型', 'UT_11', '下拉选择框(sql)', '', '120', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('9b9a97f8-e60d-4134-a86e-e28022aa0d7f', 'ui_type', 'UI控件类型', 'UT_12', '下拉选择框(json)', '', '130', '1');
INSERT INTO custom_dict(oid, dict_type, dict_type_cn, dict_code, dict_value, dict_value_desc, row_index, active) VALUES ('fb07f70c-5354-43ec-887e-a5da99460e6f', 'ui_type', 'UI控件类型', 'UT_13', '文本域', '', '130', '1');


/** 自定义功能配置, 唯一性验证 */
create table custom_fun_unique_validate(
	oid varchar(36) not null primary key,
	function_code varchar(24) not null ,
	unique_condition varchar(128)  ,
	unique_field varchar(48) not null ,
	unique_field_message varchar(64)  ,
	condition_expression varchar(64)  ,
	logical_del_field varchar(24)  ,
	create_user_id varchar(36) not null ,
	create_user_name varchar(50) not null ,
	create_datetime timestamp not null ,
	modify_user_id varchar(36) not null ,
	modify_user_name varchar(50) not null ,
	modify_datetime timestamp not null ,
	active smallint not null
);

comment on table custom_fun_unique_validate is '自定义功能配置, 唯一性验证';
comment on column custom_fun_unique_validate.oid is '主键';
comment on column custom_fun_unique_validate.function_code is '功能编号';
comment on column custom_fun_unique_validate.unique_condition is '唯一性条件(逗号分隔)';
comment on column custom_fun_unique_validate.unique_field is '唯一性字段';
comment on column custom_fun_unique_validate.unique_field_message is '提示信息';
comment on column custom_fun_unique_validate.condition_expression is '表达式';
comment on column custom_fun_unique_validate.logical_del_field is '逻辑有效字段(默认为active)';

CREATE TABLE pri_application (
oid varchar(36)  NOT NULL,
app_name varchar(50)  NOT NULL,
description varchar(200) ,
role_id varchar(36) ,
app_url varchar(200) ,
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 DEFAULT 1 NOT NULL,
CONSTRAINT pri_application_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE pri_application OWNER TO postgres;

COMMENT ON TABLE pri_application IS '应用系统表';

COMMENT ON COLUMN pri_application.oid IS '主键';

COMMENT ON COLUMN pri_application.app_name IS '应用系统名称';

COMMENT ON COLUMN pri_application.description IS '应用系统描述';

COMMENT ON COLUMN pri_application.role_id IS '应用系统管理角色ID';

COMMENT ON COLUMN pri_application.app_url IS '应用系统前缀';

COMMENT ON COLUMN pri_application.create_user_id IS '记录创建人ID';

COMMENT ON COLUMN pri_application.create_user_name IS '记录创建人姓名';

COMMENT ON COLUMN pri_application.create_datetime IS '记录创建时间';

COMMENT ON COLUMN pri_application.modify_user_id IS '最近修改人ID';

COMMENT ON COLUMN pri_application.modify_user_name IS '最近修改人姓名';

COMMENT ON COLUMN pri_application.modify_datetime IS '最近修改时间';

COMMENT ON COLUMN pri_application.active IS '数据有效标识：1有效 0无效';

CREATE TABLE pri_func_privilege (
oid varchar(36)  NOT NULL,
parent_id varchar(36) ,
privilege_name varchar(50)  NOT NULL,
privilege_code varchar(50)  NOT NULL,
privilege_type varchar(20)  NOT NULL,
open_type varchar(20) ,
url varchar(2000) ,
description varchar(200) ,
app_id varchar(36)  NOT NULL,
hierarchy varchar(200)  NOT NULL,
hlevel varchar(20)  NOT NULL,
order_num int2 NOT NULL,
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 DEFAULT 1 NOT NULL,
icon varchar(50) ,
function_type varchar(50) ,
query_name varchar(200) ,
CONSTRAINT pri_func_privilege_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE pri_func_privilege OWNER TO postgres;

COMMENT ON TABLE pri_func_privilege IS '功能权限表（包含菜单权限、功能权限、表单权限）';

COMMENT ON COLUMN pri_func_privilege.oid IS '主键';

COMMENT ON COLUMN pri_func_privilege.parent_id IS '父权限id';

COMMENT ON COLUMN pri_func_privilege.privilege_name IS '权限名称';

COMMENT ON COLUMN pri_func_privilege.privilege_code IS '权限编码';

COMMENT ON COLUMN pri_func_privilege.privilege_type IS '权限类型：1菜单 2功能点 3表单 4 表单字段';

COMMENT ON COLUMN pri_func_privilege.open_type IS '菜单的打开方式：1表示tab方式，2表示新页面方式，3表示弹窗';

COMMENT ON COLUMN pri_func_privilege.url IS '权限url';

COMMENT ON COLUMN pri_func_privilege.description IS '权限说明';

COMMENT ON COLUMN pri_func_privilege.app_id IS '应用系统ID';

COMMENT ON COLUMN pri_func_privilege.hierarchy IS '层级编号';

COMMENT ON COLUMN pri_func_privilege.hlevel IS '层级等级';

COMMENT ON COLUMN pri_func_privilege.order_num IS '排序编号';

COMMENT ON COLUMN pri_func_privilege.create_user_id IS '记录创建人ID';

COMMENT ON COLUMN pri_func_privilege.create_user_name IS '记录创建人姓名';

COMMENT ON COLUMN pri_func_privilege.create_datetime IS '记录创建时间';

COMMENT ON COLUMN pri_func_privilege.modify_user_id IS '最近修改人ID';

COMMENT ON COLUMN pri_func_privilege.modify_user_name IS '最近修改人姓名';

COMMENT ON COLUMN pri_func_privilege.modify_datetime IS '最近修改时间';

COMMENT ON COLUMN pri_func_privilege.active IS '数据有效标识：1有效 0无效';

COMMENT ON COLUMN pri_func_privilege.function_type IS '功能点类型';

CREATE TABLE pri_role (
oid varchar(36)  NOT NULL,
role_name varchar(50)  NOT NULL,
role_type varchar(20)  NOT NULL,
unit_id varchar(36)  NOT NULL,
description varchar(200) ,
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 DEFAULT 1 NOT NULL,
CONSTRAINT pri_role_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE pri_role OWNER TO postgres;

COMMENT ON TABLE pri_role IS '[权限]角色表';

COMMENT ON COLUMN pri_role.oid IS '主键';

COMMENT ON COLUMN pri_role.role_name IS '角色名';

COMMENT ON COLUMN pri_role.role_type IS '角色类型包括私有和保护两种。具体业务规则为：私有角色为角色所属部门私有的角色，其他部门的人员对其不可见，保护类型的角色的可见范围所属部门及其下属部门人员可见。该规则主要用于系统管理的分级管理，上级管理员可以设定保护角色，则下级管理员可以直接应用，但不能对其修改。';

COMMENT ON COLUMN pri_role.unit_id IS '角色所属部门ID';

COMMENT ON COLUMN pri_role.description IS '角色说明';

COMMENT ON COLUMN pri_role.create_user_id IS '记录创建人ID';

COMMENT ON COLUMN pri_role.create_user_name IS '记录创建人姓名';

COMMENT ON COLUMN pri_role.create_datetime IS '记录创建时间';

COMMENT ON COLUMN pri_role.modify_user_id IS '最近修改人ID';

COMMENT ON COLUMN pri_role.modify_user_name IS '最近修改人姓名';

COMMENT ON COLUMN pri_role.modify_datetime IS '最近修改时间';

COMMENT ON COLUMN pri_role.active IS '数据有效标识：1有效 0无效';

CREATE TABLE pri_role_func_privilege_ref (
oid varchar(36)  NOT NULL,
role_id varchar(36)  NOT NULL,
func_privilege_id varchar(36)  NOT NULL,
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 DEFAULT 1 NOT NULL,
app_id varchar(36)  NOT NULL,
CONSTRAINT pri_role_func_privilege_ref_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE pri_role_func_privilege_ref OWNER TO postgres;

COMMENT ON TABLE pri_role_func_privilege_ref IS '角色与权限关联表';

COMMENT ON COLUMN pri_role_func_privilege_ref.oid IS '主键';

COMMENT ON COLUMN pri_role_func_privilege_ref.role_id IS '角色id';

COMMENT ON COLUMN pri_role_func_privilege_ref.func_privilege_id IS '权限id';

COMMENT ON COLUMN pri_role_func_privilege_ref.create_user_id IS '记录创建人ID';

COMMENT ON COLUMN pri_role_func_privilege_ref.create_user_name IS '记录创建人姓名';

COMMENT ON COLUMN pri_role_func_privilege_ref.create_datetime IS '记录创建时间';

COMMENT ON COLUMN pri_role_func_privilege_ref.modify_user_id IS '最近修改人ID';

COMMENT ON COLUMN pri_role_func_privilege_ref.modify_user_name IS '最近修改人姓名';

COMMENT ON COLUMN pri_role_func_privilege_ref.modify_datetime IS '最近修改时间';

COMMENT ON COLUMN pri_role_func_privilege_ref.active IS '数据有效标识：1有效 0无效';

CREATE TABLE pri_unit (
oid varchar(36)  NOT NULL,
parent_id varchar(36) ,
unit_name varchar(50)  NOT NULL,
unit_code varchar(50)  NOT NULL,
description varchar(200) ,
unit_type varchar(20)  NOT NULL,
address varchar(200) ,
phone varchar(50) ,
addata int2,
ext_field1 varchar(500) ,
ext_field2 varchar(500) ,
ext_field3 varchar(500) ,
ext_field4 varchar(500) ,
ext_field5 varchar(500) ,
hierarchy varchar(200)  NOT NULL,
hlevel varchar(20)  NOT NULL,
order_num int2 NOT NULL,
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 DEFAULT 1 NOT NULL,
CONSTRAINT pri_unit_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE pri_unit OWNER TO postgres;

COMMENT ON TABLE pri_unit IS '组织机构表';

COMMENT ON COLUMN pri_unit.oid IS '主键';

COMMENT ON COLUMN pri_unit.parent_id IS '父组织机构id';

COMMENT ON COLUMN pri_unit.unit_name IS '组织机构名称';

COMMENT ON COLUMN pri_unit.unit_code IS '组织机构编码';

COMMENT ON COLUMN pri_unit.description IS '组织机构说明';

COMMENT ON COLUMN pri_unit.unit_type IS '组织机构类型分两种，部门和机关，划分的依据是组织机构所管理的业务数据范围的不同。部门可以管理本部门及下属部门和机关的业务数据，机关可以管理的业务数据范围与机关所属的部门相同。1代表机关，2代表部门';

COMMENT ON COLUMN pri_unit.address IS '组织机构地址';

COMMENT ON COLUMN pri_unit.phone IS '组织机构联系电话';

COMMENT ON COLUMN pri_unit.addata IS '0表示平台数据，1表示ldap上的数据';

COMMENT ON COLUMN pri_unit.ext_field1 IS '备用字段';

COMMENT ON COLUMN pri_unit.ext_field2 IS '备用字段';

COMMENT ON COLUMN pri_unit.ext_field3 IS '备用字段';

COMMENT ON COLUMN pri_unit.ext_field4 IS '备用字段';

COMMENT ON COLUMN pri_unit.ext_field5 IS '备用字段';

COMMENT ON COLUMN pri_unit.hierarchy IS '层级编号';

COMMENT ON COLUMN pri_unit.hlevel IS '层级等级';

COMMENT ON COLUMN pri_unit.order_num IS '同级排序编号';

COMMENT ON COLUMN pri_unit.create_user_id IS '记录创建人ID';

COMMENT ON COLUMN pri_unit.create_user_name IS '记录创建人姓名';

COMMENT ON COLUMN pri_unit.create_datetime IS '记录创建时间';

COMMENT ON COLUMN pri_unit.modify_user_id IS '最近修改人ID';

COMMENT ON COLUMN pri_unit.modify_user_name IS '最近修改人姓名';

COMMENT ON COLUMN pri_unit.modify_datetime IS '最近修改时间';

COMMENT ON COLUMN pri_unit.active IS '数据有效标识：1有效 0无效';

CREATE TABLE pri_user (
oid varchar(36)  NOT NULL,
user_name varchar(50)  NOT NULL,
login_name varchar(50)  NOT NULL,
password varchar(100)  NOT NULL,
password_expired_date timestamp(6),
validdate timestamp(6),
address varchar(200) ,
phone varchar(50) ,
email varchar(100) ,
unit_id varchar(36)  NOT NULL,
user_type varchar(20) ,
addata int2,
description varchar(200) ,
ext_field1 varchar(500) ,
ext_field2 varchar(500) ,
ext_field3 varchar(500) ,
ext_field4 varchar(500) ,
ext_field5 varchar(500) ,
find_pass_validcode varchar(50) ,
find_pass_validtime timestamp(6),
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 DEFAULT 1 NOT NULL,
CONSTRAINT pri_user_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE pri_user OWNER TO postgres;

COMMENT ON TABLE pri_user IS '用户表';

COMMENT ON COLUMN pri_user.oid IS '主键';

COMMENT ON COLUMN pri_user.user_name IS '用户姓名';

COMMENT ON COLUMN pri_user.login_name IS '登陆名';

COMMENT ON COLUMN pri_user.password IS '登陆密码  MD5码';

COMMENT ON COLUMN pri_user.password_expired_date IS '密码到期时间';

COMMENT ON COLUMN pri_user.validdate IS '有效日期';

COMMENT ON COLUMN pri_user.address IS '地址';

COMMENT ON COLUMN pri_user.phone IS '电话';

COMMENT ON COLUMN pri_user.email IS '电子邮件地址';

COMMENT ON COLUMN pri_user.unit_id IS '所属部门ID';

COMMENT ON COLUMN pri_user.user_type IS '用户类型';

COMMENT ON COLUMN pri_user.addata IS '0表示平台数据，1表示ldap上的数据';

COMMENT ON COLUMN pri_user.description IS '说明';

COMMENT ON COLUMN pri_user.ext_field1 IS '备用字段';

COMMENT ON COLUMN pri_user.ext_field2 IS '备用字段';

COMMENT ON COLUMN pri_user.ext_field3 IS '备用字段';

COMMENT ON COLUMN pri_user.ext_field4 IS '备用字段';

COMMENT ON COLUMN pri_user.ext_field5 IS '备用字段';

COMMENT ON COLUMN pri_user.find_pass_validcode IS '找回密码验证码，暂时无用';

COMMENT ON COLUMN pri_user.find_pass_validtime IS '找回密码有效时间，暂时无用';

COMMENT ON COLUMN pri_user.create_user_id IS '记录创建人ID';

COMMENT ON COLUMN pri_user.create_user_name IS '记录创建人姓名';

COMMENT ON COLUMN pri_user.create_datetime IS '记录创建时间';

COMMENT ON COLUMN pri_user.modify_user_id IS '最近修改人ID';

COMMENT ON COLUMN pri_user.modify_user_name IS '最近修改人姓名';

COMMENT ON COLUMN pri_user.modify_datetime IS '最近修改时间';

COMMENT ON COLUMN pri_user.active IS '数据有效标识：1有效 0无效';

CREATE TABLE pri_user_role_ref (
oid varchar(36)  NOT NULL,
user_id varchar(36)  NOT NULL,
role_id varchar(36)  NOT NULL,
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 DEFAULT 1 NOT NULL,
CONSTRAINT pri_user_role_ref_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE pri_user_role_ref OWNER TO postgres;

COMMENT ON TABLE pri_user_role_ref IS '用户与角色对应关系表';

COMMENT ON COLUMN pri_user_role_ref.oid IS '主键';

COMMENT ON COLUMN pri_user_role_ref.user_id IS '用户id';

COMMENT ON COLUMN pri_user_role_ref.role_id IS '角色id';

COMMENT ON COLUMN pri_user_role_ref.create_user_id IS '记录创建人ID';

COMMENT ON COLUMN pri_user_role_ref.create_user_name IS '记录创建人姓名';

COMMENT ON COLUMN pri_user_role_ref.create_datetime IS '记录创建时间';

COMMENT ON COLUMN pri_user_role_ref.modify_user_id IS '最近修改人ID';

COMMENT ON COLUMN pri_user_role_ref.modify_user_name IS '最近修改人姓名';

COMMENT ON COLUMN pri_user_role_ref.modify_datetime IS '最近修改时间';

COMMENT ON COLUMN pri_user_role_ref.active IS '数据有效标识：1有效 0无效';

CREATE TABLE sys_domain (
oid varchar(36)  NOT NULL,
domain_name varchar(50)  NOT NULL,
code_id varchar(50) ,
parent_code_id varchar(36) ,
code_name varchar(50) ,
ordinal int2,
description varchar ,
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 NOT NULL,
CONSTRAINT sys_domain_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE sys_domain OWNER TO postgres;

COMMENT ON TABLE sys_domain IS '域值表';

COMMENT ON COLUMN sys_domain.oid IS '主键';

COMMENT ON COLUMN sys_domain.domain_name IS '域名称';

COMMENT ON COLUMN sys_domain.code_id IS '域值Id';

COMMENT ON COLUMN sys_domain.parent_code_id IS '父节点Id';

COMMENT ON COLUMN sys_domain.code_name IS '域值名称';

COMMENT ON COLUMN sys_domain.ordinal IS '序号';

COMMENT ON COLUMN sys_domain.description IS '描述';

CREATE INDEX index_sys_domain_domain_name_5 ON sys_domain USING btree (domain_name);

CREATE TABLE sys_excel_template (
oid varchar(36)  NOT NULL,
excel_template_name varchar(50) ,
excel_template_code varchar(50)  NOT NULL,
excel_template_type varchar(36) ,
remark varchar(200) ,
create_user_id varchar(36) ,
create_user_name varchar(50) ,
create_datetime timestamp(6),
modify_user_id varchar(36) ,
modify_user_name varchar(50) ,
modify_datetime timestamp(6),
active int2 NOT NULL,
excel_template_path varchar(255) ,
CONSTRAINT sys_excel_template_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE sys_excel_template OWNER TO postgres;

COMMENT ON TABLE sys_excel_template IS 'excel导入导出模板管理表';

COMMENT ON COLUMN sys_excel_template.oid IS '主键';

COMMENT ON COLUMN sys_excel_template.excel_template_name IS '管线名称';

COMMENT ON COLUMN sys_excel_template.excel_template_code IS '管线编号';

COMMENT ON COLUMN sys_excel_template.excel_template_type IS '项目id';

COMMENT ON COLUMN sys_excel_template.remark IS '备注';

COMMENT ON COLUMN sys_excel_template.excel_template_path IS '模板存储路径';

CREATE INDEX index_sys_excel_template_excel_template_code_6 ON sys_excel_template USING btree (excel_template_code);

CREATE INDEX index_sys_excel_template_excel_template_name_5 ON sys_excel_template USING btree (excel_template_name);

CREATE TABLE sys_login_log (
oid varchar(36)  NOT NULL,
user_id varchar(36)  NOT NULL,
user_name varchar(50)  NOT NULL,
login_name varchar(50)  NOT NULL,
unit_id varchar(36)  NOT NULL,
unit_name varchar(50)  NOT NULL,
unit_name_fullpath varchar(200) ,
token varchar(100)  NOT NULL,
app_id varchar(36) ,
app_name varchar(50) ,
login_datetime timestamp(6) NOT NULL,
exit_datetime timestamp(6),
server_ip varchar(25) ,
client_ip varchar(25) ,
os_type varchar(20) ,
browser_type varchar(20) ,
browser_ver varchar(20) ,
screen_dpi varchar(20) ,
CONSTRAINT sys_login_log_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE sys_login_log OWNER TO postgres;

COMMENT ON TABLE sys_login_log IS '用户登录日志信息表';

COMMENT ON COLUMN sys_login_log.oid IS '主键';

COMMENT ON COLUMN sys_login_log.user_id IS '用户ID';

COMMENT ON COLUMN sys_login_log.user_name IS '用户姓名';

COMMENT ON COLUMN sys_login_log.login_name IS '用户登录名';

COMMENT ON COLUMN sys_login_log.unit_id IS '用户部门ID';

COMMENT ON COLUMN sys_login_log.unit_name IS '用户部门名称';

COMMENT ON COLUMN sys_login_log.unit_name_fullpath IS '用户部门全路径';

COMMENT ON COLUMN sys_login_log.token IS 'token值';

COMMENT ON COLUMN sys_login_log.app_id IS '登录的应用ID';

COMMENT ON COLUMN sys_login_log.app_name IS '登录的应用名称';

COMMENT ON COLUMN sys_login_log.login_datetime IS '登录时间';

COMMENT ON COLUMN sys_login_log.exit_datetime IS '退出时间';

COMMENT ON COLUMN sys_login_log.server_ip IS '服务IP';

COMMENT ON COLUMN sys_login_log.client_ip IS '客户端IP';

COMMENT ON COLUMN sys_login_log.os_type IS '操作系统类型';

COMMENT ON COLUMN sys_login_log.browser_type IS '浏览器类型';

COMMENT ON COLUMN sys_login_log.browser_ver IS '浏览器版本';

COMMENT ON COLUMN sys_login_log.screen_dpi IS '屏幕分辨率';


CREATE INDEX index_sys_login_log_oid_140 ON sys_login_log USING btree (oid);

CREATE TABLE log_business (
oid varchar(36)  NOT NULL,
business_id varchar(36)  NOT NULL,
opt_type varchar(50)  NOT NULL,
detail text ,
remark varchar(2000) ,
create_user_id varchar(36)  NOT NULL,
create_user_name varchar(50)  NOT NULL,
create_time timestamp(6),
CONSTRAINT log_business_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE log_business OWNER TO postgres;

COMMENT ON TABLE log_business IS '业务日志记录表';

COMMENT ON COLUMN log_business.oid IS '主键';

COMMENT ON COLUMN log_business.business_id IS '业务数据ID';

COMMENT ON COLUMN log_business.opt_type IS '操作类型';

COMMENT ON COLUMN log_business.detail IS '详情。详情。若是更新，必须是json数组：[{dataItem:自主外包费,newValue:1000.0,oldValue:50000.0}]），其他类型则无限制';

COMMENT ON COLUMN log_business.remark IS '备注';

CREATE TABLE log_operate (
oid varchar(36)  NOT NULL,
business_id varchar(36) ,
business_name varchar(50) ,
table_name varchar(200) ,
opt_type varchar(50)  NOT NULL,
detail varchar(2000) ,
remark varchar(2000) ,
create_user_id varchar(36)  NOT NULL,
create_user_name varchar(50)  NOT NULL,
create_time timestamp(6),
app_id varchar(36) ,
app_name varchar(50) ,
CONSTRAINT log_operate_pkey PRIMARY KEY (oid)
)
WITH (OIDS=FALSE)
;

ALTER TABLE log_operate OWNER TO postgres;

COMMENT ON TABLE log_operate IS '操作日志记录表';

COMMENT ON COLUMN log_operate.oid IS '主键';

COMMENT ON COLUMN log_operate.business_id IS '业务数据ID';

COMMENT ON COLUMN log_operate.business_name IS '业务名称';

COMMENT ON COLUMN log_operate.table_name IS '业务表名';

COMMENT ON COLUMN log_operate.opt_type IS '操作类型';

COMMENT ON COLUMN log_operate.detail IS '详情';

COMMENT ON COLUMN log_operate.remark IS '备注';

COMMENT ON COLUMN log_operate.create_user_id IS '操作人ID';

COMMENT ON COLUMN log_operate.create_user_name IS '操作人姓名';

COMMENT ON COLUMN log_operate.create_time IS '操作时间';

COMMENT ON COLUMN log_operate.app_id IS '应用Id';

COMMENT ON COLUMN log_operate.app_name IS '应用名称';
/*******框架相关数据库end********/



/**
 * 名称：		空间对象表模块postgresql数据库初始化脚本
 * 创建人：	张锡梓
 * 创建时间：	2018-05-03 09:06	
 * 更新时间:	2018-05-03 19:01 修改记录表中的flag字段值
 * */

/**域值表**/
create table sys_spatial_object( 
	oid varchar(36) not null primary key,
	wkt  TEXT ,
	create_user_id varchar(36)  ,
	create_user_name varchar(50)  ,
	create_datetime timestamp(6)  ,
	modify_user_id varchar(36)  ,
	modify_user_name varchar(50)  ,
	modify_datetime timestamp(6)  ,
	active smallint not null 
);
comment on table  sys_spatial_object is '空间对象表';	
comment on column sys_spatial_object.oid is '主键';	
comment on column sys_spatial_object.wkt is 'wkt';	
comment on column sys_spatial_object.create_user_id is '创建人id';	
comment on column sys_spatial_object.create_user_name is '创建人姓名';	
comment on column sys_spatial_object.create_datetime is '创建时间';	
comment on column sys_spatial_object.modify_user_id is '修改人id';	
comment on column sys_spatial_object.modify_user_name is '修改人姓名';	
comment on column sys_spatial_object.modify_datetime is '修改时间';	
comment on column sys_spatial_object.active is '是否有效';





/**站场数据采集start**/
create table daq_dynamic_compressor( 
oid varchar(36) not null primary key,
project_oid varchar(36)  ,
tenders_oid varchar(36)  ,
pipeline_oid varchar(36)  ,
pipe_station_oid varchar(36)  ,
device_code varchar(50)  ,
compressor_name varchar(50)  ,
manufacture_number varchar(50)  ,
concentricity numeric(9,3)  ,
picture_code varchar(50)  ,
lubricant_brand varchar(50)  ,
lubricating_total_add numeric(6,3)  ,
single_debug_record varchar(200)  ,
empty_debug_record varchar(200)  ,
operate_24_hour_record varchar(200)  ,
operate_72_hour_record varchar(200)  ,
bussiness_start_date timestamp(6)  ,
construct_unit varchar(36)  ,
supervision_unit varchar(36)  ,
supervision_engineer varchar(50)  ,
collection_person varchar(30)  ,
collection_date timestamp(6)  ,
approve_status smallint  default 0 ,
remarks varchar(200)  ,
create_user_id varchar(36)  ,
create_user_name varchar(50)  ,
create_datetime timestamp(6)  ,
modify_user_id varchar(36)  ,
modify_user_name varchar(50)  ,
modify_datetime timestamp(6)  ,
active smallint  default 1  ); 
comment on table daq_dynamic_compressor is '压缩机表';
comment on column daq_dynamic_compressor.oid is '主键';
comment on column daq_dynamic_compressor.project_oid is '项目oid';
comment on column daq_dynamic_compressor.tenders_oid is '标段oid';
comment on column daq_dynamic_compressor.pipeline_oid is '管线oid';
comment on column daq_dynamic_compressor.pipe_station_oid is '站场/阀室oid';
comment on column daq_dynamic_compressor.device_code is '设备编号';
comment on column daq_dynamic_compressor.compressor_name is '压缩机名称';
comment on column daq_dynamic_compressor.manufacture_number is '出厂编号';
comment on column daq_dynamic_compressor.concentricity is '同心度(mm)';
comment on column daq_dynamic_compressor.picture_code is '照片编号';
comment on column daq_dynamic_compressor.lubricant_brand is '润滑油牌号';
comment on column daq_dynamic_compressor.lubricating_total_add is '润滑油/脂加装总量（kg)';
comment on column daq_dynamic_compressor.single_debug_record is '单体调试记录';
comment on column daq_dynamic_compressor.empty_debug_record is '空载试验记录';
comment on column daq_dynamic_compressor.operate_24_hour_record is '24小时运行记录';
comment on column daq_dynamic_compressor.operate_72_hour_record is '72小时运行记录';
comment on column daq_dynamic_compressor.bussiness_start_date is '商业运营开始日期';
comment on column daq_dynamic_compressor.construct_unit is '施工单位';
comment on column daq_dynamic_compressor.supervision_unit is '监理单位';
comment on column daq_dynamic_compressor.supervision_engineer is '监理工程师';
comment on column daq_dynamic_compressor.collection_person is '数据采集人';
comment on column daq_dynamic_compressor.collection_date is '采集日期';
comment on column daq_dynamic_compressor.approve_status is '审核状态';
comment on column daq_dynamic_compressor.remarks is '备注';
comment on column daq_dynamic_compressor.create_user_id is '创建人id';
comment on column daq_dynamic_compressor.create_user_name is '创建人名称';
comment on column daq_dynamic_compressor.create_datetime is '创建时间';
comment on column daq_dynamic_compressor.modify_user_id is '修改人id';
comment on column daq_dynamic_compressor.modify_user_name is '修改人名称';
comment on column daq_dynamic_compressor.modify_datetime is '修改时间';
comment on column daq_dynamic_compressor.active is '有效标志';


create table daq_dynamic_compressor_motor( 
oid varchar(36) not null primary key,
project_oid varchar(36)  ,
tenders_oid varchar(36)  ,
pipeline_oid varchar(36)  ,
pipe_station_oid varchar(36)  ,
device_code varchar(50)  ,
compressor_motor_name varchar(50)  ,
manufacture_number varchar(50)  ,
picture_code varchar(50)  ,
empty_debug_record varchar(200)  ,
construct_unit varchar(36)  ,
supervision_unit varchar(36)  ,
supervision_engineer varchar(50)  ,
collection_person varchar(30)  ,
collection_date timestamp(6)  ,
construct_date timestamp(6)  ,
approve_status smallint  default 0 ,
remarks varchar(200)  ,
create_user_id varchar(36)  ,
create_user_name varchar(50)  ,
create_datetime timestamp(6)  ,
modify_user_id varchar(36)  ,
modify_user_name varchar(50)  ,
modify_datetime timestamp(6)  ,
active smallint not null default 1  ); 
comment on table daq_dynamic_compressor_motor is '压缩机电机表';
comment on column daq_dynamic_compressor_motor.oid is '主键';
comment on column daq_dynamic_compressor_motor.project_oid is '项目oid';
comment on column daq_dynamic_compressor_motor.tenders_oid is '标段oid';
comment on column daq_dynamic_compressor_motor.pipeline_oid is '管线oid';
comment on column daq_dynamic_compressor_motor.pipe_station_oid is '站场/阀室oid';
comment on column daq_dynamic_compressor_motor.device_code is '设备编号';
comment on column daq_dynamic_compressor_motor.compressor_motor_name is '压缩机电机名称';
comment on column daq_dynamic_compressor_motor.manufacture_number is '出厂编号';
comment on column daq_dynamic_compressor_motor.picture_code is '照片编号';
comment on column daq_dynamic_compressor_motor.empty_debug_record is '空载试验记录';
comment on column daq_dynamic_compressor_motor.construct_unit is '施工单位';
comment on column daq_dynamic_compressor_motor.supervision_unit is '监理单位';
comment on column daq_dynamic_compressor_motor.supervision_engineer is '监理工程师';
comment on column daq_dynamic_compressor_motor.collection_person is '数据采集人';
comment on column daq_dynamic_compressor_motor.collection_date is '采集日期';
comment on column daq_dynamic_compressor_motor.construct_date is '施工日期';
comment on column daq_dynamic_compressor_motor.approve_status is '审核状态';
comment on column daq_dynamic_compressor_motor.remarks is '备注';
comment on column daq_dynamic_compressor_motor.create_user_id is '创建人id';
comment on column daq_dynamic_compressor_motor.create_user_name is '创建人名称';
comment on column daq_dynamic_compressor_motor.create_datetime is '创建时间';
comment on column daq_dynamic_compressor_motor.modify_user_id is '修改人id';
comment on column daq_dynamic_compressor_motor.modify_user_name is '修改人名称';
comment on column daq_dynamic_compressor_motor.modify_datetime is '修改时间';
comment on column daq_dynamic_compressor_motor.active is '有效标志';


create table daq_static_receiver_launcher_device( 
oid varchar(36) not null primary key,
project_oid varchar(36)  ,
tenders_oid varchar(36)  ,
pipeline_oid varchar(36)  ,
pipe_station_oid varchar(36)  ,
device_code varchar(50)  ,
device_name varchar(50)  ,
manufacture_number varchar(50)  ,
main_tube_length numeric(9,3)  ,
device_type varchar(50)  ,
length_from_equip_to_board numeric(9,3)  ,
construct_date timestamp(6)  ,
construct_unit varchar(36)  ,
supervision_unit varchar(36)  ,
supervision_engineer varchar(50)  ,
collection_person varchar(30)  ,
collection_date timestamp(6)  ,
approve_status smallint  default 0 ,
remarks varchar(200)  ,
create_user_id varchar(36)  ,
create_user_name varchar(50)  ,
create_datetime timestamp(6)  ,
modify_user_id varchar(36)  ,
modify_user_name varchar(50)  ,
modify_datetime timestamp(6)  ,
active smallint not null default 1  ); 
comment on table daq_static_receiver_launcher_device is '清管器收发球装置装置表';
comment on column daq_static_receiver_launcher_device.oid is '主键';
comment on column daq_static_receiver_launcher_device.project_oid is '项目oid';
comment on column daq_static_receiver_launcher_device.tenders_oid is '标段oid';
comment on column daq_static_receiver_launcher_device.pipeline_oid is '管线oid';
comment on column daq_static_receiver_launcher_device.pipe_station_oid is '站场/阀室oid';
comment on column daq_static_receiver_launcher_device.device_code is '设备编号';
comment on column daq_static_receiver_launcher_device.device_name is '名称';
comment on column daq_static_receiver_launcher_device.manufacture_number is '出厂编号';
comment on column daq_static_receiver_launcher_device.main_tube_length is '主筒长度(m)';
comment on column daq_static_receiver_launcher_device.device_type is '类型';
comment on column daq_static_receiver_launcher_device.length_from_equip_to_board is '喷射器/制动器至盲板的距离(m)';
comment on column daq_static_receiver_launcher_device.construct_date is '施工日期';
comment on column daq_static_receiver_launcher_device.construct_unit is '施工单位';
comment on column daq_static_receiver_launcher_device.supervision_unit is '监理单位';
comment on column daq_static_receiver_launcher_device.supervision_engineer is '监理工程师';
comment on column daq_static_receiver_launcher_device.collection_person is '数据采集人';
comment on column daq_static_receiver_launcher_device.collection_date is '采集日期';
comment on column daq_static_receiver_launcher_device.approve_status is '审核状态';
comment on column daq_static_receiver_launcher_device.remarks is '备注';
comment on column daq_static_receiver_launcher_device.create_user_id is '创建人id';
comment on column daq_static_receiver_launcher_device.create_user_name is '创建人名称';
comment on column daq_static_receiver_launcher_device.create_datetime is '创建时间';
comment on column daq_static_receiver_launcher_device.modify_user_id is '修改人id';
comment on column daq_static_receiver_launcher_device.modify_user_name is '修改人名称';
comment on column daq_static_receiver_launcher_device.modify_datetime is '修改时间';
comment on column daq_static_receiver_launcher_device.active is '有效标志';


create table daq_static_flare( 
oid varchar(36) not null primary key,
project_oid varchar(36)  ,
tenders_oid varchar(36)  ,
pipeline_oid varchar(36)  ,
pipe_station_oid varchar(36)  ,
device_code varchar(50)  ,
device_name varchar(50)  ,
manufacture_number varchar(50)  ,
construct_date timestamp(6)  ,
construct_unit varchar(36)  ,
supervision_unit varchar(36)  ,
supervision_engineer varchar(50)  ,
collection_person varchar(30)  ,
collection_date timestamp(6)  ,
approve_status smallint  default 0 ,
remarks varchar(200)  ,
create_user_id varchar(36)  ,
create_user_name varchar(50)  ,
create_datetime timestamp(6)  ,
modify_user_id varchar(36)  ,
modify_user_name varchar(50)  ,
modify_datetime timestamp(6)  ,
active smallint not null default 1  ); 
comment on table daq_static_flare is '放空火炬表';
comment on column daq_static_flare.oid is '主键';
comment on column daq_static_flare.project_oid is '项目oid';
comment on column daq_static_flare.tenders_oid is '标段oid';
comment on column daq_static_flare.pipeline_oid is '管线oid';
comment on column daq_static_flare.pipe_station_oid is '站场/阀室oid';
comment on column daq_static_flare.device_code is '设备编号';
comment on column daq_static_flare.device_name is '名称';
comment on column daq_static_flare.manufacture_number is '出厂编号';
comment on column daq_static_flare.construct_date is '施工日期';
comment on column daq_static_flare.construct_unit is '施工单位';
comment on column daq_static_flare.supervision_unit is '监理单位';
comment on column daq_static_flare.supervision_engineer is '监理工程师';
comment on column daq_static_flare.collection_person is '数据采集人';
comment on column daq_static_flare.collection_date is '采集日期';
comment on column daq_static_flare.approve_status is '审核状态';
comment on column daq_static_flare.remarks is '备注';
comment on column daq_static_flare.create_user_id is '创建人id';
comment on column daq_static_flare.create_user_name is '创建人名称';
comment on column daq_static_flare.create_datetime is '创建时间';
comment on column daq_static_flare.modify_user_id is '修改人id';
comment on column daq_static_flare.modify_user_name is '修改人名称';
comment on column daq_static_flare.modify_datetime is '修改时间';
comment on column daq_static_flare.active is '有效标志';
/**站场数据采集end**/

create table daq_median_stake_polyline(
	project_oid varchar(36),
	pipeline_oid varchar(36) not null primary key,
	project_name varchar(100),
	pipeline_name varchar(100),
	geo_state VARCHAR (10),
	active smallint not null default 1
);
comment on table daq_median_stake_polyline is '中线桩(管线)表';
comment on column daq_median_stake_polyline.project_oid is '项目oid';
comment on column daq_median_stake_polyline.project_name is '项目名称';
comment on column daq_median_stake_polyline.pipeline_oid is '管线oid';
comment on column daq_median_stake_polyline.pipeline_name is '管线名称';

create table daq_tenders_polyline(
	oid varchar(36) not null primary key,
	tenders_name varchar(100),
	geo_state VARCHAR (10),
	active smallint not null default 1
);
comment on table daq_tenders_polyline is '标段线段表';
comment on column daq_tenders_polyline.oid is 'oid';
comment on column daq_tenders_polyline.tenders_name is '标段名称';


alter table daq_material_pipe add is_check smallint not null default 0;
comment on column daq_material_pipe.is_check is '是否检查';

alter table daq_material_hot_bends add is_check smallint not null default 0;
comment on column daq_material_hot_bends.is_check is '是否检查';

alter table daq_material_pipe_cold_bending add is_check smallint not null default 0;
comment on column daq_material_pipe_cold_bending.is_check is '是否检查';

alter table pri_role add data_filter_code varchar(45);
comment on column pri_role.data_filter_code is '数据权限过滤规则';


ALTER TABLE custom_fun_fields 
  ADD COLUMN precision int2;

COMMENT ON COLUMN custom_fun_fields.precision IS 'xiao shu dian bao liu ji wei';