package cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.dao.MesolowStatsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsUtils;
import cn.jasgroup.jasframework.utils.DateTimeUtil;

/**
 * 
  *<p>类描述：中低压统计servicve。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2019年3月6日 下午5:18:19。</p>
 */
@Service
public class MesolowStatsService {

	@Autowired
	private MesolowStatsDao mesolowStatsDao;

	/**
	 * <p>功能描述：项目-月新增管段长度统计。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param year
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月6日 下午5:24:58。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	@Transactional
	public Map<String, Object> getMonthLyGrowthAndTotal(List<String> projectOids, String year) {
		// 结果集
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取当前年对应的1-12月的数组
		Object[] monthArray = StatsUtils.getMonthArray(year+"-01", year+"-12", "yyyy-MM");
		//当年每月新增数组和每月累计数组，默认为0.0
		Double[] monthlyGrowthArray = StatsUtils.getStaticDoubleArray(12, 0.0);
		Double[] monthlyTotalArray = StatsUtils.getStaticDoubleArray(12, 0.0);
		//查询截止今年的累计长度
		List<Map<String, Object>> totalBefore = mesolowStatsDao.getTotalBefore(projectOids, year);
		double total = 0.0;
		if (totalBefore.size() > 0 ) {
			total = Double.parseDouble(totalBefore.get(0).get("total").toString());
		}
		
		//查询当年每月的新增量
		List<Map<String, Object>> monthlyGrowthList = mesolowStatsDao.getMonthlyGrowth(projectOids, year);
		// 
		if (monthlyGrowthList.size() > 0) {
			for (Map<String, Object> map : monthlyGrowthList) {
				int index = getIndex(monthArray, (String)map.get("month"));
				monthlyGrowthArray[index] = Double.parseDouble(map.get("sumPerMonth").toString());
			}
		}
		String currentMonth = DateTimeUtil.getFormatDate(DateTimeUtil.getSysDate(), "yyyy-MM");
		int index = getIndex(monthArray, currentMonth);
		for (int i = 0; i < monthlyGrowthArray.length; i++) {
			total += monthlyGrowthArray[i];
			monthlyTotalArray[i] = total;
			if (i > index) {
				monthlyTotalArray[i] = 0.0;
			}
		}
		result.put("monthlyGowth", monthlyGrowthArray);
		result.put("monthlyTotal", monthlyTotalArray);
		return result;
	}
	
	/**
	 * <p>功能描述：获取指定字符串在字符串数组中的第一个位置的索引。</p>
	  * <p> 葛建。</p>	
	  * @param monthArray
	  * @param str
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月6日 下午7:02:17。</p>
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
}
