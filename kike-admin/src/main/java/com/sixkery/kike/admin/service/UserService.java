package com.sixkery.kike.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.sixkery.kike.admin.web.dto.form.UserDTO;
import com.sixkery.kike.admin.entity.system.UserDO;

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
     * 登录
     * @param userDTO 用户名 密码
     * @return 结果
     */
    UserDTO login(UserDTO userDTO);

    /**
     * 分页查询用户信息
     *
     * @return 返回结果
     */
    PageInfo<UserDO> findAll();

}
