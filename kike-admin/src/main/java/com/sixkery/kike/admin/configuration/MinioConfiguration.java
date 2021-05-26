package com.sixkery.kike.admin.configuration;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * minio 配置
 *
 * @author sixkery
 */
@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfiguration {

    /**
     * endPoint 是一个 URL，域名，IPv4 或者 IPv6 地址
     */
    private String endpoint;

    /**
     * TCP/IP 端口号
     */
    private int port;

    /**
     * accessKey 类似于用户 ID，用于唯一标识你的账户
     */
    private String accessKey;

    /**
     * secretKey 是你账户的密码
     */
    private String secretKey;

    /**
     * 如果是 true，则用的是 https 而不是 http,默认值是 true
     */
    private Boolean secure;

    /**
     * 存储桶
     */
    private String bucketName;

    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder().endpoint(endpoint, port, secure).credentials(accessKey, secretKey).build();
    }
}
