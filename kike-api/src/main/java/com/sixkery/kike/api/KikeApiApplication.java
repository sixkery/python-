package com.sixkery.kike.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sixkery
 */
@SpringBootApplication
@MapperScan("com.sixkery.kike.api.mapper")
public class KikeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KikeApiApplication.class, args);
    }

}
