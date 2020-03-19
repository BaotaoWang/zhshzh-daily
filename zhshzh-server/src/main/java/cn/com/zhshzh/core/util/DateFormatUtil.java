package cn.com.zhshzh.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期操作工具类
 *
 * @author WBT
 * @since 2019/11/28
 */
public class DateFormatUtil {
    private static final Logger logger = LogManager.getLogger(DateFormatUtil.class);

    private static String DATE_TIME_PATTERN = "YYYY-MM-dd HH:mm:ss";
    private static String DATE_PATTERN = "YYYY-MM-dd";
    private static String DATE_BACKSLASH_PATTERN = "YYYY/MM/dd";

    /**
     * 将日期字符串格式化为日期
     *
     * @param source 日期字符串
     * @return 转换后的日期
     */
    public static LocalDateTime getDateTime(String source) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return LocalDateTime.parse(source, sdf);
    }

    /**
     * 将日期字符串格式化为日期
     *
     * @param source 日期字符串
     * @return 转换后的日期
     */
    public static LocalDate getDate(String source) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(source, sdf);
    }

    /**
     * 将日期格式化为字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @param localDateTime 日期
     * @return 格式化后的日期字符串
     */
    public static String getDateTimeString(LocalDateTime localDateTime) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return sdf.format(localDateTime);
    }

    /**
     * 将日期格式化为字符串(yyyy-MM-dd)
     *
     * @param localDate 日期
     * @return 格式化后的日期字符串
     */
    public static String getDateString(LocalDate localDate) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return sdf.format(localDate);
    }

    /**
     * 将日期格式化为字符串(yyyy/MM/dd)
     *
     * @param localDate 日期
     * @return 格式化后的日期字符串
     */
    public static String getBackslashDateString(LocalDate localDate) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern(DATE_BACKSLASH_PATTERN);
        return sdf.format(localDate);
    }
}
