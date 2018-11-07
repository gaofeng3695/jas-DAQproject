package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo;

/**
 * description: 数据审核各分类下个数据统计
 *
 * @author xiefayang
 * 2018/8/29 14:07
 */
public class DataApproveSubBo {

    /** 统计code */
    private String code;

    /** 中文名 */
    private String cnName;

    /** 统计分类的code */
    private String categoryCode;

    /** 总数量 */
    private Integer total ;

    /** 未审核数量 */
    private Integer unaudited ;

    public DataApproveSubBo() {
    }

    public DataApproveSubBo(String code, String categoryCode, Integer total, Integer unaudited) {
        this.code = code;
        this.categoryCode = categoryCode;
        this.total = total;
        this.unaudited = unaudited;
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

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
