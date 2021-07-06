package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.service.ResourceService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 资源控制器
 *
 * @author: sixkery
 * @date:2021/4/5
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    /**
     * 分页查询资源
     *
     * @param pageSize 每页大小
     * @param pageNum  第几页
     * @return 资源信息
     */
    @GetMapping("/list")
    public ApiResponses<Object> List(@RequestParam(defaultValue = "5") Integer pageSize,
                                     @RequestParam(defaultValue = "1") Integer pageNum) {
        return ApiResponses.ok(resourceService.list(pageSize, pageNum));
    }

    /**
     * 查询全部资源
     *
     * @return 资源信息
     */
    @GetMapping("/listAll")
    public ApiResponses<Object> findAll() {
        return ApiResponses.ok(resourceService.findAll());
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
