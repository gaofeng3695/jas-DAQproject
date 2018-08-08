package cn.jasgroup.jasframework.acquisitiondata.variate;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.framework.automation.query.MapQuery;
import cn.jasgroup.jasframework.base.data.BaseData;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import cn.jasgroup.jasframework.base.data.BaseQuery;
import cn.jasgroup.jasframework.security.dao.UnitDao;
import cn.jasgroup.jasframework.security.dao.entity.PriUnit;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

@Service
@Transactional
public class DaqInjectService {
	
	@Resource
	private UnitDao unitDao;
	
	public void injectCurrentUnitId(BaseData baseData){
		baseData.setValue("current_unit_id", ThreadLocalHolder.getCurrentUser().getUnitId());
	}
	
	public void privilegeStrategySql(MapQuery mapQuery){
		String sql  = mapQuery.getSql();
		if(!sql.contains("@privilege_strategy_sql")){
			return;
		}
		String unitOid = ThreadLocalHolder.getCurrentUser().getUnitId();
		List<PriUnit> unitEntityList = unitDao.findUnitByID(unitOid);
		if(unitEntityList==null || unitEntityList.size()==0){
			return;
		}
		String strategySql = "";
		PriUnit unitEntity = unitEntityList.get(0);
		String hierarchy = unitEntity.getHierarchy();
		if(hierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())){//施工单位
			strategySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())){//监理单位
			strategySql = " and supervision_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"') and approve_status != 0";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())){//检测单位
			strategySql = " and detection_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.project_unit.getHierarchy())){//建设单位
			strategySql = " ";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supplier.getHierarchy())){//厂商
			strategySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else{
			strategySql = " and create_user_id='"+ThreadLocalHolder.getCurrentUserId()+"'";
		}
		String sqlTemp = sql.substring(0,sql.lastIndexOf("@privilege_strategy_sql")).trim();
		if(sqlTemp.endsWith("and")){
			strategySql = strategySql.replace(" and", "");
		}
		sql = sql.replace("@privilege_strategy_sql", strategySql);
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
		List<PriUnit> unitEntityList = unitDao.findUnitByID(unitOid);
		if(unitEntityList==null || unitEntityList.size()==0){
			dataAuthoritySql = "";
			return;
		}
		PriUnit unitEntity = unitEntityList.get(0);
		String hierarchy = unitEntity.getHierarchy();
		if(hierarchy.startsWith(UnitHierarchyEnum.construct_unit.getHierarchy())){//施工单位
			dataAuthoritySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supervision_unit.getHierarchy())){//监理单位
			dataAuthoritySql = " and supervision_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"') and approve_status != 0";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())){//检测单位
			dataAuthoritySql = " and detection_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.project_unit.getHierarchy())){//建设单位
			dataAuthoritySql = " ";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.supplier.getHierarchy())){//厂商
			dataAuthoritySql = " and construct_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else{
			dataAuthoritySql = " and create_user_id='"+ThreadLocalHolder.getCurrentUserId()+"'";
		}
		query.setDataAuthoritySql(dataAuthoritySql);
	}
	
}
