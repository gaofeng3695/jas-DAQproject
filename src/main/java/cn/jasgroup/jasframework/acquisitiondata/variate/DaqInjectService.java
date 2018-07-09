package cn.jasgroup.jasframework.acquisitiondata.variate;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.base.data.BaseData;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
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
			dataAuthoritySql = " and supervision_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else if(hierarchy.startsWith(UnitHierarchyEnum.detection_unit.getHierarchy())){//检测单位
			dataAuthoritySql = " and detection_unit in (select uu.oid from pri_unit u left join pri_unit uu on uu.hierarchy like u.hierarchy||'%' where u.oid='"+unitOid+"')";
		}else{
			dataAuthoritySql = "";
		}
		query.setDataAuthoritySql(dataAuthoritySql);
	}
}
