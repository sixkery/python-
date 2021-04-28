package com.sixkery.kike.admin.service;

import com.sixkery.kike.admin.dto.UserDto;

import java.util.List;
import java.util.Map;

/**
 * @author sixkery
 */
public interface UserService {
    /**
     * 登录之后获取用户信息
     *
     * @return 用户菜单 角色
     */
    Map<String, Object> userInfo();


    /**
     * 查询全部用户信息
     *
     * @return 用户信息
     */
    List<UserDto> findAll();

}
