package com.sixkery.kike.common.utils.date;

import com.sixkery.kike.common.exception.ApiException;
import lombok.Data;

import java.lang.management.ManagementFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * 时间工具类
 *
 * @author sixkery
 * @date 2020/5/16
 */
public class DateUtil {

    private static final int TODAY = 1;
    private static final int SEVEN_DAY = 2;
    private static final int FIFTEEN = 3;

    /**
     * 时间格式化
     */
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    private static final String HOUR_PATTERN = "yyyy-MM-dd HH";
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String MONTH_PATTERN = "yyyy-MM";
    private static final String YEAR_PATTERN = "yyyy";
    private static final String MINUTE_ONLY_PATTERN = "mm";


    /**
     * localDate 转 Date
     *
     * @param localDate localDate
     * @return date
     */
    public static Date toDate(LocalDate localDate) {
        return toDate(localDate.atTime(LocalTime.now(ZoneId.systemDefault())));
    }

    /**
     * LocalDateTime 转 Date
     *
     * @param localDateTime localDateTime
     * @return date
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转 LocalDateTime
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }


    /**
     * 根据 1 2 3 4 获取今日，最近七天， 最近十五天， 最近三十天的时间区间
     *
     * @param inter 1 2 3 4
     * @return 2020-01-12 ---- 2020-01-05
     */
    public static DateInterval getInterval(int inter) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4);
        if (!integers.contains(inter)) {
            throw new ApiException("时间区间参数有误,只可输入 1 2 3 4");
        }
        LocalDate now = LocalDate.now();
        LocalDate beforeDay;
        if (inter == TODAY) {
            beforeDay = LocalDate.now();
        } else if (inter == SEVEN_DAY) {
            beforeDay = now.minus(7, ChronoUnit.DAYS);
        } else if (inter == FIFTEEN) {
            beforeDay = now.minus(15, ChronoUnit.DAYS);
        } else {
            beforeDay = now.minus(1, ChronoUnit.MONTHS);
        }
        DateInterval dateInterval = new DateInterval();
        String startTime = now.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
        String endTime = beforeDay.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
        dateInterval.setStartTime(startTime);
        dateInterval.setEndTime(endTime);
        return dateInterval;
    }

    @Data
    static class DateInterval {
        String startTime;
        String endTime;
    }

    /**
     * 获取服务器启动时间
     *
     * @return 启动时间
     */
    public static Date getServerStartDate() {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    public static String startPreFix(String date) {
        return date + "00:00:00";
    }


    public static String endPreFix(String date) {
        return date + "23:23:59";
    }


    public static void main(String[] args) {
        DateInterval interval = DateUtil.getInterval(4);

    }

}




