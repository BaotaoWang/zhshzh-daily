package cn.com.zhshzh.core.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public static Date getDateTime(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将日期字符串格式化为日期
     *
     * @param source 日期字符串
     * @return 转换后的日期
     */
    public static Date getDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            return sdf.parse(source);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 将日期格式化为字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String getDateTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        return sdf.format(date);
    }

    /**
     * 将日期格式化为字符串(yyyy-MM-dd)
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String getDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        return sdf.format(date);
    }

    /**
     * 将日期格式化为字符串(yyyy/MM/dd)
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String getBackslashDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_BACKSLASH_PATTERN);
        return sdf.format(date);
    }
}
