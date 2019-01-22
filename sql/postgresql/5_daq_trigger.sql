-- 中线桩触发
create or replace function process_daq_median_stake_polyline() returns trigger as $daq_median_stake_polyline$ 
	begin 
		if(TG_OP = 'DELETE') then 
			delete from daq_median_stake_polyline;insert into daq_median_stake_polyline(project_oid,pipeline_oid,project_name,pipeline_name,geom) select t.project_oid,t.pipeline_oid,t.project_name,t.pipeline_name,case when count(t.pipeline_oid)>1 then ST_GeomFromText(concat('LINESTRING(',string_agg(concat_ws(' ',t.pointx,t.pointy,t.pointz,t.mileage),','),')'),4490) else null end as geom from (select t.project_oid,t.pipeline_oid,t.pointx,t.pointy,t.pointz,t.mileage,dp.pipeline_name,p.project_name from daq_median_stake t left join (select oid,project_name from daq_project) p on p.oid=t.project_oid left join(select oid,pipeline_name from daq_pipeline) dp on dp.oid=t.pipeline_oid where active=1 order by mileage) t  group by t.project_oid,t.project_name,t.pipeline_oid,t.pipeline_name;
			return old;
		elsif(TG_OP='UPDATE') then 
			delete from daq_median_stake_polyline;insert into daq_median_stake_polyline(project_oid,pipeline_oid,project_name,pipeline_name,geom) select t.project_oid,t.pipeline_oid,t.project_name,t.pipeline_name,case when count(t.pipeline_oid)>1 then ST_GeomFromText(concat('LINESTRING(',string_agg(concat_ws(' ',t.pointx,t.pointy,t.pointz,t.mileage),','),')'),4490) else null end as geom from (select t.project_oid,t.pipeline_oid,t.pointx,t.pointy,t.pointz,t.mileage,dp.pipeline_name,p.project_name from daq_median_stake t left join (select oid,project_name from daq_project) p on p.oid=t.project_oid left join(select oid,pipeline_name from daq_pipeline) dp on dp.oid=t.pipeline_oid where active=1 order by mileage) t  group by t.project_oid,t.project_name,t.pipeline_oid,t.pipeline_name;
			return new;
		elsif(TG_OP='INSERT') then 
			delete from daq_median_stake_polyline;insert into daq_median_stake_polyline(project_oid,pipeline_oid,project_name,pipeline_name,geom) select t.project_oid,t.pipeline_oid,t.project_name,t.pipeline_name,case when count(t.pipeline_oid)>1 then ST_GeomFromText(concat('LINESTRING(',string_agg(concat_ws(' ',t.pointx,t.pointy,t.pointz,t.mileage),','),')'),4490) else null end as geom from (select t.project_oid,t.pipeline_oid,t.pointx,t.pointy,t.pointz,t.mileage,dp.pipeline_name,p.project_name from daq_median_stake t left join (select oid,project_name from daq_project) p on p.oid=t.project_oid left join(select oid,pipeline_name from daq_pipeline) dp on dp.oid=t.pipeline_oid where active=1 order by mileage) t  group by t.project_oid,t.project_name,t.pipeline_oid,t.pipeline_name;
			return new;
		end if;
		return null;
	end;
$daq_median_stake_polyline$ language plpgsql;
create trigger daq_median_stake_polyline_trigger
	after insert or update or delete on daq_median_stake
	for each row execute procedure process_daq_median_stake_polyline();

-- 标段触发器
create or replace function process_daq_tenders_polyline() returns trigger as $daq_tenders_polyline$ 
	begin 
		if(TG_OP = 'DELETE') then 
			delete from daq_tenders_polyline;insert into daq_tenders_polyline(oid,tenders_name,geom) select oid,tenders_name,case when count(oid)>1 then ST_GeomFromText(concat('LINESTRING(',string_agg(concat_ws(' ',pointx,pointy,pointz,mileage),','),')'),4490) else null end as geom from (select oid,tenders_name,pointx,pointy,pointz,mileage from ( select t.project_oid,s.pipeline_oid,t.oid,t.tenders_name,s.start_mileage,e.end_mileage from daq_tenders t  left join (select oid,pipeline_oid,mileage as start_mileage from daq_median_stake where active=1) s on s.oid=t.start_stake_oid left join (select oid,mileage as end_mileage from daq_median_stake where active=1) e on e.oid=t.end_stake_oid where t.active=1  ) v left join (select project_oid,pipeline_oid,pointx,pointy,pointz,mileage from daq_median_stake where active=1) m on v.start_mileage<=m.mileage and v.end_mileage>=m.mileage and v.project_oid=m.project_oid and v.pipeline_oid=m.pipeline_oid order by mileage ) t group by oid,tenders_name;
			return old;
		elsif(TG_OP='UPDATE') then 
			delete from daq_tenders_polyline;insert into daq_tenders_polyline(oid,tenders_name,geom) select oid,tenders_name,case when count(oid)>1 then ST_GeomFromText(concat('LINESTRING(',string_agg(concat_ws(' ',pointx,pointy,pointz,mileage),','),')'),4490) else null end as geom from (select oid,tenders_name,pointx,pointy,pointz,mileage from ( select t.project_oid,s.pipeline_oid,t.oid,t.tenders_name,s.start_mileage,e.end_mileage from daq_tenders t  left join (select oid,pipeline_oid,mileage as start_mileage from daq_median_stake where active=1) s on s.oid=t.start_stake_oid left join (select oid,mileage as end_mileage from daq_median_stake where active=1) e on e.oid=t.end_stake_oid where t.active=1  ) v left join (select project_oid,pipeline_oid,pointx,pointy,pointz,mileage from daq_median_stake where active=1) m on v.start_mileage<=m.mileage and v.end_mileage>=m.mileage and v.project_oid=m.project_oid and v.pipeline_oid=m.pipeline_oid order by mileage ) t group by oid,tenders_name;
			return new;
		elsif(TG_OP='INSERT') then 
			delete from daq_tenders_polyline;insert into daq_tenders_polyline(oid,tenders_name,geom) select oid,tenders_name,case when count(oid)>1 then ST_GeomFromText(concat('LINESTRING(',string_agg(concat_ws(' ',pointx,pointy,pointz,mileage),','),')'),4490) else null end as geom from (select oid,tenders_name,pointx,pointy,pointz,mileage from ( select t.project_oid,s.pipeline_oid,t.oid,t.tenders_name,s.start_mileage,e.end_mileage from daq_tenders t  left join (select oid,pipeline_oid,mileage as start_mileage from daq_median_stake where active=1) s on s.oid=t.start_stake_oid left join (select oid,mileage as end_mileage from daq_median_stake where active=1) e on e.oid=t.end_stake_oid where t.active=1  ) v left join (select project_oid,pipeline_oid,pointx,pointy,pointz,mileage from daq_median_stake where active=1) m on v.start_mileage<=m.mileage and v.end_mileage>=m.mileage and v.project_oid=m.project_oid and v.pipeline_oid=m.pipeline_oid order by mileage ) t group by oid,tenders_name;
			return new;
		end if;
		return null;
	end;
$daq_tenders_polyline$ language plpgsql;
create trigger daq_tenders_polyline_trigger
	after insert or update or delete on daq_tenders
	for each row execute procedure process_daq_tenders_polyline();