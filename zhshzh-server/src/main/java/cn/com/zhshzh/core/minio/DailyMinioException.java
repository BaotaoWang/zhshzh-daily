package cn.com.zhshzh.core.minio;

/**
 * minio文件系统异常
 *
 * @author WBT
 * @since 2020/03/12
 */
public class DailyMinioException extends Exception {
    public DailyMinioException() {
        super();
    }

    public DailyMinioException(String message) {
        super(message);
    }

    public DailyMinioException(String message, Throwable cause) {
        super(message, cause);
    }
}
