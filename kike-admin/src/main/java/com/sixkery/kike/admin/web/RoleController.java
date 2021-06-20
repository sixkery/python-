package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.service.RoleService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.*;

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
     * @param pageNum  页码
     * @param pageSize 每页大小
     * @param keyword  搜索关键词
     * @return 角色信息
     */
    @GetMapping("/list")
    public ApiResponses<Object> list(@RequestParam Integer pageNum, @RequestParam Integer pageSize,
                                     @RequestParam(required = false) String keyword) {
        return ApiResponses.ok(roleService.list(pageNum, pageSize, keyword));
    }

    /**
     * 查询全部角色信息
     *
     * @return 角色信息
     */
    @GetMapping("/listAll")
    public ApiResponses<Object> listAll() {
        return ApiResponses.ok(roleService.listAll());
    }

    /**
     * 更新角色的启用状态
     *
     * @param id     角色的 ID
     * @param status 角色 状态
     * @return 是否更新成功
     */
    @PostMapping("/updateStatus/{id}")
    public ApiResponses<Object> updateStatus(@PathVariable Long id, @RequestBody Integer status) {
        Integer count = roleService.updateStatus(id, status);
        if (count > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }
    }


}
