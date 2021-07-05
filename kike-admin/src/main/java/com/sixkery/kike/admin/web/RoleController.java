package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.admin.service.RoleService;
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
    public ApiResponses<Object> updateStatus(@PathVariable Long id, @RequestParam Integer status) {
        Integer count = roleService.updateStatus(id, status);
        if (count > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }
    }


    /**
     * 查询角色拥有的菜单
     *
     * @param id 角色 ID
     * @return 角色信息
     */
    @GetMapping("/listMenu/{id}")
    public ApiResponses<Object> listMenu(@PathVariable Long id) {
        return ApiResponses.ok(roleService.listMenu(id));
    }

    /**
     * 新增角色
     *
     * @param roleDto 角色信息
     * @return 新增个数
     */
    @PostMapping("/create")
    public ApiResponses<Object> create(@RequestBody RoleDto roleDto) {
        Integer insert = roleService.create(roleDto);
        if (insert > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }
    }


    /**
     * 删除角色
     *
     * @param ids 角色ids
     * @return 删除个数
     */
    @PostMapping("/delete")
    public ApiResponses<Object> delete(@RequestParam List<Long> ids) {
        Integer insert = roleService.delete(ids);
        if (insert > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }
    }

    /**
     * 编辑角色
     *
     * @param roleDto 角色信息
     * @param id      角色 ID
     * @return 角色信息
     */
    @PostMapping("/update/{id}")
    public ApiResponses<Object> update(@RequestBody RoleDto roleDto, @PathVariable Long id) {
        Integer update = roleService.update(roleDto, id);
        if (update > 0) {
            return ApiResponses.ok();
        } else {
            return ApiResponses.failed();
        }

    }

    /**
     * 角色分配菜单
     *
     * @param roleId  角色 id
     * @param menuIds 菜单 IDs
     * @return 是否分配成功
     */
    @PostMapping("/allocMenu")
    public ApiResponses<Object> allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        return ApiResponses.ok(roleService.allocMenu(roleId, menuIds));
    }

}
