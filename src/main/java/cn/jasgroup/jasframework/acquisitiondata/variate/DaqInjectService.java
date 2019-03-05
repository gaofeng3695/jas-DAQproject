package cn.jasgroup.jasframework.acquisitiondata.variate;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.utils.TempMapQuery;
import cn.jasgroup.jasframework.base.data.BaseData;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.base.data.MapQuery;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.security.service.UnitService;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Service
@Transactional
public class DaqInjectService {
	
	@Resource
//	private UnitDao unitDao;
	private UnitService unitService;
	
	public void injectCurrentUnitId(BaseData baseData){
		baseData.setValue("current_unit_id", ThreadLocalHolder.getCurrentUser().getUnitId());
	}
	
	public void privilegeStrategySql(MapQuery mapQuery){
		String sql  = mapQuery.getSql();
		if(!sql.contains("@privilege_strategy_sql")){
			return;
		}
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		PriUnit unitEntity = (PriUnit)unitService.get(PriUnit.class,unitOid);
		if(unitEntity==null){
			return;
		}
		String strategySql = "";
		String hierarchy = unitEntity.getHierarchy();
		if(hierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())){//施工单位
			strategySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())){//监理单位
			strategySql = " and supervision_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"') and approve_status != 0";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())){//检测单位
			strategySql = " and detection_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.project_unit.getHierarchy())){//建设单位
			strategySql = " and project_oid in (select distinct t.project_oid from daq_implement_scope_ref t inner join(select oid from pri_unit p inner join "
					+ "(select hierarchy from pri_unit where oid='"+unitOid+"') pp on p.hierarchy like pp.hierarchy||'%' where p.active=1) pu on pu.oid = t.unit_oid)";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supplier.getHierarchy())){//厂商
			strategySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else{
			strategySql = " and create_user_id='"+ThreadLocalHolder.getCurrentUserId()+"'";
		}
		String sqlTemp = sql.substring(0,sql.lastIndexOf("@privilege_strategy_sql")).trim();
		if(sqlTemp.endsWith("and") || sqlTemp.endsWith("where")){
			strategySql = strategySql.replaceFirst(" and", "");
		}
		sql = sql.replaceAll("@privilege_strategy_sql", strategySql);
		mapQuery.setSql(sql);
		
	}
	
	/**
	  * <p>功能描述：。</p>
	  * <p> 雷凯。</p>	
	  * @param query
	  * @param dataAuthoritySql
	  * @since JDK1.8。
	  * <p>创建日期:2018年7月13日 下午3:51:42。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void injectDataAuthoritySql(BaseJavaQuery query,String dataAuthoritySql){
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		PriUnit unitEntity = (PriUnit)unitService.get(PriUnit.class,unitOid);
		if(unitEntity==null){
			dataAuthoritySql = "";
			return;
		}
		String hierarchy = unitEntity.getHierarchy();
		if(hierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())){//施工单位
			dataAuthoritySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())){//监理单位
			dataAuthoritySql = " and supervision_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"') and approve_status != 0";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())){//检测单位
			dataAuthoritySql = " and detection_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.project_unit.getHierarchy())){//建设单位
			dataAuthoritySql = " and project_oid in (select distinct t.project_oid from daq_implement_scope_ref t inner join(select oid from pri_unit p "
					+ "inner join (select hierarchy from pri_unit where oid='"+unitOid+"') pp on p.hierarchy like pp.hierarchy||'%' where p.active=1) pu on pu.oid = t.unit_oid)";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supplier.getHierarchy())){//厂商
			dataAuthoritySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else{
			dataAuthoritySql = " and create_user_id='"+ThreadLocalHolder.getCurrentUserId()+"'";
		}
		query.setDataAuthoritySql(dataAuthoritySql);
	}
	
	public void selectPrivilegeStrategySql(TempMapQuery mapQuery){
		String sql  = mapQuery.getSql();
		if(!sql.contains("@privilege_strategy_sql")){
			return;
		}
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		PriUnit unitEntity = (PriUnit)unitService.get(PriUnit.class,unitOid);
		if(unitEntity==null){
			return;
		}
		String strategySql = "";
		String hierarchy = unitEntity.getHierarchy();
		if(hierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())){//施工单位
			strategySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.project_unit.getHierarchy())){//建设单位
			strategySql = " and project_oid in (select distinct t.project_oid from daq_implement_scope_ref t inner join(select oid from pri_unit p inner join "
					+ "(select hierarchy from pri_unit where oid='"+unitOid+"') pp on p.hierarchy like pp.hierarchy||'%' where p.active=1) pu on pu.oid = t.unit_oid)";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supplier.getHierarchy())){//厂商
			strategySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else{
			strategySql = " and create_user_id='"+ThreadLocalHolder.getCurrentUserId()+"'";
		}
		String sqlTemp = sql.substring(0,sql.lastIndexOf("@privilege_strategy_sql")).trim();
		if(sqlTemp.endsWith("and") || sqlTemp.endsWith("where")){
			strategySql = strategySql.replaceFirst(" and", "");
		}
		sql = sql.replaceAll("@privilege_strategy_sql", strategySql);
		mapQuery.setSql(sql);
		
	}
	
}
