package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.ResourceCategoryDto;

import java.util.List;

/**
 * @author sixkery
 */
public interface ResourceCategoryService {

    /**
     * 查询全部资源类型
     *
     * @return 资源类型
     */
    List<ResourceCategoryDto> findAll();

    Integer update(Long id, ResourceCategoryDto resourceCategoryDto);

    Integer delete(Long id);


}
