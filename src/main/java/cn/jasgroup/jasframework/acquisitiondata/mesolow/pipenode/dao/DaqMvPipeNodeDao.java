package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.dao;

import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>类描述：</p>。
 *
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月24日 14:37。</p>
 */

@Component
public class DaqMvPipeNodeDao extends BaseJdbcDao {

    @Resource
    private BaseJdbcDao baseJdbcDao;

    /**
     * <p>功能描述: 通过项目与点类型查询节点里列表。</p>
     * <p> cuixianing。</p>
     * @param projectOid, pipeNodeCode
     * @since JDK1.8
     * <p>创建日期:2019/1/24 15:02</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public List<Map<String,Object>> getDaqMvPipeNodeList(String projectOid, String pipeNodeType){
        StringBuffer bufferSql = new StringBuffer();
        List<String> args = new ArrayList<>();
        bufferSql.append("select wc.oid as key,wc.pipe_node_code as value,wc.pointx,wc.pointy,wc.pointz,wc.buried_depth  " +
                "from daq_mv_pipe_node wc " +
                "where wc.active=1 and wc.approve_status=2 ");
        if(StringUtils.isNotBlank(projectOid)){
            bufferSql.append(" and wc.project_oid = ? ");
            args.add(projectOid);
        }
        if(StringUtils.isNotBlank(pipeNodeType)){
            bufferSql.append(" and wc.pipe_node_type = ? ");
            args.add(pipeNodeType);
        }
        List<Map<String,Object>> result = baseJdbcDao.queryForList(bufferSql.toString(), args.toArray());
        return result;
    }
}
