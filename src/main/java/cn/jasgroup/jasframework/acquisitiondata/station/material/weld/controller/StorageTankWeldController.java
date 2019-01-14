package cn.jasgroup.jasframework.acquisitiondata.station.material.weld.controller;

import cn.jasgroup.framework.data.result.ListResult;
import cn.jasgroup.jasframework.acquisitiondata.station.material.weld.service.StorageTankWeldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("daq/storagetankweld")
public class StorageTankWeldController {

    @Autowired
    private StorageTankWeldService storageTankWeldService;

    /**
     * <p>功能描述:查询项目下储罐焊缝列表</p>
     * <p> cuixianing</p>
     * @param param
     * @return java.lang.Object
     * @since JDK1.8
     * <p>创建日期:2019/1/7 10:02</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    @RequestMapping("getStorageTankWeldList")
    public Object getStorageTankWeldList(@RequestBody Map<String,String> param){
        String pipeStationOid = (String)param.get("pipeStationOid");
        ListResult<Map<String, Object>> result= null;
        try{
            List<Map<String, Object>> rows = this.storageTankWeldService.getStorageTankWeldList(pipeStationOid);
            result = new ListResult<>(1,"200","ok",rows);
        }catch(Exception e){
            result = new ListResult<>(-1,"400","error");
            e.printStackTrace();
        }
        return result;
    }

}
