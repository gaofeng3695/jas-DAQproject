package cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao;

import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.entity.DaqWeldcodeRegular;
import cn.jasgroup.jasframework.dataaccess.base.BaseJdbcDao;
import cn.jasgroup.jasframework.dataaccess3.core.BaseJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
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

    public List<DaqWeldcodeRegular> getDaqWeldcodeRegularList(){
        //String sql = ""

        return null;
    }
}
