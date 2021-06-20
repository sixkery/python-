package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.dto.UserDto;
import com.sixkery.kike.admin.service.UserService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
     *
     * @return 用户的信息
     */
    @GetMapping("/info")
    public ApiResponses<Object> userInfo() {
        return ApiResponses.ok(userService.userInfo());
    }


    /**
     * 查询全部用户信息
     *
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param keyword  搜索关键词
     * @return 用户信息
     */
    @GetMapping("/list")
    public ApiResponses<Object> findAll(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam(required = false) String keyword) {
        return ApiResponses.ok(userService.findAll(pageNum, pageSize, keyword));
    }

    /**
     * 新增用户的信息
     *
     * @param admin 用户信息
     * @return 是否新增成功
     */
    @PostMapping("/register")
    public ApiResponses<Object> register(@RequestBody UserDto admin) {
        Integer count = userService.register(admin);
        if (count > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }
    }

    /**
     * 更新用户的信息
     *
     * @param id    用户 id
     * @param admin 用户信息
     * @return 是否更新成功
     */
    @PostMapping("/update/{id}")
    public ApiResponses<Object> updateUser(@PathVariable Long id, @RequestBody UserDto admin) {
        Integer count = userService.updateUser(id, admin);
        if (count > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }
    }

    /**
     * 更新用户的启用状态
     *
     * @param id     用户的 ID
     * @param status 用户 状态
     * @return 是否更新成功
     */
    @PostMapping("/updateStatus/{id}")
    public ApiResponses<Object> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        Integer count = userService.updateStatus(id, status);
        if (count > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }
    }

    /**
     * 获取指定用户的角色
     *
     * @param id 用户 id
     * @return 角色信息
     */
    @GetMapping("/role/{id}")
    public ApiResponses<Object> getRoleList(@PathVariable Long id) {
        return ApiResponses.ok(userService.getRoleList(id));
    }

    /**
     * 更新用户的角色
     *
     * @param adminId 用户 id
     * @param roleIds 角色列表
     * @return 是否更新成功
     */
    @PostMapping("/role/update")
    public ApiResponses<Object> updateRole(@RequestParam Long adminId, @RequestParam List<Long> roleIds) {
        Integer count = userService.updateRole(adminId, roleIds);
        if (count > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }
    }

    /**
     * 退出登录
     *
     * @return ok
     */
    @GetMapping("/logout")
    public ApiResponses<Object> logout() {
        return ApiResponses.ok(null);
    }

}
