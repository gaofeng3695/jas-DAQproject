package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.dao;

import cn.jasgroup.jasframework.acquisitiondata.BaseTestSuite;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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