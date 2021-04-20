package com.sixkery.kike.admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class KikeAdminApplicationTests {

    @Test
    void contextLoads() {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println("encode = " + encode);
    }

}
