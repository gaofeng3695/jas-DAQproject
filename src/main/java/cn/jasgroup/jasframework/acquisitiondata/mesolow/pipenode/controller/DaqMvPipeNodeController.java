package cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.controller;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.mesolow.pipenode.service.DaqMvPipeNodeService;
import cn.jasgroup.jasframework.base.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>类描述：</p>。
 *
 * @author cuixianing 。
 * @version v1.0.0.1。
 * @since JDK1.8。
 * <p>创建日期：2019年01月24日 14:34。</p>
 */
@RestController
@RequestMapping("/jdbc/commonData/daqMvPipeNode")
public class DaqMvPipeNodeController extends BaseController {

    @Autowired
    private DaqMvPipeNodeService daqMvPipeNodeService;

    /**
     * <p>功能描述: 通过项目与点类型查询节点里列表。</p>
     * <p> cuixianing。</p>
     * @param
     * @since JDK1.8
     * <p>创建日期:2019/1/24 15:02</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    @RequestMapping(value="/getDaqMvPipeNodeList", method = RequestMethod.POST)
    @ResponseBody
    public Object getDaqMvPipeNodeList(HttpServletRequest request, @RequestBody Map<String,String> param){
        ListResult<Map<String, Object>> result= null;
        try{
            String projectOid = param.get("projectOid");
            String pipeNodeType = param.get("pipeNodeType");

            List<Map<String,Object>> daqMvPipeNodeList = this.daqMvPipeNodeService.getDaqMvPipeNodeList(projectOid,pipeNodeType);
            result = new ListResult<>(1,"200","ok",daqMvPipeNodeList);
        }catch(Exception e){
            result = new ListResult<>(-1,"400","error");
            e.printStackTrace();
        }
        return result;
    }

}
