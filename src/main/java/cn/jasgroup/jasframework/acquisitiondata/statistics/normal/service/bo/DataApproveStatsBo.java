package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

import java.util.List;

/**
 * description: 数据审核统计实体
 *
 * @author xiefayang
 * 2018/8/29 14:03
 */
public class DataApproveStatsBo {

    /** 统计code */
    private String code;

    /** 中文名 */
    private String cnName;

    /** 总树木 */
    private Integer total;

    /** 未审核树木 */
    private Integer unaudited;

    private List<DataApproveSubBo> dataApproveSubBoList;


    public DataApproveStatsBo() {
    }

    public DataApproveStatsBo(String code, String cnName, Integer total, Integer unaudited, List<DataApproveSubBo> dataApproveSubBoList) {
        this.code = code;
        this.cnName = cnName;
        this.total = total;
        this.unaudited = unaudited;
        this.dataApproveSubBoList = dataApproveSubBoList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public Integer getTotal() {
        return total==null ? 0:total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUnaudited() {
        return unaudited==null ? 0:unaudited;
    }

    public void setUnaudited(Integer unaudited) {
        this.unaudited = unaudited;
    }

    public List<DataApproveSubBo> getDataApproveSubBoList() {
        return dataApproveSubBoList;
    }

    public void setDataApproveSubBoList(List<DataApproveSubBo> dataApproveSubBoList) {
        this.dataApproveSubBoList = dataApproveSubBoList;
    }
}
