package cn.jasgroup.jasframework.acquisitiondata.statistics.material.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
import java.util.Set;

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

		Date date = DateTimeUtil.getDateFromDateString(month, "yyyy-MM");
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
	
	/***
	  * <p>功能描述：物资使用情况统计。</p>
	  * <p> 雷凯。</p>	
	  * @param projectOid
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午4:34:29。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMaterialUseStatustics(String projectOid, String month) {
		Date date = DateTimeUtil.getDateFromDateString(month, "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String minDateTime = month + "-01";
		String maxDateTime = month + "-" + maxDay;
		List<Map<String, Object>> pipeData = this.materialStatisticsDao.getMaterialUseStatustics(projectOid, minDateTime, maxDateTime);
		Map<String, String> tendersMap = new HashMap<>();
		Map<String, List<Map<String,Object>>> tempDataMap = new HashMap<>();
		for (Map<String, Object> map : pipeData) {
			String tendersOid = map.get("oid").toString();
			if(tempDataMap.containsKey(tendersOid)){
				List<Map<String,Object>> mapData = tempDataMap.get(tendersOid);
				boolean isExist = false;
				for(Map<String,Object> obj : mapData){
					if(map.get("specifications")!=null && map.get("specifications").toString().equals(obj.get("specifications"))){
						obj.put(map.get("dataType").toString(), map.get("pipeLength"));
						isExist = true;
						break;
					}else if(map.get("specifications")!=null){
						isExist = false;
					}
				}
				if(!isExist && map.get("specifications")!=null){
					Map<String,Object> newObj = new LinkedHashMap<>();
					newObj.put("tendersOid", tendersOid);
					newObj.put("specifications", map.get("specifications"));
					newObj.put("pipe_month_use", 0);
					newObj.put("pipe_total_use", 0);
					newObj.put("pipe_month_receive", 0);
					newObj.put("pipe_total_receive", 0);
					newObj.put("pipe_total_overplus", 0);
					newObj.put("h_pipe_month_use", 0);
					newObj.put("h_pipe_total_use", 0);
					newObj.put("h_pipe_month_receive", 0);
					newObj.put("h_pipe_total_receive", 0);
					newObj.put("h_pipe_total_overplus", 0);
					newObj.put(map.get("dataType").toString(), map.get("pipeLength"));
					mapData.add(newObj);
				}
			}else{
				List<Map<String,Object>> mapData = new ArrayList<>();
				Map<String,Object> obj = new LinkedHashMap<>();
				if(map.get("specifications")!=null){
					obj.put("tendersOid", tendersOid);
					obj.put("specifications", map.get("specifications"));
					obj.put("pipe_month_use", 0);
					obj.put("pipe_total_use", 0);
					obj.put("pipe_month_receive", 0);
					obj.put("pipe_total_receive", 0);
					obj.put("pipe_total_overplus", 0);
					obj.put("h_pipe_month_use", 0);
					obj.put("h_pipe_total_use", 0);
					obj.put("h_pipe_month_receive", 0);
					obj.put("h_pipe_total_receive", 0);
					obj.put("h_pipe_total_overplus", 0);
					obj.put(map.get("dataType").toString(), map.get("pipeLength"));
					mapData.add(obj);
				}
				tempDataMap.put(tendersOid, mapData);
				tendersMap.put(tendersOid, map.get("tendersName").toString());
			}
		}
		List<Map<String,Object>> result = new ArrayList<>();
		for (Entry<String, List<Map<String,Object>>> entry : tempDataMap.entrySet()) {
			String oid = entry.getKey();
			List<Map<String,Object>> data = entry.getValue();
			double pipe_month_use_sum=0d;
			double pipe_total_use_sum=0d;
			double pipe_month_receive_sum=0d;
			double pipe_total_receive_sum=0d;
			double pipe_total_overplus_sum=0d;
			
			int h_pipe_month_use_sum=0;
			int h_pipe_total_use_sum=0;
			int h_pipe_month_receive_sum=0;
			int h_pipe_total_receive_sum=0;
			int h_pipe_total_overplus_sum=0;
			for(Map<String,Object> obj : data){
				double pipeTotalUse = Double.parseDouble(obj.get("pipe_total_use").toString());
				double pipeTotalReceive = Double.parseDouble(obj.get("pipe_total_receive").toString());
				double pipeMonthUse = Double.parseDouble(obj.get("pipe_month_use").toString());
				double pipeMonthReceive = Double.parseDouble(obj.get("pipe_month_receive").toString());
				double overplus = pipeTotalReceive-pipeTotalUse;
				BigDecimal bg = new BigDecimal(overplus);
				overplus = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
				obj.put("pipe_total_overplus", overplus);
				pipe_month_use_sum+=pipeMonthUse;
				pipe_total_use_sum+=pipeTotalUse;
				pipe_month_receive_sum+=pipeMonthReceive;
				pipe_total_receive_sum+=pipeTotalReceive;
				pipe_total_overplus_sum+=overplus;
				
				
				int hPipeTotalUse = Integer.parseInt(obj.get("h_pipe_total_use").toString());
				int hPipeTotalReceive = Integer.parseInt(obj.get("h_pipe_total_receive").toString());
				int hPipeMonthUse = Integer.parseInt(obj.get("h_pipe_month_use").toString());
				int hPipeMonthReceive = Integer.parseInt(obj.get("h_pipe_month_receive").toString());
				int hOverplus = hPipeTotalReceive-hPipeTotalUse;
				obj.put("h_pipe_total_overplus", hOverplus);
				
				h_pipe_month_use_sum+=hPipeMonthUse;
				h_pipe_total_use_sum+=hPipeTotalUse;
				h_pipe_month_receive_sum+=hPipeMonthReceive;
				h_pipe_total_receive_sum+=hPipeTotalReceive;
				h_pipe_total_overplus_sum+=hOverplus;
			}
			if(data!=null && data.size()>0){
				DecimalFormat df = new DecimalFormat("#.000");
				Map<String,Object> sum = new LinkedHashMap<>();
				sum.put("tendersOid", oid);
				sum.put("specifications", "合计");
				sum.put("pipe_month_use", df.format(pipe_month_use_sum));
				sum.put("pipe_total_use_", df.format(pipe_total_use_sum));
				sum.put("pipe_month_receive", df.format(pipe_month_receive_sum));
				sum.put("pipe_total_receive", df.format(pipe_total_receive_sum));
				sum.put("pipe_total_overplus", df.format(pipe_total_overplus_sum));
				sum.put("h_pipe_month_use", h_pipe_month_use_sum);
				sum.put("h_pipe_total_use", h_pipe_total_use_sum);
				sum.put("h_pipe_month_receive", h_pipe_month_receive_sum);
				sum.put("h_pipe_total_receive", h_pipe_total_receive_sum);
				sum.put("h_pipe_total_overplus", h_pipe_total_overplus_sum);
				data.add(sum);
			}
			Map<String,Object> obj = new HashMap<>();
			obj.put("tendersOid", oid);
			obj.put("tendersName", tendersMap.get(oid));
			obj.put("dataList", data);
			result.add(obj);
		}
		return result;
	}
}
