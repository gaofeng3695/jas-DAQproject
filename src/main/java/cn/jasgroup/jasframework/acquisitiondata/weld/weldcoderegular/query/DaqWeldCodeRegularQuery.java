package cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.query;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.entity.DaqWeldCodeRegular;
import cn.jasgroup.jasframework.base.annotation.QueryConfig;
import cn.jasgroup.jasframework.base.data.BaseJavaQuery;
import org.apache.commons.lang.StringUtils;

/**
 * <p>类描述：</p>。
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年03月04日 11:11。</p>
 */
@QueryConfig(
        scene ="/weldCodeRegular/getPage",
        resultClass= DaqWeldCodeRegular.class
)
public class DaqWeldCodeRegularQuery extends BaseJavaQuery {

    /**
     * 项目oid
     */
    private String projectOid;

    @Override
    public String getQuerySql() {
        StringBuffer sql = new StringBuffer();
        sql.append("select wc.*,pro.project_name from daq_weld_code_regular wc  " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid where wc.active=1 ");
        sql.append(conditionSql());
        return sql.toString();
    }

    private String conditionSql() {
        StringBuffer conditionSql = new StringBuffer();
        if (StringUtils.isNotBlank(oid)) {
            conditionSql.append(" and wc.oid=:oid ");
        }else{
            if (StringUtils.isNotBlank(projectOid)) {
                conditionSql.append(" and wc.project_oid = :projectOid ");
            }
        }
        conditionSql.append(" order by wc.create_datetime desc");
        return conditionSql.toString();
    }

    public String getProjectOid() {
        return projectOid;
    }

    public void setProjectOid(String projectOid) {
        this.projectOid = projectOid;
    }
}
