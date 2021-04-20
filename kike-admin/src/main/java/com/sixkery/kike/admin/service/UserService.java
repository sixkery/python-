package com.sixkery.kike.admin.service;

import java.util.Map;

/**
 * @author sixkery
 */
public interface UserService {
    /**
     * 登录之后获取用户信息
     *
     * @return 用户菜 单角色
     */
    Map<String, Object> userInfo();
}
