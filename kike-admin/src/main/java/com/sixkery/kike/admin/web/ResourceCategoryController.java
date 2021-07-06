package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.service.ResourceCategoryService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资源控制器
 *
 * @author: sixkery
 * @date:2021/4/5
 */
@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    @Resource
    private ResourceCategoryService resourceCategoryService;

    /**
     * 分页查询资源
     *
     * @return 资源信息
     */
    @GetMapping("/listAll")
    public ApiResponses<Object> List() {
        return ApiResponses.ok(resourceCategoryService.findAll());
    }

//    /**
//     * 根据 ID 查询菜单
//     *
//     * @param id id
//     * @return 菜单信息
//     */
//    @GetMapping("/{id}")
//    public ApiResponses<Object> List(@PathVariable Long id) {
//        return ApiResponses.ok(menuService.findOne(id));
//    }
//
//
//    /**
//     * 根据 ID 更新菜单
//     *
//     * @param id      id
//     * @param menuDto 入参数据
//     * @return 更新条数
//     */
//    @PostMapping("/update/{id}")
//    public ApiResponses<Object> update(@PathVariable Long id, @RequestBody MenuDto menuDto) {
//        return ApiResponses.ok(menuService.update(id,menuDto));
//    }
//
//    /**
//     * 创建菜单
//     *
//     * @param menuDto 入参数据
//     * @return 新增条数
//     */
//    @PostMapping("/create")
//    public ApiResponses<Object> create(@RequestBody MenuDto menuDto) {
//        return ApiResponses.ok(menuService.create(menuDto));
//    }
//
//    /**
//     * 删除菜单
//     *
//     * @param id 入参数据
//     * @return 删除条数
//     */
//    @PostMapping("/delete/{id}")
//    public ApiResponses<Object> deleted(@PathVariable Long id) {
//        return ApiResponses.ok(menuService.delete(id));
//    }

}
