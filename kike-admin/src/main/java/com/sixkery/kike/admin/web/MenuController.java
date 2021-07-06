package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.dto.MenuDto;
import com.sixkery.kike.admin.service.MenuService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜单控制器
 *
 * @author: sixkery
 * @date:2021/4/5
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 查询菜单 tree
     *
     * @return 角色信息
     */
    @GetMapping("/treeList")
    public ApiResponses<Object> treeList() {
        return ApiResponses.ok(menuService.treeList());
    }

    /**
     * 分页查询菜单
     *
     * @param pageSize 每页大小
     * @param pageNum  第几页
     * @return 菜单信息
     */
    @GetMapping("/list/{parentId}")
    public ApiResponses<Object> List(@PathVariable Long parentId,
                                     @RequestParam(defaultValue = "5") Integer pageSize,
                                     @RequestParam(defaultValue = "1") Integer pageNum) {
        return ApiResponses.ok(menuService.list(parentId, pageSize, pageNum));
    }

    /**
     * 根据 ID 查询菜单
     *
     * @param id id
     * @return 菜单信息
     */
    @GetMapping("/{id}")
    public ApiResponses<Object> List(@PathVariable Long id) {
        return ApiResponses.ok(menuService.findOne(id));
    }


    /**
     * 根据 ID 更新菜单
     *
     * @param id      id
     * @param menuDto 入参数据
     * @return 更新条数
     */
    @PostMapping("/update/{id}")
    public ApiResponses<Object> update(@PathVariable Long id, @RequestBody MenuDto menuDto) {
        return ApiResponses.ok(menuService.update(id, menuDto));
    }

    /**
     * 创建菜单
     *
     * @param menuDto 入参数据
     * @return 新增条数
     */
    @PostMapping("/create")
    public ApiResponses<Object> create(@RequestBody MenuDto menuDto) {
        return ApiResponses.ok(menuService.create(menuDto));
    }

    /**
     * 删除菜单
     *
     * @param id 入参数据
     * @return 删除条数
     */
    @PostMapping("/delete/{id}")
    public ApiResponses<Object> deleted(@PathVariable Long id) {
        return ApiResponses.ok(menuService.delete(id));
    }

}
