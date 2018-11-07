package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 人员填报情况DTO
 * @author xiefayang
 */
public class PersonFillBo {

    private Integer no;

    @JsonIgnore
    private String userId;

    private String userName;

    @JsonIgnore
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
