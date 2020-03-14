package cn.com.zhshzh.core.minio;

import java.io.InputStream;
import java.util.List;

/**
 * minio文件系统service
 *
 * @author WBT
 * @since 2020/03/06
 */
public interface MinioService {
    /**
     * 向minio中保存文件
     *
     * @param objectName
     * @param stream
     * @param size
     * @param contentType
     */
    void putFile(String objectName, InputStream stream, Long size, String contentType) throws DailyMinioException;

    /**
     * 从minio中取文件，返回流
     *
     * @param objectName
     * @return
     * @throws DailyMinioException
     */
    InputStream getStreamFile(String objectName) throws DailyMinioException;

    /**
     * 从minio中取文件，返回base64
     *
     * @param objectName
     * @return
     * @throws DailyMinioException
     */
    String getBase64File(String objectName) throws DailyMinioException;

    /**
     * 删除minio中的文件
     *
     * @param objectName
     * @throws DailyMinioException
     */
    void removeFile(String objectName) throws DailyMinioException;

    /**
     * 批量删除minio中的文件
     *
     * @param objectNames
     * @throws DailyMinioException
     */
    void removeFiles(List<String> objectNames) throws DailyMinioException;
}
