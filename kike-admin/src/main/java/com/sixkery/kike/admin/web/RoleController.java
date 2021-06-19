package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 查询全部角色信息
     *
     * @return 角色信息
     */
    @GetMapping("/listAll")
    public ApiResponses<Object> listAll() {
        return ApiResponses.ok(roleService.listAll());
    }


}
