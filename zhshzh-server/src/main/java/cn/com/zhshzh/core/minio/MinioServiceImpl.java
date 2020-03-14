package cn.com.zhshzh.core.minio;

import cn.com.zhshzh.core.util.FileUtil;
import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.messages.DeleteError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * minio文件系统service
 *
 * @author WBT
 * @since 2020/03/06
 */
@Component
public class MinioServiceImpl implements MinioService {
    /**
     * minio 桶名（根目录）
     */
    @Value("${minio.bucketName}")
    private String bucketName;

    private MinioClient minioClient;

    @Autowired
    void setMinioClient(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    /**
     * 向minio中保存文件
     *
     * @param objectName
     * @param stream
     * @param size
     * @param contentType
     */
    @Override
    public void putFile(String objectName, InputStream stream, Long size, String contentType) throws DailyMinioException {
        try {
            minioClient.putObject(bucketName, objectName, stream, size, null, null, contentType);
        } catch (InvalidBucketNameException | ErrorResponseException | IOException | NoSuchAlgorithmException
                | InsufficientDataException | InvalidKeyException | NoResponseException | XmlPullParserException
                | InternalException | InvalidArgumentException | InvalidResponseException e) {
            throw new DailyMinioException(e.getMessage(), e);
        }
    }

    /**
     * 从minio中取文件，返回流
     *
     * @param objectName
     * @return
     * @throws DailyMinioException
     */
    @Override
    public InputStream getStreamFile(String objectName) throws DailyMinioException {
        return this.getMinioObject(objectName);
    }

    /**
     * 从minio中取文件，返回base64
     *
     * @param objectName
     * @return
     * @throws DailyMinioException
     */
    @Override
    public String getBase64File(String objectName) throws DailyMinioException {
        InputStream inputStream = this.getMinioObject(objectName);
        String base64String = "";
        try {
            base64String = FileUtil.streamToBase64(inputStream);
        } catch (IOException e) {
            throw new DailyMinioException(e.getMessage(), e);
        }
        return base64String;
    }

    /**
     * 删除minio中的文件
     *
     * @param objectName
     * @throws DailyMinioException
     */
    @Override
    public void removeFile(String objectName) throws DailyMinioException {
        try {
            minioClient.removeObject(bucketName, objectName);
        } catch (InvalidBucketNameException | ErrorResponseException | IOException | NoSuchAlgorithmException
                | InsufficientDataException | InvalidKeyException | NoResponseException | XmlPullParserException
                | InternalException | InvalidArgumentException | InvalidResponseException e) {
            throw new DailyMinioException(e.getMessage(), e);
        }
    }

    /**
     * 批量删除minio中的文件
     *
     * @param objectNames
     * @throws DailyMinioException
     */
    @Override
    public void removeFiles(List<String> objectNames) throws DailyMinioException {
        Iterable<Result<DeleteError>> deleteErrors = minioClient.removeObjects(bucketName, objectNames);
        System.out.println(deleteErrors.toString());
    }

    /**
     * 获取minio中的文件
     *
     * @param objectName
     * @return
     * @throws DailyMinioException
     */
    private InputStream getMinioObject(String objectName) throws DailyMinioException {
        InputStream inputStream = null;
        try {
            inputStream = minioClient.getObject(bucketName, objectName);
        } catch (InvalidBucketNameException | ErrorResponseException | IOException | NoSuchAlgorithmException
                | InsufficientDataException | InvalidKeyException | NoResponseException | XmlPullParserException
                | InternalException | InvalidArgumentException | InvalidResponseException e) {
            throw new DailyMinioException(e.getMessage(), e);
        }
        return inputStream;
    }
}
