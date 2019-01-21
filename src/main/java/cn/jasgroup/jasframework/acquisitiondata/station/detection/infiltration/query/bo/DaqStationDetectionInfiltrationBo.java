package cn.jasgroup.jasframework.acquisitiondata.station.detection.infiltration.query.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.jasgroup.jasframework.base.data.CommonBaseBo;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>渗透检测(站场)主表bo</p>
 * @author cuixianing
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-14 10:27:40。</p>
 */
public class DaqStationDetectionInfiltrationBo extends CommonBaseBo {
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
     * 标段oid
     */
    private String tendersOid;

    /**
     * 标段名称
     */
    private String tendersName;

    /**
     * 管线oid
     */
    private String pipelineOid;

    /**
     * 管线名称
     */
    private String pipelineName;

    /**
     * 站场/阀室编号
     */
    private String pipeStationOid;

    /**
     * 站场/阀室名称
     */
    private String pipeStationName;

    /**
     * 焊口oid
     */
    private String weldOid;

    /**
     * 焊口编号
     */
    private String weldCode;

    /**
     * 检测报告编号
     */
    private String detectionReportNum;

    /**
     * 检测日期
     */
    private Date detectionDate;

    /**
     * 检测长度(mm)
     */
    private Double detectionLength;

    /**
     * 检测类型，域值：detection_type_domain
     */
    private String detectionType;

    /**
     * 检测类型名称
     */
    private String detectionTypeName;

    /**
     * 评定等级，域值：evaluation_grade_domain
     */
    private String evaluationGrade;

    /**
     * 评定等级名称
     */
    private String evaluationGradeName;

    /**
     * 评定结果{0:不合格;1:合格}
     */
    private Integer evaluationResult;

    /**
     * 评定结果名称
     */
    private String evaluationResultName;

    /**
     * 检测单位
     */
    private String detectionUnit;

    /**
     * 检测单位名称
     */
    private String detectionUnitName;

    /**
     * 检测人员
     */
    private String detectionPerson;

    /**
     * 监理单位
     */
    private String supervisionUnit;

    /**
     * 监理单位名称
     */
    private String supervisionUnitName;

    /**
     * 监理工程师
     */
    private String supervisionEngineer;

    /**
     * 采集日期
     */
    private Date collectionDate;

    /**
     * 审核状态
     */
    private Integer approveStatus;

    /**
     * 审核状态名称
     */
    private String approveStatusName;

    /**
     * 备注
     */
    private String remarks;

    /**
     *子表集合
     */
    private List<DaqStationDetectionInfiltrationSubBo> infiltrationSubList = new ArrayList<DaqStationDetectionInfiltrationSubBo>();

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
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

    public String getPipeStationOid() {
        return pipeStationOid;
    }

    public void setPipeStationOid(String pipeStationOid) {
        this.pipeStationOid = pipeStationOid;
    }

    public String getPipeStationName() {
        return pipeStationName;
    }

    public void setPipeStationName(String pipeStationName) {
        this.pipeStationName = pipeStationName;
    }

    public String getWeldOid() {
        return weldOid;
    }

    public void setWeldOid(String weldOid) {
        this.weldOid = weldOid;
    }

    public String getWeldCode() {
        return weldCode;
    }

    public void setWeldCode(String weldCode) {
        this.weldCode = weldCode;
    }

    public String getDetectionReportNum() {
        return detectionReportNum;
    }

    public void setDetectionReportNum(String detectionReportNum) {
        this.detectionReportNum = detectionReportNum;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Date detectionDate) {
        this.detectionDate = detectionDate;
    }

    public Double getDetectionLength() {
        return detectionLength;
    }

    public void setDetectionLength(Double detectionLength) {
        this.detectionLength = detectionLength;
    }

    public String getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(String detectionType) {
        this.detectionType = detectionType;
    }

    public String getDetectionTypeName() {
        return detectionTypeName;
    }

    public void setDetectionTypeName(String detectionTypeName) {
        this.detectionTypeName = detectionTypeName;
    }

    public String getEvaluationGrade() {
        return evaluationGrade;
    }

    public void setEvaluationGrade(String evaluationGrade) {
        this.evaluationGrade = evaluationGrade;
    }

    public String getEvaluationGradeName() {
        return evaluationGradeName;
    }

    public void setEvaluationGradeName(String evaluationGradeName) {
        this.evaluationGradeName = evaluationGradeName;
    }

    public Integer getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(Integer evaluationResult) {
        this.evaluationResult = evaluationResult;
    }

    public String getDetectionUnit() {
        return detectionUnit;
    }

    public void setDetectionUnit(String detectionUnit) {
        this.detectionUnit = detectionUnit;
    }

    public String getDetectionUnitName() {
        return detectionUnitName;
    }

    public void setDetectionUnitName(String detectionUnitName) {
        this.detectionUnitName = detectionUnitName;
    }

    public String getDetectionPerson() {
        return detectionPerson;
    }

    public void setDetectionPerson(String detectionPerson) {
        this.detectionPerson = detectionPerson;
    }

    public String getSupervisionUnit() {
        return supervisionUnit;
    }

    public void setSupervisionUnit(String supervisionUnit) {
        this.supervisionUnit = supervisionUnit;
    }

    public String getSupervisionUnitName() {
        return supervisionUnitName;
    }

    public void setSupervisionUnitName(String supervisionUnitName) {
        this.supervisionUnitName = supervisionUnitName;
    }

    public String getSupervisionEngineer() {
        return supervisionEngineer;
    }

    public void setSupervisionEngineer(String supervisionEngineer) {
        this.supervisionEngineer = supervisionEngineer;
    }

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
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
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public List<DaqStationDetectionInfiltrationSubBo> getInfiltrationSubList() {
        return infiltrationSubList;
    }

    public void setInfiltrationSubList(List<DaqStationDetectionInfiltrationSubBo> infiltrationSubList) {
        this.infiltrationSubList = infiltrationSubList;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getEvaluationResultName() {
        return evaluationResultName;
    }

    public void setEvaluationResultName(String evaluationResultName) {
        this.evaluationResultName = evaluationResultName;
    }

    public String getApproveStatusName() {
        return approveStatusName;
    }

    public void setApproveStatusName(String approveStatusName) {
        this.approveStatusName = approveStatusName;
    }
}