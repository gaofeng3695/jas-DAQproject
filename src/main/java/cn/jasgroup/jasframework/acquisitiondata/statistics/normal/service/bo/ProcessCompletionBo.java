package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * description: 工序累计完成情况
 *
 * @author xiefayang
 * 2018/9/11 17:02
 */
public class ProcessCompletionBo {

    /** 管材长度 */
    private Integer pipeLength;

    /** 测量放线长度 */
    private Integer laySurveyingLength;

    /** 焊接长度 */
    private Integer weldLength;

    /** 补口长度 */
    private Integer buckleLength;

    /** 管沟回填长度 */
    private Integer pipeTrenchBackFillLength;

    /** 地貌恢复长度 */
    private Integer layLandRestorationLength;

    public Integer getPipeLength() {
        return pipeLength;
    }

    public void setPipeLength(Integer pipeLength) {
        this.pipeLength = pipeLength;
    }

    public Integer getLaySurveyingLength() {
        return laySurveyingLength;
    }

    public void setLaySurveyingLength(Integer laySurveyingLength) {
        this.laySurveyingLength = laySurveyingLength;
    }

    public Integer getWeldLength() {
        return weldLength;
    }

    public void setWeldLength(Integer weldLength) {
        this.weldLength = weldLength;
    }

    public Integer getBuckleLength() {
        return buckleLength;
    }

    public void setBuckleLength(Integer buckleLength) {
        this.buckleLength = buckleLength;
    }

    public Integer getPipeTrenchBackFillLength() {
        return pipeTrenchBackFillLength;
    }

    public void setPipeTrenchBackFillLength(Integer pipeTrenchBackFillLength) {
        this.pipeTrenchBackFillLength = pipeTrenchBackFillLength;
    }

    public Integer getLayLandRestorationLength() {
        return layLandRestorationLength;
    }

    public void setLayLandRestorationLength(Integer layLandRestorationLength) {
        this.layLandRestorationLength = layLandRestorationLength;
    }
}
