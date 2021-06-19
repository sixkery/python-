package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.RoleDto;
import com.sixkery.kike.admin.dto.UserDto;
import com.sixkery.kike.common.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author sixkery
 */
public interface RoleService {

    /**
     * 查询全部角色信息
     *

     * @return 用户信息
     */
    List<RoleDto> listAll();

}
