package cn.com.zhshzh.core.minio;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio文件系统配置
 *
 * @author WBT
 * @since 2020/03/06
 */
@Configuration
public class MinioConfiguration {
    /**
     * minio 服务地址 http://ip:port
     */
    @Value("${minio.endpoint}")
    private String endpoint;

    /**
     * minio 账号
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio 密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    private static final Logger logger = LogManager.getLogger(MinioConfiguration.class);

    /**
     * 将MinioClient注入到spring容器中
     *
     * @return
     */
    @Bean
    MinioClient createMinioClient() {
        MinioClient minioClient = null;
        try {
            minioClient = new MinioClient(endpoint, accessKey, secretKey);
        } catch (InvalidEndpointException | InvalidPortException e) {
            logger.error(e.getMessage(), e);
        }
        return minioClient;
    }
}
