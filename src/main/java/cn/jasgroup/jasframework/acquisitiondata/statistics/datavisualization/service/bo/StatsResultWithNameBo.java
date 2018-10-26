package cn.jasgroup.jasframework.acquisitiondata.statistics.datavisualization.service.bo;

import cn.jasgroup.jasframework.acquisitiondata.statistics.normal.service.bo.StatsResultBo;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/10/24 10:03
 */
public class StatsResultWithNameBo extends StatsResultBo {

    private String cnName;

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
