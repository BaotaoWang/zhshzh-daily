package cn.com.zhshzh.common.util;

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
    private static String DATE_TIME_PATTERN = "YYYY-MM-dd HH:mm:ss";
    private static String DATE_PATTERN = "YYYY-MM-dd";

    /**
     * 将日期字符串格式化为日期
     *
     * @param source 日期字符串
     * @return 转换后的日期
     * @throws ParseException
     */
    public static Date getDateTime(String source) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        Date date = sdf.parse(source);
        return date;
    }

    /**
     * 将日期字符串格式化为日期
     *
     * @param source 日期字符串
     * @return 转换后的日期
     * @throws ParseException
     */
    public static Date getDate(String source) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        Date date = sdf.parse(source);
        return date;
    }

    /**
     * 将日期格式化为字符串(yyyy-MM-dd HH:mm:ss)
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String getDateTimeString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        String dateString = sdf.format(date);
        return dateString;
    }

    /**
     * 将日期格式化为字符串(yyyy-MM-dd)
     *
     * @param date 日期
     * @return 格式化后的日期字符串
     */
    public static String getDateString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        String dateString = sdf.format(date);
        return dateString;
    }
}
