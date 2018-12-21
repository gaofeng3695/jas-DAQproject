package cn.jasgroup.jasframework.acquisitiondata.statistics.normal.comm;


import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

/**
 * 统计相关的工具类
 *
 * @author xiefayang
 * 2018/9/18 15:19
 */
public class StatsUtils {

    /**
     * Double类型求和(精确)
     * @param value1 value1
     * @param value2 value2
     * @return Double
     */
    public static Double sumExact(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(value1.toString());
        BigDecimal b2 = new BigDecimal(value2.toString());
        return b1.add(b2).doubleValue();
    }


    /**
     * Double类型求和(精确)
     * @return Double
     */
    public static Double sumExact(Double value1, Double value2, Double value3) {
        return sumExact(sumExact(value1, value2), value3);
    }


    /**
     * Double类型求和(精确)
     * @param values 浮点数集合
     * @return Double
     */
    public static Double sumExact(List<Double> values) {
        values.removeIf(Objects::isNull);
        return values.stream().map(BigDecimal::new).reduce(BigDecimal::add).map(BigDecimal::doubleValue).orElse(0d);
    }


    /**
     * Double类型求和(不保证精确)
     * @param values 浮点数集合
     * @return Double
     */
    public static Double sum(List<Double> values) {
        return values.stream().mapToDouble(s -> (s == null ? 0d : s)).sum();
    }


    /**
     * 时间日期类型 -> timestamp
     * @param dateStr 日期字符串
     * @param format 日期格式化
     * @return Long
     */
    public static Long strToDateLong(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }


    /**
     * timestamp -> 时间日期类型
     * @param timestamp 日期timestamp
     * @param format 日期格式化
     * @return Long
     */
    public static String timestampToDateStr(long timestamp, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format( new Date(timestamp));
    }


    /**
     * 生成连续的日期(年月)字符串集合
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param format 日期格式化
     * @return 日期字符串集合
     */
    public static List<String> genContinuityYearMonthStr(Date startDate, Date endDate, String format) {
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        endLocalDate = endLocalDate.with(TemporalAdjusters.firstDayOfMonth());

        long months = startLocalDate.until(endLocalDate, ChronoUnit.MONTHS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        List<String> yearMonthList = Lists.newArrayList();

        LocalDate localDate = startLocalDate;
        for (int i = 0; i < months+1; i++) {
            yearMonthList.add(localDate.format(formatter));
            localDate = localDate.plusMonths(1);
        }

        return yearMonthList;
    }


    public static List<String> genContinuityDayStr(String startDateStr, String endDateStr, String dateFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
           return genContinuityDayStr(format.parse(startDateStr), format.parse(endDateStr), dateFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    /**
     * 生成连续的日期(年月日)字符串集合
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param dateFormat 日期格式化
     * @return 日期字符串集合
     */
    public static List<String> genContinuityDayStr(Date startDate, Date endDate, String dateFormat) {
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long days = startLocalDate.until(endLocalDate, ChronoUnit.DAYS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

        List<String> dayList = Lists.newArrayList();
        LocalDate localDate = startLocalDate;
        for (long i = 0; i < days+1; i++) {
            dayList.add(localDate.format(formatter));
            localDate = localDate.plusDays(1);
        }
        return dayList;
    }


    /**
     * 获取指定日期所在周的第一天
     * @param date 日期
     * @param format 日期格式
     * @return String类型日期
     */
    public static String getStartDayOfWeek(String date, String format) {
        LocalDate inputDate = LocalDate.parse(date);
        TemporalAdjuster firstOfWeek = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.minusDays(localDate.getDayOfWeek().getValue()- DayOfWeek.MONDAY.getValue()));
        return DateTimeFormatter.ofPattern(format).format(inputDate.with(firstOfWeek));
    }


    /**
     * 获取指定日期所在周的第一天
     * @param date 日期
     * @param format 日期格式
     * @return String类型日期
     */
    public static String getEndDayOfWeek(String date, String format) {
        LocalDate inputDate = LocalDate.parse(date);
        TemporalAdjuster lastOfWeek = TemporalAdjusters.ofDateAdjuster(localDate -> localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
        return DateTimeFormatter.ofPattern(format).format(inputDate.with(lastOfWeek));
    }


    /**
     * 模拟postgresql的COALESCE()函数功能
     * - (expression_1, expression_2, ...,expression_n)依次参考各参数表达式，遇到非null值即停止并返回该值
     * @param values params
     * @param <T> params and return type
     * @return T
     */
    @SafeVarargs
    public static  <T> T coalesce(T... values) {
        return Arrays.stream(values).filter(Objects::nonNull).findFirst().orElse(null);
    }

    /**
     * 通过起始月字符串，获取自开始月起到终止月的连续的月份数组
     * @param dateStr
     * @param format
     * @return date
     */
    public static Object[] getMonthArray(String beginMonthStr, String endMonthStr, String format) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    	try {
			Date beginMonthDate = dateFormat.parse(beginMonthStr);
			Date endMonthDate = dateFormat.parse(endMonthStr);
			List<String> monthList = genContinuityYearMonthStr(beginMonthDate,endMonthDate,format);
			return monthList.toArray();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }


    /**
     * <p>功能描述：创建指定长度的默认值为0的double数组。</p>
      * <p> 葛建。</p>	
      * @param length
      * @param i
      * @return
      * @since JDK1.8。
      * <p>创建日期:2018年12月12日 下午1:51:58。</p>
      * <p>更新日期:[日期YYYY-MM-DD][更改人姓名][变更描述]。</p>
     */
	public static Double[] getStaticDoubleArray(int length, Double i) {
		Double[] array = new Double[length];
		for (int j = 0; j < array.length; j++) {
			array[j] = i;
		}
		return array;
	}
}
