package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/18 15:19
 */
public class DetectionRayBo {

    private String oid;

    private String weldOid;

    private String detectionType;

    private Integer evaluationResult;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getWeldOid() {
        return weldOid;
    }

    public void setWeldOid(String weldOid) {
        this.weldOid = weldOid;
    }

    public String getDetectionType() {
        return detectionType;
    }

    public void setDetectionType(String detectionType) {
        this.detectionType = detectionType;
    }

    public Integer getEvaluationResult() {
        return evaluationResult;
    }

    public void setEvaluationResult(Integer evaluationResult) {
        this.evaluationResult = evaluationResult;
    }
}
