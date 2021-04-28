package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.service.UserService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户相关操作
 *
 * @author: sixkery
 * @date:2021/4/5
 */
@RestController
@RequestMapping("/admin")
public class UserAdminController {

    @Resource
    private UserService userService;

    /**
     * 获取登录用户的信息
     * @return 用户的信息
     */
    @GetMapping("/info")
    public ApiResponses<Object> userInfo() {
        return ApiResponses.ok(userService.userInfo());
    }

    /**
     * 退出登录
     * @return ok
     */
    @GetMapping("/logout")
    public ApiResponses<Object> logout() {
        return ApiResponses.ok(null);
    }

    /**
     * 查询全部用户信息
     * @return 用户信息
     */
    @GetMapping("/findAll")
    public ApiResponses<Object> findAll() {
        return ApiResponses.ok(userService.findAll());
    }



}
