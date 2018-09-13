package cn.jasgroup.jasframework.acquisitiondata.statistics.dao;

import cn.jasgroup.jasframework.acquisitiondata.BaseTestSuite;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.media.jai.operator.OverlayDescriptor;

import java.util.List;

import static org.junit.Assert.*;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/11 15:06
 */
public class OverallStatisticsDaoTest extends BaseTestSuite {

    private OverallStatisticsDao overallStatisticsDao;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        overallStatisticsDao = (OverallStatisticsDao) beanFactory.getBean("overallStatisticsDao");
    }

    @Test
    public void dataEntryAudit() throws Exception {

        List list = overallStatisticsDao.dataEntryAudit(null);
        System.out.println(list);
    }

}