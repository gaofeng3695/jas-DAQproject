package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo;

/**
 * 人员填报情况DTO
 */
public class PersonFillBo {

    private Integer no;

    private String unitId;

    private String unitName;

    private Integer entryCount;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(Integer entryCount) {
        this.entryCount = entryCount;
    }
}
