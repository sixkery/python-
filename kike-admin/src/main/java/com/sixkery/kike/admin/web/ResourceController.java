package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.dto.ResourceDto;
import com.sixkery.kike.admin.service.ResourceService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.*;

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
    public ApiResponses<Object> list(@RequestParam(defaultValue = "5") Integer pageSize,
                                     @RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(required = false) Long categoryId,
                                     @RequestParam(required = false) String keyword) {
        return ApiResponses.ok(resourceService.list(pageSize, pageNum,categoryId,keyword));
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


    /**
     * 创建资源
     *
     * @param resourceDto 入参数据
     * @return 新增条数
     */
    @PostMapping("/create")
    public ApiResponses<Object> create(@RequestBody ResourceDto resourceDto) {
        return ApiResponses.ok(resourceService.create(resourceDto));
    }

    /**
     * 根据 ID 更新资源
     *
     * @param id          id
     * @param resourceDto 入参数据
     * @return 更新条数
     */
    @PostMapping("/update/{id}")
    public ApiResponses<Object> update(@PathVariable Long id, @RequestBody ResourceDto resourceDto) {
        return ApiResponses.ok(resourceService.update(id, resourceDto));
    }

    /**
     * 删除资源
     *
     * @param id 入参数据
     * @return 删除条数
     */
    @PostMapping("/delete/{id}")
    public ApiResponses<Object> deleted(@PathVariable Long id) {
        return ApiResponses.ok(resourceService.delete(id));
    }

}
