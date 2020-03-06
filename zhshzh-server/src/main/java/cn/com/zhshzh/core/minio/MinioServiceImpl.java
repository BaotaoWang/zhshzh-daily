package cn.com.zhshzh.core.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    private MinioServiceImpl(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

}
