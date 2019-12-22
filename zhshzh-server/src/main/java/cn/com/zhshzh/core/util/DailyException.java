package cn.com.zhshzh.core.util;

/**
 * 日志系统异常
 *
 * @author WBT
 * @since 2019/12/16
 */
public class DailyException extends Exception {
    /**
     * 构造方法
     *
     * @param msg 详细信息
     */
    public DailyException(String msg) {
        super(msg);
    }
}
