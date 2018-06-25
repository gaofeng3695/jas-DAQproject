/*
 *施工数据采集产品数据库表 
 */
/**********范围管理数据表begin***************/

CREATE TABLE daq_project (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	project_name VARCHAR (50) NOT NULL COMMENT '项目名称',
	project_code VARCHAR (50) NOT NULL COMMENT '项目编号',
	medium_type_code VARCHAR (50) COMMENT '介质类型编号',
	pipe_network_type_code VARCHAR (50) COMMENT '管网类型编号',
	construct VARCHAR (50) COMMENT '建设单位编号',
	remarks VARCHAR (200) COMMENT '备注',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '项目表';

create index INDEX_DAQ_PROJECT_PROJECT_NAME_5 ON daq_project ( project_name );
create index INDEX_DAQ_PROJECT_PROJECT_CODE_6 ON daq_project ( project_code );

CREATE TABLE daq_pipeline (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	pipeline_name VARCHAR (50) NOT NULL COMMENT '管线名称',
	pipeline_code VARCHAR (50) NOT NULL COMMENT '管线编号',
	project_oid VARCHAR (36) COMMENT '项目oid',
	pipeline_type VARCHAR (30) COMMENT '管线类型',
	pipeline_length NUMERIC (6, 2) COMMENT '管线长度(km)',
	pipeline_grade VARCHAR (30) COMMENT '钢级',
	design_pressure VARCHAR (25) COMMENT '设计压力(mpa)',
	start_point VARCHAR (25) COMMENT '起点',
	end_point VARCHAR (25) COMMENT '终点',
	design_flowrate NUMERIC (3, 0) COMMENT '设计流量设计输量(108m3/a)',
	pipeline_diameter NUMERIC (5, 1) COMMENT '管径(mm)',
	remarks VARCHAR (200) COMMENT '备注',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '管线表';
create index INDEX_DAQ_PIPELINE_PIPELINE_NAME_5 ON daq_pipeline ( pipeline_name );
create index INDEX_DAQ_PIPELINE_PIPELINE_CODE_6 ON daq_pipeline ( pipeline_code );

CREATE TABLE daq_pipe_segment (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	pipe_segment_name VARCHAR (50) NOT NULL COMMENT '线路段名称',
	pipe_segment_code VARCHAR (50) NOT NULL COMMENT '线路段编号',
	project_oid VARCHAR (36) NOT NULL COMMENT '项目oid',
	pipeline_oid VARCHAR (36) NOT NULL COMMENT '管线oid',
	start_stake_oid VARCHAR (36) COMMENT '起始桩oid',
	end_stake_oid VARCHAR (36) COMMENT '终点桩oid',
	province VARCHAR (60) COMMENT '所属省',
	city VARCHAR (60) COMMENT '市',
	county VARCHAR (60) COMMENT '区/县',
	remarks VARCHAR (200) COMMENT '备注',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '线路段表';
create index INDEX_DAQ_PIPE_SEGMENT_PIPE_SEGMENT_NAME_5 ON daq_pipe_segment ( pipe_segment_name );
create index INDEX_DAQ_PIPE_SEGMENT_PIPE_SEGMENT_CODE_6 ON daq_pipe_segment ( pipe_segment_code );

CREATE TABLE daq_cross (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	cross_name VARCHAR (50) NOT NULL COMMENT '穿跨越名称',
	cross_code VARCHAR (50) NOT NULL COMMENT '穿跨越编号',
	project_oid VARCHAR (36) NOT NULL COMMENT '项目oid',
	pipeline_oid VARCHAR (36) NOT NULL COMMENT '管线oid',
	cross_type_code VARCHAR (36) COMMENT '穿跨越类型编号',
	cross_way_code VARCHAR (36) COMMENT '穿跨越方式编号',
	cross_length NUMERIC (8, 1) COMMENT '穿跨越长度',
	start_stake_oid VARCHAR (36) COMMENT '起始桩oid',
	end_stake_oid VARCHAR (36) COMMENT '终点桩oid',
	province VARCHAR (60) COMMENT '所属省',
	city VARCHAR (60) COMMENT '市',
	county VARCHAR (60) COMMENT '区/县',
	remarks VARCHAR (200) COMMENT '备注',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '穿跨越表';
create index INDEX_DAQ_CROSS_CROSS_NAME_5 ON daq_cross ( cross_name );
create index INDEX_DAQ_CROSS_CROSS_CODE_6 ON daq_cross ( cross_code );

CREATE TABLE daq_pipe_station (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	pipe_station_name VARCHAR (50) NOT NULL COMMENT '站场/阀室名称',
	pipe_station_code VARCHAR (50) NOT NULL COMMENT '站场/阀室编号',
	project_oid VARCHAR (36) NOT NULL COMMENT '项目oid',
	pipeline_oid VARCHAR (36) NOT NULL COMMENT '管线oid',
	pipe_station_classification VARCHAR (36) COMMENT '分类',
	pipe_station_type VARCHAR (36) COMMENT '类型',
	province VARCHAR (60) COMMENT '所属省',
	remarks VARCHAR (200) COMMENT '备注',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '站场/阀室表';
create index INDEX_DAQ_PIPE_STATION_PIPE_STATION_NAME_5 ON daq_pipe_station ( pipe_station_name );
create index INDEX_DAQ_PIPE_STATION_PIPE_STATION_CODE_6 ON daq_pipe_station ( pipe_station_code );

CREATE TABLE daq_power_line (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	power_line_name VARCHAR (50) NOT NULL COMMENT '外供电线路名称',
	power_line_code VARCHAR (50) NOT NULL COMMENT '外供电线路编号',
	project_oid VARCHAR (36) NOT NULL COMMENT '项目oid',
	pipeline_oid VARCHAR (36) NOT NULL COMMENT '管线oid',
	power_supply_name VARCHAR (50) COMMENT '电源点名称',
	line_length NUMERIC (6, 0) COMMENT '线路长度(m)',
	tower_type VARCHAR (50) COMMENT '杆塔类型',
	tower_total NUMERIC (6, 0) COMMENT '杆塔总基数',
	province VARCHAR(36) COMMENT '所属省',
	remarks VARCHAR (200) COMMENT '备注',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '外供电线路表';
create index INDEX_DAQ_POWER_LINE_POWER_LINE_NAME_5 ON daq_power_line ( power_line_name );
create index INDEX_DAQ_POWER_LINE_POWER_LINE_CODE_6 ON daq_power_line ( power_line_code );


CREATE TABLE daq_maintenance_road (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	road_name VARCHAR (50) NOT NULL COMMENT '伴行道路名称',
	road_code VARCHAR (50) NOT NULL COMMENT '伴行道路编号',
	project_oid VARCHAR (36) NOT NULL COMMENT '项目oid',
	pipeline_oid VARCHAR (36) NOT NULL COMMENT '管线oid',
	road_type VARCHAR (30) COMMENT '道路类型',
	road_grade VARCHAR (30) COMMENT '道路等级',
	road_length NUMERIC (4, 1) COMMENT '长度(km)',
	land_area NUMERIC (10, 2) COMMENT '用地面积(㎡)',
	province VARCHAR (60) COMMENT '所属省',
	remarks VARCHAR (200) COMMENT '备注',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '伴行道路管理表';
create index INDEX_DAQ_MAINTENANCE_ROAD_ROAD_NAME_5 ON daq_maintenance_road ( road_name );
create index INDEX_DAQ_MAINTENANCE_ROAD_ROAD_CODE_6 ON daq_maintenance_road ( road_code );

CREATE TABLE daq_tenders (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	tenders_name VARCHAR (50) NOT NULL COMMENT '标段名称',
	tenders_code VARCHAR (50) NOT NULL COMMENT '标段编号',
	project_oid VARCHAR (36) NOT NULL COMMENT '项目oid',
	manage_model VARCHAR (30) COMMENT '建设管理模式',
	start_stake_oid VARCHAR (36) COMMENT '起始桩号',
	end_stake_oid  VARCHAR (36) COMMENT '终点桩号',
	remarks VARCHAR (200) COMMENT '备注',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '标段表';
create index INDEX_DAQ_TENDERS_TENDERS_NAME_5 ON daq_tenders ( tenders_name );
create index INDEX_DAQ_TENDERS_TENDERS_CODE_6 ON daq_tenders ( tenders_code );

CREATE TABLE daq_median_stake (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	median_stake_code VARCHAR (50) NOT NULL COMMENT '中线桩编号',
	project_oid VARCHAR (36) NOT NULL COMMENT '项目oid',
	pipeline_oid VARCHAR (36) NOT NULL COMMENT '管线oid',
	mileage NUMERIC (7, 3) COMMENT '里程(km)',
	mark_stone_type VARCHAR (38) COMMENT '标石类型',
	mark_stone_location VARCHAR (150) COMMENT '标石概略位置',
	pointx NUMERIC (17, 9) COMMENT 'X坐标',
	pointy NUMERIC (17, 9) COMMENT 'Y坐标',
	pointz NUMERIC (7, 3) COMMENT '高程',
	remarks VARCHAR (200) COMMENT '备注',
	geo_state VARCHAR (10) COMMENT '空间数据状态',
	create_user_id VARCHAR (36),
	create_user_name VARCHAR (50),
	create_datetime datetime,
	modify_user_id VARCHAR (36),
	modify_user_name VARCHAR (50),
	modify_datetime datetime,
	active SMALLINT NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '中线桩表';
create index INDEX_DAQ_MEDIAN_STAKE_MEDIAN_STAKE_CODE_5 ON daq_median_stake ( median_stake_code );

CREATE TABLE daq_tenders_scope_ref (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	pipeline_oid VARCHAR (36) COMMENT '管线oid',
	tenders_oid VARCHAR (36) COMMENT '标段oid',
	scope_oid VARCHAR (36) COMMENT '实体oid（即线路段oid或者站场oid等）',
	scope_type VARCHAR (10) COMMENT '实体类型（1：线路段，2：创跨越，3：站场/阀室，4：伴行道路，5：外供电线路）',
	create_user_id VARCHAR (36) COMMENT '创建人id',
	create_user_name VARCHAR (50) COMMENT '创建人名称',
	create_datetime datetime COMMENT '创建时间',
	modify_user_id VARCHAR (36) COMMENT '修改人id',
	modify_user_name VARCHAR (50) COMMENT '修改人名称',
	modify_datetime datetime COMMENT '修改时间',
	active SMALLINT NOT NULL COMMENT '有效标志'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '标段范围关联表';
create index INDEX_DAQ_TENDERS_SCOPE_REF_TENDERS_OID_5 ON daq_tenders_scope_ref ( tenders_oid );
create index INDEX_DAQ_TENDERS_SCOPE_REF_SCOPE_OID_6 ON daq_tenders_scope_ref ( scope_oid );

/**********范围管理数据表end***************/
/**********权限管理数据表begin***************/
CREATE TABLE daq_implement_scope_ref (
	oid VARCHAR (36) NOT NULL PRIMARY KEY COMMENT '主键',
	unit_oid VARCHAR (36) COMMENT '部门oid',
	project_oid VARCHAR (36) COMMENT '项目oid',
	pipeline_oid VARCHAR (36) COMMENT '管线oid',
	scope_oid VARCHAR (36) COMMENT '实体oid（即线路段oid或者站场oid等）',
	scope_type VARCHAR (10) COMMENT '实体类型（1：线路段，2：创跨越，3：站场/阀室，4：伴行道路，5：外供电线路）',
	create_user_id VARCHAR (36) COMMENT '创建人id',
	create_user_name VARCHAR (50) COMMENT '创建人名称',
	create_datetime datetime COMMENT '创建时间',
	modify_user_id VARCHAR (36) COMMENT '修改人id',
	modify_user_name VARCHAR (50) COMMENT '修改人名称',
	modify_datetime datetime COMMENT '修改时间',
	active SMALLINT COMMENT '有效标志'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '实施范围关联表';
create index INDEX_DAQ_IMPLEMENT_SCOPE_REF_UNIT_OID_5 ON daq_implement_scope_ref ( unit_oid );
create index INDEX_DAQ_IMPLEMENT_SCOPE_REF_PROJECT_OID_6 ON daq_implement_scope_ref ( project_oid );

/**********权限管理数据表end***************/