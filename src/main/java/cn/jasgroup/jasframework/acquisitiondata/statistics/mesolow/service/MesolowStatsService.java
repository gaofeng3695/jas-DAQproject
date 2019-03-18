package cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jasgroup.jasframework.acquisitiondata.statistics.mesolow.dao.MesolowStatsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm.StatsUtils;
import cn.jasgroup.jasframework.acquisitiondata.statistics.quality.dao.QualityStatsDao;
import cn.jasgroup.jasframework.utils.DateTimeUtil;
import net.sf.json.JSONArray;

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
	
	@Autowired
	private QualityStatsDao qualityStatsDao;

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
	public Map<String, Object> getMonthlyGrothAndTotal(List<String> projectOids, String year) {
		// 结果集
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取当前年对应的1-12月的数组
		Object[] monthArray = StatsUtils.getMonthArray(year+"-01", year+"-12", "yyyy-MM");
		//当年每月新增数组和每月累计数组，默认为0.0
		Double[] monthlyGrowthArray = StatsUtils.getStaticDoubleArray(12, 0.0);
		Double[] monthlyTotalArray = StatsUtils.getStaticDoubleArray(12, 0.0);
		//查询截止当前年的累计长度
		List<Map<String, Object>> totalBefore = mesolowStatsDao.getTotalBefore(projectOids, year);
		// 截止当前年一月的累计长度
		double total = 0.0;
		if (totalBefore.size() > 0 ) {
			total = Double.parseDouble(totalBefore.get(0).get("total").toString());
		}
		//查询当年每月的新增量
		List<Map<String, Object>> monthlyGrowthList = mesolowStatsDao.getMonthlyGrowth(projectOids, year);
		// 根据对应的年月，将对应的月增量放到monthlyGrowthArray对应的索引下
		if (monthlyGrowthList.size() > 0) {
			for (Map<String, Object> map : monthlyGrowthList) {
				int index = getIndex(monthArray, (String)map.get("month"));
				if (index != -1) {
					monthlyGrowthArray[index] = Double.parseDouble(map.get("sumPerMonth").toString());
					// 保留一位小数
					BigDecimal bgl = new BigDecimal(monthlyGrowthArray[index]);
					monthlyGrowthArray[index] = bgl.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
				}
			}
		}
		// 获取当前年月
		String currentMonth = DateTimeUtil.getFormatDate(DateTimeUtil.getSysDate(), "yyyy-MM");
		int index = getIndex(monthArray, currentMonth);
		if (index != -1) {
			for (int i = 0; i < monthlyGrowthArray.length; i++) {
				// 每月月增量 + 截止当前年一月的累计长度 = 当前月累计量
				total += monthlyGrowthArray[i];
				monthlyTotalArray[i] = total;
				// 如果当前月超过系统当前月，则总计量置0
				if (i > index) {
					monthlyTotalArray[i] = 0.0;
				}
				// 保留一位小数
				BigDecimal bg = new BigDecimal(monthlyTotalArray[i]);
				monthlyTotalArray[i] = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
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

	/**
	 * <p>功能描述：根据项目和时间查询当月各施工单位月新增。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月7日 上午10:38:37。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getSingleMonthlyGroth(String projectOid, String month) {
		// 结果集
		Map<String, Object> result = new HashMap<String, Object>();
		// 根据项目查询所有的施工单位
		List<Map<String, Object>> constructUnitList = qualityStatsDao.getConstructUnitByProjectOid(projectOid);
		if (constructUnitList.size() > 0) {
			// 查询各施工单位当月新增
			List<Map<String, Object>> unitAndMonthlyGrowthList = mesolowStatsDao.getUnitAndMonthlyGrowth(projectOid, month);
			// 创建部门和月新增数组
			String[] unitNameArray = new String[constructUnitList.size()];			
			Double[] monthlyGrowthArray = StatsUtils.getStaticDoubleArray(constructUnitList.size(), 0.0);
			for (int i = 0; i < unitNameArray.length; i++) {
				unitNameArray[i] = constructUnitList.get(i).get("value").toString();
			}
			if (unitAndMonthlyGrowthList.size() > 0) {
				for (int i = 0; i < unitAndMonthlyGrowthList.size(); i++) {
					int index = getIndex(unitNameArray, unitAndMonthlyGrowthList.get(i).get("unitName").toString());
					if (index != -1) {
						// 保留一位小数
						BigDecimal bg = new BigDecimal(Double.parseDouble(unitAndMonthlyGrowthList.get(i).get("sumPerUnit").toString()));
						monthlyGrowthArray[i] = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
					}
				}
			}
			result.put("unitNames", unitNameArray);
			result.put("monthlyGrowths", monthlyGrowthArray);
		}
		return result;
	}

	/**
	 * <p>功能描述：施工单位-月新增管道长度(全年)。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param year
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月7日 下午3:32:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getMonthlyGrowthAllYear(String projectOid, String year) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		List<Map<String, Object>> monthlyGrowthList = mesolowStatsDao.getMonthAndUnitAndMonthlyGrowth(projectOid, year);
		for(Map<String,Object> map:monthlyGrowthList){
			Map<String,Object> obj = new HashMap<String, Object>();
			Object unitName = map.get("unitName");
			Object resultData = map.get("resultData");
			if(unitName!=null && resultData!=null){
				JSONArray array = JSONArray.fromObject(resultData.toString());
				obj.put(unitName.toString(), array);
			}
			resultList.add(obj);
		}
		return resultList;
	}

	/**
	 * <p>功能描述：求数组各元素的总和。</p>
	  * <p> 葛建。</p>	
	  * @param monthlyGrowthArray
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2019年3月7日 下午5:17:07。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	private double getSum(Double[] monthlyGrowthArray) {
		double total = 0.0;
		for (Double double1 : monthlyGrowthArray) {
			total += double1;
		}
		BigDecimal bg = new BigDecimal(total);
		total = bg.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
		return total;
	}
}
