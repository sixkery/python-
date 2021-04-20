package com.sixkery.kike.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sixkery
 */
@SpringBootApplication
@MapperScan("com.sixkery.kike.admin.mapper")
public class KikeAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(KikeAdminApplication.class, args);
    }

}
