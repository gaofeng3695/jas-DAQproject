package cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.service;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.DaqWeldCodeRegularDao;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.entity.DaqWeldCodeRegular;
import cn.jasgroup.jasframework.base.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>类描述：</p>。
 *
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年03月04日 14:51。</p>
 */
@Service
public class DaqWeldCodeRegularService extends BaseService {

    @Resource
    private DaqWeldCodeRegularDao daqWeldCodeRegularDao;

    /**
     * <p>功能描述: 查询焊口规则列表。</p>
     * <p> cuixianing。</p>
     * @param projectOid
     * @since JDK1.8
     * <p>创建日期:2019/3/4 15:56</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public List<Map<String,Object>> getDaqWeldCodeRegularList(String projectOid){
        List<Map<String,Object>> DaqWeldCodeRegularList = daqWeldCodeRegularDao.getDaqWeldCodeRegularList(projectOid);
        return DaqWeldCodeRegularList;
    }
}
