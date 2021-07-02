package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.entity.system.ResourceDo;

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

}
