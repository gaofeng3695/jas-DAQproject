package cn.jasgroup.jasframework.acquisitiondata.scope.tenders.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.scope.tenders.dao.TendersScopeDao;
import cn.jasgroup.jasframework.acquisitiondata.scope.tenders.dao.entity.TendersScope;
import cn.jasgroup.jasframework.acquisitiondata.scope.tenders.scopeenum.ScopeEnum;
import cn.jasgroup.jasframework.base.service.BaseService;
import cn.jasgroup.jasframework.security.controller.data.json.TreeItem;

@Service
@Transactional
public class TendersScopeService extends BaseService{
	
	@Resource
	private TendersScopeDao tendersScopeDao;
	
	/***
	  * <p>功能描述：保存标段与范围的关联关系。</p>
	  * <p> 雷凯。</p>	
	  * @param dataList
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月19日 下午4:25:18。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public boolean saveRef(String tendersOid,List<TendersScope> dataList){
		try {
			this.tendersScopeDao.deleteByTendersOid(tendersOid);
			if(dataList==null || dataList.size()==0){
				return true;
			}
			List<List<Object>> list = new ArrayList<List<Object>>();
			for (int i = 0; i < dataList.size(); i++) {
				List<Object> dataSub = new ArrayList<Object>();
				TendersScope entity = dataList.get(i);
				dataSub.add(0, entity.getOid());
				dataSub.add(1, entity.getPipelineOid());
				dataSub.add(2, tendersOid);
				dataSub.add(3, entity.getScopeOid());
				dataSub.add(4, entity.getScopeType());
				dataSub.add(5, entity.getCreateUserId());
				dataSub.add(6, entity.getCreateUserName());
				dataSub.add(7, entity.getCreateDatetime());
				dataSub.add(8, entity.getModifyUserId());
				dataSub.add(9, entity.getModifyUserName());
				dataSub.add(10, entity.getModifyDatetime());
				dataSub.add(11, entity.getActive());
				list.add(dataSub);
			}
			return tendersScopeDao.saveRef(tendersOid,list);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/***
	  * <p>功能描述：标段范围查询接口。</p>
	  * <p> 雷凯。</p>	
	  * @param tendersOid
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月19日 下午4:43:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public TreeItem getScopeDataTree(String tendersOid){
		List<Map<String,Object>> dataList = this.tendersScopeDao.getScopeDataTree(tendersOid);
		List<Map<String,Object>> tendersScopeRefList = this.tendersScopeDao.getTendersScopeRef(tendersOid);
		TreeItem rootItem = new TreeItem();
		for(Map<String,Object> map : dataList){
			Object parentOid = map.get("parent_oid");
			String type = map.get("type")!=null?map.get("type").toString():null;
			if(parentOid==null && type.equals("-1")){
				rootItem.setId(map.get("oid").toString());
				rootItem.setText(map.get("name").toString());
				Map<String,String> attributes = new HashMap<>();
				attributes.put("type", type);
				rootItem.setAttributes(attributes);
				break;
			}
		}
		List<TreeItem> children = getChildren(rootItem.getId(),dataList,tendersScopeRefList);
		rootItem.setChildren(children);
		return rootItem;
	}
	/***
	  * <p>功能描述：根据parentId封装管线信息及下级工程分类的节点信息。</p>
	  * <p> 雷凯。</p>	
	  * @param parentId 父节点id
	  * @param dataList 范围数据列表
	  * @param dataRefList 标段与范围的关系列表
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月20日 上午11:00:32。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	private List<TreeItem> getChildren(String parentId,List<Map<String,Object>>dataList,List<Map<String,Object>>dataRefList){
		if(parentId==null || dataList==null || dataList.size()==0){
			return null;
		}
		List<TreeItem> childrenItem =  new ArrayList<>();
		for(Map<String,Object> map : dataList){
			String parentOid = map.get("parent_oid")!=null?map.get("parent_oid").toString():null;
			String type = map.get("type").toString();
			if(parentOid!=null && parentOid.equals(parentId) && type.equals("0")){
				TreeItem item = new TreeItem();
				item.setId(map.get("oid").toString());
				item.setText(map.get("name").toString());
				Map<String,String> attributes = new HashMap<>();
				attributes.put("type", type);
				item.setAttributes(attributes);
				
				TreeItem segmentItem = new TreeItem("1",ScopeEnum.segment_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
				TreeItem crossItem =  new TreeItem("2",ScopeEnum.cross_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
				TreeItem pipeStationItem =  new TreeItem("3",ScopeEnum.pipe_station_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
				TreeItem maintenanceRoadItem =  new TreeItem("4",ScopeEnum.maintenance_road_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
				TreeItem powerLineItem =  new TreeItem("5",ScopeEnum.power_line_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
				getScopeChildren(item.getId(), dataList,dataRefList,segmentItem,crossItem,pipeStationItem,maintenanceRoadItem,powerLineItem);
				List<TreeItem> scopeDataChildre = new ArrayList<>();
				scopeDataChildre.add(segmentItem);
				scopeDataChildre.add(crossItem);
				scopeDataChildre.add(pipeStationItem);
				scopeDataChildre.add(maintenanceRoadItem);
				scopeDataChildre.add(powerLineItem);
				item.setChildren(scopeDataChildre);
				childrenItem.add(item);
			}
		}
		return childrenItem;
	}
	/***
	 * <p>功能描述： 根据父节点Id封装范围节点。</p>
	  * <p> 雷凯。</p>	
	  * @param parentId 父节点id
	  * @param dataList 范围数据列表
	  * @param dataRefList 标段与范围的关系列表
	  * @param segmentItem 线路段节点
	  * @param crossItem 穿跨越节点
	  * @param pipeStationItem 站场/阀室节点
	  * @param maintenanceRoadItem 伴行道路节点
	  * @param powerLineItem 外电节点
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月20日 上午11:10:26。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	private void getScopeChildren(String parentId,List<Map<String,Object>> dataList,List<Map<String,Object>>dataRefList,TreeItem segmentItem,TreeItem crossItem,TreeItem pipeStationItem,TreeItem maintenanceRoadItem,TreeItem powerLineItem){

		for(Map<String,Object> map : dataList){
			String type = map.get("type").toString();
			String parentOid = map.get("parent_oid")!=null?map.get("parent_oid").toString():null;
			
			if(parentOid!=null && parentOid.equals(parentId)){
				switch (type) {
				case "1":
					setProvinceItem(segmentItem,map,dataRefList);
					break;
				case "2":
					setProvinceItem(crossItem,map,dataRefList);
					break;
				case "3":
					setProvinceItem(pipeStationItem, map,dataRefList);
					break;
				case "4":
					setProvinceItem(maintenanceRoadItem, map,dataRefList);
					break;
				case "5":
					setProvinceItem(powerLineItem, map,dataRefList);
					break;
				}
			}
		}
	}
	/***
	  * <p>功能描述：根据省份划分节点。</p>
	  * <p> 雷凯。</p>	
	  * @param parentItem 父节点
	  * @param map 当前节点
	  * @param dataRefList 关联数据列表
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月20日 上午11:12:26。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	private void setProvinceItem(TreeItem parentItem,Map<String,Object> map,List<Map<String,Object>>dataRefList){
		TreeItem item = new TreeItem();
		if(dataRefList!=null && dataRefList.size()>0){
			Map<String,Object> flagMap = new HashMap<String,Object>();
			flagMap.put("scope_oid", map.get("oid"));
			if(dataRefList.contains(flagMap)){
				item.setChecked(true);
			}
		}
		String type = map.get("type").toString();
		item.setId(map.get("oid").toString());
		item.setText(map.get("name").toString());
		Map<String,String> attributes = new HashMap<>();
		attributes.put("type", type);
		attributes.put("pipelineOid", map.get("parent_oid").toString());
		item.setAttributes(attributes);
		
		List<TreeItem> childrenItem = parentItem.getChildren();
		boolean flag = false;
		if(childrenItem!=null && childrenItem.size()>0){
			for(TreeItem obj:childrenItem){
				String province = map.get("province")!=null?map.get("province").toString():"";
				if(obj.getId().equals(province)){
					obj.getChildren().add(item);
					flag = true;
					break;
				}
			}
			
		}
		if(!flag){
			TreeItem provinceItem = new TreeItem();
			provinceItem.setId(map.get("province").toString());
			provinceItem.setText(map.get("province_name").toString());
			
			List<TreeItem> children = new ArrayList<>();
			children.add(item);
			provinceItem.setChildren(children);
			childrenItem.add(provinceItem);
			parentItem.setChildren(childrenItem);
		}
	}
}
