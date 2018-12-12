package cn.jasgroup.jasframework.acquisitiondata.statistics.progress.common;

import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.StatsResultBo;

/**
 * <p>
 * 类描述：进度统计查询结果Bo。
 * </p>
 * 
 * @author 葛建 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 *        <p>
 * 		创建日期：2018年12月10日 下午5:27:42。
 *        </p>
 */
public class ProgressStatsQueryBo extends StatsResultBo {

	/*
	 * 统计日期
	 */
	private String statsDate;

	/*
	 * 项目oid
	 */
	private String projectOid;
	
	/*
	 * 项目名称
	 */
	private String projectName;

	/*
	 * 项目oid
	 */
	private String tendersOid;
	
	/*
	 * 项目名称
	 */
	private String tendersName;

	public String getStatsDate() {
		return statsDate;
	}

	public void setStatsDate(String statsDate) {
		this.statsDate = statsDate;
	}

	public String getProjectOid() {
		return projectOid;
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

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

}
