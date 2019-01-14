package cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.service;

import cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.dao.DaqStationDetectionUltrasonicDao;
import cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.query.bo.DaqStationDetectionUltrasonicBo;
import cn.jasgroup.jasframework.acquisitiondata.station.detection.ultrasonic.query.bo.DaqStationDetectionUltrasonicSubBo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * <p>Table description 超声波检测(站场)Bo</p>
 * @author 陈祥思
 * @version v1.0.0.1。
 * @since JDK1.8.0_181。
 * <p>创建日期：2019-01-14 14:00:31。</p>
 */
@Service
@Transactional
public class DaqStationDetectionUltrasonicService {
    /**
     * <p>Table description 查询子表/p>
     * @author 陈祥思
     * @version v1.0.0.1。
     * @since JDK1.8.0_181。
     * <p>创建日期：2019-01-14 14:00:31。</p>
     */
    @Autowired
    private DaqStationDetectionUltrasonicDao stationDetectionUltrasonicDao;
    public void injectStationDetectionUltrasonicSubList(List<DaqStationDetectionUltrasonicBo> stationDetectionUltrasonicBoList){
        if (stationDetectionUltrasonicBoList.size()==1) {
            DaqStationDetectionUltrasonicBo stationDetectionUltrasonicBo = stationDetectionUltrasonicBoList.get(0);
            List<DaqStationDetectionUltrasonicSubBo> stationDetectionUltrasonicSubBoList = stationDetectionUltrasonicDao.queryStationDetectionUltrasonicSubList(stationDetectionUltrasonicBo.getOid());
            stationDetectionUltrasonicBo.setStationDetectionUltrasonicSubList(stationDetectionUltrasonicSubBoList);
        }
    }
}
