package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.ResourceDto;
import com.sixkery.kike.admin.entity.system.ResourceDo;
import com.sixkery.kike.common.PageInfo;

import java.util.List;

/**
 * @author sixkery
 */
public interface ResourceService {

    /**
     * 查询全部资源
     *
     * @return 资源
     */
    List<ResourceDo> findAll();

    /**
     * 分页查询资源
     *
     * @param pageSize 每页大小
     * @param pageNum  第几页
     * @return 资源信息
     */
    PageInfo<ResourceDto> list(Integer pageSize, Integer pageNum);

}
