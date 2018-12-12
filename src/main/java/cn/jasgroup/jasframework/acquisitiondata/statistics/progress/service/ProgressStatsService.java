package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsQueryBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.dao.ProgressStatsDao;

@Service
@Transactional
public class ProgressStatsService {
	
	@Resource(name="progressStatsDao")
	private ProgressStatsDao progressStatsDao;

	/**
	 * <p>功能描述：项目-各工序分项目对比统计（km）。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月10日 下午5:02:24。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getEachItemLengthStats(List<String> projectOids, String date) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oids查询项目名称
		List<Map<String, String>> projectList = progressStatsDao.getProjectList(projectOids);
		//焊接
		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldLengthStats(projectOids,date);
		//封装焊接工序的返回值
		setListItems(list,weldList,"weld",projectList);
		//补口
		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchLengthStats(projectOids,date);
		//封装补口工序的返回值
		setListItems(list,petchList,"patch",projectList);
		//管沟回填
		List<ProgressStatsQueryBo> backFillList = progressStatsDao.getBackFillLengthStats(projectOids,date);
		//封装管沟回填的返回值
		setListItems(list,backFillList,"lay_pipe_trench_backfill",projectList);
		//地貌恢复
		List<ProgressStatsQueryBo> landRestorationList = progressStatsDao.getLandRestorationLengthStats(projectOids,date);
		//封装地貌恢复的返回值
		setListItems(list,landRestorationList,"lay_land_restoration",projectList);
		return list;
	}
	
	/**
	 * <p>功能描述：封装各工序的返回值。</p>
	  * <p> 葛建。</p>	
	  * @param resultList 
	  * @param dataList 
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午1:59:28。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public void setListItems(List<Map<String, Object>> resultList, List<ProgressStatsQueryBo> dataList,String type, List<Map<String, String>> projectMap){
		Map<String, Object> map = new HashMap<>();
		//封装类型和名称
		switch (type) {
		case "weld":
			map.put("type", "weld");
			map.put("typeName", "焊接");
			break;
		case "patch":
			map.put("type", "patch");
			map.put("typeName", "补口");
			break;
		case "lay_pipe_trench_backfill":
			map.put("type", "lay_pipe_trench_backfill");
			map.put("typeName", "管沟回填");
			break;
		case "lay_land_restoration":
			map.put("type", "lay_land_restoration");
			map.put("typeName", "地貌恢复");
			break;
		case "detectionRayList":
			map.put("type", "detectionRayList");
			map.put("typeName", "射线检测");
			break;
		case "measured_result":
			map.put("type", "measured_result");
			map.put("typeName", "焊口测量");
			break;
		default:
			break;
		}
		//若dataList不为空，取对应数据封装
		if (dataList.size() > 0) {
			Double[] statsResult = new Double[dataList.size()];
			String[] projectName = new String[dataList.size()];
			String[] projectOid = new String[dataList.size()];
			for (int i = 0; i < dataList.size(); i++) {
				statsResult[i] = Double.parseDouble(dataList.get(i).getStatsResult().toString());
				projectName[i] = (String) dataList.get(i).getProjectName();
				projectOid[i] = (String) dataList.get(i).getProjectOid();
			}
			map.put("statsResult", statsResult);
			map.put("projectName", projectName);
			map.put("projectOids", projectOid);
			resultList.add(map);
		} else {
			//若dataList为空，封装项目oid和名称，将对应的长度封装为0
			Double[] statsResult = new Double[projectMap.size()];
			String[] projectName = new String[projectMap.size()];
			String[] projectOid = new String[projectMap.size()];
			for (int i = 0; i < projectMap.size(); i++) {
				statsResult[i] = 0.0;
				projectName[i] = projectMap.get(i).get("projectName");
				projectOid[i] = projectMap.get(i).get("oid");
			}
			map.put("statsResult", statsResult);
			map.put("projectName", projectName);
			map.put("projectOids", projectOid);
			resultList.add(map);
		}
	}

	
	/**
	 * <p>功能描述：项目-各工序分项目对比统计（口数）。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午2:57:19。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getEachItemCountStats(List<String> projectOids, String date) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oids查询项目名称
		List<Map<String, String>> projectList = progressStatsDao.getProjectList(projectOids);
		//焊接
		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldCountStats(projectOids,date);
		//封装焊接工序的返回值
		setListItems(list,weldList,"weld",projectList);
		//补口
		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchCountStats(projectOids,date);
		//封装补口工序的返回值
		setListItems(list,petchList,"petch",projectList);
		//射线检测
		List<ProgressStatsQueryBo> detectionRayList = progressStatsDao.getDetectionRayCountStats(projectOids,date);
		//封装射线检测工序的返回值
		setListItems(list,detectionRayList,"detection_ray",projectList);
		//射线检测
		List<ProgressStatsQueryBo> measuredResultList = progressStatsDao.getMeasuredResultCountStats(projectOids,date);
		//封装射线检测工序的返回值
		setListItems(list,measuredResultList,"measured_result",projectList);
		return list;
	}
}
