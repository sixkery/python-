package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.UserDto;
import com.sixkery.kike.common.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author sixkery
 */
public interface ResourceService {

    /**
     * 查询全部资源
     *
     * @return 资源
     */
    List<UserDto> findAll();

}
