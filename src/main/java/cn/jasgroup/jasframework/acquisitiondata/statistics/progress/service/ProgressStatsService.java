package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsUtils;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsQueryBo;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common.ProgressStatsResultBo;
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
	public List<Map<String, Object>> getEachItemLengthStatsByProject(List<String> projectOids, String date) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oids查询项目名称
		List<Map<String, String>> projectList = progressStatsDao.getProjectList(projectOids);
		//焊接
		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldLengthStatsByProject(projectOids,date);
		//封装焊接工序的返回值
		setListItems(list,weldList,"weld",projectList);
		//补口
		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchLengthStatsByProject(projectOids,date);
		//封装补口工序的返回值
		setListItems(list,petchList,"patch",projectList);
		//管沟回填
		List<ProgressStatsQueryBo> backFillList = progressStatsDao.getBackFillLengthStatsByProject(projectOids,date);
		//封装管沟回填的返回值
		setListItems(list,backFillList,"lay_pipe_trench_backfill",projectList);
		//地貌恢复
		List<ProgressStatsQueryBo> landRestorationList = progressStatsDao.getLandRestorationLengthStatsByProject(projectOids,date);
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
	public void setListItems(List<Map<String, Object>> resultList, List<ProgressStatsQueryBo> dataList,String type, List<Map<String, String>> projectList){
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
			Double[] statsResult = new Double[projectList.size()];
			//将statsResult数组数值默认填充为0.0
			Arrays.fill(statsResult, 0.0);
			String[] projectName = new String[projectList.size()];
			String[] projectOid = new String[projectList.size()];
			for (int i = 0; i < dataList.size(); i++) {
				statsResult[i] = Double.parseDouble(dataList.get(i).getStatsResult().toString());
				projectName[i] = dataList.get(i).getProjectName();
				projectOid[i] = dataList.get(i).getProjectOid();
			}
			//若dataList中数据不包含传的项目，将遗失的项目及对应的数据补0
			//用于判断向数组中哪个索引位置插值
			int lastIndex = projectOid.length;
			for (int i = 0; i < projectList.size(); i++) {
				int index = getIndex(projectOid, projectList.get(i).get("oid"));
				if (index == -1) {
					lastIndex -= 1;
					projectOid[lastIndex] = projectList.get(i).get("oid");
					projectName[lastIndex] = projectList.get(i).get("projectName");
				}
			}
			map.put("statsResult", statsResult);
			map.put("projectName", projectName);
			map.put("projectOids", projectOid);
			resultList.add(map);
		} else {
			//若dataList为空，封装项目oid和名称，将对应的长度封装为0
			Double[] statsResult = new Double[projectList.size()];
			String[] projectName = new String[projectList.size()];
			String[] projectOid = new String[projectList.size()];
			for (int i = 0; i < projectList.size(); i++) {
				statsResult[i] = 0.0;
				projectName[i] = projectList.get(i).get("projectName");
				projectOid[i] = projectList.get(i).get("oid");
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
	public List<Map<String, Object>> getEachItemCountStatsByProject(List<String> projectOids, String date) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oids查询项目名称
		List<Map<String, String>> projectList = progressStatsDao.getProjectList(projectOids);
		//焊接
		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldCountStatsByProject(projectOids,date);
		//封装焊接工序的返回值
		setListItems(list,weldList,"weld",projectList);
		//补口
		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchCountStatsByProject(projectOids,date);
		//封装补口工序的返回值
		setListItems(list,petchList,"petch",projectList);
		//射线检测
		List<ProgressStatsQueryBo> detectionRayList = progressStatsDao.getDetectionRayCountStatsByProject(projectOids,date);
		//封装射线检测工序的返回值
		setListItems(list,detectionRayList,"detection_ray",projectList);
		//射线检测
		List<ProgressStatsQueryBo> measuredResultList = progressStatsDao.getMeasuredResultCountStatsByProject(projectOids,date);
		//封装射线检测工序的返回值
		setListItems(list,measuredResultList,"measured_result",projectList);
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
		Double[] date = new Double[monthArray.length];
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
							data[index] = Double.parseDouble(dataList.get(j).getTotalByMonth().toString());
						}
					}
				}
				map.put("month", monthArray);
				map.put("data", data);
				list.add(map);
			}
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
	private int getIndex(Object[] monthArray, String monthOfYear) {
		if (StringUtils.isBlank(monthOfYear)) {
			return -1;
		}
		for (int i = 0; i < monthArray.length; i++) {
			if (monthOfYear.equals(monthArray[i])) {
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
		//根据项目oids查询项目名称
		List<Map<String, String>> projectList = progressStatsDao.getTendersList(projectOid);
		//焊接
//		List<ProgressStatsQueryBo> weldList = progressStatsDao.getWeldLengthStatsByTenders(projectOid,date);
//		//封装焊接工序的返回值
//		//补口
//		List<ProgressStatsQueryBo> petchList = progressStatsDao.getPetchLengthStatsByTenders(projectOid,date);
//		//封装补口工序的返回值
//		//管沟回填
//		List<ProgressStatsQueryBo> backFillList = progressStatsDao.getBackFillLengthStatsByTenders(projectOid,date);
//		//封装管沟回填的返回值
//		//地貌恢复
//		List<ProgressStatsQueryBo> landRestorationList = progressStatsDao.getLandRestorationLengthStatsByTenders(projectOid,date);
		//封装地貌恢复的返回值
		return list;
	}
}
