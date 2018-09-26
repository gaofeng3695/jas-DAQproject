package cn.jasgroup.jasframework.acquisitiondata;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description: none
 *
 * @author xiefayang
 * 2018/9/11 15:09
 */
public class BaseTestSuite {

    @Before
    public void setUp() throws Exception {
        if (beanFactory == null) {
            beanFactory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        }
    }

    @After
    public void tearDown() throws Exception {

    }

    protected SessionFactory sessionFactory;
    protected Session session;
    protected BeanFactory beanFactory;
}
