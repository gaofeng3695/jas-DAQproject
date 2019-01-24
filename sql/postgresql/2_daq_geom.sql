/*********空间数据相关start*********/
/**
 * 中线桩空间字段
 */
select AddGeometryColumn('public', 'daq_median_stake', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_median_stake_geom_idx ON public.daq_median_stake USING gist (geom);
/**
 * 焊口表空间字段
 */
select AddGeometryColumn('public', 'daq_construction_weld', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_construction_weld_geom_idx ON public.daq_construction_weld USING gist (geom);
/**
 * 焊口返修表空间字段
 */
select AddGeometryColumn('public', 'daq_weld_rework_weld', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_weld_rework_weld_geom_idx ON public.daq_weld_rework_weld USING gist (geom);
/**
 * 焊口测量成表空间字段
 */
select AddGeometryColumn('public', 'daq_weld_measured_result', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_weld_measured_result_geom_idx ON public.daq_weld_measured_result USING gist (geom);
/**
 * 测量放线表空间字段
 */
select AddGeometryColumn('public', 'daq_lay_surveying', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_lay_surveying_geom_idx ON public.daq_lay_surveying USING gist (geom);

/***
 * 管沟开挖表空间字段
 */
select AddGeometryColumn('public', 'daq_lay_pipe_trench_excavation', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_lay_pipe_trench_excavation_geom_idx ON public.daq_lay_pipe_trench_excavation USING gist (geom);

/***
 * 管沟回填表空间字段
 */
select AddGeometryColumn('public', 'daq_lay_pipe_trench_backfill', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_lay_pipe_trench_backfill_geom_idx ON public.daq_lay_pipe_trench_backfill USING gist (geom);

/**
 * 地貌恢复表空间字段
 */
select AddGeometryColumn('public', 'daq_lay_land_restoration', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_lay_land_restoration_geom_idx ON public.daq_lay_land_restoration USING gist (geom);

/**
 * 保温表空间字段
 */
select AddGeometryColumn('public', 'daq_lay_thermal_insulation', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_lay_thermal_insulation_geom_idx ON public.daq_lay_thermal_insulation USING gist (geom);

/**
 * 开挖穿越表空间字段
 */
select AddGeometryColumn('public', 'daq_cross_excavation', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_excavation_geom_idx ON public.daq_cross_excavation USING gist (geom);

/**
 * 顶管穿越表空间字段
 */
select AddGeometryColumn('public', 'daq_cross_pipe_jacking', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_pipe_jacking_geom_idx ON public.daq_cross_pipe_jacking USING gist (geom);

/***
 * 箱涵穿越表空间字段
 */
select AddGeometryColumn('public', 'daq_cross_box_culvert', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_box_culvert_geom_idx ON public.daq_cross_box_culvert USING gist (geom);

/**
 * 定向钻穿越表空间字段
 */
select AddGeometryColumn('public', 'daq_cross_drilling', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_drilling_geom_idx ON public.daq_cross_drilling USING gist (geom);

/***
 * 盾构隧道穿越表空间字段
 */
select AddGeometryColumn('public', 'daq_cross_shield', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_shield_geom_idx ON public.daq_cross_shield USING gist (geom);

/**
 * 钻爆隧道穿越表空间字段
 */
select AddGeometryColumn('public', 'daq_cross_drilling_blasting', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_drilling_blasting_geom_idx ON public.daq_cross_drilling_blasting USING gist (geom);

/***
 * 跨越表空间字段
 */
select AddGeometryColumn('public', 'daq_cross_across', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_cross_across_geom_idx ON public.daq_cross_across USING gist (geom);

/***
 * 绝缘件表空间字段
 */
select AddGeometryColumn('public', 'daq_cathodic_isolating_piece', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_cathodic_isolating_piece_geom_idx ON public.daq_cathodic_isolating_piece USING gist (geom);

/***
 * 阴保电缆表空间字段
 */
select AddGeometryColumn('public', 'daq_cathodic_cable_protection', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_cathodic_cable_protection_geom_idx ON public.daq_cathodic_cable_protection USING gist (geom);

/**
 * 牺牲阳极表空间字段
 */
select AddGeometryColumn('public', 'daq_cathodic_sacrifice_anode', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_cathodic_sacrifice_anode_geom_idx ON public.daq_cathodic_sacrifice_anode USING gist (geom);

/**
 * 绝缘接头保护器表空间字段
 */
select AddGeometryColumn('public', 'daq_cathodic_insulated_joint', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_cathodic_insulated_joint_geom_idx ON public.daq_cathodic_insulated_joint USING gist (geom);

/***
 * 固态去耦合器表空间字段
 */
select AddGeometryColumn('public', 'daq_cathodic_solid_decoupler', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_cathodic_solid_decoupler_geom_idx ON public.daq_cathodic_solid_decoupler USING gist (geom);

/**
 * 测试桩表空间字段
 */
select AddGeometryColumn('public', 'daq_cathodic_test_stake', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_cathodic_test_stake_geom_idx ON public.daq_cathodic_test_stake USING gist (geom);

/***
 * 极性排流器表空间字段
 */
select AddGeometryColumn('public', 'daq_cathodic_polarity_drainage', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_cathodic_polarity_drainage_geom_idx ON public.daq_cathodic_polarity_drainage USING gist (geom);

/**
 * 辅助阳极地床表空间字段
 */
select AddGeometryColumn('public', 'daq_cathodic_anode_bed', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_cathodic_anode_bed_geom_idx ON public.daq_cathodic_anode_bed USING gist (geom);

/***
 * 标志桩表空间字段
 */
select AddGeometryColumn('public', 'daq_appendages_mark_stake', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_appendages_mark_stake_geom_idx ON public.daq_appendages_mark_stake USING gist (geom);

/***
 * 电子标签表空间字段
 */
select AddGeometryColumn('public', 'daq_appendages_electronic_label', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_appendages_electronic_label_geom_idx ON public.daq_appendages_electronic_label USING gist (geom);

/***
 * 手孔表空间字段
 */
select AddGeometryColumn('public', 'daq_appendages_hand_hole', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_appendages_hand_hole_geom_idx ON public.daq_appendages_hand_hole USING gist (geom);

/***
 * 地下障碍物表空间字段
 */
select AddGeometryColumn('public', 'daq_appendages_obstacle', 'geom', 4490, 'POINT', 4);
CREATE INDEX daq_appendages_obstacle_geom_idx ON public.daq_appendages_obstacle USING gist (geom);

/***
 * 水工保护表空间字段
 */
select AddGeometryColumn('public', 'daq_appendages_hydraulic_protection', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_appendages_hydraulic_protection_geom_idx ON public.daq_appendages_hydraulic_protection USING gist (geom);

/**
 * 套管表空间字段
 */
select AddGeometryColumn('public', 'daq_appendages_casing_pipe', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_appendages_casing_pipe_geom_idx ON public.daq_appendages_casing_pipe USING gist (geom);


select AddGeometryColumn('public', 'daq_median_stake_polyline', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_median_stake_polyline_geom_idx ON public.daq_median_stake_polyline USING gist (geom);


select AddGeometryColumn('public', 'daq_tenders_polyline', 'geom', 4490, 'LINESTRING', 4);
CREATE INDEX daq_tenders_polyline_geom_idx ON public.daq_tenders_polyline USING gist (geom);
/*********空间数据相关end*********/