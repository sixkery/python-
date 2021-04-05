package com.sixkery.kike.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt 参数配置
 * @author: sixkery
 * @date:2021/4/3
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String header;
    /**
     * 令牌前缀
     */
    private String tokenPrefix;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 过期时间 单位毫秒
     */
    private Long expiration;


}
