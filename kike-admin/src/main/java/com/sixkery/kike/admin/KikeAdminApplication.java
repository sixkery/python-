package com.sixkery.kike.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author sixkery
 */
@SpringBootApplication
@MapperScan("com.sixkery.kike.admin.mapper")
@ComponentScan(value = "com.sixkery.kike.common")
@EnableSwagger2
public class KikeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KikeAdminApplication.class, args);
    }

}
