package cn.jasgroup.jasframework.acquisitiondata.weld.weldinfo.query.bo;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.jasgroup.jasframework.base.data.BaseBo;
import cn.jasgroup.jasframework.support.ThreadLocalHolder;

/**
 * 
  *<p>类描述：焊口信息。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年9月20日 上午10:28:52。</p>
 */
public class ConstructionWeldAppBo extends BaseBo {
	/**
	 * oid
	 */
	private String oid;
	/**
	 * 项目oid
	 */
	private String projectOid;

	/**
	 * 项目编号
	 */
	private String projectName;

	/**
	 * 管线oid
	 */
	private String pipelineOid;

	/**
	 * 管线编号
	 */
	private String pipelineName;

	/**
	 * 标段oid
	 */
	private String tendersOid;

	/**
	 * 标段编号
	 */
	private String tendersName;

	/**
	 * 线路段/穿跨越
	 */
	private String pipeSegmentOrCrossOid;

	/**
	 * 线路段/穿跨越名称
	 */
	private String pipeSegmentOrCrossName;
	/**
	 * 焊口编号
	 */
	private String weldCode;
	
	/** X坐标 */
	private Double pointx; 

	/** X坐标 */
	private Double pointy; 

	/** 高程 */
	private Double pointz; 
	/**
	 * 里程
	 */
	private Double mileage;
	/**
	 *	是否补扣
	 */
	private int isAnticorrosionCheck;
	/**
	 * 是否返修
	 */
	private int isRework;
	
	/**
	 * 施工单位名称
	 */
	private String constructUnitName;
	
	/**
	 * 施工机组编号
	 */
	private String workUnitCode;
	
	/**
	 * 施工日期
	 */
	private Date constructDate;
	
	/**
	 * 监理单位名称
	 */
	private String supervisionUnitName;

	/***
	 * 是否测量
	 */
	private Integer isMeasure;
	
	/**
	 * 是否割口
	 */
	private Integer isCut;
	
	/***
	 * 审核状态
	 */
	private Integer approveStatus;
	
	private String medianStakeOid;
	
	private String relativeMileage;
	
	private String userId = ThreadLocalHolder.getCurrentUserId();

	// add by cuixianing 2019-3-5
	/**
	 * 气流方向
	 */
	private Integer airflowDirection;

    /**
     * 气流方向名称
     */
    private String airflowDirectionName;

	/**
	 * 焊口顺序号1
	 */
	private Double sequenceNumberFirst;

	/**
	 * 焊口顺序号2
	 */
	private Double sequenceNumberSecond;

    public String getAirflowDirectionName() {
        return airflowDirectionName;
    }

    public void setAirflowDirectionName(String airflowDirectionName) {
        this.airflowDirectionName = airflowDirectionName;
    }

    public Integer getAirflowDirection() {
        return airflowDirection;
    }

    public void setAirflowDirection(Integer airflowDirection) {
        this.airflowDirection = airflowDirection;
    }

    public Double getSequenceNumberFirst() {
        return sequenceNumberFirst;
    }

    public void setSequenceNumberFirst(Double sequenceNumberFirst) {
        this.sequenceNumberFirst = sequenceNumberFirst;
    }

    public Double getSequenceNumberSecond() {
        return sequenceNumberSecond;
    }

    public void setSequenceNumberSecond(Double sequenceNumberSecond) {
        this.sequenceNumberSecond = sequenceNumberSecond;
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
	public String getPipeSegmentOrCrossName() {
		return pipeSegmentOrCrossName;
	}
	public void setPipeSegmentOrCrossName(String pipeSegmentOrCrossName) {
		this.pipeSegmentOrCrossName = pipeSegmentOrCrossName;
	}
	public String getWeldCode() {
		return weldCode;
	}
	public void setWeldCode(String weldCode) {
		this.weldCode = weldCode;
	}
	public Double getPointx() {
		return pointx;
	}
	public void setPointx(Double pointx) {
		this.pointx = pointx;
	}
	public Double getPointy() {
		return pointy;
	}
	public void setPointy(Double pointy) {
		this.pointy = pointy;
	}
	public Double getPointz() {
		return pointz;
	}
	public void setPointz(Double pointz) {
		this.pointz = pointz;
	}
	public Double getMileage() {
		return mileage;
	}
	public void setMileage(Double mileage) {
		this.mileage = mileage;
	}
	public int getIsAnticorrosionCheck() {
		return isAnticorrosionCheck;
	}
	public void setIsAnticorrosionCheck(int isAnticorrosionCheck) {
		this.isAnticorrosionCheck = isAnticorrosionCheck;
	}
	public int getIsRework() {
		return isRework;
	}
	public void setIsRework(int isRework) {
		this.isRework = isRework;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getConstructUnitName() {
		return constructUnitName;
	}
	public void setConstructUnitName(String constructUnitName) {
		this.constructUnitName = constructUnitName;
	}
	public String getWorkUnitCode() {
		return workUnitCode;
	}
	public void setWorkUnitCode(String workUnitCode) {
		this.workUnitCode = workUnitCode;
	}
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getConstructDate() {
		return constructDate;
	}
	public void setConstructDate(Date constructDate) {
		this.constructDate = constructDate;
	}
	public String getSupervisionUnitName() {
		return supervisionUnitName;
	}
	public void setSupervisionUnitName(String supervisionUnitName) {
		this.supervisionUnitName = supervisionUnitName;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}

	public Integer getIsMeasure() {
		return isMeasure;
	}

	public void setIsMeasure(Integer isMeasure) {
		this.isMeasure = isMeasure;
	}

	public Integer getIsCut() {
		return isCut;
	}
	public void setIsCut(Integer isCut) {
		this.isCut = isCut;
	}
	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	public String getMedianStakeOid() {
		return medianStakeOid;
	}
	public void setMedianStakeOid(String medianStakeOid) {
		this.medianStakeOid = medianStakeOid;
	}
	public String getRelativeMileage() {
		return relativeMileage;
	}
	public void setRelativeMileage(String relativeMileage) {
		this.relativeMileage = relativeMileage;
	} 
	
	
}
