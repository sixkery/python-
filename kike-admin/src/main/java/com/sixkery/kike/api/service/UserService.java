package com.sixkery.kike.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sixkery.kike.api.config.vo.UserVo;
import com.sixkery.kike.api.entity.system.UserDO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author sixkery
 * @since 2020-11-27
 */
public interface UserService extends IService<UserDO> {



    /**
     * 根据用户名获得用户信息
     *
     * @param username 用户名
     * @return 用户信息，密码，权限 等
     */
    public UserVo findByUsername(String username);


    /**
     * 根据手机号获得用户信息
     *
     * @param mobile 手机号
     * @return 用户信息，密码，权限 等
     */
    public UserVo findByMobile(String mobile);


    /**
     * 根据邮箱获得用户信息
     *
     * @param email 邮箱
     * @return 用户信息，密码，权限 等
     */
    public UserVo findByEmail(String email);

    Object findAll();
}
