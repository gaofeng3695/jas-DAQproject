package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common;

/**
  *<p>类描述：进度统计查询结果Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年12月11日 上午9:44:34。</p>
 */
public class ProgressStatsResultBo {
	
	/*
	 * 标段oid
	 */
	private String tendersOid;
	
	/*
	 * 标段名称
	 */
	private String tendersName;
	
	/*
	 * 月
	 */
	private String monthOfYear;
	
	/*
	 * 每月总和
	 */
	private String sumPerMonth;
	
	/*
	 * 每月累计
	 */
	private String totalByMonth;

	public String getTendersOid() {
		return tendersOid;
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid;
	}

	public String getTendersName() {
		return tendersName;
	}

	public void setTendersName(String tendersName) {
		this.tendersName = tendersName;
	}

	public String getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(String monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public String getSumPerMonth() {
		return sumPerMonth;
	}

	public void setSumPerMonth(String sumPerMonth) {
		this.sumPerMonth = sumPerMonth;
	}

	public String getTotalByMonth() {
		return totalByMonth;
	}

	public void setTotalByMonth(String totalByMonth) {
		this.totalByMonth = totalByMonth;
	}
	
}
