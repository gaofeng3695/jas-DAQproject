package cn.jasgroup.jasframework.acquisitiondata.statistics.comm;


import com.google.common.collect.Lists;

import javax.xml.transform.Source;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.*;

/**
 * 统计相关的工具类
 *
 * @author xiefayang
 * 2018/9/18 15:19
 */
public class StatsUtils {

    /**
     * Double类型精确求和
     * @param value1 value1
     * @param value2 value2
     * @return Double
     */
    public static Double sumExact(Double value1, Double value2) {
        BigDecimal b1 = new BigDecimal(value1.toString());
        BigDecimal b2 = new BigDecimal(value2.toString());
        return b1.add(b2).doubleValue();
    }

    public static Double sumExact(Double value1, Double value2, Double value3) {
        return sumExact(sumExact(value1, value2), value3);
    }

    /**
     * 浮点类型求和(不保证精确)
     * @param values 浮点数集合
     * @return Double
     */
    public static Double sum(List<Double> values) {
        return values.stream().mapToDouble(s -> (s == null ? 0d : s)).sum();
    }

    /**
     * 浮点类型求和(精确)
     * @param values 浮点数集合
     * @return Double
     */
    public static Double sumExact(List<Double> values) {
        return values.stream().map(BigDecimal::new).reduce(BigDecimal::add).map(BigDecimal::doubleValue).orElse(0d);
    }

    public static Long strToDateLong(String dateStr, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0L;
    }

    public static String timestampToDateStr(long timestamp, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format( new Date(timestamp));
    }

    public static String dateToDateStr(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }


    public static List<String> genContinuityYearMonthStr(Date startDate, Date endDate) {
        LocalDate startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        endLocalDate = endLocalDate.with(TemporalAdjusters.firstDayOfMonth());

        long months = startLocalDate.until(endLocalDate, ChronoUnit.MONTHS);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
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

}
