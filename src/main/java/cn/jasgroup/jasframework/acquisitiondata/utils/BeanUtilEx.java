package cn.jasgroup.jasframework.acquisitiondata.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/****
  * 
  *<p>类描述：在Java对象的拷贝过程中，对日期类型的支持。</p>
  * @author 雷凯 。
  * @version v1.0.0.1。
  * @since JDK1.8。
  *<p>创建日期：2018年7月31日 下午4:42:08。</p>
 */
public class BeanUtilEx extends BeanUtils{
	private static Map cache = new HashMap();
    private static Log logger = LogFactory.getFactory().getInstance(BeanUtilEx.class);

    private BeanUtilEx() {
    }

    static {
        // 注册sql.date的转换器，即允许BeanUtils.copyProperties时的源目标的sql类型的值允许为空
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.sql.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlDateConverter(null), java.util.Date.class);
        ConvertUtils.register(new org.apache.commons.beanutils.converters.SqlTimestampConverter(null),
                java.sql.Timestamp.class);
        // 注册util.date的转换器，即允许BeanUtils.copyProperties时的源目标的util类型的值允许为空
    }

    public static void copyProperties(Object target, Object source) throws InvocationTargetException,
            IllegalAccessException {
        // 支持对日期copy
        org.apache.commons.beanutils.BeanUtils.copyProperties(target, source);

    }

}
