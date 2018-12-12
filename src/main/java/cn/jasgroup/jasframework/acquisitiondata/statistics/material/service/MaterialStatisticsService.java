package cn.jasgroup.jasframework.acquisitiondata.statistics.material.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.statistics.material.dao.MaterialStatisticsDao;

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
	
	public Map<String, Object> getMonthlyStatistics(String year) {
		double total = this.materialStatisticsDao.getMaterialgrandTotal(year);
		List<Map<String, Object>> monthlyData = this.materialStatisticsDao.monthlyStatistics(year);
		Map<String, Object> pipeData = new HashMap<>();
		Map<String, Object> hotPipeData = new HashMap<>();
		Map<String, Object> grandTotalData = new HashMap<>();
		Object[] pipeDataList = new Object[12];
		Object[] hotPipeDataList = new Object[12];
		Object[] grandTotalDataList = new Object[12];
		for (int i = 1; i < 13; i++) {
			String yearMonth = i > 9 ? (year + "-" + i) : (year + "-0" + i);
			pipeData.put(yearMonth, 0);
			hotPipeData.put(yearMonth, 0);
			grandTotalData.put(yearMonth, total);
			
			pipeDataList[i-1]=0;
			hotPipeDataList[i-1]=0;
			grandTotalDataList[i-1] = total;
			for (Map<String, Object> map : monthlyData) {
				if (map.containsValue(yearMonth)) {
					if (map.get("type").toString().equals("pipe")) {
						pipeData.put(yearMonth, map.get("pipeLength"));
						pipeDataList[i-1]=map.get("pipeLength");
					} else {
						hotPipeData.put(yearMonth, map.get("pipeLength"));
						hotPipeDataList[i-1]= map.get("pipeLength");
					}
					total += Double.parseDouble(map.get("pipeLength").toString());
					BigDecimal bg = new BigDecimal(total);
					total = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
					grandTotalData.put(yearMonth, total);
					grandTotalDataList[i-1]= total;
				}
			}
		}
		Map<String, Object> result = new HashMap<>();
		result.put("pipe", pipeDataList);
		result.put("hot_pipe", hotPipeDataList);
		result.put("grand_total", grandTotalDataList);
		return result;
	}
}
