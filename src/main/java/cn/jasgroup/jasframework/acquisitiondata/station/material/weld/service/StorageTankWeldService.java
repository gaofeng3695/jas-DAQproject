package cn.jasgroup.jasframework.acquisitiondata.station.material.weld.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jasgroup.jasframework.acquisitiondata.station.material.weld.dao.StorageTankWeldDao;

import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Author cuixianing
 * @Date 2019/1/7 9:50
 * @Version 1.0
 **/
@Service
public class StorageTankWeldService {

    @Autowired
    private StorageTankWeldDao storageTankWeldDao;

    /**
     * <p>功能描述:查询项目下储罐焊缝列表</p>
     * <p> cuixianing</p>
     * @param pipeStationOid
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     * @since JDK1.8
     * <p>创建日期:2019/1/7 10:01</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    public List<Map<String,Object>> getStorageTankWeldList(String pipeStationOid){
        return storageTankWeldDao.getStorageTankWeldList(pipeStationOid);
    }

}
