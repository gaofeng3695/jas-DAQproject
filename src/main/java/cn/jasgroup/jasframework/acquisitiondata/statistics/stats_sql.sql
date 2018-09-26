

-- web --

-- 各工序分月累计完成情况(自开工以来)

-- 长度统计: 测量放线, 管够回填, 地貌回复
select 'lay_surveying' as stats_type, concat(EXTRACT(YEAR from create_datetime), '-', EXTRACT(MONTH from create_datetime)) as stats_month, sum(relative_mileage) as ststs_result from daq_lay_surveying
where active = 1 and approve_status = 2 and project_oid in (:projectOids)
group by EXTRACT(YEAR from create_datetime), EXTRACT(MONTH from create_datetime)
union all
select 'lay_land_restoration' as stats_type, concat(EXTRACT(YEAR from create_datetime), '-', EXTRACT(MONTH from create_datetime)) as stats_month, sum(length) as ststs_result from daq_lay_land_restoration
where active = 1 and approve_status = 2 and project_oid in (:projectOids)
group by EXTRACT(YEAR from create_datetime), EXTRACT(MONTH from create_datetime)
union all
select 'lay_pipe_trench_backfill' as stats_type, concat(EXTRACT(YEAR from create_datetime), '-', EXTRACT(MONTH from create_datetime)) as stats_month, sum(backfill_length) as ststs_result from daq_lay_pipe_trench_backfill
where active = 1 and approve_status = 2 and project_oid in (:projectOids)
group by EXTRACT(YEAR from create_datetime), EXTRACT(MONTH from create_datetime)

-- 分年月统计管材长度
select 'pipe' as stats_type, stats_year_month as stats_year_month, sum(stats_result) as stats_result from (
  select 'check_coating_pipe' as stats_type, concat(EXTRACT(YEAR from t.create_datetime), '-', EXTRACT(MONTH from t.create_datetime)) as stats_year_month, sum(p.pipe_length) as stats_result
  from daq_check_coating_pipe t left join daq_material_pipe p on t.pipe_oid = p.oid
  where t.active = 1 and p.active = 1 and t.project_oid in (:projectOids)
  group by EXTRACT(YEAR from t.create_datetime), EXTRACT(MONTH from t.create_datetime)
  union all
  select 'check_hot_bends' as stats_type, concat(EXTRACT(YEAR from t.create_datetime), '-', EXTRACT(MONTH from t.create_datetime)) as stats_year_month, sum(p.pipe_length) as stats_result
  from daq_check_hot_bends t left join daq_material_hot_bends p on t.hot_bends_oid = p.oid
  where t.active = 1 and p.active = 1 and t.project_oid in (:projectOids)
  group by EXTRACT(YEAR from t.create_datetime), EXTRACT(MONTH from t.create_datetime)
) as ss group by stats_year_month
-- app --



select count(*) as total, sum(case when (approve_status=2) then 1 else 0 end) as audited
from %s where active = 1
group by to_char(create_datetime, 'yyyy-DD-mm')