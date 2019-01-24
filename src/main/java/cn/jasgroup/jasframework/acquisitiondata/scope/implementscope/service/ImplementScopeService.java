package cn.jasgroup.jasframework.acquisitiondata.scope.implementscope.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.scope.implementscope.dao.ImplementScopeDao;
import cn.jasgroup.jasframework.acquisitiondata.scope.implementscope.dao.entity.ImplementScope;
import cn.jasgroup.jasframework.acquisitiondata.scope.tenders.scopeenum.ScopeEnum;
import cn.jasgroup.jasframework.base.service.BaseService;
import cn.jasgroup.jasframework.security.controller.data.json.TreeItem;

@Service
@Transactional
public class ImplementScopeService extends BaseService{
	
	@Resource
	private ImplementScopeDao implementScopeDao;
	
	/***
	  * <p>功能描述：获取实施范围树形数据。</p>
	  * <p> 雷凯。</p>	
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午10:27:57。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<TreeItem> getImplementScopeTree(String unitOid){
		List<Map<String,Object>> treeDataList = this.implementScopeDao.getImplementScopeTreeData();
		List<Map<String,Object>> dataRefList = this.implementScopeDao.getImplemntScopeRef(unitOid);
		List<TreeItem> resultTree = new ArrayList<TreeItem>();
		for(Map<String,Object> obj : treeDataList){
			String type = obj.get("type")!=null?obj.get("type").toString():"";
			if(obj.get("parent_oid")==null && type.equals("-2")){
				TreeItem item = new TreeItem();
				item.setId(obj.get("oid").toString());
				item.setText(obj.get("name").toString());
				Map<String,String> attributes = new HashMap<>();
				attributes.put("type", type);
				item.setAttributes(attributes);
				item.setChildren(getProjectChildren(obj.get("oid").toString(),null,treeDataList,dataRefList));
				resultTree.add(item);
			}else if(obj.get("parent_oid")==null && type.equals("6")){
				TreeItem item = new TreeItem();
				String id = obj.get("oid").toString();
				item.setId(id);
				item.setText(obj.get("name").toString());
				Map<String,String> attributes = new HashMap<>();
				attributes.put("type", type);
				attributes.put("projectOid", obj.get("oid").toString());
				item.setAttributes(attributes);
				item.setChildren(new ArrayList<TreeItem>());
				for(Map<String,Object> map:dataRefList){
					String projectrOid = map.get("project_oid")!=null?map.get("project_oid").toString():"";
					if(projectrOid.equals(id)){
						item.setChecked(true);
						break;
					}
				}
				resultTree.add(item);
			}
		}
		return resultTree;
	}
	/***
	 * <p>功能描述：根据项目Id,获取项目的子节点。</p>
	  * <p> 雷凯。</p>	
	  * @param parentId
	  * @param dataList
	  * @param dataRefList
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午11:30:44。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	private List<TreeItem> getProjectChildren(String parentId,String tendersOid,List<Map<String,Object>> dataList,List<Map<String,Object>> dataRefList){
		List<TreeItem> itemList = new ArrayList<TreeItem>();
		for(Map<String,Object> obj : dataList){
			String parentOid = obj.get("parent_oid")!=null?obj.get("parent_oid").toString():"";
			String this_tendersOid = obj.get("tenders_oid")!=null?obj.get("tenders_oid").toString():null;
			String type = obj.get("type")!=null?obj.get("type").toString():"";
			if(parentOid.equals(parentId) && type.equals("-1")){
				TreeItem item = new TreeItem();
				item.setId(obj.get("oid").toString());
				item.setText(obj.get("name").toString());
				Map<String,String> attributes = new HashMap<>();
				attributes.put("type", type);
				item.setAttributes(attributes);
				item.setChildren(getProjectChildren(obj.get("oid").toString(),obj.get("oid").toString(),dataList,dataRefList));
				itemList.add(item);
			}else if(parentOid.equals(parentId) && type.equals("0") && this_tendersOid!=null && tendersOid.equals(this_tendersOid)){
				TreeItem item = new TreeItem();
				item.setId(obj.get("oid").toString());
				item.setText(obj.get("name").toString());
				Map<String,String> attributes = new HashMap<>();
				attributes.put("type", type);
				item.setAttributes(attributes);
				item.setChildren(getPipelineChildren(obj.get("oid").toString(),obj.get("tenders_oid").toString(),dataList,dataRefList));
				itemList.add(item);
			}
		}
		return itemList;
	}
	/***
	  * <p>功能描述：根据管线Id,获取管线的子节点。</p>
	  * <p> 雷凯。</p>	
	  * @param parentId
	  * @param dataList
	  * @param dataRefList
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 上午11:30:56。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	private List<TreeItem> getPipelineChildren(String parentId,String tendersOid,List<Map<String,Object>> dataList,List<Map<String,Object>> dataRefList){
		List<TreeItem> itemList = new ArrayList<TreeItem>();
		TreeItem segmentItem = new TreeItem("1",ScopeEnum.segment_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
		TreeItem crossItem =  new TreeItem("2",ScopeEnum.cross_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
		TreeItem pipeStationItem =  new TreeItem("3",ScopeEnum.pipe_station_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
		TreeItem powerLineItem =  new TreeItem("5",ScopeEnum.power_line_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
		TreeItem maintenanceRoadItem =  new TreeItem("4",ScopeEnum.maintenance_road_scope.getName(),"0",false,new ArrayList<TreeItem>(),null);
		boolean isExistSegment = false;
		boolean isExistCross = false;
		boolean isExistPipeStation = false;
		boolean isExistMaintenanceRoad = false;
		boolean isExistpowerLine = false;
		for(Map<String,Object> obj : dataList){
			String type = obj.get("type")!=null?obj.get("type").toString():"";
			String parentOid = obj.get("parent_oid")!=null?obj.get("parent_oid").toString():"";
			String thisTendersOid = obj.get("tenders_oid")!=null?obj.get("tenders_oid").toString():"";
			if(parentOid.equals(parentId) && thisTendersOid.equals(tendersOid)){
				switch (type) {
				case "1":
					setProvinceItem(segmentItem,tendersOid,obj,dataRefList);
					isExistSegment =true;
					break;
				case "2":
					setProvinceItem(crossItem,tendersOid,obj,dataRefList);
					isExistCross = true;
					break;
				case "3":
					setProvinceItem(pipeStationItem,tendersOid,obj,dataRefList);
					isExistPipeStation = true;
					break;
				case "4":
					setProvinceItem(maintenanceRoadItem,tendersOid,obj,dataRefList);
					isExistMaintenanceRoad = true;
					break;
				case "5":
					setProvinceItem(powerLineItem,tendersOid,obj,dataRefList);
					isExistpowerLine = true;
					break;
				}
			}
		}
		if(isExistSegment){
			itemList.add(segmentItem);
		}
		if(isExistCross){
			itemList.add(crossItem);
		}
		if(isExistPipeStation){
			itemList.add(pipeStationItem);
		}
		if(isExistMaintenanceRoad){
			itemList.add(maintenanceRoadItem);
		}
		if(isExistpowerLine){
			itemList.add(powerLineItem);
		}
		return itemList;
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
	private void setProvinceItem(TreeItem parentItem,String tendersOid,Map<String,Object> map,List<Map<String,Object>>dataRefList){
		TreeItem item = new TreeItem();
		if(dataRefList!=null && dataRefList.size()>0){
			Map<String,Object> flagMap = new HashMap<String,Object>();
			flagMap.put("scope_oid", map.get("oid"));
			flagMap.put("tenders_oid", tendersOid);
			flagMap.put("pipeline_oid", map.get("parent_oid").toString());
			flagMap.put("project_oid", map.get("project_oid").toString());
			if(dataRefList.contains(flagMap)){
				item.setChecked(true);
			}
		}
		String type = map.get("type").toString();
		item.setId(map.get("oid").toString());
		item.setText(map.get("name").toString());
		Map<String,String> attributes = new HashMap<>();
		attributes.put("type", type);
		attributes.put("projectOid", map.get("project_oid").toString());
		attributes.put("pipelineOid", map.get("parent_oid").toString());
		attributes.put("tendersOid", tendersOid);
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
	/***
	  * <p>功能描述：施工单位与实施范围关联关系保存。</p>
	  * <p> 雷凯。</p>	
	  * @param unitOid
	  * @param dataList
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年6月22日 下午3:24:40。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public boolean saveRef(String unitOid,List<ImplementScope> dataList){
		try {
			this.implementScopeDao.deleteRef(unitOid);
			if(dataList==null || dataList.size()==0){
				return true;
			}
			List<List<Object>> list = new ArrayList<>();
			for(ImplementScope entity : dataList){
				List<Object> dataSub = new ArrayList<Object>();
				dataSub.add(0, entity.getOid());
				dataSub.add(1, unitOid);
				dataSub.add(2, entity.getProjectOid());
				dataSub.add(3, entity.getPipelineOid());
				dataSub.add(4, entity.getTendersOid());
				dataSub.add(5, entity.getScopeOid());
				dataSub.add(6, entity.getScopeType());
				dataSub.add(7, entity.getCreateUserId());
				dataSub.add(8, entity.getCreateUserName());
				dataSub.add(9, entity.getCreateDatetime());
				dataSub.add(10, entity.getModifyUserId());
				dataSub.add(11, entity.getModifyUserName());
				dataSub.add(12, entity.getModifyDatetime());
				dataSub.add(13, entity.getActive());
				list.add(dataSub);
			}
			return this.implementScopeDao.saveRef(list);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
