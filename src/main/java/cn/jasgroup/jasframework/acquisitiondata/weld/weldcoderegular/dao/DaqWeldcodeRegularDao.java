package cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.entity.DaqWeldcodeRegular;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>类描述：</p>。
 *
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年03月04日 14:52。</p>
 */
@Repository
public class DaqWeldcodeRegularDao extends BaseJdbcDao {

    @Resource
    private BaseJdbcTemplate baseJdbcTemplate;

    /**
     * <p>功能描述: 查询焊口规则列表。</p>
     * <p> cuixianing。</p>
     * @param projectOid
     * @since JDK1.8
     * <p>创建日期:2019/3/4 15:56</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public List<DaqWeldcodeRegular> getDaqWeldcodeRegularList(String projectOid){
        List<String> args = new ArrayList<>();

        String sql = "select wc.*,pro.project_name from  daq_weldcode_regular wc  " +
                "LEFT JOIN (SELECT oid, project_name, active FROM daq_project where active=1) pro ON pro.oid = wc.project_oid " +
                "where wc.active=1 ";

        if(StringUtils.isNotBlank(projectOid)){
            sql += " and wc.project_oid=? ";
            args.add(projectOid);
        }
        List list = baseJdbcTemplate.queryForList(sql, args.toArray(), DaqWeldcodeRegular.class);
        return list;
    }
}
