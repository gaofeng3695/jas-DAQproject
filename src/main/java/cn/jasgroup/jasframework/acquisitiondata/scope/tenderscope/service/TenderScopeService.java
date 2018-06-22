package cn.jasgroup.jasframework.acquisitiondata.scope.tenderscope.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.scope.tenderscope.dao.TenderScopeDao;
import cn.jasgroup.jasframework.acquisitiondata.scope.tenderscope.dao.entity.TenderScopeBo;

@Service
@Transactional
public class TenderScopeService {

	@Autowired
	private TenderScopeDao tenderScopeDAO;

	/**
	 * 获取标段范围树
	 * 
	 * @param tenderId
	 *            标段id
	 * @return
	 */
	public List<Map<String, Object>> getTenderScopeTree(String tenderId) {
		// 获取范围id
		List<Map<String,Object>> scopeIdList = tenderScopeDAO.getScopeIdByTenderId(tenderId);
		// 获取项目id
		String projectId = tenderScopeDAO.getProjectByTenderId(tenderId);
		// 通过项目id获取标段范围BoList
		List<TenderScopeBo> tenderScopeBoList = tenderScopeDAO.getBoByProjectId(projectId);
		// 用于封装返回数据
		List<Map<String, Object>> itemsList = new ArrayList<Map<String, Object>>();
		if (tenderScopeBoList == null) {
			return null;
		}
		// 五种类型初始化数据
		Map<String, String> typeMap = new HashMap<>();
		typeMap.put("1", "线路段");
		typeMap.put("2", "穿跨越");
		typeMap.put("3", "站场/阀室");
		typeMap.put("4", "伴行道路");
		typeMap.put("5", "外供电线路");

		for (TenderScopeBo bo : tenderScopeBoList) {
			// 通过type=-1过滤出根节点（项目）
			if ("-1".equals(bo.getType())) {
				Map<String, Object> item = new HashMap<String, Object>();
				// 设置item的值
				setItem(item, bo.getOid(), bo.getName(), bo.getType(), "");
				List<Map<String, Object>> childrenList = getPipelineById(bo.getOid(), tenderScopeBoList, typeMap,
						scopeIdList);
				if (childrenList.size() > 0) {
					item.put("state", "closed");
					item.put("children", childrenList);
				}
				itemsList.add(item);
				break; // 一个标段只对应一个项目
			}
		}
		return itemsList;
	}

	/**
	 * 获取项目下的管线
	 * 
	 * @param parentId
	 *            项目id
	 * @param tenderScopeBoList
	 *            项目下所有的数据
	 * @param typeMap
	 *            五种类型初始化数据
	 * @param scopeIdList
	 * @return
	 */
	private List<Map<String, Object>> getPipelineById(String parentId, List<TenderScopeBo> tenderScopeBoList,
			Map<String, String> typeMap, List<Map<String, Object>> scopeIdList) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (TenderScopeBo bo : tenderScopeBoList) {
			// 根据项目过滤管线
			if (parentId.equals(bo.getParentOid())) {
				Map<String, Object> item = new HashMap<String, Object>();
				// 设置item的值
				setItem(item, bo.getOid(), bo.getName(), bo.getType(), bo.getOid());
				List<Map<String, Object>> childrenList = getChildrenById(bo.getOid(), tenderScopeBoList, typeMap,
						scopeIdList);
				if (childrenList.size() > 0) {
					item.put("state", "closed");
					item.put("children", childrenList);
				}
				items.add(item);
			}
		}
		return items;
	}

	/**
	 * 获取管线的子级(固定的五个)
	 * 
	 * @param parentId
	 *            管线id
	 * @param tenderScopeBoList
	 *            项目下所有数据
	 * @param typeMap
	 *            五种类型初始化数据
	 * @param scopeIdList
	 * @return
	 */
	private List<Map<String, Object>> getChildrenById(String parentId, List<TenderScopeBo> tenderScopeBoList,
			Map<String, String> typeMap, List<Map<String, Object>> scopeIdList) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (String key : typeMap.keySet()) {
			Map<String, Object> item = new HashMap<String, Object>();
			// 设置item的值
			setItem(item, key, typeMap.get(key), key, parentId);
			List<Map<String, Object>> childrenList = getProvinceById(parentId, tenderScopeBoList, key, scopeIdList);
			if (childrenList.size() > 0) {
				item.put("state", "closed");
				item.put("children", childrenList);
			}
			items.add(item);
		}
		return items;
	}

	/**
	 * 获取设施下对应的省份
	 * 
	 * @param parentId
	 *            管线id
	 * @param tenderScopeBoList
	 *            项目下所有数据
	 * @param parentType
	 *            父类type
	 * @param scopeIdList
	 * @return
	 */
	private List<Map<String, Object>> getProvinceById(String parentId, List<TenderScopeBo> tenderScopeBoList,
			String parentType, List<Map<String, Object>> scopeIdList) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (TenderScopeBo bo : tenderScopeBoList) {
			// 通过管线id和五大类型过滤到对应的省份
			if (parentId.equals(bo.getParentOid()) && parentType.equals(bo.getType())) {
				/*Map<String, Object> item = new HashMap<String, Object>();
				// 设置item的值
				setItem(item, bo.getProvince(), bo.getProvinceName(), bo.getType(), bo.getTypeName());
				List<Map<String, Object>> childrenList = getDeviceById(bo.getOid(), bo.getProvince(), parentType,
						tenderScopeBoList, scopeIdList);
				if (childrenList.size() > 0) {
					item.put("state", "closed");
					item.put("children", childrenList);
				}
				items.add(item);*/
				boolean isExist = false;
				if(items.size()>0){
					for(Map<String, Object> obj:items){
						String province = obj.get("id").toString();
						if(province.equals(bo.getProvince())){
							isExist = true;
							Map<String, Object> item = new HashMap<String, Object>();
							// 设置item的值
							setItemAndCheck(item, bo.getOid(), bo.getName(), bo.getType(), bo.getParentOid(), bo.getOid(), scopeIdList);
							((List<Map<String, Object>>)obj.get("children")).add(item);
						}
					}
				}
				if(!isExist){	
					Map<String, Object> item = new HashMap<String, Object>();
					// 设置item的值
					setItem(item, bo.getProvince(), bo.getProvinceName(), bo.getType(), bo.getParentOid());
					List<Map<String, Object>> childrenList = getDeviceById(bo.getOid(), bo.getProvince(), parentType,
							tenderScopeBoList, scopeIdList);
					if (childrenList.size() > 0) {
						item.put("state", "closed");
						item.put("children", childrenList);
					}
					items.add(item);
				}
			}
		}
		return items;
	}

	/**
	 * 获取省份下对应的设施细节
	 * 
	 * @param oid
	 *            省份对应数据的id
	 * @param type
	 *            父类type
	 * @param tenderScopeBoList
	 *            项目下所有数据
	 * @param scopeIdList
	 * @return
	 */
	private List<Map<String, Object>> getDeviceById(String oid, String provinceId, String parentType,
			List<TenderScopeBo> tenderScopeBoList, List<Map<String, Object>> scopeIdList) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (TenderScopeBo bo : tenderScopeBoList) {
			// 通过省份、oid和类型过滤对应的子类数据
			if (provinceId.equals(bo.getProvince()) && oid.equals(bo.getOid()) && parentType.equals(bo.getType())) {
				Map<String, Object> item = new HashMap<String, Object>();
				// 设置item的值
				setItemAndCheck(item, bo.getOid(), bo.getName(), bo.getType(), bo.getTypeName(), bo.getOid(), scopeIdList);
				items.add(item);
			}
		}
		return items;
	}

	/**
	 * 为item设置id,text和attributes属性
	 * 
	 * @param item
	 *            封装对象
	 * @param idField
	 *            id属性的值
	 * @param textField
	 *            text属性的值
	 * @param typeField
	 *            attributes中type属性的值
	 * @param typeNameField
	 *            attributes中typeName属性的值
	 */
	public void setItem(Map<String, Object> item, String idField, String textField, String typeField,
			String pipelineOid) {
		Map<String, Object> attributesMap = new HashMap<String, Object>();
		item.put("id", idField);
		item.put("text", textField);
		attributesMap.put("type", typeField);
		attributesMap.put("pipelineOid", pipelineOid);
		item.put("attributes", attributesMap);
	}
	
	
	public void setItemAndCheck(Map<String, Object> item, String idField, String textField, String typeField,
			String pipelineOid, String BoId, List<Map<String, Object>> scopeIdList) {
		Map<String, Object> attributesMap = new HashMap<String, Object>();
		Map<String, Object> scopeIdMap = new HashMap<String, Object>();
		scopeIdMap.put("scope_oid", BoId);
		item.put("id", idField);
		item.put("text", textField);
		if (scopeIdList.contains(scopeIdMap)) {
			attributesMap.put("checked", true);
		}else {
			attributesMap.put("checked", false);
		}
//		for (LinkedCaseInsensitiveMap map : scopeIdList) {
//			if (map.get("scope_oid").equals(BoId)) {
//				attributesMap.put("checked", true);
//				break;
//			}else {
//				attributesMap.put("checked", false);
//			}
//		}
		attributesMap.put("type", typeField);
		attributesMap.put("pipelineOid", pipelineOid);
		item.put("attributes", attributesMap);
	}

}