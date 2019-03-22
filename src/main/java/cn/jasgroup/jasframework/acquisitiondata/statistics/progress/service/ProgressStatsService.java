package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsUtils;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsQueryBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsResultBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.dao.ProgressStatsDao;

@Service
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
	public List<Map<String, Object>> getEachItemLengthStatsByProject(List<String> projectOids, String date) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oids查询项目名称
		List<Map<String, String>> projectList = progressStatsDao.getProjectList(projectOids);
		//焊接
		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldLengthStatsByProject(projectOids,date);
		//封装焊接工序的返回值
		setListItemsForProject(list,weldList,"weld",projectList);
		//补口
		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchLengthStatsByProject(projectOids,date);
		//封装补口工序的返回值
		setListItemsForProject(list,petchList,"patch",projectList);
		//管沟回填
		List<ProgressStatsQueryBo> backFillList = progressStatsDao.getBackFillLengthStatsByProject(projectOids,date);
		//封装管沟回填的返回值
		setListItemsForProject(list,backFillList,"lay_pipe_trench_backfill",projectList);
		//地貌恢复
		List<ProgressStatsQueryBo> landRestorationList = progressStatsDao.getLandRestorationLengthStatsByProject(projectOids,date);
		//封装地貌恢复的返回值
		setListItemsForProject(list,landRestorationList,"lay_land_restoration",projectList);
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
	public void setListItemsForProject(List<Map<String, Object>> resultList, List<ProgressStatsQueryBo> dataList,String type, List<Map<String, String>> projectList){
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
		case "detection_ray":
			map.put("type", "detection_ray");
			map.put("typeName", "射线检测");
			break;
		case "measured_result":
			map.put("type", "measured_result");
			map.put("typeName", "焊口测量");
			break;
		default:
			break;
		}
		Double[] statsResult = new Double[projectList.size()];
		Arrays.fill(statsResult, 0.0);
		String[] projectNames = new String[projectList.size()];
		String[] projectOids = new String[projectList.size()];
		if (projectList.size() > 0) {
			for (int i = 0; i < projectList.size(); i++) {
				projectNames[i] = projectList.get(i).get("projectName");
				projectOids[i] = projectList.get(i).get("oid");
			}
			if (dataList.size() > 0) {
				for (int j = 0; j < dataList.size(); j++) {
					int index = getIndex(projectOids, dataList.get(j).getProjectOid());
					if (index != -1) {
						statsResult[index] = Double.parseDouble(dataList.get(j).getStatsResult().toString());
					}
				}
			}
		}
		map.put("statsResult", statsResult);
		map.put("tendersNames", projectNames);
		map.put("tendersOids", projectOids);
		resultList.add(map);
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
	public List<Map<String, Object>> getEachItemCountStatsByProject(List<String> projectOids, String date) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oids查询项目名称
		List<Map<String, String>> projectList = progressStatsDao.getProjectList(projectOids);
		//焊接
		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldCountStatsByProject(projectOids,date);
		//封装焊接工序的返回值
		setListItemsForProject(list,weldList,"weld",projectList);
		//补口
		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchCountStatsByProject(projectOids,date);
		//封装补口工序的返回值
		setListItemsForProject(list,petchList,"patch",projectList);
		//射线检测
		List<ProgressStatsQueryBo> detectionRayList = progressStatsDao.getDetectionRayCountStatsByProject(projectOids,date);
		//封装射线检测工序的返回值
		setListItemsForProject(list,detectionRayList,"detection_ray",projectList);
		//中线测量
		List<ProgressStatsQueryBo> measuredResultList = progressStatsDao.getMeasuredResultCountStatsByProject(projectOids,date);
		//封装中线测量工序的返回值
		setListItemsForProject(list,measuredResultList,"measured_result",projectList);
		return list;
	}

	/**
	 * <p>功能描述：标段-工序分标段分月累计完成统计（km）。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param procedure
	  * @param beginMonth
	  * @param endMonth
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月11日 下午6:06:51。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getCumulateStatsByProjectAndSingleItem(String projectOid, String procedure,
			String beginMonth, String endMonth) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//获取自开始月起到终止月的连续的月份数组
		Object[] monthArray = StatsUtils.getMonthArray(beginMonth, endMonth, "yyyy-MM");
		//根据项目oid查询下属标段及对应名称
		List<Map<String, String>> tendersList = progressStatsDao.getTendersList(projectOid);
		//根据项目，工艺，起止时间查询对应工艺的按标段的每月累计里程数
		List<ProgressStatsResultBo> dataList = progressStatsDao.getCumulateStatsByProjectAndSingleItem(projectOid, procedure, beginMonth, endMonth);
		if (tendersList.size() > 0) {
			//标段不为空,遍历标段封装数据
			for (int i = 0; i < tendersList.size(); i++) {
				//封装每标段数据
				Map<String, Object> map = new HashMap<>();
				map.put("tendersOid", tendersList.get(i).get("oid"));
				map.put("tendersName", tendersList.get(i).get("tendersName"));
				//创建指定长度且有默认值的数组
				Double[] data = StatsUtils.getStaticDoubleArray(monthArray.length,0.0);
				
				//判断指定项目和时间内是否有数据
				if (dataList.size() > 0) {
					for (int j = 0; j < dataList.size(); j++) {
						//如果查询到的标段与遍历的标段oid一致，则封装数据到此标段下
						if (tendersList.get(i).get("oid").equals(dataList.get(j).getTendersOid())) {
							//获取月份对应的索引index，并将累计值放到data数组的index位置
							String monthOfYear = dataList.get(j).getMonthOfYear();
							int index = getIndex(monthArray,monthOfYear);
							double totalByMonth = Double.parseDouble(dataList.get(j).getTotalByMonth().toString());
							for (int k = 0; k < monthArray.length; k++) {
								if (k >= index) {
									data[k] = totalByMonth;
								}
							}
//							data[index] = Double.parseDouble(dataList.get(j).getTotalByMonth().toString());
							
						}
					}
				}
				map.put("month", monthArray);
				map.put("data", data);
				list.add(map);
			}
		} else {
			Map<String, Object> map = new HashMap<>();
			//创建指定长度且有默认值的数组
			Double[] data = StatsUtils.getStaticDoubleArray(monthArray.length,0.0);
			map.put("month", monthArray);
			map.put("data", data);
			list.add(map);
		}
		return list;
	}

	/**
	 * <p>功能描述：获取指定数组指定值的第一个索引。</p>
	  * <p> 葛建。</p>	
	  * @param monthArray
	  * @param monthOfYear
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午1:55:53。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	private int getIndex(Object[] monthArray, String str) {
		if (StringUtils.isBlank(str)) {
			return -1;
		}
		for (int i = 0; i < monthArray.length; i++) {
			if (str.equals(monthArray[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * <p>功能描述：标段-各工序分标段累计情况统计（km）。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午3:37:41。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getEachItemLengthStatsByTenders(String projectOid, String date) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oid查询标段oid和名称
		List<Map<String, String>> tendersList = progressStatsDao.getTendersList(projectOid);
		//焊接
		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldLengthStatsByTenders(projectOid,date);
		// 封装焊接工序的返回值
		setListItemsForTenders(list,weldList,"weld",tendersList);
		//补口
		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchLengthStatsByTenders(projectOid,date);
		//封装补口工序的返回值
		setListItemsForTenders(list,petchList,"patch",tendersList);
		//管沟回填
		List<ProgressStatsQueryBo> backFillList = progressStatsDao.getBackFillLengthStatsByTenders(projectOid,date);
		//封装管沟回填的返回值
		setListItemsForTenders(list,backFillList,"lay_pipe_trench_backfill",tendersList);
		//地貌恢复
		List<ProgressStatsQueryBo> landRestorationList = progressStatsDao.getLandRestorationLengthStatsByTenders(projectOid,date);
		//封装地貌恢复的返回值
		setListItemsForTenders(list,landRestorationList,"lay_land_restoration",tendersList);
		return list;
	}
	
	public void setListItemsForTenders(List<Map<String, Object>> resultList, List<ProgressStatsQueryBo> dataList,String type, List<Map<String, String>> tendersList){
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
		case "detection_ray":
			map.put("type", "detection_ray");
			map.put("typeName", "射线检测");
			break;
		case "measured_result":
			map.put("type", "measured_result");
			map.put("typeName", "焊口测量");
			break;
		default:
			break;
		}
		Double[] statsResult = new Double[tendersList.size()];
		Arrays.fill(statsResult, 0.0);
		String[] tendersNames = new String[tendersList.size()];
		String[] tendersOids = new String[tendersList.size()];
		if (tendersList.size() > 0) {
			for (int i = 0; i < tendersList.size(); i++) {
				tendersNames[i] = tendersList.get(i).get("tendersName");
				tendersOids[i] = tendersList.get(i).get("oid");
			}
			if (dataList.size() > 0) {
				for (int j = 0; j < dataList.size(); j++) {
					int index = getIndex(tendersOids, dataList.get(j).getTendersOid());
					if (index != -1) {
						statsResult[index] = Double.parseDouble(dataList.get(j).getStatsResult().toString());
					}
				}
			}
		}
		map.put("statsResult", statsResult);
		map.put("tendersNames", tendersNames);
		map.put("tendersOids", tendersOids);
		resultList.add(map);
	}

	/**
	 * <p>功能描述：标段-各工序分标段累计情况统计（口）。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午5:57:56。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getEachItemCountStatsByTendersAndDate(String projectOid, String date) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oid查询标段oid和名称
		List<Map<String, String>> tendersList = progressStatsDao.getTendersList(projectOid);
		//焊接
		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldCountStatsByTenders(projectOid,date);
		//封装焊接工序的返回值
		setListItemsForTenders(list,weldList,"weld",tendersList);
		//补口
		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchCountStatsByTenders(projectOid,date);
		//封装补口工序的返回值
		setListItemsForTenders(list,petchList,"patch",tendersList);
		//射线检测
		List<ProgressStatsQueryBo> detectionRayList = progressStatsDao.getDetectionRayCountStatsByTenders(projectOid,date);
		//封装射线检测工序的返回值
		setListItemsForTenders(list,detectionRayList,"detection_ray",tendersList);
		//中线测量
		List<ProgressStatsQueryBo> measuredResultList = progressStatsDao.getMeasuredResultCountStatsByTenders(projectOid,date);
		//封装中线测量工序的返回值
		setListItemsForTenders(list,measuredResultList,"measured_result",tendersList);
		return list;
	}
	
	
}
