package cn.jasgroup.jasframework.acquisitiondata.statistics.quality.service;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.statistics.quality.dao.QualityStatsDao;

@Service
public class QualityStatsService {

	private static final String String = null;
	@Resource(name = "qualityStatsDao")
	private QualityStatsDao qualityStatsDao;

	/**
	 * <p>功能描述：项目/单位分月合格率对比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param unitOids
	  * @param year
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月13日 下午2:52:31。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getMonthlyQualiifiedRateByProjectAndUnit(List<String> projectOids, List<String> unitOids,
			String year) {
		//封装返回值
		Map<String, Object> map = new HashMap<>();
		//根据条件查询每月射线检测的焊口口数及对应的一次合格率
		List<Map<String, Object>> weldCountAndRateList = qualityStatsDao.getMonthlyQualiifiedRateByProjectAndUnit(projectOids, unitOids, year);
		//定义1-12月的数组
		String[] monthArray = new String[]{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
		//定义焊口数数组,并将数据填充为0
		int[] weldCount = new int[12];
		Arrays.fill(weldCount, 0);
		//定义合格率
		Double[] rate = new Double[12];
		Arrays.fill(rate, 0.0);
		if (weldCountAndRateList.size() > 0) {
			for (Map<String, Object> weldCountAndRate : weldCountAndRateList) {
				String month = (String)weldCountAndRate.get("month") + "月";
				int index = getIndex(monthArray,month);
				if (index != -1) {
					weldCount[index] = Integer.parseInt(weldCountAndRate.get("monthCount").toString());
					DecimalFormat df = new DecimalFormat("#.00");
					double qualifiedRate = Double.parseDouble(df.format(weldCountAndRate.get("qualifiedRate")).toString());
					rate[index] = qualifiedRate;
				}
			}
		}
		map.put("month", monthArray);
		map.put("weldCount", weldCount);
		map.put("qualifiedRate", rate);
		return map;
	}
	
	/**
	 * <p>功能描述：获取指定值在数组中的第一个位置的索引。</p>
	  * <p> 葛建。</p>	
	  * @param monthArray
	  * @param monthOfYear
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 上午9:40:21。</p>
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
