package cn.jasgroup.jasframework.acquisitiondata.scope.scopeManager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jasgroup.jasframework.acquisitiondata.scope.scopeManager.dao.entity.TenderScopeBo;
import cn.jasgroup.jasframework.acquisitiondata.scope.scopeManager.service.TenderScopeService;
import cn.jasgroup.jasframework.base.controller.BaseController;

/**
 * <p>功能描述：获取标段范围树。</p>
  * <p> 葛建。</p>	
  * @return
  * @since JDK1.8。
  * <p>创建日期:2018年6月20日 下午3:42:47。</p>
  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
 */
@RestController
@RequestMapping("daq/pipeline")
public class TenderScopeController extends BaseController {

	@Autowired
	private TenderScopeService TenderScopeService;

	/**
	 * 获取标段范围树
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getTenderScopeTree")
	public Object getScopeTree(HttpServletRequest request, HttpServletResponse response) {
		String tenderId = request.getParameter("tenderId");
		// 获取项目id
		String projectId = TenderScopeService.getProjectByTenderId(tenderId);
		// 通过项目id获取标段范围Bo
		List<TenderScopeBo> tenderScopeBoList = TenderScopeService.getBoByProjectId(projectId);
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
				setItem(item, bo.getOid(), bo.getName(), bo.getType(), bo.getTypeName());
				List<Map<String, Object>> childrenList = getPipelineById(bo.getOid(), tenderScopeBoList, typeMap);
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
	 * @return
	 */
	private List<Map<String, Object>> getPipelineById(String parentId, List<TenderScopeBo> tenderScopeBoList,
			Map<String, String> typeMap) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (TenderScopeBo bo : tenderScopeBoList) {
			// 根据项目过滤管线
			if (parentId.equals(bo.getParentOid())) {
				Map<String, Object> item = new HashMap<String, Object>();
				// 设置item的值
				setItem(item, bo.getOid(), bo.getName(), bo.getType(), bo.getTypeName());
				List<Map<String, Object>> childrenList = getChildrenById(bo.getOid(), tenderScopeBoList, typeMap);
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
	 * @return
	 */
	private List<Map<String, Object>> getChildrenById(String parentId, List<TenderScopeBo> tenderScopeBoList,
			Map<String, String> typeMap) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (String key : typeMap.keySet()) {
			Map<String, Object> item = new HashMap<String, Object>();
			// 设置item的值
			setItem(item, key, typeMap.get(key), key, typeMap.get(key));
			List<Map<String, Object>> childrenList = getProvinceById(parentId, tenderScopeBoList, key);
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
	 * @return
	 */
	private List<Map<String, Object>> getProvinceById(String parentId, List<TenderScopeBo> tenderScopeBoList,
			String parentType) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (TenderScopeBo bo : tenderScopeBoList) {
			// 通过管线id和五大类型过滤到对应的省份
			if (parentId.equals(bo.getParentOid()) && parentType.equals(bo.getType())) {
				Map<String, Object> item = new HashMap<String, Object>();
				// 设置item的值
				setItem(item, bo.getProvince(), bo.getProvinceName(), bo.getType(), bo.getTypeName());
				List<Map<String, Object>> childrenList = getDeviceById(bo.getOid(), bo.getProvince(), parentType,
						tenderScopeBoList);
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
	 * 获取省份下对应的设施细节
	 * 
	 * @param oid
	 *            省份对应数据的id
	 * @param type
	 *            父类type
	 * @param tenderScopeBoList
	 *            项目下所有数据
	 * @return
	 */
	private List<Map<String, Object>> getDeviceById(String oid, String provinceId, String parentType,
			List<TenderScopeBo> tenderScopeBoList) {
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		for (TenderScopeBo bo : tenderScopeBoList) {
			// 通过省份、oid和类型过滤对应的子类数据
			if (provinceId.equals(bo.getProvince()) && oid.equals(bo.getOid()) && parentType.equals(bo.getType())) {
				Map<String, Object> item = new HashMap<String, Object>();
				// 设置item的值
				setItem(item, bo.getOid(), bo.getName(), bo.getType(), bo.getTypeName());
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
			String typeNameField) {
		Map<String, Object> attributesMap = new HashMap<String, Object>();
		item.put("id", idField);
		item.put("text", textField);
		attributesMap.put("type", typeField);
		attributesMap.put("typeName", typeNameField);
		item.put("attributes", attributesMap);
	}

}