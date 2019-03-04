package cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.entity;
import cn.jasgroup.jasframework.base.annotation.*;
import cn.jasgroup.jasframework.engine.jdbc.entity.CommonJdbcEntity;

import javax.persistence.Transient;

/**
 * <p>类描述：</p>。
 *
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年03月04日 10:51。</p>
 */
@CommonSaveConfig(
        scene = "/weldCodeRegular/save"
)
@CommonUpdateConfig(
        scene = "/weldCodeRegular/update"
)
@CommonDeleteConfig(
        scene = "/weldCodeRegular/delete"
)
@UniqueConstraints(
        strategys = {
                @UniqueConstraintStrategy(
                        columnNames = {"projectOid"},
                        name = "项目名称不能重复"
                )
        }
)
@JdbcEntity(name="daq_weldcode_regular")
public class DaqWeldcodeRegular extends CommonJdbcEntity {

    /**
     * 项目oid
     */
    private String projectOid;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 焊口规则
     */
    private String weldcodeRegular;

    /**
     * 备注
     */
    private String remarks;

    @Transient
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
        super.setField("projectOid");
    }

    public String getWeldcodeRegular() {
        return weldcodeRegular;
    }

    public void setWeldcodeRegular(String weldcodeRegular) {
        this.weldcodeRegular = weldcodeRegular;
        super.setField("weldcodeRegular");
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
        super.setField("remarks");
    }

}