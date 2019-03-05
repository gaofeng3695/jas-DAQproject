package cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.controller;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.dao.entity.DaqWeldcodeRegular;
import cn.jasgroup.jasframework.acquisitiondata.weld.weldcoderegular.service.DaqWeldcodeRegularService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>类描述：</p>。
 *
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年03月05日 10:17。</p>
 */
@RestController
@RequestMapping("daq/weldcodeRegular")
public class DaqWeldcodeRegularController {

    @Resource
    private DaqWeldcodeRegularService daqWeldcodeRegularService;


    /**
     * <p>功能描述: 查询焊口规则列表。</p>
     * <p> cuixianing。</p>
     * @param request, param
     * @since JDK1.8
     * <p>创建日期:2019/3/5 10:35</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    @RequestMapping("/getWeldcodeRegularList")
    public Object getWeldcodeRegularList(HttpServletRequest request, @RequestBody Map<String,String> param){
        ListResult<DaqWeldcodeRegular> result = null;
        try {
            String projectOid = param.get("projectOid");
            List<DaqWeldcodeRegular> rows = this.daqWeldcodeRegularService.getDaqWeldcodeRegularList(projectOid);
            result = new ListResult<>(1, "200", "ok", rows);
        } catch (Exception e) {
            result = new ListResult<>(-1, "400", "error");
            e.printStackTrace();
        }
        return result;
    }
}
