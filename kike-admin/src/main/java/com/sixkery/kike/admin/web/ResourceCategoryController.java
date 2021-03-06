package com.sixkery.kike.admin.web;

import com.sixkery.kike.admin.dto.ResourceCategoryDto;
import com.sixkery.kike.admin.service.ResourceCategoryService;
import com.sixkery.kike.common.response.ApiResponses;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 资源分类控制器
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
    public ApiResponses<Object> list() {
        return ApiResponses.ok(resourceCategoryService.findAll());
    }

    /**
     * 根据 ID 更新资源分类
     *
     * @param id                  id
     * @param resourceCategoryDto 入参数据
     * @return 更新条数
     */
    @PostMapping("/update/{id}")
    public ApiResponses<Object> update(@PathVariable Long id, @RequestBody ResourceCategoryDto resourceCategoryDto) {
        return ApiResponses.ok(resourceCategoryService.update(id, resourceCategoryDto));
    }

    /**
     * 删除资源分类
     *
     * @param id 入参数据
     * @return 删除条数
     */
    @PostMapping("/delete/{id}")
    public ApiResponses<Object> deleted(@PathVariable Long id) {
        return ApiResponses.ok(resourceCategoryService.delete(id));
    }

}
