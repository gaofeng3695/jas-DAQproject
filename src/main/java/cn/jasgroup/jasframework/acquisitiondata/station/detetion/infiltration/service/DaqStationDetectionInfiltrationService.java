package cn.jasgroup.jasframework.acquisitiondata.station.detetion.infiltration.service;

import cn.jasgroup.jasframework.acquisitiondata.station.detetion.infiltration.dao.DaqStationDetectionInfiltrationSubDao;
import cn.jasgroup.jasframework.acquisitiondata.station.detetion.infiltration.query.bo.DaqStationDetectionInfiltrationBo;
import cn.jasgroup.jasframework.acquisitiondata.station.detetion.infiltration.query.bo.DaqStationDetectionInfiltrationSubBo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DaqStationDetectionInfiltrationService {

    @Autowired
    private DaqStationDetectionInfiltrationSubDao daqStationDetectionInfiltrationSubDao;

    /**
     * <p>功能描述：向DaqStationDetectionInfiltrationBo实体插入 infiltrationSubList字段</p>
     * <p> cuixianing</p>
     * @param daqStationDetectionInfiltrationBo
     * @return void
     * @since JDK1.8
     * <p>创建日期:2019/1/14 14:32</p>
     * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]</p>
     */
    @Transactional
    public void injectinfiltrationSubList(DaqStationDetectionInfiltrationBo daqStationDetectionInfiltrationBo){
        if(StringUtils.isNotBlank(daqStationDetectionInfiltrationBo.getOid())){
            String parentOid = daqStationDetectionInfiltrationBo.getOid();
            List<DaqStationDetectionInfiltrationSubBo> infiltrationSubList = daqStationDetectionInfiltrationSubDao.getDaqStationDetectionInfiltrationSubListByParentOid(parentOid);
            daqStationDetectionInfiltrationBo.setInfiltrationSubList(infiltrationSubList);
        }
    }
}
