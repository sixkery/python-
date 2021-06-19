package com.sixkery.kike.admin;

import com.sixkery.kike.admin.dto.UserDto;
import com.sixkery.kike.admin.service.UserService;
import com.sixkery.kike.common.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class KikeAdminApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println("encode = " + encode);
        PageInfo<UserDto> all = userService.findAll(1,2);
        System.out.println("all = " + all);
    }

}
