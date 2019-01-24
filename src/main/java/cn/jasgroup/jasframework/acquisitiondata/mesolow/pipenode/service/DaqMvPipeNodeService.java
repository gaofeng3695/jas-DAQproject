package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.service;

import cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.dao.DaqMvPipeNodeDao;
import cn.jasgroup.jasframework.engine.jdbc.service.CommonDataJdbcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class DaqMvPipeNodeService extends CommonDataJdbcService {

    @Autowired
    private DaqMvPipeNodeDao daqMvPipeNodeDao;

    /**
     * <p>功能描述: 通过项目与点类型查询节点里列表。</p>
     * <p> cuixianing。</p>
     * @param projectOid, pipeNodeCode
     * @since JDK1.8
     * <p>创建日期:2019/1/24 15:02</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public List<Map<String,Object>> getDaqMvPipeNodeList(String projectOid, String pipeNodeType){
        return daqMvPipeNodeDao.getDaqMvPipeNodeList(projectOid,pipeNodeType);
    }
}
