package cn.jasgroup.jasframework.acquisitiondata.statistics.quality.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.privilege.dao.DaqPrivilegeDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.progress.dao.ProgressStatsDao;
import cn.jasgroup.jasframework.acquisitiondata.statistics.quality.dao.QualityStatsDao;

@Service
public class QualityStatsService {

	@Resource(name = "qualityStatsDao")
	private QualityStatsDao qualityStatsDao;
	
	@Resource(name="progressStatsDao")
	private ProgressStatsDao progressStatsDao;
	
	@Resource(name="daqPrivilegeDao")
	private DaqPrivilegeDao daqPrivilegeDao;

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
		String[] monthArray = new String[]{"01月","02月","03月","04月","05月","06月","07月","08月","09月","10月","11月","12月"};
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
					double qualifiedRate = Double.parseDouble(weldCountAndRate.get("qualifiedRate").toString());
					BigDecimal bg = new BigDecimal(qualifiedRate);
					qualifiedRate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
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
	 * <p>功能描述：获取指定字符串在字符串数组中的第一个位置的索引。</p>
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

	/**
	 * <p>功能描述：项目缺陷性质分类统计-根据单位分组的count。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	 * @param unitOids 
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午1:55:12。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public List<Map<String, Object>> getKindsOfDefectCountByProjects(List<String> projectOids, List<String> unitOids, String month) {
		//封装返回值
		List<Map<String, Object>> list = new ArrayList<>();
		//根据项目oids查询部门oid和对应的名称
		List<Map<String, String>> unitList = qualityStatsDao.getUnitList(unitOids);
		//查询缺陷性质列表
		List<Map<String, String>> defectList = qualityStatsDao.getDefectList();
		//根据项目、unit和月份查询对应的单位下不同缺陷性质的个数
		List<Map<String, Object>> defectCountList = qualityStatsDao.getKindsOfDefectCountByProjects(projectOids, unitOids, month);
		for (int i = 0; i < defectList.size(); i++) {
			//封装每个缺陷性质的单位和个数信息
			Map<String,Object> map = new HashMap<>();
			map.put("type", defectList.get(i).get("key"));
			map.put("typeName", defectList.get(i).get("value"));
			int[] statsResult = new int[unitList.size()];
			//将statsResult数组数值默认填充为0.0
			Arrays.fill(statsResult, 0);
			String[] unitNameArray = new String[unitList.size()];
			String[] unitOidsArray = new String[unitList.size()];
			for (int j = 0; j < unitList.size(); j++) {
				unitNameArray[j] = unitList.get(j).get("unitName");
				unitOidsArray[j] = unitList.get(j).get("oid");
				// 判断统计数据是否为空
				if (defectCountList.size() > 0) {
					for (int h = 0; h < defectCountList.size(); h++) {
						String unitOid = (String) defectCountList.get(h).get("constructUnit");
						//统计结果不为空，将对应的缺陷性质条数放到statsResult对应的位置
						if (unitOid.equals(unitOidsArray[j]) && defectList.get(i).get("key").equals(defectCountList.get(h).get("defectProperties"))) {
							statsResult[j] = Integer.parseInt(defectCountList.get(h).get("count").toString());
						}
					}
				}
			}
			map.put("statsResult", statsResult);
			map.put("unitOids", unitOidsArray);
			map.put("unitNames", unitNameArray);
			list.add(map);
		}
		return list;
	}

	/**
	 * <p>功能描述：项目缺陷性质分类统计-占比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOids
	  * @param unitOids
	  * @param month
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月14日 下午4:22:39。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getKindsOfDefectRateByProjects(List<String> projectOids,
			List<String> unitOids, String month) {
		//用于封装返回数据
		Map<String, Object> map = new HashMap<>();
		//查询缺陷性质列表
		List<Map<String, String>> defectList = qualityStatsDao.getDefectList();
		String[] typeArray = new String[defectList.size()];
		String[] typeNameArray = new String[defectList.size()];
		Integer[] countArray = new Integer[defectList.size()];
		Arrays.fill(countArray, 0);
		//根据项目，单位和月份查询对应缺陷性质的占比
		List<Map<String, Object>> rateList = qualityStatsDao.getKindsOfDefectRateByProjects(projectOids, unitOids, month);
		//填充typeArray，typeNameArray
		for (int i=0; i < defectList.size(); i++) {
			typeArray[i] = defectList.get(i).get("key");
			typeNameArray[i] = defectList.get(i).get("value");
		}
		//给对应位置填充真实的比例值
		for (Map<String, Object> defectRate : rateList) {
			int index = getIndex(typeArray, (String)defectRate.get("defectProperties"));
			if (index != -1) {
				countArray[index] = Integer.parseInt(defectRate.get("count").toString());
			}
		}
		map.put("type", typeArray);
		map.put("typeName", typeNameArray);
		map.put("count", countArray);
		return map;
	}

	/**
	 * <p>功能描述：项目各标段焊接一次合格率对比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 上午10:01:47。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getEachTendersQualifiedRateByProjects(String projectOid, String date) {
		//封装返回值
		Map<String, Object> map = new HashMap<>();
		//根据项目查询标段oid和对应的名称
		List<Map<String,String>> tendersList = progressStatsDao.getTendersList(projectOid);
		//根据项目、日期查询每个标段的检测口数和一次合格率
		List<Map<String, Object>> dataList = qualityStatsDao.getEachTendersQualifiedRateByProjects(projectOid, date);
		String[] tendersOidArray = new String[tendersList.size()];
		String[] tendersNameArray = new String[tendersList.size()];
		Integer[] countArray = new Integer[tendersList.size()];
		Arrays.fill(countArray, 0);
		Double[] rateArray = new Double[tendersList.size()];
		Arrays.fill(rateArray, 0.0);
		//判断项目下是否有标段
		if (tendersList.size() > 0) {
			for (int i = 0; i < tendersList.size(); i++) {
				tendersOidArray[i] = tendersList.get(i).get("oid");
				tendersNameArray[i] = tendersList.get(i).get("tendersName");
			}
			if (dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					String tendersOid = (String)dataList.get(i).get("tendersOid");
					int index = getIndex(tendersOidArray, tendersOid);
					if (index != -1) {
						countArray[index] = Integer.parseInt(dataList.get(i).get("totalCount").toString());
						double qualifiedRate = Double.parseDouble(dataList.get(i).get("qualifiedRate").toString());
						BigDecimal bg = new BigDecimal(qualifiedRate);
						qualifiedRate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						rateArray[index] = qualifiedRate;
					}
				}
			}
		}
		map.put("tendersOids", tendersOidArray);
		map.put("tendersNames", tendersNameArray);
		map.put("count", countArray);
		map.put("rate", rateArray);
		return map;
	}

	/**
	 * <p>功能描述：项目各标段焊接不合格口数对比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午1:28:58。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getEachTendersUnQualifiedRateByProjects(String projectOid, String date) {
		//封装返回值
		Map<String, Object> map = new HashMap<>();
		//根据项目查询标段oid和对应的名称
		List<Map<String,String>> tendersList = progressStatsDao.getTendersList(projectOid);
		//根据项目和日期查询各标段不合格口数占比
		List<Map<String, Object>> dataList = qualityStatsDao.getEachTendersUnQualifiedRateByProjects(projectOid,date);
		String[] tendersOidArray = new String[tendersList.size()];
		String[] tendersNameArray = new String[tendersList.size()];
		Double[] rateArray = new Double[tendersList.size()];
		Arrays.fill(rateArray, 0.0);
		Integer[] countArray = new Integer[tendersList.size()];
		Arrays.fill(countArray, 0);
		//判断项目下是否有标段
		if (tendersList.size() > 0) {
			for (int i = 0; i < tendersList.size(); i++) {
				tendersOidArray[i] = tendersList.get(i).get("oid");
				tendersNameArray[i] = tendersList.get(i).get("tendersName");
			}
			if (dataList.size() > 0) {
				for (int i = 0; i < dataList.size(); i++) {
					String tendersOid = (String)dataList.get(i).get("tendersOid");
					int index = getIndex(tendersOidArray, tendersOid);
					if (index != -1) {
						countArray[index] = Integer.parseInt(dataList.get(i).get("count").toString());
					}
				}
			}
		}
		map.put("tendersOids", tendersOidArray);
		map.put("tendersNames", tendersNameArray);
		map.put("count", countArray);
		return map;
	}

	/**
	 * <p>功能描述：项目各单位焊接一次合格率对比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午1:41:52。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getEachUnitQualifiedRateByProjects(String projectOid, String date) {
		//封装返回值
		Map<String, Object> map = new HashMap<>();
		//查询项目下所有的施工单位
		List<Map<String, Object>> constructAndProjectUnitList = qualityStatsDao.getConstructUnitByProjectOid(projectOid);
		//根据项目和日期查询标段下施工单位的检测口数和一次合格率
		List<Map<String, Object>> dataList = qualityStatsDao.getEachUnitQualifiedRateByProjects(projectOid, date);
		String[] unitOidArray = new String[constructAndProjectUnitList.size()];
		String[] unitNameArray = new String[constructAndProjectUnitList.size()];
		Integer[] countArray = new Integer[constructAndProjectUnitList.size()];
		Arrays.fill(countArray, 0);
		Double[] rateArray = new Double[constructAndProjectUnitList.size()];
		Arrays.fill(rateArray, 0.0);
		if (constructAndProjectUnitList.size() > 0) {
			for (int i = 0; i < constructAndProjectUnitList.size(); i++) {
				unitOidArray[i] = (String) constructAndProjectUnitList.get(i).get("key");
				unitNameArray[i] = (String) constructAndProjectUnitList.get(i).get("value");
			}
			if (dataList.size() > 0) {
				for (int h = 0; h < dataList.size(); h++) {
					int index = getIndex(unitOidArray, (String)dataList.get(h).get("constructUnit"));
					if (index != -1) {
						countArray[index] = Integer.parseInt(dataList.get(h).get("totalCount").toString());
						double qualifiedRate = Double.parseDouble(dataList.get(h).get("qualifiedRate").toString());
						BigDecimal bg = new BigDecimal(qualifiedRate);
						qualifiedRate = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
						rateArray[index] = qualifiedRate;
					}
				}
			}
		}
		map.put("unitOids", unitOidArray);
		map.put("unitNames", unitNameArray);
		map.put("count", countArray);
		map.put("rate", rateArray);
		return map;
	}

	/**
	 * <p>功能描述：项目各单位焊接不合格口数对比。</p>
	  * <p> 葛建。</p>	
	  * @param projectOid
	  * @param date
	  * @return
	  * @since JDK1.8。
	  * <p>创建日期:2018年12月17日 下午2:53:34。</p>
	  * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
	 */
	public Map<String, Object> getEachUnitUnQualifiedRateByProjects(String projectOid, String date) {
		//封装返回值
		Map<String, Object> map = new HashMap<>();
		//查询项目下所有的施工单位
		List<Map<String, Object>> constructUnitList = qualityStatsDao.getConstructUnitByProjectOid(projectOid);
		//根据项目和日期查询各单位不合格口数占比
		List<Map<String, Object>> dataList = qualityStatsDao.getEachUnitUnQualifiedRateByProjects(projectOid,date);
		String[] unitOidArray = new String[constructUnitList.size()];
		String[] unitNameArray = new String[constructUnitList.size()];
		Double[] rateArray = new Double[constructUnitList.size()];
		Arrays.fill(rateArray, 0.0);
		Integer[] countArray = new Integer[constructUnitList.size()];
		Arrays.fill(countArray, 0);
		if (constructUnitList.size() > 0) {
			for (int i = 0; i < constructUnitList.size(); i++) {
				unitOidArray[i] = (String) constructUnitList.get(i).get("key");
				unitNameArray[i] = (String) constructUnitList.get(i).get("value");
			}
			if (dataList.size() > 0) {
				for (int j = 0; j < dataList.size(); j++) {
					int index = getIndex(unitOidArray, (String)dataList.get(j).get("constructUnit"));
					if (index != -1) {
						countArray[index] = Integer.parseInt(dataList.get(j).get("count").toString());
					}
				}
			}
		}
		map.put("tendersOids", unitOidArray);
		map.put("tendersNames", unitNameArray);
		map.put("count", countArray);
		return map;
	}
	
}
