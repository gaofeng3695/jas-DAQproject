package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.query.bo;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;

/**
 * 
  *<p>类描述：焊口返修Bo。</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月10日 上午8:57:12。</p>
 */
public class ReworkWeldBo extends CommonBaseBo {

	/**
	 * oid
	 */
	private String oid;
	
	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 项目名称
	 */
	private String projectName;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 管线名称
	 */
	private String pipelineName;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段名称
	 */
	private String tendersName;

	/** 
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid; 
	
	/**
	 * 线路段/穿跨越名称
	 */
	private String name; 

	/** 
	 * 返修口编号
	 */
	private String weldOid; 

	/**
	 *  返修后焊口编号
	 */
	private String reworkWeldCode; 

	/** 
	 * 焊条批号
	 */
	private String weldRodBatchNum; 

	/**
	 *  焊丝批号
	 */
	private String weldWireBatchNum; 

	/**
	 *  焊接工艺规程
	 */
	private String weldProduce; 

	/** 
	 * 填充人员 
	 */
	private String coverOid; 

	/** 
	 * 打底人员
	 */
	private String padderOid; 

	/** 
	 * 盖面人员
	 */
	private String renderOid; 

	/**
	 *  焊接日期
	 */
	private Date weldDate; 

	/** 
	 * 施工单位
	 */
	private String constructUnit; 

	/**
	 *  施工机组代号
	 */
	private String workUnitOid; 

	/**
	 * 监理单位
	 */
	private String supervisionUnit; 

	/**
	 *  监理工程师
	 */
	private String supervisionEngineer; 

	/** 
	 * 采集人员 
	 */
	private String collectionPerson; 

	/** 
	 * 采集日期
	 */
	private Date collectionDate; 

	/** 
	 * 备注
	 */
	private String remarks;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
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

	public String getPipelineOid() {
		return pipelineOid;
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid;
	}

	public String getPipelineName() {
		return pipelineName;
	}

	public void setPipelineName(String pipelineName) {
		this.pipelineName = pipelineName;
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

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid;
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeldOid() {
		return weldOid;
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid;
	}

	public String getReworkWeldCode() {
		return reworkWeldCode;
	}

	public void setReworkWeldCode(String reworkWeldCode) {
		this.reworkWeldCode = reworkWeldCode;
	}

	public String getWeldRodBatchNum() {
		return weldRodBatchNum;
	}

	public void setWeldRodBatchNum(String weldRodBatchNum) {
		this.weldRodBatchNum = weldRodBatchNum;
	}

	public String getWeldWireBatchNum() {
		return weldWireBatchNum;
	}

	public void setWeldWireBatchNum(String weldWireBatchNum) {
		this.weldWireBatchNum = weldWireBatchNum;
	}

	public String getWeldProduce() {
		return weldProduce;
	}

	public void setWeldProduce(String weldProduce) {
		this.weldProduce = weldProduce;
	}

	public String getCoverOid() {
		return coverOid;
	}

	public void setCoverOid(String coverOid) {
		this.coverOid = coverOid;
	}

	public String getPadderOid() {
		return padderOid;
	}

	public void setPadderOid(String padderOid) {
		this.padderOid = padderOid;
	}

	public String getRenderOid() {
		return renderOid;
	}

	public void setRenderOid(String renderOid) {
		this.renderOid = renderOid;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getWeldDate() {
		return weldDate;
	}

	public void setWeldDate(Date weldDate) {
		this.weldDate = weldDate;
	}

	public String getConstructUnit() {
		return constructUnit;
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit;
	}

	public String getWorkUnitOid() {
		return workUnitOid;
	}

	public void setWorkUnitOid(String workUnitOid) {
		this.workUnitOid = workUnitOid;
	}

	public String getSupervisionUnit() {
		return supervisionUnit;
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit;
	}

	public String getSupervisionEngineer() {
		return supervisionEngineer;
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer;
	}

	public String getCollectionPerson() {
		return collectionPerson;
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	} 
	
}
