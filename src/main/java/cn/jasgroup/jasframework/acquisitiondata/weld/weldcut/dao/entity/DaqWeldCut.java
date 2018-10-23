package cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.dao.entity;

import java.util.Date;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.dao.entity.ConstructionWeld;
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
  *<p>类描述：焊口割口entity。</p>
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.service.WeldCutService #saveChanageWledStatus()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.service.WeldCutService #updateChanageWledStatusBeforeAdvice()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.service.WeldCutService #updateChanageWledStatus()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.weldcut.service.WeldCutService #deleteChanageWledStatus()}
  *{@link cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.service.WeldService #formatting()}
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年8月21日 下午5:37:44。</p>
 */
@CommonSaveConfig(scene = "/weldCut/save",
	afterAdvice = {
		@Process(service = "weldCutService", method = "saveChanageWledStatus()")
})
@CommonUpdateConfig(scene = "/weldCut/update",
	beforeAdvice={
	@Process(service = "weldCutService", method = "updateChanageWledStatusBeforeAdvice()")
	},
	afterAdvice={
	@Process(service = "weldCutService", method = "updateChanageWledStatus()")
})
@CommonDeleteConfig(scene = "/weldCut/delete",
	afterAdvice = {
		@Process(service = "weldCutService", method = "deleteChanageWledStatus()")
})
@UniqueConstraints(
	strategys ={
		@UniqueConstraintStrategy(
			columnNames={"pipeSegmentOrCrossOid","weldOid"},
			name="同一线路段/穿跨越下割口编号"
		)
	},
	formatter = @Process(service = "weldService" , method = "formatting()")
)
@JdbcEntity(name="daq_weld_cut")
public class DaqWeldCut extends CommonJdbcEntity {

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
	 * 割口编号 
	 */
	private String weldOid; 

	/**
	 * 新焊口编号1 
	 */
	private String frontWeldOid; 

	/**
	 * 新焊口编号2 
	 */
	private String backWeldOid; 

	/**
	 * 割口日期 
	 */
	private Date cutWeldDate; 

	/**
	 * 施工单位 
	 */
	private String constructUnit; 

	/**
	 * 施工机组代号 
	 */
	private String workUnitOid; 

	/**
	 * 监理单位 
	 */
	private String supervisionUnit; 

	/**
	 * 监理工程师 
	 */
	private String supervisionEngineer; 

	/**
	 * 数据采集人 
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
	
	/**
	 * 审核状态 
	 */
	private Integer approveStatus = 0;

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

	public String getFrontWeldOid() {
		return frontWeldOid; 
	}

	public void setFrontWeldOid(String frontWeldOid) {
		this.frontWeldOid = frontWeldOid; 
		super.setField("frontWeldOid");
	}

	public String getBackWeldOid() {
		return backWeldOid; 
	}

	public void setBackWeldOid(String backWeldOid) {
		this.backWeldOid = backWeldOid; 
		super.setField("backWeldOid");
	}

	public Date getCutWeldDate() {
		return cutWeldDate; 
	}

	public void setCutWeldDate(Date cutWeldDate) {
		this.cutWeldDate = cutWeldDate; 
		super.setField("cutWeldDate");
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

	public Date getCollectionDate() {
		return collectionDate; 
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate; 
		super.setField("collectionDate");
	}


	public String getRemarks() {
		return remarks; 
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks; 
		super.setField("remarks");
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
		super.setField("approveStatus");
	}
	

}