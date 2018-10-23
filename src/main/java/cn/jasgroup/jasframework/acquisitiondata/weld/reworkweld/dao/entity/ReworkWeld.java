package cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.dao.entity;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteBatchConfig;
import cn.jasgroup.jasframework.base.annotation.CommonDeleteConfig;
import cn.jasgroup.jasframework.base.annotation.CommonSaveConfig;
import cn.jasgroup.jasframework.base.annotation.CommonUpdateConfig;
import cn.jasgroup.jasframework.base.annotation.JdbcEntity;
import cn.jasgroup.jasframework.base.annotation.Process;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraintStrategy;
import cn.jasgroup.jasframework.base.annotation.UniqueConstraints;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

/**
 * 
  *<p>类描述：焊口返修实体。
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.service.ReworkWeldService #saveChangeWeldStatus()}</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.service.ReworkWeldService #updateChangeWeldStatusBeforeAdvice()}</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.service.ReworkWeldService #updateChangeWeldStatus()}</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.reworkweld.service.ReworkWeldService #deleteChangeWeldStatus()}</p>
  * @author 葛建 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月6日 下午5:35:16。</p>
 */
@CommonSaveConfig(
	scene = "/reworkWeld/save",
	afterAdvice={
		@Process(service = "reworkWeldService", method = "saveChangeWeldStatus()")
	}
)
@CommonUpdateConfig(
	scene = "/reworkWeld/update",
	beforeAdvice={
		@Process(service = "reworkWeldService", method = "updateChangeWeldStatusBeforeAdvice()")
	},
	afterAdvice={
		@Process(service = "reworkWeldService", method = "updateChangeWeldStatus()")
	}
)
@CommonDeleteConfig(
	scene = "/reworkWeld/delete",
	afterAdvice = {
			@Process(service = "reworkWeldService", method = "deleteChangeWeldStatus()")
	}
)
@UniqueConstraints(
	strategys ={
		@UniqueConstraintStrategy(	
			columnNames={"pipeSegmentOrCrossOid","reworkWeldCode"},
			name="同一线路段/穿跨越下返修口编号不能重复",
			foreignClass=ConstructionWeld.class,
			foreignField="weldCode"
		)
	}
)
@CommonDeleteBatchConfig(
		scene = "/reworkWeld/deleteBatch"
)
@JdbcEntity(name="daq_weld_rework_weld")
public class ReworkWeld extends CommonJdbcEntity{

	/** 
	 * 项目oid
	 */
	private String projectOid; 

	/** 
	 * 管线oid
	 */
	private String pipelineOid; 

	/** 
	 * 标段oid
	 */
	private String tendersOid; 

	/** 
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid; 

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
	 * 审核状态
	 */
	private Integer approveStatus = 0;

	/** 
	 * 备注
	 */
	private String remarks; 

	public String getProjectOid() {
		return projectOid; 
	}

	public void setProjectOid(String projectOid) {
		this.projectOid = projectOid; 
		super.setField("projectOid");
	}

	public String getPipelineOid() {
		return pipelineOid; 
	}

	public void setPipelineOid(String pipelineOid) {
		this.pipelineOid = pipelineOid; 
		super.setField("pipelineOid"); 
	}

	public String getTendersOid() {
		return tendersOid; 
	}

	public void setTendersOid(String tendersOid) {
		this.tendersOid = tendersOid; 
		super.setField("tendersOid"); 
	}

	public String getPipeSegmentOrCrossOid() {
		return pipeSegmentOrCrossOid; 
	}

	public void setPipeSegmentOrCrossOid(String pipeSegmentOrCrossOid) {
		this.pipeSegmentOrCrossOid = pipeSegmentOrCrossOid; 
		super.setField("pipeSegmentOrCrossOid"); 
	}

	public String getWeldOid() {
		return weldOid; 
	}

	public void setWeldOid(String weldOid) {
		this.weldOid = weldOid; 
		super.setField("weldOid"); 
	}

	public String getReworkWeldCode() {
		return reworkWeldCode; 
	}

	public void setReworkWeldCode(String reworkWeldCode) {
		this.reworkWeldCode = reworkWeldCode; 
		super.setField("reworkWeldCode");
	}

	public String getWeldRodBatchNum() {
		return weldRodBatchNum; 
	}

	public void setWeldRodBatchNum(String weldRodBatchNum) {
		this.weldRodBatchNum = weldRodBatchNum; 
		super.setField("weldRodBatchNum");
	}

	public String getWeldWireBatchNum() {
		return weldWireBatchNum; 
	}

	public void setWeldWireBatchNum(String weldWireBatchNum) {
		this.weldWireBatchNum = weldWireBatchNum; 
		super.setField("weldWireBatchNum");
	}

	public String getWeldProduce() {
		return weldProduce; 
	}

	public void setWeldProduce(String weldProduce) {
		this.weldProduce = weldProduce; 
		super.setField("weldProduce");
	}

	public String getCoverOid() {
		return coverOid; 
	}

	public void setCoverOid(String coverOid) {
		this.coverOid = coverOid; 
		super.setField("coverOid");
	}

	public String getPadderOid() {
		return padderOid; 
	}

	public void setPadderOid(String padderOid) {
		this.padderOid = padderOid; 
		super.setField("padderOid");
	}

	public String getRenderOid() {
		return renderOid; 
	}

	public void setRenderOid(String renderOid) {
		this.renderOid = renderOid; 
		super.setField("renderOid");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getWeldDate() {
		return weldDate; 
	}

	public void setWeldDate(Date weldDate) {
		this.weldDate = weldDate; 
		super.setField("weldDate");
	}

	public String getConstructUnit() {
		return constructUnit; 
	}

	public void setConstructUnit(String constructUnit) {
		this.constructUnit = constructUnit; 
		super.setField("constructUnit");
	}

	public String getWorkUnitOid() {
		return workUnitOid; 
	}

	public void setWorkUnitOid(String workUnitOid) {
		this.workUnitOid = workUnitOid; 
		super.setField("workUnitOid");
	}

	public String getSupervisionUnit() {
		return supervisionUnit; 
	}

	public void setSupervisionUnit(String supervisionUnit) {
		this.supervisionUnit = supervisionUnit; 
		super.setField("supervisionUnit");
	}

	public String getSupervisionEngineer() {
		return supervisionEngineer; 
	}

	public void setSupervisionEngineer(String supervisionEngineer) {
		this.supervisionEngineer = supervisionEngineer; 
		super.setField("supervisionEngineer");
	}

	public String getCollectionPerson() {
		return collectionPerson; 
	}

	public void setCollectionPerson(String collectionPerson) {
		this.collectionPerson = collectionPerson; 
		super.setField("collectionPerson");
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCollectionDate() {
		return collectionDate; 
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate; 
		super.setField("collectionDate");
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}
}
