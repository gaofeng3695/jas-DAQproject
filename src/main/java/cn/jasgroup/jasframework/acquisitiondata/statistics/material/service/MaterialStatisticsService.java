package cn.jasgroup.jasframework.acquisitiondata.statistics.material.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.statistics.material.dao.MaterialStatisticsDao;
import cn.jasgroup.jasframework.utils.DateTimeUtil;

/**
  *  
  *<p>类描述：物资统计service。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年12月10日 下午5:58:07。</p>
 */
@Service
public class MaterialStatisticsService {
	
	@Resource
	private MaterialStatisticsDao materialStatisticsDao;
	
	/***
	  * <p>功能描述：物资按年分月统计。</p>
	  * <p> 雷凯。</p>	
	  * @param year
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 上午10:29:20。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getMonthlyStatistics(List<Object> projectOid, String year) {
		double total = this.materialStatisticsDao.getYearGrandTotal(projectOid, year);
		List<Map<String, Object>> monthlyData = this.materialStatisticsDao.getMonthlyStatistics(projectOid, year);
		Object[] pipeDataList = new Object[12];
		Object[] hotPipeDataList = new Object[12];
		Object[] grandTotalDataList = new Object[12];
		for (int i = 1; i < 13; i++) {
			String yearMonth = i > 9 ? (year + "-" + i) : (year + "-0" + i);

			pipeDataList[i - 1] = 0;
			hotPipeDataList[i - 1] = 0;
			grandTotalDataList[i - 1] = total;
			for (Map<String, Object> map : monthlyData) {
				if (map.containsValue(yearMonth)) {
					if (map.get("type").toString().equals("pipe")) {
						pipeDataList[i - 1] = map.get("pipeLength");
					} else {
						hotPipeDataList[i - 1] = map.get("pipeLength");
					}
					total += Double.parseDouble(map.get("pipeLength").toString());
					BigDecimal bg = new BigDecimal(total);
					total = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
					grandTotalDataList[i - 1] = total;
				}
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("pipe", pipeDataList);
		result.put("hot_pipe", hotPipeDataList);
		result.put("grand_total", grandTotalDataList);
		return result;
	}
	
	/***
	  * <p>功能描述：物资按月分日统计。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午2:31:26。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getDailyStatistics(List<Object> projectOid, String month) {
		double total = this.materialStatisticsDao.getMonthGrandTotal(projectOid, month);
		List<Map<String, Object>> dailyData = this.materialStatisticsDao.getDailyStatistics(projectOid, month);

		Date date = DateTimeUtil.getDateFromDateString("2018-06", "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		Map<String, Object> pipeData = new LinkedHashMap<>();
		Map<String, Object> hotPipeData = new LinkedHashMap<>();
		Map<String, Object> grandTotalData = new LinkedHashMap<>();
		for (int i = 1; i <= maxDay; i++) {
			String monthDay = i > 9 ? (month + "-" + i) : (month + "-0" + i);
			pipeData.put(monthDay, 0);
			hotPipeData.put(monthDay, 0);
			grandTotalData.put(monthDay, total);
			for (Map<String, Object> map : dailyData) {
				if (map.containsValue(monthDay)) {
					if (map.get("type").toString().equals("pipe")) {
						pipeData.put(monthDay, map.get("pipeLength"));
					} else {
						hotPipeData.put(monthDay, map.get("pipeLength"));
					}
					total += Double.parseDouble(map.get("pipeLength").toString());
					BigDecimal bg = new BigDecimal(total);
					total = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
					grandTotalData.put(monthDay, total);
				}
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("pipe", pipeData);
		result.put("hot_pipe", hotPipeData);
		result.put("grand_total", grandTotalData);
		return result;
	}
	/***
	  * <p>功能描述：按标段统计。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param dataTime
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月12日 下午4:14:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getTendersStatistics(String projectOid,String dataTime) {
		List<Map<String, Object>> tendersData = this.materialStatisticsDao.getTendersStatistics(projectOid,dataTime);
		
		Map<String,Object> pipeData = new LinkedHashMap<>();
		Map<String,Object> hotPipeData = new LinkedHashMap<>();
		for (Map<String, Object> map : tendersData) {
			if (map.get("type").toString().equals("pipe")) {
				pipeData.put(map.get("tendersName").toString(), map.get("pipeLength"));
			}else{
				hotPipeData.put(map.get("tendersName").toString(), map.get("pipeLength"));
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("pipe", pipeData);
		result.put("hot_pipe", hotPipeData);
		return result;
	}
	
}
